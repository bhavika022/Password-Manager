package abc;

import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Random;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class TwoFactorAuthUtil {

    public static final int TIME_STEP_SECONDS = 30;
    private static int NUM_DIGITS_OUTPUT = 6;

    private final String blockOfZeros;

    {
        StringBuilder sb = new StringBuilder(NUM_DIGITS_OUTPUT);
        for (int i = 0; i < NUM_DIGITS_OUTPUT; i++) {
            sb.append('0');
        }
        blockOfZeros = sb.toString();
    }

    public String generateBase32Secret() {
        StringBuilder sb = new StringBuilder();
        Random random = new SecureRandom();
        for (int i = 0; i < 16; i++) {
            int val = random.nextInt(32);
            if (val < 26) {
                sb.append((char) ('A' + val));
            } else {
                sb.append((char) ('2' + (val - 26)));
            }
        }
        return sb.toString();
    }

    public String generateCurrentNumber(String secret) throws GeneralSecurityException {
        return generateCurrentNumber(secret, System.currentTimeMillis());
    }

    public String generateCurrentNumber(String secret, long currentTimeMillis) throws GeneralSecurityException {

        byte[] key = decodeBase32(secret);

        byte[] data = new byte[8];
        long value = currentTimeMillis / 1000 / TIME_STEP_SECONDS;
        for (int i = 7; value > 0; i--) {
            data[i] = (byte) (value & 0xFF);
            value >>= 8;
        }

        SecretKeySpec signKey = new SecretKeySpec(key, "HmacSHA1");
        Mac mac = Mac.getInstance("HmacSHA1");
        mac.init(signKey);
        byte[] hash = mac.doFinal(data);

        int offset = hash[hash.length - 1] & 0xF;

        long truncatedHash = 0;
        for (int i = offset; i < offset + 4; ++i) {
            truncatedHash <<= 8;
            truncatedHash |= (hash[i] & 0xFF);
        }
        truncatedHash &= 0x7FFFFFFF;

        truncatedHash %= 1000000;

        return zeroPrepend(truncatedHash, NUM_DIGITS_OUTPUT);
    }

    public String qrImageUrl(String keyId, String secret) {
        StringBuilder sb = new StringBuilder(128);
        sb.append("https://chart.googleapis.com/chart");
        sb.append("?chs=200x200&cht=qr&chl=200x200&chld=M|0&cht=qr&chl=");
        sb.append("otpauth://totp/").append(keyId).append("%3Fsecret%3D").append(secret);
        return sb.toString();
    }

    String zeroPrepend(long num, int digits) {
        String hashStr = Long.toString(num);
        if (hashStr.length() >= digits) {
            return hashStr;
        } else {
            StringBuilder sb = new StringBuilder(digits);
            int zeroCount = digits - hashStr.length();
            sb.append(blockOfZeros, 0, zeroCount);
            sb.append(hashStr);
            return sb.toString();
        }
    }

    byte[] decodeBase32(String str) {
        int numBytes = ((str.length() * 5) + 4) / 8;
        byte[] result = new byte[numBytes];
        int resultIndex = 0;
        int which = 0;
        int working = 0;
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            int val;
            if (ch >= 'a' && ch <= 'z') {
                val = ch - 'a';
            } else if (ch >= 'A' && ch <= 'Z') {
                val = ch - 'A';
            } else if (ch >= '2' && ch <= '7') {
                val = 26 + (ch - '2');
            } else if (ch == '=') {
                // special case
                which = 0;
                break;
            } else {
                throw new IllegalArgumentException("Invalid base-32 character: " + ch);
            }
            switch (which) {
                case 0 :
                    working = (val & 0x1F) << 3;
                    which = 1;
                    break;
                case 1 :
                    working |= (val & 0x1C) >> 2;
                    result[resultIndex++] = (byte) working;
                    working = (val & 0x03) << 6;
                    which = 2;
                    break;
                case 2 :
                    working |= (val & 0x1F) << 1;
                    which = 3;
                    break;
                case 3 :
                    working |= (val & 0x10) >> 4;
                    result[resultIndex++] = (byte) working;
                    working = (val & 0x0F) << 4;
                    which = 4;
                    break;
                case 4 :
                    working |= (val & 0x1E) >> 1;
                    result[resultIndex++] = (byte) working;
                    working = (val & 0x01) << 7;
                    which = 5;
                    break;
                case 5 :
                    working |= (val & 0x1F) << 2;
                    which = 6;
                    break;
                case 6 :
                    working |= (val & 0x18) >> 3;
                    result[resultIndex++] = (byte) working;
                    working = (val & 0x07) << 5;
                    which = 7;
                    break;
                case 7 :
                    working |= (val & 0x1F);
                    result[resultIndex++] = (byte) working;
                    which = 0;
                    break;
            }
        }
        if (which != 0) {
            result[resultIndex++] = (byte) working;
        }
        if (resultIndex != result.length) {
            result = Arrays.copyOf(result, resultIndex);
        }
        return result;
    }
}

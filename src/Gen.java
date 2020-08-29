package abc;

import java.io.*;
import java.security.SecureRandom;
import java.util.Random;
import java.util.Scanner;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import org.apache.commons.io.FileUtils;

import static abc.AES.hash;

public class Gen {
    static char[] SYMBOLS = new String("^$*.[]{}()?-\"!@#%&/\\,><':;|_~`").toCharArray();
    static char[] LOWERCASE = new String("abcdefghijklmnopqrstuvwxyz").toCharArray();
    static char[] UPPERCASE = new String("ABCDEFGHIJKLMNOPQRSTUVWXYZ").toCharArray();
    static char[] NUMBERS = new String("0123456789").toCharArray();
    static char[] ALL_CHARS = new String("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789^$*.[]{}()?-\"!@#%&/\\,><':;|_~`").toCharArray();
    static Random rand = new SecureRandom();
    static Scanner sc = new Scanner(System.in);

    public static String getPassword() throws NumberFormatException {
        int length;
        //System.out.println("Enter the Length of the Password");
        length = 8;

        char[] password = new char[length];

        password[0] = LOWERCASE[rand.nextInt(LOWERCASE.length)];
        password[1] = UPPERCASE[rand.nextInt(UPPERCASE.length)];
        password[2] = NUMBERS[rand.nextInt(NUMBERS.length)];
        password[3] = SYMBOLS[rand.nextInt(SYMBOLS.length)];

        for (int i = 4; i < length; i++) {
            password[i] = ALL_CHARS[rand.nextInt(ALL_CHARS.length)];
        }

        for (int i = 0; i < password.length; i++) {
            int randomPosition = rand.nextInt(password.length);
            char temp = password[i];
            password[i] = password[randomPosition];
            password[randomPosition] = temp;
        }

        return new String(password);
    }

    public static String encrypt(String originalString, String secretKey) {
        String encryptedString = AES.encrypt(originalString, secretKey);
        return encryptedString;
    }

    public static String decrypt(String encryptedString, String secretKey) {
        String decryptedString = AES.decrypt(encryptedString, secretKey);
        return decryptedString;
    }

    public static String readPass() throws IOException {
        String fileName = "/home/arch/Downloads/abc/pass.txt";
        String content = new String(Files.readAllBytes(Paths.get(fileName)));
        return content;
    }

    public static void writePass(String content) throws IOException {
        String fileName = "/home/arch/Downloads/abc/pass.txt";
        content = hash(content);
        Files.write(Paths.get(fileName), content.getBytes(), StandardOpenOption.CREATE);
    }

    public static String readUser() throws IOException {
        String fileName = "/home/arch/Downloads/abc/user.txt";
        String content = new String(Files.readAllBytes(Paths.get(fileName)));
        return content;
    }

    public static void writeUser(String content) throws IOException {
        String fileName = "/home/arch/Downloads/abc/user.txt";
        Files.write(Paths.get(fileName), content.getBytes(), StandardOpenOption.CREATE);
    }

    public static void acceptUser(String content) throws IOException {
        String fileName = "/home/arch/Downloads/abc/veruser.txt";
        Files.write(Paths.get(fileName), content.getBytes(), StandardOpenOption.CREATE);
    }

    public static String verUser() throws IOException {
        String fileName = "/home/arch/Downloads/abc/veruser.txt";
        String content = new String(Files.readAllBytes(Paths.get(fileName)));
        return content;
    }

    public static void acceptPass(String content) throws IOException {
        String fileName = "/home/arch/Downloads/abc/verpass.txt";
        content = hash(content);
        Files.write(Paths.get(fileName), content.getBytes(), StandardOpenOption.CREATE);
    }

    public static void acceptOTPKey(String content) throws IOException {
        String fileName = "/home/arch/Downloads/abc/otpkey.txt";
        Files.write(Paths.get(fileName), content.getBytes(), StandardOpenOption.CREATE);
    }

    public static String verOTPKey() throws IOException {
        String fileName = "/home/arch/Downloads/abc/otpkey.txt";
        String content = new String(Files.readAllBytes(Paths.get(fileName)));
        return content;
    }

    public static String verPass() throws IOException {
        String fileName = "/home/arch/Downloads/abc/verpass.txt";
        String content = new String(Files.readAllBytes(Paths.get(fileName)));
        return content;
    }


    public static String readDBPass() throws IOException {
        String fileName = "/home/arch/Downloads/abc/dbpass.txt";
        String content = new String(Files.readAllBytes(Paths.get(fileName)));
        return content;
    }

    public static boolean checkPass() {
        try {
            File file1 = new File("/home/arch/Downloads/abc/pass.txt");
            File file2 = new File("/home/arch/Downloads/abc/verpass.txt");
            boolean isTwoEqual = FileUtils.contentEquals(file1, file2);
            return isTwoEqual;
        } catch (Exception e){e.printStackTrace();}
        return  false;
    }
}

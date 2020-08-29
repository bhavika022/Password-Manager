package abc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class database {

    public static void Create(String site, String username, String password){
        try {
            String url = "jdbc:mysql://localhost:3306/test";
            String uname = "root";
            String pass = "Enter Password Here";
            String query = "INSERT INTO accounts(site_name, acc_login, pass) VALUE('" + site + "', '" + username + "', '" + password + "');";
            Connection connection = DriverManager.getConnection
                    (url, uname, pass);
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);

        }
        catch (Exception e) {
            System.out.println("Error " + e.toString());
        }
    }

    public static void ViewAll(){
        try {
            String url = "jdbc:mysql://localhost:3306/test";
            String uname = "root";
            String pass = abc.Gen.readDBPass();
            String query = "SELECT * FROM accounts";
            Connection connection = DriverManager.getConnection
                    (url, uname, pass);
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            String UserData;
            while (rs.next()){
                UserData = rs.getString(2) + " : " + rs.getString(3);
                System.out.println(UserData);
            }
        }
        catch (Exception e){
            System.out.println("Error " + e.toString());
        }
    }

    public static void writeOTPKey(String test){
        try{
            String url = "jdbc:mysql://localhost:3306/test";
            String uname = "root";
            String pass = "";
            String query1 = "DELETE FROM otpKey WHERE acc_id = 1";
            String query2 = "INSERT INTO otpKey VALUE ('1', '"+test+"');";
            Connection connection = DriverManager.getConnection
                    (url, uname, pass);
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query1);
            ResultSet rs1 = statement.executeQuery(query2);
            rs.next();
            rs1.next();
        }
        catch (Exception e){e.toString();}
    }

    public static String GetOtpKey() {
        try {
            String url = "jdbc:mysql://localhost:3306/test";
            String uname = "root";
            String pass = "Enter Password Here";
            String query = "SELECT otpKey.otpkey FROM otpKey;";
            Connection connection = DriverManager.getConnection
                    (url, uname, pass);
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            rs.next();
            String otpKey = rs.getString("otpkey");
            return otpKey;
        } catch (Exception e) {
            System.out.println("Error " + e.toString());
            return e.toString();
        }
    }

    public static String GetPass(String site, String username) {
        try {
            String url = "jdbc:mysql://localhost:3306/test";
            String uname = "root";
            String pass = abc.Gen.readDBPass();
            String query = "SELECT accounts.pass FROM accounts WHERE site_name = '" + site + "' AND acc_login = '" + username + "'";
            Connection connection = DriverManager.getConnection
                    (url, uname, pass);
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            String UserData;
            rs.next();
            UserData = rs.getString("pass");
            return UserData;
        } catch (Exception e) {
            System.out.println("Error " + e.toString());
            return e.toString();
        }
    }

        public static void ChangePass(String site, String username, String NewPassword){
            try {
                String url = "jdbc:mysql://localhost:3306/test";
                String uname = "root";
                String pass = abc.Gen.readDBPass();
                String query = "UPDATE accounts SET accounts.pass = '" + NewPassword + "' WHERE site_name = '" + site + "' AND acc_login = '" + username + "'";
                Connection connection = DriverManager.getConnection
                        (url, uname, pass);
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(query);
                rs.next();
            } catch (Exception e) {
                System.out.println("Error " + e.toString());
            }
        }

    public static void ViewUsers(){
        try {
            String url = "jdbc:mysql://localhost:3306/test";
            String uname = "root";
            String pass = abc.Gen.readDBPass();
            String query = "SELECT accounts.site_name, accounts.acc_login FROM accounts ORDER BY accounts.site_name, accounts.acc_login";
            Connection connection = DriverManager.getConnection
                    (url, uname, pass);
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            String UserData;
            while (rs.next()){
                UserData = rs.getString(2) + " : " + rs.getString(3);
                System.out.println(UserData);
            }
        }
        catch (Exception e){
            System.out.println("Error " + e.toString());
        }
    }

    public static String decPass(int accID){
        try{
            String url = "jdbc:mysql://localhost:3306/test";
            String uname = "root";
            String pass = "Enter Password Here";
            String query = "SELECT accounts.pass FROM accounts WHERE acc_id = '"+accID+"';";
            Connection connection = DriverManager.getConnection
                    (url, uname, pass);
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            rs.next();
            String accPass = rs.getString("pass");
            accPass = AES.decrypt(accPass, Gen.verPass());
            return accPass;

        } catch (Exception e){e.toString();}
        return null;
    }

}

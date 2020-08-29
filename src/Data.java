package abc;

import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import abc.Account.*;

public class Data extends javax.swing.JFrame{

    public ArrayList<Account> accountList(){
        ArrayList<Account> accountList = new ArrayList<>();
        try{
            Class.forName("org.mariadb.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/test";
            String uname = "root";
            String pass = abc.Gen.readDBPass();
            Connection con = DriverManager.getConnection(url, uname, pass);
            String query = "SELECT * FROM accounts";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            Account account;
            while (rs.next()){
                account = new Account(rs.getInt("acc_id"), rs.getString("site_name"), rs.getString("acc_login"), rs.getString("pass"));
            }
        }
        catch (Exception e){
            e.toString();
        }
        return accountList;
    }
    public void showAccounts(){
        ArrayList<Account> list = accountList();
        DefaultTableModel model =  (DefaultTableModel)(Pswrd.table.getModel());
        Object[] row = new Object[3];
        for(int i=0; i<list.size(); i++){
            row[0]=list.get(i).getSite_name();
            row[1]=list.get(i).getAcc_login();
            row[2]=list.get(i).getacc_Pass();
            model.addRow(row);
        }
    }
}

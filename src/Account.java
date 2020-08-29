package abc;

public class Account {
    private int acc_id;
    private String site_name, acc_login, pass;

    public Account(int acc_id, String site_name, String acc_login, String pass){
        this.acc_id = acc_id;
        this.site_name=site_name;
        this.acc_login=acc_login;
        this.pass=pass;
    }
    public int getAcc_id(){
        return acc_id;
    }
    public String getSite_name(){
        return site_name;
    }
    public String getAcc_login(){
        return acc_login;
    }
    public String getacc_Pass(){
        return pass;
    }
}

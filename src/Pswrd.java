package abc;

import net.proteanit.sql.DbUtils;

import java.awt.EventQueue;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;
import javax.swing.JFrame;
import java.awt.CardLayout;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.SystemColor;
import java.awt.Color;
import javax.swing.JMenu;
import java.awt.ScrollPane;
import java.awt.Scrollbar;
import java.awt.List;
import java.io.PrintWriter;
import javax.swing.JList;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.BevelBorder;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.border.CompoundBorder;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.table.TableColumnModel;

public class Pswrd {

	private JFrame frame;
	private JTextField text_username_exist;
	private JTextField text_newUser;
	private JTextField text_newPaswrd;
	private JPasswordField text_pswrd_exist;
	private JTextField textOTP;
	private JTextField textDecField;
	private JTextField textOTP2;
	private JTextField textField_2;
	private JTextField textField_4;
	public JPanel panellogin;
	public JPanel panelexist;
	public JPanel panelnew;
	public JPanel panelAuthn;
	public JPanel panelMain;
	private JTextField textField;
	private JTextField textField_1;
	public static JTable table;
	private JPanel panelAuthn2;
	public static String password;

	/**
	 * Launch the application.
	 */
	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Pswrd window = new Pswrd();
					window.frame.setVisible(true);
					Class.forName("org.mariadb.jdbc.Driver");
					fetch();
				} catch (Exception e) {
					e.toString();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Pswrd() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
	    //Data data = new Data();
		//data.showAccounts();
		frame = new JFrame();
		frame.setBounds(100, 100, 505, 433);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		
		final JPanel panellogin = new JPanel();
		panellogin.setBackground(new Color(31, 41, 46));
		panellogin.setForeground(SystemColor.text);
		frame.getContentPane().add(panellogin, "name_46195934852502");
		panellogin.setLayout(null);

		final JPanel panelexist = new JPanel();
		panelexist.setBackground(new Color(31, 41, 46));
		frame.getContentPane().add(panelexist, "name_46195941014719");
		panelexist.setLayout(null);

		try {
			//System.out.println(Gen.readPass().length());
			if(Gen.readPass().length()<=2) {
				panellogin.setVisible(true);
				panelexist.setVisible(false);
			}
			else {
				panellogin.setVisible(false);
				panelexist.setVisible(true);
			}
		} catch (Exception e){e.toString();}

		final JPanel panelnew = new JPanel();
		panelnew.setVisible(false);
		panelnew.setBackground(new Color(31, 41, 46));
		frame.getContentPane().add(panelnew, "name_46195946357825");
		panelnew.setLayout(null);
		panelnew.setVisible(false);
		
		final JPanel panelAuthn = new JPanel();
		panelAuthn.setVisible(false);
		panelAuthn.setBackground(new Color(31, 41, 46));
		frame.getContentPane().add(panelAuthn, "name_46195952157820");
		panelAuthn.setLayout(null);
		panelAuthn.setVisible(false);

		final JPanel panelAuthn2 = new JPanel();
		panelAuthn2.setVisible(false);
		panelAuthn2.setBackground(new Color(31, 41, 46));
		frame.getContentPane().add(panelAuthn2, "name_46195952157820");
		panelAuthn2.setLayout(null);
		panelAuthn2.setVisible(false);

		final JPanel panelMain = new JPanel();
		panelMain.setVisible(false);
		panelMain.setForeground(new Color(255, 255, 255));
		panelMain.setBackground(new Color(31, 41, 46));
		frame.getContentPane().add(panelMain, "name_46195958214259");
		panelMain.setLayout(null);
		panelMain.setVisible(false);
		
		final JPanel AddAcc = new JPanel();
		AddAcc.setBackground(Color.BLACK);
		frame.getContentPane().add(AddAcc, "name_6399042181295");
		AddAcc.setLayout(null);
		AddAcc.setVisible(false);


		final JPanel panel = new JPanel();
		panel.setBackground(new Color(31, 41, 46));
		frame.getContentPane().add(panel, "name_4658392178222");
		panel.setLayout(null);
		panel.setVisible(false);

		JLabel lblNewLabel_3 = new JLabel("PASSWORD :");
		lblNewLabel_3.setForeground(Color.GREEN);
		lblNewLabel_3.setBackground(Color.BLACK);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_3.setBounds(52, 84, 108, 36);
		panel.add(lblNewLabel_3);

		JButton btnBack_2 = new JButton("Back");
		btnBack_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelMain.setVisible(true);
				panel.setVisible(false);
			}
		});
		btnBack_2.setBackground(Color.CYAN);
		btnBack_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnBack_2.setBounds(12, 337, 136, 36);
		panel.add(btnBack_2);

		JLabel lblWelcome = new JLabel("Welcome");
		lblWelcome.setForeground(new Color(224, 255, 255));
		lblWelcome.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblWelcome.setBounds(12, 10, 136, 21);
		panelMain.add(lblWelcome);
		
		JButton btnLogout = new JButton("LOGOUT");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelMain.setVisible(false);
				panelexist.setVisible(true);
			}
		});
		btnLogout.setBackground(Color.CYAN);
		btnLogout.setBounds(363, 10, 97, 25);
		panelMain.add(btnLogout);
		
		
		
		JLabel lblPasman = new JLabel("WELCOME TO PASMAN");
		lblPasman.setForeground(new Color(224, 255, 255));
		lblPasman.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblPasman.setBounds(93, 40, 353, 42);
		panellogin.add(lblPasman);
		
		JButton btnNewUser = new JButton("New User");
		btnNewUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				panellogin.setVisible(false);
				panelnew.setVisible(true);
				
			}
		});
				
				
				
	
		btnNewUser.setForeground(SystemColor.textText);
		btnNewUser.setBackground(new Color(0, 255, 255));
		btnNewUser.setBounds(180, 134, 150, 42);
		panellogin.add(btnNewUser);
		
		JButton btnExistingUser = new JButton("Existing User");
		btnExistingUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panellogin.setVisible(false);
				panelexist.setVisible(true);
			}
		});
		btnExistingUser.setBackground(new Color(0, 255, 255));
		btnExistingUser.setBounds(180, 210, 150, 42);
		//panellogin.add(btnExistingUser);
		
		
		JLabel lblUsername = new JLabel("USERNAME");
		lblUsername.setForeground(new Color(245, 255, 250));
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblUsername.setBounds(57, 130, 100, 27);
		panelexist.add(lblUsername);
		
		JLabel lblPassword = new JLabel("PASSWORD");
		lblPassword.setForeground(new Color(240, 255, 255));
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPassword.setBounds(57, 205, 114, 16);
		panelexist.add(lblPassword);
		
		text_username_exist = new JTextField();
		text_username_exist.setBackground(new Color(0, 255, 255));
		text_username_exist.setFont(new Font("Tahoma", Font.PLAIN, 16));
		text_username_exist.setBounds(199, 134, 176, 22);
		panelexist.add(text_username_exist);
		text_username_exist.setColumns(10);
		
		text_pswrd_exist = new JPasswordField();
		text_pswrd_exist.setBackground(new Color(0, 255, 255));
		text_pswrd_exist.setFont(new Font("Tahoma", Font.PLAIN, 16));
		text_pswrd_exist.setToolTipText("");
		text_pswrd_exist.setBounds(199, 205, 176, 22);
		panelexist.add(text_pswrd_exist);
		
		JButton btnNext_exist = new JButton("Next");
		btnNext_exist.setBackground(Color.CYAN);
		btnNext_exist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					PrintWriter writer = new PrintWriter("/home/arch/Downloads/abc/verpass.txt");
					System.out.println(database.GetOtpKey());
					//System.out.println(AES.encrypt("hello", Gen.verPass()));
					//System.out.println(AES.decrypt(database.decPass(12), Gen.verPass()));
					//System.out.println(AES.decrypt(database.decPass(11), Gen.verPass()));
                    //System.out.println(database.decPass(12));
                    //System.out.println(database.decPass(Integer.parseInt("12")));
					writer.print("");
					writer.close();

					Gen.acceptUser(getText(text_username_exist));
					Gen.acceptPass(getText(text_pswrd_exist));
			    if(Gen.checkPass()) {
			    		//System.out.println("true");
						panelexist.setVisible(false);
						panelAuthn2.setVisible(true);
				}
			    else{
					//System.out.println("false");
			    	panelAuthn2.setVisible(false);
			    	panelexist.setVisible(true);
				}
				} catch (Exception e){e.toString();}
			}
		});
		btnNext_exist.setBounds(153, 262, 153, 39);
		panelexist.add(btnNext_exist);
		
		JButton btnBack_1 = new JButton("Back");
		btnBack_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelexist.setVisible(false);
				panellogin.setVisible(true);
			}
		});
		btnBack_1.setBackground(Color.CYAN);
		btnBack_1.setBounds(12, 348, 97, 25);
		//panelexist.add(btnBack_1);
		

		
		
		JLabel lblNewUsername = new JLabel("New Username");
		lblNewUsername.setForeground(new Color(224, 255, 255));
		lblNewUsername.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewUsername.setBounds(41, 160, 118, 16);
		panelnew.add(lblNewUsername);
		
		JLabel lblNewPassword = new JLabel("New Password");
		lblNewPassword.setForeground(new Color(224, 255, 255));
		lblNewPassword.setBackground(new Color(224, 255, 255));
		lblNewPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewPassword.setBounds(41, 203, 118, 16);
		panelnew.add(lblNewPassword);
		
		text_newUser = new JTextField();
		text_newUser.setBackground(new Color(0, 255, 255));
		text_newUser.setForeground(new Color(0, 0, 0));
		text_newUser.setFont(new Font("Tahoma", Font.PLAIN, 16));
		text_newUser.setBounds(224, 159, 148, 22);
		panelnew.add(text_newUser);
		text_newUser.setColumns(10);
		
		text_newPaswrd = new JTextField();
		text_newPaswrd.setBackground(new Color(0, 255, 255));
		text_newPaswrd.setFont(new Font("Tahoma", Font.PLAIN, 16));
		text_newPaswrd.setBounds(224, 202, 148, 22);
		panelnew.add(text_newPaswrd);
		text_newPaswrd.setColumns(10);
		
		JButton btnNext_new = new JButton("NEXT");
		btnNext_new.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Gen.writeUser(getText(text_newUser));
					Gen.writePass(getText(text_newPaswrd));
				} catch (Exception e1){e.toString();}
				panelnew.setVisible(false);
				panelAuthn.setVisible(true);
			}
		});
		btnNext_new.setBackground(new Color(0, 255, 255));
		btnNext_new.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNext_new.setBounds(158, 264, 112, 33);
		panelnew.add(btnNext_new);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelnew.setVisible(false);
				panellogin.setVisible(true);
			}
		});
		btnBack.setBackground(Color.CYAN);
		btnBack.setForeground(Color.BLACK);
		btnBack.setBounds(12, 348, 97, 25);
		panelnew.add(btnBack);
		
		
		
		JLabel lblAuthentication = new JLabel("Authentication");
		lblAuthentication.setForeground(Color.YELLOW);
		lblAuthentication.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lblAuthentication.setBounds(173, 60, 183, 41);
		panelAuthn.add(lblAuthentication);
		
		JLabel lblNewLabel = new JLabel("Username: ");
		lblNewLabel.setForeground(new Color(224, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(50, 167, 125, 16);
		//panelAuthn.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Key: ");
		lblNewLabel_1.setForeground(new Color(224, 255, 255));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(50, 212, 125, 22);
		//panelAuthn.add(lblNewLabel_1);
		
		JButton btnOtp = new JButton("OTP");
		btnOtp.setBackground(new Color(40, 85, 119));
		btnOtp.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnOtp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TwoFactorAuthUtil twoFactorAuthUtil = new TwoFactorAuthUtil();
				//twoFactorAuthUtil.generateBase32Secret();
			}
		});
		btnOtp.setBounds(50, 270, 132, 31);
		panelAuthn.add(btnOtp);
		
		textOTP = new JTextField();
		textOTP.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textOTP.setBounds(229, 270, 130, 31);
		panelAuthn.add(textOTP);
		textOTP.setColumns(10);
		
		JButton btnLogin = new JButton("Create");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					//System.out.println(database.GetOtpKey());
					if (AES.checkOTP(getText(textOTP), database.GetOtpKey())) {
						panelAuthn.setVisible(false);
						panelMain.setVisible(true);
					} else {
						panelAuthn.setVisible(true);
						panelMain.setVisible(false);
					}
				} catch (Exception e3){e.toString();}
			}
		});
		btnLogin.setBackground(new Color(0, 255, 255));
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnLogin.setBounds(335, 336, 129, 36);
		panelAuthn.add(btnLogin);
		
		JButton btnReturnMain = new JButton("RETURN MAIN");
		btnReturnMain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelAuthn.setVisible(false);
				panellogin.setVisible(true);
			}
		});
		btnReturnMain.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnReturnMain.setBackground(Color.CYAN);
		btnReturnMain.setBounds(335, 18, 136, 25);
		panelAuthn.add(btnReturnMain);

		try {
			JLabel lblSdfd = new JLabel();
			lblSdfd.setText(String.format(Gen.readUser()));
			lblSdfd.setForeground(Color.CYAN);
			lblSdfd.setBackground(Color.WHITE);
			lblSdfd.setBounds(255, 169, 151, 16);
			//panelAuthn.add(lblSdfd);
		} catch (Exception e) {e.toString();}


		try {
			JLabel lblSdfd2 = new JLabel();
			//System.out.println(String.format(Gen.readUser()));
			lblSdfd2.setText(String.format(Gen.readUser()));
			lblSdfd2.setForeground(Color.CYAN);
			lblSdfd2.setBackground(Color.WHITE);
			lblSdfd2.setBounds(255, 169, 151, 16);
			//panelAuthn2.add(lblSdfd2);
		} catch (Exception e){e.toString();}

		/*try {
			JLabel label_1 = new JLabel("");
			TwoFactorAuthUtil twoFactorAuthUtil = new TwoFactorAuthUtil();
			String otpKey = twoFactorAuthUtil.generateBase32Secret();
			database.writeOTPKey(otpKey);
			label_1.setText(String.format(otpKey));
			label_1.setForeground(Color.CYAN);
			label_1.setBackground(Color.BLACK);
			label_1.setBounds(256, 217, 150, 16);
			//panelAuthn.add(label_1);
		} catch (Exception e){e.toString();}*/

		textField_4 = new JTextField();
		textField_4.setBounds(229, 213, 130, 31);
		panelAuthn.add(textField_4);
		textField_4.setColumns(10);

		JButton btnGenerateKey = new JButton("Generate Key ");
		btnGenerateKey.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TwoFactorAuthUtil twoFactorAuthUtil = new TwoFactorAuthUtil();
				String otpKey = twoFactorAuthUtil.generateBase32Secret();
				database.writeOTPKey(otpKey);
				textField_4.setText(String.format(otpKey));
			}
		});
		btnGenerateKey.setBackground(new Color(40, 85, 119));
		btnGenerateKey.setBounds(50, 213, 132, 31);
		panelAuthn.add(btnGenerateKey);



		JButton btnAddNew = new JButton("Add New");
		btnAddNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelMain.setVisible(false);
				AddAcc.setVisible(true);
			}
		});
		btnAddNew.setBackground(Color.CYAN);
		//btnAddNew.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnAddNew.setBounds(254, 10, 97, 25);
		panelMain.add(btnAddNew);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 117, 487, 269);
		panelMain.add(scrollPane);

		JButton btnDecrypt = new JButton("Decrypt");
		btnDecrypt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				int accID = Integer.parseInt(getText(textDecField));
				Pswrd.password = database.decPass(accID);
				StringSelection stringSelection = new StringSelection(Pswrd.password);
				Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
				clipboard.setContents(stringSelection, null);
				textField_2.setText(Pswrd.password);
				//System.out.println(Pswrd.password);
				panelMain.setVisible(true);
				//panel.setVisible(true);
				//System.out.println(database.decPass(accID));
			}
		});
		btnDecrypt.setForeground(Color.CYAN);
		btnDecrypt.setBackground(new Color(31, 41, 46));
		btnDecrypt.setBounds(363, 41, 97,25);
		panelMain.add(btnDecrypt);

		textDecField = new JTextField();
		textDecField.setBounds(300, 42, 45, 22);
		panelMain.add(textDecField);
		textDecField.setColumns(10);

		table = new JTable();
		table.setBackground(new Color(0, 102, 153));
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
			},
			new String[] {
				"Acc. ID", "SITE", "USERNAME"
			}
		));
		table.getColumnModel().getColumn(0).setMinWidth(16);
		table.getColumnModel().getColumn(1).setMinWidth(16);
		table.getColumnModel().getColumn(2).setMinWidth(16);

		scrollPane.setViewportView(table);


		
		
		JLabel lblSiteName = new JLabel("SITE NAME");
		lblSiteName.setForeground(Color.WHITE);
		lblSiteName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSiteName.setBounds(26, 70, 89, 28);
		AddAcc.add(lblSiteName);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField.setBackground(Color.CYAN);
		textField.setBounds(177, 70, 186, 26);
		AddAcc.add(textField);
		textField.setColumns(10);
		
		JLabel lblUsername_1 = new JLabel("USERNAME");
		lblUsername_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblUsername_1.setForeground(Color.WHITE);
		lblUsername_1.setBounds(26, 133, 89, 28);
		AddAcc.add(lblUsername_1);
		
		textField_1 = new JTextField();
		textField_1.setBackground(Color.CYAN);
		textField_1.setBounds(177, 133, 186, 26);
		AddAcc.add(textField_1);
		textField_1.setColumns(10);

		JLabel lblsaveThisUsername = new JLabel("(Save This Key in Google Authenticator for Future Login)");
		lblsaveThisUsername.setForeground(Color.RED);
		lblsaveThisUsername.setBounds(19, 247, 452, 16);
		panelAuthn.add(lblsaveThisUsername);
		
		JButton btnNewButton = new JButton("CREATE PASSWORD");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String pass = Gen.getPassword();
					pass = AES.encrypt(pass, Gen.readPass());
					database.Create(getText(textField), getText(textField_1), pass);
					fetch();
					AddAcc.setVisible(false);
					panelMain.setVisible(true);
				} catch (Exception e1){e.toString();}
			}
		});
		btnNewButton.setBackground(Color.PINK);
		btnNewButton.setBounds(177, 200, 186, 28);
		AddAcc.add(btnNewButton);

		JLabel lblAuthentication2 = new JLabel("Authentication");
		lblAuthentication2.setForeground(Color.YELLOW);
		lblAuthentication2.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lblAuthentication2.setBounds(173, 60, 183, 41);
		panelAuthn2.add(lblAuthentication2);

		JLabel lblNewLabel2 = new JLabel("Username: ");
		lblNewLabel2.setForeground(new Color(224, 255, 255));
		lblNewLabel2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel2.setBounds(50, 167, 125, 16);
		//panelAuthn2.add(lblNewLabel2);

		JLabel lblNewLabel_2 = new JLabel("Key: ");
		lblNewLabel_2.setForeground(new Color(224, 255, 255));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(50, 212, 125, 22);
		//panelAuthn2.add(lblNewLabel_2);
		JLabel lblAcc = new JLabel("Enter Acc_id to Show Password ->");
		lblAcc.setFont(new Font("Tahoma",Font.PLAIN,15));
		lblAcc.setForeground(Color.YELLOW);
		lblAcc.setBounds(53,44,235,16);
		panelMain.add(lblAcc);

		try {
			JLabel label4 = new JLabel();
			label4.setBounds(76, 156, 326, 36);
			label4.setText(String.format(Pswrd.password));
			//label4.setText(String.valueOf(Pswrd.password));
			System.out.println(Pswrd.password);
			panel.add(label4);
		} catch (Exception e){e.toString();}

		JButton btnLogin2 = new JButton("LOGIN");
		btnLogin2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    try {
			    	//System.out.println(database.GetOtpKey());
					if (AES.checkOTP(getText(textOTP2), database.GetOtpKey())) {
						panelAuthn2.setVisible(false);
						panelMain.setVisible(true);
					} else {
						panelAuthn2.setVisible(true);
						panelMain.setVisible(false);
					}
				} catch (Exception e3){e.toString();}
			}
		});
		btnLogin2.setBackground(new Color(0, 255, 255));
		btnLogin2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnLogin2.setBounds(335, 336, 129, 36);
		panelAuthn2.add(btnLogin2);

		JButton btnOtp2 = new JButton("OTP");
		btnOtp2.setBackground(new Color(40, 85, 119));
		btnOtp2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnOtp2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnOtp2.setBounds(187, 152, 141, 31);
		panelAuthn2.add(btnOtp2);

		textOTP2 = new JTextField();
		textOTP2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textOTP2.setBounds(203, 211, 109, 27);
		panelAuthn2.add(textOTP2);
		textOTP2.setColumns(10);

		JLabel lblPassword_1 = new JLabel("PASSWORD :");
		lblPassword_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPassword_1.setForeground(new Color(255, 255, 0));
		lblPassword_1.setBackground(new Color(31, 41, 46));
		lblPassword_1.setBounds(200, 84, 97, 16);
		panelMain.add(lblPassword_1);

		try {
			JLabel label = new JLabel();
			label.setForeground(new Color(51, 255, 153));
			label.setText(String.format(Pswrd.password));
			label.setBounds(214, 75, 246, 31);
			//panelMain.add(label);
		} catch (Exception e){e.toString();}

		textField_2 = new JTextField();
		textField_2.setForeground(new Color(51, 255, 153));
		textField_2.setBackground(new Color(0, 0, 0));
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_2.setBounds(300, 79, 160, 27);
		panelMain.add(textField_2);
		textField_2.setColumns(10);
	}
	public String getText(JTextField jTextField) {
		return jTextField.getText();
	}
	public static void fetch(){
		try{
			String url = "jdbc:mysql://localhost:3306/test";
			String uname = "root";
			String pass = "ITSmerlynw00d";
			String query = "SELECT accounts.acc_id, accounts.site_name, accounts.acc_login FROM accounts;";
			Connection connection = DriverManager.getConnection
					(url, uname, pass);
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);
			table.setModel(DbUtils.resultSetToTableModel(rs));

		} catch (Exception e){e.toString();}
	}

}

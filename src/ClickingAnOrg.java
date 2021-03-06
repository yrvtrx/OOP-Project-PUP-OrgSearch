import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class ClickingAnOrg extends JFrame implements Runnable {

	private JPanel contentPane;
	private JButton btnWriteAPost;
	private JButton btnNewButton;
	private JButton btnLeaveThisOrg;
	private JButton btnBack;
	private JButton btnEdit;
	private JTextField txtWritePost;
	private JButton btnSavePost;
	private JButton btnNewsfeed;

	private Connection objConn;
	private boolean boolConn2Db;
	private Statement objSQLQuery;
	private ResultSet objResultSet;
	private String strSQLQuery;

	public void run() {
		try {
			ClickingAnOrg frame = new ClickingAnOrg();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		} //try
	} //public void run()

	ClickingAnOrg() {
		String strDriver = "com.mysql.cj.jdbc.Driver";
        String strConn = "jdbc:mysql://localhost:3306/puporgsearch";
        String strUser = "linus";
        String strPass = "password123";

        boolConn2Db = false;

        try {        
            Class.forName(strDriver);
            objConn = DriverManager.getConnection(strConn, strUser, strPass);   
            objSQLQuery = objConn.createStatement(); 
             
            boolConn2Db = true;
            
        } catch (Exception objEx) {
            System.out.println("Problem retrieving information..");
            System.out.println(objEx);
        }  //try

        if (boolConn2Db) {
            ClickingAnOrgGUI();
            setupListener();
        }  //if (boolConn2Db)
    }  //ClickingAnOrg() 

	public void ClickingAnOrgGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 530);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	
		btnWriteAPost = new JButton("Write a post");
		btnWriteAPost.setBounds(130, 290, 120, 29);
		contentPane.add(btnWriteAPost);
		
		JLabel lblPupOrganizationSearch = new JLabel("PUP ORGANIZATION SEARCH");
		lblPupOrganizationSearch.setHorizontalAlignment(SwingConstants.CENTER);
		lblPupOrganizationSearch.setForeground(Color.WHITE);
		lblPupOrganizationSearch.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblPupOrganizationSearch.setBounds(27, 29, 332, 36);
		contentPane.add(lblPupOrganizationSearch);
		
		JLabel lblOrgLogo = new JLabel("");
		lblOrgLogo.setIcon(new ImageIcon(ClickingAnOrg.class.getResource("/image/PUPLogo (1).png")));
		lblOrgLogo.setForeground(new Color(255, 255, 255));
		lblOrgLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblOrgLogo.setBounds(73, 111, 50, 50);
		contentPane.add(lblOrgLogo);
		
		JLabel lblNewLabel = new JLabel("Organization Type");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(160, 111, 161, 23);
		contentPane.add(lblNewLabel);

		int intmembercount=0;
		System.out.println("CAN: "+Search.selectedSOrg);
		
		try {
			strSQLQuery = "SELECT strorgsjoined FROM tblorgsjoin ";
			objResultSet = objSQLQuery.executeQuery(strSQLQuery);
			
			while (objResultSet.next()) {
				String strorgname=objResultSet.getString("strorgsjoined").trim();
				System.out.println(strorgname);
				
				if(strorgname.contentEquals(Search.selectedSOrg)) {
					intmembercount++;
					System.out.println("Numbers: "+intmembercount);
				} //if(strorgname.contentEquals(Search.selectedSOrg))
			} //while (objResultSet.next())
		} catch (SQLException e) {
			e.printStackTrace();
		} //try
		
		JLabel lblNumOfMembers = new JLabel("Num Of Members: "+intmembercount);
		lblNumOfMembers.setForeground(new Color(255, 255, 255));
		lblNumOfMembers.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumOfMembers.setBounds(73, 210, 130, 50);
		contentPane.add(lblNumOfMembers);
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setForeground(new Color(255, 255, 255));
		lblDescription.setHorizontalAlignment(SwingConstants.CENTER);
		lblDescription.setBounds(73, 145, 400, 105);
		contentPane.add(lblDescription);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setForeground(new Color(255, 255, 255));
		lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmail.setBounds(73, 261, 248, 23);
		contentPane.add(lblEmail);
		
		btnNewsfeed = new JButton("NEWS FEED");//CHANGE MENU
		btnNewsfeed.setHorizontalAlignment(SwingConstants.CENTER);
		btnNewsfeed.setForeground(Color.BLACK);
		btnNewsfeed.setBounds(73, 330, 248, 69);
		contentPane.add(btnNewsfeed);
		
		btnNewButton = new JButton("Join this Org");
		btnNewButton.setBounds(73, 410, 110, 23);
		contentPane.add(btnNewButton);
		
		btnLeaveThisOrg = new JButton("Leave this Org");
		btnLeaveThisOrg.setBounds(201, 410, 120, 23);
		contentPane.add(btnLeaveThisOrg);
		
		btnBack = new JButton("Back");
		btnBack.setBounds(146, 444, 93, 23);
		contentPane.add(btnBack);
		
		btnEdit = new JButton("Edit");
		btnEdit.setBounds(247, 76, 74, 23);
		contentPane.add(btnEdit);		
		
		JLabel lblOrganization = new JLabel("Organization #1");
		lblOrganization.setHorizontalAlignment(SwingConstants.CENTER);
		lblOrganization.setForeground(Color.WHITE);
		lblOrganization.setBounds(73, 76, 161, 23);
		contentPane.add(lblOrganization);
		
		if(LeaveAnOrg.boolLeaveAnOrg) {
			try {
				strSQLQuery = "SELECT strorgname, strorgtype, strorgemail, strorgdes " +
						"FROM tblorg " + "WHERE strorgname = '" + LeaveAnOrg.selectedLOrg + "';";            
                    
				objResultSet = objSQLQuery.executeQuery(strSQLQuery);
           
				while (objResultSet.next()) {
					lblOrganization.setText(objResultSet.getString("strorgname"));
					lblNewLabel.setText(objResultSet.getString("strorgtype"));
					lblEmail.setText(objResultSet.getString("strorgemail"));
					lblDescription.setText(objResultSet.getString("strorgdes"));                       
				}  // while (objResultSet.next()) 
	
				strSQLQuery = "SELECT strimgpath FROM tblimg " + "WHERE strorgname = '" + LeaveAnOrg.selectedLOrg + "';";            
				
				objResultSet = objSQLQuery.executeQuery(strSQLQuery);
           
				while (objResultSet.next()) {
					String strimgpath = objResultSet.getString("strimgpath");
					System.out.println(strimgpath);
					lblOrgLogo.setIcon(new ImageIcon("C:\\Users\\mikay\\OneDrive\\Documents\\Java Files\\src\\image\\"+strimgpath));                     
				}  // while (objResultSet.next()) 
			
			} catch (Exception objEx) {
				System.out.println("Problem retrieving information..");
				System.out.println(objEx);
			}  //try
		}// if(LeaveAnOrg.boolLeaveAnOrg)

		if(Search.boolSearch) {
			try {
				String strSQLQuery = "SELECT strorgname, strorgtype, strorgemail, strorgdes " +
						"FROM tblorg " + "WHERE strorgname = '" + Search.selectedSOrg + "';";            
			
				objResultSet = objSQLQuery.executeQuery(strSQLQuery);
           
				while (objResultSet.next()) {
					lblOrganization.setText(objResultSet.getString("strorgname"));
					lblNewLabel.setText(objResultSet.getString("strorgtype"));
					lblEmail.setText(objResultSet.getString("strorgemail"));
					lblDescription.setText(objResultSet.getString("strorgdes"));                       
				}  // while (objResultSet.next()) 

				strSQLQuery = "SELECT strimgpath FROM tblimg " + 
						"WHERE strorgname = '" + Search.selectedSOrg + "';";            
                    
				objResultSet = objSQLQuery.executeQuery(strSQLQuery);
           
				while (objResultSet.next()) {
					String strimgpath = objResultSet.getString("strimgpath");
					System.out.println(strimgpath);
					lblOrgLogo.setIcon(new ImageIcon("C:\\Users\\mikay\\OneDrive\\Documents\\Java Files\\src\\image\\"+strimgpath));
				}  // while (objResultSet.next())
				
			} catch (Exception objEx) {
				System.out.println("Problem retrieving information..");
				System.out.println(objEx);
			}  // try 
		}// if(Search.boolSearch)
	} //public void ClickingAnOrgGUI()

	public void setupListener() {
		btnWriteAPost.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent objAE) {
				WriteAPost();
			} //public void actionPerformed(ActionEvent objAE)
		}); //btnWriteAPost.addActionListener(new ActionListener()
		
		btnNewsfeed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent objAE) {
				MainActivity.ActivityNewsFeedSpecific();
				ClickingAnOrg.this.dispose();
			} //public void actionPerformed(ActionEvent objAE)
		}); //btnNewsfeed.addActionListener(new ActionListener()
		
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent objAE) {
				MainActivity.ActivityEditOrg(Search.selectedSOrg);
				ClickingAnOrg.this.dispose();	
			} //public void actionPerformed(ActionEvent objAE)
		}); //btnEdit.addActionListener(new ActionListener()


		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent objAE) {
				if(LeaveAnOrg.boolLeaveAnOrg) {
					try {		
						String strSQLInsert = "INSERT INTO tblorgsjoin " + "(strorgsjoined, struser) VALUES " + 
								"('" + LeaveAnOrg.selectedLOrg + "', '" + Homescreen.struseremail + "');"; 
            
						objSQLQuery.executeUpdate(strSQLInsert);
						System.out.println("Rows inserted on the table..");

					} catch (Exception objEx) {
						System.out.println("Problem adding information..");
						System.out.println(objEx);
					}// try
				}// if(LeaveAnOrg.boolLeaveAnOrg)

				if(Search.boolSearch) {
					try {
						String strSQLInsert = "INSERT INTO tblorgsjoin " + "(strorgsjoined, struser) VALUES " + 
								"('" + Search.selectedSOrg + "', '" + Homescreen.struseremail  + "');"; 
            
						objSQLQuery.executeUpdate(strSQLInsert);
						System.out.println("Rows inserted on the table..");

					} catch (Exception objEx) {
						System.out.println("Problem adding information..");
						System.out.println(objEx);
					}// try
				}// if(Search.boolSearch)	
			} //public void actionPerformed(ActionEvent objAE)
		}); //btnNewButton.addActionListener(new ActionListener()

		btnLeaveThisOrg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent objAE) {
				if(LeaveAnOrg.boolLeaveAnOrg) {
					try {
						String strSQLInsert = "DELETE FROM tblorgsjoin WHERE struser = '" +                                
								Homescreen.struseremail + "' AND strorgsjoined = '" + LeaveAnOrg.selectedLOrg + "';"; 
            
						objSQLQuery.executeUpdate(strSQLInsert);
           			    System.out.println("Row deleted from the table..");

       				 } catch (Exception objEx) {
       					 System.out.println("Problem deleting information..");
       					 JOptionPane.showMessageDialog(null, "You're not a member yet!");
            			 System.out.println(objEx);
       				 }// try
				}// if(LeaveAnOrg.boolLeaveAnOrg)
			} //public void actionPerformed(ActionEvent objAE)
		}); //btnLeaveThisOrg.addActionListener(new ActionListener()

		
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent objAE) {
				MainActivity.ActivityNewsFeed();
				ClickingAnOrg.this.dispose();
				LeaveAnOrg.boolLeaveAnOrg = false;
				Search.boolSearch = false;
			} //public void actionPerformed(ActionEvent objAE)
		}); //btnBack.addActionListener(new ActionListener()
	} //public void setupListener()

	public void WriteAPost() {
		ClickingAnOrg frmWitePost = new ClickingAnOrg();
		frmWitePost.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    
   		frmWitePost.setSize(400, 200);  
   		frmWitePost.setLayout(new FlowLayout());

		txtWritePost = new JTextField(30);
		frmWitePost.add(txtWritePost);  
 		
		btnSavePost = new JButton("Save");
		frmWitePost.add(btnSavePost);
		String strPostTitle = JOptionPane.showInputDialog(frmWitePost,"Enter Title:");
		String strPostBody = JOptionPane.showInputDialog(null,"Write Body:");
		System.out.println(strPostTitle+"|"+strPostBody);
		
		try {
			String insertPostStatement = "INSERT INTO tblposts (strheading,strbody,dtime,strorgname) VALUES " + "('"+strPostTitle+"','"+strPostBody+"',"+"NOW()"+",'"+Search.selectedSOrg+"');";  
			objSQLQuery.executeUpdate(insertPostStatement);
			System.out.println("Successfully added post to the table");

		}catch (Exception objEx) {
  			System.out.println("Problem adding posts information..");
   			System.out.println(objEx);

		} //try
	} //public void WriteAPost() 
} //public class ClickingAnOrg extends JFrame implements Runnable

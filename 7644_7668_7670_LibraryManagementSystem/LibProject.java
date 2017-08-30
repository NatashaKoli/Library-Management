/********************************* SOURCE CODE *********************************/

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import java.util.Date;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.text.SimpleDateFormat;
import java.text.DateFormat;

interface Home 											//INTERFACE
{	
	void createHome();
	void displayHome();
	JLabel lblMain = new JLabel("Library Name");		//Label for Library Name
}


/**************************** Library Implements Home and HAS Account *********************************/

class Library implements Home,ActionListener 	
{
	JFrame frmObjHome;

	JButton btnAdminLogin,btnUserLogin;
	JButton btnExitfromHome;

	BorderLayout brObj;
	JPanel pnlCenter,pnlSouth,pnlNorth;

	public void createHome() 							//Create home page
	{
		frmObjHome =  new JFrame("Library Management System");

		btnAdminLogin = new JButton("Admin Login");
		btnUserLogin = new JButton("User Login");
		btnExitfromHome = new JButton("Exit");

		brObj = new BorderLayout();
		pnlCenter = new JPanel();
		pnlSouth = new JPanel();
		pnlNorth = new JPanel();
	}

	public void displayHome()							//Display Home Page
	{
		frmObjHome.setLayout(brObj);

		frmObjHome.add(pnlNorth,BorderLayout.NORTH);
			pnlNorth.add(lblMain);

		frmObjHome.add(pnlCenter,BorderLayout.CENTER);
			pnlCenter.add(btnAdminLogin);
			pnlCenter.add(btnUserLogin);

		frmObjHome.add(pnlSouth,BorderLayout.SOUTH);
			pnlSouth.add(btnExitfromHome);

		btnAdminLogin.addActionListener(this);
		btnUserLogin.addActionListener(this);
		btnExitfromHome.addActionListener(this);

		frmObjHome.setSize(600,600);
		frmObjHome.setVisible(true);
	}

	

public void actionPerformed(ActionEvent e)
	{	
		if(e.getSource().equals(btnAdminLogin))			//If Admin Login Button is clicked
		{
			frmObjHome.setVisible(false);
			Account obj = new Account();
			obj.createAdminLogin();
			obj.displayAdminLogin();
		}
		else if(e.getSource().equals(btnUserLogin))		 //If User Login Button is clicked
		{
			frmObjHome.setVisible(false);
			Account obj = new Account();
			obj.createUserLogin();
			obj.displayUserLogin();
		}
		else if(e.getSource().equals(btnExitfromHome))	//If Exit from Home Button is clicked
		{
			frmObjHome.dispose();
			System.exit(0);
		}
	}
}


/********************************* Class Account HAS Admin and User ***********************************/

class Account implements ActionListener 
{
	JFrame frmObjAdminLogin,frmObjUserLogin;

	JButton btnLoginA,btnLoginU;
	JButton btnExitfromAdminLogin,btnExitfromUserLogin;
	JButton btnBackfromAdminLogin,btnBackfromUserLogin;

	JLabel lblMain,lblPage;
	JLabel lblAdminId,lblAdminPass,lblUserId,lblUserPass;

	JTextField txtAdminId,txtAdminPass,txtUserId,txtUserPass;

	BorderLayout brObj;
	JPanel pnlCenter,pnlSouth,pnlNorth;
	JPanel pnlId,pnlPass;

	Connection con;
	static int totalUserCount=0;					//STATIC variable to keep count of total users
	int userId;
	String userName,userPass,userAddr,userMob;

	Account()										//Admin and User Login Database Connection
	{
		try
		{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String url =   "jdbc:sqlserver://Melita\\SQLEXPRESS:1433;user=sa;password=Mel.7668;databaseName=Library";
			con = DriverManager.getConnection(url);

			String getCountquery = "select max(ID) from Login";
			Statement p =con.createStatement();
			ResultSet getCountResult = p.executeQuery(getCountquery);
			getCountResult.next();
			totalUserCount = getCountResult.getInt(1);
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null,"Database could not be connected"+e.getMessage());
		}
	}

	void createAdminLogin()						
	{
		frmObjAdminLogin = new JFrame("Library Management System");
		lblPage = new JLabel("Admin Login");
		lblMain = new JLabel("Library Name");

		lblAdminId = new JLabel("Enter ID");
		lblAdminPass = new JLabel("Enter Password");

		txtAdminId = new JTextField(20);
		txtAdminPass = new JTextField(20);

		btnLoginA = new JButton("LOGIN");
		btnBackfromAdminLogin = new JButton("Back");
		btnExitfromAdminLogin = new JButton("Exit");

		brObj = new BorderLayout();
		pnlCenter = new JPanel();
		pnlSouth = new JPanel();
		pnlNorth = new JPanel();
		pnlId = new JPanel();
		pnlPass = new JPanel();
	}

	public void displayAdminLogin()						//Display Admin Login
	{
		frmObjAdminLogin.setLayout(brObj);

		frmObjAdminLogin.add(pnlNorth,BorderLayout.NORTH);
		pnlNorth.add(lblMain);

		frmObjAdminLogin.add(pnlCenter,BorderLayout.CENTER);
			pnlCenter.add(lblPage);
			pnlCenter.add(pnlId);
				pnlId.add(lblAdminId);
				pnlId.add(txtAdminId);
			pnlCenter.add(pnlPass);
				pnlPass.add(lblAdminPass);
				pnlPass.add(txtAdminPass);
			pnlCenter.add(btnLoginA);

		frmObjAdminLogin.add(pnlSouth,BorderLayout.SOUTH);
			pnlSouth.add(btnBackfromAdminLogin);
			pnlSouth.add(btnExitfromAdminLogin);	

		btnBackfromAdminLogin.addActionListener(this);
		btnExitfromAdminLogin.addActionListener(this);
		btnLoginA.addActionListener(this);

		frmObjAdminLogin.setSize(600,600);
		frmObjAdminLogin.setVisible(true);
	}


	public void createUserLogin()
	{
		frmObjUserLogin = new JFrame("Library Management System");
		lblPage = new JLabel("User Login");
		lblMain = new JLabel("Library Name");

		lblUserId = new JLabel("Enter ID");
		lblUserPass = new JLabel("Enter Password");
		txtUserId = new JTextField(15);
		txtUserPass = new JTextField(10);

		btnLoginU = new JButton("LOGIN");
		btnBackfromUserLogin = new JButton("Back");
		btnExitfromUserLogin = new JButton("Exit");

		brObj = new BorderLayout();
		pnlCenter = new JPanel();
		pnlSouth = new JPanel();
		pnlNorth = new JPanel();
		pnlId = new JPanel();
		pnlPass = new JPanel();
	}

	public void displayUserLogin()					//Display User Login
	{
		frmObjUserLogin.setLayout(brObj);

		frmObjUserLogin.add(pnlNorth,BorderLayout.NORTH);
		pnlNorth.add(lblMain);

		frmObjUserLogin.add(pnlCenter,BorderLayout.CENTER);
			pnlCenter.add(lblPage);
			pnlCenter.add(pnlId);
				pnlId.add(lblUserId);
				pnlId.add(txtUserId);
			pnlCenter.add(pnlPass);
				pnlPass.add(lblUserPass);
				pnlPass.add(txtUserPass);
			pnlCenter.add(btnLoginU);

		frmObjUserLogin.add(pnlSouth,BorderLayout.SOUTH);
			pnlSouth.add(btnBackfromUserLogin);
			pnlSouth.add(btnExitfromUserLogin);

		btnBackfromUserLogin.addActionListener(this);
		btnExitfromUserLogin.addActionListener(this);
		btnLoginU.addActionListener(this);

		frmObjUserLogin.setSize(600,600);
		frmObjUserLogin.setVisible(true);
	}	

	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource().equals(btnBackfromAdminLogin))		//If Back from Admin Login button is clicked
		{
			frmObjAdminLogin.setVisible(false);
			Library lib = new Library();
				lib.createHome();
				lib.displayHome();
		}
		else if(e.getSource().equals(btnLoginA))			//If Admin Login button is clicked
		{
			try
			{
				Statement st = con.createStatement();
				String s = "select * from Login";
				ResultSet rs = st.executeQuery(s);

				String idEnt = txtAdminId.getText();
				String passEnt = txtAdminPass.getText();
				int flag=0;

				while(rs.next())
				{
					if(rs.getString(1).equals("A")&&rs.getString(2).equals(idEnt)&&rs.getString(3).equals(passEnt))
					{
						frmObjAdminLogin.setVisible(false);
						Admin ad = new Admin();
						ad.createAdmin();
						ad.displayAdmin();
						flag=1;		
						break;
					}
				}

				JFrame frame = new JFrame();
				if(flag==0)
					JOptionPane.showMessageDialog(frame,"Invalid Id or Password","Data Validation",JOptionPane.WARNING_MESSAGE);
			}
			catch(Exception e1)
			{
				JOptionPane.showMessageDialog(null,"ERROR");
			}
		}
		else if(e.getSource().equals(btnExitfromAdminLogin))	//If Exit from Admin Login Button is clicked
		{
			frmObjAdminLogin.dispose();
			System.exit(0);
		}

		else if(e.getSource().equals(btnBackfromUserLogin))		//If Back from User Login Button is clicked
		{
			frmObjUserLogin.setVisible(false);
			Library lib = new Library();
			lib.createHome();
			lib.displayHome();
		}
		else if(e.getSource().equals(btnLoginU))				//If User Login Button is clicked
		{
			try
			{
				Statement st = con.createStatement();
				String s = "select * from Login";
				ResultSet rs = st.executeQuery(s);

				int idEnt = Integer.parseInt(txtUserId.getText());
				String passEnt = txtUserPass.getText();
				int flag=0;

				while(rs.next())
				{
					if(rs.getString(1).equals("U")&&rs.getInt(2)==idEnt
					&&rs.getString(3).equals(passEnt))
					{
						frmObjUserLogin.setVisible(false);
						User us = new User();
						us.getId(idEnt);
						us.createUser();
						us.displayUser();
						flag=1;	
						break;
					}
				}

				JFrame frame = new JFrame();
				if(flag==0)
					JOptionPane.showMessageDialog(frame,"Invalid Id or Password","Data Validation",JOptionPane.WARNING_MESSAGE);
			}
			catch(SQLException e1)
			{
				JOptionPane.showMessageDialog(null,"ERROR");
			}
		}
		else if(e.getSource().equals(btnExitfromUserLogin))		//If Exit from User Login Button is clicked
		{
			frmObjUserLogin.dispose();
			System.exit(0);
		}
	}

	void getStudDetails()									//To display Student Details to Admin
	{
		try
		{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String url = "jdbc:sqlserver://Melita\\SQLEXPRESS:1433;user=sa;password=Mel.7668;databaseName=Library";
			con = DriverManager.getConnection(url);
			Statement st = con.createStatement();
			String s = "select * from Login";
			ResultSet rs = st.executeQuery(s);
			ResultSetMetaData rsmt = rs.getMetaData();
			int c = rsmt.getColumnCount();

			Vector column = new Vector(c);
			Vector data = new Vector();
			Vector row = new Vector();

			for(int i=2;i<=c;i++)
				column.add(rsmt.getColumnName(i));
			
			while(rs.next())
			{
				row = new Vector(c);
				if(rs.getString(1).equals("U"))
				{
					for(int i=2;i<=c;i++)
						row.add(rs.getString(i));
					data.add(row);
				}
			}

			JPanel pnl = new JPanel();
			JTable table = new JTable(data,column);
			JScrollPane jsp = new JScrollPane(table);
			
			Admin.pnlCenter.add(pnl,BorderLayout.SOUTH);
			pnl.add(jsp,BorderLayout.CENTER);
			Admin.frmObjAdmin.setVisible(true);
			con.close();
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null,"ERROR");
		}
	}

	void addNewUser()							//To add New User
	{
		JFrame frame = new JFrame();
		frame.setSize(400,350);

		GridLayout grObj = new GridLayout(0,1);

		final ImageIcon icon = new ImageIcon("C:\\Users\\Assisi\\Desktop\\Library\\newuser.png");
		Image img = icon.getImage();
		Image newImg = img.getScaledInstance(75, 50,  java.awt.Image.SCALE_SMOOTH);
		ImageIcon newIcon = new ImageIcon(newImg);

		JLabel lblUserName = new JLabel("Enter Name");
		JLabel lblUserPass = new JLabel("Enter Password");
		JLabel lblUserAdd = new JLabel("Enter Address");
		JLabel lblUserMob = new JLabel("Enter Mobile No");

		JTextField txtUserName = new JTextField(10);
		JTextField txtUserPass = new JTextField(10);
		JTextField txtUserAdd = new JTextField(10);
		JTextField txtUserMob = new JTextField(10);

		JPanel pnlCenter = new JPanel(grObj);
			pnlCenter.add(lblUserName);	
        		pnlCenter.add(txtUserName);	
        	pnlCenter.add(lblUserPass);	
        		pnlCenter.add(txtUserPass);	
        	pnlCenter.add(lblUserAdd);	
        		pnlCenter.add(txtUserAdd);	
        	pnlCenter.add(lblUserMob);	
        		pnlCenter.add(txtUserMob);	

    		Object[] optionsNew = {"Save","Cancel"};
		int n = JOptionPane.showOptionDialog(frame,pnlCenter, "Add New User Book", JOptionPane.YES_NO_OPTION, 
				JOptionPane.QUESTION_MESSAGE, newIcon, optionsNew, optionsNew[1]); 

		if(n==0)									//Add user to database
		{
			try
			{
				userId = ++totalUserCount;
				userName = txtUserName.getText();
				userPass = txtUserPass.getText();
				userAddr = txtUserAdd.getText();
				userMob = txtUserMob.getText();

				String insertSQL = "insert into Login values(?,?,?,?,?,?)";
				PreparedStatement ps = con.prepareStatement(insertSQL);
					ps.setString(1,"U");
					ps.setInt(2,userId);
					ps.setString(4,userName);
					ps.setString(3,userPass);
					ps.setString(5,userAddr);
					ps.setString(6,userMob);
					ps.executeUpdate();

				JOptionPane.showMessageDialog(frame, "New User Added!! \n ID no: " + totalUserCount);
				con.close();
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(null,"Database could not be connected"+e.getMessage());
			}
		}
	}

	void removeUser()							//To remove User
	{
		JFrame frame = new JFrame();
		frame.setSize(400,350);

		final ImageIcon icon = new ImageIcon("C:\\Users\\Assisi\\Desktop\\Library\\deleteuser.png");
		Image img = icon.getImage();
		Image newImg = img.getScaledInstance(75, 50,  java.awt.Image.SCALE_SMOOTH);
		ImageIcon newIcon = new ImageIcon(newImg);

		JLabel lblUserId = new JLabel("Enter User ID");
		JTextField txtUserId = new JTextField(10);

		JPanel pnlCenter = new JPanel();
			pnlCenter.add(lblUserId);	
        			pnlCenter.add(txtUserId);	

    		Object[] optionsNew = {"Save","Cancel"};
		int n = JOptionPane.showOptionDialog(frame,pnlCenter, "Remove User", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, newIcon, optionsNew, optionsNew[1]);

		if(n==0)									//Remove user from database
		{
			try
			{
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				String url = " jdbc:sqlserver://Melita\\SQLEXPRESS:1433;user=sa;password=Mel.7668;databaseName=Library";					con = DriverManager.getConnection(url);
				int userId = Integer.parseInt(txtUserId.getText());

    				String sql = "DELETE FROM Login WHERE ID="+userId;
    				Statement stmt1 = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
				stmt1.executeUpdate(sql);

JOptionPane.showMessageDialog(frame, "User Removed!! \n User ID: " + userId + "Deleted");
con.close();
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(null,"User not removed"+e.getMessage());
			}
		}
	}
}


/**************************** Class Admin HAS an Account and OPERATES on Books *****************************/
											//(ASSOCIATION)

class Admin implements ActionListener
{
	public static JFrame frmObjAdmin;

	JButton btnStudents,btnRegBooks,btnBbBooks,btnRegIssued,btnBbIssued,btnRegIssuedStud,btnBbIssuedStud;
	JButton btnNewBook,btnDelBook,btnNewUser,btnRemoveUser;
	JButton btnExitfromAdmin,btnLogoutAdmin;

	JLabel lblMain,lblPage;
	JLabel lblAdminId,lblAdminPass;
	JTextField txtAdminId,txtAdminPass;

	BorderLayout brObj;
	public static JPanel pnlCenter;
	JPanel pnlSouth,pnlNorth,pnlWest,pnlWestCenter;
	JPanel pnlId,pnlPass;

	public void createAdmin()
	{
		frmObjAdmin = new JFrame("Library Management System");

		lblMain = new JLabel("Library Name");
		lblPage = new JLabel("Welcome ______");

		btnStudents = new JButton("View Student Details");
		btnRegBooks = new JButton("View All Books");
		btnBbBooks = new JButton("View All BookBank Books");
		btnRegIssued = new JButton("View Issued Books");
		btnBbIssued = new JButton("View Issued BookBank Books");

		btnNewBook = new JButton("Add New Book");
		btnDelBook = new JButton("Remove Book");
		btnNewUser = new JButton("Add New User");
		btnRemoveUser = new JButton("Remove User");

		btnLogoutAdmin = new JButton("Logout");
		btnExitfromAdmin = new JButton("Exit");

		brObj = new BorderLayout();
		pnlCenter = new JPanel();
		pnlSouth = new JPanel();
		pnlNorth = new JPanel();
		pnlWest = new JPanel(new BorderLayout(1,1));
			pnlWestCenter = new JPanel(new BorderLayout(1,1));
	}

	public void displayAdmin()							//Display admin page
	{
		frmObjAdmin.setLayout(brObj);

		frmObjAdmin.add(pnlNorth,BorderLayout.NORTH);
		pnlNorth.add(lblMain);
		pnlNorth.add(btnLogoutAdmin);

		frmObjAdmin.add(pnlCenter,BorderLayout.CENTER);
			pnlCenter.add(lblPage,BorderLayout.NORTH);

		frmObjAdmin.add(pnlSouth,BorderLayout.SOUTH);
			pnlSouth.add(btnNewBook);
			pnlSouth.add(btnDelBook);
			pnlSouth.add(btnNewUser);
			pnlSouth.add(btnRemoveUser);
			pnlSouth.add(btnExitfromAdmin);

		frmObjAdmin.add(pnlWest,BorderLayout.WEST);
			pnlWest.add(btnStudents,BorderLayout.NORTH);
			pnlWest.add(pnlWestCenter,BorderLayout.CENTER);
			pnlWestCenter.add(btnRegBooks,BorderLayout.NORTH);
			pnlWestCenter.add(btnBbBooks,BorderLayout.CENTER);
			pnlWestCenter.add(btnRegIssued,BorderLayout.SOUTH);
			pnlWest.add(btnBbIssued,BorderLayout.SOUTH);
	
		btnStudents.addActionListener(this);
		btnRegBooks.addActionListener(this);
		btnBbBooks.addActionListener(this);
		btnRegIssued.addActionListener(this);
		btnBbIssued.addActionListener(this);
		btnNewBook.addActionListener(this);
		btnDelBook.addActionListener(this);
		btnNewUser.addActionListener(this);
		btnRemoveUser.addActionListener(this);
		btnLogoutAdmin.addActionListener(this);
		btnExitfromAdmin.addActionListener(this);

		frmObjAdmin.setSize(600,600);
		frmObjAdmin.setVisible(true);
	}

	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource().equals(btnStudents))			//If Display student details is clicked
		{
			pnlCenter.removeAll();
			Account obj = new Account();
			obj.getStudDetails();
		}
		else if(e.getSource().equals(btnRegBooks))	//If Display All Regular Books is clicked
		{
			int x;
			do
			{
				x=0;
				JFrame frame = new JFrame();
				JFrame frmObj = new JFrame();
   				frame.setSize(600,600);

			  	JRadioButton rbTech = new JRadioButton("Technical");
			   	JRadioButton rbNonTech = new JRadioButton("Non-Technical");
				ButtonGroup brType = new ButtonGroup();
					brType.add(rbTech);
					brType.add(rbNonTech);
        			
				JPanel pnlType = new JPanel();
 				       	pnlType.add(rbTech);	
    			    		pnlType.add(rbNonTech);	

    			    	pnlCenter.removeAll();

    				Object[] options = {"Next","Cancel"};
				int n = JOptionPane.showOptionDialog(frame,pnlType,"Choose Type",JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE,null,options,options[1]);

				if(n==0)
				{
					if(rbTech.isSelected()==false&&rbNonTech.isSelected()==false)
					{
						JOptionPane.showMessageDialog(frmObj,"Type Not Selected","Data Validation",JOptionPane.WARNING_MESSAGE);
						x=1;
					}
					else if(rbTech.isSelected()==true)
					{	
						Tech b = new Tech('A');
						b.displayRegBooks();
						x=0;
					}
					else
					{
						NonTech b = new NonTech('A');
						b.displayRegBooks();
						x=0;
					}
				}
				else
					x=0;
			}
			while(x==1);
		}
		else if(e.getSource().equals(btnBbBooks))	//If Display BookBank books is clicked
		{
			pnlCenter.removeAll();
			Tech t = new Tech('A');
			t.displayBbBooks();
		}
		else if(e.getSource().equals(btnNewBook))	//If Add New Book is clicked
		{
			int x;
			do
			{
				x=0;
				JFrame frame = new JFrame();
				JFrame frmObj = new JFrame();
	    			frame.setSize(600,600);

			  	JRadioButton rbTech = new JRadioButton("Technical");
			   	JRadioButton rbNonTech = new JRadioButton("Non-Technical");
				ButtonGroup brType = new ButtonGroup();
					brType.add(rbTech);
					brType.add(rbNonTech);
        		
	 			 JPanel pnlType = new JPanel();
 			       		pnlType.add(rbTech);	
    		    			pnlType.add(rbNonTech);	

    				Object[] options = {"Next","Cancel"};
				int n = JOptionPane.showOptionDialog(frame,pnlType,"Add New Book",JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE,null, options, options[1]); 

				if(n==0)
				{
					if(rbTech.isSelected()==false&&rbNonTech.isSelected()==false)
					{
						JOptionPane.showMessageDialog(frmObj,"Type Not Selected","Data Validation",JOptionPane.WARNING_MESSAGE);
						x=1;
					}
					else if(rbTech.isSelected()==true)
					{	
						Tech b = new Tech('A');
						x = b.addNewBook();
					}
					else
					{
						NonTech b = new NonTech('A');
						x = b.addNewBook();
					}
				}
				else
					x=0;
			}
			while(x==1);
		}
		else if(e.getSource().equals(btnDelBook))			//If Delete Book is clicked
		{
			Books b = new NonTech();
			b.removeBook();	
		}
		else if(e.getSource().equals(btnNewUser))		//If Add New User is clicked
		{
			Account obj = new Account();
			obj.addNewUser();
		}
		else if(e.getSource().equals(btnRemoveUser))		//If Remove User is clicked
		{
			Account obj = new Account();
			obj.removeUser();
		}
		else if(e.getSource().equals(btnLogoutAdmin))		//If Admin Logout is clicked
		{
			JFrame frame = new JFrame();
			int n = JOptionPane.showConfirmDialog(frame,"Are you sure you want to Logout?", "Logout",									JOptionPane.YES_NO_OPTION);

			if(n==0)
			{
				frmObjAdmin.setVisible(false);
				Account obj = new Account();
				obj.createAdminLogin();
				obj.displayAdminLogin();
			}
		}
		else if(e.getSource().equals(btnExitfromAdmin))		//If Exit from Admin is clicked
		{
			frmObjAdmin.dispose();
			System.exit(0);
		}		
	}	
}


/**************************** Class User HAS an Account and OPERATES on Books *****************************/
											//(ASSOCIATION)

class User implements ActionListener
{
	public static JFrame frmObjUser;

	JButton btnRegBooks,btnBbBooks,btnIssuedStud;
	JButton btnExitfromUser,btnLogoutUser;

	JLabel lblMain,lblPage;
	JLabel lblUserId,lblUserPass;
	JTextField txtUserId,txtUserPass;

	BorderLayout brObj;
	public static JPanel pnlCenter;
	JPanel pnlSouth,pnlNorth,pnlWest;
	JPanel pnlId,pnlPass;
	int entId;

	void getId(int entId)
	{
		this.entId = entId;
	}

	public void createUser()
	{
		frmObjUser = new JFrame("Library Management System");

		lblMain = new JLabel("Library Name");
		lblPage = new JLabel("Welcome ______");

		btnRegBooks = new JButton("View All Books");
		btnBbBooks = new JButton("View All BookBank Books");
		btnIssuedStud = new JButton("View Issued Books");
		btnLogoutUser = new JButton("Logout");
		btnExitfromUser = new JButton("Exit");

		brObj = new BorderLayout();
		pnlCenter = new JPanel();
		pnlNorth = new JPanel();
		pnlWest = new JPanel(new BorderLayout(1,1));
		pnlSouth = new JPanel();
	}

	public void displayUser()						//Display User Page
	{
		frmObjUser.setLayout(brObj);
		frmObjUser.add(pnlNorth,BorderLayout.NORTH);
			pnlNorth.add(lblMain);
			pnlNorth.add(btnLogoutUser);

		frmObjUser.add(pnlCenter,BorderLayout.CENTER);
			pnlCenter.add(lblPage);

		frmObjUser.add(pnlWest,BorderLayout.WEST);
			pnlWest.add(btnRegBooks,BorderLayout.NORTH);
			pnlWest.add(btnBbBooks,BorderLayout.CENTER);
			pnlWest.add(btnIssuedStud,BorderLayout.SOUTH);

		frmObjUser.add(pnlSouth,BorderLayout.SOUTH);
			pnlSouth.add(btnExitfromUser);

		btnRegBooks.addActionListener(this);
		btnBbBooks.addActionListener(this);
		btnIssuedStud.addActionListener(this);
		btnLogoutUser.addActionListener(this);
		btnExitfromUser.addActionListener(this);				

		frmObjUser.setSize(600,600);
		frmObjUser.setVisible(true);
	}

	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource().equals(btnRegBooks))		//If Display All Regular Books is clicked
		{
			int x;
			do
			{
				x=0;
				JFrame frame = new JFrame();
				JFrame frmObj = new JFrame();
    				frame.setSize(600,600);

			  	JRadioButton rbTech = new JRadioButton("Technical");
			   	JRadioButton rbNonTech = new JRadioButton("Non-Technical");
				ButtonGroup brType = new ButtonGroup();
					brType.add(rbTech);
					brType.add(rbNonTech);
       			
				 JPanel pnlType = new JPanel();
			  	     	pnlType.add(rbTech);	
   			    		pnlType.add(rbNonTech);	

   			    	pnlCenter.removeAll();

   				Object[] options = {"Next","Cancel"};
				int n = JOptionPane.showOptionDialog(frame,pnlType,"Choose Type",
	    				JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,
   					null, options, options[1]);

				if(n==0)
				{
					if(rbTech.isSelected()==false&&rbNonTech.isSelected()==false)
					{
						JOptionPane.showMessageDialog(frmObj,"Type Not Selected","Data Validation",JOptionPane.WARNING_MESSAGE);
						x=1;
					}
					else if(rbTech.isSelected()==true)
					{	
						Tech b = new Tech('U');
						b.displayRegBooks();
						x=0;
					}
					else
					{
						NonTech b = new NonTech('U');
						b.displayRegBooks();
						x=0;
					}
				}
				else
					x=0;
			}
			while(x==1);
		}
		else if(e.getSource().equals(btnBbBooks))			//If display all BookBank books is clicked
		{
			pnlCenter.removeAll();
			Tech b = new Tech('U');
			b.displayBbBooks();
		}
		else if(e.getSource().equals(btnIssuedStud))		//If Display Issued Books by that Student is clicked
		{
			pnlCenter.removeAll();
   			Tech b = new Tech();
			b.displayIssuedStud(entId);
		}
		else if(e.getSource().equals(btnLogoutUser))		//If Logout of User Account is clicked
		{
			JFrame frame = new JFrame();
			int n = JOptionPane.showConfirmDialog(frame, "Are you sure you want to Logout?", "Logout",
   				JOptionPane.YES_NO_OPTION);

			if(n==0)
			{
				frmObjUser.setVisible(false);
				Account obj = new Account();
				obj.createUserLogin();
				obj.displayUserLogin();
			}
		}
		else if(e.getSource().equals(btnExitfromUser))		//If Exit from User account is clicked
		{
			frmObjUser.dispose();
			System.exit(0);
		}
	}			
}


/***************************************** Class Books is ABSTRACT *****************************************/
											//(PARENT CLASS)

abstract class Books
{
	int bkId,bkPeriod,bkAvl;
	String bkName,bkAuthor,bkType;
	String[] bkCategory = new String[4];
	char caller;
	Connection con;

	Books()							//For Database Connection
	{
		try
		{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String url = "jdbc:sqlserver://Melita\\SQLEXPRESS:1433;user=sa;password=Mel.7668;databaseName=Library";
			con = DriverManager.getConnection(url);
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null,"Database could not be connected"+e.getMessage());
		}
	}

	Books(String a,String b,String c,String d)			//Initialize the Book Categories
	{
		this();									//"this" Keyword
		bkCategory[0] = a;
		bkCategory[1] = b;
		bkCategory[2] = c;
		bkCategory[3] = d;
	}

	abstract void displayRegBooks();			//ABSTRACT FUNCTION

	int addNewBook(String bkBb)					//To add new book into the database
	{
		GridLayout grObj = new GridLayout(0,1);
		JFrame frame = new JFrame();

		final ImageIcon icon = new ImageIcon("C:\\Users\\Assisi\\Desktop\\Library\\newbook.png");
		Image img = icon.getImage();
		Image newImg = img.getScaledInstance(75, 50,  java.awt.Image.SCALE_SMOOTH);
		ImageIcon newIcon = new ImageIcon(newImg);

		JComboBox cbxCategory = new JComboBox(bkCategory);

		JTextField txtBookID = new JTextField(10);
      	JTextField txtBookName = new JTextField(10);
        JTextField txtBookAuthor = new JTextField(10);
       	JTextField txtBookCount = new JTextField(10);
        JPanel pnlNewBook = new JPanel(grObj);
   			pnlNewBook.add(cbxCategory);
			pnlNewBook.add(new JLabel("Book ID:"));
            	pnlNewBook.add(txtBookID);
            pnlNewBook.add(new JLabel("Book Name:"));
            	pnlNewBook.add(txtBookName);
            pnlNewBook.add(new JLabel("Book Author:"));
            	pnlNewBook.add(txtBookAuthor);
           	pnlNewBook.add(new JLabel("No. of Books:"));
        		pnlNewBook.add(txtBookCount);

	    	Object[] optionsNew = {"Save","Back","Cancel"};
		int n = JOptionPane.showOptionDialog(frame,pnlNewBook, "Add New Book", JOptionPane.YES_NO_OPTION,
    			JOptionPane.QUESTION_MESSAGE, newIcon, optionsNew, 	optionsNew[2]);

		if(n==0)
		{
			try
			{
				String cat = String.valueOf(cbxCategory.getSelectedItem());
				int bkId = Integer.parseInt(txtBookID.getText());
				String bkName = txtBookName.getText();
				String bkAuthor = txtBookAuthor.getText();
				int bkAvl = Integer.parseInt(txtBookCount.getText());

				String insertSQL = "insert into Available values(?,?,?,?,?,?,?,?)";
				PreparedStatement ps = con.prepareStatement(insertSQL);
					ps.setInt(1,bkId);
					ps.setString(2,bkType);
					ps.setString(3,cat);
					ps.setString(4,bkName);
					ps.setString(5,bkAuthor);
					ps.setString(6,bkBb);
					ps.setInt(7,bkPeriod);
					ps.setInt(8,bkAvl);
				ps.executeUpdate();

				JOptionPane.showMessageDialog(frame, "New Book Added!!");
				con.close();
				return 0;
			}
			catch(SQLException e)
			{
				JOptionPane.showMessageDialog(null,"SQLException:"+e.getMessage());
				return 1;
			}
		}
		else if(n==1)
			return 1;
		else
			return 0; 
	}

	void issueBook()					//To Issue a book
	{
		JFrame frame = new JFrame();
		frame.setSize(400,350);

		final ImageIcon icon = new ImageIcon("C:\\Users\\Assisi\\Desktop\\Library\\issuebk.jpg");
		Image img = icon.getImage();
		Image newImg = img.getScaledInstance(75, 50,  java.awt.Image.SCALE_SMOOTH);
		ImageIcon newIcon = new ImageIcon(newImg);

		JLabel lblStudId = new JLabel("Enter Student ID");
		JTextField txtStudId = new JTextField(10);
		JPanel pnlCenter = new JPanel();
			pnlCenter.add(lblStudId);	
        			pnlCenter.add(txtStudId);	

    		Object[] optionsNew = {"Save","Cancel"};
		int n = JOptionPane.showOptionDialog(frame,pnlCenter, "Issue Book", JOptionPane.YES_NO_OPTION,
    			JOptionPane.QUESTION_MESSAGE, newIcon, optionsNew, optionsNew[1]);

		if(n==0)
		{
			try
			{
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				String url = "jdbc:sqlserver://Melita\\SQLEXPRESS:1433;user=sa;password=Mel.7668;databaseName=Library";
				con = DriverManager.getConnection(url);
				Statement st = con.createStatement();
				String s = "select * from Login";
				ResultSet rs = st.executeQuery(s);

				int studId = Integer.parseInt(txtStudId.getText());
				int flag=0;

				while(rs.next())
				{
					if(rs.getString(1).equals("U")&&rs.getString(2).equals(studId))
					{
						s = "select * from Available";
						rs = st.executeQuery(s);
						ResultSetMetaData rsmt = rs.getMetaData();
						String insertSQL = "insert into Issued values(?,?,?,?,?,?,?,?,?)";
						PreparedStatement ps = con.prepareStatement(insertSQL);
							ps.setInt(1,studId);
							ps.setInt(2,bkId);

						while(rs.next())
						{
							if(rs.getInt(1)==bkId)
							{
								flag=1;
								ps.setString(3,rs.getString(2));
								ps.setString(4,rs.getString(3));																						ps.setString(5,rs.getString(4));
								ps.setString(6,rs.getString(5));
								ps.setString(7,rs.getString(6));
								ps.setString(8,getCurrentDate());
								ps.setString(9,getReturnDate());
								ps.executeUpdate();

								String sql = "UPDATE Available " + "SET Availability = Availability-1 WHERE ID = " + bkId;
								st.executeUpdate(sql);			
								JOptionPane.showMessageDialog(frame, "Book " + bkId + " Issued!!");
								con.close();
								break;
							}
						}

						if(flag==1)
							break;
					}
				}
				
				if(flag==0)
					JOptionPane.showMessageDialog(frame, "Invalid ID!!");
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(null,"Book not Issued "+e.getMessage());
			}
		}
	}

	void displayIssuedStud(int entId)					//Display Issued Book by User
	{
		try
		{
			Statement st = con.createStatement();
			String s = "select * from Issued";
			ResultSet rs = st.executeQuery(s);
			ResultSetMetaData rsmt = rs.getMetaData();
			int c = rsmt.getColumnCount();

			Vector column = new Vector(c);
			for(int i=2;i<=c;i++)
			{
				if(i<=c)
					column.add(rsmt.getColumnName(i));
			}
			column.add("Penalty");

			Vector data = new Vector();
			Vector row = new Vector();
			while(rs.next())
			{
				if(entId==rs.getInt(1))
				{
					for(int i=2;i<=c;i++)
					{
						String temp;
						if(i==3)
						{
							temp = rs.getString(i);
							if(temp.equals("T"))
								row.add("Tech");
							else
								row.add("Non-Tech");
						}

						if(i==7)
						{
							temp = rs.getString(i);
							if(temp.equals("Y"))
								row.add("Book Bank");
							else
								row.add(" ");
						}
						row.add(rs.getString(i));
					}

					row.add(getPenalty(rs.getString(9)));
					data.add(row);

					JPanel pnl = new JPanel();
					JTable table = new JTable(data,column);
					JScrollPane jsp = new JScrollPane(table);
					User.pnlCenter.add(pnl,BorderLayout.SOUTH);
						pnl.add(jsp,BorderLayout.CENTER);
					User.frmObjUser.setVisible(true);
				}
			}

			con.close();
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null,"ERROR");
		}	
	}

	String getCurrentDate()					//Get Current Date
	{
		Instant i = LocalDate.now().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
       		 String stringDate = sdf.format(Date.from(i));
       	 	return stringDate;
	}

	String getReturnDate()						//To Get Return Date (After Issuing Book)
	{
		String retDate;
		Calendar cal = Calendar.getInstance();
		try
    	{
        			String start = getCurrentDate();
        	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            		Date startDate = df.parse(start);
       			cal.setTime(startDate);
      	 		cal.add(Calendar.DATE,bkPeriod);
          	}
       		catch(Exception e) 
       		{
        			e.printStackTrace();
       		 }
        		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
       		retDate = format1.format(cal.getTime());
       		return retDate;
	}

	Long getPenalty(String returnDate)						//Get Penalty on Return (If Returned Late)
	{
		LocalDate startDate = LocalDate.parse(returnDate);	//convert the String to LocalDate format
		LocalDate endtDate = LocalDate.now();				//current Date
   	    Long range = ChronoUnit.DAYS.between(startDate, endtDate);
        Long penalty = 0L;
        if(range>0)
        	penalty = range * 5;
		return penalty;
	}

	void removeBook()			//To remove Book from Database
	{
		JFrame frame = new JFrame();
		frame.setSize(400,350);
		final ImageIcon icon = new ImageIcon("C:\\Users\\Assisi\\Desktop\\Library\\bookdel.png");
		Image img = icon.getImage();
		Image newImg = img.getScaledInstance(75, 50,  java.awt.Image.SCALE_SMOOTH);
		ImageIcon newIcon = new ImageIcon(newImg);

		JLabel lblBookId = new JLabel("Enter Book ID");
		JTextField txtBookId = new JTextField(10);
		JPanel pnlCenter = new JPanel();
			pnlCenter.add(lblBookId);	
        			pnlCenter.add(txtBookId);	

    		Object[] optionsNew = {"Save","Cancel"};
		int n = JOptionPane.showOptionDialog(frame,pnlCenter, "Remove Book", JOptionPane.YES_NO_OPTION,
    			JOptionPane.QUESTION_MESSAGE, newIcon, optionsNew, optionsNew[1]);

		if(n==0)
		{
			try
			{
				bkId = Integer.parseInt(txtBookId.getText());
				String sql = "DELETE FROM Available WHERE ID="+bkId;
				Statement stmt1 = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
				stmt1.executeUpdate(sql);

				JOptionPane.showMessageDialog(frame, "Book Removed!! \n Book ID: " + bkId + "Deleted");
				con.close();
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(null,"Book not removed"+e.getMessage());
			}
		}					
}	
}

/****************************************** Class Tech Is A Book *******************************************/
											  //(CHILD CLASS)

class Tech extends Books
{ 
	int x;
	String bkBb;

	Tech()
	{
		super();							//"super" Keyword
	}

	Tech(char ac)							//Initialize Variables
	{
		super("Computer","Electronics","IT","Production");
		this.caller = ac;
		bkPeriod = 14;
		bkType = "T";
	}

	void displayRegBooks()					//Display Regular Tech Books (Definition of abstract function of Parent class)
	{
		try
		{
			Statement st = con.createStatement();
			String s = "select * from Available";
			ResultSet rs = st.executeQuery(s);
			ResultSetMetaData rsmt = rs.getMetaData();
			int c = rsmt.getColumnCount();

			Vector column = new Vector(c);					//VECTOR (Display list of Books in a table in java)
			for(int i=1;i<=c;i++)
				if(i!=6)
					column.add(rsmt.getColumnName(i));
			
			Vector data = new Vector();
			Vector row = new Vector();
			while(rs.next())
			{
				row = new Vector(c);
				if(rs.getString(2).equals("T")&&rs.getString(6).equals("N"))
				{	
					for(int i=1;i<=c;i++)
					{
						if(i!=6)
						{
							if(i==2)
								row.add("Tech");
							else
								row.add(rs.getString(i));
						}
					}
					data.add(row);
				}
			}

			JPanel pnl = new JPanel();
			JTable table = new JTable(data,column);
			JScrollPane jsp = new JScrollPane(table);

			if(caller=='A')
			{
				Admin.pnlCenter.add(pnl,BorderLayout.SOUTH);
				pnl.add(jsp,BorderLayout.CENTER);
				Admin.frmObjAdmin.setVisible(true);

				ListSelectionModel model = table.getSelectionModel();
				model.addListSelectionListener(new ListSelectionListener()
				{
					@Override
					public void valueChanged(ListSelectionEvent e)
					{
						if(!model.isSelectionEmpty())
						{
							int selR = model.getMinSelectionIndex();
							bkId = Integer.parseInt(table.getModel().getValueAt(selR,0).toString());
							bkAvl = Integer.parseInt(table.getModel().getValueAt(selR,6).toString());
							if(bkAvl!=0)
								issueBook();
							else
								JOptionPane.showMessageDialog(null,"Book Not Available");
						}
					}
				});
			}
			else if(caller=='U')
			{
				User.pnlCenter.add(pnl,BorderLayout.SOUTH);
				pnl.add(jsp,BorderLayout.CENTER);
				User.frmObjUser.setVisible(true);
			}

			con.close();
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null,"ERROR");
		}
	}

	void displayBbBooks()							//Display BookBank Books
	{
		try
		{
			Statement st = con.createStatement();
			String s = "select * from Available";
			ResultSet rs = st.executeQuery(s);
			ResultSetMetaData rsmt = rs.getMetaData();
			int c = rsmt.getColumnCount();

			Vector column = new Vector(c);
			Vector data = new Vector();
			Vector row = new Vector();

			for(int i=1;i<=c;i++)
				if(i!=6)
					column.add(rsmt.getColumnName(i));

			while(rs.next())
			{
				row = new Vector(c);
				if(rs.getString(6).equals("Y"))
				{	
					for(int i=1;i<=c;i++)
					{
						if(i!=6)
						{
							if(i==2)
								row.add("Tech");
							else
								row.add(rs.getString(i));
						}
					}
					data.add(row);
				}
			}

			JPanel pnl = new JPanel();
			JTable table = new JTable(data,column);
			JScrollPane jsp = new JScrollPane(table);
			
			if(caller=='A')
			{
				Admin.pnlCenter.add(pnl,BorderLayout.SOUTH);
				pnl.add(jsp,BorderLayout.CENTER);
				Admin.frmObjAdmin.setVisible(true);

				ListSelectionModel model = table.getSelectionModel();
				model.addListSelectionListener(new ListSelectionListener()
				{
				
					@Override
					public void valueChanged(ListSelectionEvent e)
					{
						if(!model.isSelectionEmpty())
						{
							int selR = model.getMinSelectionIndex();
							bkPeriod = 183;
							bkId = Integer.parseInt(table.getModel().getValueAt(selR,0).toString());
							bkAvl = Integer.parseInt(table.getModel().getValueAt(selR,6).toString());
							if(bkAvl!=0)
								issueBook();
							else
								JOptionPane.showMessageDialog(null,"Book Not Available");
						}
					}
				});
			}
			else if(caller=='U')
			{
				User.pnlCenter.add(pnl,BorderLayout.SOUTH);
				pnl.add(jsp,BorderLayout.CENTER);
				User.frmObjUser.setVisible(true);
			}
			con.close();
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null,"ERROR");
		}
	}

	int addNewBook()							//Add a New Technical Book
	{
		JFrame frame = new JFrame();
		int n = JOptionPane.showOptionDialog(frame,"Is it a Book Bank Book?",
    			"Add New Book",
		    	JOptionPane.YES_NO_OPTION,
    			JOptionPane.QUESTION_MESSAGE,null,null,null);

		if(n==0)						//Put Y in bkBb in database
			{				
				bkBb = "Y";	
				bkPeriod = 183;
			}
		else						
			bkBb = "N";				//Put N in bkBB in database
				
		x = super.addNewBook(bkBb);
		return x;
	}
}


/**************************************** Class NonTech Is A Book *****************************************/
        									//(CHILD CLASS)

class NonTech extends Books
{
	int x;

	NonTech()
	{
		super();
	}

	NonTech(char ac)							//Initialize variables
	{
		super("Communicatio Skills","Novel","Biography","Comics");
		this.caller = ac;
		bkPeriod = 7;
		bkType = "N";
	}

	void displayRegBooks()						//Display Non-Technical Regular Books
	{
		try
		{
			Statement st = con.createStatement();
			String s = "select * from Available";
			ResultSet rs = st.executeQuery(s);
			ResultSetMetaData rsmt = rs.getMetaData();
			int c = rsmt.getColumnCount();

			Vector column = new Vector(c);
			Vector data = new Vector();
			Vector row = new Vector();

			for(int i=1;i<=c;i++)
				if(i!=6)
					column.add(rsmt.getColumnName(i));
			
			while(rs.next())
			{
				row = new Vector(c);
				if(rs.getString(2).equals("N")&&rs.getString(6).equals("N"))
				{	
					for(int i=1;i<=c;i++)
					{
						if(i!=6)
						{
							if(i==2)
								row.add("Non-Tech");
							else
								row.add(rs.getString(i));
						}
					}
					data.add(row);
				}
			}

			JPanel pnl = new JPanel();
			JTable table = new JTable(data,column);
			JScrollPane jsp = new JScrollPane(table);

			if(caller=='A')
			{
				Admin.pnlCenter.add(pnl,BorderLayout.SOUTH);
				pnl.add(jsp,BorderLayout.CENTER);
				Admin.frmObjAdmin.setVisible(true);

				ListSelectionModel model = table.getSelectionModel();
				model.addListSelectionListener(new ListSelectionListener()
				{
				
					@Override
					public void valueChanged(ListSelectionEvent e)
					{
						if(!model.isSelectionEmpty())
						{
							int selR = model.getMinSelectionIndex();
							bkId = Integer.parseInt(table.getModel().getValueAt(selR,0).toString());
							bkAvl = Integer.parseInt(table.getModel().getValueAt(selR,6).toString());
							if(bkAvl!=0)
								issueBook();
							else
								JOptionPane.showMessageDialog(null,"Book Not Available");
						}
					}
				});
			}
			else if(caller=='U')
			{
				User.pnlCenter.add(pnl,BorderLayout.SOUTH);
				pnl.add(jsp,BorderLayout.CENTER);
				User.frmObjUser.setVisible(true);
			}
			con.close();
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null,"ERROR");
		}
	}

	int addNewBook()						//Add new Non-Technical Book
	{
		x = super.addNewBook("N");
		return x;
	}
}


/***************************************** LibProject Uses Library ******************************************/

class LibProject
{
	public static void main(String[] args) 
	{
		Connection con;
		try	 										//Database Connection
		{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String url = "jdbc:sqlserver://Melita\\SQLEXPRESS:1433;user=sa;password=Mel.7668;databaseName=Library";
			con = DriverManager.getConnection(url);
			JOptionPane.showMessageDialog(null,"Database Connected Successfully");

			String sql1 = "select* from Login";
			Statement stmt1 = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ResultSet rs1 = stmt1.executeQuery(sql1);

			con.close();
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null,"Database could not be connected"+e.getMessage());
		}

		Home lib = new Library();				//Object of class Library that implements Home Interface
		lib.createHome();
		lib.displayHome();
	}
}
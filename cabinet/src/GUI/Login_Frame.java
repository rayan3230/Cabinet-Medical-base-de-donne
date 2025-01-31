package GUI;

import Cabinet.Personnels.Accounts;
import Cabinet.Personnels.Doctor;
import Cabinet.Personnels.Secretary;
import Main_classes.MedicalOffice;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class Login_Frame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtFullname;
	private JPasswordField txtpassword;
	private Login_Doctor_menu Login_Doctor_menu;
	private Login_Sec_menu Login_Sec_menu;
	private MedicalOffice office;
	public Accounts accountManger;
	private Main_Menu mainFrame;
	private Doctor  logInDoctor;
	private Secretary logInSecretary;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public Login_Frame(Main_Menu MainFrame ,Accounts  AccountManager ,MedicalOffice office  ) {
		
		this.office = office; 
		this.accountManger = AccountManager;
		this.mainFrame = MainFrame;
		
		this.Login_Sec_menu = new Login_Sec_menu(office, mainFrame, accountManger, null);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1100, 700);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 242, 245));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		// Left Panel
		JPanel leftPanel = new JPanel();
		leftPanel.setBackground(new Color(33, 37, 41));
		leftPanel.setBounds(0, 0, 300, 700);
		leftPanel.setLayout(null);
		contentPane.add(leftPanel);

		// Logo
		JLabel lblLogo = new JLabel("MC");
		lblLogo.setForeground(Color.WHITE);
		lblLogo.setFont(new Font("Segoe UI", Font.BOLD, 42));
		lblLogo.setBounds(40, 40, 220, 60);
		leftPanel.add(lblLogo);

		JLabel lblLogoSub = new JLabel("Medical Cabinet");
		lblLogoSub.setForeground(new Color(158, 161, 178));
		lblLogoSub.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblLogoSub.setBounds(40, 100, 220, 25);
		leftPanel.add(lblLogoSub);

		// Main Panel
		JPanel mainPanel = new JPanel();
		mainPanel.setBackground(new Color(240, 242, 245));
		mainPanel.setBounds(300, 0, 800, 700);
		mainPanel.setLayout(null);
		contentPane.add(mainPanel);

		// Welcome Text
		JLabel lblWelcome = new JLabel("Login");
		lblWelcome.setForeground(new Color(33, 37, 41));
		lblWelcome.setFont(new Font("Segoe UI", Font.BOLD, 32));
		lblWelcome.setBounds(60, 40, 680, 50);
		mainPanel.add(lblWelcome);

		// Login Card
		JPanel loginCard = new JPanel();
		loginCard.setBackground(Color.WHITE);
		loginCard.setBounds(60, 120, 680, 500);
		loginCard.setLayout(null);
		mainPanel.add(loginCard);

		// Account Type Selection
		JLabel lblAccountType = new JLabel("Select Account Type");
		lblAccountType.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblAccountType.setBounds(40, 30, 600, 30);
		loginCard.add(lblAccountType);

		JRadioButton rdbDoc = new JRadioButton("Doctor");
		rdbDoc.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		rdbDoc.setBounds(40, 70, 150, 30);
		loginCard.add(rdbDoc);

		JRadioButton rdbSec = new JRadioButton("Secretary");
		rdbSec.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		rdbSec.setBounds(200, 70, 150, 30);
		loginCard.add(rdbSec);

		ButtonGroup group = new ButtonGroup();
		group.add(rdbDoc);
		group.add(rdbSec);

		// Username field
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblUsername.setBounds(40, 120, 600, 25);
		loginCard.add(lblUsername);

		txtFullname = new JTextField();
		txtFullname.setBounds(40, 150, 600, 40);
		txtFullname.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		loginCard.add(txtFullname);

		// Password field
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblPassword.setBounds(40, 210, 600, 25);
		loginCard.add(lblPassword);

		txtpassword = new JPasswordField();
		txtpassword.setBounds(40, 240, 600, 40);
		txtpassword.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		loginCard.add(txtpassword);

		// Login button
		JButton btnLogin = createStyledButton("Login", 40, 320, 600, 45);
		btnLogin.addActionListener(e -> {
			if(rdbDoc.isSelected()) {
				loginDoctor();
			} else if(rdbSec.isSelected()) {
				loginSecretary();
			} else {
				JOptionPane.showMessageDialog(this, "Please select an account type.");
			}
		});
		loginCard.add(btnLogin);

		// Back button
		JButton btnBack = createStyledButton("Back", 40, 380, 600, 45);
		btnBack.addActionListener(e -> {
			dispose();
			mainFrame.setVisible(true);
		});
		loginCard.add(btnBack);

		// Add hover effect to login card
		addPanelHoverEffect(loginCard);
	}

	private void loginDoctor() {
		String fullName = txtFullname.getText();
		String password = new String(txtpassword.getPassword());
		logInDoctor = accountManger.getDoctorByCredentials(fullName, password);
		
		if(logInDoctor != null) {
			JOptionPane.showMessageDialog(this, "Login successful!");
			dispose();
			Login_Doctor_menu doctorMenu = new Login_Doctor_menu(office, mainFrame, accountManger, logInDoctor);
			doctorMenu.setVisible(true);
		} else {
			JOptionPane.showMessageDialog(this, "Invalid credentials.");
		}
	}

	private void loginSecretary() {
		String fullName = txtFullname.getText();
		String password = new String(txtpassword.getPassword());
		Secretary logInSecretary = accountManger.getSecretaryByCredentials(fullName, password);
		
		if(logInSecretary != null) {
			JOptionPane.showMessageDialog(this, "Login successful!");
			dispose();
			Login_Sec_menu secMenu = new Login_Sec_menu(office, mainFrame, accountManger, logInSecretary);
			secMenu.setVisible(true);
		} else {
			JOptionPane.showMessageDialog(this, "Invalid credentials.");
		}
	}

	// Helper methods for styling (same as Main_Menu)
	private JButton createStyledButton(String text, int x, int y, int width, int height) {
		JButton button = new JButton(text);
		button.setBounds(x, y, width, height);
		button.setFont(new Font("Segoe UI", Font.BOLD, 14));
		button.setBackground(new Color(33, 37, 41));
		button.setForeground(Color.WHITE);
		button.setFocusPainted(false);
		button.setBorderPainted(false);
		button.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		button.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				button.setBackground(new Color(52, 58, 64));
			}
			public void mouseExited(java.awt.event.MouseEvent evt) {
				button.setBackground(new Color(33, 37, 41));
			}
		});
		
		return button;
	}

	private void addPanelHoverEffect(JPanel panel) {
		panel.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				panel.setBorder(BorderFactory.createLineBorder(new Color(33, 37, 41), 1));
			}
			public void mouseExited(java.awt.event.MouseEvent evt) {
				panel.setBorder(null);
			}
		});
	}
}

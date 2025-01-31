package GUI;

import Cabinet.Personnels.Accounts;
import Cabinet.Personnels.Doctor;
import Cabinet.Personnels.Secretary;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class Create_Account_Frame extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtFullname, txtPhonenum, txtEmail, txtSpc;
	private JPasswordField txtPassword;
	private Main_Menu mainFrame;
	public Accounts accountManager;

	public Create_Account_Frame(Main_Menu mainFrame, Accounts AccountManager) {
		this.accountManager = AccountManager;
		this.mainFrame = mainFrame;
		
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
		JLabel lblWelcome = new JLabel("Create Account");
		lblWelcome.setForeground(new Color(33, 37, 41));
		lblWelcome.setFont(new Font("Segoe UI", Font.BOLD, 32));
		lblWelcome.setBounds(60, 40, 680, 50);
		mainPanel.add(lblWelcome);

		// Registration Card
		JPanel registrationCard = new JPanel();
		registrationCard.setBackground(Color.WHITE);
		registrationCard.setBounds(60, 120, 680, 520);
		registrationCard.setLayout(null);
		mainPanel.add(registrationCard);

		// Account Type Selection
		JLabel lblAccountType = new JLabel("Account Type");
		lblAccountType.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblAccountType.setBounds(40, 30, 600, 30);
		registrationCard.add(lblAccountType);

		JRadioButton rdbdoctor = new JRadioButton("Doctor");
		rdbdoctor.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		rdbdoctor.setBounds(40, 70, 150, 30);
		registrationCard.add(rdbdoctor);

		JRadioButton rdbsecretery = new JRadioButton("Secretary");
		rdbsecretery.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		rdbsecretery.setBounds(200, 70, 150, 30);
		registrationCard.add(rdbsecretery);

		ButtonGroup group = new ButtonGroup();
		group.add(rdbdoctor);
		group.add(rdbsecretery);

		// Form Fields
		addFormField(registrationCard, "Full Name", txtFullname = new JTextField(), 120);
		addFormField(registrationCard, "Password", txtPassword = new JPasswordField(), 190);
		addFormField(registrationCard, "Email", txtEmail = new JTextField(), 260);
		addFormField(registrationCard, "Specialization", txtSpc = new JTextField(), 330);
		addFormField(registrationCard, "Phone Number", txtPhonenum = new JTextField(), 400);

		// Disable fields for secretary
		rdbsecretery.addActionListener(e -> {
			txtEmail.setEnabled(false);
			txtSpc.setEnabled(false);
			txtPhonenum.setEnabled(false);
		});

		rdbdoctor.addActionListener(e -> {
			txtEmail.setEnabled(true);
			txtSpc.setEnabled(true);
			txtPhonenum.setEnabled(true);
		});

		// Buttons
		JButton btnConfirm = createStyledButton("Create Account", 40, 470, 290, 45);
		btnConfirm.addActionListener(e -> {
			if (validateForm(rdbdoctor, rdbsecretery)) {
				createAccount(rdbdoctor);
			}
		});
		
		registrationCard.add(btnConfirm);

		JButton btnBack = createStyledButton("Back", 350, 470, 290, 45);
		btnBack.addActionListener(e -> {
			dispose();
			mainFrame.setVisible(true);
		});
		
		registrationCard.add(btnBack);

		// Add hover effect to registration card
		addPanelHoverEffect(registrationCard);
	}

	private void addFormField(JPanel panel, String labelText, JComponent field, int yPosition) {
		JLabel label = new JLabel(labelText);
		label.setFont(new Font("Segoe UI", Font.BOLD, 14));
		label.setBounds(40, yPosition, 600, 25);
		panel.add(label);

		field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		field.setBounds(40, yPosition + 25, 600, 35);
		panel.add(field);
	}

	private boolean validateForm(JRadioButton rdbDoctor, JRadioButton rdbSecretary) {
		if (txtFullname.getText().isEmpty() || txtPassword.getText().isEmpty() || 
			(!rdbDoctor.isSelected() && !rdbSecretary.isSelected())) {
			JOptionPane.showMessageDialog(this, 
				"Please fill in all required fields and select an account type.");
			return false;
		}
		return true;
	}

	private void createAccount(JRadioButton rdbDoctor) {
		if (rdbDoctor.isSelected()) {
			Doctor doc = new Doctor(
				txtFullname.getText(),
				txtSpc.getText(),
				txtEmail.getText(),
				txtPhonenum.getText(),
				txtPassword.getText()
			);
			accountManager.AddDocAccount(doc);
		} else {
			Secretary sec = new Secretary(
				txtFullname.getText(),
				txtPassword.getText()
			);
			accountManager.AddSecAccount(sec);
		}
		JOptionPane.showMessageDialog(this, "Account created successfully!");
		dispose();
		mainFrame.setVisible(true);
	}

	private JButton createStyledButton(String text, int x, int y, int width, int height) {
		JButton button = new JButton(text);
		button.setBounds(x, y, width, height);
		button.setFont(new Font("Segoe UI", Font.BOLD, 14));
		button.setBackground(new Color(25, 118, 210));
		button.setForeground(Color.WHITE);
		button.setFocusPainted(false);
		button.setBorderPainted(false);
		button.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		button.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				button.setBackground(new Color(21, 101, 192));
			}
			public void mouseExited(java.awt.event.MouseEvent evt) {
				button.setBackground(new Color(25, 118, 210));
			}
		});
		
		return button;
	}

	private void addPanelHoverEffect(JPanel panel) {
		panel.setBorder(BorderFactory.createLineBorder(new Color(21, 101, 192), 2));
		panel.setBackground(new Color(240, 242, 245));
	}
}

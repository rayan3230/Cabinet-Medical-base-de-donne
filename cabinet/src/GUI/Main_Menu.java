package GUI;

import java.awt.EventQueue;

import Cabinet.Management.VisitDates;
import Cabinet.Personnels.Accounts;
import Cabinet.Personnels.Client;
import Cabinet.Personnels.Doctor;
import Cabinet.Personnels.Secretary;
import Main_classes.MedicalOffice;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Cursor;
import javax.swing.BorderFactory;

public class Main_Menu {

	private JFrame frame1;
	private Accounts sharedAccounts = new Accounts();
	private MedicalOffice sharedOffice = new MedicalOffice();
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main_Menu window = new Main_Menu();
					window.frame1.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main_Menu() {
		initialize();
	}

	
	private void initialize() {
		frame1 = new JFrame();
		frame1.getContentPane().setBackground(new Color(240, 242, 245));
		frame1.getContentPane().setLayout(null);
		frame1.setSize(1100, 700);
        frame1.setLocationRelativeTo(null);
        
        // Left Panel - Navigation/Logo
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(new Color(33, 37, 41));
        leftPanel.setBounds(0, 0, 300, 700);
        leftPanel.setLayout(null);
        frame1.getContentPane().add(leftPanel);

        // Logo area
        JLabel lblLogo = new JLabel("MC");
        lblLogo.setForeground(new Color(255, 255, 255));
        lblLogo.setFont(new Font("Segoe UI", Font.BOLD, 42));
        lblLogo.setBounds(40, 40, 220, 60);
        leftPanel.add(lblLogo);

        JLabel lblLogoSub = new JLabel("Medical Cabinet");
        lblLogoSub.setForeground(new Color(158, 161, 178));
        lblLogoSub.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblLogoSub.setBounds(40, 100, 220, 25);
        leftPanel.add(lblLogoSub);

        // Main content area
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(240, 242, 245));
        mainPanel.setBounds(300, 0, 800, 700);
        mainPanel.setLayout(null);
        frame1.getContentPane().add(mainPanel);

        // Welcome text
        JLabel lblWelcome = new JLabel("Welcome Back");
        lblWelcome.setForeground(new Color(33, 37, 41));
        lblWelcome.setFont(new Font("Segoe UI", Font.BOLD, 32));
        lblWelcome.setBounds(60, 40, 680, 50);
        mainPanel.add(lblWelcome);

        JLabel lblSubWelcome = new JLabel("Please login to your account or create a new one");
        lblSubWelcome.setForeground(new Color(108, 117, 125));
        lblSubWelcome.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblSubWelcome.setBounds(60, 90, 680, 30);
        mainPanel.add(lblSubWelcome);

        // Action Cards Panel
        JPanel cardsPanel = new JPanel();
        cardsPanel.setBackground(new Color(240, 242, 245));
        cardsPanel.setBounds(40, 160, 720, 480);
        cardsPanel.setLayout(null);
        mainPanel.add(cardsPanel);

        // Login Card
        addActionCard(cardsPanel, "Login", 
            "Access your account", 
            "→ Login Now", 
            20, e -> {
                frame1.dispose();
                Login_Frame login_frame = new Login_Frame(Main_Menu.this, sharedAccounts, sharedOffice);
                login_frame.setVisible(true);
            });

        // Create Account Card
        addActionCard(cardsPanel, "Create Account", 
            "Register as a new user", 
            "→ Register", 
            180, e -> {
                frame1.dispose();
                Create_Account_Frame createAccountFrame = new Create_Account_Frame(Main_Menu.this, sharedAccounts);
                createAccountFrame.setVisible(true);
            });

        // Exit Card
        addActionCard(cardsPanel, "Exit", 
            "Close the application", 
            "→ Exit Now", 
            340, e -> System.exit(0));

        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void addActionCard(JPanel parent, String title, String description, 
                             String buttonText, int yPos, ActionListener action) {
        JPanel card = new JPanel();
        card.setBackground(Color.WHITE);
        card.setBounds(0, yPos, 720, 140);
        card.setLayout(null);
        parent.add(card);

        // Card Title
        JLabel lblTitle = new JLabel(title);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitle.setBounds(30, 20, 660, 30);
        card.add(lblTitle);

        // Card Description
        JLabel lblDesc = new JLabel(description);
        lblDesc.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblDesc.setForeground(new Color(108, 117, 125));
        lblDesc.setBounds(30, 50, 660, 25);
        card.add(lblDesc);

        // Card Button
        JButton btn = new JButton(buttonText);
        btn.setBounds(30, 85, 660, 40);
        styleButton(btn);
        btn.addActionListener(action);
        card.add(btn);

        // Add card hover effect
        addPanelHoverEffect(card);
    }

    private void styleButton(JButton button) {
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

    public void setVisible(boolean visible) {
        frame1.setVisible(visible);
    }
}



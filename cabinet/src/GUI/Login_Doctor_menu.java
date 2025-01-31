package GUI;

import Cabinet.Management.VisitDates;
import Cabinet.Personnels.Accounts;
import Cabinet.Personnels.Doctor;
import Main_classes.MedicalOffice;
import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Login_Doctor_menu extends JFrame {
    private static final long serialVersionUID = 1L;
    private Accounts accountManger;
    private Main_Menu mainFrame;
    private MedicalOffice office;
    private Doctor logDoctor;

    public Login_Doctor_menu(MedicalOffice office, Main_Menu MainFrame, Accounts AccountManager, Doctor logDoctor) {
        this.accountManger = AccountManager;
        this.office = office;
        this.mainFrame = MainFrame;
        this.logDoctor = logDoctor;

        setTitle("Doctor Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1100, 700);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(240, 242, 245));
        setLayout(null);

        // Left Panel
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(new Color(33, 37, 41));
        leftPanel.setBounds(0, 0, 300, 700);
        leftPanel.setLayout(null);
        add(leftPanel);

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
        add(mainPanel);

        // Doctor Info Card
        JPanel infoCard = new JPanel();
        infoCard.setBackground(Color.WHITE);
        infoCard.setBounds(60, 40, 680, 140);
        infoCard.setLayout(null);
        mainPanel.add(infoCard);

        JLabel lblWelcome = new JLabel("Welcome, Dr. " + logDoctor.FullName);
        lblWelcome.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblWelcome.setForeground(new Color(33, 37, 41));
        lblWelcome.setBounds(30, 20, 620, 35);
        infoCard.add(lblWelcome);

        JLabel lblSpeciality = new JLabel("Speciality: " + logDoctor.Profession);
        lblSpeciality.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblSpeciality.setBounds(30, 60, 620, 25);
        infoCard.add(lblSpeciality);

        JLabel lblEmail = new JLabel("Email: " + logDoctor.Mail);
        lblEmail.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblEmail.setBounds(30, 90, 620, 25);
        infoCard.add(lblEmail);

        addPanelHoverEffect(infoCard);

        // Actions Panel
        JPanel actionsPanel = new JPanel();
        actionsPanel.setBackground(Color.WHITE);
        actionsPanel.setBounds(60, 200, 680, 440);
        actionsPanel.setLayout(null);
        mainPanel.add(actionsPanel);

        // Action buttons
        JLabel lblActions = new JLabel("Quick Actions");
        lblActions.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblActions.setBounds(30, 20, 620, 30);
        actionsPanel.add(lblActions);

        addActionButton(actionsPanel, "Update Profile", "Update your personal information", 80, e -> {
            JOptionPane.showMessageDialog(this, "Update profile functionality coming soon!");
        });

        addActionButton(actionsPanel, "View Patients", "See list of all patients", 170, e -> displayPatients());

        addActionButton(actionsPanel, "View Schedule", "Check your appointments", 260, e -> displaySchedule());

        addActionButton(actionsPanel, "Logout", "Return to main menu", 350, e -> {
            dispose();
            mainFrame.setVisible(true);
        });

        addPanelHoverEffect(actionsPanel);
    }

    private void addActionButton(JPanel panel, String title, String description, int yPos, ActionListener action) {
        JPanel buttonCard = new JPanel();
        buttonCard.setBackground(new Color(240, 242, 245));
        buttonCard.setBounds(30, yPos, 620, 70);
        buttonCard.setLayout(null);
        panel.add(buttonCard);

        JLabel lblTitle = new JLabel(title);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblTitle.setBounds(20, 10, 580, 25);
        buttonCard.add(lblTitle);

        JLabel lblDesc = new JLabel(description);
        lblDesc.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblDesc.setForeground(new Color(108, 117, 125));
        lblDesc.setBounds(20, 35, 580, 25);
        buttonCard.add(lblDesc);

        buttonCard.setCursor(new Cursor(Cursor.HAND_CURSOR));
        buttonCard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                action.actionPerformed(null);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                buttonCard.setBackground(new Color(233, 236, 239));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                buttonCard.setBackground(new Color(240, 242, 245));
            }
        });
    }

    private void displayPatients() {
        StringBuilder patientsList = new StringBuilder();
        if (office.Clients.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No patients registered.");
            return;
        }

        for (int i = 0; i < office.Clients.size(); i++) {
            patientsList.append(i + 1).append(". ")
                    .append(office.Clients.get(i).FullName)
                    .append(" (Phone: ").append(office.Clients.get(i).PhoneNum).append(")\n");
        }

        JTextArea textArea = new JTextArea(patientsList.toString());
        textArea.setEditable(false);
        textArea.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(400, 300));

        JOptionPane.showMessageDialog(this, scrollPane, 
            "Patient List", JOptionPane.INFORMATION_MESSAGE);
    }

    private void displaySchedule() {
        StringBuilder schedule = new StringBuilder();
        boolean hasAppointments = false;

        for (VisitDates appointment : office.Appointments) {
            if (appointment.doctor.equals(logDoctor)) {
                hasAppointments = true;
                schedule.append("Date: ").append(appointment.Date)
                        .append("\nPatient: ").append(appointment.patient.FullName)
                        .append("\nPhone: ").append(appointment.patient.PhoneNum)
                        .append("\n-----------------\n");
            }
        }

        if (!hasAppointments) {
            JOptionPane.showMessageDialog(this, 
                "No appointments scheduled.", 
                "Schedule", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        JTextArea textArea = new JTextArea(schedule.toString());
        textArea.setEditable(false);
        textArea.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(400, 300));

        JOptionPane.showMessageDialog(this, scrollPane, 
            "Your Schedule", JOptionPane.INFORMATION_MESSAGE);
    }

    private void addPanelHoverEffect(JPanel panel) {
        panel.setBorder(BorderFactory.createLineBorder(new Color(21, 101, 192), 2));
    }
}

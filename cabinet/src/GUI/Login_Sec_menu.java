package GUI;

import Cabinet.Management.VisitDates;
import Cabinet.Personnels.Accounts;
import Cabinet.Personnels.Client;
import Cabinet.Personnels.Doctor;
import Cabinet.Personnels.Secretary;
import Main_classes.MedicalOffice;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login_Sec_menu extends JFrame {
    private static final long serialVersionUID = 1L;
    private MedicalOffice office;
    private Accounts accountManger;
    private Main_Menu mainFrame;
    private Secretary logSecretary;

    public Login_Sec_menu(MedicalOffice office, Main_Menu mainFrame, Accounts accountManager, Secretary logSecretary) {
        this.office = office;
        this.mainFrame = mainFrame;
        this.accountManger = accountManager;
        this.logSecretary = logSecretary;

        setTitle("Secretary Dashboard");
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

        // Secretary Info Card
        JPanel infoCard = new JPanel();
        infoCard.setBackground(Color.WHITE);
        infoCard.setBounds(60, 40, 680, 140);
        infoCard.setLayout(null);
        mainPanel.add(infoCard);

        JLabel lblWelcome = new JLabel("Welcome, " + (logSecretary != null ? logSecretary.FullName : "Secretary"));
        lblWelcome.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblWelcome.setForeground(new Color(33, 37, 41));
        lblWelcome.setBounds(30, 20, 620, 35);
        infoCard.add(lblWelcome);

        JLabel lblRole = new JLabel("Medical Office Management System");
        lblRole.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblRole.setBounds(30, 60, 620, 25);
        infoCard.add(lblRole);

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

        addActionButton(actionsPanel, "Add Patient", 
            "Register a new patient in the system", 80, 
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    addPatient();
                }
            });

        addActionButton(actionsPanel, "Add Appointment", 
            "Schedule a new appointment", 170, 
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    addAppointment();
                }
            });

        addActionButton(actionsPanel, "View Patients", 
            "See list of all registered patients", 260, 
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    displayPatients();
                }
            });

        addActionButton(actionsPanel, "View Appointments", 
            "Check all scheduled appointments", 350, 
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    displayAppointments();
                }
            });

        addActionButton(actionsPanel, "Logout", 
            "Return to main menu", 440, 
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    dispose();
                    mainFrame.setVisible(true);
                }
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

    private void addPatient() {
        String name = JOptionPane.showInputDialog(this, "Enter Patient Name:");
        String password = JOptionPane.showInputDialog(this, "Enter Password:");
        
        if (name != null && password != null && !name.trim().isEmpty() && !password.trim().isEmpty()) {
            Client patient = new Client(name, password);
            office.addClient(patient);
            JOptionPane.showMessageDialog(this, "Patient added successfully.");
        } else {
            JOptionPane.showMessageDialog(this, "Invalid input. Please try again.");
        }
    }

    private void addAppointment() {
        if (office.Clients.isEmpty() || accountManger.Doctors.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Need both patients and doctors to create an appointment.");
            return;
        }

        String[] patientNames = office.Clients.stream()
                .map(c -> c.FullName)
                .toArray(String[]::new);

        String[] doctorNames = accountManger.Doctors.stream()
                .map(d -> d.FullName)
                .toArray(String[]::new);

        String selectedPatient = (String) JOptionPane.showInputDialog(
                this, "Select a patient:", "Patient Selection",
                JOptionPane.QUESTION_MESSAGE, null, patientNames, patientNames[0]);

        if (selectedPatient == null) return;

        String selectedDoctor = (String) JOptionPane.showInputDialog(
                this, "Select a doctor:", "Doctor Selection",
                JOptionPane.QUESTION_MESSAGE, null, doctorNames, doctorNames[0]);

        if (selectedDoctor == null) return;

        String appointmentDate = JOptionPane.showInputDialog(
                this, "Enter appointment date (YYYY-MM-DD):");

        if (appointmentDate != null && !appointmentDate.trim().isEmpty()) {
            Client patient = office.Clients.stream()
                    .filter(c -> c.FullName.equals(selectedPatient))
                    .findFirst()
                    .orElse(null);

            Doctor doctor = accountManger.Doctors.stream()
                    .filter(d -> d.FullName.equals(selectedDoctor))
                    .findFirst()
                    .orElse(null);

            if (patient != null && doctor != null) {
                VisitDates appointment = new VisitDates(appointmentDate, patient, doctor);
                office.addAppointment(appointment);
                JOptionPane.showMessageDialog(this, String.format(
                    "Appointment scheduled successfully!\nPatient: %s\nDoctor: %s\nDate: %s",
                    patient.FullName, doctor.FullName, appointmentDate));
            }
        }
    }

    private void displayPatients() {
        if (office.Clients.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No patients registered.");
            return;
        }

        StringBuilder patientsList = new StringBuilder();
        for (int i = 0; i < office.Clients.size(); i++) {
            Client client = office.Clients.get(i);
            patientsList.append(String.format("%d. %s (Phone: %s)\n",
                i + 1, client.FullName, client.PhoneNum));
        }

        showScrollableDialog(patientsList.toString(), "Registered Patients");
    }

    private void displayAppointments() {
        if (office.Appointments.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No appointments scheduled.");
            return;
        }

        StringBuilder appointmentsList = new StringBuilder();
        for (VisitDates appointment : office.Appointments) {
            appointmentsList.append(String.format(
                "Date: %s\nPatient: %s\nDoctor: %s\n-----------------\n",
                appointment.Date, appointment.patient.FullName, appointment.doctor.FullName));
        }

        showScrollableDialog(appointmentsList.toString(), "Scheduled Appointments");
    }

    private void showScrollableDialog(String content, String title) {
        JTextArea textArea = new JTextArea(content);
        textArea.setEditable(false);
        textArea.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(400, 300));

        JOptionPane.showMessageDialog(this, scrollPane, title, JOptionPane.INFORMATION_MESSAGE);
    }

    private void addPanelHoverEffect(JPanel panel) {
        panel.setBorder(BorderFactory.createLineBorder(new Color(21, 101, 192), 2));
    }
}
package Main_classes;

import Cabinet.Management.*;
import Cabinet.Personnels.*;
import database.*;
import java.util.List;

public class MedicalOffice {
    private PatientDAO patientDAO;
    private MedicineDAO medicineDAO;
    private DoctorDAO doctorDAO;
    private AppointmentDAO appointmentDAO;
    private BillDAO billDAO;
    private PatientSheetDAO patientSheetDAO;

    public List<Client> Clients;
    public List<Medicines> medicines;
    public List<Doctor> doctors;
    public List<VisitDates> Appointments;
    public List<Bill> bills;
    public List<PatientSheet> PatientSheets;

    public MedicalOffice() {
        initializeDAOs();
        loadDataFromDatabase();
    }

    private void initializeDAOs() {
        patientDAO = new PatientDAO();
        medicineDAO = new MedicineDAO();
        doctorDAO = new DoctorDAO();
        appointmentDAO = new AppointmentDAO();
        billDAO = new BillDAO();
        patientSheetDAO = new PatientSheetDAO();
    }

    private void loadDataFromDatabase() {
        Clients = patientDAO.getAllPatients();
        medicines = medicineDAO.getAllMedicines();
        doctors = doctorDAO.getAllDoctors();
        Appointments = appointmentDAO.getAllAppointments();
        bills = billDAO.getAllBills();
        PatientSheets = patientSheetDAO.getAllPatientSheets();
    }

    public void addClient(Client client) {
        patientDAO.addPatient(client);
        Clients.add(client);
    }

    public void addDoctor(Doctor doctor) {
        doctorDAO.addDoctor(doctor);
        doctors.add(doctor);
    }

    public void addAppointment(VisitDates appointment) {
        appointmentDAO.addAppointment(appointment);
        Appointments.add(appointment);
    }

    public void addMadicines(Medicines Med) {
        medicineDAO.addMedicine(Med);
        medicines.add(Med);
    }

    public void addBill(Bill bill) {
        billDAO.addBill(bill);
        bills.add(bill);
    }

    public void addPatientSheet(PatientSheet sheet) {
        patientSheetDAO.addPatientSheet(sheet);
        PatientSheets.add(sheet);
    }
}

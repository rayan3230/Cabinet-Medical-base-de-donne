package Cabinet.Management;

import Cabinet.Personnels.Doctor;
import java.util.ArrayList;
import java.util.List;

public class Bill {
    public String PatientName;
    public String OfficeName = "PooProject_Office";
    public String DoctorName;
    public double Price;
    public int AppId;
    public List<Medicines> prescribedMedicines;
    public double hoursSpent;

    public Bill(String PatientName, int AppId, double hoursSpent) {
        this.PatientName = PatientName;
        this.AppId = AppId;
        this.hoursSpent = hoursSpent;
        this.prescribedMedicines = new ArrayList<>();
        this.Price = 0.0;
    }

    public void addMedicine(Medicines medicine, Doctor doctor) {
        prescribedMedicines.add(medicine);
        calculateTotalBill(doctor);
    }

    public void calculateTotalBill(Doctor doctor) {
        // Calculate doctor's fee based on hours spent
        double doctorFee = doctor.hourlyRate * hoursSpent;
        
        // Calculate medicines total
        double medicinesTotal = prescribedMedicines.stream()
            .mapToDouble(med -> med.Price)
            .sum();
        
        this.Price = doctorFee + medicinesTotal;
    }

    @Override
    public String toString() {
        StringBuilder bill = new StringBuilder();
        bill.append("Medical Bill\n");
        bill.append("-------------------\n");
        bill.append("Patient: ").append(PatientName).append("\n");
        bill.append("Doctor: ").append(DoctorName).append("\n");
        bill.append("Hours Spent: ").append(hoursSpent).append("\n");
        bill.append("\nPrescribed Medicines:\n");
        
        for (Medicines med : prescribedMedicines) {
            bill.append(med.Name).append(" - $").append(med.Price).append("\n");
        }
        
        bill.append("\nTotal Amount: $").append(String.format("%.2f", Price));
        return bill.toString();
    }
}

package Cabinet.Personnels;

import java.util.Scanner;

public class Doctor {
    public String FullName;
    public String Profession;
    public String Mail;
    public String PhoneNum;
    public String PassWord;
    public double hourlyRate;

    public Doctor(String FullName, String PassWord, String Mail, String Profession, String PhoneNum) {
        this.FullName = FullName;
        this.PassWord = PassWord;
        this.Mail = Mail;
        this.Profession = Profession;
        this.PhoneNum = PhoneNum;
        this.hourlyRate = 100.0;
    }

    public void setHourlyRate(double rate) {
        this.hourlyRate = rate;
    }

    public boolean[] Schedule() {
        boolean[] schedule = new boolean[7];
        Scanner scanner = new Scanner(System.in);

        System.out.println("Doctor, please indicate your availability (yes/no) for each day (1=Monday, 7=Sunday):");
        for (int i = 0; i < 7; i++) {
            System.out.print((i + 1) + ")- ");
            String response = scanner.next().toLowerCase();
            if ("yes".equals(response)) {
                schedule[i] = true;
            } else if ("no".equals(response)) {
                schedule[i] = false;
            } else {
                System.out.println("Please respond with 'yes' or 'no'.");
                i--; // Repeat the input
            }
        }
        return schedule;
    }
}

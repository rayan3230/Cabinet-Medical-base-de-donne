package Cabinet.Personnels;

import database.AccountDAO;

public class Accounts {
    private AccountDAO accountDAO;

    public Accounts() {
        accountDAO = new AccountDAO();
    }

    public void addDoctor(Doctor doctor) {
        accountDAO.addDoctor(doctor);
    }

    public void addSecretary(Secretary secretary) {
        accountDAO.addSecretary(secretary);
    }

    public Doctor getDoctorAccount(String username, String password) {
        return accountDAO.getDoctorByCredentials(username, password);
    }

    public Secretary getSecretaryAccount(String username, String password) {
        return accountDAO.getSecretaryByCredentials(username, password);
    }

    public boolean isAdmin(String username, String password) {
        return username.equals("admin") && password.equals("admin");
    }
}

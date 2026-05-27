
import java.time.LocalDate;

public abstract class Account {
    private String accountHolderName;
    private String username;
    private String password;
    private int accountNumber;
    private double accountBalance;
    private LocalDate currentDate;
    private double withdrawal;
    private double deposit;

    public Account() {
    }

    public Account(String accountHolderName, String username, String password, int accountNumber, LocalDate date, double accountBalance, double withdrawal, double deposit) {
        setAccountHolderName(accountHolderName);
        setUsername(username);
        setPassword(password);
        setAccountNumber(accountNumber);
        setAccountBalance(accountBalance);
        setCurrentDate(date);
        setWithdrawal(withdrawal);
        setAccountBalance(accountBalance);
        setDeposit(deposit);

    }

    public Account(String accountHolderName, String username, String password, int accountNumber) {
        setAccountHolderName(accountHolderName);
        setUsername(username);
        setPassword(password);
        setAccountNumber(accountNumber);
        setAccountBalance(0.0);
        setCurrentDate(LocalDate.now());
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {this.password = password;}

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public LocalDate getCurrentDate() {
        return currentDate;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public void setCurrentDate(LocalDate currentDate) {
        this.currentDate = currentDate;
    }

    public void setWithdrawal(double withdrawal) {
        this.withdrawal = withdrawal;
    }

    public double getWithdrawal() {
        return withdrawal;
    }

    public abstract String withdrawal(double amount);

    public double getDeposit() {
        return deposit;
    }

    public void setDeposit(double deposit) {
        this.deposit = deposit;
    }

    public abstract void deposit(double amount);

    public abstract String checkBalance();

    public abstract double calculateBalance();

    public String toString() {
        return "Account Holder Name: " + accountHolderName +
                "\nUsername: " + username +
                "\nPassword: " + password +
                "\nAccount Number: " + accountNumber +
                "\nDate Created: " + currentDate +
                "\nType: ";
    }
}

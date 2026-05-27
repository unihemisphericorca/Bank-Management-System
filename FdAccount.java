
import java.time.LocalDate;
import java.util.Scanner;

public class FdAccount extends Account {
    private double fdAmount;
    private double interestRate;
    private int month;

    public FdAccount() {
    }

    public FdAccount(String accountHolderName, String username, String password, int accountNumber, double fdAmount, int month) {
        super(accountHolderName, username, password, accountNumber);
        setFdAmount(fdAmount);
        setMonth(month);
        //set the interest rate
        if (month == 3) {
            setInterestRate(0.023);
        } else if (month == 6) {
            setInterestRate(0.024);
        } else if (month == 9) {
            setInterestRate(0.025);
        } else {
            setInterestRate(0.0255);
        }
    }

    public FdAccount(String accountHolderName, String username, String password, int accountNumber, LocalDate date, double accountBalance, double withdrawal,double deposit,double fdAmount, int month) {
        super(accountHolderName, username, password, accountNumber, date,accountBalance,withdrawal,deposit);
        setFdAmount(fdAmount);
        setMonth(month);
        //set the interest rate
        if (month == 3) {
            setInterestRate(0.023);
        } else if (month == 6) {
            setInterestRate(0.024);
        } else if (month == 9) {
            setInterestRate(0.025);
        } else {
            setInterestRate(0.0255);
        }
        super.setAccountBalance(calculateBalance()-withdrawal+deposit);
    }

    public double getFdAmount() {
        return fdAmount;
    }

    public void setFdAmount(double fdAmount) {
        this.fdAmount = fdAmount;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }
    
    @Override
    public String withdrawal(double amount) {
        Scanner input = new Scanner(System.in);
        if ((fdAmount + calculateBalance()) < amount) {
            //withdrawal cancelled because balance insufficient
            return "Can't withdrawal amount. Balance insufficient\nReturning to main menu....\n";
        }
        if (calculateBalance() < amount) {
            //ask if user want to cancel fixed deposit when withdrawal touch fixed deposit amount
            System.out.print("Total Amount is less than withdrawal amount\nProceeding will lead to fixed deposit interest rate cancel.\nDo you still want to proceed(yes/no): ");
            String option = input.nextLine();

            if (option.equalsIgnoreCase("yes")) {
                //cancel fixed deposit interest
                if ((fdAmount + calculateBalance()) >= amount) {
                    setFdAmount((fdAmount + super.getAccountBalance()) - amount);
                    super.setAccountBalance(0.0);
                    return "Successful withdrawal. Cancelling FD interest\nReturning to main menu....\n";

                } else {
                    return "Can't withdrawal amount. Total Amount is less than withdrawal amount\n";
                }
            } else if (option.equalsIgnoreCase("no")) {
                //when user does not want to cancel fixed deposit rate
                return "Returning to main menu.....\n";
            } else {
                return "Input error";
            }
        } else {
            //withdraw amount
            super.setWithdrawal(super.getWithdrawal()+amount);
            super.setAccountBalance(calculateBalance() - amount);
            return "Successfully withdrawal. Returning to main menu...\n";
        }
    }

    @Override
    public void deposit(double amount) {
        //deposit amount
        super.setDeposit(super.getDeposit()+amount);
        setFdAmount(fdAmount + amount);
    }

    @Override
    public String checkBalance() {
        //check balance
        return "\n==Account from " + getAccountHolderName() + "==\nType: Fixed Deposit" + "\nSince: " + getCurrentDate() + "\nMatured Amount: " + super.getAccountBalance() + "\nFD Amount: " + fdAmount + "\n";
    }

    @Override
    public double calculateBalance() {
        //calculate balance
        LocalDate dateCalculate = LocalDate.now();
        int period = (dateCalculate.getMonthValue() - super.getCurrentDate().getMonthValue());
        if (period % month == 0) {
            if (dateCalculate.compareTo(super.getCurrentDate()) > 0) {
                int period2 = period / month;
                super.setAccountBalance(fdAmount * period2 * interestRate);
            } else {
                super.setAccountBalance(fdAmount * period * interestRate);
            }
        } else {
            int period2 = period / month;
            super.setAccountBalance(fdAmount * period2 * interestRate);
        }
        return super.getAccountBalance();
    }

    @Override
    public String toString() {
        return super.toString() + "Fixed Deposit Account" +
                "\nFD Amount: " + fdAmount +
                "\nMature Amount: " + calculateBalance() +
                "\nInterest Rate: " + getInterestRate() +
                "\nMonth: " + getMonth();
    }

}

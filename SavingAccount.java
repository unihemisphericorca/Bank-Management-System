
import java.time.LocalDate;

public class SavingAccount extends Account {
    private double basicRate;
    private boolean card;
    private int cardNumber;

    public SavingAccount() {
    }

    public SavingAccount(String accountHolderName, String username, String password, int accountNumber, LocalDate date, double accountBalance, double withdrawal,double deposit,boolean card, int cardNumber) {
        super(accountHolderName, username, password, accountNumber, date, accountBalance,withdrawal,deposit);
        setCard(card);
        setBasicRate(0.05);
        setCardNumber(cardNumber);
        super.setAccountBalance(deposit-withdrawal+calculateBalance());

    }

    public SavingAccount(String accountHolderName, String username, String password, int accountNumber, boolean card) {
        super(accountHolderName, username, password, accountNumber);
        setCard(card);
        setBasicRate(0.05);
        setAccountBalance(0.0);
    }

    public SavingAccount(String accountHolderName, String username, String password, int accountNumber, boolean card, int cardNumber) {
        super(accountHolderName, username, password, accountNumber);
        setCard(card);
        setCardNumber(cardNumber);
        setBasicRate(0.05);
        setAccountBalance(0.0);
    }

    public void setBasicRate(double basicRate) {
        this.basicRate = basicRate;
    }

    public double getBasicRate() {
        return basicRate;
    }

    public void setCard(boolean card) {
        this.card = card;
    }

    public boolean isCard() {
        return card;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    @Override
    public String withdrawal(double amount) {
        if (super.getAccountBalance() < amount) {
            //prevent user from withdrawing over balance amount
            return "Withdrawal Unsuccessfully. Balance insufficient\nReturning to main menu\n";
        } else {
            //withdraw money
            super.setWithdrawal(super.getWithdrawal()+amount);
            super.setAccountBalance(super.getAccountBalance() - amount);
            return "Withdrawal successfully. Returning to main menu...\n";
        }

    }

    @Override
    public void deposit(double amount) {
        //deposit
        amount+=super.getDeposit();
        super.setDeposit(amount);
        System.out.println(super.getDeposit());
        super.setAccountBalance(super.getAccountBalance() + amount);
    }

    @Override
    public String checkBalance() {
        //check balance
        return "\n==Account from " + getAccountHolderName() + "==\nType: Savings" + "\nSince: " + getCurrentDate() + "\nBalance: " + super.getAccountBalance()+ "\n\n";
    }

    @Override
    public String toString() {
        return super.toString() + "Savings Account" +
                "\nAccount Balance: " + super.getAccountBalance() +
                "\nBasic Rate: " + basicRate +
                "\nCard availability: " + card +
                "\nCard Number: " + cardNumber;
    }

    public double calculateBalance() {
        //calculate balance
        LocalDate calculateDate = LocalDate.now();
        int period = (calculateDate.getYear() - super.getCurrentDate().getYear());
        if (period > 0) {
            int period2 = (period * 12) + (calculateDate.getMonthValue() - super.getCurrentDate().getMonthValue());
            if (period2 % 12 == 0) {
                if (calculateDate.compareTo(super.getCurrentDate()) > 0) {
                    period = period2 / 12;
                    return(super.getAccountBalance() * period * basicRate);
                } else {
                    period = (period2 - 12) / 12;
                    return ( super.getAccountBalance() * period * basicRate);
                }
            } else {
                return( super.getAccountBalance() * period * basicRate);
            }
        }else {
           return 0.0;

        }
    }

}

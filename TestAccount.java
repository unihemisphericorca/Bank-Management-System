import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.File;


public class TestAccount {
    static int error = 1;
    static Scanner input = new Scanner(System.in);
    //creating 2 txt file to store fd account and saving account
    static File fileFDAccount = new File("FD Account.txt");
    static File fileSavingAccount = new File("Saving Account.txt");

    public static void main(String[] args) {
        ArrayList<Account> arrAccount = new ArrayList<>();
        int option = 0;
        //copy account information from txt file
        copyFileToArraylist(arrAccount);
        do {
            try {
                //Display menu
                System.out.println("""
                        BANK MANAGEMENT SYSTEM
                        --------------------
                        1.Add Account
                        2.Deposit
                        3.Withdrawal
                        4.Check Balance
                        5.Edit Account
                        6.Exit""");
                System.out.print("User Option>>> ");
                option = input.nextInt();
                //read enter that is being press by user
                input.nextLine();
                switch (option) {
                    //Add Account
                    case 1 -> AddAccount(arrAccount);

                    //Deposit
                    case 2 -> deposit(arrAccount);

                    //Withdrawal
                    case 3 -> withdrawal(arrAccount);

                    //Check balance
                    case 4 -> checkBalance(arrAccount);

                    //Edit Account information
                    case 5 -> editAccount(arrAccount);

                    //Exit program
                    case 6 -> {
                        //copy account information in array list to txt file
                        copyArraylistToFile(arrAccount);
                        System.out.println("Exiting program.......");
                    }

                    //when user enter other number other than (1-6) give error message
                    default -> System.out.println("Wrong Input. Re-select option\n");
                }

                //when user enter char or String give error message
            } catch (InputMismatchException ex) {
                System.out.println("Wrong Input. Re-select option\n");
                input.nextLine();
            }

        } while (option != 6);  //Will keep looping until enter '6'
    }

    public static void AddAccount(ArrayList<Account> arrAccount) {
        do {
            try {
                //display menu
                System.out.println("""
                        
                        TYPES OF ACCOUNT
                        -------------------
                        1.FD Account
                        2.Saving Account
                        3.Exit""");
                System.out.print("User Option>>> ");
                int option = input.nextInt();
                input.nextLine();
                switch (option) {
                    case 1 -> {
                        //ask for account holder name
                        System.out.print("Enter Account Holder Name: ");
                        String accountHolderName = input.nextLine();

                        //ask for username
                        System.out.print("Enter Username: ");
                        String username = input.nextLine();

                        //prevent same username exist
                        if (accountCheckingAccountUsername(username, arrAccount) == 1) {
                            System.out.println("Account username has been used. Re-enter account username\n");
                            error = 1;
                        } else {
                            //ask for password
                            System.out.print("Enter Password: ");
                            String password = input.nextLine();

                            //ask for account number
                            System.out.print("Enter Account Number: ");
                            int accountNumber = input.nextInt();

                            //prevent same account number exist
                            if (accountCheckingAccountNumber(accountNumber, arrAccount) == 1) {
                                System.out.println("Account number has been used. Re-enter account number\n");
                                error = 1;
                            } else {
                                System.out.print("Enter FD amount: ");
                                double fdAmount = input.nextDouble();

                                //display menu
                                System.out.print("""
                                        Select plan:
                                        3 month for rate 2.3
                                        6 month for rate 2.4
                                        9 months for rate 2.5
                                        12 months for rate 2.55
                                        Option>>""");
                                int month = input.nextInt();
                                if (exceptionFDMonth(month) == 1) {
                                    error = 1;
                                } else {
                                    //add information into array list
                                    arrAccount.add(new FdAccount(accountHolderName, username, password, accountNumber, fdAmount, month));
                                    System.out.println("Account added successfully. \nReturning to main menu......\n");
                                    error = 0;
                                }
                            }
                        }
                    }
                    case 2 -> {
                        //ask for account holder name
                        System.out.print("Enter Account Holder Name: ");
                        String accountHolderName = input.nextLine();

                        //ask for username
                        System.out.print("Enter Username: ");
                        String username = input.nextLine();

                        //prevent same username exist
                        if (accountCheckingAccountUsername(username, arrAccount) == 1) {
                            System.out.println("Account username has been used. Re-enter account username\n");
                            error = 1;
                        } else {
                            //ask for password
                            System.out.print("Enter Password: ");
                            String password = input.nextLine();

                            //ask for account number
                            System.out.print("Enter Account Number: ");
                            int accountNumber = input.nextInt();

                            //prevent same account number exist
                            if (accountCheckingAccountNumber(accountNumber, arrAccount) == 1) {
                                System.out.println("Account number has been used. Re-enter account number\n");
                                error = 1;
                            } else {
                                //ask if user want to apply for bank card
                                input.nextLine();
                                System.out.print("Does Account Holder need a bank card (yes/no): ");
                                String card = input.nextLine();
                                //prevent user from entering input other than yes and no
                                error = optionExceptionHandling(card);
                                if (error == 1) {
                                    //ask for card number
                                    System.out.print("Enter Card Number: ");
                                    int cardNumber = input.nextInt();

                                    //add information into array list
                                    arrAccount.add(new SavingAccount(accountHolderName, username, password, accountNumber, true, cardNumber));
                                    System.out.println("Account added successfully. \nReturning to main menu......\n");
                                    error = 0;
                                } else if (error == 0) {
                                    //add information into array list
                                    arrAccount.add(new SavingAccount(accountHolderName, username, password, accountNumber, false));
                                    System.out.println("Account added successfully. \nReturning to main menu......\n");
                                } else {
                                    error = 1;
                                }
                            }
                        }
                    }
                    case 3 -> {
                        //return back to previous menu
                        System.out.println("Returning to main menu......\n");
                        error = 0;
                    }
                    default -> {
                        //error message when input number other than (1-3)
                        System.out.println("Wrong Input. Re-select option\n");
                        error = 1;
                    }
                }
                //error message when input String or char
            } catch (InputMismatchException ex) {
                System.out.println("Wrong Input.\nRe-select option\n");
                input.nextLine();
            }
        } while (error != 0);
    }

    //to find whether the account exists and get the index of account
    public static int checkAccount(ArrayList<Account> arrAccount) {
        int index = 0;
        do {
            int searchFail = 0;
            System.out.print("Enter Username:");
            String username = input.nextLine();
            for (int i = 0; i < arrAccount.size(); i++) {
                if (username.equals(arrAccount.get(i).getUsername())) {
                    index = i;
                    error = 0;
                    break;
                } else {
                    searchFail++;
                }
            }
            //Account not exists
            if (searchFail == arrAccount.size()) {
                System.out.println("Account does not exists\n");
                System.out.print("Do you want to re-enter(yes/no): ");
                String option = input.nextLine();
                error = optionExceptionHandling(option);
                if (error == 0) {
                    index = -1;
                }
            }
        } while (error != 0);
        return index;
    }

    //let user deposit money in to their account
    public static void deposit(ArrayList<Account> arrAccount) {
        if (arrAccount.isEmpty()) {
            System.out.println("No account exists. Create an account and try again.\n");
        } else {
            try {
                int index = checkAccount(arrAccount);
                if (index == -1) {
                    System.out.println("Account not found\n");
                } else {
                    System.out.print("Enter amount of deposit: ");
                    double amount = input.nextDouble();
                    if (amount > 0) {
                        arrAccount.get(index).deposit(amount);
                        System.out.println("Deposit Successfully. Returning to main menu......\n");
                    } else {
                        System.out.println("Amount cannot be in negative value\n");
                    }
                }

            } catch (InputMismatchException ex) {
                System.out.println("Wrong input\n");
                input.nextLine();
            }
        }
    }

    //let user withdrawal amount from their account
    public static void withdrawal(ArrayList<Account> arrAccount) {
        if (arrAccount.isEmpty()) {
            System.out.println("No account exists. Create an account and try again.\n");
        } else {
            try {
                int index = checkAccount(arrAccount);
                if (index == -1) {
                    System.out.println("Account does not exist\n");
                } else {
                    System.out.print("Enter withdrawal amount: ");
                    double amount = input.nextDouble();
                    if (amount > 0) {
                        System.out.println(arrAccount.get(index).withdrawal(amount));
                    } else {
                        System.out.println("Amount cannot be in negative value\n");
                    }
                }
            } catch (InputMismatchException ex) {
                System.out.println("Wrong Input\n");
                input.nextLine();
            }
        }
    }
    //let user check account balance
    public static void checkBalance(ArrayList<Account> arrAccount) {
        if (arrAccount.isEmpty()) {
            System.out.println("No account exists. Create an account and try again.\n");
        } else {
            int index = checkAccount(arrAccount);
            if (index == -1) {
                System.out.println("Account does not exist\n");
            } else {
                System.out.println(arrAccount.get(index).checkBalance());
            }
        }
    }
    //let user edit account information if it is wrong
    public static void editAccount(ArrayList<Account> arrAccount) {
        if (arrAccount.isEmpty()) {
            System.out.println("No account exists. Create an account and try again.\n");
        } else {
            int index = checkAccount(arrAccount);
            if (index == -1) {
                System.out.println("Account does not exists");
            } else {
                try {
                    System.out.println("\n\nACCOUNT INFORMATION\n-------------------\n" + arrAccount.get(index));
                    System.out.println("\n\nEDIT INFORMATION\n------------------\n1. Account Holder Name \n2. Username\n3. Password\n4. Account number\n5. Exit");
                    System.out.print("User Option>>> ");
                    int option = input.nextInt();
                    input.nextLine();
                    switch (option) {
                        //change account holder name
                        case 1 -> {
                            System.out.print("Enter NEW Account Holder Name: ");
                            String accountHolderName = input.nextLine();
                            arrAccount.get(index).setAccountHolderName(accountHolderName);
                            System.out.println(arrAccount.get(index).toString());
                            System.out.println("Account holder name edited successfully\n");
                        }
                        //change username
                        case 2 -> {
                            System.out.print("Enter NEW Username: ");
                            String username = input.nextLine();
                            if (accountCheckingAccountUsername(username, arrAccount) == 1) {
                                System.out.println("Username is taken\n");
                                error = 1;
                            } else {
                                arrAccount.get(index).setUsername(username);
                                System.out.println(arrAccount.get(index).toString());
                                System.out.println("Username edited successfully\n");
                            }
                        }
                        //change password
                        case 3 -> {
                            System.out.print("Enter NEW Password: ");
                            String password = input.nextLine();
                            arrAccount.get(index).setPassword(password);
                            System.out.println(arrAccount.get(index).toString());
                            System.out.println("Password edited successfully\n");
                        }
                        //change account number
                        case 4 -> {
                            System.out.print("Enter NEW Account Number:  ");
                            int accountNumber = input.nextInt();
                            if (accountCheckingAccountNumber(accountNumber, arrAccount) == 1) {
                                System.out.println("Account Number is taken\n");
                                error = 1;
                            } else {
                                arrAccount.get(index).setAccountNumber(accountNumber);
                                System.out.println(arrAccount.get(index).toString());
                                System.out.println("Account number edited successfully\n");
                            }
                        }
                        //exit
                        case 5 -> System.out.println("Returning to main menu......\n");
                    }
                } catch (InputMismatchException ex) {
                    System.out.println("Wrong Input\n");
                    input.nextLine();
                }
            }
        }
    }

    //create error message
    public static int exceptionFDMonth(int month) {
        if (month == 3 || month == 6 || month == 9 || month == 12) {
            return 0;
        } else {
            System.out.println("Plan unavailable. Action add account fail\n");
            return 1;
        }
    }

    //create checking repeating username
    public static int accountCheckingAccountUsername(String username, ArrayList<Account> arrAccount) {
        error = 0;
        for (int i = 0; i < arrAccount.size(); i++) {
            if (arrAccount.get(i).getUsername().equals(username)) {
                error = 1;
                break;
            }
        }
        if (error == 1) {
            return 1;
        } else {
            return 0;
        }
    }

    //create checking repeating account number
    public static int accountCheckingAccountNumber(int accountNumber, ArrayList<Account> arrAccount) {
        error = 0;
        for (int i = 0; i < arrAccount.size(); i++) {
            if (arrAccount.get(i).getAccountNumber()==accountNumber) {
                error = 1;
                break;
            }
        }
        if (error == 1) {
            return 1;
        } else {
            return 0;
        }
    }
    //prevent user from entering option other than yes and no
    public static int optionExceptionHandling(String option) {
        if (option.equalsIgnoreCase("Yes")) {
            return 1;
        } else if (option.equalsIgnoreCase("no")) {
            return 0;
        } else {
            System.out.println("Wrong input.Please try again");
            return -1;
        }
    }


    //copy the accounts in arrayList to txt file
    public static void copyArraylistToFile(ArrayList<Account> arrAccount) {
        try {
            PrintWriter writerFd = new PrintWriter(fileFDAccount);
            PrintWriter writerSaving = new PrintWriter(fileSavingAccount);
            for (int i = 0; i < arrAccount.size(); i++) {
                //copy account information in fd account to "FD Account.txt"
                if (arrAccount.get(i) instanceof FdAccount) {
                    writerFd.println(arrAccount.get(i).getAccountHolderName());
                    writerFd.println(arrAccount.get(i).getUsername());
                    writerFd.println(arrAccount.get(i).getPassword());
                    writerFd.println(arrAccount.get(i).getAccountNumber());
                    writerFd.println(arrAccount.get(i).getCurrentDate());
                    writerFd.println(arrAccount.get(i).getAccountBalance());
                    writerFd.println(((FdAccount) arrAccount.get(i)).getFdAmount());
                    writerFd.println(((FdAccount) arrAccount.get(i)).getMonth());
                    writerFd.println(arrAccount.get(i).getWithdrawal());
                    writerFd.println(arrAccount.get(i).getDeposit());
                    writerFd.print("\n");
                } else {
                    //copy account information in saving account to "Saving Account.txt"
                    writerSaving.println(arrAccount.get(i).getAccountHolderName());
                    writerSaving.println(arrAccount.get(i).getUsername());
                    writerSaving.println(arrAccount.get(i).getPassword());
                    writerSaving.println(arrAccount.get(i).getAccountNumber());
                    writerSaving.println(arrAccount.get(i).getCurrentDate());
                    writerSaving.println(arrAccount.get(i).getAccountBalance());
                    writerSaving.println(((SavingAccount) arrAccount.get(i)).isCard());
                    writerSaving.println(((SavingAccount) arrAccount.get(i)).getCardNumber());
                    writerSaving.println(arrAccount.get(i).getWithdrawal());
                    writerSaving.println(arrAccount.get(i).getDeposit());
                    writerSaving.print("\n");
                }
            }

            //Even though the data amount is not enough but with flush can make sure the data will be copy into the txt file
            writerSaving.flush();
            writerFd.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    //copy account information in txt file into arrayList
    public static void copyFileToArraylist(ArrayList<Account> arrAccount) {
        try {
            Scanner inputFd = new Scanner(fileFDAccount);
            while (inputFd.hasNext()) {
                //copy account information from "Fd Account.txt" to arrayList
                String accountHolderName = inputFd.nextLine();
                String username = inputFd.nextLine();
                String password = inputFd.nextLine();

                int accountNumber = inputFd.nextInt();
                inputFd.nextLine();
                String date = inputFd.nextLine();
                LocalDate date1 = LocalDate.parse(date);
                double accountBalance = inputFd.nextDouble();
                double fdAmount = inputFd.nextDouble();
                int month = inputFd.nextInt();
                double withdrawal=inputFd.nextDouble();
                double deposit =inputFd.nextDouble();
                arrAccount.add(new FdAccount(accountHolderName, username, password, accountNumber, date1, accountBalance,withdrawal,deposit,fdAmount, month));
                inputFd.nextLine();
                inputFd.nextLine();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        try {
            Scanner inputSaving = new Scanner(fileSavingAccount);
            while (inputSaving.hasNext()) {
                //copy account information from "Saving account.txt" to arrayList
                String accountHolderName = inputSaving.nextLine();
                String username = inputSaving.nextLine();
                String password = inputSaving.nextLine();
                int accountNumber = inputSaving.nextInt();
                inputSaving.nextLine();
                String date = inputSaving.nextLine();
                LocalDate date1 = LocalDate.parse(date);
                double accountBalance = inputSaving.nextDouble();
                boolean card = inputSaving.nextBoolean();
                int cardNumber = inputSaving.nextInt();
                inputSaving.nextLine();
                double withdrawal=inputSaving.nextDouble();
                double deposit=inputSaving.nextDouble();
                inputSaving.nextLine();
                arrAccount.add(new SavingAccount(accountHolderName, username, password, accountNumber, date1, accountBalance,withdrawal,deposit, card, cardNumber));
                inputSaving.nextLine();

            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}





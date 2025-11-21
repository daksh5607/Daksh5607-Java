import java.util.InputMismatchException;
import java.util.Scanner;

// Creating Account Class
class Account {
    private int accountNumber;
    private String accountHolderName;
    private double balance;
    private String email;
    private String phoneNumber;

    // Make Constructor
    public Account(int accountNumber, String accountHolderName, double balance, String email, String phoneNumber) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = balance;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    // Adding Deposit Method
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: " + amount + ". New Balance: " + balance);
        } else {
            System.out.println("Invalid deposit amount. Must be positive.");
        }
    }

    // Adding Withdraw Method
    public void withdraw(double amount) {
        if (amount > 0) {
            if (balance >= amount) {
                balance -= amount;
                System.out.println("Withdrawn: " + amount + ". Remaining Balance: " + balance);
            } else {
                System.out.println("Insufficient balance!");
            }
        } else {
            System.out.println("Invalid withdrawal amount. Must be positive.");
        }
    }

    // For  Displaying  Account Details
    public void displayAccountDetails() {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Holder: " + accountHolderName);
        System.out.println("Balance: " + balance);
        System.out.println("Email: " + email);
        System.out.println("Phone: " + phoneNumber);
    }

    // To Update Contact Details
    public void updateContactDetails(String email, String phoneNumber) {
        this.email = email;
        this.phoneNumber = phoneNumber;
        System.out.println("Contact details updated successfully.");
    }

    // To use Getter for accountNumber
    public int getAccountNumber() {
        return accountNumber;
    }
}

// Creating User Interface Class
class UserInterface {
    private Account[] accounts;
    private int accountCount;
    private Scanner sc;

    public UserInterface(int size) {
        accounts = new Account[size];
        accountCount = 0;
        sc = new Scanner(System.in);
    }

    // For  New Account Creation
    public void createAccount() {
        System.out.print("Enter account holder name: ");
        String name = sc.nextLine();

        System.out.print("Enter initial deposit amount: ");
        double balance = sc.nextDouble();
        sc.nextLine(); 

        System.out.print("Enter email address: ");
        String email = sc.nextLine();

        System.out.print("Enter phone number: ");
        String phone = sc.nextLine();

        int accNo = 1000 + accountCount ;
        accounts[accountCount] = new Account(accNo, name, balance, email, phone);
        System.out.println("Account created successfully with Account Number: " + accNo);
        accountCount++;
    }

    // To Find Account by Number
    private Account findAccount(int accNo) {
        for (int i = 0; i < accountCount; i++) {
            if (accounts[i].getAccountNumber() == accNo) {
                return accounts[i];
            }
        }
        return null;
    }

    // For Deposit Operation
    public void performDeposit() {
        System.out.print("Enter account number: ");
        int accNo = sc.nextInt();
        System.out.print("Enter amount to deposit: ");
        double amount = sc.nextDouble();
        sc.nextLine();

        Account acc = findAccount(accNo);
        if (acc != null) {
            acc.deposit(amount);
        } else {
            System.out.println("Account not found.");
        }
    }

    // For Withdrawal Operation
    public void performWithdrawal() {
        System.out.print("Enter account number: ");
        int accNo = sc.nextInt();
        System.out.print("Enter amount to withdraw: ");
        double amount = sc.nextDouble();
        sc.nextLine();

        Account acc = findAccount(accNo);
        if (acc != null) {
            acc.withdraw(amount);
        } else {
            System.out.println("Account not found.");
        }
    }

    // To Show Account Details
    public void showAccountDetails() {
        System.out.print("Enter account number: ");
        int accNo = sc.nextInt();
        sc.nextLine();

        Account acc = findAccount(accNo);
        if (acc != null) {
            acc.displayAccountDetails();
        } else {
            System.out.println("Account not found.");
        }
    }

    // To Update Contact
    public void updateContact() {
        System.out.print("Enter account number: ");
        int accNo = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter new email: ");
        String email = sc.nextLine();

        System.out.print("Enter new phone number: ");
        String phone = sc.nextLine();

        Account acc = findAccount(accNo);
        if (acc != null) {
            acc.updateContactDetails(email, phone);
        } else {
            System.out.println("Account not found.");
        }
    }

    // For  Main Menu
    public void mainMenu() {
        int choice;
        do {
            System.out.println("\nWelcome to the Banking Application!");
            System.out.println("1. Create a new account");
            System.out.println("2. Deposit money");
            System.out.println("3. Withdraw money");
            System.out.println("4. View account details");
            System.out.println("5. Update contact details");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1: createAccount(); break;
                case 2: performDeposit(); break;
                case 3: performWithdrawal(); break;
                case 4: showAccountDetails(); break;
                case 5: updateContact(); break;
                case 6: System.out.println("Exiting application. Goodbye!"); break;
                default: System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 6);
    }
}

// For Main Class
public class BankingApp {
    public static void main(String[] args) {
        UserInterface ui = new UserInterface(50); 
        ui.mainMenu();
    }
}


//import javafx.application.Application;
//import java.util.InputMismatchException;
//import java.util.Scanner;

public class UserInterface1 {
    // To store all the bank accounts
    private final Account[] accounts;
        private int accountCount;
    private final Scanner scanner;

    public UserInterface1() {
        // Initialize an array 
        this.accounts = new Account[100];
                this.accountCount = 0;
            this.scanner = new Scanner(System.in);
    }

    public void createAccount() {
        // To Check for hitting the max account limit
        if (accountCount >= accounts.length) {
            System.out.println("❌ Cannot create new account. Maximum limit reached.");
            return;
        }

        System.out.println("\n--- Create New Account ---");

        int accountNumber;
        // Loop until a valid, unique account number is entered
        while (true) {
            System.out.print("Enter a unique 5-digit Account Number: ");
            accountNumber = readInt();
            String accountNumStr = String.valueOf(accountNumber);
            if (accountNumStr.length() != 5) {
                System.out.println("❌ Account number must be exactly 5 digits. Please try again.");
            } else if (findAccount(accountNumber) != null) {
                System.out.println("❌ This account number already exists. Please choose a different one.");
            } else {
                break;
            }
        }

        System.out.print("Enter Account Holder's Name: ");
        String accountHolderName = scanner.nextLine();

        double initialBalance = 0;
        // Loop until a valid initial deposit amount is entered
        while (true) {
            System.out.print("Enter Initial Deposit Amount: ");
            initialBalance = readDouble();
            if (initialBalance < 0) {
                System.out.println("❌ Initial deposit cannot be negative. Please try again.");
            } else {
                break;
            }
        }

        System.out.print("Enter Email Address: ");
        String email = scanner.nextLine();
        System.out.print("Enter Phone Number: ");
        String phoneNumber = scanner.nextLine();

    
        accounts[accountCount] = new Account(accountNumber, accountHolderName, initialBalance, email, phoneNumber);
        accountCount++;
        System.out.println("\n✅ Account created successfully!");
        accounts[accountCount - 1].displayAccountDetails();
    }

    public void performDeposit() {
        System.out.println("\n--- Deposit Money ---");
        //  account to deposit to
        Account account = getAccountFromUser();
        if (account == null) {
            return;
        }

        System.out.print("Enter amount to deposit: ");
        double amount = readDouble();
        account.deposit(amount);
    }

    public void performWithdrawal() {
        System.out.println("\n--- Withdraw Money ---");
        // account to withdraw from
        Account account = getAccountFromUser();
        if (account == null) {
            return;
        }

        System.out.print("Enter amount to withdraw: ");
        double amount = readDouble();
        account.withdraw(amount);
    }

    public void showAccountDetails() {
        System.out.println("\n--- View Account Details ---");
        // Get the account and show its details
        Account account = getAccountFromUser();
        if (account != null) {
            account.displayAccountDetails();
        }
    }

    public void updateContact() {
        System.out.println("\n--- Update Contact Details ---");
        // Update account
        Account account = getAccountFromUser();
        if (account == null) {
            return;
        }

        System.out.print("Enter new Email Address (or press Enter to keep current): ");
        String newEmail = scanner.nextLine();
        System.out.print("Enter new Phone Number (or press Enter to keep current): ");
        String newPhoneNumber = scanner.nextLine();

                account.updateContactDetails(newEmail, newPhoneNumber);
    }

    
    private Account findAccount(int accountNumber) {
        for (int i = 0; i < accountCount; i++) {
            if (accounts[i].getAccountNumber() == accountNumber) {
                return accounts[i];
            }
        }
        return null;
    }

        private Account getAccountFromUser() {
        int accountNumber;
        while (true) {
            System.out.print("Enter Account Number (or '0' to cancel): ");
            accountNumber = readInt();
            if (accountNumber == 0) {
                System.out.println("Operation canceled.");
                return null;
            }
            Account account = findAccount(accountNumber);
            if (account != null) {
                return account;
            } else {
                System.out.println("❌ Account not found. Please check the number and try again.");
            }
        }
    }

        private int readInt() {
        while (true) {
            try {
                int value = scanner.nextInt();
                scanner.nextLine();
                return value;
            } catch (InputMismatchException e) {
                System.out.println("❌ Invalid input. Please enter a whole number.");
                scanner.nextLine();
            }
        }
    }

    
    private double readDouble() {
        while (true) {
            try {
                double value = scanner.nextDouble();
                scanner.nextLine(); 
                                return value;
            } catch (InputMismatchException e) {
                System.out.println("❌ Invalid input. Please enter a number.");
                scanner.nextLine();
            }
        }
    }

    public void mainMenu() {
        int choice;
                while (true) {
            System.out.println("\n\n=============== Banking Application Menu ===============");
            System.out.println("1. Create New Account");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. View Account Details");
            System.out.println("5. Update Contact Details");
            System.out.println("6. Exit");
            System.out.println("======================================================");

            System.out.print("Enter your choice: ");
            choice = readInt();
            System.out.println("------------------------------------------------------");

            switch (choice) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    performDeposit();
                    break;
                case 3:
                    performWithdrawal();
                    break;
                case 4:
                    showAccountDetails();
                    break;
                case 5:
                    updateContact();
                    break;
                case 6:
                    System.out.println(" Thank you for using the Banking Application. Goodbye!");
                    return;
                default:
                    System.out.println("❌ Invalid choice. Please select a number from the menu.");
            }} }
        

        public static void main( String[] args)
     {
        UserInterface1 ui = new UserInterface1();
        ui.mainMenu();
    }
}

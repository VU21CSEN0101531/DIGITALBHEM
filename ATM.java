import java.util.*;

class User {
    private String userId;
    private String pin;

    public User(String userId, String pin) {
        this.userId = userId;
        this.pin = pin;
    }

    public String getUserId() {
        return userId;
    }

    public String getPin() {
        return pin;
    }
}

class Transaction {
    private Date date;
    private String description;

    public Transaction(String description) {
        this.date = new Date();
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }
}

class Withdrawal extends Transaction {
    private double amount;

    public Withdrawal(double amount) {
        super("Withdrawal");
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }
}

class Deposit extends Transaction {
    private double amount;

    public Deposit(double amount) {
        super("Deposit");
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }
}

class ATM {
    private Map<String, User> users;
    private List<Transaction> transactions;

    public ATM() {
        users = new HashMap<>();
        users.put("Hemasree", new User("Hemasree", "2004")); // Modified user credentials
        transactions = new ArrayList<>();
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the ATM!");
        System.out.print("Enter User ID: ");
        String userId = scanner.nextLine();
        System.out.print("Enter PIN: ");
        String pin = scanner.nextLine();

        if (authenticateUser(userId, pin)) {
            System.out.println("Authentication successful.");
            showMenu();
        } else {
            System.out.println("Authentication failed. Exiting.");
        }
    }

    private boolean authenticateUser(String userId, String pin) {
        User user = users.get(userId);
        return user != null && user.getPin().equals(pin);
    }

    private void showMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean quit = false;
        while (!quit) {
            System.out.println("\nSelect an option:");
            System.out.println("1. View Transactions History");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. Quit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    viewTransactions();
                    break;
                case 2:
                    withdraw();
                    break;
                case 3:
                    deposit();
                    break;
                case 4:
                    System.out.println("Transfer functionality not implemented yet.");
                    break;
                case 5:
                    quit = true;
                    System.out.println("Exiting. Thank you for using the ATM.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void viewTransactions() {
        System.out.println("\nTransaction History:");
        for (Transaction transaction : transactions) {
            System.out.println(transaction.getDescription() + " at " + transaction.getDate());
        }
    }

    private void withdraw() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter amount to withdraw: ");
        double amount = scanner.nextDouble();
        if (amount <= 0) {
            System.out.println("Invalid amount.");
            return;
        }
        transactions.add(new Withdrawal(amount));
        System.out.println("$" + amount + " withdrawn successfully.");
    }

    private void deposit() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter amount to deposit: ");
        double amount = scanner.nextDouble();
        if (amount <= 0) {
            System.out.println("Invalid amount.");
            return;
        }
        transactions.add(new Deposit(amount));
        System.out.println("$" + amount + " deposited successfully.");
    }
}

public class Main {
    public static void main(String[] args) {
        ATM atm = new ATM();
        atm.start();
    }
}


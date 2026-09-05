class Customer {
    private String name;
    private String email;
    private String mobile;
    private final String customerId;

    private static long customerCounter = 100;

    private static String generateCustomerId() {
        customerCounter++;
        return "CUST" + customerCounter;
    }

    public Customer(String name, String email, String mobile) {
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.customerId = generateCustomerId();
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getMobile() {
        return mobile;
    }

    public String getCustomerId() {
        return customerId;
    }
}

class Account {
    private final String accountNumber;
    private String ownerName;
    private long balance;
    private boolean active;

    private static long accountCounter = 0;

    private static String generateAccountNumber() {
        accountCounter++;
        return String.format("AC%04d", accountCounter);
    }

    public Account(String ownerName, long balance) {
        this.accountNumber = generateAccountNumber();
        this.ownerName = ownerName;
        this.balance = balance;
        this.active = true;
    }

    public Account(String ownerName) {
        this(ownerName, 0);
    }

    public void deposit(long amount) {
        if (amount > 0) {
            balance = balance + amount;
        }
    }

    public boolean withdraw(long amount) {
        if (amount > 0 && balance >= amount) {
            balance = balance - amount;
            return true;
        }
        return false;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public long getBalance() {
        return balance;
    }

    public boolean isActive() {
        return active;
    }
}

public class MiniBank2 {
    public static void main(String[] args) {
        Account[] accounts = new Account[3];

        accounts[0] = new Account("Alice", 1000);
        accounts[1] = new Account("Bob", 500);
        accounts[2] = new Account("Charlie");

        accounts[0].deposit(500);
        accounts[1].withdraw(200);
        accounts[2].deposit(1200);
        accounts[2].withdraw(1500);

        System.out.println("=== MiniBank Account Balances ===");
        for (int i = 0; i < accounts.length; i++) {
            System.out.println("Account: " + accounts[i].getAccountNumber() + " | Owner: " + accounts[i].getOwnerName() + " | Balance: ₹" + accounts[i].getBalance());
        }
    }
}
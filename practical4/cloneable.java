import java.util.Objects;
import java.util.regex.Pattern;

class Customer implements Cloneable {

    private String name;
    private String email;
    private String mobile;
    private final String customerId;

    private Address address;

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

    public static class Address {

        private String line;
        private String city;
        private String pincode;

        public Address(String line, String city, String pincode) {
            this.line = line;
            this.city = city;
            this.pincode = pincode;
        }

        public String getLine() {
            return line;
        }

        public String getCity() {
            return city;
        }

        public String getPincode() {
            return pincode;
        }

        @Override
        public String toString() {
            return line + ", " + city + " - " + pincode;
        }
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Address getAddress() {
        return address;
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

    @Override
    public Customer clone() {
        try {
            Customer copy = (Customer) super.clone();

            if (this.address != null) {
                copy.address = new Address(
                    this.address.getLine(),
                    this.address.getCity(),
                    this.address.getPincode()
                );
            }

            return copy;

        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
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

    public Account(String ownerName, long openingBalance) {
        this.accountNumber = generateAccountNumber();
        this.ownerName = ownerName;
        this.balance = openingBalance;
        this.active = true;
    }

    public Account(String ownerName) {
        this(ownerName, 0);
    }

    public void deposit(long amount) {
        balance += amount;
    }

    public boolean withdraw(long amount) {
        if (amount <= balance) {
            balance -= amount;
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

    @Override
    public String toString() {
        return accountNumber + " - " + ownerName +
               " - Balance: " + balance;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj)
            return true;

        if (obj == null || !(obj instanceof Account))
            return false;

        Account other = (Account) obj;

        return accountNumber.equals(other.accountNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountNumber);
    }
}

class Validator {

    private static final Pattern MOBILE =
        Pattern.compile("^[6-9][0-9]{9}$");

    private static final Pattern EMAIL =
        Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");

    private static final Pattern PAN =
        Pattern.compile("^[A-Z]{5}[0-9]{4}[A-Z]$");

    private static final Pattern IFSC =
        Pattern.compile("^[A-Z]{4}0[A-Z0-9]{6}$");

    public static boolean isValidMobile(String mobile) {
        return MOBILE.matcher(mobile).matches();
    }

    public static boolean isValidEmail(String email) {
        return EMAIL.matcher(email).matches();
    }

    public static boolean isValidPan(String pan) {
        return PAN.matcher(pan).matches();
    }

    public static boolean isValidIfsc(String ifsc) {
        return IFSC.matcher(ifsc).matches();
    }
}

enum TransactionType {
    DEPOSIT,
    WITHDRAW,
    TRANSFER
}

record Command(
    TransactionType type,
    String accountNumber,
    long amount
) {
}

class CommandParser {

    public static Command parse(String line) {

        String[] parts = line.split(" ");

        TransactionType type =
            TransactionType.valueOf(parts[0]);

        String accountNumber = parts[1];

        long amount = Long.parseLong(parts[2]);

        return new Command(
            type,
            accountNumber,
            amount
        );
    }
}

class StatementFormatter {

    public static String buildStatement(Account account) {

        StringBuilder statement = new StringBuilder();

        statement.append("========== ACCOUNT STATEMENT ==========\n");
        statement.append("Account Number: ")
                  .append(account.getAccountNumber())
                  .append("\n");

        statement.append("Owner Name: ")
                  .append(account.getOwnerName())
                  .append("\n");

        statement.append("Balance: ")
                  .append(account.getBalance())
                  .append("\n");

        statement.append("Status: ")
                  .append(account.isActive() ? "Active" : "Inactive")
                  .append("\n");

        statement.append("=======================================\n");

        return statement.toString();
    }
}

public class miniBank {

    public static void main(String[] args) {

        System.out.println("==================================");
        System.out.println("        MINI BANK SYSTEM");
        System.out.println("==================================");

        System.out.println("SBI - ANKLESHWAR");
        System.out.println();

        Account[] accounts = new Account[3];

        accounts[0] = new Account("Fenil", 5000);
        accounts[1] = new Account("Rahul");
        accounts[2] = new Account("Jay", 10000);

        accounts[0].deposit(2000);
        accounts[0].withdraw(1000);

        accounts[1].deposit(5000);
        accounts[1].withdraw(2000);

        accounts[2].withdraw(3000);
        accounts[2].deposit(1500);

        System.out.println("Accounts:");

        for (Account account : accounts) {
            System.out.println(account.toString());
        }

        System.out.println();

        System.out.println("Account Comparison:");

        if (accounts[0].equals(accounts[1])) {
            System.out.println("Accounts are equal.");
        } else {
            System.out.println("Accounts are not equal.");
        }

        System.out.println();

        System.out.println("Type Checking:");

        if (accounts[0] instanceof Account) {
            System.out.println("accounts[0] is an Account.");
        }

        System.out.println();

        Customer customer = new Customer(
            "Fenil",
            "fenil@gmail.com",
            "9876543210"
        );

        Customer.Address address =
            new Customer.Address(
                "Main Road",
                "Ankleshwar",
                "393001"
            );

        customer.setAddress(address);

        System.out.println("Customer:");
        System.out.println("Name: " + customer.getName());
        System.out.println("Email: " + customer.getEmail());
        System.out.println("Mobile: " + customer.getMobile());
        System.out.println("Customer ID: " + customer.getCustomerId());
        System.out.println("Address: " + customer.getAddress());

        System.out.println();

        Customer clonedCustomer = customer.clone();

        System.out.println("Cloned Customer:");
        System.out.println("Name: " + clonedCustomer.getName());
        System.out.println("Customer ID: " + clonedCustomer.getCustomerId());
        System.out.println("Address: " + clonedCustomer.getAddress());

        System.out.println();

        System.out.println();
        System.out.println("Validator Testing:");

        System.out.println("Valid Mobile: "
            + Validator.isValidMobile("9876543210"));

        System.out.println("Invalid Mobile: "
            + Validator.isValidMobile("12345"));

        System.out.println("Valid Email: "
            + Validator.isValidEmail("fenil@gmail.com"));

        System.out.println("Invalid Email: "
            + Validator.isValidEmail("fenil@gmail"));

        System.out.println("Valid PAN: "
            + Validator.isValidPan("ABCDE1234F"));

        System.out.println("Invalid PAN: "
            + Validator.isValidPan("ABC123"));

        System.out.println("Valid IFSC: "
            + Validator.isValidIfsc("SBIN0001234"));

        System.out.println("Invalid IFSC: "
            + Validator.isValidIfsc("SBI123"));

        System.out.println();
        System.out.println("Command Parser:");

        String line = "DEPOSIT AC0001 500";

        Command command =
            CommandParser.parse(line);

        System.out.println("Type: "
            + command.type());

        System.out.println("Account Number: "
            + command.accountNumber());

        System.out.println("Amount: "
            + command.amount());

        System.out.println();
        System.out.println("Account Statement:");

        String statement =
            StatementFormatter.buildStatement(accounts[0]);

        System.out.println(statement);
    }
}
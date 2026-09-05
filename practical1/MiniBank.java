import java.util.Scanner;

public class MiniBank {

    record BankInfo(String name, String branch) {}

    enum MenuOption {
        OPEN_ACCOUNT,
        DEPOSIT,
        WITHDRAW,
        TRANSFER,
        EXIT
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        BankInfo bank = new BankInfo("MiniBank", "Anand Branch");
        System.out.println(bank.name());
        System.out.println(bank.branch());

        double balance = 0;
        String accountName = "";

        int choice;

        do {

            System.out.println("\n1. Open Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. Exit");

            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1 -> {
                    System.out.print("Enter Account Holder Name: ");
                    accountName = sc.nextLine();
                    balance = 0;
                    System.out.println("Account opened successfully for " + accountName);
                }

                case 2 -> {
                    System.out.print("Enter Deposit Amount: ");
                    double amount = sc.nextDouble();
                    balance = balance + amount;
                    System.out.println("Deposit Successful.");
                    System.out.println("Current Balance = " + balance);
                }

                case 3 -> {
                    System.out.print("Enter Withdraw Amount: ");
                    double amount = sc.nextDouble();

                    if (amount <= balance) {
                        balance = balance - amount;
                        System.out.println("Withdrawal Successful.");
                    } else {
                        System.out.println("Insufficient Balance.");
                    }

                    System.out.println("Current Balance = " + balance);
                }

                case 4 -> {
                    System.out.print("Enter Transfer Amount: ");
                    double amount = sc.nextDouble();

                    if (amount <= balance) {
                        balance = balance - amount;
                        System.out.println("Amount Transferred Successfully.");
                    } else {
                        System.out.println("Insufficient Balance.");
                    }

                    System.out.println("Current Balance = " + balance);
                }

                case 5 -> {
                    System.out.println("Thank You for using MiniBank.");
                }

                default -> {
                    System.out.println("Invalid Choice.");
                }
            }

        } while (choice != 5);

        sc.close();
    }
}
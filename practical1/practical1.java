import java.util.Scanner;

public class practical1 {

    enum Coin {
        ONE, TWO, FIVE, TEN
    }

    public static void main(String[] args) {

        int snackprize = 15;
        int total = 0;

        Scanner scn = new Scanner(System.in);

        System.out.println("Snack price is: " + snackprize);

        while (total < snackprize) {
            System.out.print("Enter the coin (ONE, TWO, FIVE, TEN): ");

            String input = scn.next().toUpperCase();
            Coin coin = Coin.valueOf(input);

            int value = switch (coin) {
                case ONE -> 1;
                case TWO -> 2;
                case FIVE -> 5;
                case TEN -> 10;
            };

            total += value;
            System.out.println("Total inserted: " + total);
        }

        System.out.println("Snack dispensed!");

        if (total > snackprize) {
            System.out.println("Change returned: " + (total - snackprize));
        }

        scn.close();
    }
}

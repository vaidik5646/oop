package oop;
import java.util.Scanner;

public class TollBooth {

    record Vehicle(String number, String type) {}

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int totalToll = 0;

        int bikeCount = 0;
        int carCount = 0;
        int truckCount = 0;

        while (true) {

            System.out.print("Enter Vehicle Number (or done): ");
            String number = sc.nextLine();

            if (number.equalsIgnoreCase("done")) {
                break;
            }

            System.out.print("Enter Vehicle Type (bike/car/truck): ");
            String type = sc.nextLine();

            Vehicle v = new Vehicle(number, type);

            int toll = switch (v.type().toLowerCase()) {
                case "bike" -> 20;
                case "car" -> 50;
                case "truck" -> 150;
                default -> 0;
            };

            totalToll = totalToll + toll;

            switch (v.type().toLowerCase()) {
                case "bike":
                    bikeCount++;
                    break;
                case "car":
                    carCount++;
                    break;
                case "truck":
                    truckCount++;
                    break;
            }
        }

        System.out.println("\nTotal Toll: " + totalToll);

        if (bikeCount >= carCount && bikeCount >= truckCount) {
            System.out.println("Most Frequent: bike");
        }
        else if (carCount >= bikeCount && carCount >= truckCount) {
            System.out.println("Most Frequent: car");
        }
        else {
            System.out.println("Most Frequent: truck");
        }

        sc.close();
    }
}
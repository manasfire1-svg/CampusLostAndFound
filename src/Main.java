import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        boolean running = true;

        while (running) {

            System.out.println();
            System.out.println("==========================================");
            System.out.println("       CAMPUS LOST & FOUND MATCHER");
            System.out.println("==========================================");
            System.out.println("1. Report Lost Item");
            System.out.println("2. Report Found Item");
            System.out.println("3. View Items");
            System.out.println("4. Find Possible Matches");
            System.out.println("5. Exit");
            System.out.println("------------------------------------------");
            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.println();
                    System.out.println("Report Lost Item");
                    System.out.println("Feature will be added in the next step.");
                    break;

                case "2":
                    System.out.println();
                    System.out.println("Report Found Item");
                    System.out.println("Feature will be added in the next step.");
                    break;

                case "3":
                    System.out.println();
                    System.out.println("View Items");
                    System.out.println("Feature will be added in the next step.");
                    break;

                case "4":
                    System.out.println();
                    System.out.println("Possible Matches");
                    System.out.println("Matching feature will be added in the next step.");
                    break;

                case "5":
                    running = false;
                    System.out.println();
                    System.out.println("Thank you for using Campus Lost & Found Matcher.");
                    break;

                default:
                    System.out.println();
                    System.out.println("Invalid choice. Please enter a number from 1 to 5.");
            }
        }

        scanner.close();
    }
}

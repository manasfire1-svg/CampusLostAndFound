import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        boolean running = true;

        while (running) {

            showMenu();

            String choice = scanner.nextLine();

            switch (choice) {

                case "1":
                    reportLostItem();
                    break;

                case "2":
                    reportFoundItem();
                    break;

                case "3":
                    viewItems();
                    break;

                case "4":
                    System.out.println();
                    System.out.println("========== POSSIBLE MATCHES ==========");
                    System.out.println("Matching feature will be added next.");
                    break;

                case "5":
                    running = false;
                    System.out.println();
                    System.out.println("Thank you for using Campus Lost & Found Matcher.");
                    break;

                default:
                    System.out.println();
                    System.out.println("Invalid choice.");
                    System.out.println("Please enter a number from 1 to 5.");
            }
        }

        scanner.close();
    }

    private static void showMenu() {

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
    }

    private static void reportLostItem() {

        System.out.println();
        System.out.println("========== REPORT LOST ITEM ==========");

        System.out.print("Item name: ");
        String itemName = scanner.nextLine();

        if (itemName.trim().isEmpty()) {
            System.out.println("Item name cannot be empty.");
            return;
        }

        System.out.print("Category: ");
        String category = scanner.nextLine();

        if (category.trim().isEmpty()) {
            System.out.println("Category cannot be empty.");
            return;
        }

        System.out.print("Description: ");
        String description = scanner.nextLine();

        System.out.print("Location where it was lost: ");
        String location = scanner.nextLine();

        if (location.trim().isEmpty()) {
            System.out.println("Location cannot be empty.");
            return;
        }

        System.out.print("Date (DD-MM-YYYY): ");
        String date = scanner.nextLine();

        if (date.trim().isEmpty()) {
            System.out.println("Date cannot be empty.");
            return;
        }

        System.out.print("Contact information: ");
        String contact = scanner.nextLine();

        if (contact.trim().isEmpty()) {
            System.out.println("Contact information cannot be empty.");
            return;
        }

        LostItem lostItem = new LostItem(
                itemName,
                category,
                description,
                location,
                date,
                contact
        );

        lostItem.saveItem();
    }

    private static void reportFoundItem() {

        System.out.println();
        System.out.println("========== REPORT FOUND ITEM ==========");

        System.out.print("Item name: ");
        String itemName = scanner.nextLine();

        if (itemName.trim().isEmpty()) {
            System.out.println("Item name cannot be empty.");
            return;
        }

        System.out.print("Category: ");
        String category = scanner.nextLine();

        if (category.trim().isEmpty()) {
            System.out.println("Category cannot be empty.");
            return;
        }

        System.out.print("Description: ");
        String description = scanner.nextLine();

        System.out.print("Location where it was found: ");
        String location = scanner.nextLine();

        if (location.trim().isEmpty()) {
            System.out.println("Location cannot be empty.");
            return;
        }

        System.out.print("Date (DD-MM-YYYY): ");
        String date = scanner.nextLine();

        if (date.trim().isEmpty()) {
            System.out.println("Date cannot be empty.");
            return;
        }

        System.out.print("Contact information: ");
        String contact = scanner.nextLine();

        if (contact.trim().isEmpty()) {
            System.out.println("Contact information cannot be empty.");
            return;
        }

        FoundItem foundItem = new FoundItem(
                itemName,
                category,
                description,
                location,
                date,
                contact
        );

        foundItem.saveItem();
    }

    private static void viewItems() {

        System.out.println();
        System.out.println("========== VIEW REPORTED ITEMS ==========");
        System.out.println("1. View Lost Items");
        System.out.println("2. View Found Items");
        System.out.println("3. View All Items");
        System.out.println("-----------------------------------------");
        System.out.print("Enter your choice: ");

        String choice = scanner.nextLine();

        switch (choice) {

            case "1":
                displayFile("data/lost_items.txt", "LOST ITEMS");
                break;

            case "2":
                displayFile("data/found_items.txt", "FOUND ITEMS");
                break;

            case "3":
                displayFile("data/lost_items.txt", "LOST ITEMS");
                displayFile("data/found_items.txt", "FOUND ITEMS");
                break;

            default:
                System.out.println("Invalid choice.");
        }
    }

    private static void displayFile(String filePath, String title) {

        File file = new File(filePath);

        System.out.println();
        System.out.println("========== " + title + " ==========");

        if (!file.exists()) {
            System.out.println("No reports found.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(
                new FileReader(file))) {

            String line;
            int count = 1;

            while ((line = reader.readLine()) != null) {

                String[] data = line.split("\\|", -1);

                if (data.length >= 6) {

                    System.out.println();
                    System.out.println("Report #" + count);
                    System.out.println("Item       : " + data[0]);
                    System.out.println("Category   : " + data[1]);
                    System.out.println("Description: " + data[2]);
                    System.out.println("Location   : " + data[3]);
                    System.out.println("Date       : " + data[4]);
                    System.out.println("Contact    : " + data[5]);
                    System.out.println("-----------------------------------------");

                    count++;
                }
            }

            if (count == 1) {
                System.out.println("No reports found.");
            }

        } catch (IOException e) {

            System.out.println("Unable to read the reports.");
        }
    }
}

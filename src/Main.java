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
                    System.out.println();
                    System.out.println("View Items");
                    System.out.println("This feature will be added later.");
                    break;

                case "4":
                    System.out.println();
                    System.out.println("Possible Matches");
                    System.out.println("This feature will be added later.");
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
}

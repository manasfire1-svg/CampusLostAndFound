import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

    boolean loggedIn = false;

    while (!loggedIn) {

        System.out.println();
        System.out.println("==========================================");
        System.out.println("       CAMPUS LOST & FOUND MATCHER");
        System.out.println("==========================================");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("3. Admin Login");
        System.out.println("4. Exit");
        System.out.println("------------------------------------------");
        System.out.print("Enter your choice: ");

        String choice = scanner.nextLine();

        switch (choice) {

            case "1":
                registerUser();
                break;

            case "2":
                loggedIn = loginUser();
                break;

            case "3":
            adminLogin();
                private static void adminMenu() {

    Admin admin = new Admin();

    boolean loggedIn = true;

    while (loggedIn) {

        System.out.println();
        System.out.println("================================");
        System.out.println("          ADMIN PANEL");
        System.out.println("================================");
        System.out.println("1. View Reports");
        System.out.println("2. Review Claims");
        System.out.println("3. Update Claim Status");
        System.out.println("4. Logout");
        System.out.println("--------------------------------");
        System.out.print("Enter your choice: ");

        String choice = scanner.nextLine();

        switch (choice) {

            case "1":
                admin.showReports();
                break;

            case "2":
                admin.reviewClaims();
                break;

            case "3":
                admin.updateClaimStatus();
                break;

            case "4":
                loggedIn = false;
                System.out.println("Admin logged out.");
                break;

            default:
                System.out.println("Invalid choice.");
        }
    }
}
            break;

            case "4":
            System.out.println("Thank you for using the application.");
            scanner.close();
            return;

            default:
                System.out.println("Invalid choice.");
        }
    }

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
                MatchingService matchingService =
                        new MatchingService();
                matchingService.findMatches();
                break;

            case "5":
                submitClaim();
                break;

            case "6":
                running = false;
                System.out.println();
                System.out.println(
                        "Thank you for using Campus Lost & Found Matcher.");
                break;

            default:
                System.out.println();
                System.out.println("Invalid choice.");
                System.out.println("Please enter a number from 1 to 6.");
        }
    }

    scanner.close();
}


private static void submitClaim() {

    System.out.println();
    System.out.println("========== SUBMIT A CLAIM ==========");

    System.out.print("Item name: ");
    String itemName = scanner.nextLine();

    if (itemName.trim().isEmpty()) {
        System.out.println("Item name cannot be empty.");
        return;
    }

    System.out.print("Your name: ");
    String claimantName = scanner.nextLine();

    if (claimantName.trim().isEmpty()) {
        System.out.println("Name cannot be empty.");
        return;
    }

    System.out.print("Contact information: ");
    String contact = scanner.nextLine();

    if (contact.trim().isEmpty()) {
        System.out.println("Contact information cannot be empty.");
        return;
    }

    System.out.println("Give some information that can help");
    System.out.println("verify that the item belongs to you.");

    System.out.print("Proof/details: ");
    String proof = scanner.nextLine();

    if (proof.trim().isEmpty()) {
        System.out.println("Proof/details cannot be empty.");
        return;
    }

    Claim claim = new Claim(
            itemName,
            claimantName,
            contact,
            proof
    );

    claim.saveClaim();
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
        System.out.println("5. Submit a Claim");
        System.out.println("6. Exit");
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
    private static void registerUser() {

    System.out.println();
    System.out.println("========== REGISTER ==========");

    System.out.print("Name: ");
    String name = scanner.nextLine();

    if (name.trim().isEmpty()) {
        System.out.println("Name cannot be empty.");
        return;
    }

    System.out.print("Email: ");
    String email = scanner.nextLine();

    if (email.trim().isEmpty() || !email.contains("@")) {
        System.out.println("Please enter a valid email.");
        return;
    }

    System.out.print("Password: ");
    String password = scanner.nextLine();

    if (password.length() < 4) {
        System.out.println(
                "Password must contain at least 4 characters.");
        return;
    }

    User user = new User(name, email, password);
    user.register();
}
private static boolean loginUser() {

    System.out.println();
    System.out.println("========== LOGIN ==========");

    System.out.print("Email: ");
    String email = scanner.nextLine();

    System.out.print("Password: ");
    String password = scanner.nextLine();

    if (User.login(email, password)) {

        System.out.println();
        System.out.println("Login successful.");
        System.out.println("Welcome to Campus Lost & Found Matcher.");

        return true;

    } else {

        System.out.println();
        System.out.println("Invalid email or password.");

        return false;
    }
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
    public static String readInput() {
    return scanner.nextLine();
}
    private static void adminLogin() {

    System.out.println();
    System.out.println("========== ADMIN LOGIN ==========");

    System.out.print("Admin email: ");
    String email = scanner.nextLine();

    System.out.print("Admin password: ");
    String password = scanner.nextLine();

    if (Admin.login(email, password)) {

        System.out.println();
        System.out.println("Admin login successful.");

        adminMenu();

    } else {

        System.out.println();
        System.out.println("Invalid admin email or password.");
    }
}
}

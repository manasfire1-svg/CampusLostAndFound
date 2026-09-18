import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        boolean loggedIn = false;

        // ==============================
        // LOGIN MENU
        // ==============================

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
                    break;

                case "4":
                    System.out.println();
                    System.out.println(
                            "Thank you for using the application."
                    );
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }

        // ==============================
        // USER MENU
        // ==============================

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
                    SearchService searchService =
                            new SearchService();

                    searchService.searchItems();
                    break;

                case "5":
                    MatchingService matchingService =
                            new MatchingService();

                    matchingService.findMatches();
                    break;

                case "6":
                    submitClaim();
                    break;

                case "7":
                    running = false;

                    System.out.println();
                    System.out.println(
                            "Thank you for using Campus Lost & Found Matcher."
                    );
                    break;

                default:
                    System.out.println();
                    System.out.println("Invalid choice.");
                    System.out.println(
                            "Please enter a number from 1 to 7."
                    );
            }
        }

        scanner.close();
    }

    // ==============================
    // USER MENU
    // ==============================

    private static void showMenu() {

        System.out.println();
        System.out.println("==========================================");
        System.out.println("       CAMPUS LOST & FOUND MATCHER");
        System.out.println("==========================================");
        System.out.println("1. Report Lost Item");
        System.out.println("2. Report Found Item");
        System.out.println("3. View Items");
        System.out.println("4. Search Items");
        System.out.println("5. Find Possible Matches");
        System.out.println("6. Submit a Claim");
        System.out.println("7. Exit");
        System.out.println("------------------------------------------");
        System.out.print("Enter your choice: ");
    }

    // ==============================
    // REGISTER USER
    // ==============================

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
                    "Password must contain at least 4 characters."
            );
            return;
        }

        User user = new User(name, email, password);
        user.register();
    }

    // ==============================
    // USER LOGIN
    // ==============================

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
            System.out.println(
                    "Welcome to Campus Lost & Found Matcher."
            );

            return true;

        } else {

            System.out.println();
            System.out.println("Invalid email or password.");

            return false;
        }
    }

    // ==============================
    // ADMIN LOGIN
    // ==============================

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

    // ==============================
    // ADMIN MENU
    // ==============================

    private static void adminMenu() {

        Admin admin = new Admin();

        boolean adminRunning = true;

        while (adminRunning) {

            System.out.println();
            System.out.println("================================");
            System.out.println("          ADMIN PANEL");
            System.out.println("================================");
            System.out.println("1. View Reports");
            System.out.println("2. Review Claims");
            System.out.println("3. Update Claim Status");
            System.out.println("4. Update Item Status");
            System.out.println("5. Logout");
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
                    updateItemStatus();
                    break;

                case "5":
                    adminRunning = false;
                    System.out.println("Admin logged out.");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    // ==============================
    // UPDATE ITEM STATUS
    // ==============================

    private static void updateItemStatus() {

        System.out.println();
        System.out.println("========== UPDATE ITEM STATUS ==========");

        System.out.println("Item status management is available.");
        System.out.println("Use ItemStatusManager for status updates.");

        try {

            ItemStatusManager manager =
                    new ItemStatusManager();

            manager.showStatusOptions();

        } catch (Exception e) {

            System.out.println(
                    "Unable to open item status management."
            );
        }
    }

    // ==============================
    // REPORT LOST ITEM
    // ==============================

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

        if (description.trim().isEmpty()) {
            System.out.println("Description cannot be empty.");
            return;
        }

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

    // ==============================
    // REPORT FOUND ITEM
    // ==============================

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

        if (description.trim().isEmpty()) {
            System.out.println("Description cannot be empty.");
            return;
        }

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

    // ==============================
    // VIEW ITEMS
    // ==============================

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
                displayFile(
                        "data/lost_items.txt",
                        "LOST ITEMS"
                );
                break;

            case "2":
                displayFile(
                        "data/found_items.txt",
                        "FOUND ITEMS"
                );
                break;

            case "3":
                displayFile(
                        "data/lost_items.txt",
                        "LOST ITEMS"
                );

                displayFile(
                        "data/found_items.txt",
                        "FOUND ITEMS"
                );
                break;

            default:
                System.out.println("Invalid choice.");
        }
    }

    // ==============================
    // SUBMIT CLAIM
    // ==============================

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

        System.out.println();
        System.out.println(
                "Give some information that can help"
        );
        System.out.println(
                "verify that the item belongs to you."
        );

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

    // ==============================
    // DISPLAY FILE
    // ==============================

    private static void displayFile(
            String filePath,
            String title) {

        File file = new File(filePath);

        System.out.println();
        System.out.println("========== " + title + " ==========");

        if (!file.exists()) {
            System.out.println("No reports found.");
            return;
        }

        try (
                BufferedReader reader =
                        new BufferedReader(
                                new FileReader(file)
                        )
        ) {

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

                    if (data.length >= 7) {
                        System.out.println(
                                "Status     : " + data[6]
                        );
                    }

                    System.out.println(
                            "-----------------------------------------"
                    );

                    count++;
                }
            }

            if (count == 1) {
                System.out.println("No reports found.");
            }

        } catch (IOException e) {

            System.out.println(
                    "Unable to read the reports."
            );
        }
    }

    // ==============================
    // SHARED INPUT
    // ==============================

    public static String readInput() {
        return scanner.nextLine();
    }
}

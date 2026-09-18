import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Admin {

    private static final String ADMIN_EMAIL = "admin@campus.com";
    private static final String ADMIN_PASSWORD = "admin123";

    public static boolean login(String email, String password) {

        return ADMIN_EMAIL.equalsIgnoreCase(email)
                && ADMIN_PASSWORD.equals(password);
    }

    public void showReports() {

        System.out.println();
        System.out.println("========== ADMIN REPORTS ==========");

        displayFile("data/lost_items.txt", "LOST ITEMS");
        displayFile("data/found_items.txt", "FOUND ITEMS");
    }

    public void reviewClaims() {

        File file = new File("data/claims.txt");

        System.out.println();
        System.out.println("========== CLAIM REVIEW ==========");

        if (!file.exists()) {
            System.out.println("No claims have been submitted.");
            return;
        }

        try (BufferedReader reader =
                     new BufferedReader(new FileReader(file))) {

            String line;
            int number = 1;

            while ((line = reader.readLine()) != null) {

                String[] data = line.split("\\|", -1);

                if (data.length >= 5) {

                    System.out.println();
                    System.out.println("Claim #" + number);
                    System.out.println("Item      : " + data[0]);
                    System.out.println("Claimant  : " + data[1]);
                    System.out.println("Contact   : " + data[2]);
                    System.out.println("Proof     : " + data[3]);
                    System.out.println("Status    : " + data[4]);
                    System.out.println("----------------------------------");

                    number++;
                }
            }

            if (number == 1) {
                System.out.println("No claims found.");
            }

        } catch (IOException e) {

            System.out.println("Unable to read claims.");
        }
    }

    public void updateClaimStatus() {

        File file = new File("data/claims.txt");

        if (!file.exists()) {
            System.out.println();
            System.out.println("No claims available.");
            return;
        }

        List<String> claims = new ArrayList<>();

        try (BufferedReader reader =
                     new BufferedReader(new FileReader(file))) {

            String line;

            while ((line = reader.readLine()) != null) {
                claims.add(line);
            }

        } catch (IOException e) {

            System.out.println("Unable to read claims.");
            return;
        }

        if (claims.isEmpty()) {
            System.out.println();
            System.out.println("No claims available.");
            return;
        }

        reviewClaims();

        System.out.println();
        System.out.print("Enter claim number: ");

        String input = Main.readInput();

        int claimNumber;

        try {
            claimNumber = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
            return;
        }

        if (claimNumber < 1 || claimNumber > claims.size()) {
            System.out.println("Invalid claim number.");
            return;
        }

        System.out.println();
        System.out.println("1. APPROVE");
        System.out.println("2. REJECT");
        System.out.print("Choose action: ");

        String action = Main.readInput();

        String selectedClaim = claims.get(claimNumber - 1);

        String[] data = selectedClaim.split("\\|", -1);

        if (data.length < 5) {
            System.out.println("Invalid claim data.");
            return;
        }

        if (action.equals("1")) {

            data[4] = "APPROVED";

        } else if (action.equals("2")) {

            data[4] = "REJECTED";

        } else {

            System.out.println("Invalid action.");
            return;
        }

        claims.set(
                claimNumber - 1,
                String.join("|", data)
        );

        saveClaims(claims);

        System.out.println();
        System.out.println("Claim status updated successfully.");
        System.out.println("New status: " + data[4]);
    }

    private void saveClaims(List<String> claims) {

        try (PrintWriter writer =
                     new PrintWriter(new FileWriter("data/claims.txt"))) {

            for (String claim : claims) {
                writer.println(claim);
            }

        } catch (IOException e) {

            System.out.println("Unable to update claim.");
        }
    }

    private void displayFile(String filePath, String title) {

        File file = new File(filePath);

        System.out.println();
        System.out.println("========== " + title + " ==========");

        if (!file.exists()) {
            System.out.println("No reports found.");
            return;
        }

        try (BufferedReader reader =
                     new BufferedReader(new FileReader(file))) {

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

                    count++;
                }
            }

        } catch (IOException e) {

            System.out.println("Unable to read reports.");
        }
    }
}

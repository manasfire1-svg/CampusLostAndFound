import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class SearchService {

    public void searchItems() {

        System.out.println();
        System.out.println("========== SEARCH ITEMS ==========");

        System.out.print("Enter item name, category, or location: ");
        String keyword = Main.readInput().trim().toLowerCase();

        if (keyword.isEmpty()) {
            System.out.println("Search text cannot be empty.");
            return;
        }

        boolean found = false;

        found |= searchFile(
                "data/lost_items.txt",
                "LOST ITEMS",
                keyword
        );

        found |= searchFile(
                "data/found_items.txt",
                "FOUND ITEMS",
                keyword
        );

        if (!found) {
            System.out.println();
            System.out.println("No matching items found.");
        }
    }

    private boolean searchFile(
            String filePath,
            String title,
            String keyword) {

        File file = new File(filePath);

        if (!file.exists()) {
            return false;
        }

        boolean found = false;

        try (BufferedReader reader =
                     new BufferedReader(new FileReader(file))) {

            String line;

            while ((line = reader.readLine()) != null) {

                String[] data = line.split("\\|", -1);

                if (data.length < 6) {
                    continue;
                }

                String itemName = data[0].toLowerCase();
                String category = data[1].toLowerCase();
                String description = data[2].toLowerCase();
                String location = data[3].toLowerCase();

                if (itemName.contains(keyword)
                        || category.contains(keyword)
                        || description.contains(keyword)
                        || location.contains(keyword)) {

                    if (!found) {
                        System.out.println();
                        System.out.println(
                                "========== " + title + " =========="
                        );
                    }

                    found = true;

                    System.out.println();
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
                            "------------------------------------------"
                    );
                }
            }

        } catch (IOException e) {

            System.out.println(
                    "Unable to search item records."
            );
        }

        return found;
    }
}

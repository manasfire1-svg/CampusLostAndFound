import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FoundItem {

    private String itemName;
    private String category;
    private String description;
    private String location;
    private String date;
    private String contact;

    public FoundItem(String itemName, String category, String description,
                     String location, String date, String contact) {

        this.itemName = itemName;
        this.category = category;
        this.description = description;
        this.location = location;
        this.date = date;
        this.contact = contact;
    }

    public void saveItem() {

        try {
            File folder = new File("data");

            if (!folder.exists()) {
                folder.mkdir();
            }

            File file = new File(folder, "found_items.txt");

            try (PrintWriter writer = new PrintWriter(
                    new FileWriter(file, true))) {

                writer.println(
                itemName + "|" +
                category + "|" +
                description + "|" +
                location + "|" +
                date + "|" +
                contact + "|ACTIVE"
            );
            }

            System.out.println();
            System.out.println("Found item reported successfully.");
            System.out.println("The report has been saved.");

        } catch (IOException e) {

            System.out.println();
            System.out.println("Unable to save the report.");
            System.out.println("Please try again.");
        }
    }
}

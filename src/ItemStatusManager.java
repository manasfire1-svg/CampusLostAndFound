import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class ItemStatusManager {

    public void showStatusOptions() {

        System.out.println();
        System.out.println("========== ITEM STATUS ==========");
        System.out.println("1. Mark item as MATCHED");
        System.out.println("2. Mark item as RETURNED");
        System.out.println("3. Back");
        System.out.print("Enter your choice: ");

        String choice = Main.readInput();

        switch (choice) {

            case "1":
                updateItemStatus("MATCHED");
                break;

            case "2":
                updateItemStatus("RETURNED");
                break;

            case "3":
                break;

            default:
                System.out.println("Invalid choice.");
        }
    }

    private void updateItemStatus(String newStatus) {

        System.out.println();
        System.out.print("Enter item name: ");

        String itemName = Main.readInput();

        if (itemName.trim().isEmpty()) {
            System.out.println("Item name cannot be empty.");
            return;
        }

        boolean updated = false;

        updated = updateFile(
                "data/lost_items.txt",
                itemName,
                newStatus
        );

        if (!updated) {
            updated = updateFile(
                    "data/found_items.txt",
                    itemName,
                    newStatus
            );
        }

        if (updated) {
            System.out.println();
            System.out.println("Item status updated successfully.");
            System.out.println("New status: " + newStatus);
        } else {
            System.out.println();
            System.out.println("Item not found.");
        }
    }

    private boolean updateFile(
            String filePath,
            String itemName,
            String newStatus) {

        File file = new File(filePath);

        if (!file.exists()) {
            return false;
        }

        List<String> records = new ArrayList<>();
        boolean updated = false;

        try (BufferedReader reader =
                     new BufferedReader(new FileReader(file))) {

            String line;

            while ((line = reader.readLine()) != null) {

                String[] data = line.split("\\|", -1);

                if (data.length >= 6 &&
                        data[0].equalsIgnoreCase(itemName)) {

                    if (data.length >= 7) {
                        data[6] = newStatus;
                        line = String.join("|", data);
                    } else {
                        line = line + "|" + newStatus;
                    }

                    updated = true;
                }

                records.add(line);
            }

        } catch (IOException e) {

            System.out.println("Unable to read item data.");
            return false;
        }

        if (updated) {

            try (PrintWriter writer =
                         new PrintWriter(new FileWriter(file))) {

                for (String record : records) {
                    writer.println(record);
                }

            } catch (IOException e) {

                System.out.println("Unable to update item status.");
                return false;
            }
        }

        return updated;
    }
}

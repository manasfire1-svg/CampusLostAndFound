import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Claim {

    private String itemName;
    private String claimantName;
    private String contact;
    private String proof;

    public Claim(String itemName, String claimantName,
                 String contact, String proof) {

        this.itemName = itemName;
        this.claimantName = claimantName;
        this.contact = contact;
        this.proof = proof;
    }

    public void saveClaim() {

        try {

            File folder = new File("data");

            if (!folder.exists()) {
                folder.mkdir();
            }

            File file = new File(folder, "claims.txt");

            try (PrintWriter writer = new PrintWriter(
                    new FileWriter(file, true))) {

                writer.println(
                        itemName + "|" +
                        claimantName + "|" +
                        contact + "|" +
                        proof + "|PENDING"
                );
            }

            System.out.println();
            System.out.println("Claim submitted successfully.");
            System.out.println("Status: PENDING");
            System.out.println("The claim will be reviewed.");

        } catch (IOException e) {

            System.out.println();
            System.out.println("Unable to save the claim.");
        }
    }
}

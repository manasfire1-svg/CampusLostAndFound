import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class MatchingService {

    public void findMatches() {

        System.out.println();
        System.out.println("========== POSSIBLE MATCHES ==========");

        File lostFile = new File("data/lost_items.txt");
        File foundFile = new File("data/found_items.txt");

        if (!lostFile.exists() || !foundFile.exists()) {
            System.out.println("Not enough item reports for matching.");
            return;
        }

        boolean matchFound = false;

        try (BufferedReader lostReader =
                     new BufferedReader(new FileReader(lostFile))) {

            String lostLine;

            while ((lostLine = lostReader.readLine()) != null) {

                String[] lost = lostLine.split("\\|", -1);

                if (lost.length < 6) {
                    continue;
                }

                String lostStatus =
                        lost.length >= 7 ? lost[6] : "ACTIVE";

                if (!lostStatus.equalsIgnoreCase("ACTIVE")) {
                    continue;
                }

                try (BufferedReader foundReader =
                             new BufferedReader(
                                     new FileReader(foundFile))) {

                    String foundLine;

                    while ((foundLine =
                                   foundReader.readLine()) != null) {

                        String[] found =
                                foundLine.split("\\|", -1);

                        if (found.length < 6) {
                            continue;
                        }

                        String foundStatus =
                                found.length >= 7
                                        ? found[6]
                                        : "ACTIVE";

                        if (!foundStatus.equalsIgnoreCase("ACTIVE")) {
                            continue;
                        }

                        int score = calculateScore(lost, found);

                        if (score >= 2) {

                            matchFound = true;

                            System.out.println();
                            System.out.println(
                                    "Possible Match Found!"
                            );

                            System.out.println(
                                    "Lost Item  : " + lost[0]
                            );

                            System.out.println(
                                    "Found Item : " + found[0]
                            );

                            System.out.println(
                                    "Category   : " + lost[1]
                            );

                            System.out.println(
                                    "Lost Place : " + lost[3]
                            );

                            System.out.println(
                                    "Found Place: " + found[3]
                            );

                            System.out.println(
                                    "Match Score: " + score + "/4"
                            );

                            System.out.println(
                                    "------------------------------------------"
                            );
                        }
                    }
                }
            }

        } catch (IOException e) {

            System.out.println(
                    "Unable to process matching."
            );
        }

        if (!matchFound) {

            System.out.println();
            System.out.println(
                    "No strong matches found."
            );
        }
    }

    private int calculateScore(
            String[] lost,
            String[] found) {

        int score = 0;

        String lostName =
                lost[0].trim().toLowerCase();

        String foundName =
                found[0].trim().toLowerCase();

        String lostCategory =
                lost[1].trim().toLowerCase();

        String foundCategory =
                found[1].trim().toLowerCase();

        String lostDescription =
                lost[2].trim().toLowerCase();

        String foundDescription =
                found[2].trim().toLowerCase();

        String lostLocation =
                lost[3].trim().toLowerCase();

        String foundLocation =
                found[3].trim().toLowerCase();

        if (lostName.equals(foundName)
                || lostName.contains(foundName)
                || foundName.contains(lostName)) {

            score++;
        }

        if (lostCategory.equals(foundCategory)) {
            score++;
        }

        if (lostLocation.equals(foundLocation)) {
            score++;
        }

        if (lostDescription.contains(foundDescription)
                || foundDescription.contains(lostDescription)) {

            score++;
        }

        return score;
    }
}

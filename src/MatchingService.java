import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class MatchingService {

    public void findMatches() {

        File lostFile = new File("data/lost_items.txt");
        File foundFile = new File("data/found_items.txt");

        System.out.println();
        System.out.println("==========================================");
        System.out.println("          POSSIBLE ITEM MATCHES");
        System.out.println("==========================================");

        if (!lostFile.exists()) {
            System.out.println("No lost item reports available.");
            return;
        }

        if (!foundFile.exists()) {
            System.out.println("No found item reports available.");
            return;
        }

        boolean matchFound = false;

        try (
                BufferedReader lostReader = new BufferedReader(
                        new FileReader(lostFile));
                BufferedReader foundReader = new BufferedReader(
                        new FileReader(foundFile))
        ) {

            String lostLine;

            while ((lostLine = lostReader.readLine()) != null) {

                String[] lostData = lostLine.split("\\|", -1);

                if (lostData.length < 6) {
                    continue;
                }

                String foundLine;

                try (BufferedReader currentFoundReader =
                             new BufferedReader(new FileReader(foundFile))) {

                    while ((foundLine = currentFoundReader.readLine()) != null) {

                        String[] foundData = foundLine.split("\\|", -1);

                        if (foundData.length < 6) {
                            continue;
                        }

                        int score = calculateScore(lostData, foundData);

                        if (score >= 60) {

                            matchFound = true;

                            System.out.println();
                            System.out.println("------------------------------------------");
                            System.out.println("Possible Match Found");
                            System.out.println("------------------------------------------");

                            System.out.println("Lost Item  : " + lostData[0]);
                            System.out.println("Found Item : " + foundData[0]);
                            System.out.println("Category   : " + lostData[1]);
                            System.out.println("Lost From  : " + lostData[3]);
                            System.out.println("Found At   : " + foundData[3]);
                            System.out.println("Lost Date  : " + lostData[4]);
                            System.out.println("Found Date : " + foundData[4]);

                            System.out.println("Match Score: " + score + "%");

                            if (score >= 80) {
                                System.out.println("Result     : Strong Match");
                            } else {
                                System.out.println("Result     : Possible Match");
                            }
                        }
                    }
                }
            }

        } catch (IOException e) {

            System.out.println("Unable to check for matches.");
        }

        if (!matchFound) {
            System.out.println();
            System.out.println("No possible matches found.");
        }

        System.out.println();
    }

    private int calculateScore(String[] lost, String[] found) {

        int score = 0;

        // Category comparison - 25 points
        if (lost[1].trim().equalsIgnoreCase(found[1].trim())) {
            score += 25;
        }

        // Item name comparison - 30 points
        if (similarText(lost[0], found[0])) {
            score += 30;
        }

        // Location comparison - 25 points
        if (similarText(lost[3], found[3])) {
            score += 25;
        }

        // Date comparison - 20 points
        if (lost[4].trim().equalsIgnoreCase(found[4].trim())) {
            score += 20;
        }

        return score;
    }

    private boolean similarText(String first, String second) {

        String text1 = first.toLowerCase().trim();
        String text2 = second.toLowerCase().trim();

        if (text1.equals(text2)) {
            return true;
        }

        return text1.contains(text2) || text2.contains(text1);
    }
}

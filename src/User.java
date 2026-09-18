import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class User {

    private String name;
    private String email;
    private String password;

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public boolean register() {

        try {

            File folder = new File("data");

            if (!folder.exists()) {
                folder.mkdir();
            }

            File file = new File(folder, "users.txt");

            if (emailExists(file)) {
                System.out.println();
                System.out.println("An account with this email already exists.");
                return false;
            }

            try (PrintWriter writer = new PrintWriter(
                    new FileWriter(file, true))) {

                writer.println(
                        name + "|" +
                        email + "|" +
                        password
                );
            }

            System.out.println();
            System.out.println("Registration successful.");
            System.out.println("You can now log in.");

            return true;

        } catch (IOException e) {

            System.out.println();
            System.out.println("Unable to create the account.");
            return false;
        }
    }

    public static boolean login(String email, String password) {

        File file = new File("data/users.txt");

        if (!file.exists()) {
            return false;
        }

        try {

            java.io.BufferedReader reader =
                    new java.io.BufferedReader(
                            new java.io.FileReader(file));

            String line;

            while ((line = reader.readLine()) != null) {

                String[] data = line.split("\\|", -1);

                if (data.length >= 3) {

                    if (data[1].equalsIgnoreCase(email)
                            && data[2].equals(password)) {

                        reader.close();
                        return true;
                    }
                }
            }

            reader.close();

        } catch (IOException e) {
            System.out.println("Unable to read user data.");
        }

        return false;
    }

    private boolean emailExists(File file) {

        if (!file.exists()) {
            return false;
        }

        try {

            java.io.BufferedReader reader =
                    new java.io.BufferedReader(
                            new java.io.FileReader(file));

            String line;

            while ((line = reader.readLine()) != null) {

                String[] data = line.split("\\|", -1);

                if (data.length >= 2
                        && data[1].equalsIgnoreCase(email)) {

                    reader.close();
                    return true;
                }
            }

            reader.close();

        } catch (IOException e) {
            return false;
        }

        return false;
    }
}

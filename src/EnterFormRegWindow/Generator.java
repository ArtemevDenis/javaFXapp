package EnterFormRegWindow;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

public class Generator {
    private static final String LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String DIGITS = "0123456789";
    private static final String PUNCTUATION = "!@#$%&*()_+-=[]|,./?><";

    /**
     * This method will generate a password depending the use* properties you
     * define. It will use the categories with a probability. It is not sure
     * that all of the defined categories will be used.
     * @return a password that uses the categories you define when constructing
     * the object with a probability.
     */
    public String generatePassword(int length) {
        // Variables.
            StringBuilder password = new StringBuilder(length);

            // Collect the categories to use.
            List<String> charCategories = new ArrayList<>(4);
            charCategories.add(LOWER);
            charCategories.add(UPPER);
            charCategories.add(DIGITS);
            charCategories.add(PUNCTUATION);

        SecureRandom rand = new SecureRandom();
        // Build the password.
        for (int i = 0; i < length; i++) {
            String charCategory = charCategories.get(rand.nextInt(charCategories.size()));
            int position = rand.nextInt(charCategory.length());
            password.append(charCategory.charAt(position));
        }
        return new String(password);
    }


    public String generateCode() {

        // Variables.
        StringBuilder password = new StringBuilder(12);

        // Collect the categories to use.
        List<String> charCategories = new ArrayList<>(3);
        charCategories.add(LOWER);
        charCategories.add(UPPER);
        charCategories.add(DIGITS);

        SecureRandom rand = new SecureRandom();
        // Build the cod   e.
        for (int i = 0; i < 12; i++) {
            String charCategory = charCategories.get(rand.nextInt(charCategories.size()));
            int position = rand.nextInt(charCategory.length());
            password.append(charCategory.charAt(position));
        }
        return new String(password);
    }
}

package Utils;

import java.security.SecureRandom;

public class TextGeneration {
    public static String generatePassword(int length){
        SecureRandom secureRandom = new SecureRandom();
        StringBuilder generatedPassword = new StringBuilder();

        final String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        for (int i = 0; i < length; i++) {
            int randomCharacterIndex = secureRandom.nextInt(characters.length());
            generatedPassword.append(characters.charAt(randomCharacterIndex));
        }

        return generatedPassword.toString();
    }

    public static String generateID(int length){
        SecureRandom secureRandom = new SecureRandom();
        StringBuilder generatedID = new StringBuilder();

        final String numbers = "0123456789";

        for (int i = 0; i < length; i++) {
            int randomNumberIndex = secureRandom.nextInt(numbers.length());
            generatedID.append(numbers.charAt(randomNumberIndex));
        }

        return generatedID.toString();
    }
}

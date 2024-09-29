import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {
    private static final int MAX_ATTEMPTS = 5;
    private static int score = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean playAgain;

        do {
            playGame(scanner);
            System.out.print("Do you want to play again? (yes/no): ");
            String response = scanner.next();
            playAgain = response.equalsIgnoreCase("yes");
        } while (playAgain);

        System.out.println("Thanks for playing! Your total score: " + score);
        scanner.close();
    }

    private static void playGame(Scanner scanner) {
        Random random = new Random();
        int randomNumber = random.nextInt(100) + 1; // Random number between 1 and 100
        int attempts = 0;
        boolean guessedCorrectly = false;

        System.out.println("\n==== Welcome to the Number Guessing Game! ====");
        System.out.println("I have generated a number between 1 and 100. Try to guess it!");

        while (attempts < MAX_ATTEMPTS && !guessedCorrectly) {
            System.out.print("Enter your guess (Attempt " + (attempts + 1) + " of " + MAX_ATTEMPTS + "): ");
            int userGuess = scanner.nextInt();
            attempts++;

            if (userGuess == randomNumber) {
                System.out.println("Congratulations! You've guessed the correct number: " + randomNumber);
                guessedCorrectly = true;
                score += (MAX_ATTEMPTS - attempts + 1); // Score based on remaining attempts
            } else if (userGuess < randomNumber) {
                System.out.println("Too low! Try again.");
            } else {
                System.out.println("Too high! Try again.");
            }
        }

        if (!guessedCorrectly) {
            System.out.println("Sorry, you've used all attempts. The correct number was: " + randomNumber);
        }
    }
}


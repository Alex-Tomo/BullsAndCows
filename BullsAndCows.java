package BullsAndCows;

import java.util.ArrayList;
import java.util.Scanner;

public class BullsAndCows {

    private static int turn = 0;
    private static int bull = 0;
    private static int cow = 0;
    private static int length;
    private static String answer;
    private static ArrayList<Character> characterList = new ArrayList<>();

    public static void main(String[] args) {
        randomNumberGenerator();
        String guess = playerGuess();
        gameLog(guess);
    }

    public static void randomNumberGenerator() {
        try {
            System.out.println("Please, enter the secret code's length: ");
            Scanner scanner = new Scanner(System.in);
            StringBuilder sb = new StringBuilder();
            length = scanner.nextInt();
            if (length > 36 || length < 1) {
                System.out.println("Error: can't generate a secret number with a " +
                        "length of " + length + " because there aren't enough unique digits.");
                System.exit(0);
            }
            System.out.println("Input the number of possible symbols in the code:");
            int symbols = scanner.nextInt();
            if (symbols < length || symbols > 36) {
                System.out.println("error");
                System.exit(0);
            }

            for (int i = 0; i < length; i++) {
                int whichOne = 0;
                if (symbols > 10) {
                    double tempOne = Math.random() * 2;
                    whichOne = (int) Math.floor(tempOne);
                }
                if (whichOne == 0) {
                    double x = (Math.random() * 10);
                    int y = (int) Math.floor(x);
                    char number = (char) (y + 48);
                    while (characterList.contains(number)) {
                        x = (Math.random() * 10);
                        y = (int) Math.floor(x);
                        number = (char) (y + 48);
                    }
                    characterList.add(number);
                    sb.append(number);

                } else {
                    double x = (Math.random() * (symbols - 10));
                    int y = (int) Math.floor(x);
                    int tempTwo = y + 97;
                    char character = (char) tempTwo;
                    while (characterList.contains(character)) {
                        x = (Math.random() * (symbols - 10));
                        y = (int) Math.floor(x);
                        tempTwo = y + 97;
                        character = (char) tempTwo;
                    }
                    characterList.add(character);
                    sb.append(character);
                }
            }
            System.out.println("The secret is prepared: ");
            for (int i = 0; i < length; i++) {
                System.out.print("*");
            }
            if (symbols < 11)
                System.out.print(" 0-" + (symbols - 1) + "\n");
            else {
                int temp = symbols + 86;
                char c = (char) temp;
                System.out.print(" 0-9, a-" + c + "\n");
            }
        } catch (Exception ex) {
            System.out.println("error");
            System.exit(0);
        }
    }

    public static String playerGuess() {
        Scanner scanner = new Scanner(System.in);
        String guess = scanner.nextLine();
        for(int i = 0; i < guess.length(); i++) {
            if(guess.charAt(i) < (char) 48 || (guess.charAt(i) > (char) 57 &&
                    guess.charAt(i) < (char) 97) || guess.charAt(i) > (char) 122) {
                System.out.println("error");
                System.exit(0);
            }
        }
        return guess;
    }

    public static void checkGuess(String guess) {
        bull = 0;
        cow = 0;

        for(int i = 0; i < guess.length(); i++) {
            int x = guess.charAt(i);
            int y = characterList.get(i);
            if(x == y) {
                bull++;
            } else if(characterList.contains(guess.charAt(i))) {
                cow++;
            }
        }
    }

    public static void gameLog(String guess) {
        turn++;
        checkGuess(guess);
        StringBuilder theAnswer = new StringBuilder();
        for(int i = 0; i < characterList.size(); i++) {
            theAnswer.append(characterList.get(i));
        }

        System.out.printf("Turn %d:\n", turn);
        System.out.println(guess);
        if(bull == length) {
            System.out.println("Congratulations! You guessed the secret code.");
            System.out.println("Grade: " + bull + " bulls");
            return;
        } else if (bull > 0 && cow > 0) {
            System.out.println("Grade: " + bull + " bull(s) and " + cow + " cow(s)");
            guess = playerGuess();
            gameLog(guess);
        } else if (bull > 0 && cow == 0) {
            System.out.println("Grade: " + bull + " bull(s)");
            guess = playerGuess();
            gameLog(guess);
        } else if (cow > 0 && bull == 0) {
            System.out.println("Grade: " + cow + " cow(s)");
            guess = playerGuess();
            gameLog(guess);

        } else {
            System.out.println("Grade: None");
            guess = playerGuess();
            gameLog(guess);
        }
    }
}

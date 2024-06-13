package com.tech;

import java.util.Random;
import java.util.Scanner;

public class NumberGame {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        boolean playAgain = true;
        int totalScore = 0;

        while (playAgain) {
            int numberToGuess = random.nextInt(100) + 1;
            int attemptsLeft = 10;
            boolean correctGuess = false;

            System.out.println("Welcome to the Number Game!");
            System.out.println("I have generated a number between 1 and 100.");
            System.out.println("You have 10 attempts to guess the correct number.");

            while (attemptsLeft > 0 && !correctGuess) {
                System.out.print("Enter your guess: ");
                int userGuess = scanner.nextInt();

                if (userGuess == numberToGuess) {
                    System.out.println("Congratulations! You guessed the correct number.");
                    correctGuess = true;
                    totalScore += attemptsLeft; 
                } else if (userGuess < numberToGuess) {
                    System.out.println("Your guess is too low.");
                } else {
                    System.out.println("Your guess is too high.");
                }

                attemptsLeft--;
                if (attemptsLeft > 0 && !correctGuess) {
                    System.out.println("You have " + attemptsLeft + " attempts left.");
                }
            }

            if (!correctGuess) {
                System.out.println("Sorry, you've used all your attempts. The correct number was " + numberToGuess);
            }

            System.out.print("Do you want to play another round? (yes/no): ");
            String response = scanner.next();
            playAgain = response.equalsIgnoreCase("yes");

            if (!playAgain) {
                System.out.println("Thank you for playing! Your total score is: " + totalScore);
            }
        }

        scanner.close();
    

	}

}

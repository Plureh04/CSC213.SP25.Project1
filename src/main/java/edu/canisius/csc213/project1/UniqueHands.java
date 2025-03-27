package edu.canisius.csc213.project1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UniqueHands {

    public static void main(String[] args) {
        int[] deckSizes = {24, 28}; // Deck sizes to test
        int[] handSizes = {6, 7}; // Hand sizes to test
        int trials = 5; // Number of trials per deck-hand combination

        System.out.println("Deck Simulation: How long to see every possible hand?");
        System.out.println("------------------------------------------------------");

        for (int deckSize : deckSizes) {
            for (int handSize : handSizes) {
                System.out.println("Deck Size: " + deckSize + " | Hand Size: " + handSize);
                for (int trial = 1; trial <= trials; trial++) {
                    runTrial(deckSize, handSize, trial);
                }
            }
        }
    }

    private static void runTrial(int deckSize, int handSize, int trial) {
        long startTime = System.nanoTime(); // Start timing
        int attempts = 0;
        int totalUniqueHands = calculateTotalUniqueHands(deckSize, handSize);
        Set<Set<Card>> uniqueHands = new HashSet<>();
        int maxAttempts = 10_000_000; // Set a maximum number of attempts

        while (uniqueHands.size() < totalUniqueHands && attempts < maxAttempts) {
            attempts++;
            Deck deck = new Deck(deckSize);
            deck.shuffle();

            List<Card> hand = new ArrayList<>();
            for (int i = 0; i < handSize; i++) {
                hand.add(deck.draw());
            }
            uniqueHands.add(new HashSet<>(hand));

            // Print progress every 100,000 attempts
            if (attempts % 100_000 == 0) {
                double coverage = (double) uniqueHands.size() / totalUniqueHands * 100;
                int needed = totalUniqueHands - uniqueHands.size();
                System.out.printf("Progress: %.2f%% coverage after %,d attempts (Unique Hands: %,d / %,d | Needed: %,d)%n",
                        coverage, attempts, uniqueHands.size(), totalUniqueHands, needed);
            }
        }

        long endTime = System.nanoTime(); // End timing
        double timeTaken = (endTime - startTime) / 1_000_000_000.0; // Convert nanoseconds to seconds

        if (uniqueHands.size() == totalUniqueHands) {
            System.out.printf("100.00%% coverage reached after %,d attempts (Unique Hands: %,d / %,d | Needed: 0)%n",
                    attempts, uniqueHands.size(), totalUniqueHands);
        } else {
            System.out.printf("Stopped after %,d attempts (Unique Hands: %,d / %,d | Needed: %,d)%n",
                    attempts, uniqueHands.size(), totalUniqueHands, totalUniqueHands - uniqueHands.size());
        }
        System.out.printf("Deck Size: %d | Hand Size: %d | Trial %d | Attempts: %,d | Time: %.3f sec%n",
                deckSize, handSize, trial, attempts, timeTaken);
    }

    /**
     * Calculates the total number of unique hands for a given deck size and hand size.
     *
     * @param deckSize The number of cards in the deck.
     * @param handSize The number of cards in a hand.
     * @return The total number of unique hands.
     */
    public static int calculateTotalUniqueHands(int deckSize, int handSize) {
        if (handSize > deckSize) {
            return 0;
        }
        long numerator = 1;
        long denominator = 1;
        for (int i = 1; i <= handSize; i++) {
            numerator *= (deckSize - handSize + i);
            denominator *= i;
        }
        return (int) (numerator / denominator);
    }

    /**
     * Simulates drawing hands until all unique hands are seen and returns the number of attempts.
     *
     * @param deckSize The number of cards in the deck.
     * @param handSize The number of cards in a hand.
     * @return The number of attempts to see all unique hands.
     */
    public static int countAttemptsToSeeAllHands(int deckSize, int handSize) {
        int attempts = 0;
        int totalUniqueHands = calculateTotalUniqueHands(deckSize, handSize);
        Set<Set<Card>> uniqueHands = new HashSet<>();

        while (uniqueHands.size() < totalUniqueHands) {
            attempts++;
            Deck deck = new Deck(deckSize);
            deck.shuffle();

            List<Card> hand = new ArrayList<>();
            for (int i = 0; i < handSize; i++) {
                hand.add(deck.draw());
            }
            uniqueHands.add(new HashSet<>(hand));
        }

        return attempts;
    }
}

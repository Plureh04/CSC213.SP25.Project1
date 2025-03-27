package edu.canisius.csc213.project1;

/**
 * Represents a playing card with a suit and rank.
 */
import java.util.Objects;

public class Card {

    public enum Suit { HEARTS, DIAMONDS, CLUBS, SPADES }
    public enum Rank {
        TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN,
        JACK, QUEEN, KING, ACE
    }

    // Fields
    private final Suit suit;
    private final Rank rank;

    // Constructor
    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    // Getters
    public Suit getSuit() {
        return suit;
    }

    public Rank getRank() {
        return rank;
    }

    // toString()
    @Override
    public String toString() {
        return rank + " of " + suit;
    }

    // Simplified equals()
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // Check for reference equality
        if (!(obj instanceof Card)) return false; // Check for null and class type
        Card other = (Card) obj; // Cast to Card
        return this.suit == other.suit && this.rank == other.rank; // Compare fields
    }

    // hashCode()
    @Override
    public int hashCode() {
        return Objects.hash(suit, rank);
    }
}

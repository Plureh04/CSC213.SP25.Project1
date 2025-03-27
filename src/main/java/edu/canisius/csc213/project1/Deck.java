package edu.canisius.csc213.project1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

public class Deck {
    private final List<Card> cards;

    /**
     * Creates a deck with a given size.
     * The size must be a multiple of 4 and at most 52.
     *
     * @param size The number of cards in the deck.
     * @throws IllegalArgumentException if size is invalid.
     */
    public Deck(int size) {
        if (size % 4 != 0 || size > 52 || size < 4) {
            throw new IllegalArgumentException("Size must be a multiple of 4 and at most 52.");
        }

        cards = new ArrayList<>();
        for (Card.Suit suit : Card.Suit.values()) {
            for (Card.Rank rank : Card.Rank.values()) {
                if (cards.size() >= size) {
                    return; // Stop when the deck reaches the desired size
                }
                cards.add(new Card(suit, rank));
            }
        }
    }

    /**
     * Shuffles the deck.
     */
    public void shuffle() {
        Collections.shuffle(cards);
    }

    /**
     * Draws the top card from the deck.
     *
     * @return The drawn card.
     * @throws NoSuchElementException if the deck is empty.
     */
    public Card draw() {
        if (cards.isEmpty()) {
            throw new NoSuchElementException("The deck is empty.");
        }
        return cards.remove(0);
    }

    /**
     * Gets the number of remaining cards in the deck.
     *
     * @return The number of cards left.
     */
    public int size() {
        return cards.size();
    }
}

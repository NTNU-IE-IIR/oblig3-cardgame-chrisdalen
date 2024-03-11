package no.ntnu.idatx2003.oblig3.cardgame;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Represents a hand of cards. A hand of cards is a collection of playing cards.
 * Capable of analyzing the hand.
 */
public class HandOfCards {

  private ArrayList<PlayingCard> hand;

  /**
   * Constructs a new HandOfCards by creating a DeckOfCards and picking n amount of cards from it.
   */
  public HandOfCards(int n) {
    DeckOfCards deck = new DeckOfCards();
    this.hand = deck.dealHand(n);
  }

  /**
   * Checks the hand for a flush. A flush is when 5 or more cards on the hand has the same suit.
   *
   * @return {@code true} if the hand has a flush, {@code false} otherwise.
   */
  public boolean hasFlush() {
    int hearts = 0;
    int spades = 0;
    int diamonds = 0;
    int clubs = 0;

    for (PlayingCard card : this.hand) {
      if (card.getSuit() == 'H') {
        hearts++;
      } else if (card.getSuit() == 'S') {
        spades++;
      } else if (card.getSuit() == 'D') {
        diamonds++;
      } else if (card.getSuit() == 'C') {
        clubs++;
      }
    }
    return hearts >= 5 || spades >= 5 || diamonds >= 5 || clubs >= 5;
  }

  /**
   * Checks the hand for a straight. A straight is when 5 or more cards on the hand have consecutive
   * face values, e.g., A, 2, 3, 4, 5 or 10, J, Q, K, A.
   *
   * @return {@code true} if the hand has a straight, {@code false} otherwise.
   */
  public boolean hasStraight() {
    boolean hasStraight = false;

    int[] faceValues = new int[14];
    for (PlayingCard card : this.hand) {
      faceValues[card.getFace()]++;
    }

    int count = 0;
    for (int i = 1; i < faceValues.length; i++) {
      if (faceValues[i] > 0) {
        count++;
        if (count >= 5) {
          hasStraight = true; // Straight found
        }
      } else {
        count = 0; // Reset count when a gap is encountered
      }
    }

    // Check for the special case of A, 2, 3, 4, 5
    if (count == 4 && faceValues[1] > 0) {
      hasStraight = true; // Straight found
    }

    return hasStraight;
  }


  /**
   * Returns an iterator over the hand of cards.
   *
   * @return an iterator over the hand of cards.
   */
  public Iterator<PlayingCard> getIterator() {
    return this.hand.iterator();
  }






}

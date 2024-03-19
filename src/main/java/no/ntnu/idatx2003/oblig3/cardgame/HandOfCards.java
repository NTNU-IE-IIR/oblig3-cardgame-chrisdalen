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
   * Sums the face values of the cards in the hand.
   */
  public int sumOfFaceValues() {
    int sum = 0;
    for (PlayingCard card : this.hand) {
      sum += card.getFace();
    }
    return sum;
  }

  /**
   * Returns the amount of cards in the hand.
   */
  public int getAmountOfCards() {
    return this.hand.size();
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

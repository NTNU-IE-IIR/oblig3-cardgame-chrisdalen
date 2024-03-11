package no.ntnu.idatx2003.oblig3.cardgame;

import java.util.ArrayList;
import java.util.Random;

/**
 * Represents a deck of cards with 52 playing cards.
 */
public class DeckOfCards {

  private ArrayList<PlayingCard> deck;
  private Random randomAmount = new Random();

  /**
   * Constructs a new DeckOfCards complete with 52 playing cards.
   */
  public DeckOfCards() {
    this.deck = new ArrayList<PlayingCard>();
    for (char suit : new char[] {'S', 'H', 'D', 'C'}) { //For each suit
      for (int face = 1; face <= 13; face++) { //For each face
        this.deck.add(new PlayingCard(suit, face));
      }
    }
  }

  /**
   * Picks n amount of cards from the deck and adds them to the hand.
   * The cards are pulled at random from the deck. No card may be picked more than once.
   *
   * @param n the amount of cards to pick.
   *          Must be a number between 5 and 52 (including both values).
   * @return the hand of cards.
   * @throws IllegalArgumentException if n is not a number between 5 and 52.
   */
  public ArrayList<PlayingCard> dealHand(int n) {
    //Initialize the hand
    ArrayList<PlayingCard> hand = new ArrayList<PlayingCard>();

    if (n < 5 || n > 52) {
      throw new IllegalArgumentException("Parameter n must be a number between 5 and 52");
    }

    for (int i = 0; i < n; i++) {
      //Generates random number within the size of the deck
      int randomIndex = randomAmount.nextInt(this.deck.size());
      //Gets the card at the random index and adds it to the hand
      hand.add(this.deck.get(randomIndex));
      //Removes the card from the deck, so it may not be picked again
      this.deck.remove(randomIndex);
    }
    return hand;
  }
}


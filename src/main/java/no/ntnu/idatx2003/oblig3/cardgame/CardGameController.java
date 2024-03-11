package no.ntnu.idatx2003.oblig3.cardgame;

import java.util.Iterator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Represents the controller for the card game.
 * The controller is responsible for tying the application logic (model) to the GUI (view).
 */
public class CardGameController {
  private CardGame game;
  private HandOfCards hand;
  private Iterator<PlayingCard> handIterator;

  /**
   * Constructs a new CardGameController
   */
  public CardGameController(CardGame game) {
    this.game = game;
  }

  /**
   * Deals a new hand of cards and updates the view.
   */
  public void executeDealNewHand() {
    //Gets the amount of cards to deal from the user.
    int n = game.promptUserForAmountOfCards();
    //Deals a new hand with the amount of cards specified by user.
    this.hand = new HandOfCards(n);
    this.handIterator = this.hand.getIterator();
    this.game.updateCardsView();
  }

  /**
   * Checks the hand.
   */
  public void executeCheckHand() {
    if (this.hand == null) {
      throw new IllegalArgumentException("No cards have been dealt yet.");
    }
  }

  /**
   * Passes on the iterator to the view.
   *
   * @return the iterator over the hand of cards.
   */
  public Iterator<PlayingCard> getHandIterator() {
    return this.handIterator;
  }

}

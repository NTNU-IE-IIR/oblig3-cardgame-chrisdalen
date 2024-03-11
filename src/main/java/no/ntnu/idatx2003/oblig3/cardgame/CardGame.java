package no.ntnu.idatx2003.oblig3.cardgame;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/**
 * Represents the GUI for the card game.
 */
public class CardGame extends Application {
  private HandOfCards hand;
  private Iterator<PlayingCard> handIterator;

  /**
   * Constructs a new CardGame by creating a new hand of cards.
   */
  public CardGame(int n) {
    this.hand = new HandOfCards(n);
    this.handIterator = hand.getIterator();
  }

  /**
   * Sets up the stage for the GUI.
   */
  public void start(Stage stage) {

    BorderPane rootPane = new BorderPane();
    //Shows the cards in the main window.
    FlowPane cardsContainer = new FlowPane();
    //Holds the buttons for the user to interact with.
    VBox userChoices = this.setUpUserChoices();
    //Holds the boxes that displays information to the user.
    HBox textBoxes = new HBox();

    rootPane.setCenter(cardsContainer);
    rootPane.setRight(userChoices);
    rootPane.setBottom(textBoxes);







    //Sets up the container for the cards.
    cardsContainer.setHgap(10);
    cardsContainer.setVgap(10);
    cardsContainer.setAlignment(Pos.CENTER);

    //TODO: Make this update each time a new hand is dealt. Maybe with an if(updated) do this, method.
    while (handIterator.hasNext()) {
      PlayingCard card = handIterator.next();
      String imageName = card.getAsString() + ".png";
      String imagePath = "images/" + imageName;

      Image cardImage = new Image(
          Objects.requireNonNull(getClass().getResourceAsStream(imagePath))); //Code by ChatGPT
      ImageView imageView = new ImageView(cardImage);
      cardsContainer.getChildren().add(imageView);
    }




  }

  /**
   * Sets up the buttons for the user to interact with, and returns them in a VBox.
   *
   * @return A VBox containing the buttons.
   */
  private VBox setUpUserChoices() {
    VBox userChoices = new VBox();

    Button dealNewHand = new Button("Deal new hand");
    Button checkHand = new Button("Check hand");

    userChoices.getChildren().addAll(dealNewHand, checkHand);

    return userChoices;
  }

  /**
   * Sets up the text fields which provides information to the user about their hand.
   *
   * @return A HBox containing the text fields and labels.
   */
  private HBox setUpTextBoxes() {
    HBox textBoxes = new HBox();

    //Checks for flush
    Label flushLabel = new Label("Flush: ");
    if (hand.hasFlush()) {
      flushLabel.setText("Flush: Yes");
    } else {
      flushLabel.setText("Flush: No");
    }

    //Checks for straight
    Label straightLabel = new Label("Straight: ");



    textBoxes.getChildren().addAll(flushLabel, flushLabel);
    return textBoxes;
  }





}

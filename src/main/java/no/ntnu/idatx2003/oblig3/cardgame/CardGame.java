package no.ntnu.idatx2003.oblig3.cardgame;

import java.util.Iterator;
import java.util.Objects;
import java.util.Optional;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
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
   * Constructs a new CardGame.
   */
  public CardGame() {
    //Intentionally empty
  }

  /**
   * Launches the application.
   */
  public static void appMain(String[] args) {
    launch();
  }

  /**
   * Sets up the stage for the GUI.
   */
  public void start(Stage stage) {

    BorderPane rootPane = new BorderPane();
    //Shows the cards in the main window.
    FlowPane cardsContainer = this.setUpCardContainer();
    //Holds the buttons for the user to interact with.
    VBox userChoices = this.setUpUserChoices();
    //Holds fields that give the user information about their hand.
    HBox textBoxes = this.setUpTextBoxes();

    rootPane.setCenter(cardsContainer);
    rootPane.setRight(userChoices);
    rootPane.setBottom(textBoxes);

    //Displays the main window.
    Scene scene = new Scene(rootPane, 800, 600);
    stage.setScene(scene);
    stage.setTitle("Card Game");
    stage.show();
  }

  /**
   * Sets up the card container for the main window.
   *
   * @return A FlowPane containing the cards.
   */
  private FlowPane setUpCardContainer() {
    FlowPane cardsContainer = new FlowPane();

    //Sets the spacing between the cards.
    cardsContainer.setHgap(10);
    cardsContainer.setVgap(10);
    cardsContainer.setAlignment(Pos.CENTER);

    if (this.handIterator != null) { //Checks that the iterator has been initialized.

      //TODO: Make this update each time a new hand is dealt. Maybe with an if(updated) do this, method.
      while (handIterator.hasNext()) {
        PlayingCard card = handIterator.next();
        String imageName = card.getAsString() + ".png";
        String imagePath = "images/" + imageName;

        Image cardImage = new Image(
            getClass().getResourceAsStream(imagePath)); //Code by ChatGPT
        ImageView imageView = new ImageView(cardImage);
        cardsContainer.getChildren().add(imageView);
      }
    }
    return cardsContainer;
  }

  /**
   * Sets up the buttons for the user to interact with, and returns them in a VBox.
   *
   * @return A VBox containing the buttons.
   */
  private VBox setUpUserChoices() {
    VBox userChoices = new VBox();

    Button dealNewHand = new Button("Deal new hand");
    dealNewHand.setOnAction(event -> promptForNumberOfCards());
    Button checkHand = new Button("Check hand");
    
    //TODO: Check hand should refresh the text boxes.

    userChoices.getChildren().addAll(dealNewHand, checkHand);

    return userChoices;
  }


  /**
   * Prompts the user for the number of cards they want in their new hand.
   */
  private void promptForNumberOfCards() {
    TextInputDialog dialog = new TextInputDialog();
    dialog.setTitle("Deal new hand");
    dialog.setHeaderText(null);
    dialog.setContentText("Enter the number of cards for the new hand:");

    Optional<String> result = dialog.showAndWait();

    result.ifPresent(s -> {
      try {
        int numberOfCards = Integer.parseInt(s);
        dealNewHand(numberOfCards);
      } catch (NumberFormatException e) {
        System.out.println("Invalid input. Please enter a valid integer.");
      }
    });
  }

  /**
   * Creates a new hand of cards with n cards when Deal new hand is pressed.
   */
  private void dealNewHand(int n) {
    this.hand = new HandOfCards(n);
    this.handIterator = hand.getIterator();
  }

  /**
   * Sets up the text fields which provides information to the user about their hand.
   *
   * @return A HBox containing the text fields and labels.
   */
  private HBox setUpTextBoxes() {
    HBox textBoxes = new HBox();

    //Sets the flush label
    Label flushLabel = new Label();
    if (hand != null) {
      if (hand.hasFlush()) {
        flushLabel.setText("Flush: Yes");
      } else {
        flushLabel.setText("Flush: No");
      }
    } else {
      flushLabel.setText("Flush: -");
    }

    //Sums the face values of the cards in the hand.
    Label sumLabel = new Label();
    if (hand != null) {
      sumLabel.setText("Sum of face values: " + hand.sumOfFaceValues());
    } else {
      sumLabel.setText("Sum of face values: -");
    }

    textBoxes.getChildren().addAll(flushLabel, sumLabel);

    return textBoxes;
  }





}

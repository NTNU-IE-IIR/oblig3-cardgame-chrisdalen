package no.ntnu.idatx2003.oblig3.cardgame;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;
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
  private CardGameController controller;

  /**
   * Constructs a new CardGame.
   */
  public CardGame() {
    this.controller = new CardGameController(this);
  }

  /**
   * Sets up and displays the stage.
   *
   * @param stage the stage to display.
   */
  public void start(Stage stage) {

    BorderPane rootPane = new BorderPane();

    FlowPane cardsContainer = this.setUpCardContainer();
    VBox userChoices = this.setUpUserChoices();
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
   * @return A FlowPane containing the cards. If the hand has not been dealt, an empty FlowPane is
   *          returned.
   */
  private FlowPane setUpCardContainer() {
    FlowPane cardsContainer = new FlowPane();

    //Sets spacing and alignment for the cards.
    cardsContainer.setHgap(10);
    cardsContainer.setVgap(10);
    cardsContainer.setAlignment(Pos.CENTER);

    return cardsContainer;
  }

  /**
   * Sets up the buttons for the user to interact with, and returns them in a VBox.
   *
   * @return A VBox containing the buttons.
   */
  private VBox setUpUserChoices() {
    VBox userChoices = new VBox();

    Button newHand = new Button("Deal new hand");
    newHand.setOnAction(event -> this.controller.executeDealNewHand());

    Button checkHand = new Button("Check hand");
    checkHand.setOnAction(event -> this.controller.executeCheckHand());

    userChoices.getChildren().addAll(newHand, checkHand);

    return userChoices;
  }

  /**
   * Sets up the text fields which provides information to the user about their hand.
   *
   * @return A HBox containing the text fields and labels.
   */
  private HBox setUpTextBoxes() {
    HBox textBoxes = new HBox();

    Label flushLabel = new Label(); //TODO: Make labels update when executeCheckHand is called.
    Label sumLabel = new Label();

    textBoxes.getChildren().addAll(flushLabel, sumLabel);

    return textBoxes;
  }

  /**
   * Prompts the user for the amount of cards they want to deal.
   *
   * @return the amount of cards the user wants to deal.
   */
  public int promptUserForAmountOfCards() {
    TextInputDialog dialog = new TextInputDialog();
    dialog.setTitle("Amount of cards");
    dialog.setHeaderText("Enter the amount of cards you want to deal.");
    dialog.setContentText("Amount:");

    Optional<String> result = dialog.showAndWait();

    int parsedInput = 0;

    try {
      parsedInput = Integer.parseInt(result.get());
    } catch (NumberFormatException e) {
      System.out.println("Invalid input. Please enter a number."); //TODO: Not sout but a proper error message.
    }
    return parsedInput;
  }

  /**
   * Updates the view after a new hand of cards is dealt.
   */
  public void updateCardsView() {
    FlowPane cardsContainer = this.setUpCardContainer();
    this.handIterator = this.controller.getHandIterator();

    while (this.handIterator.hasNext()) {
      //Code by ChatGPT to display images in JavaFX:
      PlayingCard card = this.handIterator.next();
      String imageName = card.getAsString() + ".png";
      Path imagePath = Path.of("resources", imageName);

      try {
        Image cardImage = new Image(Files.newInputStream(imagePath));
        ImageView imageView = new ImageView(cardImage);
        cardsContainer.getChildren().add(imageView);
        //End of code by ChatGPT
      } catch (Exception e) {
        System.out.println("Could not load image: " + imageName); //TODO: Handle appropriately
      }
    }
  }

  /**
   * Launches the application.
   */
  public static void appMain(String[] args) {
    launch();
  }


}


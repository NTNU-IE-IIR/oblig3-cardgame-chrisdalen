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
import javafx.scene.control.TextField;
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
  private Iterator<PlayingCard> handIterator;
  private CardGameController controller;
  private FlowPane cardsContainer;
  private TextField flushField;
  private TextField sumField;

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

    //Sets up all the components of the main window.
    VBox userChoices = this.setUpUserButtons();
    VBox textBoxes = this.setUpTextBoxes();

    //Initializes empty container for the cards.
    this.cardsContainer = new FlowPane();

    //Adds the components to the main window.
    rootPane.setCenter(cardsContainer);
    rootPane.setRight(userChoices);
    rootPane.setBottom(textBoxes);

    //Adds all components to the stage and displays it.
    Scene scene = new Scene(rootPane, 800, 600);
    stage.setScene(scene);
    stage.setTitle("Card Game");
    stage.show();
  }

  /**
   * Sets up the buttons for the user to interact with, and returns them in a VBox.
   *
   * @return A VBox containing the buttons.
   */
  private VBox setUpUserButtons() {
    VBox userButtons = new VBox();

    Button newHand = new Button("Deal new hand");
    newHand.setOnAction(event -> {
      try {
        this.controller.executeDealNewHand();
      } catch (Exception e) {
        System.out.println(e.getMessage()); //Should be changed to display in the GUI.
      }
    });

    Button checkHand = new Button("Check hand");
    checkHand.setOnAction(event -> {
      try {
        this.controller.executeCheckHand();
      } catch (Exception e) {
        System.out.println(e.getMessage()); //Should be changed to display in the GUI.
      }
    });

    userButtons.getChildren().addAll(newHand, checkHand);

    return userButtons;
  }

  /**
   * Sets up the text fields which provides information to the user about their hand.
   *
   * @return A VBox containing the text fields and labels.
   */
  private VBox setUpTextBoxes() {
    VBox textBoxes = new VBox();
    HBox flushBox = new HBox();
    HBox sumBox = new HBox();

    Label flushLabel = new Label("Flush: ");
    Label sumLabel = new Label("Sum of faces: ");

    this.flushField = new TextField();
    this.sumField = new TextField();
    //Sets the text fields to be uneditable.
    flushField.setEditable(false);
    sumField.setEditable(false);

    flushBox.getChildren().addAll(flushLabel, flushField);
    sumBox.getChildren().addAll(sumLabel, sumField);
    textBoxes.getChildren().addAll(flushBox, sumBox);

    return textBoxes;
  }

  /**
   * Updates the text boxes when a hand is checked.
   */
  public void updateTextBoxes(boolean hasFlush, int sum) {
    try {
      flushField.setText(Boolean.toString(hasFlush));
      sumField.setText(Integer.toString(sum));
    } catch (Exception e) {
      System.out.println(e.getMessage()); //TODO: Idk about this, it should never actually happen.
    }


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
      System.out.println("Invalid input. Please enter a number."); //Should be changed to display in the GUI.
    }
    return parsedInput;
  }

  /**
   * Updates the view after a new hand of cards is dealt.
   */
  public void updateCardsView() {
    this.handIterator = this.controller.getHandIterator();
    this.cardsContainer.getChildren().clear();

    cardsContainer.setHgap(10);
    cardsContainer.setVgap(10);
    cardsContainer.setAlignment(Pos.CENTER);

    while (this.handIterator.hasNext()) {

      PlayingCard card = this.handIterator.next();
      String imageName = card.getAsString();
      String imagePath = imageName + ".png";

      try {
        Image cardImage = new Image(imagePath);
        ImageView imageView = new ImageView(cardImage);
        imageView.setFitHeight(200);
        imageView.setFitWidth(150);
        cardsContainer.getChildren().add(imageView);

      } catch (Exception e) {
        System.out.println("Could not load image: " + imageName); //Should be changed to display in the GUI.
        System.out.println("Exception message: " + e.getMessage());
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


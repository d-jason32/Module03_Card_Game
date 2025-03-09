package edu.farmingdale.module03_card_game;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class GameController {
    @FXML
    private TextField solutionLabel;

    @FXML
    private Label output;

    @FXML
    private ImageView emoji;

    @FXML
    private ImageView card1;

    @FXML
    private ImageView card2;

    @FXML
    private ImageView card3;

    @FXML
    private ImageView card4;

    public Deck newDeck;

    @FXML
    private TextField enteredString;

    @FXML
    void closeScreen(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void refreshButton(ActionEvent event) {
        // When refresh is pressed, a new deck of 4 cards is initialized.
        newDeck = new Deck();

        // Every time refresh is clicked, the emoji is turned to the thinking emoji.
        emoji.setImage(new Image(getClass().getResourceAsStream("/png/thinking_emoji.png")));

        output.setText("Press enter to verify!");

        // All the cards are replaced with a new image.
        card1.setImage(new Image(getClass().getResourceAsStream("/png/" + newDeck.cardName[0])));
        card2.setImage(new Image(getClass().getResourceAsStream("/png/" + newDeck.cardName[1])));
        card3.setImage(new Image(getClass().getResourceAsStream("/png/" + newDeck.cardName[2])));
        card4.setImage(new Image(getClass().getResourceAsStream("/png/" + newDeck.cardName[3])));

    }

    @FXML
    void verify(ActionEvent event) {
        // String s initialized to the string the user entered into the text box.
        String s = enteredString.getText();
        // Entered solutions are initialized in the Deck class.
        newDeck.setEnteredSolution(s);

        // Returns an integer based on verification of the entered solutions.
        int i = newDeck.checkEnteredSolution();

        // Based on the integer, displays a response to the user.
        check(i);

    }

    void check(int i){
        // When the user enters a correct solution.
        if (i == 0){
            emoji.setImage(new Image(getClass().getResourceAsStream("/png/happy_emoji.png")));
            output.setText("Correct! Nice Job!");
        }
        // When a user enters a solution that contains an invalid character.
        if (i == 1){
            emoji.setImage(new Image(getClass().getResourceAsStream("/png/laughing_emoji.png")));
            output.setText("Wrong! Must only contain 0-9 + - / * ( )!");
        }
        // When the user enters a solution that is missing a card value.
        if (i == 2){
            emoji.setImage(new Image(getClass().getResourceAsStream("/png/laughing_emoji.png")));
            output.setText("Wrong! Must contain all the cards!");
        }
        // When the user enters a solution that does not evaluate to 24.
        if (i == 3){
            emoji.setImage(new Image(getClass().getResourceAsStream("/png/laughing_emoji.png")));
            output.setText("Wrong! Doesn't evaluate to 24!");
        }

    }
    @FXML
    void findSolution(ActionEvent event) {
        // Generates a string that contains the solution from ChatGPT 4o.
        String solution = newDeck.generateSolution();

        // Presents the solution to the user.
        solutionLabel.setText(solution);
    }

}

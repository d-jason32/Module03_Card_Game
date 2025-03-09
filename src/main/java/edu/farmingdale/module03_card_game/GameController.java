package edu.farmingdale.module03_card_game;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class GameController {
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
        newDeck = new Deck();

        emoji.setImage(new Image(getClass().getResourceAsStream("/png/thinking_emoji.png")));

        output.setText("Press enter to verify!");


        card1.setImage(new Image(getClass().getResourceAsStream("/png/" + newDeck.cardName[0])));
        card2.setImage(new Image(getClass().getResourceAsStream("/png/" + newDeck.cardName[1])));
        card3.setImage(new Image(getClass().getResourceAsStream("/png/" + newDeck.cardName[2])));
        card4.setImage(new Image(getClass().getResourceAsStream("/png/" + newDeck.cardName[3])));

    }

    @FXML
    void verify(ActionEvent event) {
        String s = enteredString.getText();
        newDeck.setEnteredSolution(s);

        int i = newDeck.checkEnteredSolution();

        check(i);

        System.out.println(i);
    }

    void check(int i){

        if (i == 0){
            emoji.setImage(new Image(getClass().getResourceAsStream("/png/happy_emoji.png")));
            output.setText("Correct! Nice Job!");
        }

        if (i == 1){
            emoji.setImage(new Image(getClass().getResourceAsStream("/png/laughing_emoji.png")));
            output.setText("Wrong! Must only contain 0-9 + - / * ( )!");
        }

        if (i == 2){
            emoji.setImage(new Image(getClass().getResourceAsStream("/png/laughing_emoji.png")));
            output.setText("Wrong! Must contain all the cards!");
        }

        if (i == 3){
            emoji.setImage(new Image(getClass().getResourceAsStream("/png/laughing_emoji.png")));
            output.setText("Wrong! Doesn't evaluate to 24!");
        }

    }

}

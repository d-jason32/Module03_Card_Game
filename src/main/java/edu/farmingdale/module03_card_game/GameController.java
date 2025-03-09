package edu.farmingdale.module03_card_game;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.script.ScriptException;

public class GameController {

    @FXML
    private ImageView card1;

    @FXML
    private ImageView card2;

    @FXML
    private ImageView card3;

    @FXML
    private ImageView card4;

    private Deck newDeck;

    @FXML
    private TextField enteredString;


    @FXML
    void refreshButton(ActionEvent event) throws ScriptException {
        newDeck = new Deck();


        card1.setImage(new Image(getClass().getResourceAsStream("/png/" + newDeck.cardName[0])));
        card2.setImage(new Image(getClass().getResourceAsStream("/png/" + newDeck.cardName[1])));
        card3.setImage(new Image(getClass().getResourceAsStream("/png/" + newDeck.cardName[2])));
        card4.setImage(new Image(getClass().getResourceAsStream("/png/" + newDeck.cardName[3])));

    }

    @FXML
    void verify(ActionEvent event) throws ScriptException {
        String s =enteredString.getText();
        newDeck.setEnteredSolution(s);

        int i = newDeck.checkEnteredSolution();

        System.out.println(i);
    }

}

package edu.farmingdale.module03_card_game;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GameController {

    @FXML
    private ImageView card1;

    @FXML
    private ImageView card2;

    @FXML
    private ImageView card3;

    @FXML
    private ImageView card4;

    @FXML
    void refreshButton(ActionEvent event) {
        Deck newDeck = new Deck();


        card1.setImage(new Image(getClass().getResourceAsStream("/png/" + newDeck.cardName[0])));
        card2.setImage(new Image(getClass().getResourceAsStream("/png/" + newDeck.cardName[1])));
        card3.setImage(new Image(getClass().getResourceAsStream("/png/" + newDeck.cardName[2])));
        card4.setImage(new Image(getClass().getResourceAsStream("/png/" + newDeck.cardName[3])));

    }

}

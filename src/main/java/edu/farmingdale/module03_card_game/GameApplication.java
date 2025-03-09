package edu.farmingdale.module03_card_game;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Start class of the card game.
 * @author Jason Devaraj
 */
public class GameApplication extends Application {
    /**
     * Starts the card game application.
     * @param stage Instance of a stage class.
     * @throws IOException Handles input and output errors.
     */
    @Override
    public void start(Stage stage) throws IOException {
        // Initializes fxmlLoader from card-game.fxml.
        FXMLLoader fxmlLoader = new FXMLLoader(GameApplication.class.getResource("card-game.fxml"));
        // Initializes scene from the fxmlLoader and determines dimensions.
        Scene scene = new Scene(fxmlLoader.load(), 1000, 800);
        // Sets the title of the stage
        stage.setTitle("Card Game!");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * The main method of the game application class.
     * @param args Parameter that is included when running the main method.
     */
    public static void main(String[] args) {
        launch();
    }
}
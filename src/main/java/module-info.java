module edu.farmingdale.module03_card_game {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.scripting;
    requires exp4j;
    requires simple.openai;
    requires java.net.http;


    opens edu.farmingdale.module03_card_game to javafx.fxml;
    exports edu.farmingdale.module03_card_game;
}
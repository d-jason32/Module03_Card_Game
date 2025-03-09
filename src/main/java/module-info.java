module edu.farmingdale.module03_card_game {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.scripting;
    requires exp4j;
    requires org.json;


    opens edu.farmingdale.module03_card_game to javafx.fxml;
    exports edu.farmingdale.module03_card_game;
}
package edu.farmingdale.module03_card_game;

import io.github.sashirestela.openai.SimpleOpenAI;
import io.github.sashirestela.openai.domain.chat.ChatMessage;
import io.github.sashirestela.openai.domain.chat.ChatRequest;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

/**
 * Represents a deck of playing cards and
 * generates 4 random cards.
 * @author Jason Devaraj
 */
public class Deck {
    private int[] cardNumber;
    public String[] cardName;
    public int[] cardValue;
    String enteredSolution;

    /**
     * The corresponding values of a card
     * based on its index.
     */
    private final int[] correspondingValues = {
            2, 2, 2, 2,
            3, 3, 3, 3,
            4, 4, 4, 4,
            5, 5, 5, 5,
            6, 6, 6, 6,
            7, 7, 7, 7,
            8, 8, 8, 8,
            9, 9, 9, 9,
            10, 10, 10, 10,
            1, 1, 1, 1,
            11, 11, 11, 11,
            13, 13, 13, 13,
            12, 12, 12, 12
    };

    /**
     * Deck default constructor that generates 4 random cards,
     * assigns the FXML images to them and determines the
     * cards values.
     */
    Deck() {
        cardNumber = new int[4];
        cardName = new String[4];
        cardValue = new int[4];

        this.generateRandomCards();
        this.generateImageStrings();
        this.setCardValues();
    }

    /**
     * Generates 4 random cards in a deck of 52 cards.
     */
    public void generateRandomCards(){
        int i = 0;

        while(i < 4) {
            // Picks a random integer between 1 - 52.
            int randomNumber = (int) (Math.random() * 52) + 1;

            // Prevents duplicates
            if (randomNumber != cardNumber[0] && randomNumber != cardNumber[1] &&
                randomNumber != cardNumber[2] && randomNumber != cardNumber[3]){
                cardNumber[i] = randomNumber;
                i++;
            }
        }
    }

    /**
     * Generates strings of each card that corresponds
     * to their name in the PNG resource file.
     */
    public void generateImageStrings(){
        String image1 = cardNumber[0] + ".png";
        String image2 = cardNumber[1] + ".png";
        String image3 = cardNumber[2] + ".png";
        String image4 = cardNumber[3] + ".png";

        this.cardName = new String[]{image1, image2, image3, image4};
    }


    /**
     * Determines if the user entered solution is valid.
     * @return An integer determining if the integer is valid,
     * contains invalid characters, doesn't contain all the cards,
     * or does not evaluate to 24.
     */
    public int checkEnteredSolution(){
        int check = 0;
        String card1 = Integer.toString(cardValue[0]);
        String card2 = Integer.toString(cardValue[1]);
        String card3 = Integer.toString(cardValue[2]);
        String card4 = Integer.toString(cardValue[3]);

        // Make sure it only contains certain values
        if (!enteredSolution.matches("[0-9()+/*\\s-]+")){
            System.out.println("Must only contain 0-9 + - / * ( )!");
            return 1;
        }

        // Checks to see if the entered solution contains all the cards.
        if (!enteredSolution.contains(card1) || !enteredSolution.contains(card2) || !enteredSolution.contains(card3) || !enteredSolution.contains(card4)){
            System.out.println("Must contain all the cards!");
            return 2;
        }

        // Evaluates the entered expression as a string into an integer
        Expression expression = new ExpressionBuilder(enteredSolution).build();
        int result = (int) expression.evaluate();
        System.out.println("Result: " + result);

        // Checks if the evaluated expression is equal to 24.
        if (result != 24){
            System.out.println("Must evaluate to 24!");
            return 3;
        }

        // Entered string is good
        return check;
    }

    /**
     * Takes a card index and assigns it to its corresponding
     * value.
     * For example, an ace would equal 1.
     * 3 of clubs would equal 3.
     */
    public void setCardValues(){
        // Fixes off by 1 error
        cardValue[0] = correspondingValues[cardNumber[0] - 1];
        cardValue[1] = correspondingValues[cardNumber[1] - 1];
        cardValue[2] = correspondingValues[cardNumber[2] - 1];
        cardValue[3] = correspondingValues[cardNumber[3] - 1];

    }

    /**
     * Sets the entered string by the user to the variable
     * enteredSolution.
     * @param s The entered solution set by the user.
     */
    public void setEnteredSolution(String s){
        this.enteredSolution = s;
    }

    /**
     * Uses OpenAI GPT-4o model to determine a possible solution
     * to solve the game.
     * @return A string that represents the solution
     * generated by OpenAI GPT-4o.
     */
    public String generateSolution(){
        String card1 = Integer.toString(cardValue[0]);
        String card2 = Integer.toString(cardValue[1]);
        String card3 = Integer.toString(cardValue[2]);
        String card4 = Integer.toString(cardValue[3]);

        // The message sent to the OpenAI API model.
        String messageToChatbot = String.format("""
                %s,%s,%s,%s,
                Generate an expression using these 4 numbers that evaluates to 24.
                You can only use the numbers once.
                Only use ()+-*/ and the numbers.
                Print out the expression only.
                It is possible that an expression does not exist, if that is the case, say "No Solution".
                Triple check the expression if it evaluates to 24 before sending it otherwise, just say "No Solution".
                Also, triple check if the expression has all 4 numbers.
                """, card1, card2, card3, card4);

        System.out.println(messageToChatbot);
        String apiKey = System.getenv("OPENAI_API_KEY");

        var openAI = SimpleOpenAI.builder()
                .apiKey(apiKey)
                .build();

        var chatRequest = ChatRequest.builder()
                .model("gpt-4o")
                .message(ChatMessage.SystemMessage.of("Double check your work before typing a response."))
                .message(ChatMessage.UserMessage.of(messageToChatbot))
                .temperature(0.0)
                .maxCompletionTokens(100)
                .build();

        var futureChat = openAI.chatCompletions().create(chatRequest);
        var chatResponse = futureChat.join();

        return chatResponse.firstContent();
    }

}

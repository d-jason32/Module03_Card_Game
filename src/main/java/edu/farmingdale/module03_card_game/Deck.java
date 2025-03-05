package edu.farmingdale.module03_card_game;

import javax.script.*;

public class Deck {
    private int[] cardNumber;
    String[] cardName;
    int[] correspondingValues = {
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

    String solution;

    Deck(){
        cardNumber = new int[4];
        cardName = new String[4];



        this.generateRandomCards();
        this.generateImageStrings();

    }

    public int[] getCardNumberArray() {
        return cardNumber;
    }

    public String[] getCardNameArray() {
        return cardName;
    }

    public void generateRandomCards(){
        int i = 0;
        while(i < 4) {
            int randomNumber = (int) (Math.random() * 52) + 1;

            // Prevents duplicates
            if (randomNumber != cardNumber[0] && randomNumber != cardNumber[1] &&
                randomNumber != cardNumber[2] && randomNumber != cardNumber[3]){
                cardNumber[i] = randomNumber;
                i++;
            }
        }
    }

    public void generateImageStrings(){
        String image1 = cardNumber[0] + ".png";
        String image2 = cardNumber[1] + ".png";
        String image3 = cardNumber[2] + ".png";
        String image4 = cardNumber[3] + ".png";

        this.cardName = new String[]{image1, image2, image3, image4};

    }

    /**
     *
     */
    public void generateSolution(){

    }

    public int checkEnteredSolution(String s) throws ScriptException {
        int check = 0;

        // Make sure it only contains certain values
        if (!s.matches("[0-9()+\\-]+")){
            System.out.println("Must only contain 0-9 + - / * ( )!");
            return 1;
        }

        if (parseString(s) != 24){
            System.out.println("Value is incorrect!");
            return 2;
        }



        // Entered string is good
        return check;
    }

    public int parseString(String s) throws ScriptException {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("nashorn");
        return (int) engine.eval(s);
    }
}

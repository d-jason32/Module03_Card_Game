package edu.farmingdale.module03_card_game;

// EXP4J Library
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;


public class Deck {
    private int[] cardNumber;
    public String[] cardName;
    public int[] cardValue;
    String enteredSolution;


    private int[] correspondingValues = {
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


    Deck() {
        cardNumber = new int[4];
        cardName = new String[4];
        cardValue = new int[4];





        this.generateRandomCards();
        this.generateImageStrings();
        this.setCardValues();

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

    public int checkEnteredSolution(){
        int check = 0;
        String card1 = Integer.toString(cardValue[0]);
        String card2 = Integer.toString(cardValue[1]);
        String card3 = Integer.toString(cardValue[2]);
        String card4 = Integer.toString(cardValue[3]);

        // Make sure it only contains certain values
        if (!enteredSolution.matches("[0-9()+/*-]+")){
            System.out.println("Must only contain 0-9 + - / * ( )!");
            return 1;
        }

        if (!enteredSolution.contains(card1) || !enteredSolution.contains(card2) || !enteredSolution.contains(card3) || !enteredSolution.contains(card4)){
            System.out.println("Must contain all the cards!");
            return 2;
        }

        Expression expression = new ExpressionBuilder(enteredSolution).build();
        int result = (int) expression.evaluate();
        System.out.println("Result: " + result);

        if (result != 24){
            System.out.println("Must evaluate to 24!");
            return 3;
        }

        // Entered string is good
        return check;
    }


    public void setCardValues(){
        // Fixes off by 1 error
        cardValue[0] = correspondingValues[cardNumber[0] - 1];
        cardValue[1] = correspondingValues[cardNumber[1] - 1];
        cardValue[2] = correspondingValues[cardNumber[2] - 1];
        cardValue[3] = correspondingValues[cardNumber[3] - 1];

    }

    public int[] getCardValues(){
        return cardValue;
    }

    public void setEnteredSolution(String s){
        this.enteredSolution = s;
    }



}

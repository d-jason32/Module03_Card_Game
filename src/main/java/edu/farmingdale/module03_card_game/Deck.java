package edu.farmingdale.module03_card_game;

public class Deck {
    private int[] cardNumber;
    String [] cardName;
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
            if (randomNumber != cardNumber[0] && randomNumber != cardNumber[1] && randomNumber != cardNumber[2] && randomNumber != cardNumber[3]){
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

    public void generateSolution(){

    }
}

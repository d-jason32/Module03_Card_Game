package edu.farmingdale.module03_card_game;

public class GameLogic {

    public static int[] generateRandomCards(){
        int[] arr = new int[4];

        for (int i = 0; i < 4; i++) {
            int randomNumber = (int) (Math.random() * 52) + 1;
            arr[i] = randomNumber;
        }
        return arr;
    }
}

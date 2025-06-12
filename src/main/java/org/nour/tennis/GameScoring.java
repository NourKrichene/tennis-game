package org.nour.tennis;

public class GameScoring {

    private static final String PLAYER = "Player ";
    private static final String WINS_THE_GAME = " wins the game";
    private static final String DEUCE = "Deuce";
    private static final int[] POINTS = new int[]{0, 15, 30, 40};
    private static final int POINTS_TO_WIN_IN_NO_DEUCE = 4;
    private static final int POINTS_TO_REACH_DEUCE = 3;

    public static String gameScoring(String input) {
        if (input == null || !input.matches("[AB]+")) {
            return "Invalid input : must be not empty and contain only A or B";
        }

        int[] score = new int[2];
        StringBuilder result = new StringBuilder();
        int i = 0;

        while (i < input.length()) {

            char currentPlayer = input.charAt(i);
            int playerIndex = currentPlayer - 'A';
            score[playerIndex]++;

            if (score[playerIndex] == POINTS_TO_WIN_IN_NO_DEUCE) {
                result.append(PLAYER).append(currentPlayer).append(WINS_THE_GAME);
                return result.toString();
            }
            if (score[0] == POINTS_TO_REACH_DEUCE && score[0] == score[1]) {
                i++;
                break;
            }
            result.append(PLAYER).append("A : ").append(POINTS[score[0]]).append(" / ").append(PLAYER).append("B : ").append(POINTS[score[1]]).append(System.lineSeparator());
            i++;
        }

        if (i == input.length()) return result.toString();
        result.append(DEUCE).append(System.lineSeparator());
        while (i < input.length()) {
            result.append(PLAYER).append(input.charAt(i)).append(" Advantage").append(System.lineSeparator());
            i++;
            if (input.charAt(i) != input.charAt(i - 1)) {
                result.append(DEUCE).append(System.lineSeparator());
                i++;
                continue;
            }
            result.append(PLAYER).append(input.charAt(i)).append(WINS_THE_GAME);
            return result.toString();
        }
        return result.toString();
    }

}
package org.nour.tennis;

public class GameScoring {

    private static final String PLAYER = "Player ";
    private static final String WINS_THE_GAME = " wins the game";
    private static final String DEUCE = "Deuce";
    private static final String NEW_LINE = System.lineSeparator();
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

            if (isWinPointReached(score[playerIndex])) {
                return writeWinnerLine(result, currentPlayer);
            }
            if (isDeuceReached(score)) {
                i++;
                break;
            }
            result.append(writeScoreLine(score));
            i++;
        }

        if (i == input.length()) return result.toString();

        writeDeuceLine(result);

        while (i < input.length()) {
            result.append(writeAdvantageLine(input.charAt(i)));
            i++;
            if (input.charAt(i) != input.charAt(i - 1)) {
                writeDeuceLine(result);
                i++;
                continue;
            }
            return writeWinnerLine(result, input.charAt(i));
        }
        return result.toString();
    }


    private static boolean isWinPointReached(int score) {
        return score == POINTS_TO_WIN_IN_NO_DEUCE;
    }

    private static boolean isDeuceReached(int[] score) {
        return score[0] == POINTS_TO_REACH_DEUCE && score[0] == score[1];
    }


    private static void writeDeuceLine(StringBuilder result) {
        result.append(DEUCE).append(NEW_LINE);
    }

    private static String writeAdvantageLine(char currentPlayer) {
        return PLAYER + currentPlayer + " Advantage" + NEW_LINE;
    }

    private static String writeWinnerLine(StringBuilder result, char currentPlayer) {
        result.append(PLAYER).append(currentPlayer).append(WINS_THE_GAME);
        return result.toString();
    }

    private static String writeScoreLine(int[] score) {
        return PLAYER + "A : " + POINTS[score[0]] + " / " + PLAYER + "B : " + POINTS[score[1]] + NEW_LINE;
    }

}
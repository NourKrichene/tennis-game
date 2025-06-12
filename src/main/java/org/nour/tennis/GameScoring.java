package org.nour.tennis;

public class GameScoring {

    private static final String PLAYER = "Player ";
    private static final String WINS_THE_GAME = " wins the game";
    private static final String DEUCE = "Deuce";
    private static final String NEW_LINE = System.lineSeparator();
    private static final int[] POINTS = new int[]{0, 15, 30, 40};
    private static final int POINTS_TO_WIN_IN_NO_DEUCE = 4;
    private static final int POINTS_TO_REACH_DEUCE = 3;
    private static final int DEUCE_START_POINT = 6;
    static boolean isDeuce = false;

    public static String gameScoring(String input) {

        if (input == null || !input.matches("[AB]+")) {
            return "Invalid input : must be not empty and contain only A or B";
        }

        StringBuilder scoreLines = computeGameScore(input);

        if (isDeuce) {
            scoreLines.append(computeGameScoreWithDeuce(input));
        }

        return scoreLines.toString();
    }

    private static StringBuilder computeGameScore(String input) {
        int i = 0;
        int[] score = new int[2];
        StringBuilder result = new StringBuilder();
        while (i < input.length()) {
            char currentPlayer = input.charAt(i);
            int playerIndex = currentPlayer - 'A';
            score[playerIndex]++;

            if (isWinPointReached(score[playerIndex])) {
                result.append(writeWinnerLine(currentPlayer));
                break;
            }
            if (isDeuceReached(score)) {
                isDeuce = true;
                break;
            }
            result.append(writeScoreLine(score));
            i++;
        }
        return result;
    }

    private static StringBuilder computeGameScoreWithDeuce(String input) {
        StringBuilder result = new StringBuilder();
        result.append(writeDeuceLine());
        int i = DEUCE_START_POINT;
        while (i < input.length()) {
            result.append(writeAdvantageLine(input.charAt(i)));
            i++;
            if (input.charAt(i) != input.charAt(i - 1)) {
                result.append(writeDeuceLine());
                i++;
                continue;
            }
            result.append(writeWinnerLine(input.charAt(i)));
            return result;
        }
        return result;
    }


    private static boolean isWinPointReached(int score) {
        return score == POINTS_TO_WIN_IN_NO_DEUCE;
    }

    private static boolean isDeuceReached(int[] score) {
        return score[0] == POINTS_TO_REACH_DEUCE && score[0] == score[1];
    }

    private static String writeScoreLine(int[] score) {
        return String.format("Player A : %d / Player B : %d%s", POINTS[score[0]], POINTS[score[1]], NEW_LINE);
    }

    private static String writeWinnerLine(char currentPlayer) {
        return PLAYER + currentPlayer + WINS_THE_GAME;
    }


    private static String writeDeuceLine() {
        return DEUCE + NEW_LINE;
    }

    private static String writeAdvantageLine(char currentPlayer) {
        return PLAYER + currentPlayer + " Advantage" + NEW_LINE;
    }

}
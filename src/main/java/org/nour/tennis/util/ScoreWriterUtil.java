package org.nour.tennis.util;


public class ScoreWriterUtil {

    private static final String NEW_LINE = System.lineSeparator();
    private static final int[] POINTS = new int[]{0, 15, 30, 40};

    public static String writeScoreLine(int[] score) {
        return String.format("Player A : %d / Player B : %d%s", POINTS[score[0]], POINTS[score[1]], NEW_LINE);
    }

    public static String writeWinnerLine(char currentPlayer) {
        return String.format("Player %c wins the game", currentPlayer);
    }

    public static String writeAdvantageLine(char currentPlayer) {
        return String.format("Player %c Advantage%s", currentPlayer, NEW_LINE);
    }

    public static String writeDeuceLine() {
        return "Deuce" + NEW_LINE;
    }
}

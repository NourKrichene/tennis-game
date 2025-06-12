package org.nour.tennis.util;

public class ValidatorsUtil {

    private static final int POINTS_TO_WIN_IN_NO_DEUCE = 4;
    private static final int POINTS_TO_REACH_DEUCE = 3;
    private static final String INPUT_REGEX = "[AB]+";

    public static boolean isValid(String input) {
        return input != null && input.matches(INPUT_REGEX);
    }

    public static boolean twoConsecutivePointsWon(String input, int i) {
        return input.charAt(i) == input.charAt(i - 1);
    }

    public static boolean isWinPointReached(int score) {
        return score == POINTS_TO_WIN_IN_NO_DEUCE;
    }

    public static boolean isDeuceReached(int[] score) {
        return score[0] == POINTS_TO_REACH_DEUCE && score[0] == score[1];
    }
}

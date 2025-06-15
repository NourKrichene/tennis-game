package org.nour.tennis.util;


public class ScoreWriterUtil {

    public static final char WIN = 'W';
    public static final char ADVANTAGE = 'a';
    private static final char DEUCE = 'D';
    private static final String NEW_LINE = System.lineSeparator();
    private static final int[] POINTS = new int[]{0, 15, 30, 40};

    public static String formatScoreToLines(String score) {
        var scoreLines = new StringBuilder();

        for (int i = 0; i < score.length(); i += 2) {
            char firstSymbol = score.charAt(i);
            char secondSymbol = score.charAt(i + 1);
            scoreLines.append(formatPair(firstSymbol, secondSymbol));
        }

        return scoreLines.toString();
    }

    private static String formatPair(char firstSymbol, char secondSymbol) {
        return switch (secondSymbol) {
            case ADVANTAGE -> writeAdvantageLine(firstSymbol);
            case DEUCE -> writeDeuceLine();
            case WIN -> writeWinnerLine(firstSymbol);
            default ->
                    writeScoreLine(POINTS[Character.getNumericValue(firstSymbol)],
                            POINTS[Character.getNumericValue(secondSymbol)]);
        };
    }

    private static String writeScoreLine(int pointsPlayerA, int pointsPlayerB) {
        return String.format("Player A : %d / Player B : %d%s", pointsPlayerA, pointsPlayerB, NEW_LINE);
    }

    private static String writeWinnerLine(char currentPlayer) {
        return String.format("Player %c wins the game", currentPlayer);
    }

    private static String writeAdvantageLine(char currentPlayer) {
        return String.format("Player %c Advantage%s", currentPlayer, NEW_LINE);
    }

    private static String writeDeuceLine() {
        return "Deuce" + NEW_LINE;
    }
}

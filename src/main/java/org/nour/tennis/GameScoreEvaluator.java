package org.nour.tennis;

import static org.nour.tennis.util.ScoreWriterUtil.*;
import static org.nour.tennis.util.ValidatorsUtil.*;

public class GameScoreEvaluator {

    private static final int DEUCE_START_POINT = 6;
    private static final String INVALID_INPUT_MESSAGE = "Invalid input : must be not empty and contain only A or B";

    public static String computeGameScore(String playersPointsWinHistory) {
        if (!isValid(playersPointsWinHistory)) {
            return INVALID_INPUT_MESSAGE;
        }

        PreDeuceGameScore preDeuceGameScore = computePreDeuceGameScore(playersPointsWinHistory);

        if (preDeuceGameScore.isDeuceReached) {
            String gameDeuceScore = computeDeuceGameScore(playersPointsWinHistory);
            return preDeuceGameScore.scoreLines().concat(gameDeuceScore);
        }

        return preDeuceGameScore.scoreLines();
    }

    private static PreDeuceGameScore computePreDeuceGameScore(String playersPointsWinHistory) {
        int[] playersPoints = new int[2];
        var scoreLines = new StringBuilder();

        for (int i = 0; i < playersPointsWinHistory.length(); i++) {
            char currentPlayer = playersPointsWinHistory.charAt(i);
            int currentPlayerPoints = ++playersPoints[currentPlayer - 'A'];

            if (isWinPointReached(currentPlayerPoints)) {
                scoreLines.append(writeWinnerLine(currentPlayer));
                return new PreDeuceGameScore(scoreLines.toString(), false);
            }

            if (isDeuceReached(playersPoints)) {
                return new PreDeuceGameScore(scoreLines.toString(), true);
            }

            scoreLines.append(writeScoreLine(playersPoints));
        }

        return new PreDeuceGameScore(scoreLines.toString(), false);
    }

    private static String computeDeuceGameScore(String playersPointsWinHistory) {
        var scoreLines = new StringBuilder(writeDeuceLine());
        boolean isPreviousPointDeuce = true;

        for (int i = DEUCE_START_POINT; i < playersPointsWinHistory.length(); i++) {
            char currentPlayer = playersPointsWinHistory.charAt(i);

            if (isPreviousPointDeuce) {
                scoreLines.append(writeAdvantageLine(currentPlayer));
                isPreviousPointDeuce = false;
                continue;
            }

            if (twoConsecutivePointsWon(playersPointsWinHistory, i)) {
                scoreLines.append(writeWinnerLine(currentPlayer));
                return scoreLines.toString();
            }

            scoreLines.append(writeDeuceLine());
            isPreviousPointDeuce = true;
        }

        return scoreLines.toString();
    }

    private record PreDeuceGameScore(String scoreLines, boolean isDeuceReached) {
    }
}
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
        int i = 0;
        int[] playersPoints = new int[2];
        StringBuilder scoreLines = new StringBuilder();
        while (i < playersPointsWinHistory.length()) {
            char currentPlayer = playersPointsWinHistory.charAt(i);
            int currentPlayerPoints = ++playersPoints[currentPlayer - 'A'];
            if (isWinPointReached(currentPlayerPoints)) {
                scoreLines.append(writeWinnerLine(currentPlayer));
                break;
            }
            if (isDeuceReached(playersPoints)) {
                return new PreDeuceGameScore(scoreLines.toString(), true);
            }
            scoreLines.append(writeScoreLine(playersPoints));
            i++;
        }
        return new PreDeuceGameScore(scoreLines.toString(), false);
    }


    private static String computeDeuceGameScore(String playersPointsWinHistory) {
        StringBuilder scoreLines = new StringBuilder(writeDeuceLine());
        int i = DEUCE_START_POINT;
        while (i < playersPointsWinHistory.length()) {
            char currentPlayer = playersPointsWinHistory.charAt(i);
            scoreLines.append(writeAdvantageLine(currentPlayer));
            i++;
            if (!twoConsecutivePointsWon(playersPointsWinHistory, i)) {
                scoreLines.append(writeDeuceLine());
                i++;
                continue;
            }
            scoreLines.append(writeWinnerLine(currentPlayer));
            return scoreLines.toString();
        }
        return scoreLines.toString();
    }


    private record PreDeuceGameScore(String scoreLines, boolean isDeuceReached) {
    }

}
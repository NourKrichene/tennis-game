package org.nour.tennis;

import static org.nour.tennis.util.ScoreWriterUtil.*;
import static org.nour.tennis.util.ValidatorsUtil.*;

public class GameScoreEvaluator {

    private static final int DEUCE_START_POINT = 6;
    private static final String INVALID_INPUT_MESSAGE = "Invalid input : must be not empty and contain only A or B";
    private static final String DEUCE = "DD";

    public static String computeGameScore(String playersPointsWinHistory) {
        if (!isValid(playersPointsWinHistory)) {
            return INVALID_INPUT_MESSAGE;
        }

        PreDeuceGameScore preDeuceGameScore = computePreDeuceGameScore(playersPointsWinHistory);

        String score = preDeuceGameScore.isDeuceReached ?
                preDeuceGameScore.score().concat(computeDeuceGameScore(playersPointsWinHistory)) :
                preDeuceGameScore.score();

        return formatScoreToLines(score);
    }

    private static PreDeuceGameScore computePreDeuceGameScore(String playersPointsWinHistory) {
        int[] playersPoints = new int[2];
        var score = new StringBuilder();

        for (int i = 0; i < playersPointsWinHistory.length(); i++) {
            char currentPlayer = playersPointsWinHistory.charAt(i);
            int currentPlayerPoints = ++playersPoints[currentPlayer - 'A'];

            if (isWinPointReached(currentPlayerPoints)) {
                score.append(currentPlayer).append(WIN);
                return new PreDeuceGameScore(score.toString(), false);
            }

            if (isDeuceReached(playersPoints)) {
                return new PreDeuceGameScore(score.toString(), true);
            }

            score.append(playersPoints[0]).append(playersPoints[1]);
        }

        return new PreDeuceGameScore(score.toString(), false);
    }

    private static String computeDeuceGameScore(String playersPointsWinHistory) {
        var score = new StringBuilder(DEUCE);
        boolean isPreviousPointDeuce = true;

        for (int i = DEUCE_START_POINT; i < playersPointsWinHistory.length(); i++) {
            char currentPlayer = playersPointsWinHistory.charAt(i);

            if (isPreviousPointDeuce) {
                score.append(currentPlayer).append(ADVANTAGE);
                isPreviousPointDeuce = false;
                continue;
            }

            if (twoConsecutivePointsWon(playersPointsWinHistory, i)) {
                score.append(currentPlayer).append(WIN);
                return score.toString();
            }

            score.append(DEUCE);
            isPreviousPointDeuce = true;
        }

        return score.toString();
    }

    private record PreDeuceGameScore(String score, boolean isDeuceReached) {
    }
}
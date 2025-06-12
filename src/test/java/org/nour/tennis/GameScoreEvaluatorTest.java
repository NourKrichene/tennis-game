package org.nour.tennis;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.nour.tennis.GameScoreEvaluator.computeGameScore;

class GameScoreEvaluatorTest {

    @ParameterizedTest(name = "Invalid input \"{0}\"")
    @NullAndEmptySource
    @ValueSource(strings = {"   ", "ABAC", "ABAbAaA"})
    void computeGameScoreShouldReturnInvalidInput(String input) {
        String result = computeGameScore(input);
        assertEquals("Invalid input : must be not empty and contain only A or B", result);
    }


    @ParameterizedTest(name = "Score of unfinished game \"{0}\"")
    @CsvFileSource(resources = "/unfinished-game.csv")
    void computeGameScoreShouldReturnScoreOfUnfinishedGame(String input, String expected) {
        String result = computeGameScore(input);
        assertEquals(expected, result);
    }


    @ParameterizedTest(name = "Score of game \"{0}\"")
    @CsvFileSource(resources = "/game-without-deuce.csv")
    void computeGameScoreShouldReturnScoreOfGameWithoutDeuce(String input, String expected) {
        String result = computeGameScore(input);
        assertEquals(expected, result);
    }


    @ParameterizedTest(name = "Score of game with deuce \"{0}\"")
    @CsvFileSource(resources = "/game-with-deuce.csv")
    void computeGameScoreShouldReturnScoreOfGameWithDeuce(String input, String expected) {
        String result = computeGameScore(input);
        assertEquals(expected, result);
    }
}

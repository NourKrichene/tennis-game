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

        int[] playersPoints = new int[]{0, 0};
        StringBuilder result = new StringBuilder();
        boolean deuce = false;
        int i = 0;

        while (i < input.length()) {

            if (deuce) {
                result.append(PLAYER).append(input.charAt(i)).append(" Advantage").append(System.lineSeparator());
                i++;
                if (input.charAt(i) != input.charAt(i - 1)) {
                    result.append(DEUCE).append(System.lineSeparator());
                    i++;
                    continue;
                }
                if (input.charAt(i) == input.charAt(i - 1)) {
                    result.append(PLAYER).append(input.charAt(i)).append(WINS_THE_GAME);
                    return result.toString();
                }
            }

            int playerIndex = input.charAt(i) - 'A';
            playersPoints[playerIndex]++;

            if (playersPoints[playerIndex] == POINTS_TO_WIN_IN_NO_DEUCE) {
                result.append(PLAYER).append(input.charAt(i)).append(WINS_THE_GAME);
                return result.toString();
            }

            if (playersPoints[0] == POINTS_TO_REACH_DEUCE && playersPoints[0] == playersPoints[1]) {
                deuce = true;
                result.append(DEUCE).append(System.lineSeparator());
                i++;
                continue;
            }

            result.append(PLAYER).append("A : ").append(POINTS[playersPoints[0]]).append(" / ")
                    .append(PLAYER).append("B : ").append(POINTS[playersPoints[1]]).append(System.lineSeparator());
            i++;
        }
        return result.toString();
    }

}
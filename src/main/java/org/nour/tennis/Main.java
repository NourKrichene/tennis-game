package org.nour.tennis;

import java.util.Scanner;

import static org.nour.tennis.GameScoreEvaluator.computeGameScore;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter players' points win history (e.g., ABABAA): ");
        String input = scanner.nextLine();
        System.out.println(computeGameScore(input));
    }

}
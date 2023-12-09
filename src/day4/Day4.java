package day4;

import iDay.IDay;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Day4 implements IDay {
    @Override
    public long part1() throws IOException {
        File file = new File("src/day4/input.txt");
        Scanner in = new Scanner(file);

        int totalPoints = 0;
        while (in.hasNextLine()) {
            String line = in.nextLine();
            int winningNumbersCount = computeWinningNumberCount(line);

            // if winningNumberCount is 0, pow(2, -1) = 0.5, which converts to 0 points
            totalPoints += (int) Math.pow(2, winningNumbersCount - 1);
        }

        return totalPoints;
    }

    @Override
    public long part2() throws IOException {
        File file = new File("src/day4/input.txt");
        Scanner in = new Scanner(file);

        int totalScratchcards = 0;
        int[] multiplier = new int[25];
        Arrays.fill(multiplier, 0);
        while (in.hasNextLine()) {
            int currentMultiplier = multiplier[0];
            for (int i = 0; i < multiplier.length - 1; i++) {
                multiplier[i] = multiplier[i + 1];
            }
            multiplier[multiplier.length - 1] = 0;
            String line = in.nextLine();
            int winningNumbersCount = computeWinningNumberCount(line);

            for (int i = 0; i < winningNumbersCount; i++) {
                multiplier[i] += currentMultiplier + 1;
            }

            totalScratchcards += currentMultiplier + 1;
        }

        return totalScratchcards;
    }

    private static int computeWinningNumberCount(String line) {
        int colonPosition = line.indexOf(":");
        line = line.substring(colonPosition + 2);
        String[] numbers = line.split(" \\| ");

        List<Integer> winningNumbers = Arrays.stream(numbers[0].split(" ")).filter(s -> !s.equals("")).mapToInt(Integer::parseInt).boxed().toList();
        List<Integer> losingNumbers = Arrays.stream(numbers[1].split(" ")).filter(s -> !s.equals("")).mapToInt(Integer::parseInt).boxed().toList();

        int winningNumbersCount = 0;
        for (int number : losingNumbers) {
            if (winningNumbers.contains(number)) {
                winningNumbersCount++;
            }
        }

        return winningNumbersCount;
    }
}

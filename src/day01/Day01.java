package day01;

import iDay.IDay;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Day01 implements IDay {
    @Override
    public long part1() throws IOException {
        File file = new File("src/day01/input.txt");
        Scanner in = new Scanner(file);
        int sum = 0;
        String[] numbers = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};

        while (in.hasNextLine()) {
            String line = in.nextLine();

            int firstDigit = 0, lastDigit = 0;
            int firstDigitIndex = -1, lastDigitIndex = -1;
            for (int i = 0; i < line.length(); i++) {
                if (line.charAt(i) >= '0' && line.charAt(i) <= '9') {
                    int digit = Integer.parseInt(line.substring(i, i + 1));
                    if (firstDigitIndex == -1) {
                        firstDigit = digit;
                        firstDigitIndex = i;
                    }
                    lastDigit = digit;
                    lastDigitIndex = i;
                }
            }

            for (int i = 0; i <= 9; i++) {
                int firstPosition = line.indexOf(numbers[i]);
                if (firstPosition != -1 && (firstPosition < firstDigitIndex || firstDigitIndex == -1)) {
                    firstDigit = i;
                    firstDigitIndex = firstPosition;
                }

                int lastPosition = line.lastIndexOf(numbers[i]);
                if (lastPosition != -1 && lastPosition > lastDigitIndex) {
                    lastDigit = i;
                    lastDigitIndex = lastPosition;
                }
            }

            sum += firstDigit * 10 + lastDigit;
        }
        return sum;
    }

    @Override
    public long part2() throws IOException {
        return part1();
    }
}

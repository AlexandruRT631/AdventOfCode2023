package day3;

import iDay.IDay;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Day3 implements IDay {
    private static char[][] reduceSize(char[][] grid, int x, int y) {
        char[][] newGrid = new char[x][y];
        for (int i = 0; i < x; i++) {
            newGrid[i] = Arrays.copyOf(grid[i], y);
        }
        return newGrid;
    }

    private static boolean checkForNeighbouringSymbol(char[][] grid, int start, int end, int row) {
        if (row != 0) {
            for (int i = Integer.max(start - 1, 0); i <= Integer.min(end + 1, grid[row].length - 1); i++) {
                if (grid[row - 1][i] != '.' && (grid[row - 1][i] < '0' || grid[row - 1][i] > '9')) {
                    return true;
                }
            }
        }
        if (row != grid.length - 1) {
            for (int i = Integer.max(start - 1, 0); i <= Integer.min(end + 1, grid[row].length - 1); i++) {
                if (grid[row + 1][i] != '.' && (grid[row + 1][i] < '0' || grid[row + 1][i] > '9')) {
                    return true;
                }
            }
        }
        if (start - 1 >= 0 && grid[row][start - 1] != '.' && (grid[row][start - 1] < '0' || grid[row][start - 1] > '9')) {
            return true;
        }
        return end + 1 < grid[row].length && grid[row][end + 1] != '.' && (grid[row][end + 1] < '0' || grid[row][end + 1] > '9');
    }

    @Override
    public void part1() throws IOException {
        File file = new File("src/day3/input.txt");
        Scanner in = new Scanner(file);
        int sum = 0;

        char[][] grid = new char[1000][1000];
        int x = 0;
        int y = 0;
        while (in.hasNextLine()) {
            y = 0;
            String line = in.nextLine();
            for (char c : line.toCharArray()) {
                grid[x][y] = c;
                y++;
            }
            x++;
        }
        grid = reduceSize(grid, x, y);

        boolean isNumber = false;
        int number = 0;
        int numberStart = 0;
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if (!isNumber && grid[i][j] >= '0' && grid[i][j] <= '9') {
                    isNumber = true;
                    number = grid[i][j] - '0';
                    numberStart = j;
                } else if (isNumber && grid[i][j] >= '0' && grid[i][j] <= '9') {
                    number = number * 10 + grid[i][j] - '0';
                } else if (isNumber && (grid[i][j] < '0' || grid[i][j] > '9')) {
                    isNumber = false;

                    if (checkForNeighbouringSymbol(grid, numberStart, j - 1, i)) {
                        sum += number;
                    }
                }
            }
            if (isNumber) {
                isNumber = false;

                if (checkForNeighbouringSymbol(grid, numberStart, y - 1, i)) {
                    sum += number;
                }
            }
        }

        System.out.println(sum);
    }

    @Override
    public void part2() throws IOException {
        File file = new File("src/day3/input.txt");
        Scanner in = new Scanner(file);
        int sum = 0;

        char[][] grid = new char[1000][1000];
        int x = 0;
        int y = 0;
        while (in.hasNextLine()) {
            y = 0;
            String line = in.nextLine();
            for (char c : line.toCharArray()) {
                grid[x][y] = c;
                y++;
            }
            x++;
        }
        grid = reduceSize(grid, x, y);

        boolean[][] checked = new boolean[x][y];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if (grid[i][j] == '*') {
                    int ratio = 1;
                    int adjacentNumbers = 0;
                    if (grid[i][j - 1] >= '0' && grid[i][j - 1] <= '9') {
                        adjacentNumbers++;
                        checked[i][j - 1] = true;
                        int number = grid[i][j - 1] - '0';
                        int power = 10;
                        for (int k = j - 2; k >= 0 && grid[i][k] >= '0' && grid[i][k] <= '9'; k--) {
                            number = number + power * (grid[i][k] - '0');
                            power *= 10;
                            checked[i][k] = true;
                        }
                        ratio *= number;
                    }
                    if (grid[i][j + 1] >= '0' && grid[i][j + 1] <= '9') {
                        adjacentNumbers++;
                        checked[i][j + 1] = true;
                        int number = grid[i][j + 1] - '0';
                        for (int k = j + 2; k < y && grid[i][k] >= '0' && grid[i][k] <= '9'; k++) {
                            number = number * 10 + (grid[i][k] - '0');
                            checked[i][k] = true;
                        }
                        ratio *= number;
                    }
                    if (i != 0) {
                        if (grid[i - 1][j - 1] >= '0' && grid[i - 1][j - 1] <= '9') {
                            adjacentNumbers++;
                            checked[i - 1][j - 1] = true;
                            int number = grid[i - 1][j - 1] - '0';
                            int power = 10;
                            for (int k = j - 2; k >= 0 && grid[i - 1][k] >= '0' && grid[i - 1][k] <= '9'; k--) {
                                number = number + power * (grid[i - 1][k] - '0');
                                power *= 10;
                                checked[i - 1][k] = true;
                            }
                            for (int k = j; k < y && grid[i - 1][k] >= '0' && grid[i - 1][k] <= '9'; k++) {
                                number = number * 10 + grid[i - 1][k] - '0';
                                checked[i - 1][k] = true;
                            }
                            ratio *= number;
                        }
                        if (grid[i - 1][j] >= '0' && grid[i - 1][j] <= '9' && !checked[i - 1][j]) {
                            adjacentNumbers++;
                            checked[i - 1][j] = true;
                            int number = grid[i - 1][j] - '0';
                            for (int k = j + 1; k < y && grid[i - 1][k] >= '0' && grid[i - 1][k] <= '9'; k++) {
                                number = number * 10 + grid[i - 1][k] - '0';
                                checked[i - 1][k] = true;
                            }
                            ratio *= number;
                        }
                        if (grid[i - 1][j + 1] >= '0' && grid[i - 1][j + 1] <= '9' && !checked[i - 1][j + 1]) {
                            adjacentNumbers++;
                            checked[i - 1][j + 1] = true;
                            int number = grid[i - 1][j + 1] - '0';
                            for (int k = j + 2; k < y && grid[i - 1][k] >= '0' && grid[i - 1][k] <= '9'; k++) {
                                number = number * 10 + grid[i - 1][k] - '0';
                                checked[i - 1][k] = true;
                            }
                            ratio *= number;
                        }
                    }
                    if (i != x - 1) {
                        if (grid[i + 1][j - 1] >= '0' && grid[i + 1][j - 1] <= '9' && !checked[i + 1][j - 1]) {
                            adjacentNumbers++;
                            checked[i + 1][j - 1] = true;
                            int number = grid[i + 1][j - 1] - '0';
                            int power = 10;
                            for (int k = j - 2; k >= 0 && grid[i + 1][k] >= '0' && grid[i + 1][k] <= '9'; k--) {
                                number = number + power * (grid[i + 1][k] - '0');
                                power *= 10;
                                checked[i + 1][k] = true;
                            }
                            for (int k = j; k < y && grid[i + 1][k] >= '0' && grid[i + 1][k] <= '9'; k++) {
                                number = number * 10 + grid[i + 1][k] - '0';
                                checked[i + 1][k] = true;
                            }
                            ratio *= number;
                        }
                        if (grid[i + 1][j] >= '0' && grid[i + 1][j] <= '9' && !checked[i + 1][j]) {
                            adjacentNumbers++;
                            checked[i + 1][j] = true;
                            int number = grid[i + 1][j] - '0';
                            for (int k = j + 1; k < y && grid[i + 1][k] >= '0' && grid[i + 1][k] <= '9'; k++) {
                                number = number * 10 + grid[i + 1][k] - '0';
                                checked[i + 1][k] = true;
                            }
                            ratio *= number;
                        }
                        if (grid[i + 1][j + 1] >= '0' && grid[i + 1][j + 1] <= '9' && !checked[i + 1][j + 1]) {
                            adjacentNumbers++;
                            checked[i + 1][j + 1] = true;
                            int number = grid[i + 1][j + 1] - '0';
                            for (int k = j + 2; k < y && grid[i + 1][k] >= '0' && grid[i + 1][k] <= '9'; k++) {
                                number = number * 10 + grid[i + 1][k] - '0';
                                checked[i + 1][k] = true;
                            }
                            ratio *= number;
                        }
                    }
                    if (adjacentNumbers == 2) {
                        sum += ratio;
                    }
                }
            }
        }

        System.out.println(sum);
    }
}

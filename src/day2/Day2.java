package day2;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Day2 {
    public static void part1() throws IOException {
        File file = new File("src/day2/input.txt");
        Scanner in = new Scanner(file);

        int maxRed = 12;
        int maxGreen = 13;
        int maxBlue = 14;

        int sum = 0;

        while (in.hasNextLine()) {
            String line = in.nextLine();

            line = line.substring(5).replace(": ", ":");
            String[] game = line.split(":");
            int gameNumber = Integer.parseInt(game[0]);

            boolean possible = true;
            String[] rounds = game[1].replace("; ", ";").split(";");

            for (String round : rounds) {
                if (!possible) {
                    break;
                }

                round = round.replace(", ", ",");
                String[] colors = round.split(",");
                for (String color: colors) {
                    String[] values = color.split(" ");
                    if (values[1].equals("red") && Integer.parseInt(values[0]) > maxRed) {
                        possible = false;
                        break;
                    } else if (values[1].equals("green") && Integer.parseInt(values[0]) > maxGreen) {
                        possible = false;
                        break;
                    } else if (values[1].equals("blue") && Integer.parseInt(values[0]) > maxBlue) {
                        possible = false;
                        break;
                    }
                }
            }

            if (possible) {
                sum += gameNumber;
            }
        }

        System.out.println(sum);
    }

    public static void part2() throws IOException {
        File file = new File("src/day2/input.txt");
        Scanner in = new Scanner(file);

        long sum = 0;

        while (in.hasNextLine()) {
            String line = in.nextLine();

            line = line.substring(5).replace(": ", ":");
            String[] game = line.split(":");

            String[] rounds = game[1].replace("; ", ";").split(";");

            int maxRed = 0;
            int maxGreen = 0;
            int maxBlue = 0;

            for (String round : rounds) {

                String[] colors = round.replace(", ", ",").split(",");
                for (String color: colors) {
                    String[] values = color.split(" ");
                    if (values[1].equals("red") && Integer.parseInt(values[0]) > maxRed) {
                        maxRed = Integer.parseInt(values[0]);
                    } else if (values[1].equals("green") && Integer.parseInt(values[0]) > maxGreen) {
                        maxGreen = Integer.parseInt(values[0]);
                    } else if (values[1].equals("blue") && Integer.parseInt(values[0]) > maxBlue) {
                        maxBlue = Integer.parseInt(values[0]);
                    }
                }
            }

            sum += maxRed * maxGreen * maxBlue;
        }

        System.out.println(sum);
    }
}

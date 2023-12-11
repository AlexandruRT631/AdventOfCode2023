package day06;

import iDay.IDay;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Day06 implements IDay {
    @Override
    public long part1() throws IOException {
        File file = new File("src/day06/input.txt");
        Scanner in = new Scanner(file);

        String[] line1Values = in.nextLine().trim().replaceAll(" +", " ").split(" ");
        String[] line2Values = in.nextLine().trim().replaceAll(" +", " ").split(" ");

        int[] times = Arrays.stream(Arrays.copyOfRange(line1Values, 1, line1Values.length)).mapToInt(Integer::parseInt).toArray();
        int[] distances = Arrays.stream(Arrays.copyOfRange(line2Values, 1, line2Values.length)).mapToInt(Integer::parseInt).toArray();

        int[] waysToWin = new int[times.length];
        Arrays.fill(waysToWin, 0);
        for (int i = 0; i < times.length; i++) {
            for (int j = 0; j < times[i]; j++) {
                if ((times[i] - j) * j > distances[i]) {
                    waysToWin[i] += 1;
                }
            }
        }
        int ways = 1;
        for (int way : waysToWin) {
            ways *= way;
        }

        return ways;
    }

    @Override
    public long part2() throws IOException {
        File file = new File("src/day06/input.txt");
        Scanner in = new Scanner(file);

        String line1 = in.nextLine().trim().replaceAll(" +", " ");
        String line2 = in.nextLine().trim().replaceAll(" +", " ");
        int spacePos = line1.indexOf(" ");
        line1 = line1.substring(spacePos + 1).replace(" ", "");
        spacePos = line2.indexOf(" ");
        line2 = line2.substring(spacePos + 1).replace(" ", "");
        long time = Long.parseLong(line1);
        long distance = Long.parseLong(line2);

        long waysToWin = 0;
        for (long i = 0; i < time; i++) {
            if ((time - i) * i > distance) {
                waysToWin += 1;
            }
        }

        return waysToWin;
    }
}

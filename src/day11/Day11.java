package day11;

import iDay.IDay;
import utilities.CharMap;
import utilities.Util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Day11 implements IDay {
    @Override
    public long part1() throws IOException {
        return solve(2);
    }

    @Override
    public long part2() throws IOException {
        return solve(1000000);
    }

    private static long solve(int expansion) throws IOException {
        CharMap charMap = Util.getCharMap("src/day11/input.txt");
        char[][] map = charMap.map();
        int x = charMap.x();
        int y = charMap.y();

        int[] distancesX = new int[x];
        Arrays.fill(distancesX, 1);
        int[] distancesY = new int[y];
        Arrays.fill(distancesY, 1);

        for (int i = 0; i < x; i++) {
            boolean hasGalaxy = false;
            for (int j = 0; j < y; j++) {
                if (map[i][j] == '#') {
                    hasGalaxy = true;
                    break;
                }
            }
            if (!hasGalaxy) {
                distancesX[i] = expansion;
            }
        }
        for (int i = 0; i < y; i++) {
            boolean hasGalaxy = false;
            for (int j = 0; j < x; j++) {
                if (map[j][i] == '#') {
                    hasGalaxy = true;
                    break;
                }
            }
            if (!hasGalaxy) {
                distancesY[i] = expansion;
            }
        }

        List<GalaxyPos> galaxies = new ArrayList<>();
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if (map[i][j] == '#') {
                    galaxies.add(new GalaxyPos(i, j));
                }
            }
        }

        long sumOfDistances = 0;
        for (int i = 0; i < galaxies.size() - 1; i++) {
            for (int j = i + 1; j < galaxies.size(); j++) {
                int xStart = galaxies.get(i).x();
                int xEnd = galaxies.get(j).x();
                if (xStart > xEnd) {
                    int temp = xStart;
                    xStart = xEnd;
                    xEnd = temp;
                }
                while (xStart != xEnd) {
                    sumOfDistances += distancesX[xStart];
                    xStart++;
                }

                int yStart = galaxies.get(i).y();
                int yEnd = galaxies.get(j).y();
                if (yStart > yEnd) {
                    int temp = yStart;
                    yStart = yEnd;
                    yEnd = temp;
                }
                while (yStart != yEnd) {
                    sumOfDistances += distancesY[yStart];
                    yStart++;
                }
            }
        }

        return sumOfDistances;
    }
}

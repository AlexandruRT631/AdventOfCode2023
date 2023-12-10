package day10;

import iDay.IDay;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Day10 implements IDay {
    @Override
    public long part1() throws IOException {
        File file = new File("src/day10/input.txt");
        Scanner in = new Scanner(file);

        char[][] map = getMapFromInput(in);

        int[] sPos = getSPos(map);
        int sX = sPos[0];
        int sY = sPos[1];

        map[sX][sY] = getStartPipe(map, sX, sY);

        int startX = sX;
        int startY = sY;
        int prevX = sX;
        int prevY = sY;
        if (map[sX][sY] == '-') {
            sY++;
        } else if (map[sX][sY] == '|') {
            sX++;
        } else if (map[sX][sY] == 'L') {
            sY++;
        } else if (map[sX][sY] == 'J') {
            sY--;
        } else if (map[sX][sY] == '7') {
            sY--;
        } else {
            sY++;
        }

        long steps = 0;
        while (startX != sX || startY != sY) {
            steps++;
            if (prevX == sX - 1 && prevY == sY) {
                prevX = sX;
                if (map[sX][sY] == '|') {
                    sX++;
                } else if (map[sX][sY] == 'L') {
                    sY++;
                } else {
                    sY--;
                }
            } else if (prevX == sX + 1 && prevY == sY) {
                prevX = sX;
                if (map[sX][sY] == '|') {
                    sX--;
                } else if (map[sX][sY] == '7') {
                    sY--;
                } else {
                    sY++;
                }
            } else if (prevX == sX && prevY == sY - 1) {
                prevY = sY;
                if (map[sX][sY] == '-') {
                    sY++;
                } else if (map[sX][sY] == 'J') {
                    sX--;
                } else {
                    sX++;
                }
            } else {
                prevY = sY;
                if (map[sX][sY] == '-') {
                    sY--;
                } else if (map[sX][sY] == 'L') {
                    sX--;
                } else {
                    sX++;
                }
            }
        }

        return (steps + 1) / 2;
    }

    @Override
    public long part2() throws IOException {
        File file = new File("src/day10/input.txt");
        Scanner in = new Scanner(file);

        char[][] map = getMapFromInput(in);
        int[][] loop = new int[map.length][map[0].length];
        for (int[] ints : loop) {
            Arrays.fill(ints, -1);
        }

        int[] sPos = getSPos(map);
        int sX = sPos[0];
        int sY = sPos[1];

        map[sX][sY] = getStartPipe(map, sX, sY);

        loop[sX][sY] = 0;
        int startX = sX;
        int startY = sY;
        int prevX = sX;
        int prevY = sY;
        if (map[sX][sY] == '-') {
            sY++;
        } else if (map[sX][sY] == '|') {
            sX++;
        } else if (map[sX][sY] == 'L') {
            sY++;
        } else if (map[sX][sY] == 'J') {
            sY--;
        } else if (map[sX][sY] == '7') {
            sY--;
        } else {
            sY++;
        }

        while (startX != sX || startY != sY) {
            loop[sX][sY] = 0;
            if (prevX == sX - 1 && prevY == sY) {
                prevX = sX;
                if (map[sX][sY] == '|') {
                    sX++;
                } else if (map[sX][sY] == 'L') {
                    sY++;
                } else {
                    sY--;
                }
            } else if (prevX == sX + 1 && prevY == sY) {
                prevX = sX;
                if (map[sX][sY] == '|') {
                    sX--;
                } else if (map[sX][sY] == '7') {
                    sY--;
                } else {
                    sY++;
                }
            } else if (prevX == sX && prevY == sY - 1) {
                prevY = sY;
                if (map[sX][sY] == '-') {
                    sY++;
                } else if (map[sX][sY] == 'J') {
                    sX--;
                } else {
                    sX++;
                }
            } else {
                prevY = sY;
                if (map[sX][sY] == '-') {
                    sY--;
                } else if (map[sX][sY] == 'L') {
                    sX--;
                } else {
                    sX++;
                }
            }
        }

        boolean ok = false;
        for (int i = 0; i < loop.length && !ok; i++) {
            for (int j = 0; j < loop.length && !ok; j++) {
                if (loop[i][j] == 0) {
                    ok = true;
                    sX = i;
                    sY = j;
                }
            }
        }
        startX = sX;
        startY = sY;
        prevX = sX;
        prevY = sY;
        sY++;

        while (startX != sX || startY != sY) {
            if (prevX == sX - 1 && prevY == sY) {
                prevX = sX;
                if (loop[sX][sY - 1] != 0) {
                    loop[sX][sY - 1] = 2;
                }
                if (map[sX][sY] == '|') {
                    sX++;
                } else if (map[sX][sY] == 'L') {
                    if (sX < loop.length - 1 && loop[sX + 1][sY] != 0) {
                        loop[sX + 1][sY] = 2;
                    }
                    sY++;
                } else {
                    sY--;
                }
            } else if (prevX == sX + 1 && prevY == sY) {
                prevX = sX;
                if (loop[sX][sY + 1] != 0) {
                    loop[sX][sY + 1] = 2;
                }
                if (map[sX][sY] == '|') {
                    sX--;
                } else if (map[sX][sY] == '7') {
                    if (sX > 0 && loop[sX - 1][sY] != 0) {
                        loop[sX - 1][sY] = 2;
                    }
                    sY--;
                } else {
                    sY++;
                }
            } else if (prevX == sX && prevY == sY - 1) {
                prevY = sY;
                if (loop[sX + 1][sY] != 0) {
                    loop[sX + 1][sY] = 2;
                }
                if (map[sX][sY] == '-') {
                    sY++;
                } else if (map[sX][sY] == 'J') {
                    if (sY < loop[0].length - 1 && loop[sX][sY + 1] != 0) {
                        loop[sX][sY + 1] = 2;
                    }
                    sX--;
                } else {
                    sX++;
                }
            } else {
                prevY = sY;
                if (loop[sX - 1][sY] != 0) {
                    loop[sX - 1][sY] = 2;
                }
                if (map[sX][sY] == '-') {
                    sY--;
                } else if (map[sX][sY] == 'F') {
                    if (sY > 0 && loop[sX][sY - 1] != 0) {
                        loop[sX][sY - 1] = 2;
                    }
                    sX++;
                } else {
                    sX--;
                }
            }
        }

        for (int i = 0; i < loop.length; i++) {
            for (int j = 0; j < loop[i].length; j++) {
                if (loop[i][j] == 2) {
                    java.util.Queue<int[]> queue = new java.util.LinkedList<>();
                    queue.add(new int[]{i, j});
                    while (!queue.isEmpty()) {
                        int[] pos = queue.poll();
                        loop[pos[0]][pos[1]] = 2;
                        if (pos[0] > 0 && loop[pos[0] - 1][pos[1]] == -1) {
                            queue.add(new int[]{pos[0] - 1, pos[1]});
                        }
                        if (pos[0] < loop.length - 1 && loop[pos[0] + 1][pos[1]] == -1) {
                            queue.add(new int[]{pos[0] + 1, pos[1]});
                        }
                        if (pos[1] > 0 && loop[pos[0]][pos[1] - 1] == -1) {
                            queue.add(new int[]{pos[0], pos[1] - 1});
                        }
                        if (pos[1] < loop[0].length - 1 && loop[pos[0]][pos[1] + 1] == -1) {
                            queue.add(new int[]{pos[0], pos[1] + 1});
                        }
                    }
                }
            }
        }
        long tiles = 0;
        for (int[] line : loop) {
            for (int tile : line) {
                if (tile == 2) {
                    tiles++;
                }
            }
        }

        return tiles;
    }

    private static char[][] getMapFromInput(Scanner in) {
        java.util.ArrayList<char[]> map = new java.util.ArrayList<>();
        while (in.hasNextLine()) {
            map.add(in.nextLine().toCharArray());
        }
        return map.toArray(new char[0][0]);
    }

    private static int[] getSPos(char[][] map) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == 'S') {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{-1, -1};
    }

    private static char getStartPipe(char[][] map, int sX, int sY) {
        if (sX > 0 && sX < map.length - 1 &&
                (map[sX - 1][sY] == '|' || map[sX - 1][sY] == '7' || map[sX - 1][sY] == 'F') &&
                (map[sX + 1][sY] == '|' || map[sX + 1][sY] == 'J' || map[sX + 1][sY] == 'L')) {
            return '|';
        }
        if (sY > 0 && sY < map[0].length - 1 &&
                (map[sX][sY - 1] == '-' || map[sX][sY - 1] == 'F' || map[sX][sY - 1] == 'L') &&
                (map[sX][sY + 1] == '-' || map[sX][sY + 1] == '7' || map[sX][sY + 1] == 'J')) {
            return '-';
        }
        if (sX > 0 && sY < map[0].length - 1 &&
                (map[sX - 1][sY] == '|' || map[sX - 1][sY] == '7' || map[sX - 1][sY] == 'F') &&
                (map[sX][sY + 1] == '-' || map[sX][sY + 1] == '7' || map[sX][sY + 1] == 'J')) {
            return 'L';
        }
        if (sX > 0 && sY > 0 &&
                (map[sX - 1][sY] == '|' || map[sX - 1][sY] == '7' || map[sX - 1][sY] == 'F') &&
                (map[sX][sY - 1] == '-' || map[sX][sY - 1] == 'F' || map[sX][sY - 1] == 'L')) {
            return 'J';
        }
        if (sX < map.length - 1 && sY > 0 &&
                (map[sX + 1][sY] == '|' || map[sX + 1][sY] == 'J' || map[sX + 1][sY] == 'L') &&
                (map[sX][sY - 1] == '-' || map[sX][sY - 1] == 'F' || map[sX][sY - 1] == 'L')) {
            return '7';
        }
        return 'F';
    }
}

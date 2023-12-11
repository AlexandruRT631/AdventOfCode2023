package utilities;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Util {
    public static CharMap getCharMap(String path) throws IOException {
        File file = new File(path);
        Scanner in = new Scanner(file);

        char[][] map = new char[1000][1000];
        int x = 0;
        int y = 0;
        while (in.hasNextLine()) {
            y = 0;
            String line = in.nextLine();
            for (char c : line.toCharArray()) {
                map[x][y] = c;
                y++;
            }
            x++;
        }

        return new CharMap(map, x, y);
    }
}

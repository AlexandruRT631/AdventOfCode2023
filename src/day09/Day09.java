package day09;

import iDay.IDay;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Day09 implements IDay {
    @Override
    public long part1() throws IOException {
        File file = new File("src/day09/input.txt");
        Scanner in = new Scanner(file);
        long sum = 0;

        while(in.hasNextLine()) {
            List<Long> values = new java.util.ArrayList<>(Arrays.stream(in.nextLine().split(" ")).mapToLong(Long::parseLong).boxed().toList());
            while (values.stream().anyMatch(value -> value != 0)) {
                sum += values.get(values.size() - 1);
                for (int i = 0; i < values.size() - 1; i++) {
                    values.set(i, values.get(i + 1) - values.get(i));
                }
                values.remove(values.size() - 1);
            }
        }

        return sum;
    }

    @Override
    public long part2() throws IOException {
        File file = new File("src/day09/input.txt");
        Scanner in = new Scanner(file);
        long sum = 0;

        while(in.hasNextLine()) {
            int sign = 1;
            List<Long> values = new java.util.ArrayList<>(Arrays.stream(in.nextLine().split(" ")).mapToLong(Long::parseLong).boxed().toList());
            while (values.stream().anyMatch(value -> value != 0)) {
                sum += values.get(0) * sign;
                sign *= -1;
                for (int i = 0; i < values.size() - 1; i++) {
                    values.set(i, values.get(i + 1) - values.get(i));
                }
                values.remove(values.size() - 1);
            }
        }

        return sum;
    }
}

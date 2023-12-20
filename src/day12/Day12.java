package day12;

import iDay.IDay;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.Math.pow;

public class Day12 implements IDay {
    private final Map<Map.Entry<String, List<Integer>>, Long> cache = new HashMap<>();

    @Override
    public long part1() throws IOException {
        File file = new File("src/day12/input.txt");
        Scanner in = new Scanner(file);

        long sum = 0;

        while (in.hasNextLine()) {
            String[] lineValues = in.nextLine().split(" ");
            List<Integer> values = Arrays.stream(lineValues[1].split(",")).map(Integer::parseInt).toList();
            String line = lineValues[0].replaceAll("\\.+", ".");
            sum += backtrack(line, values);
        }

        return sum;
    }

    @Override
    public long part2() throws IOException {
        File file = new File("src/day12/input.txt");
        Scanner in = new Scanner(file);

        long sum = 0;
        int index = 0;

        while (in.hasNextLine()) {
            String[] lineValues = in.nextLine().split(" ");
            List<Integer> values = Arrays.stream(lineValues[1].split(",")).map(Integer::parseInt).toList();

            String newLine = (lineValues[0] + "?" + lineValues[0] + "?" + lineValues[0] + "?" + lineValues[0] + "?" + lineValues[0]).replaceAll("\\.+", ".");
            List<Integer> newValues = Stream.concat(values.stream(), values.stream()).collect(Collectors.toList());
            newValues = Stream.concat(newValues.stream(), newValues.stream()).collect(Collectors.toList());
            newValues = Stream.concat(newValues.stream(), values.stream()).collect(Collectors.toList());

            sum += backtrack(newLine, newValues);
        }

        return sum;
    }

    private long backtrack(String line, List<Integer> values) {
        if (values.size() == 0) {
            if (line.contains("#")) {
                return 0;
            }
            return 1;
        }
        if (line.isEmpty()) {
            return 0;
        }
        if (cache.containsKey(Map.entry(line, values))) {
            return cache.get(Map.entry(line, values));
        }

        long ways = 0;

        int value = values.get(0);
        List<Integer> newValues = values.subList(1, values.size());

        while (line.length() >= value) {
            String lineToCheck = line.substring(0, value);

            if (lineToCheck.matches("[#?]+")) {
                if (line.length() == value) {
                    if (newValues.size() == 0) {
                        ways++;
                    }
                } else if (line.charAt(value) != '#') {
                    long backtrackResult = backtrack(line.substring(value + 1), newValues);
                    ways += backtrackResult;
                    cache.put(Map.entry(line.substring(value + 1), newValues), backtrackResult);
                }
            }

            if (line.charAt(0) == '#') {
                break;
            } else {
                line = line.substring(1);
            }
        }
        return ways;
    }
}

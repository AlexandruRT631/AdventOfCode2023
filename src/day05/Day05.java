package day05;

import iDay.IDay;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Day05 implements IDay {
    @Override
    public long part1() throws IOException {
        File file = new File("src/day05/input.txt");
        Scanner in = new Scanner(file);

        String seedsLine = in.nextLine().substring(7);
        List<Long> locations = Arrays.stream(seedsLine.split(" ")).toList().stream().map(Long::parseLong).toList();

        in.nextLine();
        in.nextLine();
        String map = in.nextLine();
        List<Long> newLocations;

        while (in.hasNextLine()) {
            newLocations = new ArrayList<>(locations);
            while (!map.isEmpty()) {
                long[] seedToSoilArray = Arrays.stream(map.split(" ")).mapToLong(Long::parseLong).toArray();
                for (int i = 0; i < locations.size(); i++) {
                    if (locations.get(i) >= seedToSoilArray[1] && locations.get(i) < seedToSoilArray[1] + seedToSoilArray[2]) {
                        newLocations.set(i, seedToSoilArray[0] - seedToSoilArray[1] + locations.get(i));
                    }
                }
                if (in.hasNextLine()) {
                    map = in.nextLine();
                } else {
                    map = "";
                }
            }
            locations = new ArrayList<>(newLocations);
            if (!in.hasNextLine()) {
                break;
            }
            in.nextLine();
            map = in.nextLine();
        }

        return locations.stream().min(Long::compareTo).orElseThrow();
    }

    @Override
    public long part2() throws IOException {
        File file = new File("src/day05/input.txt");
        Scanner in = new Scanner(file);

        String seedsLine = in.nextLine().substring(7);
        Long[] locations = Arrays.stream(seedsLine.split(" ")).map(Long::parseLong).toArray(Long[]::new);
        List<Range> ranges = new ArrayList<>();
        for (int i = 0; i < locations.length; i +=2) {
            ranges.add(new Range(locations[i], locations[i] + locations[i + 1] - 1));
        }

        in.nextLine();
        in.nextLine();
        String line = in.nextLine();
        while (in.hasNextLine()) {
            List<Map> maps = new ArrayList<>();
            while (!line.isEmpty()) {
                long[] seedToSoilArray = Arrays.stream(line.split(" ")).mapToLong(Long::parseLong).toArray();
                maps.add(new Map(seedToSoilArray[1], seedToSoilArray[1] + seedToSoilArray[2] - 1, seedToSoilArray[0] - seedToSoilArray[1]));
                if (in.hasNextLine()) {
                    line = in.nextLine();
                } else {
                    line = "";
                }
            }

            ranges = transform(ranges, maps);

            if (!in.hasNextLine()) {
                break;
            }
            in.nextLine();
            line = in.nextLine();
        }

        long min = Long.MAX_VALUE;

        for (Range r : ranges) {
            if (r.start() < min) {
                min = r.start();
            }
        }

        return min;
    }

    private static List<Range> transform(List<Range> ranges, List<Map> maps) {
        List<Range> result = new ArrayList<>();
        for (Range r : ranges) {
            List<Range> tempRanges = new ArrayList<>();
            tempRanges.add(r);
            for (Map m : maps) {
                List<Range> newTempRanges = new ArrayList<>();
                for (Range tempRange: tempRanges) {
                    if (tempRange.start() < m.sourceStart() && tempRange.end() <= m.sourceEnd() && m.sourceStart() < tempRange.end()) {
                        newTempRanges.add(new Range(tempRange.start(), m.sourceStart() - 1));
                        result.add(new Range(m.sourceStart() + m.increment(), tempRange.end() + m.increment()));
                    }
                    else if (m.sourceStart() < tempRange.start() && m.sourceEnd() <= tempRange.end() && tempRange.start() < m.sourceEnd()) {
                        result.add(new Range(tempRange.start() + m.increment(), tempRange.end() + m.increment()));
                        newTempRanges.add(new Range(m.sourceEnd() + 1, tempRange.end()));
                    }
                    else if (m.sourceStart() <= tempRange.start() && tempRange.end() <= m.sourceEnd()) {
                        result.add(new Range(tempRange.start() + m.increment(), tempRange.end() + m.increment()));
                    }
                    else if (tempRange.start() <= m.sourceStart() && m.sourceEnd() <= tempRange.end()) {
                        newTempRanges.add(new Range(tempRange.start(), m.sourceStart() - 1));
                        result.add(new Range(m.sourceStart() + m.increment(), m.sourceEnd() + m.increment()));
                        newTempRanges.add(new Range(m.sourceEnd() + 1, tempRange.end()));
                    }
                    else {
                        newTempRanges.add(tempRange);
                    }
                }
                tempRanges = new ArrayList<>(newTempRanges);
            }
            result.addAll(tempRanges);
        }
        return result;
    }
}

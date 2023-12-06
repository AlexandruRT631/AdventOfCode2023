package day5;

import iDay.IDay;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Day5 implements IDay {
    private static List<Range> transform(List<Range> ranges, List<Map> maps) {
        List<Range> result = new ArrayList<>();
        for (Range r : ranges) {
            List<Range> tempRanges = new ArrayList<>();
            tempRanges.add(r);
            for (Map m : maps) {
                List<Range> newTempRanges = new ArrayList<>();
                for (Range tempRange: tempRanges) {
                    if (tempRange.getStart() < m.getSourceStart() && tempRange.getEnd() <= m.getSourceEnd() && m.getSourceStart() < tempRange.getEnd()) {
                        newTempRanges.add(new Range(tempRange.getStart(), m.getSourceStart() - 1));
                        result.add(new Range(m.getSourceStart() + m.getIncrement(), tempRange.getEnd() + m.getIncrement()));
                    }
                    else if (m.getSourceStart() < tempRange.getStart() && m.getSourceEnd() <= tempRange.getEnd() && tempRange.getStart() < m.getSourceEnd()) {
                        result.add(new Range(tempRange.getStart() + m.getIncrement(), tempRange.getEnd() + m.getIncrement()));
                        newTempRanges.add(new Range(m.getSourceEnd() + 1, tempRange.getEnd()));
                    }
                    else if (m.getSourceStart() <= tempRange.getStart() && tempRange.getEnd() <= m.getSourceEnd()) {
                        result.add(new Range(tempRange.getStart() + m.getIncrement(), tempRange.getEnd() + m.getIncrement()));
                    }
                    else if (tempRange.getStart() <= m.getSourceStart() && m.getSourceEnd() <= tempRange.getEnd()) {
                        newTempRanges.add(new Range(tempRange.getStart(), m.getSourceStart() - 1));
                        result.add(new Range(m.getSourceStart() + m.getIncrement(), m.getSourceEnd() + m.getIncrement()));
                        newTempRanges.add(new Range(m.getSourceEnd() + 1, tempRange.getEnd()));
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

    @Override
    public void part1() throws IOException {
        File file = new File("src/day5/input.txt");
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

        locations.stream().min(Long::compareTo).ifPresent(System.out::println);
    }

    @Override
    public void part2() throws IOException {
        File file = new File("src/day5/input.txt");
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
            if (r.getStart() < min) {
                min = r.getStart();
            }
        }

        System.out.println(min);
    }
}

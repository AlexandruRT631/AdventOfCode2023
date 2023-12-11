package day08;

import iDay.IDay;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Day08 implements IDay {
    @Override
    public long part1() throws IOException {
        File file = new File("src/day08/input.txt");
        Scanner in = new Scanner(file);
        Dictionary<String, LeftRight> node = new Hashtable<>();
        int instruction = 0;
        int totalInstructions = 0;
        char[] instructions = new char[1000];
        String current = "AAA";
        long steps = 0;

        String line = in.nextLine();
        for (char c : line.toCharArray()) {
            instructions[totalInstructions] = c;
            totalInstructions++;
        }
        in.nextLine();
        while (in.hasNextLine()) {
            String[] lineValues = in.nextLine().split(" = ");
            String[] directions = lineValues[1].replace("(", "").replace(")", "").replace(" ", "").split(",");
            node.put(lineValues[0], new LeftRight(directions[0], directions[1]));
        }

        while (!current.equals("ZZZ")) {
            if (instructions[instruction] == 'R') {
                current = node.get(current).getRight();
            } else {
                current = node.get(current).getLeft();
            }

            instruction++;
            if (instruction == totalInstructions) {
                instruction = 0;
            }
            steps++;
        }

        return steps;
    }

    @Override
    public long part2() throws IOException {
        File file = new File("src/day08/input.txt");
        Scanner in = new Scanner(file);
        int instruction = 0;
        int totalInstructions = 0;
        char[] instructions = new char[1000];
        Dictionary<String, LeftRight> node = new Hashtable<>();
        List<String> currentNode = new ArrayList<>();
        List<Long> step = new ArrayList<>();
        long steps = 0;

        String line = in.nextLine();
        for (char c : line.toCharArray()) {
            instructions[totalInstructions] = c;
            totalInstructions++;
        }
        in.nextLine();
        while (in.hasNextLine()) {
            String[] lineValues = in.nextLine().split(" = ");
            String[] directions = lineValues[1].replace("(", "").replace(")", "").replace(" ", "").split(",");
            if (lineValues[0].toCharArray()[2] == 'A') {
                currentNode.add(lineValues[0]);
            }
            node.put(lineValues[0], new LeftRight(directions[0], directions[1]));
        }

        for (String current : currentNode) {
            instruction = 0;
            steps = 0;
            while (current.toCharArray()[2] != 'Z') {
                if (instructions[instruction] == 'L') {
                    current = node.get(current).getLeft();
                } else {
                    current = node.get(current).getRight();
                }

                instruction++;
                if (instruction == totalInstructions) {
                    instruction = 0;
                }
                steps++;
            }
            step.add(steps);
        }

        steps = step.get(0);
        for (int i = 1; i < step.size(); i++) {
            steps = getLeastCommonMultiple(steps, step.get(i));
        }

        return steps;
    }

    private long getLeastCommonMultiple(long a, long b) {
        long tempA = a;
        long tempB = b;

        while (tempB > 0) {
            long temp = tempB;
            tempB = tempA % tempB;
            tempA = temp;
        }

        return a * (b / tempA);
    }
}

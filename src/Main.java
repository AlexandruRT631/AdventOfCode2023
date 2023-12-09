import day1.Day1;
import day2.Day2;
import day3.Day3;
import day4.Day4;
import day5.Day5;
import day6.Day6;
import day7.Day7;
import day8.Day8;
import iDay.IDay;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        IDay day = new Day8();
        System.out.println(day.part2());
    }
}
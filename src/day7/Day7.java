package day7;

import iDay.IDay;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day7 implements IDay {
    @Override
    public long part1() throws IOException {
        File file = new File("src/day7/input.txt");
        Scanner in = new Scanner(file);
        List<Hand> hands = new ArrayList<>();

        while(in.hasNextLine()) {
            String[] lineValues = in.nextLine().split(" ");

            char[] cardValues = lineValues[0].toCharArray();
            Card[] cards = new Card[5];
            for (int i = 0; i < 5; i++) {
                cards[i] = Card.valueOf("_" + cardValues[i]);
            }

            hands.add(new Hand(cards, Integer.parseInt(lineValues[1])));
        }

        return computeWinnings(hands);
    }

    @Override
    public long part2() throws IOException {
        File file = new File("src/day7/input.txt");
        Scanner in = new Scanner(file);
        List<Hand> hands = new ArrayList<>();

        while(in.hasNextLine()) {
            String[] lineValues = in.nextLine().split(" ");

            char[] cardValues = lineValues[0].toCharArray();
            Card[] cards = new Card[5];
            for (int i = 0; i < 5; i++) {
                if (cardValues[i] == 'J') {
                    cards[i] = Card._JOKER;
                }
                else {
                    cards[i] = Card.valueOf("_" + cardValues[i]);
                }
            }

            hands.add(new Hand(cards, Integer.parseInt(lineValues[1])));
        }

        return computeWinnings(hands);
    }

    private long computeWinnings(List<Hand> hands) {
        long sum = 0;
        hands.sort((h1, h2) -> {
            if (h1.getHandType() == h2.getHandType()) {
                Card[] h1Cards = h1.getCards();
                Card[] h2Cards = h2.getCards();

                for (int i = 0; i < 5; i++) {
                    if (h1Cards[i] != h2Cards[i]) {
                        return h1Cards[i].compareTo(h2Cards[i]);
                    }
                }

                return 0;
            }
            return h1.getHandType().compareTo(h2.getHandType());
        });

        for (int i = 0; i < hands.size(); i++) {
            sum += (long) hands.get(i).getBid() * (i + 1);
        }
        return sum;
    }
}

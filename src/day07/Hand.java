package day07;

import java.util.Arrays;

public class Hand {
    private final Card[] cards;
    private final HandType handType;
    private final int bid;

    public Hand(Card[] cards, int bid) {
        this.cards = cards;
        this.bid = bid;
        this.handType = ComputeHandType(cards);
    }

    public Card[] getCards() {
        return cards;
    }

    public HandType getHandType() {
        return handType;
    }

    public int getBid() {
        return bid;
    }

    private HandType ComputeHandType(Card[] cards) {
        Card[] tempCards = Arrays.stream(cards).sorted().toArray(Card[]::new);
        int nrOfJokers = 0;
        for (Card card : tempCards) {
            if (card == Card._JOKER) {
                nrOfJokers++;
            }
        }

        if (tempCards[0] == tempCards[1] && tempCards[1] == tempCards[2] && tempCards[2] == tempCards[3] && tempCards[3] == tempCards[4]) {
            return HandType.FiveOfAKind;
        }

        if ((tempCards[0] == tempCards[1] && tempCards[1] == tempCards[2] && tempCards[2] == tempCards[3]) ||
                (tempCards[1] == tempCards[2] && tempCards[2] == tempCards[3] && tempCards[3] == tempCards[4])) {
            if (nrOfJokers == 1 || nrOfJokers == 4)
                return HandType.FiveOfAKind;
            return HandType.FourOfAKind;
        }

        if ((tempCards[0] == tempCards[1] && tempCards[2] == tempCards[3] && tempCards[3] == tempCards[4]) ||
                (tempCards[0] == tempCards[1] && tempCards[1] == tempCards[2] && tempCards[3] == tempCards[4])){
            if (nrOfJokers != 0) {
                return HandType.FiveOfAKind;
            }
            return HandType.FullHouse;
        }

        if ((tempCards[0] == tempCards[1] && tempCards[1] == tempCards[2]) ||
                (tempCards[1] == tempCards[2] && tempCards[2] == tempCards[3]) ||
                (tempCards[2] == tempCards[3] && tempCards[3] == tempCards[4])) {
            if (nrOfJokers == 1 || nrOfJokers == 3) {
                return HandType.FourOfAKind;
            }
            return HandType.ThreeOfAKind;
        }

        if ((tempCards[0] == tempCards[1] && tempCards[2] == tempCards[3]) ||
                (tempCards[0] == tempCards[1] && tempCards[3] == tempCards[4]) ||
                (tempCards[1] == tempCards[2] && tempCards[3] == tempCards[4])) {
            if (nrOfJokers == 2) {
                return HandType.FourOfAKind;
            }
            if (nrOfJokers == 1) {
                return HandType.FullHouse;
            }
            return HandType.TwoPair;
        }

        if (tempCards[0] == tempCards[1] || tempCards[1] == tempCards[2] || tempCards[2] == tempCards[3] || tempCards[3] == tempCards[4]) {
            if (nrOfJokers == 1 || nrOfJokers == 2) {
                return HandType.ThreeOfAKind;
            }
            return HandType.OnePair;
        }

        if (nrOfJokers == 1) {
            return HandType.OnePair;
        }
        return HandType.HighCard;
    }
}

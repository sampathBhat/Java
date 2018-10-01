package blackJack;

import java.util.Collections;
import java.util.Queue;

import blackJack.cards.Card;

import java.util.PriorityQueue;

public final class DisplayCards {

	public static final String[] CARD_NAMES = { "joker", "ace", "two", "three", "four", "five", "six", "seven", "eight",
			"nine", "ten", "jack", "queen", "king" };
	public static final String[] CARD_NAMES_PLURAL = { "jokers", "aces", "twos", "threes", "fours", "fives", "sixes",
			"sevens", "eights", "nines", "tens", "jacks", "queens", "kings" };

	public static String cardsToString(final Queue<Card> cards) {
		if (cards.size() == 0)
			return "no cards IDK how";

		StringBuilder sb = new StringBuilder();
		PriorityQueue<Card> cardsCopy = new PriorityQueue<Card>(cards);
		int numberofSameCards = 1;
		while (cardsCopy.size() > 0) {
			Card card = cardsCopy.remove();
			numberofSameCards += Collections.frequency(cardsCopy, card);
			for (int j = 1; j < numberofSameCards; j++) {
				cardsCopy.remove(card);
			}

			sb.append(numberofSameCards + " "
					+ (numberofSameCards == 1 ? CARD_NAMES[card.getCardIndex()] : CARD_NAMES_PLURAL[card.getCardIndex()]));

			switch (cardsCopy.size()) {
			case 0:
				break;
			case 1:
				sb.append(" and ");
				break;
			default:
				sb.append(", ");

			}
		}
		if (cards.size() == 1)
			sb.append(" and a hidden card");
		return sb.toString();
	}
}
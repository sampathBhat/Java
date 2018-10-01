package blackJack.cards;

public enum Card implements Comparable<Card> {

	ACE(1, 1), KING(13, 10), QUEEN(12, 10), JACK(11, 10), TEN(10, 10), NINE(9, 9), EIGHT(8, 8), SEVEN(7, 7), SIX(6, 6),
	FIVE(5, 5), FOUR(4, 4), THREE(3, 3), TWO(2, 2), JOKER(0, 0);

	private final int cardIndex;
	private final int cardValue;
	private Card(final int index, final int value) {
		this.cardIndex = index;
		this.cardValue = value;
	}

	public int getCardIndex() {
		return cardIndex;
	}

	public int getCardValue() {
		return cardValue;
	}
}
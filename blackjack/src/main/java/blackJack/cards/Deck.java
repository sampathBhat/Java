package blackJack.cards;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Collections;

public class Deck {
    private LinkedList<Card> cards = new LinkedList<Card>();
    public Deck() {
        fillDeckWithAllCards();
    }
    public LinkedList<Card> getCards() {
        return cards;
    }
    public Card drawNextCard() {
        try {
            return cards.remove();
        } catch (NoSuchElementException e) {
            fillDeckWithAllCards();
            return drawNextCard();
        }
    }
    private void fillDeckWithAllCards() {
        for(int i = 0; i < 4; i++) {
            for(Card card : Card.values()) {
                cards.add(card);
            }
        }
        shuffle();
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }
}
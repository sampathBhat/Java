package blackJack;

import java.util.PriorityQueue;
import java.util.Queue;

import blackJack.cards.Card;

import java.util.List;
import java.util.ArrayList;

public class Hand {
    private Queue<Card> cards = new PriorityQueue<Card>();
    public static final int MAX_TOTAL_LIMIT=21;

    public void addCard(Card card) {
        cards.add(card);
    }
    public int getTotal() {
    	int total=0;
        for(Card card : cards) {
        	total+=card.getCardValue();
            
            if (card.getCardValue() == 1 && total == MAX_TOTAL_LIMIT-10)total+=10;
        }
        return total;
    }

    public Queue<Card> getCards() {
        return cards;
    }
}
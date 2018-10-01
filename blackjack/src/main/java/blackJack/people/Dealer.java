package blackJack.people;

import java.util.Random;

import blackJack.BlackJack;
import blackJack.Hand;
import blackJack.cards.Card;

public class Dealer extends Person {
    private static final int SOFT_BUST = 16;

    public Dealer(final BlackJack blackJack) {
        super("Dealer", blackJack);
    }

 
    @Override
    public Hand getOpenHand() {
        Hand hand = new Hand();
        hand.addCard(super.getHand().getCards().peek());
        return hand;
    }
}
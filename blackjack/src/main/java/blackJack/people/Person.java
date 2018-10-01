package blackJack.people;

import blackJack.BlackJack;
import blackJack.Hand;
import blackJack.cards.Card;

public class Person {
    private final BlackJack blackjack;
    private final String name;
    private Hand hand = new Hand();
    private boolean busted=false;

   
     public Person(final String name, final BlackJack blackjack) {
        this.name = name;
        this.blackjack = blackjack;
    }

   
    public String getName() {
        return name;
    }

    public Hand getHand() {
        return hand;
    }

    public Hand getOpenHand() {
        return hand;
    }

    public void receiveCard(final Card card) {
        hand.addCard(card);
    }

    public String autoRespondWantToHit() {
        return "";
    }

    public void wantsToHit() {
        blackjack.dealCard(this);
    }
	public boolean isBusted() {
		return busted;
	}


	public void setBusted(boolean busted) {
		this.busted = busted;
	}
}

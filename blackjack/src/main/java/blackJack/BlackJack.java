package blackJack;

import java.util.ArrayList;
import java.util.List;

import blackJack.cards.Card;
import blackJack.cards.Deck;
import blackJack.people.Dealer;
import blackJack.people.Person;
import blackJack.people.Player;

public class BlackJack {
	private Person[] players = new Person[2];
	private Deck deck = new Deck();

	public Person[] getPlayers() {
		return players;
	}

	public Deck getDeck() {
		return deck;
	}

	public static BlackJack initializeGame() {
		return new BlackJack();
	}

	public void addPlayers(final String playerName) {
		players[0] = new Dealer(this);
		players[1] = new Player(playerName, this);

	}

	public void dealCard(Person person) {
		Card card = deck.drawNextCard();
		person.receiveCard(card);
	}

	public void dealInitialCards() {
		for (Person person : players) {
			dealCard(person);
			dealCard(person);
		}
	}

	public Person getWinner() {
		int highest = 0;
		Person topPlayer = players[0];
		for (Person player : players) {
			int total = player.getHand().getTotal();
			if (total >= highest && total <= 21) {
				highest = total;
				topPlayer = player;
			}
		}
		return topPlayer;
	}
}

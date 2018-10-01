package blackJack;

import java.util.Queue;
import java.util.Scanner;

import blackJack.cards.Card;
import blackJack.people.Person;

public class TerminalView {
	private BlackJack blackjack;
	private Scanner keyboard = new Scanner(System.in);

	public TerminalView(BlackJack blackjack) {
		this.blackjack = blackjack;
	}

	public void showInitialHand(Person person) {
		Hand hand = person.getOpenHand();
		Queue<Card> cards = hand.getCards();
		System.out.println(person.getName() + " has " + DisplayCards.cardsToString(cards) + ".");
		return;
	}

	public void showHand(Person person) {
		Hand hand = person.getHand();
		Queue<Card> cards = hand.getCards();
		System.out.println(person.getName() + " has " + DisplayCards.cardsToString(cards) + ".");
		if (hand.getTotal() > Hand.MAX_TOTAL_LIMIT) {
			person.setBusted(true);
			System.out.println(person.getName() + " is Busted");
			return;
		}
		System.out.println("That makes a total of " + hand.getTotal() + ".");
	}

	public String askForTurn(Person person) {
		if (person.getName().equalsIgnoreCase("Dealer"))
			return "";
		System.out.println(person.getName() + " do you want to 'hit' or 'stand'? ");
		return keyboard.nextLine();
	}

	public void displayTotal(Person person) {
		int total = person.getHand().getTotal();
		if (person.isBusted()) {
			System.out.println(person.getName() + " is busted.");
		} else {
			System.out.println(person.getName() + "'s total is " + total + ".");
		}
	}

	public void showWinner(Person person) {
		System.out.println(person.getName() + " is the winner!");
	}

	public boolean wantToPlayAgain() {
		System.out.println("Do you want to play again? 'y'/'n': ");
		String input = keyboard.nextLine();
		if (input.equals("y") || input.equals("n")) {
			return (input.equals("y"));
		}
		return wantToPlayAgain();
	}

	public static void welcome() {

		System.out.println("Welcome to a new Blackjack game!");
	}

	public static void thanksForPlaying() {
		System.out.println("Thanks for playing!");
	}

}
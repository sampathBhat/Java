package blackJack;

import blackJack.people.Person;

public class BlackjackGame {
	private BlackJack blackjack;
	private TerminalView view;

	public static void main(String[] args) {
		BlackjackGame game = new BlackjackGame();
		game.run();
	}

	private void run() {
		do {
			playOneGame();
		} while (view.wantToPlayAgain());
		TerminalView.thanksForPlaying();
	}

	private void playOneGame() {
		TerminalView.welcome();
		blackjack = BlackJack.initializeGame();
		blackjack.addPlayers("Player");

		blackjack.dealInitialCards();

		view = new TerminalView(blackjack);

		for (Person person : blackjack.getPlayers()) {
			view.showInitialHand(person);
		}
		for (Person person : blackjack.getPlayers()) {
			letPersonPlayItsTurns(person);
		}
		for (Person person : blackjack.getPlayers()) {
			view.displayTotal(person);
			
		}
		Person winner = blackjack.getWinner();
		view.showWinner(winner);
	}

	private void letPersonPlayItsTurns(Person person) {
		String userInput = view.askForTurn(person);
		boolean playOn = true;
		while (playOn) {
			if (userInput.equalsIgnoreCase("hit")) {
				person.wantsToHit();
				view.showHand(person);
				if(person.isBusted()) {
					break;
				}
				userInput=view.askForTurn(person);
			}else {playOn=false;}
		}
	}

}
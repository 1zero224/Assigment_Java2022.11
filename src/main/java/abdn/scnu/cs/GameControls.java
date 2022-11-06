package abdn.scnu.cs;

public interface GameControls {
	AbstractGameGrid getPlayersGrid();

	AbstractGameGrid getOpponentssGrid();

	void exitGame(String input);

	boolean checkVictory();

	void playRound(String inputCoordinates);
}

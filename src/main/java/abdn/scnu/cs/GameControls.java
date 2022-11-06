package abdn.scnu.cs;

public interface GameControls {
	AbstractGameGrid getPlayersGrid();

	AbstractGameGrid getOpponentssGrid();

	void exitGame();

	void checkVictory();

	void playRound(String inputCoordinates);
}

package abdn.scnu.cs;

//Provide a definition of the methods required for the game to run
public interface GameControls {
	AbstractGameGrid getPlayersGrid();

	AbstractGameGrid getOpponentssGrid();

	void exitGame(String input);

	boolean checkVictory();

	void playRound(String inputCoordinates);
}

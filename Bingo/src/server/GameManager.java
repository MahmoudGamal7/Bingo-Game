package server;

import javax.microedition.io.StreamConnection;


public class GameManager {

	private static GameManager gameManager = null;
	
	private GameManager(){
		//TODO implement your constructor here
	}
	
	public static GameManager getGameManager() {
		return (gameManager == null) ? gameManager = new GameManager() : gameManager;
	}
	
	
	/*
	 * This method adds a new Gameplayer and notify all other players with this new player
	 */
	public void addNewPlayer(StreamConnection con,String name){
		//TODO implement your method here
	}

}

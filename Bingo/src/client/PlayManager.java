package client;

import javax.microedition.io.StreamConnection;


public class PlayManager {

	private static PlayManager playManager = null;
	
	private PlayManager(){
		//TODO implement your constructor here
	}
	
	public static PlayManager getPlayManager() {
		return (playManager == null) ? playManager = new PlayManager() : playManager;
	}
	
	
	/*
	 * This method adds current player to an existing game. The method saves the connection 
	 * and name of the server, and may ask the server to send any needed information.
	 */
	public void joinGame(StreamConnection con,String name){
		//TODO implement your method here
	}

}

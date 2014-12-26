package com;

import java.io.IOException;

import javax.bluetooth.BluetoothStateException;
import javax.bluetooth.LocalDevice;
import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;
import javax.microedition.io.StreamConnectionNotifier;

import client.PlayManager;

public class ClientConnectionManager extends ConnectionManager {
	
	private static ConnectionManager connectionManager = null;

	/** Creates a new instance of ConnectionManager */
	private ClientConnectionManager() {
		try {
			LocalDevice local = LocalDevice.getLocalDevice();
			deviceName = local.getFriendlyName();
			discoveryAgent = local.getDiscoveryAgent();
			discoveryServer = new DiscoveryServer(discoveryAgent);
			// LoggingForm.log("inside connection manager constructor");
		} catch (BluetoothStateException e) {
		}
		
		// uncomment this if and only if you need to make the client waiting until added by game
		// initiator. If the client is the one who will join the game, which is the typical situation, 
		// then leave this line commented. It is highly recommended you don't use this scenario.
		
		//this.start();
		
	}

	/**
	 * This is the factory method to get a reference to this class
	 * 
	 * @return reference to object from this class or create a new object and
	 *         return it's reference
	 */
	public static ConnectionManager getServer() {
		return (connectionManager == null) ? connectionManager = new ClientConnectionManager()
				: connectionManager;
	}
	
	// Use this method if and only if you need to make the client waiting until added by 
	// game initiator. If the client is the one who will join the game, which is the typical
	// situation, then leave this line commented. It is highly recommended you don't use this scenario.
	public void run() {
		String url = "btspp://localhost:" + uuid.toString()
				+ ";name=" + deviceName;
		try {
			StreamConnectionNotifier notifier = (StreamConnectionNotifier) Connector
					.open(url);
			System.out.println("ConnectionClient Ready to Recieve Connections");
			while (!serverStoped) {
				System.out.println("before accept");
				StreamConnection con = notifier.acceptAndOpen();
				System.out.println("after accept");			
				String name = getPlayerFriendlyName(con);
				System.out.println("Server " + name + " is Now Connected");
				PlayManager.getPlayManager().joinGame(con, name);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

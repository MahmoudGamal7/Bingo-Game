package com;

import java.io.IOException;

import javax.bluetooth.BluetoothStateException;
import javax.bluetooth.LocalDevice;
import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;
import javax.microedition.io.StreamConnectionNotifier;

import server.GameManager;

public class ServerConnectionManager extends ConnectionManager {

	private static ConnectionManager connectionManager = null;

	/** Creates a new instance of ConnectionManager */
	private ServerConnectionManager() {
		try {
			LocalDevice local = LocalDevice.getLocalDevice();
			deviceName = local.getFriendlyName();
			discoveryAgent = local.getDiscoveryAgent();
			discoveryServer = new DiscoveryServer(discoveryAgent);
			// LoggingForm.log("inside connection manager constructor");
			this.start();
		} catch (BluetoothStateException e) {
		}
	}

	/**
	 * This is the factory method to get a reference to this class
	 * 
	 * @return reference to object from this class or create a new object and
	 *         return it's reference
	 */
	public static ConnectionManager getServer() {
		return (connectionManager == null) ? connectionManager = new ServerConnectionManager()
				: connectionManager;
	}

	
	public void run() {
		String url = "btspp://localhost:" + uuid.toString()
				+ ";name=SnakeServer";
		try {
			StreamConnectionNotifier notifier = (StreamConnectionNotifier) Connector
					.open(url);
			System.out.println("ConnectionServer Ready to Recieve Connections");
			while (!serverStoped) {
				System.out.println("before accept");
				StreamConnection con = notifier.acceptAndOpen();
				System.out.println("after accept");			
				String name = getPlayerFriendlyName(con);
				System.out.println("Client " + name + " is Now Connected");
				GameManager.getGameManager().addNewPlayer(con, name);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}

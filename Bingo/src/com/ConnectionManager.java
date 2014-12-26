package com;

import java.io.IOException;
import java.util.Vector;

import javax.bluetooth.BluetoothStateException;
import javax.bluetooth.DiscoveryAgent;
import javax.bluetooth.RemoteDevice;
import javax.bluetooth.UUID;
import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;
import javax.microedition.io.StreamConnectionNotifier;

import server.GameManager;

/**
 * 
 * 		This is a singleton class that manages the establishment of
 *         bluetooth connection for each new player and Alocate the required
 *         resources for him. the user can take a reference from this class by
 *         calling the factory method {@link getServer}
 */
public abstract class ConnectionManager extends Thread{

	// service UUID
	protected final static UUID uuid = new UUID(
			"102030405060708090A0B0C0D0E0F010", false);// SPP_Server specific
//			"0000000000001000800000805F9B34FB", false);// SPP_Server specific
//			"00000000-0000-1000-8000-00805F9B34FB", false);// SPP_Server specific

	protected boolean serverStoped = false;
	protected DiscoveryAgent discoveryAgent;
	protected DiscoveryServer discoveryServer;
	public static String deviceName;
//	private Command searchCommand = new Command("Search", Command.SCREEN, 0);
//	private Command exitCommand = new Command("Exit", Command.EXIT, 1);
	

	/**
	 * get the name of the bluetooth device that want to connect with the server
	 * and consider this name as the name of the player.
	 * @param con the connection object between the server and the client
	 * @return the name of the new Player
	 * */
	protected String getPlayerFriendlyName(StreamConnection con) {
		String name = "";
		try {
			name = RemoteDevice.getRemoteDevice(con).getFriendlyName(false);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return name;
	}

	/**
	 * call to this method will clear the previous search history
	 * and start new one, after calling this method you can obtain the list of devices
	 * by calling discoveredDevices() method.
	 * You should wait a reasonable amount of time until searching devices is completed,
	 * before calling discoveredDevices() method. otherwise, you may get an empty vector.
	 */
	public void startDeviceSearch() {
		
		discoveryServer.searchForDevices();

	}
	
	/**
	 * call to this method will return discovered devices vector.
	 * You should wait a reasonable amount of time until searching devices is completed,
	 * then call this method. otherwise, if you called it immediately after startDeviceSearch()
	 * you may get an empty vector
	 */
	public Vector discoveredDevices(){
		return discoveryServer.getDeviceDisoveredVector();
	}
	
	/**
	 * connect a device using its index in the returned devices vector
	 */
	public void connectToDevice(int index){
		discoveryServer.selectDevice(index);
	}
	
	/**
	 * check if the server is started or not
	 * @return true if the server is started 
	 * */
	public boolean isStarted() {
		return !serverStoped;
	}
	
	/**
	 * this method is used to sutdown the server 
	 * */
	public void shutDownServer() {
		serverStoped=true;
	}
	
}

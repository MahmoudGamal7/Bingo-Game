package com;

import java.io.IOException;
import java.util.Vector;

import javax.bluetooth.BluetoothStateException;
import javax.bluetooth.DeviceClass;
import javax.bluetooth.DiscoveryAgent;
import javax.bluetooth.DiscoveryListener;
import javax.bluetooth.RemoteDevice;
import javax.bluetooth.ServiceRecord;
import javax.bluetooth.UUID;
import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;


/**
 * This class is the server for any search response (device search or service
 * search)
 */
public class DiscoveryServer implements DiscoveryListener {
	
	private Vector deviceDisoveredVector;
	private Vector deviceDisoveredVectorFriendlyNames;
	private DiscoveryAgent agent;
	
	public DiscoveryServer(DiscoveryAgent agent) {
		// deviceDiscoveredList = new List("Device Discovered List",
		// List.IMPLICIT);
		deviceDisoveredVector = new Vector();
		deviceDisoveredVectorFriendlyNames = new Vector();
		this.agent = agent;
	}

	/**
	 * @see javax.bluetooth.DiscoveryListener#deviceDiscovered(RemoteDevice,
	 *      DeviceClass) appends the discovered device to the device discovered vector
	 */
	public void deviceDiscovered(RemoteDevice remoteDevice, DeviceClass arg1) {
		
		//TODO You may add some notification to GUI here so that you show the discovered device name
		// immediately on the screen

		try {
			deviceDisoveredVector.addElement(remoteDevice);
			deviceDisoveredVectorFriendlyNames.addElement(remoteDevice.getFriendlyName(false));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void inquiryCompleted(int arg0) {

	}

	public void serviceSearchCompleted(int arg0, int arg1) {

	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see javax.bluetooth.DiscoveryListener#servicesDiscovered(int,
	 *      ServiceRecord[])
	 *      try to connect to the discovered service and then start the data transfere server
	 */
	public void servicesDiscovered(int arg0, ServiceRecord[] serviceRecord) {
		// LoggingForm.log("finished services search discovery");
		for (int i = 0; i < serviceRecord.length; i++) {
			String url = serviceRecord[i].getConnectionURL(
					ServiceRecord.NOAUTHENTICATE_NOENCRYPT, false);
			// LoggingForm.log(url +" service discovered");
			System.out.println(url);
			try {
				StreamConnection connection = (StreamConnection) Connector
						.open(url);
				// LoggingForm.log("Snake service discovered");
			} catch (Throwable e) {
				
			}
		}

	}
	
	/*
	 * Selects a device by its index in the dicovered devices vector
	 */
	public void selectDevice(int n){
		try {
			agent.cancelInquiry(this);
			// LoggingForm.log("after search devices inquiery canceled");
			UUID[] uuidSet = new UUID[1];
			uuidSet[0] = new UUID("102030405060708090A0B0C0D0E0F010", false);
//			uuidSet[0] = new UUID("0000000000001000800000805F9B34FB", false);
			agent.searchServices(null, uuidSet,
					(RemoteDevice) deviceDisoveredVector
							.elementAt(n), this);
			// LoggingForm.log("after search services invoked");
		} catch (Exception e) {
			
		}
	}

	public void searchForDevices(){
		agent.cancelInquiry(this);
		deviceDisoveredVector.removeAllElements();
		deviceDisoveredVectorFriendlyNames.removeAllElements();
		try {
			agent.startInquiry(DiscoveryAgent.GIAC, this);
		} catch (BluetoothStateException e) {
			
		}
	}

	public Vector getDeviceDisoveredVector() {
		return deviceDisoveredVectorFriendlyNames;
	}
}

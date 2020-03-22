package de.oaknetwork.oaknetlink.masterserver.network.udp;

import java.net.DatagramPacket;
import java.util.ArrayList;
import java.util.List;

public class UDPClientHelper {
	
	private static List<UDPClient> connectedClients = new ArrayList<UDPClient>();
	
	public static UDPClient getClientByPacket(DatagramPacket dpacket) {
		UDPClient result = null;
		for(UDPClient client:connectedClients) {
			if(client.udpAdress()==dpacket.getAddress()&&client.udpPort()==dpacket.getPort())
				result=client;
		}
		if(result==null) {
			result = new UDPClient(dpacket.getAddress(), dpacket.getPort());
			connectedClients.add(result);
			System.out.println(result.udpAdress().toString() + " connected via UDP.");			
		}
		return result;
	}

}

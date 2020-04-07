package de.oaknetwork.oaknetlink.api.network.udp.packets;

import java.util.HashMap;
import java.util.Map;

import de.oaknetwork.oaknetlink.api.mcinterface.DummyServer;
import de.oaknetwork.oaknetlink.api.network.udp.UDPEndpoint;
import de.oaknetwork.oaknetlink.api.network.utils.BytePackage;
import de.oaknetwork.oaknetlink.api.server.ServerHelper;

/**
 * This packet capsules Minecraft packets 
 * 
 * @author Fabian Fila
 */
public class UDPMinecraftDataPacket extends UDPPacket{

	@Override
	public Map<String, Class<?>> expectedTypes() {
		Map<String , Class<?>> expectedTypes = new HashMap<String, Class<?>>();
		expectedTypes.put("data", BytePackage.class);
		return expectedTypes();
	}

	@Override
	protected void processPacket(Map<String, Object> data, UDPEndpoint sender) {
		BytePackage bytePackage = (BytePackage) data.get("data");
		if(ServerHelper.isServerRunning) {
			sender.dummyClient().sendPacket(bytePackage.data);
		}else {
			DummyServer.sendPacket(bytePackage.data);
		}
		
	}

	public static void sendPacket(UDPEndpoint host, BytePackage bytePackage) {
		Map<String, Object> data = new HashMap<String, Object>(); 
		data.put("data", bytePackage);
		sendPacket(host, bytePackage);
	}

}
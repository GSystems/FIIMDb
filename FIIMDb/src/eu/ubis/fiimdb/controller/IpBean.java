package eu.ubis.fiimdb.controller;

import eu.ubis.fiimdb.model.Ip;
import eu.ubis.fiimdb.service.IpService;
import eu.ubis.fiimdb.service.ServiceFactory;

public class IpBean {
	
	private static IpService ipService = ServiceFactory.getIpService();
	private static String ip;
	
	public String getIp() {
		return ip;
	}
	
	public void saveIp(Ip ip, String username) {
		ipService.saveIp(ip, username);
	}
}

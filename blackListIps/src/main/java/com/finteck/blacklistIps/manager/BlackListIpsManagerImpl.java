package com.finteck.blacklistIps.manager;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 
 * @author salmah the model which contain balcklist IPs to block there connection.
 */
@Service
public class BlackListIpsManagerImpl implements BlackListManager {

	public static final Logger logger = LoggerFactory.getLogger(BlackListIpsManagerImpl.class);

	private HashMap<String, String> blackListIPs;

	@Override
	public void AddBlacklistIp(String ipAddress) {
		getBlackListIPs().put(ipAddress, ipAddress);
	}

	@Override
	public void AddBlacklistIps(List<String> ipsAddresses) {
		for (String ip : ipsAddresses) {
			getBlackListIPs().put(ip, ip);

		}
	}

	/**
	 * search for specific ip on map , if exist , return true to block connection , else it safe IP and enable connection
	 * 
	 * @param ip
	 * @return
	 */
	public boolean isBlacklistIP(String ip) {
		return getBlackListIPs().get(ip) != null ? true : false;
	}

	@Override
	public void deleteBlacklistIp(String ipAddress) {
		getBlackListIPs().remove(ipAddress);
	}

	@Override
	public void deleteBlacklistIps(List<String> ipsAddresses) {
		for (String ip : ipsAddresses) {
			getBlackListIPs().remove(ip);
		}
	}

	@Override
	public Set<String> listBlacklistIps() {
		return getBlackListIPs().keySet();
	}

	@Override
	public String getExistIP(String ip) {
		return getBlackListIPs().get(ip);
	}

	// ///////////////////////////////setters and getters

	public HashMap<String, String> getBlackListIPs() {
		if (blackListIPs == null) {
			blackListIPs = new HashMap<String, String>();
		}
		return blackListIPs;
	}

	public void setBlackListIPs(HashMap<String, String> blackListIPs) {
		this.blackListIPs = blackListIPs;
	}

}

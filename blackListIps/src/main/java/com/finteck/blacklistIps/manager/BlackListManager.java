package com.finteck.blacklistIps.manager;

import java.util.List;
import java.util.Set;

public interface BlackListManager {

	
	public void AddBlacklistIp(String ipAddress);
	
	public void AddBlacklistIps(List<String> ipsAddresses);
	
	public void deleteBlacklistIp(String ipAddress);
	
	public void deleteBlacklistIps(List<String> ipsAddresses);
	
	public Set<String> listBlacklistIps();
	
	public String getExistIP(String ip) ;
	
	public boolean isBlacklistIP(String ipAddress);
}

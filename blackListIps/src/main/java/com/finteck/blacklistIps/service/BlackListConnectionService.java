package com.finteck.blacklistIps.service;

import java.util.List;
import java.util.Set;

public interface BlackListConnectionService {
	
	public void AddBlacklistIp(String ipAddress);
	
	public void AddBlacklistIps(List<String> ipsAddresses);
	
	public void deleteBlacklistIp(String ipAddress);
	
	public void deleteBlacklistIps(List<String> ipsAddresses);
	
	public Set<String> getBlacklistIps();
	
	public String getExistIP(String ip) ;
	
	public boolean isBlacklistIP(String ipAddress);
	

}

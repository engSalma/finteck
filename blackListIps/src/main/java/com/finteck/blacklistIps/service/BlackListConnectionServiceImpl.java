package com.finteck.blacklistIps.service;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finteck.blacklistIps.manager.BlackListIpsManagerImpl;

@Service("BlackListConnectionService")
public class BlackListConnectionServiceImpl implements BlackListConnectionService {

	public static final Logger logger = LoggerFactory.getLogger(BlackListConnectionServiceImpl.class);
	@Autowired
	BlackListIpsManagerImpl blackListIpsManagerImpl;

	@Override
	public void AddBlacklistIp(String ipAddress) {
		logger.info("Starting Adding the Ip = ", ipAddress + " into our blacklist Ips");
		blackListIpsManagerImpl.AddBlacklistIp(ipAddress);
		logger.info("Finish Adding the Ip = ", ipAddress);
	}

	@Override
	public void AddBlacklistIps(List<String> ipsAddresses) {
		logger.info("Starting Adding batch of Ips = ", ipsAddresses + " into our blacklist Ips");
		blackListIpsManagerImpl.AddBlacklistIps(ipsAddresses);
		logger.info("Finish Adding batch of Ips = ", ipsAddresses);
	}

	@Override
	public void deleteBlacklistIp(String ipAddress) {
		logger.info("Starting deleteing Ip = ", ipAddress + " from our blacklist Ips");
		blackListIpsManagerImpl.deleteBlacklistIp(ipAddress);
		logger.info("Finish deleteing Ip= ", ipAddress);
	}

	@Override
	public void deleteBlacklistIps(List<String> ipsAddresses) {
		logger.info("Starting deleting batch of Ips = ", ipsAddresses + " into our blacklist Ips");
		blackListIpsManagerImpl.deleteBlacklistIps(ipsAddresses);
		logger.info("Finish deleting batch of Ips = ", ipsAddresses);
	}

	@Override
	public Set<String> getBlacklistIps() {
		logger.info("Starting fetch blackListIps  ");
		Set<String> blacklistIps = blackListIpsManagerImpl.listBlacklistIps();
		logger.info("Finish deleting batch of Ips = ", blacklistIps);
		return blacklistIps;
	}

	@Override
	public String getExistIP(String ip) {
		logger.info("Starting fetch blackListIp  " + ip);
		String blacklistIp = blackListIpsManagerImpl.getExistIP(ip);
		logger.info("Finish get ip = ", blacklistIp);
		return blacklistIp;
	}

	@Override
	public boolean isBlacklistIP(String ipAddress) {
		logger.info("Starting check ip = ", ipAddress + " against our blacklist Ips");
		boolean isBlackListIp = blackListIpsManagerImpl.isBlacklistIP(ipAddress);
		logger.info("Finish check  Ips = ", ipAddress + " and it isBlackList =" + isBlackListIp);
		return isBlackListIp;
	}

}

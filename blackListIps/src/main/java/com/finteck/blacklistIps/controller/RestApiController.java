package com.finteck.blacklistIps.controller;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.finteck.blacklistIps.service.BlackListConnectionServiceImpl;
import com.finteck.blacklistIps.util.CustomErrorType;

@RestController
@RequestMapping("/api")
public class RestApiController {

	public static final Logger logger = LoggerFactory.getLogger(RestApiController.class);

	@Autowired
	BlackListConnectionServiceImpl blackListConnectionService; // Service which will do all data retrieval/manipulation work

	// -------------------Retrieve All blackListIps---------------------------------------------

	@RequestMapping(value = "/blacklistIP/", method = RequestMethod.GET)
	public ResponseEntity<Set<String>> listAllBlackListIps() {
		Set<String> blackListIps = blackListConnectionService.getBlacklistIps();
		if (blackListIps.isEmpty()) {
			return new ResponseEntity(new CustomErrorType("no configurable blackListIps , please add ips filtering first "), HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Set<String>>(blackListIps, HttpStatus.OK);
	}

	// -------------------get blackListIp------------------------------------------

	@RequestMapping(value = "/blacklistIP/{ip}", method = RequestMethod.GET)
	public ResponseEntity<String> getIpAddress(@PathVariable("ip") String ip) {
		logger.info("check ip ", ip);
		blackListConnectionService.getExistIP(ip);
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	// -------------------checkIPAddress against blackListIps------------------------------------------

	@RequestMapping(value = "/blacklistIP/isBlackListIp/{ip}", method = RequestMethod.GET)
	public ResponseEntity<String> isBlackListIp(@PathVariable("ip") String ip) {
		logger.info("check ip ", ip);
		Boolean isBlackListIP = blackListConnectionService.isBlacklistIP(ip);
		String responsre = "ip =" + ip + " isBlackListIP =" + isBlackListIP;
		return new ResponseEntity<String>(responsre, HttpStatus.OK);
	}

	// -------------------Add blacklist IP-------------------------------------------

	@RequestMapping(value = "/blacklistIP", method = RequestMethod.POST)
	public ResponseEntity<?> addBlackListIp(@RequestBody String ip, UriComponentsBuilder ucBuilder) {
		logger.info("Add to blacklist ip : ", ip);

		if (blackListConnectionService.getExistIP(ip) != null) {
			logger.error("this IP is already Added to blackList", ip);
			return new ResponseEntity(new CustomErrorType("Unable to Add ip " + ip + " already exist."), HttpStatus.CONFLICT);
		}
		blackListConnectionService.AddBlacklistIp(ip);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/blacklistIP/{ip}").buildAndExpand(ip).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

	// ------------------- Add list of blacklistIps -----------------------------

	@RequestMapping(value = "/blacklistIP/addBlackListIps/", method = RequestMethod.POST)
	public ResponseEntity<?> addBlackListIps(@RequestBody List<String> ips, UriComponentsBuilder ucBuilder) {
		logger.info("Adding list of blacklist ips" + ips);
		blackListConnectionService.AddBlacklistIps(ips);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/blacklistIP/").buildAndExpand().toUri());
		return new ResponseEntity<Set<String>>(headers, HttpStatus.CREATED);

	}

	// ------------------- Delete blackList IP-----------------------------------------

	@RequestMapping(value = "/blacklistIP/{ip}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteIP(@PathVariable("ip") String ip) {
		logger.info("Fetching & Deleting Black list Ip ", ip);

		if (blackListConnectionService.getExistIP(ip) == null) {
			logger.error("Unable to delete. ip , {} not found.", ip);
			return new ResponseEntity(new CustomErrorType("Unable to delete. ip , " + ip + " not found."), HttpStatus.NOT_FOUND);
		}
		blackListConnectionService.deleteBlacklistIp(ip);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	// ------------------- Delete blackList IP-----------------------------------------

	@RequestMapping(value = "/blacklistIP", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteAll() {
		logger.info("delete All blackList Ips ");
		blackListConnectionService.deleteAll();
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	// ------------------- Delete list of blacklistIps -----------------------------

	@RequestMapping(value = "/blacklistIP/deleteblackListIps/", method = RequestMethod.POST)
	public ResponseEntity<?> deleteblackListIps(@RequestBody List<String> ips, UriComponentsBuilder ucBuilder) {
		logger.info("Deleting list of blacklist ips" + ips);
		blackListConnectionService.deleteBlacklistIps(ips);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
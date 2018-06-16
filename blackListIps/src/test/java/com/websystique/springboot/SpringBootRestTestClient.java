package com.websystique.springboot;
 
import java.net.URI;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.web.client.RestTemplate;
 

public class SpringBootRestTestClient {
 
    public static final String REST_SERVICE_URI = "http://localhost:8080/blacklistIps/api";
    static List<String> Addedlistips ;
	static List<String> deletedListIps;
    static{
    	Addedlistips = new ArrayList<String>();
    	for (int i = 0; i < 50; i++) {
    		Addedlistips.add("196.192.112.1"+i);
		}
    	
    	deletedListIps = new ArrayList<String>();
    	for (int i = 0; i < 16; i++) {
    		deletedListIps.add("196.192.112.1"+i);
		}
    	
    }
    /* GET */
    @SuppressWarnings("unchecked")
    private static void listAllIps(){
        System.out.println("Testing listAllIps API-----------");
         
        RestTemplate restTemplate = new RestTemplate();
        List<LinkedHashMap<String, String>> blackListIps = restTemplate.getForObject(REST_SERVICE_URI+"/blacklistIP/", List.class);
         
        if(blackListIps!=null){
                System.out.println("black list ips = "+blackListIps);;
            
        }else{
            System.out.println("No user exist----------");
        }
    }
     
    /* GET */
    private static void getIpAddress(){
        System.out.println("Testing get IP API----------");
        RestTemplate restTemplate = new RestTemplate();
        String ip = restTemplate.getForObject(REST_SERVICE_URI+"/blacklistIP/196.192.112.115", String.class);
        System.out.println(ip);
    }
    
    /* GET */
    private static void checkIPAddress(){
        System.out.println("Testing get IP API----------");
        RestTemplate restTemplate = new RestTemplate();
        Boolean isBlackList = restTemplate.getForObject(REST_SERVICE_URI+"/blacklistIP/isBlackListIp/196.192.112.115", Boolean.class);
        System.out.println(isBlackList);
    }
     
    /* POST */
    private static void AddBlackListIP() {
        System.out.println("Testing Adding BlackList API----------");
        RestTemplate restTemplate = new RestTemplate();
        URI uri = restTemplate.postForLocation(REST_SERVICE_URI+"/blacklistIP/","196.192.112.115", String.class);
        System.out.println("Location : "+uri.toASCIIString());
    }
 
    /* Post */
    private static void AddBlackListIPs() {
        System.out.println("Testing Add batch of blackListIps API----------");
        RestTemplate restTemplate = new RestTemplate();
        URI uri = restTemplate.postForLocation(REST_SERVICE_URI+"/blacklistIP/addBlackListIps/", Addedlistips, String.class);
        System.out.println("Location : "+uri.toASCIIString());
    }
 
    /* DELETE */
    private static void deleteIp() {
        System.out.println("Testing delete User API----------");
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(REST_SERVICE_URI+"/blacklistIP/196.192.112.115");
    }
 
 
    /* DELETE */
    private static void deletelistIps() {
        System.out.println("Testing all delete Users API----------");
        RestTemplate restTemplate = new RestTemplate();
		restTemplate.delete(REST_SERVICE_URI + "/blacklistIP/deleteblackListIps/");
    }
 
    public static void main(String args[]){
       listAllIps();
       AddBlackListIPs();
       checkIPAddress();
       deleteIp();
       checkIPAddress();
       AddBlackListIP();
       getIpAddress();
       deletelistIps();
       listAllIps();
    }
}
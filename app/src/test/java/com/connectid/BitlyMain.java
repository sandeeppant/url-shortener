package com.connectid;

public class BitlyMain {
	public static void main(String[] args) {
		BitlyAPI.initializeProperties();
		String bitlinkShortURL = "bit.ly/3IYlvxI";
		String groupId = BitlyAPI.getGroups();
		System.out.println("Group Id: " + groupId);
		String access_token = BitlyAPI.getAccessToken();
		System.out.println("Access Token: " + access_token);
		String longURL = BitlyAPI.getExpandURL(access_token, bitlinkShortURL);
		System.out.println("Long URL: " + longURL);
	}
}

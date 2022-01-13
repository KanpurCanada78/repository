package com.app.utility;

import java.util.Random;



public class GeneralUtility {
	/***********
	 * Method generate random text
	 * @return
	 */
	public  static String randonText() {
		String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		StringBuilder salt = new StringBuilder();
		Random rnd = new Random();
		while (salt.length() < 18) { 
			int index = (int) (rnd.nextFloat() * SALTCHARS.length());
			salt.append(SALTCHARS.charAt(index));
		}
        String saltStr = salt.toString();
        System.out.println("Random String"+saltStr);
       return saltStr;

    }

}

package com.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
//Idea is to read through the lines, store the last CSUIP and latest price. Print both when a new CSUIP is encountered.
public class ClosingCusip {
	
	private static void printClosingCusip(String path) throws IOException {
		//Print File Path to distinguish CUSIPs for particular file
		System.out.println("File: "+path);
		BufferedReader br = null;
		//Pattern to check if CUSIP is alphanumeric and length is 8
		String pattern= "^[a-zA-Z0-9]+{8}$";
		//Pattern to not consider 8 digits numbers as CUSIP
		String patternNumOnly= "^[0-9]*$";
		try {
			//Use Buffered reader to read lines without having all in memory
			br = Files.newBufferedReader(Paths.get(path));
			String lastCusip = null;
			String latestPrice = null;
			String line = br.readLine();
			while(line != null) {
				
				//Check if line is CUSIP
				if(line.matches(pattern) && !line.matches(patternNumOnly)) {
					if(lastCusip != null) {
						//Print latest price and last CUSIP, if current line is CUSIP
						System.out.println(lastCusip + " - " + latestPrice);						
					}
					//Reset last CUSIP to store latest CSUIP and latest price to null
					lastCusip = line;
					latestPrice = null;
				}else {
					//If not CUSIP, then just set latest price to price as these are sorted
					latestPrice = line;
				}
				line = br.readLine();
			}
			//If file is blank, print No CSUIP present
			if(lastCusip != null)
				System.out.println(lastCusip + " - " + latestPrice);
			else
				System.out.println("No CUSIPs present");
			
		} finally {
			//Close br
			if(br != null)
				br.close();				
		}
		
	}

	public static void main(String[] args) {
		try {
			//Case 1: Multiple CSUIPs and prices combinations
			printClosingCusip("cusip.txt");
			//Case 2: Blank file
			printClosingCusip("cusip_blank.txt");
			//Case 3: CSUIP with no prices
			printClosingCusip("cusip_noprices.txt");
			//Case 4: CSUIP with a 8 digit price
			printClosingCusip("cusip_8digit_price.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

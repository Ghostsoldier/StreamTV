package de.ro.inf.streamtv;

import java.util.Arrays;

public class SenderTest {

	public static void main(String[] args) {
		
		/*
		 *  Test class for understanding the use of the methods.
		 *  Important: Channels are distinct in their position in the channel list, which starts at 0 and ends at getXMLLength()
		 */
		
		System.out.println("TV:\n-----------------------------");
		
		for (int i = 0; i<Sender.getXMLLength(); i++) {
			if (Sender.getType(i).equals("TV") == true) {
					
				System.out.println(Sender.getType(i));
				System.out.println(Sender.getName(i));
				System.out.println(Arrays.toString(TVSender.getLink(i)) + "\n");
				
			}
			
		}
		
		System.out.println("Radio:\n-----------------------------");
		
		for (int i = 0; i<Sender.getXMLLength(); i++) {
			if (Sender.getType(i).equals("Radio") == true) {
					
				System.out.println(Sender.getType(i));
				System.out.println(Sender.getName(i));
				System.out.println(Arrays.toString(RadioSender.getLink(i)));
				
			}
			
		}
		
	}
	
}
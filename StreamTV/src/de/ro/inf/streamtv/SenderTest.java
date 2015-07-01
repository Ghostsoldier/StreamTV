package de.ro.inf.streamtv;

import java.util.Arrays;

/**
 * Test class for understanding how the methods from Sender, TVSender and
 * RadioSender work, and also to get a nicely parsed output of the links.xml
 * file in the console Note: Channels are distinct in their position within the
 * channel list, which starts at 0 and ends at getXMLLength(), not with their
 * name
 * 
 * @author Nico Welles
 *
 */
public class SenderTest {

	/**
	 * Main method for testing the XML file input and to see how it is parsed
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		/*
		 * Test class for understanding the use of the methods. Important:
		 * Channels are distinct in their position within the channel list,
		 * which starts at 0 and ends at getXMLLength(), not with their name
		 */

		System.out.println("TV-Sender:\n--------------------------------------------------");

		for (int i = 0; i < Sender.getXMLLength(); i++) {
			if (Sender.getType(i).equals("TV") == true) {

				System.out.println("Nummer: " + i);
				System.out.println(Sender.getType(i));
				System.out.println(Sender.getName(i));
				System.out.println(Arrays.toString(TVSender.getLink(i)) + "\n");

			}

		}

		System.out.println("Radiosender:\n--------------------------------------------------");

		for (int i = 0; i < Sender.getXMLLength(); i++) {
			if (Sender.getType(i).equals("Radio") == true) {

				System.out.println("Nummer: " + i);
				System.out.println(Sender.getType(i));
				System.out.println(Sender.getName(i));
				System.out.println(Arrays.toString(RadioSender.getLink(i)) + "\n");

			}

		}

	}

}
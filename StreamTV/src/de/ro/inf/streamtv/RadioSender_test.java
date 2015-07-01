package de.ro.inf.streamtv;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * JUnit testing class for RadioSender
 * 
 * @author Nico Welles
 *
 */
public class RadioSender_test {

	@Test
	public void testGetLink() {

		for (int i = 0; i < Sender.getXMLLength(); i++) {

			// Tests if the link Array has exactly two elements, one for
			// bitrate and one for the link (For the new system where every
			// channel has only one link)
			assertTrue("Array has other than two elements on channel number " + i, RadioSender.getLink(i).length == 2);

		}

	}

}

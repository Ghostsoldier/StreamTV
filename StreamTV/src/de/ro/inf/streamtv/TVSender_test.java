package de.ro.inf.streamtv;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * JUnit testing class for TVSender
 * 
 * @author Nico Welles
 *
 */
public class TVSender_test {

	@Test
	public void testGetLink() {

		for (int i = 0; i < Sender.getXMLLength(); i++) {

			// Tests if the link Array has exactly two elements, one for
			// resolution and one for the link (For the new system where every
			// channel has only one link)
			assertTrue("Array has other than two elements on channel number " + i, TVSender.getLink(i).length == 2);

		}

	}

}

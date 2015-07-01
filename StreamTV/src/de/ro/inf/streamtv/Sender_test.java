package de.ro.inf.streamtv;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * JUnit testing class for Sender
 * 
 * @author Nico Welles
 *
 */
public class Sender_test {

	@Test
	public void testParseXML() {

		// Tests if the Document is null
		assertNotNull("Document is null", Sender.parseXML());

	}

	@Test
	public void testGetXMLLength() {

		// Tests if links.xml contains any channels
		assertFalse("No channels in list", Sender.getXMLLength() == 0);

	}

	@Test
	public void testGetType() {

		// Tests if every channel in links.xml has either TV or Radio as type
		for (int i = 0; i < Sender.getXMLLength(); i++) {

			assertTrue("Wrong channel type on number " + i,
					(Sender.getType(i).equals("TV") || Sender.getType(i).equals("Radio")));

		}

	}

	@Test
	public void testGetName() {

		// Tests if every channel has a name
		for (int i = 0; i < Sender.getXMLLength(); i++) {

			assertNotSame("No channel name on number " + i, "", Sender.getName(i));

		}

	}

}

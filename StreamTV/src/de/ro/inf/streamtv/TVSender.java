package de.ro.inf.streamtv;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * 
 * Subclass of Sender for handling the links of only TV channels
 * 
 * @author Nico Welles
 *
 */
public class TVSender extends Sender {

	/**
	 * An array of all the links of the channel with their resolution in front
	 * Example: [LD, http://daserste.../master.m3u8, HD,
	 * http://ard.../teststream.m3u8]
	 */
	static String[] link;

	/**
	 * Returns all the links of the channel with their resolution in front.
	 * Array length is made variable through first using ArrayList, adding
	 * content, and then converting to Array. At the moment this is not
	 * necessary because of a lack of publicly acessible HDTV stream links, but
	 * has been left in because it is still functional and allows reintroducing
	 * multiple links per channel in future updates.
	 * 
	 * @param channelNumber
	 *            The number of the channel in the channel list. The minimum
	 *            number is 0, maximum number is found through getXMLLength()
	 * @return An array of links with their resolution in front, at the moment
	 *         only the first link and resolution is used by the GUI
	 */
	public static String[] getLink(int channelNumber) {

		List<String> linkArrayList = new ArrayList<String>();

		Document doc = parseXML();

		NodeList nList = doc.getElementsByTagName("sender");

		Node nNode = nList.item(channelNumber);

		Element eElement = (Element) nNode;

		// Goes through all <link> elements in the links.xml file, adding its
		// resolution and the link itself to the ArrayList
		for (int i = 0; i < eElement.getElementsByTagName("link").getLength(); i++) {

			linkArrayList.add(((Element) eElement.getElementsByTagName("link").item(i)).getAttribute("resolution"));

			linkArrayList.add(eElement.getElementsByTagName("link").item(i).getTextContent());

		}

		// Converting the ArrayList to Array to be returned by the method
		link = new String[linkArrayList.size()];
		linkArrayList.toArray(link);

		return link;

	}

}

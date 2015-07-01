package de.ro.inf.streamtv;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class RadioSender extends Sender {
	
	/**
	 * An array of all the links of the channel with their bitrate in front
	 * Example:	[128, http://streams.br.de/bayern3_2.m3u]
	 */
	static String[] link;
	
	/**
	 * Returns all the links of the channel with their bitrate in front. Array length is made variable 
	 * through first using ArrayList, adding content, and then converting to Array. At the moment this is 
	 * not necessary because of a lack of publicly acessible high quality radio stream links, but has been left in because
	 * it is still functional and allows reintroducing multiple links per channel in future updates.
	 * @param channelNumber The number of the channel in the channel list. The minimum number is 0, maximum number is found through getXMLLength()
	 * @return An array of links with their bitrate in front, at the moment only the first link and bitrate is used by the GUI
	 */
	public static String[] getLink(int channelNumber) {
		
		List<String> linkArrayList = new ArrayList<String>();
		
		Document doc =  parseXML();
		
		NodeList nList = doc.getElementsByTagName("sender");
		
		Node nNode = nList.item(channelNumber);
		
		Element eElement = (Element) nNode;
		
		//Goes through all <link> elements in the links.xml file, adding its bitrate and the link itself to the ArrayList
		for (int i = 0; i < eElement.getElementsByTagName("link").getLength(); i++) {
			
			linkArrayList.add(((Element) eElement.getElementsByTagName("link").item(i)).getAttribute("bitrate"));
			
			linkArrayList.add(eElement.getElementsByTagName("link").item(i).getTextContent());
			
		}
		
		//Converting the ArrayList to Array to be returned by the method
		link = new String[linkArrayList.size()];
		linkArrayList.toArray(link);
		
		return link;
		
	}

}

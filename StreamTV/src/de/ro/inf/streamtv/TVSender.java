package de.ro.inf.streamtv;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class TVSender extends Sender {
	
	/**
	 * An array of all the links of the channel with their resolution (for TV) or bitrate (for radio) in front
	 * Example:	TV:		[LD, http://daserste.../master.m3u8, HD, http://ard.../teststream.m3u8]
	 * 			Radio:	[128, http://streams.br.de/bayern3_2.m3u]
	 */
	static String[] link;
	
	/**
	 * Returns all the links of the channel with their resolution (for TV) or bitrate (for radio) in front. Array length is made variable 
	 * through first using ArrayList, adding content, and then converting to Array
	 * @param channelNumber The number of the channel in the channel list. The maximum channel number is found through getXMLLength()
	 * @return An array of links with their resolution in front
	 */
	public static String[] getLink(int channelNumber) {
		
		List<String> linkArrayList = new ArrayList<String>();
		
		Document doc =  parseXML();
		
		NodeList nList = doc.getElementsByTagName("sender");
		
		Node nNode = nList.item(channelNumber);
		
		Element eElement = (Element) nNode;
		
		for (int i = 0; i < eElement.getElementsByTagName("link").getLength(); i++) {
		
			linkArrayList.add(((Element) eElement.getElementsByTagName("link").item(i)).getAttribute("resolution"));
		
			linkArrayList.add(eElement.getElementsByTagName("link").item(i).getTextContent());
		
		}
			
		link = new String[linkArrayList.size()];
		
		linkArrayList.toArray(link);
		
		return link;
		
	}

}

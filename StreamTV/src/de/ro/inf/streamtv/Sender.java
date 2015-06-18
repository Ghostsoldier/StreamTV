package de.ro.inf.streamtv;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Sender {

	/**
	 * Type of the channel, can be either "TV" or "Radio".
	 */
	static String type;
	
	/**
	 * Name of the channel
	 */
	static String name;
	
	/**
	 * The method to parse the XML file into a Document that can be processed.
	 * ATTENTION: The other methods of Sender and TV/RadioSender that need this already contain it!
	 * @return The parsed channel links XML file as a Document
	 */
	public static Document parseXML() {
		
		//DocumentBuilder and DocumentBuilderFactory initialised
		DocumentBuilder dBuilder = null;
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		Document doc = null;
		
		try {
			
			dBuilder = dbFactory.newDocumentBuilder();
			
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		
		try {
			
			//Parses the links.xml file into the document
			doc = dBuilder.parse(new FileInputStream("links.xml"));
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//Normalizes the parsed content. This is recommended when working with the DOM XML parser
		doc.getDocumentElement().normalize();
		
		return doc;
		
	}
	
	/**
	 * Counts the amount of channels of both types
	 * @return An int number of channels
	 */
	public static int getXMLLength () {
		
		Document doc =  parseXML();
		
		NodeList nList = doc.getElementsByTagName("sender");
		
		int length = nList.getLength();
		
		return length;
		
	}
	
	/**
	 * Returns the type of the channel, either "TV" or "Radio"
	 * @param channelNumber The number of the channel in the channel list. The maximum channel number is found through getXMLLength()
	 * @return The type of the channel
	 */
	public static String getType(int channelNumber) {
		
		Document doc =  parseXML();
		
		NodeList nList = doc.getElementsByTagName("sender");
		
		Node nNode = nList.item(channelNumber);
		
		Element eElement = (Element) nNode;
		
		type = eElement.getElementsByTagName("type").item(0).getTextContent();
		
		return type;
		
	}
	
	/**
	 * Returns the name of the channel
	 * @param channelNumber The number of the channel in the channel list. The maximum channel number is found through getXMLLength()
	 * @return The name of the channel
	 */
	public static String getName(int channelNumber) {
		
		Document doc =  parseXML();
		
		NodeList nList = doc.getElementsByTagName("sender");
		
		Node nNode = nList.item(channelNumber);
		
		Element eElement = (Element) nNode;
		
		name = eElement.getElementsByTagName("name").item(0).getTextContent();
		
		return name;
		
	}
	
}

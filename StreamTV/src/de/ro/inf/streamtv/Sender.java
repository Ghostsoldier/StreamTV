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

/**
 * Class for handling the channels from the links.xml file
 * 
 * @author Nico Welles
 *
 */
public class Sender {

	/**
	 * Type of the channel, can be either "TV" or "Radio"
	 */
	static String type;

	/**
	 * Name of the channel
	 */
	static String name;

	/**
	 * The method to parse the XML file into a Document that can be processed
	 * 
	 * @return The parsed channel links.xml file as a document. Document = null
	 *         if links.xml not found.
	 * @throws ParserConfigurationException
	 * @throws FileNotFoundException
	 * @throws SAXException
	 * @throws IOException
	 */
	public static Document parseXML() {

		// DocumentBuilder and DocumentBuilderFactory for the returned Document
		DocumentBuilder dBuilder = null;
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		Document doc = null;

		try {

			dBuilder = dbFactory.newDocumentBuilder();

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}

		try {

			// Parses the links.xml file into the Document
			doc = dBuilder.parse(new FileInputStream("links.xml"));

		} catch (FileNotFoundException e) {
			return null;
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Normalizes the parsed content. This is recommended when working with
		// the DOM XML parser
		doc.getDocumentElement().normalize();

		return doc;

	}

	/**
	 * The method for counting the amount of channels of both types ("TV" and
	 * "Radio")
	 * 
	 * @return The number of channels. 0 if links.xml not found in parseXML().
	 */
	public static int getXMLLength() {

		Document doc = parseXML();
		
		if (doc == null) {
			return 0;
		}

		NodeList nList = doc.getElementsByTagName("sender");

		int length = nList.getLength();

		return length;

	}

	/**
	 * The method to return the type of the channel, either "TV" or "Radio"
	 * 
	 * @param channelNumber
	 *            The number of the channel in the channel list. The minimum
	 *            number is 0, maximum number is found through getXMLLength()
	 * @return The type of the channel
	 */
	public static String getType(int channelNumber) {

		Document doc = parseXML();

		NodeList nList = doc.getElementsByTagName("sender");

		Node nNode = nList.item(channelNumber);

		Element eElement = (Element) nNode;

		type = eElement.getElementsByTagName("type").item(0).getTextContent();

		return type;

	}

	/**
	 * The method to return the name of the channel
	 * 
	 * @param channelNumber
	 *            The number of the channel in the channel list. The minimum
	 *            number is 0, maximum number is found through getXMLLength()
	 * @return The name of the channel
	 */
	public static String getName(int channelNumber) {

		Document doc = parseXML();

		NodeList nList = doc.getElementsByTagName("sender");

		Node nNode = nList.item(channelNumber);

		Element eElement = (Element) nNode;

		name = eElement.getElementsByTagName("name").item(0).getTextContent();

		return name;

	}

}

package de.ro.inf.streamtv;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Just an ordinary testing class, no other use.
 * 
 * @author Ghostsoldier
 */
public class Player_test {

	public static void main(String[] args) throws UnsupportedEncodingException,
			FileNotFoundException, IOException {

		// outputkernel.OutputDebugStringA(System.getProperty("os.name"));
		Player test = new Player();
		test.findPlayerPath();
		test.writePathToFile();
		test.startStream("http://www.antenne.de/webradio/channels/oldies-but-goldies.m3u");
	}

}

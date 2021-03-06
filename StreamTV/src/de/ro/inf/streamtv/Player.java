package de.ro.inf.streamtv;

import java.awt.HeadlessException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

import javax.swing.JFileChooser;

//DebugOutput to Windows
import com.sun.jna.Library;
import com.sun.jna.Native;

/**
 * Class for handling the Vlc including searching the directory
 * 
 * @author Markus Deindl
 */
public class Player {

	protected String sPathPlayer;
	protected Process process;
	private JFileChooser FileSelector = new JFileChooser();
	public Kernel32 outputkernel = (Kernel32) Native.loadLibrary("kernel32",
			Kernel32.class);

	/**
	 * Windows debug output for debug only
	 */
	public interface Kernel32 extends Library {
		public void OutputDebugStringA(String Text);
	}

	/**
	 * Opens JFileChooser for User to select the vlc path
	 * 
	 * @returns true if no errors occurred, else the error is sent to kernel32
	 *          output
	 * @throws IOException
	 * @throws FileNotFoundException
	 * @throws UnsupportedEncodingException
	 */
	public boolean findPlayerPath() throws UnsupportedEncodingException,
			FileNotFoundException, IOException {
		try {
			File myFile;
			FileSelector.showOpenDialog(FileSelector);
			// FileSelector.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			// FileSelector.showSaveDialog(null);
			myFile = FileSelector.getSelectedFile();
			sPathPlayer = myFile.getAbsolutePath();
			outputkernel.OutputDebugStringA(sPathPlayer);
			writePathToFile();
			return true;
		} catch (HeadlessException e) {
			outputkernel.OutputDebugStringA(e.toString());
			return false;
		}
	}

	/**
	 * Starts an instance of vlc player with given link
	 * 
	 * @param sLink
	 *            is the Streamlink, or filepath for vlc
	 * @throws IOException
	 */
	public void startStream(String sLink) throws IOException {
		outputkernel.OutputDebugStringA("StreamTV: " + sPathPlayer);
		if (sPathPlayer == null)
			readPathFromFile();
		try {
			if (process != null) {
				process.destroy();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		ProcessBuilder pb = new ProcessBuilder(sPathPlayer, sLink);
		process = pb.start();
		outputkernel.OutputDebugStringA("StreamTV: Stream starting");
	}

	/**
	 * Searches for the file "path.txt" and reads the vlc path if existing if no
	 * path.txt is found then the function findPlayerPath is started
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void readPathFromFile() throws FileNotFoundException, IOException {
		try (BufferedReader br = new BufferedReader(new FileReader("path.txt"))) {
			String everything = br.readLine();
			sPathPlayer = everything;
			outputkernel.OutputDebugStringA(everything);
		} catch (FileNotFoundException notfound) {
			findPlayerPath();
		}
	}

	/**
	 * Used to save vlc path to path.txt in the same directory; overwrites of
	 * already existing
	 * 
	 * @throws UnsupportedEncodingException
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void writePathToFile() throws UnsupportedEncodingException,
			FileNotFoundException, IOException {
		try (Writer writer = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream("path.txt"), "utf-8"))) {
			writer.write(sPathPlayer);
		}
		outputkernel.OutputDebugStringA(sPathPlayer);
	}

}

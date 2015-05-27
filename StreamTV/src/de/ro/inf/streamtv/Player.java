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
 * Class for handling the Vlc
 * including searching the directory
 * @ author Ghostsoldier @ version 0.3, 25 May 2015
 */

public class Player {

	protected String sPathPlayer;
	protected Process process;
	private JFileChooser FileSelector = new JFileChooser();
	public Kernel32 outputkernel = (Kernel32) Native.loadLibrary("kernel32", Kernel32.class);

	
	
	/**
	 * not used at the moment
	 * @param args
	 */
	public static void main(String[] args) {
	}

	
	
	/**
	 * Windows debug output
	 */
	public interface Kernel32 extends Library
	{
	    public void OutputDebugStringA(String Text);
	}
	
	
	
	/**
	 * Sreaches the Vlc directory
	 * @return true, if player is found
	 */
	public boolean findPlayerPath() {
		try {
			File myFile;
			FileSelector.showOpenDialog(FileSelector);
			//FileSelector.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			//FileSelector.showSaveDialog(null);
			myFile = FileSelector.getSelectedFile();
			sPathPlayer = myFile.getAbsolutePath();
			outputkernel.OutputDebugStringA(sPathPlayer);
			return true;
		} catch (HeadlessException e) {
			outputkernel.OutputDebugStringA(e.toString());
			return false;
		}
	}
	
	/**
	 * 
	 * 
	 * @param sLink is the Streamlink, or filepath for vlc
	 * @throws IOException 
	 */
	public void startStream(String sLink) throws IOException{
		if(sPathPlayer==null) findPlayerPath();
		try {
			if(process!=null){process.destroy();}
		} catch (Exception e) {
			e.printStackTrace();
		}
		ProcessBuilder pb = new ProcessBuilder(sPathPlayer, sLink);
		process=pb.start();
		outputkernel.OutputDebugStringA("StreamTV: Stream starting");
	}
	
	
	public void readPathFromFile() throws FileNotFoundException, IOException{
	    try(BufferedReader br = new BufferedReader(new FileReader("Path.txt"))) {
	        StringBuilder sb = new StringBuilder();
	        String line = br.readLine();

	        while (line != null) {
	            sb.append(line);
	            sb.append(System.lineSeparator());
	            line = br.readLine();
	        }
	        String everything = sb.toString();
	        outputkernel.OutputDebugStringA(everything);		
	    }
	}
	
	public void writePathToFile() throws UnsupportedEncodingException, FileNotFoundException, IOException {
		try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("Path.txt"), "utf-8"))) {
        			writer.write(sPathPlayer);
		}
		outputkernel.OutputDebugStringA(sPathPlayer);	
	}
	
	
	
}

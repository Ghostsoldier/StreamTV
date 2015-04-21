package de.ro.inf.streamtv;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random; 
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.io.IOException;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import net.miginfocom.swing.MigLayout;


public class StreamTV extends JFrame{
	public StreamTV() {
	}
	
	//private static JButton btnZdf; 
	//private JFrame mainFrame;
	


	public static void main(String[] args) throws IOException {
		
		JFrame mainFrame = new JFrame("StreamTV");
		mainFrame.setSize(500,500);
		JPanel panel = new JPanel();
		mainFrame.getContentPane().add(panel, BorderLayout.CENTER);
		JButton btnZdf = new JButton("ZDF");
		panel.add(btnZdf);

		mainFrame.setVisible(true);
		
		ProcessBuilder pb = new ProcessBuilder("C:\\Program Files (x86)\\VideoLAN\\VLC\\vlc.exe", "http://zdf_hds_de-f.akamaihd.net/i/de14_v1@147090/master.m3u8");
		Process start = pb.start();
		
	}
	
}
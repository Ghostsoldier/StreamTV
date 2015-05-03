package de.ro.inf.streamtv;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random; 
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.io.IOException;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import net.miginfocom.swing.MigLayout;


public class StreamTV extends JFrame implements ActionListener{
	public StreamTV() {
	}
	
	//private static JButton btnZdf; 
	//private JFrame mainFrame;
	protected String sActionListenerDummy ="";
	


	public static void main(String[] args) throws IOException {
		
		JFrame mainFrame = new JFrame("StreamTV");
		mainFrame.setSize(500,500);
		JTabbedPane switchTab = new JTabbedPane();
		JPanel panelFernsehen = new JPanel();
		JPanel panelRadio = new JPanel();
		switchTab.addTab("Fernsehen", null, panelFernsehen,
                "Fernsehsender");
		switchTab.addTab("Radio", null, panelRadio,
                "Radiosender");
		mainFrame.getContentPane().add(switchTab, BorderLayout.CENTER);
		JButton btnArd = new JButton("ARD");
		btnArd.setName("btnArd");
		panelFernsehen.add(btnArd);
		JButton btnZdf = new JButton("ZDF");
		btnZdf.setName("btnZdf");
		panelFernsehen.add(btnZdf);
		
		
		StreamTV test = new StreamTV();
		btnArd.addActionListener(test);
		btnZdf.addActionListener(test);
		

		mainFrame.setVisible(true);
		
		//ProcessBuilder pb = new ProcessBuilder("C:\\Program Files (x86)\\VideoLAN\\VLC\\vlc.exe", "http://zdf_hds_de-f.akamaihd.net/i/de14_v1@147090/master.m3u8");
		//pb.start();
		
	}
	
	public void actionPerformed(ActionEvent event) {
		ProcessBuilder pb = new ProcessBuilder();
		System.out.println((((Component) event.getSource()).getName()));
		switch((((Component) event.getSource()).getName())) {
			case "btnArd" :
				pb = new ProcessBuilder("C:\\Program Files (x86)\\VideoLAN\\VLC\\vlc.exe", "http://daserste_live-lh.akamaihd.net/i/daserste_de@91204/master.m3u8");
				break;
			case "btnZdf" : 
				pb = new ProcessBuilder("C:\\Program Files (x86)\\VideoLAN\\VLC\\vlc.exe", "http://zdf_hds_de-f.akamaihd.net/i/de14_v1@147090/master.m3u8");
				break;
			
		}
		try {
			pb.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
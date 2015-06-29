package de.ro.inf.streamtv;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.io.IOException;



public class StreamTV extends JFrame implements ActionListener{
	public StreamTV() {
	}
	
	//private static JButton btnZdf; 
	//private JFrame mainFrame;
	protected String sActionListenerDummy ="";
	Sender sender = new Sender();
	TVSender tvSender = new TVSender();
	RadioSender radioSender = new RadioSender();
	Player player = new Player();
	


	public static void main(String[] args) throws IOException {
		
		JFrame mainFrame = new JFrame("StreamTV");
		mainFrame.setSize(700,700);
		JTabbedPane switchTab = new JTabbedPane();
		JPanel panelFernsehen = new JPanel(new GridLayout(0,2));
		JPanel panelRadio = new JPanel(new GridLayout(0,2));
		JPanel panelSettings = new JPanel();
		switchTab.addTab("Fernsehen", null, panelFernsehen,
                "Fernsehsender");
		switchTab.addTab("Radio", null, panelRadio,
                "Radiosender");
		switchTab.addTab("Einstellungen", null, panelSettings);
		mainFrame.getContentPane().add(switchTab, BorderLayout.CENTER);
		
		StreamTV player = new StreamTV();
		Sender sender = new Sender();
		TVSender tvSender = new TVSender();
		RadioSender radioSender = new RadioSender();
		
		JButton[] SenderButtons = new JButton[sender.getXMLLength()];
		
		for(int i=0; i<SenderButtons.length; i++){
			if (sender.getType(i).equals("TV")) {
				SenderButtons[i] = new JButton(sender.getName(i) + " (" + tvSender.getLink(i)[0] + ")");
			}
			else if (sender.getType(i).equals("Radio")) {
				SenderButtons[i] = new JButton(sender.getName(i) + " (" + radioSender.getLink(i)[0] + " kbit)");
			}
			SenderButtons[i].setName(String.valueOf(i));
			if(sender.getType(i).equals("TV")) panelFernsehen.add(SenderButtons[i]);
			else if(sender.getType(i).equals("Radio")) panelRadio.add(SenderButtons[i]);
			SenderButtons[i].addActionListener(player);
			
		}
		
		//btnArd.addActionListener(player);
		//btnZdf.addActionListener(player);
		

		mainFrame.setVisible(true);
		
		//ProcessBuilder pb = new ProcessBuilder("C:\\Program Files (x86)\\VideoLAN\\VLC\\vlc.exe", "http://zdf_hds_de-f.akamaihd.net/i/de14_v1@147090/master.m3u8");
		//pb.start();
		
	}

	/**
	 * reads the performed Action, in this case JButton-click
	 */
	public void actionPerformed(ActionEvent event) {
		System.out.println((((Component) event.getSource()).getName()));
		int senderCount =Integer.parseInt((((Component) event.getSource()).getName()));
		String streamLink = "";
		if(sender.getType(senderCount).equals("TV")) {
			streamLink=tvSender.getLink(senderCount)[1];
		}
		else if(sender.getType(senderCount).equals("Radio")) {
			streamLink=radioSender.getLink(senderCount)[1];
		}
		try {
			player.startStream(streamLink);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}

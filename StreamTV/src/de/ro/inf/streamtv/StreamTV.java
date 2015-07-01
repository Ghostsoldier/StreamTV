package de.ro.inf.streamtv;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.io.IOException;

/**
 * Main Class containing he GUI and the main program functions
 * 
 * @author Markus Deindl
 */
public class StreamTV extends JFrame implements ActionListener {
	public StreamTV() {
	}

	protected String sActionListenerDummy = "";
	Sender sender = new Sender();
	TVSender tvSender = new TVSender();
	RadioSender radioSender = new RadioSender();
	Player player = new Player();

	/**
	 * Creates the GUI and adds buttons for the channels the class sender is
	 * used to get all the information about the channels
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {

		JFrame mainFrame = new JFrame("StreamTV");
		mainFrame.setSize(700, 700);
		JTabbedPane switchTab = new JTabbedPane();
		JPanel panelFernsehen = new JPanel(new GridLayout(0, 2));
		JPanel panelRadio = new JPanel(new GridLayout(0, 2));
		// JPanel panelSettings = new JPanel();
		switchTab.addTab("Fernsehen", null, panelFernsehen, "Fernsehsender");
		switchTab.addTab("Radio", null, panelRadio, "Radiosender");
		// switchTab.addTab("Einstellungen", null, panelSettings);
		mainFrame.getContentPane().add(switchTab, BorderLayout.CENTER);

		StreamTV player = new StreamTV();
		Sender sender = new Sender();
		TVSender tvSender = new TVSender();
		RadioSender radioSender = new RadioSender();

		JButton[] SenderButtons = new JButton[sender.getXMLLength()];

		for (int i = 0; i < SenderButtons.length; i++) {
			if (sender.getType(i).equals("TV")) {
				SenderButtons[i] = new JButton(sender.getName(i) + " ("
						+ tvSender.getLink(i)[0] + ")");
			} else if (sender.getType(i).equals("Radio")) {
				SenderButtons[i] = new JButton(sender.getName(i) + " ("
						+ radioSender.getLink(i)[0] + " kbit)");
			}
			SenderButtons[i].setName(String.valueOf(i));
			if (sender.getType(i).equals("TV"))
				panelFernsehen.add(SenderButtons[i]);
			else if (sender.getType(i).equals("Radio"))
				panelRadio.add(SenderButtons[i]);
			SenderButtons[i].addActionListener(player);

		}

		mainFrame.setVisible(true);
	}

	/**
	 * reads the performed Action, in this case JButton-click the link from
	 * RadioSender and TvSender is sent to Player to start the stream
	 */
	public void actionPerformed(ActionEvent event) {
		System.out.println((((Component) event.getSource()).getName()));
		int senderCount = Integer.parseInt((((Component) event.getSource())
				.getName()));
		String streamLink = "";
		if (sender.getType(senderCount).equals("TV")) {
			streamLink = tvSender.getLink(senderCount)[1];
		} else if (sender.getType(senderCount).equals("Radio")) {
			streamLink = radioSender.getLink(senderCount)[1];
		}
		try {
			player.startStream(streamLink);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

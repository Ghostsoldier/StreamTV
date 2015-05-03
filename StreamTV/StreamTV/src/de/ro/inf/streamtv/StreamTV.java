package de.ro.inf.streamtv;

import javax.swing.*;

import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import net.miginfocom.swing.MigLayout;


public class StreamTV {

	public static void main(String[] args) {
		JFrame mainFrame = new JFrame("StreamTV");
		mainFrame.setSize(500,500);
		
		JPanel panel = new JPanel();
		mainFrame.getContentPane().add(panel, BorderLayout.CENTER);
		
		String sSenderliste[] = {"3sat", "ARD", "tagesschau24", "ARTE", "N24", "n-tv", "Phoenix", "ZDF", "ZDFKultur"};
		
		JList jSenderliste = new JList(sSenderliste);
		panel.add(jSenderliste);
		mainFrame.setVisible(true);
		
				
		
	}
	
	
	
	protected void actionPerformed(ActionEvent e) {
		//event listener für sender
	}

}
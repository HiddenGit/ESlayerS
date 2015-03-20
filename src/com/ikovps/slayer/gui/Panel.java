package com.ikovps.slayer.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.ikovps.slayer.data.Variables;

@SuppressWarnings("serial")
public class Panel extends JFrame {

	private JPanel contentPane;
	private final ButtonGroup slayerMasterGroup = new ButtonGroup();
	private final ButtonGroup prayerGroup = new ButtonGroup();
	private final JRadioButton masterVannaka = new JRadioButton("Vannaka");
	private final JRadioButton masterChaelder = new JRadioButton("Chaelder");
	private final JRadioButton masterDuradel = new JRadioButton("Duradel");
	private final JRadioButton masterMazachna = new JRadioButton("Mazachna");
	private final JRadioButton normalPrayers = new JRadioButton("Normal");
	private final JRadioButton cursesPrayer = new JRadioButton("Curses");
	private final JRadioButton looting = new JRadioButton("Loot caskets and rare items");
	private final JRadioButton withdraw = new JRadioButton("Withdraw sharks and restores");
	private final JRadioButton banking = new JRadioButton("Bank when Inventory is Full");
	private final String fileName = "data/ESlayerData.eslayer";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Panel frame = new Panel();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Panel() {
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setTitle("ESlayer - By Empathy");
		setBounds(100, 100, 417, 473);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 381, 337);
		contentPane.add(tabbedPane);

		JPanel panel = new JPanel();
		tabbedPane.addTab("Main", null, panel, null);
		panel.setLayout(null);

		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Settings", null, panel_1, null);
		panel_1.setLayout(null);

		JLabel prayerLabel = new JLabel("Prayers", SwingConstants.CENTER);
		prayerLabel.setFont(new Font("Serif", Font.CENTER_BASELINE, 16));
		prayerLabel.setBounds(157, 22, 62, 22);
		panel_1.add(prayerLabel);

		prayerGroup.add(cursesPrayer);
		cursesPrayer.setBounds(148, 61, 109, 23);
		panel_1.add(cursesPrayer);

		prayerGroup.add(normalPrayers);
		normalPrayers.setBounds(148, 107, 109, 23);
		panel_1.add(normalPrayers);

		JLabel bankingLootingLabel = new JLabel("Banking and Looting", SwingConstants.CENTER);
		bankingLootingLabel.setFont(new Font("Serif", Font.BOLD, 16));
		bankingLootingLabel.setBounds(118, 161, 139, 22);
		panel_1.add(bankingLootingLabel);

		banking.setBounds(98, 190, 228, 23);
		banking.setVisible(false);
		panel_1.add(banking);

		withdraw.setBounds(98, 222, 228, 23);
		withdraw.setVisible(false);
		panel_1.add(withdraw);

		looting.setBounds(98, 252, 228, 23);
		looting.setVisible(false);
		panel_1.add(looting);

		String path = "http://i.imgur.com/8oD7B5v.png";
		URL url;
		try {
			url = new URL(path);
			BufferedImage image = ImageIO.read(url);
			JLabel eSlayer = new JLabel(new ImageIcon(image));
			eSlayer.setBackground(new Color(255, 255, 255));
			eSlayer.setBounds(10, 11, 356, 111);
			panel.add(eSlayer);
		} catch (Exception e) {
			e.printStackTrace();
		}

		JLabel slayerMaster = new JLabel("Choose A Slayer Master", JLabel.CENTER);
		slayerMaster.setFont(new Font("Serif", Font.PLAIN, 16));
		slayerMaster.setBounds(112, 131, 151, 22);
		panel.add(slayerMaster);

		slayerMasterGroup.add(masterMazachna);
		masterMazachna.setBounds(66, 176, 109, 23);
		panel.add(masterMazachna);

		slayerMasterGroup.add(masterVannaka);
		masterVannaka.setBounds(66, 221, 109, 23);
		panel.add(masterVannaka);

		slayerMasterGroup.add(masterChaelder);
		masterChaelder.setBounds(231, 176, 109, 23);
		masterChaelder.setVisible(false);
		panel.add(masterChaelder);

		slayerMasterGroup.add(masterDuradel);
		masterDuradel.setBounds(231, 221, 109, 23);
		masterDuradel.setVisible(false);
		panel.add(masterDuradel);

		JButton startButton = new JButton("Start");
		startButton.setBounds(162, 368, 89, 36);
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				start();
			}
		});
		contentPane.add(startButton);

		JButton loadButton = new JButton("Load Settings");
		loadButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				load();
			}
		});
		loadButton.setBounds(292, 368, 99, 36);
		contentPane.add(loadButton);

		JButton saveButton = new JButton("Save Settings");
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				save();
			}
		});
		saveButton.setBounds(20, 368, 99, 36);
		contentPane.add(saveButton);
	}
	
	private void start() {
		
		if (masterMazachna.isSelected()) {
			Variables.slayerMaster = 0;
		}
		
		if (masterVannaka.isSelected()) {
			Variables.slayerMaster = 1;
		}
		
		Variables.start = true;
		
		dispose();
	}
	
	private void save() {

		Properties prop = new Properties();
		OutputStream output = null;

		try {
			File file = new File(fileName);
			
			if (!file.exists()) {
				file.getParentFile().mkdir();
				file.createNewFile();
			}

			output = new FileOutputStream(fileName);

			if (masterMazachna.isSelected()) {

				prop.setProperty("masterMazachna", "true");

			}

			if (masterVannaka.isSelected()) {

				prop.setProperty("masterVannaka", "true");

			}

			if (masterChaelder.isSelected()) {

				prop.setProperty("masterChaelder", "true");

			}

			if (masterDuradel.isSelected()) {

				prop.setProperty("masterDuradel", "true");

			}

			if (normalPrayers.isSelected()) {

				prop.setProperty("normalPrayers", "true");

			}

			if (cursesPrayer.isSelected()) {

				prop.setProperty("cursePrayers", "true");

			}

			if (looting.isSelected()) {

				prop.setProperty("looting", "true");

			}

			if (banking.isSelected()) {

				prop.setProperty("banking", "true");

			}

			if (withdraw.isSelected()) {

				prop.setProperty("withdraw", "true");

			}

			// save properties to project root folder
			prop.store(output, null);

		} catch (IOException io) {
			io.printStackTrace();
		}
	}

	private void load() {
		Properties prop = new Properties();
		InputStream input = null;

		try {
			input = new FileInputStream(fileName);

			// load a properties file
			prop.load(input);

			// get the property value and load it
			masterMazachna.setSelected(Boolean.parseBoolean(prop.getProperty("masterMazachna")));
			masterVannaka.setSelected(Boolean.parseBoolean(prop.getProperty("masterVannaka")));
			masterChaelder.setSelected(Boolean.parseBoolean(prop.getProperty("masterChaelder")));
			masterDuradel.setSelected(Boolean.parseBoolean(prop.getProperty("masterDuradel")));
			normalPrayers.setSelected(Boolean.parseBoolean(prop.getProperty("normalPrayers")));
			cursesPrayer.setSelected(Boolean.parseBoolean(prop.getProperty("cursePrayers")));
			looting.setSelected(Boolean.parseBoolean(prop.getProperty("looting")));
			banking.setSelected(Boolean.parseBoolean(prop.getProperty("banking")));
			withdraw.setSelected(Boolean.parseBoolean(prop.getProperty("withdraw")));

		} catch (IOException io) {
			io.printStackTrace();
		}
	}
}

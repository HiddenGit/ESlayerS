package com.ikovps.slayer;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;

import javax.imageio.ImageIO;

import org.parabot.core.Context;
import org.parabot.environment.api.interfaces.Paintable;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.Category;
import org.parabot.environment.scripts.Script;
import org.parabot.environment.scripts.ScriptManifest;
import org.rev317.min.api.events.MessageEvent;
import org.rev317.min.api.events.listeners.MessageListener;
import org.rev317.min.api.methods.Skill;

import com.ikovps.slayer.data.Variables;
import com.ikovps.slayer.gui.Panel;
import com.ikovps.slayer.strategies.AttackNpcs;
import com.ikovps.slayer.strategies.ObtainTask;
import com.ikovps.slayer.strategies.TeleportToTask;
import com.ikovps.slayer.strategies.WalkToTask;
import com.ikovps.slayer.utils.ScriptTimer;

@ScriptManifest(author = "Empathy", category = Category.SLAYER, description = "A Slayer script for Ikov", name = "ESlayer", servers = { "Ikov" }, version = 1.0)
public class ESlayer extends Script implements Paintable, MessageListener {

	private Image background = getImage("http://i.imgur.com/9dJtXxm.png");
	// private Image toggleButton = getImage("http://i.imgur.com/JoLFGKv.png");
	private boolean hide;
	// private Point p;
	// private Rectangle toggle = new Rectangle(498, 345, 200, 200);
	private final Color color1 = new Color(204, 204, 204);
	private final Font font1 = new Font("Agency FB", 0, 19);

	private ScriptTimer slayerTimer = new ScriptTimer(Skill.SLAYER, 0);

	@Override
	public boolean onExecute() {

		// Keep this in just for beta testing (which will be available for free
		// to sponsors)
		if (Context.getUsername().toLowerCase().contains("botterino")) {
			System.out.println("Hahaha, fuck you Botterino you ain't using my script for free - Empathy.");
		} else {
			Panel pan = new Panel();
			pan.setVisible(true);
			while (pan.isVisible()) {
				Time.sleep(1000);
			}

			provide(Arrays.asList(new ObtainTask(), new TeleportToTask(), new WalkToTask(), new AttackNpcs()));
		}
		return true;
	}
	public void onFinish() {
		System.out.println("XP Gained: " + slayerTimer.getXpGained() + ", Tasks Completed: " + Variables.tasksCompleted);
	}
	private Image getImage(String url) {
		try {
			return ImageIO.read(new URL(url));
		} catch (IOException e) {
			return null;
		}
	}

	@Override
	public void paint(Graphics g1) {
		Graphics2D g = (Graphics2D) g1;
		try {
			if (!hide) {
				g.drawImage(background, 1, 251, null);
				// g.drawImage(toggleButton, 498, 345, null);
				g.setFont(font1);
				g.setColor(color1);
				g.drawString("" + slayerTimer.toString(), 96, 377);
				g.drawString("" + Skill.SLAYER.getRealLevel(), 96, 402);
				g.drawString("N/A", 96, 429);
				g.drawString("" + Variables.status, 95, 455);

				if (Variables.task.getName() != null) {
					g.drawString("" + Variables.taskAmount + " " + Variables.task.getName(), 244, 377);
				} else {
					g.drawString("No Task", 244, 377);
				}

				g.drawString("" + slayerTimer.getXpGained(), 244, 403);
				g.drawString("" + Variables.tasksCompleted, 244, 429);
			}

			if (hide) {
				// g.drawImage(toggleButton, 498, 345, null);
			}
		} catch (NullPointerException e) {

		}
	}

	@SuppressWarnings("unused")
	private final long timeToNextLevel() {
		if (slayerTimer.getXpGained() > 0) {
			return (long) ((slayerTimer.getElapsedTime() * (Skill.getExperienceByLevel(Skill.SLAYER.getRealLevel() + 1) - Skill.SLAYER.getExperience()) / slayerTimer.getXpGained()));
		}
		return 0;
	}

	@Override
	public void messageReceived(MessageEvent m) {
		if (m.getType() == 0) {
			if ((m.getMessage().contains("have completed your task"))) {
				Variables.hasTask = false;
				Variables.teleported = false;
				Variables.arrived = false;
				Variables.clickObstacle = false;
				Variables.tasksCompleted++;
				Variables.decreaseTask = false;
			}
		}
	}
}
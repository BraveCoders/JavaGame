package GameEngine;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class GameSys extends Core {

	private Image im;
	private long time;

	public static void main(String args[]) {
		new GameSys().run();
	}

	public void init() {
		super.init();
		// ---------------- Inicjacja Ekranu --------------
		Window w = s.getFullScreenWindow();
		w.setFocusTraversalKeysEnabled(false);

		// ---------------- Ustawienie niewidocznego kursora --------------
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		BufferedImage cursorImage = new BufferedImage(1, 1, BufferedImage.TRANSLUCENT);
		Point hotSpot = new Point(0, 0);
		Cursor invisibleCursor = toolkit.createCustomCursor(cursorImage, hotSpot, "InvisibleCursor");
		w.setCursor(invisibleCursor);

		// ---------------- Inicjacja Eventów --------------
		GameMouse gm = new GameMouse(this);
		w.addKeyListener(new Keys(this));
		w.addMouseMotionListener(gm);
		w.addMouseListener(gm);

		objects = new ArrayList<GameObject>();
		objects.add(new GameImage(0, 0, 1600, 900, this.getClass().getResource("").getPath()+"../images/main_back.jpg"));
		objects.add(new GameTextButton(760, 660, 0, 0, "New Game"));
		objects.add(new GameTextButton(762, 700, 0, 0, "Load Game"));
		objects.add(new GameTextButton(775, 740, 0, 0, "Exit"));
	}

	@Override
	public void draw(Graphics2D g) {

		Window w = s.getFullScreenWindow();
		g.setColor(w.getBackground());
		g.fillRect(0, 0, s.getWidth(), s.getHeight());
		for (GameObject obj : objects) {
			obj.onHover(GameMouse.getX(), GameMouse.getY(), GameMouse.getState());
			obj.draw(g);
		}
		g.setColor(new Color(255, 255, 255));
		g.drawString(GameMouse.getX() + ":" + GameMouse.getY(), 10, 50);
		g.drawString(Keys.getPressedKeysList() + "", 10, 100);
		g.drawOval(GameMouse.getX() - 1, GameMouse.getY() - 1, 3, 3);
	}

	@Override
	public void update(long timePassed) {
		time = timePassed;

		for (GameObject koko : objects)
			koko.update(timePassed);
	}

	@Override
	public void keyTyped(Key k) {

	}

	@Override
	public void mouseClicked(int x, int y) {
		for (GameObject koko : objects)
			if (koko.onHover(x, y, GameMouse.getState()))
				koko.onClick();
	}
}

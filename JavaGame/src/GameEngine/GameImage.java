package GameEngine;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class GameImage extends GameObject {

	private Image img;
	
	public GameImage(int xx, int yy, int w, int h, String path) {
		super(xx, yy, w, h);
		img = new ImageIcon(path).getImage();
	}

	@Override
	public void draw(Graphics2D g) {
		g.drawImage(img, x, y, width, height, null);
	}

	@Override
	public void onClick() {
	}

	@Override
	public void onHover() {
	}

	@Override
	public void onHoverOver() {
	}

	@Override
	public void update(long timePassed) {
	}

}

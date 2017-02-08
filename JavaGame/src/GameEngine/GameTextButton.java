package GameEngine;
import java.awt.Color;
import java.awt.Graphics2D;

public class GameTextButton extends GameObject {
	
	private String caption;
	private Color color = Color.GRAY;

	public GameTextButton(int xx, int yy, int w, int h, String c) {
		super(xx, yy, w, h);
		caption = c;
		width = caption.length()*10;
		height = 30;
		x-=width/2;
		y-=height/2;
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(color);
		g.drawString(caption, x, y+height-5);
	}

	@Override
	public void onClick() {
	}

	@Override
	public void onHover() {
		color = Color.LIGHT_GRAY;
	}

	@Override
	public void onHoverOver() {
		color = Color.GRAY;
	}

	@Override
	public void update(long timePassed) {
		// TODO Auto-generated method stub

	}

}

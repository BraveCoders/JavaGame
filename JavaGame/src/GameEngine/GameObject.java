package GameEngine;
import java.awt.Graphics2D;

public abstract class GameObject {

	protected int width, height, x ,y, state;
	private boolean hover;
	
	public GameObject(int xx, int yy, int w, int h) {
		width = w;
		height = h;
		x =xx;
		y = yy;
		state = 0;
		hover = false;
	}

	public abstract void draw(Graphics2D g);

	public boolean onHover(int xpos, int ypos, int buttons) {
		if(xpos>x && xpos<x+width && ypos>y && ypos<y+height){
			if(!hover) {
				hover=true;
				onHover();
			}
			return true;
		}else{
			if(hover){
				hover=false;
				onHoverOver();
			}
			return false;
		}
	}

	public abstract void onClick();
	public abstract void onHover();
	public abstract void onHoverOver();
	public abstract void update(long timePassed);
	
	
	
	
}

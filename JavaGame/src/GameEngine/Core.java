package GameEngine;
import java.awt.Color;
import java.awt.DisplayMode;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Window;
import java.util.List;


public abstract class Core {

	private static DisplayMode modes[] = {
			new DisplayMode(1600, 900, 32, 0),
			new DisplayMode(1600, 900, 24, 0),
			new DisplayMode(1600, 900, 16, 0)
	};
	
	protected List<GameObject> objects;
	
	private boolean running;
	
	protected ScreenManager s;
	
	public void stop(){
		running = false;
	}
	
	public void run(){
		try{
			init();
			gameLoop();
		}finally{
			s.restore();
		}
	}
	
	public void init(){
		s = new ScreenManager();
		DisplayMode dm = s.findCM(modes);
		s.setFullScreen(dm);
		
		Window w = s.getFullScreenWindow();
		w.setFont(new Font("Arial", Font.PLAIN, 24));
		w.setBackground(Color.black);
		w.setForeground(Color.white);
		running = true;
		
	}
	
	public void gameLoop(){
		long startTime = System.currentTimeMillis();
		long curTime = startTime;
		
		while (running){
			long timePassed = System.currentTimeMillis() - curTime;
			curTime +=timePassed;
			update(timePassed);
			
			Graphics2D g = s.getGraphics();
			draw(g);
			g.dispose();
			s.update();
		}
	}

	public abstract void keyTyped(Key k);
	public abstract void mouseClicked(int x, int y);
	
	public abstract void update(long timePassed);
	
	public abstract void draw(Graphics2D g);
	
	
}

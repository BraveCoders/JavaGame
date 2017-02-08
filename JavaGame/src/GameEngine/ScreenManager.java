package GameEngine;
import java.awt.DisplayMode;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Window;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;


public class ScreenManager {
	
	private GraphicsDevice vc;
	
	public ScreenManager(){
		
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		this.vc = ge.getDefaultScreenDevice();
		
	}
	
	public DisplayMode[] getAllDM(){
		return vc.getDisplayModes();
	}
	
	public DisplayMode findCM(DisplayMode[] displayList){
		DisplayMode goodModes[] = vc.getDisplayModes();
		for (int mode = 0; mode < displayList.length; mode++){
			for (int goodMode = 0; goodMode < goodModes.length; goodMode++){
				if(displayModeMatch(displayList[mode], goodModes[goodMode])){
					return displayList[mode];
				}
			}
			
		}
		return null;
	}
	
	public DisplayMode getCurrentDM(){
		return vc.getDisplayMode();
	}
	
	private boolean displayModeMatch(DisplayMode x, DisplayMode y){
		return x.equals(y);
	}
	
	public void setFullScreen(DisplayMode dm){

		JFrame window = new JFrame();
		window.setUndecorated(true);
		window.setResizable(false);
		window.setIgnoreRepaint(true);
		vc.setFullScreenWindow(window);
		window.createBufferStrategy(2);
	}
	
	public Graphics2D getGraphics(){
		Window w = vc.getFullScreenWindow();
		if( w != null ){
			BufferStrategy s = w.getBufferStrategy();
			return (Graphics2D) s.getDrawGraphics();
		}else return null;
	}
	
	public void update(){
		Window w = vc.getFullScreenWindow();
		if( w != null ){
			BufferStrategy s = w.getBufferStrategy();
			if(!s.contentsLost()){
				s.show();
			}
		}
	}
	
	public Window getFullScreenWindow() {
		return vc.getFullScreenWindow();
	}
	
	public int getWidth() {
		Window w = vc.getFullScreenWindow();
		if( w != null){
			return w.getWidth();
		}else return 0;
	}
	
	public int getHeight() {
		Window w = vc.getFullScreenWindow();
		if( w != null){
			return w.getHeight();
		}else return 0;
	}
	
	public void restore() {
		Window w = vc.getFullScreenWindow();
		if ( w != null ){
			w.dispose();
		}
		vc.setFullScreenWindow(null);
	}
	
	public BufferedImage createImage(int w, int h, int t){
		Window win = vc.getFullScreenWindow();
		if(win!= null){
			GraphicsConfiguration gc = win.getGraphicsConfiguration();
			return gc.createCompatibleImage(w, h, t);
		}else return null;
	}
}

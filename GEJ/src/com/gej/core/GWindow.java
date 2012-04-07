package com.gej.core;

import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Window;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

public class GWindow implements WindowListener {
	
	JFrame frame = null;
	boolean fullscreen = false;
	GraphicsDevice device = null;
	
	public GWindow(Game gm, boolean fullscreen){
		this.fullscreen = fullscreen;
		LookAndFeelInfo[] lafs = UIManager.getInstalledLookAndFeels();
		for (LookAndFeelInfo laf : lafs){
			if (laf.getName().startsWith("Nimbus")){
				try {
					UIManager.setLookAndFeel(laf.getClassName());
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
					e.printStackTrace();
				}
			}
		}
		frame = new JFrame(Global.TITLE);
		frame.add(gm);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setIgnoreRepaint(true);
		if (fullscreen){
			GraphicsEnvironment genv = GraphicsEnvironment.getLocalGraphicsEnvironment();
			device = genv.getDefaultScreenDevice();
			frame.setUndecorated(true);
			device.setFullScreenWindow((Window)frame);
			DisplayMode[] modes = device.getDisplayModes();
			for (DisplayMode mode : modes){
				if (mode.getWidth()==Global.WIDTH && mode.getHeight()==Global.HEIGHT && mode.getBitDepth()==32){
					device.setDisplayMode(mode);
				}
			}
		} else {
			frame.setSize(Global.WIDTH, Global.HEIGHT);
			frame.setLocationRelativeTo(null);
		}
		frame.setVisible(true);
	}
	
	public int getWidth(){
		return frame.getWidth();
	}
	
	public int getHeight(){
		return frame.getHeight();
	}
	
	public Window getWindow(){
		if (!fullscreen){
			return (Window)frame;
		} else {
			return device.getFullScreenWindow();
		}
	}
	
	public void setTitle(String title){
		frame.setTitle(title);
	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		if (fullscreen){
			device.getFullScreenWindow().dispose();
			device.setFullScreenWindow(null);
		}
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		
	}
	
}
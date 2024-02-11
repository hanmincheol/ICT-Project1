package common.utils;

import java.io.FileInputStream;

import javazoom.jl.player.Player;

public class MediaPlayer extends Thread{
	private Player player;
	private FileInputStream fis;
	String filePath;
	private boolean stopped;
	
	public MediaPlayer(String filePath) {
		this.filePath = filePath;
	}
	
	void play() {
		try {fis = new FileInputStream(filePath);
		player = new Player(fis);
		player.play();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(!stopped) {
			play();
			}
		}
	}
	
	void playStop() {
		stopped = true;
		if(player != null) {
			player.close();
			player=null;
		}
	}
	
	@Override
	public void run() {
		play();
	}
}
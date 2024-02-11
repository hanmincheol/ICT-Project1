package common.utils;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;

import javazoom.jl.player.Player;

public class InnerEvent extends JFrame{
	JButton button1,button2,button3, button4, button5, button6;
	MediaPlayer player = null;
	private ActionListener handler = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == button1) {
				music("music/sokodomo - 회전목마 (Feat. Zion.T, 원슈타인) (Prod. Slom) [쇼미더머니 10 Episode 2]ㅣLyrics 가사.mp3");
			}
			else if(e.getSource() == button2) {
				music("music/Charlie Puth - I Don't Think That I Like Her (Official Audio)_9voN0gkdlS4.mp3");
			}
			else if(e.getSource() == button3) {
				music("music/Escort _ DOVA-SYNDROME BGM _ 피아노커버_EpDvg215Hvc.mp3");
			}
			else if(e.getSource() == button4) {
				music("music/Alan Walker - Faded_60ItHLz5WEA.mp3");
			}
			else if(e.getSource() == button5) {
				music("music/Wiz Khalifa - See You Again ft. Charlie Puth [Official Video] Furious 7 Soundtrack.mp3");
			}
			else if(e.getSource() == button6) {
				if(player != null) player.playStop();
			}
			
		}
	};
	
	//음악 무한재생 메소드
	private void music(String url){
		if(player != null) player.playStop();
		player = new MediaPlayer(url);
		player.setDaemon(true);
		player.start();
	
	}
	
	public InnerEvent() {
		setTitle("뮤직 리모컨");
		setLayout(new FlowLayout());
		add(button1 = new JButton("인생은 회전목마"));
		add(button2 = new JButton("I don't like her"));
		add(button3 = new JButton("Escort"));
		add(button4 = new JButton("Faded"));
		add(button5 = new JButton("See you again"));
		add(button6 = new JButton("재생중지"));
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				if(player != null) player.playStop();
			}			
		});
		
		button1.addActionListener(handler);
		button2.addActionListener(handler);
		button3.addActionListener(handler);
		button4.addActionListener(handler);
		button5.addActionListener(handler);
		button6.addActionListener(handler);
		pack();
		setVisible(true);
	}
}
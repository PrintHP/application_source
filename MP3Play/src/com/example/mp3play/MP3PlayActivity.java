package com.example.mp3play;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MP3PlayActivity extends Activity {
	MediaPlayer m ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		m = MediaPlayer.create(this, R.raw.music);
		
		LinearLayout layout = new LinearLayout(this);
		Button start = new Button(this);
		Button stop = new Button(this);
		layout.addView(start);
		layout.addView(stop);

		start.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				m.start();
			}
		});
		stop.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				m.pause();
			}
		});
		
		
		SoundPool sp = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
		
		int song1 = sp.load(this, R.raw.music, 1);
		
		sp.play(song1, 1f, 1f, 0, 0, 1);
		
		
		setContentView(layout);

	}
}

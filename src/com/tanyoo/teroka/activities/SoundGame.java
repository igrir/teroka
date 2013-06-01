package com.tanyoo.teroka.activities;

import java.io.IOException;

import com.tanyoo.teroka.R;
import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;

public class SoundGame extends Activity {
	Context con;
	
	//activity dari main (sincron)
	public SoundGame(Context context){
		this.con = context;
	}
	
	public void soundAttack(){		
		//suara
		MediaPlayer mp = MediaPlayer.create(con, R.raw.sound_button);
		mp.setLooping(false);
		mp.setVolume(80, 100);
		try {
			mp.prepare();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mp.start();		
	}
	
}

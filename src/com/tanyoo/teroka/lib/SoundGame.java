package com.tanyoo.teroka.lib;

import java.io.IOException;

import com.tanyoo.teroka.R;
import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;

public class SoundGame extends Activity {
	Context con;
	MediaPlayer mp;
	
	//activity dari main (sincron)
	public SoundGame(Context context){
		this.con = context;
	}
	
	public void soundAttack(){		
		//suara
		mp = MediaPlayer.create(con, R.raw.sword_swing);
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
	
	//untuk menghentikan sound
	@Override
	public void onStop(){ 
		super.onStop();
		if(mp.isPlaying()){
			mp.stop();
		}else{
		   return;
		}
	}
	
}

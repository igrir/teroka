package com.tanyoo.teroka.lib;

import java.io.IOException;
import java.util.HashMap;

import com.tanyoo.teroka.R;
import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.media.MediaPlayer.OnCompletionListener;
import android.util.Log;

public class SoundGame {
	Context con;
	MediaPlayer mp;
	
	public static final int SOUND_SWING = 1;
	public static final int SOUND_DEFENSE = 2;
	public static final int SOUND_MONSTER = 3;
	public static final int SOUND_WIN = 4;
	public static final int SOUND_LOSE = 5;
	public static final int SOUND_BACKSOUND = 6;
	
	private SoundPool soundPool;
	private HashMap<Integer, Integer> soundPoolMap;
	
	//activity dari main (sincron)
	public SoundGame(Context context){
		this.con = context;
	}
	
	public void initSound(){
		soundPool = new SoundPool(4, AudioManager.STREAM_MUSIC, 100);
		soundPoolMap = new HashMap<Integer, Integer>();
		soundPoolMap.put(SOUND_SWING, soundPool.load(this.con, R.raw.sword_swing, 1));
		soundPoolMap.put(SOUND_DEFENSE, soundPool.load(this.con, R.raw.sound_defense, 1));
		soundPoolMap.put(SOUND_MONSTER, soundPool.load(this.con, R.raw.sound_monster, 1));
		soundPoolMap.put(SOUND_WIN, soundPool.load(this.con, R.raw.win_2, 1));
		soundPoolMap.put(SOUND_LOSE, soundPool.load(this.con, R.raw.lose_1, 1));
		soundPoolMap.put(SOUND_BACKSOUND, soundPool.load(this.con, R.raw.backsound, 1));
	
	}
	
//	public void soundAttack(){		
		//suara
//		mp = MediaPlayer.create(con, R.raw.sword_swing);
//		mp.setLooping(false);
//		mp.setVolume(80, 100);
//		try {
//			mp.prepare();
//		} catch (IllegalStateException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		mp.start();	
//		mp.setOnCompletionListener(new OnCompletionListener() {
//			
//			@Override
//			public void onCompletion(MediaPlayer arg0) {
//				// TODO Auto-generated method stub
//				mp.release();
//			}
//		});
	
	public void playSound(int sound){
		AudioManager mgr = (AudioManager) this.con.getSystemService(Context.AUDIO_SERVICE);
		
		int streamVolume = mgr.getStreamVolume(AudioManager.STREAM_MUSIC);
		
		soundPool.play(soundPoolMap.get(sound), streamVolume, streamVolume, 1, 0, 1f);
		
	}
	
	
	/*
	 * Destructor sound. Panggil ini di onDestroy pada
	 * activity yang memanggilnya
	 * 
	 * OBSOLOTE
	 */
//	public void destroy(){
//		
//		Log.e("terokaSound", "destroy");
//		
//		if (mp != null) {
//			if(mp.isPlaying()){
//				mp.stop();
//				
//				mp.release();
//				mp = null;
//			}	
//		}
//			
//	}
	
	
}

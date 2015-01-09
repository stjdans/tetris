package com.example.tetris.env;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;

import com.example.tetris.R;

public class SoundManager {
	private SoundPool sp;
	private Map<Integer, Integer> soundMap;

	int mid;
	private Context c;
	
	private static SoundManager instance;
	@SuppressWarnings("deprecation")
	private SoundManager(Context context) {
		// TODO Auto-generated constructor stub
		this.soundMap = new HashMap<Integer, Integer>();
		this.c = context;
		sp = new SoundPool(3, AudioManager.STREAM_MUSIC, 0);
		addSound(R.raw.block,1);
		addSound(R.raw.block2,1);
		addSound(R.raw.del,0);
	}
	public static SoundManager getInstance(Context context){
		if(instance == null)
			instance = new SoundManager(context);
		return instance;
	}
	public void addSound(int resId, int priority){
		mid = sp.load(c, resId, priority);
		soundMap.put(resId, mid);
		
	}
	public void addSound(int resId) {
		mid = sp.load(c, resId, 0);
		soundMap.put(resId, mid);
	}

	public int getSound(int resId) {
		return soundMap.get(resId);
	}

	public void playSound(int resId) {
		sp.play(soundMap.get(resId), 1, 1, 0, 0, 1);
	}
	public void playSound(int resId,float rate) {
		sp.play(soundMap.get(resId), 1, 1, 0, 0, rate);
	}

	public void stopSound(int resId) {
		sp.stop(soundMap.get(resId));
	}

}

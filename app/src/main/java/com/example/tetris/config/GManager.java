package com.example.tetris.config;

import com.example.tetris.env.CollisionManager;
import com.example.tetris.env.GDisplay;
import com.example.tetris.env.GTable;
import com.example.tetris.env.NextShapeDisplay;
import com.example.tetris.env.SoundManager;
import com.example.tetris.env.TouchPad;
import com.example.tetris.shape.ShapeFactory;
import com.example.tetris.view.GameView;
import com.example.tetris.view.SurfaceViewThread;

public class GManager {
	private static GManager instance;
	
	private GameView gv;
	private SurfaceViewThread th;
	private NextShapeDisplay nsd;
	private GDisplay gd;
	private GTable gmap;
	private ShapeFactory sf;
	private CollisionManager cm;
	private TouchPad tp;
	private SoundManager sm;
	
	
	public SoundManager getSm() {
		return sm;
	}

	public GManager setSm(SoundManager sm) {
		this.sm = sm;
		return this;
	}

	public TouchPad getTp() {
		return tp;
	}

	public GManager setTp(TouchPad tp) {
		this.tp = tp;
		return this;
	}

	public CollisionManager getCm() {
		return cm;
	}

	public GManager setCm(CollisionManager cm) {
		this.cm = cm;
		return this;
	}

	public GameView getGv() {
		return gv;
	}
	
	public SurfaceViewThread getTh() {
		return th;
	}

	public GManager setTh(SurfaceViewThread th) {
		this.th = th;
		return this;
	}

	public NextShapeDisplay getNsd() {
		return nsd;
	}

	public GManager setNsd(NextShapeDisplay nsd) {
		this.nsd = nsd;
		return this;
	}

	public GDisplay getGd() {
		return gd;
	}

	public GManager setGd(GDisplay gd) {
		this.gd = gd;
		return this;
	}

	public GTable getGmap() {
		return gmap;
	}

	public GManager setGmap(GTable gmap) {
		this.gmap = gmap;
		return this;
	}

	public ShapeFactory getSf() {
		return sf;
	}

	public GManager setSf(ShapeFactory sf) {
		this.sf = sf;
		return this;
	}

	public GManager setGv(GameView gv) {
		this.gv = gv;
		return this;
	}

	public static GManager getInstance(){
		if(instance == null){
			instance = new GManager();
		}
		return instance;
	}
	
	
}

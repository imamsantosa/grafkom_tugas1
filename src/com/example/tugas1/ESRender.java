package com.example.tugas1;

import android.content.Context; 
import android.opengl.GLSurfaceView; 
import android.util.Log;
import android.view.MotionEvent;

public class ESRender extends GLSurfaceView{
	private final GLRender glrender;
	
	public ESRender(Context context) { 
		super(context);
		// TODO Auto-generated constructor stub 
		glrender = new GLRender(); 
		setRenderer(glrender);
	}
	
	public boolean onTouchEvent(MotionEvent v){ 
		switch (v.getAction()) {
			case MotionEvent.ACTION_DOWN:
				Log.v("Test Action Down", "action down working"); 
				Log.i("Test Action Nilai", ""+Math.abs(glrender.getRunMode()-1));
				glrender.setRunMode(Math.abs(glrender.getRunMode()-1)); 
				requestRender();
		}
		return true; 
	}
}

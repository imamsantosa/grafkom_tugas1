package com.example.tugas1;

import android.content.Context; 
import android.graphics.Point;
import android.opengl.GLSurfaceView; 
import android.util.Log;
import android.view.MotionEvent;

public class ESRender extends GLSurfaceView{
	private final GLRender glrender;
	private boolean _dragging = false;
	
	public ESRender(Context context) { 
		super(context);
		glrender = new GLRender(); 
		setRenderer(glrender);
	}
	
	public boolean onTouchEvent(MotionEvent v){ 
		switch (v.getAction()) {
			case MotionEvent.ACTION_DOWN:
				Log.v("Test Action Down", "action down working"); 
				Log.i("Test Action Nilai", ""+Math.abs(glrender.getRunMode()-1));
				//glrender.setRunMode(Math.abs(glrender.getRunMode()-1)); 
				requestRender();
		}
		update(v);
		return true; 
	}
	
	public void update(MotionEvent event){
		Point _touchingPoint = new Point(1013, 500);
		
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			_dragging = true;
		} else if (event.getAction() == MotionEvent.ACTION_UP) {
			_dragging = false;
		}
		
		if(_dragging){
			_touchingPoint.x = (int) event.getX();
			_touchingPoint.y = glrender.height - (int) event.getY();
			
			//record touch on arrow up
			if((_touchingPoint.x >= 665 && _touchingPoint.x <= 720) && (_touchingPoint.y >= 169 && _touchingPoint.y <= 218)){
				glrender.moveUp();
			}
			
			//record touch on arrow down
			if((_touchingPoint.x >= 665 && _touchingPoint.x <= 720) && (_touchingPoint.y >= 57 && _touchingPoint.y <= 101)){
				glrender.moveDown();
			}
			
			//record touch on arrow left
			if((_touchingPoint.x >= 614 && _touchingPoint.x <= 659) && (_touchingPoint.y >= 107 && _touchingPoint.y <= 165)){
				glrender.moveLeft();
			}
			
			//record touch on arrow right
			if((_touchingPoint.x >= 727 && _touchingPoint.x <= 773) && (_touchingPoint.y >= 106 && _touchingPoint.y <= 162)){
				glrender.moveRight();
			}
			
			//record touch on circle
			if((_touchingPoint.x >= 669 && _touchingPoint.x <= 716) && (_touchingPoint.y >= 113 && _touchingPoint.y <= 160)){
				glrender.rotate();
			}
		} 
		
	}
	
}

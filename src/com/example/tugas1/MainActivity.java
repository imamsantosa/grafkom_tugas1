package com.example.tugas1;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity { 
	private ESRender glSurfaceView;
	/** Called when the activity is first created. */ 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// requesting to turn the title OFF 
		requestWindowFeature(Window.FEATURE_NO_TITLE); 
		// making it full screen
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		// Initiate the Open GL view and
		// create an instance with this activity 
		glSurfaceView = new ESRender(this);
		// set our renderer to be the main renderer with 
		// the current activity context 
		//glSurfaceView.setRenderer(new GlRenderer()); 
		setContentView(glSurfaceView);
	}

	/**
	 * Remember to resume the glSurface 
	 * */
	@Override
	protected void onResume() {
		super.onResume(); 
		glSurfaceView.onResume();
	}
	/**
	 * Also pause the glSurface
	 **/
	@Override
	protected void onPause() {	
		super.onPause(); 
		glSurfaceView.onPause();
	}
	
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present. 
		//getMenuInflater().inflate(R.menu.main,menu);
		return true;
	}
}
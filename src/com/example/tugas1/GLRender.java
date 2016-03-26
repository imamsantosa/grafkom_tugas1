package com.example.tugas1;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import android.opengl.GLU;
import android.opengl.GLSurfaceView.Renderer;
import android.util.Log;

public class GLRender implements Renderer {
	private GLObject object;
	private float speedBesar = 2.0f;
	private int RunMode = 1;
	// Angle in degrees
	private float AngleKincirBesar = 0.0f;
	private float AngleKincirKecil = 0.0f;
	private float sumbu_x = 0.0f;
	private float sumbu_y = 0.0f;
	private float motion = 0.02f;
	private float angle_rumah = 0.0f;
	int height;
	int width;
	int x;
	int y;
	
	public GLRender() {
		this.object = new GLObject();
	}
	
	@Override
	public void onDrawFrame(GL10 gl) {
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		gl.glEnable(GL10.GL_DEPTH_TEST);
		gl.glLoadIdentity();
		
		lingkaran(gl);
		persegiPanjang(gl);
		
		kincir(gl);
		gl.glPushMatrix();
			gl.glTranslatef(0.0f, 0.0f, 0.0f);
			gl.glTranslatef(sumbu_x, sumbu_y, 0.0f);
			rumah(gl);
		gl.glPopMatrix();
		kontrol(gl);
		
	}
	
	/**
	 * action for move to up of house
	 */
	public void moveUp(){
		sumbu_y += motion;
	}
	
	/**
	 * action for move to down of house
	 */
	public void moveDown(){
		sumbu_y -= motion;
	}
	
	/**
	 * action for move to right of house
	 */
	public void moveRight(){
		sumbu_x += motion;
	}
	
	/**
	 * action for move to left of house
	 */
	public void moveLeft(){
		sumbu_x -= motion;
	}
	
	/**
	 * action for rotate of house
	 */
	public void rotate(){
		angle_rumah += 2.0f;
		if (angle_rumah > 360.0) {
			angle_rumah = 0.0f;
			angle_rumah += 2.0f;
		}
	}
	
	/**
	 * to make control button object
	 * @param gl
	 */
	private void kontrol(GL10 gl){
		//panah atas
		gl.glPushMatrix();
			gl.glTranslatef(1.7f, -0.8f, -5.0f);
			//gl.glScalef(3.0f, 3.0f, 3.0f);
			object.panah_kontrol(gl);
		gl.glPopMatrix();
		
		//panah kiri
		gl.glPushMatrix();
			gl.glTranslatef(2.2f, -1.5f, -5.0f);
			gl.glRotatef(90, 0.0f, 0.0f, 1.0f);
			//gl.glScalef(3.0f, 3.0f, 3.0f);
			object.panah_kontrol(gl);
		gl.glPopMatrix();
		
		//panah bawah
		gl.glPushMatrix();
			gl.glTranslatef(2.9f, -1.0f, -5.0f);
			gl.glRotatef(180, 0.0f, 0.0f, 1.0f);
			//gl.glScalef(3.0f, 3.0f, 3.0f);
			object.panah_kontrol(gl);
		gl.glPopMatrix();
		
		//panah kanan
		gl.glPushMatrix();
			gl.glTranslatef(2.4f, -0.3f, -5.0f);
			gl.glRotatef(270, 0.0f, 0.0f, 1.0f);
			//gl.glScalef(3.0f, 3.0f, 3.0f);
			object.panah_kontrol(gl);
		gl.glPopMatrix();
		
		//lingkaran tengah
		gl.glPushMatrix();
			gl.glTranslatef(2.3f, -0.9f, -5.0f);
			gl.glScalef(0.2f, 0.2f, 0.2f);
			object.lingkaran_kontrol(gl);
		gl.glPopMatrix();
	}
	
	/**
	 * to make rectangle object
	 * @param gl
	 */
	private void  persegiPanjang(GL10 gl){
		gl.glPushMatrix();
			gl.glTranslatef(-2.7f, 1.0f, -5.0f);
			//gl.glScalef(3.0f, 3.0f, 3.0f);
			object.persegi_panjang(gl);
		gl.glPopMatrix();
	}
	
	/**
	 * to make circle object
	 * @param gl
	 */
	private void lingkaran(GL10 gl){
		gl.glPushMatrix();
			gl.glTranslatef(0.0f, 0.0f, -5.0f);
			gl.glScalef(0.9f, 0.9f, 0.9f);
			gl.glTranslatef(4.0f, 2.5f, -5.0f);
			object.lingkaran(gl);
		gl.glPopMatrix();
	}
	
	/**
	 * to make house object
	 * @param gl
	 */
	private void rumah(GL10 gl){
		float skala = 1.8f;
		float posisi_x = -2.3f;
		float posisi_y = -1.0f;
		
		gl.glPushMatrix();
			gl.glTranslatef(posisi_x, posisi_y, -5.0f);
			gl.glScalef(skala, skala, skala);
			gl.glRotatef(angle_rumah, 0.0f, 0.0f, 1.0f);
			object.tembok_rumah(gl);
		gl.glPopMatrix();
		
		gl.glPushMatrix();
			gl.glTranslatef(posisi_x, posisi_y, -5.0f);
			gl.glScalef(skala, skala, skala);
			gl.glRotatef(angle_rumah, 0.0f, 0.0f, 1.0f);
			object.pintu_rumah(gl);
		gl.glPopMatrix();
					
		gl.glPushMatrix();
			gl.glTranslatef(posisi_x, posisi_y, -5.0f);
			gl.glScalef(skala, skala, skala);
			gl.glRotatef(angle_rumah, 0.0f, 0.0f, 1.0f);
			object.cerobong_asap_rumah(gl);
		gl.glPopMatrix();
	
		gl.glPushMatrix();
			gl.glTranslatef(posisi_x, posisi_y, -5.0f);
			gl.glScalef(skala, skala, skala);
			gl.glRotatef(angle_rumah, 0.0f, 0.0f, 1.0f);
			object.atap_rumah(gl);
		gl.glPopMatrix();
	}
	
	/**
	 * to make windmill object
	 * @param gl
	 */
	private void kincir(GL10 gl) {
		// membuat segitiga untuk kincir
		gl.glPushMatrix();
		gl.glTranslatef(0.0f, 0.0f, 0.0f);
		// move y+1
		gl.glTranslatef(0.0f, -1.0f, 0.0f);
		gl.glTranslatef(0.0f, 0.0f, -7.0f);
		object.segitiga_kincir(gl);
		gl.glPopMatrix();

		// Membuat Baling-Baling Besar
		gl.glPushMatrix();
		gl.glTranslatef(0.0f, 0.0f, -5.0f);
		gl.glRotatef(-AngleKincirBesar, 0.0f, 0.0f, 1.0f);
		object.persegi_panjang_kincir_besar(gl);
		gl.glPopMatrix();
		
		gl.glPushMatrix();
		gl.glTranslatef(0.0f, 0.0f, -5.0f);
		gl.glRotatef(-AngleKincirBesar, 0.0f, 0.0f, 1.0f);
		gl.glRotatef(90, 0.0f, 0.0f, 1.0f);
		object.persegi_panjang_kincir_besar(gl);
		gl.glPopMatrix();

		// membuat baling baling kecil 1
		gl.glPushMatrix();
			// diputar lagi agar mengikuti kincir besar
			gl.glRotatef(-AngleKincirBesar, 0.0f, 0.0f, 1.0f);
			// dipindah posisinya pada kincir atas
			gl.glTranslatef(0.0f, 0.7f, -5.0f);
			// setelah diputar baru dikecilkan
			gl.glScalef(0.5f, 0.5f, 0.5f);
			// diputar dulu objeknya
			gl.glRotatef(-AngleKincirKecil, 0.0f, 0.0f, 1.0f);
			object.persegi_panjang_kincir_kecil(gl);
		gl.glPopMatrix();
		
		gl.glPushMatrix();
		gl.glRotatef(-AngleKincirBesar, 0.0f, 0.0f, 1.0f);
		gl.glTranslatef(0.0f, 0.7f, -5.0f);
		gl.glRotatef(-AngleKincirKecil, 0.0f, 0.0f, 1.0f);
		gl.glRotatef(90, 0.0f, 0.0f, 1.0f);
		gl.glScalef(0.5f, 0.5f, 0.5f);
		object.persegi_panjang_kincir_kecil(gl);
		gl.glPopMatrix();

		// membuat baling baling kecil 2
		gl.glPushMatrix();
		gl.glRotatef(-AngleKincirBesar, 0.0f, 0.0f, 1.0f);
		gl.glTranslatef(0.0f, -0.7f, -5.0f);
		gl.glScalef(0.5f, 0.5f, 0.5f);
		gl.glRotatef(-AngleKincirKecil, 0.0f, 0.0f, 1.0f);
		object.persegi_panjang_kincir_kecil(gl);
		gl.glPopMatrix();
		gl.glPushMatrix();
		gl.glRotatef(-AngleKincirBesar, 0.0f, 0.0f, 1.0f);
		gl.glTranslatef(0.0f, -0.7f, -5.0f);
		gl.glRotatef(-AngleKincirKecil, 0.0f, 0.0f, 1.0f);
		gl.glRotatef(90, 0.0f, 0.0f, 1.0f);
		gl.glScalef(0.5f, 0.5f, 0.5f);
		object.persegi_panjang_kincir_kecil(gl);
		gl.glPopMatrix();

		// membuat baling baling kecil 3
		gl.glPushMatrix();
		gl.glRotatef(-AngleKincirBesar, 0.0f, 0.0f, 1.0f);
		gl.glTranslatef(0.7f, 0.0f, -5.0f);
		gl.glScalef(0.5f, 0.5f, 0.5f);
		gl.glRotatef(-AngleKincirKecil, 0.0f, 0.0f, 1.0f);
		object.persegi_panjang_kincir_kecil(gl);
		gl.glPopMatrix();
		gl.glPushMatrix();
		gl.glRotatef(-AngleKincirBesar, 0.0f, 0.0f, 1.0f);
		gl.glTranslatef(0.7f, 0.0f, -5.0f);
		gl.glRotatef(-AngleKincirKecil, 0.0f, 0.0f, 1.0f);
		gl.glRotatef(90, 0.0f, 0.0f, 1.0f);
		gl.glScalef(0.5f, 0.5f, 0.5f);
		object.persegi_panjang_kincir_kecil(gl);
		gl.glPopMatrix();

		// membuat baling baling kecil 4
		gl.glPushMatrix();
		gl.glRotatef(-AngleKincirBesar, 0.0f, 0.0f, 1.0f);
		gl.glTranslatef(-0.7f, 0.0f, -5.0f);
		gl.glScalef(0.5f, 0.5f, 0.5f);
		gl.glRotatef(-AngleKincirKecil, 0.0f, 0.0f, 1.0f);
		object.persegi_panjang_kincir_kecil(gl);
		gl.glPopMatrix();
		gl.glPushMatrix();
		gl.glRotatef(-AngleKincirBesar, 0.0f, 0.0f, 1.0f);
		gl.glTranslatef(-0.7f, 0.0f, -5.0f);
		gl.glRotatef(-AngleKincirKecil, 0.0f, 0.0f, 1.0f);
		gl.glRotatef(90, 0.0f, 0.0f, 1.0f);
		gl.glScalef(0.5f, 0.5f, 0.5f);
		object.persegi_panjang_kincir_kecil(gl);
		gl.glPopMatrix();
		
		// membuat titik kecil
		gl.glPushMatrix();
			gl.glRotatef(-AngleKincirBesar, 0.0f, 0.0f, 1.0f);
			gl.glTranslatef(0.0f, 0.0f, -5.0f);
			gl.glTranslatef(0.0f, 0.70f, 0f);
			gl.glScalef(0.025f, 0.025f, 0.025f);
			object.lingkaran_kecil_kincir(gl);
		gl.glPopMatrix();
		
		// membuat titik kecil
		gl.glPushMatrix();
			gl.glRotatef(-AngleKincirBesar, 0.0f, 0.0f, 1.0f);
			gl.glTranslatef(0.0f, 0.0f, -5.0f);
			gl.glTranslatef(0.0f, -0.70f, 0f);
			gl.glScalef(0.025f, 0.025f, 0.025f);
			object.lingkaran_kecil_kincir(gl);
		gl.glPopMatrix();

		// membuat titik kecil
		gl.glPushMatrix();
			gl.glRotatef(-AngleKincirBesar, 0.0f, 0.0f, 1.0f);
			gl.glTranslatef(0.0f, 0.0f, -5.0f);
			gl.glTranslatef(0.7f, 0.0f, 0f);
			gl.glScalef(0.025f, 0.025f, 0.025f);
			object.lingkaran_kecil_kincir(gl);
		gl.glPopMatrix();

		// membuat titik kecil
		gl.glPushMatrix();
			gl.glRotatef(-AngleKincirBesar, 0.0f, 0.0f, 1.0f);
			gl.glTranslatef(0.0f, 0.0f, -5.0f);
			gl.glTranslatef(-0.7f, 0.0f, 0f);
			gl.glScalef(0.025f, 0.025f, 0.025f);
			object.lingkaran_kecil_kincir(gl);
		gl.glPopMatrix();

		if (RunMode == 1) {
			// re-Calculate animation parameters
			
			AngleKincirBesar += speedBesar;
			if (AngleKincirBesar > 360.0) {
				AngleKincirBesar = 0.0f;
				AngleKincirBesar += speedBesar;
			}

			float speedKecil = 8.0f;
			AngleKincirKecil += speedKecil;
			if (AngleKincirKecil > 360.0) {
				AngleKincirKecil = 0.0f;
				AngleKincirKecil += speedKecil;
			}

		}
	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		// Prevent A Divide By Zero By
		/**if (height == 0) {
			// Making Height Equal One
			height = 1;
		}*/
		
		this.width = width;
		this.height = height;
		
		// Reset The Current Viewport
		gl.glViewport(0, 0, width, height);
		// Select The Projection Matrix
		gl.glMatrixMode(GL10.GL_PROJECTION);
		// Reset The Projection Matrix
		gl.glLoadIdentity();
		// Calculate The Aspect Ratio Of The Window
		GLU.gluPerspective(gl, 45.0f, (float) width / (float) height, 0.1f,
				100.0f);
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		// Select The Modelview Matrix
		gl.glLoadIdentity();
		// Reset The Modelview Matrix
	}

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		object = new GLObject();
		gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
		// Enable Smooth Shading, default not really needed
		gl.glShadeModel(GL10.GL_SMOOTH);
		// Depth buffer setup.
		gl.glClearDepthf(1.0f);
		// Enables depth testing.
		gl.glEnable(GL10.GL_DEPTH_TEST);
		// The type of depth testing to do.
		gl.glDepthFunc(GL10.GL_LEQUAL);
		// Really nice perspective calculations.
		gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST);
	}

	public void setRunMode(int runMode) {
		RunMode = runMode;
	}

	public int getRunMode() {
		return RunMode;
	}

}

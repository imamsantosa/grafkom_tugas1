package com.example.tugas1;

import java.io.Console;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLES20;
import android.util.Log;

public class GLObject {
	
	private float vertices_circle[] = null;
	private float vertices_circle_color[] = null;
	private float batas_sudut_lower = 0.0f, batas_sudut_upper = 360.0f;
	float jari_jari;
	float a, b;
	float x, y;
	private int loop, loop_color;
	float step;
	int byk_vertices, byk_vertices1; 
	int realloop;

	private float getApproxValue(float f) {
		if (Math.abs(f) < 0.001) {
			return 0;
		}
		return f;
	}
	
	public GLObject(){
		// ============ start to generate vertices to circle
		// ==========================
		// Inisialisasi
		jari_jari = 1.0f;

		// Titik Pusat
		a = 0.0f;
		b = 0.0f;
		x = a + jari_jari;
		y = b;
		// step = 1.0f;
		step = 3.0f;
		byk_vertices1 = (int) (((batas_sudut_upper - batas_sudut_lower) + step) / step);
		// byk_vertices=(((int)
		// Math.floor((batas_sudut_upper-batas_sudut_lower)/ step))+1); //
		// 4.7/0.1 = 46.9999 ??
		byk_vertices = (((int) Math
				.round((batas_sudut_upper - batas_sudut_lower) / step)) + 1);

		loop = 0;
		loop_color = 0;
		realloop = 0;
		// menambah nilai +1 untuk byk_vertices untuk menghindari pemesanan
		// memori yang kurang
		vertices_circle = new float[(byk_vertices + 1) * 3];
		vertices_circle_color = new float[(byk_vertices + 1) * 4];
		for (float teta = batas_sudut_lower; teta <= batas_sudut_upper + step; teta += step) {
			vertices_circle[loop] = (float) ((x - a)
					* getApproxValue((float) Math.cos(Math.toRadians(teta)))
					- ((y - b) * getApproxValue((float) Math.sin(Math
							.toRadians(teta)))) + a);
			vertices_circle[loop + 1] = (float) ((x - a)
					* getApproxValue((float) Math.sin(Math.toRadians(teta)))
					- ((y - b) * getApproxValue((float) Math.cos(Math
							.toRadians(teta)))) + b);
			vertices_circle[loop + 2] = 0;
			loop += 3;
			realloop = realloop + 1;

			// mengenerate warna untuk setiap vertex
			vertices_circle_color[loop_color] = (float) ((x - a)
					* Math.cos(Math.toRadians(teta))
					- ((y - b) * Math.sin(Math.toRadians(teta))) + a);
			vertices_circle_color[loop_color + 1] = (float) ((x - a)
					* Math.sin(Math.toRadians(teta))
					- ((y - b) * Math.cos(Math.toRadians(teta))) + b);
			vertices_circle_color[loop_color + 2] = 0.5f;
			vertices_circle_color[loop_color + 3] = 0.5f;
			loop_color += 4;
		}
	}
	
	public static FloatBuffer makeFloatBuffer(float[] arr){
		ByteBuffer bb = ByteBuffer.allocateDirect(arr.length * 4);
		bb.order(ByteOrder.nativeOrder());
		FloatBuffer fb = bb.asFloatBuffer();
		fb.put(arr);
		fb.position(0);
		return fb;
	}
	
	public void lingkaran_kecil_kincir(GL10 gl) {
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);

        // memberi warna
        gl.glColor4f(0.0f, 0.0f, 0.0f, 1.0f);

        // membuat titik lingkaran
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0,  makeFloatBuffer(vertices_circle));
        
        //menggambar lingkaran
        gl.glDrawArrays(GL10.GL_TRIANGLE_FAN, 0, byk_vertices);

        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
    }
	
	public void persegi_panjang_kincir_besar(GL10 gl) {
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
		
		// menentukan titik-titik sudut segitiga sama kaki
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, makeFloatBuffer(new float []{
				-0.8f, -0.1f, 0.0f, // v1 - segitiga 1
				-0.8f,  0.1f, 0.0f, // v2 - segitiga 1
				 0.8f,  0.1f, 0.0f, // v3 - segitiga 1 & 2
				 0.8f, -0.1f, 0.0f, // v2 - segitiga 2
				-0.8f, -0.1f, 0.0f //V1 - segitiga 2
		}));
		
		// memberi warna persegi dengan degradasi warna dari titik sudutnya
		gl.glColorPointer(4, GL10.GL_FLOAT, 0, makeFloatBuffer(new float[]{
				1.0f, 0.5f, 0.0f, 1.0f,
				1.0f, 0.5f, 0.0f, 1.0f,
				1.0f, 0.5f, 0.0f, 1.0f,
				1.0f, 0.5f, 0.0f, 1.0f,
				1.0f, 0.5f, 0.0f, 1.0f,
		}));
		
		//menggambar segitiga pertama (atas) 
		gl.glDrawArrays(GL10.GL_TRIANGLES, 0, 3);
		
		//menggambar segitiga kedua (bawah)
		gl.glDrawArrays(GL10.GL_TRIANGLES, 2, 3);
		
		//Disable the client state before leaving
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
	}
	
	public void persegi_panjang_kincir_kecil(GL10 gl) {
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
		
		// menentukan titik-titik sudut segitiga siku-siku
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, makeFloatBuffer(new float []{
				-0.5f, -0.1f, 0.0f, // v1 - segitiga 1
				-0.5f,  0.1f, 0.0f, // v2 - segitiga 1
				 0.5f,  0.1f, 0.0f, // v3 - segitiga 1 & 2
				 0.5f, -0.1f, 0.0f, // v2 - segitiga 2
				-0.5f, -0.1f, 0.0f //V1 - segitiga 2
		}));
		
		// memberi warna persegi dengan degradasi warna dari titik sudutnya
		gl.glColorPointer(4, GL10.GL_FLOAT, 0, makeFloatBuffer(new float[]{
				0.0f, 1.0f, 1.0f, 1.0f,
				0.0f, 1.0f, 1.0f, 1.0f,
				0.0f, 1.0f, 1.0f, 1.0f,
				0.0f, 1.0f, 1.0f, 1.0f,
				0.0f, 1.0f, 1.0f, 1.0f,
		}));
		
		//menggambar segitiga pertama (atas)
		gl.glDrawArrays(GL10.GL_TRIANGLES, 0, 3);
		//menggambar segitiga kedua (bawah)
		gl.glDrawArrays(GL10.GL_TRIANGLES, 2, 3);
		
		//Disable the client state before leaving
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
	}
	
	public void segitiga_kincir(GL10 gl) {
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
		
		//menentukan titik sudut segitiga
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0,	makeFloatBuffer(new float [] {
			-0.7f, -1.7f, 0.0f, //	V1 
			 0.7f, -1.7f, 0.0f, // V2 
			 0.0f,  1.0f, 0.0f // V3 
		}));
		
		// Mewarnai segitiga dengan warna biru
		gl.glColorPointer(4, GL10.GL_FLOAT, 0,	makeFloatBuffer(new float[]{
				0.0f, 0.0f, 1.0f, 1.0f,
				0.0f, 0.0f, 1.0f, 0.1f,
				0.0f, 0.0f, 1.0f, 0.1f,
				0.0f, 0.0f, 1.0f, 0.1f,
		}));
		
		// Menggambar segitiga
		gl.glDrawArrays(GLES20.GL_TRIANGLES, 0,	3);
		
		//Disable the client state before leaving
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
	}
	
	public void tembok_rumah(GL10 gl){
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
		
		// menentukan titik-titik sudut segitiga siku siku
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, makeFloatBuffer(new float []{
				-1.0f, -1.0f, 0.0f, // v1 - segitiga 1
				-0.4f, -1.0f, 0.0f, // v2 - segitiga 1 & 2
				-1.0f, -0.6f, 0.0f, // v3 - segitiga 1 & 2
				-0.4f, -0.6f, 0.0f, // v2 - segitiga 2
		}));
		
		// memberi warna persegi
		gl.glColorPointer(4, GL10.GL_FLOAT, 0, makeFloatBuffer(new float[]{
				0.53f, 0.12f, 0.47f, 1.0f,
				0.53f, 0.12f, 0.47f, 1.0f,
				0.53f, 0.12f, 0.47f, 1.0f,
				0.53f, 0.12f, 0.47f, 1.0f,
		}));
		
		//menggambar segitiga pertama (atas)
		gl.glDrawArrays(GL10.GL_TRIANGLES, 0, 3);
		//menggambar segitiga kedua (bawah)
		gl.glDrawArrays(GL10.GL_TRIANGLES, 1, 3);
		
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
	}
	
	public void atap_rumah(GL10 gl){
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
		
		// menentukan titik-titik sudut segitiga sama kaki
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, makeFloatBuffer(new float []{
				-1.1f, -0.6f, 0.0f, // v1 
				-0.3f, -0.6f, 0.0f, // v2 
				-0.7f, -0.3f, 0.0f, // v3 
		}));
		
		// memberi warna segitiga
		gl.glColorPointer(4, GL10.GL_FLOAT, 0, makeFloatBuffer(new float[]{
				0.55f, 0.39f, 0.14f, 1.0f,
				0.55f, 0.39f, 0.14f, 1.0f,
				0.55f, 0.39f, 0.14f, 1.0f,
		}));
		
		//menggambar segitiga
		gl.glDrawArrays(GL10.GL_TRIANGLES, 0, 3);
		
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
	}
	
	public void cerobong_asap_rumah(GL10 gl){
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
		
		// menentukan titik-titik sudut segitiga siku siku
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, makeFloatBuffer(new float []{
				-0.55f, -0.6f,  0.0f, // v1 - segitiga 1
				-0.55f, -0.35f, 0.0f, // v2 - segitiga 1 & 2
				-0.48f, -0.6f,  0.0f, // v3 - segitiga 1 & 2
				-0.48f, -0.35f, 0.0f, // v2 - segitiga 2
		}));
		
		// memberi warna persegi
		gl.glColorPointer(4, GL10.GL_FLOAT, 0, makeFloatBuffer(new float[]{
				0.60f, 0.61f, 0.68f, 1.0f,
				0.60f, 0.61f, 0.68f, 1.0f,
				0.60f, 0.61f, 0.68f, 1.0f,
				0.60f, 0.61f, 0.68f, 1.0f,
		}));
		
		//menggambar segitiga siku siku atas
		gl.glDrawArrays(GL10.GL_TRIANGLES, 0, 3);
		//menggambar segitiga siku siku bawah
		gl.glDrawArrays(GL10.GL_TRIANGLES, 1, 3);
		
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
	}
	
	public void pintu_rumah(GL10 gl){
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
		
		// menentukan titik-titik sudut segitiga siku siku
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, makeFloatBuffer(new float []{
				-0.75f, -1.00f, 0.0f, // v1 - segitiga 1
				-0.75f, -0.85f, 0.0f, // v2 - segitiga 1 & 2
				-0.65f, -1.00f, 0.0f, // v3 - segitiga 1 & 2
				-0.65f, -0.85f, 0.0f, // v2 - segitiga 2
		}));
		
		// memberi warna persegi
		gl.glColorPointer(4, GL10.GL_FLOAT, 0, makeFloatBuffer(new float[]{
				0.0f, 0.0f, 0.0f, 1.0f,
				0.0f, 0.0f, 0.0f, 1.0f,
				0.0f, 0.0f, 0.0f, 1.0f,
				0.0f, 0.0f, 0.0f, 1.0f,
		}));
		
		//menggambar segitiga siku siku atas
		gl.glDrawArrays(GL10.GL_TRIANGLES, 0, 3);
		// menggambar segitiga siku siku bawah
		gl.glDrawArrays(GL10.GL_TRIANGLES, 1, 3);
		
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
	}
	
	public void persegi_panjang(GL10 gl){
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		
		// mewarnai garis line
        gl.glColor4f(0.9f, 0.4f, -0.1f, 1.0f);
        
        // mengatur ketebalan garis
        gl.glLineWidth(3.0f);
        
		// menentukan titik-titik sudut persegi
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, makeFloatBuffer(new float []{
				-1.0f, -1.0f, 0.0f, // v1 
				-1.0f, -0.6f, 0.0f, // v3 
				-0.4f, -0.6f, 0.0f, // v2 
				-0.4f, -1.0f, 0.0f, // v2 
				
		}));
		
		//menggambar persegi dengan line loop
		gl.glDrawArrays(GL10.GL_LINE_LOOP, 0, 4);
		
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
	}
	
	public void lingkaran(GL10 gl){
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);

        // mewarnai garis line
        gl.glColor4f(0.9f, 0.0f, 0.0f, 1.0f);

        // membuat titik lingkaran
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0,  makeFloatBuffer(vertices_circle));
        
        // mengubah ketebalan garis
        gl.glLineWidth(5.0f);
        
        //menggambar lingkaran
        gl.glDrawArrays(GL10.GL_LINE_LOOP, 0, byk_vertices);

        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
	}
	
	public void lingkaran_kontrol(GL10 gl) {
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);

        // mewarnai lingkaran
        gl.glColor4f(1.0f, 0.0f, 0.0f, 1.0f);

        // membuat titik lingkaran
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0,  makeFloatBuffer(vertices_circle));

        //menggambar lingkaran
        gl.glDrawArrays(GL10.GL_TRIANGLE_FAN, 0, byk_vertices);

        // Disable the client state before leaving
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
    }
	
	public void panah_kontrol(GL10 gl){
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
		
		// menentukan titik-titik sudut segitiga sama kaki
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, makeFloatBuffer(new float []{
				0.3f, 0.4f, 0.0f, // v1 
				0.6f, 0.6f, 0.0f, // v2 
				0.9f, 0.4f, 0.0f, // v3
				0.8f, 0.2f, 0.0f, // v5 
				0.8f, 0.4f, 0.0f, // v4
				0.4f, 0.2f, 0.0f, // v6 
				0.4f, 0.4f, 0.0f, // v7 
		}));
		
		// memberi warna persegi
		gl.glColorPointer(4, GL10.GL_FLOAT, 0, makeFloatBuffer(new float[]{
				0.137255f, 0.137255f, 0.556863f, 1.0f,
				0.137255f, 0.137255f, 0.556863f, 1.0f,
				0.137255f, 0.137255f, 0.556863f, 1.0f,
				0.137255f, 0.137255f, 0.556863f, 1.0f,
				0.137255f, 0.137255f, 0.556863f, 1.0f,
				0.137255f, 0.137255f, 0.556863f, 1.0f,
				0.137255f, 0.137255f, 0.556863f, 1.0f,
		}));
		
		//menggambar segitiga
		gl.glDrawArrays(GL10.GL_TRIANGLES, 0, 3);
		gl.glDrawArrays(GL10.GL_TRIANGLES, 3, 3);
		gl.glDrawArrays(GL10.GL_TRIANGLES, 4, 3);
		
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
	}
}

package com.example.catapult;


import com.example.catapult.R;

import android.os.Bundle;
import android.R.color;
import android.R.integer;
import android.app.Activity;
import android.view.Menu;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

public class MainView extends SurfaceView implements Callback,Runnable {
	private Resources res;
	private SurfaceHolder sfh;
	private Thread th;
	private Canvas canvas;
	private Paint paint;
	private boolean thread_flag ;
	
	private final float FLOOR_HEIGHT=82f;
	private float ScreenH;
	private float ScreenW;
	private float move_X;
	private float position_X;
	
	private Bitmap background_bottom;
	private Bitmap background_top;
	private Bitmap squirrel_1;
	private Bitmap squirrel_2;
	private Bitmap catapult_base_1;
	private Bitmap catapult_base_2;
	
	private Bitmap catapultArmBitmap;
	
	private Bitmap bulletBitmap;
	public MainView(Context context){
		super(context);
		res=this.getResources();
		sfh=this.getHolder();
		sfh.addCallback(this);
		paint=new Paint();
		paint.setAntiAlias(true);
		paint.setColor(Color.RED);
		this.setKeepScreenOn(true);
		
		background_top=BitmapFactory.decodeResource(res, R.drawable.bg);
		background_bottom = BitmapFactory.decodeResource(res, R.drawable.fg);
		
		 squirrel_1 = BitmapFactory.decodeResource(res, R.drawable.squirrel_1);
	     squirrel_2 = BitmapFactory.decodeResource(res, R.drawable.squirrel_2);
	     
	     catapult_base_1 = BitmapFactory.decodeResource(res, R.drawable.catapult_base_1);
	     catapult_base_2 = BitmapFactory.decodeResource(res, R.drawable.catapult_base_2);
	     
	     catapultArmBitmap = BitmapFactory.decodeResource(res, R.drawable.catapult_arm);
	     
	     bulletBitmap = BitmapFactory.decodeResource(res, R.drawable.acorn);
	}
	@Override
	public void surfaceChanged(SurfaceHolder arg0,int arg1,int arg2,int arg3){
	
	}
	public void surfaceCreated(SurfaceHolder arg0){
		ScreenW=this.getWidth();
		ScreenH=this.getHeight();
		thread_flag=true;
		th=new Thread(this);
		th.start();
	}
	public void surfaceDestroyed(SurfaceHolder arg0){
		
	}
	public void run(){
		draw();
	}
	public void draw(){
		 try {
			canvas=sfh.lockCanvas();
			if(canvas!=null){
				canvas.drawColor(color.white);
				canvas.drawBitmap(background_top, 0-move_X/2,0, paint);
                canvas.drawBitmap(catapult_base_2,260-move_X,ScreenH-FLOOR_HEIGHT-catapult_base_2.getHeight()-catapult_base_2.getHeight()/4,paint);
                canvas.drawBitmap(background_bottom, 0-move_X, ScreenH-background_bottom.getHeight(), paint);
	           // ((CatapultArm)catapultArmBody.m_userData).draw(canvas, paint, move_X);
	            
	            canvas.drawBitmap(catapult_base_1,265-move_X,ScreenH-FLOOR_HEIGHT-catapult_base_1.getHeight()-catapult_base_1.getHeight()/4,paint);
	            
	            canvas.drawBitmap(squirrel_1, 50-move_X, ScreenH-FLOOR_HEIGHT-squirrel_1.getHeight(), paint);
	            canvas.drawBitmap(squirrel_2, 350-move_X, ScreenH-FLOOR_HEIGHT-squirrel_2.getHeight(), paint);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			if(canvas!=null)
				sfh.unlockCanvasAndPost(canvas);
		}
		
	}

}

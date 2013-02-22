package com.mad_duo.centerforpuppetryarts;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;

public class ImageButtonView implements OnTouchListener 
{
	private ImageView view;
	private Bitmap img;
	private OnTouchListener listener;
	
	public ImageButtonView(ImageView v) 
	{
		view = v;
		img = ((BitmapDrawable) view.getDrawable()).getBitmap();
	}
	
	public ImageButtonView(ImageView v, int id) 
	{
		view = v;
		setBackgroundResource(id);
	}

	public void setBackgroundResource(int id) 
	{
		view.setBackgroundResource(id);
		img = BitmapFactory.decodeResource(view.getResources(), id);
	}

	public boolean onTouch(View v, MotionEvent event) 
	{
		if(event.getAction() == MotionEvent.ACTION_UP)
		{
			// coordinates of the touch action
			int xTouch = (int) event.getX();
			int yTouch = (int) event.getY();
			// transparency at the point of the touch
			int transparency = Color.alpha(img.getPixel(xTouch, yTouch));
			
			// determine if it was a click
			if(xTouch < img.getWidth() && yTouch < img.getHeight() 
					&& transparency > 100)
			{
				listener.onTouch(v, event);	
			}
		}
		
		return true;
	}
	
	public void setOnTouchListener(OnTouchListener l)
	{
		view.setOnTouchListener(this);
		listener = l;
	}
	
	public float getWidth()
	{
		return img.getWidth();
	}
	
	public float getHeight()
	{
		return img.getHeight();
	}
}

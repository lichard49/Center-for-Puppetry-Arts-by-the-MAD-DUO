package com.mad_duo.centerforpuppetryarts;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MuseumActivity extends Activity 
{
	final String MUSEUM_URL = "http://puppet.org/museum/index.shtml";
	
	private Button membershipButton;
	private TextView membershipBenefits;
	
	private ImageButtonView facebookButton;
	private ImageButtonView twitterButton;
	private ImageButtonView youtubeButton;
	private ImageButtonView logoButton;
	
	private Button museum;
	private Button membership;
	private Button film_series;
	private Button explore_workshop;
	private Button create_puppet;
	
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.museum_layout);
		
		facebookButton = new ImageButtonView((ImageView) findViewById(R.id.facebook_icon));
        twitterButton = new ImageButtonView((ImageView) findViewById(R.id.twitter_icon));
        youtubeButton = new ImageButtonView((ImageView) findViewById(R.id.youtube_icon));
        logoButton = new ImageButtonView((ImageView) findViewById(R.id.logo_button));
        Data.socialMediaLinks(MuseumActivity.this, logoButton, facebookButton, 
        		twitterButton, youtubeButton);
        
        membershipButton = (Button) findViewById(R.id.membership);
        membershipButton.setOnClickListener(new OnClickListener()
        {
        	public void onClick(View v)
        	{
        		MembershipDialog d = new MembershipDialog(MuseumActivity.this, h);
        		membershipBenefits = (TextView) d.findViewById(R.id.membership_benefits);
        	}
        });
         
        museum=(Button) findViewById(R.id.museum);
        museum.setOnClickListener(new OnClickListener()
        {
        	public void onClick(View v)
        	{
        		Dialog d = new Dialog(MuseumActivity.this);
        		d.setTitle("Our Museum");
        		d.setContentView(R.layout.museum);
        		d.show();
        	}
        });
        
        film_series=(Button) findViewById(R.id.film_series);
        film_series.setOnTouchListener(new OnTouchListener()
        {
        	public boolean onTouch(View v, MotionEvent e)
        	{
        		Dialog d = new Dialog(MuseumActivity.this);
        		d.setTitle("The Film Series");
        		d.show();
        		return true;
        	}
        });
         
        explore_workshop=(Button) findViewById(R.id.explore_workshop);
        explore_workshop.setOnTouchListener(new OnTouchListener()
        {
        	public boolean onTouch(View v, MotionEvent e)
        	{
        		Dialog d = new Dialog(MuseumActivity.this);
        		d.setTitle("Explore Workshop");
        		d.setContentView(R.layout.explore_workshop);
        		d.show();
        		return true;
        	}
        });
         
        create_puppet=(Button) findViewById(R.id.create_puppet);
        create_puppet.setOnTouchListener(new OnTouchListener()
        {
        	public boolean onTouch(View v, MotionEvent e)
        	{
        		Dialog d = new Dialog(MuseumActivity.this);
        		d.setTitle("Create-a-puppet");
        		d.setContentView(R.layout.create_puppet);
        		d.show();
        		return true;
        	}
        });
	}
	
	private Handler h = new Handler()
	{
		public void handleMessage(Message m)
		{
			membershipBenefits.setText(m.obj+"");
		}
	};
}

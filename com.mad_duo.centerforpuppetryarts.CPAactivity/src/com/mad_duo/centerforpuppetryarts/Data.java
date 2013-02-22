package com.mad_duo.centerforpuppetryarts;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.text.util.Linkify;
import android.view.ContextThemeWrapper;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.TextView;
import android.widget.Toast;

public class Data 
{
	public final static String FACEBOOK_URL = "https://www.facebook.com/CenterforPuppetryArts";
	public final static String YOUTUBE_URL = "http://www.youtube.com/user/CtrPuppetryArts";
	public final static String ABOUT_CONTACT = "The Center for Puppetry Arts is a unique cultural treasure " +
			"- a magical place where children and adults are educated, enlightened and entertained. " +
			"\n\nTicket sales: 404.873.3391\nInquiries: info@puppet.org\n1404 Spring St NW\nAtlanta, Georgia 30309";
	
	public final static String[] MEMBERSHIP_LEVEL_BENEFITS = {"Family- $90\n\n-Members only Birthday Party\n\n-Additional free admission to film screenings and Special Exhibits\n\n-Additional Tickets at Member Discount\n\n-and many more...",
		"Sponser- $150\n\n-2 Free passes to Xperimental Puppetry Theatre\n\n-Free Membership only Backstage Tours\n\n-4 Free passes to a Family Performance\n\n-and many more...",
		"Sustainer- $250\n\n-Acknowledgement in the annual Contributor's List displayed in the Center Lobby\n\n-2 Free passes for select Explore Puppetry Series of Workshops/webinars\n\n-2 Free passes to a New Directions Series for Adults and Teens Performance\n\n-and many more...",
		"Benefactor- $500\n\n-Free Subscription to Puppetry International magazine\n\n-Invitations to private donor receptions\n\n-Free Admissions to The Life and Legacey of Jim Henson Tour\n\n-and many more...",
		"Impressario and above- $1000+\n\n-Executive Tour of the Center with Founder, Vincent Anthony\n\n-Acknowledgments in programs\n\n-and many more..." };
	
	
	
	public static AlertDialog aboutContactDialog(Context c)
	{
		AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(c, android.R.style.Theme_Translucent));
		builder.setTitle("About & Contact Us");
		TextView text = new TextView(c);
		text.setText(Data.ABOUT_CONTACT);
		Linkify.addLinks(text, Linkify.PHONE_NUMBERS | Linkify.WEB_URLS);	
		builder.setView(text);
		AlertDialog result = builder.create();
		result.getWindow().setBackgroundDrawable(new ColorDrawable(0));
		return result;
	}
	
	public static void socialMediaLinks(final Context c, ImageButtonView logoButton, 
			ImageButtonView facebookButton, ImageButtonView twitterButton, 
			ImageButtonView youtubeButton)
	{
		logoButton.setOnTouchListener(new OnTouchListener()
        {
			public boolean onTouch(View v, MotionEvent e) 
			{
				Data.aboutContactDialog(c).show();
				
				return true;
			}	
        });
		facebookButton.setOnTouchListener(new OnTouchListener()
        {
        	public boolean onTouch(View v, MotionEvent e)
        	{
        		Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(Data.FACEBOOK_URL));
            	c.startActivity(browserIntent);
        		return true;
        	}
        });
		twitterButton.setOnTouchListener(new OnTouchListener()
        {
        	public boolean onTouch(View v, MotionEvent e)
        	{
        		Toast.makeText(c.getApplicationContext(), "Tweeting", Toast.LENGTH_SHORT).show();
        		return true;
        	}
        });
		youtubeButton.setOnTouchListener(new OnTouchListener()
        {
        	public boolean onTouch(View v, MotionEvent e)
        	{
        		Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(Data.YOUTUBE_URL));
            	c.startActivity(browserIntent);
        		return true;
        	}
        });
	}
}

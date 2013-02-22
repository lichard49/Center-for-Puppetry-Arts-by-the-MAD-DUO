package com.mad_duo.centerforpuppetryarts;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.CalendarView;
import android.widget.CalendarView.OnDateChangeListener;
import android.widget.ImageView;
import android.widget.TextView;

public class PerformanceActivity extends Activity implements OnDateChangeListener 
{
	private final String TICKET_SALES_URL = "http://ticketsales.puppet.org/";
	private final String DATE = "divDayContainer";
	private final String TIME = "divTime";
	private final String TITLE = "divTitle";
	private final String LOCATION = "divVenue";
	private final String BUY = "buyNow";
	private final String TAP_TO_BUY = "Tap here to purchase now!";
	private final String NO_SHOW = "No shows scheduled at this time";
	
	private Show selectedShow;
	private HashMap<String, LinkedList<Show>> calendar = new HashMap<String, LinkedList<Show>>();
	
	private CalendarView showCalendar;
	private TextView showDetails;
	
	private ImageButtonView facebookButton;
	private ImageButtonView twitterButton;
	private ImageButtonView youtubeButton;
	private ImageButtonView logoButton;
	
	public void onCreate(Bundle savedInstanceState) 
    {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.performance_layout);
		showDetails = (TextView) findViewById(R.id.show_details);
		showCalendar = (CalendarView) findViewById(R.id.show_calendar);
		showCalendar.setOnDateChangeListener(this);

		showDetails.setOnTouchListener(new OnTouchListener()
		{
			public boolean onTouch(View v, MotionEvent e)
			{
				if(e.getAction() == MotionEvent.ACTION_UP)
				{
					if(selectedShow != null && selectedShow.buyUrl != null)
					{
						Intent browserIntent = new Intent(Intent.ACTION_VIEW, 
								Uri.parse(selectedShow.buyUrl));
		            	startActivity(browserIntent);
					}
				}
				return true;
			}
		});
	
		facebookButton = new ImageButtonView((ImageView) findViewById(R.id.facebook_icon));
        twitterButton = new ImageButtonView((ImageView) findViewById(R.id.twitter_icon));
        youtubeButton = new ImageButtonView((ImageView) findViewById(R.id.youtube_icon));
        logoButton = new ImageButtonView((ImageView) findViewById(R.id.logo_button));
        Data.socialMediaLinks(PerformanceActivity.this, logoButton, facebookButton, 
        		twitterButton, youtubeButton);
		
		new Thread()
        {
        	public void run()
        	{
        		try
        		{
        			System.out.println("start");
	        		HttpClient client = new DefaultHttpClient();
	        		HttpGet request = new HttpGet(TICKET_SALES_URL);
	        		HttpResponse response = client.execute(request);
	        		System.out.println("get HTTP");
	        		
	        		InputStream in = response.getEntity().getContent();
	        		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
	        		System.out.println("streams built");
	        		String line = null;
	        		LinkedList<Show> currentList = null;
	        		Show currentShow = null;
	        		String date = "";
	        		while((line = reader.readLine()) != null)
	        		{
	        			if(line.contains(DATE))
	        			{
	        				if(currentList != null)
	        					currentList.add(currentShow);
	        				currentShow = new Show();
	        				date = line.substring(line.length()-12, line.length()-2);
	        				currentList = new LinkedList<Show>();
	        				calendar.put(date, currentList);
	        			}
	        			else if(line.contains(TIME))
	        			{
	        				try
	        				{
		        				int start = line.indexOf('>')+1;
		        				int end = line.indexOf('<', start);
		        				currentShow.time = line.substring(start, end).replace("\t", "");
	        				}
	        				catch (Exception e)
	        				{
	        					
	        				}
	        			}
	        			else if(line.contains(TITLE))
	        			{
	        				try
	        				{
		        				int start = line.indexOf('>')+1;
		        				int end = line.indexOf('<', start);
		        				currentShow.title = line.substring(start, end).replace("\t", "").replace("&#0174;", "");
	        				}
	        				catch (Exception e)
	        				{
	        					
	        				}
	        			}
	        			else if(line.contains(LOCATION))
	        			{
	        				try
	        				{
		        				int start = line.indexOf('>')+1;
		        				int end = line.indexOf('<', start);
		        				currentShow.location = line.substring(start, end).replace("\t", "") + ", ";
	        				}
	        				catch (Exception e)
	        				{
	        					
	        				}
	        			}
	        			else if(line.contains(BUY))
	        			{
	        				try
	        				{
		        				int start = line.indexOf("href=")+6;
		        				int end = line.indexOf('\"', start);
		        				currentShow.buyUrl = line.substring(start, end).replace("\t", "");
	        				}
	        				catch (Exception e)
	        				{
	        					
	        				}
	        				
	        			}
	        		}

	        		System.out.println("done");
	        		in.close();
        		}
        		catch (Exception e)
        		{
        			System.out.println("fail");
        			e.printStackTrace();
        		}
        	}
        }.start();
    }
	
	private Handler h = new Handler()
	{
		public void handleMessage(Message m)
		{
			showDetails.setText(m.obj+"");
		}
	};

	public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) 
	{
		month += 1;
		String date = year + "-" + month + "-" + dayOfMonth;
		LinkedList<Show> allShows = calendar.get(year + "-" + month + "-" + dayOfMonth);
		Message m = new Message();
		if(allShows != null)
		{
			Show details = allShows.get(0);
			selectedShow = details;
			m.obj = date + ", " + details.time + "\n" + details.title + "\n@" + details.location;
			m.obj = m.obj + "\n" + TAP_TO_BUY;
		}
		else
		{
			m.obj = date + "\n" + NO_SHOW;
			selectedShow = null;
		}
		
		h.sendMessage(m);
	}
}

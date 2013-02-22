package com.mad_duo.centerforpuppetryarts;


import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MembershipDialog extends Dialog 
{
	private Context c;
	private Spinner spinner;
	private Button membershipBuy;
	private Handler h;
	
	public MembershipDialog(Context context, Handler ha) 
	{
		super(context);
		setContentView(R.layout.membership_dialog);
		setTitle("Purchase Membership");
		
		h = ha;
		c = context;
		
		membershipBuy = (Button) findViewById(R.id.membership_buy);
		spinner = (Spinner) findViewById(R.id.membership_level);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(context,
		        R.array.membership_levels, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);
		spinner.setOnItemSelectedListener(new OnItemSelectedListener()
		{
			public void onItemSelected(AdapterView<?> parent, View v, int pos, long id) 
			{
				if(id > 1 && id < 7)
				{
					id-=2;
					Message m = new Message();
					m.obj = Data.MEMBERSHIP_LEVEL_BENEFITS[Math.min((int) id, 4)];
					h.sendMessage(m);
				}
				else
				{
					Message m = new Message();
					m.obj = "";
					h.sendMessage(m);
				}
			}
			

			public void onNothingSelected(AdapterView<?> parent) 
			{
			}
		});
		membershipBuy.setOnClickListener(new android.view.View.OnClickListener()
		{
			public void onClick(View v)
			{
				int id = spinner.getSelectedItemPosition();
				if(id > 1 && id < 7)
				{
					int amount=-1;
					try
					{
						amount = Integer.parseInt(((EditText) findViewById(R.id.membership_amount)).getText().toString());
					}
					catch (Exception e)
					{
						
					}
					if(amount == -1)
					{
						String s = (String) spinner.getItemAtPosition(id);
						s = s.substring(s.indexOf("$")+1, s.length()-1);
						amount = Integer.parseInt(s);
					}
					Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://ticketsales.puppet.org/dev/contribute.aspx?don=" + id + "&fieldAmt=" + amount));
	            	c.startActivity(browserIntent);
				}
				else
				{
					Toast.makeText(c, "Please select a membership level", Toast.LENGTH_SHORT).show();
				}
			}
		});
		show();
	}

}

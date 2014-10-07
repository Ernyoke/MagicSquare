package com.sapientia.magicsquare;

import com.sapientia.magicsquare.R;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

public class Numpad extends Dialog{

	private int size;
	Context context;
	private int result;
	
	public Numpad(Context context, int size) {
		super(context);
		this.size = size;
		this.context = context;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.num_pad);
		
		//set title
		this.setTitle(context.getString(R.string.numdialogtitle));
		
		result = -1;
		
		//generate numerical buttons
		int k = 1;
		
		TableLayout table = (TableLayout)findViewById(R.id.table_numpad);
		
		for(int i = 0; i < size; ++i) {
			TableRow row = new TableRow(context);
			row.setGravity(Gravity.CENTER);
			for(int j = 0; j < size; ++j) {
				Button button = new Button(context);
				button.setId(k);
				button.setTag(k);
				button.setText(k + "");
				button.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						result = (int)v.getTag();
						dismiss();
					}
					
				});
				row.addView(button);
				++k;
			}
			table.addView(row);
		}
		//add clear button
		Button clear =  new Button(context);
		clear.setText(context.getString(R.string.clear));
		clear.setTag(0);
		clear.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				result = (int)v.getTag();
				dismiss();
			}
		});
		TableRow row = new TableRow(context);
		row.setGravity(Gravity.CENTER);
		row.addView(clear);
		table.addView(row);
	}
	
	//return selected number if it was one, else returned value will be -1, i guess...
	public int getResult() {
		int res_temp = result;
		result = -1;
		return res_temp;
	}

}

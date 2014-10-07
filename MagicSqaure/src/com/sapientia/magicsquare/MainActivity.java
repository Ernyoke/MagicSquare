package com.sapientia.magicsquare;

import com.sapientia.magicsquare.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends Activity {
	
	private Button dim3;
	private Button dim4;
	private Button dim5;
	
	private ClickListener clickListener;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		dim3 = (Button) findViewById(R.id.dim3);
		dim4 = (Button) findViewById(R.id.dim4);
		dim5 = (Button) findViewById(R.id.dim5);
		
		clickListener = new ClickListener(this);
		
		dim3.setOnClickListener(clickListener);
		dim4.setOnClickListener(clickListener);
		dim5.setOnClickListener(clickListener);
		
	}
}

package com.sapientia.magiccube;

import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;

public class PlayGround extends Activity {
	
	private static int BORDER = 20;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_play_ground);
		
		Bundle bundle = this.getIntent().getExtras();
		int size = bundle.getInt("size"); 
		
		//initialize the layout for the blocks
		GridLayout layout = (GridLayout)findViewById(R.id.gridlayout);
		layout.setRowCount(size);
		layout.setColumnCount(size);
		
		//get display resolution
		Display display = getWindowManager().getDefaultDisplay();
		Point resolution = new Point();
		display.getSize(resolution);
		
		int blockSize;
		//calculate the size of a View(block)
		blockSize = (resolution.x - 2 * this.convertDipToPix(BORDER)) / size;
		
		GameLogic logic = new GameLogic(this, size);
		//initialize the gameplay, generate the Views
		logic.generateGamePlay(layout, blockSize);
		
	}
	
	private int convertDipToPix(int dip) {
		return (int) (dip * getResources().getDisplayMetrics().density + 0.5f);
	}
}

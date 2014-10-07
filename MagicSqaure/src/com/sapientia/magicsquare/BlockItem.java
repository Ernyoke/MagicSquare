package com.sapientia.magicsquare;

import android.content.Context;
import android.widget.Button;

public class BlockItem {
	private Button button;
	private String text;
	private int value;
	private Context context;
	
	public BlockItem(Context context, int blockSize, GameLogic listener) {
		button = new Button(context);
		value = 0;
		text = "";
		button.setOnClickListener(listener);
		button.setWidth(blockSize);
		button.setHeight(blockSize);
	}
	
	public void setValue(int value) {
		if(value == 0) {
			this.value = value;
			text = "";
			button.setText(text);
		}
		else {
			if(value > 0) {
				this.value = value;
				text = "" + value;
				button.setText(text);
			}
		}
	}
	
	public void setDefaultValue() {
		this.value = 0;
		text = "";
		button.setText(text);
	}
	
	public int getValue() {
		return value;
	}
	
	public Button getView() {
		return button;
	}
	
}

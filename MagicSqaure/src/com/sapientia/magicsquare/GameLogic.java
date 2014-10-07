package com.sapientia.magicsquare;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.Toast;

import java.util.*;

public class GameLogic implements View.OnClickListener, DialogInterface.OnDismissListener{
	
	private Context context;
	private int size;
	private BlockItem pressed;
	private Numpad numpad;
	ArrayList<ArrayList<BlockItem>> gameplay;
	
	public GameLogic(Context context, int size) {
		this.context = context;
		this.size = size;
		this.numpad = new Numpad(context, size);
	}
	
	//generate gameplay and add Views to the layout 
	public void generateGamePlay(GridLayout layout, int blockSize) {
		gameplay = new ArrayList();
		for(int i = 0; i < size; ++i) {
			ArrayList<BlockItem> array = new ArrayList<BlockItem>();
			for(int j = 0; j < size; ++j) {
				BlockItem item = new BlockItem(context, blockSize, this);
				array.add(item);
				Button btn = item.getView();
				layout.addView(btn);
			}
			gameplay.add(array);
		}
	}

	@Override
	//when the dialog is closed we check if there is a winner
	public void onDismiss(DialogInterface dialog) {
		int result = numpad.getResult();
		//update the view with the inserted number if it was one
		pressed.setValue(result);
		//if the user inserted a number(the user can also dismiss the dialog with the BACK menu button)
		if(result > 0) {
			if(eval(result)) {
				//i have no idea what is going on here :'(
				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
				alertDialogBuilder.setTitle(context.getString(R.string.winner));
				alertDialogBuilder
				.setMessage(context.getString(R.string.retry))
				.setCancelable(false)
				.setPositiveButton(context.getString(R.string.yes),new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,int id) {
						restart();
					}
				  })
				.setNegativeButton(context.getString(R.string.no),new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,int id) {
						dialog.cancel();
						//go back to the main menus
						((PlayGround) context).finish();
					}
				});
 
				// create alert dialog
				AlertDialog alertDialog = alertDialogBuilder.create();
 
				// show it
				alertDialog.show();
				
				//play some crappy sound here
				MediaPlayer mediaPlayer = MediaPlayer.create(context, R.raw.win);
				mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
				if(!mediaPlayer.isPlaying()) {
					mediaPlayer.start();
				}
			}
			else {
				//if it was not found a winner, check if the inserted number was already used
				clearDouble(pressed);
			}
		}
	}

	@Override
	//show the Numbers input dialog when a View is clicked, bitches like numbers
	public void onClick(View v) {
		numpad.show();
		numpad.setOnDismissListener(this);
		pressed = findItem(v);
	}
	
	//find the BlockItem who has the pressed View
	private BlockItem findItem(View v) {
		Button btn = (Button)v;
		for(ArrayList it : gameplay) {
			for(BlockItem item : (ArrayList<BlockItem>)it) {
				if(item.getView() == btn) {
					return item;
				}
			}
		}
		return null;
	}
	
	//evaluate the game's outcome after every move
	private boolean eval(int result) {
		int sum = 0;
		int tmp_sum;
		//check vertical
		for(int i = 0; i < size; ++i) {
			tmp_sum = 0;
			for(int j = 0; j < size; ++j) {
				if(gameplay.get(i).get(j).getValue() == 0 || gameplay.get(i).get(j).getValue() == 0) {
					return false;
				}
				else {
					tmp_sum += gameplay.get(i).get(j).getValue();
				}
			}
			if(i == 0) {
				sum = tmp_sum;
			}
			else {
				if(sum != tmp_sum) {
					return false;
				}
			}
		}
		
		//check horizontal
		for(int i = 0; i < size; ++i) {
			tmp_sum = 0;
			for(int j = 0; j < size; ++j) {
				if(gameplay.get(j).get(i).getValue() == 0) {
					return false;
				}
				else {
					tmp_sum += gameplay.get(j).get(i).getValue();
				}
			}
			if(i == 0) {
				sum = tmp_sum;
			}
			else {
				if(sum != tmp_sum) {
					return false;
				}
			}
		}
		
		//check diagonal
		sum = 0;
		for(int i = 0; i < size; ++i) {
			sum += gameplay.get(i).get(i).getValue();
		}
		tmp_sum = 0;
		for(int i = 0; i < size; ++i) {
			tmp_sum += gameplay.get(size - i - 1).get(size - 1 - 1).getValue();
		}
		if(tmp_sum != sum) {
			return false;
		}
		
		return true;
	}
	
	//searching for doubles
	private void clearDouble(BlockItem block) {
		boolean stop = false;
		for(ArrayList it : gameplay) {
			for(BlockItem item : (ArrayList<BlockItem>)it) {
				if(item != block && item.getValue() == block.getValue()) {
					item.setDefaultValue();
					stop = true;
					break;
				}
			}
			if(stop) {
				break;
			}
		}
	}
	
	//restart the game, reset defaul values
	public void restart() {
		for(ArrayList it : gameplay) {
			for(BlockItem item : (ArrayList<BlockItem>)it) {
				item.setDefaultValue();
			}
		}
	}
	

}

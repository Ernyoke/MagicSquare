package com.sapientia.magiccube;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

public class ClickListener implements OnClickListener {
	
	private Context context;
	
	public ClickListener(Context context) {
		this.context = context;
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()) {
			case R.id.dim3: {
				Intent intent = new Intent(context, PlayGround.class);
				intent.putExtra("size", 3 );
				context.startActivity(intent);
				break;
			}
			case R.id.dim4: {
				Intent intent = new Intent(context, PlayGround.class);
				intent.putExtra("size", 4 );
				context.startActivity(intent);
				break;
			}
			case R.id.dim5: {
				Intent intent = new Intent(context, PlayGround.class);
				intent.putExtra("size", 5 );
				context.startActivity(intent);
				break;
			}
		}
		
	}


}

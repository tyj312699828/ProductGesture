package com.example.productgesture;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;
import cn.com.core.gesture.view.GestureSetView;
import cn.com.core.gesture.view.GestureSetView.GestureSetCallback;


/**
 * 
 * 手势密码设置界面
 *
 */
public class GestureSetActivity extends Activity {
	
	private GestureSetView gestureSetView;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gesture_set);
		setUpViews();
	}
	
	private void setUpViews() {
		gestureSetView = (GestureSetView) findViewById(R.id.gesture_set_view);
		gestureSetView.setGestureListener(new GestureSetCallback() {
			
			@Override
			public void onSetSuccess(String password) {
				// TODO Auto-generated method stub
				Toast.makeText(GestureSetActivity.this, "设置密码成功："+password, Toast.LENGTH_LONG).show();
			}

			@Override
			public void onBottomClick() {

			}
		});

	}
	

	
	
}

package com.example.productgesture;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import cn.com.core.gesture.view.GestureVerifyView;


/**
 * 
 * 手势绘制/校验界面
 *
 */
public class GestureVerifyActivity extends Activity{

	private GestureVerifyView view;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gesture_verify);
		setUpViews();
	}
	
	
	private void setUpViews() {
		view = (GestureVerifyView) findViewById(R.id.gestrue_verify_view);
		view.setUserAcNo("186****1111");
		
		view.setGestureListener(new GestureVerifyView.GestureVerifyCallback() {
			
			@Override
			public void onGestureResult(String password) {
				// TODO Auto-generated method stub
				System.out.println("main："+password);
				if ("1235789".equals(password)) {
					Toast.makeText(GestureVerifyActivity.this, "手势校验成功！", Toast.LENGTH_SHORT).show();
					view.verifySuccess();
				}else {
					view.verifyFailed();
				}
			}
			
			@Override
			public void onBottomClick() {
				// TODO Auto-generated method stub
				Toast.makeText(GestureVerifyActivity.this, "其他方式登录！", Toast.LENGTH_SHORT).show();
			}
		});
	}
	
	
	
}

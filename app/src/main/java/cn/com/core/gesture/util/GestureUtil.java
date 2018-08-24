/**
 * 
 */
package cn.com.core.gesture.util;

import java.io.IOException;
import java.util.List;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ComponentName;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.TranslateAnimation;

public class GestureUtil {
	public static final int Node_Normal_type = 0;
	public static final int Node_Pressed_type = 1;
	public static final int Node_Wrong_type = 2;
	private int Node_Normal_id = -1;
	private int Node_Pressed_id = -1;
	private int Node_Wrong_id = -1;
	private static final String Node_Normal = "gesture_node_normal.png";
	private static final String Node_Pressed = "gesture_node_pressed.png";
	private static final String Node_Wrong = "gesture_node_wrong.png";
	
	/**
	 * 手势录入过程中是否显示过程线
	 */
	public static boolean IsShowLine = true;
	
	private static GestureUtil gestureUtil = null;
	public static GestureUtil getInstance(){
		if (gestureUtil==null) {
			gestureUtil = new GestureUtil();
			return gestureUtil;
		}
		return gestureUtil;
	}
	
	/**
	 * 获取资源文件
	 * */
	public Bitmap getBitmap(Context context, int type) {
		Bitmap bitmap = null;
		switch (type) {
		case Node_Normal_type:
			if (Node_Normal_id!=-1) {
				Drawable drawable = context.getResources().getDrawable(Node_Normal_id);
				if (drawable!=null) {
					bitmap = Drawable2Bitmap(drawable);
				}
			}else {
				bitmap = getAssetsBitmap(context, Node_Normal);
			}
			break;
		case Node_Pressed_type:
			if (Node_Pressed_id!=-1) {
				Drawable drawable = context.getResources().getDrawable(Node_Pressed_id);
				if (drawable!=null) {
					bitmap = Drawable2Bitmap(drawable);
				}
			}else {
				bitmap = getAssetsBitmap(context, Node_Pressed);
			}
			break;
		case Node_Wrong_type:
			if (Node_Wrong_id!=-1) {
				Drawable drawable = context.getResources().getDrawable(Node_Wrong_id);
				if (drawable!=null) {
					bitmap = Drawable2Bitmap(drawable);
				}
			}else {
				bitmap = getAssetsBitmap(context, Node_Wrong);
			}
			break;
		default:
			break;
		}
		return bitmap;
	}

	public Bitmap getAssetsBitmap(Context context, String name) {
		try {
			return BitmapFactory.decodeStream(context.getAssets().open(
					name));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return null;
		}
	}
	/**
	 * 动画
	 * @return
	 */
	public TranslateAnimation getTranslateAnimation() {
		TranslateAnimation translateAnimation = new TranslateAnimation(0,
				10, 0, 0);
		translateAnimation.setDuration(120);
		translateAnimation.setInterpolator(new AccelerateInterpolator());
		translateAnimation.setRepeatMode(TranslateAnimation.RESTART);
		translateAnimation.setRepeatCount(2);
		return translateAnimation;
	}
	/**
	 * 获取屏幕分辨率
	 * 
	 * @param context
	 * @return
	 */
	public int[] getScreenDispaly(Context context) {
		WindowManager windowManager = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		int width = windowManager.getDefaultDisplay().getWidth();// 手机屏幕的宽度
		int height = windowManager.getDefaultDisplay().getHeight();// 手机屏幕的高度
		int result[] = { width-100, height };
		return result;
	}
	public int dip2px(Context activity, float dipValue) {
		float m = activity.getResources().getDisplayMetrics().density;
		return (int) (dipValue * m + 0.5f);
	}
	/**
	 * @brief Drawable转换成Bitmap
	 * @param d
	 *            图片对象（Drawable）
	 * @return Bitmap
	 * */
	private Bitmap Drawable2Bitmap(Drawable d) {
		Bitmap bitmap = Bitmap.createBitmap(d.getIntrinsicWidth(), d
				.getIntrinsicHeight(),
				d.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
						: Bitmap.Config.RGB_565);
		Canvas canvas = new Canvas(bitmap);
		d.setBounds(0, 0, d.getIntrinsicWidth(), d.getIntrinsicHeight());
		d.draw(canvas);
		return bitmap;
	}
	public void setNode_Normal_id(int node_Normal_id) {
		Node_Normal_id = node_Normal_id;
	}

	public void setNode_Pressed_id(int node_Pressed_id) {
		Node_Pressed_id = node_Pressed_id;
	}

	public void setNode_Wrong_id(int node_Wrong_id) {
		Node_Wrong_id = node_Wrong_id;
	}
	
}

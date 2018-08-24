package cn.com.core.gesture.view;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.text.Html;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.productgesture.R;

import cn.com.core.gesture.util.GestureDrawline.GestureCallBack;
import cn.com.core.gesture.util.GestureUtil;
import cn.com.core.gesture.util.GestureView;
import cn.com.core.gesture.util.LockIndicator;


/**  
 * 手势密码校验自定义view
* @author 田裕杰 
* @version 1.0.0 
*/
public class GestureVerifyView extends LinearLayout {

	private Context context;
	private TextView userAcNo;
	private TextView errorTv;
	private GestureView gestureView;
	private GestureVerifyCallback callback;
	private LockIndicator lockIndicator;
	private TextView topTv;
	private TextView bottomTv;
	private Handler handler =new Handler();
	public GestureVerifyView(Context context) {
		super(context);
		this.context = context;
		init();
	}
	public GestureVerifyView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		init();
	}
	public GestureVerifyView(Context context, GestureVerifyCallback callback){
		super(context);
		this.context = context;
		this.callback = callback;
		init();
	}
	private void init(){
		this.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
		View view = LayoutInflater.from(context).inflate(R.layout.gesture_verify,null);
		userAcNo = (TextView) view.findViewById(R.id.gesture_verify_username);
		errorTv = (TextView) view.findViewById(R.id.gesture_verify_error);

		lockIndicator = (LockIndicator) view.findViewById(R.id.gesture_verify_lockindicator);
		gestureView = (GestureView) view.findViewById(R.id.gesture_verify_view);
		gestureView.setGestureCallBack(new GestureCallBack() {

			@Override
			public void onGestureCodeInput(String inputCode) {
				// TODO Auto-generated method stub

				if (callback!=null) {
					callback.onGestureResult(inputCode);
				}
			}

			@Override
			public void onGestureRefresh(String code) {
				updateCodeList(code);
			}
		});


		bottomTv = (TextView) view.findViewById(R.id.gesture_verify_bottom);
		bottomTv.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (callback!=null) {
					callback.onBottomClick();
				}
			}
		});
		view.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
		this.addView(view);

	}
	/**
	 * 校验失败
	 */
	public void verifyFailed(){
		lockIndicator.setPath("");
		gestureView.clearDrawline(1000L);
		errorTv.setVisibility(View.VISIBLE);
		errorTv.setText(Html.fromHtml("<font color='#c70c1e'>密码错误</font>"));
		errorTv.startAnimation(GestureUtil.getInstance().getTranslateAnimation());
		handler.postDelayed(new Runnable() {
			@Override
			public void run() {
				errorTv.setVisibility(View.INVISIBLE);
			}
		},2000);
	}
	/**
	 * 校验成功
	 */
	public void verifySuccess(){
		gestureView.clearDrawline();
		errorTv.setVisibility(View.INVISIBLE);
	}
	/**
	 * 设置监听事件
	 * @param callback 回调
	 */
	public void setGestureListener(GestureVerifyCallback callback){
		this.callback = callback;
	}
	/**
	 * 设置用户账号
	 * @param str 账户
	 */
	public void setUserAcNo(String str){
		userAcNo.setText(str);
	}

	/**
	 * 设置连接线颜色
	 * @param color 颜色值
	 */
	public void setLineColor(int color){
		gestureView.setLineColor(color);
	}
	/**
	 * 设置连接线宽度
	 * @param width
	 */
	public void setLineWidth(int width){
		gestureView.setLineWidth(width);
	}
	/**
	 * 设置点的图片资源
	 * @param normalId 正常状态
	 * @param pressedId 选中状态
	 * @param wrongId 错误状态
	 */
	public void setGesturePointBitmap(int normalId,int pressedId,int wrongId){
		gestureView.setGesturePointBitmap(normalId, pressedId, wrongId);
	}
	/**  
	 * 校验手势密码回调函数
	* @author 田裕杰 
	* @version 1.0.0 
	*/
	public interface GestureVerifyCallback{
		public void onGestureResult(String password);
		public void onBottomClick();
	}
	private void updateCodeList(String inputCode) {
		// 更新选择的图案
		lockIndicator.setPath(inputCode);
	}
}

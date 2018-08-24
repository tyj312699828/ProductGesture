package cn.com.core.gesture.view;

import android.content.Context;
import android.graphics.Color;
import android.text.Html;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.productgesture.R;

import cn.com.core.gesture.util.GestureDrawline.GestureCallBack;
import cn.com.core.gesture.util.GestureUtil;
import cn.com.core.gesture.util.GestureView;
import cn.com.core.gesture.util.LockIndicator;


/**  
 * 手势密码设置自定义view
* @author 田裕杰 
* @version 1.0.0 
*/
public class GestureSetView extends RelativeLayout{

	private Context context;
	private LockIndicator lockIndicator;
	private GestureView gestureView;
	private TextView bottomTv;
	private TextView topTv,errorTv;
	private GestureSetCallback callback;
	private boolean mIsFirstInput = true;
	private String mFirstPassword = null;
	public GestureSetView(Context context) {
		super(context);
		this.context = context;
		init();
	}
	public GestureSetView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		init();
	}
	public GestureSetView(Context context,GestureSetCallback callback) {
		super(context);
		this.context = context;
		this.callback = callback;
		init();
	}
	
	private void init(){
		this.setBackgroundColor(Color.parseColor("#FFFFFFFF"));

		View view = LayoutInflater.from(context).inflate(R.layout.gesture_set,null);
		errorTv = (TextView) view.findViewById(R.id.gesture_set_error);

		lockIndicator = (LockIndicator) view.findViewById(R.id.gesture_set_lockindicator);
		gestureView = (GestureView) view.findViewById(R.id.gesture_set_view);
		gestureView.setGestureCallBack(new GestureCallBack() {

			@Override
			public void onGestureCodeInput(String inputCode) {
				// TODO Auto-generated method stub
				if (!isInputPassValidate(inputCode)) {
					errorTv.setVisibility(View.VISIBLE);
					errorTv.setText(Html.fromHtml("<font color='#c70c1e'>您需要至少连接4个点，请重试</font>"));
					// 左右移动动画
					errorTv.startAnimation(GestureUtil.getInstance().getTranslateAnimation());
					gestureView.clearDrawline(1500L);
					return;
				}
				if (mIsFirstInput) {
					mFirstPassword = inputCode;
					updateCodeList(inputCode);
					gestureView.clearDrawline(1000L);
					errorTv.setVisibility(View.VISIBLE);
					errorTv.setText("请再次输入");
				} else {
					if (inputCode.equals(mFirstPassword)) {
						//设置成功
						if (callback!=null) {
							callback.onSetSuccess(inputCode);
						}
//						gestureView.clearDrawline();
//						lockIndicator.setPath("");
					} else {
						errorTv.setVisibility(View.VISIBLE);
						errorTv.setText(Html.fromHtml("<font color='#c70c1e'>与上一次绘制不一致，请重试</font>"));
						// 左右移动动画
						errorTv.startAnimation(GestureUtil.getInstance().getTranslateAnimation());
						// 保持绘制的线，1.5秒后清除
						gestureView.clearDrawline(1500L);
					}
				}
				mIsFirstInput = false;
			}

			@Override
			public void onGestureRefresh(String code) {
				updateCodeList(code);
			}
		});


		bottomTv = (TextView) view.findViewById(R.id.gesture_set_bottom);
		bottomTv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (callback!=null) {
					callback.onBottomClick();
				}
			}
		});
		view.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
		this.addView(view);
	}
	/**
	 * 设置结果回调监听
	 * @param callback
	 */
	public void setGestureListener(GestureSetCallback callback){
		this.callback = callback;
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
	private void updateCodeList(String inputCode) {
		// 更新选择的图案
		lockIndicator.setPath(inputCode);
	}
	private boolean isInputPassValidate(String inputPassword) {
		if (TextUtils.isEmpty(inputPassword) || inputPassword.length() < 4) {
			return false;
		}
		return true;
	}
	/**  
	 * 回调接口
	* @author 田裕杰 
	* @version 1.0.0 
	*/
	public interface GestureSetCallback{
		public void onSetSuccess(String password);
		public void onBottomClick();
	}
}

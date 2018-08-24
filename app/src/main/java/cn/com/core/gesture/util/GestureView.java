package cn.com.core.gesture.util;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import cn.com.core.gesture.util.GestureContentView;
import cn.com.core.gesture.util.GestureDrawline.GestureCallBack;
import cn.com.core.gesture.util.GestureUtil;


/**  
 * 手势密码自定义view
* @author 田裕杰 
* @version 1.0.0 
*/
public class GestureView extends FrameLayout {

	private GestureContentView mGestureContentView;
	private GestureCallBack callBack;
	private Context context;
	private int bgColor = Color.parseColor("#48423D");
	public GestureView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		this.context = context;
		init();
	}

	public GestureView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		this.context = context;
		init();
	}
	public GestureView(Context context,GestureCallBack callBack){
		super(context);
		this.context = context;
		this.callBack = callBack;
		init();
	}
	public void setGestureCallBack(GestureCallBack callBack){
		this.callBack = callBack;
	}
	public void init() {
		// 初始化一个显示各个点的viewGroup
		mGestureContentView = new GestureContentView(context,new GestureCallBack() {
			
			@Override
			public void onGestureCodeInput(String inputCode) {
				// TODO Auto-generated method stub
				if (callBack!=null) {
					callBack.onGestureCodeInput(inputCode);
				}
			}

			@Override
			public void onGestureRefresh(String code) {
				if (callBack!=null) {
					callBack.onGestureRefresh(code);
				}
			}
		});
		// 设置手势解锁显示到哪个布局里面
		mGestureContentView.setParentView(this);
	}
	/**
	 * 清除画线
	 */
	public void clearDrawline(){
		mGestureContentView.clearDrawlineState(0L);
	}
	/**
	 * 清除画线
	 */
	public void clearDrawline(long time){
		mGestureContentView.clearDrawlineState(time);
	}
	/**
	 * 设置连接线颜色
	 * @param color
	 */
	public void setLineColor(int color){
		mGestureContentView.setLineColor(color);
	}
	/**
	 * 设置连接线宽度
	 * @param width
	 */
	public void setLineWidth(int width){
		mGestureContentView.setLineWidth(width);
	}
	/**
	 * 设置点的图片资源
	 * @param normalId 正常状态
	 * @param pressedId 选中状态
	 * @param wrongId 错误状态
	 */
	public void setGesturePointBitmap(int normalId,int pressedId,int wrongId){
		GestureUtil.getInstance().setNode_Normal_id(normalId);
		GestureUtil.getInstance().setNode_Pressed_id(pressedId);
		GestureUtil.getInstance().setNode_Wrong_id(wrongId);
	}
}

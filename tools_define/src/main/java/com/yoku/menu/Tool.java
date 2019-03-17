package com.yoku.menu;

import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.RotateAnimation;

public class Tool {
	/**标示动画是否执行，true:动画执行，另一个动画不能执行，false:动画执行结束另一个动画可以执行**/
	private static boolean isAnimantion;
	
	/**
	 * 隐藏菜单操作
	 * View : 面向父类进行开发，这样的好处，只要你传递过来的控件是view的子类，就不需要更改参数类型，就可以直接实现操作
	 * 2016-10-28 上午9:50:03
	 */
	public static void hide(View menu,long startOffset){
		//旋转动画，0   -180
		//因为隐藏上部菜单没有延迟，但是隐藏中部菜单是有延迟，所以把延迟时间作为隐藏方法的参数，由activity传递参数决定是否延迟
		setAnimation(menu,0,-180,startOffset);
		//因为使用的是补间动画实现旋转操作，所以菜单的点击事件和焦点还在原来的位置，所以当隐藏菜单的时候，需要将菜单设置为不可用
		setClickAble(menu,false);
	}
	/**
	 * 显示菜单的操作
	 *
	 * 2016-10-28 上午9:50:18
	 */
	public static void show(View menu){
		//旋转动画  -180    0
		setAnimation(menu,-180,0,0l);
		//显示，设置按钮可以使用，可以点击
		setClickAble(menu, true);
	}
	
	/**
	 * 隐藏显示菜单动画效果
	 *	startOffset : 动画延迟开始执行的时间，因为显示没有延迟，隐藏有延迟，需要抽取，让不同的操作进行不同的设置
	 * 2016-10-28 上午9:55:48
	 */
	private static void setAnimation(View menu,float fromDegrees,float toDegrees,long startOffset){
		//fromDegrees : 开始的旋转的角度
		//toDegrees : 结束的角度
		//pivotXType : 动画执行的类型（参照物）
		//pivotXValue : 旋转的中心点x的坐标
		RotateAnimation animation = new RotateAnimation(fromDegrees, toDegrees, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 1.0f);
		animation.setDuration(500);
		
		//保持动画结束的状态
		animation.setFillAfter(true);//保持动画结束的操作
		
		animation.setStartOffset(startOffset);//设置动画的开始执行的时间（延迟执行的时间）
		
		animation.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation animation) {
				isAnimantion = true;
			}
			
			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				isAnimantion = false;
			}
		});
		
		menu.startAnimation(animation);
	}
	
	
	/**
	 * 设置菜单是否可以点击的操作
	 *@param menu
	 *@param b ： 是否可用
	 * 2016-10-28 上午10:27:16
	 */
	private static void setClickAble(View menu, boolean b) {
		menu.setEnabled(b);
		//因为隐藏显示的时候，去除菜单隐藏显示之外，菜单中的按钮也隐藏显示了，所有除了菜单设置为不可用（可用）之外，还是设置菜单中的按钮也不可用（可用）
		//获取菜单中的按钮
		//instanceof : 判断控件是否是ViewGroup的类型
		if (menu instanceof ViewGroup) {
			ViewGroup viewGroup = (ViewGroup) menu;
			//viewGroup.getChildAt(index);//根据子控件的索引，获取子控件,index:控件的索引，从0开始的（在布局文件，控件从上往下，依次从0开始排列）
			//viewGroup.getChildCount();//获取菜单中的子控件的个数
			for (int i = 0; i < viewGroup.getChildCount(); i++) {
				View view = viewGroup.getChildAt(i);
				view.setEnabled(b);
			}
		}
	}
	
	/**
	 * 提供给activity使用的，让activity获取动画执行的标示，根据标示好去判断是否调用一个操作动画
	 *@return
	 * 2016-10-28 上午10:42:32
	 */
	public static boolean isAnimationStart(){
		return isAnimantion;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

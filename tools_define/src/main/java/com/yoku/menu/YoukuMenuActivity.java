package com.yoku.menu;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.tools_define.R;

public class YoukuMenuActivity extends Activity implements View.OnClickListener{
    private Button mMenu;
    private Button mHome;

    /**标示上部菜单隐藏显示，true:显示，false:隐藏**/
    private boolean isShowMenuTop = true;
    /**标示中部菜单隐藏显示，true:显示，false:隐藏**/
    private boolean isShowMenuMiddle = true;
    private RelativeLayout mRelMiddle;
    private RelativeLayout mRelTop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.youku_menu_layout);

        initView();
    }
    /**
     * 初始化控件
     *
     * 2016-10-28 上午9:39:12
     */
    private void initView() {
        mMenu = (Button) findViewById(R.id.menu);
        mHome = (Button) findViewById(R.id.home);
        mRelTop = (RelativeLayout) findViewById(R.id.rel_top);
        mRelMiddle = (RelativeLayout) findViewById(R.id.rel_middle);

        //设置按钮点击事件
        mMenu.setOnClickListener(this);
        mHome.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.menu:
                if (Tool.isAnimationStart()) {
                    return;
                }
                //如果上部的菜单显示，点击隐藏
                //如果上部菜单隐藏，点击显示
                if (isShowMenuTop) {
                    //isShowMenuTop = !isShowMenuTop;
                    Tool.hide(mRelTop,0l);
                }else{
                    //isShowMenuTop = !isShowMenuTop;
                    Tool.show(mRelTop);
                }

                isShowMenuTop = !isShowMenuTop;
                break;
            case R.id.home:
                if (Tool.isAnimationStart()) {
                    return;
                }
                //如果上部菜单显示，点击隐藏上部菜单和中部菜单
                //如果上部菜单隐藏，中部菜单显示，点击隐藏中部菜单
                //如果全部隐藏，点击显示中部菜单
                if (isShowMenuTop) {
                    //如果上部菜单显示，点击隐藏上部菜单和中部菜单
                    isShowMenuTop = false;
                    //isShowMenuMiddle = false;

                    Tool.hide(mRelTop,0l);
                    Tool.hide(mRelMiddle,300l);

                }else if(isShowMenuMiddle){
                    //如果上部菜单隐藏，中部菜单显示，点击隐藏中部菜单
                    //isShowMenuMiddle = false;
                    Tool.hide(mRelMiddle,0l);
                }else{
                    //如果全部隐藏，点击显示中部菜单
                    //isShowMenuMiddle = true;
                    Tool.show(mRelMiddle);
                }
                isShowMenuMiddle = !isShowMenuMiddle;
                break;
        }
    }

}

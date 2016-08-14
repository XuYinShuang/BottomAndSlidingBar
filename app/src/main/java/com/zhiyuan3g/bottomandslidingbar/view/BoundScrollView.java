package com.zhiyuan3g.bottomandslidingbar.view;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.ScrollView;

/**
 * Created by Administrator on 2016/7/14.
 */
public class BoundScrollView extends ScrollView {
    private View view;//子视图
    private float y = 0;//点解的Y坐标
    private Rect rect = new Rect();//矩形

    private boolean idCount = false;


    public BoundScrollView(Context context) {
        super(context);
    }

    public BoundScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (this.getChildCount() > 0) {
            view = getChildAt(0);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (view != null) {
            connectionOnTouch(ev);
        }
        return super.onTouchEvent(ev);
    }

    private void connectionOnTouch(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                actionMove(event);
                break;
            case MotionEvent.ACTION_UP:
                if (isNeedAnimation()) {
                    translateAnimation();
                    idCount = false;
                }
                break;
        }
    }

    //手动平移
    private void translateAnimation() {
        TranslateAnimation animation = new TranslateAnimation(0, 0, view.getTop(), rect.top);
        animation.setDuration(200);
        view.startAnimation(animation);
        view.layout(rect.left, rect.top, rect.right, rect.bottom);//设置动画为原来位置
        rect.setEmpty();//设置动画无效

    }

    //是否要开启动画
    private boolean isNeedAnimation() {
        return !rect.isEmpty();
    }

    //手势移动方法

    private void actionMove(MotionEvent event) {
        //表示按下时Y坐标
        float Y = y;
        //离开时Y坐标
        float newY = event.getY();
        //手势移动后的距离
        int DY = (int) (Y - newY);

        if (!idCount) {
            //使移动后的距离为0
            DY = 0;
        }
        y = newY;

        //当滚动到最上或最下时不会再滚动
        if (isNeedMove()) {
            //初始化头部矩形
            if (rect.isEmpty()) {
                rect.set(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
            }

            //移动后的布局
            view.layout(view.getLeft(), view.getTop() - DY, view.getRight(), view.getBottom() - DY);
        }
        idCount = true;
    }

    private boolean isNeedMove() {
        int bottom = view.getMeasuredHeight() - this.getHeight();
        int scrollY = this.getScrollY();

        if (scrollY == 0 || scrollY == bottom) {
            return true;
        }
        return false;
    }
}

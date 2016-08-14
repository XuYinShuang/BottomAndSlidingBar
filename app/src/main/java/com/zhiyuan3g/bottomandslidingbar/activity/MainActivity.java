package com.zhiyuan3g.bottomandslidingbar.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.zhiyuan3g.bottomandslidingbar.R;
import com.zhiyuan3g.bottomandslidingbar.fragment.ContentFragment;
import com.zhiyuan3g.bottomandslidingbar.fragment.FirstFragment;
import com.zhiyuan3g.bottomandslidingbar.fragment.SecondFragment;
import com.zhiyuan3g.bottomandslidingbar.fragment.SlidingFragment;
import com.zhiyuan3g.bottomandslidingbar.fragment.ThreeFragment;

public class MainActivity extends AppCompatActivity {

    private FirstFragment firstFragment;
    private SecondFragment secondFragment;
    private ThreeFragment threeFragment;

    private LinearLayout firstLayout, secondLayout, threeLayout;

    private SlidingPaneLayout slidingPaneLayout;
    private SlidingFragment slidingFragment;
    private ContentFragment contentFragment;
    private int maxMargin = 0;


    private DisplayMetrics displayMetrics = new DisplayMetrics();


    private FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);


        //实例化manager
        manager = getFragmentManager();
        initView();
//        setChooseItem(0);


        maxMargin = displayMetrics.heightPixels / 10;
        slidingPaneLayout = (SlidingPaneLayout) findViewById(R.id.sliding_pane_layout);
        slidingFragment = new SlidingFragment();
        contentFragment = new ContentFragment();


        slidingPaneLayout.setPanelSlideListener(slideListener);
        fragmentTranceaction();

    }



    //初始化底部控件
    private void initView() {
        firstLayout = (LinearLayout) findViewById(R.id.firstLayout);
        secondLayout = (LinearLayout) findViewById(R.id.secondLayout);
        threeLayout = (LinearLayout) findViewById(R.id.threeLayout);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.firstLayout:
                setChooseItem(0);
                break;
            case R.id.secondLayout:
                setChooseItem(1);
                break;
            case R.id.threeLayout:
                setChooseItem(2);
                break;
            default:
                break;

        }

    }

    //设置选中的Item
    private void setChooseItem(int index) {
        //开始交易
        FragmentTransaction transaction = manager.beginTransaction();

//        //清空其他颜色
//        clearChoose();
        //隐藏Fragment
        hideFragment(transaction);

        //判断选中了哪一个Item
        switch (index) {
            case 0:

                if (firstFragment == null) {
                    firstFragment = new FirstFragment();
                    transaction.add(R.id.contentFragment, firstFragment);
                } else {
                    transaction.show(firstFragment);
                }
                break;
            case 1:

                if (secondFragment == null) {
                    secondFragment = new SecondFragment();
                    transaction.add(R.id.contentFragment, secondFragment);
                } else {
                    transaction.show(secondFragment);
                }
                break;
            case 2:

                if (threeFragment == null) {
                    threeFragment = new ThreeFragment();
                    transaction.add(R.id.contentFragment, threeFragment);
                } else {
                    transaction.show(threeFragment);
                }
                break;
        }

        transaction.commit();
    }

//    //当选中某一个Item时，其他的被重置
//    private void clearChoose() {
//        firstLayout.setBackgroundColor(Color.WHITE);
//        secondLayout.setBackgroundColor(Color.WHITE);
//        threeLayout.setBackgroundColor(Color.WHITE);
//    }

    //隐藏Fragment
    private void hideFragment(FragmentTransaction transactions) {
        if (firstFragment != null) {
            transactions.hide(firstFragment);

        }
        if (secondFragment != null) {
            transactions.hide(secondFragment);
        }
        if (threeFragment != null) {
            transactions.hide(threeFragment);
        }
    }

    private void fragmentTranceaction() {
        //碎片事务交换
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        //添加视图
        transaction.add(R.id.sliding_pane_menu, slidingFragment);
        //添加内容对象
        transaction.add(R.id.sliding_pane_content, contentFragment);


        transaction.commit();
    }

    private SlidingPaneLayout.PanelSlideListener slideListener = new SlidingPaneLayout.PanelSlideListener() {
        @Override
        public void onPanelSlide(View panel, float slideOffset) {

            int contentMargin = (int) (slideOffset * maxMargin);
            FrameLayout.LayoutParams contentParmas = contentFragment.getCurrentViewParmas();
            contentParmas.setMargins(0, contentMargin, 0, contentMargin);


            //设置内容碎片的当前视图参数
            contentFragment.setCurrentViewParmas(contentParmas);

            float scale = 1 - ((1 - slideOffset) * maxMargin * 2) / (float) displayMetrics.heightPixels;

            slidingFragment.getCurrentView().setPivotX(0);
            slidingFragment.getCurrentView().setPivotY(displayMetrics.heightPixels / 2);
            slidingFragment.getCurrentView().setScaleY(scale);
            slidingFragment.getCurrentView().setScaleX(scale);

            slidingFragment.getCurrentView().setAlpha(slideOffset);
        }

        @Override
        public void onPanelOpened(View panel) {
            Toast.makeText(MainActivity.this, "我成功了", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onPanelClosed(View panel) {
            Toast.makeText(MainActivity.this, "表扬我把", Toast.LENGTH_SHORT).show();
        }
    };
}

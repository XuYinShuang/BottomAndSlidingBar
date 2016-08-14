package com.zhiyuan3g.bottomandslidingbar.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.zhiyuan3g.bottomandslidingbar.R;

/**
 * Created by Administrator on 2016/7/14.
 */
public class ContentFragment extends Fragment {

    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.content_fragment, container, false);
        return view;
    }
    public void setCurrentViewParmas(FrameLayout.LayoutParams layoutParams){
        view.setLayoutParams(layoutParams);
    }
    public FrameLayout.LayoutParams getCurrentViewParmas(){
        return (FrameLayout.LayoutParams) view.getLayoutParams();
    }
}

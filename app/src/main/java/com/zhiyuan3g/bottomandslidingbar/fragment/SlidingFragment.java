package com.zhiyuan3g.bottomandslidingbar.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhiyuan3g.bottomandslidingbar.R;

/**
 * Created by Administrator on 2016/7/14.
 */
public class SlidingFragment extends Fragment {
    View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.sliding_menu_fragment,container,false);
        return view;
    }

    public View getCurrentView(){
        return  view;
    }
}

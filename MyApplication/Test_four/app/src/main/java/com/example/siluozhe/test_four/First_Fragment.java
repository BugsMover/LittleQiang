package com.example.siluozhe.test_four;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by SILUOZHE on 2017-12-12.
 */

public class First_Fragment extends android.support.v4.app.Fragment{
    private Button btn;
    private ImageView img;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.first_fragment,null);
        img=view.findViewById(R.id.img);
        return view;
    }
    public void setAnimation(){
        Animation animation= AnimationUtils.loadAnimation(getActivity(),R.anim.xuanzhuan);
        img.startAnimation(animation);
    }
}

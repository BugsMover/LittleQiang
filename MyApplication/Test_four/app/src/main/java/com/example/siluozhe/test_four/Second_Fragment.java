package com.example.siluozhe.test_four;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class Second_Fragment extends android.support.v4.app.Fragment{
    private ImageView img;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.second_fragment,null);
        img=view.findViewById(R.id.img);
        return view;
    }
    public void setAnimation(){
        Animation animation= AnimationUtils.loadAnimation(getActivity(),R.anim.xuanzhuan);
        img.startAnimation(animation);
    }
}

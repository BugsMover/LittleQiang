package com.example.siluozhe.test_four;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private android.support.v4.view.ViewPager viewPager;
    private List<Fragment> list;
    private First_Fragment frist;
    private Second_Fragment second;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        viewPager= (android.support.v4.view.ViewPager) findViewById(R.id.viewpager);
        viewPager.addOnPageChangeListener(new android.support.v4.view.ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position==0){
                    frist.setAnimation();
                }else {
                    second.setAnimation();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewPager.setAdapter(new ViewPager(getSupportFragmentManager()));
    }

    public void init(){
        frist= new First_Fragment();
        second= new Second_Fragment();
        list=new ArrayList<>();
        list.add(frist);
        list.add(second);

    }
    public class ViewPager extends FragmentPagerAdapter{

        public ViewPager(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }
    }
}

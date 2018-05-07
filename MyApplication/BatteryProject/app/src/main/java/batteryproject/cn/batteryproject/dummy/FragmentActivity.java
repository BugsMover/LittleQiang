package batteryproject.cn.batteryproject.dummy;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import batteryproject.cn.batteryproject.R;
import batteryproject.cn.batteryproject.dummy.fragment.Fragment1;
import batteryproject.cn.batteryproject.dummy.fragment.Fragment2;
import batteryproject.cn.batteryproject.dummy.fragment.Fragment3;

public class FragmentActivity extends android.support.v4.app.FragmentActivity {
       private ViewPager mViewPager;
       private FragmentPagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        mViewPager=(ViewPager)findViewById(R.id.viewpager);
        final List<Fragment> mFragmemt=new ArrayList<Fragment>();
        Fragment fragment1=new Fragment1();
        Fragment fragment2=new Fragment2();
        Fragment fragment3=new Fragment3();
        mFragmemt.add(fragment1);
        mFragmemt.add(fragment2);
        mFragmemt.add(fragment3);
        mAdapter=new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragmemt.get(position);
            }

            @Override
            public int getCount() {
                return mFragmemt.size();
            }
        };
        //为ViewPager添加动画效果，3.0以上可用
        //mViewPager.setPageTransformer(true,new );
        mViewPager.setAdapter(mAdapter);
    }
}

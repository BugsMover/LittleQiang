package showfragment.itcast.cn.showfragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment1 extends Fragment {


    public void  Fragment1() {
        // Required empty public constructor
//        Animation animation= AnimationUtils.loadAnimation(getActivity(),R.anim.scale_animation);
//        iv.startAnimation(animation);
    }

private ImageView iv;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        View view=inflater.inflate(R.layout.fragment1, container, false);
//        iv=view.findViewById(R.id.iv);
        return inflater.inflate(R.layout.fragment1, container, false);
    }

}

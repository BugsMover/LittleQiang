package batteryproject.cn.batteryproject.dummy.fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import batteryproject.cn.batteryproject.R;
import batteryproject.cn.batteryproject.dummy.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment3 extends Fragment {
   private Button button;
   private View view;
    public Fragment3() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =inflater.inflate(R.layout.fragment_fragment3, container, false);
        return view;
    }
  public void onActivityCreated(Bundle savedInstanceState) {
      super.onActivityCreated(savedInstanceState);
      button=(Button)view.findViewById(R.id.button);
      button.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              SharedPreferences sharedPreferences=getActivity().getSharedPreferences("is_first_in_data",Context.MODE_PRIVATE);
              SharedPreferences.Editor editor=sharedPreferences.edit();
              editor.putBoolean("isFirstIn",false);
              editor.commit();

              Intent intent=new Intent(getActivity(),MainActivity.class);
              startActivity(intent);

              getActivity().finish();
          }
      });
  }

}

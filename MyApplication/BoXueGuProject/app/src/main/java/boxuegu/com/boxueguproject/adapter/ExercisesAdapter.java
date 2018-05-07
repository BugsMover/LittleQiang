package boxuegu.com.boxueguproject.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import boxuegu.com.boxueguproject.R;
import boxuegu.com.boxueguproject.activity.ExercisesDetailActivity;
import boxuegu.com.boxueguproject.bean.ExercisesBean;

/**
 * Created by asus on 2017-12-06.
 */

public class ExercisesAdapter extends BaseAdapter{
    private Context mContext;
    private List<ExercisesBean> ebl;
    public ExercisesAdapter(Context context){
        this.mContext=context;
    }

    /**
     *设置数据更新界面
     */
    public void setData(List<ExercisesBean> ebl){
        this.ebl=ebl;
        notifyDataSetChanged();
    }

    /**
     * 获取Item的总数
     */
    @Override
    public int getCount() {
        return ebl==null ? 0 :ebl.size();
    }

    /**
     * 根据position得到对应Item的对象
     */
    @Override
    public ExercisesBean getItem(int position) {
        return ebl==null ? null : ebl.get(position);
    }

    /**
     * 根据position得到对应Item的id
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * 得到相应poistion对应Item视图，poistion是当前Item的位置
     * convertView参数就是滑出屏幕的Item的View
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final  ViewHolder vh;
        //复用convertView
        if (convertView==null){
            vh=new ViewHolder();
            convertView= LayoutInflater.from(mContext).inflate(R.layout.exercises_list_item,null);
            vh.title=(TextView) convertView.findViewById(R.id.tv_title);
            vh.content=(TextView)convertView.findViewById(R.id.tv_content);
            vh.order=(TextView)convertView.findViewById(R.id.tv_order);
            convertView.setTag(vh);
        }else {
            vh=(ViewHolder) convertView.getTag();
        }
        //获取position对应的Item数据对象
        final ExercisesBean bean=getItem(position);
        if (bean!=null){
            vh.order.setText(position + 1 + "");
            vh.title.setText(bean.title);
            vh.content.setText(bean.content);
            vh.order.setBackgroundResource(bean.background);
        }
        //每一个Item的点击事件
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bean==null){
                    return;
                }
                //跳转到习题详细页面
                Intent intent=new Intent(mContext, ExercisesDetailActivity.class);
                //把章节Id传递到习题详细页面
                intent.putExtra("id",bean.id);
                //把标题传递到习题详细页面
                intent.putExtra("title",bean.title);
                mContext.startActivity(intent);
            }
        });
        return convertView;
    }

        class ViewHolder {
        public TextView title,content;
        public TextView order;
    }
}

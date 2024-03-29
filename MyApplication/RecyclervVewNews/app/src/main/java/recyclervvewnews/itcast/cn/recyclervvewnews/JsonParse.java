package recyclervvewnews.itcast.cn.recyclervvewnews;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;
/**
 * Created by asus on 2017-11-09.
 */
public class JsonParse {
    public static List<NewsInfo> getNewsInfo(String json){
        //使用Gson库解析JSON数据
        Gson gson = new Gson();
        //创建一个TypeToken的匿名子类对象，并调用对象的getType（）方法
        Type listType =new TypeToken<List<NewsInfo>>(){}.getType();
        //把 获取到的信息集合存到newsInfos中
        List<NewsInfo> newsInfos = gson.fromJson(json,listType);
        return newsInfos;
    }
}

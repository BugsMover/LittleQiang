package cn.itcast.saveqq;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by asus on 2017-09-30.
 */

public class FileSaveQQ {
    public static boolean saveUserInfo(Context context,String number,String passswor){
        try {
            FileOutputStream fos = context.openFileOutput("data.text",Context.MODE_ENABLE_WRITE_AHEAD_LOGGING);
            fos.write((number+":"+passswor).getBytes());
            fos.close();
            return true;
        } catch (java.io.IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static Map<String,String> getUserInfo(Context context){
        String content = ":";
        try {
            FileInputStream fis = context.openFileInput("data.text");
            byte[] buffer = new byte[fis.available()];
                    fis.read(buffer);
            content = new String(buffer);
            Map<String,String> userMap = new HashMap<String, String>();
            String[] infos = content.split(":");
            userMap.put("number",infos[0]);
            userMap.put("password",infos[1]);
            fis.close();
            return  userMap;
        } catch (java.io.IOException e) {

            e.printStackTrace();
            return  null;
        }


    }
}

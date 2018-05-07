package boxuegu.com.boxueguproject.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import boxuegu.com.boxueguproject.bean.UserBean;
import boxuegu.com.boxueguproject.bean.VideoBean;
import boxuegu.com.boxueguproject.sqlite.SQLiteHelper;

/**
 * Created by asus on 2017-12-05.
 */

public class DBUtils {
    private static DBUtils instance = null;
    private static SQLiteHelper helper;
    private static SQLiteDatabase db;
    public DBUtils(Context context){
        helper = new SQLiteHelper(context);
        db=helper.getWritableDatabase();
    }
    public static DBUtils getInstance(Context context){
        if (instance==null){
            instance=new DBUtils(context);
        }
        return instance;
    }
    /**
     * 保存个人资料信息
     */
    public void saveUserInfo(UserBean bean){
        ContentValues cv=new ContentValues();
        cv.put("userName",bean.userName);
        cv.put("nickName",bean.nickName);
        cv.put("sex",bean.sex);
        cv.put("signature",bean.signature);
        db.insert(SQLiteHelper.U_USERINFO,null,cv);
    }
    /**
     * 获取个人资料信息
     */
    public UserBean getUserInfo(String userName){
        String sql=" SELECT * FROM "+ SQLiteHelper.U_USERINFO+" WHERE userName=?";
        Cursor cursor =db.rawQuery(sql, new String[]{userName});
        UserBean bean = null;
        while (cursor.moveToNext()){
            bean=new UserBean();
            bean.userName=cursor.getString(cursor.getColumnIndex("userName"));
            bean.nickName=cursor.getString(cursor.getColumnIndex("nickName"));
            bean.sex=cursor.getString(cursor.getColumnIndex("sex"));
            bean.signature=cursor.getString(cursor.getColumnIndex("signature"));
        }
        cursor.close();
        return bean;
    }
    /**
     * 修改个人资料
     */
    public void updateUserInfo(String key,String value,String userName){
        ContentValues cv =new ContentValues();
        cv.put(key,value);
        db.update(SQLiteHelper.U_USERINFO,cv,"userName=?",new String[]{userName});
    }

    public void saveVideoPlayList(VideoBean bean, String userName) {
        //判断如果里面有此播放记录则需要删除重新存放
        if (hasVideoPlay(bean.chapterId,bean.videoId,userName)){
            //删除之前存入的播放记录
            boolean isDelete=delVideoPlay(bean.chapterId,bean.videoId,userName);
            if (!isDelete){
                //没有删除成功时，跳出此方法不再执行下面的语句
                return;
            }
        }
        ContentValues cv=new ContentValues();
        cv.put("userName",userName);
        cv.put("chapterId",bean.chapterId);
        cv.put("videoId",bean.videoId);
        cv.put("videoPath",bean.videoPath);
        cv.put("title",bean.title);
        cv.put("secondTitle",bean.secondTitle);
        db.insert(SQLiteHelper.U_VIDEO_PLAY_LIST,null,cv);
    }

    /**
     * 删除已经存在的视频记录
     * @param chapterId
     * @param videoId
     * @param userName
     * @return
     */
    private boolean delVideoPlay(int chapterId, int videoId, String userName) {
        boolean delSuccess=false;
        int row =db.delete(SQLiteHelper.U_VIDEO_PLAY_LIST,"chapterId=? AND videoId=? AND userName=?",new String[]{ videoId + "" ,userName});
        if (row>0){
            delSuccess=true;
        }
        return delSuccess;
    }

    /**
     * 判断视频记录是否存在
     */
    private boolean hasVideoPlay(int chapterId, int videoId, String userName) {
        boolean hasVideo=false;
        String sql="SELECT * FROM "+SQLiteHelper.U_VIDEO_PLAY_LIST + "WHERE chapterId=? AND videoId=? AND userName=?";
        Cursor cursor=db.rawQuery(sql,new String[]{chapterId+"",videoId+"",userName});
        if (cursor.moveToNext()){
            hasVideo=true;
        }
        cursor.close();
        return  hasVideo;
    }

    public List<VideoBean> getVideoHistory(String userName) {
        String sql="SELECT * FROM" + SQLiteHelper.U_VIDEO_PLAY_LIST + "WHERE userName=?";
        Cursor cursor=db.rawQuery(sql,new String[]{userName});
        List<VideoBean> vbl=new ArrayList<VideoBean>();
        VideoBean bean=null;
        while (cursor.moveToNext()){
            bean=new VideoBean();
            bean.chapterId=cursor.getInt(cursor.getColumnIndex("chapterId"));
            bean.videoId=cursor.getInt(cursor.getColumnIndex("videoId"));
            bean.videoPath=cursor.getString(cursor.getColumnIndex("videoPath"));
            bean.title=cursor.getString(cursor.getColumnIndex("title"));
            bean.secondTitle=cursor.getString(cursor.getColumnIndex("secondTitle"));
            vbl.add(bean);
            bean=null;
        }
        cursor.close();
        return vbl;
    }
}

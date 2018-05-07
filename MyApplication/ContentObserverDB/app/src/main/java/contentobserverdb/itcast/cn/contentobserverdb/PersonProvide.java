package contentobserverdb.itcast.cn.contentobserverdb;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by Administrator on 2017-11-06.
 */

public class PersonProvide extends ContentProvider{
    //定义一个uri路径的匹配器，如果路径匹配不成功返回-1
    private static UriMatcher mUriMatcher = new UriMatcher(-1);
    //匹配路径成功时的返码
    private static final int SUCCESS = 1;
//数据库操作类的对象
    private PersonDBOpenHelper helper;
    //添加路径匹配器的规则
    static {
        mUriMatcher.addURI("monitordata.itcast.cn.myapplication","info",SUCCESS);
    }
    //当内容提供者被创建的时候调用
    @Override
    public boolean onCreate() {
        helper = new PersonDBOpenHelper(getContext());
        return false;
    }
    //查询操作
    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {

        int code = mUriMatcher.match(uri);
        if (code == SUCCESS){
          SQLiteDatabase db = helper.getReadableDatabase();
          return db.query("info",projection,selection,selectionArgs,null,null,sortOrder);
      }else{
          throw new IllegalArgumentException("路径不正确，我是不会给你提供数据的!");
      }
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }
    //添加数据操作
    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        int code = mUriMatcher.match(uri);
        if (code == SUCCESS){
            SQLiteDatabase db = helper.getReadableDatabase();
            long rowId = db.insert("info",null,values);
            if (rowId > 0 ){
                Uri insertUri = ContentUris.withAppendedId(uri,rowId);
                getContext().getContentResolver().notifyChange(insertUri,null);
                return insertUri;
            }
            db.close();
            return uri;
        }else{
            throw new IllegalArgumentException("路径不正确，我是不会给你插入数据的!");
        }
    }
    //删除数据操作
    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        int code = mUriMatcher.match(uri);
        if (code == SUCCESS){
            SQLiteDatabase db = helper.getReadableDatabase();
        int count = db.delete("info",selection,selectionArgs);
            if ( count > 0 ){
                getContext().getContentResolver().notifyChange(uri,null);
            }
            db.close();
            return count;
        }else{
            throw new IllegalArgumentException("路径不正确，我是不会让你随便删除数据的!");
        }
    }
    //更新数据库操作
    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        int code = mUriMatcher.match(uri);
        if (code == SUCCESS){
            SQLiteDatabase db = helper.getReadableDatabase();
        int count = db.update("info",values,selection,selectionArgs);
            if (count > 0 ){
                getContext().getContentResolver().notifyChange(uri,null);
            }
            db.close();
            return count;
        }else {
            throw new IllegalArgumentException("路径不正确，我是不会让你更新数据的!");
        }
    }
}

package mina.king.com.minachat.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by hxb on 2017/11/1.
 * 个人用户缓存
 */
public class UserInfoCache {
    private static SharedPreferences preferences;
    public static final String FILE_NAME = "USER_EE";
    public static Context mContext;
    public static String USER_ID = "USER_ID";//用户id
    public static String USER_OTHER_ID = "USER_OTHER_ID";//点击用户的ID
    public static String HEAD_IMAGE = "HEAD_IMAGE";//用户头像
    public static String HEAD_OTHER_IMAGE = "HEAD_OTHER_IMAGE";//点击用户头像


    public static void init(Context context) {
        mContext = context;
        if (null == preferences) {//默认文件名
            preferences = mContext.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        }
    }

    //保存缓存值
    public static void saveUserInfo(String key, Object value) {
        SharedPreferences.Editor editor = preferences.edit();
        if (value instanceof String) {
            editor.putString(key, (String) value);
        }
        editor.apply();
    }

    //取出缓存值
    public static String getUserInfo(String key) {
        String object = null;
        try {
            object = preferences.getString(key, "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return object;
    }

    //清理某个缓存值
    public static void removeAKey(String key) {
        preferences.edit().remove(key).apply();
    }

    //清理整个缓存
    @SuppressLint("ApplySharedPref")
    public static void clearUserInfoCache() {
        preferences.edit().clear().commit();
    }

}

package mina.king.com.minademo.base;

import android.app.Application;
import android.content.Context;
import android.util.DisplayMetrics;

import mina.king.com.minachat.utils.UserInfoCache;


/**
 * Created by king
 * @date 2018.11.14
 */

public class MyApplication extends Application {
    private static MyApplication mInstance;
    public static Context mContext;
    /**
     * 屏幕宽度
     */
    public static int screenWidth;
    /**
     * 屏幕高度
     */
    public static int screenHeight;
    /**
     * 屏幕密度
     */
    public static float screenDensity;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        mInstance = this;
        initScreenSize();
        UserInfoCache.init(mContext);
    }

    public static Context getInstance() {
        return mInstance;
    }

    /**
     * 初始化当前设备屏幕宽高
     */
    private void initScreenSize() {
        DisplayMetrics curMetrics = getApplicationContext().getResources().getDisplayMetrics();
        screenWidth = curMetrics.widthPixels;
        screenHeight = curMetrics.heightPixels;
        screenDensity = curMetrics.density;
    }
}

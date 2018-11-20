package ui.king.com.kinglibrary.base;

import android.app.Activity;
import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.View;

import org.zackratos.ultimatebar.UltimateBar;

import ui.king.com.kinglibrary.R;

public class BaseViewModel extends AndroidViewModel {
    private Activity activity;

    /**
     * 网络是否可用
     */
    protected MutableLiveData<Boolean> isNetworkAvailable = new MutableLiveData<>();
    /**
     * 页面无数据显示
     */
    protected MutableLiveData<Boolean> isNoData = new MutableLiveData<>();

    public BaseViewModel(@NonNull Application application, Activity activity) {
        super(application);
        this.activity = activity;
    }

    public void setIsNetworkAvailable(Boolean isNetworkAvailable) {
        this.isNetworkAvailable.setValue(isNetworkAvailable);
    }

    public void onCreate() {
        isNoData.setValue(false);
        //沉浸式
        UltimateBar ultimateBar = new UltimateBar(activity);
        ultimateBar.setTransparentBar(ContextCompat.getColor(activity, R.color.color_EA7B58), 50);
//        ultimateBar.setImmersionBar();//沉浸式
//        activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//设置状态栏字体颜色为深色
        activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);//设置状态栏字体颜色为浅色
    }

    public void onDestroy() {
        onCleared();
    }

    public MutableLiveData<Boolean> getIsNoData() {
        return isNoData;
    }

    public void setIsNoData(Boolean isNoData) {
        this.isNoData.setValue(isNoData);
    }


}

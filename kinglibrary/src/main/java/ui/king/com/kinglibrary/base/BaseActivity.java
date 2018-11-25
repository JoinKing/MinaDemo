package ui.king.com.kinglibrary.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Toast;

import org.zackratos.ultimatebar.UltimateBar;

public abstract class BaseActivity<V extends ViewDataBinding, VM extends BaseViewModel> extends FragmentActivity {
    protected V mBinding;
    protected VM mViewModel;
    protected MyNetBroadCastReciver receiver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //初始化数据
        mBinding = DataBindingUtil.setContentView(this, initContentView());
        mBinding.setVariable(initVariableId(), mViewModel = initViewModel());
        //网络请求状态
        setNetworkBroadcast();
        mViewModel.onCreate();
        //沉浸式
        initTitle();
    }

    private void initTitle() {
//        if (mViewModel.getTitle() == mViewModel.BLACK) {
        UltimateBar ultimateBar = new UltimateBar(this);
        ultimateBar.setImmersionBar();//沉浸式
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);//设置状态栏字体颜色为浅色
//        } else {
//            UltimateBar ultimateBar = new UltimateBar(this);
//            ultimateBar.setImmersionBar();//沉浸式
//            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//设置状态栏字体颜色为深色
//        }

    }

    /**
     * 初始化根布局
     *
     * @return 布局layout的id
     */
    public abstract int initContentView();

    /**
     * 初始化ViewModel的id
     *
     * @return BR的id(根据对应xml文件 < data > < variable > name < / variable > < / data > 自动生成)
     */
    public abstract int initVariableId();

    /**
     * 初始化ViewModel
     *
     * @return 继承BaseViewModel的ViewModel
     */
    public abstract VM initViewModel();

    /**
     * 处理livedata的订阅方法
     */
    public abstract void initViewObservable();

    /**
     * 设置网络监听
     */
    private void setNetworkBroadcast() {
        receiver = new MyNetBroadCastReciver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
        filter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(receiver, filter);
    }

    /**
     * ==================================判断网络连接======================================
     */
    class MyNetBroadCastReciver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            //如果是在开启wifi连接和有网络状态下
            if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())) {
                ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo info = intent.getParcelableExtra(ConnectivityManager.EXTRA_NETWORK_INFO);
                if (NetworkInfo.State.CONNECTED == info.getState()) {
                    //连接状态 处理自己的业务逻辑
                    mViewModel.setIsNetworkAvailable(true);
                } else {
                    Toast.makeText(context, "网络链接失败", Toast.LENGTH_SHORT).show();
                    mViewModel.setIsNetworkAvailable(false);
                }
            }
        }
    }

    public void backFinash(View view) {
        finish();
    }

}

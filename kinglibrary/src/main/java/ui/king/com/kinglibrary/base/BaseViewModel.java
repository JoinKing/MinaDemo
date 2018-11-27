package ui.king.com.kinglibrary.base;

import android.annotation.SuppressLint;
import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.Toast;

import java.util.HashMap;

import okhttp3.Call;
import ui.king.com.kinglibrary.okhttp.OkHttpUtils;
import ui.king.com.kinglibrary.okhttp.callback.StringCallback;



public class BaseViewModel extends ViewModel {

    protected Context context;
    protected BaseActivity activity;
    protected String TAG = "[KING]";
    public int BLACK = 0X001;
    public int WRITE = 0X002;
    private int title = WRITE;
    protected int SUCCESS = 200; //登陆成功 或者 请求成功
    protected int LOGIN_FAIl = 201; //密码错误
    protected int LOGIN_NO_REGISTER = 202;//账号未注册
    protected int LOGIN_YES_REGISTER = 203;//账号已注册
    protected int SEND_MISTAKE = 204; //发送失败
    protected int SEND_CODETAKE = 205; //验证码已过期
    protected int PARMS_MIS = 300; //参数错误
    protected int EXCEPTION = -1; //异常
    protected HashMap<String,String> parms  =  new HashMap<>();

    /**
     * 网络是否可用
     */
    protected MutableLiveData<Boolean> isNetworkAvailable = new MutableLiveData<>();
    /**
     * 页面无数据显示
     */
    protected MutableLiveData<Boolean> isNoData = new MutableLiveData<>();


    @SuppressLint("StaticFieldLeak")
    private Application mApplication;

    public BaseViewModel() {

    }

    public BaseViewModel(@NonNull Application application) {
        mApplication = application;
    }

    public BaseViewModel(BaseActivity activity) {
        this.activity = activity;
        this.context = activity;
    }

    public BaseViewModel(BaseActivity activity, Application mApplication) {
        this.activity = activity;
        this.mApplication = mApplication;
    }

    /**
     * Return the application.
     */
    @SuppressWarnings("TypeParameterUnusedInFormals")
    @NonNull
    public <T extends Application> T getApplication() {
        //noinspection unchecked
        return (T) mApplication;
    }

    public void setIsNetworkAvailable(Boolean isNetworkAvailable) {
        this.isNetworkAvailable.setValue(isNetworkAvailable);
    }

    public void onCreate() {
        isNoData.setValue(false);
    }


    public void setTitle(int title) {
        this.title = title;
    }

    public int getTitle() {
        return title;
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

    public void toast(String obj) {
        Toast.makeText(context, obj, Toast.LENGTH_SHORT).show();
    }

    public void initNetWorkData(String url, HashMap<String,String>prams){
        OkHttpUtils.post()
                .url(url)
                .params(prams)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        if (callBack!=null){
                            callBack.onError(e.getMessage());
                        }
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        if (callBack!=null){
                            callBack.onResponse(response);
                        }


                    }
                });
    }

    private ResponseCallBack callBack;

    public void setCallBack(ResponseCallBack callBack) {
        this.callBack = callBack;
    }

    public interface ResponseCallBack{
       void onError(String msg);
       void onResponse(String response);
    }



}

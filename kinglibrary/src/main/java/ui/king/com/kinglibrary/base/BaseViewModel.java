package ui.king.com.kinglibrary.base;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.support.annotation.NonNull;


public class BaseViewModel extends AndroidViewModel {

    private Context context;
    protected String TAG = "[KING]";
    public int BLACK = 0X001;
    public int WRITE = 0X002;
    private int title = WRITE;

    /**
     * 网络是否可用
     */
    protected MutableLiveData<Boolean> isNetworkAvailable = new MutableLiveData<>();
    /**
     * 页面无数据显示
     */
    protected MutableLiveData<Boolean> isNoData = new MutableLiveData<>();


    public void setTitle(int title) {
        this.title = title;
    }

    public int getTitle() {
        return title;
    }

    public BaseViewModel(@NonNull Application application) {
        super(application);
    }

    public void setIsNetworkAvailable(Boolean isNetworkAvailable) {
        this.isNetworkAvailable.setValue(isNetworkAvailable);
    }

    public void onCreate() {
        isNoData.setValue(false);
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

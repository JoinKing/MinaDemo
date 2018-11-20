package mina.king.com.minademo.login.viewModel;

import android.app.Activity;
import android.app.Application;
import android.support.annotation.NonNull;

import ui.king.com.kinglibrary.base.BaseViewModel;

public class LoginViewModel extends BaseViewModel {
    private Activity activity;

    public LoginViewModel(@NonNull Application application,Activity activity) {
        super(application,activity);
    }



    public String xx(){
        return "xx";
    }
}

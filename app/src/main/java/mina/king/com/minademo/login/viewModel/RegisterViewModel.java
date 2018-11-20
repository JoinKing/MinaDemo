package mina.king.com.minademo.login.viewModel;

import android.app.Application;
import android.content.Context;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import mina.king.com.minademo.R;
import ui.king.com.kinglibrary.base.BaseViewModel;

public class RegisterViewModel extends BaseViewModel {
    private Context context;
    private String phoneNumber = "";
    private String psd = "";
    public ObservableInt isShow = new ObservableInt(View.INVISIBLE);//删除按钮的显示
    public ObservableField<String> phone = new ObservableField();

    public RegisterViewModel(@NonNull Application application) {
        super(application);
        this.context = application;
    }

    public TextWatcher getPhoneNumber(){
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().trim().isEmpty()){
                    isShow.set(View.VISIBLE);
                    phoneNumber = s.toString().trim();
                    phone.set(phoneNumber);
                }else {
                    isShow.set(View.INVISIBLE);
                    phoneNumber = "";
                    phone.set(phoneNumber);
                }
            }
        };
    }

    public TextWatcher getPsd(){
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().trim().isEmpty()){
                    psd = s.toString().trim();
                }else {
                    psd = "";
                }
            }
        };
    }

    /**
     * 登录
     * @param view
     */
    public void loginButton(View view){
        if (phoneNumber.isEmpty()||psd.isEmpty()){
            Toast.makeText(context, context.getText(R.string.countAndPsdIsemoty), Toast.LENGTH_SHORT).show();
            return;
        }else {
            //登录接口


        }
    }

    public void deletePhoneNumber(View view){
        phone.set("");
    }
}

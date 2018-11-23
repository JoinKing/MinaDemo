package mina.king.com.minademo.login.viewModel;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;

import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import mina.king.com.minademo.R;
import mina.king.com.minademo.chat.activity.ChatActivity;
import mina.king.com.minademo.login.model.LoginModel;
import mina.king.com.minademo.login.view.RegisterActivity;
import mina.king.com.minademo.util.Api;
import okhttp3.Call;
import ui.king.com.kinglibrary.base.BaseViewModel;
import ui.king.com.kinglibrary.okhttp.OkHttpUtils;
import ui.king.com.kinglibrary.okhttp.callback.GenericsCallback;
import ui.king.com.kinglibrary.okhttp.callback.StringCallback;
import ui.king.com.kinglibrary.okhttp.utils.JsonGenericsSerializator;


public class LoginViewModel extends BaseViewModel {

    protected Context context;
    private String phoneNumber = "";
    private String psd = "";
    public ObservableInt isShow = new ObservableInt(View.INVISIBLE);//删除按钮的显示
    public ObservableField<String> phone = new ObservableField();

    public LoginViewModel(@NonNull Application application) {
        super(application);
        this.context =application;
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
            //缓存用户名id
            OkHttpUtils.post()
                    .url(Api.login)
                    .addParams("phone",phoneNumber)
                    .addParams("psd",psd)
                    .build()
                    .execute(new GenericsCallback<LoginModel>(new JsonGenericsSerializator()) {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            Log.e(TAG, "onError: "+e.getMessage() );
                        }

                        @Override
                        public void onResponse(LoginModel response, int id) {
                            if (response.getCode()==SUCCESS){
                                toast(response.getMsg());
                                context.startActivity(new Intent(context, ChatActivity.class));
                                activity.finish();
                            }else {
                                toast(response.getMsg());
                            }
                        }
                    });


        }
    }

    /**
     * 忘记密码
     * @param view
     */
    public void forgetPsd(View view){
        Log.e(TAG, "loginButton: "+phoneNumber );
    }

    /**
     * 注册
     * @param view
     */
    public void register(View view){
        context.startActivity(new Intent(context,RegisterActivity.class));
    }

    public void deletePhoneNumber(View view){
        phone.set("");
    }

    @Override
    public void getAvtivity(Activity activity) {
        super.getAvtivity(activity);
    }
}

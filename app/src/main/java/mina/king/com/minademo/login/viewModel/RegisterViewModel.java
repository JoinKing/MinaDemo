package mina.king.com.minademo.login.viewModel;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import mina.king.com.minademo.R;
import mina.king.com.minademo.login.model.LoginModel;
import mina.king.com.minademo.util.Api;
import okhttp3.Call;
import ui.king.com.kinglibrary.base.BaseViewModel;
import ui.king.com.kinglibrary.okhttp.OkHttpUtils;
import ui.king.com.kinglibrary.okhttp.callback.GenericsCallback;
import ui.king.com.kinglibrary.okhttp.callback.StringCallback;
import ui.king.com.kinglibrary.okhttp.utils.JsonGenericsSerializator;
import ui.king.com.kinglibrary.utils.EmailUtils;
import ui.king.com.kinglibrary.utils.PhoneUtil;

public class RegisterViewModel extends BaseViewModel {
    private Context context;
    private String phoneNumber = "";
    private String psd = "";
    private String code = "";
    private String eamil = "";
    private Activity activity;
    public ObservableInt isShow = new ObservableInt(View.INVISIBLE);//删除按钮的显示
    public ObservableField<String> phone = new ObservableField();

    public RegisterViewModel(@NonNull Application application) {
        super(application);
        this.context = application;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
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

    public TextWatcher getEmail(){
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
                    eamil = s.toString().trim();
                }else {
                    eamil = "";
                }
            }
        };
    }
    public TextWatcher getCode(){
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
                    code = s.toString().trim();
                }else {
                    code = "";
                }
            }
        };
    }

    public void getRegisCode(View view){
        if (eamil.isEmpty()){
            Toast.makeText(context, context.getText(R.string.emailIsemoty), Toast.LENGTH_SHORT).show();
            return;
        }else {
            OkHttpUtils.post()
                    .url(Api.code)
                    .addParams("type","1")
                    .addParams("email",eamil)
                    .build()
                    .execute(new GenericsCallback<LoginModel>(new JsonGenericsSerializator()) {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            Log.e(TAG, "onResponse: "+e.getMessage() );
                        }
                        @Override
                        public void onResponse(LoginModel response, int id) {
                            if (response.getCode()==SUCCESS){
                                toast(response.getMsg());
                            }else {
                                toast(response.getMsg());
                            }
                        }
                    });
        }

    }

    /**
     * 登录
     * @param view
     */
    public void loginButton(View view){
        if (phoneNumber.isEmpty()||psd.isEmpty()||eamil.isEmpty()||code.isEmpty()){
            if (phoneNumber.isEmpty()||psd.isEmpty()){
                Toast.makeText(context, context.getText(R.string.countAndPsdIsemoty), Toast.LENGTH_SHORT).show();
                return;
            }
            if (eamil.isEmpty()||code.isEmpty()){
                Toast.makeText(context, context.getText(R.string.emailAndCodeIsemoty), Toast.LENGTH_SHORT).show();
                return;
            }
        }else {
            //登录接口
            if (!PhoneUtil.isMobile(phoneNumber)){
                Toast.makeText(context, "手机号不合法，请重新输入", Toast.LENGTH_SHORT).show();
                return;
            }else if (!EmailUtils.isEmail(eamil)){
                Toast.makeText(context, "邮件不合法，请重新输入", Toast.LENGTH_SHORT).show();
                return;
            }else {
                register(phoneNumber,psd,eamil,code);
            }


        }
    }

    private void register(String phoneNumber, String psd, String eamil, String code) {
        OkHttpUtils.post()
                .url(Api.register)
                .addParams("name",phoneNumber)
                .addParams("psd",psd)
                .addParams("email",eamil)
                .addParams("code",code)
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
                            activity.finish();
                        }else {
                            toast(response.getMsg());
                        }
                    }
                });


    }

    public void deletePhoneNumber(View view){
        phone.set("");
    }

}

package mina.king.com.minademo.ui.activity;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import org.zackratos.ultimatebar.UltimateBar;

import mina.king.com.minademo.R;

public class LoginActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        UltimateBar ultimateBar = new UltimateBar(this);
//        ultimateBar.setColorBar(ContextCompat.getColor(this, R.color.color_2ca9e8));//自定义颜色
        ultimateBar.setTransparentBar(ContextCompat.getColor(this, R.color.chat_accept_bg), 50);
//        ultimateBar.setImmersionBar();//沉浸式
//        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//设置状态栏字体颜色为深色
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);//设置状态栏字体颜色为浅色
    }
}

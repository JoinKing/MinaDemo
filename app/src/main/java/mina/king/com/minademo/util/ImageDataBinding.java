package mina.king.com.minademo.util;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import mina.king.com.minademo.base.MyApplication;

public class ImageDataBinding {

    @BindingAdapter("app:image")
    public static void setImage(ImageView imageView, String url){
        Glide.with(MyApplication.getInstance().getApplicationContext()).load(url).into(imageView);
    }

}

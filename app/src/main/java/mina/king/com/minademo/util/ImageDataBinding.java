package mina.king.com.minademo.util;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import mina.king.com.minademo.base.MyApplication;

public class ImageDataBinding {
    /**
     * 加载方形图片
     * @param imageView
     * @param url
     */
    @BindingAdapter("image")
    public static void setImage(ImageView imageView, String url){
        Glide.with(MyApplication.getInstance().getApplicationContext()).load(url).into(imageView);
    }

    /**
     * 加载圆形图片
     * @param imageView
     * @param url
     */
    @BindingAdapter("shap_image")
    public static void setShapImage(ImageView imageView,String url){
        RequestOptions mRequestOptions = RequestOptions.circleCropTransform()
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)//不做磁盘缓存
                .skipMemoryCache(true);//不做内存缓存

        Glide.with(MyApplication.getInstance().getApplicationContext()).load(url).apply(mRequestOptions).into(imageView);
    }

}

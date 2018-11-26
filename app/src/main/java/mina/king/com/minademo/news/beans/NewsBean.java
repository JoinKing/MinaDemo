package mina.king.com.minademo.news.beans;

import android.databinding.BaseObservable;
import android.databinding.Bindable;


public class NewsBean extends BaseObservable {

    private String image;
    private String name;
    private String qianm;
    @Bindable
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Bindable
    public String getQianm() {
        return qianm;
    }

    public void setQianm(String qianm) {
        this.qianm = qianm;
    }

    @Override
    public String toString() {
        return "NewsBean{" +
                "image='" + image + '\'' +
                ", name='" + name + '\'' +
                ", qianm='" + qianm + '\'' +
                '}';
    }
}

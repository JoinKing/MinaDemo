package mina.king.com.minademo.users.beans;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

public class UserChildBean extends BaseObservable {
    private String headImage;
    private String title;
    private String content;

    @Bindable
    public String getHeadImage() {
        return headImage;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }


    @Bindable
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Bindable
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "UserChildBean{" +
                "headImage='" + headImage + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }


}

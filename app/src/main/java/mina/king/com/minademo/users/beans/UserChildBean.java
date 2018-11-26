package mina.king.com.minademo.users.beans;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

public class UserChildBean extends BaseObservable {
    private String image;
    private String title;
    private String content;
    @Bindable
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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
                "image='" + image + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}

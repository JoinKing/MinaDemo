package mina.king.com.minademo.users.beans;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import java.util.List;

public class UserGroupBean extends BaseObservable {
    private String title;
    private List<UserChildBean>userChildBeanList;

    @Override
    public String toString() {
        return "UserGroupBean{" +
                "title='" + title + '\'' +
                ", userChildBeanList=" + userChildBeanList +
                '}';
    }

    public List<UserChildBean> getUserChildBeanList() {
        return userChildBeanList;
    }

    public void setUserChildBeanList(List<UserChildBean> userChildBeanList) {
        this.userChildBeanList = userChildBeanList;
    }

    @Bindable
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}

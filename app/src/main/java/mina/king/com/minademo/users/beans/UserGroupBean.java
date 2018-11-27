package mina.king.com.minademo.users.beans;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import java.util.List;

public class UserGroupBean extends BaseObservable {
    private String title;
    private List<UsersBean.ListBean>userChildBeanList;

    public List<UsersBean.ListBean> getUserChildBeanList() {
        return userChildBeanList;
    }

    public void setUserChildBeanList(List<UsersBean.ListBean> userChildBeanList) {
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

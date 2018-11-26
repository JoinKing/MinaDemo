package mina.king.com.minademo.users.viewModel;

import android.app.Application;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import mina.king.com.minademo.users.adapter.UsersAdapter;
import mina.king.com.minademo.users.beans.UserChildBean;
import mina.king.com.minademo.users.beans.UserGroupBean;
import ui.king.com.kinglibrary.base.BaseViewModel;

public class UsersViewModel extends BaseViewModel {
    private UsersAdapter adapter;

    public UsersViewModel() {
    }

    public void setAdapter(UsersAdapter adapter) {
        this.adapter = adapter;
    }

    public void initData(){
        List<UserGroupBean>userGroupBeans = new ArrayList<>();
        List<UserChildBean>userChildBeans = new ArrayList<>();
        for (int i = 0; i <5 ; i++) {
            UserChildBean bean = new UserChildBean();
            bean.setContent("setContent");
            bean.setHeadImage("https://ss0.baidu.com/73F1bjeh1BF3odCf/it/u=449308356,1283831331&fm=73&s=3C1105DB44AB331519384D3A03005043");
            bean.setTitle("zhangsan");
            userChildBeans.add(bean);
        }

        for (int i = 0; i <5 ; i++) {
            UserGroupBean bean = new UserGroupBean();
            bean.setTitle("group");
            bean.setUserChildBeanList(userChildBeans);
            userGroupBeans.add(bean);
        }
        if (userGroupBeans.size()>0){
            adapter.setBeanList(userGroupBeans);
        }


    }




}

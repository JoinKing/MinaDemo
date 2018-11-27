package mina.king.com.minademo.users.viewModel;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import mina.king.com.minachat.utils.UserInfoCache;
import mina.king.com.minademo.base.MyApplication;
import mina.king.com.minademo.chat.activity.ChatActivity;
import mina.king.com.minademo.users.adapter.UsersAdapter;
import mina.king.com.minademo.users.beans.UserGroupBean;
import mina.king.com.minademo.users.beans.UsersBean;
import mina.king.com.minademo.util.Api;
import okhttp3.Call;
import ui.king.com.kinglibrary.base.BaseActivity;
import ui.king.com.kinglibrary.base.BaseViewModel;
import ui.king.com.kinglibrary.okhttp.OkHttpUtils;
import ui.king.com.kinglibrary.okhttp.callback.GenericsCallback;
import ui.king.com.kinglibrary.okhttp.utils.JsonGenericsSerializator;

public class UsersViewModel extends BaseViewModel implements UsersAdapter.Onclick{
    private UsersAdapter adapter;


    public UsersViewModel(Context context) {
        super(context);
    }

    public void setAdapter(UsersAdapter adapter) {
        this.adapter = adapter;
    }
    List<UserGroupBean> userGroupBeans = new ArrayList<>();

    public void initData() {
        for (int i = 0; i < 1; i++) {
            UserGroupBean bean = new UserGroupBean();
            bean.setTitle("默认");
            userGroupBeans.add(bean);
        }
        if (userGroupBeans.size() > 0) {
            adapter.setOnclick(this);
            adapter.setBeanList(userGroupBeans);
        }
        OkHttpUtils.post()
                .url(Api.users)
                .build()
                .execute(new GenericsCallback<UsersBean>(new JsonGenericsSerializator()) {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e(TAG, "onResponse: " + e.getMessage());
                    }

                    @Override
                    public void onResponse(UsersBean response, int id) {
                        Log.e(TAG, "onResponse: " + response);
                        userGroupBeans.get(0).setUserChildBeanList(response.getList());
                    }
                });


    }


    public ExpandableListView.OnChildClickListener getOnChildClicklistener(){
        return new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Toast.makeText(activity, "休闲鞋"+childPosition, Toast.LENGTH_SHORT).show();
                return true;
            }
        };
    }


    @Override
    public void onClick(int group,int child) {
        UserInfoCache.saveUserInfo(UserInfoCache.USER_OTHER_ID,adapter.getBeanList().get(group).getUserChildBeanList().get(child).getUserName());
        UserInfoCache.saveUserInfo(UserInfoCache.HEAD_OTHER_IMAGE,adapter.getBeanList().get(group).getUserChildBeanList().get(child).getHeadImage());
        context.startActivity(new Intent(context,ChatActivity.class));
    }
}

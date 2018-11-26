package mina.king.com.minademo.news.viewModel;


import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import mina.king.com.minademo.news.adapter.NewsAdapter;
import mina.king.com.minademo.news.beans.NewsBean;
import ui.king.com.kinglibrary.base.BaseViewModel;

public class NewsViewModel extends BaseViewModel {

    private NewsAdapter adapter;
    private List<NewsBean> newsBeanList = new ArrayList<>();


    public void setAdapter(NewsAdapter adapter) {
        this.adapter = adapter;
    }

    public void initData(){
        for (int i = 0; i <100 ; i++) {
            NewsBean bean = new NewsBean();
            bean.setImage("https://csdnimg.cn/pubfooter/images/csdn-kf.png");
            bean.setName("name"+i);
            bean.setQianm("qianm"+i);
            newsBeanList.add(bean);
        }
        Log.e(TAG, "initData: "+newsBeanList.toString() );
        adapter.onRefreshData(newsBeanList);
        adapter.notifyDataSetChanged();

    }




}

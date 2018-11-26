package mina.king.com.minademo.news.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import mina.king.com.minademo.BR;
import mina.king.com.minademo.R;
import mina.king.com.minademo.databinding.ItemNewsBinding;
import mina.king.com.minademo.news.beans.NewsBean;
import ui.king.com.kinglibrary.base.BaseBindViewHolder;
import ui.king.com.kinglibrary.base.BaseBindingAdapter;

public class NewsAdapter extends BaseBindingAdapter<NewsBean,BaseBindViewHolder>{

    public NewsAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseBindViewHolder onCreateVH(ViewGroup parent, int viewType) {
        ViewDataBinding binding=DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.item_news,parent,false);
        return new BaseBindViewHolder(binding);
    }

    @Override
    public void onBindVH(BaseBindViewHolder holder, int position) {
        ViewDataBinding binding=holder.getBinding();
        binding.setVariable(BR.news,mDataList.get(position));
        binding.executePendingBindings();

    }
}

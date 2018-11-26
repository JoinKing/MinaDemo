package mina.king.com.minademo.news.view;


import android.support.v7.widget.LinearLayoutManager;

import mina.king.com.minademo.BR;
import mina.king.com.minademo.databinding.FragmentNewBinding;
import mina.king.com.minademo.R;
import mina.king.com.minademo.news.adapter.NewsAdapter;
import mina.king.com.minademo.news.viewModel.NewsViewModel;
import ui.king.com.kinglibrary.base.BaseFragment;

/**
 *
 */
public class NewFragment extends BaseFragment<FragmentNewBinding,NewsViewModel> {

    private NewsAdapter adapter;
    private NewsViewModel model;

    public NewFragment() {

    }

    @Override
    protected void commonLoad() {

    }

    @Override
    public int initContentView() {
        return R.layout.fragment_new;
    }

    @Override
    public int initVariableId() {
        return BR.messageVM;
    }

    @Override
    public NewsViewModel initViewModel() {
        model = new NewsViewModel();
        return model;
    }

    @Override
    public void initData() {
        adapter = new NewsAdapter(getContext());
        mBinding.rvNewList.setLayoutManager(new LinearLayoutManager(getContext()));
        mBinding.rvNewList.setAdapter(adapter);
        model.setAdapter(adapter);
        model.initData();
    }

    @Override
    public void initViewObservable() {

    }

}

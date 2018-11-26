package mina.king.com.minademo.main.view;

import mina.king.com.minademo.BR;
import mina.king.com.minademo.R;
import mina.king.com.minademo.databinding.ActivityMain1Binding;
import mina.king.com.minademo.main.viewModel.MainViewModl;
import ui.king.com.kinglibrary.base.BaseActivity;

public class MainActivity extends BaseActivity<ActivityMain1Binding, MainViewModl> {

    @Override
    public int initContentView() {
        return R.layout.activity_main1;
    }

    @Override
    public int initVariableId() {
        return BR.mainVM;
    }

    @Override
    public MainViewModl initViewModel() {
        MainViewModl model = new MainViewModl(this, mBinding);
        return model;

    }

    @Override
    public void initViewObservable() {

    }


}

package mina.king.com.minademo.login.view;

import mina.king.com.minademo.BR;
import mina.king.com.minademo.R;
import mina.king.com.minademo.databinding.ActivityRegisterBinding;
import mina.king.com.minademo.login.viewModel.RegisterViewModel;
import ui.king.com.kinglibrary.base.BaseActivity;

public class RegisterActivity extends BaseActivity<ActivityRegisterBinding, RegisterViewModel> {
    @Override
    public int initContentView() {
        return R.layout.activity_register;
    }

    @Override
    public int initVariableId() {
        return BR.registerVM;
    }

    @Override
    public RegisterViewModel initViewModel() {
        return new RegisterViewModel(getApplication());
    }

    @Override
    public void initViewObservable() {
        mViewModel.setTitle(mViewModel.BLACK);
    }
}

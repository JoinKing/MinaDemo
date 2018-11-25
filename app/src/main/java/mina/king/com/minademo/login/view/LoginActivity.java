package mina.king.com.minademo.login.view;

import mina.king.com.minademo.BR;
import mina.king.com.minademo.R;
import mina.king.com.minademo.databinding.ActivityLoginBinding;
import mina.king.com.minademo.login.viewModel.LoginViewModel;
import ui.king.com.kinglibrary.base.BaseActivity;


public class LoginActivity extends BaseActivity<ActivityLoginBinding,LoginViewModel> {

    @Override
    public int initContentView() {
        return R.layout.activity_login;
    }

    @Override
    public int initVariableId() {
        return BR.loginVm;
    }

    @Override
    public LoginViewModel initViewModel() {
        LoginViewModel model = new LoginViewModel(this);
        return model;
    }

    @Override
    public void initViewObservable() {
        mViewModel.setTitle(mViewModel.WRITE);
    }
}

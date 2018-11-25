package mina.king.com.minademo.main.viewModel;

import android.app.Activity;
import android.app.Application;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioGroup;
import android.widget.Toast;

import mina.king.com.minademo.R;
import mina.king.com.minademo.mine.view.MineFragment;
import mina.king.com.minademo.news.view.NewFragment;
import mina.king.com.minademo.users.view.UsersFragment;
import ui.king.com.kinglibrary.base.BaseActivity;
import ui.king.com.kinglibrary.base.BaseFragment;
import ui.king.com.kinglibrary.base.BaseViewModel;

public class MainViewModl extends BaseViewModel {

    private ViewDataBinding binding;
    private FragmentManager fragmentManager;

    public MainViewModl(BaseActivity activity, ViewDataBinding binding) {
        super(activity);
        this.binding = binding;
        changeFragment(new NewFragment());
    }


    public RadioGroup.OnCheckedChangeListener getRg() {
        return new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rbNews) {
                    changeFragment(new NewFragment());
                } else if (checkedId == R.id.rbUser) {
                    changeFragment(new UsersFragment());
                } else if (checkedId == R.id.rbMine) {
                    changeFragment(new MineFragment());
                }

            }
        };
    }

    private void changeFragment(BaseFragment fragment) {
        fragmentManager = activity.getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.flFragemnt, fragment);
        transaction.commit();
    }

}

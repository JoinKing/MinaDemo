package mina.king.com.minademo.main.viewModel;

import android.app.Activity;
import android.app.Application;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.widget.RadioGroup;
import android.widget.Toast;

import mina.king.com.minademo.R;
import ui.king.com.kinglibrary.base.BaseViewModel;

public class MainViewModl extends BaseViewModel {

    private ViewDataBinding binding;

    public MainViewModl(Activity activity, ViewDataBinding binding) {
        super(activity);
        this.binding = binding;
    }


    public RadioGroup.OnCheckedChangeListener getRg() {
        return new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (group.getId() == R.id.rbNews) {
                    toast("消息");

                } else if (group.getId() == R.id.rbUser) {
                    toast("消息1");

                } else if (group.getId() == R.id.rbMine) {
                    toast("消息2");

                }

            }
        };
    }

}

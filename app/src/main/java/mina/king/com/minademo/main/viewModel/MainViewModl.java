package mina.king.com.minademo.main.viewModel;

import android.databinding.ViewDataBinding;
import android.support.v4.app.FragmentManager;
import android.widget.RadioGroup;
import java.util.ArrayList;
import mina.king.com.minademo.R;
import mina.king.com.minademo.mine.view.MineFragment;
import mina.king.com.minademo.news.view.NewFragment;
import mina.king.com.minademo.users.view.UsersFragment;
import ui.king.com.kinglibrary.base.BaseActivity;
import ui.king.com.kinglibrary.base.BaseFragment;
import ui.king.com.kinglibrary.base.BaseViewModel;
import ui.king.com.kinglibrary.utils.FragmentUtils;

public class MainViewModl extends BaseViewModel {

    private ViewDataBinding binding;
    private FragmentManager fragmentManager;

    private ArrayList<BaseFragment> fragments = new ArrayList<>();
    private FragmentUtils fragmentPersistenceUtils;

    public MainViewModl(BaseActivity activity, ViewDataBinding binding) {
        super(activity);
        this.binding = binding;
        initFragment();
    }

    private void initFragment() {
        fragments.add(new NewFragment());
        fragments.add(new UsersFragment());
        fragments.add(new MineFragment());
        fragmentManager = activity.getSupportFragmentManager();
        fragmentPersistenceUtils = FragmentUtils.init(fragmentManager, R.id.flFragemnt, fragments);
        fragmentPersistenceUtils.switchFragment(0);
    }


    public RadioGroup.OnCheckedChangeListener getRg() {
        return new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rbNews) {
                    fragmentPersistenceUtils.switchFragment(0);
                } else if (checkedId == R.id.rbUser) {
                    fragmentPersistenceUtils.switchFragment(1);
                } else if (checkedId == R.id.rbMine) {
                    fragmentPersistenceUtils.switchFragment(2);
                }

            }
        };
    }


}

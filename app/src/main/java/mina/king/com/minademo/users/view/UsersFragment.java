package mina.king.com.minademo.users.view;


import mina.king.com.minademo.BR;
import mina.king.com.minademo.R;
import mina.king.com.minademo.databinding.FragmentNewBinding;
import mina.king.com.minademo.databinding.FragmentUsersBinding;
import mina.king.com.minademo.users.viewModel.UsersViewModel;
import ui.king.com.kinglibrary.base.BaseFragment;

/**
 *
 */
public class UsersFragment extends BaseFragment<FragmentUsersBinding,UsersViewModel> {


    public UsersFragment() {

    }

    @Override
    protected void commonLoad() {

    }

    @Override
    public int initContentView() {
        return R.layout.fragment_users;
    }

    @Override
    public int initVariableId() {
        return BR.messageVM;
    }

    @Override
    public UsersViewModel initViewModel() {
        return new UsersViewModel();
    }

    @Override
    public void initData() {

    }

    @Override
    public void initViewObservable() {

    }

}

package mina.king.com.minademo.users.view;


import mina.king.com.minademo.BR;
import mina.king.com.minademo.R;
import mina.king.com.minademo.databinding.FragmentNewBinding;
import mina.king.com.minademo.databinding.FragmentUsersBinding;
import mina.king.com.minademo.users.adapter.UsersAdapter;
import mina.king.com.minademo.users.viewModel.UsersViewModel;
import ui.king.com.kinglibrary.base.BaseFragment;

/**
 *
 */
public class UsersFragment extends BaseFragment<FragmentUsersBinding,UsersViewModel> {

    private UsersAdapter adapter;
    private UsersViewModel model;


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
        model = new UsersViewModel();
        return model;
    }

    @Override
    public void initData() {
        adapter = new UsersAdapter();
        model.initData();
        mBinding.elUsers.setAdapter(adapter);
    }

    @Override
    public void initViewObservable() {

    }

}

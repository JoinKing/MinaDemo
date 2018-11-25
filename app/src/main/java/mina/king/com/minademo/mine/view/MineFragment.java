package mina.king.com.minademo.mine.view;


import mina.king.com.minademo.BR;
import mina.king.com.minademo.R;
import mina.king.com.minademo.databinding.FragmentMineBinding;
import mina.king.com.minademo.databinding.FragmentUsersBinding;
import mina.king.com.minademo.mine.viewModel.MineViewModel;
import ui.king.com.kinglibrary.base.BaseFragment;

/**
 *
 */
public class MineFragment extends BaseFragment<FragmentMineBinding,MineViewModel> {


    public MineFragment() {

    }

    @Override
    protected void commonLoad() {

    }

    @Override
    public int initContentView() {
        return R.layout.fragment_mine;
    }

    @Override
    public int initVariableId() {
        return BR.mineVM;
    }

    @Override
    public MineViewModel initViewModel() {
        return null;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initViewObservable() {

    }

}

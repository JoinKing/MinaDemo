package mina.king.com.minademo.news.view;


import mina.king.com.minademo.BR;
import mina.king.com.minademo.databinding.FragmentNewBinding;
import mina.king.com.minademo.R;
import mina.king.com.minademo.news.viewModel.NewsViewModel;
import ui.king.com.kinglibrary.base.BaseFragment;

/**
 *
 */
public class NewFragment extends BaseFragment<FragmentNewBinding,NewsViewModel> {


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
        return new NewsViewModel();
    }

    @Override
    public void initData() {

    }

    @Override
    public void initViewObservable() {

    }

}

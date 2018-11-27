package ui.king.com.kinglibrary.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.util.ArrayList;

import ui.king.com.kinglibrary.base.BaseFragment;

/**
 * Created by king king 2018/11/27.
 * fragment 在当前的activity界面持久化
 */

public class FragmentUtils {

    private ArrayList<Fragment> fragments;
    private FragmentManager fragmentManager;
    private int idRes;

    public static FragmentUtils init(FragmentManager manager, int idRes, ArrayList<BaseFragment> list){
        FragmentUtils fragmentUtils = new FragmentUtils();
        fragmentUtils.fragmentManager = manager;
        fragmentUtils.idRes = idRes;
        if (null == fragmentUtils.fragments){
            fragmentUtils.fragments = new ArrayList<>();
        }
        fragmentUtils.fragments.addAll(list);
        return fragmentUtils;
    }

    private FragmentTransaction transaction;
    //之前显示的fragment
    private Fragment beforeFragment = null;
    public void switchFragment(int i) {
        // 已优化fragment 当前activity持久缓存问题
        if (beforeFragment != fragments.get(i)) {
            transaction = fragmentManager.beginTransaction();
            if (!fragments.get(i).isAdded()) { // 先判断是否被add过
                if (beforeFragment == null) {
                    transaction.add(idRes, fragments.get(i)).commit();
                } else {
                    transaction.hide(beforeFragment).add(idRes, fragments.get(i)).commit(); // 隐藏当前的fragment，add下一个到Activity中
                }
            } else {
                if (beforeFragment == null) {
                    transaction.show(fragments.get(i)).commit();
                } else {
                    transaction.hide(beforeFragment).show(fragments.get(i)).commit(); // 隐藏当前的fragment，显示下一个
                }
            }
            beforeFragment = fragments.get(i);
        }
    }
}

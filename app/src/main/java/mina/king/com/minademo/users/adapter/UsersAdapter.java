package mina.king.com.minademo.users.adapter;

import android.database.DataSetObserver;
import android.databinding.DataBindingUtil;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;

import java.util.ArrayList;
import java.util.List;

import mina.king.com.minademo.BR;
import mina.king.com.minademo.R;
import mina.king.com.minademo.databinding.ItemUserChildBinding;
import mina.king.com.minademo.databinding.ItemUserGroupBinding;
import mina.king.com.minademo.users.beans.UserGroupBean;

public class UsersAdapter implements ExpandableListAdapter {

    private List<UserGroupBean> beanList = new ArrayList<>();

    public void setBeanList(List<UserGroupBean> beanList) {
        this.beanList = beanList;
    }

    public List<UserGroupBean> getBeanList() {
        return beanList;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public int getGroupCount() {
        return beanList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return beanList.get(groupPosition).getUserChildBeanList().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return beanList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return beanList.get(groupPosition).getUserChildBeanList().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ItemUserGroupBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_user_group, parent, false);
        binding.setVariable(BR.group, beanList.get(groupPosition));
        binding.executePendingBindings();
        if (isExpanded) {
            binding.ivSelect.setImageResource(R.drawable.select_up);
        } else {
            binding.ivSelect.setImageResource(R.drawable.select_down);
        }
        return binding.getRoot();
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ItemUserChildBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_user_child, parent, false);
        binding.setVariable(BR.child, beanList.get(groupPosition).getUserChildBeanList().get(childPosition));
        binding.executePendingBindings();
        if (beanList.get(groupPosition).getUserChildBeanList().size()>0){
            binding.clChild.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onclick != null) {
                        onclick.onClick(groupPosition, childPosition);
                    }

                }
            });
        }

        return binding.getRoot();
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return true;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void onGroupExpanded(int groupPosition) {

    }

    @Override
    public void onGroupCollapsed(int groupPosition) {

    }

    @Override
    public long getCombinedChildId(long groupId, long childId) {
        return beanList.get(0).getUserChildBeanList().size();
    }

    @Override
    public long getCombinedGroupId(long groupId) {
        return 0;
    }

    private Onclick onclick;


    public void setOnclick(Onclick onclick) {
        this.onclick = onclick;
    }

    public interface Onclick {
        void onClick(int group, int child);
    }


}

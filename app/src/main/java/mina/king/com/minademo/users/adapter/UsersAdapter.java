//package mina.king.com.minademo.users.adapter;
//
//import android.database.DataSetObserver;
//import android.databinding.DataBindingUtil;
//import android.databinding.ViewDataBinding;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ExpandableListAdapter;
//import android.widget.ExpandableListView;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import mina.king.com.minademo.BR;
//import mina.king.com.minademo.R;
//import mina.king.com.minademo.databinding.ItemNewsBinding;
//import mina.king.com.minademo.databinding.ItemUserRootBinding;
//import mina.king.com.minademo.users.beans.UsersBean;
//
//public class UsersAdapter implements ExpandableListAdapter {
//
//    private List<UsersBean>beanList = new ArrayList<>();
//
//
//
//    @Override
//    public void registerDataSetObserver(DataSetObserver observer) {
//
//    }
//
//    @Override
//    public void unregisterDataSetObserver(DataSetObserver observer) {
//
//    }
//
//    @Override
//    public int getGroupCount() {
//        return beanList.size();
//    }
//
//    @Override
//    public int getChildrenCount(int groupPosition) {
//        return beanList.get(groupPosition).getNodes().size();
//    }
//
//    @Override
//    public Object getGroup(int groupPosition) {
//        return beanList.get(groupPosition);
//    }
//
//    @Override
//    public Object getChild(int groupPosition, int childPosition) {
//        return beanList.get(groupPosition).getNodes().get(childPosition);
//    }
//
//    @Override
//    public long getGroupId(int groupPosition) {
//        return groupPosition;
//    }
//
//    @Override
//    public long getChildId(int groupPosition, int childPosition) {
//        return childPosition;
//    }
//
//    @Override
//    public boolean hasStableIds() {
//        return false;
//    }
//
//    @Override
//    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
//        ItemUserRootBinding binding=DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_news,parent,false);
//        binding.setVariable(BR.root,beanList.get(groupPosition));
//        binding.executePendingBindings();
//        return null;
//    }
//
//    @Override
//    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
//
//        return null;
//    }
//
//    @Override
//    public boolean isChildSelectable(int groupPosition, int childPosition) {
//        return false;
//    }
//
//    @Override
//    public boolean areAllItemsEnabled() {
//        return false;
//    }
//
//    @Override
//    public boolean isEmpty() {
//        return false;
//    }
//
//    @Override
//    public void onGroupExpanded(int groupPosition) {
//
//    }
//
//    @Override
//    public void onGroupCollapsed(int groupPosition) {
//
//    }
//
//    @Override
//    public long getCombinedChildId(long groupId, long childId) {
//        return 0;
//    }
//
//    @Override
//    public long getCombinedGroupId(long groupId) {
//        return 0;
//    }
//}

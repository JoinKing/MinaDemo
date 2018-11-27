package mina.king.com.minademo.chat.adapter;

import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import mina.king.com.minademo.chat.adapter.holder.BaseViewHolder;
import mina.king.com.minademo.chat.adapter.holder.ChatAcceptViewHolder;
import mina.king.com.minademo.chat.adapter.holder.ChatSendViewHolder;
import mina.king.com.minachat.beans.MessageInfo;
import mina.king.com.minachat.utils.Constants;

/**
 * Created by king
 * @date 2018.11.14
 */

public class ChatAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private onItemClickListener onItemClickListener;
    public Handler handler;
    private List<MessageInfo> messageInfoList;

    public ChatAdapter(List<MessageInfo> messageInfoList) {
        handler = new Handler();
        this.messageInfoList = messageInfoList;
    }

    public void addItemClickListener(onItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        BaseViewHolder viewHolder = null;
        switch (viewType) {
            case Constants.CHAT_ITEM_TYPE_LEFT:
                viewHolder = new ChatAcceptViewHolder(parent, onItemClickListener, handler);
                break;
            case Constants.CHAT_ITEM_TYPE_RIGHT:
                viewHolder = new ChatSendViewHolder(parent, onItemClickListener, handler);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.itemView.setTag(position);
        holder.setData(messageInfoList.get(position));
    }

    @Override
    public int getItemViewType(int position) {
        return messageInfoList.get(position).getType();
    }

    @Override
    public int getItemCount() {
        if (messageInfoList == null) {
            return 0;
        } else {
            return messageInfoList.size();
        }
    }

    public void addAll(List<MessageInfo> messageInfos) {
        if (messageInfoList == null) {
            messageInfoList = messageInfos;
        } else {
            messageInfoList.addAll(messageInfos);
        }

        notifyDataSetChanged();
    }

    public void add(MessageInfo messageInfo) {
        if (messageInfoList == null) {
            messageInfoList = new ArrayList<>();
        }

        messageInfoList.add(messageInfo);

        notifyDataSetChanged();
    }

    public interface onItemClickListener {
        void onHeaderClick(int position);

        void onImageClick(View view, int position);

        void onVoiceClick(ImageView imageView, int position);

        void onFileClick(View view, int position);

        void onLinkClick(View view, int position);

        void onLongClickImage(View view, int position);

        void onLongClickText(View view, int position);

        void onLongClickItem(View view, int position);

        void onLongClickFile(View view, int position);

        void onLongClickLink(View view, int position);
    }
}

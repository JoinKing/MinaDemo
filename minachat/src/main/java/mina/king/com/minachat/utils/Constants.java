package mina.king.com.minachat.utils;

/**
 * Created by king
 * @date 2018.11.14
 */

public class Constants {
    public static final String TAG = "rance";
    public static final String AUTHORITY = "com.chatui.fileprovider";
    /** 0x001-接受消息  0x002-发送消息**/
    public static final int CHAT_ITEM_TYPE_LEFT = 0x001;
    public static final int CHAT_ITEM_TYPE_RIGHT = 0x002;
    /** 0x003-发送中  0x004-发送失败  0x005-发送成功**/
    public static final int CHAT_ITEM_SENDING = 0x003;
    public static final int CHAT_ITEM_SEND_ERROR = 0x004;
    public static final int CHAT_ITEM_SEND_SUCCESS = 0x005;

    public static final String CHAT_FILE_TYPE_TEXT = "TEXT";
    public static final String CHAT_FILE_TYPE_FILE = "FILE";
    public static final String CHAT_FILE_TYPE_IMAGE = "IMAGE";
    public static final String CHAT_FILE_TYPE_VOICE = "VOICE";
    public static final String CHAT_FILE_TYPE_CONTACT = "CONTENT";
    public static final String CHAT_FILE_TYPE_LINK = "LINK";
}

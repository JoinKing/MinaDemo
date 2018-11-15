package mina.king.com.minachat.contract;

import java.io.File;

import mina.king.com.minachat.beans.MessageInfo;


/**
 * Created by king on 2018/3/28.
 */

public interface ChatScreenContract {
    interface View{
        void msgSuccessStatus(Object message);
        void receivedMsg(MessageInfo bean);
    }
    interface Model{
       void sendTextMsg(View showView, Object sender, Object receiver, Object msgTyp, Object fileName, String msg);
       void sendPicMsg(View showView, Object sender, Object receiver, Object msgTyp, Object fileName, File msg);
       void sendVoiceMsg(View showView, Object sender, Object receiver, Object msgTyp, Object fileName, File msg);
       void sendPlanMsg(View showView, Object sender, Object receiver, Object msgTyp, Object fileName, String msg);
    }
}

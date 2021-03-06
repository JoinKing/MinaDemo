package mina.king.com.minachat.contract;

import java.io.File;

import mina.king.com.minachat.beans.MessageInfo;


/**
 * Created by king on 2018/3/28.
 */

public interface ChatScreenContract {
    interface View{
        void msgSuccessStatus(Object message);
        void receivedMsg(MessageInfo message);
    }
    interface Model{
       void sendTextMsg(View showView, Object sender, Object receiver, Object msgTyp, long time, String msg);
       void sendPicMsg(View showView, Object sender, Object receiver, Object msgTyp, Object viceTime, File msg);
       void sendVoiceMsg(View showView, Object sender, Object receiver, Object msgTyp, long time, File msg);
       void sendPlanMsg(View showView, Object sender, Object receiver, Object msgTyp, Object viceTime, String msg);
    }
}

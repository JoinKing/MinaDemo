package mina.king.com.minachat.handler;

import android.util.Log;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import mina.king.com.minachat.model.MsgCodeModel;

/**
 * Created by king on 2017/11/17.
 * 消息接收
 */

public class ClientHandler extends IoHandlerAdapter{

    //服务器发送异常
    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        super.exceptionCaught(session, cause);
    }

    //收到消息
    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        super.messageReceived(session, message);
        MsgCodeModel model = (MsgCodeModel) message;
        if (handlerCallback != null){
            handlerCallback.receivedMsg(model);
        }else {
            Log.e("msg", "==null:数据回调失败 " );
        }
    }

    //发送成功回调
    @Override
    public void messageSent(IoSession session, Object message) throws Exception {
        super.messageSent(session, message);
        if (handlerCallback != null){
            handlerCallback.successStatus(message);
        }

    }

    @Override
    public void sessionClosed(IoSession session) throws Exception {
        super.sessionClosed(session);
        Log.d("mina", "服务器与客户端断开连接: " );
    }

    @Override
    public void sessionCreated(IoSession session) throws Exception {
        super.sessionCreated(session);
        Log.d("mina", "服务器与客户端创建连接: " );
    }

    @Override
    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
        super.sessionIdle(session, status);
        Log.d("mina", "服务器进入空闲状态: ");
    }

    @Override
    public void sessionOpened(IoSession session) throws Exception {
        super.sessionOpened(session);
        Log.d("mina", "服务器与客户端连接打开: " );
    }

    private ClientHandlerCallback handlerCallback;
    public interface ClientHandlerCallback{

        void receivedMsg(MsgCodeModel message);
        void successStatus(Object state);
    }

    public void setMsgState(ClientHandlerCallback handlerCallback) {
        this.handlerCallback = handlerCallback;
    }
}

package mina.king.com.minachat.handler;

import android.util.Log;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import mina.king.com.minachat.model.MsgCodeModel;

/**
 * Created by hxb on 2017/11/17.
 * 消息接收
 */

public class ClientHandlerMsg extends IoHandlerAdapter{
    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        super.exceptionCaught(session, cause);
        Log.e("mina", "服务器发送异常: "+cause.getMessage());
    }

    //收到消息
    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        super.messageReceived(session, message);
        MsgCodeModel model = (MsgCodeModel) message;
        if (msg != null){
            msg.receivedMsg(model);
        }
    }

    //发送成功回调
    @Override
    public void messageSent(IoSession session, Object message) throws Exception {
        super.messageSent(session, message);
        Log.d("mina心跳", "messageSent: "+message);
        if (status != null){
            status.successStatus(message);
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

   public interface IsSuccess{
       void successStatus(Object message);
   }
   private IsSuccess status;

    public void setDataStatus(IsSuccess status) {
        this.status = status;
    }

    private ReceivedMsg msg;
    public interface ReceivedMsg{
        void receivedMsg(MsgCodeModel message);
    }

    public void setMsg(ReceivedMsg msg) {
        this.msg = msg;
    }
}

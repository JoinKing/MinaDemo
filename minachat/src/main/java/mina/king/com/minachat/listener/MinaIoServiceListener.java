package mina.king.com.minachat.listener;

import android.util.Log;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.service.IoService;
import org.apache.mina.core.service.IoServiceListener;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import java.net.InetSocketAddress;

import mina.king.com.minachat.model.EditDataModel;
import mina.king.com.minachat.utils.IpUtils;

public class MinaIoServiceListener implements IoServiceListener {
    public static String TAG = "mina";
    private IoConnector connector;

    public MinaIoServiceListener() {
    }

    public MinaIoServiceListener(IoConnector connector) {
        this.connector = connector;
    }

    @Override
    public void serviceActivated(IoService ioService) throws Exception {
        Log.e(TAG, "serviceActivated: " );
    }

    @Override
    public void serviceIdle(IoService ioService, IdleStatus idleStatus) throws Exception {
        Log.e(TAG, "serviceIdle: " );

    }

    @Override
    public void serviceDeactivated(IoService ioService) throws Exception {
        Log.e(TAG, "serviceDeactivated: " );

    }

    @Override
    public void sessionCreated(IoSession ioSession) throws Exception {
        Log.e(TAG, "sessionCreated: " );

    }

    @Override
    public void sessionClosed(IoSession ioSession) throws Exception {
        Log.e(TAG, "sessionClosed: " );

    }

    @Override
    public void sessionDestroyed(IoSession ioSession) {
        Log.e(TAG, "sessionDestroyed: " );

    }

//    @Override
//    public void sessionDestroyed(IoSession session) {
//        try {
//            int failCount = 0;
//            while (true) {
//                Thread.sleep(5000);
//                System.out.println(((InetSocketAddress) connector.getDefaultRemoteAddress()).getAddress()
//                        .getHostAddress());
//                ConnectFuture future = connector.connect();
//                System.out.println("断线2");
//                future.awaitUninterruptibly();// 等待连接创建完成
//                Log.e(TAG, "sessionDestroyed: 正在重连1" );
//                session = future.getSession();// 获得session
//                Log.e(TAG, "sessionDestroyed: 正在重连2" );
//                if (session != null && session.isConnected()) {
//                    Log.e(TAG, "sessionDestroyed: 正在重连3" );
//                    System.out.println("断线重连["
//                            + ((InetSocketAddress) session.getRemoteAddress()).getAddress().getHostAddress()
//                            + ":" + ((InetSocketAddress) session.getRemoteAddress()).getPort() + "]成功");
//                    session.write(EditDataModel.init().sendData(IpUtils.sendUserId));
////                    connectStatus = CONNECT_SUCCESSFUL;
//                    break;
//                } else {
//                    Log.e(TAG, "sessionDestroyed: 断线重连失败"+failCount+"次" );
////                    connectStatus = CONNECT_FAILURE;
//                }
//            }
//        } catch (Exception e) {
//
//        }
//    }
}

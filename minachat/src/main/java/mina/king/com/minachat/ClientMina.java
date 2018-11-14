package mina.king.com.minachat;

import android.util.Log;
import org.apache.mina.core.filterchain.IoFilterAdapter;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.service.IoService;
import org.apache.mina.core.service.IoServiceListener;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.keepalive.KeepAliveFilter;
import org.apache.mina.filter.keepalive.KeepAliveMessageFactory;
import org.apache.mina.filter.keepalive.KeepAliveRequestTimeoutHandler;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.filter.logging.MdcInjectionFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;
import java.net.InetSocketAddress;

import mina.king.com.minachat.factory.CodingProtocol;
import mina.king.com.minachat.factory.KeepAliveMessageFactoryImpl;
import mina.king.com.minachat.handler.ClientHandlerMsg;
import mina.king.com.minachat.listener.MinaIoServiceListener;
import mina.king.com.minachat.model.EditDataModel;
import mina.king.com.minachat.model.MsgCodeModel;
import mina.king.com.minachat.utils.IpUtils;


/**
 * Created by king on 2017/11/17.
 */

public class ClientMina extends Thread {
    public static ClientMina clientMina;
    private static String TAG = "[mina]";
    //30秒后超时
    private static final int IDELTIMEOUT = 10;
    //15秒发送一次心跳包
    private static final int HEARTBEATRATE = 10;

    private ClientHandlerMsg clientHandlerMsg;
    private ConnectFuture connectFuture;
    private IoSession session;
    private IoConnector connector;

    private EditDataModel mEditDataModel;

    private static final int CONNECT_SUCCESSFUL = 200;//连接成功
    private static final int CONNECT_FAILURE = 201;//连接失败
    private static final int CONNECT_CANCEL = 202;//连接取消
    private  int connectStatus;


    private ClientMina() {}

    public static ClientMina getIntrans() {
        if (null == clientMina) {
            clientMina = new ClientMina();
        }
        clientMina.mEditDataModel = EditDataModel.init();

        return clientMina;
    }

    public EditDataModel getEditDataModel() {
        return mEditDataModel;
    }

    @Override
    public void run() {
        super.run();
        initialize();
        connection();
    }
    /**
     * 设置外部回调
     */
    public void initialize() {
        /* ClientHandlerMsg */
        if (null == clientHandlerMsg) {
            clientHandlerMsg = new ClientHandlerMsg();
            clientHandlerMsg.setDataStatus(new ClientHandlerMsg.IsSuccess() {
                @Override
                public void successStatus(Object message) {
                    if (null != status) {
                        status.successStatus(message);
                    }
                }
            });
            clientHandlerMsg.setMsg(new ClientHandlerMsg.ReceivedMsg() {
                @Override
                public void receivedMsg(MsgCodeModel message) {
                    if (null != msg) {
                        msg.receivedMsg(message);
                    }
                }
            });
        }
    }

    private void connection() {
        if (null == connector) {
            connector = new NioSocketConnector();
            System.out.println(101);
            // 设置链接超时时间
            connector.setConnectTimeoutMillis(10000);
            System.out.println(102);
            // 添加过滤器
            connector.getFilterChain().addLast("codec", new ProtocolCodecFilter(new CodingProtocol()));
            System.out.println(110);
            connector.setHandler(new ClientHandlerMsg());
            System.out.println(111);
            //设置默认连接远程服务器的IP地址和端口
            connector.setDefaultRemoteAddress(new InetSocketAddress(IpUtils.ip, IpUtils.port));
            // 监听客户端是否断线
            connector.addListener(new MinaIoServiceListener(){
                @Override
                public void sessionDestroyed(IoSession session) {
                    try {
                        int failCount = 0;
                        while (true) {
                            Thread.sleep(5000);
                            System.out.println(((InetSocketAddress) connector.getDefaultRemoteAddress()).getAddress()
                                    .getHostAddress());
                            ConnectFuture future = connector.connect();
                            System.out.println("断线2");
                            future.awaitUninterruptibly();// 等待连接创建完成
                            Log.e(TAG, "sessionDestroyed: 正在重连1" );
                            session = future.getSession();// 获得session
                            Log.e(TAG, "sessionDestroyed: 正在重连2" );
                            if (session != null && session.isConnected()) {
                                Log.e(TAG, "sessionDestroyed: 正在重连3" );
                                System.out.println("断线重连["
                                        + ((InetSocketAddress) session.getRemoteAddress()).getAddress().getHostAddress()
                                        + ":" + ((InetSocketAddress) session.getRemoteAddress()).getPort() + "]成功");
                                session.write(EditDataModel.init().sendData(IpUtils.sendUserId));
                                break;
                            } else {
                                Log.e(TAG, "sessionDestroyed: 断线重连失败"+failCount+"次" );
                            }
                        }
                    } catch (Exception e) {

                    }
                }

            });
            //开始连接
            try {
                System.out.println(112);
                ConnectFuture future = connector.connect();
                System.out.println(113);
                future.awaitUninterruptibly();// 等待连接创建完成
                System.out.println(114);
                session = future.getSession();// 获得session
                System.out.println(115);
                //判断是否连接服务器成功
                if (session != null && session.isConnected()) {
                    session.write(EditDataModel.init().sendData(IpUtils.sendUserId));
                } else {
                    System.out.println("写数据失败");
                }
                System.out.println(11);
            } catch (Exception e) {
                Log.e(TAG, "connection: 客户端链接异常...");
            }
            if (session != null && session.isConnected()) {
                session.getCloseFuture().awaitUninterruptibly();// 等待连接断开
                Log.e(TAG, "connection: 客户端断开" );
                // connector.dispose();//彻底释放Session,退出程序时调用不需要重连的可以调用这句话，也就是短连接不需要重连。长连接不要调用这句话，注释掉就OK。
            }

        }
    }



    public void writeData(Object order) {
        if (null != session && session.isConnected()) {
            session.write(order);
            return;
        }
    }

    /**
     * 关闭Mina长连接 ** （外部调用）
     */
    public void minaClose() {
        if (connectStatus == CONNECT_FAILURE || connectStatus == CONNECT_SUCCESSFUL){
            return;//这两种状态不执行关闭长连接
        }
        if (null != session) {
            session.close(false);
            session.closeNow();
            session = null;
        }
        if (null != connectFuture && connectFuture.isConnected()) {
            connectFuture.cancel();
            connectFuture = null;
        }
        if (null != connector && !connector.isDisposed()) {
            //清空里面注册的所以过滤器
            connector.getFilterChain().clear();
            connector.dispose();
            connector = null;
        }
    }

    /**
     * 接口
     */

    public interface LoginConfig {
        Object senderLogin(String msg);
    }

    private LoginConfig loginConfig;

    public void setLoginConfig(LoginConfig loginConfig) {
        this.loginConfig = loginConfig;
    }

    public interface IsSuccess {//是否发送成功

        void successStatus(Object message);
    }

    private IsSuccess status;

    public void setDataStatus(IsSuccess status) {
        this.status = status;
    }

    private ReceivedMsg msg;

    public interface ReceivedMsg {//接受对方消息

        void receivedMsg(MsgCodeModel message);
    }

    public void setMsg(ReceivedMsg msg) {
        this.msg = msg;
    }

}

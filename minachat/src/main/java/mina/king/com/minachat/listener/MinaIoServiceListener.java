package mina.king.com.minachat.listener;

import android.util.Log;

import org.apache.mina.core.service.IoService;
import org.apache.mina.core.service.IoServiceListener;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

public class MinaIoServiceListener implements IoServiceListener {
    public static String TAG = "mina";
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

    }

    @Override
    public void sessionDestroyed(IoSession ioSession) throws Exception {

    }
}

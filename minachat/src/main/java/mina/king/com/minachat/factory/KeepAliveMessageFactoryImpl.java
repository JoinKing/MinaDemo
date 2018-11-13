package mina.king.com.minachat.factory;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.keepalive.KeepAliveMessageFactory;

/**
 * Created by king on 2017/11/17.
 */

public class KeepAliveMessageFactoryImpl implements KeepAliveMessageFactory{
    @Override
    public boolean isRequest(IoSession ioSession, Object o) {
        return false;
    }

    @Override
    public boolean isResponse(IoSession ioSession, Object o) {
        return false;
    }

    @Override
    public Object getRequest(IoSession ioSession) {
        return 10;
    }

    @Override
    public Object getResponse(IoSession ioSession, Object o) {
        return 10;
    }
}

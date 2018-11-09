package mina.king.com.minachat.decoding;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

import mina.king.com.minachat.model.MsgCodeModel;


/**
 * 加码类
 * @author Administrator
 *
 *
 */
public class Encodex implements ProtocolEncoder {

    @Override
    public void encode(IoSession ioSession, Object message, ProtocolEncoderOutput out) throws Exception {
        try {
            MsgCodeModel model = (MsgCodeModel) message;
            IoBuffer buffer = IoBuffer.allocate(1024).setAutoExpand(true);

            byte[] length = intToByteArray(model.getHeadLength());

            String head = model.getHeader();

            byte[] headByte = head.getBytes();
            
            buffer.put(length);
            buffer.put(headByte);
            System.out.println(new String(headByte));
            System.out.println(new String(model.getBody()));
            buffer.put(model.getBody());
            byte[] array = buffer.array();
            String str = new String(array);
            System.out.println(str);
            buffer.flip();
            out.write(buffer);
        } catch (Exception e) {
            System.out.println("44"+e.getMessage());
        }
    }

    @Override
    public void dispose(IoSession ioSession) throws Exception {

    }

    public static byte[] intToByteArray(int a) {
        return new byte[]{
                (byte) ((a >> 24) & 0xFF),
                (byte) ((a >> 16) & 0xFF),
                (byte) ((a >> 8) & 0xFF),
                (byte) (a & 0xFF)
        };
    }
}

package mina.king.com.minachat.model;


import org.apache.mina.core.buffer.IoBuffer;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import mina.king.com.minachat.contract.ChatScreenContract;
import mina.king.com.minachat.presenter.ChatScreenPresenter;


/**
 * Created by king on 2018/3/28.
 */

public class ChatScreenModel implements ChatScreenContract.Model {

    @Override
    public void sendTextMsg(final ChatScreenContract.View showView, Object sender, Object receiver, Object msgTyp, long time, final String msg) {
        IoBuffer ioBuffer = ChatScreenPresenter.mEditDataModel.sendData(sender, receiver, msgTyp, time, msg.getBytes());
        ChatScreenPresenter.mSendDataPresenter.sendOBDData(ioBuffer);
    }

    @Override
    public void sendPicMsg(ChatScreenContract.View showView, Object sender, Object receiver, Object msgTyp, Object viceTime, File msg) {
        try {
            FileInputStream fis = new FileInputStream(msg);
            ByteArrayOutputStream bos = new ByteArrayOutputStream((int) msg.length());
            byte[] b = new byte[1024];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            byte[] data = bos.toByteArray();
            IoBuffer ioBuffer = ChatScreenPresenter.mEditDataModel.sendData(sender, receiver, msgTyp, viceTime, data);
            ChatScreenPresenter.mSendDataPresenter.sendOBDData(ioBuffer);
            bos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendVoiceMsg(ChatScreenContract.View showView, Object sender, Object receiver, Object msgTyp, long time, File voiceFile) {
        try {
            FileInputStream fis = new FileInputStream(voiceFile);
            ByteArrayOutputStream bos = new ByteArrayOutputStream((int) voiceFile.length());
            byte[] b = new byte[1024];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            byte[] data = bos.toByteArray();
            IoBuffer ioBuffer = ChatScreenPresenter.mEditDataModel.sendData(sender, receiver, msgTyp, time, data);
            ChatScreenPresenter.mSendDataPresenter.sendOBDData(ioBuffer);
            bos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendPlanMsg(ChatScreenContract.View showView, Object sender, Object receiver, Object msgTyp, Object viceTime, String json) {
        IoBuffer ioBuffer = ChatScreenPresenter.mEditDataModel.sendData(sender, receiver, msgTyp, viceTime, json.getBytes());
        ChatScreenPresenter.mSendDataPresenter.sendOBDData(ioBuffer);
    }

}

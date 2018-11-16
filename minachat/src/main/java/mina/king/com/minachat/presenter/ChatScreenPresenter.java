package mina.king.com.minachat.presenter;

import android.os.Environment;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import mina.king.com.minachat.ClientMina;
import mina.king.com.minachat.beans.MessageInfo;
import mina.king.com.minachat.contract.ChatScreenContract;
import mina.king.com.minachat.model.ChatScreenModel;
import mina.king.com.minachat.model.EditDataModel;
import mina.king.com.minachat.model.MsgCodeModel;
import mina.king.com.minachat.utils.Constants;
import mina.king.com.minachat.utils.GetMp3Time;

/**
 * Created by king on 2018/3/28.
 */

public class ChatScreenPresenter {
    public static ChatScreenPresenter presenter;
    public final static EditDataModel mEditDataModel;//数据编辑
    public final static SendDataPresenter mSendDataPresenter;//发送数据操类
    private ChatScreenContract.View mView;
    private ChatScreenContract.Model mModel;

    private ChatScreenPresenter() {
    }

    public static ChatScreenPresenter getInstans(ChatScreenContract.View view) {
        if (null == presenter) {
            presenter = new ChatScreenPresenter();
        }
        //初始化
        presenter.mView = view;
        presenter.mModel = new ChatScreenModel();

        mSendDataPresenter.getmClientMina().setLoginConfig(new ClientMina.LoginConfig() {
            @Override
            public Object senderLogin(String msg) {
                return presenter.mSender;
            }
        });
        presenter.initMsgSendReceipt();
        return presenter;
    }

    static {
        mSendDataPresenter = SendDataPresenter.init();
        mEditDataModel = mSendDataPresenter.getmClientMina().getEditDataModel();
    }

    private Object mSender;//发送者的ID
    String msgType;
    private void initMsgSendReceipt() {
        //发送成功接口回调
        mSendDataPresenter.getmClientMina().setDataStatus(new ClientMina.IsSuccess() {
            @Override
            public void successStatus(Object message) {
                mView.msgSuccessStatus(message);
            }
        });

        //接受消息接口回调
        mSendDataPresenter.getmClientMina().setMsg(new ClientMina.ReceivedMsg() {
            @Override
            public void receivedMsg(MsgCodeModel message) {
                try {
                    JSONObject headerJson = new JSONObject(message.getHeader());
                    MessageInfo messageInfo = null;
                    msgType = headerJson.optString("msgType").trim();
                    Log.e("ss", "receivedMsg: "+headerJson );
                    switch (msgType) {
                        case Constants.CHAT_FILE_TYPE_TEXT:
                            messageInfo = new MessageInfo();
                            messageInfo.setHeader("http://img0.imgtn.bdimg.com/it/u=401967138,750679164&fm=26&gp=0.jpg");
                            messageInfo.setType(Constants.CHAT_ITEM_TYPE_LEFT);
                            messageInfo.setFileType(msgType);
                            messageInfo.setContent(new String(message.getBody()));
                            mView.receivedMsg(messageInfo);
                            break;
                        case Constants.CHAT_FILE_TYPE_IMAGE:
                            messageInfo = new MessageInfo();
                            messageInfo.setHeader("http://img0.imgtn.bdimg.com/it/u=401967138,750679164&fm=26&gp=0.jpg");
                            messageInfo.setType(Constants.CHAT_ITEM_TYPE_LEFT);
                            messageInfo.setFileType(msgType);
                            writeSD(message.getBody(),msgType,messageInfo);

                            break;
                        case Constants.CHAT_FILE_TYPE_VOICE:
                            messageInfo = new MessageInfo();
                            messageInfo.setHeader("http://img0.imgtn.bdimg.com/it/u=401967138,750679164&fm=26&gp=0.jpg");
                            messageInfo.setType(Constants.CHAT_ITEM_TYPE_LEFT);
                            messageInfo.setVoiceTime(headerJson.optLong("viceTime"));
                            messageInfo.setFileType(msgType);
                            writeSD(message.getBody(),msgType,messageInfo);
                            break;
                        case Constants.CHAT_FILE_TYPE_FILE:
                            messageInfo = new MessageInfo();
                            messageInfo.setHeader("http://img0.imgtn.bdimg.com/it/u=401967138,750679164&fm=26&gp=0.jpg");
                            messageInfo.setType(Constants.CHAT_ITEM_TYPE_LEFT);
                            messageInfo.setFileType(msgType);
                            break;
                        case Constants.CHAT_FILE_TYPE_LINK:
                            messageInfo = new MessageInfo();
                            messageInfo.setHeader("http://img0.imgtn.bdimg.com/it/u=401967138,750679164&fm=26&gp=0.jpg");
                            messageInfo.setType(Constants.CHAT_ITEM_TYPE_LEFT);
                            messageInfo.setFileType(msgType);
                            break;
                        case Constants.CHAT_FILE_TYPE_CONTACT:
                            messageInfo = new MessageInfo();
                            messageInfo.setHeader("http://img0.imgtn.bdimg.com/it/u=401967138,750679164&fm=26&gp=0.jpg");
                            messageInfo.setType(Constants.CHAT_ITEM_TYPE_LEFT);
                            messageInfo.setFileType(msgType);
                            break;

                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    File files;

    public void writeSD(byte[] file, String type, MessageInfo model) {
        boolean isMounted = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
        if (isMounted) {
            try {
                File parent_path = Environment.getExternalStorageDirectory();
                // 可以建立一个子目录专门存放自己专属文件
                File dir = new File(parent_path.getAbsoluteFile(), "MINA_CHAT");
                dir.mkdir();
                if (type.equals(Constants.CHAT_FILE_TYPE_IMAGE)) {
                    files = new File(dir.getAbsoluteFile(), System.currentTimeMillis() + ".wav");
                }
                if (type.equals(Constants.CHAT_FILE_TYPE_VOICE)) {
                    files = new File(dir.getAbsoluteFile(), System.currentTimeMillis() + ".jpg");
                }
                // 创建这个文件，如果不存在
                files.createNewFile();
                FileOutputStream fos = new FileOutputStream(files);
                byte[] buffer = file;
                // 开始写入数据到这个文件。
                fos.write(buffer, 0, buffer.length);
                //设置model，回调参数
                model.setFilepath(files.getAbsolutePath());
                mView.receivedMsg(model);
                fos.flush();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Log.d("receivedMsg", "未安装SDCard！");
        }

    }

    /**
     * @param receiver
     * @param msgTyp   TEXT
     * @param time
     * @param msg
     */
    public void sendTextMsg(Object receiver, Object msgTyp, long time, String msg) {
        mModel.sendTextMsg(mView, mSender, receiver, msgTyp, time, msg);
    }

    /**
     * @param receiver
     * @param msgTyp   IMAGHE
     * @param viceTime
     * @param msg
     */
    public void sendPicMsg(Object receiver, Object msgTyp, Object viceTime, File msg) {
        mModel.sendPicMsg(mView, mSender, receiver, msgTyp, viceTime, msg);
    }

    /**
     * @param receiver
     * @param msgTyp    VOICE
     * @param time
     * @param voiceFile
     */

    public void sendVoiceMsg(Object receiver, Object msgTyp, long time, File voiceFile) {
        mModel.sendVoiceMsg(mView, mSender, receiver, msgTyp, time, voiceFile);
    }


    public void closeClient() {
        if (mSendDataPresenter != null) {
            mSendDataPresenter.closeMinaThread();
        }
    }

}

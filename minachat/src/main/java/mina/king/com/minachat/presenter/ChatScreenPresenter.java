package mina.king.com.minachat.presenter;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;


import mina.king.com.minachat.ClientMina;
import mina.king.com.minachat.contract.ChatScreenContract;
import mina.king.com.minachat.model.ChatScreenModel;
import mina.king.com.minachat.model.EditDataModel;
import mina.king.com.minachat.model.MsgCodeModel;

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
                String msgType = null;
                try {
                    JSONObject contentJson = new JSONObject(message.getHeader());
                    msgType = contentJson.getString("msgType");
                    Log.e("TAG", "receivedMsg: "+new String(message.getBody()) );
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (jdbcInsert != null) {
                    jdbcInsert.heartCallback(message);
                }
                if (!TextUtils.isEmpty(msgType)) {
                    switch (msgType) {
                        case "10":
                            break;
//                        case "11":
//                            ChatDialogueBean model = new ChatDialogueBean();
//                            model.itemType = RoleUtils.TEXT_LEFT;//代表对方
//                            model.textContent = new String(message.getBody());
//                            mView.receivedMsg(model);
//                            //回掉 message
//                            if (jdbcInsert != null) {
//                                jdbcInsert.MessageCallback(message,RoleUtils.TEXT_LEFT);
//                            }
//                            break;
//                        case "21":
//                            ChatDialogueBean image = new ChatDialogueBean();
//                            image.itemType = RoleUtils.IMG_LEFT;//代表对方
//                            image.file = message.getBody();
//                            image.bitmap = BitmapFactory.decodeByteArray(message.getBody(), 0, message.getBody().length);
//                            writeSD(image.file, "21", image, message);
//                            break;
//                        case "22":
//                            ChatDialogueBean voice = new ChatDialogueBean();
//                            voice.itemType = RoleUtils.VOICE_LEFT;//代表对方
//                            voice.file = message.getBody();
//                            writeSD(voice.file, "22", voice, message);
//                            break;
//                        case "31":
//                            try {
//                                JSONObject jsonObject = new JSONObject(new String(message.getBody()));
//                                Log.e("TAG", "receivedMsg: " + jsonObject);
//                                ChatDialogueBean plan = new ChatDialogueBean();
//                                plan.itemType = RoleUtils.PLAN_LEFT;//代表对方
//                                plan.setCJHJJ(jsonObject.optString("CJHJJ"));
//                                plan.setCJKFABT(jsonObject.optString("CJKFABT"));
//                                plan.setCYSMC(jsonObject.optString("CYSMC"));
//                                plan.setDCREATTIME(jsonObject.optString("DCREATTIME"));
//                                plan.setCBM(jsonObject.optString("CBM"));
//                                plan.setCYSBM(jsonObject.optString("CYSBM"));
//                                plan.setCYSFABM(jsonObject.optString("CYSFABM"));
//                                mView.receivedMsg(plan);
//                                //回掉 message
//                                if (jdbcInsert != null) {
//                                    jdbcInsert.MessageCallback(message,RoleUtils.PLAN_LEFT);
//                                }
//                                //回掉 message
//                                if (callback != null) {
//                                    callback.healthPlan(
//                                            jsonObject.optString("CYSBM"),
//                                            UserInfoCache.getUserInfo(UserInfoCache.USER_PERSONALDOC) + "",
//                                            jsonObject.optString("CYSFABM"),
//                                            jsonObject.optString("1"),
//                                            jsonObject.optString("CBM")
//                                    );
//                                }
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//                            break;
                        case "41":
                            if (callback != null) {
                                callback.message(new String(message.getBody()));
                            }
                            break;
                        case "42":
                            if (callback != null) {
                                callback.bloodMessage(new String(message.getBody()));
                            }
                            break;
                        case "43":
                            Log.e("messageSent", "receivedMsg43: " + message.getBody().length);
                            break;

                    }
                }
            }
        });
    }

    File files;

//    public void writeSD(byte[] file, String type, ChatDialogueBean model, MsgCodeModel message) {
//        boolean isMounted = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
//        if (isMounted) {
//            try {
//                File parent_path = Environment.getExternalStorageDirectory();
//                // 可以建立一个子目录专门存放自己专属文件
//                File dir = new File(parent_path.getAbsoluteFile(), "CDCT_CHAT");
//                dir.mkdir();
//                if (type.equals("22")) {
//                    files = new File(dir.getAbsoluteFile(), System.currentTimeMillis() + ".wav");
//                }
//                if (type.equals("21")) {
//                    files = new File(dir.getAbsoluteFile(), System.currentTimeMillis() + ".jpg");
//                }
//                Log.d("文件路径", files.getAbsolutePath());
//                // 创建这个文件，如果不存在
//                files.createNewFile();
//                FileOutputStream fos = new FileOutputStream(files);
//                byte[] buffer = file;
//                // 开始写入数据到这个文件。
//                fos.write(buffer, 0, buffer.length);
//                switch (type) {
//                    case "21":
//                        model.imageUrl = files.getAbsolutePath();
//                        Log.e(" model.imageUrl", "writeSD: " + model.imageUrl);
//                        mView.receivedMsg(model);
//                        //回掉 message
//                        if (jdbcInsert != null) {
//                            jdbcInsert.MessageCallback(message, model.imageUrl,RoleUtils.IMG_LEFT);
//                        }
//                        break;
//                    case "22":
//                        model.voiceUrl = files.getAbsolutePath();
//                        mView.receivedMsg(model);
//                        //回掉 message
//                        if (jdbcInsert != null) {
//                            jdbcInsert.MessageCallback(message, model.voiceUrl,RoleUtils.VOICE_LEFT);
//                        }
//                        break;
//                }
//                fos.flush();
//                fos.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        } else {
//            Log.d("receivedMsg", "未安装SDCard！");
//        }
//
//    }


    /**
     * @param CYSBM //医生编号
     * @param CHZBM //患者编号
     * @param CBM   //健康方案编号
     * @param type  //发送类型 1.医生健康管理方案 2系统健康管理方案
     */
    private void sendServer(String CYSBM, String CHZBM, String CBM, String type) {

    }

    public void sendTextMsg(Object receiver, Object msgTyp, Object fileName, String msg) {
        mModel.sendTextMsg(mView, mSender, receiver, msgTyp, fileName, msg);
    }

    public void sendPicMsg(Object receiver, Object msgTyp, Object fileName, File msg) {
        mModel.sendPicMsg(mView, mSender, receiver, msgTyp, fileName, msg);
    }

    public void sendVoiceMsg(Object receiver, Object msgTyp, Object fileName, File voiceFile) {
        mModel.sendVoiceMsg(mView, mSender, receiver, msgTyp, fileName, voiceFile);
    }

    public void sendPlanMsg(Object receiver, Object msgTyp, Object fileName, String json) {
        mModel.sendPlanMsg(mView, mSender, receiver, msgTyp, fileName, json);
    }

    private MessageCallback callback;
    private JdbcInsert jdbcInsert;

    public void setJdbcInsert(JdbcInsert jdbcInsert) {
        this.jdbcInsert = jdbcInsert;
    }

    public void setCallback(MessageCallback callback) {
        this.callback = callback;
    }

    public interface MessageCallback {
        void message(String json);
        void bloodMessage(String json);

        void healthPlan(String CYSBM, String CHZBM, String CBM, String type, String CYSFABM);
    }

    public interface JdbcInsert {
        void MessageCallback(MsgCodeModel message, int type);

        void MessageCallback(MsgCodeModel message, String url, int type);

        void heartCallback(MsgCodeModel message);
    }

    public void closeClient() {
        if (mSendDataPresenter != null) {
            mSendDataPresenter.closeMinaThread();
        }
    }

}

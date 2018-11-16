package mina.king.com.minachat.model;


import org.apache.mina.core.buffer.IoBuffer;
import org.json.JSONObject;

import java.util.HashMap;

import mina.king.com.minachat.utils.ByteUtils;

/**
 * Created by king on 2017/11/17.
 *
 */

public class EditDataModel {

    public static EditDataModel model = null;
    private EditDataModel(){
    }
    public static EditDataModel init(){
        if (model==null){
            model = new EditDataModel();
        }
        return model;
    }
    public IoBuffer sendData(Object senderFirst){//首次跟后台建立连接
        HashMap<String,Object> json = new HashMap<>();
        json.put("sender",senderFirst);
        json.put("receiver","");
        json.put("msgType","1");
        json.put("viceTime","");
        json.put("bodyLength",0);
        JSONObject jsonObject = new JSONObject(json);

        byte[] jsonByte = jsonObject.toString().getBytes();

        int jsonByteLength1 = jsonByte.length;

        byte[] jsonLengthByte1 = ByteUtils.intToByteArray(jsonByteLength1);

        byte[] all = ByteUtils.unitByteArray(jsonLengthByte1,jsonByte);

        IoBuffer buffer1 = IoBuffer.allocate(all.length).put(all,0,all.length);
        buffer1.flip();
        return buffer1;
    }

    public IoBuffer sendData(Object sender,Object receiver,Object msgTyp,Object viceTime,byte[] body){
        int bodyLength = body.length;
        HashMap<String,Object> contentMap = new HashMap<>();
        contentMap.put("sender", sender);
        contentMap.put("receiver",receiver);
        contentMap.put("msgType",msgTyp);
        contentMap.put("viceTime",viceTime);
        contentMap.put("bodyLength",bodyLength);

        JSONObject jsonObject = new JSONObject(contentMap);
        byte[] jsonByte = jsonObject.toString().getBytes();
        int jsonByteLength = jsonByte.length;
        byte[] jsonLengthToByte = ByteUtils.intToByteArray(jsonByteLength);
        byte[] jsonBody = ByteUtils.unitByteArray(jsonLengthToByte,jsonByte);
        byte[] all = ByteUtils.unitByteArray(jsonBody,body);
        IoBuffer buffer1 = IoBuffer.allocate(all.length).put(all,0,all.length);
        buffer1.flip();
        return buffer1;
    }
}

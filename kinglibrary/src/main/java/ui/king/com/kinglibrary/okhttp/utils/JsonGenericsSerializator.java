package ui.king.com.kinglibrary.okhttp.utils;


import com.google.gson.Gson;

import ui.king.com.kinglibrary.okhttp.callback.IGenericsSerializator;

/**
 *  Created by KING on 2018.1.15.
 */
public class JsonGenericsSerializator implements IGenericsSerializator {
    Gson mGson = new Gson();
    @Override
    public <T> T transform(String response, Class<T> classOfT) {
        return mGson.fromJson(response, classOfT);
    }
}

package ui.king.com.kinglibrary.okhttp.builder;

import java.util.Map;

/**
 * Created by KING on 2018.1.15.
 */
public interface HasParamsable
{
    OkHttpRequestBuilder params(Map<String, String> params);
    OkHttpRequestBuilder addParams(String key, String val);
}

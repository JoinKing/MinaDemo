package ui.king.com.kinglibrary.okhttp.builder;


import ui.king.com.kinglibrary.okhttp.OkHttpUtils;
import ui.king.com.kinglibrary.okhttp.request.OtherRequest;
import ui.king.com.kinglibrary.okhttp.request.RequestCall;

/**
 * Created by KING on 2018.1.15.
 */
public class HeadBuilder extends GetBuilder
{
    @Override
    public RequestCall build()
    {
        return new OtherRequest(null, null, OkHttpUtils.METHOD.HEAD, url, tag, params, headers,id).build();
    }
}

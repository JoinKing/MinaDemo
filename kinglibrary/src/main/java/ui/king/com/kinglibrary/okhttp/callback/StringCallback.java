package ui.king.com.kinglibrary.okhttp.callback;

import java.io.IOException;

import okhttp3.Response;

/**
 * Created by KING on 2018.1.15.
 */
public abstract class StringCallback extends Callback<String>
{
    @Override
    public String parseNetworkResponse(Response response, int id) throws IOException
    {
        return response.body().string();
    }
}

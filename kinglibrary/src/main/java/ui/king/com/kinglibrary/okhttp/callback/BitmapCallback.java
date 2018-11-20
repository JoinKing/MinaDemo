package ui.king.com.kinglibrary.okhttp.callback;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import okhttp3.Response;

/**
 * Created by KING on 2018.1.15.
 */
public abstract class BitmapCallback extends Callback<Bitmap>
{
    @Override
    public Bitmap parseNetworkResponse(Response response , int id) throws Exception
    {
        return BitmapFactory.decodeStream(response.body().byteStream());
    }

}

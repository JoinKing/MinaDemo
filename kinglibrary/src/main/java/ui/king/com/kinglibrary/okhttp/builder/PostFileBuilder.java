package ui.king.com.kinglibrary.okhttp.builder;



import java.io.File;

import okhttp3.MediaType;
import ui.king.com.kinglibrary.okhttp.request.PostFileRequest;
import ui.king.com.kinglibrary.okhttp.request.RequestCall;

/**
 * Created by KING on 2018.1.15.
 */
public class PostFileBuilder extends OkHttpRequestBuilder<PostFileBuilder>
{
    private File file;
    private MediaType mediaType;


    public OkHttpRequestBuilder file(File file)
    {
        this.file = file;
        return this;
    }

    public OkHttpRequestBuilder mediaType(MediaType mediaType)
    {
        this.mediaType = mediaType;
        return this;
    }

    @Override
    public RequestCall build()
    {
        return new PostFileRequest(url, tag, params, headers, file, mediaType,id).build();
    }


}

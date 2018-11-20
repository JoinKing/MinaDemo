package ui.king.com.kinglibrary.okhttp.builder;



import okhttp3.MediaType;
import ui.king.com.kinglibrary.okhttp.request.PostStringRequest;
import ui.king.com.kinglibrary.okhttp.request.RequestCall;

/**
 * Created by KING on 2018.1.15.
 */
public class PostStringBuilder extends OkHttpRequestBuilder<PostStringBuilder>
{
    private String content;
    private MediaType mediaType;


    public PostStringBuilder content(String content)
    {
        this.content = content;
        return this;
    }

    public PostStringBuilder mediaType(MediaType mediaType)
    {
        this.mediaType = mediaType;
        return this;
    }

    @Override
    public RequestCall build()
    {
        return new PostStringRequest(url, tag, params, headers, content, mediaType,id).build();
    }


}

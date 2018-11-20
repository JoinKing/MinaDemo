package ui.king.com.kinglibrary.okhttp.builder;



import okhttp3.RequestBody;
import ui.king.com.kinglibrary.okhttp.request.OtherRequest;
import ui.king.com.kinglibrary.okhttp.request.RequestCall;

/**
 * Created by KING on 2018.1.15.
 */
public class OtherRequestBuilder extends OkHttpRequestBuilder<OtherRequestBuilder>
{
    private RequestBody requestBody;
    private String method;
    private String content;

    public OtherRequestBuilder(String method)
    {
        this.method = method;
    }

    @Override
    public RequestCall build()
    {
        return new OtherRequest(requestBody, content, method, url, tag, params, headers,id).build();
    }

    public OtherRequestBuilder requestBody(RequestBody requestBody)
    {
        this.requestBody = requestBody;
        return this;
    }

    public OtherRequestBuilder requestBody(String content)
    {
        this.content = content;
        return this;
    }


}

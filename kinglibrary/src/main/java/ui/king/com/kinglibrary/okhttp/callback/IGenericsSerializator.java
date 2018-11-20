package ui.king.com.kinglibrary.okhttp.callback;

/**
 * Created by KING on 2018.1.15.
 */
public interface IGenericsSerializator {
    <T> T transform(String response, Class<T> classOfT);
}

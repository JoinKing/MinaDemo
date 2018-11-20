package ui.king.com.kinglibrary.okhttp.utils;

/**
 *  Created by KING on 2018.1.15.
 */
public class Exceptions
{
    public static void illegalArgument(String msg, Object... params)
    {
        throw new IllegalArgumentException(String.format(msg, params));
    }


}

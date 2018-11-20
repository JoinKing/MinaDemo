package ui.king.com.kinglibrary.okhttp.utils;

import android.util.Log;

/**
 *  Created by KING on 2018.1.15.
 */
public class L
{
    private static boolean debug = true;

    public static void e(String msg)
    {
        if (debug)
        {
            Log.e("KING", msg);
        }
    }

}


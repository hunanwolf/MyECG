package com.test720.auxiliary.Utils;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 操作安装包中的“assets”目录下的文件
 *
 * @author 李玉江[QQ:1023694760]
 * @version 2013-11-2
 */
public class AssetsUtils {

    /**
     * read file content
     *
     * @param context   the context
     * @param assetPath the asset path
     * @return String string
     */
    public static String readText(Context context, String assetPath) {
        L.e("read assets file as text: " + assetPath);
        try {
            return toString(context.getAssets().open(assetPath));
        } catch (Exception e) {
            L.e(e);
            return "";
        }
    }
    /**
     * To string string.
     *
     * @param is the is
     * @return the string
     */
    public static String toString(InputStream is) {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            is.close();
        } catch (IOException e) {
            L.e(e);
        }
        return sb.toString();
    }


}

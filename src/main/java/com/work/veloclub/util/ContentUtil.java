package com.work.veloclub.util;

public class ContentUtil {

    public static String getPreview(String content) {
        int maxLength = 200;
        if (content.length() <= maxLength) {
            return content;
        }
        return content.substring(0, maxLength) + "...";
    }

}

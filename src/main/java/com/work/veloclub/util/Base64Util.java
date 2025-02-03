package com.work.veloclub.util;

import java.util.Base64;

public class Base64Util {

    public static byte[] decoder(String base64String){
        return base64String != null ? Base64.getDecoder().decode(base64String) : null;
    }

    public static String encoder(byte[] bytes){
        return bytes != null ? Base64.getEncoder().encodeToString(bytes) : null;
    }
}

package com.topxgun.appbase.net;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;

/**
 * Created by rjm4413 on 2016/5/24.
 */
public class ApiUtils {

    public static String sign(String paramsStr, String secret) {
        StringBuffer sb = new StringBuffer();
        try {
            JSONObject params = new JSONObject(paramsStr);
            params.remove("token");
            Iterator it = params.keys();
            while (it.hasNext()) {
                String key = (String) it.next();
                String value = params.getString(key);
                sb.append(key+value);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return md5(secret+sb.toString()+secret);
    }

    public static String md5(String string) {
        byte[] hash;
        try {
            hash = MessageDigest.getInstance("MD5").digest(string.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Huh, MD5 should be supported?", e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Huh, UTF-8 should be supported?", e);
        }

        StringBuilder hex = new StringBuilder(hash.length * 2);
        for (byte b : hash) {
            if ((b & 0xFF) < 0x10) hex.append("0");
            hex.append(Integer.toHexString(b & 0xFF));
        }
        return hex.toString();
    }
}

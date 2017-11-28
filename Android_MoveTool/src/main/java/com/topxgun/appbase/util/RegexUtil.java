package com.topxgun.appbase.util;

import java.util.regex.Pattern;

/**
 * Created by rjm4413 on 2017/1/19.
 */

public class RegexUtil {
    /**
     * 正则表达式:验证密码(不包含特殊字符)
     */
    public static final String REGEX_PASSWORD = "^[a-zA-Z0-9]{8,24}$";

    public static boolean regexPassword(String pwd){
        return Pattern.matches(REGEX_PASSWORD, pwd);
    }
}

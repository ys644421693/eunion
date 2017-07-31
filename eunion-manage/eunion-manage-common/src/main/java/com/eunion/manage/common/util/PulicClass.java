package com.eunion.manage.common.util;

/**
 * Created by yangshuo on 2016/11/27.
 */
public class PulicClass {

    public static String getErrorMessage(String str) {
        String[] dt = str.split("}");
        StringBuilder s = new StringBuilder();
        for (String t : dt) {
            if (t.contains("messageTemplate")){
                s.append("[");
                s.append(t.substring(t.lastIndexOf("=") + 2, t.lastIndexOf("'")));
                s.append("]");
            }

        }
        return s.toString();
    }
}

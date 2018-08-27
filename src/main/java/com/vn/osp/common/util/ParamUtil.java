package com.vn.osp.common.util;

/**
 * Created by tranv on 12/26/2016.
 */
public class ParamUtil {
    public static final int NULL = 0;
    public static int getIntParam(String param){
        if(param != null) return Integer.valueOf(param);
        return NULL;
    }

    public static long getLongParam(String param){
        if(param != null) return Long.valueOf(param);
        return NULL;
    }
    public static String setIntParam(int param){
        return String.valueOf(param);
    }
    public static String setIntParam(long param){
        return String.valueOf(param);
    }
}

package com.vn.osp.util;

import java.lang.reflect.Field;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by TienManh on 6/29/2017.
 */
public class StringUtils {
    /**
     * method strim() all field ob object if it's String.class
     * @param item
     * @return item has no field string have space
     */
    public static void trimAllFieldOfObject(Object item) {
        if (item == null) {
            return;
        }
        Field[] fields = item.getClass().getDeclaredFields();
        if (fields == null) {
            return;
        }

        for (Field f : fields) {
            if (f.getType().isPrimitive()) {
                continue;
            }
            if (f.getType().equals(String.class)) {
                try {
                    f.setAccessible(true);
                    String value = (String) f.get(item);
                    f.set(item, org.apache.commons.lang3.StringUtils.trimToNull(value));
                } catch (IllegalAccessException e) {
                }

            }
        }
    }

    /**
     * method remove all char not is charactor and number
     * @param value
     * @return a string has no special charactor
     */
    public static String removeSpecialCharacters(String value) {
        try {
            return value.replaceAll("[^a-zA-Z0-9]", "").replaceAll(" ", "").replaceAll("\t", "").replaceAll("(\\r|\\n)", "");
        } catch (Exception var2) {
            return null;
        }
    }

    public static String removeSpecialCharactersNotSpace(String value) {
        String ALPHANUMERIC = "[^\\p{L}\\p{N} ]+";
        try {
            return value.replaceAll(ALPHANUMERIC, "");
        } catch (Exception var2) {
            return null;
        }
    }
    public static boolean checkSpecialCharacter(String value) {
        String ALPHANUMERIC = "^[\\p{L}\\p{N} ]+";//gom` ko phai la cac so, chu~ ko dau' va co dau', dau cach
        try {
            Pattern p = Pattern.compile(ALPHANUMERIC, Pattern.CANON_EQ | Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
            Matcher m = p.matcher(value);
            return m.matches();
        } catch (Exception var2) {
            return false;
        }
    }




}

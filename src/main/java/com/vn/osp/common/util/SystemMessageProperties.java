package com.vn.osp.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


/**
 * SystemMessageProperties
 * 
 * @author Nguyen Thanh Hai
 * @version $Revision: 17051 $
 */
public class SystemMessageProperties {


    public static final String DEFAULT_VALUE = "invalid";


    private static final Properties SYSTEM_PROPERTIES = new Properties();

    static {

        ClassLoader loader = SystemMessageProperties.class.getClassLoader();
        try {
            SYSTEM_PROPERTIES.load(loader
                    .getResourceAsStream("systemMessage.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * getSystemProperty
     * 
     * @param key
     * @return value
     */
    public static String getSystemProperty(String key) {
        String ret = SystemMessageProperties.DEFAULT_VALUE;
        if (SYSTEM_PROPERTIES.containsKey(key)) {
            ret = SYSTEM_PROPERTIES.getProperty(key);
        }

        return ret;
    }

    public static String getSystemPropertyFromClassPath(String fileName,String key) {
        String ret = SystemMessageProperties.DEFAULT_VALUE;
        Properties prop = new Properties();
        InputStream input = null;
        try {
            //String filename = "system.properties";
            input = SystemMessageProperties.class.getClassLoader().getResourceAsStream(fileName);
            if (input == null) {
                return ret;
            }
            prop.load(input);
            ret = prop.getProperty(key);

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return ret;
    }
}
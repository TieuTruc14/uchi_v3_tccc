package com.vn.osp.modelview;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.Iterator;

/**
 * Created by TienManh on 6/26/2017.
 */
public class test {
    public static void main(String[] args) {
        Contract contract=new Contract();
        contract.setBank_name("   mama kakâ kdfj  ");
        contract.setNote("   dfdfdf    ");
        //System.out.println("123"+contract.getBank_name()+"123");
        //System.out.println(contract.getNote());
        trimAllFieldOfObject(contract);
        //System.out.println("123"+contract.getBank_name()+"123");
        //System.out.println(contract.getNote());

    }


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
                    f.set(item, StringUtils.trimToNull(value));
                } catch (IllegalAccessException e) {
                }

            }
        }
    }

}

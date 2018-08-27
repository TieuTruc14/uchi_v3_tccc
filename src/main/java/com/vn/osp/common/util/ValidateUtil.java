package com.vn.osp.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by HoaRonal on 6/23/2017.
 */
public class ValidateUtil {
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    private static Pattern pattern;
    private static Matcher matcher;

    public static boolean exitsSpace(String input){
        pattern = Pattern.compile("\\s");
        matcher = pattern.matcher(input);
        boolean check = matcher.find();
        return check;
    }

    public static String validate_msg_from_date;
    public static String validate_msg_to_date;
    static{
        validate_msg_from_date = "";
        validate_msg_to_date="";
    }

    public static boolean validateDateto(String datePathern) {
        int check = checkDate(datePathern);
        switch (check) {
            case 0:
                return true;
            case 1:
                validate_msg_to_date = "Thời gian nhập không hợp lệ.(dd/MM/yyyy)";
                return false;
            /*case 2:
                validate_msg_to_date = "Không được để trống.";
                return false;*/
            case 3:
                validate_msg_to_date = "Ngày nhập vượt quá thời gian hiện tại.";
                return false;
            default:
                return false;
        }
    }

    public static boolean validateDatefrom(String datePathern) {
        int check = checkDate(datePathern);
        switch (check) {
            case 0:
                return true;
            case 1:
                validate_msg_from_date = "Thời gian nhập không hợp lệ.(dd/MM/yyyy)";
                return false;
            /*case 2:
                validate_msg_from_date = "Không được để trống.";
                return false;*/
            case 3:
                validate_msg_from_date = "Ngày nhập vượt quá thời gian hiện tại.";
                return false;
            default:
                return false;
        }
    }

    public static int checkDate(String datePathern) {
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        df.setLenient(false);
        if(datePathern == null || datePathern.equals(""))
        {
            return 2;
        }
        try {
            Date date = df.parse(datePathern);
            if(date.after(new Date()))
            {
                return 3;
            }
            else
            {
                return 0;
            }
        } catch (ParseException e) {
            return 1;
        }
    }

    /**
     * Hàm validate email
     */
    public static boolean validate(Object value){
        String emailValue = value.toString();
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(emailValue);
        boolean check = matcher.matches();
        return check;
    }
}

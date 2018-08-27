package com.vn.osp.common.util;

import com.vn.osp.context.CommonContext;
import com.vn.osp.modelview.GrouproleAuthority;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by tranv on 4/7/2017.
 */
public class ValidationPool {
    /*Bieu thuc chinh quy cho truong khong chua cac ky tu dac biet*/
    public static final String NOT_SPECICAL = "[^\\\\/:\\*\\?\"<>\\|]*";

    public static final String TR_NOT_SPECICAL = "[^\\`|\\~|\\!|\\@|\\#|\\$|\\%|\\^|\\&|\\*|\\(|\\)|\\+|\\=|\\[|\\{|\\]|\\}|\\||\\\\|\\'|\\<|\\,|\\.|\\>|\\?|\\/|\\\"\"|\\;|\\:|\\|]*";

    /*Bieu thuc chinh quy cho do dai cua truong*/
    public static String length(int from, int to) {
        return ".{" + from + "," + to + "}";
    }
    /*Bieu thuc chinh quy cho truong chi chua cac chữ cái, số, dấu gạch dưới và dấu gạch ngang.*/
    public static final String OK_SPECICAL = "[\\w-]*";
    /*Bieu thuc chinh quy cho truong email.*/
    public static final String EMAIL = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
    /*Bieu thuc chinh quy cho mobile/telephone.*/
    public static final String PHONE = "[\\d]*";
    /*Bieu thuc chinh quy cho date.*/
    public static final String DATE = "[\\d]{2}/[\\d]{2}/[\\d]{4}";

    /*Ham kiem tra dinh dang du lieu dau vao dang date*/
    public static Boolean checkDateFormat(String date){
        Boolean success = true;
        if (date != null && !date.equals("") && !date.matches(ValidationPool.DATE)){
            success = false;
        } else {
            String[] arr =  date.split("/");
            int ngay = Integer.valueOf(arr[0]);
            int thang = Integer.valueOf(arr[1]);
            int nam = Integer.valueOf(arr[2]);
            if(ngay <1 || ngay>31){
                success = false;
            }
            if(thang <1 || thang>12){
                success = false;
            }
        }
        return success;
    }
    public static Boolean checkDecimal(String number){
       try{
           int temp = Integer.parseInt(number);
           if(temp < 0) return false;
           return true;
       }catch (NumberFormatException e){
           return false;
       }
    }

    /*
    * Ham so sanh date1 co truoc date2 hay ko
    * Co : true
    * Khong : false
    * example: 2017/4/12 <= 2017/4/13 ? true: false
    * */
    public  static Boolean compareDate(String date1, String date2){
        try {
            if (date1 == null || date1.equals("")) return false;
            if (date2 == null || date2.equals("")) return false;

            String temp1= RelateDateTime.formatDate(date1, "dd/MM/yyyy", "yyyy/MM/dd");
            String temp2= RelateDateTime.formatDate(date2, "dd/MM/yyyy", "yyyy/MM/dd");

            String[] arr1 = temp1.split("/");
            String[] arr2 = temp2.split("/");

            int ngay1 = Integer.parseInt(arr1[2]);
            int thang1 = Integer.parseInt(arr1[1]);
            int nam1 = Integer.parseInt(arr1[0]);

            int ngay2 = Integer.parseInt(arr2[2]);
            int thang2 = Integer.parseInt(arr2[1]);
            int nam2 = Integer.parseInt(arr2[0]);

            if(nam1 < nam2) return true;
            if(nam1== nam2){
                if(thang1 < thang2) return true;
                if(thang1 == thang2) {
                    if(ngay1 <= ngay2) {
                        return true;
                    }
                }
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    /*
    * Ham so sanh date co truoc today hay ko
    * Co : true
    * Khong : false
    * example: 2017/4/12 <= today ? true: false
    * */
    public  static Boolean compareToday(String date){
        try {
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date recent = new Date();
            String today = dateFormat.format(recent);

            return ValidationPool.compareDate(date, today);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public static  Boolean checkRole(HttpServletRequest request,String code) {
        HttpSession session = request.getSession();
        CommonContext context = ((CommonContext)request.getSession().getAttribute(request.getSession().getId()));
        List<GrouproleAuthority> grouproleAuthorities = context.getGrouproleAuthorities();
        for (int i = 0; i < grouproleAuthorities.size(); i++) {
            String authority_code = grouproleAuthorities.get(i).getAuthority_code();
            if (authority_code.equals(code)) return true;
        }
        return false;
    }

    /**
     * author:manhpt
     * @param request
     * @param listCode
     * @return
     */
    public static boolean checkHasRoleInList(HttpServletRequest request,String listCode){
        HttpSession session=request.getSession();
        CommonContext context=((CommonContext)request.getSession().getAttribute(request.getSession().getId()));
        String list[];
        if(!StringUtils.isBlank(listCode)){
            list=listCode.split(",");
            if(list!=null && list.length>0){
                for(int i=0;i<list.length;i++){
                    if(checkRole(request,list[i])) return true;
                }
            }
        }
        return false;
    }

    /**
     * author: manhpt
     * check detail of role code
     * @param request
     * @param code: authority_code of npo_authority
     * @param authorityType: value of authority
     * @return
     */

    public static Boolean checkRoleDetail(HttpServletRequest request,String code, int authorityType){
        HttpSession session=request.getSession();
        CommonContext context=((CommonContext)request.getSession().getAttribute(request.getSession().getId()));
        List<GrouproleAuthority> grouproleAuthorities = context.getGrouproleAuthorities();
        for (int i = 0; i < grouproleAuthorities.size(); i++) {
            int temp = grouproleAuthorities.get(i).getValue() & authorityType;
            if (temp > 0 && grouproleAuthorities.get(i).getAuthority_code().equals(code)) return true;
        }
        return false;
    }
}

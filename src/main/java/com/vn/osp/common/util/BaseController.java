package com.vn.osp.common.util;

import com.vn.osp.modelview.AccessHistory;
import com.vn.osp.modelview.User;
import com.vn.osp.service.QueryFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Created by HoaRonal on 6/27/2017.
 */

public class BaseController {

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

    public static User getUserSession(HttpServletRequest request){
        HttpSession sessions = request.getSession();
        User user = (User) sessions.getAttribute(sessions.getId());
        return user;
    }

    protected String forward(String requestMapping) {
        return "forward:" + requestMapping;
    }

    protected String redirect(String requestMapping) {
        return "redirect:" + requestMapping;
    }

    private boolean isNullBoth(Object obj1, Object obj2) {
        return obj1 == null && obj2 == null;
    }

    private boolean isNullEachOther(Object obj1, Object obj2) {
        return obj1 == null || obj2 == null;
    }
    public void setAccessHistory(User user, int accessType, HttpServletRequest request,String description) {
        AccessHistory accessHistory = new AccessHistory();
        accessHistory.setUsid(Long.valueOf(user.getUserId()).intValue());
        /*accessHistory.setExecute_person(user.getFamily_name() + " " + user.getFirst_name() + " (" + user.getAccount() + ")");*/
        accessHistory.setExecute_person(user.getAccount());
        accessHistory.setAccess_type(accessType);
        String remoteAddr="";
        if(request!=null){
            remoteAddr=request.getHeader("X-FORWARDED-FOR");
            if(remoteAddr==null || "".equals(remoteAddr)){
                remoteAddr=request.getRemoteAddr();
            }
        }
        accessHistory.setClient_info(remoteAddr + " [" + request.getSession().getId() + "]");
       // accessHistory.setClient_info(request.getRemoteAddr() + " [" + request.getSession().getId() + "]");
        accessHistory.setDescription(description.toString());
        QueryFactory.setAccesHistory(accessHistory);
    }

    public static String formatNumber(int number) {
        if (number < 1000) {
            return String.valueOf(number);
        }
        try {
            NumberFormat formatter = new DecimalFormat("###,###");
            String resp = formatter.format(number);
            resp = resp.replaceAll(",", ".");
            return resp;
        } catch (Exception e) {
            return "";
        }
    }
}

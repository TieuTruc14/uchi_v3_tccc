package com.vn.osp.modelview;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.vn.osp.common.util.TimeUtil;

import java.util.Date;
import java.util.List;

/**
 * Created by minh on 3/24/2017.
 */
public class AccessHistoryList {
    public static final String SESSION_KEY = "AccessHistory";

    private List<AccessHistory> accessHistories;

    private String execute_person;
    private String client_info;
    private String execute_date_time;
    private Integer access_type;

    private int total;
    private int page;
    private int totalPage;
    private Long userId;

    private List<User> userList;

    private String timeType;
    private String toDate;
    private String fromDate;

    private String notaryDateFromFilter;
    private String notaryDateToFilter;


    public AccessHistoryList() {
    }
    public AccessHistoryList(List<AccessHistory> accessHistories, int total, int page, int totalPage, String execute_person, Integer access_type, String client_info, String execute_date_time, List<User> userList, Long userId ) {
        this.accessHistories = accessHistories;
        this.total = total;
        this.page = page;
        this.totalPage = totalPage;
        this.execute_person = execute_person;
        this.client_info = client_info;
        this.execute_date_time = execute_date_time;
        this.access_type = access_type;
        this.userList = userList;
        this.userId = userId;


    }
    public List<AccessHistory> getAccessHistories() {
        return accessHistories;
    }

    public void setAccessHistories(List<AccessHistory> accessHistories) {
        this.accessHistories = accessHistories;
    }

    /*User*/
    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

/*AccessHistory*/

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }


    public String getExecute_person() {
        return execute_person;
    }

    public void setExecute_person(String execute_person) {
        this.execute_person = execute_person;
    }

    public String getClient_info() {
        return client_info;
    }

    public void setClient_info(String client_info) {
        this.client_info = client_info;
    }

    public String getExecute_date_time() {
        return execute_date_time;
    }

    public void setExecute_date_time(String execute_date_time) {
        this.execute_date_time = execute_date_time;
    }

    public Integer getAccess_type() {
        return access_type;
    }

    public void setAccess_type(Integer access_type) {
        this.access_type = access_type;
    }

    public String getNotaryDateFromFilter() {
        return notaryDateFromFilter;
    }

    public void setNotaryDateFromFilter(String notaryDateFromFilter) {
        this.notaryDateFromFilter = notaryDateFromFilter;
    }

    public String getNotaryDateToFilter() {
        return notaryDateToFilter;
    }

    public void setNotaryDateToFilter(String notaryDateToFilter) {
        this.notaryDateToFilter = notaryDateToFilter;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }


    public String getOrderString(){
        String whereString ="where 1=1" ;
        String userString ="";
        String accessString="";
        String executeString ="";
        String orderBy = " ORDER BY execute_date_time DESC";

        if(userId !=null && !userId.equals("")){
            userString += " and usid = '"+userId+"' ";
        }
        if(access_type !=null && !access_type.equals("")){
            accessString = " and  access_type = '"+access_type+"' ";
        }
        if(timeType !=null && !timeType.equals("")){
            executeString = " and  execute_date_time >= '"+notaryDateFromFilter+"' and execute_date_time <= '"+notaryDateToFilter+"'  ";
        }
        String query = whereString +userString + accessString + executeString + orderBy;
        return query;
    }
    public String getTimeType() {
        return timeType;
    }

    public void setTimeType(String timeType) {
        this.timeType = timeType;
    }
    public void createFromToDate(){
        if(this.timeType == null || this.timeType.equals("")){
            this.timeType = "03";
        }
        if(timeType.equals("01")){
            this.notaryDateFromFilter = TimeUtil.toTimestamp(true,TimeUtil.getDay(new Date())).toString();
            this.notaryDateToFilter = TimeUtil.toTimestamp(false,TimeUtil.getDay(new Date())).toString();

            //fromDate = TimeUtil.convertTimeStampToString(TimeUtil.toTimestamp(true,TimeUtil.getDay(new Date())));
            //toDate = TimeUtil.convertTimeStampToString(TimeUtil.toTimestamp(false,TimeUtil.getDay(new Date())));
        } else if(timeType.equals("02")){
            this.notaryDateFromFilter = TimeUtil.toTimestamp(true,TimeUtil.getDateByFirstDayOfWeek(new Date())).toString();
            this.notaryDateToFilter = TimeUtil.toTimestamp(false,TimeUtil.getDateByLastDayOfWeek(new Date())).toString();

            //fromDate = TimeUtil.convertTimeStampToString(TimeUtil.toTimestamp(true,TimeUtil.getDateByFirstDayOfWeek(new Date())));
            //toDate =  TimeUtil.convertTimeStampToString(TimeUtil.toTimestamp(false,TimeUtil.getDateByLastDayOfWeek(new Date())));
        } else if(timeType.equals("03")){
            this.notaryDateFromFilter = TimeUtil.toTimestamp(true,TimeUtil.getDateByFirstDayOfMonth(new Date())).toString();
            this.notaryDateToFilter = TimeUtil.toTimestamp(false,TimeUtil.getDateByLastDayOfMonth(new Date())).toString();

            //fromDate = TimeUtil.convertTimeStampToString(TimeUtil.toTimestamp(true,TimeUtil.getDateByFirstDayOfMonth(new Date())));
            //toDate =  TimeUtil.convertTimeStampToString(TimeUtil.toTimestamp(false,TimeUtil.getDateByLastDayOfMonth(new Date())));
        } else if(timeType.equals("04")){
            this.notaryDateFromFilter = TimeUtil.toTimestamp(true,TimeUtil.getDateByFirstDayOfYear(new Date())).toString();
            this.notaryDateToFilter = TimeUtil.toTimestamp(false,TimeUtil.getDateByLastDayOfYear(new Date())).toString();

            //fromDate = TimeUtil.convertTimeStampToString(TimeUtil.toTimestamp(true,TimeUtil.getDateByFirstDayOfYear(new Date())));
            //toDate =  TimeUtil.convertTimeStampToString(TimeUtil.toTimestamp(false,TimeUtil.getDateByLastDayOfYear(new Date())));
        } else if(timeType.equals("05")){
            this.notaryDateFromFilter = TimeUtil.toTimestamp(true,fromDate).toString();
            this.notaryDateToFilter = TimeUtil.toTimestamp(false,toDate).toString();;
        }
    }

    public String getToDate(){return toDate; }

    public void setToDate(String toDate){ this.toDate = toDate;}

    public String getFromDate(){return fromDate; }

    public void setFromDate(String fromDate){ this.fromDate = fromDate;}

}

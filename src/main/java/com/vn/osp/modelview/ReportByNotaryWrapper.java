package com.vn.osp.modelview;

import com.vn.osp.common.util.TimeUtil;

import java.util.Date;
import java.util.List;

/**
 * Created by minh on 5/18/2017.
 */
public class ReportByNotaryWrapper {
    public static final String SESSION_KEY = "ReportByNotaryPerson";

    private List<ReportByNotaryPerson> reportByNotaryPersons;

    private int total;
    private int page;
    private int totalPage;
    private List<User> users;
    private List<UserByRoleList> userByRoleLists;

    private String notaryDateFromFilter;
    private String notaryDateToFilter;
    private String notaryPersonFilter;
    private String notary_person;


    private String timeType;
    private String toDate;
    private String fromDate;

    private String printFromDate;
    private String printToDate;


    public ReportByNotaryWrapper() {
    }

    public String getPrintFromDate() {
        return printFromDate;
    }

    public void setPrintFromDate(String printFromDate) {
        this.printFromDate = printFromDate;
    }

    public String getPrintToDate() {
        return printToDate;
    }

    public void setPrintToDate(String printToDate) {
        this.printToDate = printToDate;
    }

    public List<ReportByNotaryPerson> getReportByNotaryPersons() {
        return reportByNotaryPersons;
    }

    public void setReportByNotaryPersons(List<ReportByNotaryPerson> reportByNotaryPersons) {
        this.reportByNotaryPersons = reportByNotaryPersons;
    }

    public List<UserByRoleList> getUserByRoleLists() {
        return userByRoleLists;
    }

    public void setUserByRoleLists(List<UserByRoleList> userByRoleLists) {
        this.userByRoleLists = userByRoleLists;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
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

    public String getNotaryPersonFilter() {
        return notaryPersonFilter;
    }

    public void setNotaryPersonFilter(String notaryPersonFilter) {
        this.notaryPersonFilter = notaryPersonFilter;
    }

    public String getNotary_person() {
        return notary_person;
    }

    public void setNotary_person(String notary_person) {
        this.notary_person = notary_person;
    }

    public String getTimeType() {
        return timeType;
    }

    public void setTimeType(String timeType) {
        this.timeType = timeType;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public String orderFilter(){
        String whereString ="  where 1=1 ";
        String notaryFilter = "";
        String senderDate ="";
        String orderBy=" ORDER BY notary_date DESC, contract_number DESC ";
        if (notaryPersonFilter!= null && !notaryPersonFilter.equals("")) {
            notaryFilter += " and nc.notary_id = '" + notaryPersonFilter + "'  ";
        }

        if (timeType != null && !timeType.equals("")) {
            senderDate = " and  notary_date BETWEEN '" + notaryDateFromFilter + "' and '" + notaryDateToFilter + "'";
        }


        String query = whereString + notaryFilter  + senderDate +orderBy  ;

        return query;


    }

    public void createFromToDate(){
        if(this.timeType == null || this.timeType.equals("")){
            this.timeType = "03";
        }
        if(timeType.equals("01")){
            this.notaryDateFromFilter = TimeUtil.toTimestamp(true,TimeUtil.getDay(new Date())).toString();
            this.notaryDateToFilter = TimeUtil.toTimestamp(false,TimeUtil.getDay(new Date())).toString();

            /*fromDate = TimeUtil.convertTimeStampToString(TimeUtil.toTimestamp(true,TimeUtil.getDay(new Date())));
            toDate = TimeUtil.convertTimeStampToString(TimeUtil.toTimestamp(false,TimeUtil.getDay(new Date())));*/
            printFromDate = TimeUtil.convertTimeStampToString(TimeUtil.toTimestamp(true,TimeUtil.getDay(new Date())));
            printToDate = TimeUtil.convertTimeStampToString(TimeUtil.toTimestamp(false,TimeUtil.getDay(new Date())));
        } else if(timeType.equals("02")){
            this.notaryDateFromFilter = TimeUtil.toTimestamp(true,TimeUtil.getDateByFirstDayOfWeek(new Date())).toString();
            this.notaryDateToFilter = TimeUtil.toTimestamp(false,TimeUtil.getDateByLastDayOfWeek(new Date())).toString();

            /*fromDate = TimeUtil.convertTimeStampToString(TimeUtil.toTimestamp(true,TimeUtil.getDateByFirstDayOfWeek(new Date())));
            toDate =  TimeUtil.convertTimeStampToString(TimeUtil.toTimestamp(false,TimeUtil.getDateByLastDayOfWeek(new Date())));*/
            printFromDate = TimeUtil.convertTimeStampToString(TimeUtil.toTimestamp(true,TimeUtil.getDateByFirstDayOfWeek(new Date())));
            printToDate =  TimeUtil.convertTimeStampToString(TimeUtil.toTimestamp(false,TimeUtil.getDateByLastDayOfWeek(new Date())));
        } else if(timeType.equals("03")){
            this.notaryDateFromFilter = TimeUtil.toTimestamp(true,TimeUtil.getDateByFirstDayOfMonth(new Date())).toString();
            this.notaryDateToFilter = TimeUtil.toTimestamp(false,TimeUtil.getDateByLastDayOfMonth(new Date())).toString();

            /*fromDate = TimeUtil.convertTimeStampToString(TimeUtil.toTimestamp(true,TimeUtil.getDateByFirstDayOfMonth(new Date())));
            toDate =  TimeUtil.convertTimeStampToString(TimeUtil.toTimestamp(false,TimeUtil.getDateByLastDayOfMonth(new Date())));*/

            printFromDate = TimeUtil.convertTimeStampToString(TimeUtil.toTimestamp(true,TimeUtil.getDateByFirstDayOfMonth(new Date())));
            printToDate =  TimeUtil.convertTimeStampToString(TimeUtil.toTimestamp(false,TimeUtil.getDateByLastDayOfMonth(new Date())));
        } else if(timeType.equals("04")){
            this.notaryDateFromFilter = TimeUtil.toTimestamp(true,TimeUtil.getDateByFirstDayOfYear(new Date())).toString();
            this.notaryDateToFilter = TimeUtil.toTimestamp(false,TimeUtil.getDateByLastDayOfYear(new Date())).toString();

            /*fromDate = TimeUtil.convertTimeStampToString(TimeUtil.toTimestamp(true,TimeUtil.getDateByFirstDayOfYear(new Date())));
            toDate =  TimeUtil.convertTimeStampToString(TimeUtil.toTimestamp(false,TimeUtil.getDateByLastDayOfYear(new Date())));*/
            printFromDate = TimeUtil.convertTimeStampToString(TimeUtil.toTimestamp(true,TimeUtil.getDateByFirstDayOfYear(new Date())));
            printToDate =  TimeUtil.convertTimeStampToString(TimeUtil.toTimestamp(false,TimeUtil.getDateByLastDayOfYear(new Date())));
        } else if(timeType.equals("05")){
            this.notaryDateFromFilter = TimeUtil.toTimestamp(true,fromDate).toString();
            this.notaryDateToFilter = TimeUtil.toTimestamp(false,toDate).toString();

            printFromDate = fromDate;
            printToDate = toDate;
        }
    }

}

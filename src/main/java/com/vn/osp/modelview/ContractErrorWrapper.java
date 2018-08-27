package com.vn.osp.modelview;

import com.vn.osp.common.util.TimeUtil;

import java.util.Date;
import java.util.List;

/**
 * Created by minh on 5/22/2017.
 */
public class ContractErrorWrapper {
    public static final String SESSION_KEY = "ContractError";

    private List<ContractError> contractErrors;

    private int total;
    private int page;
    private int totalPage;

    private List<UserByRoleList> notaryName;
    private List<UserByRoleList> drafterName;

    private String notaryDateFromFilter;
    private String notaryDateToFilter;
    private String notaryPersonFilter;
    private String drafterNameFilter;
    private String notary_person;


    private String timeType;
    private String toDate;
    private String fromDate;

    public ContractErrorWrapper() {
    }

    public List<ContractError> getContractErrors() {
        return contractErrors;
    }

    public void setContractErrors(List<ContractError> contractErrors) {
        this.contractErrors = contractErrors;
    }

    public List<UserByRoleList> getNotaryName() {
        return notaryName;
    }

    public void setNotaryName(List<UserByRoleList> notaryName) {
        this.notaryName = notaryName;
    }

    public List<UserByRoleList> getDrafterName() {
        return drafterName;
    }

    public void setDrafterName(List<UserByRoleList> drafterName) {
        this.drafterName = drafterName;
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

    public String getDrafterNameFilter() {
        return drafterNameFilter;
    }

    public void setDrafterNameFilter(String drafterNameFilter) {
        this.drafterNameFilter = drafterNameFilter;
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

    public String orderFilter(){
        String whereString ="  where nc.error_status = true ";
        String notaryFilter = "";
        String drafterFilter = "";
        String senderDate ="";

        if (notaryPersonFilter!= null && !notaryPersonFilter.equals("")) {
            notaryFilter += " and nc.notary_id = '" + notaryPersonFilter + "'  ";
        }

        if (drafterNameFilter!= null && !drafterNameFilter.equals("")) {
            drafterFilter += " and nc.drafter_id = '" + drafterNameFilter+ "'  ";
        }

        if (timeType != null && !timeType.equals("")) {
            senderDate = " and  nc.notary_date >= '" + notaryDateFromFilter + "' and nc.notary_date <= '" + notaryDateToFilter + "'  ";
        }


        String query = whereString + notaryFilter + drafterFilter  + senderDate   ;

        return query;


    }

    public void createFromToDate(){
        if(this.timeType == null || this.timeType.equals("")){
            this.timeType = "03";
        }
        if(timeType.equals("01")){
            this.notaryDateFromFilter = TimeUtil.toTimestamp(true,TimeUtil.getDay(new Date())).toString();
            this.notaryDateToFilter = TimeUtil.toTimestamp(false,TimeUtil.getDay(new Date())).toString();

            fromDate = TimeUtil.convertTimeStampToString(TimeUtil.toTimestamp(true,TimeUtil.getDay(new Date())));
            toDate = TimeUtil.convertTimeStampToString(TimeUtil.toTimestamp(false,TimeUtil.getDay(new Date())));
        } else if(timeType.equals("02")){
            this.notaryDateFromFilter = TimeUtil.toTimestamp(true,TimeUtil.getDateByFirstDayOfWeek(new Date())).toString();
            this.notaryDateToFilter = TimeUtil.toTimestamp(false,TimeUtil.getDateByLastDayOfWeek(new Date())).toString();

            fromDate = TimeUtil.convertTimeStampToString(TimeUtil.toTimestamp(true,TimeUtil.getDateByFirstDayOfWeek(new Date())));
            toDate =  TimeUtil.convertTimeStampToString(TimeUtil.toTimestamp(false,TimeUtil.getDateByLastDayOfWeek(new Date())));
        } else if(timeType.equals("03")){
            this.notaryDateFromFilter = TimeUtil.toTimestamp(true,TimeUtil.getDateByFirstDayOfMonth(new Date())).toString();
            this.notaryDateToFilter = TimeUtil.toTimestamp(false,TimeUtil.getDateByLastDayOfMonth(new Date())).toString();

            fromDate = TimeUtil.convertTimeStampToString(TimeUtil.toTimestamp(true,TimeUtil.getDateByFirstDayOfMonth(new Date())));
            toDate =  TimeUtil.convertTimeStampToString(TimeUtil.toTimestamp(false,TimeUtil.getDateByLastDayOfMonth(new Date())));
        } else if(timeType.equals("04")){
            this.notaryDateFromFilter = TimeUtil.toTimestamp(true,TimeUtil.getDateByFirstDayOfYear(new Date())).toString();
            this.notaryDateToFilter = TimeUtil.toTimestamp(false,TimeUtil.getDateByLastDayOfYear(new Date())).toString();

            fromDate = TimeUtil.convertTimeStampToString(TimeUtil.toTimestamp(true,TimeUtil.getDateByFirstDayOfYear(new Date())));
            toDate =  TimeUtil.convertTimeStampToString(TimeUtil.toTimestamp(false,TimeUtil.getDateByLastDayOfYear(new Date())));
        } else if(timeType.equals("05")){
            this.notaryDateFromFilter = TimeUtil.toTimestamp(true,fromDate).toString();
            this.notaryDateToFilter = TimeUtil.toTimestamp(false,toDate).toString();;
        }
    }

}

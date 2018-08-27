package com.vn.osp.modelview;

import com.vn.osp.common.util.TimeUtil;

import java.util.Date;
import java.util.List;

/**
 * Created by minh on 2/28/2017.
 */
public class ReportByBankForm {

    public static final String SESSION_KEY = "ReportByBank";

    private List<ReportByBankDetail> reportByBankDetails;
    private List<Bank> bankList;

    private int total;
    private int page;
    private int totalPage;

    private int totalDetail;
    private int pageDetail;
    private int totalPageDetail;


    private String notaryDateFromFilter;
    private String notaryDateToFilter;
    private String bankFilter;
    private String authenticationFilter;

    private String timeType;
    private String toDate;
    private String fromDate;

    private String printFromDate;
    private String printToDate;


    public ReportByBankForm() {
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

    public List<ReportByBankDetail> getReportByBankDetails() {
        return reportByBankDetails;
    }

    public void setReportByBankDetails(List<ReportByBankDetail> reportByBankDetails) {
        this.reportByBankDetails = reportByBankDetails;
    }


    public List<Bank> getBankList() {
        return bankList;
    }

    public void setBankList(List<Bank> bankList) {
        this.bankList = bankList;
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

    public String getBankFilter() {
        return bankFilter;
    }

    public void setBankFilter(String bankFilter) {
        this.bankFilter = bankFilter;
    }

    public String getAuthenticationFilter() {
        return authenticationFilter;
    }

    public void setAuthenticationFilter(String authenticationFilter) {
        this.authenticationFilter = authenticationFilter;
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

    public int getTotalDetail() {
        return totalDetail;
    }

    public void setTotalDetail(int totalDetail) {
        this.totalDetail = totalDetail;
    }

    public int getPageDetail() {
        return pageDetail;
    }

    public void setPageDetail(int pageDetail) {
        this.pageDetail = pageDetail;
    }

    public int getTotalPageDetail() {
        return totalPageDetail;
    }

    public void setTotalPageDetail(int totalPageDetail) {
        this.totalPageDetail = totalPageDetail;
    }

    public String getOrderString(){

        String whereString ="  where 1=1 ";
        String bankString = "";
        String authenticationString="";
        String senderDate="";
        String orderBy=" ORDER BY notary_date DESC,contract_number DESC ";
        if (bankFilter != null && !bankFilter.equals("")) {
            bankString += " and bank_code = '" + bankFilter + "'  ";
        }

        if (timeType != null && !timeType.equals("")) {
            senderDate = " and  notary_date >= '" + notaryDateFromFilter + "' and notary_date <= '" + notaryDateToFilter + "'  ";
        }


        String query = whereString + bankString + authenticationString + senderDate +orderBy  ;
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

            printFromDate = TimeUtil.convertTimeStampToString(TimeUtil.toTimestamp(true,TimeUtil.getDay(new Date())));
            printToDate = TimeUtil.convertTimeStampToString(TimeUtil.toTimestamp(false,TimeUtil.getDay(new Date())));
        } else if(timeType.equals("02")){
            this.notaryDateFromFilter = TimeUtil.toTimestamp(true,TimeUtil.getDateByFirstDayOfWeek(new Date())).toString();
            this.notaryDateToFilter = TimeUtil.toTimestamp(false,TimeUtil.getDateByLastDayOfWeek(new Date())).toString();

            fromDate = TimeUtil.convertTimeStampToString(TimeUtil.toTimestamp(true,TimeUtil.getDateByFirstDayOfWeek(new Date())));
            toDate =  TimeUtil.convertTimeStampToString(TimeUtil.toTimestamp(false,TimeUtil.getDateByLastDayOfWeek(new Date())));
            printFromDate = TimeUtil.convertTimeStampToString(TimeUtil.toTimestamp(true,TimeUtil.getDateByFirstDayOfWeek(new Date())));
            printToDate =  TimeUtil.convertTimeStampToString(TimeUtil.toTimestamp(false,TimeUtil.getDateByLastDayOfWeek(new Date())));
        } else if(timeType.equals("03")){
            this.notaryDateFromFilter = TimeUtil.toTimestamp(true,TimeUtil.getDateByFirstDayOfMonth(new Date())).toString();
            this.notaryDateToFilter = TimeUtil.toTimestamp(false,TimeUtil.getDateByLastDayOfMonth(new Date())).toString();

         fromDate = TimeUtil.convertTimeStampToString(TimeUtil.toTimestamp(true,TimeUtil.getDateByFirstDayOfMonth(new Date())));
            toDate =  TimeUtil.convertTimeStampToString(TimeUtil.toTimestamp(false,TimeUtil.getDateByLastDayOfMonth(new Date())));

            printFromDate = TimeUtil.convertTimeStampToString(TimeUtil.toTimestamp(true,TimeUtil.getDateByFirstDayOfMonth(new Date())));
            printToDate =  TimeUtil.convertTimeStampToString(TimeUtil.toTimestamp(false,TimeUtil.getDateByLastDayOfMonth(new Date())));
        } else if(timeType.equals("04")){
            this.notaryDateFromFilter = TimeUtil.toTimestamp(true,TimeUtil.getDateByFirstDayOfYear(new Date())).toString();
            this.notaryDateToFilter = TimeUtil.toTimestamp(false,TimeUtil.getDateByLastDayOfYear(new Date())).toString();

            fromDate = TimeUtil.convertTimeStampToString(TimeUtil.toTimestamp(true,TimeUtil.getDateByFirstDayOfYear(new Date())));
            toDate =  TimeUtil.convertTimeStampToString(TimeUtil.toTimestamp(false,TimeUtil.getDateByLastDayOfYear(new Date())));
            printFromDate = TimeUtil.convertTimeStampToString(TimeUtil.toTimestamp(true,TimeUtil.getDateByFirstDayOfYear(new Date())));
            printToDate =  TimeUtil.convertTimeStampToString(TimeUtil.toTimestamp(false,TimeUtil.getDateByLastDayOfYear(new Date())));
        } else if(timeType.equals("05")){
            this.notaryDateFromFilter = TimeUtil.toTimestamp(true,fromDate).toString();
            this.notaryDateToFilter = TimeUtil.toTimestamp(false,toDate).toString();;

            printFromDate = fromDate;
            printToDate = toDate;
        }
    }


}

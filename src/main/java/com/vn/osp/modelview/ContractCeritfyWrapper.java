package com.vn.osp.modelview;

import com.vn.osp.common.util.TimeUtil;

import java.util.Date;
import java.util.List;

/**
 * Created by minh on 5/24/2017.
 */
public class ContractCeritfyWrapper {
    public static String SESSION_KEY = "ContractCertify";
    private List<ContractCertify> contractCertifies;
    private int total;
    private String notaryDateFromFilter;
    private String notaryDateToFilter;
    private int page;
    private int totalPage;
    private String contractKind;

    private String toDate;
    private String fromDate;
    private String timeType;

    private List<ContractKind> contractKinds;
    private String cbChild;
    private String sort;

    public ContractCeritfyWrapper() {
    }

    public List<ContractCertify> getContractCertifies() {
        return contractCertifies;
    }

    public void setContractCertifies(List<ContractCertify> contractCertifies) {
        this.contractCertifies = contractCertifies;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
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

    public String getToDate(){return toDate; }

    public void setToDate(String toDate){ this.toDate = toDate;}

    public String getContractKind(){return contractKind; }

    public void setContractKind(String contractKind){ this.contractKind = contractKind;}

    public String getFromDate(){return fromDate; }

    public void setFromDate(String fromDate){ this.fromDate = fromDate;}

    public List<ContractKind> getContractKinds() {
        return contractKinds;
    }

    public void setContractKinds(List<ContractKind> contractKinds) {
        this.contractKinds = contractKinds;
    }

    public String getCbChild() {
        return cbChild;
    }

    public void setCbChild(String cbChild) {
        this.cbChild = cbChild;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String orderFilter(){
        String whereString ="  where 1=1";
        String cbChildFilter ;
        String drafterFilter = "";
        String sortFilter ="";
        String senderDate ="";
        String[] arr = cbChild.split(",");


        if(sort.equals("1")) {sortFilter = " order by contract_number DESC";}
        if(sort.equals("2")) {sortFilter = " order by notary_date DESC, contract_number DESC";}

        if (timeType != null && !timeType.equals("")) {
            senderDate = " and  nc.notary_date >= '" + notaryDateFromFilter + "' and nc.notary_date <= '" + notaryDateToFilter + "'  ";
        }
        cbChildFilter = " and ( 1=0 ";
        for (int i = 0; i < arr.length; i++) {
            cbChildFilter += "or nct.code =" + arr[i] + " ";
        }

        cbChildFilter += ") ";


        String query = whereString + senderDate + cbChildFilter + sortFilter    ;

        return query;


    }


}

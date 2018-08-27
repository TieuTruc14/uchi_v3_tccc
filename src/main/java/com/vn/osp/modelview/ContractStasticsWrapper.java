package com.vn.osp.modelview;

import com.vn.osp.common.util.TimeUtil;
import org.json.JSONObject;

import java.util.Date;
import java.util.List;

/**
 * Created by minh on 5/29/2017.
 */
public class ContractStasticsWrapper {
    public static String SESSION_KEY = "ContractStastics";
    private List<ContractStastics> contractStasticsDrafter;
    private List<ContractStastics> contractStasticsNotary;
    private List<ContractStasticsBank> contractStasticsBanks;

    private String toDate;
    private String fromDate;
    private String timeType;
    private String notaryDateFromFilter;
    private String notaryDateToFilter;

    Integer totalContractByNotary ;
    Integer totalErrorContractByNotary ;
    Integer totalContractByDrafter ;
    Integer totalErrorContractByDrafter ;
    Integer totalContractBank;

    private String printFromDate;
    private String printToDate;

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

    public List<ContractStastics> getContractStasticsDrafter() {
        return contractStasticsDrafter;
    }

    public void setContractStasticsDrafter(List<ContractStastics> contractStasticsDrafter) {
        this.contractStasticsDrafter = contractStasticsDrafter;
    }

    public List<ContractStastics> getContractStasticsNotary() {
        return contractStasticsNotary;
    }

    public void setContractStasticsNotary(List<ContractStastics> contractStasticsNotary) {
        this.contractStasticsNotary = contractStasticsNotary;
    }

    public List<ContractStasticsBank> getContractStasticsBanks() {
        return contractStasticsBanks;
    }

    public void setContractStasticsBanks(List<ContractStasticsBank> contractStasticsBanks) {
        this.contractStasticsBanks = contractStasticsBanks;
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

    public String getTimeType() {
        return timeType;
    }

    public void setTimeType(String timeType) {
        this.timeType = timeType;
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

    public Integer getTotalContractByNotary() {
        return totalContractByNotary;
    }

    public void setTotalContractByNotary(Integer totalContractByNotary) {
        this.totalContractByNotary = totalContractByNotary;
    }

    public Integer getTotalErrorContractByNotary() {
        return totalErrorContractByNotary;
    }

    public void setTotalErrorContractByNotary(Integer totalErrorContractByNotary) {
        this.totalErrorContractByNotary = totalErrorContractByNotary;
    }

    public Integer getTotalContractByDrafter() {
        return totalContractByDrafter;
    }

    public void setTotalContractByDrafter(Integer totalContractByDrafter) {
        this.totalContractByDrafter = totalContractByDrafter;
    }

    public Integer getTotalErrorContractByDrafter() {
        return totalErrorContractByDrafter;
    }

    public void setTotalErrorContractByDrafter(Integer totalErrorContractByDrafter) {
        this.totalErrorContractByDrafter = totalErrorContractByDrafter;
    }

    public Integer getTotalContractBank() {
        return totalContractBank;
    }

    public void setTotalContractBank(Integer totalContractBank) {
        this.totalContractBank = totalContractBank;
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
    public JSONObject generateJsonObject(){
        JSONObject obj = new JSONObject();

        try {
            obj.put("notaryDateFromFilter", notaryDateFromFilter);
            obj.put("notaryDateToFilter", notaryDateToFilter);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }


}

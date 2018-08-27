package com.vn.osp.modelview;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vn.osp.common.util.TimeUtil;
import org.json.JSONObject;

import java.util.Date;
import java.util.List;

/**
 * Created by tranv on 12/28/2016.
 */

public class ReportByTT20List {
    @JsonIgnore
    public static final String SESSION_KEY = "ReportByTT20List";

    private String notaryDateFromFilter;

    private String notaryDateToFilter;

    private String timeType;

    private String fromDate;

    private String toDate;

    private int numberOfNotaryPerson;
    private int numberOfTotalContract;
    private int numberOfContractLand;
    private int numberOfContractOther;
    private int numberOfContractDanSu;
    private int numberOfThuaKe;
    private int numberOfOther;
    private long tongPhiCongChung;
    private int tongNopNganSach;

    private String printFromDate;
    private String printToDate;

    public ReportByTT20List() {
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

    public String getNotaryDateFromFilter() {
        return notaryDateFromFilter;
    }

    public void setNotaryDateFromFilter(String notaryDateFromFilter) {
        this.notaryDateFromFilter = notaryDateFromFilter;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
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

    public int getNumberOfNotaryPerson() {
        return numberOfNotaryPerson;
    }

    public void setNumberOfNotaryPerson(int numberOfNotaryPerson) {
        this.numberOfNotaryPerson = numberOfNotaryPerson;
    }

    public int getNumberOfTotalContract() {
        return numberOfTotalContract;
    }

    public void setNumberOfTotalContract(int numberOfTotalContract) {
        this.numberOfTotalContract = numberOfTotalContract;
    }

    public int getNumberOfContractLand() {
        return numberOfContractLand;
    }

    public void setNumberOfContractLand(int numberOfContractLand) {
        this.numberOfContractLand = numberOfContractLand;
    }

    public int getNumberOfContractOther() {
        return numberOfContractOther;
    }

    public void setNumberOfContractOther(int numberOfContractOther) {
        this.numberOfContractOther = numberOfContractOther;
    }

    public int getNumberOfContractDanSu() {
        return numberOfContractDanSu;
    }

    public void setNumberOfContractDanSu(int numberOfContractDanSu) {
        this.numberOfContractDanSu = numberOfContractDanSu;
    }

    public int getNumberOfThuaKe() {
        return numberOfThuaKe;
    }

    public void setNumberOfThuaKe(int numberOfThuaKe) {
        this.numberOfThuaKe = numberOfThuaKe;
    }

    public int getNumberOfOther() {
        return numberOfOther;
    }

    public void setNumberOfOther(int numberOfOther) {
        this.numberOfOther = numberOfOther;
    }

    public long getTongPhiCongChung() {
        return tongPhiCongChung;
    }

    public void setTongPhiCongChung(long tongPhiCongChung) {
        this.tongPhiCongChung = tongPhiCongChung;
    }

    public int getTongNopNganSach() {
        return tongNopNganSach;
    }

    public void setTongNopNganSach(int tongNopNganSach) {
        this.tongNopNganSach = tongNopNganSach;
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

    public String generateJson(){
        JSONObject obj = new JSONObject();
        ObjectMapper mapper = new ObjectMapper();
        try {
            ////System.out.println(mapper.writeValueAsString(this));
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}

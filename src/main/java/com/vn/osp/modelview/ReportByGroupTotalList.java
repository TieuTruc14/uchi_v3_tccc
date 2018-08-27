package com.vn.osp.modelview;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vn.osp.common.util.TimeUtil;
import org.json.JSONObject;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

/**
 * Created by tranv on 12/28/2016.
 */

public class ReportByGroupTotalList {
    @JsonIgnore
    public static final String SESSION_KEY = "ReportByGroupTotalList";
    @JsonIgnore
    private List<ReportByGroupTotal> reportByGroupTotals;
    @JsonProperty
    private long total;
    @JsonProperty
    private String notaryDateFromFilter;
    @JsonProperty
    private String notaryDateToFilter;
    @JsonProperty
    private String timeType;
    @JsonProperty
    private int page;
    @JsonProperty
    private String fromDate;
    @JsonProperty
    private String toDate;
    @JsonProperty
    private int kindId;
    @JsonProperty
    private List<TransactionProperty> contractList;
    @JsonProperty
    private int contractListNumber;
    @JsonProperty
    private int totalPage;

    @JsonIgnore
    private List<ContractKind> contractKinds;
    @JsonIgnore
    private List<ContractTemplate> contractTemplates;
    @JsonIgnore
    private List<NotaryOffice> notaryOffices;

    @JsonProperty
    private String nhomHD;
    @JsonProperty
    private String tenHD;
    @JsonProperty
    private String codeTemplate;
    @JsonProperty
    private String source;
    @JsonProperty
    private String donVi;
    @JsonIgnore
    private String tenHDChiTiet;
    @JsonIgnore
    private String nhomHDChiTiet;
    @JsonIgnore
    private String labelNhomHD;
    @JsonIgnore
    private String labelTenHD;
    @JsonIgnore
    private String labelSource;
    @JsonIgnore
    private String labelDonVi;
    @JsonIgnore
    private String printToDate;
    @JsonIgnore
    private String printFromDate;

    public String getPrintToDate() {
        return printToDate;
    }

    public void setPrintToDate(String printToDate) {
        this.printToDate = printToDate;
    }

    public String getPrintFromDate() {
        return printFromDate;
    }

    public void setPrintFromDate(String printFromDate) {
        this.printFromDate = printFromDate;
    }

    public String getCodeTemplate() {
        return codeTemplate;
    }

    public void setCodeTemplate(String codeTemplate) {
        this.codeTemplate = codeTemplate;
    }

    public String getNhomHDChiTiet() {
        return nhomHDChiTiet;
    }

    public void setNhomHDChiTiet(String nhomHDChiTiet) {
        this.nhomHDChiTiet = nhomHDChiTiet;
    }

    public String getTenHDChiTiet() {
        return tenHDChiTiet;
    }

    public void setTenHDChiTiet(String tenHDChiTiet) {
        this.tenHDChiTiet = tenHDChiTiet;
    }

    public ReportByGroupTotalList() {
    }

    public List<ReportByGroupTotal> getReportByGroupTotals() {
        return reportByGroupTotals;
    }

    public void setReportByGroupTotals(List<ReportByGroupTotal> reportByGroupTotals) {
        this.reportByGroupTotals = reportByGroupTotals;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
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
    public void createFromToDate(){
        if(this.timeType == null || this.timeType.equals("")){
            this.timeType = "03";
        }
        if(timeType.equals("01")){
            this.notaryDateFromFilter = TimeUtil.toTimestamp(true,TimeUtil.getDay(new Date())).toString();
            this.notaryDateToFilter = TimeUtil.toTimestamp(false,TimeUtil.getDay(new Date())).toString();

            fromDate = TimeUtil.convertTimeStampToString(TimeUtil.toTimestamp(true,TimeUtil.getDay(new Date())));
            toDate = TimeUtil.convertTimeStampToString(TimeUtil.toTimestamp(false,TimeUtil.getDay(new Date())));
            // biến hiện thị
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
            this.notaryDateToFilter = TimeUtil.toTimestamp(false,toDate).toString();

            printFromDate = fromDate;
            printToDate =  toDate;

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

    public int getKindId() {
        return kindId;
    }

    public void setKindId(int kindId) {
        this.kindId = kindId;
    }

    public List<TransactionProperty> getContractList() {
        return contractList;
    }

    public void setContractList(List<TransactionProperty> contractList) {
        this.contractList = contractList;
    }

    public int getContractListNumber() {
        return contractListNumber;
    }

    public void setContractListNumber(int contractListNumber) {
        this.contractListNumber = contractListNumber;
    }

    public List<ContractKind> getContractKinds() {
        return contractKinds;
    }

    public void setContractKinds(List<ContractKind> contractKinds) {
        this.contractKinds = contractKinds;
    }

    public List<ContractTemplate> getContractTemplates() {
        return contractTemplates;
    }

    public void setContractTemplates(List<ContractTemplate> contractTemplates) {
        this.contractTemplates = contractTemplates;
    }

    public String getNhomHD() {
        return nhomHD;
    }

    public void setNhomHD(String nhomHD) {
        this.nhomHD = nhomHD;
    }

    public String getTenHD() {
        return tenHD;
    }

    public void setTenHD(String tenHD) {
        this.tenHD = tenHD;
    }

    public String getSource() {
        return source;
    }


    public void setSource(String source) {
        this.source = source;
    }

    public String getDonVi() {
        return donVi;
    }

    public void setDonVi(String donVi) {
        this.donVi = donVi;
    }

    public List<NotaryOffice> getNotaryOffices() {
        return notaryOffices;
    }

    public void setNotaryOffices(List<NotaryOffice> notaryOffices) {
        this.notaryOffices = notaryOffices;
    }

    public String getLabelNhomHD() {
        return labelNhomHD;
    }

    public void setLabelNhomHD(String labelNhomHD) {
        this.labelNhomHD = labelNhomHD;
    }

    public String getLabelTenHD() {
        return labelTenHD;
    }

    public void setLabelTenHD(String labelTenHD) {
        this.labelTenHD = labelTenHD;
    }

    public String getLabelSource() {
        return labelSource;
    }

    public void setLabelSource(String labelSource) {
        this.labelSource = labelSource;
    }

    public String getLabelDonVi() {
        return labelDonVi;
    }

    public void setLabelDonVi(String labelDonVi) {
        this.labelDonVi = labelDonVi;
    }

    public String generateJson(){
        JSONObject obj = new JSONObject();
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}

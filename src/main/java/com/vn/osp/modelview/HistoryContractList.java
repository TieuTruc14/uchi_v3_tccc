package com.vn.osp.modelview;

import java.util.List;

/**
 * Created by minh on 5/5/2017.
 */
public class HistoryContractList {
    private List<HistoryContract> historyContractList;
    private int page;
    private int total;
    private int totalPage;

    private String contractNumber;
    private String contractContent;


    public HistoryContractList() {
    }

    public List<HistoryContract> getHistoryContractList() {
        return historyContractList;
    }

    public void setHistoryContractList(List<HistoryContract> historyContractList) {
        this.historyContractList = historyContractList;
    }
    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public String getContractContent() {
        return contractContent;
    }

    public void setContractContent(String contractContent) {
        this.contractContent = contractContent;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    public String getOrderString(){
        String whereString ="where 1=1" ;
        String contractNumberString ="";
        String contractContentString="";
        String authenticationFilterString ="";
        String orderBy = " ORDER BY execute_date_time DESC ";

        if(contractNumber !=null && !contractNumber.equals("")){
            contractNumberString += " and nch.contract_number like '%" + contractNumber.trim() + "%'";
        }
        if(contractContent !=null && !contractContent.equals("")){
            contractContentString = " and  nch.contract_content like '%" + contractContent.trim() + "%'";
        }
        String query = whereString + contractNumberString + contractContentString + authenticationFilterString + orderBy  ;
        return query;
    }


}

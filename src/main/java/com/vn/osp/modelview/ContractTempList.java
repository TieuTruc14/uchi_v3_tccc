package com.vn.osp.modelview;

import java.util.List;

/**
 * Created by Admin on 7/6/2017.
 */
public class ContractTempList {
    private List<ContractTempListByKindName> contractTempListByKindNames;
    private ContractTemp contractTempDetail;
    private String name;
    private String code;
    private Long active_flg;
    private List<ContractKind> listContractKind;

    private int page;
    private int totalPage;
    private int total;
    private String action_status ;

    public ContractTempList() {
    }

    public ContractTempList(List<ContractTempListByKindName> contractTempListByKindNames, ContractTemp contractTempDetail, String name, String code, Long active_flg, List<ContractKind> listContractKind, int page, int totalPage, int total, String action_status) {
        this.contractTempListByKindNames = contractTempListByKindNames;
        this.contractTempDetail = contractTempDetail;
        this.name = name;
        this.code = code;
        this.active_flg = active_flg;
        this.listContractKind = listContractKind;
        this.page = page;
        this.totalPage = totalPage;
        this.total = total;
        this.action_status = action_status;
    }

    public List<ContractTempListByKindName> getContractTempListByKindNames() {
        return contractTempListByKindNames;
    }

    public void setContractTempListByKindNames(List<ContractTempListByKindName> contractTempListByKindNames) {
        this.contractTempListByKindNames = contractTempListByKindNames;
    }

    public ContractTemp getContractTempDetail() {
        return contractTempDetail;
    }

    public void setContractTempDetail(ContractTemp contractTempDetail) {
        this.contractTempDetail = contractTempDetail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getActive_flg() {
        return active_flg;
    }

    public void setActive_flg(Long active_flg) {
        this.active_flg = active_flg;
    }

    public List<ContractKind> getListContractKind() {
        return listContractKind;
    }

    public void setListContractKind(List<ContractKind> listContractKind) {
        this.listContractKind = listContractKind;
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

    public String getAction_status() {
        return action_status;
    }

    public void setAction_status(String action_status) {
        this.action_status = action_status;
    }

    public String getOrderString(){
        String whereString ="where 1=1" ;
        String orderString1 ="";
        String orderString2 ="";
        String orderString3 ="";
        String orderBy = " ORDER BY nct.name asc";
        if(name!= null && !name.equals("")){

            orderString1 = " and nct.name like '%"+name.trim()+"%'";
        }

        if(code!= null && !code.equals("")){

            orderString2 = " and code like '%"+code.trim()+"%'";
        }
        if(active_flg!= null && !active_flg.equals("")){

            orderString3 = " and active_flg = "+active_flg;
        }

        String query = whereString  + orderString1 + orderString2 + orderString3 + orderBy ;
        return query;
    }
}

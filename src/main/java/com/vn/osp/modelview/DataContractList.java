package com.vn.osp.modelview;

import com.vn.osp.common.util.PagingResult;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TienManh on 5/16/2017.
 */
public class DataContractList {
    //get data from npo_transaction_property
    private List<PagingResult> listContract=new ArrayList<PagingResult>();//syn_status=1
    private List<PagingResult> lstContractNotSyn=new ArrayList<PagingResult>();//Chua dong` bo ---- syn_status=0

    public DataContractList(){}

    public List<PagingResult> getListContract() {
        return listContract;
    }

    public void setListContract(List<PagingResult> listContract) {
        this.listContract = listContract;
    }

    public List<PagingResult> getLstContractNotSyn() {
        return lstContractNotSyn;
    }

    public void setLstContractNotSyn(List<PagingResult> lstContractNotSyn) {
        this.lstContractNotSyn = lstContractNotSyn;
    }
}

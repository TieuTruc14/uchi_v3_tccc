package com.vn.osp.modelview;

import java.util.List;

/**
 * Created by minh on 8/14/2017.
 */
public class ContractHistoryDetail {

    private Long tpid;


    private List<HistoryContract> historyContractList;


    public Long getTpid() {
        return tpid;
    }

    public void setTpid(Long tpid) {
        this.tpid = tpid;
    }

    public List<HistoryContract> getHistoryContractList() {
        return historyContractList;
    }

    public void setHistoryContractList(List<HistoryContract> historyContractList) {
        this.historyContractList = historyContractList;
    }
}

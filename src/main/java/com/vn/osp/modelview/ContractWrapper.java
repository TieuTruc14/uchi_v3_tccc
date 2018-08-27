package com.vn.osp.modelview;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.vn.osp.common.util.PagingResult;

/**
 * Created by TienManh on 5/15/2017.
 */
public class ContractWrapper {
    private PagingResult listContract=new PagingResult();
    private PagingResult listNotSynch=new PagingResult();
    private int defaultTab=1;
    private int pageNumberTab1=1;
    private int pageNumberTab2=1;
    private String status="";//dung khi delete object thi tra lai
    private String json;

    public ContractWrapper() {
    }

    public ContractWrapper(PagingResult listContract, PagingResult listNotSynch) {
        this.listContract = listContract;
        this.listNotSynch = listNotSynch;
    }

    public PagingResult getListContract() {
        return listContract;
    }

    public void setListContract(PagingResult listContract) {
        this.listContract = listContract;
    }

    public PagingResult getListNotSynch() {
        return listNotSynch;
    }

    public void setListNotSynch(PagingResult listNotSynch) {
        this.listNotSynch = listNotSynch;
    }

    public int getDefaultTab() {
        return defaultTab;
    }

    public void setDefaultTab(int defaultTab) {
        this.defaultTab = defaultTab;
    }

    public int getPageNumberTab1() {
        return pageNumberTab1;
    }

    public void setPageNumberTab1(int pageNumberTab1) {
        this.pageNumberTab1 = pageNumberTab1;
    }

    public int getPageNumberTab2() {
        return pageNumberTab2;
    }

    public void setPageNumberTab2(int pageNumberTab2) {
        this.pageNumberTab2 = pageNumberTab2;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

package com.vn.osp.modelview;

/**
 * Created by VIETDUNG on 11/05/2017.
 */
public class ContractName {
    private String tenHD;
    private int total;

    public ContractName() {
    }

    public ContractName(String tenHD, int total) {
        this.tenHD = tenHD;
        this.total = total;
    }

    public String getTenHD() {
        return tenHD;
    }

    public void setTenHD(String tenHD) {
        this.tenHD = tenHD;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}

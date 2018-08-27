package com.vn.osp.modelview;

import java.util.ArrayList;

/**
 * Created by VIETDUNG on 11/05/2017.
 */
public class ReportByGroupTableView {
    private int stt;
    private String nhomHD;
    private ArrayList<ContractName> tenHD;
    private int total;

    public ReportByGroupTableView() {
    }

    public ReportByGroupTableView(int stt, String nhomHD, ArrayList<ContractName> tenHD, int total) {
        this.stt = stt;
        this.nhomHD = nhomHD;
        this.tenHD = tenHD;
        this.total = total;
    }

    public int getStt() {
        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }

    public String getNhomHD() {
        return nhomHD;
    }

    public void setNhomHD(String nhomHD) {
        this.nhomHD = nhomHD;
    }

    public ArrayList<ContractName> getTenHD() {
        return tenHD;
    }

    public void setTenHD(ArrayList<ContractName> tenHD) {
        this.tenHD = tenHD;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}

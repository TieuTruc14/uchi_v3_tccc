package com.vn.osp.modelview;

import java.util.ArrayList;

/**
 * Created by VIETDUNG on 26/04/2017.
 */
public class BackupList {
    private ArrayList<BackupInfo> list;

    private int total;
    private int page;
    private int totalPage;

    private String filename;
    private String cb11;

    public BackupList() {
    }

    public ArrayList<BackupInfo> getList() {
        return list;
    }

    public void setList(ArrayList<BackupInfo> list) {
        this.list = list;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
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

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getCb11() {
        return cb11;
    }

    public void setCb11(String cb11) {
        this.cb11 = cb11;
    }
}

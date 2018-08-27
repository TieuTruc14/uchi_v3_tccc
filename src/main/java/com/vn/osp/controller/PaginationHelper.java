package com.vn.osp.controller;

public class PaginationHelper {

    public int rowPerPage = 20;
    public int totalPage;
    public int currentPage = 1;
    public int totalRecord;

    public PaginationHelper() {

    }

    public PaginationHelper(int totalPage, int currentPage, int totalRecord) {
        this.currentPage = currentPage;
        this.totalPage = totalPage;
        this.totalRecord = totalRecord;
    }

    public int getRowPerPage() {
        return rowPerPage;
    }

    public void setRowPerPage(int rowPerPage) {
        this.rowPerPage = rowPerPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
    }
}
package com.vn.osp.modelview;

import java.util.List;

/**
 * Created by nmha on 3/29/2017.
 */
public class OfficeListForm {

    private List<NotaryOffice> officeList;
    private int listNumber;
    private int totalPage;
    private int page;

    private String titleFilter;

    public OfficeListForm() {
    }

    public List<NotaryOffice> getOfficeList() {
        return officeList;
    }

    public void setOfficeList(List<NotaryOffice> officeList) {
        this.officeList = officeList;
    }

    public int getListNumber() {
        return listNumber;
    }

    public void setListNumber(int listNumber) {
        this.listNumber = listNumber;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getTitleFilter() {
        return titleFilter;
    }

    public void setTitleFilter(String titleFilter) {
        this.titleFilter = titleFilter;
    }
}

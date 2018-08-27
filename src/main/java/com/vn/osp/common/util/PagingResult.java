package com.vn.osp.common.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TienManh on 5/16/2017.
 * Class nay` tro giup van de phan trang hien thi
 */

public class PagingResult {
    private List<?> items = new ArrayList();
    private int rowCount = 0;
    private int numberPerPage = 20;
    private int pageNumber = 1;
    public List<Integer> getPageList() {
        List<Integer> pages = new ArrayList<Integer>();

        int from = pageNumber  - 3;
        int to = pageNumber + 5;
        if (from < 0) {
            to -= from;
            from = 1;
        }

        if (from < 1) {
            from = 1;
        }


        if (to > getPageCount()) {
            to = getPageCount();
        }

        for (int i=from; i<=to; i++ ) {
            pages.add(i);
        }

        return pages;
    }

    public int getPageCount() {
        return (int) (Math.ceil( (double) rowCount / numberPerPage));
    }

    public List<?> getItems() {
        return items;
    }

    public void setItems(List<?> items) {
        this.items = items;
    }

    public int getRowCount() {
        return rowCount;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    public int getNumberPerPage() {
        return numberPerPage;
    }

    public void setNumberPerPage(int numberPerPage) {
        this.numberPerPage = numberPerPage;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }
}

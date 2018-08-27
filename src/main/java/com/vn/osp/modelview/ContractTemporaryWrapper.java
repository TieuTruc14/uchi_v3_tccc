package com.vn.osp.modelview;

import com.vn.osp.common.util.PagingResult;

/**
 * Created by TienManh on 5/27/2017.
 */
public class ContractTemporaryWrapper {
    private PagingResult listChoKy=new PagingResult();
    private PagingResult listDaKy=new PagingResult();
    private PagingResult listDongDau=new PagingResult();
    private PagingResult listChinhSua=new PagingResult();
    private PagingResult listTraVe=new PagingResult();
    private int defaultTab=1;
    private int pageNumberTab1=1;
    private int pageNumberTab2=1;
    private int pageNumberTab3=1;
    private int pageNumberTab4=1;
    private int pageNumberTab5=1;
    private String status="";//dung khi thong bao delete/add/edit/cancel object thi tra lai
    private String json;

    public ContractTemporaryWrapper() {
    }

    public ContractTemporaryWrapper(PagingResult listChoKy, PagingResult listDaKy, PagingResult listDongDau, PagingResult listChinhSua, PagingResult listTraVe) {
        this.listChoKy = listChoKy;
        this.listDaKy = listDaKy;
        this.listDongDau = listDongDau;
        this.listChinhSua = listChinhSua;
        this.listTraVe = listTraVe;
    }

    public PagingResult getListChoKy() {
        return listChoKy;
    }

    public void setListChoKy(PagingResult listChoKy) {
        this.listChoKy = listChoKy;
    }

    public PagingResult getListDaKy() {
        return listDaKy;
    }

    public void setListDaKy(PagingResult listDaKy) {
        this.listDaKy = listDaKy;
    }

    public PagingResult getListDongDau() {
        return listDongDau;
    }

    public void setListDongDau(PagingResult listDongDau) {
        this.listDongDau = listDongDau;
    }

    public PagingResult getListChinhSua() {
        return listChinhSua;
    }

    public void setListChinhSua(PagingResult listChinhSua) {
        this.listChinhSua = listChinhSua;
    }

    public PagingResult getListTraVe() {
        return listTraVe;
    }

    public void setListTraVe(PagingResult listTraVe) {
        this.listTraVe = listTraVe;
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

    public int getPageNumberTab3() {
        return pageNumberTab3;
    }

    public void setPageNumberTab3(int pageNumberTab3) {
        this.pageNumberTab3 = pageNumberTab3;
    }

    public int getPageNumberTab4() {
        return pageNumberTab4;
    }

    public void setPageNumberTab4(int pageNumberTab4) {
        this.pageNumberTab4 = pageNumberTab4;
    }

    public int getPageNumberTab5() {
        return pageNumberTab5;
    }

    public void setPageNumberTab5(int pageNumberTab5) {
        this.pageNumberTab5 = pageNumberTab5;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }
}

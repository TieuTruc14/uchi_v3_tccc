package com.vn.osp.modelview;

import java.util.List;

/**
 * Created by minh on 6/5/2017.
 */
public class ProvinceListForm {
    private List<ProvinceList> provinceList;
    private ProvinceList provinceDetail;
    private int total;
    private int totalPage;
    private int page;

    private String name;
    private String code;
    private String titleFilter;

    public ProvinceListForm() {
    }


    public List<ProvinceList> getProvinceList() {
        return provinceList;
    }

    public void setProvinceList(List<ProvinceList> provinceList) {
        this.provinceList = provinceList;
    }

    public ProvinceList getProvinceDetail() {
        return provinceDetail;
    }

    public void setProvinceDetail(ProvinceList provinceDetail) {
        this.provinceDetail = provinceDetail;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getOrderString(){
        String whereString ="where 1=1" ;
        String orderString1 ="";
        String orderString2 = "";
        String orderBy = " ORDER BY name asc";
        if(name!= null && !name.equals("")){

            orderString1 = " and name like '%"+name.trim()+"%'";
        }

        if(code!= null && !code.equals("")){

            orderString2 = " and code like '%"+code.trim()+"%'";
        }

        String query = whereString  + orderString1 + orderString2 + orderBy ;
        return query;
    }

}

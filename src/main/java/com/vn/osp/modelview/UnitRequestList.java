package com.vn.osp.modelview;

import com.vn.osp.common.util.TimeUtil;

import java.util.Date;
import java.util.List;

/**
 * Created by minh on 2/28/2017.
 */
public class UnitRequestList {


    private List<UnitRequest> selectUnitRequest;

    private int total;
    private int page;
    private int totalPage;

    private String name;
    private Long active_flg;
    private String nameFilter;


    public List<UnitRequest> getSelectUnitRequest() {
        return selectUnitRequest;
    }

    public void setSelectUnitRequest(List<UnitRequest> selectUnitRequest) {
        this.selectUnitRequest = selectUnitRequest;
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

    public String getName(){return name; }

    public void setName(String name){ this.name = name;}

    public Long getActive_flg(){return active_flg;}

    public void setActive_flg(Long active_flg){this.active_flg=active_flg;}

    public String getNameFilter(){return nameFilter; }

    public void setNameFilter(String nameFilter){ this.nameFilter = nameFilter;}

    public String getOrderString(){
        String whereString ="where 1 = 1 " ;
        String titleString ="";

        if(nameFilter !=null && !nameFilter.equals("")){
            whereString  += " and name like '%"+nameFilter+"%' ";
        }
        if(active_flg !=null && !active_flg.equals("")){
            titleString += " and  active_flg = '"+active_flg+"' ";
        }
        String query = whereString + titleString ;
        return query;
    }


}

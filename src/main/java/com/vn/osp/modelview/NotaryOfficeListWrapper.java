package com.vn.osp.modelview;

import java.util.List;

/**
 * Created by minh on 4/20/2017.
 */
public class NotaryOfficeListWrapper {

    private List<NotaryOffice> notaryOffices;

    private int total;
    private int page;
    private int totalPage;

    private String name;
    private Long active_flg;
    private String nameFilter;

    private String stringKey;
    private String searchTime;



    public List<NotaryOffice> getNotaryOffices() {
        return notaryOffices;
    }

    public void setNotaryOffices(List<NotaryOffice> notaryOffices) {
        this.notaryOffices = notaryOffices;
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

    public String getStringKey() {
        return stringKey;
    }

    public void setStringKey(String stringKey) {
        this.stringKey = stringKey;
    }

    public String getSearchTime() {
        return searchTime;
    }

    public void setSearchTime(String searchTime) {
        this.searchTime = searchTime;
    }


    public String getOrderString(){
        String whereString ="where 1 = 1 " ;
        String titleString ="";

        if(nameFilter !=null && !nameFilter.equals("")){
            whereString  += " and name like '%"+nameFilter+"%' ";
        }
        ////System.out.println("query name " + nameFilter);
        String query = whereString  ;
        return query;
    }


}

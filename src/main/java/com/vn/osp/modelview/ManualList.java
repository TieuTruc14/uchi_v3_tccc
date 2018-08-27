package com.vn.osp.modelview;

import java.util.List;

/**
 * Created by minh on 3/28/2017.
 */
public class ManualList {
    private List<Manual> manualList;

    private int total;
    private int page;
    private int totalPage;

    private String title;
    private String entry_date_time;
    private String update_date_time;
    private String file_name;
    private String action_status;

    public List<Manual> getManualList() {
        return manualList;
    }

    public void setManualList(List<Manual> manualList) {
        this.manualList = manualList;
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

    public String getTitle(){return title; }

    public void setTitle(String title){ this.title = title;}

    public String getFile_name(){return file_name; }

    public void setFile_name(String file_name){ this.file_name = file_name;}

    public String getEntry_date_time(){return entry_date_time;}

    public void setEntry_date_time(String entry_date_time){this.entry_date_time=entry_date_time;}

    public String getUpdate_date_time(){return update_date_time; }

    public void setUpdate_date_time(String update_date_time){ this.update_date_time = update_date_time;}

    public String getAction_status() {
        return action_status;
    }

    public void setAction_status(String action_status) {
        this.action_status = action_status;
    }

    public String getOrderString(){
        String whereString ="where 1=1" ;
        String orderString =" ORDER BY entry_date_time DESC" ;
        if(title !=null && !title.equals("")){
            orderString = " and title like '%"+title.trim()+"%'"+orderString+"";
        }
        String query = whereString + orderString ;
        return query;
    }

}

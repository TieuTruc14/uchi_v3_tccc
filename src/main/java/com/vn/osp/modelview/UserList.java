package com.vn.osp.modelview;

import java.util.List;

/**
 * Created by tranv on 3/6/2017.
 */
public class UserList {
    public static final String SESSION_KEY = "UserList";

    private List<User> userList;
    private int userListNumber;
    private int userTotalPage;
    private int page;

    private String family_name;
    private String first_name;
    private String account;
    private int active_flg;

    private List<NotaryOffice> notaryOffices;
    private Long office_id;

    private String action_status;

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public int getUserListNumber() {
        return userListNumber;
    }

    public void setUserListNumber(int userListNumber) {
        this.userListNumber = userListNumber;
    }

    public int getUserTotalPage() {
        return userTotalPage;
    }

    public void setUserTotalPage(int userTotalPage) {
        this.userTotalPage = userTotalPage;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getFamily_name() {
        return family_name;
    }

    public void setFamily_name(String family_name) {
        this.family_name = family_name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public int getActive_flg() {
        return active_flg;
    }

    public void setActive_flg(int active_flg) {
        this.active_flg = active_flg;
    }

    public String getAction_status() {
        return action_status;
    }

    public void setAction_status(String action_status) {
        this.action_status = action_status;
    }

    public List<NotaryOffice> getNotaryOffices() {
        return notaryOffices;
    }

    public void setNotaryOffices(List<NotaryOffice> notaryOffices) {
        this.notaryOffices = notaryOffices;
    }

    public Long getOffice_id() {
        return office_id;
    }

    public void setOffice_id(Long office_id) {
        this.office_id = office_id;
    }

    public String getFilter(){
        String whereString =" where 1=1 ";
        String orderString =" ORDER BY first_name asc ";
        if(account !=null && !account.equals("")){
            whereString += " and account like '%"+account+"%' ";
        }
        if(family_name !=null && !family_name.equals("")){
            whereString += " and family_name like '%"+family_name+"%' ";
        }
        if(first_name !=null && !first_name.equals("")){
            whereString += " and first_name like '%"+first_name+"%' ";
        }
        if(active_flg == 1){
            whereString += " and active_flg="+active_flg+" ";
        }
        if(active_flg == 2){
            whereString += " and active_flg="+0+" ";
        }
        String query = whereString + orderString;
        ////System.out.println(query);
        return query;
    }
}

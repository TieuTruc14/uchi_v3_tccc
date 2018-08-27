package com.vn.osp.modelview;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vn.osp.context.CommonContext;
import org.json.JSONObject;

import java.sql.Timestamp;
import java.util.ArrayList;


/**
 * Created by nmha on 3/28/2017.
 */
public class CreateBankForm {
    private Long id;
    private String name;
    private String code;
    private Long order_number;
    private Long entry_user_id;
    private String entry_user_name;
    private Timestamp entry_date_time;
    private Long update_user_id;
    private String update_user_name;
    private Timestamp update_date_time;
    private Long active;

    private ArrayList<Authority> systemManagerList;
    private ArrayList<Authority> functionList;
    private ArrayList<Authority> reportList;

    public CreateBankForm() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getOrder_number() {
        return order_number;
    }

    public void setOrder_number(Long order_number) {
        this.order_number = order_number;
    }

    public Long getEntry_user_id() {
        return entry_user_id;
    }

    public void setEntry_user_id(Long entry_user_id) {
        this.entry_user_id = entry_user_id;
    }

    public String getEntry_user_name() {
        return entry_user_name;
    }

    public void setEntry_user_name(String entry_user_name) {
        this.entry_user_name = entry_user_name;
    }

    public Timestamp getEntry_date_time() {
        return entry_date_time;
    }

    public void setEntry_date_time(Timestamp entry_date_time) {
        this.entry_date_time = entry_date_time;
    }

    public Long getUpdate_user_id() {
        return update_user_id;
    }

    public void setUpdate_user_id(Long update_user_id) {
        this.update_user_id = update_user_id;
    }

    public String getUpdate_user_name() {
        return update_user_name;
    }

    public void setUpdate_user_name(String update_user_name) {
        this.update_user_name = update_user_name;
    }

    public Timestamp getUpdate_date_time() {
        return update_date_time;
    }

    public void setUpdate_date_time(Timestamp update_date_time) {
        this.update_date_time = update_date_time;
    }

    public Long getActive() {
        return active;
    }

    public void setActive(Long active) {
        this.active = active;
    }

    public ArrayList<Authority> getSystemManagerList() {
        return systemManagerList;
    }

    public void setSystemManagerList(ArrayList<Authority> systemManagerList) {
        this.systemManagerList = systemManagerList;
    }

    public ArrayList<Authority> getFunctionList() {
        return functionList;
    }

    public void setFunctionList(ArrayList<Authority> functionList) {
        this.functionList = functionList;
    }

    public ArrayList<Authority> getReportList() {
        return reportList;
    }

    public void setReportList(ArrayList<Authority> reportList) {
        this.reportList = reportList;
    }

    public String generateAddJson(User user) {
        JSONObject obj = new JSONObject();
        ObjectMapper mapper = new ObjectMapper();

        try {
            // get from common
            this.setEntry_user_name(user.getAccount());
            this.setEntry_user_id(user.getUserId());
            this.setEntry_date_time(new Timestamp(System.currentTimeMillis()));
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String generateUpdateJson(User user) {
        JSONObject obj = new JSONObject();
        ObjectMapper mapper = new ObjectMapper();

        try {
            // get from common
            this.setUpdate_user_name(user.getAccount());
            this.setUpdate_user_id(user.getUserId());
            this.setUpdate_date_time(new Timestamp(System.currentTimeMillis()));
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}

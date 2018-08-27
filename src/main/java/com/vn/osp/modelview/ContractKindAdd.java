package com.vn.osp.modelview;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vn.osp.context.CommonContext;
import org.json.JSONObject;

/**
 * Created by Admin on 8/6/2017.
 */
public class ContractKindAdd {
    private Long id;
    private String name;
    private Long update_user_id;
    private String update_user_name;



    private String code;

    public ContractKindAdd() {
    }

    public ContractKindAdd(Long id ,String name, Long update_user_id, String update_user_name, String code) {
        this.id= id;
        this.name = name;
        this.update_user_id = update_user_id;
        this.update_user_name = update_user_name;
        this.code = code;
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


        if(code!= null && !code.equals("")){
            orderString2 = " and code like '%"+code.trim()+"%'";
        }

        String query = whereString +orderString2 ;
        return query;
    }
    public String generateAddJson(User user) {
        JSONObject obj = new JSONObject();
        ObjectMapper mapper = new ObjectMapper();
        this.setUpdate_user_id(user.getUserId());
        this.setUpdate_user_name(user.getFirst_name()+" "+user.getFamily_name());
        try {
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}

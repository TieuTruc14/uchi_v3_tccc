package com.vn.osp.modelview;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;

/**
 * Created by minh on 3/18/2017.
 */
public class UpdateUnitRequest {
    private Long id;
    private String name;
    private String description;
    private Long active_flg;
    private Long update_user_id;
    private String update_user_name;

    @JsonCreator
    public UpdateUnitRequest(  @JsonProperty(value = "id", required = true) Long id,
                            @JsonProperty(value = "name", required = true) String name,
                            @JsonProperty(value = "description", required = true) String description,
                            @JsonProperty(value = "active_flg", required = true) Long active_flg,
                            @JsonProperty(value = "update_user_id", required = true) Long update_user_id,
                            @JsonProperty(value = "update_user_name", required = true) String update_user_name){
        this.id = id;
        this.name = name;
        this.description = description;
        this.active_flg = active_flg;
        this.update_user_id = update_user_id;
        this.update_user_name = update_user_name;
    }


    public UpdateUnitRequest() {
    }




    public Long getUid() {
        return id;
    }

    public void setUid(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public Long getActive_flg() {
        return active_flg;
    }

    public void setActive_flg(Long active_flg) {
        this.active_flg = active_flg;
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

    public JSONObject generateJsonObject(){
        JSONObject obj = new JSONObject();
        AddUnitRequest addUnitRequest =new AddUnitRequest();

        try {

            obj.put("uid",id);
            obj.put("name", name);
            obj.put("description", description);
            obj.put("active_flg", active_flg);
            obj.put("update_user_id", "1");
            obj.put("update_user_name", "minhbq");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }
}

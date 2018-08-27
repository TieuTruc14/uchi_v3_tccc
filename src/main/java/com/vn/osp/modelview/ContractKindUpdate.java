package com.vn.osp.modelview;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;

/**
 * Created by Admin on 4/8/2017.
 */
public class ContractKindUpdate {
    private Long id ;
    private String name;
    private Long update_user_id;
    private String update_user_name;
    private String contract_kind_code;

    public ContractKindUpdate() {
    }

    public ContractKindUpdate(Long id, String name, Long update_user_id, String update_user_name, String contract_kind_code) {
        this.id = id;
        this.name = name;
        this.update_user_id = update_user_id;
        this.update_user_name = update_user_name;
        this.contract_kind_code = contract_kind_code;
    }

    public Long getCkId() {
        return id;
    }

    public void setCkId(Long id) {
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

    public String getContract_kind_code() {
        return contract_kind_code;
    }

    public void setContract_kind_code(String contract_kind_code) {
        this.contract_kind_code = contract_kind_code;
    }
    public String generateUpdateJson(User user) {
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

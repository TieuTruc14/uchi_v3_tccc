package com.vn.osp.modelview;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vn.osp.common.util.SystemMessageProperties;
import com.vn.osp.context.CommonContext;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by Admin on 7/6/2017.
 */
public class ContractTemp {
    private Long id;
    private String name;
    private Long kind_id;
    private Long kind_id_tt08;
    private String code;
    private String description;
    private String file_name;
    private String file_path;
    private Long active_flg;
    private Long relate_object_number;
    private String relate_object_A_display;
    private String relate_object_B_display;
    private String relate_object_C_display;
    private Long period_flag;
    private Long period_req_flag;
    private Long mortage_cancel_func;
    private Long sync_option;
    private Long entry_user_id;
    private String entry_user_name;
    private String entry_date_time;
    private Long update_user_id;
    private String update_user_name;
    private String update_date_time;
    private String kind_html;
    private String office_code;
    private String code_template;
    private String getCode_template_;
    @JsonIgnore
    private List<ContractKind> listContractKind;
    @JsonIgnore
    private String name_;
    @JsonIgnore
    private String code_;
    @JsonIgnore
    private String description_;
    @JsonIgnore
    private String relate_object_number_;
    @JsonIgnore
    private String office_code_;
    @JsonIgnore
    private Boolean success;

    @JsonCreator
    public ContractTemp(
            @JsonProperty(value = "id") final Long id,
            @JsonProperty(value = "name") final String name,
            @JsonProperty(value = "kind_id") final Long kind_id,
            @JsonProperty(value = "kind_id_tt08")final Long kind_id_tt08,
            @JsonProperty(value = "code") final String code,
            @JsonProperty(value = "description") final String description,
            @JsonProperty(value = "file_name") final String file_name,
            @JsonProperty(value = "file_path") final String file_path,
            @JsonProperty(value = "active_flg") final Long active_flg,
            @JsonProperty(value = "relate_object_number") final Long relate_object_number,
            @JsonProperty(value = "relate_object_A_display") final String relate_object_A_display,
            @JsonProperty(value = "relate_object_B_display") final String relate_object_B_display,
            @JsonProperty(value = "relate_object_C_display") final String relate_object_C_display,
            @JsonProperty(value = "period_flag") final Long period_flag,
            @JsonProperty(value = "period_req_flag") final Long period_req_flag,
            @JsonProperty(value = "mortage_cancel_func") final Long mortage_cancel_func,
            @JsonProperty(value = "sync_option") final Long sync_option,
            @JsonProperty(value = "entry_user_id") final Long entry_user_id,
            @JsonProperty(value = "entry_user_name") final String entry_user_name,
            @JsonProperty(value = "entry_date_time") final String entry_date_time,
            @JsonProperty(value = "update_user_id") final Long update_user_id,
            @JsonProperty(value = "update_user-name") final String update_user_name,
            @JsonProperty(value = "update_date_time") final String update_date_time,
            @JsonProperty(value = "kind_html") final String kind_html,
            @JsonProperty(value = "office_code") final String office_code,
            @JsonProperty(value = "code_template") final String code_template
    ){
        this.id = id;
        this.name = name;
        this.kind_id = kind_id;
        this.kind_id_tt08 = kind_id_tt08;
        this.code = code;
        this.description = description;
        this.file_name = file_name;
        this.file_path = file_path;
        this.active_flg = active_flg;
        this.relate_object_number = relate_object_number;
        this.relate_object_A_display = relate_object_A_display;
        this.relate_object_B_display = relate_object_B_display;
        this.relate_object_C_display = relate_object_C_display;
        this.period_flag = period_flag;
        this.period_req_flag = period_req_flag;
        this.mortage_cancel_func = mortage_cancel_func;
        this.sync_option = sync_option;
        this.entry_user_id = entry_user_id;
        this.entry_user_name = entry_user_name;
        this.entry_date_time = entry_date_time;
        this.update_user_id = update_user_id;
        this.update_user_name = update_user_name;
        this.update_date_time = update_date_time;
        this.kind_html = kind_html;
        this.office_code = office_code;
        this.code_template = code_template;

    }
    public ContractTemp(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode_template() {
        return code_template;
    }

    public void setCode_template(String code_template) {
        this.code_template = code_template;
    }

    public String getGetCode_template_() {
        return getCode_template_;
    }

    public void setGetCode_template_(String getCode_template_) {
        this.getCode_template_ = getCode_template_;
    }

    public Long getSid() {
        return id;
    }

    public void setSid(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getKind_id() {
        return kind_id;
    }

    public void setKind_id(Long kind_id) {
        this.kind_id = kind_id;
    }

    public Long getKind_id_tt08() {
        return kind_id_tt08;
    }

    public void setKind_id_tt08(Long kind_id_tt08) {
        this.kind_id_tt08 = kind_id_tt08;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public String getFile_path() {
        return file_path;
    }

    public void setFile_path(String file_path) {
        this.file_path = file_path;
    }

    public Long getActive_flg() {
        return active_flg;
    }

    public void setActive_flg(Long active_flg) {
        this.active_flg = active_flg;
    }

    public Long getRelate_object_number() {
        return relate_object_number;
    }

    public void setRelate_object_number(Long relate_object_number) {
        this.relate_object_number = relate_object_number;
    }

    public Long getPeriod_flag() {
        return period_flag;
    }

    public void setPeriod_flag(Long period_flag) {
        this.period_flag = period_flag;
    }

    public Long getPeriod_req_flag() {
        return period_req_flag;
    }

    public void setPeriod_req_flag(Long period_req_flag) {
        this.period_req_flag = period_req_flag;
    }

    public Long getMortage_cancel_func() {
        return mortage_cancel_func;
    }

    public void setMortage_cancel_func(Long mortage_cancel_func) {
        this.mortage_cancel_func = mortage_cancel_func;
    }

    public Long getSync_option() {
        return sync_option;
    }

    public void setSync_option(Long sync_option) {
        this.sync_option = sync_option;
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

    public String getEntry_date_time() {
        return entry_date_time;
    }

    public void setEntry_date_time(String entry_date_time) {
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

    public String getUpdate_date_time() {
        return update_date_time;
    }

    public void setUpdate_date_time(String update_date_time) {
        this.update_date_time = update_date_time;
    }

    public String getKind_html() {
        return kind_html;
    }

    public void setKind_html(String kind_html) {
        this.kind_html = kind_html;
    }

    public String getOffice_code() {
        return office_code;
    }

    public void setOffice_code(String office_code) {
        this.office_code = office_code;
    }

    public List<ContractKind> getListContractKind() {
        return listContractKind;
    }

    public void setListContractKind(List<ContractKind> listContractKind) {
        this.listContractKind = listContractKind;
    }

    public String getName_() {
        return name_;
    }

    public void setName_(String name_) {
        this.name_ = name_;
    }

    public String getCode_() {
        return code_;
    }

    public void setCode_(String code_) {
        this.code_ = code_;
    }

    public String getDescription_() {
        return description_;
    }

    public void setDescription_(String description_) {
        this.description_ = description_;
    }

    public String getRelate_object_number_() {
        return relate_object_number_;
    }

    public void setRelate_object_number_(String relate_object_number_) {
        this.relate_object_number_ = relate_object_number_;
    }

    public String getOffice_code_() {
        return office_code_;
    }

    public void setOffice_code_(String office_code_) {
        this.office_code_ = office_code_;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getRelate_object_A_display() {
        return relate_object_A_display;
    }

    public void setRelate_object_A_display(String relate_object_A_display) {
        this.relate_object_A_display = relate_object_A_display;
    }

    public String getRelate_object_B_display() {
        return relate_object_B_display;
    }

    public void setRelate_object_B_display(String relate_object_B_display) {
        this.relate_object_B_display = relate_object_B_display;
    }

    public String getRelate_object_C_display() {
        return relate_object_C_display;
    }

    public void setRelate_object_C_display(String relate_object_C_display) {
        this.relate_object_C_display = relate_object_C_display;
    }

    public void validateUpdate(){

        success = true;
        String truong = SystemMessageProperties.getSystemProperty("v3_truong") +" ";
        String notEmpty = SystemMessageProperties.getSystemProperty("v3_not_empty");
        String max_length = SystemMessageProperties.getSystemProperty("v3_max_length");
        String min_length = SystemMessageProperties.getSystemProperty("v3_min_length");
        String ky_tu = SystemMessageProperties.getSystemProperty("v3_ky_tu");


        if(name == null || name.equals("")){
            name_ = ( truong + SystemMessageProperties.getSystemProperty("v3_contract_template_name")+ notEmpty);
            success= false;
        }
        if(code == null || code.equals("")){
            code_= (truong+ SystemMessageProperties.getSystemProperty("v3_contract_template_code")+ notEmpty);
            success= false;
        }



    }

    public String generateAddJson(User user) {
        JSONObject obj = new JSONObject();
        ObjectMapper mapper = new ObjectMapper();
        this.setEntry_user_id(user.getUserId());
        this.setEntry_user_name(user.getFirst_name()+" "+user.getFamily_name());
        if(this.update_user_id == null){
            this.setUpdate_user_id(Long.valueOf(-1));
        }
        if(this.update_user_name == null){
            this.setUpdate_user_name("");
        }
        if(this.kind_id == null){
            this.setKind_id(Long.valueOf(0));
        }
        if(this.kind_id_tt08 == null){
            this.setKind_id_tt08(Long.valueOf(0));
        }
        if(this.description==null){
            this.setDescription("");
        }
        if(this.file_name==null){
            this.setFile_name("");
        }
        if(this.file_path == null)
        {
            this.setFile_path("");
        }
        if(this.active_flg == null){
            this.setActive_flg(Long.valueOf(0));
        }

        if(this.relate_object_number == null)
        {
            this.setRelate_object_number(Long.valueOf(2));
        }


        if(this.relate_object_A_display==null){
            this.setRelate_object_A_display("");
        }
        if(this.relate_object_B_display==null){
            this.setRelate_object_B_display("");
        }
        if(this.relate_object_C_display==null){
            this.setRelate_object_C_display("");
        }
        if(this.period_flag == null){
            this.setPeriod_flag(Long.valueOf(0));
        }
        if(this.period_req_flag == null){
            this.setPeriod_req_flag(Long.valueOf(0));
        }
        if(this.mortage_cancel_func == null){
            this.setMortage_cancel_func(Long.valueOf(0));
        }
        if(this.sync_option==null){
            this.setSync_option(Long.valueOf(0));
        }

        if(this.kind_html==null){
            this.setKind_html("");
        }
        if(this.office_code==null){
            this.setOffice_code("");
        }
        try {
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String generateUpdateJson(User user) {
        JSONObject obj = new JSONObject();
        ObjectMapper mapper = new ObjectMapper();
        this.setUpdate_user_id(user.getUserId());
        this.setUpdate_user_name(user.getFirst_name()+" "+user.getFamily_name());
        if(this.kind_id == null){
            this.setKind_id(Long.valueOf(0));
        }
        if(this.kind_id_tt08 == null){
            this.setKind_id_tt08(Long.valueOf(0));
        }
        if(this.description==null){
            this.setDescription("");
        }
        if(this.file_name==null){
            this.setFile_name("");
        }
        if(this.file_path == null)
        {
            this.setFile_path("");
        }
        if(this.active_flg == null){
            this.setActive_flg(Long.valueOf(0));
        }

        if(this.relate_object_number == null)
        {
            this.setRelate_object_number(Long.valueOf(2));
        }


        if(this.relate_object_A_display==null){
            this.setRelate_object_A_display("");
        }
        if(this.relate_object_B_display==null){
            this.setRelate_object_B_display("");
        }
        if(this.relate_object_C_display==null){
            this.setRelate_object_C_display("");
        }
        if(this.period_flag == null){
            this.setPeriod_flag(Long.valueOf(0));
        }
        if(this.period_req_flag == null){
            this.setPeriod_req_flag(Long.valueOf(0));
        }
        if(this.mortage_cancel_func == null){
            this.setMortage_cancel_func(Long.valueOf(0));
        }
        if(this.sync_option==null){
            this.setSync_option(Long.valueOf(0));
        }

        if(this.kind_html==null){
            this.setKind_html("");
        }
        if(this.office_code==null){
            this.setOffice_code("");
        }
        try {
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}

package com.vn.osp.modelview;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.vn.osp.common.util.EditString;
import com.vn.osp.common.util.SystemMessageProperties;
import com.vn.osp.common.util.ValidationPool;
import org.springframework.web.multipart.MultipartFile;

public class DataPrevent {
  private Long id;
  private String synchronize_id;
  private Long property_id;
  private String origin_kind;
  private Long delete_flg;
  private String prevent_regist_agency;
  private String prevent_in_book_number;
  private String prevent_person_info;
  private String prevent_doc_number;
  private String prevent_doc_date;
  private String prevent_doc_receive_date;
  private String prevent_input_date;
  private String prevent_doc_summary;
  private String prevent_file_name;
  private String prevent_file_path;
  private String prevent_note;
  private Long release_flg;
  private String release_regist_agency;
  private String release_in_book_number;
  private String release_person_info;
  private String release_doc_number;
  private String release_doc_date;
  private String release_doc_receive_date;
  private String release_input_date;
  private String release_doc_summary;
  private String release_file_name;
  private String release_file_path;
  private String release_note;
  private Long entry_user_id;
  private Long leader_id;
  private Long divison_id;
  private String entry_user_name;
  private String entry_date_time;
  private Long update_user_id;
  private String update_user_name;
  private String update_date_time;
  private Long status;
  @JsonIgnore
  private MultipartFile multipartFile;

  //validate
  private Boolean success;
  private Long id_;
  private String synchronize_id_;
  private Long property_id_;
  private String origin_kind_;
  private Long delete_flg_;
  private String prevent_regist_agency_;
  private String prevent_in_book_number_;
  private String prevent_person_info_;
  private String prevent_doc_number_;
  private String prevent_doc_date_;
  private String prevent_doc_receive_date_;
  private String prevent_input_date_;
  private String prevent_doc_summary_;
  private String prevent_file_name_;
  private String prevent_file_path_;
  private String prevent_note_;
  private Long release_flg_;
  private String release_regist_agency_;
  private String release_in_book_number_;
  private String release_person_info_;
  private String release_doc_number_;
  private String release_doc_date_;
  private String release_doc_receive_date_;
  private String release_input_date_;
  private String release_doc_summary_;
  private String release_file_name_;
  private String release_file_path_;
  private String release_note_;
  private Long entry_user_id_;
  private Long leader_id_;
  private Long divison_id_;
  private String entry_user_name_;
  private String entry_date_time_;
  private Long update_user_id_;
  private String update_user_name_;
  private String update_date_time_;
  private Long status_;

  @JsonCreator
  public DataPrevent(@JsonProperty(value = "id", required = false) final Long id,
                     @JsonProperty(value = "synchronize_id", required = false) final String synchronize_id,
                     @JsonProperty(value = "property_id", required = false) final Long property_id,
                     @JsonProperty(value = "origin_kind", required = false) final String origin_kind,
                     @JsonProperty(value = "delete_flg", required = false) final Long delete_flg,
                     @JsonProperty(value = "prevent_regist_agency", required = false) final String prevent_regist_agency,
                     @JsonProperty(value = "prevent_in_book_number", required = false) final String prevent_in_book_number,
                     @JsonProperty(value = "prevent_person_info", required = false) final String prevent_person_info,
                     @JsonProperty(value = "prevent_doc_number", required = false) final String prevent_doc_number,
                     @JsonProperty(value = "prevent_doc_date", required = false) final String prevent_doc_date,
                     @JsonProperty(value = "prevent_doc_receive_date", required = false) final String prevent_doc_receive_date,
                     @JsonProperty(value = "prevent_input_date", required = false) final String prevent_input_date,
                     @JsonProperty(value = "prevent_doc_summary", required = false) final String prevent_doc_summary,
                     @JsonProperty(value = "prevent_file_name", required = false) final String prevent_file_name,
                     @JsonProperty(value = "prevent_file_path", required = false) final String prevent_file_path,
                     @JsonProperty(value = "prevent_note", required = false) final String prevent_note,
                     @JsonProperty(value = "release_flg", required = false) final Long release_flg,
                     @JsonProperty(value = "release_regist_agency", required = false) final String release_regist_agency,
                     @JsonProperty(value = "release_in_book_number", required = false) final String release_in_book_number,
                     @JsonProperty(value = "release_person_info", required = false) final String release_person_info,
                     @JsonProperty(value = "release_doc_number", required = false) final String release_doc_number,
                     @JsonProperty(value = "release_doc_date", required = false) final String release_doc_date,
                     @JsonProperty(value = "release_doc_receive_date", required = false) final String release_doc_receive_date,
                     @JsonProperty(value = "release_input_date", required = false) final String release_input_date,
                     @JsonProperty(value = "release_doc_summary", required = false) final String release_doc_summary,
                     @JsonProperty(value = "release_file_name", required = false) final String release_file_name,
                     @JsonProperty(value = "release_file_path", required = false) final String release_file_path,
                     @JsonProperty(value = "release_note", required = false) final String release_note,
                     @JsonProperty(value = "entry_user_id", required = false) final Long entry_user_id,
                     @JsonProperty(value = "leader_id", required = false) final Long leader_id,
                     @JsonProperty(value = "divison_id", required = false) final Long divison_id,
                     @JsonProperty(value = "entry_user_name", required = false) final String entry_user_name,
                     @JsonProperty(value = "entry_date_time", required = false) final String entry_date_time,
                     @JsonProperty(value = "update_user_id", required = false) final Long update_user_id,
                     @JsonProperty(value = "update_user_name", required = false) final String update_user_name,
                     @JsonProperty(value = "update_date_time", required = false) final String update_date_time,
                     @JsonProperty(value = "status", required = false) final Long status) {
    this.id = id;
    this.synchronize_id = synchronize_id;
    this.property_id = property_id;
    this.origin_kind = origin_kind;
    this.delete_flg = delete_flg;
    this.prevent_regist_agency = prevent_regist_agency;
    this.prevent_in_book_number = prevent_in_book_number;
    this.prevent_person_info = prevent_person_info;
    this.prevent_doc_number = prevent_doc_number;
    this.prevent_doc_date = prevent_doc_date;
    this.prevent_doc_receive_date = prevent_doc_receive_date;
    this.prevent_input_date = prevent_input_date;
    this.prevent_doc_summary = prevent_doc_summary;
    this.prevent_file_name = prevent_file_name;
    this.prevent_file_path = prevent_file_path;
    this.prevent_note = prevent_note;
    this.release_flg = release_flg;
    this.release_regist_agency = release_regist_agency;
    this.release_in_book_number = release_in_book_number;
    this.release_person_info = release_person_info;
    this.release_doc_number = release_doc_number;
    this.release_doc_date = release_doc_date;
    this.release_doc_receive_date = release_doc_receive_date;
    this.release_input_date = release_input_date;
    this.release_doc_summary = release_doc_summary;
    this.release_file_name = release_file_name;
    this.release_file_path = release_file_path;
    this.release_note = release_note;
    this.entry_user_id = entry_user_id;
    this.leader_id = leader_id;
    this.divison_id = divison_id;
    this.entry_user_name = entry_user_name;
    this.entry_date_time = entry_date_time;
    this.update_user_id = update_user_id;
    this.update_user_name = update_user_name;
    this.update_date_time = update_date_time;
    this.status = status;
  }

  public DataPrevent() {
  }

  public Long getDataPreventId() {
    return id;
  }

  public MultipartFile getMultipartFile() {
    return multipartFile;
  }

  public void setMultipartFile(MultipartFile multipartFile) {
    this.multipartFile = multipartFile;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getSynchronize_id() {
    return synchronize_id;
  }

  public void setSynchronize_id(String synchronize_id) {
    this.synchronize_id = synchronize_id;
  }

  public Long getProperty_id() {
    return property_id;
  }

  public void setProperty_id(Long property_id) {
    this.property_id = property_id;
  }

  public String getOrigin_kind() {
    return origin_kind;
  }

  public void setOrigin_kind(String origin_kind) {
    this.origin_kind = origin_kind;
  }

  public Long getDelete_flg() {
    return delete_flg;
  }

  public void setDelete_flg(Long delete_flg) {
    this.delete_flg = delete_flg;
  }

  public String getPrevent_regist_agency() {
    return prevent_regist_agency;
  }

  public void setPrevent_regist_agency(String prevent_regist_agency) {
    this.prevent_regist_agency = prevent_regist_agency;
  }

  public String getPrevent_in_book_number() {
    return prevent_in_book_number;
  }

  public void setPrevent_in_book_number(String prevent_in_book_number) {
    this.prevent_in_book_number = prevent_in_book_number;
  }

  public String getPrevent_person_info() {
    return prevent_person_info;
  }

  public void setPrevent_person_info(String prevent_person_info) {
    this.prevent_person_info = prevent_person_info;
  }

  public String getPrevent_doc_number() {
    return prevent_doc_number;
  }

  public void setPrevent_doc_number(String prevent_doc_number) {
    this.prevent_doc_number = prevent_doc_number;
  }

  public String getPrevent_doc_date() {
    return prevent_doc_date;
  }

  public void setPrevent_doc_date(String prevent_doc_date) {
    this.prevent_doc_date = prevent_doc_date;
  }

  public String getPrevent_doc_receive_date() {
    return prevent_doc_receive_date;
  }

  public void setPrevent_doc_receive_date(String prevent_doc_receive_date) {
    this.prevent_doc_receive_date = prevent_doc_receive_date;
  }

  public String getPrevent_input_date() {
    return prevent_input_date;
  }

  public void setPrevent_input_date(String prevent_input_date) {
    this.prevent_input_date = prevent_input_date;
  }

  public String getPrevent_doc_summary() {
    return prevent_doc_summary;
  }

  public void setPrevent_doc_summary(String prevent_doc_summary) {
    this.prevent_doc_summary = prevent_doc_summary;
  }

  public String getPrevent_file_name() {
    return prevent_file_name;
  }

  public void setPrevent_file_name(String prevent_file_name) {
    this.prevent_file_name = prevent_file_name;
  }

  public String getPrevent_file_path() {
    return prevent_file_path;
  }

  public void setPrevent_file_path(String prevent_file_path) {
    this.prevent_file_path = prevent_file_path;
  }

  public String getPrevent_note() {
    return prevent_note;
  }

  public void setPrevent_note(String prevent_note) {
    this.prevent_note = prevent_note;
  }

  public Long getRelease_flg() {
    return release_flg;
  }

  public void setRelease_flg(Long release_flg) {
    this.release_flg = release_flg;
  }

  public String getRelease_regist_agency() {
    return release_regist_agency;
  }

  public void setRelease_regist_agency(String release_regist_agency) {
    this.release_regist_agency = release_regist_agency;
  }

  public String getRelease_in_book_number() {
    return release_in_book_number;
  }

  public void setRelease_in_book_number(String release_in_book_number) {
    this.release_in_book_number = release_in_book_number;
  }

  public String getRelease_person_info() {
    return release_person_info;
  }

  public void setRelease_person_info(String release_person_info) {
    this.release_person_info = release_person_info;
  }

  public String getRelease_doc_number() {
    return release_doc_number;
  }

  public void setRelease_doc_number(String release_doc_number) {
    this.release_doc_number = release_doc_number;
  }

  public String getRelease_doc_date() {
    return release_doc_date;
  }

  public void setRelease_doc_date(String release_doc_date) {
    this.release_doc_date = release_doc_date;
  }

  public String getRelease_doc_receive_date() {
    return release_doc_receive_date;
  }

  public void setRelease_doc_receive_date(String release_doc_receive_date) {
    this.release_doc_receive_date = release_doc_receive_date;
  }

  public String getRelease_input_date() {
    return release_input_date;
  }

  public void setRelease_input_date(String release_input_date) {
    this.release_input_date = release_input_date;
  }

  public String getRelease_doc_summary() {
    return release_doc_summary;
  }

  public void setRelease_doc_summary(String release_doc_summary) {
    this.release_doc_summary = release_doc_summary;
  }

  public String getRelease_file_name() {
    return release_file_name;
  }

  public void setRelease_file_name(String release_file_name) {
    this.release_file_name = release_file_name;
  }

  public String getRelease_file_path() {
    return release_file_path;
  }

  public void setRelease_file_path(String release_file_path) {
    this.release_file_path = release_file_path;
  }

  public String getRelease_note() {
    return release_note;
  }

  public void setRelease_note(String release_note) {
    this.release_note = release_note;
  }

  public Long getEntry_user_id() {
    return entry_user_id;
  }

  public void setEntry_user_id(Long entry_user_id) {
    this.entry_user_id = entry_user_id;
  }

  public Long getLeader_id() {
    return leader_id;
  }

  public void setLeader_id(Long leader_id) {
    this.leader_id = leader_id;
  }

  public Long getDivison_id() {
    return divison_id;
  }

  public void setDivison_id(Long divison_id) {
    this.divison_id = divison_id;
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

  public Long getStatus() {
    return status;
  }

  public void setStatus(Long status) {
    this.status = status;
  }

    public Long getId() {
        return id;
    }

    public Long getId_() {
        return id_;
    }

    public void setId_(Long id_) {
        this.id_ = id_;
    }

    public String getSynchronize_id_() {
        return synchronize_id_;
    }

    public void setSynchronize_id_(String synchronize_id_) {
        this.synchronize_id_ = synchronize_id_;
    }

    public Long getProperty_id_() {
        return property_id_;
    }

    public void setProperty_id_(Long property_id_) {
        this.property_id_ = property_id_;
    }

    public String getOrigin_kind_() {
        return origin_kind_;
    }

    public void setOrigin_kind_(String origin_kind_) {
        this.origin_kind_ = origin_kind_;
    }

    public Long getDelete_flg_() {
        return delete_flg_;
    }

    public void setDelete_flg_(Long delete_flg_) {
        this.delete_flg_ = delete_flg_;
    }

    public String getPrevent_regist_agency_() {
        return prevent_regist_agency_;
    }

    public void setPrevent_regist_agency_(String prevent_regist_agency_) {
        this.prevent_regist_agency_ = prevent_regist_agency_;
    }

    public String getPrevent_in_book_number_() {
        return prevent_in_book_number_;
    }

    public void setPrevent_in_book_number_(String prevent_in_book_number_) {
        this.prevent_in_book_number_ = prevent_in_book_number_;
    }

    public String getPrevent_person_info_() {
        return prevent_person_info_;
    }

    public void setPrevent_person_info_(String prevent_person_info_) {
        this.prevent_person_info_ = prevent_person_info_;
    }

    public String getPrevent_doc_number_() {
        return prevent_doc_number_;
    }

    public void setPrevent_doc_number_(String prevent_doc_number_) {
        this.prevent_doc_number_ = prevent_doc_number_;
    }

    public String getPrevent_doc_date_() {
        return prevent_doc_date_;
    }

    public void setPrevent_doc_date_(String prevent_doc_date_) {
        this.prevent_doc_date_ = prevent_doc_date_;
    }

    public String getPrevent_doc_receive_date_() {
        return prevent_doc_receive_date_;
    }

    public void setPrevent_doc_receive_date_(String prevent_doc_receive_date_) {
        this.prevent_doc_receive_date_ = prevent_doc_receive_date_;
    }

    public String getPrevent_input_date_() {
        return prevent_input_date_;
    }

    public void setPrevent_input_date_(String prevent_input_date_) {
        this.prevent_input_date_ = prevent_input_date_;
    }

    public String getPrevent_doc_summary_() {
        return prevent_doc_summary_;
    }

    public void setPrevent_doc_summary_(String prevent_doc_summary_) {
        this.prevent_doc_summary_ = prevent_doc_summary_;
    }

    public String getPrevent_file_name_() {
        return prevent_file_name_;
    }

    public void setPrevent_file_name_(String prevent_file_name_) {
        this.prevent_file_name_ = prevent_file_name_;
    }

    public String getPrevent_file_path_() {
        return prevent_file_path_;
    }

    public void setPrevent_file_path_(String prevent_file_path_) {
        this.prevent_file_path_ = prevent_file_path_;
    }

    public String getPrevent_note_() {
        return prevent_note_;
    }

    public void setPrevent_note_(String prevent_note_) {
        this.prevent_note_ = prevent_note_;
    }

    public Long getRelease_flg_() {
        return release_flg_;
    }

    public void setRelease_flg_(Long release_flg_) {
        this.release_flg_ = release_flg_;
    }

    public String getRelease_regist_agency_() {
        return release_regist_agency_;
    }

    public void setRelease_regist_agency_(String release_regist_agency_) {
        this.release_regist_agency_ = release_regist_agency_;
    }

    public String getRelease_in_book_number_() {
        return release_in_book_number_;
    }

    public void setRelease_in_book_number_(String release_in_book_number_) {
        this.release_in_book_number_ = release_in_book_number_;
    }

    public String getRelease_person_info_() {
        return release_person_info_;
    }

    public void setRelease_person_info_(String release_person_info_) {
        this.release_person_info_ = release_person_info_;
    }

    public String getRelease_doc_number_() {
        return release_doc_number_;
    }

    public void setRelease_doc_number_(String release_doc_number_) {
        this.release_doc_number_ = release_doc_number_;
    }

    public String getRelease_doc_date_() {
        return release_doc_date_;
    }

    public void setRelease_doc_date_(String release_doc_date_) {
        this.release_doc_date_ = release_doc_date_;
    }

    public String getRelease_doc_receive_date_() {
        return release_doc_receive_date_;
    }

    public void setRelease_doc_receive_date_(String release_doc_receive_date_) {
        this.release_doc_receive_date_ = release_doc_receive_date_;
    }

    public String getRelease_input_date_() {
        return release_input_date_;
    }

    public void setRelease_input_date_(String release_input_date_) {
        this.release_input_date_ = release_input_date_;
    }

    public String getRelease_doc_summary_() {
        return release_doc_summary_;
    }

    public void setRelease_doc_summary_(String release_doc_summary_) {
        this.release_doc_summary_ = release_doc_summary_;
    }

    public String getRelease_file_name_() {
        return release_file_name_;
    }

    public void setRelease_file_name_(String release_file_name_) {
        this.release_file_name_ = release_file_name_;
    }

    public String getRelease_file_path_() {
        return release_file_path_;
    }

    public void setRelease_file_path_(String release_file_path_) {
        this.release_file_path_ = release_file_path_;
    }

    public String getRelease_note_() {
        return release_note_;
    }

    public void setRelease_note_(String release_note_) {
        this.release_note_ = release_note_;
    }

    public Long getEntry_user_id_() {
        return entry_user_id_;
    }

    public void setEntry_user_id_(Long entry_user_id_) {
        this.entry_user_id_ = entry_user_id_;
    }

    public Long getLeader_id_() {
        return leader_id_;
    }

    public void setLeader_id_(Long leader_id_) {
        this.leader_id_ = leader_id_;
    }

    public Long getDivison_id_() {
        return divison_id_;
    }

    public void setDivison_id_(Long divison_id_) {
        this.divison_id_ = divison_id_;
    }

    public String getEntry_user_name_() {
        return entry_user_name_;
    }

    public void setEntry_user_name_(String entry_user_name_) {
        this.entry_user_name_ = entry_user_name_;
    }

    public String getEntry_date_time_() {
        return entry_date_time_;
    }

    public void setEntry_date_time_(String entry_date_time_) {
        this.entry_date_time_ = entry_date_time_;
    }

    public Long getUpdate_user_id_() {
        return update_user_id_;
    }

    public void setUpdate_user_id_(Long update_user_id_) {
        this.update_user_id_ = update_user_id_;
    }

    public String getUpdate_user_name_() {
        return update_user_name_;
    }

    public void setUpdate_user_name_(String update_user_name_) {
        this.update_user_name_ = update_user_name_;
    }

    public String getUpdate_date_time_() {
        return update_date_time_;
    }

    public void setUpdate_date_time_(String update_date_time_) {
        this.update_date_time_ = update_date_time_;
    }

    public Long getStatus_() {
        return status_;
    }

    public void setStatus_(Long status_) {
        this.status_ = status_;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public void valiate() {
        success = true;
        String truong = SystemMessageProperties.getSystemProperty("v3_truong") +" ";
        String notEmpty = SystemMessageProperties.getSystemProperty("v3_not_empty");
        String max_length = SystemMessageProperties.getSystemProperty("v3_max_length");
        String min_length = SystemMessageProperties.getSystemProperty("v3_min_length");
        String ky_tu = SystemMessageProperties.getSystemProperty("v3_ky_tu");

        if(prevent_in_book_number != null && !prevent_in_book_number.equals("")){
            if(!prevent_in_book_number.matches(ValidationPool.length(1,50))){
                prevent_in_book_number_=(truong + SystemMessageProperties.getSystemProperty("v3_so_vao_so_cv") + max_length + EditString.SPACE+ 50 + ky_tu);
                success = false;
            }
        }

        if (prevent_doc_number == null || prevent_doc_number.equals("")){
            prevent_doc_number_=(truong + SystemMessageProperties.getSystemProperty("v3_so_cv") + notEmpty);
            success = false;
        }else if(!prevent_doc_number.matches(ValidationPool.length(1,50))){
          prevent_doc_number_=(truong + SystemMessageProperties.getSystemProperty("v3_so_cv") + max_length+EditString.SPACE + 50 + ky_tu);
          success = false;
        }
        if (prevent_person_info == null || prevent_person_info.equals("")){
            prevent_person_info_=(truong + SystemMessageProperties.getSystemProperty("v3_prevent_dv_gui_yc") + notEmpty);
            success = false;
        }
        if (prevent_doc_date == null || prevent_doc_date.equals("")){
            prevent_doc_date_=(truong + SystemMessageProperties.getSystemProperty("v3_ngay_cv") + notEmpty);
            success = false;
        }else{
          if(!ValidationPool.checkDateFormat(prevent_doc_date)){
            prevent_doc_date_=(truong + SystemMessageProperties.getSystemProperty("v3_date_format"));
            success = false;
          } else if(!ValidationPool.compareToday(prevent_doc_date)){
              prevent_doc_date_=(truong + SystemMessageProperties.getSystemProperty("v3_ngay_cv")+SystemMessageProperties.getSystemProperty("v3_compare_today"));
                success = false;
            }
        }
        if (prevent_doc_summary == null || prevent_doc_summary.equals("")){
            prevent_doc_summary_=(truong + SystemMessageProperties.getSystemProperty("v3_trich_yeu") + notEmpty);
            success = false;
        }else if(!prevent_doc_summary.matches(ValidationPool.length(1,1000))){
            prevent_doc_summary_=(truong + SystemMessageProperties.getSystemProperty("v3_trich_yeu") + max_length+EditString.SPACE + 1000 + ky_tu);
            success = false;
        }

        if(prevent_note != null && !prevent_note.equals("")){
            if(!prevent_note.matches(ValidationPool.length(1,1000))){
                prevent_note_=(truong + SystemMessageProperties.getSystemProperty("v3_ghi_chu") + max_length + EditString.SPACE+ 1000 + ky_tu);
                success = false;
            }
        }
        if(prevent_doc_receive_date != null && !prevent_doc_receive_date.equals("")){
            if(!ValidationPool.checkDateFormat(prevent_doc_receive_date)){
                prevent_doc_receive_date_=(truong + SystemMessageProperties.getSystemProperty("v3_date_format"));
                success = false;
            }else if(!ValidationPool.compareToday(prevent_doc_receive_date)){
              prevent_doc_receive_date_=(truong + SystemMessageProperties.getSystemProperty("v3_ngay_nhan")+SystemMessageProperties.getSystemProperty("v3_compare_today"));
              success = false;
            }
        }
        if(prevent_input_date != null && !prevent_input_date.equals("")){
            if(!ValidationPool.checkDateFormat(prevent_input_date)){
                prevent_input_date_=(truong + SystemMessageProperties.getSystemProperty("v3_date_format"));
                success = false;
            }else if(!ValidationPool.compareToday(prevent_input_date)){
              prevent_input_date_=(truong + SystemMessageProperties.getSystemProperty("v3_ngay_nhap")+SystemMessageProperties.getSystemProperty("v3_compare_today"));
              success = false;
            }
        }

    }
}

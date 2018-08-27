package com.vn.osp.modelview;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vn.osp.common.global.Constants;
import com.vn.osp.common.util.EditString;
import com.vn.osp.common.util.RelateDateTime;
import com.vn.osp.common.util.SystemMessageProperties;
import com.vn.osp.common.util.ValidationPool;
import org.json.JSONObject;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by tranv on 11/22/2016.
 */
public class DataPreventInfor {
    private Integer prevent_id;
    private String synchronize_id;
    private Integer property_id;
    private String origin_kind;
    private Integer delete_flg;
    private String prevent_regist_agency;
    private String prevent_in_book_number;
    private String prevent_person_info;
    private String prevent_doc_number;
    private String prevent_doc_number_to;
    private String prevent_doc_date;
    private String prevent_doc_receive_date;
    private String prevent_input_date;
    private String prevent_doc_summary;
    private String prevent_file_name;
    private String prevent_file_path;
    private String prevent_note;
    private Integer release_flg;
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
    private Integer entry_user_id;
    private Integer leader_id;
    private Integer divison_id;
    private String entry_user_name;
    private String entry_date_time;
    private Integer update_user_id;
    private String update_user_name;
    private String update_date_time;
    private Integer status;

    @JsonIgnore
    private MultipartFile preventFile;
    @JsonIgnore
    private MultipartFile releaseFile;

    //property
    private String type;
    private String property_info;
    private String owner_info;
    private String other_info;
    private String land_certificate;
    private String land_issue_place;
    private String land_issue_date;
    private String land_paper_number;
    private String land_map_number;
    private String land_number;
    private String land_address;
    private String land_area;
    private String land_public_area;
    private String land_private_area;
    private String land_use_purpose;
    private String land_use_period;
    private String land_use_origin;
    private String land_associate_property;
    private String land_street;
    private String land_district;
    private String land_province;
    private String car_license_number;
    private String car_regist_number;
    private String car_issue_place;
    private String car_issue_date;
    private String car_frame_number;
    private String car_machine_number;

    //History List
    @JsonIgnore
    private List<PreventHistory> preventHistories;
    //User List
    @JsonIgnore
    private List<User> leaders;
    //Unit request
    @JsonIgnore
    private List<UnitRequest> unitRequests;

    //province code
    private String province_code;

    //2 truong date them vao ES
    private String prevent_doc_receive_date_es;
    private String update_date_time_es;

    //validate
    @JsonIgnore
    private Boolean success;
    @JsonIgnore
    private Integer prevent_id_;
    @JsonIgnore
    private String synchronize_id_;
    @JsonIgnore
    private Integer property_id_;
    @JsonIgnore
    private String origin_kind_;
    @JsonIgnore
    private Integer delete_flg_;
    @JsonIgnore
    private String prevent_regist_agency_;
    @JsonIgnore
    private String prevent_in_book_number_;
    @JsonIgnore
    private String prevent_person_info_;
    @JsonIgnore
    private String prevent_doc_number_;
    @JsonIgnore
    private String prevent_doc_number_to_;
    @JsonIgnore
    private String prevent_doc_date_;
    @JsonIgnore
    private String prevent_doc_receive_date_;
    @JsonIgnore
    private String prevent_input_date_;
    @JsonIgnore
    private String prevent_doc_summary_;
    @JsonIgnore
    private String prevent_file_name_;
    @JsonIgnore
    private String prevent_file_path_;
    @JsonIgnore
    private String prevent_note_;
    @JsonIgnore
    private Integer release_flg_;
    @JsonIgnore
    private String release_regist_agency_;
    @JsonIgnore
    private String release_in_book_number_;
    @JsonIgnore
    private String release_person_info_;
    @JsonIgnore
    private String release_doc_number_;
    @JsonIgnore
    private String release_doc_date_;
    @JsonIgnore
    private String release_doc_receive_date_;
    @JsonIgnore
    private String release_input_date_;
    @JsonIgnore
    private String release_doc_summary_;
    @JsonIgnore
    private String release_file_name_;
    @JsonIgnore
    private String release_file_path_;
    @JsonIgnore
    private String release_note_;
    @JsonIgnore
    private Integer entry_user_id_;
    @JsonIgnore
    private Integer leader_id_;
    @JsonIgnore
    private Integer divison_id_;
    @JsonIgnore
    private String entry_user_name_;
    @JsonIgnore
    private String entry_date_time_;
    @JsonIgnore
    private Integer update_user_id_;
    @JsonIgnore
    private String update_user_name_;
    @JsonIgnore
    private String update_date_time_;
    @JsonIgnore
    private Integer status_;

    @JsonIgnore
    private String type_;
    @JsonIgnore
    private String property_info_;
    @JsonIgnore
    private String owner_info_;
    @JsonIgnore
    private String other_info_;
    @JsonIgnore
    private String land_certificate_;
    @JsonIgnore
    private String land_issue_place_;
    @JsonIgnore
    private String land_issue_date_;
    @JsonIgnore
    private String land_paper_number_;
    @JsonIgnore
    private String land_map_number_;
    @JsonIgnore
    private String land_number_;
    @JsonIgnore
    private String land_address_;
    @JsonIgnore
    private String land_area_;
    @JsonIgnore
    private String land_public_area_;
    @JsonIgnore
    private String land_private_area_;
    @JsonIgnore
    private String land_use_purpose_;
    @JsonIgnore
    private String land_use_period_;
    @JsonIgnore
    private String land_use_origin_;
    @JsonIgnore
    private String land_associate_property_;
    @JsonIgnore
    private String land_street_;
    @JsonIgnore
    private String land_district_;
    @JsonIgnore
    private String land_province_;
    @JsonIgnore
    private String car_license_number_;
    @JsonIgnore
    private String car_regist_number_;
    @JsonIgnore
    private String car_issue_place_;
    @JsonIgnore
    private String car_issue_date_;
    @JsonIgnore
    private String car_frame_number_;
    @JsonIgnore
    private String car_machine_number_;

    @JsonIgnore
    private String action_status;


    private String property_info_disp;

    @JsonIgnore
    public DataPrevent getDataPrevent() {
        DataPrevent dataPrevent = new DataPrevent();
        dataPrevent.setId(Long.valueOf(prevent_id));
        dataPrevent.setSynchronize_id(synchronize_id);
        dataPrevent.setProperty_id(Long.valueOf(property_id));
        dataPrevent.setOrigin_kind(origin_kind);
        dataPrevent.setDelete_flg(Long.valueOf(delete_flg));
        dataPrevent.setPrevent_regist_agency(prevent_regist_agency);
        dataPrevent.setPrevent_in_book_number(prevent_in_book_number);
        dataPrevent.setPrevent_person_info(prevent_person_info);
        dataPrevent.setPrevent_doc_number(prevent_doc_number);
        dataPrevent.setPrevent_doc_date(prevent_doc_date);
        dataPrevent.setPrevent_doc_receive_date(prevent_doc_receive_date);
        dataPrevent.setPrevent_input_date(prevent_input_date);
        dataPrevent.setPrevent_doc_summary(prevent_doc_summary);
        dataPrevent.setPrevent_file_name(prevent_file_name);
        dataPrevent.setPrevent_file_path(prevent_file_path);
        dataPrevent.setPrevent_note(prevent_note);
        dataPrevent.setRelease_flg(Long.valueOf(release_flg));
        dataPrevent.setRelease_regist_agency(release_regist_agency);
        dataPrevent.setRelease_in_book_number(release_in_book_number);
        dataPrevent.setRelease_person_info(release_person_info);
        dataPrevent.setRelease_doc_number(release_doc_number);
        dataPrevent.setRelease_doc_date(release_doc_date);
        dataPrevent.setRelease_doc_receive_date(release_doc_receive_date);
        dataPrevent.setRelease_input_date(release_input_date);
        dataPrevent.setRelease_doc_summary(release_doc_summary);
        ;
        dataPrevent.setRelease_file_name(release_file_name);
        dataPrevent.setRelease_file_path(release_file_path);
        dataPrevent.setRelease_note(release_note);
        dataPrevent.setEntry_user_id(entry_user_id == null ? null : Long.valueOf(entry_user_id));
        dataPrevent.setLeader_id(leader_id == null ? null : Long.valueOf(leader_id));
        dataPrevent.setDivison_id(divison_id == null ? null : Long.valueOf(divison_id));
        dataPrevent.setEntry_user_name(entry_user_name);
        dataPrevent.setEntry_date_time(entry_date_time);
        dataPrevent.setUpdate_user_id(update_user_id == null ? null : Long.valueOf(update_user_id));
        dataPrevent.setUpdate_user_name(update_user_name);
        dataPrevent.setUpdate_date_time(update_date_time);
        dataPrevent.setStatus(Long.valueOf(status));
        return dataPrevent;
    }

    @JsonIgnore
    public PropertyPrevent getPropertyPrevent() {
        PropertyPrevent propertyPrevent = new PropertyPrevent();
        propertyPrevent.setId(Long.valueOf(property_id));
        propertyPrevent.setType(type);
        propertyPrevent.setProperty_info(property_info);
        propertyPrevent.setOwner_info(owner_info);
        propertyPrevent.setOther_info(other_info);
        propertyPrevent.setLand_certificate(land_certificate);
        propertyPrevent.setLand_issue_place(land_issue_place);
        propertyPrevent.setLand_issue_date(land_issue_date);
        propertyPrevent.setLand_paper_number(land_paper_number);
        propertyPrevent.setLand_map_number(land_map_number);
        propertyPrevent.setLand_number(land_number);
        propertyPrevent.setLand_address(land_address);
        propertyPrevent.setLand_area(land_area);
        propertyPrevent.setLand_public_area(land_public_area);
        propertyPrevent.setLand_private_area(land_private_area);
        propertyPrevent.setLand_use_purpose(land_use_purpose);
        propertyPrevent.setLand_use_period(land_use_period);
        propertyPrevent.setLand_use_origin(land_use_origin);
        propertyPrevent.setLand_associate_property(land_associate_property);
        propertyPrevent.setLand_street(land_street);
        propertyPrevent.setLand_district(land_district);
        propertyPrevent.setLand_province(land_province);
        propertyPrevent.setCar_license_number(car_license_number);
        propertyPrevent.setCar_regist_number(car_regist_number);
        propertyPrevent.setCar_issue_place(car_issue_place);
        propertyPrevent.setCar_issue_date(car_issue_date);
        propertyPrevent.setCar_frame_number(car_frame_number);
        propertyPrevent.setCar_machine_number(car_machine_number);
        propertyPrevent.genneratePropertyInfor();
        return propertyPrevent;
    }


    @JsonCreator
    public DataPreventInfor(@JsonProperty(value = "prevent_id") final Integer prevent_id,
                            @JsonProperty(value = "synchronize_id") final String synchronize_id,
                            @JsonProperty(value = "property_id") final Integer property_id,
                            @JsonProperty(value = "origin_kind") final String origin_kind,
                            @JsonProperty(value = "delete_flg") final Integer delete_flg,
                            @JsonProperty(value = "prevent_regist_agency") final String prevent_regist_agency,
                            @JsonProperty(value = "prevent_in_book_number") final String prevent_in_book_number,
                            @JsonProperty(value = "prevent_person_info") final String prevent_person_info,
                            @JsonProperty(value = "prevent_doc_number") final String prevent_doc_number,
                            @JsonProperty(value = "prevent_doc_number_to") final String prevent_doc_number_to,
                            @JsonProperty(value = "prevent_doc_date") final String prevent_doc_date,
                            @JsonProperty(value = "prevent_doc_receive_date") final String prevent_doc_receive_date,
                            @JsonProperty(value = "prevent_input_date") final String prevent_input_date,
                            @JsonProperty(value = "prevent_doc_summary") final String prevent_doc_summary,
                            @JsonProperty(value = "prevent_file_name") final String prevent_file_name,
                            @JsonProperty(value = "prevent_file_path") final String prevent_file_path,
                            @JsonProperty(value = "prevent_note") final String prevent_note,
                            @JsonProperty(value = "release_flg") final Integer release_flg,
                            @JsonProperty(value = "release_regist_agency") final String release_regist_agency,
                            @JsonProperty(value = "release_in_book_number") final String release_in_book_number,
                            @JsonProperty(value = "release_person_info") final String release_person_info,
                            @JsonProperty(value = "release_doc_number") final String release_doc_number,
                            @JsonProperty(value = "release_doc_date") final String release_doc_date,
                            @JsonProperty(value = "release_doc_receive_date") final String release_doc_receive_date,
                            @JsonProperty(value = "release_input_date") final String release_input_date,
                            @JsonProperty(value = "release_doc_summary") final String release_doc_summary,
                            @JsonProperty(value = "release_file_name") final String release_file_name,
                            @JsonProperty(value = "id") final String release_file_path,
                            @JsonProperty(value = "release_file_path") final String release_note,
                            @JsonProperty(value = "entry_user_id") final Integer entry_user_id,
                            @JsonProperty(value = "leader_id") final Integer leader_id,
                            @JsonProperty(value = "divison_id") final Integer divison_id,
                            @JsonProperty(value = "entry_user_name") final String entry_user_name,
                            @JsonProperty(value = "entry_date_time") final String entry_date_time,
                            @JsonProperty(value = "update_user_id") final Integer update_user_id,
                            @JsonProperty(value = "update_user_name") final String update_user_name,
                            @JsonProperty(value = "update_date_time") final String update_date_time,
                            @JsonProperty(value = "status") final Integer status,

                            @JsonProperty(value = "type") final String type,
                            @JsonProperty(value = "property_info") final String property_info,
                            @JsonProperty(value = "owner_info") final String owner_info,
                            @JsonProperty(value = "other_info") final String other_info,
                            @JsonProperty(value = "land_certificate") final String land_certificate,
                            @JsonProperty(value = "land_issue_place") final String land_issue_place,
                            @JsonProperty(value = "land_issue_date") final String land_issue_date,
                            @JsonProperty(value = "land_paper_number") final String land_paper_number,
                            @JsonProperty(value = "land_map_number") final String land_map_number,
                            @JsonProperty(value = "land_number") final String land_number,
                            @JsonProperty(value = "land_address") final String land_address,
                            @JsonProperty(value = "land_area") final String land_area,
                            @JsonProperty(value = "land_public_area") final String land_public_area,
                            @JsonProperty(value = "land_private_area") final String land_private_area,
                            @JsonProperty(value = "land_use_purpose") final String land_use_purpose,
                            @JsonProperty(value = "land_use_period") final String land_use_period,
                            @JsonProperty(value = "land_use_origin") final String land_use_origin,
                            @JsonProperty(value = "land_associate_property") final String land_associate_property,
                            @JsonProperty(value = "land_street") final String land_street,
                            @JsonProperty(value = "land_district") final String land_district,
                            @JsonProperty(value = "land_province") final String land_province,
                            @JsonProperty(value = "car_license_number") final String car_license_number,
                            @JsonProperty(value = "car_regist_number") final String car_regist_number,
                            @JsonProperty(value = "car_issue_place") final String car_issue_place,
                            @JsonProperty(value = "car_issue_date") final String car_issue_date,
                            @JsonProperty(value = "car_frame_number") final String car_frame_number,
                            @JsonProperty(value = "car_machine_number") final String car_machine_number) {
        this.prevent_id = prevent_id;
        this.synchronize_id = synchronize_id;
        this.property_id = property_id;
        this.origin_kind = origin_kind;
        this.delete_flg = delete_flg;
        this.prevent_regist_agency = prevent_regist_agency;
        this.prevent_in_book_number = prevent_in_book_number;
        this.prevent_person_info = prevent_person_info;
        this.prevent_doc_number = prevent_doc_number;
        this.prevent_doc_number_to = prevent_doc_number_to;
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
        this.type = type;
        this.property_info = property_info;
        this.owner_info = owner_info;
        this.other_info = other_info;
        this.land_certificate = land_certificate;
        this.land_issue_place = land_issue_place;
        this.land_issue_date = land_issue_date;
        this.land_paper_number = land_paper_number;
        this.land_map_number = land_map_number;
        this.land_number = land_number;
        this.land_address = land_address;
        this.land_area = land_area;
        this.land_public_area = land_public_area;
        this.land_private_area = land_private_area;
        this.land_use_purpose = land_use_purpose;
        this.land_use_period = land_use_period;
        this.land_use_origin = land_use_origin;
        this.land_associate_property = land_associate_property;
        this.land_street = land_street;
        this.land_district = land_district;
        this.land_province = land_province;
        this.car_license_number = car_license_number;
        this.car_regist_number = car_regist_number;
        this.car_issue_place = car_issue_place;
        this.car_issue_date = car_issue_date;
        this.car_frame_number = car_frame_number;
        this.car_machine_number = car_machine_number;
    }

    public DataPreventInfor() {
    }

    public Integer getPrevent_id() {
        return prevent_id;
    }

    public void setPrevent_id(Integer prevent_id) {
        this.prevent_id = prevent_id;
    }

    public String getSynchronize_id() {
        return synchronize_id;
    }

    public void setSynchronize_id(String synchronize_id) {
        this.synchronize_id = synchronize_id;
    }

    public Integer getProperty_id() {
        return property_id;
    }

    public void setProperty_id(Integer property_id) {
        this.property_id = property_id;
    }

    public String getOrigin_kind() {
        return origin_kind;
    }

    public void setOrigin_kind(String origin_kind) {
        this.origin_kind = origin_kind;
    }

    public Integer getDelete_flg() {
        return delete_flg;
    }

    public void setDelete_flg(Integer delete_flg) {
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

    public Integer getRelease_flg() {
        return release_flg;
    }

    public void setRelease_flg(Integer release_flg) {
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

    public Integer getEntry_user_id() {
        return entry_user_id;
    }

    public void setEntry_user_id(Integer entry_user_id) {
        this.entry_user_id = entry_user_id;
    }

    public Integer getLeader_id() {
        return leader_id;
    }

    public void setLeader_id(Integer leader_id) {
        this.leader_id = leader_id;
    }

    public Integer getDivison_id() {
        return divison_id;
    }

    public void setDivison_id(Integer divison_id) {
        this.divison_id = divison_id;
    }

    public String getEntry_user_name() {
        return entry_user_name;
    }

    public void setEntry_user_name(String entry_user_name) {
        this.entry_user_name = entry_user_name;
    }

    public Integer getUpdate_user_id() {
        return update_user_id;
    }

    public void setUpdate_user_id(Integer update_user_id) {
        this.update_user_id = update_user_id;
    }

    public String getUpdate_user_name() {
        return update_user_name;
    }

    public void setUpdate_user_name(String update_user_name) {
        this.update_user_name = update_user_name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProperty_info() {
        return property_info;
    }

    public void setProperty_info(String property_info) {
        this.property_info = property_info;
    }

    public String getOwner_info() {
        return owner_info;
    }

    public void setOwner_info(String owner_info) {
        this.owner_info = owner_info;
    }

    public String getOther_info() {
        return other_info;
    }

    public void setOther_info(String other_info) {
        this.other_info = other_info;
    }

    public String getLand_certificate() {
        return land_certificate;
    }

    public void setLand_certificate(String land_certificate) {
        this.land_certificate = land_certificate;
    }

    public String getLand_issue_place() {
        return land_issue_place;
    }

    public void setLand_issue_place(String land_issue_place) {
        this.land_issue_place = land_issue_place;
    }

    public String getLand_map_number() {
        return land_map_number;
    }

    public void setLand_map_number(String land_map_number) {
        this.land_map_number = land_map_number;
    }

    public String getLand_number() {
        return land_number;
    }

    public void setLand_number(String land_number) {
        this.land_number = land_number;
    }

    public String getLand_address() {
        return land_address;
    }

    public void setLand_address(String land_address) {
        this.land_address = land_address;
    }

    public String getLand_area() {
        return land_area;
    }

    public void setLand_area(String land_area) {
        this.land_area = land_area;
    }

    public String getLand_public_area() {
        return land_public_area;
    }

    public void setLand_public_area(String land_public_area) {
        this.land_public_area = land_public_area;
    }

    public String getLand_private_area() {
        return land_private_area;
    }

    public void setLand_private_area(String land_private_area) {
        this.land_private_area = land_private_area;
    }

    public String getLand_use_purpose() {
        return land_use_purpose;
    }

    public void setLand_use_purpose(String land_use_purpose) {
        this.land_use_purpose = land_use_purpose;
    }

    public String getLand_use_period() {
        return land_use_period;
    }

    public void setLand_use_period(String land_use_period) {
        this.land_use_period = land_use_period;
    }

    public String getLand_use_origin() {
        return land_use_origin;
    }

    public void setLand_use_origin(String land_use_origin) {
        this.land_use_origin = land_use_origin;
    }

    public String getLand_associate_property() {
        return land_associate_property;
    }

    public void setLand_associate_property(String land_associate_property) {
        this.land_associate_property = land_associate_property;
    }

    public String getLand_street() {
        return land_street;
    }

    public void setLand_street(String land_street) {
        this.land_street = land_street;
    }

    public String getLand_district() {
        return land_district;
    }

    public void setLand_district(String land_district) {
        this.land_district = land_district;
    }

    public String getLand_province() {
        return land_province;
    }

    public void setLand_province(String land_province) {
        this.land_province = land_province;
    }

    public String getCar_license_number() {
        return car_license_number;
    }

    public void setCar_license_number(String car_license_number) {
        this.car_license_number = car_license_number;
    }

    public String getCar_regist_number() {
        return car_regist_number;
    }

    public void setCar_regist_number(String car_regist_number) {
        this.car_regist_number = car_regist_number;
    }

    public String getCar_issue_place() {
        return car_issue_place;
    }

    public void setCar_issue_place(String car_issue_place) {
        this.car_issue_place = car_issue_place;
    }


    public String getCar_frame_number() {
        return car_frame_number;
    }

    public void setCar_frame_number(String car_frame_number) {
        this.car_frame_number = car_frame_number;
    }

    public String getCar_machine_number() {
        return car_machine_number;
    }

    public void setCar_machine_number(String car_machine_number) {
        this.car_machine_number = car_machine_number;
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

    public String getEntry_date_time() {
        return entry_date_time;
    }

    public void setEntry_date_time(String entry_date_time) {
        this.entry_date_time = entry_date_time;
    }

    public String getUpdate_date_time() {
        return update_date_time;
    }

    public void setUpdate_date_time(String update_date_time) {
        this.update_date_time = update_date_time;
    }

    public String getLand_issue_date() {
        return land_issue_date;
    }

    public void setLand_issue_date(String land_issue_date) {
        this.land_issue_date = land_issue_date;
    }

    public String getCar_issue_date() {
        return car_issue_date;
    }

    public void setCar_issue_date(String car_issue_date) {
        this.car_issue_date = car_issue_date;
    }

    public MultipartFile getPreventFile() {
        return preventFile;
    }

    public void setPreventFile(MultipartFile preventFile) {
        this.preventFile = preventFile;
    }

    public MultipartFile getReleaseFile() {
        return releaseFile;
    }

    public void setReleaseFile(MultipartFile releaseFile) {
        this.releaseFile = releaseFile;
    }

    public List<PreventHistory> getPreventHistories() {
        return preventHistories;
    }

    public void setPreventHistories(List<PreventHistory> preventHistories) {
        this.preventHistories = preventHistories;
    }

    public String getLand_paper_number() {
        return land_paper_number;
    }

    public void setLand_paper_number(String land_paper_number) {
        this.land_paper_number = land_paper_number;
    }

    public List<User> getLeaders() {
        return leaders;
    }

    public void setLeaders(List<User> leaders) {
        this.leaders = leaders;
    }

    public List<UnitRequest> getUnitRequests() {
        return unitRequests;
    }

    public void setUnitRequests(List<UnitRequest> unitRequests) {
        this.unitRequests = unitRequests;
    }

    public String getProvince_code() {
        return province_code;
    }

    public void setProvince_code(String province_code) {
        this.province_code = province_code;
    }

    public String getPrevent_doc_receive_date_es() {
        return prevent_doc_receive_date_es;
    }

    public void setPrevent_doc_receive_date_es(String prevent_doc_receive_date_es) {
        this.prevent_doc_receive_date_es = prevent_doc_receive_date_es;
    }

    public String getPrevent_doc_number_to_() {
        return prevent_doc_number_to_;
    }

    public void setPrevent_doc_number_to_(String prevent_doc_number_to_) {
        this.prevent_doc_number_to_ = prevent_doc_number_to_;
    }

    public String getPrevent_doc_number_to() {

        return prevent_doc_number_to;
    }

    public void setPrevent_doc_number_to(String prevent_doc_number_to) {
        this.prevent_doc_number_to = prevent_doc_number_to;
    }

    public String getUpdate_date_time_es() {
        return update_date_time_es;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Integer getPrevent_id_() {
        return prevent_id_;
    }

    public void setPrevent_id_(Integer prevent_id_) {
        this.prevent_id_ = prevent_id_;
    }

    public String getSynchronize_id_() {
        return synchronize_id_;
    }

    public void setSynchronize_id_(String synchronize_id_) {
        this.synchronize_id_ = synchronize_id_;
    }

    public Integer getProperty_id_() {
        return property_id_;
    }

    public void setProperty_id_(Integer property_id_) {
        this.property_id_ = property_id_;
    }

    public String getOrigin_kind_() {
        return origin_kind_;
    }

    public void setOrigin_kind_(String origin_kind_) {
        this.origin_kind_ = origin_kind_;
    }

    public Integer getDelete_flg_() {
        return delete_flg_;
    }

    public void setDelete_flg_(Integer delete_flg_) {
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

    public Integer getRelease_flg_() {
        return release_flg_;
    }

    public void setRelease_flg_(Integer release_flg_) {
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

    public Integer getEntry_user_id_() {
        return entry_user_id_;
    }

    public void setEntry_user_id_(Integer entry_user_id_) {
        this.entry_user_id_ = entry_user_id_;
    }

    public Integer getLeader_id_() {
        return leader_id_;
    }

    public void setLeader_id_(Integer leader_id_) {
        this.leader_id_ = leader_id_;
    }

    public Integer getDivison_id_() {
        return divison_id_;
    }

    public void setDivison_id_(Integer divison_id_) {
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

    public Integer getUpdate_user_id_() {
        return update_user_id_;
    }

    public void setUpdate_user_id_(Integer update_user_id_) {
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

    public Integer getStatus_() {
        return status_;
    }

    public void setStatus_(Integer status_) {
        this.status_ = status_;
    }

    public String getType_() {
        return type_;
    }

    public void setType_(String type_) {
        this.type_ = type_;
    }

    public String getProperty_info_() {
        return property_info_;
    }

    public void setProperty_info_(String property_info_) {
        this.property_info_ = property_info_;
    }

    public String getOwner_info_() {
        return owner_info_;
    }

    public void setOwner_info_(String owner_info_) {
        this.owner_info_ = owner_info_;
    }

    public String getOther_info_() {
        return other_info_;
    }

    public void setOther_info_(String other_info_) {
        this.other_info_ = other_info_;
    }

    public String getLand_certificate_() {
        return land_certificate_;
    }

    public void setLand_certificate_(String land_certificate_) {
        this.land_certificate_ = land_certificate_;
    }

    public String getLand_issue_place_() {
        return land_issue_place_;
    }

    public void setLand_issue_place_(String land_issue_place_) {
        this.land_issue_place_ = land_issue_place_;
    }

    public String getLand_issue_date_() {
        return land_issue_date_;
    }

    public void setLand_issue_date_(String land_issue_date_) {
        this.land_issue_date_ = land_issue_date_;
    }

    public String getLand_paper_number_() {
        return land_paper_number_;
    }

    public void setLand_paper_number_(String land_paper_number_) {
        this.land_paper_number_ = land_paper_number_;
    }

    public String getLand_map_number_() {
        return land_map_number_;
    }

    public void setLand_map_number_(String land_map_number_) {
        this.land_map_number_ = land_map_number_;
    }

    public String getLand_number_() {
        return land_number_;
    }

    public void setLand_number_(String land_number_) {
        this.land_number_ = land_number_;
    }

    public String getLand_address_() {
        return land_address_;
    }

    public void setLand_address_(String land_address_) {
        this.land_address_ = land_address_;
    }

    public String getLand_area_() {
        return land_area_;
    }

    public void setLand_area_(String land_area_) {
        this.land_area_ = land_area_;
    }

    public String getLand_public_area_() {
        return land_public_area_;
    }

    public void setLand_public_area_(String land_public_area_) {
        this.land_public_area_ = land_public_area_;
    }

    public String getLand_private_area_() {
        return land_private_area_;
    }

    public void setLand_private_area_(String land_private_area_) {
        this.land_private_area_ = land_private_area_;
    }

    public String getLand_use_purpose_() {
        return land_use_purpose_;
    }

    public void setLand_use_purpose_(String land_use_purpose_) {
        this.land_use_purpose_ = land_use_purpose_;
    }

    public String getLand_use_period_() {
        return land_use_period_;
    }

    public void setLand_use_period_(String land_use_period_) {
        this.land_use_period_ = land_use_period_;
    }

    public String getLand_use_origin_() {
        return land_use_origin_;
    }

    public void setLand_use_origin_(String land_use_origin_) {
        this.land_use_origin_ = land_use_origin_;
    }

    public String getLand_associate_property_() {
        return land_associate_property_;
    }

    public void setLand_associate_property_(String land_associate_property_) {
        this.land_associate_property_ = land_associate_property_;
    }

    public String getLand_street_() {
        return land_street_;
    }

    public void setLand_street_(String land_street_) {
        this.land_street_ = land_street_;
    }

    public String getLand_district_() {
        return land_district_;
    }

    public void setLand_district_(String land_district_) {
        this.land_district_ = land_district_;
    }

    public String getLand_province_() {
        return land_province_;
    }

    public void setLand_province_(String land_province_) {
        this.land_province_ = land_province_;
    }

    public String getCar_license_number_() {
        return car_license_number_;
    }

    public void setCar_license_number_(String car_license_number_) {
        this.car_license_number_ = car_license_number_;
    }

    public String getCar_regist_number_() {
        return car_regist_number_;
    }

    public void setCar_regist_number_(String car_regist_number_) {
        this.car_regist_number_ = car_regist_number_;
    }

    public String getCar_issue_place_() {
        return car_issue_place_;
    }

    public void setCar_issue_place_(String car_issue_place_) {
        this.car_issue_place_ = car_issue_place_;
    }

    public String getCar_issue_date_() {
        return car_issue_date_;
    }

    public void setCar_issue_date_(String car_issue_date_) {
        this.car_issue_date_ = car_issue_date_;
    }

    public String getCar_frame_number_() {
        return car_frame_number_;
    }

    public void setCar_frame_number_(String car_frame_number_) {
        this.car_frame_number_ = car_frame_number_;
    }

    public String getCar_machine_number_() {
        return car_machine_number_;
    }

    public void setCar_machine_number_(String car_machine_number_) {
        this.car_machine_number_ = car_machine_number_;
    }

    public void setUpdate_date_time_es(String update_date_time_es) {
        this.update_date_time_es = update_date_time_es;
    }

    public String getAction_status() {
        return action_status;
    }

    public void setAction_status(String action_status) {
        this.action_status = action_status;
    }

    public String getProperty_info_disp() {
        return property_info_disp;
    }

    public void setProperty_info_disp(String property_info_disp) {
        this.property_info_disp = property_info_disp;
    }

    public String loaiTaiSan() {
        if (this.type.equals(Constants.NHA_DAT)) return "Nhà đất";
        else if (this.type.equals(Constants.OTO_XEMAY)) return "Ô tô - Xe máy";
        else if (this.type.equals(Constants.TAI_SAN_KHAC)) return "Tài sản khác";
        else return "";
    }
    public void validate(){
        success = true;
        String truong = SystemMessageProperties.getSystemProperty("v3_truong") +" ";
        String notEmpty = SystemMessageProperties.getSystemProperty("v3_not_empty");
        String max_length = SystemMessageProperties.getSystemProperty("v3_max_length");
        String min_length = SystemMessageProperties.getSystemProperty("v3_min_length");
        String ky_tu = SystemMessageProperties.getSystemProperty("v3_ky_tu");
        String thap_phan = SystemMessageProperties.getSystemProperty("v3_thap_phan");
        //nha dat
        if(type.equals("01")){
            if (land_address == null || land_address.equals("")){
                land_address_=(truong + SystemMessageProperties.getSystemProperty("v3_dia_chi") + notEmpty);
                success = false;
            }else if(!land_address.matches(ValidationPool.length(1,200))){
                land_address_=(truong + SystemMessageProperties.getSystemProperty("v3_dia_chi") + max_length + EditString.SPACE+ 200 + ky_tu);
                success = false;
            }
            if (land_certificate == null || land_certificate.equals("")){
                land_certificate_=(truong + SystemMessageProperties.getSystemProperty("v3_so_giay_chung_nhan") + notEmpty);
                success = false;
            }else if(!land_certificate.matches(ValidationPool.length(1,50))){
                land_certificate_=(truong + SystemMessageProperties.getSystemProperty("v3_so_giay_chung_nhan") + max_length + EditString.SPACE+ 50 + ky_tu);
                success = false;
            }
            if (land_paper_number == null || land_paper_number.equals("")){
                land_paper_number_=(truong + SystemMessageProperties.getSystemProperty("v3_so_giay_vao_so") + notEmpty);
                success = false;
            }else if(!land_paper_number.matches(ValidationPool.length(1,50))){
                land_paper_number_=(truong + SystemMessageProperties.getSystemProperty("v3_so_giay_vao_so") + max_length + EditString.SPACE+ 50 + ky_tu);
                success = false;
            }
            if (land_use_period == null || land_use_period.equals("")){
            }else if(!land_use_period.matches(ValidationPool.length(1,200))){
                land_use_period_=(truong + SystemMessageProperties.getSystemProperty("v3_thoi_han_sd") + max_length + EditString.SPACE+ 200 + ky_tu);
                success = false;
            }
            if (land_issue_place == null || land_issue_place.equals("")){
                land_issue_place_=(truong + SystemMessageProperties.getSystemProperty("v3_noi_cap") + notEmpty);
                success = false;
            }else if(!land_issue_place.matches(ValidationPool.length(1,100))){
                land_issue_place_=(truong + SystemMessageProperties.getSystemProperty("v3_noi_cap") + max_length + EditString.SPACE+ 100 + ky_tu);
                success = false;
            }
            if (land_number == null || land_number.equals("")){
                land_number_=(truong + SystemMessageProperties.getSystemProperty("v3_thua_dat_so") + notEmpty);
                success = false;
            }else if(!land_number.matches(ValidationPool.length(1,50))){
                land_number_=(truong + SystemMessageProperties.getSystemProperty("v3_thua_dat_so") + max_length + EditString.SPACE+ 50 + ky_tu);
                success = false;
            }
            if (land_map_number == null || land_map_number.equals("")){
                land_map_number_=(truong + SystemMessageProperties.getSystemProperty("v3_to_ban_do_so") + notEmpty);
                success = false;
            }else if(!land_map_number.matches(ValidationPool.length(1,50))){
                land_map_number_=(truong + SystemMessageProperties.getSystemProperty("v3_to_ban_do_so") + max_length + EditString.SPACE+ 50 + ky_tu);
                success = false;
            }
            if (land_area == null || land_area.equals("")){
                land_area_=(truong + SystemMessageProperties.getSystemProperty("v3_dien_tich") + notEmpty);
                success = false;
            }else{
                if(!ValidationPool.checkDecimal(land_area)){
                    land_area_=(truong + SystemMessageProperties.getSystemProperty("v3_dien_tich") + thap_phan);
                    success = false;
                }
            }
            if(land_private_area != null && !land_private_area.equals("")){
                if(!ValidationPool.checkDecimal(land_private_area)){
                    land_private_area_=(truong + SystemMessageProperties.getSystemProperty("v3_rieng") + thap_phan);
                    success = false;
                }
            }
            if(land_use_purpose != null && !land_use_purpose.equals("")){
                if(!land_use_purpose.matches(ValidationPool.length(1,50))){
                    land_use_purpose_=(truong + SystemMessageProperties.getSystemProperty("v3_muc_dich_sd") + max_length + EditString.SPACE+ 500 + ky_tu);
                    success = false;
                }
            }
            if(land_public_area != null && !land_public_area.equals("")){
                if(!ValidationPool.checkDecimal(land_public_area)){
                    land_public_area_=(truong + SystemMessageProperties.getSystemProperty("v3_chung") + thap_phan);
                    success = false;
                }
            }

            if (land_use_origin == null || land_use_origin.equals("")){
                land_use_origin_=(truong + SystemMessageProperties.getSystemProperty("v3_nguoc_goc_sd") + notEmpty);
                success = false;
            }else if(!land_use_origin.matches(ValidationPool.length(1,500))){
                land_use_origin_=(truong + SystemMessageProperties.getSystemProperty("v3_nguoc_goc_sd") + max_length + EditString.SPACE+ 500 + ky_tu);
                success = false;
            }
            if(land_associate_property != null && !land_associate_property.equals("")){
                if(!land_associate_property.matches(ValidationPool.length(1,200))){
                    land_associate_property_=(truong + SystemMessageProperties.getSystemProperty("v3_ts_gan_lien_voi_dat") + max_length + EditString.SPACE+ 200 + ky_tu);
                    success = false;
                }
            }

            if(land_issue_date != null && !land_issue_date.equals("")){
                if(!ValidationPool.checkDateFormat(land_issue_date)){
                    land_issue_date_=(truong + SystemMessageProperties.getSystemProperty("v3_date_format"));
                    success = false;
                }else if(!ValidationPool.compareToday(land_issue_date)){
                    land_issue_date_=(truong + SystemMessageProperties.getSystemProperty("v3_ngay_cap")+SystemMessageProperties.getSystemProperty("v3_compare_today"));
                    success = false;
                }

            }

        }
        //Oto xe may
        if(type.equals("02")){
            if (car_license_number == null || car_license_number.equals("")){
                car_license_number_=(truong + SystemMessageProperties.getSystemProperty("v3_bien_kiem_soat") + notEmpty);
                success = false;
            }else if(!car_license_number.matches(ValidationPool.length(1,50))){
                car_license_number_=(truong + SystemMessageProperties.getSystemProperty("v3_bien_kiem_soat") + max_length + EditString.SPACE+ 50 + ky_tu);
                success = false;
            }
            if (car_regist_number == null || car_regist_number.equals("")){
                car_regist_number_=(truong + SystemMessageProperties.getSystemProperty("v3_so_giay_dky") + notEmpty);
                success = false;
            }else if(!car_regist_number.matches(ValidationPool.length(1,100))){
                car_regist_number_=(truong + SystemMessageProperties.getSystemProperty("v3_so_giay_dky") + max_length + EditString.SPACE+ 100 + ky_tu);
                success = false;
            }
            if (car_issue_place == null || car_issue_place.equals("")){
                car_issue_place_=(truong + SystemMessageProperties.getSystemProperty("v3_noi_cap") + notEmpty);
                success = false;
            }else if(!car_issue_place.matches(ValidationPool.length(1,100))){
                car_issue_place_=(truong + SystemMessageProperties.getSystemProperty("v3_noi_cap") + max_length + EditString.SPACE+ 100 + ky_tu);
                success = false;
            }
            if (car_frame_number == null || car_frame_number.equals("")){
                car_frame_number_=(truong + SystemMessageProperties.getSystemProperty("v3_so_khung") + notEmpty);
                success = false;
            }else if(!car_frame_number.matches(ValidationPool.length(1,50))){
                car_frame_number_=(truong + SystemMessageProperties.getSystemProperty("v3_so_khung") + max_length + EditString.SPACE+ 50 + ky_tu);
                success = false;
            }
            if (car_machine_number == null || car_machine_number.equals("")){
                car_machine_number_=(truong + SystemMessageProperties.getSystemProperty("v3_so_may") + notEmpty);
                success = false;
            }else if(!car_machine_number.matches(ValidationPool.length(1,50))){
                car_machine_number_=(truong + SystemMessageProperties.getSystemProperty("v3_so_may") + max_length + EditString.SPACE+ 50 + ky_tu);
                success = false;
            }
            if(car_issue_date != null && !car_issue_date.equals("")){
                if(!ValidationPool.compareToday(car_issue_date)){
                    car_issue_date_=(truong + SystemMessageProperties.getSystemProperty("v3_ngay_cap") + SystemMessageProperties.getSystemProperty("v3_compare_today"));
                    success = false;
                }
            }
        }
        //Tai san khac
        if(type.equals("99")){
            if (property_info == null || property_info.equals("")){
                property_info_=(truong + SystemMessageProperties.getSystemProperty("v3_tt_tai_san") + notEmpty);
                success = false;
            }else if(!property_info.matches(ValidationPool.length(1,1000))){
                property_info_=(truong + SystemMessageProperties.getSystemProperty("v3_tt_tai_san") + max_length + EditString.SPACE+ 1000 + ky_tu);
                success = false;
            }
        }
        if (owner_info == null || owner_info.equals("")){
            owner_info_=(truong + SystemMessageProperties.getSystemProperty("v3_thong_tin_chu_so_huu") + notEmpty);
            success = false;
        }else if(!owner_info.matches(ValidationPool.length(1,500))){
            owner_info_=(truong + SystemMessageProperties.getSystemProperty("v3_thong_tin_chu_so_huu") + max_length + EditString.SPACE+ 500 + ky_tu);
            success = false;
        }
        if(other_info != null && !other_info.equals("")){
            if(!other_info.matches(ValidationPool.length(1,1000))){
                other_info_=(truong + SystemMessageProperties.getSystemProperty("v3_ts_gan_lien_voi_dat") + max_length + EditString.SPACE+ 1000 + ky_tu);
                success = false;
            }
        }
        //prevent
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

    public void releaseValidate(){
        success = true;
        String truong = SystemMessageProperties.getSystemProperty("v3_truong") +" ";
        String notEmpty = SystemMessageProperties.getSystemProperty("v3_not_empty");
        String max_length = SystemMessageProperties.getSystemProperty("v3_max_length");
        String min_length = SystemMessageProperties.getSystemProperty("v3_min_length");
        String ky_tu = SystemMessageProperties.getSystemProperty("v3_ky_tu");
        String thap_phan = SystemMessageProperties.getSystemProperty("v3_thap_phan");
        //release
        if(release_in_book_number != null && !release_in_book_number.equals("")){
            if(!release_in_book_number.matches(ValidationPool.length(1,50))){
                release_in_book_number_=(truong + SystemMessageProperties.getSystemProperty("v3_so_vao_so_cv") + max_length + EditString.SPACE+ 50 + ky_tu);
                success = false;
            }
        }

        if (release_doc_number == null || release_doc_number.equals("")){
            release_doc_number_=(truong + SystemMessageProperties.getSystemProperty("v3_so_cv") + notEmpty);
            success = false;
        }else if(!release_doc_number.matches(ValidationPool.length(1,50))){
            release_doc_number_=(truong + SystemMessageProperties.getSystemProperty("v3_so_cv") + max_length+EditString.SPACE + 50 + ky_tu);
            success = false;
        }
        if (release_person_info == null || release_person_info.equals("")){
            release_person_info_=(truong + SystemMessageProperties.getSystemProperty("v3_prevent_dv_gui_yc") + notEmpty);
            success = false;
        }
        if (release_doc_date == null || release_doc_date.equals("")){
            release_doc_date_=(truong + SystemMessageProperties.getSystemProperty("v3_ngay_cv") + notEmpty);
            success = false;
        }else{
            if(!ValidationPool.checkDateFormat(release_doc_date)){
                release_doc_date_=(truong + SystemMessageProperties.getSystemProperty("v3_date_format"));
                success = false;
            }
            if(!ValidationPool.compareToday(release_doc_date)){
                release_doc_date_=(truong + SystemMessageProperties.getSystemProperty("v3_compare_today"));
                success = false;
            }
        }
        if (release_doc_summary == null || release_doc_summary.equals("")){
            release_doc_summary_=(truong + SystemMessageProperties.getSystemProperty("v3_trich_yeu") + notEmpty);
            success = false;
        }else if(!release_doc_summary.matches(ValidationPool.length(1,1000))){
            release_doc_summary_=(truong + SystemMessageProperties.getSystemProperty("v3_trich_yeu") + max_length+EditString.SPACE + 1000 + ky_tu);
            success = false;
        }

        if(release_note != null && !release_note.equals("")){
            if(!release_note.matches(ValidationPool.length(1,1000))){
                release_note_=(truong + SystemMessageProperties.getSystemProperty("v3_ghi_chu") + max_length + EditString.SPACE+ 1000 + ky_tu);
                success = false;
            }
        }
        if(release_doc_receive_date != null && !release_doc_receive_date.equals("")){
            if(!ValidationPool.checkDateFormat(release_doc_receive_date)){
                release_doc_receive_date_=(truong + SystemMessageProperties.getSystemProperty("v3_date_format"));
                success = false;
            }
            if(!ValidationPool.compareToday(release_doc_receive_date)){
                release_doc_receive_date_=(truong + SystemMessageProperties.getSystemProperty("v3_compare_today"));
                success = false;
            }
        }
        if(release_input_date != null && !release_input_date.equals("")){
            if(!ValidationPool.checkDateFormat(release_input_date)){
                release_input_date_=(truong + SystemMessageProperties.getSystemProperty("v3_date_format"));
                success = false;
            }
            if(!ValidationPool.compareToday(release_input_date)){
                release_input_date_=(truong + SystemMessageProperties.getSystemProperty("v3_compare_today"));
                success = false;
            }
        }
    }
    public String layThongTinTaiSan() {
        String result = "";
        if (type.equals(Constants.NHA_DAT)) {
            if (land_address != null && !land_address.equals("")) result += "Địa chỉ: " + land_address + "; ";
            if (land_certificate != null && !land_certificate.equals(""))
                result += "Số giấy chứng nhận: " + land_certificate + "; ";
            if (land_issue_place != null && !land_issue_place.equals(""))
                result += "Nơi cấp: " + land_issue_place + "; ";
            if (land_issue_date != null && !land_issue_date.equals("")) result += "Ngày cấp: " + land_issue_date + "; ";
            if (land_paper_number != null && !land_paper_number.equals(""))
                result += "Số giấy vào sổ: " + land_paper_number + "; ";
            if (land_number != null && !land_number.equals("")) result += "Thửa đất số: " + land_number + "; ";
            if (land_map_number != null && !land_map_number.equals(""))
                result += "Tờ bản đồ số: " + land_map_number + "; ";
            if (land_area != null && !land_area.equals("")) result += "Diện tích (m2): " + land_area + "; ";
            if (land_private_area != null && !land_private_area.equals(""))
                result += "Diện tích sử dụng riêng: " + land_private_area + "; ";
            if (land_public_area != null && !land_public_area.equals(""))
                result += "Diện tích sử dụng chúng: " + land_public_area+"; " ;
            if (land_use_purpose != null && !land_use_purpose.equals(""))
                result += "Mục đích sử dụng: " + land_use_purpose + "; ";
            if (land_use_period != null && !land_use_period.equals(""))
                result += "Thời hạn sử dụng: " + land_use_period + "; ";
            if (land_associate_property != null && !land_associate_property.equals(""))
                result += "Tài sản gắn liền với đất: " + land_associate_property + "; ";
            if (owner_info != null && !owner_info.equals("")) result += "Thông tin chủ sở hữu: " + owner_info + "; ";
            if (other_info != null && !other_info.equals("")) result += "Thông tin khác: " + other_info;
        }
        if (type.equals(Constants.OTO_XEMAY)) {
            if (car_license_number != null && !car_license_number.equals(""))
                result += "Biển kiểm soát: " + car_license_number + "; ";
            if (car_regist_number != null && !car_regist_number.equals(""))
                result += "Số giấy đăng ký: " + land_address + "; ";
            if (car_issue_place != null && !car_issue_place.equals("")) result += "Nơi cấp: " + land_address + "; ";
            if (car_issue_date != null && !car_issue_date.equals("")) result += "Ngày cấp: " + land_address + "; ";
            if (car_frame_number != null && !car_frame_number.equals("")) result += "Số khung: " + land_address + "; ";
            if (car_machine_number != null && !car_machine_number.equals(""))
                result += "Số máy: " + car_machine_number + "; ";
            if (owner_info != null && !owner_info.equals("")) result += "Thông tin chủ sở hữu: " + owner_info + "; ";
            if (other_info != null && !other_info.equals("")) result += "Thông tin khác: " + other_info;
        }
        if (type.equals(Constants.TAI_SAN_KHAC)) {
            if (property_info != null && !property_info.equals(""))
                result += "Thông tin tài sản: " + property_info + "; ";
            if (owner_info != null && !owner_info.equals("")) result += "Thông tin chủ sở hữu: " + owner_info + "; ";
            if (other_info != null && !other_info.equals("")) result += "Thông tin khác: " + other_info;
        }

        return result;
    }

    public String generateJson() {
        JSONObject obj = new JSONObject();
        ObjectMapper mapper = new ObjectMapper();
        try {
            if(prevent_doc_receive_date == null || prevent_doc_receive_date.equals(""))  this.setPrevent_doc_receive_date_es(null);
            else this.setUpdate_date_time_es(setESDate(update_date_time));
            ////System.out.println(mapper.writeValueAsString(this));
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String setESDate(String date) {
        try {
            if (date == null || date.equals("")) return "";
            return RelateDateTime.formatDate(date, "dd/MM/yyyy", "yyyy/MM/dd");
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}

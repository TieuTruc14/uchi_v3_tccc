package com.vn.osp.modelview;

import java.sql.Timestamp;

/**
 * Created by minh on 5/15/2017.
 */
public class AnnouncementFromStp {
    private Integer aid;
    private String synchronize_id;
    private Byte kind;
    private Byte confirm_request;
    private Byte importance_type;
    private Boolean popup_display_flg;
    private Integer popup_display_day;
    private String title;
    private String content;
    private String sender_info;
    private java.sql.Timestamp send_date_time;
    private String attach_file_name;
    private String attach_file_path;
    private Integer entry_user_id;
    private String entry_user_name;
    private java.sql.Timestamp entry_date_time;
    private Integer update_user_id;
    private String update_user_name;
    private java.sql.Timestamp update_date_time;
    private Byte announcement_type;
    private Integer rid;
    private String authentication_id;
    private String data_id;

    private String entryDateTimeConver;

    public AnnouncementFromStp() {
    }

    public String getEntryDateTimeConver() {
        return entryDateTimeConver;
    }

    public void setEntryDateTimeConver(String entryDateTimeConver) {
        this.entryDateTimeConver = entryDateTimeConver;
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public String getSynchronize_id() {
        return synchronize_id;
    }

    public void setSynchronize_id(String synchronize_id) {
        this.synchronize_id = synchronize_id;
    }

    public Byte getKind() {
        return kind;
    }

    public void setKind(Byte kind) {
        this.kind = kind;
    }

    public Byte getConfirm_request() {
        return confirm_request;
    }

    public void setConfirm_request(Byte confirm_request) {
        this.confirm_request = confirm_request;
    }

    public Byte getImportance_type() {
        return importance_type;
    }

    public void setImportance_type(Byte importance_type) {
        this.importance_type = importance_type;
    }

    public Boolean getPopup_display_flg() {
        return popup_display_flg;
    }

    public void setPopup_display_flg(Boolean popup_display_flg) {
        this.popup_display_flg = popup_display_flg;
    }

    public Integer getPopup_display_day() {
        return popup_display_day;
    }

    public void setPopup_display_day(Integer popup_display_day) {
        this.popup_display_day = popup_display_day;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSender_info() {
        return sender_info;
    }

    public void setSender_info(String sender_info) {
        this.sender_info = sender_info;
    }

    public Timestamp getSend_date_time() {
        return send_date_time;
    }

    public void setSend_date_time(Timestamp send_date_time) {
        this.send_date_time = send_date_time;
    }

    public String getAttach_file_name() {
        return attach_file_name;
    }

    public void setAttach_file_name(String attach_file_name) {
        this.attach_file_name = attach_file_name;
    }

    public String getAttach_file_path() {
        return attach_file_path;
    }

    public void setAttach_file_path(String attach_file_path) {
        this.attach_file_path = attach_file_path;
    }

    public Integer getEntry_user_id() {
        return entry_user_id;
    }

    public void setEntry_user_id(Integer entry_user_id) {
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

    public Timestamp getUpdate_date_time() {
        return update_date_time;
    }

    public void setUpdate_date_time(Timestamp update_date_time) {
        this.update_date_time = update_date_time;
    }

    public Byte getAnnouncement_type() {
        return announcement_type;
    }

    public void setAnnouncement_type(Byte announcement_type) {
        this.announcement_type = announcement_type;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public String getAuthentication_id() {
        return authentication_id;
    }

    public void setAuthentication_id(String authentication_id) {
        this.authentication_id = authentication_id;
    }

    public String getData_id() {
        return data_id;
    }

    public void setData_id(String data_id) {
        this.data_id = data_id;
    }

}

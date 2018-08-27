package com.vn.osp.modelview;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vn.osp.common.util.EditString;
import com.vn.osp.common.util.SystemMessageProperties;
import com.vn.osp.common.util.ValidationPool;
import org.json.JSONObject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by minh on 12/16/2016.
 */
public class Announcement {
    private Long aid;
    private String synchronize_id;
    private Long kind;
    private Long confirm_request;
    private Long importance_type;
    private Long popup_display_flg;
    private Long popup_display_day;
    private String title;
    private String content;
    private String sender_info;
    private String send_date_time;
    private String attach_file_name;
    private String attach_file_path;
    private Long entry_user_id;
    private String entry_user_name;
    private String entry_date_time;
    private Long update_user_id;
    private String update_user_name;
    private String update_date_time;
    private String authentication_id;
    private Long announcement_type;
    private String stringFilter;
    private String notaryOffices;

    @JsonIgnore
    private String title_;
    @JsonIgnore
    private String content_;

    @JsonIgnore
    private Boolean success;


    @JsonIgnore
    private String content_display;
    @JsonIgnore
    private MultipartFile announcementFile;

    @JsonIgnore
    private String prevent_data_;
    @JsonIgnore
    private String popup_display_day_;


    @JsonCreator
    public Announcement(
            @JsonProperty(value = "aid", required = false) final Long aid,
            @JsonProperty(value = "synchronize_id", required = false) final String synchronize_id,
            @JsonProperty(value = "kind", required = false) final Long kind,
            @JsonProperty(value = "confirm_request", required = false) final Long confirm_request,
            @JsonProperty(value = "importance_type", required = false) final Long importance_type,
            @JsonProperty(value = "popup_display_flg", required = false) final Long popup_display_flg,
            @JsonProperty(value = "popup_display_day", required = false) final Long popup_display_day,
            @JsonProperty(value = "title", required = false) final String title,
            @JsonProperty(value = "content", required = false) final String content,
            @JsonProperty(value = "send_date_time", required = false) final String send_date_time,
            @JsonProperty(value = "attach_file_name", required = false) final String attach_file_name,
            @JsonProperty(value = "attach_file_path", required = false) final String attach_file_path,
            @JsonProperty(value = "entry_user_id", required = false) final Long entry_user_id,
            @JsonProperty(value = "entry_user_name", required = false) final String entry_user_name,
            @JsonProperty(value = "entry_date_time", required = false) final String entry_date_time,
            @JsonProperty(value = "update_user_id", required = false) final Long update_user_id,
            @JsonProperty(value = "update_user_name", required = false) final String update_user_name,
            @JsonProperty(value = "update_date_time", required = false) final String update_date_time,
            @JsonProperty(value = "sender_info", required = false) final String sender_info,
            @JsonProperty(value = "announcement_type", required = false) final Long announcement_type,
            @JsonProperty(value = "stringFilter", required = false) final String stringFilter) {
        this.aid = aid;
        this.synchronize_id = synchronize_id;
        this.kind = kind;
        this.confirm_request = confirm_request;
        this.importance_type = importance_type;
        this.popup_display_flg = popup_display_flg;
        this.popup_display_day = popup_display_day;
        this.title = title;
        this.content = content;
        this.sender_info = sender_info;
        this.send_date_time = send_date_time;
        this.attach_file_name = attach_file_name;
        this.attach_file_path = attach_file_path;
        this.entry_user_id = entry_user_id;
        this.entry_user_name = entry_user_name;
        this.entry_date_time = entry_date_time;
        this.update_user_id = update_user_id;
        this.update_user_name = update_user_name;
        this.update_date_time = update_date_time;
        this.announcement_type = announcement_type;
        this.stringFilter = stringFilter;
    }

    public Announcement() {

    }

    public String getAuthentication_id() {
        return authentication_id;
    }

    public void setAuthentication_id(String authentication_id) {
        this.authentication_id = authentication_id;
    }

    public MultipartFile getAnnouncementFile() {
        return announcementFile;
    }

    public void setAnnouncementFile(MultipartFile announcementFile) {
        this.announcementFile = announcementFile;
    }

    public Long getAid() {
        return aid;
    }

    public void setAid(Long aid) {

        this.aid = aid;
    }

    public String getSynchronize_id() {
        return synchronize_id;
    }

    public void setSynchronize_id(String synchronize_id) {
        this.synchronize_id = synchronize_id;
    }

    public Long getKind() {
        return kind;
    }

    public void setKind(Long kind) {
        this.kind = kind;
    }

    public Long getConfirm_request() {
        return confirm_request;
    }

    public void setConfirm_request(Long confirm_request) {
        this.confirm_request = confirm_request;
    }

    public Long getImportance_type() {
        return importance_type;
    }

    public void setImportance_type(Long importance_type) {
        this.importance_type = importance_type;
    }

    public Long getPopup_display_flg() {
        return popup_display_flg;
    }

    public void setPopup_display_flg(Long popup_display_flg) {
        this.popup_display_flg = popup_display_flg;
    }

    public Long getPopup_display_day() {
        return popup_display_day;
    }

    public void setPopup_display_day(Long popup_display_day) {
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

    public String getSend_date_time() {
        return send_date_time;
    }

    public void setSend_date_time(String send_date_time) {
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

    public Long getAnnouncement_type() {
        return announcement_type;
    }

    public void setAnnouncement_type(Long announcement_type) {
        this.announcement_type = announcement_type;
    }

    public String getStringFilter() {
        return stringFilter;
    }

    public void setStringFilter(String stringFilter) {
        this.stringFilter = stringFilter;
    }

    public String getNotaryOffices() {
        return notaryOffices;
    }

    public void setNotaryOffices(String notaryOffices) {
        this.notaryOffices = notaryOffices;
    }

    public String getContent_display() {
        return content_display;
    }

    public void setContent_display(String content_display) {
        this.content_display = content_display;
    }

    public String getTitle_() {
        return title_;
    }

    public void setTitle_(String title_) {
        this.title_ = title_;
    }

    public String getContent_() {
        return content_;
    }

    public void setContent_(String content_) {
        this.content_ = content_;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getPrevent_data_() {
        return prevent_data_;
    }

    public void setPrevent_data_(String prevent_data_) {
        this.prevent_data_ = prevent_data_;
    }

    public String getPopup_display_day_() {
        return popup_display_day_;
    }

    public void setPopup_display_day_(String popup_display_day_) {
        this.popup_display_day_ = popup_display_day_;
    }

    public void validate() {
        success = true;
        String truong = SystemMessageProperties.getSystemProperty("v3_truong") + " ";
        String notEmpty = SystemMessageProperties.getSystemProperty("v3_not_empty");
        String max_length = SystemMessageProperties.getSystemProperty("v3_max_length");
        String min_length = SystemMessageProperties.getSystemProperty("v3_min_length");
        String ky_tu = SystemMessageProperties.getSystemProperty("v3_ky_tu");

        if (title == null || title.equals("")) {
            title_ = (truong + SystemMessageProperties.getSystemProperty("v3_title") + notEmpty);
            success = false;
        } else if (title.length() > 200) {
            title_ = (truong + SystemMessageProperties.getSystemProperty("v3_title") + max_length + EditString.SPACE + 200 + ky_tu);
            success = false;
        }
        if (content == null || content.equals("")) {
            content_ = (truong + SystemMessageProperties.getSystemProperty("v3_content") + notEmpty);
            success = false;
        } else if (content.length() > 3000) {
            content_ = (truong + SystemMessageProperties.getSystemProperty("v3_content") + EditString.SPACE + SystemMessageProperties.getSystemProperty("v3_text_too_long"));
            success = false;
        }
        if (popup_display_flg == 1) {

            if (popup_display_day == null || popup_display_day.equals("")) {
                popup_display_day_ = (SystemMessageProperties.getSystemProperty("v3_popup_display_day"));
                success = false;
            }
        }
    }
}

package com.vn.osp.modelview;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.vn.osp.common.util.EditString;
import com.vn.osp.common.util.SystemMessageProperties;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by minh on 3/27/2017.
 */
public class Manual {
    private Long id;
    private String title;
    private String content;
    private String file_name;
    private String file_path;
    private Long entry_user_id;
    private String entry_user_name;
    private String entry_date_time;
    private Long update_user_id;
    private String update_user_name;
    private String update_date_time;
    private String[] listFileName;
    @JsonIgnore
    private MultipartFile manualFile;
    @JsonIgnore
    private String title_;
    @JsonIgnore
    private String content_;
    @JsonIgnore
    private Boolean success;


    @JsonCreator
    public Manual(
            @JsonProperty(value="mid",required= true) final Long id,
            @JsonProperty(value="title", required = true) final String title,
            @JsonProperty(value="content", required= true) final String content,
            @JsonProperty(value="file_name", required= true) final String file_name,
            @JsonProperty(value="file_path", required= true) final String file_path,
            @JsonProperty(value="entry_user_id",required = true) final Long entry_user_id,
            @JsonProperty(value="entry_user_name", required= true) final String entry_user_name,
            @JsonProperty(value="entry_date_time", required = true) final String entry_date_time,
            @JsonProperty(value="update_user_id", required= true) final Long update_user_id,
            @JsonProperty(value="update_user_name",required= true) final String update_user_name,
            @JsonProperty(value="update_date_time", required=true) final String update_date_time
    ){
        this.id = id;
        this.title = title;
        this.content = content;
        this.file_name = file_name;
        this.file_path = file_path;
        this.entry_user_id = entry_user_id;
        this.entry_user_name = entry_user_name;
        this.entry_date_time = entry_date_time;
        this.update_user_id = update_user_id;
        this.update_user_name = update_user_name;
        this.update_date_time = update_date_time;
    };

    public Manual() {
    }

    public String[] getListFileName() {
        return listFileName;
    }

    public void setListFileName(String[] listFileName) {
        this.listFileName = listFileName;
    }

    public Long getMid() {
        return id;
    }

    public void setMid(Long id) {
        this.id = id;
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

    public MultipartFile getManualfile() {
        return manualFile;
    }

    public void setManualfile(MultipartFile manualFile) {
        this.manualFile = manualFile;
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

    public void validate(){
        success = true;
        String truong = SystemMessageProperties.getSystemProperty("v3_truong") +" ";
        String notEmpty = SystemMessageProperties.getSystemProperty("v3_not_empty");
        String max_length = SystemMessageProperties.getSystemProperty("v3_max_length");
        String min_length = SystemMessageProperties.getSystemProperty("v3_min_length");
        String ky_tu = SystemMessageProperties.getSystemProperty("v3_ky_tu");



        if(StringUtils.isBlank(title)){
            title_ = ( truong + SystemMessageProperties.getSystemProperty("v3_title")+ notEmpty);
            success= false;
        }else if(title.length() > 200){
            title_ = ("Trường tiêu đề có độ dài tối đa 200 kí tự !");
            success= false;
        }
        if(content == null || content.equals("")){
            content_ = ( truong + SystemMessageProperties.getSystemProperty("v3_content")+ notEmpty);
            success= false;
        } else if (content.length()>3000){
            content_ = ( truong + SystemMessageProperties.getSystemProperty("v3_content") + EditString.SPACE + SystemMessageProperties.getSystemProperty("v3_text_too_long"));
            success = false;
        }
    }
    public void validateupdate(){
        success = true;
        String truong = SystemMessageProperties.getSystemProperty("v3_truong") +" ";
        String notEmpty = SystemMessageProperties.getSystemProperty("v3_not_empty");
        String max_length = SystemMessageProperties.getSystemProperty("v3_max_length");
        String min_length = SystemMessageProperties.getSystemProperty("v3_min_length");
        String ky_tu = SystemMessageProperties.getSystemProperty("v3_ky_tu");

        if(title == null || title.equals("")){
            title_= (truong+ SystemMessageProperties.getSystemProperty("v3_title")+ notEmpty);
            success= false;
        } else if (title.length()>200){
            title_ = ( truong+ SystemMessageProperties.getSystemProperty("v3_title")+ max_length + EditString.SPACE + 200 + ky_tu);
            success = false;
        }
        if(content == null || content.equals("")){
            content_ = ( truong + SystemMessageProperties.getSystemProperty("v3_content")+ notEmpty);
            success= false;
        } else if (content.length()>3000){
            content_ = ( truong + SystemMessageProperties.getSystemProperty("v3_content") + EditString.SPACE +SystemMessageProperties.getSystemProperty("v3_text_too_long"));
            success = false;
        }
    }
    public JSONObject generateJsonObject(User user){
        JSONObject obj = new JSONObject();
        AddUnitRequest addUnitRequest =new AddUnitRequest();

        try {
            obj.put("id", id);
            obj.put("title", title);
            obj.put("content", content);
            obj.put("file_name", file_name);
            obj.put("file_path", file_path);
            obj.put("update_user_id", user.getUserId());
            obj.put("update_user_name", user.getAccount());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Manual manual = (Manual) o;

        if (id != null ? !id.equals(manual.id) : manual.id != null) return false;
        if (title != null ? !title.equals(manual.title) : manual.title != null) return false;
        if (content != null ? !content.equals(manual.content) : manual.content != null) return false;
        if (file_name != null ? !file_name.equals(manual.file_name) : manual.file_name != null) return false;
        if (file_path != null ? !file_path.equals(manual.file_path) : manual.file_path != null) return false;
        if (entry_user_id != null ? !entry_user_id.equals(manual.entry_user_id) : manual.entry_user_id != null)
            return false;
        if (entry_user_name != null ? !entry_user_name.equals(manual.entry_user_name) : manual.entry_user_name != null)
            return false;
        if (entry_date_time != null ? !entry_date_time.equals(manual.entry_date_time) : manual.entry_date_time != null)
            return false;
        if (update_user_id != null ? !update_user_id.equals(manual.update_user_id) : manual.update_user_id != null)
            return false;
        if (update_user_name != null ? !update_user_name.equals(manual.update_user_name) : manual.update_user_name != null)
            return false;
        if (update_date_time != null ? !update_date_time.equals(manual.update_date_time) : manual.update_date_time != null)
            return false;
        if (manualFile != null ? !manualFile.equals(manual.manualFile) : manual.manualFile != null) return false;
        if (title_ != null ? !title_.equals(manual.title_) : manual.title_ != null) return false;
        if (content_ != null ? !content_.equals(manual.content_) : manual.content_ != null) return false;
        return success != null ? success.equals(manual.success) : manual.success == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (file_name != null ? file_name.hashCode() : 0);
        result = 31 * result + (file_path != null ? file_path.hashCode() : 0);
        result = 31 * result + (entry_user_id != null ? entry_user_id.hashCode() : 0);
        result = 31 * result + (entry_user_name != null ? entry_user_name.hashCode() : 0);
        result = 31 * result + (entry_date_time != null ? entry_date_time.hashCode() : 0);
        result = 31 * result + (update_user_id != null ? update_user_id.hashCode() : 0);
        result = 31 * result + (update_user_name != null ? update_user_name.hashCode() : 0);
        result = 31 * result + (update_date_time != null ? update_date_time.hashCode() : 0);
        result = 31 * result + (manualFile != null ? manualFile.hashCode() : 0);
        result = 31 * result + (title_ != null ? title_.hashCode() : 0);
        result = 31 * result + (content_ != null ? content_.hashCode() : 0);
        result = 31 * result + (success != null ? success.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Manual{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", file_name='" + file_name + '\'' +
                ", file_path='" + file_path + '\'' +
                ", entry_user_id=" + entry_user_id +
                ", entry_user_name='" + entry_user_name + '\'' +
                ", entry_date_time='" + entry_date_time + '\'' +
                ", update_user_id=" + update_user_id +
                ", update_user_name='" + update_user_name + '\'' +
                ", update_date_time='" + update_date_time + '\'' +
                ", manualFile=" + manualFile +
                ", title_='" + title_ + '\'' +
                ", content_='" + content_ + '\'' +
                ", success=" + success +
                '}';
    }
}

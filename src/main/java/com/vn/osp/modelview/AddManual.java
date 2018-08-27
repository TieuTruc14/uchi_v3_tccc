package com.vn.osp.modelview;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.vn.osp.common.util.EditString;
import com.vn.osp.common.util.SystemMessageProperties;
import org.json.JSONObject;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by minh on 3/27/2017.
 */
public class AddManual {
    public static final String SESSION_KEY = "AddManual";
    private String title;
    private String content;
    private String file_name;
    private String file_path;
    private Long entry_user_id;
    private String entry_user_name;
    private Long update_user_id;
    private String update_user_name;

    @JsonIgnore
    private MultipartFile manualFile;
    @JsonIgnore
    private String title_;
    @JsonIgnore
    private String content_;
    @JsonIgnore
    private Boolean success;

    @JsonCreator
    public AddManual(
                            @JsonProperty(value = "title", required = true) String title,
                            @JsonProperty(value = "content", required = true) String content,
                            @JsonProperty(value = "file_name", required = true) String file_name,
                            @JsonProperty(value = "file_path", required = true) String file_path,
                            @JsonProperty(value = "entry_user_id", required = true) Long entry_user_id,
                            @JsonProperty(value = "entry_user_name", required = true) String entry_user_name,
                            @JsonProperty(value = "update_user_id", required = true) Long update_user_id,
                            @JsonProperty(value = "update_user_name", required = true) String update_user_name){
        this.title = title;
        this.content = content;
        this.file_name = file_name;
        this.file_path = file_path;
        this.entry_user_id = entry_user_id;
        this.entry_user_name = entry_user_name;
        this.update_user_id = update_user_id;
        this.update_user_name = update_user_name;
    }


    public AddManual() {
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

    public Boolean isSuccess() {
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


        if(title == null || title.equals("")){
            title_= (truong+ SystemMessageProperties.getSystemProperty("v3_title")+ notEmpty);
            success= false;
        } else if (title.length()>199){
            title_ = ( truong+ SystemMessageProperties.getSystemProperty("v3_title")+ max_length + EditString.SPACE + 200 + ky_tu);
            success = false;
        }
        if(content == null || content.equals("")){
            content_ = ( truong + SystemMessageProperties.getSystemProperty("v3_content")+ notEmpty);
            success= false;
        } else if (content.length()>3000){
            content_ = ( truong + SystemMessageProperties.getSystemProperty("v3_content") + EditString.SPACE + SystemMessageProperties.getSystemProperty("v3_text_too_long"));
            success = false;
        }
    }

    public JSONObject generateJsonObject(){
        JSONObject obj = new JSONObject();
        AddUnitRequest addUnitRequest =new AddUnitRequest();

        try {
            obj.put("title", title);
            obj.put("content", content);
            obj.put("file_name", file_name);
            obj.put("file_path", file_path);
            obj.put("entry_user_id", "1");
            obj.put("entry_user_name", "minhbq");
            obj.put("update_user_id", "1");
            obj.put("update_user_name", "minhbq");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }
}

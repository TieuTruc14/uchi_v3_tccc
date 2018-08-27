package com.vn.osp.modelview;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vn.osp.common.util.EditString;
import com.vn.osp.common.util.SystemMessageProperties;
import com.vn.osp.common.util.ValidationPool;

/**
 * Created by tranv on 3/17/2017.
 */
public class LoginForm {
    private String username;
    private String password;

    @JsonIgnore
    private String username_;
    @JsonIgnore
    private String password_;
    @JsonIgnore
    private Boolean success;

    public LoginForm() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername_() {
        return username_;
    }

    public void setUsername_(String username_) {
        this.username_ = username_;
    }

    public String getPassword_() {
        return password_;
    }

    public void setPassword_(String password_) {
        this.password_ = password_;
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

        if (username == null || username.equals("")){
            username_=(truong + SystemMessageProperties.getSystemProperty("v3_tk_dang_nhap") + notEmpty);
            success = false;
        }else if(!username.matches(ValidationPool.length(1,200))){
            username_=(truong + SystemMessageProperties.getSystemProperty("v3_tk_dang_nhap") + min_length +EditString.SPACE+ 1 + ky_tu+", "+max_length +EditString.SPACE+ 200+ky_tu);
            success = false;
        }

        if (password == null || password.equals("")){
            password_=(truong + SystemMessageProperties.getSystemProperty("v3_mat_khau") + notEmpty);
            success = false;
        }else if(!password.matches(ValidationPool.length(1,100))){
            password_=(truong + SystemMessageProperties.getSystemProperty("v3_mat_khau") + min_length +EditString.SPACE+ 1 + ky_tu+", "+max_length +EditString.SPACE+ 100+ky_tu);
            success = false;
        }
    }
}

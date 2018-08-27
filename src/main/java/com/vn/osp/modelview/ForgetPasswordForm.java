package com.vn.osp.modelview;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vn.osp.common.util.EditString;
import com.vn.osp.common.util.SystemMessageProperties;
import com.vn.osp.common.util.ValidationPool;

/**
 * Created by tranv on 4/18/2017.
 */
public class ForgetPasswordForm {
    private String account;
    private String email;

    @JsonIgnore
    private String account_;
    @JsonIgnore
    private String email_;
    @JsonIgnore
    private Boolean success;
    @JsonIgnore
    private String emailSuccess_;


    public ForgetPasswordForm() {

    }

    public String getEmailSuccess_() {
        return emailSuccess_;
    }

    public void setEmailSuccess_(String emailSuccess_) {
        this.emailSuccess_ = emailSuccess_;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAccount_() {
        return account_;
    }

    public void setAccount_(String account_) {
        this.account_ = account_;
    }

    public String getEmail_() {
        return email_;
    }

    public void setEmail_(String email_) {
        this.email_ = email_;
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

        if (account == null || account.equals("")){
            account_=(truong + SystemMessageProperties.getSystemProperty("v3_tk_dang_nhap") + notEmpty);
            success = false;
        }else if(!account.matches(ValidationPool.length(3,20))){
            account=(truong + SystemMessageProperties.getSystemProperty("v3_tk_dang_nhap") + min_length + EditString.SPACE+ 3 + ky_tu+", "+max_length +EditString.SPACE+ 20+ky_tu);
            success = false;
        } else if(!account.matches(ValidationPool.OK_SPECICAL)){
            account_=(truong + SystemMessageProperties.getSystemProperty("v3_tk_dang_nhap") +  SystemMessageProperties.getSystemProperty("v3_only_character"));
            success = false;
        }

        if (email == null || email.equals("")){
            email_=(truong + SystemMessageProperties.getSystemProperty("v3_email") + notEmpty);
            success = false;
        } else if(!email.matches(ValidationPool.EMAIL)){
            email_=(truong + SystemMessageProperties.getSystemProperty("v3_email") +  SystemMessageProperties.getSystemProperty("v3_valid_email"));
            success = false;
        }


    }
}


package com.vn.osp.modelview;
import com.vn.osp.common.util.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vn.osp.common.global.Constants;
import com.vn.osp.context.CommonContext;
import com.vn.osp.service.QueryFactory;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class User {
    public static final String SESSION_KEY = "User";
    private Long userId;
    private Long office_id;
    private String family_name;
    private String first_name;
    private String account;
    private String password;
    private Long sex;
    private Long active_flg;
    private Long active_ccv;
    private Long hidden_flg;
    private String role;
    private String birthday;
    private String telephone;
    private String mobile;
    private String email;
    private String address;
    private String last_login_date;
    private Long entry_user_id;
    private String entry_user_name;
    private String entry_date_time;
    private Long update_user_id;
    private String update_user_name;
    private String update_date_time;
    private String rePassword;
    private String file_path;
    private String file_name;
    //validate
    @JsonIgnore
    private String family_name_;
    @JsonIgnore
    private String first_name_;
    @JsonIgnore
    private String account_;
    @JsonIgnore
    private String password_;
    @JsonIgnore
    private String sex_;
    @JsonIgnore
    private String role_;
    @JsonIgnore
    private String birthday_;
    @JsonIgnore
    private String telephone_;
    @JsonIgnore
    private String mobile_;
    @JsonIgnore
    private String email_;
    @JsonIgnore
    private String address_;
    @JsonIgnore
    private String rePassword_;
    @JsonIgnore
    private Boolean success;

    @JsonIgnore
    private String newPassword;
    @JsonIgnore
    private String reNewPassword;
    @JsonIgnore
    private String oldPassword;
    @JsonIgnore
    private String newPassword_;
    @JsonIgnore
    private String reNewPassword_;
    @JsonIgnore
    private String oldPassword_;
    @JsonIgnore
    private List<NotaryOffice> notaryOffices;
    @JsonIgnore
    private String authorId;
    @JsonIgnore
    private String isAdvanceSearch;


    public String getName(){
        return family_name+" "+first_name;
    }

    public String getFamily_name() {
        return family_name;
    }

    public String getEntryUserName(){
        String EntryUserName = this.getFamily_name() + " " + this.getFirst_name();
        return EntryUserName;
    }

    public void setFamily_name(String family_name) {
        this.family_name = family_name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getOffice_id() {
        return office_id;
    }

    public void setOffice_id(Long office_id) {
        this.office_id = office_id;
    }

    public String getLast_login_date() {
        return last_login_date;
    }

    public void setLast_login_date(String last_login_date) {
        this.last_login_date = last_login_date;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getActive_flg() {
        return active_flg;
    }
    public Long getActive_ccv() {
        return active_ccv;
    }

    public void setActive_ccv(Long active_ccv) {
        this.active_ccv = active_ccv;
    }
    public void setActive_flg(Long active_flg) {
        this.active_flg = active_flg;
    }

    public Long getHidden_flg() {
        return hidden_flg;
    }

    public void setHidden_flg(Long hidden_flg) {
        this.hidden_flg = hidden_flg;
    }

    public Long getSex() {
        return sex;
    }

    public void setSex(Long sex) {
        this.sex = sex;
    }

    public String getFamily_name_() {
        return family_name_;
    }

    public void setFamily_name_(String family_name_) {
        this.family_name_ = family_name_;
    }

    public String getFirst_name_() {
        return first_name_;
    }

    public void setFirst_name_(String first_name_) {
        this.first_name_ = first_name_;
    }

    public String getAccount_() {
        return account_;
    }

    public void setAccount_(String account_) {
        this.account_ = account_;
    }

    public String getPassword_() {
        return password_;
    }

    public void setPassword_(String password_) {
        this.password_ = password_;
    }

    public String getSex_() {
        return sex_;
    }

    public void setSex_(String sex_) {
        this.sex_ = sex_;
    }

    public String getRole_() {
        return role_;
    }

    public void setRole_(String role_) {
        this.role_ = role_;
    }

    public String getBirthday_() {
        return birthday_;
    }

    public void setBirthday_(String birthday_) {
        this.birthday_ = birthday_;
    }

    public String getTelephone_() {
        return telephone_;
    }

    public void setTelephone_(String telephone_) {
        this.telephone_ = telephone_;
    }

    public String getMobile_() {
        return mobile_;
    }

    public void setMobile_(String mobile_) {
        this.mobile_ = mobile_;
    }

    public String getEmail_() {
        return email_;
    }

    public void setEmail_(String email_) {
        this.email_ = email_;
    }

    public String getAddress_() {
        return address_;
    }

    public void setAddress_(String address_) {
        this.address_ = address_;
    }

    public String getRePassword_() {
        return rePassword_;
    }

    public void setRePassword_(String rePassword_) {
        this.rePassword_ = rePassword_;
    }

    public String getRePassword() {
        return rePassword;
    }

    public void setRePassword(String rePassword) {
        this.rePassword = rePassword;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getReNewPassword() {
        return reNewPassword;
    }

    public void setReNewPassword(String reNewPassword) {
        this.reNewPassword = reNewPassword;
    }

    public List<NotaryOffice> getNotaryOffices() {
        return notaryOffices;
    }

    public void setNotaryOffices(List<NotaryOffice> notaryOffices) {
        this.notaryOffices = notaryOffices;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword_() {
        return newPassword_;
    }

    public void setNewPassword_(String newPassword_) {
        this.newPassword_ = newPassword_;
    }

    public String getReNewPassword_() {
        return reNewPassword_;
    }

    public void setReNewPassword_(String reNewPassword_) {
        this.reNewPassword_ = reNewPassword_;
    }

    public String getOldPassword_() {
        return oldPassword_;
    }

    public void setOldPassword_(String oldPassword_) {
        this.oldPassword_ = oldPassword_;
    }

    public String getIsAdvanceSearch() {
        return isAdvanceSearch;
    }

    public void setIsAdvanceSearch(String isAdvanceSearch) {
        this.isAdvanceSearch = isAdvanceSearch;
    }
    public void userStrim(){
        if(family_name!=null){
            setFamily_name(getFamily_name().trim());
        }
        if(first_name!=null){
            setFirst_name(getFirst_name().trim());
        }
        if(account!=null){
            setAccount(getAccount().trim());
        }
        if(address!=null){
            setAddress(getAddress().trim());
        }
        if(email!=null){
            setEmail(getEmail().trim());
        }
        if(telephone!=null){
            setTelephone(getTelephone().trim());
        }
        if(mobile!=null){
            setMobile(getMobile().trim());
        }
        if(role!=null){
            setRole(getRole().trim());
        }
    }

    public String getFile_path() {
        return file_path;
    }

    public void setFile_path(String file_path) {
        this.file_path = file_path;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public void valiate() {
        success = true;
        String truong = SystemMessageProperties.getSystemProperty("v3_truong") + " ";
        String notEmpty = SystemMessageProperties.getSystemProperty("v3_not_empty");
        String max_length = SystemMessageProperties.getSystemProperty("v3_max_length");
        String min_length = SystemMessageProperties.getSystemProperty("v3_min_length");
        String ky_tu = SystemMessageProperties.getSystemProperty("v3_ky_tu");
        String family_name1 = null;
        String first_name1 = null;
        if(StringUtils.isBlank(family_name) == false){
            family_name1 = family_name.replaceAll("@","").replaceAll("&","");
        }else{
            family_name1 = family_name;
        }
        if (family_name1 == null || family_name1.equals("")){
            family_name_=(truong + SystemMessageProperties.getSystemProperty("v3_ho_dem") + notEmpty);
            success = false;
        }else {
            boolean check = com.vn.osp.util.StringUtils.checkSpecialCharacter(family_name1);
            if(check == false){
                family_name_=(truong + SystemMessageProperties.getSystemProperty("v3_ho_dem") + EditString.SPACE+ SystemMessageProperties.getSystemProperty("v3_not_special_name"));
                success = false;
            }else if(!family_name.matches(ValidationPool.length(1,50))){
                family_name_=(truong + SystemMessageProperties.getSystemProperty("v3_ho_dem") + max_length + EditString.SPACE+ 50 + ky_tu);
                success = false;
            }
        }
        /*else if(!family_name1.matches(ValidationPool.length(1,50))){
            family_name_=(truong + SystemMessageProperties.getSystemProperty("v3_ho_dem") + max_length + EditString.SPACE+ 50 + ky_tu);
            success = false;
        } else if(!family_name1.matches(ValidationPool.TR_NOT_SPECICAL)){
            family_name_=(truong + SystemMessageProperties.getSystemProperty("v3_ho_dem") + SystemMessageProperties.getSystemProperty("v3_not_special"));
            success = false;
        }*/

        if(StringUtils.isBlank(first_name) == false){
            first_name1 = first_name.replaceAll("@","").replaceAll("&","");
        }else{
            first_name1 = first_name;
        }
        if (first_name1 == null || first_name1.equals("")){
            first_name_=(truong + SystemMessageProperties.getSystemProperty("v3_ten") + notEmpty);
            success = false;
        }else {
            boolean check = com.vn.osp.util.StringUtils.checkSpecialCharacter(first_name1);
            if(check == false){
                first_name_=(truong + SystemMessageProperties.getSystemProperty("v3_ten") + EditString.SPACE+ SystemMessageProperties.getSystemProperty("v3_not_special_name"));
                success = false;
            }else if(!first_name.matches(ValidationPool.length(1,50))){
                first_name_=(truong + SystemMessageProperties.getSystemProperty("v3_ten") + max_length + EditString.SPACE+ 50 + ky_tu);
                success = false;
            }
        }

        if (account == null || account.equals("")) {
            account_ = (truong + SystemMessageProperties.getSystemProperty("v3_tk_dang_nhap") + notEmpty);
            success = false;
        }/*else if(!account.matches(ValidationPool.length(3,20))){
            account_=(truong + SystemMessageProperties.getSystemProperty("v3_tk_dang_nhap") + min_length +EditString.SPACE+ 3 + ky_tu+", "+max_length +EditString.SPACE+ 20+ky_tu);
            success = false;
        }*/ else if (!account.matches(ValidationPool.OK_SPECICAL)) {
            account_ = (truong + SystemMessageProperties.getSystemProperty("v3_tk_dang_nhap") + SystemMessageProperties.getSystemProperty("v3_only_character"));
            success = false;
        } else if (account.length() < 3) {
            account_ = (truong + SystemMessageProperties.getSystemProperty("v3_tk_dang_nhap") + min_length + EditString.SPACE + 3 + ky_tu);
            success = false;
        } else if (account.length() > 20) {
            account_ = (truong + SystemMessageProperties.getSystemProperty("v3_tk_dang_nhap") + max_length + EditString.SPACE + 20 + ky_tu);
            success = false;
        }

        if (password == null || password.equals("")) {
            password_ = (truong + SystemMessageProperties.getSystemProperty("v3_mat_khau") + notEmpty);
            success = false;
        }else{
            if(PasswordValidator.verifyPassword(password) == false){
                password_ = "Mật khẩu chỉ có thể là số hoặc chữ hoa và chữ thường không dấu, phải dài tối thiểu 6 kí tự và tối đa 50 kí tự, có ít nhất 1 kí tự số , 1 kí tự viết hoa và 1 kí tự viết thường ";
                success = false;
            }
        }

        /*else if(!password.matches(ValidationPool.length(6,50))){
            password_=(truong + SystemMessageProperties.getSystemProperty("v3_mat_khau") + min_length +EditString.SPACE+ 6 + ky_tu+", "+max_length +EditString.SPACE+ 50+ky_tu);
            success = false;
        }*/
        /*else if (password.length() < 6) {
            password_ = (truong + SystemMessageProperties.getSystemProperty("v3_mat_khau") + min_length + EditString.SPACE + 6 + ky_tu);
            success = false;
        } else if (password.length() > 20) {
            password_ = (truong + SystemMessageProperties.getSystemProperty("v3_mat_khau") + max_length + EditString.SPACE + 20 + ky_tu);
            success = false;
        }*/

        if (rePassword == null || rePassword.equals("")) {
            rePassword_ = (truong + SystemMessageProperties.getSystemProperty("v3_xac_nhan_mk") + notEmpty);
            success = false;
        }
        if (!StringUtils.isBlank(password)) {
            if (!password.equals(rePassword)) {
                rePassword_ = SystemMessageProperties.getSystemProperty("v3_check_pass");
                password_ = EditString.SPACE;
                success = false;
            }
        }
        boolean checkBirthday = true ;
        if (birthday != null && !birthday.equals("")) {
            if( checkBirthday == true){
                if (!ValidationPool.compareToday(birthday)) {
                    birthday_ = (truong + SystemMessageProperties.getSystemProperty("v3_birthday") + EditString.SPACE + SystemMessageProperties.getSystemProperty("v3_compare_today"));
                    success = false;
                }
            }
            if (!birthday.matches(ValidationPool.DATE)) {
                birthday_ = (truong + SystemMessageProperties.getSystemProperty("v3_birthday") + EditString.SPACE + SystemMessageProperties.getSystemProperty("v3_date_format"));
                success = false;
                checkBirthday = false;
            }  else if (birthday != null && !birthday.equals("")) {
                String[] arr = birthday.split("/");
                int ngay = Integer.valueOf(arr[0]);
                int thang = Integer.valueOf(arr[1]);
                int nam = Integer.valueOf(arr[2]);
                if (ngay < 1 || ngay > 31) {
                    birthday_ = (truong + SystemMessageProperties.getSystemProperty("v3_birthday") + EditString.SPACE + SystemMessageProperties.getSystemProperty("v3_date_format"));
                    success = false;
                    checkBirthday = false;
                }
                if (thang < 1 || thang > 12) {
                    birthday_ = (truong + SystemMessageProperties.getSystemProperty("v3_birthday") + EditString.SPACE + SystemMessageProperties.getSystemProperty("v3_date_format"));
                    success = false;
                    checkBirthday = false;
                }
            }
        }

        if (address != null && !address.equals("") && !address.matches(ValidationPool.length(1, 200))) {
            address_ = (truong + SystemMessageProperties.getSystemProperty("v3_dia_chi") + max_length + EditString.SPACE + 200 + ky_tu);
            success = false;
        }

        if (email == null || email.equals("")) {
            email_ = (truong + SystemMessageProperties.getSystemProperty("v3_email") + notEmpty);
            success = false;
        } else if (!email.matches(ValidationPool.EMAIL)) {
            email_ = (truong + SystemMessageProperties.getSystemProperty("v3_email") + SystemMessageProperties.getSystemProperty("v3_valid_email"));
            success = false;
        }


        if (!(telephone == null || telephone.equals(""))) {
            if (!telephone.matches(ValidationPool.length(10, 15))) {
                telephone_ = (truong + SystemMessageProperties.getSystemProperty("v3_telephone") + min_length + EditString.SPACE + 10 + ky_tu + max_length + EditString.SPACE + 15 + ky_tu);
                success = false;
            }
            if (!telephone.matches(ValidationPool.PHONE)) {
                telephone_ = (truong + SystemMessageProperties.getSystemProperty("v3_telephone") + SystemMessageProperties.getSystemProperty("v3_valid_telephone"));
                success = false;
            }
        }
        if (!(mobile == null || mobile.equals(""))) {
            if (!mobile.matches(ValidationPool.length(10, 15))) {
                mobile_ = (truong + SystemMessageProperties.getSystemProperty("v3_mobile") + min_length + EditString.SPACE + 10 + ky_tu + max_length + EditString.SPACE + 15 + ky_tu);
                success = false;
            }
            if (!mobile.matches(ValidationPool.PHONE)) {
                mobile_ = (truong + SystemMessageProperties.getSystemProperty("v3_mobile") + SystemMessageProperties.getSystemProperty("v3_valid_phone"));
                success = false;
            }
        }


        if (role != null && !role.equals("") && !role.matches(ValidationPool.length(1, 200))) {
            role_ = (truong + SystemMessageProperties.getSystemProperty("v3_chuc_vu") + max_length + EditString.SPACE + 200 + ky_tu);
            success = false;
        }

    }

    public void updateValiate() {
        success = true;
        String truong = SystemMessageProperties.getSystemProperty("v3_truong") + " ";
        String notEmpty = SystemMessageProperties.getSystemProperty("v3_not_empty");
        String max_length = SystemMessageProperties.getSystemProperty("v3_max_length");
        String min_length = SystemMessageProperties.getSystemProperty("v3_min_length");
        String ky_tu = SystemMessageProperties.getSystemProperty("v3_ky_tu");

        User userUpdated = QueryFactory.getUserById(Long.valueOf(userId));
        String family_name1 = null;
        String first_name1 = null;

        if(StringUtils.isBlank(family_name) == false){
            family_name1 = family_name.replaceAll("@","").replaceAll("&","");
        }else{
            family_name1 = family_name;
        }
        if (family_name1 == null || family_name1.equals("")){
            family_name_=(truong + SystemMessageProperties.getSystemProperty("v3_ho_dem") + notEmpty);
            success = false;
        }else {
            boolean check = com.vn.osp.util.StringUtils.checkSpecialCharacter(family_name1);
            if(check == false){
                family_name_=(truong + SystemMessageProperties.getSystemProperty("v3_ho_dem") + EditString.SPACE+ SystemMessageProperties.getSystemProperty("v3_not_special_name"));
                success = false;
            }else if(!family_name.matches(ValidationPool.length(1,50))){
                family_name_=(truong + SystemMessageProperties.getSystemProperty("v3_ho_dem") + max_length + EditString.SPACE+ 50 + ky_tu);
                success = false;
            }
        }
        if(StringUtils.isBlank(first_name) == false){
            first_name1 = first_name.replaceAll("@","").replaceAll("&","");
        }else{
            first_name1 = first_name;
        }
        if (first_name1 == null || first_name1.equals("")){
            first_name_=(truong + SystemMessageProperties.getSystemProperty("v3_ten") + notEmpty);
            success = false;
        }else {
            boolean check = com.vn.osp.util.StringUtils.checkSpecialCharacter(first_name1);
            if(check == false){
                first_name_=(truong + SystemMessageProperties.getSystemProperty("v3_ten") + EditString.SPACE+ SystemMessageProperties.getSystemProperty("v3_not_special_name"));
                success = false;
            }else if(!first_name.matches(ValidationPool.length(1,50))){
                first_name_=(truong + SystemMessageProperties.getSystemProperty("v3_ten") + max_length + EditString.SPACE+ 50 + ky_tu);
                success = false;
            }
        }

        if (account == null || account.equals("")) {
            account_ = (truong + SystemMessageProperties.getSystemProperty("v3_tk_dang_nhap") + notEmpty);
            success = false;
        } else if (!account.matches(ValidationPool.length(3, 20))) {
            account_ = (truong + SystemMessageProperties.getSystemProperty("v3_tk_dang_nhap") + min_length + EditString.SPACE + 3 + ky_tu + ", " + max_length + EditString.SPACE + 20 + ky_tu);
            success = false;
        } else if (!account.matches(ValidationPool.OK_SPECICAL)) {
            account_ = (truong + SystemMessageProperties.getSystemProperty("v3_tk_dang_nhap") + SystemMessageProperties.getSystemProperty("v3_only_character"));
            success = false;
        }
        if (!StringUtils.isBlank(isAdvanceSearch)) {
            if (isAdvanceSearch.equals("true")) {
                if (StringUtils.isBlank(oldPassword)) {
                    oldPassword_ = (truong + SystemMessageProperties.getSystemProperty("v3_mat_khau") + notEmpty);
                    success = false;
                } else if (!Crypter.matches(userUpdated.getPassword(), oldPassword)) {
                    oldPassword_ = SystemMessageProperties.getSystemProperty("COM004_old_password_wrong");
                    success = false;
                }
            }
        }

        if (newPassword != null && !newPassword.equals("") && !newPassword.equals(reNewPassword)) {
            /*password_ = ("Giá trị Nhập lại mật khẩu phải trùng với giá trị Mật khẩu !");*/
            rePassword_ = "Giá trị Nhập lại mật khẩu phải trùng với giá trị Mật khẩu !";
            success = false;
        } else {
            password = newPassword;
        }
        if (!StringUtils.isBlank(newPassword) && newPassword.length() > 50) {
            password_ = (truong + SystemMessageProperties.getSystemProperty("v3_mat_khau") + " mới có độ dài tối đa 25 ký tự!");
            rePassword_ = " ";
            success = false;
        } else {
            password = newPassword;
        }
        if (!StringUtils.isBlank(birthday)) {
            if(!ValidateUtil.validateDateto(birthday)){
                birthday_ = ValidateUtil.validate_msg_to_date;
                success = false;
            }
        }
        if (address != null && !address.equals("") && !address.matches(ValidationPool.length(1, 200))) {
            address_ = (truong + SystemMessageProperties.getSystemProperty("v3_dia_chi") + max_length + EditString.SPACE + 200 + ky_tu);
            success = false;
        }

        if (email == null || email.equals("")) {
            email_ = (truong + SystemMessageProperties.getSystemProperty("v3_email") + notEmpty);
            success = false;
        } else if (!email.matches(ValidationPool.EMAIL)) {
            email_ = (truong + SystemMessageProperties.getSystemProperty("v3_email") + SystemMessageProperties.getSystemProperty("v3_valid_email"));
            success = false;
        }
        if (!(telephone == null || telephone.equals(""))) {
            if (!telephone.matches(ValidationPool.length(10, 15))) {
                telephone_ = (truong + SystemMessageProperties.getSystemProperty("v3_telephone") + min_length + EditString.SPACE + 10 + ky_tu + max_length + EditString.SPACE + 15 + ky_tu);
                success = false;
            }
            if (!telephone.matches(ValidationPool.PHONE)) {
                telephone_ = ("Trường Điện thoại cố định không đúng định dạng điện thoại cố định !");
                success = false;
            }
        }
        if (!(mobile == null || mobile.equals(""))) {
            if (!mobile.matches(ValidationPool.length(10, 15))) {
                mobile_ = (truong + SystemMessageProperties.getSystemProperty("v3_mobile") + min_length + EditString.SPACE + 10 + ky_tu + max_length + EditString.SPACE + 15 + ky_tu);
                success = false;
            }
            if (!mobile.matches(ValidationPool.PHONE)) {
                mobile_ = (truong + SystemMessageProperties.getSystemProperty("v3_mobile") + SystemMessageProperties.getSystemProperty("v3_valid_phone"));
                success = false;
            }
        }
        /*
        if (telephone != null && !telephone.equals("") && !telephone.matches(ValidationPool.PHONE)){
            telephone_=(truong + SystemMessageProperties.getSystemProperty("v3_telephone") +  SystemMessageProperties.getSystemProperty("v3_valid_telephone"));
            success = false;
        }
        if (mobile != null && !mobile.equals("") && !mobile.matches(ValidationPool.PHONE)){
            mobile_=(truong + SystemMessageProperties.getSystemProperty("v3_mobile") +  SystemMessageProperties.getSystemProperty("v3_valid_phone"));
            success = false;
        } */
        if (role != null && !role.equals("") && !role.matches(ValidationPool.length(1, 200))) {
            role_ = (truong + SystemMessageProperties.getSystemProperty("v3_chuc_vu") + max_length + EditString.SPACE + 200 + ky_tu);
            success = false;
        }

    }

    public String generateAddJson(User user) {
        JSONObject obj = new JSONObject();
        ObjectMapper mapper = new ObjectMapper();
        this.setPassword(Crypter.crypt(password));
        this.setHidden_flg(Long.valueOf(0));
        this.setEntry_user_id(user.getUserId());
        this.setEntry_user_name(user.getAccount());
        this.setAccount(account);
        try {
            String xml_content = mapper.writeValueAsString(this);
            return xml_content;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String generateUpdateJsons(Long id,User userUpdated,User user) {
        JSONObject obj = new JSONObject();
        ObjectMapper mapper = new ObjectMapper();
        this.setUserId(id);
        if(StringUtils.isBlank(this.getPassword())){
            this.setPassword(userUpdated.getPassword());
        }else{
            this.setPassword(Crypter.crypt(password));
        }
        this.setHidden_flg(Long.valueOf(0));
        this.setUpdate_user_id(user.getUserId());
        this.setUpdate_user_name(user.getAccount());
        try {
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String generateUpdateJson(Long id, User user) {
        JSONObject obj = new JSONObject();
        ObjectMapper mapper = new ObjectMapper();
        this.setUserId(id);
        this.setPassword(Crypter.crypt(password));
        this.setOffice_id(Long.valueOf(0));
        this.setHidden_flg(Long.valueOf(0));
        this.setUpdate_user_id(user.getUserId());
        this.setUpdate_user_name(user.getAccount());
        try {
            ////System.out.println(mapper.writeValueAsString(this));
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String generateUpdateJson(Long id, User userUpdated, User user) {
        JSONObject obj = new JSONObject();
        ObjectMapper mapper = new ObjectMapper();
        this.setUserId(id);
        this.setOffice_id(Long.valueOf(0));
        if (StringUtils.isBlank(this.getPassword())) {
            this.setPassword(userUpdated.getPassword());
        } else {
            this.setPassword(Crypter.crypt(password));
        }
        this.setHidden_flg(Long.valueOf(0));
        this.setUpdate_user_id(user.getUserId());
        this.setUpdate_user_name(user.getAccount());
        try {
            ////System.out.println(mapper.writeValueAsString(this));
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String generateUpdateJsonNoneUser(Long id) {
        JSONObject obj = new JSONObject();
        ObjectMapper mapper = new ObjectMapper();
        this.setUserId(id);
        this.setPassword(Crypter.crypt(password));
        this.setUpdate_user_id(userId);
        this.setUpdate_user_name(account);
        try {
            ////System.out.println(mapper.writeValueAsString(this));
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String generateUploadAvatarJson(Long id,User userUpdated,User user) {
        JSONObject obj = new JSONObject();
        ObjectMapper mapper = new ObjectMapper();
        this.setUserId(id);
        this.setPassword(userUpdated.getPassword());
        this.setHidden_flg(Long.valueOf(0));
        this.setUpdate_user_id(user.getUserId());
        this.setUpdate_user_name(user.getAccount());
        try {
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}

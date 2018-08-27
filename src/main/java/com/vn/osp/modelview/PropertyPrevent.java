package com.vn.osp.modelview;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.vn.osp.common.global.Constants;
import com.vn.osp.common.util.EditString;
import com.vn.osp.common.util.SystemMessageProperties;
import com.vn.osp.common.util.ValidationPool;


public class PropertyPrevent {
    private Long id;
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

    //validate
    private Boolean success;
    private Long id_;
    private String type_;
    private String property_info_;
    private String owner_info_;
    private String other_info_;
    private String land_certificate_;
    private String land_issue_place_;
    private String land_issue_date_;
    private String land_paper_number_;
    private String land_map_number_;
    private String land_number_;
    private String land_address_;
    private String land_area_;
    private String land_public_area_;
    private String land_private_area_;
    private String land_use_purpose_;
    private String land_use_period_;
    private String land_use_origin_;
    private String land_associate_property_;
    private String land_street_;
    private String land_district_;
    private String land_province_;
    private String car_license_number_;
    private String car_regist_number_;
    private String car_issue_place_;
    private String car_issue_date_;
    private String car_frame_number_;
    private String car_machine_number_;

    @JsonCreator
    public PropertyPrevent(@JsonProperty(value = "id", required = true) final Long id,
                           @JsonProperty(value = "type", required = true) final String type,
                           @JsonProperty(value = "property_info", required = true) final String property_info,
                           @JsonProperty(value = "owner_info", required = false) final String owner_info,
                           @JsonProperty(value = "other_info", required = false) final String other_info,
                           @JsonProperty(value = "land_certificate", required = false) final String land_certificate,
                           @JsonProperty(value = "land_issue_place", required = false) final String land_issue_place,
                           @JsonProperty(value = "land_issue_date", required = false) final String land_issue_date,
                           @JsonProperty(value = "land_paper_number", required = false) final String land_paper_number,
                           @JsonProperty(value = "land_map_number", required = false) final String land_map_number,
                           @JsonProperty(value = "land_number", required = false) final String land_number,
                           @JsonProperty(value = "land_address", required = false) final String land_address,
                           @JsonProperty(value = "land_area", required = false) final String land_area,
                           @JsonProperty(value = "land_public_area", required = false) final String land_public_area,
                           @JsonProperty(value = "land_private_area", required = false) final String land_private_area,
                           @JsonProperty(value = "land_use_purpose", required = false) final String land_use_purpose,
                           @JsonProperty(value = "land_use_period", required = false) final String land_use_period,
                           @JsonProperty(value = "land_use_origin", required = false) final String land_use_origin,
                           @JsonProperty(value = "land_associate_property", required = false) final String land_associate_property,
                           @JsonProperty(value = "land_street", required = false) final String land_street,
                           @JsonProperty(value = "land_district", required = false) final String land_district,
                           @JsonProperty(value = "land_province", required = false) final String land_province,
                           @JsonProperty(value = "car_license_number", required = false) final String car_license_number,
                           @JsonProperty(value = "car_regist_number", required = false) final String car_regist_number,
                           @JsonProperty(value = "car_issue_place", required = false) final String car_issue_place,
                           @JsonProperty(value = "car_issue_date", required = false) final String car_issue_date,
                           @JsonProperty(value = "car_frame_number", required = false) final String car_frame_number,
                           @JsonProperty(value = "car_machine_number", required = false) final String car_machine_number) {
        this.id = id;
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

    public PropertyPrevent() {
    }

    public Long getPropertyPreventId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getLand_issue_date() {
        return land_issue_date;
    }

    public void setLand_issue_date(String land_issue_date) {
        this.land_issue_date = land_issue_date;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getCar_issue_date() {
        return car_issue_date;
    }

    public void setCar_issue_date(String car_issue_date) {
        this.car_issue_date = car_issue_date;
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

    public String getLand_paper_number() {
        return land_paper_number;
    }

    public void setLand_paper_number(String land_paper_number) {
        this.land_paper_number = land_paper_number;
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

    public void validate() {
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
                if(!land_use_purpose.matches(ValidationPool.length(1,500))){
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
    }

    public void genneratePropertyInfor() {
        String result = "";
        if (type.equals(Constants.NHA_DAT)) {
            if (land_address != null && !land_address.equals("")) result += "Địa chỉ: " + land_address + "; ";
            if (land_certificate != null && !land_certificate.equals(""))
                result += "Số giấy chứng nhận: " + land_certificate + "; ";
            if (land_issue_place != null && !land_issue_place.equals(""))
                result += "Nơi cấp: " + land_issue_place + "; ";
            if (land_issue_date != null && !land_issue_date.equals("")) result += "Ngày cấp: " + land_issue_date + "; ";
            if (land_paper_number != null && !land_paper_number.equals("")) result += "Số giấy vào sổ: " + land_paper_number + "; ";
            if (land_number != null && !land_number.equals("")) result += "Thửa đất số: " + land_number + "; ";
            if (land_map_number != null && !land_map_number.equals(""))
                result += "Tờ bản đồ số: " + land_map_number + "; ";
            if (land_area != null && !land_area.equals("")) result += "Diện tích (m2): " + land_area + "; ";
            if (land_private_area != null && !land_private_area.equals(""))
                result += "Diện tích sử dụng riêng: " + land_private_area + "; ";
            if (land_public_area != null && !land_public_area.equals(""))
                result += "Diện tích sử dụng chung: " + land_public_area +"; ";
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

        this.property_info = result;
    }
}

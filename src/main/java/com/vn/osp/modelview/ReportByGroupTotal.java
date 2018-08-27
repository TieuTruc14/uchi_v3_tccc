package com.vn.osp.modelview;


import java.math.BigInteger;

/**
 * Created by tranv on 12/28/2016.
 */

public class ReportByGroupTotal {
    private long kind_id;
    private String kind_name;
    private String template_name;
    private BigInteger template_number;
    private Integer code_template;


    public ReportByGroupTotal() {
    }

    public ReportByGroupTotal(long kind_id, String kind_name, String template_name, BigInteger template_number, Integer code_template) {
        this.kind_id = kind_id;
        this.kind_name = kind_name;
        this.template_name = template_name;
        this.template_number = template_number;
        this.code_template = code_template;
    }

    public Integer getCode_template() {
        return code_template;
    }

    public void setCode_template(Integer code_template) {
        this.code_template = code_template;
    }

    public long getKind_id() {
        return kind_id;
    }

    public void setKind_id(long kind_id) {
        this.kind_id = kind_id;
    }

    public String getKind_name() {
        return kind_name;
    }

    public void setKind_name(String kind_name) {
        this.kind_name = kind_name;
    }

    public String getTemplate_name() {
        return template_name;
    }

    public void setTemplate_name(String template_name) {
        this.template_name = template_name;
    }

    public BigInteger getTemplate_number() {
        return template_number;
    }

    public void setTemplate_number(BigInteger template_number) {
        this.template_number = template_number;
    }
}
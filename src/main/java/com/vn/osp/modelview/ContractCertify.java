package com.vn.osp.modelview;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.vn.osp.common.util.TimeUtil;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Created by tranv on 12/28/2016.
 */

public class ContractCertify {
    private String contract_number;
    private String notary_date;
    private String relate_object_A_display;
    private String relate_object_B_display;
    private String relate_object_C_display;
    private String relation_object_A;
    private String relation_object_B;
    private String relation_object_C;
    private String name;
    private String notary_family_name;
    private String notary_first_name;
    private BigInteger cost_total;
    private String note;

    @JsonCreator
    public ContractCertify(@JsonProperty(value = "contract_number", required = false) final String contract_number,
                         @JsonProperty(value = "notary_date", required = false) final String notary_date,
                         @JsonProperty(value = "relate_object_A_display", required = false) final String relate_object_A_display,
                         @JsonProperty(value = "relate_object_B_display", required = false) final String relate_object_B_display,
                         @JsonProperty(value = "relate_object_C_display", required = false) final String relate_object_C_display,
                         @JsonProperty(value = "relation_object_A", required = false) final String relation_object_A,
                         @JsonProperty(value = "relation_object_B", required = false) final String relation_object_B,
                         @JsonProperty(value = "relation_object_C", required = false) final String relation_object_C,
                         @JsonProperty(value = "name", required = false) final String name,
                         @JsonProperty(value = "notary_family_name", required = false) final String notary_family_name,
                         @JsonProperty(value = "notary_first_name", required = false) final String notary_first_name,
                         @JsonProperty(value = "cost_total", required = false) final BigInteger cost_total,
                         @JsonProperty(value = "note", required = false) final String note){
        this.contract_number = contract_number;
        this.notary_date = notary_date;
        this.relate_object_A_display = relate_object_A_display;
        this.relate_object_B_display = relate_object_B_display;
        this.relate_object_C_display = relate_object_C_display;
        this.relation_object_A = relation_object_A;
        this.relation_object_B = relation_object_B;
        this.relation_object_C = relation_object_C;
        this.name = name;
        this.notary_family_name = notary_family_name;
        this.notary_first_name = notary_first_name;
        this.cost_total = cost_total;
        this.note = note;

    }


    public ContractCertify(){

    }


    public String getContract_number() {
        return contract_number;
    }

    public void setContract_number(String contract_number) {
        this.contract_number = contract_number;
    }

    public String getNotary_date() {
        return notary_date;
    }

    public void setNotary_date(String notary_date) {
        this.notary_date = notary_date;
    }

    public String getRelate_object_A_display() {
        return relate_object_A_display;
    }

    public void setRelate_object_A_display(String relate_object_A_display) {
        this.relate_object_A_display = relate_object_A_display;
    }

    public String getRelate_object_B_display() {
        return relate_object_B_display;
    }

    public void setRelate_object_B_display(String relate_object_B_display) {
        this.relate_object_B_display = relate_object_B_display;
    }

    public String getRelate_object_C_display() {
        return relate_object_C_display;
    }

    public void setRelate_object_C_display(String relate_object_C_display) {
        this.relate_object_C_display = relate_object_C_display;
    }

    public String getRelation_object_A() {
        return relation_object_A;
    }

    public void setRelation_object_A(String relation_object_A) {
        this.relation_object_A = relation_object_A;
    }

    public String getRelation_object_B() {
        return relation_object_B;
    }

    public void setRelation_object_B(String relation_object_B) {
        this.relation_object_B = relation_object_B;
    }

    public String getRelation_object_C() {
        return relation_object_C;
    }

    public void setRelation_object_C(String relation_object_C) {
        this.relation_object_C = relation_object_C;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNotary_family_name() {
        return notary_family_name;
    }

    public void setNotary_family_name(String notary_family_name) {
        this.notary_family_name = notary_family_name;
    }

    public String getNotary_first_name() {
        return notary_first_name;
    }

    public void setNotary_first_name(String notary_first_name) {
        this.notary_first_name = notary_first_name;
    }

    public BigInteger getCost_total() {
        return cost_total;
    }

    public void setCost_total(BigInteger cost_total) {
        this.cost_total = cost_total;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

}

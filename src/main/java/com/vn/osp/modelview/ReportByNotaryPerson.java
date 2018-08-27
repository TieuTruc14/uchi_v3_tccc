package com.vn.osp.modelview;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * Created by minh on 5/18/2017.
 */
public class ReportByNotaryPerson {
    private Integer id;
    private String contract_number;
    private String contract_name;
    private String relate_object_A_display;
    private String relate_object_B_display;
    private String relate_object_C_display;
    private String relation_object_A;
    private String relation_object_B;
    private String relation_object_C;
    private String notary_date;
    private Integer notary_id;
    private String family_name;
    private String first_name;
    private String summary;
    private String relationObject;

    @JsonCreator
    public ReportByNotaryPerson(@JsonProperty(value = "id", required = false) final Integer id,
                          @JsonProperty(value = "contract_number", required = false) final String contract_number,
                          @JsonProperty(value = "contract_name", required = false) final String contract_name,
                          @JsonProperty(value = "relate_object_A_display", required = false) final String relate_object_A_display,
                          @JsonProperty(value = "relate_object_B_display", required = false) final String relate_object_B_display,
                          @JsonProperty(value = "relate_object_C_display", required = false) final String relate_object_C_display,
                          @JsonProperty(value = "relation_object_A", required = false) final String relation_object_A,
                          @JsonProperty(value = "relation_object_B", required = false) final String relation_object_B,
                          @JsonProperty(value = "relation_object_C", required = false) final String relation_object_C,
                          @JsonProperty(value = "notary_date", required = false) final String notary_date,
                          @JsonProperty(value = "notary_id", required = false) final Integer notary_id,
                          @JsonProperty(value = "family_name", required = false) final String family_name,
                          @JsonProperty(value = "first_name", required = false) final String first_name,
                          @JsonProperty(value = "summary", required = false) final String summary){
        this.id = id;
        this.contract_number = contract_number;
        this.contract_name = contract_name;
        this.relate_object_A_display = relate_object_A_display;
        this.relate_object_B_display = relate_object_B_display;
        this.relate_object_C_display = relate_object_C_display;
        this.relation_object_A = relation_object_A;
        this.relation_object_B = relation_object_B;
        this.relation_object_C = relation_object_C;
        this.notary_date = notary_date;
        this.notary_id = notary_id;
        this.family_name = family_name;
        this.first_name = first_name;
        this.summary = summary;

    }
    public ReportByNotaryPerson(){

    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContract_number() {
        return contract_number;
    }

    public void setContract_number(String contract_number) {
        this.contract_number = contract_number;
    }

    public String getContract_name() {
        return contract_name;
    }

    public void setContract_name(String contract_name) {
        this.contract_name = contract_name;
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

    public String getNotary_date() {
        return notary_date;
    }

    public void setNotary_date(String notary_date) {
        this.notary_date = notary_date;
    }

    public Integer getNotary_id() {
        return notary_id;
    }

    public void setNotary_id(Integer notary_id) {
        this.notary_id = notary_id;
    }

    public String getFamily_name() {
        return family_name;
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

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getRelationObject() {
        return relationObject;
    }

    public void setRelationObject(String relationObject) {
        this.relationObject = relationObject;
    }
}

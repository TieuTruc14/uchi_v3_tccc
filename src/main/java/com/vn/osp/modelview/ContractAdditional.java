package com.vn.osp.modelview;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;

/**
 * Created by minh on 5/23/2017.
 */
public class ContractAdditional {
    private Integer id;
    private String contract_number;
    private String contract_name;
    private String relate_object_A_display;
    private String relate_object_B_display;
    private String relate_object_C_display;
    private String relation_object_A;
    private String relation_object_B;
    private String relation_object_C;
    private Timestamp notary_date;
    private String family_name;
    private String first_name;
    private Integer drafter_id;
    private String drafter_family_name;
    private String drafter_first_name;
    private String addition_description;
    @JsonCreator
    public ContractAdditional(@JsonProperty(value = "id", required = false) final Integer id,
                         @JsonProperty(value = "contract_number", required = false) final String contract_number,
                         @JsonProperty(value = "contract_name", required = false) final String contract_name,
                         @JsonProperty(value = "relate_object_A_display", required = false) final String relate_object_A_display,
                         @JsonProperty(value = "relate_object_B_display", required = false) final String relate_object_B_display,
                         @JsonProperty(value = "relate_object_C_display", required = false) final String relate_object_C_display,
                         @JsonProperty(value = "relation_object_A", required = false) final String relation_object_A,
                         @JsonProperty(value = "relation_object_B", required = false) final String relation_object_B,
                         @JsonProperty(value = "relation_object_C", required = false) final String relation_object_C,
                         @JsonProperty(value = "notary_date", required = false) final Timestamp notary_date,
                         @JsonProperty(value = "family_name", required = false) final String family_name,
                         @JsonProperty(value = "first_name", required = false) final String first_name,
                              @JsonProperty(value = "drafter_id", required = false) final Integer drafter_id,
                              @JsonProperty(value = "drafter_family_name", required = false) final String drafter_family_name,
                              @JsonProperty(value = "drafter_first_name", required = false) final String drafter_first_name,
                         @JsonProperty(value = "addition_description", required = false) final String addition_description){
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
        this.family_name = family_name;
        this.first_name = first_name;
        this.drafter_id = drafter_id;
        this.drafter_family_name = drafter_family_name;
        this.drafter_first_name = drafter_first_name;
        this.addition_description = addition_description;

    }

    public ContractAdditional(){

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

    public Timestamp getNotary_date() {
        return notary_date;
    }

    public void setNotary_date(Timestamp notary_date) {
        this.notary_date = notary_date;
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

    public Integer getDrafter_id() {
        return drafter_id;
    }

    public void setDrafter_id(Integer drafter_id) {
        this.drafter_id = drafter_id;
    }

    public String getDrafter_family_name() {
        return drafter_family_name;
    }

    public void setDrafter_family_name(String drafter_family_name) {
        this.drafter_family_name = drafter_family_name;
    }

    public String getDrafter_first_name() {
        return drafter_first_name;
    }

    public void setDrafter_first_name(String drafter_first_name) {
        this.drafter_first_name = drafter_first_name;
    }

    public String getAddition_description() {
        return addition_description;
    }

    public void setAddition_description(String addition_description) {
        this.addition_description = addition_description;
    }


}

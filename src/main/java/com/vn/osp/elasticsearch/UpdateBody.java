package com.vn.osp.elasticsearch;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vn.osp.modelview.DataPreventInfor;
import org.json.JSONObject;

/**
 * Created by tranv on 3/24/2017.
 */
public class UpdateBody {
    private DataPreventInfor doc;

    @JsonCreator
    public UpdateBody(@JsonProperty(value = "doc", required = false) DataPreventInfor doc) {
        this.doc = doc;
    }

    public UpdateBody() {
    }

    public DataPreventInfor getDoc() {
        return doc;
    }

    public void setDoc(DataPreventInfor doc) {
        this.doc = doc;
    }

    public String generateJson(){
        JSONObject obj = new JSONObject();
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}

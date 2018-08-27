package com.vn.osp.elasticsearch;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.vn.osp.modelview.DataPreventInfor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tranv on 3/25/2017.
 */
public class DatePreventInforHits {
    private Long total;
    private Long max_score;
    private List<DataPreventInforHit> hits;

    @JsonCreator
    public DatePreventInforHits(@JsonProperty(value = "total", required = false) final Long total,
                                @JsonProperty(value = "max_score", required = false) final Long max_score,
                                @JsonProperty(value = "hits", required = false) final List<DataPreventInforHit> hits) {
        this.total = total;
        this.max_score = max_score;
        this.hits = hits;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Long getMax_score() {
        return max_score;
    }

    public void setMax_score(Long max_score) {
        this.max_score = max_score;
    }

    public List<DataPreventInforHit> getHits() {
        return hits;
    }

    public void setHits(List<DataPreventInforHit> hits) {
        this.hits = hits;
    }

    public List<DataPreventInfor> getDataPreventInfors(){
        ArrayList<DataPreventInfor> dataPreventInfors = new ArrayList<DataPreventInfor>();
        if(hits != null && hits.size()>0){
            for (int i=0; i<hits.size();i++){
                DataPreventInfor dataPreventInfor = hits.get(i).get_source();
                String property_info = dataPreventInfor.getProperty_info();
                if(property_info.length()>150) dataPreventInfor.setProperty_info_disp(property_info.substring(0,150)+" ...");
                else dataPreventInfor.setProperty_info_disp(null);
                dataPreventInfors.add(dataPreventInfor);
            }
        }
        return dataPreventInfors;
    }
}

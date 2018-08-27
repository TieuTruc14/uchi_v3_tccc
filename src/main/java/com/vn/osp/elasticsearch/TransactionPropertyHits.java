package com.vn.osp.elasticsearch;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.vn.osp.modelview.DataPreventInfor;
import com.vn.osp.modelview.TransactionProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tranv on 3/25/2017.
 */
public class TransactionPropertyHits {
    private Long total;
    private Long max_score;
    private List<TransactionPropertyHit> hits;

    @JsonCreator
    public TransactionPropertyHits(@JsonProperty(value = "total", required = false) final Long total,
                                   @JsonProperty(value = "max_score", required = false) final Long max_score,
                                   @JsonProperty(value = "hits", required = false) final List<TransactionPropertyHit> hits) {
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

    public List<TransactionPropertyHit> getHits() {
        return hits;
    }

    public void setHits(List<TransactionPropertyHit> hits) {
        this.hits = hits;
    }

    public List<TransactionProperty> getTransactionProperties(){
        ArrayList<TransactionProperty> transactionProperties = new ArrayList<TransactionProperty>();
        if(hits != null && hits.size()>0){
            for (int i=0; i<hits.size();i++){
                transactionProperties.add(hits.get(i).get_source());
            }
        }
        return transactionProperties;
    }
}

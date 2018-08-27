package com.vn.osp.elasticsearch;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.vn.osp.modelview.DataPreventInfor;
import com.vn.osp.modelview.TransactionProperty;

/**
 * Created by tranv on 3/25/2017.
 */
public class DataPreventInforHit {
    private Long _id;
    private String _index;
    private String _type;
    private Long _score;
    private DataPreventInfor _source;

    @JsonCreator
    public DataPreventInforHit(@JsonProperty(value = "_id", required = false) final Long _id,
                               @JsonProperty(value = "_index", required = false) final String _index,
                               @JsonProperty(value = "_type", required = false) final String _type,
                               @JsonProperty(value = "_score", required = false) final Long _score,
                               @JsonProperty(value = "_source", required = false) final DataPreventInfor _source) {
        this._id = _id;
        this._index = _index;
        this._type = _type;
        this._score = _score;
        this._source = _source;
    }

    public Long get_id() {
        return _id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    public String get_index() {
        return _index;
    }

    public void set_index(String _index) {
        this._index = _index;
    }

    public String get_type() {
        return _type;
    }

    public void set_type(String _type) {
        this._type = _type;
    }

    public Long get_score() {
        return _score;
    }

    public void set_score(Long _score) {
        this._score = _score;
    }

    public DataPreventInfor get_source() {
        return _source;
    }

    public void set_source(DataPreventInfor _source) {
        this._source = _source;
    }
}

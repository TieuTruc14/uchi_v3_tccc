package com.vn.osp.elasticsearch;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by tranv on 3/26/2017.
 */
public class DeleteResponse {
    private String _index;
    private String _type;
    private String _id;
    private String _version;
    private String result;
    private Shards _shards;
    private Boolean found;


    @JsonCreator
    public DeleteResponse(@JsonProperty(value = "_index", required = false) final String _index,
                       @JsonProperty(value = "_type", required = false) final String _type,
                       @JsonProperty(value = "_id", required = false) final String _id,
                       @JsonProperty(value = "_version", required = false) final String _version,
                       @JsonProperty(value = "result", required = false) final String result,
                       @JsonProperty(value = "_shards", required = false) final Shards _shards,
                       @JsonProperty(value = "found", required = false) final Boolean found) {
        this._index = _index;
        this._type = _type;
        this._id = _id;
        this._version = _version;
        this.result = result;
        this._shards = _shards;
        this.found = found;
    }

    public DeleteResponse() {
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

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String get_version() {
        return _version;
    }

    public void set_version(String _version) {
        this._version = _version;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Shards get_shards() {
        return _shards;
    }

    public void set_shards(Shards _shards) {
        this._shards = _shards;
    }

    public Boolean getFound() {
        return found;
    }

    public void setFound(Boolean found) {
        this.found = found;
    }
}

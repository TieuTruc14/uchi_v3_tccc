package com.vn.osp.elasticsearch;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by tranv on 3/24/2017.
 */
public class CountResponse {
    private Shards _shards;
    private Long count;


    @JsonCreator
    public CountResponse(
            @JsonProperty(value = "_shards", required = false) final Shards _shards,
            @JsonProperty(value = "count", required = false) final Long count) {
        this._shards = _shards;
        this.count = count;
    }

    public CountResponse() {
    }

    public Shards get_shards() {
        return _shards;
    }

    public void set_shards(Shards _shards) {
        this._shards = _shards;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}

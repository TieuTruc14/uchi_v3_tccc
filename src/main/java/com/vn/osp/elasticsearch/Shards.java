package com.vn.osp.elasticsearch;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by tranv on 3/24/2017.
 */
public class Shards {
    private Long total;
    private Long successful;
    private Long failed;

    @JsonCreator
    public Shards(@JsonProperty(value = "total", required = false) final Long total,
                  @JsonProperty(value = "successful", required = false) final Long successful,
                  @JsonProperty(value = "failed", required = false) final Long failed) {
        this.total = total;
        this.successful = successful;
        this.failed = failed;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Long getSuccessful() {
        return successful;
    }

    public void setSuccessful(Long successful) {
        this.successful = successful;
    }

    public Long getFailed() {
        return failed;
    }

    public void setFailed(Long failed) {
        this.failed = failed;
    }
}

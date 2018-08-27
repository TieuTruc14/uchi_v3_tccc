package com.vn.osp.modelview;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;


public class SystemConfig {
    private Integer id;
    private String config_key;
    private String config_value;

    @JsonCreator
    public SystemConfig(@JsonProperty(value = "id", required = true) final Integer id,
                        @JsonProperty(value = "config_key", required = true) final String config_key,
                        @JsonProperty(value = "config_value", required = false) final String config_value) {
        this.id = id;
        this.config_key = config_key;
        this.config_value = config_value;
    }

    public SystemConfig() {
    }

    public Integer getSystemConfigId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getConfig_key() {
        return config_key;
    }

    public void setConfig_key(String config_key) {
        this.config_key = config_key;
    }

    public String getConfig_value() {
        return config_value;
    }

    public void setConfig_value(String config_value) {
        this.config_value = config_value;
    }
}

package com.vn.osp.modelview;


import java.util.Date;

/**
 * Created by TienManh on 8/5/2017.
 */
public class PropertyTemplate {
    private int id;
    private String code;
    private String type;
    private String name;
    private String description;
    private String html;
    private int entry_user;
    private Date entry_time;
    private int update_user;
    private Date update_time;
    private boolean disable;

    public PropertyTemplate(int id,String code, String type, String name, String description, String html, int entry_user, Date entry_time, int update_user, Date update_time,boolean disabled) {
        this.id = id;
        this.code=code;
        this.type = type;
        this.name = name;
        this.description=description;
        this.html = html;
        this.entry_user = entry_user;
        this.entry_time = entry_time;
        this.update_user = update_user;
        this.update_time = update_time;
        this.disable=disabled;
    }

    public PropertyTemplate() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public int getEntry_user() {
        return entry_user;
    }

    public void setEntry_user(int entry_user) {
        this.entry_user = entry_user;
    }

    public int getUpdate_user() {
        return update_user;
    }

    public void setUpdate_user(int update_user) {
        this.update_user = update_user;
    }

    public Date getEntry_time() {
        return entry_time;
    }

    public void setEntry_time(Date entry_time) {
        this.entry_time = entry_time;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDisable() {
        return disable;
    }

    public void setDisable(boolean disable) {
        this.disable = disable;
    }
}

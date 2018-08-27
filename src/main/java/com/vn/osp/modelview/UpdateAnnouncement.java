package com.vn.osp.modelview;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vn.osp.context.CommonContext;
import org.json.JSONObject;

import java.util.Date;
import java.util.List;

/**
 * Created by minh on 4/10/2017.
 */
public class UpdateAnnouncement {

    private Announcement Announcement;

    private Long aid;



    private String authentication_id;

    @JsonCreator
    public UpdateAnnouncement(@JsonProperty(value = "announcement", required = true) Announcement Announcement,
                               @JsonProperty(value = "aid", required = true) Long aid,
                              String authentication_id){
        this.Announcement = Announcement;
        this.aid = aid;
        this.authentication_id = authentication_id;
    }

    public UpdateAnnouncement() {
    }

    public Announcement getAnnouncement() {
        return Announcement;
    }

    public void setAnnouncement(Announcement announcement) {
        Announcement = announcement;
    }

    public Long getAid(){
        return aid;}

    public void setAid(Long aid){ this.aid = aid;}


    public String getAuthentication_id(){return authentication_id;}

    public void setAuthentication_id(String authentication_id){this.authentication_id = authentication_id;}





    public String updateJSON(User user){
        JSONObject obj = new JSONObject();
        ObjectMapper mapper = new ObjectMapper();
        Announcement.setUpdate_user_id(user.getUserId());
        Announcement.setUpdate_date_time(user.getUpdate_date_time());
        Announcement.setSend_date_time(null);
        /*Announcement.setSender_info("truong_check");*/
        Announcement.setEntry_date_time(user.getEntry_date_time());
        Announcement.setEntry_user_id(user.getUserId());
        Announcement.setAuthentication_id(null);
        Announcement.setUpdate_user_name(user.getFirst_name()+" " +user.getFamily_name());

        try {
            return mapper.writeValueAsString(Announcement);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

}

package com.vn.osp.modelview;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vn.osp.context.CommonContext;
import org.json.JSONObject;

import java.util.List;


public class AddAnnouncement{

    private Announcement Announcement;

    private Long aid;

    private List<NotaryOffice> notaryOffices;


    @JsonCreator
    public AddAnnouncement(@JsonProperty(value = "announcement", required = true) Announcement Announcement,
                           @JsonProperty(value = "aid", required = true) Long aid,
                           List<NotaryOffice> notaryOffices ){
        this.Announcement = Announcement;
        this.aid = aid;
        this.notaryOffices = notaryOffices;
    }

    public AddAnnouncement() {
    }

    public Announcement getAnnouncement() {
        return Announcement;
    }

    public void setAnnouncement(Announcement announcement) {
        Announcement = announcement;
    }

    public Long getAid(){return aid;}

    public void setAid(Long aid){ this.aid = aid;}


    public List<NotaryOffice> getNotaryOffices() {
        return notaryOffices;
    }

    public void setNotaryOffices(List<NotaryOffice> notaryOffices) {
        this.notaryOffices = notaryOffices;
    }




    public String addJSON(User user){
        JSONObject obj = new JSONObject();
        ObjectMapper mapper = new ObjectMapper();



        Announcement.setKind(Long.valueOf(1));
        Announcement.setConfirm_request(Long.valueOf(1));
        Announcement.setUpdate_user_id(user.getUpdate_user_id());
        Announcement.setUpdate_date_time(user.getUpdate_date_time());
        Announcement.setSend_date_time(null);
        Announcement.setEntry_date_time(user.getEntry_date_time());
        Announcement.setEntry_user_id(user.getUserId());
        Announcement.setAuthentication_id(null);
        Announcement.setSender_info(user.getFamily_name()+" " + user.getFirst_name());
        Announcement.setEntry_user_name(user.getAccount());
        Announcement.setAnnouncement_type(Long.valueOf(0));

        Announcement.setUpdate_user_name(null);

        try {
            return mapper.writeValueAsString(Announcement);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

}

package com.vn.osp.modelview;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;


public class AnnouncementWrapper {

    private Announcement Announcement;

    private Long aid;

    @JsonCreator
    public AnnouncementWrapper(@JsonProperty(value = "announcement", required = true) Announcement Announcement,
                               @JsonProperty(value = "aid", required = true) Long aid)  {
        this.Announcement = Announcement;
        this.aid = aid;
    }

    public AnnouncementWrapper() {
    }

    public Announcement getAnnouncement() {
        return Announcement;
    }

    public void setAnnouncement(Announcement announcement) {
        Announcement = announcement;
    }

    public Long getAid(){return aid;}

    public void setAid(Long aid){ this.aid = aid;}



    public String abcJson(){
        JSONObject obj = new JSONObject();
        ObjectMapper mapper = new ObjectMapper();
        //


        Announcement.setKind(Long.valueOf(1));
        Announcement.setPopup_display_flg(Long.valueOf(1));
        Announcement.setPopup_display_day(Long.valueOf(7));
        Announcement.setConfirm_request(Long.valueOf(1));
        Announcement.setPopup_display_day(Long.valueOf(1));
        Announcement.setUpdate_user_id(Long.valueOf(1));
        Announcement.setUpdate_date_time("10/10/2016");/*cua anh Long*/
        Announcement.setSend_date_time("10/10/2016");
        Announcement.setEntry_date_time("10/10/2016");
        Announcement.setEntry_user_id(Long.valueOf(3));
        Announcement.setAuthentication_id("MAN");

        try {
            return mapper.writeValueAsString(Announcement);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

}

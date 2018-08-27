package com.vn.osp.modelview;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;

/**
 * Created by minh on 12/23/2016.
 */
public class DeleteAnnouncementWrapper {
    private Announcement Announcement;

    private Long aid;

    @JsonCreator
    public DeleteAnnouncementWrapper(@JsonProperty(value = "announcement", required = true) Announcement Announcement,
                                     @JsonProperty(value = "aid", required = true) Long aid)
    {
        this.Announcement = Announcement;
        this.aid = aid;
    }

    public DeleteAnnouncementWrapper() {
    }
    public Long getAid() {
        return aid;
    }

    public void setAid(Long aid) {
        this.aid= aid;
    }

    public Announcement getAnnouncement() {
        return Announcement;
    }

    public void setAnnouncement(Announcement announcement) {
        Announcement = announcement;
    }

    public JSONObject generateJsonObject(){
        JSONObject obj = new JSONObject();
        ObjectMapper mapper = new ObjectMapper();

        try {
            obj.put("aid", aid);
            obj.put("announcement", new JSONObject(mapper.writeValueAsString(Announcement)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

}

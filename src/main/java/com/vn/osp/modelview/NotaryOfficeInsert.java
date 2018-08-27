package com.vn.osp.modelview;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vn.osp.context.CommonContext;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by minh on 4/19/2017.
 */
public class NotaryOfficeInsert {
    private NotaryOffice NotaryOffice;

    private Long noid;

    private List<ProvinceList> provinceList;

    private String authenticationId;

    private Long notaryIndex;


    @JsonCreator
    public NotaryOfficeInsert(@JsonProperty(value = "NotaryOffice", required = true) NotaryOffice notaryOffice,
                           @JsonProperty(value = "noid", required = true) Long noid,
                              List<ProvinceList> preventList,
                              String authenticationId){
        this.NotaryOffice = notaryOffice;
        this.noid = noid;
        this.provinceList = preventList;
        this.authenticationId =authenticationId;
    }

    public NotaryOfficeInsert() {
    }

    public NotaryOffice getNotaryOffice() {
        return NotaryOffice;
    }

    public void setNotaryOffice(NotaryOffice notaryOffice) {
        NotaryOffice = notaryOffice;
    }

    public Long getNoid(){return noid;}

    public void setNoid(Long noid){ this.noid = noid;}

    public List<ProvinceList> getProvinceList() {return provinceList;}

    public void setProvinceList(List<ProvinceList> provinceList){ this.provinceList=provinceList;}

    public String getAuthenticationId(){return authenticationId;}

    public void setAuthenticationId(String authenticationId){ this.authenticationId= authenticationId;}

    public Long getNotaryIndex(){return notaryIndex;}

    public void setNotaryIndex(Long notaryIndex){ this.notaryIndex = notaryIndex;}


    public String addJSON(User user){
        JSONObject obj = new JSONObject();
        ObjectMapper mapper = new ObjectMapper();
        //

        NotaryOffice.setMac_address("");

        NotaryOffice.setOffice_type(Long.valueOf(2));
        NotaryOffice.setEntry_user_name(user.getFirst_name()+" "+user.getFamily_name());
        NotaryOffice.setEntry_user_id(user.getUserId());
        NotaryOffice.setLast_connection_time(null);
        NotaryOffice.setHidden_flg(Long.valueOf(0));
        NotaryOffice.setLast_connection_time("2016-6-6");
        NotaryOffice.setSynchronize_type(Long.valueOf(2));
        NotaryOffice.setAuthentication_id(authenticationId);


        

        try {
            return mapper.writeValueAsString(NotaryOffice);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

}

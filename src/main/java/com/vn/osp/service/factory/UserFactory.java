package com.vn.osp.service.factory;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vn.osp.common.global.Constants;
import com.vn.osp.modelview.GrouproleAuthority;
import com.vn.osp.modelview.User;
import com.vn.osp.service.Client.Client;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by TienManh on 7/10/2017.
 */
public class UserFactory {

    public static List<User> getListUser(){
        String path="/users/selectByFilter";
        String response= Client.postObject(path,"where true");
        List<User> users=new ArrayList<User>();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try{
            users = Arrays.asList(objectMapper.readValue(response, User[].class));
        }catch (Exception e){
            System.out.printf("Co loi xay ra UserFactory.getListUser: "+e.getMessage());
        }
        return users;
    }

    public static List<User> getListUserByAuthorityCode(String authority_code){
        String path="/users/users-by-authority-code";
        String response= Client.getObjectByFilter(path,"authority_code",authority_code);
        List<User> users=new ArrayList<User>();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try{
            users = Arrays.asList(objectMapper.readValue(response, User[].class));
        }catch (Exception e){
            System.out.printf("Co loi xay ra UserFactory.getListUserByAuthorityCode: "+e.getMessage());
        }
        return users;
    }

    public static List<User> getListUserByAuthorityDetail(String authority_code, int authority_type){
        if(StringUtils.isBlank(authority_code)){return null;}
        List<User> users=getListUserByAuthorityCode(authority_code);
        List<User> items=new ArrayList<User>();

        if(users!=null && users.size()>0){
            for(int i=0;i<users.size();i++){
                if(checkRoleDetailUser(users.get(i),authority_code,authority_type)){
                    items.add(users.get(i));
                }
            }
        }
        return items;
    }


    public static boolean checkRoleDetailUser(User user,String authority_code, int authority_type){
        List<GrouproleAuthority> items=new ArrayList<GrouproleAuthority>();
        String param1[]={"userId",user.getUserId().toString()};
        String param2[]={"authority_code",authority_code};
        List<String[]> params=new ArrayList<String[]>();
        params.add(param1);
        params.add(param2);
        String path= Constants.VPCC_API_LINK + "/users/select-grouprole-authority-by-user-and-authority-code";
        String response=Client.getObjectByParams(path,params);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try{
            items = Arrays.asList(objectMapper.readValue(response, GrouproleAuthority[].class));
            if(items!=null && items.size()>0){
                for(int i=0;i<items.size();i++){
                    if((items.get(i).getValue()& authority_type)>0){
                        return true;
                    }
                }
            }
        }catch (Exception e){
            System.out.printf("Co loi xay ra UserFactory.getListUserByAuthorityCode: "+e.getMessage());
        }
        return false;
    }
}

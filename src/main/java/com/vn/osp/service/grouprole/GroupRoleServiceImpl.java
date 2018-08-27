package com.vn.osp.service.grouprole;

import com.vn.osp.common.global.Constants;
import com.vn.osp.modelview.*;
import com.vn.osp.service.APIUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 5/6/2017.
 */
public class GroupRoleServiceImpl implements GroupRoleService{
    private static GroupRoleService instance;

    public static GroupRoleService getInstance(){
        if (instance == null) {
            instance = new GroupRoleServiceImpl();
        }
        return instance;
    }

    /******************************Group-Role*************************************/
    public GroupRole getGroupRole(long id) {
        String filter = " where id=" + id + " ";
        List<GroupRole> result = APIUtil.getGroupRoleByFilter(Constants.VPCC_API_LINK+"/users/select-group-role", filter);
        if (result != null && result.size() > 0) {
            return result.get(0);
        }
        return null;
    }

    public List<UserGroupRole> getUserGroupRoleList(Long userId) {
        String filter = " where nua.user_Id =" + userId + " and active_flg=1";
        List<UserGroupRole> result = APIUtil.getUserGroupRoleByFilter(Constants.VPCC_API_LINK+"/users/select-user-group-role", filter);
        return result;
    }

    public List<GroupRole> getActiveGroupRole(String filter) {
        List<GroupRole> result = APIUtil.getGroupRoleByFilter(Constants.VPCC_API_LINK+"/users/select-group-role", "where active_flg=1");
        return result;
    }

    public List<GroupRole> getActiveGroupRoleByPage(int page, String titleFilter) {
        int offset = Constants.ROW_PER_PAGE * (page - 1);
        if (offset < 0) offset = 0;
        String filter = " where active_flg =1 and grouprolename like '%" + titleFilter + "%'";
        filter = filter + " limit " + offset + "," + Constants.ROW_PER_PAGE;
        ////System.out.println(filter);
        List<GroupRole> result = APIUtil.getGroupRoleByFilter(Constants.VPCC_API_LINK+"/users/select-group-role", filter);
        return result;
    }
    public List<GroupRole> getGroupRoleByPage(int page, String titleFilter) {
        int offset = Constants.ROW_PER_PAGE * (page - 1);
        if (offset < 0) offset = 0;
        String filter = " where grouprolename like '%" + titleFilter + "%'";
        filter = filter + " limit " + offset + "," + Constants.ROW_PER_PAGE;
        ////System.out.println(filter);
        List<GroupRole> result = APIUtil.getGroupRoleByFilter(Constants.VPCC_API_LINK+"/users/select-group-role", filter);
        return result;
    }

    public int countTotalActiveGroupRole(String titleFilter) {
        String filter = " where active_flg =1 and grouprolename like '%" + titleFilter + "%'";
        Integer result = APIUtil.countTotalList(Constants.VPCC_API_LINK+"/users/count-total-group-role", filter);
        return result;
    }
    public int countTotalGroupRole(String titleFilter) {
        String filter = " where grouprolename like '%" + titleFilter + "%'";
        Integer result = APIUtil.countTotalList(Constants.VPCC_API_LINK+"/users/count-total-group-role", filter);
        return result;
    }

    public boolean createGroupRole(String data) {
        Boolean result = APIUtil.callAPIResult(Constants.VPCC_API_LINK+"/users/add-grouprole", data);
        return result;
    }

    public boolean updateGroupRole(String data) {
        Boolean result = APIUtil.callAPIResult(Constants.VPCC_API_LINK+"/users/update-grouprole", data);
        return result;
    }

    public boolean deleteGroupRole(long id) {
        Boolean result = APIUtil.callAPIResult(Constants.VPCC_API_LINK+"/users/delete-group-role-by-groupid", String.valueOf(id));
        return result;
    }

    /*******************************Group-Role-Authority****************************/
    public ArrayList<GrouproleAuthority> getGroupRoleAuthorityByFilter(String strFilter) {
        ArrayList<GrouproleAuthority> result = APIUtil.getGroupRoleAuthorityFilter(Constants.VPCC_API_LINK+"/users/select-group-role-authority", strFilter);
        return result;
    }

    public GrouproleAuthority getGroupRoleAuthority(long id) {
        String strFilter = " where grouprole_id=" + id;
        ArrayList<GrouproleAuthority> result = APIUtil.getGroupRoleAuthorityFilter(Constants.VPCC_API_LINK+"/users/select-group-role-authority", strFilter);
        if (result != null && result.size() > 0) return result.get(0);
        else return null;
    }
    public List<GrouproleAuthority> getGroupRoleAuthorites(long id) {
        String strFilter = " where grouprole_id=" + id;
        ArrayList<GrouproleAuthority> result = APIUtil.getGroupRoleAuthorityFilter(Constants.VPCC_API_LINK+"/users/select-group-role-authority", strFilter);
        return result;
    }

    public boolean deleteGroupRoleAuthority(long id) {
        Boolean result = APIUtil.callAPIResult(Constants.VPCC_API_LINK+"/users/delete-group-role-authority-by-groupid", String.valueOf(id));
        return result;
    }

}

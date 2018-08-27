package com.vn.osp.service.grouprole;

import com.vn.osp.modelview.GroupRole;
import com.vn.osp.modelview.GrouproleAuthority;
import com.vn.osp.modelview.UserGroupRole;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 5/6/2017.
 */
public interface GroupRoleService {
    /********************Group-Role**************************/
    public GroupRole getGroupRole(long id);

    public List<UserGroupRole> getUserGroupRoleList(Long userId);

    public List<GroupRole> getActiveGroupRole(String strFilter);

    public List<GroupRole> getActiveGroupRoleByPage(int page, String titleFilter);

    public List<GroupRole> getGroupRoleByPage(int page, String titleFilter);

    public int countTotalActiveGroupRole(String titleFilter);

    public int countTotalGroupRole(String strFilter);

    public boolean createGroupRole(String data);

    public boolean updateGroupRole(String data);

    public boolean deleteGroupRole(long id);

    /*********************Group-Role-Authority******************/
    public ArrayList<GrouproleAuthority> getGroupRoleAuthorityByFilter(String strFilter);

    public GrouproleAuthority getGroupRoleAuthority(long id);

    public List<GrouproleAuthority> getGroupRoleAuthorites(long id);

    public boolean deleteGroupRoleAuthority(long id);

}

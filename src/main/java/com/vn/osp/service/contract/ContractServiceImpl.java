package com.vn.osp.service.contract;

import com.vn.osp.modelview.ContractKindHome;
import com.vn.osp.service.grouprole.GroupRoleService;
import com.vn.osp.service.grouprole.GroupRoleServiceImpl;

import java.util.List;

/**
 * Created by Admin on 5/8/2017.
 */
public class ContractServiceImpl implements ContractService{
    private static GroupRoleService instance;

    public static GroupRoleService getInstance(){
        if (instance == null) {
            instance = new GroupRoleServiceImpl();
        }
        return instance;
    }

    public List<ContractKindHome> getAllContractKind() {
        return null;
    }
}

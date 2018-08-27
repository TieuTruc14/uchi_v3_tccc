package com.vn.osp.modelview;

import com.vn.osp.common.util.TimeUtil;

import java.util.Date;
import java.util.List;

/**
 * Created by minh on 3/3/2017.
 */
public class NotaryOfficeList {
    private String notary_office_name;
    private Long type;
    public NotaryOfficeList() {
    }

    public String getNotary_office_name() {
        return notary_office_name;
    }

    public void setNotary_office_name(String notary_office_name) {
        this.notary_office_name= notary_office_name;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long Type) {
        this.type = type;
    }


}

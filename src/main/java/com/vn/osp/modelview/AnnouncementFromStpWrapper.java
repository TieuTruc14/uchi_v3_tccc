package com.vn.osp.modelview;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by minh on 5/16/2017.
 */
public class AnnouncementFromStpWrapper {
    List<AnnouncementFromStp> announcementFromStpList;
    Integer countTotal;

    public AnnouncementFromStpWrapper() {
    }

    public List<AnnouncementFromStp> getAnnouncementFromStpList() {
        return announcementFromStpList;
    }

    public void setAnnouncementFromStpList(List<AnnouncementFromStp> announcementFromStpList) {
        this.announcementFromStpList = announcementFromStpList;
    }

    public Integer getCountTotal() {
        return countTotal;
    }

    public void setCountTotal(Integer countTotal) {
        this.countTotal = countTotal;
    }
}

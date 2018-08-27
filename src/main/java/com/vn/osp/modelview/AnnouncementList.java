package com.vn.osp.modelview;

import com.vn.osp.common.util.TimeUtil;
import com.vn.osp.context.CommonContext;

import java.util.Date;
import java.util.List;

/**
 * Created by minh on 12/29/2016.
 */
public class AnnouncementList {

    public static final String SESSION_KEY = "AnnouncemeneList";

    private List<Announcement> announcementInternalList;

    private int internalListNumber;
    private int internalTotalPage;

    private List<AnnouncementFromStp> announcementFromStps;
    private int publicListNumber;
    private int publicTotalPage;

    private AnnouncementFromStpWrapper announcementFromStpWrapper;


    private int page;

    private String senderInfoFilter;
    private int titleFilter;
    private int importansFilter;
    private int userEntryFilter;
    private int dateEntryFilter;
    private String authenticationFilter;
    /*tab*/
    private int defaultTabOpen;
    private int internalPage;
    private int publicPage;

    /*date*/

    private String timeType;
    private String toDate;
    private String fromDate;

    private String notaryDateFromFilter;
    private String notaryDateToFilter;

    private String actionStatus;


    private String announcementTitleFilter;

    public AnnouncementList() {
    }


    public List<Announcement> getAnnouncementInternalList() {
        return announcementInternalList;
    }

    public void setAnnouncementInternalList(List<Announcement> announcementInternalList) {
        this.announcementInternalList = announcementInternalList;
    }

    public List<AnnouncementFromStp> getAnnouncementFromStps() {
        return announcementFromStps;
    }

    public void setAnnouncementFromStps(List<AnnouncementFromStp> announcementFromStps) {
        this.announcementFromStps = announcementFromStps;
    }

    public int getInternalListNumber() {
        return internalListNumber;
    }

    public void setInternalListNumber(int internalListNumber) {
        this.internalListNumber = internalListNumber;
    }

    public int getInternalTotalPage() {
        return internalTotalPage;
    }

    public void setInternalTotalPage(int internalTotalPage) {
        this.internalTotalPage = internalTotalPage;
    }

    public int getPublicListNumber() {
        return publicListNumber;
    }

    public void setPublicListNumber(int publicListNumber) {
        this.publicListNumber = publicListNumber;
    }

    public int getPublicTotalPage() {
        return publicTotalPage;
    }

    public void setPublicTotalPage(int publicTotalPage) {
        this.publicTotalPage = publicTotalPage;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTitleFilter() {
        return titleFilter;
    }

    public void setTitleFilter(int titleFilter) {
        this.titleFilter = titleFilter;
    }

    public int getImportansFilter() {
        return importansFilter;
    }

    public void setImportansFilter(int importansFilter) {
        this.importansFilter = importansFilter;
    }

    public int getUserEntryFilter() {
        return userEntryFilter;
    }

    public void setUserEntryFilter(int userEntryFilter) {
        this.userEntryFilter = userEntryFilter;
    }

    public int getDateEntryFilter() {
        return dateEntryFilter;
    }

    public void setDateEntryFilter(int dateEntryFilter) {
        this.dateEntryFilter = dateEntryFilter;
    }

    public String getAnnouncementTitleFilter() {
        return announcementTitleFilter;
    }

    public int getDefaultTabOpen() {
        return defaultTabOpen;
    }

    public void setDefaultTabOpen(int defaultTabOpen) {
        this.defaultTabOpen = defaultTabOpen;
    }

    public int getInternalPage() {
        return internalPage;
    }

    public void setInternalPage(int internalPage) {
        this.internalPage = internalPage;
    }

    public int getPublicPage() {
        return publicPage;
    }

    public void setPublicPage(int publicPage) {
        this.publicPage = publicPage;
    }


    public void setAnnouncementTitleFilter(String announcementTitleFilter) {
        this.announcementTitleFilter = announcementTitleFilter;
    }

    public String getNotaryDateFromFilter() {
        return notaryDateFromFilter;
    }

    public void setNotaryDateFromFilter(String notaryDateFromFilter) {
        this.notaryDateFromFilter = notaryDateFromFilter;
    }

    public String getNotaryDateToFilter() {
        return notaryDateToFilter;
    }

    public void setNotaryDateToFilter(String notaryDateToFilter) {
        this.notaryDateToFilter = notaryDateToFilter;
    }

    public String getSenderInfoFilter() {
        return senderInfoFilter;
    }

    public void setSenderInfoFilter(String senderInfoFilter) {
        this.senderInfoFilter = senderInfoFilter;
    }


    public String getTimeType() {
        return timeType;
    }

    public void setTimeType(String timeType) {
        this.timeType = timeType;
    }

    public String getToDate(){return toDate; }

    public void setToDate(String toDate){ this.toDate = toDate;}

    public String getFromDate(){return fromDate; }

    public void setFromDate(String fromDate){ this.fromDate = fromDate;}

    public String getAuthenticationFilter(){return authenticationFilter; }

    public void setAuthenticationFilter(String authenticationFilter){ this.authenticationFilter = authenticationFilter;}

    public AnnouncementFromStpWrapper getAnnouncementFromStpWrapper() {
        return announcementFromStpWrapper;
    }

    public void setAnnouncementFromStpWrapper(AnnouncementFromStpWrapper announcementFromStpWrapper) {
        this.announcementFromStpWrapper = announcementFromStpWrapper;
    }

    public String getActionStatus() {
        return actionStatus;
    }

    public void setActionStatus(String actionStatus) {
        this.actionStatus = actionStatus;
    }

    public String getOrderString1(){

        String whereString ="  where 1=1 " ;
        String titleString = "";
        if (announcementTitleFilter != null && !announcementTitleFilter.equals("")) {
            titleString += " and title like '%" + announcementTitleFilter.trim() + "%' ";
        }
        String query = whereString + titleString  ;
        return query;
    }

    public String getOrderString2(){
        String whereString ="  where nar.authentication_id = '"+CommonContext.authentication_id+"'" ;
        String titleString = "";
        if (announcementTitleFilter != null && !announcementTitleFilter.equals("")) {
            titleString += " and na.title like '%" + announcementTitleFilter.trim() + "%' ";
        }
        String query = whereString + titleString +"ORDER BY na.entry_date_time DESC" ;
        return query;

    }

}

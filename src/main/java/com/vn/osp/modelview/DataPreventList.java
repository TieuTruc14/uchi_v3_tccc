package com.vn.osp.modelview;

import java.util.List;

/**
 * Created by tranv on 12/10/2016.
 */
public class DataPreventList {
    public static final String SESSION_KEY = "DataPreventList";
    private List<DataPreventInfor> daTiepNhanList;
    private int daTiepNhanListNumber;
    private int daTiepNhanTotalPage;

    private List<DataPreventInfor> daTrinhLanhdaoList;
    private int daTrinhLanhdaoListNumber;
    private int daTrinhLanhDaoTotalPage;

    private List<DataPreventInfor> dangXuLyList;
    private int dangXuLyListNumber;
    private int dangXuLyTotalPage;

    private List<DataPreventInfor> daDuyetList;
    private int daDuyetListNumber;
    private int daDuyetTotalPage;

    private List<DataPreventInfor> khongDuyetList;
    private int khongDuyetListNumber;
    private int khongDuyetTotalPage;

    private int defaultTabOpen;
    private int daTiepNhanPage;
    private int daTrinhLanhDaoPage;
    private int dangXuLyPage;
    private int daDuyetPage;
    private int khongDuyetPage;

    //is advance search
    private String isAdvanceSearch;
    private String propertyType;
    private String propertyInfo;
    private String ownerInfo;
    private boolean advanceAreaSearch;

    //is key search
    private String stringKey;
    private boolean basicAreaSearch;
    private String searchTime;
    private Boolean onlyAreaSaerch;

    private String action_status;

    public DataPreventList() {
    }

    public List<DataPreventInfor> getKhongDuyetList() {
        return khongDuyetList;
    }

    public void setKhongDuyetList(List<DataPreventInfor> khongDuyetList) {
        this.khongDuyetList = khongDuyetList;
    }

    public int getKhongDuyetListNumber() {
        return khongDuyetListNumber;
    }

    public void setKhongDuyetListNumber(int khongDuyetListNumber) {
        this.khongDuyetListNumber = khongDuyetListNumber;
    }

    public int getDaTiepNhanListNumber() {
        return daTiepNhanListNumber;
    }

    public void setDaTiepNhanListNumber(int daTiepNhanListNumber) {
        this.daTiepNhanListNumber = daTiepNhanListNumber;
    }

    public int getDaTrinhLanhdaoListNumber() {
        return daTrinhLanhdaoListNumber;
    }

    public void setDaTrinhLanhdaoListNumber(int daTrinhLanhdaoListNumber) {
        this.daTrinhLanhdaoListNumber = daTrinhLanhdaoListNumber;
    }

    public int getDangXuLyListNumber() {
        return dangXuLyListNumber;
    }

    public void setDangXuLyListNumber(int dangXuLyListNumber) {
        this.dangXuLyListNumber = dangXuLyListNumber;
    }

    public int getDaDuyetListNumber() {
        return daDuyetListNumber;
    }

    public void setDaDuyetListNumber(int daDuyetListNumber) {
        this.daDuyetListNumber = daDuyetListNumber;
    }

    public List<DataPreventInfor> getDaTiepNhanList() {
        return daTiepNhanList;
    }

    public void setDaTiepNhanList(List<DataPreventInfor> daTiepNhanList) {
        this.daTiepNhanList = daTiepNhanList;
    }

    public List<DataPreventInfor> getDaTrinhLanhdaoList() {
        return daTrinhLanhdaoList;
    }

    public void setDaTrinhLanhdaoList(List<DataPreventInfor> daTrinhLanhdaoList) {
        this.daTrinhLanhdaoList = daTrinhLanhdaoList;
    }

    public List<DataPreventInfor> getDangXuLyList() {
        return dangXuLyList;
    }

    public void setDangXuLyList(List<DataPreventInfor> dangXuLyList) {
        this.dangXuLyList = dangXuLyList;
    }

    public List<DataPreventInfor> getDaDuyetList() {
        return daDuyetList;
    }

    public void setDaDuyetList(List<DataPreventInfor> daDuyetList) {
        this.daDuyetList = daDuyetList;
    }

    public int getDefaultTabOpen() {
        return defaultTabOpen;
    }

    public void setDefaultTabOpen(int defaultTabOpen) {
        this.defaultTabOpen = defaultTabOpen;
    }

    public int getDaTiepNhanTotalPage() {
        return daTiepNhanTotalPage;
    }

    public void setDaTiepNhanTotalPage(int daTiepNhanTotalPage) {
        this.daTiepNhanTotalPage = daTiepNhanTotalPage;
    }

    public int getDaTrinhLanhDaoTotalPage() {
        return daTrinhLanhDaoTotalPage;
    }

    public void setDaTrinhLanhDaoTotalPage(int daTrinhLanhDaoTotalPage) {
        this.daTrinhLanhDaoTotalPage = daTrinhLanhDaoTotalPage;
    }

    public int getDangXuLyTotalPage() {
        return dangXuLyTotalPage;
    }

    public void setDangXuLyTotalPage(int dangXuLyTotalPage) {
        this.dangXuLyTotalPage = dangXuLyTotalPage;
    }

    public int getDaDuyetTotalPage() {
        return daDuyetTotalPage;
    }

    public void setDaDuyetTotalPage(int daDuyetTotalPage) {
        this.daDuyetTotalPage = daDuyetTotalPage;
    }

    public int getKhongDuyetTotalPage() {
        return khongDuyetTotalPage;
    }

    public void setKhongDuyetTotalPage(int khongDuyetTotalPage) {
        this.khongDuyetTotalPage = khongDuyetTotalPage;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public String getPropertyInfo() {
        return propertyInfo;
    }

    public void setPropertyInfo(String propertyInfo) {
        this.propertyInfo = propertyInfo;
    }

    public String getOwnerInfo() {
        return ownerInfo;
    }

    public void setOwnerInfo(String ownerInfo) {
        this.ownerInfo = ownerInfo;
    }

    public int getDaTiepNhanPage() {
        return daTiepNhanPage;
    }

    public void setDaTiepNhanPage(int daTiepNhanPage) {
        this.daTiepNhanPage = daTiepNhanPage;
    }

    public int getDaTrinhLanhDaoPage() {
        return daTrinhLanhDaoPage;
    }

    public void setDaTrinhLanhDaoPage(int daTrinhLanhDaoPage) {
        this.daTrinhLanhDaoPage = daTrinhLanhDaoPage;
    }

    public int getDangXuLyPage() {
        return dangXuLyPage;
    }

    public void setDangXuLyPage(int dangXuLyPage) {
        this.dangXuLyPage = dangXuLyPage;
    }

    public int getDaDuyetPage() {
        return daDuyetPage;
    }

    public void setDaDuyetPage(int daDuyetPage) {
        this.daDuyetPage = daDuyetPage;
    }

    public int getKhongDuyetPage() {
        return khongDuyetPage;
    }

    public void setKhongDuyetPage(int khongDuyetPage) {
        this.khongDuyetPage = khongDuyetPage;
    }

    public String getIsAdvanceSearch() {
        return isAdvanceSearch;
    }

    public void setIsAdvanceSearch(String isAdvanceSearch) {
        this.isAdvanceSearch = isAdvanceSearch;
    }

    public boolean isAdvanceAreaSearch() {
        return advanceAreaSearch;
    }

    public void setAdvanceAreaSearch(boolean advanceAreaSearch) {
        this.advanceAreaSearch = advanceAreaSearch;
    }

    public String getStringKey() {
        return stringKey;
    }

    public void setStringKey(String stringKey) {
        this.stringKey = stringKey;
    }

    public boolean isBasicAreaSearch() {
        return basicAreaSearch;
    }

    public void setBasicAreaSearch(boolean basicAreaSearch) {
        this.basicAreaSearch = basicAreaSearch;
    }

    public String getSearchTime() {
        return searchTime;
    }

    public void setSearchTime(String searchTime) {
        this.searchTime = searchTime;
    }

    public Boolean getOnlyAreaSaerch() {
        return onlyAreaSaerch;
    }

    public void setOnlyAreaSaerch(Boolean onlyAreaSaerch) {
        this.onlyAreaSaerch = onlyAreaSaerch;
    }

    public String getAction_status() {
        return action_status;
    }

    public void setAction_status(String action_status) {
        this.action_status = action_status;
    }
}

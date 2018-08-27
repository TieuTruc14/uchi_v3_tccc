package com.vn.osp.modelview;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by tranv on 12/22/2016.
 */
public class PreventContractList {
    private long user_id;
    private List<DataPreventInfor> daDuyetList;
    private int daDuyetListNumber;
    private int daDuyetTotalPage;

    private List<TransactionProperty> hopDongList;
    private int hopDongListNumber;
    private int hopDongTotalPage;

    private int defaultTabOpen;
    private int daDuyetPage;
    private int hopDongPage;



    //is advance search
    private String isAdvanceSearch;
    private String propertyType;
    private String propertyInfo;
    private String ownerInfo;

    //is key search
    private String stringKey;
    private String listKey;
    private boolean basicAreaSearch;
    private boolean advanceAreaSearch;
    private String searchTime;

    private String notary_office_code;


    public PreventContractList() {
    }

    public List<DataPreventInfor> getDaDuyetList() {
        return daDuyetList;
    }

    public void setDaDuyetList(List<DataPreventInfor> daDuyetList) {
        this.daDuyetList = daDuyetList;
    }

    public int getDaDuyetListNumber() {
        return daDuyetListNumber;
    }

    public void setDaDuyetListNumber(int daDuyetListNumber) {
        this.daDuyetListNumber = daDuyetListNumber;
    }

    public int getDaDuyetTotalPage() {
        return daDuyetTotalPage;
    }

    public void setDaDuyetTotalPage(int daDuyetTotalPage) {
        this.daDuyetTotalPage = daDuyetTotalPage;
    }

    public List<TransactionProperty> getHopDongList() {
        return hopDongList;
    }

    public void setHopDongList(List<TransactionProperty> hopDongList) {
        this.hopDongList = hopDongList;
    }

    public int getHopDongListNumber() {
        return hopDongListNumber;
    }

    public void setHopDongListNumber(int hopDongListNumber) {
        this.hopDongListNumber = hopDongListNumber;
    }

    public int getHopDongTotalPage() {
        return hopDongTotalPage;
    }

    public void setHopDongTotalPage(int hopDongTotalPage) {
        this.hopDongTotalPage = hopDongTotalPage;
    }

    public int getDefaultTabOpen() {
        return defaultTabOpen;
    }

    public void setDefaultTabOpen(int defaultTabOpen) {
        this.defaultTabOpen = defaultTabOpen;
    }

    public int getDaDuyetPage() {
        return daDuyetPage;
    }

    public void setDaDuyetPage(int daDuyetPage) {
        this.daDuyetPage = daDuyetPage;
    }

    public int getHopDongPage() {
        return hopDongPage;
    }

    public void setHopDongPage(int hopDongPage) {
        this.hopDongPage = hopDongPage;
    }

    public String getIsAdvanceSearch() {
        return isAdvanceSearch;
    }

    public void setIsAdvanceSearch(String isAdvanceSearch) {
        this.isAdvanceSearch = isAdvanceSearch;
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

    public String getStringKey() {
        return stringKey;
    }

    public void setStringKey(String stringKey) {
        this.stringKey = stringKey;
    }

    public String getSearchTime() {
        return searchTime;
    }

    public void setSearchTime(String searchTime) {
        this.searchTime = searchTime;
    }


    public boolean isAdvanceAreaSearch() {
        return advanceAreaSearch;
    }

    public void setAdvanceAreaSearch(boolean advanceAreaSearch) {
        this.advanceAreaSearch = advanceAreaSearch;
    }

    public boolean isBasicAreaSearch() {
        return basicAreaSearch;
    }

    public void setBasicAreaSearch(boolean basicAreaSearch) {
        this.basicAreaSearch = basicAreaSearch;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public String getNotary_office_code() {
        return notary_office_code;
    }

    public void setNotary_office_code(String notary_office_code) {
        this.notary_office_code = notary_office_code;
    }

    public String generateJson(){
        JSONObject obj = new JSONObject();
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getListKey() {
        return listKey;
    }

    public void setListKey(String listKey) {
        this.listKey = listKey;
    }
}

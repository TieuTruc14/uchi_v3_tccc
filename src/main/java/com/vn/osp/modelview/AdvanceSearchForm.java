package com.vn.osp.modelview;

/**
 * Created by tranv on 12/19/2016.
 */
public class AdvanceSearchForm {
    private final String SESSION_KEY = "AdvanceSearchForm";
    private Boolean isAdvanceSearch;
    private String propertyType;
    private String propertyInfo;
    private String ownerInfo;

    //if using key search
    private String keySearch;

    public AdvanceSearchForm() {
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

    public Boolean getAdvanceSearch() {
        return isAdvanceSearch;
    }

    public void setAdvanceSearch(Boolean advanceSearch) {
        isAdvanceSearch = advanceSearch;
    }

    public String getKeySearch() {
        return keySearch;
    }

    public void setKeySearch(String keySearch) {
        this.keySearch = keySearch;
    }
}

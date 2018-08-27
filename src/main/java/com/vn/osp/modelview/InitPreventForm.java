package com.vn.osp.modelview;

import java.util.ArrayList;

/**
 * Created by tranv on 4/10/2017.
 */
public class InitPreventForm {
    private ArrayList<UnitRequest> requestArrayList;
    private DataPrevent dataPrevent;
    private PropertyPrevent propertyPrevent;

    public InitPreventForm() {
    }

    public ArrayList<UnitRequest> getRequestArrayList() {
        return requestArrayList;
    }

    public void setRequestArrayList(ArrayList<UnitRequest> requestArrayList) {
        this.requestArrayList = requestArrayList;
    }

    public DataPrevent getDataPrevent() {
        return dataPrevent;
    }

    public void setDataPrevent(DataPrevent dataPrevent) {
        this.dataPrevent = dataPrevent;
    }

    public PropertyPrevent getPropertyPrevent() {
        return propertyPrevent;
    }

    public void setPropertyPrevent(PropertyPrevent propertyPrevent) {
        this.propertyPrevent = propertyPrevent;
    }
}

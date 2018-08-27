package com.vn.osp.modelview;

import com.vn.osp.service.QueryFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by tranv on 3/17/2017.
 */
public class HomeForm {
    public static final String POPUP_FROM_STP_SESSION_KEY = "PopupFromSTPAnnouncement";
    public static final String POPUP_SESSION_KEY = "PopupAnnouncement";
    private Announcement latest;
    private ArrayList<Announcement> announcementArrayList;
    private ArrayList<AnnouncementFromStp> stpAnnouncementArrayList;
    private List<AnnouncementFromStp> stpPopupAnnouncement;
    private List<Announcement> announcementPopup;
    private ArrayList<Contract> contractArrayList;


    private String contentHtmlFromSTP;
    private String contentHtml;


    List<ContractKind> contractKinds;
    List<ContractTemplate> contractTemplates;

    private long contract_kind_id;
    private String contract_kind_name;
    private Map<List<Contract>, Integer> contract_today_info;
    private Map<List<TemporaryContract>, Integer> temp_contract_today_info;

    private int contract_number_in_day;


    public HomeForm() {
    }

    public ArrayList<Contract> getContractArrayList() {
        return contractArrayList;
    }

    public void setContractArrayList(ArrayList<Contract> contractArrayList) {
        this.contractArrayList = contractArrayList;
    }

    public Announcement getLatest() {
        return latest;
    }

    public void setLatest(Announcement latest) {
        this.latest = latest;
    }

    public ArrayList<Announcement> getAnnouncementArrayList() {
        return announcementArrayList;
    }

    public void setAnnouncementArrayList(ArrayList<Announcement> announcementArrayList) {
        this.announcementArrayList = announcementArrayList;
    }


    public List<ContractKind> getContractKinds() {
        return contractKinds;
    }

    public void setContractKinds(List<ContractKind> contractKinds) {
        this.contractKinds = contractKinds;
    }

    public List<ContractTemplate> getContractTemplates() {
        return contractTemplates;
    }

    public void setContractTemplates(List<ContractTemplate> contractTemplates) {
        this.contractTemplates = contractTemplates;
    }

    public long getContract_kind_id() {
        return contract_kind_id;
    }

    public void setContract_kind_id(long contract_kind_id) {
        this.contract_kind_id = contract_kind_id;
    }

    public String getContract_kind_name() {
        return contract_kind_name;
    }

    public void setContract_kind_name(String contract_kind_name) {
        this.contract_kind_name = contract_kind_name;
    }

    public Map<List<Contract>, Integer> getContract_today_info() {
        return contract_today_info;
    }

    public void setContract_today_info(Map<List<Contract>, Integer> contract_today_info) {
        this.contract_today_info = contract_today_info;
    }

    public Map<List<TemporaryContract>, Integer> getTemp_contract_today_info() {
        return temp_contract_today_info;
    }

    public void setTemp_contract_today_info(Map<List<TemporaryContract>, Integer> temp_contract_today_info) {
        this.temp_contract_today_info = temp_contract_today_info;
    }

    public int getContract_number_in_day() {
        return contract_number_in_day;
    }

    public void setContract_number_in_day(int contract_number_in_day) {
        this.contract_number_in_day = contract_number_in_day;
    }

    public ArrayList<AnnouncementFromStp> getStpAnnouncementArrayList() {
        return stpAnnouncementArrayList;
    }

    public void setStpAnnouncementArrayList(ArrayList<AnnouncementFromStp> stpAnnouncementArrayList) {
        this.stpAnnouncementArrayList = stpAnnouncementArrayList;
    }

    public List<AnnouncementFromStp> getStpPopupAnnouncement() {
        return stpPopupAnnouncement;
    }

    public void setStpPopupAnnouncement(List<AnnouncementFromStp> stpPopupAnnouncement) {
        this.stpPopupAnnouncement = stpPopupAnnouncement;
    }

    public List<Announcement> getAnnouncementPopup() {
        return announcementPopup;
    }

    public void setAnnouncementPopup(List<Announcement> announcementPopup) {
        this.announcementPopup = announcementPopup;
    }

    public String getContentHtml() {
        return contentHtml;
    }

    public void setContentHtml(String contentHtml) {
        this.contentHtml = contentHtml;
    }

    public String getContentHtmlFromSTP() {
        return contentHtmlFromSTP;
    }

    public void setContentHtmlFromSTP(String contentHtmlFromSTP) {
        this.contentHtmlFromSTP = contentHtmlFromSTP;
    }
}

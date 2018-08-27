package com.vn.osp.modelview;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by VIETDUNG on 24/04/2017.
 */
public class ConfigBackupDatabaseForm {

    public static final String SESSON_KEY = "ConfigBackupDatabaseForm";

    private String pathBackUp;

    private boolean checkBackup;

    private String backupTime;

    private boolean mon;

    private boolean tue;

    private boolean wed;

    private boolean thu;

    private boolean fri;

    private boolean sat;

    private boolean sun;

    private String fileRestore;

    private String email;

    @JsonIgnore
    private String action_status;

    @JsonIgnore
    private Boolean success;

    /**
     * @return the pathBackUp
     */
    public String getPathBackUp() {
        return pathBackUp;
    }

    /**
     * @param pathBackUp the pathBackUp to set
     */
    public void setPathBackUp(String pathBackUp) {
        this.pathBackUp = pathBackUp;
    }



    /**
     * @return the backupTime
     */
    public String getBackupTime() {
        return backupTime;
    }

    /**
     * @param backupTime the backupTime to set
     */
    public void setBackupTime(String backupTime) {
        this.backupTime = backupTime;
    }

    /**
     * @return the checkBackup
     */
    public boolean isCheckBackup() {
        return checkBackup;
    }

    /**
     * @param checkBackup the checkBackup to set
     */
    public void setCheckBackup(boolean checkBackup) {
        this.checkBackup = checkBackup;
    }



    /**
     * @return the mon
     */
    public boolean isMon() {
        return mon;
    }

    /**
     * @param mon the mon to set
     */
    public void setMon(boolean mon) {
        this.mon = mon;
    }

    /**
     * @return the tue
     */
    public boolean isTue() {
        return tue;
    }

    /**
     * @param tue the tue to set
     */
    public void setTue(boolean tue) {
        this.tue = tue;
    }

    /**
     * @return the wed
     */
    public boolean isWed() {
        return wed;
    }

    /**
     * @param wed the wed to set
     */
    public void setWed(boolean wed) {
        this.wed = wed;
    }

    /**
     * @return the thu
     */
    public boolean isThu() {
        return thu;
    }

    /**
     * @param thu the thu to set
     */
    public void setThu(boolean thu) {
        this.thu = thu;
    }

    /**
     * @return the fri
     */
    public boolean isFri() {
        return fri;
    }

    /**
     * @param fri the fri to set
     */
    public void setFri(boolean fri) {
        this.fri = fri;
    }

    /**
     * @return the sat
     */
    public boolean isSat() {
        return sat;
    }

    /**
     * @param sat the sat to set
     */
    public void setSat(boolean sat) {
        this.sat = sat;
    }

    /**
     * @return the sun
     */
    public boolean isSun() {
        return sun;
    }

    /**
     * @param sun the sun to set
     */
    public void setSun(boolean sun) {
        this.sun = sun;
    }



    /**
     * @return the fileRestore
     */
    public String getFileRestore() {
        return fileRestore;
    }

    /**
     * @param fileRestore the fileRestore to set
     */
    public void setFileRestore(String fileRestore) {
        this.fileRestore = fileRestore;
    }

    public String getAction_status() {
        return action_status;
    }

    public void setAction_status(String action_status) {
        this.action_status = action_status;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

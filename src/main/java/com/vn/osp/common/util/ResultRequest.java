package com.vn.osp.common.util;

import java.util.List;

public class ResultRequest {
    private String messageCode;
    private String message;
    private List<String> messageList;
    private List resultObject;
    private Object object;
    public ResultRequest() {
    }
    public ResultRequest(String messageCode, String message) {
        this.messageCode = messageCode;
        this.message = message;
    }

    public ResultRequest(String messageCode, String message,List resultObject,Object object) {
        this.messageCode = messageCode;
        this.message = message;
        this.resultObject= resultObject;
        this.object = object;
    }

    public ResultRequest(String messageCode, String message, List<String> messageList) {
        this.messageCode = messageCode;
        this.message = message;
        this.messageList = messageList;
    }

    public ResultRequest(String messageCode, String message, List<String> messageList,List resultObject) {
        this.messageCode = messageCode;
        this.message = message;
        this.messageList = messageList;
        this.resultObject= resultObject;
    }

    public String getMessageCode() {
        return messageCode;
    }

    public void setMessageCode(String messageCode) {
        this.messageCode = messageCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<String> messageList) {
        this.messageList = messageList;
    }

    public List getResultObject() {
        return resultObject;
    }

    public void setResultObject(List resultObject) {
        this.resultObject = resultObject;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}

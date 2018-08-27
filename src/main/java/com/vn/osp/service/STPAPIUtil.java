package com.vn.osp.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vn.osp.modelview.*;
import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;

/**
 * Created by tranv on 12/7/2016.
 */
public class STPAPIUtil {
    private static final Logger LOG = LoggerFactory.getLogger(STPAPIUtil.class);

    public static List<DataPreventInfor> daTiepNhan(String actionURL, String data) {
        HttpURLConnection conn =null;
        ArrayList<DataPreventInfor> list =null;
        try {
            URL url = new URL(actionURL);
            conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            String input = data.toString();
            OutputStream os = conn.getOutputStream();
            os.write(input.getBytes("UTF-8"));
            os.flush();
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream()), "UTF-8"));
            String output;
            list = new ArrayList<DataPreventInfor>();
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            while ((output = br.readLine()) != null) {
                JSONArray jsonArray = new JSONArray(output);
                if (jsonArray != null) {
                    int len = jsonArray.length();
                    for (int i = 0; i < len; i++) {
                        list.add(mapper.readValue(jsonArray.get(i).toString(), DataPreventInfor.class));
                    }
                }
            }


        } catch (Exception e) {
          LOG.error("Have an error in method STPAPIUtil.daTiepNhan:"+e.getMessage());
        }finally {
            conn.disconnect();
        }
        return list;
    }

    public static List<TransactionProperty> getTransactionPropertyList(String actionURL, String data) {
        HttpURLConnection conn =null;
        ArrayList<TransactionProperty> list =null;
        try {
            URL url = new URL(actionURL);
            conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            String input = data.toString();
            OutputStream os = conn.getOutputStream();
            os.write(input.getBytes("UTF-8"));
            os.flush();
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream()), "UTF-8"));
            String output;
            list = new ArrayList<TransactionProperty>();
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            while ((output = br.readLine()) != null) {
                JSONArray jsonArray = new JSONArray(output);
                if (jsonArray != null) {
                    int len = jsonArray.length();
                    for (int i = 0; i < len; i++) {
                        list.add(mapper.readValue(jsonArray.get(i).toString(), TransactionProperty.class));

                    }
                }
            }


        } catch (Exception e) {
            LOG.error("Have an error in method STPAPIUtil.getTransactionPropertyList:"+e.getMessage());
        }finally {
            conn.disconnect();
        }
        return list;
    }

    public static AnnouncementFromStpWrapper getAnnouncementFromStpWrapper(String actionURL, String data) {
        HttpURLConnection conn =null;
        AnnouncementFromStpWrapper list =null;
        try {
            URL url = new URL(actionURL);
            conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            //conn.setRequestProperty("Accept-Charset", "UTF-8");

            String input = data.toString();

            if(conn==null || conn.getOutputStream()==null){
                return null;
            }
            OutputStream os = conn.getOutputStream();
            os.write(input.getBytes("UTF-8"));
            os.flush();
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream()), "UTF-8"));

            String output;
            list = new AnnouncementFromStpWrapper();
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            while ((output = br.readLine()) != null) {
                list = mapper.readValue(output, AnnouncementFromStpWrapper.class);
            }


        } catch (Exception e) {
            LOG.error("Have an error in method STPAPIUtil.getAnnouncementFromStpWrapper:"+e.getMessage());
        }finally {
            conn.disconnect();
        }
        return list;
    }
    public static PreventContractList getPreventContractList(String actionURL, String data) {
        HttpURLConnection conn =null;
        PreventContractList list =null;
        try {
            URL url = new URL(actionURL);
            conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            //conn.setRequestProperty("Accept-Charset", "UTF-8");

            String input = data.toString();

            OutputStream os = conn.getOutputStream();
            os.write(input.getBytes("UTF-8"));
            os.flush();

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream()), "UTF-8"));

            String output;
            list = new PreventContractList();
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            while ((output = br.readLine()) != null) {
                list = mapper.readValue(output, PreventContractList.class);
            }

        } catch (Exception e) {
            LOG.error("Have an error in method STPAPIUtil.getPreventContractList:"+e.getMessage());
        }finally {
            conn.disconnect();
        }
        return list;
    }
    public static List<SynchonizeContractKey> synchronizeContract(String actionURL, String data) {
        List<SynchonizeContractKey> list =null;
        HttpURLConnection conn =null;
        try {
            URL url = new URL(actionURL);
            conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            //conn.setRequestProperty("Accept-Charset", "UTF-8");

            String input = data.toString();

            OutputStream os = conn.getOutputStream();
            os.write(input.getBytes("UTF-8"));
            os.flush();

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream()), "UTF-8"));

            String output;
            list = new ArrayList<SynchonizeContractKey>();
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            while ((output = br.readLine()) != null) {
                JSONArray jsonArray = new JSONArray(output);
                if (jsonArray != null) {
                    int len = jsonArray.length();
                    for (int i=0;i<len;i++){
                        list.add(mapper.readValue(jsonArray.get(i).toString(), SynchonizeContractKey.class));
                    }
                }
            }


        } catch (Exception e) {
            LOG.error("Have an error in method STPAPIUtil.synchronizeContract:"+e.getMessage());
        }finally {
            conn.disconnect();
        }
        return list;
    }
    public static List<AnnouncementFromStp> announcementFromSTPList(String actionURL, String data) {
        List<AnnouncementFromStp> list =null;
        HttpURLConnection conn =null;
        try {
            URL url = new URL(actionURL);
            conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            //conn.setRequestProperty("Accept-Charset", "UTF-8");

            String input = data.toString();

            OutputStream os = conn.getOutputStream();
            os.write(input.getBytes("UTF-8"));
            os.flush();

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream()), "UTF-8"));

            String output;
            list = new ArrayList<AnnouncementFromStp>();
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            while ((output = br.readLine()) != null) {
                JSONArray jsonArray = new JSONArray(output);
                if (jsonArray != null) {
                    int len = jsonArray.length();
                    for (int i=0;i<len;i++){
                        list.add(mapper.readValue(jsonArray.get(i).toString(), AnnouncementFromStp.class));
                    }
                }
            }

        } catch (Exception e) {
            LOG.error("Have an error in method STPAPIUtil.announcementFromSTPList:"+e.getMessage());
        }finally {
            conn.disconnect();
        }
        return list;
    }

    public Object getObj(Object obj) {
        if (obj instanceof Announcement) return (Announcement) obj;
        if (obj instanceof DataPreventInfor) return (DataPreventInfor) obj;
        if (obj instanceof PreventHistory) return (PreventHistory) obj;
        if (obj instanceof TransactionProperty) return (TransactionProperty) obj;
        if (obj instanceof ReportByGroupTotal) return (ReportByGroupTotal) obj;

        return obj;
    }
}

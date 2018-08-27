package com.vn.osp.elasticsearch;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by tranv on 3/25/2017.
 */
public class ESUtil {
    private static final Logger LOG = LoggerFactory.getLogger(ESUtil.class);
    public static AddResponse callAddElasic(String actionURL, String data) {
        HttpURLConnection conn =null;
        AddResponse addResponse =null;
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
            if (conn.getResponseCode() != 200 && conn.getResponseCode() != 201) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream()), "UTF-8"));
            String output;
            ObjectMapper mapper = new ObjectMapper();
            addResponse = new AddResponse();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            while ((output = br.readLine()) != null) {
                addResponse = mapper.readValue(output, AddResponse.class);
            }

        } catch (ConnectException ex) {
            LOG.error("Loi ket noi toi Elastic server");
        }  catch (Exception e) {
           LOG.error("Have an error in method ESUtil.callAddElasic:"+e.getMessage());
        }finally {
            conn.disconnect();
        }
        return addResponse;
    }
    public static UpdateResponse callUpdateElasic(String actionURL, String data) {
        HttpURLConnection conn =null;
        UpdateResponse updateResponse =null;
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
            if (conn.getResponseCode() != 200 && conn.getResponseCode() != 201) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream()), "UTF-8"));
            String output = "";
            ObjectMapper mapper = new ObjectMapper();
            updateResponse = new UpdateResponse();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            String temp;
            while ((temp = br.readLine()) != null) {
                output = output + temp;
            }
            updateResponse = mapper.readValue(output, UpdateResponse.class);

        } catch (ConnectException ex) {
            LOG.error("Loi ket noi toi Elastic server");
        }  catch (Exception e) {
            LOG.error("Have an error in method ESUtil.callUpdateElasic:"+e.getMessage());
        }finally {
            conn.disconnect();
        }
        return updateResponse;
    }
    public static CountResponse countTotal(String actionURL, String data) {
        HttpURLConnection conn =null;
        CountResponse updateResponse =null;
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
            if (conn.getResponseCode() != 200 && conn.getResponseCode() != 201) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream()), "UTF-8"));
            String output = "";
            ObjectMapper mapper = new ObjectMapper();
            updateResponse = new CountResponse();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            String temp;
            while ((temp = br.readLine()) != null) {
                output = output + temp;
            }
            updateResponse = mapper.readValue(output, CountResponse.class);


        } catch (ConnectException ex) {
            LOG.error("Loi ket noi toi Elastic server");
        }  catch (Exception e) {
            LOG.error("Have an error in method ESUtil.countTotal:"+e.getMessage());
        }finally {
            conn.disconnect();
        }
        return updateResponse;
    }
    public static DeleteResponse deleteById(String actionURL) {
        HttpURLConnection conn =null;
        DeleteResponse deleteResponse =null;
        try {
            URL url = new URL(actionURL);
            conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(false);
            conn.setRequestMethod("DELETE");
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            if (conn.getResponseCode() != 200 && conn.getResponseCode() != 201) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream()), "UTF-8"));
            String output = "";
            ObjectMapper mapper = new ObjectMapper();
            deleteResponse = new DeleteResponse();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            String temp;
            while ((temp = br.readLine()) != null) {
                output = output + temp;
            }
            deleteResponse = mapper.readValue(output, DeleteResponse.class);


        } catch (ConnectException ex) {
            LOG.error("Loi ket noi toi Elastic server");
        }  catch (Exception e) {
            LOG.error("Have an error in method ESUtil.deleteById:"+e.getMessage());
        }finally {
            conn.disconnect();
        }
        return deleteResponse;
    }
    public static DataPreventInforResponseData queryElasic(String actionURL, String data) {
        HttpURLConnection conn =null;
        DataPreventInforResponseData responseData =null;
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
            if (conn.getResponseCode() != 200 && conn.getResponseCode() != 201) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream()), "UTF-8"));
            String output;
            ObjectMapper mapper = new ObjectMapper();
            responseData = new DataPreventInforResponseData();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            while ((output = br.readLine()) != null) {
                responseData = mapper.readValue(output, DataPreventInforResponseData.class);
            }

        } catch (ConnectException ex) {
            LOG.error("Loi ket noi toi Elastic server");
        } catch (Exception e) {
            LOG.error("Have an error in method ESUtil.queryElasic:"+e.getMessage());
        }finally {
            conn.disconnect();
        }
        return responseData;
    }
    public static TransactionPropertyResponseData queryElasicTP(String actionURL, String data) {
        HttpURLConnection conn =null;
        TransactionPropertyResponseData responseData =null;
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
            if (conn.getResponseCode() != 200 && conn.getResponseCode() != 201) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream()), "UTF-8"));
            String output;
            ObjectMapper mapper = new ObjectMapper();
            responseData = new TransactionPropertyResponseData();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            while ((output = br.readLine()) != null) {
                responseData = mapper.readValue(output, TransactionPropertyResponseData.class);
            }

        } catch (ConnectException ex) {
            LOG.error("Loi ket noi toi Elastic server");
        }  catch (Exception e) {
            LOG.error("Have an error in method ESUtil.queryElasicTP:"+e.getMessage());
        }finally {
            conn.disconnect();
        }
        return responseData;
    }
    public static void mapping(String actionURL, String data) {
        HttpURLConnection conn =null;
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
            if (conn.getResponseCode() != 200 && conn.getResponseCode() != 201) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream()), "UTF-8"));
            String output;
        } catch (ConnectException ex) {
            LOG.error("Loi ket noi toi Elastic server");
        }  catch (Exception e) {
            LOG.error("Have an error in method ESUtil.queryElasicTP:"+e.getMessage());
        }finally {
            conn.disconnect();
        }
    }
}

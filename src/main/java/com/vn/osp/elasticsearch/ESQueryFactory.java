package com.vn.osp.elasticsearch;

import com.vn.osp.common.global.Constants;
import com.vn.osp.common.util.EditString;
import com.vn.osp.context.CommonContext;
import com.vn.osp.modelview.DataPreventInfor;
import com.vn.osp.modelview.TransactionProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tranv on 3/25/2017.
 */
public class ESQueryFactory {
    private static final Logger LOG = LoggerFactory.getLogger(ESQueryFactory.class);

    public static void dataPreventMapping1() {
        try {
            ESUtil.mapping("http://localhost:9200/stp/_mapping/datapreventinfor", "{\n" +
                    "   \"datapreventinfor\": {\n" +
                    "      \"properties\": {\n" +
                    "        \"prevent_doc_receive_date_es\": {\n" +
                    "          \"type\": \"text\",\n" +
                    "          \"fielddata\": true\n" +
                    "        }\n" +
                    "      }\n" +
                    "   }\n" +
                    "}");
        }catch (Exception e){
            LOG.error("dataPreventMapping1");
        }
    }
    public static void dataPreventMapping2() {
        try {
            ESUtil.mapping("http://localhost:9200/stp/_mapping/datapreventinfor", "{\n" +
                    "   \"datapreventinfor\": {\n" +
                    "      \"properties\": {\n" +
                    "        \"update_date_time_es\": {\n" +
                    "          \"type\": \"text\",\n" +
                    "          \"fielddata\": true\n" +
                    "        }\n" +
                    "      }\n" +
                    "   }\n" +
                    "}");
        }catch (Exception e){
            LOG.error("dataPreventMapping2");
        }
    }
    public static void transactionpropertyMapping() {
        try {
            ESUtil.mapping("http://localhost:9200/stp/_mapping/transactionproperty", "{\n" +
                    "   \"transactionproperty\": {\n" +
                    "      \"properties\": {\n" +
                    "        \"notary_date_es\": {\n" +
                    "          \"type\": \"text\",\n" +
                    "          \"fielddata\": true\n" +
                    "        }\n" +
                    "      }\n" +
                    "   }\n" +
                    "}");
        }catch (Exception e){
            LOG.error("transactionpropertyMapping");
        }
    }
    public static Long checkIndexed(Long prevent_id) {
        try {
            CountResponse countResponse = ESUtil.countTotal("http://localhost:9200/stp/datapreventinfor/_count", "{\n" +
                    "    \"query\" : {\n" +
                    "        \"term\" : { \"prevent_id\" : \"" + prevent_id + "\" }\n" +
                    "    }\n" +
                    "}");
            return countResponse.getCount();
        }catch (Exception e){
            LOG.error("Co loi xay ra tai ham checkIndexed");
            return Long.valueOf(0);
        }
    }

    public static DeleteResponse deleteDatapreventById(Long prevent_id) {
        return ESUtil.deleteById("http://localhost:9200/stp/datapreventinfor/" + prevent_id+"?refresh=wait_for");
    }

    public static AddResponse addDataPrevent(DataPreventInfor dataPreventInfor) {
        return ESUtil.callAddElasic("http://localhost:9200/stp/datapreventinfor/" + dataPreventInfor.getPrevent_id()+"?refresh=wait_for", dataPreventInfor.generateJson());
    }

    public static AddResponse addTransactionProperty(TransactionProperty transactionProperty) {
        return ESUtil.callAddElasic("http://localhost:9200/stp/transactionproperty/" + transactionProperty.getTpid(), transactionProperty.generateJson());
    }

    public static UpdateResponse updateDataPrevent(DataPreventInfor dataPreventInfor) {
        UpdateBody updateBody = new UpdateBody();
        updateBody.setDoc(dataPreventInfor);
        return ESUtil.callUpdateElasic("http://localhost:9200/stp/datapreventinfor/" + dataPreventInfor.getPrevent_id() + "/_update?pretty", updateBody.generateJson());
    }

    public static int countTotalByFilter(int status, long user_id, String basicFilter, String type, String property_info, String owner_info) {
        try {
            if(!property_info.equals("") && !owner_info.equals("")) property_info+= " ";
            String filter = basicFilter + property_info + owner_info;
            if (filter.equals("null")) filter = "";
            filter = EditString.removeDauNhayKep(filter);
            //filter = EditString.parseKeyForSearch(filter);

            String json = "";
            if(!filter.equals("")){
                if(type!= null &&!type.equals("00") && !type.equals("")){
                    json = "{\n" +
                            "  \"query\": {\n" +
                            "    \"bool\": {\n" +
                            "      \"must\": [\n" +
                            "        {\n" +
                            "          \"multi_match\" : {\n" +
                            "            \"query\":      \""+filter+"\",\n" +
                            "            \"type\":       \"cross_fields\",\n" +
                            "            \"fields\":     [ \"property_info\", \"owner_info\" , \"prevent_person_info\" , \"prevent_doc_receive_date\" ],\n" +
                            "            \"operator\":   \"and\"\n" +
                            "          }\n" +
                            "        },\n" +
                            "        {\n" +
                            "          \"multi_match\" : {\n" +
                            "            \"query\":      \""+type+"\",\n" +
                            "            \"type\":       \"cross_fields\",\n" +
                            "            \"fields\":     [ \"type\" ]\n" +
                            "          }\n" +
                            "        },\n" +
                            "        {\n" +
                            "          \"multi_match\" : {\n" +
                            "            \"query\":      \"0\",\n" +
                            "            \"type\":       \"cross_fields\",\n" +
                            "            \"fields\":     [ \"delete_flg\" ]\n" +
                            "          }\n" +
                            "        },\n" +
                            "        {\n" +
                            "          \"multi_match\" : {\n" +
                            "            \"query\":      \""+status+"\",\n" +
                            "            \"type\":       \"cross_fields\",\n" +
                            "            \"fields\":     [ \"status\" ]\n" +
                            "          }\n" +
                            "        },\n" +
                            "        {\n" +
                            "          \"multi_match\" : {\n" +
                            "            \"query\":      \""+user_id+"\",\n" +
                            "            \"type\":       \"cross_fields\",\n" +
                            "            \"fields\":     [ \"entry_user_id\",\"leader_id\",\"divison_id\" ]\n" +
                            "          }\n" +
                            "        }\n" +
                            "      ]\n" +
                            "    }\n" +
                            "  }\n" +
                            "}";
                } else{
                    json = "{\n" +
                            "  \"query\": {\n" +
                            "    \"bool\": {\n" +
                            "      \"must\": [\n" +
                            "        {\n" +
                            "          \"multi_match\" : {\n" +
                            "            \"query\":      \""+filter+"\",\n" +
                            "            \"type\":       \"cross_fields\",\n" +
                            "            \"fields\":     [ \"property_info\", \"owner_info\" , \"prevent_person_info\" , \"prevent_doc_receive_date\" ],\n" +
                            "            \"operator\":   \"and\"\n" +
                            "          }\n" +
                            "        },\n" +
                            "        {\n" +
                            "          \"multi_match\" : {\n" +
                            "            \"query\":      \"0\",\n" +
                            "            \"type\":       \"cross_fields\",\n" +
                            "            \"fields\":     [ \"delete_flg\" ]\n" +
                            "          }\n" +
                            "        },\n" +
                            "        {\n" +
                            "          \"multi_match\" : {\n" +
                            "            \"query\":      \""+status+"\",\n" +
                            "            \"type\":       \"cross_fields\",\n" +
                            "            \"fields\":     [ \"status\" ]\n" +
                            "          }\n" +
                            "        },\n" +
                            "        {\n" +
                            "          \"multi_match\" : {\n" +
                            "            \"query\":      \""+user_id+"\",\n" +
                            "            \"type\":       \"cross_fields\",\n" +
                            "            \"fields\":     [ \"entry_user_id\",\"leader_id\",\"divison_id\" ]\n" +
                            "          }\n" +
                            "        }\n" +
                            "      ]\n" +
                            "    }\n" +
                            "  }\n" +
                            "}";
                }
            } else{
                if(type!= null &&!type.equals("00") && !type.equals("")){
                    json = "{\n" +
                            "  \"query\": {\n" +
                            "    \"bool\": {\n" +
                            "      \"must\": [\n" +
                            "        {\n" +
                            "          \"multi_match\" : {\n" +
                            "            \"query\":      \""+type+"\",\n" +
                            "            \"type\":       \"cross_fields\",\n" +
                            "            \"fields\":     [ \"type\" ]\n" +
                            "          }\n" +
                            "        },\n" +
                            "        {\n" +
                            "          \"multi_match\" : {\n" +
                            "            \"query\":      \"0\",\n" +
                            "            \"type\":       \"cross_fields\",\n" +
                            "            \"fields\":     [ \"delete_flg\" ]\n" +
                            "          }\n" +
                            "        },\n" +
                            "        {\n" +
                            "          \"multi_match\" : {\n" +
                            "            \"query\":      \""+status+"\",\n" +
                            "            \"type\":       \"cross_fields\",\n" +
                            "            \"fields\":     [ \"status\" ]\n" +
                            "          }\n" +
                            "        },\n" +
                            "        {\n" +
                            "          \"multi_match\" : {\n" +
                            "            \"query\":      \""+user_id+"\",\n" +
                            "            \"type\":       \"cross_fields\",\n" +
                            "            \"fields\":     [ \"entry_user_id\",\"leader_id\",\"divison_id\" ]\n" +
                            "          }\n" +
                            "        }\n" +
                            "      ]\n" +
                            "    }\n" +
                            "  }\n" +
                            "}";
                } else{
                    json = "{\n" +
                            "  \"query\": {\n" +
                            "    \"bool\": {\n" +
                            "      \"must\": [\n" +
                            "        {\n" +
                            "          \"multi_match\" : {\n" +
                            "            \"query\":      \"0\",\n" +
                            "            \"type\":       \"cross_fields\",\n" +
                            "            \"fields\":     [ \"delete_flg\" ]\n" +
                            "          }\n" +
                            "        },\n" +
                            "        {\n" +
                            "          \"multi_match\" : {\n" +
                            "            \"query\":      \""+status+"\",\n" +
                            "            \"type\":       \"cross_fields\",\n" +
                            "            \"fields\":     [ \"status\" ]\n" +
                            "          }\n" +
                            "        },\n" +
                            "        {\n" +
                            "          \"multi_match\" : {\n" +
                            "            \"query\":      \""+user_id+"\",\n" +
                            "            \"type\":       \"cross_fields\",\n" +
                            "            \"fields\":     [ \"entry_user_id\",\"leader_id\",\"divison_id\" ]\n" +
                            "          }\n" +
                            "        }\n" +
                            "      ]\n" +
                            "    }\n" +
                            "  }\n" +
                            "}";
                }
            }
            return ESUtil.queryElasic("http://localhost:9200/stp/datapreventinfor/_search", json).getHits().getTotal().intValue();
        }catch (Exception e){
            LOG.error("Co loi xay ra tai ham countTotalByFilter");
            return 0;
        }
    }

    // Ham su dung cho tra cuu thong tin ngan chan
    public static List<DataPreventInfor> daTiepNhan(int page, String basicFilter, String type, String property_info, String owner_info,long user_id) {
        try {
            if(!property_info.equals("") && !owner_info.equals("")) property_info+= " ";
            String filter = basicFilter + property_info + owner_info;
            if (filter.equals("null")) filter = "";
            filter = EditString.removeDauNhayKep(filter);
            int offset = Constants.ROW_PER_PAGE * (page - 1);
            //filter = EditString.parseKeyForSearch(filter);

            String json = "";
            if(!filter.equals("")){
                if(type!= null &&!type.equals("00") && !type.equals("")){
                    json = "{\n" +
                            "  \"query\": {\n" +
                            "    \"bool\": {\n" +
                            "      \"must\": [\n" +
                            "        {\n" +
                            "          \"multi_match\" : {\n" +
                            "            \"query\":      \""+filter+"\",\n" +
                            "            \"type\":       \"cross_fields\",\n" +
                            "            \"fields\":     [ \"property_info\", \"owner_info\" , \"prevent_person_info\" , \"prevent_doc_receive_date\" ],\n" +
                            "            \"operator\":   \"and\"\n" +
                            "          }\n" +
                            "        },\n" +
                            "        {\n" +
                            "          \"multi_match\" : {\n" +
                            "            \"query\":      \""+type+"\",\n" +
                            "            \"type\":       \"cross_fields\",\n" +
                            "            \"fields\":     [ \"type\" ]\n" +
                            "          }\n" +
                            "        },\n" +
                            "        {\n" +
                            "          \"multi_match\" : {\n" +
                            "            \"query\":      \"0\",\n" +
                            "            \"type\":       \"cross_fields\",\n" +
                            "            \"fields\":     [ \"delete_flg\" ]\n" +
                            "          }\n" +
                            "        },\n" +
                            "        {\n" +
                            "          \"multi_match\" : {\n" +
                            "            \"query\":      \"1\",\n" +
                            "            \"type\":       \"cross_fields\",\n" +
                            "            \"fields\":     [ \"status\" ]\n" +
                            "          }\n" +
                            "        },\n" +
                            "        {\n" +
                            "          \"multi_match\" : {\n" +
                            "            \"query\":      \""+user_id+"\",\n" +
                            "            \"type\":       \"cross_fields\",\n" +
                            "            \"fields\":     [ \"entry_user_id\",\"leader_id\",\"divison_id\" ]\n" +
                            "          }\n" +
                            "        }\n" +
                            "      ]\n" +
                            "    }\n" +
                            "  }\n" +
                            ",\n" +
                            "  \"from\": "+offset+",\n" +
                            "  \"size\": "+Constants.ROW_PER_PAGE+",\n" +
                            "    \"sort\": [\n" +
                            "        { \"prevent_doc_receive_date_es\":   { \"order\": \"desc\" }},\n" +
                            "        { \"update_date_time_es\": { \"order\": \"desc\" }}\n" +
                            "    ]"+
                            "}";
                } else{
                    json = "{\n" +
                            "  \"query\": {\n" +
                            "    \"bool\": {\n" +
                            "      \"must\": [\n" +
                            "        {\n" +
                            "          \"multi_match\" : {\n" +
                            "            \"query\":      \""+filter+"\",\n" +
                            "            \"type\":       \"cross_fields\",\n" +
                            "            \"fields\":     [ \"property_info\", \"owner_info\" , \"prevent_person_info\" , \"prevent_doc_receive_date\" ],\n" +
                            "            \"operator\":   \"and\"\n" +
                            "          }\n" +
                            "        },\n" +
                            "        {\n" +
                            "          \"multi_match\" : {\n" +
                            "            \"query\":      \"0\",\n" +
                            "            \"type\":       \"cross_fields\",\n" +
                            "            \"fields\":     [ \"delete_flg\" ]\n" +
                            "          }\n" +
                            "        },\n" +
                            "        {\n" +
                            "          \"multi_match\" : {\n" +
                            "            \"query\":      \"1\",\n" +
                            "            \"type\":       \"cross_fields\",\n" +
                            "            \"fields\":     [ \"status\" ]\n" +
                            "          }\n" +
                            "        },\n" +
                            "        {\n" +
                            "          \"multi_match\" : {\n" +
                            "            \"query\":      \""+user_id+"\",\n" +
                            "            \"type\":       \"cross_fields\",\n" +
                            "            \"fields\":     [ \"entry_user_id\",\"leader_id\",\"divison_id\" ]\n" +
                            "          }\n" +
                            "        }\n" +
                            "      ]\n" +
                            "    }\n" +
                            "  }\n" +
                            ",\n" +
                            "  \"from\": "+offset+",\n" +
                            "  \"size\": "+Constants.ROW_PER_PAGE+",\n" +
                            "    \"sort\": [\n" +
                            "        { \"prevent_doc_receive_date_es\":   { \"order\": \"desc\" }},\n" +
                            "        { \"update_date_time_es\": { \"order\": \"desc\" }}\n" +
                            "    ]"+
                            "}";
                }
            } else{
                if(type!= null &&!type.equals("00") && !type.equals("")){
                    json = "{\n" +
                            "  \"query\": {\n" +
                            "    \"bool\": {\n" +
                            "      \"must\": [\n" +
                            "        {\n" +
                            "          \"multi_match\" : {\n" +
                            "            \"query\":      \""+type+"\",\n" +
                            "            \"type\":       \"cross_fields\",\n" +
                            "            \"fields\":     [ \"type\" ]\n" +
                            "          }\n" +
                            "        },\n" +
                            "        {\n" +
                            "          \"multi_match\" : {\n" +
                            "            \"query\":      \"0\",\n" +
                            "            \"type\":       \"cross_fields\",\n" +
                            "            \"fields\":     [ \"delete_flg\" ]\n" +
                            "          }\n" +
                            "        },\n" +
                            "        {\n" +
                            "          \"multi_match\" : {\n" +
                            "            \"query\":      \"1\",\n" +
                            "            \"type\":       \"cross_fields\",\n" +
                            "            \"fields\":     [ \"status\" ]\n" +
                            "          }\n" +
                            "        },\n" +
                            "        {\n" +
                            "          \"multi_match\" : {\n" +
                            "            \"query\":      \""+user_id+"\",\n" +
                            "            \"type\":       \"cross_fields\",\n" +
                            "            \"fields\":     [ \"entry_user_id\",\"leader_id\",\"divison_id\" ]\n" +
                            "          }\n" +
                            "        }\n" +
                            "      ]\n" +
                            "    }\n" +
                            "  }\n" +
                            ",\n" +
                            "  \"from\": "+offset+",\n" +
                            "  \"size\": "+Constants.ROW_PER_PAGE+",\n" +
                            "    \"sort\": [\n" +
                            "        { \"prevent_doc_receive_date_es\":   { \"order\": \"desc\" }},\n" +
                            "        { \"update_date_time_es\": { \"order\": \"desc\" }}\n" +
                            "    ]"+
                            "}";
                } else{
                    json = "{\n" +
                            "  \"query\": {\n" +
                            "    \"bool\": {\n" +
                            "      \"must\": [\n" +
                            "        {\n" +
                            "          \"multi_match\" : {\n" +
                            "            \"query\":      \"0\",\n" +
                            "            \"type\":       \"cross_fields\",\n" +
                            "            \"fields\":     [ \"delete_flg\" ]\n" +
                            "          }\n" +
                            "        },\n" +
                            "        {\n" +
                            "          \"multi_match\" : {\n" +
                            "            \"query\":      \"1\",\n" +
                            "            \"type\":       \"cross_fields\",\n" +
                            "            \"fields\":     [ \"status\" ]\n" +
                            "          }\n" +
                            "        },\n" +
                            "        {\n" +
                            "          \"multi_match\" : {\n" +
                            "            \"query\":      \""+user_id+"\",\n" +
                            "            \"type\":       \"cross_fields\",\n" +
                            "            \"fields\":     [ \"entry_user_id\",\"leader_id\",\"divison_id\" ]\n" +
                            "          }\n" +
                            "        }\n" +
                            "      ]\n" +
                            "    }\n" +
                            "  }\n" +
                            ",\n" +
                            "  \"from\": "+offset+",\n" +
                            "  \"size\": "+Constants.ROW_PER_PAGE+",\n" +
                            "    \"sort\": [\n" +
                            "        { \"prevent_doc_receive_date_es\":   { \"order\": \"desc\" }},\n" +
                            "        { \"update_date_time_es\": { \"order\": \"desc\" }}\n" +
                            "    ]"+
                            "}";
                }
            }

            DataPreventInforResponseData responseData = ESUtil.queryElasic("http://localhost:9200/stp/datapreventinfor/_search", json);
            DatePreventInforHits hits = responseData.getHits();
            return hits.getDataPreventInfors();
        }catch (Exception e){
            LOG.error("Co loi xay ra tai ham daTiepNhan");
            return new ArrayList<DataPreventInfor>();
        }
    }

    public static List<DataPreventInfor> daTrinhLanhDao(int page, String basicFilter, String type, String property_info, String owner_info,long user_id) {
        try {
            if(!property_info.equals("") && !owner_info.equals("")) property_info+= " ";
            String filter = basicFilter + property_info + owner_info ;
            if (filter.equals("null")) filter = "";
            filter = EditString.removeDauNhayKep(filter);
            int offset = Constants.ROW_PER_PAGE * (page - 1);
            //filter = EditString.parseKeyForSearch(filter);

            String json = "";
            if(!filter.equals("")){
                if(type!= null &&!type.equals("00") && !type.equals("")){
                    json = "{\n" +
                            "  \"query\": {\n" +
                            "    \"bool\": {\n" +
                            "      \"must\": [\n" +
                            "        {\n" +
                            "          \"multi_match\" : {\n" +
                            "            \"query\":      \""+filter+"\",\n" +
                            "            \"type\":       \"cross_fields\",\n" +
                            "            \"fields\":     [ \"property_info\", \"owner_info\" , \"prevent_person_info\" , \"prevent_doc_receive_date\" ],\n" +
                            "            \"operator\":   \"and\"\n" +
                            "          }\n" +
                            "        },\n" +
                            "        {\n" +
                            "          \"multi_match\" : {\n" +
                            "            \"query\":      \""+type+"\",\n" +
                            "            \"type\":       \"cross_fields\",\n" +
                            "            \"fields\":     [ \"type\" ]\n" +
                            "          }\n" +
                            "        },\n" +
                            "        {\n" +
                            "          \"multi_match\" : {\n" +
                            "            \"query\":      \"0\",\n" +
                            "            \"type\":       \"cross_fields\",\n" +
                            "            \"fields\":     [ \"delete_flg\" ]\n" +
                            "          }\n" +
                            "        },\n" +
                            "        {\n" +
                            "          \"multi_match\" : {\n" +
                            "            \"query\":      \"2\",\n" +
                            "            \"type\":       \"cross_fields\",\n" +
                            "            \"fields\":     [ \"status\" ]\n" +
                            "          }\n" +
                            "        },\n" +
                            "        {\n" +
                            "          \"multi_match\" : {\n" +
                            "            \"query\":      \""+user_id+"\",\n" +
                            "            \"type\":       \"cross_fields\",\n" +
                            "            \"fields\":     [ \"entry_user_id\",\"leader_id\",\"divison_id\" ]\n" +
                            "          }\n" +
                            "        }\n" +
                            "      ]\n" +
                            "    }\n" +
                            "  }\n" +
                            ",\n" +
                            "  \"from\": "+offset+",\n" +
                            "  \"size\": "+Constants.ROW_PER_PAGE+",\n" +
                            "    \"sort\": [\n" +
                            "        { \"prevent_doc_receive_date_es\":   { \"order\": \"desc\" }},\n" +
                            "        { \"update_date_time_es\": { \"order\": \"desc\" }}\n" +
                            "    ]"+
                            "}";
                } else{
                    json = "{\n" +
                            "  \"query\": {\n" +
                            "    \"bool\": {\n" +
                            "      \"must\": [\n" +
                            "        {\n" +
                            "          \"multi_match\" : {\n" +
                            "            \"query\":      \""+filter+"\",\n" +
                            "            \"type\":       \"cross_fields\",\n" +
                            "            \"fields\":     [ \"property_info\", \"owner_info\" , \"prevent_person_info\" , \"prevent_doc_receive_date\" ],\n" +
                            "            \"operator\":   \"and\"\n" +
                            "          }\n" +
                            "        },\n" +
                            "        {\n" +
                            "          \"multi_match\" : {\n" +
                            "            \"query\":      \"0\",\n" +
                            "            \"type\":       \"cross_fields\",\n" +
                            "            \"fields\":     [ \"delete_flg\" ]\n" +
                            "          }\n" +
                            "        },\n" +
                            "        {\n" +
                            "          \"multi_match\" : {\n" +
                            "            \"query\":      \"2\",\n" +
                            "            \"type\":       \"cross_fields\",\n" +
                            "            \"fields\":     [ \"status\" ]\n" +
                            "          }\n" +
                            "        },\n" +
                            "        {\n" +
                            "          \"multi_match\" : {\n" +
                            "            \"query\":      \""+user_id+"\",\n" +
                            "            \"type\":       \"cross_fields\",\n" +
                            "            \"fields\":     [ \"entry_user_id\",\"leader_id\",\"divison_id\" ]\n" +
                            "          }\n" +
                            "        }\n" +
                            "      ]\n" +
                            "    }\n" +
                            "  }\n" +
                            ",\n" +
                            "  \"from\": "+offset+",\n" +
                            "  \"size\": "+Constants.ROW_PER_PAGE+",\n" +
                            "    \"sort\": [\n" +
                            "        { \"prevent_doc_receive_date_es\":   { \"order\": \"desc\" }},\n" +
                            "        { \"update_date_time_es\": { \"order\": \"desc\" }}\n" +
                            "    ]"+
                            "}";
                }
            } else{
                if(type!= null &&!type.equals("00") && !type.equals("")){
                    json = "{\n" +
                            "  \"query\": {\n" +
                            "    \"bool\": {\n" +
                            "      \"must\": [\n" +
                            "        {\n" +
                            "          \"multi_match\" : {\n" +
                            "            \"query\":      \""+type+"\",\n" +
                            "            \"type\":       \"cross_fields\",\n" +
                            "            \"fields\":     [ \"type\" ]\n" +
                            "          }\n" +
                            "        },\n" +
                            "        {\n" +
                            "          \"multi_match\" : {\n" +
                            "            \"query\":      \"0\",\n" +
                            "            \"type\":       \"cross_fields\",\n" +
                            "            \"fields\":     [ \"delete_flg\" ]\n" +
                            "          }\n" +
                            "        },\n" +
                            "        {\n" +
                            "          \"multi_match\" : {\n" +
                            "            \"query\":      \"2\",\n" +
                            "            \"type\":       \"cross_fields\",\n" +
                            "            \"fields\":     [ \"status\" ]\n" +
                            "          }\n" +
                            "        },\n" +
                            "        {\n" +
                            "          \"multi_match\" : {\n" +
                            "            \"query\":      \""+user_id+"\",\n" +
                            "            \"type\":       \"cross_fields\",\n" +
                            "            \"fields\":     [ \"entry_user_id\",\"leader_id\",\"divison_id\" ]\n" +
                            "          }\n" +
                            "        }\n" +
                            "      ]\n" +
                            "    }\n" +
                            "  }\n" +
                            ",\n" +
                            "  \"from\": "+offset+",\n" +
                            "  \"size\": "+Constants.ROW_PER_PAGE+",\n" +
                            "    \"sort\": [\n" +
                            "        { \"prevent_doc_receive_date_es\":   { \"order\": \"desc\" }},\n" +
                            "        { \"update_date_time_es\": { \"order\": \"desc\" }}\n" +
                            "    ]"+
                            "}";
                } else{
                    json = "{\n" +
                            "  \"query\": {\n" +
                            "    \"bool\": {\n" +
                            "      \"must\": [\n" +
                            "        {\n" +
                            "          \"multi_match\" : {\n" +
                            "            \"query\":      \"0\",\n" +
                            "            \"type\":       \"cross_fields\",\n" +
                            "            \"fields\":     [ \"delete_flg\" ]\n" +
                            "          }\n" +
                            "        },\n" +
                            "        {\n" +
                            "          \"multi_match\" : {\n" +
                            "            \"query\":      \"2\",\n" +
                            "            \"type\":       \"cross_fields\",\n" +
                            "            \"fields\":     [ \"status\" ]\n" +
                            "          }\n" +
                            "        },\n" +
                            "        {\n" +
                            "          \"multi_match\" : {\n" +
                            "            \"query\":      \""+user_id+"\",\n" +
                            "            \"type\":       \"cross_fields\",\n" +
                            "            \"fields\":     [ \"entry_user_id\",\"leader_id\",\"divison_id\" ]\n" +
                            "          }\n" +
                            "        }\n" +
                            "      ]\n" +
                            "    }\n" +
                            "  }\n" +
                            ",\n" +
                            "  \"from\": "+offset+",\n" +
                            "  \"size\": "+Constants.ROW_PER_PAGE+",\n" +
                            "    \"sort\": [\n" +
                            "        { \"prevent_doc_receive_date_es\":   { \"order\": \"desc\" }},\n" +
                            "        { \"update_date_time_es\": { \"order\": \"desc\" }}\n" +
                            "    ]"+
                            "}";
                }
            }
            DataPreventInforResponseData responseData = ESUtil.queryElasic("http://localhost:9200/stp/datapreventinfor/_search", json);
            DatePreventInforHits hits = responseData.getHits();
            return hits.getDataPreventInfors();
        }catch (Exception e){
            LOG.error("Co loi xay ra tai ham daTrinhLanhDao");
            return new ArrayList<DataPreventInfor>();
        }
    }

    public static List<DataPreventInfor> dangXuLy(int page, String basicFilter, String type, String property_info, String owner_info,long user_id) {
        try {
            if(!property_info.equals("") && !owner_info.equals("")) property_info+= " ";
            String filter = basicFilter + property_info + owner_info;
            if (filter.equals("null")) filter = "";
            filter = EditString.removeDauNhayKep(filter);
            int offset = Constants.ROW_PER_PAGE * (page - 1);
            //filter = EditString.parseKeyForSearch(filter);

            String json = "";
            if(!filter.equals("")){
                if(type!= null &&!type.equals("00") && !type.equals("")){
                    json = "{\n" +
                            "  \"query\": {\n" +
                            "    \"bool\": {\n" +
                            "      \"must\": [\n" +
                            "        {\n" +
                            "          \"multi_match\" : {\n" +
                            "            \"query\":      \""+filter+"\",\n" +
                            "            \"type\":       \"cross_fields\",\n" +
                            "            \"fields\":     [ \"property_info\", \"owner_info\" , \"prevent_person_info\" , \"prevent_doc_receive_date\" ],\n" +
                            "            \"operator\":   \"and\"\n" +
                            "          }\n" +
                            "        },\n" +
                            "        {\n" +
                            "          \"multi_match\" : {\n" +
                            "            \"query\":      \""+type+"\",\n" +
                            "            \"type\":       \"cross_fields\",\n" +
                            "            \"fields\":     [ \"type\" ]\n" +
                            "          }\n" +
                            "        },\n" +
                            "        {\n" +
                            "          \"multi_match\" : {\n" +
                            "            \"query\":      \"0\",\n" +
                            "            \"type\":       \"cross_fields\",\n" +
                            "            \"fields\":     [ \"delete_flg\" ]\n" +
                            "          }\n" +
                            "        },\n" +
                            "        {\n" +
                            "          \"multi_match\" : {\n" +
                            "            \"query\":      \"3\",\n" +
                            "            \"type\":       \"cross_fields\",\n" +
                            "            \"fields\":     [ \"status\" ]\n" +
                            "          }\n" +
                            "        },\n" +
                            "        {\n" +
                            "          \"multi_match\" : {\n" +
                            "            \"query\":      \""+user_id+"\",\n" +
                            "            \"type\":       \"cross_fields\",\n" +
                            "            \"fields\":     [ \"entry_user_id\",\"leader_id\",\"divison_id\" ]\n" +
                            "          }\n" +
                            "        }\n" +
                            "      ]\n" +
                            "    }\n" +
                            "  }\n" +
                            ",\n" +
                            "  \"from\": "+offset+",\n" +
                            "  \"size\": "+Constants.ROW_PER_PAGE+",\n" +
                            "    \"sort\": [\n" +
                            "        { \"prevent_doc_receive_date_es\":   { \"order\": \"desc\" }},\n" +
                            "        { \"update_date_time_es\": { \"order\": \"desc\" }}\n" +
                            "    ]"+
                            "}";
                } else{
                    json = "{\n" +
                            "  \"query\": {\n" +
                            "    \"bool\": {\n" +
                            "      \"must\": [\n" +
                            "        {\n" +
                            "          \"multi_match\" : {\n" +
                            "            \"query\":      \""+filter+"\",\n" +
                            "            \"type\":       \"cross_fields\",\n" +
                            "            \"fields\":     [ \"property_info\", \"owner_info\" , \"prevent_person_info\" , \"prevent_doc_receive_date\" ],\n" +
                            "            \"operator\":   \"and\"\n" +
                            "          }\n" +
                            "        },\n" +
                            "        {\n" +
                            "          \"multi_match\" : {\n" +
                            "            \"query\":      \"0\",\n" +
                            "            \"type\":       \"cross_fields\",\n" +
                            "            \"fields\":     [ \"delete_flg\" ]\n" +
                            "          }\n" +
                            "        },\n" +
                            "        {\n" +
                            "          \"multi_match\" : {\n" +
                            "            \"query\":      \"3\",\n" +
                            "            \"type\":       \"cross_fields\",\n" +
                            "            \"fields\":     [ \"status\" ]\n" +
                            "          }\n" +
                            "        },\n" +
                            "        {\n" +
                            "          \"multi_match\" : {\n" +
                            "            \"query\":      \""+user_id+"\",\n" +
                            "            \"type\":       \"cross_fields\",\n" +
                            "            \"fields\":     [ \"entry_user_id\",\"leader_id\",\"divison_id\" ]\n" +
                            "          }\n" +
                            "        }\n" +
                            "      ]\n" +
                            "    }\n" +
                            "  }\n" +
                            ",\n" +
                            "  \"from\": "+offset+",\n" +
                            "  \"size\": "+Constants.ROW_PER_PAGE+",\n" +
                            "    \"sort\": [\n" +
                            "        { \"prevent_doc_receive_date_es\":   { \"order\": \"desc\" }},\n" +
                            "        { \"update_date_time_es\": { \"order\": \"desc\" }}\n" +
                            "    ]"+
                            "}";
                }
            } else{
                if(type!= null &&!type.equals("00") && !type.equals("")){
                    json = "{\n" +
                            "  \"query\": {\n" +
                            "    \"bool\": {\n" +
                            "      \"must\": [\n" +
                            "        {\n" +
                            "          \"multi_match\" : {\n" +
                            "            \"query\":      \""+type+"\",\n" +
                            "            \"type\":       \"cross_fields\",\n" +
                            "            \"fields\":     [ \"type\" ]\n" +
                            "          }\n" +
                            "        },\n" +
                            "        {\n" +
                            "          \"multi_match\" : {\n" +
                            "            \"query\":      \"0\",\n" +
                            "            \"type\":       \"cross_fields\",\n" +
                            "            \"fields\":     [ \"delete_flg\" ]\n" +
                            "          }\n" +
                            "        },\n" +
                            "        {\n" +
                            "          \"multi_match\" : {\n" +
                            "            \"query\":      \"3\",\n" +
                            "            \"type\":       \"cross_fields\",\n" +
                            "            \"fields\":     [ \"status\" ]\n" +
                            "          }\n" +
                            "        },\n" +
                            "        {\n" +
                            "          \"multi_match\" : {\n" +
                            "            \"query\":      \""+user_id+"\",\n" +
                            "            \"type\":       \"cross_fields\",\n" +
                            "            \"fields\":     [ \"entry_user_id\",\"leader_id\",\"divison_id\" ]\n" +
                            "          }\n" +
                            "        }\n" +
                            "      ]\n" +
                            "    }\n" +
                            "  }\n" +
                            ",\n" +
                            "  \"from\": "+offset+",\n" +
                            "  \"size\": "+Constants.ROW_PER_PAGE+",\n" +
                            "    \"sort\": [\n" +
                            "        { \"prevent_doc_receive_date_es\":   { \"order\": \"desc\" }},\n" +
                            "        { \"update_date_time_es\": { \"order\": \"desc\" }}\n" +
                            "    ]"+
                            "}";
                } else{
                    json = "{\n" +
                            "  \"query\": {\n" +
                            "    \"bool\": {\n" +
                            "      \"must\": [\n" +
                            "        {\n" +
                            "          \"multi_match\" : {\n" +
                            "            \"query\":      \"0\",\n" +
                            "            \"type\":       \"cross_fields\",\n" +
                            "            \"fields\":     [ \"delete_flg\" ]\n" +
                            "          }\n" +
                            "        },\n" +
                            "        {\n" +
                            "          \"multi_match\" : {\n" +
                            "            \"query\":      \"3\",\n" +
                            "            \"type\":       \"cross_fields\",\n" +
                            "            \"fields\":     [ \"status\" ]\n" +
                            "          }\n" +
                            "        },\n" +
                            "        {\n" +
                            "          \"multi_match\" : {\n" +
                            "            \"query\":      \""+user_id+"\",\n" +
                            "            \"type\":       \"cross_fields\",\n" +
                            "            \"fields\":     [ \"entry_user_id\",\"leader_id\",\"divison_id\" ]\n" +
                            "          }\n" +
                            "        }\n" +
                            "      ]\n" +
                            "    }\n" +
                            "  }\n" +
                            ",\n" +
                            "  \"from\": "+offset+",\n" +
                            "  \"size\": "+Constants.ROW_PER_PAGE+",\n" +
                            "    \"sort\": [\n" +
                            "        { \"prevent_doc_receive_date_es\":   { \"order\": \"desc\" }},\n" +
                            "        { \"update_date_time_es\": { \"order\": \"desc\" }}\n" +
                            "    ]"+
                            "}";
                }
            }
            DataPreventInforResponseData responseData = ESUtil.queryElasic("http://localhost:9200/stp/datapreventinfor/_search", json);
            DatePreventInforHits hits = responseData.getHits();
            return hits.getDataPreventInfors();
        }catch (Exception e){
            LOG.error("Co loi xay ra tai ham dangXuLy");
            return new ArrayList<DataPreventInfor>();
        }
    }

    public static List<DataPreventInfor> daDuyet(int page, String basicFilter, String type, String property_info, String owner_info,long user_id) {
        try {
            if(!property_info.equals("") && !owner_info.equals("")) property_info+= " ";
            String filter = basicFilter + property_info + owner_info;
            if (filter.equals("null")) filter = "";
            filter = EditString.removeDauNhayKep(filter);
            int offset = Constants.ROW_PER_PAGE * (page - 1);
            //filter = EditString.parseKeyForSearch(filter);

            String json = "";
            if(!filter.equals("")){
                if(type!= null &&!type.equals("00") && !type.equals("")){
                    json = "{\n" +
                            "  \"query\": {\n" +
                            "    \"bool\": {\n" +
                            "      \"must\": [\n" +
                            "        {\n" +
                            "          \"multi_match\" : {\n" +
                            "            \"query\":      \""+filter+"\",\n" +
                            "            \"type\":       \"cross_fields\",\n" +
                            "            \"fields\":     [ \"property_info\", \"owner_info\" , \"prevent_person_info\" , \"prevent_doc_receive_date\" ],\n" +
                            "            \"operator\":   \"and\"\n" +
                            "          }\n" +
                            "        },\n" +
                            "        {\n" +
                            "          \"multi_match\" : {\n" +
                            "            \"query\":      \""+type+"\",\n" +
                            "            \"type\":       \"cross_fields\",\n" +
                            "            \"fields\":     [ \"type\" ]\n" +
                            "          }\n" +
                            "        },\n" +
                            "        {\n" +
                            "          \"multi_match\" : {\n" +
                            "            \"query\":      \"0\",\n" +
                            "            \"type\":       \"cross_fields\",\n" +
                            "            \"fields\":     [ \"delete_flg\" ]\n" +
                            "          }\n" +
                            "        },\n" +
                            "        {\n" +
                            "          \"multi_match\" : {\n" +
                            "            \"query\":      \"4\",\n" +
                            "            \"type\":       \"cross_fields\",\n" +
                            "            \"fields\":     [ \"status\" ]\n" +
                            "          }\n" +
                            "        },\n" +
                            "        {\n" +
                            "          \"multi_match\" : {\n" +
                            "            \"query\":      \""+user_id+"\",\n" +
                            "            \"type\":       \"cross_fields\",\n" +
                            "            \"fields\":     [ \"entry_user_id\",\"leader_id\",\"divison_id\" ]\n" +
                            "          }\n" +
                            "        }\n" +
                            "      ]\n" +
                            "    }\n" +
                            "  }\n" +
                            ",\n" +
                            "  \"from\": "+offset+",\n" +
                            "  \"size\": "+Constants.ROW_PER_PAGE+",\n" +
                            "    \"sort\": [\n" +
                            "        { \"prevent_doc_receive_date_es\":   { \"order\": \"desc\" }},\n" +
                            "        { \"update_date_time_es\": { \"order\": \"desc\" }}\n" +
                            "    ]"+
                            "}";
                } else{
                    json = "{\n" +
                            "  \"query\": {\n" +
                            "    \"bool\": {\n" +
                            "      \"must\": [\n" +
                            "        {\n" +
                            "          \"multi_match\" : {\n" +
                            "            \"query\":      \""+filter+"\",\n" +
                            "            \"type\":       \"cross_fields\",\n" +
                            "            \"fields\":     [ \"property_info\", \"owner_info\" , \"prevent_person_info\" , \"prevent_doc_receive_date\" ],\n" +
                            "            \"operator\":   \"and\"\n" +
                            "          }\n" +
                            "        },\n" +
                            "        {\n" +
                            "          \"multi_match\" : {\n" +
                            "            \"query\":      \"0\",\n" +
                            "            \"type\":       \"cross_fields\",\n" +
                            "            \"fields\":     [ \"delete_flg\" ]\n" +
                            "          }\n" +
                            "        },\n" +
                            "        {\n" +
                            "          \"multi_match\" : {\n" +
                            "            \"query\":      \"4\",\n" +
                            "            \"type\":       \"cross_fields\",\n" +
                            "            \"fields\":     [ \"status\" ]\n" +
                            "          }\n" +
                            "        },\n" +
                            "        {\n" +
                            "          \"multi_match\" : {\n" +
                            "            \"query\":      \""+user_id+"\",\n" +
                            "            \"type\":       \"cross_fields\",\n" +
                            "            \"fields\":     [ \"entry_user_id\",\"leader_id\",\"divison_id\" ]\n" +
                            "          }\n" +
                            "        }\n" +
                            "      ]\n" +
                            "    }\n" +
                            "  }\n" +
                            ",\n" +
                            "  \"from\": "+offset+",\n" +
                            "  \"size\": "+Constants.ROW_PER_PAGE+",\n" +
                            "    \"sort\": [\n" +
                            "        { \"prevent_doc_receive_date_es\":   { \"order\": \"desc\" }},\n" +
                            "        { \"update_date_time_es\": { \"order\": \"desc\" }}\n" +
                            "    ]"+
                            "}";
                }
            } else{
                if(type!= null &&!type.equals("00") && !type.equals("")){
                    json = "{\n" +
                            "  \"query\": {\n" +
                            "    \"bool\": {\n" +
                            "      \"must\": [\n" +
                            "        {\n" +
                            "          \"multi_match\" : {\n" +
                            "            \"query\":      \""+type+"\",\n" +
                            "            \"type\":       \"cross_fields\",\n" +
                            "            \"fields\":     [ \"type\" ]\n" +
                            "          }\n" +
                            "        },\n" +
                            "        {\n" +
                            "          \"multi_match\" : {\n" +
                            "            \"query\":      \"0\",\n" +
                            "            \"type\":       \"cross_fields\",\n" +
                            "            \"fields\":     [ \"delete_flg\" ]\n" +
                            "          }\n" +
                            "        },\n" +
                            "        {\n" +
                            "          \"multi_match\" : {\n" +
                            "            \"query\":      \"4\",\n" +
                            "            \"type\":       \"cross_fields\",\n" +
                            "            \"fields\":     [ \"status\" ]\n" +
                            "          }\n" +
                            "        },\n" +
                            "        {\n" +
                            "          \"multi_match\" : {\n" +
                            "            \"query\":      \""+user_id+"\",\n" +
                            "            \"type\":       \"cross_fields\",\n" +
                            "            \"fields\":     [ \"entry_user_id\",\"leader_id\",\"divison_id\" ]\n" +
                            "          }\n" +
                            "        }\n" +
                            "      ]\n" +
                            "    }\n" +
                            "  }\n" +
                            ",\n" +
                            "  \"from\": "+offset+",\n" +
                            "  \"size\": "+Constants.ROW_PER_PAGE+",\n" +
                            "    \"sort\": [\n" +
                            "        { \"prevent_doc_receive_date_es\":   { \"order\": \"desc\" }},\n" +
                            "        { \"update_date_time_es\": { \"order\": \"desc\" }}\n" +
                            "    ]"+
                            "}";
                } else{
                    json = "{\n" +
                            "  \"query\": {\n" +
                            "    \"bool\": {\n" +
                            "      \"must\": [\n" +
                            "        {\n" +
                            "          \"multi_match\" : {\n" +
                            "            \"query\":      \"0\",\n" +
                            "            \"type\":       \"cross_fields\",\n" +
                            "            \"fields\":     [ \"delete_flg\" ]\n" +
                            "          }\n" +
                            "        },\n" +
                            "        {\n" +
                            "          \"multi_match\" : {\n" +
                            "            \"query\":      \"4\",\n" +
                            "            \"type\":       \"cross_fields\",\n" +
                            "            \"fields\":     [ \"status\" ]\n" +
                            "          }\n" +
                            "        },\n" +
                            "        {\n" +
                            "          \"multi_match\" : {\n" +
                            "            \"query\":      \""+user_id+"\",\n" +
                            "            \"type\":       \"cross_fields\",\n" +
                            "            \"fields\":     [ \"entry_user_id\",\"leader_id\",\"divison_id\" ]\n" +
                            "          }\n" +
                            "        }\n" +
                            "      ]\n" +
                            "    }\n" +
                            "  }\n" +
                            ",\n" +
                            "  \"from\": "+offset+",\n" +
                            "  \"size\": "+Constants.ROW_PER_PAGE+",\n" +
                            "    \"sort\": [\n" +
                            "        { \"prevent_doc_receive_date_es\":   { \"order\": \"desc\" }},\n" +
                            "        { \"update_date_time_es\": { \"order\": \"desc\" }}\n" +
                            "    ]"+
                            "}";
                }
            }
            DataPreventInforResponseData responseData = ESUtil.queryElasic("http://localhost:9200/stp/datapreventinfor/_search", json);
            DatePreventInforHits hits = responseData.getHits();
            return hits.getDataPreventInfors();
        }catch (Exception e){
            LOG.error("Co loi xay ra tai ham daDuyet");
            return new ArrayList<DataPreventInfor>();
        }
    }

    public static List<DataPreventInfor> khongDuyet(int page, String basicFilter, String type, String property_info, String owner_info,long user_id) {
        try {
            if(!property_info.equals("") && !owner_info.equals("")) property_info+= " ";
            String filter = basicFilter + property_info + owner_info;
            if (filter.equals("null")) filter = "";
            filter = EditString.removeDauNhayKep(filter);
            int offset = Constants.ROW_PER_PAGE * (page - 1);

            String json = "";
            if(!filter.equals("")){
                if(type!= null &&!type.equals("00") && !type.equals("")){
                    json = "{\n" +
                            "  \"query\": {\n" +
                            "    \"bool\": {\n" +
                            "      \"must\": [\n" +
                            "        {\n" +
                            "          \"multi_match\" : {\n" +
                            "            \"query\":      \""+filter+"\",\n" +
                            "            \"type\":       \"cross_fields\",\n" +
                            "            \"fields\":     [ \"property_info\", \"owner_info\" , \"prevent_person_info\" , \"prevent_doc_receive_date\" ],\n" +
                            "            \"operator\":   \"and\"\n" +
                            "          }\n" +
                            "        },\n" +
                            "        {\n" +
                            "          \"multi_match\" : {\n" +
                            "            \"query\":      \""+type+"\",\n" +
                            "            \"type\":       \"cross_fields\",\n" +
                            "            \"fields\":     [ \"type\" ]\n" +
                            "          }\n" +
                            "        },\n" +
                            "        {\n" +
                            "          \"multi_match\" : {\n" +
                            "            \"query\":      \"0\",\n" +
                            "            \"type\":       \"cross_fields\",\n" +
                            "            \"fields\":     [ \"delete_flg\" ]\n" +
                            "          }\n" +
                            "        },\n" +
                            "        {\n" +
                            "          \"multi_match\" : {\n" +
                            "            \"query\":      \"5\",\n" +
                            "            \"type\":       \"cross_fields\",\n" +
                            "            \"fields\":     [ \"status\" ]\n" +
                            "          }\n" +
                            "        },\n" +
                            "        {\n" +
                            "          \"multi_match\" : {\n" +
                            "            \"query\":      \""+user_id+"\",\n" +
                            "            \"type\":       \"cross_fields\",\n" +
                            "            \"fields\":     [ \"entry_user_id\",\"leader_id\",\"divison_id\" ]\n" +
                            "          }\n" +
                            "        }\n" +
                            "      ]\n" +
                            "    }\n" +
                            "  }\n" +
                            ",\n" +
                            "  \"from\": "+offset+",\n" +
                            "  \"size\": "+Constants.ROW_PER_PAGE+",\n" +
                            "    \"sort\": [\n" +
                            "        { \"prevent_doc_receive_date_es\":   { \"order\": \"desc\" }},\n" +
                            "        { \"update_date_time_es\": { \"order\": \"desc\" }}\n" +
                            "    ]"+
                            "}";
                } else{
                    json = "{\n" +
                            "  \"query\": {\n" +
                            "    \"bool\": {\n" +
                            "      \"must\": [\n" +
                            "        {\n" +
                            "          \"multi_match\" : {\n" +
                            "            \"query\":      \""+filter+"\",\n" +
                            "            \"type\":       \"cross_fields\",\n" +
                            "            \"fields\":     [ \"property_info\", \"owner_info\" , \"prevent_person_info\" , \"prevent_doc_receive_date\" ],\n" +
                            "            \"operator\":   \"and\"\n" +
                            "          }\n" +
                            "        },\n" +
                            "        {\n" +
                            "          \"multi_match\" : {\n" +
                            "            \"query\":      \"0\",\n" +
                            "            \"type\":       \"cross_fields\",\n" +
                            "            \"fields\":     [ \"delete_flg\" ]\n" +
                            "          }\n" +
                            "        },\n" +
                            "        {\n" +
                            "          \"multi_match\" : {\n" +
                            "            \"query\":      \"5\",\n" +
                            "            \"type\":       \"cross_fields\",\n" +
                            "            \"fields\":     [ \"status\" ]\n" +
                            "          }\n" +
                            "        },\n" +
                            "        {\n" +
                            "          \"multi_match\" : {\n" +
                            "            \"query\":      \""+user_id+"\",\n" +
                            "            \"type\":       \"cross_fields\",\n" +
                            "            \"fields\":     [ \"entry_user_id\",\"leader_id\",\"divison_id\" ]\n" +
                            "          }\n" +
                            "        }\n" +
                            "      ]\n" +
                            "    }\n" +
                            "  }\n" +
                            ",\n" +
                            "  \"from\": "+offset+",\n" +
                            "  \"size\": "+Constants.ROW_PER_PAGE+",\n" +
                            "    \"sort\": [\n" +
                            "        { \"prevent_doc_receive_date_es\":   { \"order\": \"desc\" }},\n" +
                            "        { \"update_date_time_es\": { \"order\": \"desc\" }}\n" +
                            "    ]"+
                            "}";
                }
            } else{
                if(type!= null &&!type.equals("00") && !type.equals("")){
                    json = "{\n" +
                            "  \"query\": {\n" +
                            "    \"bool\": {\n" +
                            "      \"must\": [\n" +
                            "        {\n" +
                            "          \"multi_match\" : {\n" +
                            "            \"query\":      \""+type+"\",\n" +
                            "            \"type\":       \"cross_fields\",\n" +
                            "            \"fields\":     [ \"type\" ]\n" +
                            "          }\n" +
                            "        },\n" +
                            "        {\n" +
                            "          \"multi_match\" : {\n" +
                            "            \"query\":      \"0\",\n" +
                            "            \"type\":       \"cross_fields\",\n" +
                            "            \"fields\":     [ \"delete_flg\" ]\n" +
                            "          }\n" +
                            "        },\n" +
                            "        {\n" +
                            "          \"multi_match\" : {\n" +
                            "            \"query\":      \"5\",\n" +
                            "            \"type\":       \"cross_fields\",\n" +
                            "            \"fields\":     [ \"status\" ]\n" +
                            "          }\n" +
                            "        },\n" +
                            "        {\n" +
                            "          \"multi_match\" : {\n" +
                            "            \"query\":      \""+user_id+"\",\n" +
                            "            \"type\":       \"cross_fields\",\n" +
                            "            \"fields\":     [ \"entry_user_id\",\"leader_id\",\"divison_id\" ]\n" +
                            "          }\n" +
                            "        }\n" +
                            "      ]\n" +
                            "    }\n" +
                            "  }\n" +
                            ",\n" +
                            "  \"from\": "+offset+",\n" +
                            "  \"size\": "+Constants.ROW_PER_PAGE+",\n" +
                            "    \"sort\": [\n" +
                            "        { \"prevent_doc_receive_date_es\":   { \"order\": \"desc\" }},\n" +
                            "        { \"update_date_time_es\": { \"order\": \"desc\" }}\n" +
                            "    ]"+
                            "}";
                } else{
                    json = "{\n" +
                            "  \"query\": {\n" +
                            "    \"bool\": {\n" +
                            "      \"must\": [\n" +
                            "        {\n" +
                            "          \"multi_match\" : {\n" +
                            "            \"query\":      \"0\",\n" +
                            "            \"type\":       \"cross_fields\",\n" +
                            "            \"fields\":     [ \"delete_flg\" ]\n" +
                            "          }\n" +
                            "        },\n" +
                            "        {\n" +
                            "          \"multi_match\" : {\n" +
                            "            \"query\":      \"5\",\n" +
                            "            \"type\":       \"cross_fields\",\n" +
                            "            \"fields\":     [ \"status\" ]\n" +
                            "          }\n" +
                            "        },\n" +
                            "        {\n" +
                            "          \"multi_match\" : {\n" +
                            "            \"query\":      \""+user_id+"\",\n" +
                            "            \"type\":       \"cross_fields\",\n" +
                            "            \"fields\":     [ \"entry_user_id\",\"leader_id\",\"divison_id\" ]\n" +
                            "          }\n" +
                            "        }\n" +
                            "      ]\n" +
                            "    }\n" +
                            "  }\n" +
                            ",\n" +
                            "  \"from\": "+offset+",\n" +
                            "  \"size\": "+Constants.ROW_PER_PAGE+",\n" +
                            "    \"sort\": [\n" +
                            "        { \"prevent_doc_receive_date_es\":   { \"order\": \"desc\" }},\n" +
                            "        { \"update_date_time_es\": { \"order\": \"desc\" }}\n" +
                            "    ]"+
                            "}";
                }
            }
            DataPreventInforResponseData responseData = ESUtil.queryElasic("http://localhost:9200/stp/datapreventinfor/_search", json);
            DatePreventInforHits hits = responseData.getHits();
            return hits.getDataPreventInfors();
        }catch (Exception e){
            LOG.error("Co loi xay ra tai ham khongDuyet");
            return new ArrayList<DataPreventInfor>();
        }
    }

    //Ham su dung cho trang tra cuu thong tin khong phu thuoc vao user
    public static List<DataPreventInfor> daDuyetAll(int page, String basicFilter, String type, String property_info, String owner_info) {
        try {
            if(!property_info.equals("") && !owner_info.equals("")) property_info+= " ";
            String filter = basicFilter + property_info + owner_info;
            if (filter.equals("null")) filter = "";
            filter = EditString.removeDauNhayKep(filter);
            int offset = Constants.ROW_PER_PAGE * (page - 1);
            //filter = EditString.parseKeyForSearch(filter);

            String json = "";
            if(!filter.equals("")){
                if(type!= null &&!type.equals("00") && !type.equals("")){
                    json = "{\n" +
                            "  \"query\": {\n" +
                            "    \"bool\": {\n" +
                            "      \"must\": [\n" +
                            "        {\n" +
                            "          \"multi_match\" : {\n" +
                            "            \"query\":      \""+filter+"\",\n" +
                            "            \"type\":       \"cross_fields\",\n" +
                            "            \"fields\":     [ \"property_info\", \"owner_info\" , \"prevent_person_info\" , \"prevent_doc_receive_date\" ],\n" +
                            "            \"operator\":   \"and\"\n" +
                            "          }\n" +
                            "        },\n" +
                            "        {\n" +
                            "          \"multi_match\" : {\n" +
                            "            \"query\":      \""+type+"\",\n" +
                            "            \"type\":       \"cross_fields\",\n" +
                            "            \"fields\":     [ \"type\" ]\n" +
                            "          }\n" +
                            "        },\n" +
                            "        {\n" +
                            "          \"multi_match\" : {\n" +
                            "            \"query\":      \"0\",\n" +
                            "            \"type\":       \"cross_fields\",\n" +
                            "            \"fields\":     [ \"delete_flg\" ]\n" +
                            "          }\n" +
                            "        },\n" +
                            "        {\n" +
                            "          \"multi_match\" : {\n" +
                            "            \"query\":      \"4\",\n" +
                            "            \"type\":       \"cross_fields\",\n" +
                            "            \"fields\":     [ \"status\" ]\n" +
                            "          }\n" +
                            "        }\n" +

                            "      ]\n" +
                            "    }\n" +
                            "  }\n" +
                            ",\n" +
                            "  \"from\": "+offset+",\n" +
                            "  \"size\": "+Constants.ROW_PER_PAGE+",\n" +
                            "    \"sort\": [\n" +
                            "        { \"prevent_doc_receive_date_es\":   { \"order\": \"desc\" }},\n" +
                            "        { \"update_date_time_es\": { \"order\": \"desc\" }}\n" +
                            "    ]"+
                            "}";
                } else{
                    json = "{\n" +
                            "  \"query\": {\n" +
                            "    \"bool\": {\n" +
                            "      \"must\": [\n" +
                            "        {\n" +
                            "          \"multi_match\" : {\n" +
                            "            \"query\":      \""+filter+"\",\n" +
                            "            \"type\":       \"cross_fields\",\n" +
                            "            \"fields\":     [ \"property_info\", \"owner_info\" , \"prevent_person_info\" , \"prevent_doc_receive_date\" ],\n" +
                            "            \"operator\":   \"and\"\n" +
                            "          }\n" +
                            "        },\n" +
                            "        {\n" +
                            "          \"multi_match\" : {\n" +
                            "            \"query\":      \"0\",\n" +
                            "            \"type\":       \"cross_fields\",\n" +
                            "            \"fields\":     [ \"delete_flg\" ]\n" +
                            "          }\n" +
                            "        },\n" +
                            "        {\n" +
                            "          \"multi_match\" : {\n" +
                            "            \"query\":      \"4\",\n" +
                            "            \"type\":       \"cross_fields\",\n" +
                            "            \"fields\":     [ \"status\" ]\n" +
                            "          }\n" +
                            "        }\n" +
                            "      ]\n" +
                            "    }\n" +
                            "  }\n" +
                            ",\n" +
                            "  \"from\": "+offset+",\n" +
                            "  \"size\": "+Constants.ROW_PER_PAGE+",\n" +
                            "    \"sort\": [\n" +
                            "        { \"prevent_doc_receive_date_es\":   { \"order\": \"desc\" }},\n" +
                            "        { \"update_date_time_es\": { \"order\": \"desc\" }}\n" +
                            "    ]"+
                            "}";
                }
            } else{
                if(type!= null &&!type.equals("00") && !type.equals("")){
                    json = "{\n" +
                            "  \"query\": {\n" +
                            "    \"bool\": {\n" +
                            "      \"must\": [\n" +
                            "        {\n" +
                            "          \"multi_match\" : {\n" +
                            "            \"query\":      \""+type+"\",\n" +
                            "            \"type\":       \"cross_fields\",\n" +
                            "            \"fields\":     [ \"type\" ]\n" +
                            "          }\n" +
                            "        },\n" +
                            "        {\n" +
                            "          \"multi_match\" : {\n" +
                            "            \"query\":      \"0\",\n" +
                            "            \"type\":       \"cross_fields\",\n" +
                            "            \"fields\":     [ \"delete_flg\" ]\n" +
                            "          }\n" +
                            "        },\n" +
                            "        {\n" +
                            "          \"multi_match\" : {\n" +
                            "            \"query\":      \"4\",\n" +
                            "            \"type\":       \"cross_fields\",\n" +
                            "            \"fields\":     [ \"status\" ]\n" +
                            "          }\n" +
                            "        }\n" +

                            "      ]\n" +
                            "    }\n" +
                            "  }\n" +
                            ",\n" +
                            "  \"from\": "+offset+",\n" +
                            "  \"size\": "+Constants.ROW_PER_PAGE+",\n" +
                            "    \"sort\": [\n" +
                            "        { \"prevent_doc_receive_date_es\":   { \"order\": \"desc\" }},\n" +
                            "        { \"update_date_time_es\": { \"order\": \"desc\" }}\n" +
                            "    ]"+
                            "}";
                } else{
                    json = "{\n" +
                            "  \"query\": {\n" +
                            "    \"bool\": {\n" +
                            "      \"must\": [\n" +
                            "        {\n" +
                            "          \"multi_match\" : {\n" +
                            "            \"query\":      \"0\",\n" +
                            "            \"type\":       \"cross_fields\",\n" +
                            "            \"fields\":     [ \"delete_flg\" ]\n" +
                            "          }\n" +
                            "        },\n" +
                            "        {\n" +
                            "          \"multi_match\" : {\n" +
                            "            \"query\":      \"4\",\n" +
                            "            \"type\":       \"cross_fields\",\n" +
                            "            \"fields\":     [ \"status\" ]\n" +
                            "          }\n" +
                            "        }\n" +

                            "      ]\n" +
                            "    }\n" +
                            "  }\n" +
                            ",\n" +
                            "  \"from\": "+offset+",\n" +
                            "  \"size\": "+Constants.ROW_PER_PAGE+",\n" +
                            "    \"sort\": [\n" +
                            "        { \"prevent_doc_receive_date_es\":   { \"order\": \"desc\" }},\n" +
                            "        { \"update_date_time_es\": { \"order\": \"desc\" }}\n" +
                            "    ]"+
                            "}";
                }
            }

            DataPreventInforResponseData responseData = ESUtil.queryElasic("http://localhost:9200/stp/datapreventinfor/_search", json);
            DatePreventInforHits hits = responseData.getHits();
            return hits.getDataPreventInfors();
        }catch (Exception e){
            LOG.error("Co loi xay ra tai ham daDuyetAll");
            return new ArrayList<DataPreventInfor>();
        }
    }

    //Ham dem so luong ttnc da duyet khong phu thuoc vao user
    public static Long countDaDuyetAll(String basicFilter, String type, String property_info, String owner_info) {
        try {
            if(!property_info.equals("") && !owner_info.equals("")) property_info+= " ";
            String filter = basicFilter + property_info + owner_info;
            if (filter.equals("null")) filter = "";
            filter = EditString.removeDauNhayKep(filter);
            //filter = EditString.parseKeyForSearch(filter);

            String json = "";
            if(!filter.equals("")){
                if(type!= null &&!type.equals("00") && !type.equals("")){
                    json = "{\n" +
                            "  \"query\": {\n" +
                            "    \"bool\": {\n" +
                            "      \"must\": [\n" +
                            "        {\n" +
                            "          \"multi_match\" : {\n" +
                            "            \"query\":      \""+filter+"\",\n" +
                            "            \"type\":       \"cross_fields\",\n" +
                            "            \"fields\":     [ \"property_info\", \"owner_info\" , \"prevent_person_info\" , \"prevent_doc_receive_date\" ],\n" +
                            "            \"operator\":   \"and\"\n" +
                            "          }\n" +
                            "        },\n" +
                            "        {\n" +
                            "          \"multi_match\" : {\n" +
                            "            \"query\":      \""+type+"\",\n" +
                            "            \"type\":       \"cross_fields\",\n" +
                            "            \"fields\":     [ \"type\" ]\n" +
                            "          }\n" +
                            "        },\n" +
                            "        {\n" +
                            "          \"multi_match\" : {\n" +
                            "            \"query\":      \"0\",\n" +
                            "            \"type\":       \"cross_fields\",\n" +
                            "            \"fields\":     [ \"delete_flg\" ]\n" +
                            "          }\n" +
                            "        },\n" +
                            "        {\n" +
                            "          \"multi_match\" : {\n" +
                            "            \"query\":      \"4\",\n" +
                            "            \"type\":       \"cross_fields\",\n" +
                            "            \"fields\":     [ \"status\" ]\n" +
                            "          }\n" +
                            "        }\n" +

                            "      ]\n" +
                            "    }\n" +
                            "  }\n" +
                            "}";
                } else{
                    json = "{\n" +
                            "  \"query\": {\n" +
                            "    \"bool\": {\n" +
                            "      \"must\": [\n" +
                            "        {\n" +
                            "          \"multi_match\" : {\n" +
                            "            \"query\":      \""+filter+"\",\n" +
                            "            \"type\":       \"cross_fields\",\n" +
                            "            \"fields\":     [ \"property_info\", \"owner_info\" , \"prevent_person_info\" , \"prevent_doc_receive_date\" ],\n" +
                            "            \"operator\":   \"and\"\n" +
                            "          }\n" +
                            "        },\n" +
                            "        {\n" +
                            "          \"multi_match\" : {\n" +
                            "            \"query\":      \"0\",\n" +
                            "            \"type\":       \"cross_fields\",\n" +
                            "            \"fields\":     [ \"delete_flg\" ]\n" +
                            "          }\n" +
                            "        },\n" +
                            "        {\n" +
                            "          \"multi_match\" : {\n" +
                            "            \"query\":      \"4\",\n" +
                            "            \"type\":       \"cross_fields\",\n" +
                            "            \"fields\":     [ \"status\" ]\n" +
                            "          }\n" +
                            "        }\n" +
                            "      ]\n" +
                            "    }\n" +
                            "  }\n" +
                            "}";
                }
            } else{
                if(type!= null &&!type.equals("00") && !type.equals("")){
                    json = "{\n" +
                            "  \"query\": {\n" +
                            "    \"bool\": {\n" +
                            "      \"must\": [\n" +
                            "        {\n" +
                            "          \"multi_match\" : {\n" +
                            "            \"query\":      \""+type+"\",\n" +
                            "            \"type\":       \"cross_fields\",\n" +
                            "            \"fields\":     [ \"type\" ]\n" +
                            "          }\n" +
                            "        },\n" +
                            "        {\n" +
                            "          \"multi_match\" : {\n" +
                            "            \"query\":      \"0\",\n" +
                            "            \"type\":       \"cross_fields\",\n" +
                            "            \"fields\":     [ \"delete_flg\" ]\n" +
                            "          }\n" +
                            "        },\n" +
                            "        {\n" +
                            "          \"multi_match\" : {\n" +
                            "            \"query\":      \"4\",\n" +
                            "            \"type\":       \"cross_fields\",\n" +
                            "            \"fields\":     [ \"status\" ]\n" +
                            "          }\n" +
                            "        }\n" +

                            "      ]\n" +
                            "    }\n" +
                            "  }\n" +
                            "}";
                } else{
                    json = "{\n" +
                            "  \"query\": {\n" +
                            "    \"bool\": {\n" +
                            "      \"must\": [\n" +
                            "        {\n" +
                            "          \"multi_match\" : {\n" +
                            "            \"query\":      \"0\",\n" +
                            "            \"type\":       \"cross_fields\",\n" +
                            "            \"fields\":     [ \"delete_flg\" ]\n" +
                            "          }\n" +
                            "        },\n" +
                            "        {\n" +
                            "          \"multi_match\" : {\n" +
                            "            \"query\":      \"4\",\n" +
                            "            \"type\":       \"cross_fields\",\n" +
                            "            \"fields\":     [ \"status\" ]\n" +
                            "          }\n" +
                            "        }\n" +

                            "      ]\n" +
                            "    }\n" +
                            "  }\n" +
                            "}";
                }
            }

            CountResponse countResponse = ESUtil.countTotal("http://localhost:9200/stp/datapreventinfor/_count", json);
            return countResponse.getCount();
        }catch (Exception e){
            LOG.error("Co loi xay ra tai ham countDaDuyetAll");
            return Long.valueOf(0);
        }
    }

    public static List<TransactionProperty> getTransactionPropertyList(int page, String basicFilter, String type, String property_info, String owner_info){
        try {
            if(!property_info.equals("") && !owner_info.equals("")) property_info+= " ";
            String filter = basicFilter + property_info + owner_info;
            if (filter.equals("null")) filter = "";
            filter = EditString.removeDauNhayKep(filter);
            int offset = Constants.ROW_PER_PAGE * (page - 1);
            //filter = EditString.parseKeyForSearch(filter);
            String json = "";
            if(!filter.equals("")){
                if(type!= null &&!type.equals("00") && !type.equals("")){
                    json = "{\n" +
                            "  \"query\": {\n" +
                            "    \"bool\": {\n" +
                            "      \"must\": [\n" +
                            "        {\n" +
                            "          \"multi_match\" : {\n" +
                            "            \"query\":      \""+filter+"\",\n" +
                            "            \"type\":       \"cross_fields\",\n" +
                            "            \"fields\":     [ \"contract_number\", \"property_info\", \"contract_name\", \"transaction_content\",\"notary_date\",\"notary_person\",\"notary_place\",\"relation_object\" ],\n" +
                            "            \"operator\":   \"and\"\n" +
                            "          }\n" +
                            "        },\n" +
                            "        {\n" +
                            "          \"multi_match\" : {\n" +
                            "            \"query\":      \""+type+"\",\n" +
                            "            \"type\":       \"cross_fields\",\n" +
                            "            \"fields\":     [ \"type\" ]\n" +
                            "          }\n" +
                            "        }\n" +
                            "      ]\n" +
                            "    }\n" +
                            "  },\n" +
                            "  \"from\": "+offset+",\n" +
                            "  \"size\": "+Constants.ROW_PER_PAGE+",\n" +
                            "  \"sort\": { \"notary_date_es\": { \"order\": \"desc\" } }\n" +
                            "}";
                } else{
                    json = "{\n" +
                            "  \"query\": {\n" +
                            "    \"bool\": {\n" +
                            "      \"must\": [\n" +
                            "        {\n" +
                            "          \"multi_match\" : {\n" +
                            "            \"query\":      \""+filter+"\",\n" +
                            "            \"type\":       \"cross_fields\",\n" +
                            "            \"fields\":     [ \"contract_number\", \"property_info\", \"contract_name\", \"transaction_content\",\"notary_date\",\"notary_person\",\"notary_place\",\"relation_object\" ],\n" +
                            "            \"operator\":   \"and\"\n" +
                            "          }\n" +
                            "        }\n" +
                            "      ]\n" +
                            "    }\n" +
                            "  },\n" +
                            "  \"from\": "+offset+",\n" +
                            "  \"size\": "+Constants.ROW_PER_PAGE+",\n" +
                            "  \"sort\": { \"notary_date_es\": { \"order\": \"desc\" } }\n" +
                            "}";
                }
            } else{
                if(type!= null &&!type.equals("00") && !type.equals("")){
                    json = "{\n" +
                            "  \"query\": {\n" +
                            "    \"bool\": {\n" +
                            "      \"must\": [\n" +
                            "        {\n" +
                            "          \"multi_match\" : {\n" +
                            "            \"query\":      \""+type+"\",\n" +
                            "            \"type\":       \"cross_fields\",\n" +
                            "            \"fields\":     [ \"type\" ]\n" +
                            "          }\n" +
                            "        }\n" +
                            "      ]\n" +
                            "    }\n" +
                            "  },\n" +
                            "  \"from\": "+offset+",\n" +
                            "  \"size\": "+Constants.ROW_PER_PAGE+",\n" +
                            "  \"sort\": { \"notary_date_es\": { \"order\": \"desc\" } }\n" +
                            "}";
                } else{
                    json = "{\n" +
                            "  \"query\": { \"match_all\": {} },\n" +
                            "  \"from\": "+offset+",\n" +
                            "  \"size\": "+Constants.ROW_PER_PAGE+",\n" +
                            "  \"sort\": { \"notary_date_es\": { \"order\": \"desc\" } }\n" +
                            "}";
                }

            }
            TransactionPropertyResponseData responseData = ESUtil.queryElasicTP("http://localhost:9200/stp/transactionproperty/_search", json);
            TransactionPropertyHits hits = responseData.getHits();
            return hits.getTransactionProperties();
        }catch (Exception e){
            LOG.error("Co loi xay ra tai ham getTransactionPropertyList");
            return new ArrayList<TransactionProperty>();
        }
    }

    public static long counTransactionList(String basicFilter, String type, String property_info, String owner_info) {
        try {
            if(!property_info.equals("") && !owner_info.equals("")) property_info+= " ";
            String filter = basicFilter + property_info + owner_info;
            if (filter.equals("null")) filter = "";
            filter = EditString.removeDauNhayKep(filter);
            //filter = EditString.parseKeyForSearch(filter);
            String json = "";
            if(!filter.equals("")){
                if(type!= null &&!type.equals("00") && !type.equals("")){
                    json = "{\n" +
                            "  \"query\": {\n" +
                            "    \"bool\": {\n" +
                            "      \"must\": [\n" +
                            "        {\n" +
                            "          \"multi_match\" : {\n" +
                            "            \"query\":      \""+filter+"\",\n" +
                            "            \"type\":       \"cross_fields\",\n" +
                            "            \"fields\":     [ \"contract_number\", \"property_info\", \"contract_name\", \"transaction_content\",\"notary_date\",\"notary_person\",\"notary_place\",\"relation_object\" ],\n" +
                            "            \"operator\":   \"and\"\n" +
                            "          }\n" +
                            "        },\n" +
                            "        {\n" +
                            "          \"multi_match\" : {\n" +
                            "            \"query\":      \""+type+"\",\n" +
                            "            \"type\":       \"cross_fields\",\n" +
                            "            \"fields\":     [ \"type\" ]\n" +
                            "          }\n" +
                            "        }\n" +
                            "      ]\n" +
                            "    }\n" +
                            "  }\n" +
                            "}";
                } else{
                    json = "{\n" +
                            "  \"query\": {\n" +
                            "    \"bool\": {\n" +
                            "      \"must\": [\n" +
                            "        {\n" +
                            "          \"multi_match\" : {\n" +
                            "            \"query\":      \""+filter+"\",\n" +
                            "            \"type\":       \"cross_fields\",\n" +
                            "            \"fields\":     [ \"contract_number\", \"property_info\", \"contract_name\", \"transaction_content\",\"notary_date\",\"notary_person\",\"notary_place\",\"relation_object\" ],\n" +
                            "            \"operator\":   \"and\"\n" +
                            "          }\n" +
                            "        }\n" +
                            "      ]\n" +
                            "    }\n" +
                            "  }\n" +
                            "}";
                }
            } else{
                if(type!= null &&!type.equals("00") && !type.equals("")){
                    json = "{\n" +
                            "  \"query\": {\n" +
                            "    \"bool\": {\n" +
                            "      \"must\": [\n" +
                            "        {\n" +
                            "          \"multi_match\" : {\n" +
                            "            \"query\":      \""+type+"\",\n" +
                            "            \"type\":       \"cross_fields\",\n" +
                            "            \"fields\":     [ \"type\" ]\n" +
                            "          }\n" +
                            "        }\n" +
                            "      ]\n" +
                            "    }\n" +
                            "  }\n" +
                            "}";
                } else{
                    json = "{\n" +
                            "  \"query\": { \"match_all\": {} }\n" +
                            "}";
                }

            }
            CountResponse countResponse = ESUtil.countTotal("http://localhost:9200/stp/transactionproperty/_count", json);
            return countResponse.getCount();
        }catch (Exception e){
            LOG.error("Co loi xay ra tai ham counTransactionList");
            return 0;
        }
    }
}

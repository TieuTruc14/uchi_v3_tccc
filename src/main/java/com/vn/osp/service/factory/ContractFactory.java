package com.vn.osp.service.factory;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vn.osp.common.global.Constants;
import com.vn.osp.common.util.EditString;
import com.vn.osp.common.util.PagingResult;
import com.vn.osp.modelview.Contract;
import com.vn.osp.modelview.ContractWrapperDetail;
import com.vn.osp.modelview.TemporaryContract;
import com.vn.osp.modelview.TransactionProperty;
import com.vn.osp.service.Client.Client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by TienManh on 5/18/2017.
 */
public class ContractFactory {

    public static PagingResult listContract(PagingResult page){
        if(page.getPageNumber()<1) page.setNumberPerPage(1);
        int ofsset=page.getNumberPerPage()*(page.getPageNumber()-1);
        String stringFilter="ORDER BY entry_date_time desc limit "+ofsset+","+page.getNumberPerPage();
        String path="/transaction/findTransactionByFilter";
        String response= Client.postObject(path,stringFilter);

        String path1="/transaction/countTotalByFilter";
        path1=Client.postObject(path1,"limit 0,1");

        List<TransactionProperty> transactionPropertys=new ArrayList<TransactionProperty>();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try{
            transactionPropertys = Arrays.asList(objectMapper.readValue(response, TransactionProperty[].class));
            page.setItems(transactionPropertys);
            if(EditString.isNumber(path1)){
                page.setRowCount(Integer.parseInt(path1));
            }


        }catch (Exception e){
            System.out.printf("error roi: "+e.getMessage());
        }

        return page;
    }

    public static PagingResult listContractNotSynchronize(PagingResult page){
        if(page.getPageNumber()<1) page.setNumberPerPage(1);
        int ofsset=page.getNumberPerPage()*(page.getPageNumber()-1);
        String stringFilter="where syn_status=0 ";//chua dong bo
        stringFilter+="ORDER BY entry_date_time desc limit "+ofsset+","+page.getNumberPerPage();

        String path= "/transaction/findTransactionByFilter";
        String response=Client.postObject(path,stringFilter);

        String path1="/transaction/countTotalByFilter";
        path1=Client.postObject(path1,"where syn_status=0 limit 0,1");

        List<TransactionProperty> transactionPropertyNotSynchs=new ArrayList<TransactionProperty>();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try{
            transactionPropertyNotSynchs = Arrays.asList(objectMapper.readValue(response, TransactionProperty[].class));
            page.setItems(transactionPropertyNotSynchs);
            if(EditString.isNumber(path1)){
                page.setRowCount(Integer.parseInt(path1));
            }
        }catch (Exception e){
            System.out.printf("error roi: "+e.getMessage());
        }

        return page;
    }

    public static Contract getById(String id){
        Contract con=new Contract();
        String path="/contract/get-contract-by-id";
        String response=Client.getObjectByFilter(path,"id",id);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try{
            con=objectMapper.readValue(response,Contract.class);
        }catch (Exception e){
            ////System.out.println("loi tai method ContractFactory.getById: " +e.getMessage());
        }

        return con;
    }

    public static PagingResult getTemporaryByType(PagingResult page,String type){
        if(page.getPageNumber()<1) page.setNumberPerPage(1);
        int ofsset=page.getNumberPerPage()*(page.getPageNumber()-1);
        String stringFilter="WHERE type="+type+" ORDER BY entry_date_time desc limit "+ofsset+","+page.getNumberPerPage();

        String path="/contract/temporary/get-by-filter";
        String response =Client.getObjectByFilter(path,"filter",stringFilter);

        String path1="/contract/temporary/count-by-type";
        path1=Client.getObjectByFilter(path1,"type",type);
        ObjectMapper objectMapper=new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_MISSING_CREATOR_PROPERTIES, false);
        objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
        List<TemporaryContract> list=new ArrayList<TemporaryContract>();
        try {
            list=Arrays.asList(objectMapper.readValue(response, TemporaryContract[].class));
            page.setItems(list);
            if(EditString.isNumber(path1)){
                page.setRowCount(Integer.parseInt(path1));
            }
        }catch (Exception e){
            System.out.printf("error tai method ContractFactory.getTemporaryByType() roi: "+e.getMessage());
        }
        return page;
    }

    public static PagingResult listTemporaryMark(PagingResult page){
        if(page.getPageNumber()<1) page.setNumberPerPage(1);
        int ofsset=page.getNumberPerPage()*(page.getPageNumber()-1);
//        String stringFilter="WHERE type_contract="+type_contract+" ORDER BY entry_date_time desc limit "+ofsset+","+page.getNumberPerPage();
        String stringFilter=" ORDER BY entry_date_time desc limit "+ofsset+","+page.getNumberPerPage();

        String path="/transaction/findTransactionByFilter";
        String response =Client.postObject(path,stringFilter);

        String path1="/transaction/countTotalByFilter";
        path1=Client.postObject(path1," limit 0,1");

        ObjectMapper objectMapper=new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_MISSING_CREATOR_PROPERTIES, false);
        objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
        List<TransactionProperty> list=new ArrayList<TransactionProperty>();
        try {
            list=Arrays.asList(objectMapper.readValue(response, TransactionProperty[].class));
            page.setItems(list);
            if(EditString.isNumber(path1)){
                page.setRowCount(Integer.parseInt(path1));
            }
        }catch (Exception e){
            System.out.printf("error tai method ContractFactory.listTemporaryMark() roi: "+e.getMessage());
        }
        return page;
    }

    public static TemporaryContract getTemporaryById(String id){
        TemporaryContract con=new TemporaryContract();
        String path="/contract/temporary";
        String response=Client.getObjectByFilter(path,"id",id);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try{
            con=objectMapper.readValue(response,TemporaryContract.class);
        }catch (Exception e){
            ////System.out.println("loi tai method ContractFactory.getById: " +e.getMessage());
        }

        return con;
    }
}

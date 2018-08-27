package com.vn.osp.service.factory;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vn.osp.common.global.Constants;
import com.vn.osp.common.util.EditString;
import com.vn.osp.common.util.PagingResult;
import com.vn.osp.modelview.ContractKind;
import com.vn.osp.modelview.ContractTemplate;
import com.vn.osp.modelview.ContractTemplateBO;
import com.vn.osp.modelview.TransactionProperty;
import com.vn.osp.service.Client.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by TienManh on 8/21/2017.
 */
public class TemplateFactory {
    private static final Logger LOG  = LoggerFactory.getLogger(TemplateFactory.class);

    public static PagingResult listContractTemplate(PagingResult page){
        if(page.getPageNumber()<1) page.setNumberPerPage(1);
        int offset=page.getNumberPerPage()*(page.getPageNumber()-1);
        String param2[]={"offset",String.valueOf(offset)};
        String param3[]={"number",String.valueOf(page.getNumberPerPage())};
        List<String[]> params=new ArrayList<String[]>();
        params.add(param2);
        params.add(param3);

        String response= Constants.VPCC_API_LINK + "/ContractTemplate/list-page";
        response=Client.getObjectByParams(response,params);

        String path="/ContractTemplate/total";
        path=Client.getObject(path);

        List<ContractTemplateBO> items=new ArrayList<ContractTemplateBO>();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try{
            items = Arrays.asList(objectMapper.readValue(response, ContractTemplateBO[].class));
            page.setItems(items);
            if(EditString.isNumber(path)){
                page.setRowCount(Integer.parseInt(path));
            }
        }catch (Exception e){
            LOG.error("error roi TemplateFacotory.listContractTemplate: "+e.getMessage());
        }

        return page;
    }
    public static List<ContractKind> listContractKind(){

        String response="/contract/list-contract-kind";
        response=Client.getObject(response);

        List<ContractKind> items=new ArrayList<ContractKind>();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try{
            items = Arrays.asList(objectMapper.readValue(response, ContractKind[].class));

        }catch (Exception e){
            LOG.error("error roi TemplateFacotory.listContractKind: "+e.getMessage());
        }

        return items;
    }

    public static ContractTemplateBO getContractTemplate(String id){
        String response="/ContractTemplate/";
        response=Client.getObjectByFilter(response,"id",id);
        ContractTemplateBO item=new ContractTemplateBO();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try{
            item=objectMapper.readValue(response, ContractTemplateBO.class);
            return item;
        }catch (Exception e){
            LOG.error("error roi getContractTemplate: "+e.getMessage());
        }

        return null;
    }

    public static Boolean updateContractTemplate(ContractTemplateBO item){
        String response=Constants.VPCC_API_LINK +"/ContractTemplate/";

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try{
            response=Client.putObject(response,objectMapper.writeValueAsString(item));
            return Boolean.parseBoolean(response);
        }catch (Exception e){
            LOG.error("error roi updateContractTemplate: "+e.getMessage());
        }

        return false;
    }
}

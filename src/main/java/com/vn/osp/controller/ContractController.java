package com.vn.osp.controller;

import com.vn.osp.common.global.Constants;
import com.vn.osp.common.util.*;
import com.vn.osp.context.CommonContext;
import com.vn.osp.modelview.*;
import com.vn.osp.service.QueryFactory;
import com.vn.osp.service.STPQueryFactory;
import com.vn.osp.service.factory.ContractFactory;
/*import com.vn.osp.service.factory.UserFactory;*/
import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by TienManh on 5/8/2017.
 */
@Controller
@RequestMapping("/contract")
public class ContractController extends BaseController {

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView list(ContractWrapper contractWrapper,String status, HttpServletRequest request){
        if(!ValidationPool.checkRoleDetail(request,"11",Constants.AUTHORITY_XEM)) return new ModelAndView("/404");
        if(StringUtils.isBlank(status)){
            status="100";
        }
        setAccessHistory(((CommonContext)request.getSession().getAttribute(request.getSession().getId())).getUser(), Constants.VIEW, request, "Xem danh sách hợp đồng công chứng." + "<br> Trạng thái : Thành công." + "<br> Url : " + request.getContextPath()+"/contract/list");
        return new ModelAndView("contract/offline/contract.list","status",status);
    }

    @RequestMapping(value = "/not-sync-list", method = RequestMethod.GET)
    public ModelAndView notSyncList( HttpServletRequest request,String status){
        if(!ValidationPool.checkRoleDetail(request,"11",Constants.AUTHORITY_THEM)) return new ModelAndView("/404");
        if(StringUtils.isBlank(status)){
            status="100";
        }
        setAccessHistory(((CommonContext)request.getSession().getAttribute(request.getSession().getId())).getUser(), Constants.VIEW, request, "Xem danh sách hợp đồng công chứng chưa đồng bộ." + "<br> Trạng thái : Thành công." + "<br> Url : " + request.getContextPath()+"/contract/not-sync-list");
        return new ModelAndView("contract/synchronize/list","status",status);
    }
    @RequestMapping(value = "/re-sync", method = RequestMethod.GET)
    public ModelAndView reSync( HttpServletRequest request){
        if(!ValidationPool.checkRoleDetail(request,"11",Constants.AUTHORITY_THEM)) return new ModelAndView("/404");
        HttpSession session = request.getSession();

        try {
            List<TransactionProperty> properties = QueryFactory.getTransactionPropertyNotSync();
            ArrayList<SynchronizeContractTag> tagArrayList = new ArrayList<SynchronizeContractTag>();

            if (properties != null && properties.size() > 0) {
                for (int i = 0; i < properties.size(); i++) {

                    TransactionProperty property = properties.get(i);
                    SynchronizeContractTag contractTag = new SynchronizeContractTag();
                    contractTag.setNotaryOfficeCode(CommonContext.authentication_id);
                    contractTag.setContractKindCode(property.getContract_kind());
                    contractTag.setContractNumber(property.getContract_number());
                    contractTag.setTypeSynchronize(Constants.CREATE_CONTRACT);
                    contractTag.setContractName(property.getContract_name());
                    contractTag.setTransactionContent(property.getTransaction_content());
                    contractTag.setNotaryDate(RelateDateTime.formatDate(property.getNotary_date(), "dd/MM/yyyy", "yyyy-MM-dd"));
                    contractTag.setNotaryOfficeName(property.getNotary_office_name());
                    contractTag.setNotaryPerson(property.getNotary_person());
                    contractTag.setNotaryPlace(property.getNotary_place());
                    contractTag.setNotaryFee(property.getNotary_fee());
                    contractTag.setRemunerationCost(property.getRemuneration_cost());
                    contractTag.setNotaryCostTotal(property.getNotary_cost_total());
                    contractTag.setRelationObjects(property.getRelation_object());
                    contractTag.setContractPeriod(property.getContract_period());
                    contractTag.setMortageCancelFlag(property.getMortage_cancel_flag());
                    contractTag.setMortageCancelDate(RelateDateTime.formatDate(property.getMortage_cancel_date(), "dd/MM/yyyy", "yyyy-MM-dd"));
                    contractTag.setMortageCancelNote(property.getMortage_cancel_note());
                    contractTag.setCancelStatus(property.getCancel_status());
                    contractTag.setCancelDescription(property.getCancel_description());
                    contractTag.setEntryUserName(property.getEntry_user_name());
                    contractTag.setEntryDateTime(RelateDateTime.formatDate(property.getEntry_date_time(), "dd/MM/yyyy", "yyyy-MM-dd"));
                    contractTag.setUpdateUserName(property.getUpdate_user_name());
                    contractTag.setUpdateDateTime(RelateDateTime.formatDate(property.getUpdate_date_time(), "dd/MM/yyyy", "yyyy-MM-dd"));
                    contractTag.setBankCode(property.getBank_code());
                    contractTag.setContractNote(property.getNote());
                    contractTag.setPropertyInfo(property.getProperty_info());
                    contractTag.setJson_property(property.getJson_property());
                    contractTag.setJson_person(property.getJson_person());
                    contractTag.setCode_template(property.getCode_template());

                    tagArrayList.add(contractTag);
                }

                SynchonizeContractList contractList = new SynchonizeContractList();
                contractList.setSynAccount(QueryFactory.getSystemConfigByKey("synchronize_account"));
                contractList.setSynPass(QueryFactory.getSystemConfigByKey("synchronize_password"));
                contractList.setSynPass(Crypter.EncryptText(contractList.getSynPass()));
                contractList.setSynchonizeContractList(tagArrayList);

                //nhung~ hop dong dong bo thannh cong thi se co trong results, con ko thif ko co nen se update lai o tccc dung'
                List<SynchonizeContractKey> results = STPQueryFactory.synchronizeContract(contractList.convertToJson());
                if(results != null && results.size()>0){
                    for (int i=0;i<results.size();i++){
                        QueryFactory.synchronizeOK(results.get(i).toString());
                    }
                    //set lai so luong hop dong loi trong thong bao
                    CommonContext context=((CommonContext) request.getSession().getAttribute(request.getSession().getId()));
                    List<TransactionProperty> properties1 = QueryFactory.getTransactionPropertyNotSync();
                    if (properties1 != null && properties1.size() > 0) context.setNotSyncContract(properties1.size());
                    else context.setNotSyncContract(0);
                    session.setAttribute(session.getId(), context);
                }else{
                    return new ModelAndView("redirect:/contract/not-sync-list?status=2");
                }
            }
            return new ModelAndView("redirect:/contract/not-sync-list?status=1");
        }catch (Exception e){
            e.printStackTrace();
            return new ModelAndView("redirect:/contract/not-sync-list?status=3");
        }

    }


    @RequestMapping(value = "/temporary/list", method = RequestMethod.GET)
    public ModelAndView listTemporary(String status, HttpServletRequest request){
        if(!ValidationPool.checkRoleDetail(request,"14",Constants.AUTHORITY_XEM)) return new ModelAndView("/404");
        if(StringUtils.isBlank(status)){
            status="100";
        }
        setAccessHistory(((CommonContext)request.getSession().getAttribute(request.getSession().getId())).getUser(), Constants.LOGIN, request, "Xem danh sách hợp đồng online." + "<br> Trạng thái : Thành công." + "<br> Url : " + request.getContextPath()+"/contract/temporary/list");
        return new ModelAndView("contract/temporary/list","status",status);
    }


    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView add(Contract contract, HttpServletRequest request, HttpServletResponse response){
        if(!ValidationPool.checkRoleDetail(request,"11",Constants.AUTHORITY_THEM)) return new ModelAndView("/404");
        return new ModelAndView("contract/offline/contract.add","contract",contract);
    }

    @RequestMapping(value = "/temporary/add", method = RequestMethod.GET)
    public ModelAndView TemporaryAdd(TemporaryContract contractWrapper, HttpServletRequest request, HttpServletResponse response){
        if(!ValidationPool.checkRoleDetail(request,"14",Constants.AUTHORITY_THEM)) return new ModelAndView("/404");
        return new ModelAndView("contract/temporary/add","contract",contractWrapper);
    }

    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public ModelAndView detail(@PathVariable("id") String id,String from, Contract contract, HttpServletRequest request, HttpServletResponse response){
        if(!ValidationPool.checkRoleDetail(request,"11",Constants.AUTHORITY_XEM)) return new ModelAndView("/404");
        if(id==null || id.equals("")){}
        contract =ContractFactory.getById(id);
        if(!StringUtils.isBlank(from)){
            contract.setJsonstring(from);
        }
        setAccessHistory(((CommonContext)request.getSession().getAttribute(request.getSession().getId())).getUser(), Constants.VIEW, request, "Xem chi tiết hợp đồng công chứng số "+ id + "<br> Trạng thái : Thành công."+ "<br> Url : " + request.getContextPath()+"/contract/detail/"+id);
        return new ModelAndView("contract/offline/detail","contract",contract);
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable("id") String id,String from,Contract contract, HttpServletRequest request, HttpServletResponse response){
        if(!ValidationPool.checkRoleDetail(request,"11",Constants.AUTHORITY_SUA)) return new ModelAndView("/404");
        if(id==null || id.equals("")){}
        contract =ContractFactory.getById(id);
        if(!StringUtils.isBlank(from)){
            contract.setJsonstring(from);
        }

        return new ModelAndView("contract/offline/contract.edit","contract",contract);
    }

    @RequestMapping(value = "/cancel/{id}", method = RequestMethod.GET)
    public ModelAndView cancel(@PathVariable("id") String id,String from, HttpServletRequest request, HttpServletResponse response){
        if(!ValidationPool.checkRoleDetail(request,"11",Constants.AUTHORITY_THEM)) return new ModelAndView("/404");
        if(id==null || id.equals("")){}
        Contract contract =ContractFactory.getById(id);
        if(!StringUtils.isBlank(from)){
            contract.setJsonstring(from);
        }
        return new ModelAndView("contract/offline/contract.cancel","contract",contract);
    }

    @RequestMapping(value = "/addCoppy/{id}", method = RequestMethod.GET)
    public ModelAndView addCoppy(@PathVariable("id") String id,String from, HttpServletRequest request, HttpServletResponse response){
        if(!ValidationPool.checkRoleDetail(request,"11",Constants.AUTHORITY_THEM)) return new ModelAndView("/404");
        if(id==null || id.equals("")){}
        Contract contract =ContractFactory.getById(id);
        if(!StringUtils.isBlank(from)){
            contract.setJsonstring(from);
        }
        return new ModelAndView("/contract/offline/add.coppy","contract",contract);
    }

    @RequestMapping(value = "/addendum/{id}", method = RequestMethod.GET)
    public ModelAndView addendum(@PathVariable("id") String id,String from, HttpServletRequest request, HttpServletResponse response){
        if(!ValidationPool.checkRoleDetail(request,"11",Constants.AUTHORITY_THEM)) return new ModelAndView("/404");
        if(id==null || id.equals("")){}
        Contract contract =ContractFactory.getById(id);
        if(!StringUtils.isBlank(from)){
            contract.setJsonstring(from);
        }
        return new ModelAndView("/contract/offline/addendum","contract",contract);
    }

    @RequestMapping(value = "/temporary/detail/{id}", method = RequestMethod.GET)
    public ModelAndView temporaryDetail(@PathVariable("id") String id, HttpServletRequest request, HttpServletResponse response){
        if(!ValidationPool.checkRoleDetail(request,"14",Constants.AUTHORITY_XEM)) return new ModelAndView("/404");
        if(id==null || id.equals("")){}
        TemporaryContract contract =ContractFactory.getTemporaryById(id);
        return new ModelAndView("contract/temporary/detail","contract",contract);
    }

    @RequestMapping(value = "/temporary/edit/{id}", method = RequestMethod.GET)
    public ModelAndView temporaryEdit(@PathVariable("id") String id, HttpServletRequest request, HttpServletResponse response){
        if(!ValidationPool.checkRoleDetail(request,"14",Constants.AUTHORITY_SUA)) return new ModelAndView("/404");
        if(id==null || id.equals("")){}
        TemporaryContract contract =ContractFactory.getTemporaryById(id);
        return new ModelAndView("contract/temporary/edit","contract",contract);
    }

    @RequestMapping(value = "/temporary/addCoppy/{id}", method = RequestMethod.GET)
    public ModelAndView temporaryAddCoppy(@PathVariable("id") String id, HttpServletRequest request, HttpServletResponse response){
        if(!ValidationPool.checkRoleDetail(request,"14",Constants.AUTHORITY_THEM)) return new ModelAndView("/404");
        if(id==null || id.equals("")){}
        TemporaryContract contract =ContractFactory.getTemporaryById(id);
        return new ModelAndView("contract/temporary/addCoppy","contract",contract);
    }

    @ResponseBody
    @RequestMapping(value = "/get-kind", method = RequestMethod.GET)
    public String getkind(HttpServletRequest request) throws JSONException {
        String result = "";
        long kind_id = Long.valueOf(request.getParameter("kind_id"));
        List<ContractTemplate> contractTemplates = QueryFactory.getContractTemplate(" where 1=1 and kind_id="+kind_id);
        StringBuilder stringBuilder = new StringBuilder();
        for (int i=0; i< contractTemplates.size();i++){
            if(i!=0) result+=";";
            ContractTemplate contractTemplate = contractTemplates.get(i);
            //stringBuilder.append("<li><a href='/system/download" + "?filename=" + contractTemplate.getFile_name() + "&filepath=" + contractTemplate.getFile_path() + "'>" + contractTemplate.getName() + "</a></li><br>");
            stringBuilder.append("<li><a href='#'>" + contractTemplate.getName() + "</a></li><br>");
            result += contractTemplate.getContractTemplateId()+","+contractTemplate.getFile_name()+","+contractTemplate.getFile_path();
        }
        return stringBuilder.toString();
    }

    /*@ResponseBody
    @RequestMapping(value = "/get-kind", method = RequestMethod.GET)
    public ResultRequest multiDelete(HttpServletRequest request) throws JSONException {
        String result = "";
        ResultRequest resultRequest = new ResultRequest();
        long kind_id = Long.valueOf(request.getParameter("kind_id"));
        List<ContractTemplate> contractTemplates = QueryFactory.getContractTemplate(" where 1=1 and kind_id="+kind_id);
        for (int i=0; i< contractTemplates.size();i++){
            if(i!=0) result+=";";
            ContractTemplate contractTemplate = contractTemplates.get(i);
            result += contractTemplate.getContractTemplateId()+","+contractTemplate.getFile_name()+","+contractTemplate.getFile_path();
        }
        resultRequest.setResultObject(contractTemplates);
        return resultRequest;
    }*/

    @ResponseBody
    @RequestMapping(value = "/get-kinds", method = RequestMethod.GET)
    public String multiDelete(HttpServletRequest request) throws JSONException {
        String result = "";
        String kindcode = request.getParameter("kind_code");
        long kind_code = Long.valueOf(kindcode);
        List<ContractTemplate> contractTemplates = QueryFactory.getContractTemplate(" where 1=1 and code="+kind_code);
        for (int i=0; i< contractTemplates.size();i++){
            if(i!=0) result+=";";
            ContractTemplate contractTemplate = contractTemplates.get(i);
            result += contractTemplate.getContractTemplateId()+","+contractTemplate.getFile_name()+","+contractTemplate.getFile_path()+","+contractTemplate.getName();
        }
        return new JSONObject().put("result", result).toString();
    }
}

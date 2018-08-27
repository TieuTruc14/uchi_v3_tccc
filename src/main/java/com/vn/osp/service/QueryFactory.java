package com.vn.osp.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vn.osp.common.global.Constants;
import com.vn.osp.common.util.EditString;
import com.vn.osp.common.util.PagingResult;
import com.vn.osp.modelview.*;
import com.vn.osp.service.Client.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by tranv on 12/20/2016.
 */
public class QueryFactory {

    private static Logger log = LoggerFactory.getLogger(QueryFactory.class.getName());

    public static PrivyTemplate getPrivyTemplateById(int id) {
        String response = Constants.VPCC_API_LINK + "/ContractTemplate/privy-template-by-id";
        String param[] = {"id", String.valueOf(id)};
        List<String[]> params = new ArrayList<String[]>();
        params.add(param);
        response = Client.getObjectByParams(response, params);
        PrivyTemplate item = null;
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            item=objectMapper.readValue(response, PrivyTemplate.class);
        } catch (Exception e) {
            log.error("Lỗi rồi : ----" + e.getMessage() + "----");
        }
        return item;
    }

    public static Role getRoleByCode(String code) {
        String response = Constants.VPCC_API_LINK + "/users/roleByCode";
        String param[] = {"code", String.valueOf(code)};
        List<String[]> params = new ArrayList<String[]>();
        params.add(param);
        response = Client.getObjectByParams(response, params);
        Role item = null;
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            item=objectMapper.readValue(response, Role.class);
        } catch (Exception e) {
            log.error("Lỗi rồi : ----" + e.getMessage() + "----");
        }
        return item;
    }

    public static PropertyTemplate getPropertyTemplateById(int id) {
        String response = Constants.VPCC_API_LINK + "/ContractTemplate/property-template-by-id";
        String param[] = {"id", String.valueOf(id)};
        List<String[]> params = new ArrayList<String[]>();
        params.add(param);
        response = Client.getObjectByParams(response, params);
        PropertyTemplate item = null;
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            item=objectMapper.readValue(response, PropertyTemplate.class);
        } catch (Exception e) {
            log.error("Lỗi rồi : ----" + e.getMessage() + "----");
        }
        return item;
    }

    public static List<PrivyTemplate> getPrivyTemplateByName(String name) {
        String response = Constants.VPCC_API_LINK + "/ContractTemplate/privy-template-get-by-name";
        String param[] = {"name", name};
        List<String[]> params = new ArrayList<String[]>();
        params.add(param);
        response = Client.getObjectByParams(response, params);
        List<PrivyTemplate> items = null;
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            items = Arrays.asList(objectMapper.readValue(response, PrivyTemplate[].class));
        } catch (Exception e) {
            log.error("Lỗi rồi : ----" + e.getMessage() + "----");
        }
        return items;
    }

    public static List<PrivyTemplate> getPrivyTemplateByCode(String code) {
        String response = Constants.VPCC_API_LINK + "/ContractTemplate/privy-template-get-by-code";
        String param[] = {"code", code};
        List<String[]> params = new ArrayList<String[]>();
        params.add(param);
        response = Client.getObjectByParams(response, params);
        List<PrivyTemplate> items = null;
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            items = Arrays.asList(objectMapper.readValue(response, PrivyTemplate[].class));
        } catch (Exception e) {
            log.error("Lỗi rồi : ----" + e.getMessage() + "----");
        }
        return items;
    }

    public static List<PropertyTemplate> getPropertyTemplateByName(String name) {
        String response = Constants.VPCC_API_LINK + "/ContractTemplate/property-template-get-by-name";
        String param[] = {"name", name};
        List<String[]> params = new ArrayList<String[]>();
        params.add(param);
        response = Client.getObjectByParams(response, params);
        List<PropertyTemplate> items = null;
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            items = Arrays.asList(objectMapper.readValue(response, PropertyTemplate[].class));
        } catch (Exception e) {
            log.error("Lỗi rồi : ----" + e.getMessage() + "----");
        }
        return items;
    }

    public static List<PropertyTemplate> getPropertyTemplateByCode(String code) {
        String response = Constants.VPCC_API_LINK + "/ContractTemplate/property-template-get-by-code";
        String param[] = {"code", code};
        List<String[]> params = new ArrayList<String[]>();
        params.add(param);
        response = Client.getObjectByParams(response, params);
        List<PropertyTemplate> items = null;
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            items = Arrays.asList(objectMapper.readValue(response, PropertyTemplate[].class));
        } catch (Exception e) {
            log.error("Lỗi rồi : ----" + e.getMessage() + "----");
        }
        return items;
    }

    public static boolean deletePrivyTemplate(String id) {
        String response = Constants.VPCC_API_LINK + "/ContractTemplate/privy-template";
        response = Client.deleteObject(response, "id", id);
        return Boolean.parseBoolean(response);
    }

    public static Boolean updatePrivyTemplate(PrivyTemplate item) {
        String response = Constants.VPCC_API_LINK + "/ContractTemplate/privy-template";
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            response = Client.putObject(response, objectMapper.writeValueAsString(item));
            return Boolean.parseBoolean(response);
        } catch (Exception e) {
            log.error("Lỗi rồi : ----" + e.getMessage() + "----");
        }
        return false;
    }

    public static boolean deletePropertyTemplate(String id) {
        String response = Constants.VPCC_API_LINK + "/ContractTemplate/property-template";
        response = Client.deleteObject(response, "id", id);
        return Boolean.parseBoolean(response);
    }


    public static Boolean updatePropertyTemplate(PropertyTemplate item) {
        String response = Constants.VPCC_API_LINK + "/ContractTemplate/property-template";
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            response = Client.putObject(response, objectMapper.writeValueAsString(item));
            return Boolean.parseBoolean(response);
        } catch (Exception e) {
            log.error("Lỗi rồi : ----" + e.getMessage() + "----");
        }
        return false;
    }

    public static Boolean addPropertyTemplate(PropertyTemplate item) {
        String response = Constants.VPCC_API_LINK + "/ContractTemplate/property-template";
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            response = Client.postToOSP(response, objectMapper.writeValueAsString(item));
            return Boolean.parseBoolean(response);
        } catch (Exception e) {
            log.error("Lỗi rồi : ----" + e.getMessage() + "----");
        }
        return false;
    }

    public static Boolean addPrivyTemplate(PrivyTemplate item) {
        String response = Constants.VPCC_API_LINK + "/ContractTemplate/privy-template";

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            response = Client.postToOSP(response, objectMapper.writeValueAsString(item));
            return Boolean.parseBoolean(response);
        } catch (Exception e) {
            log.error("Lỗi rồi : ----" + e.getMessage() + "----");
        }
        return false;
    }

    public static List<PrivyTemplate> getAllPrivyTemplateByPage(int firstResult, int maxResult) {
        List<PrivyTemplate> items = APIUtil.getPrivyTemplateByPage(Constants.VPCC_API_LINK + "/ContractTemplate/privy-template-get-page", firstResult, maxResult);
        /*ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        String param2[] = {"offset", String.valueOf(page)};
        String param3[] = {"number", String.valueOf(rowPerPage)};
        List<String[]> params = new ArrayList<String[]>();
        params.add(param2);
        params.add(param3);
        String result = Client.getObjectByParams(Constants.VPCC_API_LINK + "/ContractTemplate/privy-template-get-page", params);
        try {
            items = Arrays.asList(objectMapper.readValue(result, PrivyTemplate[].class));
        } catch (Exception e) {
            log.error("Lỗi rồi : ----" + e.getMessage() + "----");
        }*/
        return items;
    }

    public static List<PropertyTemplate> getAllPropertyplateByPage(int firstResult, int maxResult) {
        List<PropertyTemplate> items = APIUtil.getPropertyTemplateByPage(Constants.VPCC_API_LINK + "/ContractTemplate/property-template-get-page", firstResult, maxResult);
        return items;
    }

    public static List<PropertyTemplate> getAllPropertyTemplate() {
        List<PropertyTemplate> items = null;
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        String result = Client.getObject("/ContractTemplate/property-template");
        try {
            items = Arrays.asList(objectMapper.readValue(result, PropertyTemplate[].class));
        } catch (Exception e) {
            log.error("Lỗi rồi : ----" + e.getMessage() + "----");
        }
        return items;
    }

    public static List<PrivyTemplate> getAllPrivyTemplate() {
        List<PrivyTemplate> items = null;
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        String result = Client.getObject("/ContractTemplate/privy-template");
        try {
            items = Arrays.asList(objectMapper.readValue(result, PrivyTemplate[].class));
        } catch (Exception e) {
            log.error("Lỗi rồi : ----" + e.getMessage() + "----");
        }
        return items;
    }

    public static List<PrivyTemplate> getAllPrivyTemplateFromOSP() {
        List<PrivyTemplate> items = null;
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        String result = Client.getFromOSP(Constants.OSP_API_LINK + "/ContractTemplate/privy-template");
        try {
            items = Arrays.asList(objectMapper.readValue(result, PrivyTemplate[].class));
        } catch (Exception e) {
            log.error("Lỗi rồi : ----" + e.getMessage() + "----");
        }
        return items;
    }

    public static List<PropertyTemplate> getAllPropertyTemplateFromOSP() {
        List<PropertyTemplate> items = null;
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        String result = Client.getFromOSP(Constants.OSP_API_LINK + "/ContractTemplate/property-template");
        try {
            items = Arrays.asList(objectMapper.readValue(result, PropertyTemplate[].class));
        } catch (Exception e) {
            log.error("Lỗi rồi : ----" + e.getMessage() + "----");
        }
        return items;
    }

    public static String getClientInfo(HttpServletRequest request) {
        String clientInfo = request.getRemoteAddr() + " [" + request.getSession().getId() + "]";
        return clientInfo;
    }

    public static List<NotaryOffice> getAllNotaryOffice() {
        List<NotaryOffice> result = APIUtil.getNotaryOffice(Constants.VPCC_API_LINK + "/notaryoffice/selectByFilter", " where 1=1 ");
        return result;
    }

    public static int countTotalUserList(String query) {
        int result = APIUtil.countTotalList(Constants.VPCC_API_LINK + "/users/countTotal", query);
        return result;
    }

    public static User getUserById(Long id) {
        String filter = "where id=" + id;
        List<User> result = APIUtil.getUserByFilter(Constants.VPCC_API_LINK + "/users/selectByFilter", filter);
        if (result != null && result.size() > 0) return result.get(0);
        else return null;
    }

    public static List<User> getUserByAccount(String username) {
        String filter = "where account = '" + username + "'";
        List<User> result = APIUtil.getUserByFilter(Constants.VPCC_API_LINK + "/users/selectByFilter", filter);
        return result;
    }

    public static List<User> getUserList(int page, String filter) {
        int offset = Constants.ROW_PER_PAGE * (page - 1);
        if (offset < 0) offset = 0;
        filter = filter + " limit " + offset + "," + Constants.ROW_PER_PAGE;
        List<User> result = APIUtil.getUserByFilter(Constants.VPCC_API_LINK + "/users/selectByFilter", filter);
        return result;
    }

    public static List<User> getAllUser() {
        String filter = " where active_flg=1";
        List<User> result = APIUtil.getUserByFilter(Constants.VPCC_API_LINK + "/users/selectByFilter", filter);
        return result;
    }

    public static List<User> getNotaryPersonByFilter(String filter) {

        List<User> result = APIUtil.getUserByFilter(Constants.VPCC_API_LINK + "/users/selectByFilter", filter);
        return result;
    }

    public static Boolean updateUser(String data) {
        Boolean result = APIUtil.updateUserAPI(Constants.VPCC_API_LINK + "/users/update-user", data);
        return result;
    }

    public static Boolean removeUser(String data) {
        Boolean result = APIUtil.updateUserAPI(Constants.VPCC_API_LINK + "/users/remove-user", data);
        return result;
    }

    public static Integer countUserAuthorityByGroupId(Long groupId) {
        String filter = " where groupid=" + groupId;
        Integer result = APIUtil.countTotalList(Constants.VPCC_API_LINK + "/users/count-user-authority", filter);
        return result;
    }

    public static Boolean permissionUser(String data) {
        Boolean result = APIUtil.callAPIResult(Constants.VPCC_API_LINK + "/users/permission-user", data);
        return result;
    }

    public static ArrayList<Authority> getAuthorityByFilter(String stringFilter) {
        ArrayList<Authority> result = APIUtil.getAuthorityByFilter(Constants.VPCC_API_LINK + "/users/get-authority-by-filter", stringFilter);
        return result;
    }

    public static GroupRole getGroupRoleByName(String grouprolename) {
        String filter = " where grouprolename='" + grouprolename + "' ";
        List<GroupRole> result = APIUtil.getGroupRoleByFilter(Constants.STP_API_LINK +"/users/select-group-role", filter);
        if (result != null && result.size() > 0) {
            return result.get(0);
        }
        return null;
    }

    public static GrouproleAuthority getGroupRoleAuthority(long grouprole_id) {
        String stringFilter = " where grouprole_id=" + grouprole_id;
        ArrayList<GrouproleAuthority> result = APIUtil.getGroupRoleAuthorityFilter(Constants.VPCC_API_LINK + "/users/select-group-role-authority", stringFilter);
        if (result != null && result.size() > 0) return result.get(0);
        else return null;
    }

    public static List<GrouproleAuthority> getGroupRoleAuthorites(long grouprole_id) {
        String stringFilter = " where grouprole_id=" + grouprole_id;
        ArrayList<GrouproleAuthority> result = APIUtil.getGroupRoleAuthorityFilter(Constants.VPCC_API_LINK + "/users/select-group-role-authority", stringFilter);
        return result;
    }

    public static ArrayList<UserAuthority> getUserAuthorityByFilter(String stringFilter) {
        ArrayList<UserAuthority> result = APIUtil.getUserAuthorityFilter(Constants.VPCC_API_LINK + "/users/select-user-authority-by-filter", stringFilter);
        return result;
    }

    public static ArrayList<Authority> getAuthorityByType(int type) {
        String filter = " where type=" + type;
        ArrayList<Authority> result = APIUtil.getAuthorityByFilter(Constants.VPCC_API_LINK + "/users/get-authority-by-filter", filter);
        return result;
    }

    public static ArrayList<Announcement> getLatestAnnouncement(int number) {
        ArrayList<Announcement> result = (ArrayList<Announcement>) APIUtil.getAnnouncementList(Constants.VPCC_API_LINK + "/announcement/findLatestAnnouncement", String.valueOf(number));
        return result;
    }
    public static ArrayList<Contract> getLatestContract(int number) {
        ArrayList<Contract> result = (ArrayList<Contract>) APIUtil.getContractList(Constants.VPCC_API_LINK + "/contract/findLatestContract", String.valueOf(number));
        return result;
    }

    public static String getSystemConfigByKey(String key) {
        String result = APIUtil.getSystemConfigByKey(Constants.VPCC_API_LINK + "/systemmanager/getConfigValue", key);
        return result;
    }

    public static ArrayList<Authority> getAuthorities() {
        ArrayList<Authority> result = APIUtil.getAuthorityByFilter(Constants.VPCC_API_LINK + "/users/get-authority-by-filter", "where 1=1");
        return result;
    }

    /*
    * author vietmanh
    * date 3/14/2017
    * Ham dem so trang
    * */
    public static int countPage(int total) {
        int rowPerpage = Constants.ROW_PER_PAGE;
        int result = 0;
        result = total / rowPerpage;
        int temp = total % rowPerpage;
        if (temp > 0) {
            result = result + 1;
            return result;
        } else return result;
    }


    public static List<User> getUser(String filter) {
        List<User> result = APIUtil.getUserByFilter(Constants.VPCC_API_LINK + "/users/selectByFilter", "where 1=1");
        return result;
    }

    public static int countTotalAccessHistory(int page, String advanceSearchFilter) {
        if (advanceSearchFilter == null || advanceSearchFilter.equals("")) advanceSearchFilter = " where 1=1";
        String filter = advanceSearchFilter;


        Integer result = APIUtil.countTotalList(Constants.VPCC_API_LINK + "/systemmanager/countTotal", filter);
        return result;
    }

    /*
  * author minhbq
  * date 3/23/2017
  * lich su truy cap he thong
  * */
    public static List<AccessHistory> getAccesHistory(int page, String advanceSearchFilter) {
        int offset = Constants.ROW_PER_PAGE * (page - 1);
        if (offset < 0) offset = 0;
        if (advanceSearchFilter == null || advanceSearchFilter.equals("")) advanceSearchFilter = " where 1=1";
        String paggingQuery = " LIMIT " + offset + ", " + Constants.ROW_PER_PAGE;
        String filter = advanceSearchFilter + paggingQuery;
        ////System.out.println("Query vao " + filter);
        List<AccessHistory> result = APIUtil.getAccessHistory(Constants.VPCC_API_LINK + "/systemmanager/selectAccessHistoryByFilter", filter);
        return result;
    }

    /*
  * author MANHTV
  * date 3/23/2017
  * lich su truy cap he thong
  * */
    public static Boolean setAccesHistory(AccessHistory accessHistory) {
        Boolean result = APIUtil.callAPIResult(Constants.VPCC_API_LINK + "/systemmanager/setAccessHistory", accessHistory.generateUpdateJson());
        return result;
    }

    public static List<ContractKind> getContractKind(String filter) {
        List<ContractKind> result = APIUtil.getContractKind(Constants.VPCC_API_LINK + "/contract/getContractKindByFilter", filter);
        return result;
    }

    public static List<ContractTemplate> getContractTemplate(String filter) {
        List<ContractTemplate> result = APIUtil.getContractTemplate(Constants.VPCC_API_LINK + "/contract/getContractTemplateByFilter", filter);
        return result;
    }

    public static List<AccessHistory> getAllAccessHistory(String advanceSearchFilter) {
        String filter = advanceSearchFilter;
        List<AccessHistory> result = APIUtil.getAccessHistory(Constants.VPCC_API_LINK + "/systemmanager/selectAccessHistoryByFilter", filter);
        return result;
    }


    /*
* author Minhbq
* date 5/15/2017
* danh sach thong bao noi bo
* */
    public static List<Announcement> getAnnouncement(int page, String advanceSearchFilter) {
        int offset = Constants.ROW_PER_PAGE * (page - 1);
        if (offset < 0) offset = 0;
        if (advanceSearchFilter == null || advanceSearchFilter.equals("")) advanceSearchFilter = " where 1=1";
        String paggingQuery = " ORDER BY entry_date_time DESC " + " LIMIT " + offset + ", " + Constants.ROW_PER_PAGE ;
        String filter = advanceSearchFilter + paggingQuery;

        List<Announcement> result = APIUtil.getAnnouncementList(Constants.VPCC_API_LINK + "/announcement/findAnnouncementByFilter", filter);
        return result;
    }

    public static int countTotalAnnouncement(int page, String advanceSearchFilter) {
        if (advanceSearchFilter == null || advanceSearchFilter.equals("")) advanceSearchFilter = " where 1=1";
        String filter = advanceSearchFilter;


        Integer result = APIUtil.countTotalList(Constants.VPCC_API_LINK + "/announcement/countTotalByFilter", filter);
        return result;
    }

    /*Báo cáo theo ngân hàng */
    public static List<Bank> getBankList(String advanceSearchFilter) {

        if (advanceSearchFilter == null || advanceSearchFilter.equals("")) advanceSearchFilter = " where 1=1";
        String filter = advanceSearchFilter;
        List<Bank> result = APIUtil.getBankList(Constants.VPCC_API_LINK + "/bank/selectBank", filter);
        return result;
    }

    public static List<TransactionProperty> getReportByBank(int page, String advanceSearchFilter) {

        int offset = Constants.ROW_PER_PAGE * (page - 1);
        if (advanceSearchFilter == null || advanceSearchFilter.equals("")) advanceSearchFilter = " where 1=1";
        ////System.out.println("page " + page);
        String paggingQuery = " LIMIT " + offset + ", " + Constants.ROW_PER_PAGE;
        String filter = advanceSearchFilter + paggingQuery;
        ////System.out.println("filter bank " + filter);
        List<TransactionProperty> result = APIUtil.getTransactionProperty(Constants.VPCC_API_LINK + "/transaction/findTransactionByFilter", filter);
        return result;
    }

    public static Boolean createGroupRole(String data) {
        Boolean result = APIUtil.callAPIResult(Constants.VPCC_API_LINK + "/users/add-grouprole", data);
        return result;
    }

    public static Boolean checkExitsGroupRole(String data) {
        Boolean result = APIUtil.callAPIResult(Constants.VPCC_API_LINK + "/users/check-exits-grouprole", data);
        return result;
    }

    public static Boolean updateGroupRole(String data) {
        Boolean result = APIUtil.callAPIResult(Constants.VPCC_API_LINK + "/users/update-grouprole", data);
        return result;
    }

    public static Integer countTotalGroupRoleByName(String grouprolename) {
        String filter = " where grouprolename like '" + grouprolename + "'";
        Integer result = APIUtil.countTotalList(Constants.VPCC_API_LINK + "/users/count-total-group-role", filter);
        return result;
    }

    public static Integer countTotalGroupRole(String titleFilter) {
        String filter = " where grouprolename like '%" + titleFilter + "%'";
        Integer result = APIUtil.countTotalList(Constants.VPCC_API_LINK + "/users/count-total-group-role", filter);
        return result;
    }

    public static Boolean deleteGroupRoleAuthority(Long groupid) {
        Boolean result = APIUtil.callAPIResult(Constants.VPCC_API_LINK + "/users/delete-group-role-authority-by-groupid", groupid.toString());
        return result;
    }

    public static Boolean deleteGroupRole(Long groupid) {
        Boolean result = APIUtil.callAPIResult(Constants.VPCC_API_LINK + "/users/delete-group-role-by-groupid", groupid.toString());
        return result;
    }

    public static List<GroupRole> getGroupRoleByPage(int page, String titleFilter) {
        int offset = Constants.ROW_PER_PAGE * (page - 1);
        if (offset < 0) offset = 0;
        String filter = " where grouprolename like '%" + titleFilter + "%' order by grouprolename ASC";
        filter = filter + " limit " + offset + "," + Constants.ROW_PER_PAGE;
        List<GroupRole> result = APIUtil.getGroupRoleByFilter(Constants.VPCC_API_LINK + "/users/select-group-role", filter);
        return result;
    }

    public static List<TransactionProperty> getTransactionPropertyNotSync() {

        String filter = " where syn_status = 0 order by notary_date desc";
        List<TransactionProperty> result = APIUtil.getTransactionProperty(Constants.VPCC_API_LINK + "/transaction/findTransactionByFilter", filter);
        return result;
    }

    public static Boolean synchronizeOK(String data) {
        Boolean result = APIUtil.synchronizeOK(Constants.VPCC_API_LINK + "/transaction/synchronize-ok", data);
        return result;
    }

    public static int countTotalReportBank(String advanceSearchFilter) {
        if (advanceSearchFilter == null || advanceSearchFilter.equals("")) advanceSearchFilter = " where 1=1";
        String filter = advanceSearchFilter;


        Integer result = APIUtil.countTotalList(Constants.VPCC_API_LINK + "/transaction/countTotalByFilter", filter);
        return result;
    }

    /*History Contract*/
    public static List<HistoryContract> getHistoryContract(int page, String advanceSearchFilter) {
        int offset = Constants.ROW_PER_PAGE * (page - 1);
        if (advanceSearchFilter == null || advanceSearchFilter.equals("")) advanceSearchFilter = " where 1=1";
        String paggingQuery = " LIMIT " + offset + ", " + Constants.ROW_PER_PAGE;
        String filter = advanceSearchFilter + paggingQuery;
        List<HistoryContract> result = APIUtil.getHistoryContract(Constants.VPCC_API_LINK + "/contract/selectContractHistoryByFilter", filter);
        return result;
    }

    public static int countHistoryContract(int page, String advanceSearchFilter) {
        if (advanceSearchFilter == null || advanceSearchFilter.equals("")) advanceSearchFilter = " where 1=1";
        String filter = advanceSearchFilter;

        Integer result = APIUtil.countTotalList(Constants.VPCC_API_LINK + "/contract/countTotalByFilter", filter);
        return result;
    }

    public static List<Role> listRole1() {
        List<Role> result = APIUtil.getRole(Constants.VPCC_API_LINK + "/users/list-role", " where 1 = 1");
        return result;
    }

    public static List<NotaryOffice> notaryOffices(String advanceSearchFilter) {

        List<NotaryOffice> result = APIUtil.getNotaryOffice(Constants.VPCC_API_LINK + "/notaryoffice/selectByFilter", "where npo_notary_office.office_type = 2 or npo_notary_office.office_type = 3");
        return result;
    }

    /*Hướng dẫn sử dụng
    * Minhbq
    * 17/5/2017
    * */
    public static List<Manual> getManual(int page, String advanceSearchFilter) {
        int offset = Constants.ROW_PER_PAGE * (page - 1);
        if (advanceSearchFilter == null || advanceSearchFilter.equals("")) advanceSearchFilter = " where 1=1";
        String paggingQuery = " LIMIT " + offset + ", " + Constants.ROW_PER_PAGE;
        String filter = advanceSearchFilter + paggingQuery;
        ////System.out.println("stringFilter " + advanceSearchFilter);
        List<Manual> result = APIUtil.getManual(Constants.VPCC_API_LINK + "/manual/findManualByFilter", filter);
        return result;
    }

    public static int countTotalManual(int page, String advanceSearchFilter) {
        if (advanceSearchFilter == null || advanceSearchFilter.equals("")) advanceSearchFilter = " where 1=1";
        String filter = advanceSearchFilter;

        Integer result = APIUtil.countTotalList(Constants.VPCC_API_LINK + "/manual/countTotalByFilter", filter);
        return result;
    }

    public static Boolean updateManual(String data) {
        Boolean result = APIUtil.callAPIResult(Constants.VPCC_API_LINK + "/manual/updateManual", data);
        return result;
    }

    /*Báo cáo theo công chứng viên
    * Minhbq
    * 17/5/2017
    * */
    public static List<UserByRoleList> getNotaryPerson(String advanceSearchFilter) {
        String filter;
        filter = " where ng.id = 42 ";
        List<UserByRoleList> result = APIUtil.getUserByRoleList(Constants.VPCC_API_LINK + "/users/select-user-by-group-role", filter);
        return result;
    }

    public static List<ReportByGroupTotal> getReportTotalListByGroup(String data) {
        List<ReportByGroupTotal> result = APIUtil.getReportByGroupList(Constants.VPCC_API_LINK + "/contract/selectReportByGroupTotal", data);
        return result;
    }

    public static List<TransactionProperty> getAllReportDetailByGroup(String data) {
        List<TransactionProperty> result = APIUtil.getTransactionPropertyList(Constants.VPCC_API_LINK + "/contract/selectAllDetailReportByGroup", data);
        return result;
    }

    public static List<TransactionProperty> getReportDetailByGroup(String data) {
        List<TransactionProperty> result = APIUtil.getTransactionPropertyList(Constants.VPCC_API_LINK + "/contract/selectDetailReportByGroup", data);
        return result;
    }

    public static int countDetailReportByGroup(String advanceSearchFilter) {
        int result = APIUtil.countTotalList(Constants.VPCC_API_LINK + "/contract/countDetailReportByGroup", advanceSearchFilter);
        return result;
    }

    public static List<GroupRole> getActiveGroupRole(String filter) {
        List<GroupRole> result = APIUtil.getGroupRoleByFilter(Constants.VPCC_API_LINK + "/users/select-group-role", "where active_flg=1");
        return result;
    }

    public static List<UserGroupRole> getUserGroupRoleList(Long userId) {
        String filter = " where nua.user_Id =" + userId + " and active_flg=1";
        List<UserGroupRole> result = APIUtil.getUserGroupRoleByFilter(Constants.VPCC_API_LINK + "/users/select-user-group-role", filter);
        return result;
    }

    public static ArrayList<GrouproleAuthority> getGroupRoleAuthorityByFilter(String stringFilter) {
        ArrayList<GrouproleAuthority> result = APIUtil.getGroupRoleAuthorityFilter(Constants.VPCC_API_LINK + "/users/select-group-role-authority", stringFilter);
        return result;
    }

    public static GroupRole getGroupRole(long id) {
        String filter = " where id=" + id + " ";
        List<GroupRole> result = APIUtil.getGroupRoleByFilter(Constants.VPCC_API_LINK + "/users/select-group-role", filter);
        if (result != null && result.size() > 0) {
            return result.get(0);
        }
        return null;
    }

    public static List<ReportByNotaryPerson> getReportByNotaryPerson(int page, String advanceSearchFilter) {
        int offset = Constants.ROW_PER_PAGE * (page - 1);
        if (offset < 0) offset = 0;
        if (advanceSearchFilter == null || advanceSearchFilter.equals("")) advanceSearchFilter = " where 1=1";
        String paggingQuery = " LIMIT " + offset + ", " + Constants.ROW_PER_PAGE;
        String filter = advanceSearchFilter + paggingQuery;
        ////System.out.println("stringFilter " + filter);
        List<ReportByNotaryPerson> result = APIUtil.getReportByNotaryPerson(Constants.VPCC_API_LINK + "/contract/selectReportByNotary", filter);
        return result;
    }

    public static List<ReportByNotaryPerson> getReportByNotaryPersonAll(int page, String advanceSearchFilter) {

        if (advanceSearchFilter == null || advanceSearchFilter.equals("")) advanceSearchFilter = " where 1=1";


        List<ReportByNotaryPerson> result = APIUtil.getReportByNotaryPerson(Constants.VPCC_API_LINK + "/contract/selectReportByNotary", advanceSearchFilter);
        return result;
    }

    public static List<ReportByNotaryPerson> getAllReportByNotaryPerson(String advanceSearchFilter) {
        List<ReportByNotaryPerson> result = APIUtil.getReportByNotaryPerson(Constants.VPCC_API_LINK + "/contract/selectReportByNotary", advanceSearchFilter);
        return result;
    }

    public static int countTotalReportByNotary(String advanceSearchFilter) {
        if (advanceSearchFilter == null || advanceSearchFilter.equals("")) advanceSearchFilter = " where 1=1";
        String filter = advanceSearchFilter;

        Integer result = APIUtil.countTotalList(Constants.VPCC_API_LINK + "/contract/countTotalReportByNotary", filter);
        return result;
    }

    /*Báo cáo theo chuyên viên soạn thảo
  * Minhbq
  * 17/5/2017
  * */
    public static List<UserByRoleList> getDrafter(String advanceSearchFilter) {
        String filter;
        filter = " where ng.id = 43 ";
        List<UserByRoleList> result = APIUtil.getUserByRoleList(Constants.VPCC_API_LINK + "/users/select-user-by-group-role", filter);
        return result;
    }

    public static List<ReportByUser> getReportByUser(int page, String advanceSearchFilter) {
        int offset = Constants.ROW_PER_PAGE * (page - 1);
        if (offset < 0) offset = 0;
        if (advanceSearchFilter == null || advanceSearchFilter.equals("")) advanceSearchFilter = " where 1=1";
        String paggingQuery = " LIMIT " + offset + ", " + Constants.ROW_PER_PAGE;
        String filter = advanceSearchFilter + paggingQuery;
        ////System.out.println("stringFilter " + filter);
        List<ReportByUser> result = APIUtil.getReportByUser(Constants.VPCC_API_LINK + "/contract/selectReportByUser", filter);
        return result;
    }

    public static int countTotalReportByUser(String advanceSearchFilter) {
        if (advanceSearchFilter == null || advanceSearchFilter.equals("")) advanceSearchFilter = " where 1=1";
        String filter = advanceSearchFilter;

        Integer result = APIUtil.countTotalList(Constants.VPCC_API_LINK + "/contract/countTotalReportByUser", filter);
        return result;
    }

    public static List<ContractError> getReportContractError(int page, String advanceSearchFilter) {
        int offset = Constants.ROW_PER_PAGE * (page - 1);
        if (offset < 0) offset = 0;
        if (advanceSearchFilter == null || advanceSearchFilter.equals("")) advanceSearchFilter = " where 1=1";
        String paggingQuery = " LIMIT " + offset + ", " + Constants.ROW_PER_PAGE;
        String filter = advanceSearchFilter + paggingQuery;
        ////System.out.println("stringFilter " + filter);
        List<ContractError> result = APIUtil.getContractError(Constants.VPCC_API_LINK + "/contract/selectReportByContractError", filter);
        return result;
    }

    public static List<ReportByTT20List> getReportByTT20List(String data) {

        List<ReportByTT20List> result = APIUtil.getReportByTT20List(Constants.VPCC_API_LINK + "/contract/report-tt-20", data);
        return result;
    }

    public static List<ReportByTT04List> getReportByTT04List(String data) {
        List<ReportByTT04List> result = APIUtil.getReportByTT04List(Constants.VPCC_API_LINK + "/contract/report-tt-04", data);
        return result;
    }

    public static List<ContractAdditional> getReportContractAdditional(int page, String advanceSearchFilter) {
        int offset = Constants.ROW_PER_PAGE * (page - 1);
        if (offset < 0) offset = 0;
        if (advanceSearchFilter == null || advanceSearchFilter.equals("")) advanceSearchFilter = " where 1=1";
        String paggingQuery = " LIMIT " + offset + ", " + Constants.ROW_PER_PAGE;
        String filter = advanceSearchFilter + paggingQuery;
        ////System.out.println("stringFilter " + filter);
        List<ContractAdditional> result = APIUtil.getReportContractAdditional(Constants.VPCC_API_LINK + "/contract/selectReportByContractAdditional", filter);

        return result;
    }

    public static List<ContractError> getAllReportContractError(String advanceSearchFilter) {
        List<ContractError> result = APIUtil.getContractError(Constants.VPCC_API_LINK + "/contract/selectReportByContractError", advanceSearchFilter);
        return result;
    }

    public static List<ContractCertify> getContractCertify(String advanceSearchFilter) {
        if (advanceSearchFilter == null || advanceSearchFilter.equals("")) advanceSearchFilter = " where 1=1";
        String filter = advanceSearchFilter;
        ////System.out.println("stringFilter " + filter);
        List<ContractCertify> result = APIUtil.getContractCertify(Constants.VPCC_API_LINK + "/contract/selectReportByContractCertify", filter);
        return result;
    }

    public static List<ContractStastics> getContractStasticsDrafter(String advanceSearchFilter) {
        String filter = advanceSearchFilter;
        List<ContractStastics> result = APIUtil.getContractStastics(Constants.VPCC_API_LINK + "/contract/contractStatisticsDrafter", filter);
        return result;
    }

    public static List<ContractStastics> getContractStasticsNotary(String advanceSearchFilter) {
        String filter = advanceSearchFilter;
        List<ContractStastics> result = APIUtil.getContractStastics(Constants.VPCC_API_LINK + "/contract/contractStatisticsNotary", filter);
        return result;
    }

    public static List<ContractStasticsBank> getContractStasticsBank(String advanceSearchFilter) {
        String filter = advanceSearchFilter;
        List<ContractStasticsBank> result = APIUtil.getContractStasticsBank(Constants.VPCC_API_LINK + "/contract/contractStatisticsBank", filter);
        return result;
    }

    public static Boolean addTransactionProperty(TransactionProperty transactionProperty) {

        Boolean result = APIUtil.addTransactionPropertyAPI(Constants.VPCC_API_LINK + "/transaction/addTransactionPropertyNative", transactionProperty.generateJson());
        return result;
    }
    /*public static Boolean addTransactionProperty(TransactionProperty transactionProperty) {

        Boolean result = APIUtil.addTransactionPropertyAPI(Constants.VPCC_API_LINK + "/transaction/addTransactionProperty", transactionProperty.generateJson());
        return result;
    }*/

    /*Ngân hàng OSP*/
    public static List<Bank> getBank(int page, String advanceSearchFilter) {
        int offset = Constants.ROW_PER_PAGE * (page - 1);
        if (offset < 0) offset = 0;
        if (advanceSearchFilter == null || advanceSearchFilter.equals("")) advanceSearchFilter = " where 1=1";
        String paggingQuery = " LIMIT " + offset + ", " + Constants.ROW_PER_PAGE;
        String filter = advanceSearchFilter + paggingQuery;
        ////System.out.println("stringFilter " + filter);
        List<Bank> result = APIUtil.getBankList(Constants.VPCC_API_LINK + "/bank/selectBank", filter);
        return result;
    }

    public static int countTotalBank(String advanceSearchFilter) {
        if (advanceSearchFilter == null || advanceSearchFilter.equals("")) advanceSearchFilter = " where 1=1";
        String filter = advanceSearchFilter;

        Integer result = APIUtil.countTotalList(Constants.VPCC_API_LINK + "/bank/countBank", filter);
        return result;
    }

    public static int countTotalBankFromOSP(String advanceSearchFilter) {

        if (advanceSearchFilter == null || advanceSearchFilter.equals("")) advanceSearchFilter = " where 1=1";
        String filter = advanceSearchFilter;

        Integer result = APIUtil.countTotalList(Constants.OSP_API_LINK + "/bank/CountBankByFilter", filter);
        return result;
    }

    public static List<Bank> getBankFromOSP(String advanceSearchFilter) {
        List<Bank> result = APIUtil.getBankFromOSP(Constants.OSP_API_LINK + "/bank/FindBankByFilter", advanceSearchFilter);
        return result;
    }

    /*Proivince OSP*/
    public static List<ProvinceList> getProvince(int page, String advanceSearchFilter) {
        int offset = Constants.ROW_PER_PAGE * (page - 1);
        if (offset < 0) offset = 0;
        if (advanceSearchFilter == null || advanceSearchFilter.equals("")) advanceSearchFilter = " where 1=1";
        String paggingQuery = " LIMIT " + offset + ", " + Constants.ROW_PER_PAGE;
        String filter = advanceSearchFilter + paggingQuery;
        ////System.out.println("stringFilter " + filter);
        List<ProvinceList> result = APIUtil.getProvinceList(Constants.VPCC_API_LINK + "/province/findProvinceByFilter", filter);
        return result;
    }

    public static int countTotalProvince(String advanceSearchFilter) {
        if (advanceSearchFilter == null || advanceSearchFilter.equals("")) advanceSearchFilter = " where 1=1";
        String filter = advanceSearchFilter;

        Integer result = APIUtil.countTotalList(Constants.VPCC_API_LINK + "/province/countProvinceByFilter", filter);
        return result;
    }

    public static int countTotalProvinceFromOSP(String advanceSearchFilter) {

        if (advanceSearchFilter == null || advanceSearchFilter.equals("")) advanceSearchFilter = " where 1=1";
        String filter = advanceSearchFilter;

        Integer result = APIUtil.countTotalList(Constants.OSP_API_LINK + "/province/countProvinceByFilter", filter);
        return result;
    }

    public static List<ProvinceList> getProvinceFromOSP(String advanceSearchFilter) {
        List<ProvinceList> result = APIUtil.getProvinceList(Constants.OSP_API_LINK + "/province/findProvinceByFilter", advanceSearchFilter);
        return result;
    }

    /*ContractTemplate OSP*/
    public static List<ContractTemp> getContractTemp(int page, String advanceSearchFilter) {
        int offset = Constants.ROW_PER_PAGE * (page - 1);
        if (offset < 0) offset = 0;
        if (advanceSearchFilter == null || advanceSearchFilter.equals("")) advanceSearchFilter = " where 1=1";
        String paggingQuery = " LIMIT " + offset + ", " + Constants.ROW_PER_PAGE;
        String filter = advanceSearchFilter + paggingQuery;
        ////System.out.println("stringFilter " + filter);
        List<ContractTemp> result = APIUtil.getContractTempList(Constants.VPCC_API_LINK + "/ContractTemplate/FindContractTempByFilter", filter);
        return result;
    }

    public static List<ContractTempListByKindName> getContractTempByKindName(int page, String advanceSearchFilter) {
        int offset = Constants.ROW_PER_PAGE * (page - 1);
        if (offset < 0) offset = 0;
        if (advanceSearchFilter == null || advanceSearchFilter.equals("")) advanceSearchFilter = " where 1=1";
        String paggingQuery = " LIMIT " + offset + ", " + Constants.ROW_PER_PAGE;
        String filter = advanceSearchFilter + paggingQuery;
        ////System.out.println("stringFilter " + filter);
        List<ContractTempListByKindName> result = APIUtil.getContractTempListByKindName(Constants.VPCC_API_LINK + "/ContractTemplate/FindContractTempListByFilter", filter);
        return result;
    }

    public static int countTotalContractTemp(String advanceSearchFilter) {
        if (advanceSearchFilter == null || advanceSearchFilter.equals("")) advanceSearchFilter = " where 1=1";
        String filter = advanceSearchFilter;

        Integer result = APIUtil.countTotalList(Constants.VPCC_API_LINK + "/ContractTemplate/CountContractTempByFilter", filter);
        return result;
    }

    public static int countTotalContractTempFromOSP(String advanceSearchFilter) {

        if (advanceSearchFilter == null || advanceSearchFilter.equals("")) advanceSearchFilter = " where 1=1";
        String filter = advanceSearchFilter;

        Integer result = APIUtil.countTotalList(Constants.OSP_API_LINK + "/ContractTemplate/CountContractTempByFilter", filter);
        return result;
    }

    public static List<ContractTemp> getContractTempFromOSP(String advanceSearchFilter) {
        List<ContractTemp> result = APIUtil.getContractTempList(Constants.OSP_API_LINK + "/ContractTemplate/FindContractTempByFilter", advanceSearchFilter);
        return result;
    }

    /*ContractKind OSP*/
    public static List<ContractKinds> getContractKind(int page, String advanceSearchFilter) {
        int offset = Constants.ROW_PER_PAGE * (page - 1);
        if (offset < 0) offset = 0;
        if (advanceSearchFilter == null || advanceSearchFilter.equals("")) advanceSearchFilter = " where 1=1";
        String paggingQuery = " LIMIT " + offset + ", " + Constants.ROW_PER_PAGE;
        String filter = advanceSearchFilter + paggingQuery;
        List<ContractKinds> result = APIUtil.getContractKindList(Constants.VPCC_API_LINK + "/contract/AddContractKind", filter);
        return result;
    }

    public static int countTotalContractKind(String advanceSearchFilter) {
        if (advanceSearchFilter == null || advanceSearchFilter.equals("")) advanceSearchFilter = " where 1=1";
        String filter = advanceSearchFilter;

        Integer result = APIUtil.countTotalList(Constants.VPCC_API_LINK + "/contract/countContractKindByFilter", filter);
        return result;
    }

    public static int countTotalContractKindFromOSP(String advanceSearchFilter) {

        if (advanceSearchFilter == null || advanceSearchFilter.equals("")) advanceSearchFilter = " where 1=1";
        String filter = advanceSearchFilter;

        Integer result = APIUtil.countTotalList(Constants.OSP_API_LINK + "/contractkind/countContractKindByFilter", filter);
        return result;
    }

    public static List<ContractKinds> getContractKindFromOSP(String advanceSearchFilter) {
        List<ContractKinds> result = APIUtil.getContractKindList(Constants.OSP_API_LINK + "/contractkind/findContractKindByFilter", advanceSearchFilter);
        return result;
    }

    public static List<Announcement> getPopupAnnouncement() {
        List<Announcement> result = APIUtil.getAnnouncementList(Constants.VPCC_API_LINK + "/announcement/popupAnnouncement", "");
        return result;
    }

    public static List<ReportByBankDetail> getAllReportByBank(String advanceSearchFilter) {

        List<ReportByBankDetail> result = APIUtil.getReportByBankDetail(Constants.VPCC_API_LINK + "/transaction/reportByBank", advanceSearchFilter);
        return result;
    }
    public static List<ReportByBankDetail> getReportBankDetail(int page,String advanceSearchFilter) {
        int offset = Constants.ROW_PER_PAGE * (page - 1);
        if (advanceSearchFilter == null || advanceSearchFilter.equals("")) advanceSearchFilter = " where 1=1";
        ////System.out.println("page " + page);
        String paggingQuery = " LIMIT " + offset + ", " + Constants.ROW_PER_PAGE;
        String filter = advanceSearchFilter + paggingQuery;
        List<ReportByBankDetail> result = APIUtil.getReportByBankDetail(Constants.VPCC_API_LINK + "/transaction/reportByBank", advanceSearchFilter);
        return result;
    }
    public static int countTotalReportBankDetail(String advanceSearchFilter) {
        if (advanceSearchFilter == null || advanceSearchFilter.equals("")) advanceSearchFilter = " where 1=1";
        String filter = advanceSearchFilter;


        Integer result = APIUtil.countTotalList(Constants.VPCC_API_LINK + "/transaction/countTotalReportBank", filter);
        return result;
    }

}








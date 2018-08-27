package com.vn.osp.controller;

import com.vn.osp.common.global.Constants;
import com.vn.osp.common.util.*;
import com.vn.osp.context.CommonContext;
import com.vn.osp.modelview.*;
import com.vn.osp.service.APIUtil;
import com.vn.osp.service.QueryFactory;
import com.vn.osp.service.STPQueryFactory;
import com.vn.osp.task.BackupFromSTPCallable;
import com.vn.osp.util.StringUtils;
import net.sf.jett.transform.ExcelTransformer;
import org.apache.poi.ss.usermodel.Workbook;

import org.apache.poi.util.ArrayUtil;
import org.hibernate.metamodel.relational.Database;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
//

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by tranv on 12/27/2016.
 */
@Controller
@RequestMapping("/system")
public class SystemController extends BaseController {
    @RequestMapping(value = "/add-user-view", method = RequestMethod.GET)
    public ModelAndView addUserViewer(HttpServletRequest request) {
        if (!ValidationPool.checkRoleDetail(request, "02", Constants.AUTHORITY_THEM)) return new ModelAndView("/404");
        User user = new User();
        if (!org.apache.commons.lang3.StringUtils.isBlank(user.getRole())) {
            Role role = QueryFactory.getRoleByCode(user.getRole());
            user.setRole(role.getName());
        } else {
            user.setRole("");
        }
        user.setActive_flg(Long.valueOf(1));
        user.setActive_ccv(Long.valueOf(1));
        return new ModelAndView("/system/SM001", "user", user);
    }

    @RequestMapping(value = "/add-user", method = RequestMethod.POST)
    public ModelAndView addUser(User user, HttpServletRequest request, HttpServletResponse response) {
        if (!ValidationPool.checkRoleDetail(request, "02", Constants.AUTHORITY_THEM)) return new ModelAndView("/404");
        trimAllFieldOfObject(user);
        HttpSession session = request.getSession();
        User userUpdate = ((CommonContext) session.getAttribute(request.getSession().getId())).getUser();
        user.setOffice_id(Long.valueOf(0));
        if (!org.apache.commons.lang3.StringUtils.isBlank(user.getFirst_name())) {
            user.setFirst_name(user.getFirst_name());
        }
        if (!org.apache.commons.lang3.StringUtils.isBlank(user.getFamily_name())) {
            user.setFamily_name(user.getFamily_name());
        }

        user.setUpdate_user_id(userUpdate.getUserId());
        user.setUpdate_user_name(userUpdate.getEntry_user_name());
        user.setEntry_date_time(generateDateToString());
        user.setUpdate_date_time(generateDateToString());
        user.setActive_ccv(user.getActive_ccv() == null ? Long.valueOf(0) : 1);
        user.valiate();

        if (user.getSuccess() == false) return new ModelAndView("/system/SM001", "user", user);
        Long id = APIUtil.addUserAPI(Constants.VPCC_API_LINK + "/users/add-user", user.generateAddJson(userUpdate));
        if (id < 0) {
            user.setAccount_(SystemMessageProperties.getSystemProperty("v3_user_exits"));
            user.setSuccess(false);
            return new ModelAndView("/system/SM001", "user", user);
        }
        CommonContext commonContext = ((CommonContext) session.getAttribute(request.getSession().getId()));

        int x = QueryFactory.countTotalUserList("where role = 02 and active_ccv = 0");
        int y = QueryFactory.countTotalUserList("where active_ccv = 1");
        int z = x +y;
        String test ="";
        test = commonContext.getAuthentication_id()+";"+z+";"+y;
        Boolean xz = STPQueryFactory.synchronizeNotaryNumber(test);
        session.setAttribute(Constants.SESSION_ACTION, SystemMessageProperties.getSystemProperty("v3_user_success"));
        return new ModelAndView("redirect:/system/user-list");
    }

    public String generateDateToString() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date today = Calendar.getInstance().getTime();
        String reportDate = df.format(today);
        return reportDate;
    }

    @RequestMapping(value = "/user-list", method = RequestMethod.GET)
    public ModelAndView getUserList(UserList userList, HttpServletRequest request, HttpServletResponse response) {
        if (!ValidationPool.checkRoleDetail(request, "02", Constants.AUTHORITY_XEM)) return new ModelAndView("/404");
        BaseController.trimAllFieldOfObject(userList);
        List<Role> roleList = QueryFactory.listRole1();
        HttpSession session = request.getSession();
        UserList users = createUserList(userList);
        String action = (String) session.getAttribute(Constants.SESSION_ACTION);
        users.setAction_status(action);
        for (int j = 0; j < users.getUserList().size(); j++) {
            for (int i = 0; i < roleList.size(); i++) {
                if (users.getUserList().get(j).getRole().equals(roleList.get(i).getCode())) {
                    users.getUserList().get(j).setRole(roleList.get(i).getName());
                }
            }
        }
        session.removeAttribute(Constants.SESSION_ACTION);
        return new ModelAndView("/system/SM002", "users", users);
    }

    public UserList createUserList(UserList userList) {
        int userListNumber = 0;
        int userTotalPage = 0;
        int page = 1;

        String family_name = "";
        String first_name = "";
        String account = "";
        int active_flg = 0;

        if (userList != null) {
            page = userList.getPage();
            family_name = userList.getFamily_name();
            first_name = userList.getFirst_name();
            account = userList.getAccount();
            active_flg = userList.getActive_flg();
        }

        userList.setAccount(account);
        userList.setFamily_name(family_name);
        userList.setFirst_name(first_name);
        userList.setActive_flg(active_flg);

        String query = userList.getFilter();
        userListNumber = QueryFactory.countTotalUserList(query);
        userTotalPage = QueryFactory.countPage(userListNumber);
        userList.setUserTotalPage(userTotalPage);
        userList.setUserListNumber(userListNumber);
        if (page < 1) page = 1;
        if (page > userTotalPage) page = userTotalPage;
        userList.setPage(page);
        userList.setUserList(QueryFactory.getUserList(page, query));
        return userList;
    }

    @RequestMapping(value = "/user-update-view/{userId}", method = RequestMethod.GET)
    public ModelAndView updateUserViewer(@PathVariable("userId") final String userId, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        if (!ValidationPool.checkRoleDetail(request, "02", Constants.AUTHORITY_SUA)) return new ModelAndView("/404");
        User user = QueryFactory.getUserById(Long.valueOf(userId));
        if (user != null) {
            if (org.apache.commons.lang3.StringUtils.isBlank(user.getAccount()) == false) {
                return new ModelAndView("/system/SM003", "user", user);
            } else {
                redirectAttributes.addFlashAttribute("errorCode", "Tài khoản không tồn tại !");
                return new ModelAndView("redirect:/system/user-list");
            }
        } else {
            redirectAttributes.addFlashAttribute("errorCode", "Tài khoản không tồn tại !");
            return new ModelAndView("redirect:/system/user-list");
        }
    }

    @RequestMapping(value = "/update-user", method = RequestMethod.POST)
    public ModelAndView updateUser(User user, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
        if (!ValidationPool.checkRoleDetail(request, "02", Constants.AUTHORITY_SUA)) return new ModelAndView("/404");
        HttpSession session = request.getSession();
        user.userStrim();
        user.updateValiate();
        user.setActive_ccv(user.getActive_ccv() == null ? Long.valueOf(0) : 1);
        if (!org.apache.commons.lang3.StringUtils.isBlank(user.getNewPassword())) {
            if (PasswordValidator.verifyPassword(user.getNewPassword().trim()) == false) {
                user.setPassword_("Mật khẩu chỉ được sử dụng chữ hoa, chữ thường và chữ số, phải dài tối thiểu 6 kí tự và tối đa 50 kí tự, có ít nhất 1 kí tự số , 1 kí tự viết hoa và 1 kí tự viết thường ");
                return new ModelAndView("/system/SM003", "user", user);
            }
        } else {
            if (!org.apache.commons.lang3.StringUtils.isBlank(user.getReNewPassword())) {
                user.setPassword_("Giá trị Nhập lại mật khẩu phải trùng với giá trị Mật khẩu!");
                return new ModelAndView("/system/SM003", "user", user);
            }
        }
        User u = QueryFactory.getUserById(Long.valueOf(user.getUserId()));
        if (u != null) {
            user.setUpdate_user_id(u.getUserId());
            user.setUpdate_user_name(u.getEntry_user_name());
            user.setOffice_id(u.getOffice_id());
            if (!org.apache.commons.lang3.StringUtils.isBlank(user.getOldPassword())) {
                if (!Crypter.matches(u.getPassword(), user.getOldPassword())) {
                    user.setOldPassword_("Mật khẩu cũ không chính xác !");
                    return new ModelAndView("/system/SM003", "user", user);
                }
            }
            if (!org.apache.commons.lang3.StringUtils.isBlank(user.getNewPassword())) {
                String regex = "^[a-zA-Z0-9]+$";
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(user.getNewPassword());
                if (matcher.matches() == false) {
                    user.setPassword_("Mật khẩu không được chứa kí tự đặc biệt và khoảng trắng !");
                    return new ModelAndView("/system/SM003", "user", user);
                }
            }
            if (user.getSuccess() == false) return new ModelAndView("/system/SM003", "user", user);
            if (org.apache.commons.lang3.StringUtils.isBlank(u.getAccount()) == false) {
                QueryFactory.updateUser(user.generateUpdateJsons(user.getUserId(), u, ((CommonContext) session.getAttribute(session.getId())).getUser()));
                session.setAttribute(Constants.SESSION_ACTION, SystemMessageProperties.getSystemProperty("v3_user_update"));
                CommonContext commonContext = ((CommonContext) session.getAttribute(request.getSession().getId()));

                int x = QueryFactory.countTotalUserList("where role = 02 and active_ccv = 0");
                int y = QueryFactory.countTotalUserList("where active_ccv = 1");
                String test ="";
                int z = x +y;
                test = commonContext.getAuthentication_id()+";"+z+";"+y;
                Boolean xz = STPQueryFactory.synchronizeNotaryNumber(test);
                return new ModelAndView("redirect:/system/user-list");
            }
       /*     CommonContext commonContext = ((CommonContext) session.getAttribute(request.getSession().getId()));

            int x = QueryFactory.countTotalUserList("where role = 02 and active_ccv = 0");
            int y = QueryFactory.countTotalUserList("where active_ccv = 1");
            String test ="";
            int z = x +y;
            test = commonContext.getAuthentication_id()+";"+z+";"+y;
            Boolean xz = STPQueryFactory.synchronizeNotaryNumber(test);*/
            return new ModelAndView("redirect:/system/user-list");
        } else {
            redirectAttributes.addFlashAttribute("errorCode", "Tài khoản không tồn tại !");
            return new ModelAndView("redirect:/system/user-list");
        }

    }

    @RequestMapping(value = "/remove-user/{id}", method = RequestMethod.GET)
    public ModelAndView removeUser(@PathVariable("id") Long id, HttpServletRequest request, HttpServletResponse response) {
        if (!ValidationPool.checkRoleDetail(request, "02", Constants.AUTHORITY_XOA)) return new ModelAndView("/404");
        HttpSession session = request.getSession();
        Boolean result = QueryFactory.removeUser(id.toString());
        if (result) {
            session.setAttribute(Constants.SESSION_ACTION, SystemMessageProperties.getSystemProperty("v3_user_delete_ok"));
            return new ModelAndView("redirect:/system/user-list");
        } else {
            session.setAttribute(Constants.SESSION_ACTION, SystemMessageProperties.getSystemProperty("v3_user_delete_not"));
            return new ModelAndView("redirect:/system/user-list");
        }
    }

    @RequestMapping(value = "/grouprole-list", method = RequestMethod.GET)
    public ModelAndView getGroupRoleList(GroupRoleListForm groupRoleListForm, HttpServletRequest request, HttpServletResponse response) {
        if (!ValidationPool.checkRoleDetail(request, "03", Constants.AUTHORITY_XEM)) return new ModelAndView("/404");
        HttpSession session = request.getSession();
        ModelAndView view = new ModelAndView();

        int firstRecord = 1;
        String action = (String) session.getAttribute(Constants.SESSION_ACTION);
        groupRoleListForm.setAction_status(action);
        session.removeAttribute(Constants.SESSION_ACTION);

        int listNumber = 0;
        int totalPage = 0;
        int page = 1;
        String titleFilter = "";
        if (groupRoleListForm != null) {
            listNumber = groupRoleListForm.getListNumber();
            totalPage = groupRoleListForm.getTotalPage();
            switch (page = groupRoleListForm.getPage()) {
            }
            if (!org.apache.commons.lang3.StringUtils.isBlank(groupRoleListForm.getTitleFilter())) {
                titleFilter = groupRoleListForm.getTitleFilter().trim();
            }

        }

        listNumber = QueryFactory.countTotalGroupRole(titleFilter);
        totalPage = QueryFactory.countPage(listNumber);
        if (page < 1) page = 1;
        if (page > totalPage) page = totalPage;
        groupRoleListForm.setListNumber(listNumber);
        groupRoleListForm.setTotalPage(totalPage);
        groupRoleListForm.setPage(page);
        groupRoleListForm.setTitleFilter(titleFilter);
        groupRoleListForm.setGroupRoles(QueryFactory.getGroupRoleByPage(page, titleFilter));
        firstRecord = (page - 1) * 20 + 1;
        view.setViewName("/system/SM0011");
        view.addObject("firstRecord", firstRecord);
        view.addObject("groupRoleListForm", groupRoleListForm);
        return view;
    }

    @RequestMapping(value = "/create-grouprole-view", method = RequestMethod.GET)
    public ModelAndView createGroupRoleView(HttpServletRequest request) {
        if (!ValidationPool.checkRoleDetail(request, "03", Constants.AUTHORITY_THEM)) return new ModelAndView("/404");
        ArrayList<Authority> systemManagerList = QueryFactory.getAuthorityByType(Constants.AUTHORITY_TYPE_SYSTEM_MANAGER);
        ArrayList<Authority> functionList = QueryFactory.getAuthorityByType(Constants.AUTHORITY_TYPE_FUNCION);
        ArrayList<Authority> reportList = QueryFactory.getAuthorityByType(Constants.AUTHORITY_TYPE_REPORT);

        CreateGroupRoleForm createGroupRoleForm = new CreateGroupRoleForm();
        createGroupRoleForm.setSystemManagerList(systemManagerList);
        createGroupRoleForm.setFunctionList(functionList);
        createGroupRoleForm.setReportList(reportList);
        createGroupRoleForm.setActive_flg(Long.valueOf(1));

        return new ModelAndView("/system/SM0012", "createGroupRoleForm", createGroupRoleForm);
    }

    @RequestMapping(value = "/create-grouprole", method = RequestMethod.POST)
    public ModelAndView createGroupRole(CreateGroupRoleForm createGroupRoleForm, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirect) {
        if (!ValidationPool.checkRoleDetail(request, "03", Constants.AUTHORITY_THEM)) return new ModelAndView("/404");
        ModelAndView view = new ModelAndView();
        ArrayList<Authority> systemManagerList = QueryFactory.getAuthorityByType(Constants.AUTHORITY_TYPE_SYSTEM_MANAGER);
        ArrayList<Authority> functionList = QueryFactory.getAuthorityByType(Constants.AUTHORITY_TYPE_FUNCION);
        ArrayList<Authority> reportList = QueryFactory.getAuthorityByType(Constants.AUTHORITY_TYPE_REPORT);
        createGroupRoleForm.setSystemManagerList(systemManagerList);
        createGroupRoleForm.setFunctionList(functionList);
        createGroupRoleForm.setReportList(reportList);
        if (createGroupRoleForm != null) {
            if (org.apache.commons.lang3.StringUtils.isBlank(createGroupRoleForm.getCb01()) && org.apache.commons.lang3.StringUtils.isBlank(createGroupRoleForm.getCb02()) && org.apache.commons.lang3.StringUtils.isBlank(createGroupRoleForm.getCb03())) {
                createGroupRoleForm.setSuccess(false);
                view.setViewName("/system/SM0012");
                view.addObject("createGroupRoleForm", createGroupRoleForm);
                view.addObject("msg", "Thêm mới nhóm quyền không thành công, bạn nên cấp ít nhất một quyền cho người dùng này ");
                return view;
            }
        }
        HttpSession session = request.getSession();
        StringUtils.trimAllFieldOfObject(createGroupRoleForm);
//        createGroupRoleForm.setGrouprolename(StringUtils.removeSpecialCharactersNotSpace(createGroupRoleForm.getGrouprolename()));
//        createGroupRoleForm.setDescription(StringUtils.removeSpecialCharactersNotSpace(createGroupRoleForm.getDescription()));
        createGroupRoleForm.validate();
        if (createGroupRoleForm.isSuccess() == false) {
            view.setViewName("/system/SM0012");
            view.addObject("createGroupRoleForm", createGroupRoleForm);
            view.addObject("msg", "Thêm mới nhóm quyền không thành công !");
            return view;
        }
        Boolean check = QueryFactory.checkExitsGroupRole(createGroupRoleForm.generateUpdateJson(((CommonContext) session.getAttribute(request.getSession().getId())).getUser()));
        if (check) {
            redirect.addFlashAttribute("code", "error");
            redirect.addFlashAttribute("status", "Thêm nhóm quyền thất bại, tên nhóm quyền đã tồn tại !");
            return new ModelAndView("redirect:/system/grouprole-list");
        } else {
            Boolean result = QueryFactory.createGroupRole(createGroupRoleForm.generateAddJson(((CommonContext) session.getAttribute(request.getSession().getId())).getUser()));
            session.setAttribute(Constants.SESSION_ACTION, SystemMessageProperties.getSystemProperty("v3_grouprole_add_success"));
            if (result) {
                redirect.addFlashAttribute("code", "success");
                redirect.addFlashAttribute("status", "Thêm nhóm quyền thành công!");
                return new ModelAndView("redirect:/system/grouprole-list");
            } else {
                redirect.addFlashAttribute("code", "error");
                redirect.addFlashAttribute("status", "Thêm nhóm quyền thất bại!");
                return new ModelAndView("redirect:/system/grouprole-list");
            }
        }
    }

    @RequestMapping(value = "/update-grouprole", method = RequestMethod.POST)
    public ModelAndView updateGroupRole(CreateGroupRoleForm createGroupRoleForm, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirect) {
        if (!ValidationPool.checkRoleDetail(request, "03", Constants.AUTHORITY_SUA)) return new ModelAndView("/404");
        HttpSession session = request.getSession();
        ModelAndView view = new ModelAndView();
        StringUtils.trimAllFieldOfObject(createGroupRoleForm);
        createGroupRoleForm.validate();
        if (createGroupRoleForm.isSuccess() == false) {
            ArrayList<Authority> systemManagerList = QueryFactory.getAuthorityByType(Constants.AUTHORITY_TYPE_SYSTEM_MANAGER);
            ArrayList<Authority> functionList = QueryFactory.getAuthorityByType(Constants.AUTHORITY_TYPE_FUNCION);
            ArrayList<Authority> reportList = QueryFactory.getAuthorityByType(Constants.AUTHORITY_TYPE_REPORT);
            createGroupRoleForm.setSystemManagerList(systemManagerList);
            createGroupRoleForm.setFunctionList(functionList);
            createGroupRoleForm.setReportList(reportList);
            return new ModelAndView("/system/SM0013", "createGroupRoleForm", createGroupRoleForm);
        }

        GroupRole groupRole = QueryFactory.getGroupRole(createGroupRoleForm.getGroupRoleId());
        if (groupRole == null) {
            redirect.addFlashAttribute("code", "error");
            redirect.addFlashAttribute("status", "Nhóm quyền không tồn tại!");
            return new ModelAndView("redirect:/system/grouprole-list");
        } else {
            if (createGroupRoleForm.getGrouprolename().equals(groupRole.getGrouprolename())) {
                Boolean result = QueryFactory.updateGroupRole(createGroupRoleForm.generateUpdateJson(((CommonContext) session.getAttribute(request.getSession().getId())).getUser()));
                if (result) {
                    redirect.addFlashAttribute("code", "success");
                    redirect.addFlashAttribute("status", "Sửa thông tin nhóm quyền thành công.");
                    view.setViewName("redirect:/system/grouprole-list");
                    return view;
                } else {
                    view.addObject("code", "error");
                    view.addObject("status", "Sửa thông tin nhóm quyền thất bại!");
                    view.addObject("createGroupRoleForm", createGroupRoleForm);
                    view.setViewName("/system/SM0013");
                    return view;
                }
            } else {
                Boolean check = QueryFactory.checkExitsGroupRole(createGroupRoleForm.generateUpdateJson(((CommonContext) session.getAttribute(request.getSession().getId())).getUser()));
                if (check) {
                    createGroupRoleForm.setGrouprolename_("Tên nhóm quyền đã tồn tại !");
                    view.addObject("code", "error");
                    view.addObject("status", "Sửa thông tin không thành công!");
                    view.addObject("createGroupRoleForm", createGroupRoleForm);
                    view.setViewName("/system/SM0013");
                    return view;
                } else {
                    Boolean result = QueryFactory.updateGroupRole(createGroupRoleForm.generateUpdateJson(((CommonContext) session.getAttribute(request.getSession().getId())).getUser()));
                    if (result) {
                        redirect.addFlashAttribute("code", "success");
                        redirect.addFlashAttribute("status", "Sửa thông tin thành công.!");
                        view.setViewName("redirect:/system/grouprole-list");
                        return view;
                    } else {
                        view.addObject("code", "error");
                        view.addObject("status", "Sửa thông tin thất bại!");
                        view.addObject("createGroupRoleForm", createGroupRoleForm);
                        view.setViewName("/system/SM0013");
                        return view;
                    }
                }
            }
        }
    }

    @RequestMapping(value = "/delete-grouprole/{id}", method = RequestMethod.GET)
    public String deleteGroupRole(@PathVariable("id") Long id, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirect) {
        if (!ValidationPool.checkRoleDetail(request, "03", Constants.AUTHORITY_XOA)) return "redirect:/404";
        int numberUserAuthority = QueryFactory.countUserAuthorityByGroupId(id);
        if (numberUserAuthority > 0) {
            redirect.addFlashAttribute("code", "error");
            redirect.addFlashAttribute("status", "Đang có cán bộ sử dụng Nhóm quyền này. Xóa Nhóm quyền không thành công!");
            return "redirect:/system/grouprole-list";
        } else {
            Boolean deleteGroupRoleAuthority = QueryFactory.deleteGroupRoleAuthority(id);
            HttpSession session = request.getSession();
            session.setAttribute(Constants.SESSION_ACTION, SystemMessageProperties.getSystemProperty("v3_grouprole_delete_success"));
            if (deleteGroupRoleAuthority) {
                boolean check = QueryFactory.deleteGroupRole(id);
                if (check) {
                    redirect.addFlashAttribute("code", "success");
                    redirect.addFlashAttribute("status", "Xóa Nhóm quyền thành công.");
                } else {
                    redirect.addFlashAttribute("code", "error");
                    redirect.addFlashAttribute("status", "Đang có cán bộ sử dụng Nhóm quyền này. Xóa Nhóm quyền không thành công!");
                }
            }
        }
        return "redirect:/system/grouprole-list";
    }

    @RequestMapping(value = "/update-grouprole-view/{id}", method = RequestMethod.GET)
    public ModelAndView updateGroupRoleView(@PathVariable("id") long id, HttpServletRequest request, RedirectAttributes redirect) {
        if (!ValidationPool.checkRoleDetail(request, "03", Constants.AUTHORITY_XEM)) return new ModelAndView("/404");
        CreateGroupRoleForm createGroupRoleForm = new CreateGroupRoleForm();

        ArrayList<Authority> systemManagerList = QueryFactory.getAuthorityByType(Constants.AUTHORITY_TYPE_SYSTEM_MANAGER);
        ArrayList<Authority> functionList = QueryFactory.getAuthorityByType(Constants.AUTHORITY_TYPE_FUNCION);
        ArrayList<Authority> reportList = QueryFactory.getAuthorityByType(Constants.AUTHORITY_TYPE_REPORT);

        createGroupRoleForm.setSystemManagerList(systemManagerList);
        createGroupRoleForm.setFunctionList(functionList);
        createGroupRoleForm.setReportList(reportList);

        //lay du lieu grouprole
        GroupRole groupRole = QueryFactory.getGroupRole(id);
        if (groupRole == null) {
            redirect.addFlashAttribute("code", "error");
            redirect.addFlashAttribute("status", "Nhóm quyền không tồn tại!");
            return new ModelAndView("redirect:/system/grouprole-list");
        }
        createGroupRoleForm.setGroupRoleId(groupRole.getGroupRoleId());
        createGroupRoleForm.setGrouprolename(groupRole.getGrouprolename());
        createGroupRoleForm.setDescription(groupRole.getDescription());

        createGroupRoleForm.setActive_flg(groupRole.getActive_flg());
        createGroupRoleForm.setEntry_user_id(groupRole.getEntry_user_id());
        createGroupRoleForm.setEntry_user_name(groupRole.getEntry_user_name());
        createGroupRoleForm.setEntry_date_time(groupRole.getEntry_date_time());

        createGroupRoleForm.setUpdate_user_id(groupRole.getUpdate_user_id());
        createGroupRoleForm.setUpdate_user_name(groupRole.getUpdate_user_name());
        createGroupRoleForm.setUpdate_date_time(groupRole.getUpdate_date_time());

        //lay giu lieu grouprole_authority
        List<GrouproleAuthority> grouproleAuthorities = QueryFactory.getGroupRoleAuthorityByFilter(" where grouprole_id=" + id);
        String cb01 = "";
        String cb02 = "";
        String cb03 = "";
        for (int i = 0; i < grouproleAuthorities.size(); i++) {
            GrouproleAuthority grouproleAuthority = grouproleAuthorities.get(i);
            int value = grouproleAuthority.getValue();
            String authority_code = grouproleAuthority.getAuthority_code();
            List<Authority> authorities = QueryFactory.getAuthorityByFilter(" where code like '" + authority_code + "'");
            if (authorities == null || authorities.size() == 0) continue;
            long type = authorities.get(0).getType();
            if (type == 1) {
                cb01 = generateAuthoryCode(new StringBuilder(cb01), authority_code, value, (int) type);
            }
            if (type == 2) {
                cb02 = generateAuthoryCode(new StringBuilder(cb02), authority_code, value, (int) type);
            }
            if (type == 3) {
                cb03 = generateAuthoryCode(new StringBuilder(cb03), authority_code, value, (int) type);
            }
        }

        createGroupRoleForm.setCb01(cb01);
        createGroupRoleForm.setCb02(cb02);
        createGroupRoleForm.setCb03(cb03);
        return new ModelAndView("/system/SM0013", "createGroupRoleForm", createGroupRoleForm);
    }

    /*
    * @author vietmanh
    * @date 3/14/2017
    * Ham generate chuoi authority code de dua len giao dien
    * */
    public String generateAuthoryCode(StringBuilder cb, String authority_code, int value, int type) {
        int temp64 = value & 64;
        int temp32 = value & 32;
        int temp16 = value & 16;
        int temp8 = value & 8;
        int temp4 = value & 4;
        int temp2 = value & 2;
        int temp1 = value & 1;
        int item = 0;
        if (temp64 > 0) {
            cb.append(authority_code + "_64,");
            item++;
        }
        if (temp32 > 0) {
            cb.append(authority_code + "_32,");
            item++;
        }
        if (temp16 > 0) {
            cb.append(authority_code + "_16,");
            item++;
        }
        if (temp8 > 0) {
            cb.append(authority_code + "_8,");
            item++;
        }
        if (temp4 > 0) {
            cb.append(authority_code + "_4,");
            item++;
        }
        if (temp2 > 0) {
            cb.append(authority_code + "_2,");
            item++;
        }
        if (temp1 > 0) {
            cb.append(authority_code + "_1,");
            item++;
        }
        switch (type) {
            case 1:
                if (item == 7) cb.append(authority_code + "_0,");
                break;
            case 2:
                if (item == 6) cb.append(authority_code + "_0,");
                break;
            case 3:
                if (item == 3) cb.append(authority_code + "_0,");
                break;
            default:
                break;
        }
        return cb.toString();
    }

    @RequestMapping(value = "/user-permission-view/{userId}", method = RequestMethod.GET)
    public ModelAndView userPermissionViewer(@PathVariable("userId") Long userId, HttpServletRequest request) {
        // load thong tin user
        User user = QueryFactory.getUserById(Long.valueOf(userId));
        if (!org.apache.commons.lang3.StringUtils.isBlank(user.getRole())) {
            Role role = QueryFactory.getRoleByCode(user.getRole());
            user.setRole(role.getName());
        } else {
            user.setRole("");
        }
        //load tat ca nhom quyen
        List<GroupRole> groupRoles = QueryFactory.getActiveGroupRole("");
        UserPermissionForm userPermissionForm = new UserPermissionForm();
        userPermissionForm.setUser(user);
        userPermissionForm.setGroupRoles(groupRoles);
        //load nhom quyen cua usser
        List<UserGroupRole> userGroupRoles = QueryFactory.getUserGroupRoleList(user.getUserId());
        userPermissionForm.setUserGroupRoles(userGroupRoles);
        // dua nhom quyen cua user len giao dien
        userPermissionForm.mappingUserRole();

        return new ModelAndView("/system/SM004", "userPermissionForm", userPermissionForm);
    }

    @RequestMapping(value = "/user-permission", method = RequestMethod.POST)
    public String userPermission(UserPermisson userPermisson, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        ResultRequest resultRequest = null;
        String selectAll = request.getParameter("totalPermission");
        if (org.apache.commons.lang3.StringUtils.isBlank(selectAll) && org.apache.commons.lang3.StringUtils.isBlank(userPermisson.getCb01())) {
            userPermisson.setCb01("");
        }
        Boolean result = QueryFactory.permissionUser(userPermisson.generateJson());
        //if false
        if (result) {
            redirectAttributes.addFlashAttribute("successCode", "Phân quyền thành công .");
        } else {
            redirectAttributes.addFlashAttribute("errorCode", "Phân quyền thất bại !");
        }
        return "redirect:/system/user-list";
    }

    /**
     * @author minhbq
     * @date 3/19/2017
     * Lich su truy cap he thong
     */
    @RequestMapping(value = "/access-history", method = {RequestMethod.GET})
    public ModelAndView getAccessHistory(AccessHistoryList accessHistoryList, HttpServletRequest request) {
        if (!ValidationPool.checkRoleDetail(request, "06", Constants.AUTHORITY_XEM)) return new ModelAndView("/404");
        HttpSession session = request.getSession();
        ModelAndView view = new ModelAndView();
        session.removeAttribute(AccessHistoryList.SESSION_KEY);

        if (org.apache.commons.lang3.StringUtils.isBlank(accessHistoryList.getTimeType())) {
            accessHistoryList.setTimeType("03");
        }
        if (ValidateUtil.validateDatefrom(accessHistoryList.getFromDate())) {
            ValidateUtil.validate_msg_from_date = "";
        }
        if (ValidateUtil.validateDateto(accessHistoryList.getToDate())) {
            ValidateUtil.validate_msg_to_date = "";
        }
        if (!org.apache.commons.lang3.StringUtils.isBlank(accessHistoryList.getTimeType())) {
            if (accessHistoryList.getTimeType().equals("05")) {
                if (!org.apache.commons.lang3.StringUtils.isBlank(accessHistoryList.getFromDate())) {
                    if (!org.apache.commons.lang3.StringUtils.isBlank(accessHistoryList.getToDate())) {
                        Date fromDate = TimeUtil.stringToDate(accessHistoryList.getFromDate());
                        Date toDate = TimeUtil.stringToDate(accessHistoryList.getToDate());
                        if (fromDate.getTime() > toDate.getTime()) {
                            view.setViewName("/contract/CTR010");
                            view.addObject("accessHistoryList", accessHistoryList);
                            ValidateUtil.validate_msg_from_date = "Từ ngày không được lớn hơn Đến ngày !";
                            view.addObject("validate_msg_from_date", ValidateUtil.validate_msg_from_date);
                            return view;
                        }
                        if (ValidateUtil.validateDatefrom(accessHistoryList.getFromDate()) == false || ValidateUtil.validateDateto(accessHistoryList.getToDate()) == false) {
                            view.setViewName("/contract/CTR010");
                            view.addObject("accessHistoryList", accessHistoryList);
                            view.addObject("validate_msg_from_date", ValidateUtil.validate_msg_from_date);
                            view.addObject("validate_msg_to_date", ValidateUtil.validate_msg_to_date);
                            return view;
                        }
                    } else {
                        Integer year = (Integer) Calendar.getInstance().get(Calendar.YEAR);
                        Integer month = (Integer) Calendar.getInstance().get(Calendar.MONTH) + 1;
                        Integer date = (Integer) Calendar.getInstance().get(Calendar.DATE);
                        String toDate = date.toString() + "/" + month.toString() + "/" + year.toString();
                        accessHistoryList.setToDate(toDate);
                    }
                } else {
                    view.setViewName("/contract/CTR010");
                    view.addObject("validate_msg_from_date", "Trường từ ngày không được để trống !");
                    return view;
                }
            }
        }
        List<User> users = QueryFactory.getUser(null);
        accessHistoryList.setUserList(users);
        accessHistoryList.createFromToDate();

        int accessListNumber = 1;
        int accessTotalPage = 1;
        int page = 1;

        if (accessHistoryList != null) {
            accessListNumber = accessHistoryList.getTotal();
            accessTotalPage = accessHistoryList.getTotalPage();
            page = accessHistoryList.getPage();

        }
        Long userId = accessHistoryList.getUserId();
        String executeDateTime = accessHistoryList.getExecute_date_time();
        String clientInfo = accessHistoryList.getClient_info();
        Integer accessType = accessHistoryList.getAccess_type();


        if (accessTotalPage < 1) accessTotalPage = 1;
        if (page < 1) page = 1;
        if (page > accessTotalPage) page = accessTotalPage;

        accessHistoryList.setPage(page);

        String query = accessHistoryList.getOrderString();

        accessHistoryList.setAccessHistories(QueryFactory.getAccesHistory(page, query));
        accessListNumber = QueryFactory.countTotalAccessHistory(page, query);
        accessTotalPage = QueryFactory.countPage(accessListNumber);

        accessHistoryList.setTotal(accessListNumber);
        accessHistoryList.setTotalPage(accessTotalPage);

        accessHistoryList.setUserId(userId);
        accessHistoryList.setExecute_date_time(executeDateTime);
        accessHistoryList.setClient_info(clientInfo);
        accessHistoryList.setAccess_type(accessType);

        session.setAttribute(AccessHistoryList.SESSION_KEY, accessHistoryList);

        view.setViewName("/contract/CTR010");
        view.addObject("accessHistoryList", accessHistoryList);
        view.addObject("validate_msg_from_date", ValidateUtil.validate_msg_from_date);
        view.addObject("validate_msg_to_date", ValidateUtil.validate_msg_to_date);
        return view;
    }

    @RequestMapping(value = "/export-access-history", method = RequestMethod.GET)
    public void exportAccessHistory(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession session = request.getSession();
            AccessHistoryList accessHistoryList = (AccessHistoryList) session.getAttribute(AccessHistoryList.SESSION_KEY);
            List<AccessHistory> accessHistories = QueryFactory.getAllAccessHistory(accessHistoryList.getOrderString());
            Map<String, Object> beans = new HashMap<String, Object>();
            beans.put("accesshistory", accessHistories);
            beans.put("login", SystemMessageProperties.getSystemProperty("v3_access_login"));
            beans.put("logout", SystemMessageProperties.getSystemProperty("v3_access_logout"));
            Date date = new Date(); // your date
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH);
            int day = cal.get(Calendar.DAY_OF_MONTH);

            beans.put("year", year);
            beans.put("month", month);
            beans.put("day", day);
            String realPathOfFolder = request.getServletContext().getRealPath(SystemProperties.getProperty("template_path"));
            InputStream fileIn = new BufferedInputStream(new FileInputStream(realPathOfFolder + "LichSuTruyCapHeThong.xls"));


            ExcelTransformer transformer = new ExcelTransformer();
            Workbook workbook = transformer.transform(fileIn, beans);

            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment; filename=" + "LichSuTruyCapHeThong.xls");
            ServletOutputStream out = response.getOutputStream();
            workbook.write(out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/backup-init", method = RequestMethod.POST)
    public ModelAndView backupInit(ConfigBackupDatabaseForm cfBackupForm, HttpServletRequest request, HttpServletResponse response) {
        if (cfBackupForm.getPathBackUp() != null && cfBackupForm.getPathBackUp().length() > 0) {
            if (cfBackupForm.getPathBackUp().trim().indexOf(" ") > 0) {
                cfBackupForm.setSuccess(false);
                cfBackupForm.setAction_status(SystemMessageProperties.getSystemProperty("err_cfg_backup_filepath"));
                return new ModelAndView("/system/SM0014", "cfBackup", cfBackupForm);
            }
        } else {
            cfBackupForm.setSuccess(false);
            cfBackupForm.setAction_status(SystemMessageProperties.getSystemProperty("err_cfg_backup_not_filepath"));
            return new ModelAndView("/system/SM0014", "cfBackup", cfBackupForm);
        }
        String dateBackUp = String.valueOf(cfBackupForm.isMon()) + " "
                + String.valueOf(cfBackupForm.isTue()) + " "
                + String.valueOf(cfBackupForm.isWed()) + " "
                + String.valueOf(cfBackupForm.isThu()) + " "
                + String.valueOf(cfBackupForm.isFri()) + " "
                + String.valueOf(cfBackupForm.isSat()) + " "
                + String.valueOf(cfBackupForm.isSun());
        XmlHandler.checkExitsAndWriteFile("<?xml version = \"1.0\" encoding = \"UTF-8\" standalone = \"yes\"?>\n" +
                "<data>\n" +
                "    <Folder>" + EditString.convertToFilePath(cfBackupForm.getPathBackUp().replaceAll("\\\\", "/")) + "</Folder>\n" +
                "    <DatesBackup>" + dateBackUp + "</DatesBackup>\n" +
                "    <TimeBackup>" + cfBackupForm.getBackupTime() + "</TimeBackup>\n" +
                "    <Emails>" + cfBackupForm.getEmail().trim() + "</Emails>\n" +
                "    <StatusBackup>" + "true" + "</StatusBackup>\n" +
                "    <LastBackUp>" + new SimpleDateFormat("dd-MM-yyyy").format(new Date()) + "</LastBackUp>\n" +
                "    <CheckBackUp>" + "false" + "</CheckBackUp>\n" +
                "</data>");
        cfBackupForm.setSuccess(true);
        if (!cfBackupForm.isCheckBackup())
            cfBackupForm.setAction_status(SystemMessageProperties.getSystemProperty("v3_backup_stop"));
        else cfBackupForm.setAction_status(SystemMessageProperties.getSystemProperty("msg_save_backup_succes"));
        return new ModelAndView("/system/SM0016", "cfBackup", cfBackupForm);
    }

    @RequestMapping(value = "/backup-init-update", method = RequestMethod.POST)
    public ModelAndView backupInitUpdate(ConfigBackupDatabaseForm cfBackupForm, HttpServletRequest request, HttpServletResponse response) {
        if (cfBackupForm.getPathBackUp() != null && cfBackupForm.getPathBackUp().length() > 0) {
            if (cfBackupForm.getPathBackUp().trim().indexOf(" ") > 0) {
                cfBackupForm.setSuccess(false);
                cfBackupForm.setAction_status(SystemMessageProperties.getSystemProperty("err_cfg_backup_filepath"));
                return new ModelAndView("/system/SM0014", "cfBackup", cfBackupForm);
            }
        } else {
            cfBackupForm.setSuccess(false);
            cfBackupForm.setAction_status(SystemMessageProperties.getSystemProperty("err_cfg_backup_not_filepath"));
            return new ModelAndView("/system/SM0014", "cfBackup", cfBackupForm);
        }
        //if (org.apache.commons.lang3.StringUtils.isBlank(cfBackupForm.getEmail()) == false) {
        //SystemProperties.saveProperties(Constants.CONFIG_EMAIL_BACKUP, cfBackupForm.getEmail().trim());
        //}
        //SystemProperties.saveProperties(Constants.CHECK_BACKUP_DATABASE, String.valueOf(cfBackupForm.isCheckBackup()));
        //SystemProperties.saveProperties(Constants.CONFIG_TIME_BACKUP, cfBackupForm.getBackupTime());

        String dateBackUp = String.valueOf(cfBackupForm.isMon()) + " "
                + String.valueOf(cfBackupForm.isTue()) + " "
                + String.valueOf(cfBackupForm.isWed()) + " "
                + String.valueOf(cfBackupForm.isThu()) + " "
                + String.valueOf(cfBackupForm.isFri()) + " "
                + String.valueOf(cfBackupForm.isSat()) + " "
                + String.valueOf(cfBackupForm.isSun());
        //SystemProperties.saveProperties(Constants.CONFIG_DATE_BACKUP, dateBackUp);
        XmlHandler.checkExitsAndWriteFile("<?xml version = \"1.0\" encoding = \"UTF-8\" standalone = \"yes\"?>\n" +
                "<data>\n" +
                "    <Folder>" + EditString.convertToFilePath(cfBackupForm.getPathBackUp().replaceAll("\\\\", "/")) + "</Folder>\n" +
                "    <DatesBackup>" + dateBackUp + "</DatesBackup>\n" +
                "    <TimeBackup>" + cfBackupForm.getBackupTime() + "</TimeBackup>\n" +
                "    <Emails>" + cfBackupForm.getEmail().trim() + "</Emails>\n" +
                "    <StatusBackup>" + String.valueOf(cfBackupForm.isCheckBackup()) + "</StatusBackup>\n" +
                "    <LastBackUp>" + new SimpleDateFormat("dd-MM-yyyy").format(new Date()) + "</LastBackUp>\n" +
                "    <CheckBackUp>" + "false" + "</CheckBackUp>\n" +
                "</data>");
        cfBackupForm.setSuccess(true);
        if (!cfBackupForm.isCheckBackup())
            cfBackupForm.setAction_status(SystemMessageProperties.getSystemProperty("v3_backup_stop"));
        else cfBackupForm.setAction_status(SystemMessageProperties.getSystemProperty("msg_save_backup_succes"));
        if (cfBackupForm.isCheckBackup() == true) return new ModelAndView("/system/SM0016", "cfBackup", cfBackupForm);
        else return new ModelAndView("/system/SM0017", "cfBackup", cfBackupForm);
        //return new ModelAndView("redirect:/system/backup-view");
    }

    @RequestMapping(value = "/backup-init-view", method = RequestMethod.GET)
    public ModelAndView backupView(HttpServletRequest request, HttpServletResponse response) {
        if (!ValidationPool.checkRoleDetail(request, "07", Constants.AUTHORITY_SUA)) return new ModelAndView("/404");
        ConfigBackupDatabaseForm cfBackup = new ConfigBackupDatabaseForm();
        if (FileUtil.checkFileExits("backup-config.xml", SystemProperties.getProperty("time_backup"))) {
            cfBackup.setPathBackUp(XmlHandler.getValueNode("Folder"));
            if (XmlHandler.getValueNode("StatusBackup").equals("true")) {
                cfBackup.setCheckBackup(true);
            } else {
                cfBackup.setCheckBackup(false);
            }
            cfBackup.setBackupTime(XmlHandler.getValueNode("TimeBackup"));
            cfBackup.setEmail(XmlHandler.getValueNode("Emails"));
            List<Boolean> listDatebackup = null;
            if (org.apache.commons.lang3.StringUtils.isBlank(XmlHandler.getValueNode("DatesBackup"))) {
                cfBackup.setMon(false);
                cfBackup.setTue(false);
                cfBackup.setWed(false);
                cfBackup.setThu(false);
                cfBackup.setFri(false);
                cfBackup.setSat(false);
                cfBackup.setSun(false);
            } else {
                listDatebackup = EditString.parseListDateBackup1(XmlHandler.getValueNode("DatesBackup"));
                cfBackup.setMon(listDatebackup.get(0));
                cfBackup.setTue(listDatebackup.get(1));
                cfBackup.setWed(listDatebackup.get(2));
                cfBackup.setThu(listDatebackup.get(3));
                cfBackup.setFri(listDatebackup.get(4));
                cfBackup.setSat(listDatebackup.get(5));
                cfBackup.setSun(listDatebackup.get(6));
            }
        } else {
            if (cfBackup == null) cfBackup = new ConfigBackupDatabaseForm();
            cfBackup.setPathBackUp(SystemProperties.getProperty(Constants.CONFIG_BACKUP_DATABASE_FOLDER));
            cfBackup.setCheckBackup(Boolean.valueOf(SystemProperties.getProperty(Constants.CHECK_BACKUP_DATABASE)));
            cfBackup.setBackupTime(SystemProperties.getProperty(Constants.CONFIG_TIME_BACKUP));
            cfBackup.setEmail(SystemProperties.getProperty(Constants.CONFIG_EMAIL_BACKUP));
            List<Boolean> listDatebackup = null;
            listDatebackup = EditString.parseListDateBackup1(SystemProperties.getProperty(Constants.CONFIG_DATE_BACKUP));
            cfBackup.setMon(listDatebackup.get(0));
            cfBackup.setTue(listDatebackup.get(1));
            cfBackup.setWed(listDatebackup.get(2));
            cfBackup.setThu(listDatebackup.get(3));
            cfBackup.setFri(listDatebackup.get(4));
            cfBackup.setSat(listDatebackup.get(5));
            cfBackup.setSun(listDatebackup.get(6));
        }
        return new ModelAndView("/system/SM0014", "cfBackup", cfBackup);
    }

    @RequestMapping(value = "/backup-update-view", method = RequestMethod.GET)
    public ModelAndView backupUpdateView(HttpServletRequest request, HttpServletResponse response) {
        if (!ValidationPool.checkRoleDetail(request, "07", Constants.AUTHORITY_SUA)) return new ModelAndView("/404");
        ConfigBackupDatabaseForm cfBackup = new ConfigBackupDatabaseForm();
        if (FileUtil.checkFileExits("backup-config.xml", SystemProperties.getProperty("system_backup_folder"))) {
            cfBackup.setPathBackUp(XmlHandler.getValueNode("Folder"));
            if (XmlHandler.getValueNode("StatusBackup").equals("true")) {
                cfBackup.setCheckBackup(true);
            } else {
                cfBackup.setCheckBackup(false);
            }
            cfBackup.setBackupTime(XmlHandler.getValueNode("TimeBackup"));
            cfBackup.setEmail(XmlHandler.getValueNode("Emails"));
            List<Boolean> listDatebackup = null;
            if (org.apache.commons.lang3.StringUtils.isBlank(XmlHandler.getValueNode("DatesBackup"))) {
                cfBackup.setMon(false);
                cfBackup.setTue(false);
                cfBackup.setWed(false);
                cfBackup.setThu(false);
                cfBackup.setFri(false);
                cfBackup.setSat(false);
                cfBackup.setSun(false);
            } else {
                listDatebackup = EditString.parseListDateBackup1(XmlHandler.getValueNode("DatesBackup"));
                cfBackup.setMon(listDatebackup.get(0));
                cfBackup.setTue(listDatebackup.get(1));
                cfBackup.setWed(listDatebackup.get(2));
                cfBackup.setThu(listDatebackup.get(3));
                cfBackup.setFri(listDatebackup.get(4));
                cfBackup.setSat(listDatebackup.get(5));
                cfBackup.setSun(listDatebackup.get(6));
            }
        } else {
            if (cfBackup == null) cfBackup = new ConfigBackupDatabaseForm();
            cfBackup.setPathBackUp(SystemProperties.getProperty(Constants.CONFIG_BACKUP_DATABASE_FOLDER));
            cfBackup.setCheckBackup(Boolean.valueOf(SystemProperties.getProperty(Constants.CHECK_BACKUP_DATABASE)));
            cfBackup.setBackupTime(SystemProperties.getProperty(Constants.CONFIG_TIME_BACKUP));
            cfBackup.setEmail(SystemProperties.getProperty(Constants.CONFIG_EMAIL_BACKUP));
            List<Boolean> listDatebackup = null;
            listDatebackup = EditString.parseListDateBackup1(SystemProperties.getProperty(Constants.CONFIG_DATE_BACKUP));
            cfBackup.setMon(listDatebackup.get(0));
            cfBackup.setTue(listDatebackup.get(1));
            cfBackup.setWed(listDatebackup.get(2));
            cfBackup.setThu(listDatebackup.get(3));
            cfBackup.setFri(listDatebackup.get(4));
            cfBackup.setSat(listDatebackup.get(5));
            cfBackup.setSun(listDatebackup.get(6));
        }
        return new ModelAndView("/system/SM0015", "cfBackup", cfBackup);
    }

    @RequestMapping(value = "/backup-view", method = RequestMethod.GET)
    public ModelAndView backupconfig(HttpServletRequest request, HttpServletResponse response) {
        if (!ValidationPool.checkRoleDetail(request, "07", Constants.AUTHORITY_XEM)) return new ModelAndView("/404");
        HttpSession session = request.getSession();
        ConfigBackupDatabaseForm cfBackup = new ConfigBackupDatabaseForm();
        if (FileUtil.checkFileExits("backup-config.xml", SystemProperties.getProperty("system_backup_folder"))) {
            cfBackup.setPathBackUp(XmlHandler.getValueNode("Folder"));
            if (XmlHandler.getValueNode("StatusBackup").equals("true")) {
                cfBackup.setCheckBackup(true);
            } else {
                cfBackup.setCheckBackup(false);
            }
            cfBackup.setBackupTime(XmlHandler.getValueNode("TimeBackup"));
            cfBackup.setEmail(XmlHandler.getValueNode("Emails"));
            List<Boolean> listDatebackup = null;
            if (org.apache.commons.lang3.StringUtils.isBlank(XmlHandler.getValueNode("DatesBackup"))) {
                cfBackup.setMon(false);
                cfBackup.setTue(false);
                cfBackup.setWed(false);
                cfBackup.setThu(false);
                cfBackup.setFri(false);
                cfBackup.setSat(false);
                cfBackup.setSun(false);
            } else {
                listDatebackup = EditString.parseListDateBackup1(XmlHandler.getValueNode("DatesBackup"));
                cfBackup.setMon(listDatebackup.get(0));
                cfBackup.setTue(listDatebackup.get(1));
                cfBackup.setWed(listDatebackup.get(2));
                cfBackup.setThu(listDatebackup.get(3));
                cfBackup.setFri(listDatebackup.get(4));
                cfBackup.setSat(listDatebackup.get(5));
                cfBackup.setSun(listDatebackup.get(6));
            }
        } else {
            if (cfBackup == null) cfBackup = new ConfigBackupDatabaseForm();
            session.removeAttribute(ConfigBackupDatabaseForm.SESSON_KEY);
            cfBackup.setPathBackUp(SystemProperties.getProperty(Constants.CONFIG_BACKUP_DATABASE_FOLDER));
            cfBackup.setCheckBackup(Boolean.valueOf(SystemProperties.getProperty(Constants.CHECK_BACKUP_DATABASE)));
            cfBackup.setBackupTime(SystemProperties.getProperty(Constants.CONFIG_TIME_BACKUP));
            cfBackup.setEmail(SystemProperties.getProperty(Constants.CONFIG_EMAIL_BACKUP));
            List<Boolean> listDatebackup = null;
            listDatebackup = EditString.parseListDateBackup1(SystemProperties.getProperty(Constants.CONFIG_DATE_BACKUP));
            cfBackup.setMon(listDatebackup.get(0));
            cfBackup.setTue(listDatebackup.get(1));
            cfBackup.setWed(listDatebackup.get(2));
            cfBackup.setThu(listDatebackup.get(3));
            cfBackup.setFri(listDatebackup.get(4));
            cfBackup.setSat(listDatebackup.get(5));
            cfBackup.setSun(listDatebackup.get(6));
        }
        if (cfBackup.isCheckBackup() == true) return new ModelAndView("/system/SM0016", "cfBackup", cfBackup);
        else return new ModelAndView("/system/SM0017", "cfBackup", cfBackup);
    }

    @RequestMapping(value = "/backup-now", method = RequestMethod.GET)
    public ModelAndView backupNow(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
        if (!ValidationPool.checkRoleDetail(request, "07", Constants.AUTHORITY_SUA)) return new ModelAndView("/404");
        ConfigBackupDatabaseForm configBackupDatabaseForm = new ConfigBackupDatabaseForm();
        HttpSession session = request.getSession();

        File file = new File(SystemProperties.getProperty(Constants.CONFIG_BACKUP_DATABASE_FOLDER));
        if (!file.exists()) {
            file.mkdirs();
        }
        String database = SystemProperties.getProperty("system_backup_database");
        String user = SystemProperties.getProperty("system_backup_user");
        String pass = SystemProperties.getProperty("system_backup_pass");
        String host = SystemProperties.getProperty("system_backup_host");
        String port = SystemProperties.getProperty("system_backup_port");
        Calendar cal = Calendar.getInstance();
        String fileBackUpName = String.valueOf(cal.get(Calendar.HOUR_OF_DAY)) + "h" + String.valueOf(cal.get(Calendar.MINUTE)) + "p_" + String.valueOf(cal.get(Calendar.DATE)) + "-" + String.valueOf(cal.get(Calendar.MONDAY) + 1) + "-" + String.valueOf(cal.get(Calendar.YEAR)) + ".sql";
        String fileBackupPath = SystemProperties.getProperty(Constants.CONFIG_BACKUP_DATABASE_FOLDER);
        String[] cmd = new String[10];
        int i = 0;
        cmd[i++] = SystemProperties.getProperty(Constants.CONFIG_MYSQL_DUMP_FOLDER).substring(0, 2);
        cmd[i++] = "cd \"" + SystemProperties.getProperty(Constants.CONFIG_MYSQL_DUMP_FOLDER) + "\"";
        cmd[i++] = "mysqldump -u" + user + " -p" + pass + " -h" + host + " -P" + port + " " + database + " -r \"" + fileBackupPath + fileBackUpName + "\"";

        Runtime c = Runtime.getRuntime();
        String filebackup = Constants.FILE_NAME_BACKUP;
        if (SystemProperties.getProperty("system_backup_os").equals("1")) {
            filebackup = "backupnow.sh";
        }
        createFileBackUpOrRetore(cmd, filebackup, i);
        String cmdStr = "cmd /c start " + SystemProperties.getProperty(Constants.CONFIG_BACKUP_DATABASE_FOLDER) + Constants.FILE_NAME_BACKUP;
        try {
            Process pro;
            if (SystemProperties.getProperty("system_backup_os").equals("0")) {
                pro = c.exec(cmdStr, null);
            } else {
                pro = c.exec(SystemProperties.getProperty("system_backup_database_folder") + "backupnow.sh",
                        null, new File(SystemProperties.getProperty("system_backup_database_folder")));
            }
            /*Process pro = c.exec(cmdStr, null);*/
            if (pro.waitFor() == 0) {
                File f = new File(fileBackupPath + fileBackUpName);
                if (file.exists()) {
                    redirectAttributes.addFlashAttribute("successCode", SystemMessageProperties.getSystemProperty("msg_backup_succes"));
                    String[] emails = XmlHandler.getValueNode("Emails").replaceAll("\\s+", "").split(",");
                    String content = "<h3 style='color:black'>Dữ liệu đã sao lưu thành công:</h3>" +
                            "<div>• Thư mục lưu trữ: " + SystemProperties.getProperty("system_backup_database_folder") + "<div>" +
                            "<div>• Tên file: " + fileBackUpName + "<div>" +
                            "<div>• Thời gian lưu trữ: " + new Date().toString() + "<div>";
                    SendMailUtil.sendGmail(emails, "[Uchi] Sao Lưu Dữ Liệu Thành Công", content);
                } else {
                    redirectAttributes.addFlashAttribute("errorCode", SystemMessageProperties.getSystemProperty("err_backup_database"));
                    String content = "<h3 style='color:black'>Dữ liệu chưa được sao lưu</h3>" +
                            "<div>Vào lúc: " + new Date().toString() + "<div>";
                    String[] emails = XmlHandler.getValueNode("Emails").replaceAll("\\s+", "").split(",");
                    SendMailUtil.sendGmail(emails, "[Uchi] Sao lưu dữ liệu không thành công", content);
                    configBackupDatabaseForm.setAction_status(SystemMessageProperties.getSystemProperty("err_backup_database"));
                }
            } else {
                redirectAttributes.addFlashAttribute("errorCode", SystemMessageProperties.getSystemProperty("err_backup_database"));
                String content = "<h3 style='color:black'>Dữ liệu chưa được sao lưu</h3>" +
                        "<div>Vào lúc: " + new Date().toString() + "<div>";
                String[] emails = XmlHandler.getValueNode("Emails").replaceAll("\\s+", "").split(",");
                SendMailUtil.sendGmail(emails, "[Uchi] Sao lưu dữ liệu không thành công", content);
                configBackupDatabaseForm.setAction_status(SystemMessageProperties.getSystemProperty("err_backup_database"));
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorCode", SystemMessageProperties.getSystemProperty("err_backup_database"));
        }
        return new ModelAndView("redirect:/system/backup-view");
    }

    public void createFileBackUpOrRetore(String[] fileContent, String fileName, int length) {
        try {
            File file = new File(SystemProperties.getProperty(Constants.CONFIG_BACKUP_DATABASE_FOLDER) + fileName);
            File folder = new File(SystemProperties.getProperty(Constants.CONFIG_BACKUP_DATABASE_FOLDER));
            if (file.exists()) {
                file.delete();
            } else {
                if (!folder.exists())
                    folder.mkdirs();
            }
            file.createNewFile();
            PrintWriter writer = new PrintWriter(file, "UTF-8");
            for (int i = 0; i < length; i++) {
                writer.println(fileContent[i]);
            }
            writer.println("Exit");
            writer.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/backup-list", method = RequestMethod.GET)
    public ModelAndView backupList(BackupList backupList, HttpServletRequest request, HttpServletResponse response) {
        if (!ValidationPool.checkRoleDetail(request, "07", Constants.AUTHORITY_XEM)) return new ModelAndView("/404");
        String controllerFileName;
        if (backupList.getFilename() != null) {
            backupList.setFilename(backupList.getFilename().trim());
        }
        int backUpListNumber = 1;
        int backUpTotalPage = 1;
        int page = 1;
        if (backupList != null) {
            backUpListNumber = backupList.getTotal();
            page = backupList.getPage();
            backUpTotalPage = backupList.getTotalPage();

        }

        controllerFileName = backupList.getFilename();

        backupList = saveList(backupList.getFilename());

        backupList.setFilename(controllerFileName);


        backUpListNumber = backupList.getList().size();
        backUpTotalPage = (backUpListNumber / 20) + 1;

        if (page > backUpTotalPage) page = backUpTotalPage;
        if (page < 1) page = 1;
        /*int row=page*20;*/
        ArrayList<BackupInfo> rowBackUpList = new ArrayList<BackupInfo>();
        for (int row = ((page * 20) - 20); row < backUpListNumber; row++) {

            rowBackUpList.add(backupList.getList().get(row));
            if (rowBackUpList.size() > 20) {
                break;
            }
        }
        backupList.setTotal(backUpListNumber);
        backupList.setTotalPage(backUpTotalPage);
        backupList.setPage(page);
        backupList.setList(rowBackUpList);


        return new ModelAndView("/system/SM0018", "backupList", backupList);
    }

    public BackupList saveList(String key) {

        BackupList backupList = new BackupList();
        File folder = new File(SystemProperties.getProperty(Constants.CONFIG_BACKUP_DATABASE_FOLDER));
        if (!folder.exists()) {
            folder.mkdirs();
        }
        File[] listFiles = folder.listFiles();
        ArrayList<BackupInfo> listBackup = new ArrayList<BackupInfo>();
        BackupInfo info = null;
        SimpleDateFormat splFomat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        for (File file : listFiles) {
            if (!EditString.isNull(key)) {
                if (file.getName().indexOf(key) > -1) {
                    info = new BackupInfo();
                    if (!file.getName().equals("backup.bat") && !file.getName().equals("backupnow.bat") && !file.getName().equals("restore.bat") && !file.isDirectory()) {
                        info.setFileName(file.getName());
                        info.setDateFile(splFomat.format(file.lastModified()));
                        listBackup.add(info);
                    }
                }
            } else {
                info = new BackupInfo();
                if (!file.getName().equals("backup.bat") && !file.getName().equals("backupnow.bat") && !file.getName().equals("restore.bat") && !file.isDirectory()) {
                    info.setFileName(file.getName());
                    info.setDateFile(splFomat.format(file.lastModified()));
                    listBackup.add(info);
                }
            }
        }
        Collections.sort(listBackup, new Comparator<BackupInfo>() {

            public int compare(BackupInfo o1, BackupInfo o2) {
                SimpleDateFormat splFomat1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                try {
                    Date date1 = splFomat1.parse(o1.getDateFile());
                    Date date2 = splFomat1.parse(o2.getDateFile());
                    return date1.compareTo(date2);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                return 0;
            }
        });
        Collections.reverse(listBackup);
        backupList.setList(listBackup);
        return backupList;
    }

    @RequestMapping(value = "/restore-view", method = RequestMethod.GET)
    public ModelAndView restoreView(HttpServletRequest request, HttpServletResponse response) {
        if (!ValidationPool.checkRoleDetail(request, "07", Constants.AUTHORITY_XEM)) return new ModelAndView("/404");

        return new ModelAndView("/system/SM0019");
    }

    @RequestMapping(value = "/restore", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView restore(ConfigBackupDatabaseForm cfBackupForm, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
        HttpSession session = request.getSession();
        String filePath = cfBackupForm.getFileRestore();
        File file = new File(SystemProperties.getProperty(Constants.CONFIG_BACKUP_DATABASE_FOLDER));
        if (!file.exists()) {
            file.mkdirs();
        }
        String database = SystemProperties.getProperty("database");
        String user = SystemProperties.getProperty("user");
        String pass = SystemProperties.getProperty("pass");
        String host = SystemProperties.getProperty("host");


        File fileBackUp = new File(filePath.replaceAll("\\\\", "/"));
//            if(!fileBackUp.exists()) //System.out.println("1");
//        if(!fileBackUp.isDirectory()) //System.out.println("2");
//        if(!fileBackUp.canRead()) //System.out.println("3");
//            //System.out.println("cmnr="+filePath.replaceAll("\\\\", "/"));
        if (fileBackUp.exists() && !fileBackUp.isDirectory() && fileBackUp.canRead()) {
//                //System.out.println("exits");
            String[] cmd = new String[10];
            int i = 0;
            cmd[i++] = SystemProperties.getProperty(Constants.CONFIG_MYSQL_DUMP_FOLDER).substring(0, 2);
            cmd[i++] = "cd \"" + SystemProperties.getProperty(Constants.CONFIG_MYSQL_DUMP_FOLDER) + "\"";
            cmd[i++] = "mysql -u" + user + " -p" + pass + " -h" + host + " " + database + " < \"" + filePath.replaceAll("\\\\", "/") + "\"";

            Runtime c = Runtime.getRuntime();
            String fileretore = Constants.FILE_NAME_RESTORE;
            if (SystemProperties.getProperty("system_backup_os").equals("1")) {
                fileretore = "restore.sh";
            }
            createFileBackUpOrRetore(cmd, fileretore, i);
            String cmdStr = "cmd /c start " + SystemProperties.getProperty(Constants.CONFIG_BACKUP_DATABASE_FOLDER) + Constants.FILE_NAME_RESTORE;
            try {
                Process pro;
                if (SystemProperties.getProperty("system_backup_os").equals("0")) {
                    pro = c.exec(cmdStr, null);
                } else {
                    pro = c.exec(SystemProperties.getProperty("system_backup_database_folder") + "restore.sh",
                            null, new File(SystemProperties.getProperty("system_backup_database_folder")));

                }
                /*Process pro = c.exec(cmdStr, null);*/
                if (pro.waitFor() == 0) {
                    redirectAttributes.addFlashAttribute("successCode", SystemMessageProperties.getSystemProperty("msg_restore_success"));
                    /*cfBackupForm.setSuccess(true);
                    cfBackupForm.setAction_status(SystemMessageProperties.getSystemProperty("msg_restore_success"));
                    session.setAttribute(ConfigBackupDatabaseForm.SESSON_KEY, cfBackupForm);*/
                } else {
                    redirectAttributes.addFlashAttribute("errorCode", SystemMessageProperties.getSystemProperty("err_restore_database"));
                    /*cfBackupForm.setSuccess(false);
                    cfBackupForm.setAction_status(SystemMessageProperties.getSystemProperty("err_restore_database"));
                    session.setAttribute(ConfigBackupDatabaseForm.SESSON_KEY, cfBackupForm);*/
                }
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("errorCode", SystemMessageProperties.getSystemProperty("err_restore_database"));
                /*cfBackupForm.setSuccess(false);
                cfBackupForm.setAction_status(SystemMessageProperties.getSystemProperty("err_restore_database"));
                session.setAttribute(ConfigBackupDatabaseForm.SESSON_KEY, cfBackupForm);*/
            }
        } else {
            redirectAttributes.addFlashAttribute("errorCode", SystemMessageProperties.getSystemProperty("err_restore_database_file_path"));
            /*cfBackupForm.setSuccess(false);
            cfBackupForm.setAction_status(SystemMessageProperties.getSystemProperty("err_restore_database_file_path"));
            session.setAttribute(ConfigBackupDatabaseForm.SESSON_KEY, cfBackupForm);*/
        }

        return new ModelAndView("redirect:/system/backup-view");

    }

    @ResponseBody
    @RequestMapping(value = "/remove-backup", method = RequestMethod.GET)
    public ResultRequest remove(HttpServletRequest request, HttpServletResponse response) {
        // TODO Auto-generated method stub
        ResultRequest resultRequest = null;
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String cb11 = request.getParameter("deleteData");
        String[] filenames = null;
        if (cb11 != null && !cb11.equals("")) {
            filenames = cb11.split(",");
        }


        if (filenames == null || filenames.length <= 0) {
            resultRequest = new ResultRequest(Constant.ERROR_CODE.ERROR, "Bạn cần chọn thông tin để xóa");
            /*ActionErrors error = new ActionErrors();
            error.add(new MessageUtil().createActionMessages("", "FILE_not_selected_announcement"));
            this.addErrors(request, error);
             return mapping.findForward(SUCCESS);*/
        } else {
            for (String fileName : filenames) {
                //System.out.println(" truong check co vao day khong ");
                //System.out.println(SystemProperties.getProperty(Constants.CONFIG_BACKUP_DATABASE_FOLDER) + "/" + fileName.replaceAll("&#39;", "'"));
                File file = new File(SystemProperties.getProperty(Constants.CONFIG_BACKUP_DATABASE_FOLDER) + "/" + fileName.replaceAll("&#39;", "'"));
                if (file.exists()) {
                    file.delete();
                }
            }
            resultRequest = new ResultRequest(Constant.ERROR_CODE.SUCCESS, "Xóa thành công");
        }
        return resultRequest;
        /*viewHelper.reset(f);
        ActionMessages messages = new ActionMessages();
        messages.add(new MessageUtil().createActionMessages("", request, "msg_delete_success", "item_fie"));
        this.addMessages(request, messages);

        return mapping.findForward(SUCCESS);*/
    }

    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public void download(@RequestParam("filename") String filename, @RequestParam("filepath") String filepath, HttpServletResponse response) {
        try {
            File file = new File(filepath + filename);
            if (!file.exists()) {
                String errorMessage = "Sorry. The file you are looking for does not exist";
                OutputStream outputStream = response.getOutputStream();
                outputStream.write(errorMessage.getBytes());
                outputStream.close();
                return;
            }
            String mimeType = URLConnection.guessContentTypeFromName(file.getName());
            if (mimeType == null) {
                mimeType = "application/octet-stream";
            }
            response.setContentType(mimeType);
            response.setHeader("Content-Disposition", String.format("inline; filename=\"" + filename + "\""));
            response.setContentLength((int) file.length());
            InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
            FileCopyUtils.copy(inputStream, response.getOutputStream());
                inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*History Contract*/
    @RequestMapping(value = "/contract-history", method = RequestMethod.GET)
    public ModelAndView getContractHistory(HistoryContractList historyContractList, HttpServletRequest request) {
        if (!ValidationPool.checkRoleDetail(request, "05", Constants.AUTHORITY_XEM)) return new ModelAndView("/404");
        int historyList = 1;
        int historyTotal = 1;
        int page = 1;
        if (historyContractList != null) {
            historyList = historyContractList.getTotal();
            historyTotal = historyContractList.getTotalPage();
            page = historyContractList.getPage();
        }
        String contractNumber = historyContractList.getContractNumber();
        String contractContent = historyContractList.getContractContent();
        if (historyTotal < 1) historyTotal = 1;
        if (page < 1) page = 1;
        if (page > historyTotal) page = historyTotal;
        historyContractList.setPage(page);
        String query = historyContractList.getOrderString();
        historyContractList.setHistoryContractList(QueryFactory.getHistoryContract(page, query));
        historyList = QueryFactory.countHistoryContract(page, query);
        historyTotal = QueryFactory.countPage(historyList);
        historyContractList.setTotal(historyList);
        historyContractList.setTotalPage(historyTotal);
        historyContractList.setContractNumber(contractNumber);
        historyContractList.setContractContent(contractContent);


        return new ModelAndView("/system/SM006", "historyContractList", historyContractList);
    }

    @RequestMapping(value = "/backup-from-stp", method = RequestMethod.GET)
    public void backupFromSTP(HttpServletRequest request, HttpServletResponse response) {
        String fromDate = request.getParameter("fromDate");
        String toDate = request.getParameter("toDate");
        fromDate = TimeUtil.toTimestamp(true, fromDate).toString();
        toDate = TimeUtil.toTimestamp(false, toDate).toString();
        //System.out.println("from=" + fromDate);
        //System.out.println("to=" + toDate);
        ExecutorService pool = Executors.newFixedThreadPool(1);
        HttpSession session = request.getSession();
        CommonContext commonContext = ((CommonContext) session.getAttribute(request.getSession().getId()));
        String filter = " where notary_date >= '" + fromDate + "' and notary_date <= '" + toDate + "' and synchronize_id ='"+ commonContext.getAuthentication_id()+"'";
        List<TransactionProperty> trans = STPQueryFactory.getTransactionPropertyByFilter(filter);
        int total = trans.size();
        if(trans.size() >0){
            for(int i = 0 ;i<total;i++){
                if (trans.get(i).getNotary_fee() == null) {
                    trans.get(i).setNotary_fee(Long.valueOf(0));
                }
                if (trans.get(i).getRemuneration_cost() == null) {
                    trans.get(i).setRemuneration_cost(Long.valueOf(0));
                }
                if (trans.get(i).getNotary_cost_total()== null) {
                    trans.get(i).setNotary_cost_total(Long.valueOf(0));
                }
            }
        }

        int index = 0;
        BackupFromSTPCallable callable = null;
        callable = BackupFromSTPCallable.getInstance();
        callable.setTotal(total);
        callable.setTrans(trans);
        callable.setIndex(index);

        Future<String> future = pool.submit(callable);
        pool.shutdown();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try {
            response.getWriter().write("ok");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/backup-from-stp-handle", method = RequestMethod.GET)
    public void backupFromSTPHandle(HttpServletRequest request, HttpServletResponse response) {
        ExecutorService pool = Executors.newFixedThreadPool(1);
        BackupFromSTPCallable callable = null;
        callable = BackupFromSTPCallable.getInstance();
        float total = callable.getTotal();
        if (total > 0) {
            float index = callable.getIndex();
            float percen = (index / total) * 100;

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            try {
                response.getWriter().write(String.valueOf(Math.round(percen)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            try {
                response.getWriter().write(String.valueOf("100"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @RequestMapping(value = "/configuration", method = RequestMethod.GET)
    public ModelAndView configurationSystem(HttpServletRequest request, HttpServletResponse response) {
        return new ModelAndView("/system/SM0020");
    }

    @RequestMapping(value = "/contract-history-view/{contract_id}", method = RequestMethod.GET)
    public ModelAndView updateview(@PathVariable("contract_id") Long contract_id, HttpServletRequest request, HttpServletResponse response) {

        ContractHistoryDetail contractHistoryDetail = new ContractHistoryDetail();

        List<HistoryContract> historyContracts = getContractHistoryById(contract_id);
        contractHistoryDetail.setHistoryContractList(historyContracts);

        return new ModelAndView("/system/SM0027", "contractHistoryDetail", contractHistoryDetail);

    }

    public List<HistoryContract> getContractHistoryById(Long contract_id) {
        int entry_user_id = 1;
        String filter = "where contract_id = " + contract_id;

        List<HistoryContract> result = APIUtil.getHistoryContract(Constants.VPCC_API_LINK + "/contract/selectContractHistoryByFilter", filter);
        return result;
    }
    @RequestMapping(value = "/synchronize-notary", method = RequestMethod.GET)
    public ModelAndView synchronizeNotaryNumber(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        CommonContext commonContext = ((CommonContext) session.getAttribute(request.getSession().getId()));

        int x = QueryFactory.countTotalUserList("where role = 02 and active_ccv = 0");
        int y = QueryFactory.countTotalUserList("where active_ccv = 1");
        int z = x +y;
        String test ="";
        test = commonContext.getAuthentication_id()+";"+z+";"+y;
        Boolean xz = STPQueryFactory.synchronizeNotaryNumber(test);
        return new ModelAndView("redirect:/system/user-list");
    }

}

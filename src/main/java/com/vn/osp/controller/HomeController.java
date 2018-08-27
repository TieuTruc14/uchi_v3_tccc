package com.vn.osp.controller;

import com.vn.osp.common.global.Constants;
import com.vn.osp.common.util.*;
import com.vn.osp.context.CommonContext;
import com.vn.osp.modelview.*;
import com.vn.osp.service.APIUtil;
import com.vn.osp.service.QueryFactory;
import com.vn.osp.service.STPQueryFactory;
import com.vn.osp.service.mail.SendMailTLS;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;
import java.io.File;
import java.io.FileOutputStream;
import java.math.BigInteger;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by tranv on 12/14/2016.
 */
@Controller
public class HomeController extends BaseController {

    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public ModelAndView toHome(HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        HttpSession session = request.getSession();
        ModelAndView view = new ModelAndView();
        String[] listFileName = null;
        String[] listFileNameTCCC = null;
        CommonContext commonContext = ((CommonContext) session.getAttribute(request.getSession().getId()));
        if (commonContext != null) {
            List<Announcement> announcements = QueryFactory.getLatestAnnouncement(3);
            HomeForm homeForm = new HomeForm();
            List<AnnouncementFromStp> announcementsSTP = new ArrayList<AnnouncementFromStp>();
            AnnouncementFromStpWrapper wrapper = STPQueryFactory.getAnnouncementFromStpWrapperHomePage();
            if (wrapper != null && wrapper.getAnnouncementFromStpList() != null && wrapper.getAnnouncementFromStpList().size() > 0) {
                announcementsSTP = wrapper.getAnnouncementFromStpList();
                homeForm.setStpAnnouncementArrayList((ArrayList<AnnouncementFromStp>) announcementsSTP);
            }
            if (announcements != null && announcements.size() > 0) {
                homeForm.setAnnouncementArrayList((ArrayList<Announcement>) announcements);
            }
            if (session.getAttribute(HomeForm.POPUP_FROM_STP_SESSION_KEY) == null) {

                List<AnnouncementFromStp> announcementPopupFromSTP = STPQueryFactory.getAnnouncementPopupFromSTP(" and nar.authentication_id = '" + commonContext.getAuthentication_id() + "'");

                if (announcementPopupFromSTP != null && announcementPopupFromSTP.size() > 0) {
                    int checkSize = announcementPopupFromSTP.size();
                    for (int i = 0; i < checkSize; i++) {
                        String entryDateTime = TimeUtil.convertTimeStampToStringMinutes(announcementPopupFromSTP.get(i).getEntry_date_time());
                        announcementPopupFromSTP.get(i).setEntryDateTimeConver(entryDateTime);
                    }


                    if (!StringUtils.isBlank(announcementPopupFromSTP.get(0).getAttach_file_name())) {
                        listFileName = announcementPopupFromSTP.get(0).getAttach_file_name().split(",");

                    }

                    String contentHtmlFromSTP = StringEscapeUtils.unescapeHtml4(announcementPopupFromSTP.get(0).getContent());
                    session.setAttribute(HomeForm.POPUP_FROM_STP_SESSION_KEY, contentHtmlFromSTP);
                    homeForm.setStpPopupAnnouncement(announcementPopupFromSTP);
                    homeForm.setContentHtmlFromSTP(contentHtmlFromSTP);
                }
            } else {
                homeForm.setStpPopupAnnouncement(null);
            }
            if (session.getAttribute(HomeForm.POPUP_SESSION_KEY) == null) {
                List<Announcement> popupAnnouncement = QueryFactory.getPopupAnnouncement();
                if (popupAnnouncement != null && popupAnnouncement.size() > 0) {
                    if (!StringUtils.isBlank(popupAnnouncement.get(0).getAttach_file_name())) {
                        listFileNameTCCC = popupAnnouncement.get(0).getAttach_file_name().split(",");

                    }
                    String contentHtml = StringEscapeUtils.unescapeHtml4(popupAnnouncement.get(0).getContent());
                    session.setAttribute(HomeForm.POPUP_SESSION_KEY, contentHtml);
                    homeForm.setAnnouncementPopup(popupAnnouncement);
                    homeForm.setContentHtml(contentHtml);
                }

            } else {
                homeForm.setAnnouncementPopup(null);
            }
            try {
                List<ContractKind> contractKinds = QueryFactory.getContractKind(" where 1=1");
                List<ContractTemplate> contractTemplates = QueryFactory.getContractTemplate(" where 1=1 and kind_id=" + contractKinds.get(0).getContractKindId());
                if (contractKinds != null && contractKinds.size() > 0) {
                    homeForm.setContractKinds(contractKinds);
                    homeForm.setContract_kind_id(contractKinds.get(0).getContractKindId());

                } else {
                    homeForm.setContractKinds(null);
                    homeForm.setContract_kind_id(0);
                }
                if (contractTemplates != null && contractTemplates.size() > 0) {
                    homeForm.setContractTemplates(contractTemplates);
                } else {
                    homeForm.setContractTemplates(null);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            ArrayList<Contract> contractArrayList = QueryFactory.getLatestContract(3);
            homeForm.setContractArrayList(contractArrayList);


            view.addObject("homeForm", homeForm);
            view.addObject("listFileName", listFileName);
            view.addObject("listFileNameTCCC", listFileNameTCCC);
            view.setViewName("index");
            return view;
        } else return new ModelAndView("redirect:/login-view");
    }

    @RequestMapping(value = "/user-info", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView admin_info(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        CommonContext commonContext = (CommonContext) session.getAttribute(session.getId());
        User user = QueryFactory.getUserByAccount(commonContext.getUser().getAccount()).get(0);
        return new ModelAndView("/user-info", "user", user);
    }

    @RequestMapping(value = "/update-user-home", method = RequestMethod.POST)
    public ModelAndView update(User user, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
        HttpSession session = request.getSession();
        String image = request.getParameter("image");
        String base64 = "";
        if (!StringUtils.isBlank(image)) {
            base64 = image.substring(image.indexOf("base64,") + 7);
        }
        String filename = request.getParameter("filename");
        String userId = request.getParameter("userId");
        User userUpdated = QueryFactory.getUserById(Long.valueOf(user.getUserId()));
        user.setUpdate_user_id(userUpdated.getUserId());
        user.setUpdate_user_name(userUpdated.getEntry_user_name());
        user.setUpdate_date_time(TimeUtil.convertTodayToString());
        user.setOffice_id(userUpdated.getOffice_id());
        if (!StringUtils.isBlank(user.getNewPassword())) {
            if (StringUtils.isBlank(user.getOldPassword())) {
                user.setOldPassword_("Trường Mật khẩu cũ không được phép bỏ trống");
                return new ModelAndView("/user-info", "user", user);
            } else {
                if (user.getNewPassword().trim().length() < 6) {
                    user.setPassword_("Mật khẩu mới phải dài trên 6 kí tự !");
                    user.setSuccess(false);
                    return new ModelAndView("/user-info", "user", user);
                }
            }
        } else {
            if (!StringUtils.isBlank(user.getReNewPassword())) {
                user.setReNewPassword_("Bạn chưa nhập lại mật khẩu mới !");
                return new ModelAndView("/user-info", "user", user);
            }
            if (!user.getReNewPassword().equals(user.getNewPassword())) {
                user.setPassword_("Trường Mật khẩu mới không khớp với Xác nhận mật khẩu mới !");
                return new ModelAndView("/user-info", "user", user);
            }
        }
        if (user.getIsAdvanceSearch() == null || user.getIsAdvanceSearch().equals("")) {
            user.setIsAdvanceSearch("false");
        }
        user.setUpdate_user_id(userUpdated.getUserId());
        user.setUpdate_user_name(userUpdated.getEntry_user_name());
        if (!StringUtils.isBlank(user.getOldPassword())) {
            if (!Crypter.matches(userUpdated.getPassword(), user.getOldPassword())) {
                user.setOldPassword_("Mật khẩu cũ không chính xác !");
                return new ModelAndView("/user-info", "user", user);
            }
        }
        if (!StringUtils.isBlank(user.getNewPassword())) {
            if (PasswordValidator.verifyPassword(user.getNewPassword()) == false) {
                user.setPassword_("Mật khẩu chỉ có thể là số hoặc chữ hoa và chữ thường không dấu, phải dài tối thiểu 6 kí tự và tối đa 50 kí tự, có ít nhất 1 kí tự số , 1 kí tự viết hoa và 1 kí tự viết thường !");
                return new ModelAndView("/user-info", "user", user);
            }
        }
        if (userUpdated != null && userUpdated.getUserId() != null) {
            user.updateValiate();
            try {
                if (!StringUtils.isBlank(filename)) {
                    deleteFileExits(userUpdated.getUserId());
                    String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
                    String localName = userUpdated.getAccount() + "_" + date + "." + getFileExtendtion(filename);
                    String path = "";
                    // Creating the directory to store file
                    String rootPath = SystemProperties.getProperty("system_user_folder");
                    File dir = new File(rootPath + File.separator + "tmpFiles");
                    if (!dir.exists())
                        dir.mkdirs();
                    // Create the file on server
                    String onlyUserImagePath = userUpdated.getAccount() + "_" + date + "." + getFileExtendtion(filename);
                    File serverFile = new File(dir.getAbsolutePath()
                            + File.separator + onlyUserImagePath);
                    FileOutputStream outThumbnail = new FileOutputStream(serverFile);
                    outThumbnail.write(DatatypeConverter.parseBase64Binary(base64));
                    outThumbnail.close();
                    path = serverFile.getAbsolutePath();
                    userUpdated.setFile_path(path);
                    userUpdated.setFile_name(localName);
                    QueryFactory.updateUser(userUpdated.generateUploadAvatarJson(userUpdated.getUserId(), userUpdated, ((CommonContext) session.getAttribute(session.getId())).getUser()));
                }
                User userDB = QueryFactory.getUserById(user.getUserId());
                user.setFile_path(userDB.getFile_path());
                user.setFile_name(userDB.getFile_name());
                user.setRole(userDB.getRole());
            } catch (Exception e) {
                e.printStackTrace();
                return new ModelAndView("/user-info");
            }
            List<NotaryOffice> notaryOfficeList = QueryFactory.getAllNotaryOffice();
            user.setNotaryOffices(notaryOfficeList);
            user.setOffice_id(userUpdated.getOffice_id());
            user.setActive_flg(userUpdated.getActive_flg());
            user.setActive_ccv(user.getActive_ccv() == null ? Long.valueOf(0) : 1);
            if (user.getSuccess() == false) return new ModelAndView("/user-info", "user", user);

            QueryFactory.updateUser(user.generateUpdateJson(user.getUserId(), userUpdated, ((CommonContext) session.getAttribute(session.getId())).getUser()));
            CommonContext commonContext = new CommonContext();
            commonContext.setUser(user);
            session.setAttribute(Constants.SESSION_ACTION, SystemMessageProperties.getSystemProperty("v3_user_update"));

            redirectAttributes.addFlashAttribute("successCode", "Sửa thông tin cán bộ Tổ chức công chứng thành công");
            return new ModelAndView("redirect:/home");
        } else {
            return new ModelAndView("/user-info", "errorCode", "Thông tin Cán bộ không tồn tại vì đã có hành động xóa trước đó");
        }
    }

    @RequestMapping(value = "/manual-search", method = RequestMethod.GET)
    public String manualSearch() {
        return "/manual-search";
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(LoginForm loginForm, HttpServletRequest request) {
        loginForm.valiate();
        HttpSession session = request.getSession();
        if (loginForm.getSuccess() == false) return new ModelAndView("login", "loginForm", loginForm);
        String username = loginForm.getUsername();
        String password = loginForm.getPassword();
        List<User> users = QueryFactory.getUserByAccount(username);
        if (users != null && users.size() > 0) {
            User user = users.get(0);
            if (!StringUtils.isBlank(user.getAccount())) {
                if (user.getActive_flg() == 0) {
                    loginForm.setPassword_("Tài khoản đã ngừng hoạt động!");
                    loginForm.setSuccess(false);
                    return new ModelAndView("login", "loginForm", loginForm);
                }
                if (Crypter.matches(user.getPassword(), password)) {
                    CommonContext commonContext = new CommonContext();
                    commonContext.setUser(user);
                    // lay du lieu grouprole
                    ArrayList<GroupRole> groupRoles = new ArrayList<GroupRole>();
                    Long userId = user.getUserId();
                    List<UserAuthority> userAuthorities = QueryFactory.getUserAuthorityByFilter(" where user_id=" + userId);
                    if (userAuthorities != null && userAuthorities.size() > 0) {
                        for (int i = 0; i < userAuthorities.size(); i++) {
                            GroupRole groupRole = QueryFactory.getGroupRole(userAuthorities.get(i).getGroupid());
                            if (groupRole != null) groupRoles.add(groupRole);
                        }
                    }
                    commonContext.setGroupRoles(groupRoles);
                    //lay du lieu grouprole authority
                    ArrayList<GrouproleAuthority> grouproleAuthorities = new ArrayList<GrouproleAuthority>();
                    for (int i = 0; i < groupRoles.size(); i++) {
                        List<GrouproleAuthority> part = QueryFactory.getGroupRoleAuthorites(groupRoles.get(i).getGroupRoleId());
                        if (part != null && part.size() > 0) {
                            for (int j = 0; j < part.size(); j++) {
                                grouproleAuthorities.add(part.get(j));
                            }
                        }
                    }
                    commonContext.setGrouproleAuthorities(grouproleAuthorities);
                    //lay du lieu config
                    String agent = QueryFactory.getSystemConfigByKey("notary_office_name");
                    commonContext.setAgency(agent);
                    commonContext.setAuthentication_id(QueryFactory.getSystemConfigByKey("system_authentication_id"));

                    //hop dong loi
                    List<TransactionProperty> properties = QueryFactory.getTransactionPropertyNotSync();
                    if (properties != null && properties.size() > 0)
                        commonContext.setNotSyncContract(properties.size());
                    else commonContext.setNotSyncContract(0);

                    session.setAttribute(session.getId(), commonContext);
                    //set access history
                    setAccessHistory(user, Constants.LOGIN, request, "Đăng nhập vào hệ thống." + "<br> Trạng thái : Thành công." + "<br> Url :" + request.getContextPath() + "/login");
                    return new ModelAndView("redirect:/home");
                } else {
                    setAccessHistory(user, Constants.LOGIN, request, "Đăng nhập vào hệ thống." + "<br> Trạng thái : thất bại." + "<br> Url :" + request.getContextPath() + "/login");
                    // sai pass
                    loginForm.setPassword_(SystemMessageProperties.getSystemProperty("v3_login_error"));
                    loginForm.setSuccess(false);
                    return new ModelAndView("login", "loginForm", loginForm);
                }
            } else {
                setAccessHistory(user, Constants.LOGIN, request, "Đăng nhập vào hệ thống." + "<br> Trạng thái : thất bại." + "<br> Url :" + request.getContextPath() + "/login");
                //khong ton tai username
                loginForm.setPassword_(SystemMessageProperties.getSystemProperty("v3_login_error"));
                loginForm.setSuccess(false);
                return new ModelAndView("login", "loginForm", loginForm);
            }
        } else {
            //khong ton tai username
            loginForm.setPassword_(SystemMessageProperties.getSystemProperty("v3_login_error"));
            loginForm.setSuccess(false);
            return new ModelAndView("login", "loginForm", loginForm);
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        setAccessHistory(((CommonContext) request.getSession().getAttribute(request.getSession().getId())).getUser(), Constants.LOGOUT, request, "Đăng xuất khỏi hệ thống." + "<br> Trạng thái : Thành công." + "<br> Url :" + request.getContextPath() + "/logout");
        session.removeAttribute(session.getId());
//        session.removeAttribute(CommonContext.SESSION_KEY);
        session.removeAttribute(HomeForm.POPUP_SESSION_KEY);
        session.removeAttribute(HomeForm.POPUP_FROM_STP_SESSION_KEY);
        return "redirect:/login-view";
    }

    @RequestMapping(value = "/login-view", method = RequestMethod.GET)
    public String loginView() {
        return "login";
    }

    @RequestMapping(value = "/forgot-password", method = RequestMethod.GET)
    public String forgetPassword() {
        return "forgot-password";
    }

    @RequestMapping(value = "/contact-us", method = RequestMethod.GET)
    public String contact() {
        return "contact-us";
    }

    @RequestMapping(value = "/send-email", method = RequestMethod.POST)
    public ModelAndView sendEmail(HttpServletRequest request, ForgetPasswordForm forgetPasswordForm) {
        request.getRequestURL().toString();
        ModelAndView modelAndView = new ModelAndView();
        String path = request.getContextPath();
        /*String path1 = request.getRequestURL().toString();
        String path2 = request.getRequestURI();
        String path3 = getRequestURL(request);*/
        String linkLogin = "http://" + getDomainName(getRequestURL(request)) + path + "/login-view";
        try {
            forgetPasswordForm.valiate();
            if (forgetPasswordForm.getSuccess() == false)
                return new ModelAndView("forgot-password", "forgetPasswordForm", forgetPasswordForm);
            String account = forgetPasswordForm.getAccount();
            String email = forgetPasswordForm.getEmail();
            List<User> users = QueryFactory.getUserByAccount(account);
            if (ValidateUtil.validate(email) == false) {
                forgetPasswordForm.setEmail_("Email không đúng định dạng !");
                return new ModelAndView("forgot-password", "forgetPasswordForm", forgetPasswordForm);
            } else {
                if (users != null && users.size() > 0) {
                    User user = users.get(0);
                    if (user.getEmail().equals(email)) {
                        String newPass = new BigInteger(130, new SecureRandom()).toString(32);
                        user.setPassword(newPass);
                        boolean result = QueryFactory.updateUser(user.generateUpdateJsonNoneUser(user.getUserId()));
                        boolean mail = SendMailTLS.mail(user.getEmail(), newPass, user.getAccount(), linkLogin);
                        if (mail == true && mail == true) {
                            forgetPasswordForm.setEmailSuccess_(SystemMessageProperties.getSystemProperty("v3_send_mail_ok"));
                            forgetPasswordForm.setSuccess(true);
                            modelAndView.setViewName("forgot-password");
                            modelAndView.addObject("forgetPasswordForm", forgetPasswordForm);
                            return new ModelAndView("forgot-password", "forgetPasswordForm", forgetPasswordForm);
                        } else {
                            forgetPasswordForm.setEmail_(SystemMessageProperties.getSystemProperty("v3_not_send_mail"));
                            forgetPasswordForm.setSuccess(false);
                            return new ModelAndView("forgot-password", "forgetPasswordForm", forgetPasswordForm);
                        }
                    } else {
                        forgetPasswordForm.setEmail_(SystemMessageProperties.getSystemProperty("v3_account_and_email_not_exits"));
                        forgetPasswordForm.setSuccess(false);
                        return new ModelAndView("forgot-password", "forgetPasswordForm", forgetPasswordForm);
                    }
                }
            }
            forgetPasswordForm.setEmail_(SystemMessageProperties.getSystemProperty("v3_account_and_email_not_exits"));
            forgetPasswordForm.setSuccess(false);
            return new ModelAndView("forgot-password", "forgetPasswordForm", forgetPasswordForm);
        } catch (Exception e) {
            forgetPasswordForm.setEmail_(SystemMessageProperties.getSystemProperty("v3_not_send_mail"));
            forgetPasswordForm.setSuccess(false);
            return new ModelAndView("forgot-password", "forgetPasswordForm", forgetPasswordForm);
        }
    }

    public static String getFileExtendtion(String nameFile) {
        String ext1 = FilenameUtils.getExtension(nameFile);
        return ext1;
    }

    public static boolean isImage(String filepath) {
        String[] extensions = {".jpg", ".png", ".jpeg"};
        for (String extension : extensions) {
            if (filepath.endsWith(extension)) {
                return true;
            }
        }
        return false;
    }

    public static User checkFileShowView(Long idUser) {
        User user = QueryFactory.getUserById(idUser);
        if (user != null) {
            if (!user.getAccount().equals("") && user.getAccount() != null) {
                if (!StringUtils.isBlank(user.getFile_path())) {
                    File file = new File(user.getFile_path());
                    if (user.getFile_name() == null || user.getFile_name().equals("")) {
                        user.setFile_name("/static/image/user.png");
                    } else if (!file.exists()) {
                        user.setFile_name("/static/image/user.png");
                    } else {
                        user.setFile_name("/tmpFiles/" + user.getFile_name());
                    }
                } else {
                    user.setFile_name("/static/image/user.png");
                }
            }
        }
        return user;
    }

    public static void deleteFileExits(Long id) {
        User user = QueryFactory.getUserById(id);
        String result = "";
        try {
            File file = new File(user.getFile_path());
            if (!file.exists()) {
            } else {
                file.delete();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        APIUtil.callAPI(Constants.STP_API_LINK + "/users/removeFile", String.valueOf(id));
    }

    public String getDomainName(String url) {
        URI uri;
        String domain = "";
        try {
            uri = new URI(url);
            domain = uri.getPort() > 0 ? uri.getHost() + ":" + uri.getPort() : uri.getHost();
            domain = domain.startsWith("www.") ? domain.substring(4) : domain;
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return domain;
    }

    public String getRequestURL(HttpServletRequest request) {
        return request.getRequestURL().toString();
    }

}

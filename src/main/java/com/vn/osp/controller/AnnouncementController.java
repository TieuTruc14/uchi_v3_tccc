package com.vn.osp.controller;

import com.vn.osp.common.global.Constants;
import com.vn.osp.common.util.*;
import com.vn.osp.context.CommonContext;
import com.vn.osp.modelview.*;
import com.vn.osp.service.APIUtil;
import com.vn.osp.service.QueryFactory;
import com.vn.osp.service.STPQueryFactory;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by minh on 5/15/2017.
 */
@Controller
@RequestMapping("announcement")
public class AnnouncementController {

    @RequestMapping(value = "/download/{aid}/{index}", method = RequestMethod.GET)
    public void downloadFile(@PathVariable("aid") Long aid, @PathVariable("index") int index, HttpServletRequest request, HttpServletResponse response) {
        try {
            if (!(ValidationPool.checkRoleDetail(request, "18", Constants.AUTHORITY_XEM))) {
                return;
            }
            List<Announcement> list = getAnnouncementId(aid);
            Announcement announcement = list.get(0);
            String[] announcement_file_path = announcement.getAttach_file_path().split(",");
            String[] announcement_file_name = announcement.getAttach_file_name().split(",");
            File file = new File(announcement_file_path[index]);
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
            response.setHeader("Content-Disposition", String.format("inline; filename=\"" + announcement_file_name[index] + "\""));
            response.setContentLength((int) file.length());
            InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
            FileCopyUtils.copy(inputStream, response.getOutputStream());
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/download-from-stp/{aid}", method = RequestMethod.GET)
    public void downloadFileFromStp(@PathVariable("aid") Long aid, HttpServletRequest request, HttpServletResponse response) {
        if (!ValidationPool.checkRoleDetail(request, "18", Constants.AUTHORITY_XEM)) return;
        try {
            List<Announcement> list = getAnnouncementIdFromStp(aid);
            Announcement announcement = list.get(0);
            File file = new File(announcement.getAttach_file_path());
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
            response.setHeader("Content-Disposition", String.format("inline; filename=\"" + announcement.getAttach_file_name() + "\""));
            response.setContentLength((int) file.length());
            InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
            FileCopyUtils.copy(inputStream, response.getOutputStream());
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/download-from-stp/{aid}/{index}", method = RequestMethod.GET)
    public void downloadFileFromStp2(@PathVariable("aid") Long aid, @PathVariable("index") int index, HttpServletRequest request, HttpServletResponse response) {
        if (!ValidationPool.checkRoleDetail(request, "18", Constants.AUTHORITY_XEM)) return;
        try {
            List<Announcement> list = getAnnouncementIdFromStp(aid);
            Announcement announcement = list.get(0);
            String[] anouncementFileName = announcement.getAttach_file_name().split(",");
            String[] anouncementFilePath = announcement.getAttach_file_path().split(",");
            File file = new File(anouncementFilePath[index]);
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
            response.setHeader("Content-Disposition", String.format("inline; filename=\"" + anouncementFileName[index] + "\""));
            response.setContentLength((int) file.length());
            InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
            FileCopyUtils.copy(inputStream, response.getOutputStream());
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/removeFile/{aid}", method = RequestMethod.GET)
    public String removeFile(@PathVariable("aid") Long aid, HttpServletRequest request) {
        if (!ValidationPool.checkRoleDetail(request, "18", Constants.AUTHORITY_SUA)) return "redirect:/404";
        List<Announcement> list = getAnnouncementId(aid);
        Announcement announcement = list.get(0);
        AnnouncementWrapper announcementWrapper = new AnnouncementWrapper();

        try {
            File file = new File(announcement.getAttach_file_path());
            if (!file.exists()) {
                String errorMessage = "Sorry. The file you are looking for does not exist";
            } else file.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        APIUtil.callAPI(Constants.VPCC_API_LINK + "/announcement/removeFile", String.valueOf(aid));

        return "redirect:/announcement/update-view/" + String.valueOf(aid);
    }


    @RequestMapping(value = "/update-view/{aid}", method = RequestMethod.GET)
    public ModelAndView updateview(@PathVariable("aid") Long aid, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
        if (!ValidationPool.checkRoleDetail(request, "18", Constants.AUTHORITY_XEM)) return new ModelAndView("/404");
        UpdateAnnouncement updateAnnouncement = new UpdateAnnouncement();
        ModelAndView view = new ModelAndView();

        List<Announcement> list = getAnnouncementId(aid);
        Announcement announcement = null;
        String[] listName = null;
        List<String> listFileName = new ArrayList<>();
        if (list.size() > 0) {
            announcement = list.get(0);
            updateAnnouncement.setAnnouncement(announcement);
            if (!StringUtils.isBlank(announcement.getAttach_file_name())) {
                listName = announcement.getAttach_file_name().split(",");
                for (int i = 0; i < listName.length; i++) {
                    if (!StringUtils.isBlank(listName[i])) {
                        listFileName.add(listName[i]);
                    }
                }
                view.addObject("countlistFileName", listFileName.size());
            } else {
                view.addObject("countlistFileName", 0);
            }
        } else {
            redirectAttributes.addFlashAttribute("errorCode", "Thông tin Thông báo không tồn tại vì đã có hành động xóa trước đó");
            return new ModelAndView("redirect:/announcement/list");
        }
        view.addObject("updateAnnouncement", updateAnnouncement);
        view.addObject("listFileName", listFileName);
        view.setViewName("/announcement/ANN003");
        return view;

    }

    @RequestMapping(value = "/view/{aid}", method = RequestMethod.GET)
    public ModelAndView updateviewFromStp(@PathVariable("aid") Long aid, HttpServletRequest request, HttpServletResponse response) {
        if (!ValidationPool.checkRoleDetail(request, "18", Constants.AUTHORITY_XEM)) return new ModelAndView("/404");
        UpdateAnnouncement updateAnnouncementStp = new UpdateAnnouncement();
        ModelAndView view = new ModelAndView();
        Announcement announcement = null;
        String[] listName = null;
        List<String> listFileName = new ArrayList<>();
        List<Announcement> list = getAnnouncementIdFromStp(aid);
        if (list.size() > 0) {
            announcement = list.get(0);
            if (!StringUtils.isBlank(announcement.getAttach_file_name())) {
                listName = announcement.getAttach_file_name().split(",");
                for (int i = 0; i < listName.length; i++) {
                    if (!StringUtils.isBlank(listName[i])) {
                        listFileName.add(listName[i]);
                    }
                }
            }
        }
        updateAnnouncementStp.setAnnouncement(announcement);
        view.setViewName("/announcement/ANN004");
        view.addObject("listFileName",listFileName);
        view.addObject("updateAnnouncementStp",updateAnnouncementStp);
        return view;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ModelAndView updateAnnouncement(@RequestParam("multipartFile") MultipartFile[] files, Announcement announcement, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
        if (!ValidationPool.checkRoleDetail(request, "18", Constants.AUTHORITY_SUA)) {
            return new ModelAndView("redirect:/404");
        }
        ModelAndView view = new ModelAndView();
        try {
            if (announcement.getTitle() != null) {
                announcement.setTitle(announcement.getTitle().trim());
            }
            HttpSession session = request.getSession();
            UpdateAnnouncement announcementWrapper = new UpdateAnnouncement();
            String localName = "";
            String path = "";
            List<Announcement> list = getAnnouncementId(announcement.getAid());
            if (list != null) {
                if (!StringUtils.isBlank(list.get(0).getAttach_file_name())) {
                    localName = list.get(0).getAttach_file_name() + ",";
                    path = list.get(0).getAttach_file_path() + ",";
                }
            }

            announcementWrapper.setAnnouncement(announcement);
            /*MultipartFile announcementFile = announcement.getAnnouncementFile();*/
            announcement.validate();
            if (announcement.getSuccess() == false) {
                return new ModelAndView("/announcement/ANN003", "updateAnnouncement", announcementWrapper);
            }
            long sizes = 0;
            for (MultipartFile multipartFile : files) {
                sizes = sizes + multipartFile.getSize();
            }
            if (sizes > Constants.MAX_SIZE_FILE_UPLOAD_10MB) {
                ModelAndView modelAndView = new ModelAndView();
                announcementWrapper.getAnnouncement().setPrevent_data_("Tổng dung lượng file phải nhỏ hơn 10mb!");
                modelAndView.addObject("updateAnnouncement", announcementWrapper);
                modelAndView.addObject("maxSizeUpload", "Tổng dung lượng file phải nhỏ hơn 10mb!");
                modelAndView.setViewName("/announcement/ANN003");
                return modelAndView;
            }

            if (files != null) {
                if (files.length > 0) {
                    for (int i = 0; i < files.length; i++) {
                        MultipartFile multipartFile = files[i];
                        if (!multipartFile.isEmpty()) {
                            Long size = multipartFile.getSize();
                            if (size > Constants.MAX_SIZE_FILE_UPLOAD_10MB) {
                                announcement.setSuccess(false);
                                announcement.setPrevent_data_(SystemMessageProperties.getSystemProperty("v3_max_file_size_10mb"));
                                return new ModelAndView("/announcement/ANN003", "updateAnnouncement", announcementWrapper);
                            }
                            File file = FileUtil.createNewFile(com.vn.osp.common.util.SystemProperties.getProperty("system_announcement_folder"), "ANNT_");
                            localName += EditString.convertUnicodeToASCII(multipartFile.getOriginalFilename()) + ",";
                            try {
                                FileOutputStream outputStream = null;
                                outputStream = new FileOutputStream(file);
                                outputStream.write(multipartFile.getBytes());
                                outputStream.close();
                                path += file.getAbsolutePath() + ",";
                                announcementWrapper.getAnnouncement().setAttach_file_name(localName.substring(0, localName.length() - 1));
                                announcementWrapper.getAnnouncement().setAttach_file_path(path.substring(0, path.length() - 1));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            announcementWrapper.getAnnouncement().setAttach_file_name(localName);
                            announcementWrapper.getAnnouncement().setAttach_file_path(path);
                        }
                    }
                }
            }
            String result = announcementWrapper.updateJSON(((CommonContext) session.getAttribute(request.getSession().getId())).getUser());
            APIUtil.callAPI(Constants.VPCC_API_LINK + "/announcement/UpdateAnnouncement", result.toString());
            session.setAttribute(Constants.SESSION_ACTION, SystemMessageProperties.getSystemProperty("v3_announcement_update_success"));
            return new ModelAndView("redirect:/announcement/list");
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("errorCode", "Thông tin Thông báo không tồn tại vì đã có hành động xóa trước đó");

            return new ModelAndView("redirect:/announcement/list");
        }
    }

    @RequestMapping(value = "/add-view", method = RequestMethod.GET)
    public ModelAndView addAnnouncement(Announcement announcement, HttpServletRequest request) {
        if (!ValidationPool.checkRoleDetail(request, "18", Constants.AUTHORITY_THEM)) return new ModelAndView("/404");
        AddAnnouncement addAnnouncement = new AddAnnouncement();
        addAnnouncement.setAnnouncement(announcement);

        return new ModelAndView("/announcement/ANN002", "addAnnouncement", addAnnouncement);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView addAnnouncement(@RequestParam("multipartFile") MultipartFile[] files, Announcement announcement, HttpServletRequest request, HttpServletResponse response) {
        if (!ValidationPool.checkRoleDetail(request, "18", Constants.AUTHORITY_THEM)) return new ModelAndView("/404");
        try {
            if (announcement.getTitle() != null && !announcement.getTitle().equals("")) {
                announcement.setTitle(announcement.getTitle().trim());
            }
            String localName = "";
            String path = "";
            HttpSession session = request.getSession();
            AddAnnouncement announcementWrapper = new AddAnnouncement();
            announcementWrapper.setAnnouncement(announcement);
            announcement.validate();
            if (announcement.getSuccess() == false) {
                return new ModelAndView("/announcement/ANN002", "addAnnouncement", announcementWrapper);
            }
            long sizes = 0;
            for (MultipartFile multipartFile : files) {
                sizes = sizes + multipartFile.getSize();
            }
            if (sizes > Constants.MAX_SIZE_FILE_UPLOAD_10MB) {
                announcement.setSuccess(false);
                announcement.setPrevent_data_("Tổng dung lượng file phải nhỏ hơn 10mb!");
                return new ModelAndView("/announcement/ANN002", "addAnnouncement", announcementWrapper);
            }
            for (int i = 0; i < files.length; i++) {
                MultipartFile multipartFile = files[i];
                if (!multipartFile.isEmpty()) {
                    Long size = multipartFile.getSize();
                    if (size > Constants.MAX_SIZE_FILE_UPLOAD) {
                        announcement.setSuccess(false);
                        announcement.setPrevent_data_(SystemMessageProperties.getSystemProperty("v3_max_file_size_5mb"));
                        return new ModelAndView("/announcement/ANN002", "addAnnouncement", announcementWrapper);
                    }
                    File file = FileUtil.createNewFile(com.vn.osp.common.util.SystemProperties.getProperty("system_announcement_folder"), "ANNT_");
                    localName += EditString.convertUnicodeToASCII(multipartFile.getOriginalFilename()) + ",";
                    try {
                        FileOutputStream outputStream = null;
                        outputStream = new FileOutputStream(file);
                        outputStream.write(multipartFile.getBytes());
                        outputStream.close();
                        path += file.getAbsolutePath() + ",";
                        announcementWrapper.getAnnouncement().setAttach_file_name(localName.substring(0, localName.length() - 1));
                        announcementWrapper.getAnnouncement().setAttach_file_path(path.substring(0, path.length() - 1));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            if (announcementWrapper.getAnnouncement().getPopup_display_day() == null || announcementWrapper.getAnnouncement().getPopup_display_day().equals("")) {
                announcementWrapper.getAnnouncement().setPopup_display_day(Long.valueOf(0));
            }
            String result = announcementWrapper.addJSON(((CommonContext) session.getAttribute(request.getSession().getId())).getUser());
            APIUtil.callAPI(Constants.VPCC_API_LINK + "/announcement/AddAnnouncement", result.toString());
            session.setAttribute(Constants.SESSION_ACTION, SystemMessageProperties.getSystemProperty("v3_announcement_add_success"));

            return new ModelAndView("redirect:/announcement/list/");
            //mess
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelAndView("announcement/ANN002");
        }
    }

    @RequestMapping(value = "/remove/{aid}", method = RequestMethod.GET)
    public String remove(@PathVariable(value = "aid") Long aid, HttpServletRequest request, HttpServletResponse response) {
        if (!ValidationPool.checkRoleDetail(request, "18", Constants.AUTHORITY_XOA)) return "redirect:/404";
        List<Announcement> list = getAnnouncementId(aid);
        Announcement announcement = list.get(0);


        APIUtil.callAPI(Constants.VPCC_API_LINK + "/announcement/deleteByID", String.valueOf(aid));
        return "redirect:https://www.google.com.vn/";
    }

    public List<Announcement> getAnnouncementIdFromStp(Long aid) {
        String filter = "where aid = " + aid;

        List<Announcement> result = APIUtil.getAnnouncementList(Constants.STP_API_LINK + "/announcement/findAnnouncementByFilter", filter);
        return result;
    }

    public List<Announcement> getAnnouncementId(Long aid) {
        String filter = "where aid = " + aid;

        List<Announcement> result = APIUtil.getAnnouncementList(Constants.VPCC_API_LINK + "/announcement/findAnnouncementByFilter", filter);
        return result;
    }
/*
    public List<NotaryOfficeByAnnouncement> getNotaryByAnnouncement(Long aid){

        String filter = "where aid = "+aid;

        List<NotaryOfficeByAnnouncement> result = APIUtil.getNotaryOfficeByAnnouncement(Constants.STP_API_LINK+"/announcement/selectNotaryByAnnouncement", filter);
        return result;
    }
    public List<User> getUser(){
        String data = "where 1=1";
        List<User> result = APIUtil.getUserByFilter(Constants.STP_API_LINK+"/users/selectByFilter", data);
        return result;

    }
*/

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView announcementlist(String tab, AnnouncementList announcementList, HttpServletRequest request) {
        ModelAndView view = new ModelAndView();

        if (!ValidationPool.checkRoleDetail(request, "18", Constants.AUTHORITY_XEM)) return new ModelAndView("/404");
        HttpSession session = request.getSession();
        String action = (String) session.getAttribute(Constants.SESSION_ACTION);
        announcementList.setActionStatus(action);
        session.removeAttribute(Constants.SESSION_ACTION);
        AnnouncementList announcementLists = createAnnouncementList(announcementList);
        if (!StringUtils.isBlank(tab)) {
            if (tab.equals("stp")) {
                announcementLists.setDefaultTabOpen(2);
            }
        } else {
            announcementLists.setDefaultTabOpen(1);
        }
        view.setViewName("/announcement/ANN001");
        view.addObject("tab", tab);
        view.addObject("announcementList", announcementLists);
        return view;
    }

    public AnnouncementList createAnnouncementList(AnnouncementList announcementList) {

        int tab = 1;
        int internalTotalPage = 1;
        int internalPage = 1;

        int publicTotalPage = 1;
        int publicPage = 1;

        int titleFilter = 0;
        String announcementType = "";
        String announcementTitleFilter = "";
        if (announcementList != null) {
            tab = announcementList.getDefaultTabOpen();

            internalTotalPage = announcementList.getInternalTotalPage();
            internalPage = announcementList.getInternalPage();

            publicTotalPage = announcementList.getPublicTotalPage();
            publicPage = announcementList.getPublicPage();

            titleFilter = announcementList.getTitleFilter();
            announcementTitleFilter = announcementList.getAnnouncementTitleFilter();
        }

        if (tab < 1) tab = 1;
        if (internalPage < 1) internalPage = 1;
        if (publicPage < 1) publicPage = 1;
        if (internalTotalPage < 1) internalTotalPage = 1;
        if (publicTotalPage < 1) publicTotalPage = 1;
        if (publicPage > publicTotalPage) publicPage = publicTotalPage;
        if (internalPage > internalTotalPage) internalPage = internalTotalPage;
        List<Announcement> announcementInternalList = new ArrayList<Announcement>();
        List<AnnouncementFromStp> announcementPublicList = new ArrayList<AnnouncementFromStp>();

        AnnouncementFromStpWrapper announcementFromStpWrapper = new AnnouncementFromStpWrapper();

        String internalQuery = announcementList.getOrderString1();
        String publicQuery = announcementList.getOrderString2();


        announcementList.setDefaultTabOpen(tab);
        announcementList.setInternalPage(internalPage);
        announcementList.setPublicPage(publicPage);


        announcementInternalList = QueryFactory.getAnnouncement(internalPage, internalQuery);
        announcementList.setAnnouncementInternalList(announcementInternalList);
        int internalListNumber = QueryFactory.countTotalAnnouncement(internalPage, internalQuery);
        announcementList.setInternalListNumber(internalListNumber);
        announcementList.setInternalTotalPage(QueryFactory.countPage(internalListNumber));

        announcementFromStpWrapper = STPQueryFactory.getAnnouncementFromStpWrapper(publicPage, publicQuery);


        if (announcementFromStpWrapper != null && announcementFromStpWrapper.getAnnouncementFromStpList() != null && announcementFromStpWrapper.getAnnouncementFromStpList().size() >= 0) {
            announcementList.setAnnouncementFromStps(announcementFromStpWrapper.getAnnouncementFromStpList());
            announcementList.setPublicListNumber(announcementFromStpWrapper.getCountTotal());
            announcementList.setPublicTotalPage(QueryFactory.countPage(announcementFromStpWrapper.getCountTotal()));

        }


        announcementList.setTitleFilter(titleFilter);
        announcementList.setAnnouncementTitleFilter(announcementTitleFilter);


        return announcementList;
    }


    @ResponseBody
    @RequestMapping(value = "/multiDelete", method = RequestMethod.GET)
    public String multiDelete(HttpServletRequest request) throws JSONException {
        if (!ValidationPool.checkRoleDetail(request, "18", Constants.AUTHORITY_XOA)) return "redirect:/404";
        String result = "";
        String deleteData = request.getParameter("deleteData");
        if (deleteData != null && !deleteData.equals("")) {
            String[] idArr = deleteData.split(",");
            for (int i = 0; i < idArr.length; i++) {
                APIUtil.callAPI(Constants.VPCC_API_LINK + "/announcement/deleteByID", idArr[i]);
                HttpSession session = request.getSession();
                session.setAttribute(Constants.SESSION_ACTION, SystemMessageProperties.getSystemProperty("v3_announcement_delete_success"));
            }
        }
        return new JSONObject().put("ok", result).toString();
    }

    @ResponseBody
    @RequestMapping(value = "/removeFile/{announcementId}/{index}", method = RequestMethod.GET)
    public ResultRequest deleteFile(@PathVariable("announcementId") Long announcementId, @PathVariable("index") int index, HttpServletRequest request) throws JSONException {
        ResultRequest result = null;
        if (!ValidationPool.checkRoleDetail(request, "18", Constants.AUTHORITY_SUA)) {
            result = new ResultRequest(Constant.ERROR_CODE.NOT_FOUND, "");
            return result;
        }
        Long aid = Long.valueOf(request.getParameter("deleteData"));
        List<Announcement> list = getAnnouncementId(aid);
        Announcement announcement = list.get(0);
        try {
            if (announcement.getAttach_file_path().split(",").length >= index + 1) {
                File file = new File(announcement.getAttach_file_path().split(",")[index]);
                if (file.exists()) {
                    file.delete();
                    //result = new ResultRequest(Constant.ERROR_CODE.ERROR, "File không tồn tại !");
                }
                result = new ResultRequest(Constant.ERROR_CODE.SUCCESS, "File đã được xóa thành công .");
                List<String> file_name = new ArrayList<String>();
                List<String> file_path = new ArrayList<String>();
                String announcement_file_name = "";
                String announcement_file_path = "";
                for (int i = 0; i < announcement.getAttach_file_name().split(",").length; i++) {
                    if (i != index) {
                        file_name.add(announcement.getAttach_file_name().split(",")[i]);
                    }
                }
                for (int i = 0; i < announcement.getAttach_file_path().split(",").length; i++) {
                    if (i != index) {
                        file_path.add(announcement.getAttach_file_path().split(",")[i]);
                    }
                }
                for (int i = 0; i < file_name.size(); i++) {
                    announcement_file_name += file_name.get(i) + ",";
                }
                for (int i = 0; i < file_path.size(); i++) {
                    announcement_file_path += file_path.get(i) + ",";
                }
                if (StringUtils.isBlank(announcement_file_path) != true && StringUtils.isBlank(announcement_file_name) != true) {
                    APIUtil.callAPI(Constants.VPCC_API_LINK + "/announcement/removeFile", String.valueOf(announcementId) + ";" + announcement_file_name.substring(0, announcement_file_name.length() - 1) + ";" + announcement_file_path.substring(0, announcement_file_path.length() - 1));
                } else {
                    APIUtil.callAPI(Constants.VPCC_API_LINK + "/announcement/removeFile", String.valueOf(announcementId) + ";" + " " + ";" + " ");
                }
            } else {
                result = new ResultRequest(Constant.ERROR_CODE.ERROR, "Thông báo không tồn tại ");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}

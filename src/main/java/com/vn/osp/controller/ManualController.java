package com.vn.osp.controller;

import com.vn.osp.common.global.Constants;
import com.vn.osp.common.util.*;
import com.vn.osp.context.CommonContext;
import com.vn.osp.modelview.AddManual;
import com.vn.osp.modelview.Manual;
import com.vn.osp.modelview.ManualList;
import com.vn.osp.modelview.UpdateManual;
import com.vn.osp.service.APIUtil;
import com.vn.osp.service.QueryFactory;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.sql.Update;
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
 * Created by minh on 3/27/2017.
 */
@Controller
@RequestMapping("manual")
public class ManualController {
    @RequestMapping(value = "/add-view", method = RequestMethod.GET)
    public ModelAndView addManuelView(HttpServletRequest request) {
        if (!ValidationPool.checkRoleDetail(request, "08", Constants.AUTHORITY_THEM)) return new ModelAndView("/404");
        AddManual addManual = new AddManual();
        return new ModelAndView("/manual/MAN001", "addManual", addManual);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView addManual(@RequestParam("multipartFile") MultipartFile[] files, AddManual addManual, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
        try {
            ModelAndView view = new ModelAndView();
            BaseController.trimAllFieldOfObject(addManual);
            if (!ValidationPool.checkRoleDetail(request, "08", Constants.AUTHORITY_THEM))
                return new ModelAndView("/404");
            String localName = "";
            String path = "";
            addManual.validate();
            if (addManual.isSuccess() == false) {
                view.addObject("errorCode", "Tạo mới thông tin tài liệu không thành công");
                view.addObject("addManual", addManual);
                view.setViewName("/manual/MAN001");
                return view;
            }
            long sizes = 0;
            for (MultipartFile multipartFile : files) {
                sizes = sizes + multipartFile.getSize();
            }
            if (sizes > Constants.MAX_SIZE_FILE_UPLOAD_10MB) {
                addManual.setSuccess(false);
                addManual.setContent_("Tổng dung lượng file phải nhỏ hơn 10mb!");
                return new ModelAndView("/manual/MAN001", "addManual", addManual);
            }
            for (int i = 0; i < files.length; i++) {
                MultipartFile multipartFile = files[i];
                if (!multipartFile.isEmpty()) {
                    Long size = multipartFile.getSize();
                    if (size > Constants.MAX_SIZE_FILE_UPLOAD) {
                        addManual.setSuccess(false);
                        addManual.setContent_(SystemMessageProperties.getSystemProperty("v3_max_file_size_5mb"));
                        return new ModelAndView("/manual/MAN001", "addManual", addManual);
                    }
                    File file = FileUtil.createNewFile(com.vn.osp.common.util.SystemProperties.getProperty("system_manual_folder"), "MANUAL_");
                    localName += EditString.convertUnicodeToASCII(multipartFile.getOriginalFilename()) + ",";
                    try {
                        FileOutputStream outputStream = null;
                        outputStream = new FileOutputStream(file);
                        outputStream.write(multipartFile.getBytes());
                        outputStream.close();
                        path += file.getAbsolutePath() + ",";
                        addManual.setFile_name(localName.substring(0, localName.length() - 1));
                        addManual.setFile_path(path.substring(0, path.length() - 1));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            String result = addManual.generateJsonObject().toString();
            APIUtil.callAPI(Constants.VPCC_API_LINK + "/manual/AddManual", result.toString());
            redirectAttributes.addFlashAttribute("successCode", "Thêm mới thông tin tài liệu thành công .");
            return new ModelAndView("redirect:/manual/list");
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelAndView("/manual/MAN001");
        }
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView getManual(ManualList manualList, HttpServletRequest request) {
        BaseController.trimAllFieldOfObject(manualList);
        String title = manualList.getTitle();
        if (!StringUtils.isBlank(manualList.getTitle())) {
            try {
                manualList.setTitle(EditString.convertUnicodeToASCII(manualList.getTitle()));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        if (!ValidationPool.checkRoleDetail(request, "08", Constants.AUTHORITY_XEM)) return new ModelAndView("/404");
        int manualListNumber = 0;
        int manualTotalPage = 0;
        int page = 1;
        if (manualList != null) {
            page = manualList.getPage();
        }
        String query = manualList.getOrderString();
        String titleFilter = manualList.getTitle();
        String entryDateTime = manualList.getEntry_date_time();
        String updateDateTime = manualList.getUpdate_date_time();
        String fileName = manualList.getFile_name();
        manualListNumber = QueryFactory.countTotalManual(page, query);
        manualTotalPage = QueryFactory.countPage(manualListNumber);
        manualList.setTotal(manualListNumber);
        manualList.setTotalPage(manualTotalPage);
        manualList.setTitle(titleFilter);
        manualList.setEntry_date_time(entryDateTime);
        manualList.setFile_name(fileName);
        manualList.setUpdate_date_time(updateDateTime);
        if (page < 1) page = 1;
        if (page > manualTotalPage) page = manualTotalPage;
        if (!StringUtils.isBlank(manualList.getTitle())) {
            manualList.setPage(1);
        } else {
            manualList.setPage(page);
        }
        List<Manual> manuals = QueryFactory.getManual(page, query);
        manualList.setManualList(manuals);
        if (manualList.getManualList() != null) {
            for (int i = 0; i < manualList.getManualList().size(); i++) {
                if (!StringUtils.isBlank(manualList.getManualList().get(i).getFile_name())) {
                    manualList.getManualList().get(i).setListFileName(manualList.getManualList().get(i).getFile_name().split(","));
                }
            }
        }
        manualList.setTotalPage(manualTotalPage);
        manualList.setTitle(title != null ? title.trim() : "");
        return new ModelAndView("/manual/MAN002", "manualList", manualList);
    }

    public List<Manual> getManual(Long id) {
        String filter = "where id = " + id;
        List<Manual> result = APIUtil.getManual(Constants.VPCC_API_LINK + "/manual/findManualByFilter", filter);
        return result;
    }

    @RequestMapping(value = "/update-view/{id}", method = RequestMethod.GET)
    public ModelAndView updateview(@PathVariable("id") Long id, HttpServletRequest request, HttpServletResponse response) {
        if (!(ValidationPool.checkRoleDetail(request, "08", Constants.AUTHORITY_XEM) || ValidationPool.checkRoleDetail(request, "08", Constants.AUTHORITY_SUA))) {
            return new ModelAndView("redirect:/404");
        }
        ModelAndView view = new ModelAndView();
        List<Manual> list = getManual(id);
        Manual manual = list.get(0);
        String[] listFileName = null;
        if (!StringUtils.isBlank(manual.getFile_name())) {
            listFileName = manual.getFile_name().split(",");
            view.addObject("countlistFileName", listFileName.length);
        } else {
            view.addObject("countlistFileName", 0);
        }

        view.addObject("manual", manual);
        view.addObject("listFileName", listFileName);
        view.setViewName("/manual/MAN003");
        return view;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ModelAndView updateUnitRequest(@RequestParam("multipartFile") MultipartFile[] files, Manual manual, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
        if (!ValidationPool.checkRoleDetail(request, "08", Constants.AUTHORITY_SUA)) {
            return new ModelAndView("redirect:/404");
        }
        String localName = "";
        String path = "";
        List<Manual> list = getManual(manual.getMid());
        if (list != null) {
            if (!StringUtils.isBlank(list.get(0).getFile_name())) {
                localName = list.get(0).getFile_name() + ",";
                path = list.get(0).getFile_path() + ",";
            }
        }
        HttpSession session = request.getSession();
        manual.validateupdate();
        if (manual.getSuccess()== false) {
            return new ModelAndView("/manual/MAN003", "manual", manual);
        }
        long sizes = 0;
        for (MultipartFile multipartFile : files) {
            sizes = sizes + multipartFile.getSize();
        }
        if (sizes > Constants.MAX_SIZE_FILE_UPLOAD_10MB) {
            manual.setSuccess(false);
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("manual",manual);
            modelAndView.addObject("maxSizeUpload","Tổng dung lượng file phải nhỏ hơn 10mb!");
            modelAndView.setViewName("/manual/MAN003");
            return new ModelAndView("/manual/MAN003", "manual", manual);
        }
        try {
            if (files.length > 0) {
                for (int i = 0; i < files.length; i++) {
                    MultipartFile multipartFile = files[i];
                    if (!multipartFile.isEmpty()) {
                        Long size = multipartFile.getSize();
                        if (size > Constants.MAX_SIZE_FILE_UPLOAD) {
                            manual.setSuccess(false);
                            return new ModelAndView("/manual/MAN003", "manual", manual);
                        }
                        File file = FileUtil.createNewFile(com.vn.osp.common.util.SystemProperties.getProperty("system_announcement_folder"), "ANNT_");
                        localName += EditString.convertUnicodeToASCII(multipartFile.getOriginalFilename()) + ",";
                        try {
                            FileOutputStream outputStream = null;
                            outputStream = new FileOutputStream(file);
                            outputStream.write(multipartFile.getBytes());
                            outputStream.close();
                            path += file.getAbsolutePath() + ",";
                            manual.setFile_name(localName.substring(0, localName.length() - 1));
                            manual.setFile_path(path.substring(0, path.length() - 1));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        manual.setFile_name(localName);
                        manual.setFile_path(path);
                    }
                }
            }
            if (list.size() > 0) {
                if (StringUtils.isBlank(list.get(0).getTitle()) || StringUtils.isBlank(list.get(0).getContent())) {
                    redirectAttributes.addFlashAttribute("errorCode", "Tài liệu không tồn tại !");
                    return new ModelAndView("redirect:/manual/list");
                } else {
                    if (StringUtils.isBlank(manual.getFile_name())) {
                        manual.setFile_name(list.get(0).getFile_name());
                        manual.setFile_path(list.get(0).getFile_path());
                    }
                    manual.setUpdate_user_id(((CommonContext) session.getAttribute(session.getId())).getUser().getUserId());
                    Boolean result = QueryFactory.updateManual(manual.generateJsonObject(((CommonContext) session.getAttribute(session.getId())).getUser()).toString());
                    session.setAttribute(Constants.SESSION_ACTION, SystemMessageProperties.getSystemProperty("v3_manual_update_success"));
                    if (result == true) {
                        redirectAttributes.addFlashAttribute("successCode", "Cập nhật tài liệu thành công .");
                    } else {
                        redirectAttributes.addFlashAttribute("errorCode", "Cập nhật tài liệu thất bại !");
                    }
                }
            } else {
                redirectAttributes.addFlashAttribute("errorCode", "Tài liệu không tồn tại !");
                return new ModelAndView("redirect:/manual/list");
            }
            return new ModelAndView("redirect:/manual/list");
            //mess
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelAndView("/manual/MAN003");
        }

    }

    @ResponseBody
    @RequestMapping(value = "/removeFile", method = RequestMethod.GET)
    public String deleteFile(HttpServletRequest request) throws JSONException {
        if (!ValidationPool.checkRoleDetail(request, "08", Constants.AUTHORITY_SUA)) {
            return "redirect:/404";
        }
        Long id = Long.valueOf(request.getParameter("deleteData"));
        int index = Integer.parseInt(request.getParameter("index"));
        List<Manual> list = getManual(id);
        Manual manual = list.get(0);
        String result = "";
        try {
            if (manual.getFile_path().split(",").length >= index + 1) {

            }
            File file = new File(manual.getFile_path().split(",")[index]);
            if (file.exists()) {
                file.delete();
            }
            List<String> file_name = new ArrayList<String>();
            List<String> file_path = new ArrayList<String>();
            String manual_file_name = "";
            String manual_file_path = "";
            for (int i = 0; i < manual.getFile_name().split(",").length; i++) {
                if (i != index) {
                    file_name.add(manual.getFile_name().split(",")[i]);
                }
            }
            for (int i = 0; i < manual.getFile_path().split(",").length; i++) {
                if (i != index) {
                    file_path.add(manual.getFile_path().split(",")[i]);
                }
            }
            for (int i = 0; i < file_name.size(); i++) {
                manual_file_name += file_name.get(i) + ",";
            }
            for (int i = 0; i < file_path.size(); i++) {
                manual_file_path += file_path.get(i) + ",";
            }

            if (StringUtils.isBlank(manual_file_path) != true && StringUtils.isBlank(manual_file_name) != true) {
                APIUtil.callAPI(Constants.VPCC_API_LINK + "/manual/removeFile", String.valueOf(id) + ";" + manual_file_name.substring(0, manual_file_name.length() - 1) + ";" + manual_file_path.substring(0, manual_file_path.length() - 1));
            } else {
                APIUtil.callAPI(Constants.VPCC_API_LINK + "/manual/removeFile", String.valueOf(id) + ";" + " " + ";" + " ");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        result = "ok";
        return new JSONObject().put("ok", result).toString();
    }

    @RequestMapping(value = "/download/{id}/{index}", method = RequestMethod.GET)
    public void downloadFile(@PathVariable("id") Long aid, @PathVariable("index") int index, HttpServletRequest request, HttpServletResponse response) {
        try {
            if (!(ValidationPool.checkRoleDetail(request, "08", Constants.AUTHORITY_XEM))) {
                return;
            }
            String[] file_path = null;
            String[] file_name = null;
            List<Manual> list = getManual(aid);
            Manual manual = list.get(0);
            if (manual != null) {
                if (!StringUtils.isBlank(manual.getFile_path())) {
                    file_path = manual.getFile_path().split(",");
                }
                if (!StringUtils.isBlank(manual.getFile_name())) {
                    file_name = manual.getFile_name().split(",");
                }
            }
            if (file_path.length > 0) {
                File file = new File(file_path[index]);
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
                response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file_name[index] + "\""));
                response.setContentLength((int) file.length());
                InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
                FileCopyUtils.copy(inputStream, response.getOutputStream());
                inputStream.close();
            } else {
                String errorMessage = "Sorry. The file you are looking for does not exist";
                OutputStream outputStream = response.getOutputStream();
                outputStream.write(errorMessage.getBytes());
                outputStream.close();
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/remove/{id}", method = RequestMethod.GET)
    public String remove(@PathVariable(value = "id") Long id, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
        if (!ValidationPool.checkRoleDetail(request, "08", Constants.AUTHORITY_XOA)) return "redirect:/404";
        APIUtil.callAPI(Constants.VPCC_API_LINK + "/manual/deleteById", String.valueOf(id));
        List<Manual> list = getManual(id);
        if (list.size() <= 0) {
            redirectAttributes.addFlashAttribute("successCode", "Xóa thông tin tài liệu thành công");
            return "redirect:/manual/list/";
        } else {
            redirectAttributes.addFlashAttribute("errorCode", "Xóa thông tin tài liệu không thành công !");
            return "redirect:/manual/list/";
        }
    }
}

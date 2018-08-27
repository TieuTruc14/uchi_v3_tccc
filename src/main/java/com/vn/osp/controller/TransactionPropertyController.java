package com.vn.osp.controller;

import com.vn.osp.common.global.Constants;
import com.vn.osp.common.util.EditString;
import com.vn.osp.common.util.ValidationPool;
import com.vn.osp.context.CommonContext;
import com.vn.osp.elasticsearch.AddResponse;
import com.vn.osp.elasticsearch.ESQueryFactory;
import com.vn.osp.modelview.*;
import com.vn.osp.service.QueryFactory;
import com.vn.osp.service.STPQueryFactory;
import com.vn.osp.task.SearchCallable;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;

/**
 * Created by tranv on 12/20/2016.
 */
@Controller
@RequestMapping("/transaction")
public class TransactionPropertyController {
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ModelAndView search(PreventContractList preventContractList, HttpServletRequest request) {
        if (!ValidationPool.checkRoleDetail(request, "10", Constants.AUTHORITY_XEM))
            return new ModelAndView("/404", "message", null);
        HttpSession session = request.getSession();
        String page = request.getParameter("page");
        String tab = request.getParameter("tab");
        preventContractList.setUser_id(((CommonContext) session.getAttribute(request.getSession().getId())).getUser().getUserId());
        preventContractList.setNotary_office_code(CommonContext.authentication_id);
        preventContractList = STPQueryFactory.getPreventContractList(preventContractList.generateJson());
        String isAdvance = null;
        if (preventContractList != null) {
            isAdvance = preventContractList.getIsAdvanceSearch();
            if (preventContractList.getHopDongList().size() > 0) {
                for (int i = 0; i < preventContractList.getHopDongList().size(); i++) {
                    String property_info = preventContractList.getHopDongList().get(i).getProperty_info();
                    String transaction_contend = preventContractList.getHopDongList().get(i).getTransaction_content();
                    if (preventContractList.getHopDongList().get(i).getProperty_info() != null && preventContractList.getHopDongList().get(i).getProperty_info().length() > 0) {
                        preventContractList.getHopDongList().get(i).setProperty_info("<div>Tài sản: " + preventContractList.getHopDongList().get(i).getProperty_info() + "</div>");
                    }
                    if (preventContractList.getHopDongList().get(i).getTransaction_content() != null && preventContractList.getHopDongList().get(i).getTransaction_content().length() > 0) {
                        preventContractList.getHopDongList().get(i).setProperty_info(preventContractList.getHopDongList().get(i).getProperty_info() + "Nội dung hợp đồng:" + preventContractList.getHopDongList().get(i).getTransaction_content());
                    }
                    if (preventContractList.getHopDongList().get(i).getProperty_info() != null && preventContractList.getHopDongList().get(i).getProperty_info().length() > 0) {
                        preventContractList.getHopDongList().get(i).setTransaction_content("<div class='title-green'>Tài sản:</div>" + property_info);
                    }
                    if (preventContractList.getHopDongList().get(i).getTransaction_content() != null && preventContractList.getHopDongList().get(i).getTransaction_content().length() > 0) {
                        preventContractList.getHopDongList().get(i).setTransaction_content(preventContractList.getHopDongList().get(i).getTransaction_content() + "<div class='title-green'>Nội dung hợp đồng:</div>" + transaction_contend);
                    }

                    if (preventContractList.getHopDongList().get(i).getMortage_cancel_flag() != null) {
                        if (preventContractList.getHopDongList().get(i).getMortage_cancel_flag() == 1) {
                            /*if(!StringUtils.isBlank(preventContractList.getHopDongList().get(i).getProperty_info())){
                                preventContractList.getHopDongList().get(i).setProperty_info(preventContractList.getHopDongList().get(i).getProperty_info() + "<div>Tình trạng: <span style='color:#9c3328;font-weight: bold'>Đã giải chấp ngày" + preventContractList.getHopDongList().get(i).getMortage_cancel_date()+"</span></div>");
                            }else{
                                preventContractList.getHopDongList().get(i).setProperty_info("<div>Tình trạng:<span style='color:#9c3328;font-weight: bold'> Đã giải chấp ngày " + preventContractList.getHopDongList().get(i).getMortage_cancel_date() + "</span></div>");
                            }*/
                            if(!StringUtils.isBlank(preventContractList.getHopDongList().get(i).getTransaction_content())){
                                preventContractList.getHopDongList().get(i).setTransaction_content(preventContractList.getHopDongList().get(i).getTransaction_content() + "<div><span class='title-green'>Tình trạng: </span>Đã giải chấp ngày </div>" + preventContractList.getHopDongList().get(i).getMortage_cancel_date());
                            }else{
                                preventContractList.getHopDongList().get(i).setTransaction_content("<div><span class='title-green'>Tình trạng: </span>Đã giải chấp ngày </div>" + preventContractList.getHopDongList().get(i).getMortage_cancel_date());
                            }

                        }
                    }
                }
            }

            if (preventContractList.getDaDuyetList().size() > 0) {
                for (int i = 0; i < preventContractList.getDaDuyetList().size(); i++) {
                    String propertyInfo = getProperty_info(preventContractList.getDaDuyetList().get(i));
                    preventContractList.getDaDuyetList().get(i).setProperty_info(propertyInfo);
                }
            }
        }

        if (isAdvance == null) isAdvance = "false";
        String listKey = "";
        if (isAdvance.equals("true")) {
            String propertyInfo = preventContractList.getPropertyInfo();
            String ownerInfo = preventContractList.getOwnerInfo();

            if ((propertyInfo != null && !propertyInfo.equals("")) || (ownerInfo != null && !ownerInfo.equals(""))) {
                if (propertyInfo != null && !propertyInfo.equals("")) {
                    listKey += propertyInfo;
                }
                if (ownerInfo != null && !ownerInfo.equals("")) {
                    listKey += " " + ownerInfo;
                    listKey.trim();
                }
                preventContractList.setListKey(EditString.parseKeySearchToJson(listKey));
            }
        } else {
            if (preventContractList != null) {
                String stringKey = preventContractList.getStringKey();
                //remove dau nhay
                if (stringKey != null && !stringKey.equals("")) {
                    listKey += stringKey;
                    listKey.trim();
                    preventContractList.setListKey(EditString.parseKeySearchToJson(listKey));
                }
            }

        }
        if (!StringUtils.isBlank(tab)) {
            if (Integer.valueOf(tab) == 2) {
                preventContractList.setDefaultTabOpen(2);
                if (!StringUtils.isBlank(page)) {
                    preventContractList.setHopDongPage(Integer.parseInt(page));
                }
            } else if (Integer.valueOf(tab) == 1) {
                preventContractList.setDefaultTabOpen(1);
                if (!StringUtils.isBlank(page)) {
                    preventContractList.setDaDuyetPage(Integer.parseInt(page));
                }
            }
        }
        session.setAttribute("PreventContractList", preventContractList);
        return new ModelAndView("/contract/CTR001", "preventContractList", preventContractList);
    }

    @RequestMapping(value = "/multi-search", method = RequestMethod.GET)
    public ModelAndView searchMultithread(PreventContractList preventContractList, HttpServletRequest request) {
        if (!ValidationPool.checkRoleDetail(request, "25", Constants.AUTHORITY_XEM))
            return new ModelAndView("/404", "message", null);
        HttpSession session = request.getSession();
        String isAdvance = "false";
        isAdvance = preventContractList.getIsAdvanceSearch();
        if (isAdvance == null) isAdvance = "false";
        if (isAdvance.equals("true")) {

            String propertyInfo = preventContractList.getPropertyInfo();
            String ownerInfo = preventContractList.getOwnerInfo();
            String listKey = "";
            if ((propertyInfo != null && !propertyInfo.equals("")) || (ownerInfo != null && !ownerInfo.equals(""))) {
                if (propertyInfo != null && !propertyInfo.equals("")) {
                    listKey += propertyInfo;
                }
                if (ownerInfo != null && !ownerInfo.equals("")) {
                    listKey += " " + ownerInfo;
                    listKey.trim();

                }
                preventContractList.setListKey(EditString.parseKeySearchToJson(listKey));
            }


            preventContractList.setStringKey("");
        } else {
            preventContractList.setPropertyInfo("");
            preventContractList.setOwnerInfo("");
        }
        preventContractList.setUser_id(((CommonContext) session.getAttribute(request.getSession().getId())).getUser().getUserId());
        preventContractList.setNotary_office_code(CommonContext.authentication_id);
        //preventContractList = STPQueryFactory.getPreventContractList(preventContractList.generateJson());

        Queue queue = new LinkedList();
        queue.add(Constants.STP_API_LINK + "/search/transaction");
        /*queue.add("http://localhost:8082/api/search/transaction");
        queue.add("http://localhost:8083/api/search/transaction");
        queue.add("http://localhost:8084/api/search/transaction");
        queue.add("http://localhost:8085/api/search/transaction");
        queue.add("http://localhost:8086/api/search/transaction");
        queue.add("http://localhost:8087/api/search/transaction");
        queue.add("http://localhost:8088/api/search/transaction");
        queue.add("http://localhost:8089/api/search/transaction");
        queue.add("http://localhost:8090/api/search/transaction");*/
        ExecutorService pool = Executors.newFixedThreadPool(3);

        ArrayList<Future<PreventContractList>> list = new ArrayList();
        Iterator iterator = queue.iterator();
        while (iterator.hasNext()) {
            Callable callable = null;
            callable = new SearchCallable(preventContractList, queue);
            Future<PreventContractList> future = pool.submit(callable);
            try {
                if (future.get() instanceof PreventContractList) list.add(future);
                else list.add(null);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        pool.shutdown();
        List<DataPreventInfor> daDuyetList = new ArrayList<DataPreventInfor>();
        List<TransactionProperty> hopDongList = new ArrayList<TransactionProperty>();
        int daDuyetTotalPage = 0;
        int hopDongTotalPage = 0;
        int daDyetPage = 1;
        int hopDongPage = 1;
        int defaultTabOpen = 1;
        int daDuyetListNumber = 0;
        int hopDongListNumber = 0;

        for (int i = 0; i < list.size(); i++) {
            try {
                if (list.get(i) != null) {
                    daDuyetList.addAll(list.get(i).get().getDaDuyetList());
                    hopDongList.addAll(list.get(i).get().getHopDongList());
                    daDuyetTotalPage = list.get(i).get().getDaDuyetTotalPage();
                    hopDongTotalPage = list.get(i).get().getHopDongTotalPage();
                    daDyetPage = list.get(i).get().getDaDuyetPage();
                    hopDongPage = list.get(i).get().getHopDongPage();
                    defaultTabOpen = list.get(i).get().getDefaultTabOpen();
                    daDuyetListNumber += list.get(i).get().getDaDuyetListNumber();
                    hopDongListNumber += list.get(i).get().getHopDongListNumber();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        preventContractList.setDaDuyetList(daDuyetList);
        preventContractList.setHopDongList(hopDongList);
        preventContractList.setDaDuyetListNumber(daDuyetListNumber);
        preventContractList.setHopDongListNumber(hopDongListNumber);
        preventContractList.setDefaultTabOpen(1);
        if (preventContractList != null && preventContractList.getStringKey() != null && !preventContractList.getStringKey().equals(""))
            preventContractList.setListKey(EditString.parseKeySearchToJson(preventContractList.getStringKey()));
        preventContractList.setDaDuyetTotalPage(daDuyetTotalPage);
        preventContractList.setHopDongTotalPage(hopDongTotalPage);
        preventContractList.setDaDuyetPage(daDyetPage);
        preventContractList.setHopDongPage(hopDongPage);
        preventContractList.setDefaultTabOpen(defaultTabOpen);

        session.setAttribute("PreventContractList", preventContractList);
        return new ModelAndView("/contract/CTR001_", "preventContractList", preventContractList);

        //return new ModelAndView("/contract/CTR001", "preventContractList", preventContractList);

    }


    @RequestMapping(value = "/advance-search", method = RequestMethod.POST)
    public String advanceSearch(AdvanceSearchForm advanceSearchForm, HttpServletRequest request) {
//        if(!checkSearchRole(request)) return "/404";
//        request.getSession().setAttribute("AdvanceSearchForm", advanceSearchForm);
        return "redirect:/search/list/1/1";
    }

    @RequestMapping(value = "/detail/{tpid}", method = RequestMethod.GET)
    public ModelAndView transactionDetail(@PathVariable("tpid") Long tpid, HttpServletRequest request) {
        if (!ValidationPool.checkRoleDetail(request, "10", Constants.AUTHORITY_XEM)) {
            return new ModelAndView("/404");
        }
        String page = request.getParameter("page");
        String tab = request.getParameter("tab");
        ModelAndView view = new ModelAndView();
        TransactionProperty transactionProperty = STPQueryFactory.getTransactionPropertyById(tpid);
        if (!StringUtils.isBlank(transactionProperty.getRelation_object())) {
            transactionProperty.setRelation_object(transactionProperty.getRelation_object().replaceAll("\\\n", "<br>"));
        } else {
            transactionProperty.setRelation_object("");
        }
        if (transactionProperty == null) {
            view.addObject("errorCode", "Không tìm thấy hợp đồng !");
            view.setViewName("/contract/search");
            return view;
        } else {
            view.setViewName("/contract/CTR002");
            view.addObject("transactionProperty", transactionProperty);
            view.addObject("page", page);
            view.addObject("tab", tab);
            return view;
        }
    }

    @RequestMapping(value = "/prevent-detail/{id}", method = RequestMethod.GET)
    public ModelAndView preventDetail(@PathVariable("id") Long id, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        if (!ValidationPool.checkRoleDetail(request, "10", Constants.AUTHORITY_XEM)) {
            return new ModelAndView("/404");
        }
        ModelAndView view = new ModelAndView();
        String[] listFileRelease = null;
        String[] listFileName = null;
        String page = request.getParameter("page");
        String tab = request.getParameter("tab");
        DataPreventInfor dataPreventInfor = STPQueryFactory.getDataPreventInforById(id);
        try {
            if (dataPreventInfor != null) {
                dataPreventInfor.setPreventHistories(STPQueryFactory.getPreventHistoryList(id));
                dataPreventInfor.setRelease_file_name(EditString.convertUnicodeToASCII(dataPreventInfor.getRelease_file_name()));
                if (!StringUtils.isBlank(dataPreventInfor.getPrevent_file_name())) {
                    listFileName = dataPreventInfor.getPrevent_file_name().split(",");
                }
                if (!StringUtils.isBlank(dataPreventInfor.getRelease_file_name())) {
                    listFileRelease = dataPreventInfor.getRelease_file_name().split(",");
                }
            } else {
                redirectAttributes.addFlashAttribute("msg", "Thông tin ngăn chặn không tồn tại!");
                return new ModelAndView("redirect:/transaction/search");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        view.addObject("dataPreventInfor", dataPreventInfor);
        view.addObject("listFileName", listFileName);
        view.addObject("listFileRelease", listFileRelease);
        view.addObject("page", page);
        view.addObject("tab", tab);
        view.setViewName("/prevent/PRV004");
        return view;
    }

    @RequestMapping(value = "/prevent/download-prevent/{preventId}/{index}", method = RequestMethod.GET)
    public void downloadFile(@PathVariable("preventId") Long preventId, @PathVariable("index") int index, HttpServletRequest request, HttpServletResponse response) {
        try {
            DataPreventInfor dataPreventInfor = STPQueryFactory.getDataPreventInforById(preventId);
            String[] prevent_file_path = dataPreventInfor.getPrevent_file_path().split(",");
            String[] prevent_file_name = dataPreventInfor.getPrevent_file_name().split(",");
            File file = new File(prevent_file_path[index]);
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
            response.setHeader("Content-Disposition", String.format("inline; filename=\"" + prevent_file_name[index] + "\""));
            response.setContentLength((int) file.length());
            InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
            FileCopyUtils.copy(inputStream, response.getOutputStream());
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/print-view", method = RequestMethod.GET)
    public ModelAndView printView(HttpServletRequest request) {
        if (!ValidationPool.checkRoleDetail(request, "10", Constants.AUTHORITY_IN)) {
            return new ModelAndView("/404");
        }
        PreventContractList preventContractList = (PreventContractList) request.getSession().getAttribute("PreventContractList");

        return new ModelAndView("/contract/CTR003", "preventContractList", preventContractList);
    }


    public String getProperty_info(DataPreventInfor dataPreventInfor) {
        StringBuilder result = new StringBuilder();
        if (dataPreventInfor.getType().equals(Constants.NHA_DAT)) {
            if (dataPreventInfor.getLand_address() != null && !dataPreventInfor.getLand_address().equals("")) {
                result.append("Địa chỉ : ").append(dataPreventInfor.getLand_address()).append("; ");
            } else {
                result.append(dataPreventInfor.getProperty_info()).append("; ");
            }
            if (dataPreventInfor.getLand_certificate() != null && !dataPreventInfor.getLand_certificate().equals(""))
                result.append("Số giấy chứng nhận : ").append(dataPreventInfor.getLand_certificate()).append("; ");
            if (dataPreventInfor.getLand_issue_place() != null && !dataPreventInfor.getLand_issue_place().equals(""))
                result.append("Nơi cấp : ").append(dataPreventInfor.getLand_issue_place()).append("; ");
            if (dataPreventInfor.getLand_issue_date() != null && !dataPreventInfor.getLand_issue_date().equals(""))
                result.append("Ngày cấp : ").append(dataPreventInfor.getLand_issue_date()).append("; ");
            if (dataPreventInfor.getLand_paper_number() != null && !dataPreventInfor.getLand_paper_number().equals(""))
                result.append("Số giấy vào sổ : ").append(dataPreventInfor.getLand_paper_number()).append("; ");
            if (dataPreventInfor.getLand_number() != null && !dataPreventInfor.getLand_number().equals(""))
                result.append("Thửa đất số : ").append(dataPreventInfor.getLand_number()).append("; ");
            if (dataPreventInfor.getLand_map_number() != null && !dataPreventInfor.getLand_map_number().equals(""))
                result.append("Tờ bản đồ số : ").append(dataPreventInfor.getLand_map_number()).append("; ");
            if (dataPreventInfor.getLand_area() != null && !dataPreventInfor.getLand_area().equals(""))
                result.append("Diện tích (m2) : ").append(dataPreventInfor.getLand_area()).append("; ");
            if (dataPreventInfor.getLand_private_area() != null && !dataPreventInfor.getLand_private_area().equals(""))
                result.append("Diện tích sử dụng riêng : ").append(dataPreventInfor.getLand_private_area()).append("; ");
            if (dataPreventInfor.getLand_public_area() != null && !dataPreventInfor.getLand_public_area().equals(""))
                result.append("Diện tích sử dụng chung : ").append(dataPreventInfor.getLand_public_area()).append("; ");
            if (dataPreventInfor.getLand_use_purpose() != null && !dataPreventInfor.getLand_use_purpose().equals(""))
                result.append("Mục đích sử dụng : ").append(dataPreventInfor.getLand_use_purpose()).append("; ");
            if (dataPreventInfor.getLand_use_period() != null && !dataPreventInfor.getLand_use_period().equals(""))
                result.append("Thời hạn sử dụng : ").append(dataPreventInfor.getLand_use_period()).append("; ");
            if (dataPreventInfor.getLand_use_origin() != null && !dataPreventInfor.getLand_use_origin().equals(""))
                result.append("Nguồn gốc sử dụng đất : ").append(dataPreventInfor.getLand_use_origin()).append("; ");
            if (dataPreventInfor.getLand_associate_property() != null && !dataPreventInfor.getLand_associate_property().equals(""))
                result.append("Tài sản gắn liền với đất : ").append(dataPreventInfor.getLand_associate_property()).append("; ");
            if (dataPreventInfor.getOwner_info() != null && !dataPreventInfor.getOwner_info().equals(""))
                result.append("Thông tin chủ sở hữu : ").append(dataPreventInfor.getOwner_info()).append("; ");
            if (dataPreventInfor.getOther_info() != null && !dataPreventInfor.getOther_info().equals(""))
                result.append("Thông tin khác : ").append(dataPreventInfor.getOther_info()).append("; ");
        }
        if (dataPreventInfor.getType().equals(Constants.OTO_XEMAY)) {
            if (dataPreventInfor.getCar_license_number() != null && !dataPreventInfor.getCar_license_number().equals(""))
                result.append("Biển kiểm soát : ").append(dataPreventInfor.getCar_license_number()).append("; ");
            if (dataPreventInfor.getCar_regist_number() != null && !dataPreventInfor.getCar_regist_number().equals(""))
                result.append("Số giấy đăng ký : ").append(dataPreventInfor.getCar_regist_number()).append("; ");
            if (dataPreventInfor.getCar_issue_place() != null && !dataPreventInfor.getCar_issue_place().equals(""))
                result.append("Nơi cấp : ").append(dataPreventInfor.getCar_issue_place()).append("; ");
            if (dataPreventInfor.getCar_issue_date() != null && !dataPreventInfor.getCar_issue_date().equals(""))
                result.append("Ngày cấp : ").append(dataPreventInfor.getCar_issue_date()).append("; ");
            if (dataPreventInfor.getCar_frame_number() != null && !dataPreventInfor.getCar_frame_number().equals(""))
                result.append("Số khung : ").append(dataPreventInfor.getCar_frame_number()).append("; ");
            if (dataPreventInfor.getCar_machine_number() != null && !dataPreventInfor.getCar_machine_number().equals("")) {
                result.append("Số máy : ").append(dataPreventInfor.getCar_machine_number()).append("; ");
            } else {
                result.append(dataPreventInfor.getProperty_info()).append(";");
            }

            if (dataPreventInfor.getOwner_info() != null && !dataPreventInfor.getOwner_info().equals(""))
                result.append("Thông tin chủ sở hữu : ").append(dataPreventInfor.getOwner_info()).append("; ");
            if (dataPreventInfor.getOther_info() != null && !dataPreventInfor.getOther_info().equals(""))
                result.append("Thông tin khác : ").append(dataPreventInfor.getOther_info()).append("; ");
        }
        if (dataPreventInfor.getType().equals(Constants.TAI_SAN_KHAC)) {
            if (dataPreventInfor.getProperty_info() != null && !dataPreventInfor.getProperty_info().equals(""))
                result.append("Thông tin tài sản : ").append(dataPreventInfor.getProperty_info()).append("; ");
            if (dataPreventInfor.getOwner_info() != null && !dataPreventInfor.getOwner_info().equals(""))
                result.append("Thông tin chủ sở hữu : ").append(dataPreventInfor.getOwner_info()).append("; ");
            if (dataPreventInfor.getOther_info() != null && !dataPreventInfor.getOther_info().equals(""))
                result.append("Thông tin khác : ").append(dataPreventInfor.getOther_info()).append("; ");
        }
        return result.toString();
    }
}

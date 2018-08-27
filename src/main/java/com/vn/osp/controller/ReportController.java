package com.vn.osp.controller;

import com.vn.osp.common.global.Constants;
import com.vn.osp.common.util.*;
import com.vn.osp.context.CommonContext;
import com.vn.osp.modelview.*;
import com.vn.osp.service.QueryFactory;
import net.sf.jett.transform.ExcelTransformer;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.annotation.*;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.ArrayUtil;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by minh on 5/16/2017.
 */
@Controller
@RequestMapping("/report")
public class ReportController {

    public Boolean checkGroupRole(HttpServletRequest request) {
        HttpSession session = request.getSession();
        CommonContext context = ((CommonContext) request.getSession().getAttribute(request.getSession().getId()));
        List<GrouproleAuthority> grouproleAuthorities = context.getGrouproleAuthorities();
        for (int i = 0; i < grouproleAuthorities.size(); i++) {
            int temp = grouproleAuthorities.get(i).getValue() & Constants.AUTHORITY_XEM;
            String authority_code = grouproleAuthorities.get(i).getAuthority_code();
            if (temp > 0 && authority_code.equals("19")) return true;
        }
        return false;
    }

    //MinhBQ
    @RequestMapping(value = "/group-bank", method = RequestMethod.GET)
    public ModelAndView BankGroup(ReportByBankForm reportByBankForm, HttpServletRequest request, HttpServletResponse response ) {
        //if (!checkGroupRole(request)) return new ModelAndView("/404", "message", null);
        if(!ValidationPool.checkRoleDetail(request,"24",Constants.AUTHORITY_XEM)) return new ModelAndView("/404");
        HttpSession session = request.getSession();
        ModelAndView view = new ModelAndView();
        session.removeAttribute(ReportByBankForm.SESSION_KEY);
        if(reportByBankForm.getTimeType()== null){
            reportByBankForm.setTimeType("03");
            ValidateUtil.validate_msg_from_date ="";
            ValidateUtil.validate_msg_to_date ="";
        }

        ValidateUtil.validate_msg_from_date ="";
        ValidateUtil.validate_msg_to_date ="";
        ValidateUtil.validateDatefrom(reportByBankForm.getFromDate());
        ValidateUtil.validateDateto(reportByBankForm.getToDate());


        if(!StringUtils.isBlank(reportByBankForm.getTimeType())) {
            if(reportByBankForm.getTimeType().equals("05")) {
                if (!StringUtils.isBlank(reportByBankForm.getFromDate())) {
                    if (!StringUtils.isBlank(reportByBankForm.getToDate())) {
                        Date fromDate = TimeUtil.stringToDate(reportByBankForm.getFromDate());
                        Date toDate = TimeUtil.stringToDate(reportByBankForm.getToDate());
                        if(fromDate.getTime() > toDate.getTime()){
                            view.setViewName("/contract/CTR005");
                            view.addObject("reportByBankForm", reportByBankForm);
                            ValidateUtil.validate_msg_from_date = "Từ ngày không được lớn hơn Đến ngày !";
                            view.addObject("validate_msg_from_date", ValidateUtil.validate_msg_from_date);
                            return view;
                        }
                        if (ValidateUtil.validateDatefrom(reportByBankForm.getFromDate()) == false || ValidateUtil.validateDateto(reportByBankForm.getToDate()) == false) {
                            view.setViewName("/contract/CTR005");
                            view.addObject("reportByBankForm", reportByBankForm);
                            view.addObject("validate_msg_from_date", ValidateUtil.validate_msg_from_date);
                            view.addObject("validate_msg_to_date", ValidateUtil.validate_msg_to_date);
                            return view;
                        }
                    } else {
                        Integer year = (Integer) Calendar.getInstance().get(Calendar.YEAR);
                        Integer month = (Integer) Calendar.getInstance().get(Calendar.MONTH)+1;
                        Integer date = (Integer) Calendar.getInstance().get(Calendar.DATE);
                        String toDate = date.toString() + "/" + month.toString() + "/" + year.toString();
                        reportByBankForm.setToDate(toDate);
                        if(ValidateUtil.validateDatefrom(reportByBankForm.getFromDate())== false){
                            view.setViewName("/contract/CTR005");
                            view.addObject("reportByBankForm",reportByBankForm);
                            view.addObject("validate_msg_from_date",ValidateUtil.validate_msg_from_date);
                            return view;
                        }
                    }
                } else {
                    view.setViewName("/contract/CTR005");
                    view.addObject("validate_msg_from_date", "Trường từ ngày không được để trống !");
                    return view;
                }
            }
        }
        reportByBankForm.createFromToDate();

        int bankListNumber = 1;
        int bankTotalPage = 1;
        int page = 1;
        if (reportByBankForm != null) {
            bankListNumber = reportByBankForm.getTotal();
            bankTotalPage = reportByBankForm.getTotalPage();
            page = reportByBankForm.getPage();

        }

        List<Bank> bank = QueryFactory.getBankList(null);
        reportByBankForm.setBankList(bank);



        String orderFilter="";
        orderFilter = reportByBankForm.getOrderString();
        bankListNumber = QueryFactory.countTotalReportBankDetail(orderFilter);
        bankTotalPage = QueryFactory.countPage(bankListNumber);
        reportByBankForm.setTotal(bankListNumber);
        reportByBankForm.setTotalPage(bankTotalPage);

        if (page < 1) page = 1;
        if (bankListNumber < 1) bankListNumber = 1;
        if (bankTotalPage<1) bankTotalPage =1;
        if (page > bankTotalPage) page = bankTotalPage;

        reportByBankForm.setPage(page);
        orderFilter += "LIMIT " + ((page-1) * Constants.ROW_PER_PAGE)+",20";
        List<ReportByBankDetail> reportByBanks = QueryFactory.getReportBankDetail(page,orderFilter);

        reportByBankForm.setReportByBankDetails(reportByBanks);

        reportByBankForm.setBankList(bank);

        session.setAttribute(ReportByBankForm.SESSION_KEY, reportByBankForm);
        view.setViewName("/contract/CTR005");
        if(!reportByBankForm.getTimeType().equals("05")){
            reportByBankForm.setFromDate("");
            reportByBankForm.setToDate("");
        }

        if(reportByBankForm != null){
            if(reportByBankForm.getReportByBankDetails().size() > 0){
                for(int i = 0 ; i< reportByBankForm.getReportByBankDetails().size() ;i++){
                    if(!StringUtils.isBlank(reportByBankForm.getReportByBankDetails().get(i).getTransaction_content()) || !StringUtils.isBlank(reportByBankForm.getReportByBankDetails().get(i).getProperty_info())){
                        if(!StringUtils.isBlank(reportByBankForm.getReportByBankDetails().get(i).getTransaction_content())){
                            reportByBankForm.getReportByBankDetails().get(i).setTransaction_content("Nội dung hợp đồng:<br>"+reportByBankForm.getReportByBankDetails().get(i).getTransaction_content());
                        }
                        if(!StringUtils.isBlank(reportByBankForm.getReportByBankDetails().get(i).getProperty_info())){
                            if(!StringUtils.isBlank(reportByBankForm.getReportByBankDetails().get(i).getTransaction_content())){
                                reportByBankForm.getReportByBankDetails().get(i).setTransaction_content(reportByBankForm.getReportByBankDetails().get(i).getTransaction_content() + "<br>Thông tin tài sản:<br>"+reportByBankForm.getReportByBankDetails().get(i).getProperty_info());
                            }else{
                                reportByBankForm.getReportByBankDetails().get(i).setTransaction_content("<br>Thông tin tài sản:<br>"+reportByBankForm.getReportByBankDetails().get(i).getProperty_info());
                            }
                        }
                    }
                }
            }
        }
        view.addObject("reportByBankForm",reportByBankForm);
        view.addObject("validate_msg_from_date",ValidateUtil.validate_msg_from_date);
        view.addObject("validate_msg_to_date",ValidateUtil.validate_msg_to_date);
        return view;
    }
    @RequestMapping(value = "/export-bank", method = RequestMethod.GET)
    public void exportBankReport(HttpServletRequest request, HttpServletResponse response ) {
        try {
            HttpSession session = request.getSession();
            ReportByBankForm reportByBankForm = (ReportByBankForm) session.getAttribute(ReportByBankForm.SESSION_KEY);
            List<Bank> bank = QueryFactory.getBankList(null);


            List<ReportByBankDetail> allReportByBank = QueryFactory.getAllReportByBank(reportByBankForm.getOrderString());
            String test = "";
            if(allReportByBank !=null){
                int i = allReportByBank.size();
                for(int j=0; j<i;j++){
                    if(!StringUtils.isBlank(allReportByBank.get(j).getRelation_object())){
                    allReportByBank.get(j).setRelation_object(allReportByBank.get(j).getRelation_object().replaceAll("\\\\r\\\\n|\\\\n","\n"));
                    }

                }
            }
            if(reportByBankForm != null){
                if(reportByBankForm.getReportByBankDetails().size() > 0){
                    for(int i = 0 ; i< reportByBankForm.getReportByBankDetails().size() ;i++){
                        if(!StringUtils.isBlank(reportByBankForm.getReportByBankDetails().get(i).getTransaction_content()) || !StringUtils.isBlank(reportByBankForm.getReportByBankDetails().get(i).getProperty_info())){
                            if(!StringUtils.isBlank(reportByBankForm.getReportByBankDetails().get(i).getTransaction_content())){
                                reportByBankForm.getReportByBankDetails().get(i).setTransaction_content("Nội dung hợp đồng:<br>"+reportByBankForm.getReportByBankDetails().get(i).getTransaction_content());
                            }
                            if(!StringUtils.isBlank(reportByBankForm.getReportByBankDetails().get(i).getProperty_info())){
                                if(!StringUtils.isBlank(reportByBankForm.getReportByBankDetails().get(i).getTransaction_content())){
                                    reportByBankForm.getReportByBankDetails().get(i).setTransaction_content(reportByBankForm.getReportByBankDetails().get(i).getTransaction_content() + "<br>Thông tin tài sản:<br>"+reportByBankForm.getReportByBankDetails().get(i).getProperty_info());
                                }else{
                                    reportByBankForm.getReportByBankDetails().get(i).setTransaction_content("<br>Thông tin tài sản:<br>"+reportByBankForm.getReportByBankDetails().get(i).getProperty_info());
                                }
                            }
                        }
                    }
                }
            }
            if(allReportByBank != null){
                if(allReportByBank.size() > 0){
                    for(int i = 0 ; i< allReportByBank.size() ;i++){
                        if(!StringUtils.isBlank(allReportByBank.get(i).getTransaction_content()) || !StringUtils.isBlank(allReportByBank.get(i).getProperty_info())){
                            if(!StringUtils.isBlank(allReportByBank.get(i).getTransaction_content())){
                                allReportByBank.get(i).setTransaction_content("Nội dung hợp đồng: \n"+allReportByBank.get(i).getTransaction_content());
                            }
                            if(!StringUtils.isBlank(allReportByBank.get(i).getProperty_info())){
                                if(!StringUtils.isBlank(allReportByBank.get(i).getTransaction_content())){
                                    allReportByBank.get(i).setTransaction_content(allReportByBank.get(i).getTransaction_content() + "\nThông tin tài sản: \n"+allReportByBank.get(i).getProperty_info());
                                }else{
                                    allReportByBank.get(i).setTransaction_content("\nThông tin tài sản: \n"+allReportByBank.get(i).getProperty_info());
                                }
                            }
                        }
                    }
                }
            }
            Map<String, Object> beans = new HashMap<String, Object>();

            beans.put("report", allReportByBank);
            beans.put("total", allReportByBank.size());

            beans.put("fromDate", reportByBankForm.getPrintFromDate());
            beans.put("toDate", reportByBankForm.getPrintToDate());
            beans.put("agency", ((CommonContext)request.getSession().getAttribute(request.getSession().getId())).getAgency());
            beans.put("bank", reportByBankForm.getBankFilter());

            Date date = new Date(); // your date
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH);
            int day = cal.get(Calendar.DAY_OF_MONTH);

            beans.put("year", year);
            beans.put("month", month);
            beans.put("day", day);
            String realPathOfFolder=request.getServletContext().getRealPath(SystemProperties.getProperty("template_path"));
            InputStream fileIn = new BufferedInputStream(new FileInputStream(realPathOfFolder+"BaocaoHDCCtheoNH.xls"));

            ExcelTransformer transformer = new ExcelTransformer();
            Workbook workbook = transformer.transform(fileIn, beans);

            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment; filename=" + "BaocaoHDCCtheoNH.xls");
            ServletOutputStream out = response.getOutputStream();
            workbook.write(out);
            out.flush();
            out.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/group", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView forGroup_(ReportByGroupTotalList reportByGroupTotalList, HttpServletRequest request, HttpServletResponse response) {
        if (!ValidationPool.checkRoleDetail(request, "19", Constants.AUTHORITY_XEM)) return new ModelAndView("/404");
        HttpSession session = request.getSession();
        String kind_id = request.getParameter("nhomHD");
        session.removeAttribute(ReportByGroupTotalList.SESSION_KEY);
        List<ContractTemplate> contractTemplates = null;
        ModelAndView view = new ModelAndView();

        List<ContractKind> contractKinds = QueryFactory.getContractKind(" where 1=1");
        if (StringUtils.isBlank(kind_id) || Integer.parseInt(kind_id) <= 0) {
            contractTemplates = QueryFactory.getContractTemplate(" where 1=1 ");
        } else {
            contractTemplates = QueryFactory.getContractTemplate(" where code = " + kind_id);
        }

        reportByGroupTotalList.setContractKinds(contractKinds);
        reportByGroupTotalList.setContractTemplates(contractTemplates);

        if (reportByGroupTotalList.getTimeType() == null) {
            reportByGroupTotalList.setTimeType("03");
            ValidateUtil.validate_msg_from_date = "";
            ValidateUtil.validate_msg_to_date = "";
        }
        if (reportByGroupTotalList.getTimeType().equals("05")) {

                ValidateUtil.validate_msg_from_date = "";
                ValidateUtil.validate_msg_to_date = "";
            ValidateUtil.validateDateto(reportByGroupTotalList.getToDate());
            ValidateUtil.validateDatefrom(reportByGroupTotalList.getFromDate());

            if (!StringUtils.isBlank(reportByGroupTotalList.getFromDate())) {
                if (!StringUtils.isBlank(reportByGroupTotalList.getToDate())) {
                    if (ValidateUtil.validateDatefrom(reportByGroupTotalList.getFromDate()) == false || ValidateUtil.validateDateto(reportByGroupTotalList.getToDate()) == false) {
                        view.setViewName("/contract/CTR004");
                        view.addObject("reportByGroupTotalList", reportByGroupTotalList);
                        view.addObject("validate_msg_from_date", ValidateUtil.validate_msg_from_date);
                        view.addObject("validate_msg_to_date", ValidateUtil.validate_msg_to_date);
                        return view;
                    } else if (ValidateUtil.validateDatefrom(reportByGroupTotalList.getFromDate()) == true && ValidateUtil.validateDateto(reportByGroupTotalList.getToDate()) == true) {
                        if (TimeUtil.stringToDate(reportByGroupTotalList.getFromDate()).getTime() > TimeUtil.stringToDate(reportByGroupTotalList.getToDate()).getTime()) {
                            ValidateUtil.validate_msg_from_date = "Từ ngày không được lớn hơn Đến ngày ";
                            view.setViewName("/contract/CTR004");
                            view.addObject("reportByGroupTotalList", reportByGroupTotalList);
                            view.addObject("validate_msg_from_date", ValidateUtil.validate_msg_from_date);
                            view.addObject("validate_msg_to_date", ValidateUtil.validate_msg_to_date);
                            return view;
                        }
                    }
                } else {
                    Integer year = (Integer) Calendar.getInstance().get(Calendar.YEAR);
                    Integer month = (Integer) Calendar.getInstance().get(Calendar.MONTH) + 1;
                    Integer date = (Integer) Calendar.getInstance().get(Calendar.DATE);
                    String toDate = date.toString() + "/" + month.toString() + "/" + year.toString();
                    reportByGroupTotalList.setToDate(toDate);
                }
            } else {
                view.setViewName("/contract/CTR004");
                view.addObject("validate_msg_from_date", "Trường từ ngày không được để trống");
                return view;
            }
        }
        reportByGroupTotalList.createFromToDate();
        if (reportByGroupTotalList.getNhomHD() == null) reportByGroupTotalList.setNhomHD(String.valueOf(0));
        if (reportByGroupTotalList.getTenHD() == null) reportByGroupTotalList.setTenHD(String.valueOf(0));


        List<ReportByGroupTotal> reportByGroupTotals = QueryFactory.getReportTotalListByGroup(reportByGroupTotalList.generateJson());

        reportByGroupTotalList.setReportByGroupTotals(reportByGroupTotals);
        long total = 0;
        ArrayList<ReportByGroupTableView> reportByGroupTableViews = new ArrayList<ReportByGroupTableView>();
        for (int i = 0; i < reportByGroupTotals.size(); i++) {
            total += reportByGroupTotals.get(i).getTemplate_number().longValue();
        }
        reportByGroupTotalList.setTotal(total);

        session.setAttribute(ReportByGroupTotalList.SESSION_KEY, reportByGroupTotalList);
        if (!reportByGroupTotalList.getTimeType().equals("05")) {
            reportByGroupTotalList.setFromDate("");
            reportByGroupTotalList.setToDate("");
        }
        if (!reportByGroupTotalList.getTimeType().equals("05")) {
            reportByGroupTotalList.setFromDate("");
            reportByGroupTotalList.setToDate("");
            ValidateUtil.validate_msg_from_date = "";
            ValidateUtil.validate_msg_to_date = "";
        }
        view.setViewName("/contract/CTR004");
        view.addObject("reportByGroupTotalList", reportByGroupTotalList);
        view.addObject("validate_msg_from_date", ValidateUtil.validate_msg_from_date);
        view.addObject("validate_msg_to_date", ValidateUtil.validate_msg_to_date);
        return view;
    }

    @RequestMapping(value = "/group/export", method = RequestMethod.GET)
    public void groupExportExcel(HttpServletRequest request, HttpServletResponse response) {
        //if (!checkGroupRole(request)) return;
        try {
            HttpSession session = request.getSession();
            ReportByGroupTotalList reportByGroupTotalList = (ReportByGroupTotalList) session.getAttribute(ReportByGroupTotalList.SESSION_KEY);
            Map<String, Object> beans = new HashMap<String, Object>();

            beans.put("report", reportByGroupTotalList.getReportByGroupTotals());
            beans.put("total", reportByGroupTotalList.getTotal());
            beans.put("fromDate", reportByGroupTotalList.getPrintFromDate());
            beans.put("toDate", reportByGroupTotalList.getPrintToDate());
            beans.put("agency", ((CommonContext) request.getSession().getAttribute(request.getSession().getId())).getAgency());

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
            InputStream fileIn = new BufferedInputStream(new FileInputStream(realPathOfFolder + "BaocaotheonhomHD.xls"));
            ExcelTransformer transformer = new ExcelTransformer();
            Workbook workbook = transformer.transform(fileIn, beans);

            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment; filename=" + "BaocaotheonhomHD.xls");
            ServletOutputStream out = response.getOutputStream();
            workbook.write(out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/group-detail/export", method = RequestMethod.GET)
    public void groupdetailDetailExportExcel(HttpServletRequest request, HttpServletResponse response) {
        //if (!checkGroupRole(request)) return;
        try {
            HttpSession session = request.getSession();
            ReportByGroupTotalList reportByGroupTotalList = (ReportByGroupTotalList) session.getAttribute(ReportByGroupTotalList.SESSION_KEY);
            reportByGroupTotalList.setPage(0);
            List<TransactionProperty> dsHopDong = new ArrayList<TransactionProperty>();
            dsHopDong = QueryFactory.getReportDetailByGroup(reportByGroupTotalList.generateJson());


            reportByGroupTotalList.setContractList(dsHopDong);
            int hopDongListNumber = QueryFactory.countDetailReportByGroup(reportByGroupTotalList.generateJson());

            reportByGroupTotalList.setContractListNumber(hopDongListNumber);
            reportByGroupTotalList.setTotalPage(QueryFactory.countPage(hopDongListNumber));
            Map<String, Object> beans = new HashMap<String, Object>();
            List<TransactionProperty> contractList = reportByGroupTotalList.getContractList();
            if (contractList != null) {
                int j = contractList.size();
                for (int i = 0; i < j; i++) {
                    if (!StringUtils.isBlank(contractList.get(i).getRelation_object()))
                        contractList.get(i).setRelation_object(contractList.get(i).getRelation_object().replaceAll("\\\\r\\\\n|\\\\n", "\n"));
                }
            }
            reportByGroupTotalList.setContractList(contractList);
            beans.put("report", reportByGroupTotalList.getContractList());
            beans.put("total", reportByGroupTotalList.getContractListNumber());
            beans.put("fromDate", reportByGroupTotalList.getFromDate());
            beans.put("toDate", reportByGroupTotalList.getToDate());
            beans.put("agency", ((CommonContext) request.getSession().getAttribute(request.getSession().getId())).getAgency());
            beans.put("contract_kind", reportByGroupTotalList.getNhomHDChiTiet());

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
            InputStream fileIn = null;
            if (reportByGroupTotalList.getSource().equals("2")) {
                fileIn = new BufferedInputStream(new FileInputStream(realPathOfFolder + "BaocaoHDCCDetail.xls"));

            }
            if (reportByGroupTotalList.getSource().equals("3")) {
                fileIn = new BufferedInputStream(new FileInputStream(realPathOfFolder + "BaocaoHDCT.xls"));

            }

            ExcelTransformer transformer = new ExcelTransformer();
            Workbook workbook = transformer.transform(fileIn, beans);

            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment; filename=" + "BaocaoHDCCDetail.xls");
            ServletOutputStream out = response.getOutputStream();
            workbook.write(out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/group/detail/contract/{page}", method = RequestMethod.GET)
    public ModelAndView forGroupDetail(@PathVariable("page") int page, HttpServletRequest request, HttpServletResponse response) {
        //if (!checkGroupRole(request)) return new ModelAndView("/404", "message", null);

        HttpSession session = request.getSession();
        ReportByGroupTotalList reportByGroupTotalList = (ReportByGroupTotalList) session.getAttribute(ReportByGroupTotalList.SESSION_KEY);
        reportByGroupTotalList.setSource("2");
        reportByGroupTotalList.setPage(page);
        createContractList(reportByGroupTotalList, request);

        List<ContractKind> contractKinds = QueryFactory.getContractKind(" where contract_kind_code = " + reportByGroupTotalList.getNhomHD());
        reportByGroupTotalList.setNhomHDChiTiet(contractKinds.get(0).getName());
        String fromDate = reportByGroupTotalList.getNotaryDateFromFilter().substring(0, 10);
        String toDate = reportByGroupTotalList.getNotaryDateToFilter().substring(0, 10);
        String[] fromDateArr = fromDate.split("-");
        ArrayUtils.reverse(fromDateArr);
        fromDate = fromDateArr[0] + "/" + fromDateArr[1] + "/" + fromDateArr[2];
        String[] toDateArr = toDate.split("-");
        ArrayUtils.reverse(toDateArr);
        toDate = toDateArr[0] + "/" + toDateArr[1] + "/" + toDateArr[2];


        reportByGroupTotalList.setFromDate(fromDate);
        reportByGroupTotalList.setToDate(toDate);
        session.setAttribute(ReportByGroupTotalList.SESSION_KEY, reportByGroupTotalList);
        return new ModelAndView("/contract/CTR009", "reportByGroupTotalList", reportByGroupTotalList);
    }

    @RequestMapping(value = "/group/detail/pagging", method = RequestMethod.GET)
    public String forGroupPagging(ReportByGroupTotalList reportByGroupTotalListForm, HttpServletRequest request, HttpServletResponse response) {
        //if (!checkGroupRole(request)) return "/404";
        HttpSession session = request.getSession();
        ReportByGroupTotalList reportByGroupTotalList = (ReportByGroupTotalList) session.getAttribute(ReportByGroupTotalList.SESSION_KEY);
        reportByGroupTotalList.setPage(reportByGroupTotalListForm.getPage());
        session.setAttribute(ReportByGroupTotalList.SESSION_KEY, reportByGroupTotalList);
        return "redirect:/report/group/detail/contract/" + reportByGroupTotalListForm.getPage();
    }

    public ReportByGroupTotalList createContractList(ReportByGroupTotalList reportByGroupTotalList, HttpServletRequest request) {
        int page = 1;
        if (reportByGroupTotalList != null) {
            page = reportByGroupTotalList.getPage();
        }

        if (page < 1) page = 1;

        List<TransactionProperty> dsHopDong = new ArrayList<TransactionProperty>();
        dsHopDong = QueryFactory.getReportDetailByGroup(reportByGroupTotalList.generateJson());
        reportByGroupTotalList.setPage(page);

        reportByGroupTotalList.setContractList(dsHopDong);
        int hopDongListNumber = QueryFactory.countDetailReportByGroup(reportByGroupTotalList.generateJson());

        reportByGroupTotalList.setContractListNumber(hopDongListNumber);
        reportByGroupTotalList.setTotalPage(QueryFactory.countPage(hopDongListNumber));

        return reportByGroupTotalList;
    }
    /*Báo cáo theo công chứng viên
   * minhbq
   * 17/5/2017*/

    @RequestMapping(value = "/by-notary", method = RequestMethod.GET)
    public ModelAndView ReportByNotary(ReportByNotaryWrapper reportByNotaryWrapper, HttpServletRequest request, HttpServletResponse response) {
        if (!ValidationPool.checkRoleDetail(request, "20", Constants.AUTHORITY_XEM)) return new ModelAndView("/404");
        HttpSession session = request.getSession();
        session.removeAttribute(reportByNotaryWrapper.SESSION_KEY);
        ModelAndView view = new ModelAndView();


        int notaryListNumber = 0;
        int notaryTotalPage = 1;
        int page = 1;
        if (reportByNotaryWrapper != null) {
            /*notaryListNumber = reportByNotaryWrapper.getTotal();
            notaryTotalPage = reportByNotaryWrapper.getTotalPage();*/
            page = reportByNotaryWrapper.getPage();
        }
        String orderString = "where role = 02";
        List<User> user = QueryFactory.getNotaryPersonByFilter(orderString);
        ValidateUtil.validate_msg_from_date = "";
        ValidateUtil.validate_msg_to_date = "";
        reportByNotaryWrapper.setUsers(user);
        if (reportByNotaryWrapper.getTimeType() == null) {
            reportByNotaryWrapper.setTimeType("03");
            ValidateUtil.validate_msg_from_date = "";
            ValidateUtil.validate_msg_to_date = "";
        }
        if (!StringUtils.isBlank(reportByNotaryWrapper.getTimeType())) {
            if (reportByNotaryWrapper.getTimeType().equals("05")) {
                ValidateUtil.validateDateto(reportByNotaryWrapper.getToDate());
                ValidateUtil.validateDatefrom(reportByNotaryWrapper.getFromDate());

                if (!StringUtils.isBlank(reportByNotaryWrapper.getFromDate())) {
                    if (!StringUtils.isBlank(reportByNotaryWrapper.getToDate())) {
                        Date fromDate = TimeUtil.stringToDate(reportByNotaryWrapper.getFromDate());
                        Date toDate = TimeUtil.stringToDate(reportByNotaryWrapper.getToDate());
                        if (fromDate.getTime() > toDate.getTime()) {
                            view.setViewName("/contract/CTR006");
                            view.addObject("reportByNotaryWrapper", reportByNotaryWrapper);
                            ValidateUtil.validate_msg_from_date = "Từ ngày không được lớn hơn Đến ngày";
                            view.addObject("validate_msg_from_date", ValidateUtil.validate_msg_from_date);
                            return view;
                        }
                        if (ValidateUtil.validateDatefrom(reportByNotaryWrapper.getFromDate()) == false || ValidateUtil.validateDateto(reportByNotaryWrapper.getToDate()) == false) {
                            view.setViewName("/contract/CTR006");
                            view.addObject("reportByNotaryWrapper", reportByNotaryWrapper);
                            view.addObject("validate_msg_from_date", ValidateUtil.validate_msg_from_date);
                            view.addObject("validate_msg_to_date", ValidateUtil.validate_msg_to_date);
                            return view;
                        }
                    } else {

                        Integer year = (Integer) Calendar.getInstance().get(Calendar.YEAR);
                        Integer month = (Integer) Calendar.getInstance().get(Calendar.MONTH) + 1;
                        Integer date = (Integer) Calendar.getInstance().get(Calendar.DATE);
                        String toDate = date.toString() + "/" + month.toString() + "/" + year.toString();
                        reportByNotaryWrapper.setToDate(toDate);
                        if(ValidateUtil.validateDatefrom(reportByNotaryWrapper.getFromDate())== false){
                            view.setViewName("/contract/CTR006");
                            view.addObject("reportByNotaryWrapper",reportByNotaryWrapper);
                            view.addObject("validate_msg_from_date",ValidateUtil.validate_msg_from_date);
                            return view;
                        }
                    }
                } else {
                    view.setViewName("/contract/CTR006");
                    view.addObject("validate_msg_from_date", "Trường từ ngày không được để trống");
                    return view;
                }
            }
        }
        reportByNotaryWrapper.createFromToDate();
        String orderFilter = "";
        orderFilter = reportByNotaryWrapper.orderFilter();
        List<ReportByNotaryPerson> reportByNotaryPersons = QueryFactory.getReportByNotaryPerson(page, orderFilter);
        /*notaryListNumber = reportByNotaryPersons.size();*/
        notaryListNumber = QueryFactory.countTotalReportByNotary(orderFilter);
        notaryTotalPage = QueryFactory.countPage(notaryListNumber);
        reportByNotaryWrapper.setTotal(notaryListNumber);
        reportByNotaryWrapper.setTotalPage(notaryTotalPage);
        if (page < 1) page = 1;
        if (page > notaryTotalPage) page = notaryTotalPage;
        reportByNotaryWrapper.setPage(page);
        reportByNotaryWrapper.setReportByNotaryPersons(reportByNotaryPersons);
        session.setAttribute(ReportByNotaryWrapper.SESSION_KEY, reportByNotaryWrapper);
        if (reportByNotaryWrapper != null) {
            if (reportByNotaryWrapper.getReportByNotaryPersons().size() > 0) {
                for (int i = 0; i < reportByNotaryWrapper.getReportByNotaryPersons().size(); i++) {
                    if (!StringUtils.isBlank(reportByNotaryWrapper.getReportByNotaryPersons().get(i).getSummary()) || !StringUtils.isBlank(reportByNotaryWrapper.getReportByNotaryPersons().get(i).getRelation_object_B())) {
                        if (!StringUtils.isBlank(reportByNotaryWrapper.getReportByNotaryPersons().get(i).getSummary())) {
                            reportByNotaryWrapper.getReportByNotaryPersons().get(i).setSummary("<div>Nội dung hợp đồng:</div>" + reportByNotaryWrapper.getReportByNotaryPersons().get(i).getSummary());
                        }
                        if (!StringUtils.isBlank(reportByNotaryWrapper.getReportByNotaryPersons().get(i).getRelation_object_B())) {
                            if (!StringUtils.isBlank(reportByNotaryWrapper.getReportByNotaryPersons().get(i).getSummary())) {
                                reportByNotaryWrapper.getReportByNotaryPersons().get(i).setSummary(reportByNotaryWrapper.getReportByNotaryPersons().get(i).getSummary() + "<div>Thông tin tài sản:</div>" + reportByNotaryWrapper.getReportByNotaryPersons().get(i).getRelation_object_B());
                            } else {
                                reportByNotaryWrapper.getReportByNotaryPersons().get(i).setSummary("<div>Thông tin tài sản:</div>" + reportByNotaryWrapper.getReportByNotaryPersons().get(i).getRelation_object_B());
                            }
                        }
                    }
                }
            }
        }
        view.setViewName("/contract/CTR006");
        view.addObject("reportByNotaryWrapper", reportByNotaryWrapper);
        view.addObject("validate_msg_from_date", ValidateUtil.validate_msg_from_date);
        view.addObject("validate_msg_to_date", ValidateUtil.validate_msg_to_date);
        return view;
    }

    @RequestMapping(value = "/export-by-notary", method = RequestMethod.GET)
    public void exportContractByNotary(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession session = request.getSession();
            ReportByNotaryWrapper reportByNotaryWrapper = (ReportByNotaryWrapper) session.getAttribute(ReportByNotaryWrapper.SESSION_KEY);
            String orderFilter = "";
            orderFilter = reportByNotaryWrapper.orderFilter();
            List<ReportByNotaryPerson> reportByNotaryPersons = QueryFactory.getReportByNotaryPersonAll(1, orderFilter);
            if (reportByNotaryPersons != null) {
                int j = reportByNotaryPersons.size();
                for (int i = 0; i < j; i++) {

                    if (reportByNotaryPersons.get(i).getRelation_object_A() != null) {
                        reportByNotaryPersons.get(i).setRelation_object_A(reportByNotaryPersons.get(i).getRelation_object_A().replaceAll("(\\\\r\\\\n|\\\\n)", "\n"));

                    }
                    if (reportByNotaryPersons.get(i).getRelation_object_B() != null) {
                        reportByNotaryPersons.get(i).setRelation_object_B(reportByNotaryPersons.get(i).getRelation_object_B().replaceAll("(\\\\r\\\\n|\\\\n)", "\n"));
                    }
                    if (reportByNotaryPersons.get(i).getRelation_object_C() != null) {
                        reportByNotaryPersons.get(i).setRelation_object_C(reportByNotaryPersons.get(i).getRelation_object_C().replaceAll("(\\\\r\\\\n|\\\\n)", "\n"));
                    }
                }
            }
            reportByNotaryWrapper.setReportByNotaryPersons(reportByNotaryPersons);
            Map<String, Object> beans = new HashMap<String, Object>();
            beans.put("fromdate", reportByNotaryWrapper.getPrintFromDate());
            beans.put("todate", reportByNotaryWrapper.getPrintToDate());
            beans.put("report", reportByNotaryWrapper.getReportByNotaryPersons());
            beans.put("agency", ((CommonContext) request.getSession().getAttribute(request.getSession().getId())).getAgency());
            beans.put("total", reportByNotaryPersons.size());


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
            InputStream fileIn = new BufferedInputStream(new FileInputStream(realPathOfFolder + "BaocaoHDCCtheoCCV.xls"));


            ExcelTransformer transformer = new ExcelTransformer();
            Workbook workbook = transformer.transform(fileIn, beans);

            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment; filename=" + "BaocaoHDCCtheoCCV.xls");
            ServletOutputStream out = response.getOutputStream();
            workbook.write(out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*Báo cáo theo chuyên viên soạn thảo
    * Minhbq
    * 5/19/2017
    * */
    @RequestMapping(value = "/by-user", method = RequestMethod.GET)
    public ModelAndView ReportByUser(ReportByUserWrapper reportByUserWrapper, HttpServletRequest request, HttpServletResponse response) {
        //if (!checkGroupRole(request)) return new ModelAndView("/404", "message", null);
        HttpSession session = request.getSession();
        session.removeAttribute(reportByUserWrapper.SESSION_KEY);
        ModelAndView view = new ModelAndView();
        if (reportByUserWrapper.getTimeType() == null) {
            reportByUserWrapper.setTimeType("03");
            ValidateUtil.validate_msg_from_date = "";
            ValidateUtil.validate_msg_to_date = "";
        }
        /*if(ValidateUtil.validateDatefrom(reportByUserWrapper.getFromDate())){
            ValidateUtil.validate_msg_from_date ="";
        }
        if(ValidateUtil.validateDateto(reportByUserWrapper.getToDate())){
            ValidateUtil.validate_msg_to_date ="";
        }*/
        if (!StringUtils.isBlank(reportByUserWrapper.getTimeType())) {
            if (reportByUserWrapper.getTimeType().equals("05")) {
                if (!StringUtils.isBlank(reportByUserWrapper.getFromDate())) {
                    if (!StringUtils.isBlank(reportByUserWrapper.getToDate())) {
                        Date fromDate = TimeUtil.stringToDate(reportByUserWrapper.getFromDate());
                        Date toDate = TimeUtil.stringToDate(reportByUserWrapper.getToDate());
                        if (fromDate.getTime() > toDate.getTime()) {
                            view.setViewName("/contract/CTR007");
                            view.addObject("reportByUserWrapper", reportByUserWrapper);
                            ValidateUtil.validate_msg_from_date = "Từ ngày không được lớn hơn Đến ngày";
                            view.addObject("validate_msg_from_date", ValidateUtil.validate_msg_from_date);
                            return view;
                        }
                        if (ValidateUtil.validateDatefrom(reportByUserWrapper.getFromDate()) == false || ValidateUtil.validateDateto(reportByUserWrapper.getToDate()) == false) {
                            view.setViewName("/contract/CTR007");
                            view.addObject("reportByUserWrapper", reportByUserWrapper);
                            view.addObject("validate_msg_from_date", ValidateUtil.validate_msg_from_date);
                            view.addObject("validate_msg_to_date", ValidateUtil.validate_msg_to_date);
                            return view;
                        }
                    } else {
                        Integer year = (Integer) Calendar.getInstance().get(Calendar.YEAR);
                        Integer month = (Integer) Calendar.getInstance().get(Calendar.MONTH) + 1;
                        Integer date = (Integer) Calendar.getInstance().get(Calendar.DATE);
                        String toDate = date.toString() + "/" + month.toString() + "/" + year.toString();
                        reportByUserWrapper.setToDate(toDate);
                    }
                } else {
                    view.setViewName("/contract/CTR007");
                    view.addObject("validate_msg_from_date", "Trường từ ngày không được để trống");
                    return view;
                }
            }
        }
        reportByUserWrapper.createFromToDate();

        int notaryListNumber = 0;
        int notaryTotalPage = 1;
        int page = 1;
        if (reportByUserWrapper != null) {
            notaryListNumber = reportByUserWrapper.getTotal();
            notaryTotalPage = reportByUserWrapper.getTotalPage();
            page = reportByUserWrapper.getPage();

        }

        List<UserByRoleList> userByRoleLists = QueryFactory.getDrafter(null);
        reportByUserWrapper.setUserByRoleLists(userByRoleLists);


        String orderFilter = "";
        orderFilter = reportByUserWrapper.orderFilter();
        notaryListNumber = QueryFactory.countTotalReportByUser(orderFilter);
        notaryTotalPage = QueryFactory.countPage(notaryListNumber);
        reportByUserWrapper.setTotal(notaryListNumber);
        reportByUserWrapper.setTotalPage(notaryTotalPage);

        if (page < 1) page = 1;
        if (page > notaryTotalPage) page = notaryTotalPage;

        reportByUserWrapper.setPage(page);

        List<ReportByUser> reportByUsers = QueryFactory.getReportByUser(page, orderFilter);
        reportByUserWrapper.setReportByUsers(reportByUsers);


        session.setAttribute(ReportByUserWrapper.SESSION_KEY, reportByUserWrapper);
        view.setViewName("/contract/CTR007");
        view.addObject("reportByUserWrapper", reportByUserWrapper);
        view.addObject("validate_msg_from_date", ValidateUtil.validate_msg_from_date);
        view.addObject("validate_msg_to_date", ValidateUtil.validate_msg_to_date);
        return view;
    }


    /*Báo cáo theo hợp đồng lỗi
    * Minhbq
    * 5/22/2017
    * */
    @RequestMapping(value = "/contract-error", method = RequestMethod.GET)
    public ModelAndView reportContractError(ContractErrorWrapper contractErrorWrapper, HttpServletRequest request, HttpServletResponse response) {
        //if (!checkGroupRole(request)) return new ModelAndView("/404", "message", null);
        HttpSession session = request.getSession();
        session.removeAttribute(contractErrorWrapper.SESSION_KEY);
        ModelAndView view = new ModelAndView();
        if (contractErrorWrapper.getTimeType() == null) {
            contractErrorWrapper.setTimeType("03");
            ValidateUtil.validate_msg_from_date = "";
            ValidateUtil.validate_msg_to_date = "";
        }
        if (ValidateUtil.validateDatefrom(contractErrorWrapper.getFromDate())) {
            ValidateUtil.validate_msg_from_date = "";
        }
        if (ValidateUtil.validateDateto(contractErrorWrapper.getToDate())) {
            ValidateUtil.validate_msg_to_date = "";
        }
        if (!StringUtils.isBlank(contractErrorWrapper.getTimeType())) {
            if (contractErrorWrapper.getTimeType().equals("05")) {
                if (!StringUtils.isBlank(contractErrorWrapper.getFromDate())) {
                    if (!StringUtils.isBlank(contractErrorWrapper.getToDate())) {
                        Date fromDate = TimeUtil.stringToDate(contractErrorWrapper.getFromDate());
                        Date toDate = TimeUtil.stringToDate(contractErrorWrapper.getToDate());
                        if (fromDate.getTime() > toDate.getTime()) {
                            view.setViewName("/contract/CTR008");
                            view.addObject("contractErrorWrapper", contractErrorWrapper);
                            ValidateUtil.validate_msg_from_date = "Từ ngày không được lớn hơn Đến ngày";
                            view.addObject("validate_msg_from_date", ValidateUtil.validate_msg_from_date);
                            return view;
                        }
                        if (ValidateUtil.validateDatefrom(contractErrorWrapper.getFromDate()) == false || ValidateUtil.validateDateto(contractErrorWrapper.getToDate()) == false) {
                            view.setViewName("/contract/CTR008");
                            view.addObject("contractErrorWrapper", contractErrorWrapper);
                            view.addObject("validate_msg_from_date", ValidateUtil.validate_msg_from_date);
                            view.addObject("validate_msg_to_date", ValidateUtil.validate_msg_to_date);
                            return view;
                        }
                    } else {
                        Integer year = (Integer) Calendar.getInstance().get(Calendar.YEAR);
                        Integer month = (Integer) Calendar.getInstance().get(Calendar.MONTH) + 1;
                        Integer date = (Integer) Calendar.getInstance().get(Calendar.DATE);
                        String toDate = date.toString() + "/" + month.toString() + "/" + year.toString();
                        contractErrorWrapper.setToDate(toDate);
                    }
                } else {
                    view.setViewName("/contract/CTR008");
                    view.addObject("validate_msg_from_date", "Trường từ ngày không được để trống");
                    return view;
                }
            }
        }
        contractErrorWrapper.createFromToDate();

        int notaryListNumber = 0;
        int notaryTotalPage = 1;
        int page = 1;
        if (contractErrorWrapper != null) {
            notaryListNumber = contractErrorWrapper.getTotal();
            notaryTotalPage = contractErrorWrapper.getTotalPage();
            page = contractErrorWrapper.getPage();

        }

        List<UserByRoleList> drafterName = QueryFactory.getDrafter(null);
        contractErrorWrapper.setDrafterName(drafterName);

        List<UserByRoleList> notaryName = QueryFactory.getNotaryPerson(null);
        contractErrorWrapper.setNotaryName(notaryName);

        String orderFilter = "";
        orderFilter = contractErrorWrapper.orderFilter();
        notaryListNumber = QueryFactory.countTotalReportByUser(orderFilter);
        notaryTotalPage = QueryFactory.countPage(notaryListNumber);
        contractErrorWrapper.setTotal(notaryListNumber);
        contractErrorWrapper.setTotalPage(notaryTotalPage);

        if (page < 1) page = 1;
        if (page > notaryTotalPage) page = notaryTotalPage;

        contractErrorWrapper.setPage(page);

        List<ContractError> reportContractError = QueryFactory.getReportContractError(page, orderFilter);
        contractErrorWrapper.setContractErrors(reportContractError);
        session.setAttribute(ContractErrorWrapper.SESSION_KEY, contractErrorWrapper);
        view.setViewName("/contract/CTR008");
        view.addObject("contractErrorWrapper", contractErrorWrapper);
        view.addObject("validate_msg_from_date", ValidateUtil.validate_msg_from_date);
        view.addObject("validate_msg_to_date", ValidateUtil.validate_msg_to_date);
        return view;
    }

    @RequestMapping(value = "/export-contract-error", method = RequestMethod.GET)
    public void exportContractError(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession session = request.getSession();
            ContractErrorWrapper contractErrorWrapper = (ContractErrorWrapper) session.getAttribute(ContractErrorWrapper.SESSION_KEY);
            List<ContractError> contractErrors = QueryFactory.getAllReportContractError(contractErrorWrapper.orderFilter());
            Map<String, Object> beans = new HashMap<String, Object>();
            beans.put("fromdate", contractErrorWrapper.getFromDate());
            beans.put("todate", contractErrorWrapper.getToDate());
            beans.put("report", contractErrorWrapper.getContractErrors());
            beans.put("agency", ((CommonContext) request.getSession().getAttribute(request.getSession().getId())).getAgency());
            beans.put("total", contractErrors.size());

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
            InputStream fileIn = new BufferedInputStream(new FileInputStream(realPathOfFolder + "DSHopDongLoi.xls"));


            ExcelTransformer transformer = new ExcelTransformer();
            Workbook workbook = transformer.transform(fileIn, beans);

            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment; filename=" + "BaocaoHDCCtheoCCV.xls");
            ServletOutputStream out = response.getOutputStream();
            workbook.write(out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/by-tt20", method = RequestMethod.GET)
    public ModelAndView tt20(ReportByTT20List reportByTT20List, HttpServletRequest request, HttpServletResponse response) {
        if (!ValidationPool.checkRoleDetail(request, "23", Constants.AUTHORITY_XEM)) return new ModelAndView("/404");
        ModelAndView view = new ModelAndView();
        HttpSession session = request.getSession();

        if (reportByTT20List.getTimeType() == null) {
            reportByTT20List.setTimeType("03");
            ValidateUtil.validate_msg_from_date = "";
            ValidateUtil.validate_msg_to_date = "";
        }

            ValidateUtil.validate_msg_from_date = "";

            ValidateUtil.validate_msg_to_date = "";

        if (!StringUtils.isBlank(reportByTT20List.getTimeType())) {
            if (reportByTT20List.getTimeType().equals("05")) {
                ValidateUtil.validateDateto(reportByTT20List.getToDate());
                ValidateUtil.validateDatefrom(reportByTT20List.getFromDate());

                if (!StringUtils.isBlank(reportByTT20List.getFromDate())) {
                    if (!StringUtils.isBlank(reportByTT20List.getToDate())) {
                        Date fromDate = TimeUtil.stringToDate(reportByTT20List.getFromDate());
                        Date toDate = TimeUtil.stringToDate(reportByTT20List.getToDate());
                        if (fromDate.getTime() > toDate.getTime()) {
                            view.setViewName("/contract/CTR0011");
                            view.addObject("reportByTT20List", reportByTT20List);
                            ValidateUtil.validate_msg_from_date = "Từ ngày không được lớn hơn Đến ngày";
                            view.addObject("validate_msg_from_date", ValidateUtil.validate_msg_from_date);
                            return view;
                        }
                        if (ValidateUtil.validateDatefrom(reportByTT20List.getFromDate()) == false || ValidateUtil.validateDateto(reportByTT20List.getToDate()) == false) {
                            view.setViewName("/contract/CTR0011");
                            view.addObject("reportByTT20List", reportByTT20List);
                            view.addObject("validate_msg_from_date", ValidateUtil.validate_msg_from_date);
                            view.addObject("validate_msg_to_date", ValidateUtil.validate_msg_to_date);
                            return view;
                        }
                    } else {
                        Integer year = (Integer) Calendar.getInstance().get(Calendar.YEAR);
                        Integer month = (Integer) Calendar.getInstance().get(Calendar.MONTH) + 1;
                        Integer date = (Integer) Calendar.getInstance().get(Calendar.DATE);
                        String toDate = date.toString() + "/" + month.toString() + "/" + year.toString();
                        reportByTT20List.setToDate(toDate);
                    }
                } else {
                    view.setViewName("/contract/CTR0011");
                    view.addObject("validate_msg_from_date", "Trường từ ngày không được để trống");
                    return view;
                }
            }
        }
        reportByTT20List.createFromToDate();
        String printFormDate = reportByTT20List.getPrintFromDate();
        String printToDate = reportByTT20List.getPrintToDate();
        reportByTT20List = QueryFactory.getReportByTT20List(reportByTT20List.generateJson()).get(0);
        reportByTT20List.setPrintFromDate(printFormDate);
        reportByTT20List.setPrintToDate(printToDate);
        session.setAttribute(ReportByTT20List.SESSION_KEY, reportByTT20List);
        view.setViewName("/contract/CTR0011");
        view.addObject("reportByTT20List", reportByTT20List);
        view.addObject("validate_msg_from_date", ValidateUtil.validate_msg_from_date);
        view.addObject("validate_msg_to_date", ValidateUtil.validate_msg_to_date);
        return view;
    }

    @RequestMapping(value = "/by-tt04", method = RequestMethod.GET)
    public ModelAndView tt04(ReportByTT04List reportByTT04List, HttpServletRequest request, HttpServletResponse response) {
        if (!ValidationPool.checkRoleDetail(request, "23", Constants.AUTHORITY_XEM)) return new ModelAndView("/404");
        ModelAndView view = new ModelAndView();
        HttpSession session = request.getSession();
        String validateToDate = ValidateUtil.validate_msg_to_date;
        String validateFromDate = ValidateUtil.validate_msg_from_date;
        if (reportByTT04List.getTimeType() == null) {
            validateToDate = "";
            validateFromDate = "";
            reportByTT04List.setTimeType("03");
        }
        if (ValidateUtil.validateDatefrom(reportByTT04List.getFromDate())) {
            validateFromDate = "";
        }
        if (ValidateUtil.validateDateto(reportByTT04List.getToDate())) {
            validateToDate = "";
        }
        if (!StringUtils.isBlank(reportByTT04List.getTimeType())) {
            if (reportByTT04List.getTimeType().equals("05")) {
                if (!StringUtils.isBlank(reportByTT04List.getPrintFromDate())) {
                    if (!StringUtils.isBlank(reportByTT04List.getPrintToDate())) {
                        Date fromDate = TimeUtil.stringToDate(reportByTT04List.getPrintFromDate());
                        Date toDate = TimeUtil.stringToDate(reportByTT04List.getPrintToDate());
                        if (fromDate.getTime() > toDate.getTime()) {
                            view.setViewName("/contract/tt04");
                            view.addObject("reportByTT04List", reportByTT04List);
                            validateFromDate = "Từ ngày không được lớn hơn Đến ngày";
                            view.addObject("validate_msg_from_date", validateFromDate);
                            return view;
                        }
                        if (ValidateUtil.validateDatefrom(reportByTT04List.getPrintFromDate()) == false || ValidateUtil.validateDateto(reportByTT04List.getPrintToDate()) == false) {
                            view.setViewName("/contract/tt04");
                            view.addObject("reportByTT04List", reportByTT04List);
                            view.addObject("validate_msg_from_date", validateFromDate);
                            view.addObject("validate_msg_to_date", validateToDate);
                            return view;
                        }
                    } else {
                        Integer year = (Integer) Calendar.getInstance().get(Calendar.YEAR);
                        Integer month = (Integer) Calendar.getInstance().get(Calendar.MONTH) + 1;
                        Integer date = (Integer) Calendar.getInstance().get(Calendar.DATE);
                        String toDate = date.toString() + "/" + month.toString() + "/" + year.toString();
                        reportByTT04List.setPrintToDate(toDate);
                    }
                } else {
                    view.setViewName("/contract/tt04");
                    view.addObject("validate_msg_from_date", "Trường từ ngày không được để trống");
                    return view;
                }
            }
        }
        reportByTT04List.createFromToDate();
        reportByTT04List.setNameNotaryOffice(SystemProperties.getProperty("NameNotaryOffice"));
        List<ReportByTT04List> reportByTT04Lists= QueryFactory.getReportByTT04List(reportByTT04List.generateJson());
        if(reportByTT04Lists != null){
            if(reportByTT04Lists.size() > 0){
                reportByTT04List = reportByTT04Lists.get(0);
            }
        }
        reportByTT04List.setNumNotaryOffice(1);
        session.setAttribute(ReportByTT04List.SESSION_KEY, reportByTT04List);
        view.setViewName("/contract/tt04");
        view.addObject("reportByTT04List", reportByTT04List);
        view.addObject("tongPhiCongChung",BaseController.formatNumber(reportByTT04List.getTongPhiCongChung()));
        view.addObject("tongThuLao",BaseController.formatNumber(reportByTT04List.getThuLaoCongChung()));
        view.addObject("tongSoThuLao",BaseController.formatNumber(reportByTT04List.getThuLaoCongChung()));
        view.addObject("validate_msg_from_date", validateFromDate);
        view.addObject("validate_msg_to_date", validateToDate);
        return view;
    }

    @RequestMapping(value = "/export-tt04", method = RequestMethod.GET)
    public void exportTT04(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession session = request.getSession();
            ReportByTT04List reportByTT04List = (ReportByTT04List) session.getAttribute(ReportByTT04List.SESSION_KEY);
            Map<String, Object> beans = new HashMap<String, Object>();
            beans.put("fromdate", reportByTT04List.getPrintFromDate());
            beans.put("todate", reportByTT04List.getPrintToDate());
            beans.put("numNotaryOffice", reportByTT04List.getNumNotaryOffice());
            beans.put("numberOfNotaryPerson", reportByTT04List.getNumberOfNotaryPerson());
            beans.put("numberOfNotaryPersonHopDanh", reportByTT04List.getNumberOfNotaryPersonHopDanh());
            beans.put("numberOfTotalContract", reportByTT04List.getNumberOfTotalContract());
            beans.put("numberOfContractLand", reportByTT04List.getNumberOfContractLand());
            beans.put("numberOfContractOther", reportByTT04List.getNumberOfContractOther());
            beans.put("numberOfContractDanSu", reportByTT04List.getNumberOfContractDanSu());
            beans.put("numberOfThuaKe", reportByTT04List.getNumberOfThuaKe());
            beans.put("numberOfOther", reportByTT04List.getNumberOfOther());
            beans.put("tongPhiCongChung", BaseController.formatNumber(reportByTT04List.getTongPhiCongChung()));
            beans.put("tongThuLao", BaseController.formatNumber(reportByTT04List.getThuLaoCongChung()));
            beans.put("tongSoThuLao", BaseController.formatNumber(reportByTT04List.getThuLaoCongChung()));
            beans.put("congChungBanDichVaLoaiKhac", 0);
            beans.put("NameVPCC", QueryFactory.getSystemConfigByKey("notary_office_name"));
            Date date = new Date(); // your date
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH) + 1;
            int day = cal.get(Calendar.DAY_OF_MONTH);

            beans.put("year", year);
            beans.put("month", month);
            beans.put("day", day);
            String realPathOfFolder = request.getServletContext().getRealPath(SystemProperties.getProperty("template_path"));
            InputStream fileIn = new BufferedInputStream(new FileInputStream(realPathOfFolder + "BaoCaoTheoTT04.xls"));

            ExcelTransformer transformer = new ExcelTransformer();
            Workbook workbook = transformer.transform(fileIn, beans);

            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment; filename=" + "BaoCaoTheoTT04.xls");
            ServletOutputStream out = response.getOutputStream();
            workbook.write(out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/export-tt20", method = RequestMethod.GET)
    public void exportTT20(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession session = request.getSession();
            ReportByTT20List reportByTT20List = (ReportByTT20List) session.getAttribute(ReportByTT20List.SESSION_KEY);
            Map<String, Object> beans = new HashMap<String, Object>();
            beans.put("fromdate", reportByTT20List.getFromDate());
            beans.put("todate", reportByTT20List.getToDate());
            beans.put("numberOfNotaryPerson", reportByTT20List.getNumberOfNotaryPerson());
            beans.put("numberOfTotalContract", reportByTT20List.getNumberOfTotalContract());
            beans.put("numberOfContractLand", reportByTT20List.getNumberOfContractLand());
            beans.put("numberOfContractOther", reportByTT20List.getNumberOfContractOther());
            beans.put("numberOfContractDanSu", reportByTT20List.getNumberOfContractDanSu());
            beans.put("numberOfThuaKe", reportByTT20List.getNumberOfThuaKe());
            beans.put("numberOfOther", reportByTT20List.getNumberOfOther());
            beans.put("tongPhiCongChung", reportByTT20List.getTongPhiCongChung());


            Date date = new Date(); // your date
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH) + 1;
            int day = cal.get(Calendar.DAY_OF_MONTH);

            beans.put("year", year);
            beans.put("month", month);
            beans.put("day", day);
            String realPathOfFolder = request.getServletContext().getRealPath(SystemProperties.getProperty("template_path"));
            InputStream fileIn = new BufferedInputStream(new FileInputStream(realPathOfFolder + "BaoCaoTheoTT20.xls"));


            ExcelTransformer transformer = new ExcelTransformer();
            Workbook workbook = transformer.transform(fileIn, beans);

            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment; filename=" + "BaoCaoTheoTT20.xls");
            ServletOutputStream out = response.getOutputStream();
            workbook.write(out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/contract-additional", method = RequestMethod.GET)
    public ModelAndView reportContractAdditional(ContractAdditionalWrapper contractAdditionalWrapper, HttpServletRequest request, HttpServletResponse response) {
        //if (!checkGroupRole(request)) return new ModelAndView("/404", "message", null);
        HttpSession session = request.getSession();
        session.removeAttribute(contractAdditionalWrapper.SESSION_KEY);
        ModelAndView view = new ModelAndView();
        if (contractAdditionalWrapper.getTimeType() == null) {
            ValidateUtil.validate_msg_from_date = "";
            ValidateUtil.validate_msg_to_date = "";
            contractAdditionalWrapper.setTimeType("03");
        }
        if (ValidateUtil.validateDatefrom(contractAdditionalWrapper.getFromDate())) {
            ValidateUtil.validate_msg_from_date = "";
        }
        if (ValidateUtil.validateDateto(contractAdditionalWrapper.getToDate())) {
            ValidateUtil.validate_msg_to_date = "";
        }
        if (!StringUtils.isBlank(contractAdditionalWrapper.getTimeType())) {
            if (contractAdditionalWrapper.getTimeType().equals("05")) {
                if (!StringUtils.isBlank(contractAdditionalWrapper.getFromDate())) {
                    if (!StringUtils.isBlank(contractAdditionalWrapper.getToDate())) {
                        Date fromDate = TimeUtil.stringToDate(contractAdditionalWrapper.getFromDate());
                        Date toDate = TimeUtil.stringToDate(contractAdditionalWrapper.getToDate());
                        if (fromDate.getTime() > toDate.getTime()) {
                            view.setViewName("/contract/CTR012");
                            view.addObject("contractAdditionalWrapper", contractAdditionalWrapper);
                            ValidateUtil.validate_msg_from_date = "Từ ngày không được lớn hơn Đến ngày";
                            view.addObject("validate_msg_from_date", ValidateUtil.validate_msg_from_date);
                            return view;
                        }
                        if (ValidateUtil.validateDatefrom(contractAdditionalWrapper.getFromDate()) == false || ValidateUtil.validateDateto(contractAdditionalWrapper.getToDate()) == false) {
                            view.setViewName("/contract/CTR012");
                            view.addObject("contractAdditionalWrapper", contractAdditionalWrapper);
                            view.addObject("validate_msg_from_date", ValidateUtil.validate_msg_from_date);
                            view.addObject("validate_msg_to_date", ValidateUtil.validate_msg_to_date);
                            return view;
                        }
                    } else {
                        Integer year = (Integer) Calendar.getInstance().get(Calendar.YEAR);
                        Integer month = (Integer) Calendar.getInstance().get(Calendar.MONTH) + 1;
                        Integer date = (Integer) Calendar.getInstance().get(Calendar.DATE);
                        String toDate = date.toString() + "/" + month.toString() + "/" + year.toString();
                        contractAdditionalWrapper.setToDate(toDate);
                    }
                } else {
                    view.setViewName("/contract/CTR012");
                    view.addObject("validate_msg_from_date", "Trường từ ngày không được để trống");
                    return view;
                }
            }
        }
        contractAdditionalWrapper.createFromToDate();

        int notaryListNumber = 0;
        int notaryTotalPage = 1;
        int page = 1;
        if (contractAdditionalWrapper != null) {
            notaryListNumber = contractAdditionalWrapper.getTotal();
            notaryTotalPage = contractAdditionalWrapper.getTotalPage();
            page = contractAdditionalWrapper.getPage();

        }

        String orderFilter = "";
        orderFilter = contractAdditionalWrapper.orderFilter();
        notaryListNumber = QueryFactory.countTotalReportByUser(orderFilter);
        notaryTotalPage = QueryFactory.countPage(notaryListNumber);
        contractAdditionalWrapper.setTotal(notaryListNumber);
        contractAdditionalWrapper.setTotalPage(notaryTotalPage);

        if (page < 1) page = 1;
        if (page > notaryTotalPage) page = notaryTotalPage;
        contractAdditionalWrapper.setPage(page);
        List<ContractAdditional> reportContractAdditional = QueryFactory.getReportContractAdditional(page, orderFilter);
        contractAdditionalWrapper.setContractAdditionals(reportContractAdditional);
        session.setAttribute(ContractAdditionalWrapper.SESSION_KEY, contractAdditionalWrapper);
        view.setViewName("/contract/CTR012");
        view.addObject("contractAdditionalWrapper", contractAdditionalWrapper);
        view.addObject("validate_msg_from_date", ValidateUtil.validate_msg_from_date);
        view.addObject("validate_msg_to_date", ValidateUtil.validate_msg_to_date);
        return view;
    }

    @RequestMapping(value = "/contract-certify", method = RequestMethod.GET)
    public ModelAndView contractCertify(ContractCeritfyWrapper contractCeritfyWrapper, HttpServletRequest request, HttpServletResponse response) {
        if (!ValidationPool.checkRoleDetail(request, "22", Constants.AUTHORITY_XEM)) return new ModelAndView("/404");
        ModelAndView view = new ModelAndView();
        ValidateUtil.validate_msg_from_date = "";
        ValidateUtil.validate_msg_to_date = "";
        if (contractCeritfyWrapper.getTimeType() == null) {
            contractCeritfyWrapper.setTimeType("03");
            ValidateUtil.validate_msg_from_date = "";
            ValidateUtil.validate_msg_to_date = "";
        }

        if (!StringUtils.isBlank(contractCeritfyWrapper.getTimeType())) {
            if (contractCeritfyWrapper.getTimeType().equals("05")) {
                ValidateUtil.validateDateto(contractCeritfyWrapper.getToDate());
                ValidateUtil.validateDatefrom(contractCeritfyWrapper.getFromDate());

                if (!StringUtils.isBlank(contractCeritfyWrapper.getFromDate())) {
                    if (!StringUtils.isBlank(contractCeritfyWrapper.getToDate())) {
                        Date fromDate = TimeUtil.stringToDate(contractCeritfyWrapper.getFromDate());
                        Date toDate = TimeUtil.stringToDate(contractCeritfyWrapper.getToDate());
                        if (fromDate.getTime() > toDate.getTime()) {
                            view.setViewName("/contract/CTR013");
                            view.addObject("contractCeritfyWrapper", contractCeritfyWrapper);
                            ValidateUtil.validate_msg_from_date = "Từ ngày không được lớn hơn Đến ngày";
                            view.addObject("validate_msg_from_date", ValidateUtil.validate_msg_from_date);
                            return view;
                        }
                        if (ValidateUtil.validateDatefrom(contractCeritfyWrapper.getFromDate()) == false || ValidateUtil.validateDateto(contractCeritfyWrapper.getToDate()) == false) {
                            view.setViewName("/contract/CTR013");
                            view.addObject("contractCeritfyWrapper", contractCeritfyWrapper);
                            view.addObject("validate_msg_from_date", ValidateUtil.validate_msg_from_date);
                            view.addObject("validate_msg_to_date", ValidateUtil.validate_msg_to_date);
                            return view;
                        }
                    } else {
                        Integer year = (Integer) Calendar.getInstance().get(Calendar.YEAR);
                        Integer month = (Integer) Calendar.getInstance().get(Calendar.MONTH) + 1;
                        Integer date = (Integer) Calendar.getInstance().get(Calendar.DATE);
                        String toDate = date.toString() + "/" + month.toString() + "/" + year.toString();
                        contractCeritfyWrapper.setToDate(toDate);
                    }
                } else {
                    view.setViewName("/contract/CTR013");
                    view.addObject("validate_msg_from_date", "Trường từ ngày không được để trống");
                    return view;
                }
            }
        }

        contractCeritfyWrapper.createFromToDate();
        List<ContractKind> contractKinds = QueryFactory.getContractKind(" where 1=1");
        contractCeritfyWrapper.setContractKinds(contractKinds);
        String kindIdList = contractCeritfyWrapper.getCbChild();
        if (kindIdList == null || kindIdList.equals(""))
            return new ModelAndView("/contract/CTR013", "contractCeritfyWrapper", contractCeritfyWrapper);

        String query = "";

        query = contractCeritfyWrapper.orderFilter();
        List<ContractCertify> contractCertifies = QueryFactory.getContractCertify(query);
        if (contractCertifies != null) {
            int i = contractCertifies.size();
            for (int j = 0; j < i; j++) {
                if (!StringUtils.isBlank(contractCertifies.get(j).getRelation_object_A())) {
                    contractCertifies.get(j).setRelation_object_A(contractCertifies.get(j).getRelation_object_A().replaceAll("\\\\r\\\\n|\\\\n", "\n"));
                }
                if (!StringUtils.isBlank(contractCertifies.get(j).getRelation_object_B())) {
                    contractCertifies.get(j).setRelation_object_B(contractCertifies.get(j).getRelation_object_B().replaceAll("\\\\r\\\\n|\\\\n", "\n"));
                }
                if (!StringUtils.isBlank(contractCertifies.get(j).getRelation_object_C())) {
                    contractCertifies.get(j).setRelation_object_C(contractCertifies.get(j).getRelation_object_C().replaceAll("\\\\r\\\\n|\\\\n", "\n"));
                }
            }
        }
        contractCeritfyWrapper.setContractCertifies(contractCertifies);
        try {
            Map<String, Object> beans = new HashMap<String, Object>();

            beans.put("report", contractCertifies);
            beans.put("total", contractCertifies.size());
            beans.put("fromDate", contractCeritfyWrapper.getFromDate());
            beans.put("toDate", contractCeritfyWrapper.getToDate());
            beans.put("agency", ((CommonContext) request.getSession().getAttribute(request.getSession().getId())).getAgency());

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
            InputStream fileIn = new BufferedInputStream(new FileInputStream(realPathOfFolder + "SoHDCC.xls"));


            ExcelTransformer transformer = new ExcelTransformer();
            Workbook workbook = transformer.transform(fileIn, beans);

            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment; filename=" + "SoHDCC.xls");
            ServletOutputStream out = response.getOutputStream();
            workbook.write(out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        view.setViewName("/contract/CTR013");
        view.addObject("contractCeritfyWrapper", contractCeritfyWrapper);
        view.addObject("validate_msg_from_date", ValidateUtil.validate_msg_from_date);
        view.addObject("validate_msg_to_date", ValidateUtil.validate_msg_to_date);
        return view;
    }

    @RequestMapping(value = "/general-stastics", method = RequestMethod.GET)
    public ModelAndView reportContractStastics(ContractStasticsWrapper contractStasticsWrapper, HttpServletRequest request, HttpServletResponse response) {
        if (!ValidationPool.checkRoleDetail(request, "21", Constants.AUTHORITY_XEM)) return new ModelAndView("/404");
        HttpSession session = request.getSession();
        session.removeAttribute(contractStasticsWrapper.SESSION_KEY);
        ModelAndView view = new ModelAndView();
        ValidateUtil.validate_msg_from_date = "";
        ValidateUtil.validate_msg_to_date = "";
        if (contractStasticsWrapper.getTimeType() == null) {
            contractStasticsWrapper.setTimeType("03");
            ValidateUtil.validate_msg_from_date = "";
            ValidateUtil.validate_msg_to_date = "";
        }

        if (!StringUtils.isBlank(contractStasticsWrapper.getTimeType())) {
            if (contractStasticsWrapper.getTimeType().equals("05")) {
                ValidateUtil.validateDatefrom(contractStasticsWrapper.getFromDate());
                ValidateUtil.validateDateto(contractStasticsWrapper.getToDate());
                if (!StringUtils.isBlank(contractStasticsWrapper.getFromDate())) {
                    if (!StringUtils.isBlank(contractStasticsWrapper.getToDate())) {
                        Date fromDate = TimeUtil.stringToDate(contractStasticsWrapper.getFromDate());
                        Date toDate = TimeUtil.stringToDate(contractStasticsWrapper.getToDate());
                        if (fromDate.getTime() > toDate.getTime()) {
                            view.setViewName("/contract/CTR014");
                            view.addObject("contractStasticsWrapper", contractStasticsWrapper);
                            ValidateUtil.validate_msg_from_date = "Từ ngày không được lớn hơn Đến ngày";
                            view.addObject("validate_msg_from_date", ValidateUtil.validate_msg_from_date);
                            return view;
                        }
                        if (ValidateUtil.validateDatefrom(contractStasticsWrapper.getFromDate()) == false || ValidateUtil.validateDateto(contractStasticsWrapper.getToDate()) == false) {
                            view.setViewName("/contract/CTR014");
                            view.addObject("contractCeritfyWrapper", contractStasticsWrapper);
                            view.addObject("validate_msg_from_date", ValidateUtil.validate_msg_from_date);
                            view.addObject("validate_msg_to_date", ValidateUtil.validate_msg_to_date);
                            return view;
                        }
                    } else {
                        Integer year = (Integer) Calendar.getInstance().get(Calendar.YEAR);
                        Integer month = (Integer) Calendar.getInstance().get(Calendar.MONTH) + 1;
                        Integer date = (Integer) Calendar.getInstance().get(Calendar.DATE);
                        String toDate = date.toString() + "/" + month.toString() + "/" + year.toString();
                        contractStasticsWrapper.setToDate(toDate);
                    }
                } else {
                    view.setViewName("/contract/CTR014");
                    view.addObject("validate_msg_from_date", "Trường từ ngày không được để trống");
                    return view;
                }
            }
        }
        contractStasticsWrapper.createFromToDate();
        String orderFilter = "";
        orderFilter = contractStasticsWrapper.generateJsonObject().toString();
        int totalContractByNotary = 0;
        int totalErrorContractByNotary = 0;
        int totalContractByDrafter = 0;
        int totalErrorContractByDrafter = 0;
        int totalContractBank = 0;

        List<ContractStastics> contractStasticsDrafter = QueryFactory.getContractStasticsDrafter(orderFilter);
        contractStasticsWrapper.setContractStasticsDrafter(contractStasticsDrafter);

        for (int i = 0; i < contractStasticsDrafter.size(); i++) {
            totalContractByDrafter += contractStasticsDrafter.get(i).getNumber_of_contract();
        }
        contractStasticsWrapper.setTotalContractByDrafter(totalContractByDrafter);


        for (int i = 0; i < contractStasticsDrafter.size(); i++) {
            totalErrorContractByDrafter += contractStasticsDrafter.get(i).getNumber_of_error_contract();

        }
        contractStasticsWrapper.setTotalErrorContractByDrafter(totalErrorContractByDrafter);

        List<ContractStastics> contractStasticsNotary = QueryFactory.getContractStasticsNotary(orderFilter);
        contractStasticsWrapper.setContractStasticsNotary(contractStasticsNotary);
        for (int i = 0; i < contractStasticsNotary.size(); i++) {
            totalContractByNotary += contractStasticsNotary.get(i).getNumber_of_contract();
        }
        contractStasticsWrapper.setTotalContractByNotary(totalContractByNotary);
        for (int i = 0; i < contractStasticsNotary.size(); i++) {
            totalErrorContractByNotary += contractStasticsNotary.get(i).getNumber_of_error_contract();

        }
        contractStasticsWrapper.setTotalErrorContractByNotary(totalErrorContractByNotary);
        List<ContractStasticsBank> contractStasticsBanks = QueryFactory.getContractStasticsBank(orderFilter);
        contractStasticsWrapper.setContractStasticsBanks(contractStasticsBanks);
        for (int i = 0; i < contractStasticsBanks.size(); i++) {
            totalContractBank += contractStasticsBanks.get(i).getNumber_of_contract();
        }
        contractStasticsWrapper.setTotalContractBank(totalContractBank);
        if (!contractStasticsWrapper.getTimeType().equals("05")) {
            contractStasticsWrapper.setFromDate("");
            contractStasticsWrapper.setToDate("");
        }
        view.setViewName("/contract/CTR014");
        view.addObject("contractStasticsWrapper", contractStasticsWrapper);
        view.addObject("validate_msg_from_date", ValidateUtil.validate_msg_from_date);
        view.addObject("validate_msg_to_date", ValidateUtil.validate_msg_to_date);
        session.setAttribute(ContractStasticsWrapper.SESSION_KEY, contractStasticsWrapper);

        return view;
    }

    @ResponseBody
    @RequestMapping(value = "/loadContractTemplate", method = RequestMethod.GET)
    public String loadContractTemplate(HttpServletRequest request) throws JSONException {
        Long contractKind = Long.valueOf(request.getParameter("contractKind"));
        String result = "";
        List<ContractTemplate> contractTemplates = null;
        if (contractKind == 0) {
            contractTemplates = QueryFactory.getContractTemplate("where 1=1");
        } else {
            contractTemplates = QueryFactory.getContractTemplate(" where code = " + contractKind);
        }
        for (int i = 0; i < contractTemplates.size(); i++) {
            result += contractTemplates.get(i).getCode_template() + "," + contractTemplates.get(i).getName() + ";";
        }

        return new JSONObject().put("result", result).toString();

    }

    @ResponseBody
    @RequestMapping(value = "/loadDetail", method = RequestMethod.GET)
    public String loadDetail(HttpServletRequest request, HttpServletResponse response) throws JSONException {
        HttpSession session = request.getSession();
        session.removeAttribute(ReportByGroupTotalList.SESSION_KEY);
        String tenHDCT = request.getParameter("loadDetail1");
        String[] idArr = tenHDCT.split(",");
        ReportByGroupTotalList reportByGroupTotalList = new ReportByGroupTotalList();
        reportByGroupTotalList.setNhomHD(idArr[1]);
        reportByGroupTotalList.setTenHD(idArr[0]);
        reportByGroupTotalList.setNotaryDateFromFilter(idArr[2]);
        reportByGroupTotalList.setNotaryDateToFilter(idArr[3]);
        reportByGroupTotalList.setCodeTemplate(idArr[4]);

        session.setAttribute(ReportByGroupTotalList.SESSION_KEY, reportByGroupTotalList);
        return new JSONObject().put("result", "ok").toString();
    }

    @RequestMapping(value = "/export-general-stastics", method = RequestMethod.GET)
    public void exportGeneralStastistics(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession session = request.getSession();
            ContractStasticsWrapper contractStasticsWrapper = (ContractStasticsWrapper) session.getAttribute(ContractStasticsWrapper.SESSION_KEY);

            Map<String, Object> beans = new HashMap<String, Object>();
            beans.put("fromdate", contractStasticsWrapper.getPrintFromDate());
            beans.put("todate", contractStasticsWrapper.getPrintToDate());
            beans.put("totalNotary", contractStasticsWrapper.getTotalContractByNotary());
            beans.put("totalErrorNotary", contractStasticsWrapper.getTotalErrorContractByNotary());
            beans.put("totalDrafter", contractStasticsWrapper.getTotalContractByDrafter());
            beans.put("totalErrorDrafter", contractStasticsWrapper.getTotalErrorContractByDrafter());
            beans.put("totalBank", contractStasticsWrapper.getTotalContractBank());
            beans.put("TotalNotary", contractStasticsWrapper.getTotalContractByNotary());
            beans.put("TotalErrorNotary", contractStasticsWrapper.getTotalErrorContractByNotary());

            beans.put("totalByNotary", contractStasticsWrapper.getContractStasticsNotary());
            beans.put("totalByDrafter", contractStasticsWrapper.getContractStasticsDrafter());
            beans.put("totalByBank", contractStasticsWrapper.getContractStasticsBanks());


            beans.put("agency", ((CommonContext) request.getSession().getAttribute(request.getSession().getId())).getAgency());

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
            InputStream fileIn = new BufferedInputStream(new FileInputStream(realPathOfFolder + "Thongke.xls"));


            ExcelTransformer transformer = new ExcelTransformer();
            Workbook workbook = transformer.transform(fileIn, beans);

            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment; filename=" + "Thongke.xls");
            ServletOutputStream out = response.getOutputStream();
            workbook.write(out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

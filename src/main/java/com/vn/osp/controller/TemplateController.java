package com.vn.osp.controller;

import com.vn.osp.common.util.EditString;
import com.vn.osp.common.util.PagingResult;
import com.vn.osp.common.util.StringUtil;
import com.vn.osp.context.CommonContext;
import com.vn.osp.modelview.*;
import com.vn.osp.service.QueryFactory;
import com.vn.osp.service.factory.TemplateFactory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * Created by TienManh on 8/8/2017.
 */
@Controller
@RequestMapping("/template")
public class TemplateController {
    @RequestMapping(value = "/contract/list", method = RequestMethod.GET)
    public ModelAndView contractList(PagingResult page, String pageNumber, HttpServletRequest request, HttpServletResponse response) {
        if (EditString.isNumber(pageNumber)) {
            page.setPageNumber(Integer.parseInt(pageNumber));
        }

        page = TemplateFactory.listContractTemplate(page);
        List<ContractKind> kinds = TemplateFactory.listContractKind();
        HashMap<String, String> mapKind = new HashMap<String, String>();
        if (kinds != null && kinds.size() > 0) {
            for (ContractKind item : kinds) {
                mapKind.put(item.getContract_kind_code(), item.getName());
            }
        }
        List<ContractTemplateBO> items = (List<ContractTemplateBO>) page.getItems();
        if (items != null && items.size() > 0) {
            for (int i = 0; i < items.size(); i++) {
                items.get(i).setCode(mapKind.get(items.get(i).getCode()));
            }
        }
        page.setItems(items);

        return new ModelAndView("template/contract/list", "page", page);
    }

    @RequestMapping(value = "/contract/edit/{id}", method = RequestMethod.GET)
    public ModelAndView contractEdit(@PathVariable("id") String id, HttpServletRequest request) {
        if (StringUtils.isBlank(id)) {
            return new ModelAndView("redirect:/template/contract/list");
        }
        ContractTemplateBO item = TemplateFactory.getContractTemplate(id);
        if (!StringUtils.isBlank(item.getKind_html())) {
            item.setKind_html(StringUtil.removeSpecialCharactersNotHTML(item.getKind_html()));
            item.setKind_html(item.getKind_html().trim());
        }
        return new ModelAndView("template/contract/edit", "item", item);
    }

    @RequestMapping(value = "/contract/update", method = RequestMethod.POST)
    public ModelAndView contractEditSave(ContractTemplateBO item, HttpServletRequest request, HttpServletResponse response) {
        if (item == null || item.getId() == 0) {
            return new ModelAndView("template/contract/edit", "item", item);
        }
        if (!StringUtils.isBlank(item.getKind_html())) {
            item.setKind_html(StringUtil.removeSpecialCharactersNotHTML(item.getKind_html()));
            item.setKind_html(item.getKind_html().trim());
        }
        CommonContext commonContext = (CommonContext) request.getSession().getAttribute(request.getSession().getId());
        item.setUpdate_user_id(commonContext.getUser().getUserId());
        item.setUpdate_date_time(new Date());
        boolean checkUpdate = TemplateFactory.updateContractTemplate(item);
        if (!checkUpdate) {
            return new ModelAndView("template/contract/edit", "item", item);
        }
        return new ModelAndView("redirect:/template/contract/list");

    }


    @RequestMapping(value = "/property/list", method = RequestMethod.GET)
    public ModelAndView propertyList(PaginationHelper paginationHelper, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView view = new ModelAndView();
        List<PropertyTemplate> propertyTemplateList = null;
        int totalRecord = 0;
        if (QueryFactory.getAllPropertyTemplate() != null && QueryFactory.getAllPropertyTemplate().size() > 0) {
            totalRecord = QueryFactory.getAllPropertyTemplate().size();
        }
        int totalPage = 0;
        if (totalRecord % paginationHelper.rowPerPage == 0) {
            totalPage = totalRecord / paginationHelper.rowPerPage;
        } else {
            totalPage = (totalRecord / paginationHelper.rowPerPage) + 1;
        }
        propertyTemplateList = QueryFactory.getAllPropertyplateByPage((paginationHelper.currentPage - 1) * paginationHelper.rowPerPage, paginationHelper.currentPage * paginationHelper.rowPerPage);
        view.setViewName("/template/property/list");
        paginationHelper = new PaginationHelper(totalPage, paginationHelper.currentPage, totalRecord);
        view.addObject("paginationHelper", paginationHelper);
        view.addObject("propertyTemplateList", propertyTemplateList);
        return view;
    }

    @RequestMapping(value = "/privy/list", method = RequestMethod.GET)
    public ModelAndView privyList(PaginationHelper paginationHelper, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView view = new ModelAndView();
        int totalRecord = 0;
        List<PrivyTemplate> privyTemplateList = null;
        if (QueryFactory.getAllPrivyTemplate() != null && QueryFactory.getAllPrivyTemplate().size() > 0) {
            totalRecord = QueryFactory.getAllPrivyTemplate().size();
        }
        int totalPage = 0;
        if (totalRecord % paginationHelper.rowPerPage == 0) {
            totalPage = totalRecord / paginationHelper.rowPerPage;
        } else {
            totalPage = (totalRecord / paginationHelper.rowPerPage) + 1;
        }
        privyTemplateList = QueryFactory.getAllPrivyTemplateByPage((paginationHelper.currentPage - 1) * paginationHelper.rowPerPage, paginationHelper.currentPage * paginationHelper.rowPerPage);
        view.setViewName("template/privy/list");
        paginationHelper = new PaginationHelper(totalPage, paginationHelper.currentPage, totalRecord);
        view.addObject("paginationHelper", paginationHelper);
        view.addObject("privyTemplateList", privyTemplateList);
        return view;
    }

    @RequestMapping(value = {"/property/delete/{id}"}, method = RequestMethod.GET)
    public ModelAndView delete_property(@PathVariable("id") String id, HttpServletRequest request, RedirectAttributes redirectAttributes){
        ModelAndView view = new ModelAndView();
        if(StringUtils.isBlank(id)){
            view.setViewName("redirect:/template/property/list");
            redirectAttributes.addFlashAttribute("styleCss","error");
            redirectAttributes.addFlashAttribute("msg","Thông tin tài sản không tồn tại!");
        }else{
            boolean check = QueryFactory.deletePropertyTemplate(id);
            if(check){
                redirectAttributes.addFlashAttribute("msg","Xóa thành công thông tin tài sản.");
                redirectAttributes.addFlashAttribute("styleCss","success");
                view.setViewName("redirect:/template/property/list");
            }
        }
        return view;
    }

    @RequestMapping(value = {"/privy/delete/{id}"}, method = RequestMethod.GET)
    public ModelAndView delete_privy(@PathVariable("id") String id, HttpServletRequest request, RedirectAttributes redirectAttributes){
        ModelAndView view = new ModelAndView();
        if(StringUtils.isBlank(id)){
            redirectAttributes.addFlashAttribute("msg","Thông tin đương sự không tồn tại!");
            redirectAttributes.addFlashAttribute("styleCss","error");
            view.setViewName("redirect:/template/privy/list");
        }else{
            boolean check = QueryFactory.deletePrivyTemplate(id);
            if(check){
                redirectAttributes.addFlashAttribute("msg","Xóa thành công thông tin đương sự.");
                redirectAttributes.addFlashAttribute("styleCss","success");
                view.setViewName("redirect:/template/privy/list");
            }
        }
        return view;
    }

    @RequestMapping(value = {"/property/edit/{id}"}, method = RequestMethod.GET)
    public ModelAndView viewProperty(@PathVariable("id") String id, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        String path = (String) request.getAttribute(
                HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);
        ModelAndView view = new ModelAndView();
        PropertyTemplate propertyTemplate = QueryFactory.getPropertyTemplateById(Integer.valueOf(id));
        if (propertyTemplate != null) {
            if (!StringUtils.isBlank(propertyTemplate.getName())) {
                return new ModelAndView("/template/property/edit", "propertyTemplate", propertyTemplate);
            } else {
                redirectAttributes.addFlashAttribute("styleCss", "error");
                redirectAttributes.addFlashAttribute("msg", "Không tồn tại template !");
                return new ModelAndView("redirect:/template/property/list");
            }
        } else {
            redirectAttributes.addFlashAttribute("styleCss", "error");
            redirectAttributes.addFlashAttribute("", "Không tồn tại template !");
            return new ModelAndView("redirect:/template/property/list");
        }
    }

    @RequestMapping(value = {"/privy/edit/{id}", "/privy/detail/{id}"}, method = RequestMethod.GET)
    public ModelAndView viewPrivy(@PathVariable("id") String id, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        String path = (String) request.getAttribute(
                HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);
        ModelAndView view = new ModelAndView();
        PrivyTemplate privyTemplate = QueryFactory.getPrivyTemplateById(Integer.valueOf(id));
        if (privyTemplate != null) {
            if (!StringUtils.isBlank(privyTemplate.getName())) {
                if (path.equals("/template/privy/edit/{id}")) {
                    return new ModelAndView("template/privy/edit", "privyTemplate", privyTemplate);
                } else {
                    return new ModelAndView("template/privy/detail", "privyTemplate", privyTemplate);
                }
            } else {
                redirectAttributes.addFlashAttribute("styleCss", "error");
                redirectAttributes.addFlashAttribute("msg", "Không tồn tại template !");
                return new ModelAndView("redirect:/template/privy/list");
            }
        } else {
            redirectAttributes.addFlashAttribute("styleCss", "error");
            redirectAttributes.addFlashAttribute("", "Không tồn tại template !");
            return new ModelAndView("redirect:/template/privy/list");
        }
    }

    @RequestMapping(value = "/property/update", method = RequestMethod.POST)
    public ModelAndView propertyEditSave(PropertyTemplate propertyTemplate, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
        ModelAndView view = new ModelAndView();
        Map<String, String> map = validatePropertyTemplate(propertyTemplate);
        if (map.size() <= 0) {
            PrivyTemplate template = QueryFactory.getPrivyTemplateById(propertyTemplate.getId());
            if (template == null || template.getId() == 0) {
                redirectAttributes.addFlashAttribute("msg", "Không tồn tại template!");
                view.setViewName("redirect:/template/property/list");
            }
            if (!StringUtils.isBlank(propertyTemplate.getHtml())) {
                propertyTemplate.setHtml(StringUtil.removeSpecialCharactersNotHTML(propertyTemplate.getHtml()));
                propertyTemplate.setHtml(propertyTemplate.getHtml().trim());
            }
            propertyTemplate.setUpdate_user((int) (long) ((CommonContext) request.getSession().getAttribute(request.getSession().getId())).getUser().getUserId());
            propertyTemplate.setUpdate_time(new Date());
            boolean checkUpdate = QueryFactory.updatePropertyTemplate(propertyTemplate);
            if (!checkUpdate) {
                redirectAttributes.addFlashAttribute("styleCss", "error");
                view.addObject("msg", "Cập nhật không thành công !");
                view.setViewName("/template/property/edit");
                return view;
            } else {
                redirectAttributes.addFlashAttribute("styleCss", "success");
                redirectAttributes.addFlashAttribute("msg", "Cập nhật thành công !");
                view.setViewName("redirect:/template/property/list");
                return view;
            }
        } else {
            map.put("msg", "Cập nhật thông tin mẫu đương sự không thành công!");
            view.addObject("list_msg", map);
            view.addObject("propertyTemplate", propertyTemplate);
            view.setViewName("/template/property/edit");
            return view;
        }

    }

    @RequestMapping(value = "/privy/update", method = RequestMethod.POST)
    public ModelAndView privyEditSave(PrivyTemplate privyTemplate, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
        ModelAndView view = new ModelAndView();
        Map<String, String> map = validatePrivyTemplate(privyTemplate);
        if (map.size() <= 0) {
            PrivyTemplate template = QueryFactory.getPrivyTemplateById(privyTemplate.getId());
            if (template == null || template.getId() == 0) {
                redirectAttributes.addFlashAttribute("msg", "Không tồn tại template!");
                view.setViewName("redirect:/template/privy/list");
            }
            if (!StringUtils.isBlank(privyTemplate.getHtml())) {
                privyTemplate.setHtml(StringUtil.removeSpecialCharactersNotHTML(privyTemplate.getHtml()));
                privyTemplate.setHtml(privyTemplate.getHtml().trim());
            }
            privyTemplate.setUpdate_user((int) (long) ((CommonContext) request.getSession().getAttribute(request.getSession().getId())).getUser().getUserId());
            privyTemplate.setUpdate_time(new Date());
            boolean checkUpdate = QueryFactory.updatePrivyTemplate(privyTemplate);
            if (!checkUpdate) {
                redirectAttributes.addFlashAttribute("styleCss", "error");
                view.addObject("msg", "Cập nhật không thành công !");
                view.setViewName("/template/privy/edit");
                return view;
            } else {
                redirectAttributes.addFlashAttribute("styleCss", "success");
                redirectAttributes.addFlashAttribute("msg", "Cập nhật thành công !");
                view.setViewName("redirect:/template/privy/list");
                return view;
            }
        } else {
            map.put("msg", "Cập nhật thông tin mẫu đương sự không thành công!");
            view.addObject("list_msg", map);
            view.addObject("privyTemplate", privyTemplate);
            view.setViewName("/template/privy/edit");
            return view;
        }
    }

    @RequestMapping(value = "/property/add-view", method = RequestMethod.GET)
    public ModelAndView propertyAdd(PropertyTemplate propertyTemplate, HttpServletRequest request) {
        if (propertyTemplate == null || propertyTemplate.getName() == null || propertyTemplate.getHtml() == null) {
            propertyTemplate = new PropertyTemplate();
            return new ModelAndView("/template/property/add", "propertyTemplate", propertyTemplate);
        }
        return new ModelAndView("redirect:/template/property/list");
    }

    @RequestMapping(value = "/privy/add-view", method = RequestMethod.GET)
    public ModelAndView privyAdd(PrivyTemplate privyTemplate, HttpServletRequest request) {
        if (privyTemplate == null || privyTemplate.getName() == null || privyTemplate.getHtml() == null) {
            privyTemplate = new PrivyTemplate();
            return new ModelAndView("/template/privy/add", "privyTemplate", privyTemplate);
        }
        return new ModelAndView("redirect:/template/privy/list");
    }

    @RequestMapping(value = "/property/save", method = RequestMethod.POST)
    public ModelAndView propertySave(PropertyTemplate propertyTemplate, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
        ModelAndView view = new ModelAndView();
        Map<String, String> map = validatePropertyTemplate(propertyTemplate);
        if (map.size() <= 0) {
            if (propertyTemplate == null || propertyTemplate.getId() == 0) {
                redirectAttributes.addFlashAttribute("styleCss", "error");
                view.addObject("msg", "Thêm mới không thành công!");
                view.setViewName("redirect:/template/privy/list");
            }
            if (!StringUtils.isBlank(propertyTemplate.getHtml())) {
                propertyTemplate.setHtml(StringUtil.removeSpecialCharactersNotHTML(propertyTemplate.getHtml()));
                propertyTemplate.setHtml(propertyTemplate.getHtml().trim());
            }
            propertyTemplate.setUpdate_user((int) (long) ((CommonContext) request.getSession().getAttribute(request.getSession().getId())).getUser().getUserId());
            propertyTemplate.setUpdate_time(new Date());
            boolean checkUpdate = QueryFactory.addPropertyTemplate(propertyTemplate);
            if (!checkUpdate) {
                redirectAttributes.addFlashAttribute("styleCss", "error");
                view.addObject("msg", "Thêm mới không thành công !");
                view.setViewName("redirect:/template/property/list");
                return view;
            } else {
                redirectAttributes.addFlashAttribute("styleCss", "success");
                redirectAttributes.addFlashAttribute("msg", "Thêm mới thành công !");
                view.setViewName("redirect:/template/property/list");
                return view;
            }
        } else {
            map.put("msg", "Thêm mới thông tin mẫu đương sự không thành công!");
            view.addObject("list_msg", map);
            view.addObject("propertyTemplate", propertyTemplate);
            view.setViewName("/template/property/add");
            return view;
        }

    }

    @RequestMapping(value = "/privy/save", method = RequestMethod.POST)
    public ModelAndView privySave(PrivyTemplate privyTemplate, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
        ModelAndView view = new ModelAndView();
        Map<String, String> map = validatePrivyTemplate(privyTemplate);
        if (map.size() <= 0) {
            if (privyTemplate == null || privyTemplate.getId() == 0) {
                redirectAttributes.addFlashAttribute("styleCss", "error");
                view.addObject("msg", "Thêm mới không thành công!");
                view.setViewName("redirect:/template/privy/list");
            }
            if (!StringUtils.isBlank(privyTemplate.getHtml())) {
                privyTemplate.setHtml(StringUtil.removeSpecialCharactersNotHTML(privyTemplate.getHtml()));
                privyTemplate.setHtml(privyTemplate.getHtml().trim());
            }
            privyTemplate.setUpdate_user((int) (long) ((CommonContext) request.getSession().getAttribute(request.getSession().getId())).getUser().getUserId());
            privyTemplate.setUpdate_time(new Date());
            boolean checkUpdate = QueryFactory.addPrivyTemplate(privyTemplate);
            if (!checkUpdate) {
                redirectAttributes.addFlashAttribute("styleCss", "error");
                view.addObject("msg", "Thêm mới không thành công !");
                view.setViewName("redirect:/template/privy/list");
                return view;
            } else {
                redirectAttributes.addFlashAttribute("styleCss", "success");
                redirectAttributes.addFlashAttribute("msg", "Thêm mới thành công !");
                view.setViewName("redirect:/template/privy/list");
                return view;
            }
        } else {
            map.put("msg", "Thêm mới thông tin mẫu đương sự không thành công!");
            view.addObject("list_msg", map);
            view.addObject("privyTemplate", privyTemplate);
            view.setViewName("/template/privy/add");
            return view;
        }

    }

    @RequestMapping(value = "/property/property-synch", method = RequestMethod.GET)
    public ModelAndView propertySynch(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
        List<PropertyTemplate> items = QueryFactory.getAllPropertyTemplateFromOSP();
        if (items != null) {
            if (items.size() > 0) {
                for (int i = 0; i < items.size(); i++) {
                    items.get(i).setId(0);
                    List<PropertyTemplate> checkList = QueryFactory.getPropertyTemplateByCode(items.get(i).getCode());
                    if (checkList.size() <= 0) {
                        QueryFactory.addPropertyTemplate(items.get(i));
                    }else{
                        PropertyTemplate propertyTemplate = checkList.get(0);
                        propertyTemplate.setHtml(items.get(i).getHtml());
                        propertyTemplate.setCode(items.get(i).getCode());
                        propertyTemplate.setDescription(items.get(i).getDescription());
                        propertyTemplate.setName(items.get(i).getName());
                        propertyTemplate.setType(items.get(i).getType());
                        propertyTemplate.setDisable(items.get(i).isDisable());
                        propertyTemplate.setUpdate_user(items.get(i).getUpdate_user());
                        propertyTemplate.setEntry_time(items.get(i).getEntry_time());
                        propertyTemplate.setUpdate_time(new Date());
                        QueryFactory.updatePropertyTemplate(propertyTemplate);
                    }
                }
                redirectAttributes.addFlashAttribute("styleCss", "success");
                redirectAttributes.addFlashAttribute("msg", "Đồng bộ thành công.");
                return new ModelAndView("redirect:/template/property/list");
            } else {
                redirectAttributes.addFlashAttribute("styleCss", "error");
                redirectAttributes.addFlashAttribute("msg", "Đồng bộ thất bại!");
                return new ModelAndView("redirect:/template/property/list");
            }
        } else {
            redirectAttributes.addFlashAttribute("styleCss", "error");
            redirectAttributes.addFlashAttribute("msg", "Đồng bộ thất bại!");
            return new ModelAndView("redirect:/template/property/list");
        }
    }

    @RequestMapping(value = "/privy/privy-synch", method = RequestMethod.GET)
    public ModelAndView privysynch(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
        List<PrivyTemplate> privyTemplateListFromOSP;
        privyTemplateListFromOSP = QueryFactory.getAllPrivyTemplateFromOSP();
        if (privyTemplateListFromOSP != null) {
            if (privyTemplateListFromOSP.size() > 0) {
                for (int i = 0; i < privyTemplateListFromOSP.size(); i++) {
                    privyTemplateListFromOSP.get(i).setId(0);
                    List<PrivyTemplate> checkList = QueryFactory.getPrivyTemplateByCode(privyTemplateListFromOSP.get(i).getCode());
                    if (checkList.size() <= 0) {
                        QueryFactory.addPrivyTemplate(privyTemplateListFromOSP.get(i));
                    }else{
                        PrivyTemplate privyTemplate = checkList.get(0);
                        privyTemplate.setHtml(privyTemplateListFromOSP.get(i).getHtml());
                        privyTemplate.setCode(privyTemplateListFromOSP.get(i).getCode());
                        privyTemplate.setDescription(privyTemplateListFromOSP.get(i).getDescription());
                        privyTemplate.setType(privyTemplateListFromOSP.get(i).getType());
                        privyTemplate.setName(privyTemplateListFromOSP.get(i).getName());
                        privyTemplate.setDisable(privyTemplateListFromOSP.get(i).isDisable());
                        QueryFactory.updatePrivyTemplate(privyTemplate);
                    }
                }
                redirectAttributes.addFlashAttribute("styleCss", "success");
                redirectAttributes.addFlashAttribute("msg", "Đồng bộ thành công.");
                return new ModelAndView("redirect:/template/privy/list");
            } else {
                redirectAttributes.addFlashAttribute("styleCss", "error");
                redirectAttributes.addFlashAttribute("msg", "Đồng bộ thất bại!");
                return new ModelAndView("redirect:/template/privy/list");
            }
        } else {
            redirectAttributes.addFlashAttribute("styleCss", "error");
            redirectAttributes.addFlashAttribute("msg", "Đồng bộ thất bại!");
            return new ModelAndView("redirect:/template/privy/list");
        }
    }

    public Map<String, String> validatePrivyTemplate(PrivyTemplate privyTemplate) {
        Map<String, String> map = new HashMap<String, String>();
        if (StringUtils.isBlank(privyTemplate.getName())) {
            map.put("msgName", "Tên mẫu hợp đồng không được để trống!");
        }
        if (StringUtils.isBlank(privyTemplate.getDescription())) {
            map.put("msgDescription", "Trường mô tả không được để trống!");
        }
        return map;
    }

    public Map<String, String> validatePropertyTemplate(PropertyTemplate propertyTemplate) {
        Map<String, String> map = new HashMap<String, String>();
        if (StringUtils.isBlank(propertyTemplate.getName())) {
            map.put("msgName", "Tên mẫu hiển thị tài sản không được để trống!");
        }
        if (StringUtils.isBlank(propertyTemplate.getType())) {
            map.put("msgType", "Loại tài sản không được để trống!");
        }
        if (StringUtils.isBlank(propertyTemplate.getDescription())) {
            map.put("msgDescription", "Trường mô tả không được để trống!");
        }
        return map;
    }
}


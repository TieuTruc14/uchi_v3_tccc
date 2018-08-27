<%@ page import="com.vn.osp.context.CommonContext" %>
<%@ page import="com.vn.osp.common.util.ValidationPool" %>
<%@ page import="com.vn.osp.common.global.Constants" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/pages/layout/header.jsp"/>

<!--script type="text/javascript" src="<%=request.getContextPath()%>/static/plugin/mark/mark.js" charset="UTF-8"></script-->
<jsp:include page="/WEB-INF/pages/layout/body_top.jsp"/>
<%--
    Tra cứu thông tin liên tỉnh
--%>
<spring:url value="/transaction/detail" var="contractDetailUrl"/>
<spring:url value="/transaction/prevent-detail" var="preventDetailUrl"/>
<spring:url value="/transaction/print-view" var="printViewUrl"/>
<spring:url value="/contract" var="listUrl"/>
<spring:url value="/transaction/multi-search" var="searchUrl"/>



<div id="menu-map">
    <a href="#menu-toggle" id="menu-toggle"><img src="<%=request.getContextPath()%>/static/image/menu-icon.png"></a>
    <span id="web-map">Tra cứu thông tin liên tỉnh</span>
</div>
<div class="panel-group col-md-12">
    <div class="panel-group">
        <form class="form-horizontal" action="${searchUrl}" method="get" id="search-frm">

            <input type="hidden" name="isAdvanceSearch" value="${preventContractList.isAdvanceSearch}" id="isAdvanceSearch">
            <input type="hidden" name="defaultTabOpen" id="defaultTabOpen" value="${preventContractList.defaultTabOpen}">
            <input type="hidden" name="daDuyetPage" id="daDuyetPage" value="${preventContractList.daDuyetPage}">
            <input type="hidden" name="hopDongPage" id="hopDongPage" value="${preventContractList.hopDongPage}">
            <input type="hidden" name="daDuyetListNumber" id="daDuyetListNumber" value="${preventContractList.daDuyetListNumber}">
            <input type="hidden" name="hopDongPage" id="daDuyetTotalPage" value="${preventContractList.daDuyetTotalPage}">
            <input type="hidden" name="hopDongPage" id="hopDongListNumber" value="${preventContractList.hopDongListNumber}">
            <input type="hidden" name="hopDongPage" id="hopDongTotalPage" value="${preventContractList.hopDongTotalPage}">

            <input type="hidden" name="listKey" id="listKey" value='${preventContractList.listKey}'>

            <%
                if(ValidationPool.checkRoleDetail(request,"10", Constants.AUTHORITY_TIMKIEM)){
            %>
            <div id="basic-frm" style="display: block">
                <div class="form-group">
                    <label  class="  control-label label-bam-trai truong-label-fixprv " >Điều kiện tìm kiếm</label>
                    <div class="" >
                        <div onclick="openAdvanceSearchFrm()" class=" truong-search-tc advanced-search">TÌM KIẾM NÂNG CAO
                        </div>
                    </div>

                    <label class="control-label  checkbox-inline label-bam-trai truong-search-area text-nowrap" ><input
                            name="basicAreaSearch"
                            type="checkbox" ${preventContractList.basicAreaSearch == true?"checked":""}>Chỉ tìm kiếm dữ liệu của <%=((CommonContext)request.getSession().getAttribute(request.getSession().getId())).getAgency()%></label>

                    <div class="col-md-3">
                        <label class="control-label label-bam-trai"><a target="_blank" href="<%=request.getContextPath()%>/manual-search"><img class="truong-image-hd"
                                                                                                              src="<%=request.getContextPath()%>/static/image/Forma 6.png"> Hướng dẫn tra cứu </a> </label>
                    </div>

                </div>

                <div class="form-group">
                    <label class="col-md-7 control-label label-bam-trai">
                        <input type="text" name="stringKey" class="form-control" id="key-search" placeholder="Nhập từ khóa tìm kiếm..." value='${preventContractList.stringKey}'>

                    </label>

                    <div class=" control-label" id="truong-button-fixprv">
                        <input type="button" onclick="searchSubmit()" class="form-control luu textInputSearch" value="Tìm kiếm">
                    </div>

                </div>
            </div>
            <div id="advance-frm" style="display: none">

                <div class="form-group">
                    <label  class=" control-label label-bam-trai truong-label-fixprv" >Điều kiện tìm kiếm</label>

                    <div onclick="openBasicSearchFrm()" class=" truong-search-tc advanced-search">Tìm kiếm theo từ khóa
                    </div>


                    <label class="control-label  checkbox-inline label-bam-trai truong-search-area " ><input
                            name="basicAreaSearch"
                            type="checkbox" ${preventContractList.advanceAreaSearch == true?"checked":""}>Chỉ tìm kiếm dữ liệu của <%=((CommonContext)request.getSession().getAttribute(request.getSession().getId())).getAgency()%></label>

                    <div class="col-md-3">
                        <label class="control-label label-bam-trai"><a target="_blank" href="<%=request.getContextPath()%>/manual-search"><img class="truong-image-hd" src="<%=request.getContextPath()%>/static/image/Forma 6.png"> Hướng dẫn tra cứu </a> </label>
                    </div>

                </div>

                <div class="form-group truong-margin-fixprv">
                    <div class="control-label">
                        <div class="col-md-9">
                            <div class="row">
                                <%--label class="col-md-2   control-label label-bam-trai">Loại tài sản</label>
                                <div class="col-md-2">
                                    <select id="property-type" class=" form-control truong-padding-delete" name="propertyType">
                                        <option value="00">Tất cả</option>
                                        <option value="01" style="color: black;" ${preventContractList.propertyType == "01"?"selected":""}>Nhà đất</option>
                                        <option value="02" style="color: black;" ${preventContractList.propertyType == "02"?"selected":""}>Ô tô - Xe máy</option>
                                        <option value="99" style="color: black;" ${preventContractList.propertyType == "99"?"selected":""}>Tài sản khác</option>
                                    </select>
                                </div--%>
                                    <label class="col-md-2 control-label label-bam-trai">Thông tin tài sản</label>
                                    <div class="col-md-3 " style="padding-left: 7px!important;">
                                        <input  type="text" id="property-information" class="form-control" name="propertyInfo" value='${preventContractList.propertyInfo}'>
                                    </div>

                                    <label class="col-md-3 control-label label-bam-phai">Bên liên quan/ Chủ sở hữu</label>
                                    <div class="col-md-3">
                                        <input type="text" id="property-own" class="form-control" name="ownerInfo"  value='${preventContractList.ownerInfo}'>
                                    </div>


                            </div>
                        </div>
                        <div class="col-md-3">
                            <div class="row">
                                <div class=" col-md-5 truong-rs-tcbt2">
                                    <input type="submit" onclick="searchSubmit()" class="form-control luu" value="Tìm kiếm"/>
                                </div>

                                <div class=" col-md-5 truong-rs-tcbt2">
                                    <input type="button" class="form-control huybo" value="Xóa điều kiện" onclick="clearText()">
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <%
                }
            %>

            <div class="col-md-12 truong-margin-footer0px ">
                <div class="panel-group ">


                    <ul class="nav nav-tabs">
                        <li onclick="setDefaultTabOpen(1)"><a data-toggle="tab" href="#daduyet">Thông tin ngăn chặn
                            (<span style="color:red">${preventContractList.daDuyetListNumber}</span>)</a></li>
                        <li onclick="setDefaultTabOpen(2)"><a data-toggle="tab" href="#hopdong">Hợp đồng, giao dịch đã công chứng
                            (<span style="color: red">${preventContractList.hopDongListNumber}</span>)</a></li>
                        <%
                            if(ValidationPool.checkRoleDetail(request,"10", Constants.AUTHORITY_IN)){
                        %>
                        <li id="prevent-them-moi"><a href="${printViewUrl}"><input type="button"  id="add-prevent" class="form-control luu" value="Bản in"></a>
                        </li>
                        <%
                            }
                        %>
                    </ul>


                    <div class="tab-content">
                        <c:if test="${preventContractList.defaultTabOpen == 1}">
                        <div id="daduyet" class="tab-pane fade in active">
                            </c:if>
                            <c:if test="${preventContractList.defaultTabOpen != 1}">
                            <div id="daduyet" class="tab-pane fade">
                                </c:if>

                                <table class="table" style="margin-bottom:0%">

                                    <tr class="border-table">
                                        <th class=" ann-title border-table table-giua truong-rstable-widthper5">Phân loại</th>
                                        <th class=" ann-title border-table table-giua truong-rstable-widthper12">Loại tài sản</th>
                                        <th class=" ann-title border-table table-giua">Thông tin tài sản</th>
                                        <th class=" ann-title border-table table-giua truong-rstable-widthper12">Đơn vị gửi yêu cầu ngăn
                                            chặn
                                        </th>
                                        <th class=" ann-title border-table table-giua truong-rstable-widthper8">Ngày nhận công văn</th>
                                        <th class=" ann-title border-table table-giua truong-rstable-widthper5">Giải tỏa</th>
                                        <th class=" ann-title border-table table-giua truong-rstable-widthper5">Xem chi tiết</th>

                                    </tr>
                                    <c:if test="${preventContractList.daDuyetListNumber == 0}">
                                        <tr>
                                            <td colspan="7"
                                                style="height: 100%;background-color: #ececec; line-height: 5.429;text-align: center;font-size: 265%">
                                                Chưa có dữ liệu
                                            </td>
                                        </tr>
                                    </c:if>
                                    <c:if test="${preventContractList.daDuyetListNumber != 0}">
                                        <c:forEach items="${preventContractList.daDuyetList}" var="item">

                                            <tr>
                                                <td class="border-table  align-giua ">
                                                    <c:if test="${item.origin_kind.equals('01')}">
                                                        <img src="<%=request.getContextPath()%>/static/image/Forma 4.png"
                                                             alt="Thông tin ngăn chặn"
                                                             title="Thông tin ngăn chặn">
                                                    </c:if>
                                                    <c:if test="${item.origin_kind.equals('02')}">
                                                        <img src="<%=request.getContextPath()%>/static/image/Forma 5.png"
                                                             alt="Thông tin tham khảo"
                                                             title="Thông tin tham khảo">
                                                    </c:if>
                                                </td>
                                                <td class="border-table truong-text-verticel" style="color: black;">
                                                        ${item.loaiTaiSan()}
                                                </td>



                                                <td class='highlight-content border-table truong-text-verticel'
                                                    style="color: black;" >
                                                    <c:if test="${item.property_info_disp == null}">
                                                        ${item.property_info}
                                                    </c:if>
                                                    <c:if test="${item.property_info_disp != null}">
                                                        ${item.property_info_disp}
                                                        <img data-html="true" data-toggle="popover" title="Thông tin tài sản" data-trigger="click"  data-placement="top"  src="<%=request.getContextPath()%>/static/image/xemthem.png">
                                                        <span class="hidden"><div class='title-green'>Tài sản:</div>${item.property_info}</span>

                                                    </c:if>
                                                </td>
                                                <td class='${!preventContractList.stringKey.equals("")?"highlight-content":""} border-table truong-text-verticel'
                                                    style="color: black;">${item.prevent_person_info}</td>
                                                <td class='${!preventContractList.stringKey.equals("")?"highlight-content":""} border-table align-giua'
                                                    style="color: black;">${item.prevent_doc_receive_date}</td>
                                                <td class="border-table align-giua" style="color: black;"
                                                <c:if test="${item.release_flg==1}">
                                                    <img src="<%=request.getContextPath()%>/static/image/giaitoa.png" alt="Đã giải tỏa"
                                                         title="Đã giải tỏa">
                                                </c:if>
                                                </td>
                                                <td class="border-table align-giua" style="color: black;"><a
                                                        href="${preventDetailUrl}/${item.prevent_id}"><img
                                                        src="<%=request.getContextPath()%>/static/image/xem.png" alt="Xem chi tiết"
                                                        title="Xem chi tiết"></a></td>

                                            </tr>
                                        </c:forEach>
                                        <tr class="table-tr-xam">
                                            <th colspan="7"><div  class="truong-padding-foot-table" >Tổng số <span style="color: red;">${preventContractList.daDuyetListNumber}</span> dữ
                                                liệu </div>
                                                <div class="align-phai">
                                                    <c:if test="${preventContractList.daDuyetPage==1}">
                                                    <img
                                                         class="truong-pagging-icon"
                                                         src="<%=request.getContextPath()%>/static/image/pagging/icon1.png">


                                                    <img
                                                         class="truong-pagging-icon"
                                                         src="<%=request.getContextPath()%>/static/image/pagging/icon2.png">
                                                    </c:if>
                                                    <c:if test="${preventContractList.daDuyetPage!=1}">
                                                        <img onclick="firstPage(1)"
                                                             class="pagging-icon"
                                                             src="<%=request.getContextPath()%>/static/image/pagging/icon1.png">


                                                        <img onclick="previewPage(1)"
                                                             class="pagging-icon"
                                                             src="<%=request.getContextPath()%>/static/image/pagging/icon2.png">
                                                    </c:if>
                                                        ${preventContractList.daDuyetPage}
                                                    trên ${preventContractList.daDuyetTotalPage}
                                                    <c:if test="${preventContractList.daDuyetPage== preventContractList.daDuyetTotalPage}">
                                                    <img
                                                         class="truong-pagging-icon"
                                                         src="<%=request.getContextPath()%>/static/image/pagging/icon8.png">
                                                    <img
                                                         class="truong-pagging-icon"
                                                         src="<%=request.getContextPath()%>/static/image/pagging/icon7.png">
                                                    </c:if>
                                                    <c:if test="${preventContractList.daDuyetPage!= preventContractList.daDuyetTotalPage}">
                                                        <img onclick="nextPage(1)"
                                                             class="pagging-icon"
                                                             src="<%=request.getContextPath()%>/static/image/pagging/icon8.png">
                                                        <img onclick="lastPage(1)"
                                                             class="pagging-icon"
                                                             src="<%=request.getContextPath()%>/static/image/pagging/icon7.png">
                                                    </c:if>
                                                </div>
                                            </th>


                                        </tr>
                                    </c:if>


                                </table>
                            </div>

                            <c:if test="${preventContractList.defaultTabOpen == 2}">
                            <div id="hopdong" class="tab-pane fade in active">
                                </c:if>
                                <c:if test="${preventContractList.defaultTabOpen != 2}">
                                <div id="hopdong" class="tab-pane fade">
                                    </c:if>

                                    <table class="table" style="margin-bottom:0%">

                                        <tr class="border-table">
                                            <th class=" ann-title border-table table-giua truong-rstable-widthper8">Ngày công chứng</th>
                                            <th class=" ann-title border-table table-giua truong-rstable-widthper8">Số hợp đồng</th>
                                            <th class=" ann-title border-table table-giua truong-rstable-widthper12">Tên hợp đồng</th>
                                            <th class=" ann-title border-table table-giua truong-rstable-widthper12">Bên liên quan</th>
                                            <th class=" ann-title border-table table-giua">Nội dung</th>
                                            <th class=" ann-title border-table table-giua truong-rstable-widthper12">Công chứng viên</th>
                                            <th class=" ann-title border-table table-giua truong-rstable-widthper12">Tổ chức công chứng</th>

                                        </tr>
                                        <c:if test="${preventContractList.hopDongListNumber == 0}">
                                            <tr>
                                                <td colspan="7"
                                                    style="height: 100%;background-color: #ececec; line-height: 5.429;text-align: center;font-size: 265%">
                                                    Chưa có dữ liệu
                                                </td>
                                            </tr>
                                        </c:if>
                                        <c:if test="${preventContractList.hopDongListNumber != 0}">
                                            <c:forEach items="${preventContractList.hopDongList}" var="item">

                                                <tr>
                                                    <td class='${!preventContractList.stringKey.equals("")?"highlight-content":""} border-table  align-giua '>
                                                            ${item.notary_date}
                                                    </td>
                                                    <td class='${!preventContractList.stringKey.equals("")?"highlight-content":""} border-table align-giua' style="color: black;">
                                                        <a href="${contractDetailUrl}/${item.tpid}">${item.contract_number}</a>
                                                    </td>
                                                    <td class='${!preventContractList.stringKey.equals("")?"highlight-content":""} border-table truong-text-verticel'
                                                        style="color: black;">${item.contract_name}</td>
                                                    <td class='${(!preventContractList.stringKey.equals("")||!preventContractList.ownerInfo.equals(""))?"highlight-content":""} border-table truong-text-verticel' style="color: black;" >
                                                        <c:if test="${item.relation_object.length() <= 70}">
                                                            ${item.relation_object.replace('\\n', '<br>')}
                                                        </c:if>
                                                        <c:if test="${item.relation_object.length() > 70}">
                                                            ${item.relation_object.substring(0,70).replace('\\n', '<br>')}
                                                            <img data-html="true" data-toggle="popover" title="Bên liên quan" data-trigger="click"  data-placement="top"  src="<%=request.getContextPath()%>/static/image/xemthem.png">
                                                            <span class="hidden">${item.relation_object.replace('\\n', '<br>')}</span>
                                                        </c:if>
                                                    </td>
                                                    <td class='${(!preventContractList.stringKey.equals("")||!preventContractList.propertyInfo.equals(""))?"highlight-content":""} border-table truong-text-verticel'
                                                        style="color: black;" >
                                                        <c:if test="${(item.property_info.length()<=70 && item.transaction_content.length() <=70)}">
                                                            <div>Tài sản:</div>${item.property_info.replace('\\n', '<br>')}
                                                            <div>Nội dung hợp đồng:</div>${item.transaction_content.replace('\\n', '<br>')}
                                                        </c:if>
                                                        <c:if test="${(item.property_info.length()>70 && item.transaction_content.length() >70)}">
                                                            <div>Tài sản:</div>${item.property_info.substring(0,70).replace('\\n', '<br>')} ...
                                                            <img data-html="true" data-toggle="popover" title="Nội dung" data-trigger="click"  data-placement="top" src="<%=request.getContextPath()%>/static/image/xemthem.png">
                                                            <span class="hidden"><div class='title-green'>Tài sản:</div>${item.property_info}<div class='title-green'>Nội dung hợp đồng:</div>${item.transaction_content.replace('\\n', '<br>')}</span>
                                                        </c:if>
                                                    </td>
                                                    <td class='${!preventContractList.stringKey.equals("")?"highlight-content":""} border-table truong-text-verticel'
                                                        style="color: black;">${item.notary_person}</td>
                                                    <td class='${!preventContractList.stringKey.equals("")?"highlight-content":""} border-table truong-text-verticel' style="color: black;">
                                                            ${item.notary_office_name}
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                            <tr class="table-tr-xam ">
                                                <th colspan="7"><div class="truong-padding-foot-table"> Tổng số <span style="color: red;">${preventContractList.hopDongListNumber}</span>
                                                    dữ liệu </div>
                                                    <div class="align-phai">
                                                        <c:if test="${preventContractList.hopDongPage==1}">
                                                        <img
                                                             class="truong-pagging-icon"
                                                             src="<%=request.getContextPath()%>/static/image/pagging/icon1.png">

                                                        <img
                                                             class="truong-pagging-icon"
                                                             src="<%=request.getContextPath()%>/static/image/pagging/icon2.png">
                                                        </c:if>
                                                        <c:if test="${preventContractList.hopDongPage!=1}">
                                                            <img onclick="firstPage(2)"
                                                                 class="pagging-icon"
                                                                 src="<%=request.getContextPath()%>/static/image/pagging/icon1.png">

                                                            <img onclick="previewPage(2)"
                                                                 class="pagging-icon"
                                                                 src="<%=request.getContextPath()%>/static/image/pagging/icon2.png">
                                                        </c:if>
                                                            ${preventContractList.hopDongPage}
                                                        trên ${preventContractList.hopDongTotalPage}
                                                        <c:if test="${preventContractList.hopDongPage== preventContractList.hopDongTotalPage}">
                                                        <img
                                                             class="truong-pagging-icon"
                                                             src="<%=request.getContextPath()%>/static/image/pagging/icon8.png">
                                                        <img
                                                             class="truong-pagging-icon"
                                                             src="<%=request.getContextPath()%>/static/image/pagging/icon7.png">
                                                        </c:if>
                                                        <c:if test="${preventContractList.hopDongPage!= preventContractList.hopDongTotalPage}">
                                                            <img onclick="nextPage(2)"
                                                                 class="pagging-icon"
                                                                 src="<%=request.getContextPath()%>/static/image/pagging/icon8.png">
                                                            <img onclick="lastPage(2)"
                                                                 class="pagging-icon"
                                                                 src="<%=request.getContextPath()%>/static/image/pagging/icon7.png">
                                                        </c:if>
                                                    </div>
                                                </th>


                                            </tr>
                                        </c:if>


                                    </table>
                                </div>

                            </div>

                        </div>
                    </div>


                </div>
            </div>


        </form>
        <script>
            function searchSubmit(){
                $('#key-search').val($('#key-search').val().trim());
                $('#property-information').val($('#property-information').val().trim());
                $('#property-own').val($('#property-own').val().trim());
                $('#daDuyetPage').val(1);
                $('#hopDongPage').val(1);
                $( "#search-frm" ).submit();
            }
            $(window).on('load resize', function() {
                var win = $(this);
                if (win.width() < 1600) {
                    $('.truong-rs-tcbt2').removeClass('col-md-5');
                    $('.truong-rs-tcbt2').addClass('col-md-6');


                } else {
                    $('.truong-rs-tcbt2').removeClass('col-md-6');
                    $('.truong-rs-tcbt2').addClass('col-md-5');

                }
            });
        </script>

        <script>


            $(window).on('load resize', function() {
                var win = $(this);
                if (win.width() < 1510) {

                    $('.truong-rs-ttnct').addClass('col-md-2');

                } else {
                    $('.truong-rs-ttnct').removeClass('col-md-2');

                }
            });
        </script>
        <script>
            function firstPage(tab){
                if(tab == 1){
                    $('#daDuyetPage').val(1);
                }
                if(tab == 2){
                    $('#hopDongPage').val(1);
                }

                $( "#defaultTabOpen" ).val(tab);
                $( "#search-frm" ).submit();
            }
            function previewPage(tab){
                if(tab == 1){
                    var daDuyetPage = $('#daDuyetPage').val();
                    $('#daDuyetPage').val(parseInt(daDuyetPage) -1);
                }
                if(tab == 2){
                    var hopDongPage = $('#hopDongPage').val();
                    $('#hopDongPage').val(parseInt(hopDongPage) -1);
                }
                $( "#defaultTabOpen" ).val(tab);
                $( "#search-frm" ).submit();
            }
            function nextPage(tab){
                if(tab == 1){
                    var daDuyetPage = $('#daDuyetPage').val();
                    $('#daDuyetPage').val(parseInt(daDuyetPage) +1);
                }
                if(tab == 2){
                    var hopDongPage = $('#hopDongPage').val();
                    $('#hopDongPage').val(parseInt(hopDongPage) +1);
                }
                $( "#defaultTabOpen" ).val(tab);
                $( "#search-frm" ).submit();
            }
            function lastPage(tab){
                if(tab == 1){
                    var lastPage = $('#daDuyetTotalPage').val();
                    $('#daDuyetPage').val(lastPage);
                }
                if(tab == 2){
                    var lastPage = $('#hopDongTotalPage').val();
                    $('#hopDongPage').val(lastPage);
                }
                $( "#defaultTabOpen" ).val(tab);
                $( "#search-frm" ).submit();
            }

            function openAdvanceSearchFrm() {
                $("#basic-frm").css("display", "none");
                $("#advance-frm").css("display", "block");
                $('#isAdvanceSearch').val("true");
            }
            function openBasicSearchFrm() {
                $("#basic-frm").css("display", "block");
                $("#advance-frm").css("display", "none");
                $('#isAdvanceSearch').val("false");
            }
            $(document).ready(function () {

                $(".tab_content").hide();
                $(".tab_content:first").show();

                $("ul.tabs li").click(function () {
                    $("ul.tabs li").removeClass("active");
                    $(this).addClass("active");
                    $(".tab_content").hide();
                    var activeTab = $(this).attr("rel");
                    $("#" + activeTab).fadeIn();
                });
                loadMenu();
            });
            var defaultTab = ${preventContractList.defaultTabOpen};
            $(document).ready(function () {
                if (defaultTab == 1) activaTab('daduyet');
                if (defaultTab == 2) activaTab('hopdong');
                if($('#isAdvanceSearch').val()=="true") openAdvanceSearchFrm();
                else openBasicSearchFrm();
               // $('[data-toggle="popover"]').popover();

                $("[data-toggle=popover]").popover({
                    html: true,
                    content: function() {
                        return $(this).parent().children("span").html();
                    }
                });

                $("[data-toggle=popover]").on('click', function (e) {
                    $("[data-toggle=popover]").not(this).popover('hide');
                });

                $("[data-toggle=popover]").click(function (e) {
                    e.stopPropagation();
                });

                $(document).click(function (e) {
                    if (($('.popover').has(e.target).length == 0) || $(e.target).is('.close')) {
                        $("[data-toggle=popover]").popover('hide');
                    }
                });

            });

            function activaTab(tab) {
                $('.nav-tabs a[href="#' + tab + '"]').tab('show');
                if(tab=='daduyet') $('#defaultTabOpen').val(1);
                if(tab=='hopdong') $('#defaultTabOpen').val(2);
            }
            function setDefaultTabOpen(tab){
                $('#defaultTabOpen').val(tab);
            }
            function clearText() {
                $('#property-type').val("00");
                $('#property-information').val("");
                $('#property-own').val("");

            };
            $(document).ready(function () {
                var keyList=$('#listKey').val();
                var text = $('#key-search').val();
                var property = $('#property-information').val();
                var propertyown = $('#property-own').val();
                var keyword = text +" "+property +" "+ propertyown;
                keyword=$.trim(keyword);
                var options=true;
                keyList=keyList.replace(/[\^\'\!\~\@\#\$\%\&]/g, '');
                keyword=keyword.replace(/[\^\'\!\~\@\#\$\%\&]/g, '');
                if(keyword.indexOf('"') >= 0){
                    options=false;
                    keyword=keyList;

                    var values = [];
                    var newArray = keyList.split(',');
                    for (var i = 0; i < newArray.length; i++) {
                        values.push(newArray[i]);
                    }

                    keyword=values;
                }
                //  alert(options);
              //  keyword = keyword.replace(/"/g,'');
                var optionsAccuracy='"exactly"';
             //   if($('#key-search').val().indexOf('*') >= 0)
              //      optionsAccuracy='"complementary"';
                //keyword = keyword.replace(/\*/g, ' ');
                // Determine selected options
                //var options = {};
                //options[0] = "separateWordSearch";
                //options[1] = "enabled";
                // Remove previous marked elements and mark
                // the new keyword inside the context
                // alert(options+ '-' +optionsAccuracy);

                $(".highlight-content").unmark({
                    done: function() {
                        //   $(".highlight-content").mark(keyword, options);
                        $(".highlight-content").mark(keyword, {
                            "ignorePunctuation": [
                                "/",
                                "-",
                                "!"
                            ],
                            "separateWordSearch":options,
                            "wildcards": "enabled",
                            // "accuracy": "exactly",
                            "accuracy": {
                                "value": "exactly",
                                "limiters": [":",";",",", ".","?"]
                            },
                            "separateWordSearch":options,
                            "wildcards": "enabled",


                            //  "separateWordSearch": true,
                            //  "wildcards": true,
                            //  "diacritics": true,
                            //  "accuracy":"complementary",
                            "synonyms": {
                                "ư":"u",
                                "ứ":"u",
                                "ừ":"u",
                                "ử":"u",
                                "ữ":"u",
                                "ự":"u",

                                "ệ":"e",
                                "ê":"e",
                                "ề":"e",
                                "ế":"e",
                                "ể":"e",
                                "ễ":"e",

                                "ô":"o",
                                "ồ":"o",
                                "ố":"o",
                                "ộ":"o",
                                "ổ":"o",
                                "ỗ":"o",

                                "ù":"u",
                                "ú":"u",
                                "ụ":"u",
                                "ủ":"u",
                                "ũ":"u",

                                "è":"e",
                                "é":"e",
                                "ẹ":"e",
                                "ẻ":"e",
                                "ẽ":"e",

                                "ò":"o",
                                "ó":"o",
                                "ọ":"o",
                                "ỏ":"o",
                                "õ":"o",

                                "à":"a",
                                "á":"a",
                                "ạ":"a",
                                "ả":"a",
                                "ã":"a",

                                "â":"a",
                                "ấ":"a",
                                "ầ":"a",
                                "ẩ":"a",
                                "ẫ":"a",
                                "ậ":"a",

                                "ă":"a",
                                "ắ":"a",
                                "ằ":"a",
                                "ẳ":"a",
                                "ẵ":"a",
                                "ặ":"a",

                                "ì":"i",
                                "í":"i",
                                "ị":"i",
                                "ỉ":"i",
                                "ĩ":"i",

                                "ơ":"o",
                                "ờ":"o",
                                "ớ":"o",
                                "ợ":"o",
                                "ở":"o",
                                "ỡ":"o"
                            }
                        });
                    }
                });
                $('.textInputSearch').keypress(function (e) {
                    if (e.which == 13) {

                        $('#daDuyetPage').val(1);
                        $('#hopDongPage').val(1);
                        $( "#search-frm" ).submit();
                    }
                });
                $('#listKey').val("");
            });
        </script>


        <jsp:include page="/WEB-INF/pages/layout/footer.jsp"/>
        <script>
            $(document).ready(function () {
                loadMenu();
            });
            function loadMenu(){
                $(".sidebar-nav > li > #tra-cuu-thong-tin-lien-tinh").addClass("father-menu");
            }
        </script>
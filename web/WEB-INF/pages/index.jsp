<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/pages/layout/header.jsp"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/index.css">
<jsp:include page="/WEB-INF/pages/layout/body_top.jsp"/>
<%--
    Thêm mói nhóm quyền
--%>
<spring:url value="/system/create-grouprole" var="addUrl"/>
<spring:url value="/announcement/update-view" var="announcementUrl"/>
<spring:url value="/contract/detail" var="contractUrl"/>
<spring:url value="/announcement/list" var="announcementListUrl"/>
<spring:url value="/transaction/search" var="searchUrl"/>
<spring:url value="/contract/add" var="addUrl"/>
<spring:url value="/contract/get-kinds" var="getKindUrl"/>
<spring:url value="/system/download" var="downloadUrl"/>
<spring:url value="/announcement/download-from-stp" var="downloadUrlFromSTP"/>
<spring:url value="/announcement/download" var="downloadUrlTCCC"/>
<spring:url value="system/osp/contracttemplate-view" var="contractTemplateView"/>


<div id="menu-map">
    <a href="#menu-toggle" id="menu-toggle"><img src="<%=request.getContextPath()%>/static/image/menu-icon.png"></a>
    <span id="web-map">Trang chủ</span>
</div>
<div id="home-wapper">
    <div class="col-md-12">
        <div id="uchi-status" style="padding-left: 15px;padding-right: 15px; padding-bottom: 5px;margin-top:0px !important;">
            <c:if test="${successCode != null}">
                <div class="status-success"><img class="status-img" src="<%=request.getContextPath()%>/static/image/success.png">${successCode}</div>
            </c:if>
            <c:if test="${errorCode != null}">
                <div class="status-error"><img class="status-img" src="<%=request.getContextPath()%>/static/image/error.png">${errorCode}</div>
            </c:if>
        </div>
        <div class="col-md-8">
            <div class="home-header "><a href="${announcementListUrl}" class="truong-small-home">BẢNG THÔNG BÁO NỘI
                BỘ</a></div>


            <div class="home-content">

                <c:forEach items="${homeForm.announcementArrayList}" var="item" varStatus="index">
                    <div class="other-row"><span class="other-cursor">${index.index + 1}</span>
                        <div class="other-title">
                            <a href="${announcementUrl}/${item.aid}">${item.title}</a>
                            <c:if test="${item.importance_type == 2}">
                                <img style="height: 21px;float: top;padding-bottom: 7px;"
                                     src="<%=request.getContextPath()%>/static/image/flag.png">
                            </c:if>
                        </div>
                    </div>

                </c:forEach>

            </div>
            <%-- <div ="home-anouncement-other">Thông báo khác<span class="ann-see-all"><a href="${announcementListUrl}">Xem tất cả ></a></span>
             </div>--%>


            <div class="home-header truong-margin-top30px"><a href="${announcementListUrl}?tab=stp"
                                                              class="truong-small-home">BẢNG THÔNG BÁO TỪ SỞ TƯ PHÁP</a>
            </div>
            <div class="home-content">

                <c:forEach items="${homeForm.stpAnnouncementArrayList}" var="item" varStatus="index">
                    <div class="other-row"><span class="other-cursor">${index.index + 1}</span>
                        <div class="other-title">
                            <a href="<%=request.getContextPath()%>/announcement/view/${item.aid}">${item.title}</a>
                            <c:if test="${item.importance_type == 2}">
                                <img style="height: 21px;float: top;padding-bottom: 7px;"
                                     src="<%=request.getContextPath()%>/static/image/flag.png">
                            </c:if>
                        </div>
                    </div>

                </c:forEach>

            </div>
            <%--<div class="home-anouncement-other">Thông báo khác<span class="ann-see-all"><a href="${announcementListUrl}">Xem tất cả ></a></span>
            </div>--%>

            <div class="home-header truong-margin-top30px"><a href="${announcementListUrl}" class="truong-small-home">HỢP
                ĐỒNG MỚI NHẬP </a></div>
            <div class="home-content">

                <c:forEach items="${homeForm.contractArrayList}" var="item" varStatus="index">
                    <div class="other-row"><span class="other-cursor">${index.index + 1}</span>
                        <div class="other-title"><a href="${contractUrl}/${item.id}">${item.contract_number}</a></div>

                    </div>

                </c:forEach>

            </div>

        </div>
        <div class="col-md-4">
            <div class="home-header"><a href="" class="truong-small-home">CÔNG CỤ TRUY CẬP NHANH</a></div>
            <div class="home-content col-md-12">
                <div class="col-md-6 clear-padding"><a href="${searchUrl}"><img class="home-fast-icon"
                                                                                src="<%=request.getContextPath()%>/static/image/home-1.png"
                                                                                width="90%"></a></div>
                <div class="col-md-6 clear-padding align-phai"><a href="${addUrl}"><img class="home-fast-icon"
                                                                                        src="<%=request.getContextPath()%>/static/image/home-2.png"
                                                                                        width="90%"></a></div>
            </div>
            <div class="home-header"><a href="" class="truong-small-home">HỢP ĐỒNG CÔNG CHỨNG</a></div>
            <div class="home-content">

                <div class="other-row">
                    <div class="other-title2 truong-other-title"><img class="home-tttc"
                                                                      src="<%=request.getContextPath()%>/static/image/cursor.png">
                        <a href="<%=request.getContextPath()%>/contract/list" onclick="showImage()">Hợp đồng công
                            chứng </a></div>
                </div>
                <div class="other-row">
                    <div class="other-title2 truong-other-title"><img class="home-tttc"
                                                                      src="<%=request.getContextPath()%>/static/image/cursor.png">
                        <a href="<%=request.getContextPath()%>/contract/not-sync-list" onclick="showImage()">Hợp đồng
                            chưa đồng
                            bộ </a></div>
                </div>
            </div>
            <div class="home-header col-item"><a href="" class="truong-small-home">DANH SÁCH HỢP ĐỒNG MẪU</a></div>
            <div class="home-content col-md-12 truong-padding-delete">
                <div style="margin-left: 10px;margin-right: 10px;margin-bottom: 10px">
                    <select onchange="changContractkind(this)" id="changContractkind"
                            class="form-control truong-select truong-selectbox-padding">
                        <c:forEach items="${homeForm.contractKinds}" var="item">
                            <option value="${item.contract_kind_code}"
                                    onchange="getcontractTemplates(item.contractKindId)">${item.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div id="contract-template"  style="margin: 20px;">
                        <c:forEach items="${homeForm.contractTemplates}" var="item">
                            <c:if test="${item.file_name != null && item.file_name != '' && item.file_name != 'null'}">
                                <a href="${downloadUrl}?filename=${item.file_name}&filepath=${item.file_path}">
                                    ${item.file_name}
                                </a><br>
                            </c:if>
                        </c:forEach>
                </div>
            </div>
        </div>


    </div>
</div>
<jsp:include page="/WEB-INF/pages/layout/footer.jsp"/>
<!-- Modal -->
<div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <c:forEach items="${homeForm.stpPopupAnnouncement}" var="item">
            <div class="panel-heading" style="background-color: #2e9ad0 ">
                <h5 class="panel-title truong-text-colorwhite truong-modal-heading"
                    style="padding-left: 15px !important;">
                        ${item.title}

                </h5>
                <button type="button" class="close truong-button-xoa" data-dismiss="modal" style="margin-bottom: 5px">
                    <img
                            src="<%=request.getContextPath()%>/static/image/close.png"
                            class="control-label truong-imagexoa-fix"></button>


            </div>

            <div class="panel-body" style="padding-bottom: 0px !important;">
                <span class="sender-info"
                      style="padding-bottom: 7px !important;">Đăng bởi: ${item.sender_info}, ngày ${item.entryDateTimeConver}</span></br>
                <span class="col-md-12 truong-padding-rightdelete"> ${homeForm.contentHtmlFromSTP} </span>

            </div>

            <div class="modal-footer" style="padding-left: 30px !important;">
                <div class="col-md-12">
                    <label class="control-label label-bam-trai" style="float: left">File đính kèm: </label>
                </div>
                <c:if test="${item.attach_file_path == null}">
                    <span style="float: left">Không có file đính kèm</span>
                </c:if>
                <c:if test="${item.attach_file_path.equals('')}">
                    <span style="float: left">Không có file đính kèm</span>
                </c:if>
                <c:if test="${!item.attach_file_path.equals('') && item.attach_file_path != null}">
                    <div class="col-md-12">
                        <c:forEach items="${listFileName}" var="listFileName" varStatus="count">
                    <span style="float: left">${count.index + 1} : <a class="truong-small-linkbt"
                                                                      href="${downloadUrlFromSTP}/${item.aid}/${count.index}">${listFileName}</a></span><br>
                        </c:forEach>
                    </div>
                </c:if>
            </div>


        </div>
        </c:forEach>

    </div>
</div>

<%--End Modal--%>


<!-- Modal -->
<div class="modal fade" id="myModal2" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <c:forEach items="${homeForm.announcementPopup}" var="item">
            <div class="panel-heading" style="background-color: #2e9ad0 ">
                <h5 class="panel-title truong-text-colorwhite truong-modal-heading"
                    style="padding-left: 15px !important;">
                        ${item.title}


                </h5>
                <button type="button" class="close truong-button-xoa" data-dismiss="modal" style="margin-bottom: 5px">
                    <img
                            src="<%=request.getContextPath()%>/static/image/close.png"
                            class="control-label truong-imagexoa-fix"></button>
            </div>
            <div class="panel-body" style="padding-bottom: 0px !important;">

                    <%--<span class="sender-info" style="padding-bottom: 7px !important;">Đăng bởi: ${homeForm.latest.sender_info}, ngày ${homeForm.latest.entry_date_time}</span></br>--%>
                <span class="sender-info"
                      style="padding-bottom: 7px !important;">Đăng bởi: ${item.sender_info}, ngày ${item.entry_date_time}</span></br>


                <span class="col-md-12 truong-padding-rightdelete"> ${homeForm.contentHtml} </span>

            </div>
            <%--<div class="modal-footer" style="padding-left: 30px !important;">

                <c:if test="${item.attach_file_path == null}">
                    <label class="control-label label-bam-trai" style="float: left">File đính kèm :</label>
                    <span style="float: left">Không có file đính kèm </span>
                </c:if>
                <c:if test="${item.attach_file_path.equals('')}">
                    <label class="control-label label-bam-trai" style="float: left">File đính kèm :</label>
                    <span style="float: left">Không có file đính kèm </span>
                </c:if>
                <c:if test="${item.attach_file_path != null && item.attach_file_path.equals('') == false}">
                    <label class="control-label label-bam-trai" style="float: left">File đính kèm :</label>
                    <div class="truong-image-tablexanh"></div>
                    <a style="float: left" href="${downloadUrl}/${item.aid}">${item.attach_file_name}</a>
                </c:if>


            </div>--%>
                <div class="modal-footer" style="padding-left: 30px !important;">
                    <div class="col-md-12">
                        <label class="control-label label-bam-trai" style="float: left">File đính kèm: </label>
                    </div>
                    <c:if test="${item.attach_file_path == null}">
                        <span style="float: left">Không có file đính kèm</span>
                    </c:if>
                    <c:if test="${item.attach_file_path.equals('')}">
                        <span style="float: left">Không có file đính kèm</span>
                    </c:if>
                    <c:if test="${!item.attach_file_path.equals('') && item.attach_file_path != null}">
                        <div class="col-md-12">
                            <c:forEach items="${listFileNameTCCC}" var="listFileNameTCCC" varStatus="count">
                    <span style="float: left">${count.index + 1} : <a class="truong-small-linkbt"
                                                                      href="${downloadUrlTCCC}/${item.aid}/${count.index}">${listFileNameTCCC}</a></span><br>
                            </c:forEach>
                        </div>
                    </c:if>
                </div>
        </div>
        </c:forEach>

    </div>
</div>
<%--End Modal--%>

<%--<div class="col-md-12 content">
    <div class="col-md-8 col-item">
        <div class="col-md-6">
            <div class="col-md-2">
                <img src="${pageContext.request.contextPath}/static/image/ico_stop_large.png"/>
            </div>
            <div style="float: left">
                <ul class="ul-data">
                    <li class="title">THÔNG TIN NGĂN CHẶN</li>
                    <li><a href="#">Tra cứu thông tin ngăn chặn</a></li>
                </ul>
            </div>
        </div>

        <div class="col-md-6">
            <div class="col-md-2">
                <img src="${pageContext.request.contextPath}/static/image/ico_temp_large.png"/>
            </div>
            <div style="float: left">
                <ul class="ul-data">
                    <li class="title">HỢP ĐỒNG CÔNG CHỨNG</li>
                    <li><a href="#">Danh sách hợp đồng công chứng</a></li>
                    <li><a href="#">Thêm mới hợp đồng công chứng</a></li>
                    <li><a href="#">Hợp đồng mẫu</a></li>
                </ul>
            </div>
        </div>
    </div>

    <div class="col-md-8 col-item">
        <div class="col-md-6">
            <div class="col-md-2">
                <img src="${pageContext.request.contextPath}/static/image/ico_chart_large.png"/>
            </div>
            <div style="float: left">
                <ul class="ul-data">
                    <li class="title">BÁO CÁO THỐNG KÊ</li>
                    <li><a href="#">Báo cáo theo thông tư 20</a></li>
                    <li><a href="#">Báo cáo theo nhóm hợp đồng</a></li>
                    <li><a href="#">Báo cáo theo công chứng viên</a></li>
                    <li><a href="#">Báo cáo theo chuyên viên soạn thảo</a></li>
                    <li><a href="#">Báo cáo theo ngân hàng </a></li>
                </ul>
            </div>
        </div>

        <div class="col-md-6">
            <div style="float: left">
                <ul class="ul-data-not-title">
                    <li><a href="#">Báo cáo hợp đồng lỗi</a></li>
                    <li><a href="#">Báo cáo hợp đồng cần bổ sung</a></li>
                    <li><a href="#">Thống kê tổng hợp</a></li>
                    <li><a href="#">In sổ công chứng</a></li>
                </ul>
            </div>
        </div>
    </div>

</div>--%>

</div>
<script>
    function changContractkind(e) {
        var kind_code = $(e).val();
        $.ajax({
            type: "GET",
            url: '${getKindUrl}',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: {
                kind_code: kind_code,
            },
            success: function (response) {
                var result = response.result;

                var template = result.split(";");
                var html = "";
                for (var i = 0; i < template.length; i++) {
                    var temp = template[i].split(",");
                    //html += "<a href='${downloadUrl}" + "?filename=" + temp[1] + "&filepath=" + temp[2] + "'>";
                    html += "+ <a href='<%=request.getContextPath()%>/template/contract/edit/"+temp[0]+"' style='margin-top:3px;margin-bottom: 3px; '>";
                    html += temp[3];
                    html += "</a><br>";
                }
                $("#contract-template").html(html);
            },
            error: function (e) {
                console.log("ERROR: ", e);
            },
            done: function (e) {
                console.log("DONE");
            }
        })
    }
</script>


<script type="text/javascript">

    if (${homeForm.stpPopupAnnouncement.size() == 1}) {

        $(window).on('load', function () {
            $('#myModal').modal('show');
        });

    }
    $("#myModal").on("hidden.bs.modal", function () {
        if (${homeForm.announcementPopup.size() == 1 }) {
            $('#myModal2').modal('show');
        }
    });

    if (${homeForm.announcementPopup.size() == 1 } &&
    ${homeForm.stpPopupAnnouncement.size()<1 } )
    {
        $(window).on('load', function () {
            $('#myModal2').modal('show');
        });
    }


</script>
<script>
    $(document).ready(function () {
        loadMenu();
    });

    function loadMenu() {
        $(".sidebar-nav > li > #trang-chu").addClass("father-menu");
    }

    function getcontractTemplates(id) {
        alert(id);
    }

    $(document).ready(function () {
        var kind_code = $("#changContractkind").val();
        $.ajax({
            type: "GET",
            url: '${getKindUrl}',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: {
                kind_code: kind_code,
            },
            success: function (response) {
                var result = response.result;

                var template = result.split(";");
                var html = "";
                for (var i = 0; i < template.length; i++) {
                    var temp = template[i].split(",");
                    //html += "<a href='${downloadUrl}" + "?filename=" + temp[1] + "&filepath=" + temp[2] + "'>";
                    html += "+ <a href='<%=request.getContextPath()%>/template/contract/edit/"+temp[0]+"' style='margin-top:3px;margin-bottom: 3px; '>";
                    html += temp[3];
                    html += "</a><br>";
                }
                $("#contract-template").html(html);
            },
            error: function (e) {
                console.log("ERROR: ", e);
            },
            done: function (e) {
                console.log("DONE");
            }
        })
    });
</script>
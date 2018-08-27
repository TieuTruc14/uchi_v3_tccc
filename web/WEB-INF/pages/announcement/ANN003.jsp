<%@ page import="com.vn.osp.common.util.ValidationPool" %>
<%@ page import="com.vn.osp.common.global.Constants" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/pages/layout/header.jsp"/>
<jsp:include page="/WEB-INF/pages/layout/body_top.jsp"/>
<%--chỉnh sửa thông báo--%>
<style type="text/css">
    #abc_chosen{
        display: none!important;
    }
</style>
<script src="<%=request.getContextPath()%>/static/ckeditor/ckeditor.js"></script>
<spring:url value="/announcement/update" var="updateUrl"/>
<spring:url value="/announcement/update-view" var="backUrl"/>
<spring:url value="/announcement/list" var="backToListUrl"/>
<spring:url value="/announcement/list" var="backToListUrl"/>
<spring:url value="/announcement/download" var="downloadUrl"/>
<spring:url value="/announcement/removeFile" var="removeFileUrl"/>

<div id="menu-map">
    <a href="#menu-toggle" id="menu-toggle"><img src="<%=request.getContextPath()%>/static/image/menu-icon.png"></a>
    <span id="web-map">Chỉnh sửa thông báo</span>
</div>
<div class="col-md-12" style="width: 95%;margin-left: 36px">
    <div id="uchi-status" class="padding-default">

        <c:if test = "${updateAnnouncement.announcement.success==false}">
            <div class="status-error"><img class="status-img" src="<%=request.getContextPath()%>/static/image/error.png">Chỉnh sửa thông tin thông báo không thành công</div>
        </c:if>
        <c:if test="${errorCode != null}">
            <div class="status-error"><img class="status-img" src="<%=request.getContextPath()%>/static/image/error.png">${errorCode}</div>
        </c:if>
    </div>
    <div class="panel-group" id="accordion">
        <form class="form-horizontal" action="${updateUrl}" method="post" enctype="multipart/form-data">

            <input type="hidden" name="aid" value="${updateAnnouncement.announcement.aid}">
            <input type="hidden" name="sender_info" value="${updateAnnouncement.announcement.sender_info}">

            <div class="panel panel-default" id="panel1">
                <div class="panel-heading">
                    <h4 class="panel-title">

                            THÔNG TIN THÔNG BÁO

                    </h4>

                </div>
                <div class="panel-body">
                    <div class="form-group">
                        <label class="col-md-2 control-label label-bam-trai required">Tiêu đề thông báo</label>
                        <div class="col-md-10">
                            <input type="text" class="form-control" name="title"
                                   value="${updateAnnouncement.announcement.title}">
                            <div class="error_tooltip">${updateAnnouncement.announcement.title_}</div>

                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-2 control-label label-bam-trai required">Nội dung thông báo</label>
                        <div class="col-md-10">
                            <textarea class="form-control" name="content" id="content">${updateAnnouncement.announcement.content}</textarea>
                            <div class="error_tooltip">${updateAnnouncement.announcement.content_}</div>

                        </div>
                    </div>


                        <div class="form-group">
                            <label class="col-md-2 control-label label-bam-trai">File đính kèm</label>
                            <div class="col-md-7 reload-file" id="reload-file">
                                <c:if test="${updateAnnouncement.announcement.attach_file_name.equals('') == false}">
                                    <c:forEach items="${listFileName}" var="item" varStatus="count">
                                        <span><a
                                                href="${downloadUrl}/${updateAnnouncement.announcement.aid}/${count.index}">${item}</a><a
                                                style="margin-left: 20px"
                                                onclick="removeFile(${updateAnnouncement.announcement.aid},${count.index})"
                                                class="removeFile">Gỡ bỏ</a></span><br>
                                        <input type="hidden" name="multipartFile">
                                    </c:forEach>
                                </c:if>
                                <div class="themfile">
                                    <c:if test="${countlistFileName < 3}">
                                        <input style='float: left ;width: 350px' class="prevent-file_upload myFile"
                                               type="file" id="myFile" name="multipartFile">
                                    </c:if>
                                    <div class="error_tooltip">${updateAnnouncement.announcement.prevent_data_}</div>
                                </div>

                            </div>
                            <div class="col-md-2">
                                <a class="btn btn-primary btn-xs" style="float: right" onclick="themFile();">Thêm file</a>
                            </div>
                        </div>

                    <div class="form-group">
                        <label class="col-md-2 control-label label-bam-trai">Tùy chọn</label>
                        <div class="col-md-10">
                            <c:if test="${updateAnnouncement.announcement.importance_type == 2}">
                                <input class="truong-check-fixminh2" type="checkbox" name="importance_type" value="2" checked/>
                            </c:if>
                            <c:if test="${updateAnnouncement.announcement.importance_type != 2}">
                                <input  class="truong-check-fixminh2" type="checkbox" name="importance_type" value="2"/>
                            </c:if>

                            <input type="hidden" value="1" name="importance_type"/>
                            <span class="truong-font-label text-nowrap">Tin quan trọng</span>
                        </div>



                    </div>
                    <div class="form-group">
                        <div class="col-md-10 col-md-offset-2">
                      <span>
                        <c:if test="${updateAnnouncement.announcement.popup_display_flg == 1}">

                            <input class="truong-check-fixminh2" type="checkbox" value="1" name="popup_display_flg" checked/>
                        </c:if>
                        <c:if test="${updateAnnouncement.announcement.popup_display_flg != 1}">
                            <input class="truong-check-fixminh2" type="checkbox"  value="1" name="popup_display_flg" />
                        </c:if>
                          <input type="hidden" value="0" name="popup_display_flg"/>

                      </span>
                        <span class="truong-font-label">Hiển thị trong popup(
                            <input type="text" class="form-control popup-display-day" name = "popup_display_day" value=" ${updateAnnouncement.announcement.popup_display_day}" > ngày)</span>
                    </div>

                    </div>
                </div>
            </div>
            <div class="row prevent-type-box">
                <div class="form-group">
                    <%
                        if(ValidationPool.checkRoleDetail(request,"18", Constants.AUTHORITY_SUA)){
                    %>
                    <div class="col-md-2 col-md-offset-4">
                        <input type="submit" class="form-control luu" name="" value="Lưu">
                    </div>
                    <%
                        }
                    %>
                    <div class="col-md-2">
                        <a href="${backToListUrl}"><input type="button" class="form-control huybo" name=""
                                                          value="Hủy bỏ"></a>
                    </div>

                    <!-- Modal -->
                    <div class="modal fade" id="myModal" role="dialog">
                        <div class="modal-dialog">

                            <!-- Modal content-->
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                                </div>
                                <h4 class="modal-title">Thông báo</h4>

                                <div class="modal-body">
                                    <strong><p> Bạn có thực sự muốn xóa không</p></strong>
                                </div>
                                <div class="modal-footer">
                                    <div class="col-md-2 col-md-offset-4">
                                        <a href="${removeUrl}/${updateAnnouncement.announcement.aid}"><input
                                                type="button" class="form-control luu" name="" value="Đồng ý"></a>
                                    </div>
                                    <div class="col-md-2 ">
                                        <a href="${backUrl}/${updateAnnouncement.announcement.aid}"><input type="button"
                                                                                                           class="form-control huybo"
                                                                                                           name=""
                                                                                                           value="Hủy bỏ"></a>
                                    </div>

                                </div>
                            </div>

                        </div>
                    </div>

                    <%--End Modal--%>
                </div>


                <script>
                    $(".modal-wide").on("show.bs.modal", function () {
                        var height = $(window).height() - 200;
                        $(this).find(".modal-body").css("max-height", height);
                    });


                    CKEDITOR.replace('content', {

                        height: '300px',
                        resize_enabled: true
                    });

                    $(document).ready(function () {
                        function checkradiobox() {
                            var radio = $("input[name='announcement_type']:checked").val();
                            if (radio == 0) {
                                $('#demo').hide();
                            }
                            if (radio == 1) {
                                $("#demo").hide();
                            }

                            if (radio == 2) {
                                $("#demo").show();

                            }
                        }

                        $("#radio_1, #radio_2,#radio_3").change(function () {
                            checkradiobox();
                        });

                        checkradiobox();

                    });

                </script>
            </div>
        </form>
    </div>


</div>


<div class="col-md-1"></div>
<div class="modal fade" id="iModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="iModalLabel">Lỗi thêm file đính kèm.</h4>
            </div>
            <div class="modal-body" style="color:red;">
                Chỉ cho phép chèn tối đa 3 file đính kèm!
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
<script>
    $(document).ready(function () {
        console.log(${updateAnnouncement.announcement.aid});

    });
    $(function () {
        $('select#abc').find('option').each(function() {
            var a = $(this).val();
            var ur = $("#test option[value=" + a + "]");
            $(ur).attr("selected", true);

        });
        $(".chosen-select").trigger("chosen:updated");

        $(".test").chosen({
            disable_search_threshold: 10,
            no_results_text: "Không tìm kiếm được kết quả ",
            width: "95%",
            display_selected_options: true
        });
        $('.test').trigger('chosen:updated');
    });
    function deleteFile() {
        var deleteFile = "${updateAnnouncement.announcement.aid}";
        $.ajax({
            type: "GET",
            url: '${removeFileUrl}',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: {
                deleteData: deleteFile,
            },
            success: function (response) {
                if(response.ok = "ok"){
                    $('#removeFile').html('<input type="file"  name="multipartFile">');
                }
            },
            error: function (e) {
                console.log("ERROR: ", e);
            },
            done: function (e) {
                console.log("DONE");
            }
        })
    }
    function removeFile(a, b) {
        var deleteFile = "${updateAnnouncement.announcement.aid}";
        $.ajax({
            type: "get",
            url: '${removeFileUrl}' + '/' + a + '/' + b,
            cache: false,
            data: {
                deleteData: deleteFile,
            },
            success: function (response) {
                if (response.messageCode == "SUCCESS") {
                    $('#reload-file').load(location.href + " #reload-file>*", "");
                } else if (response.messageCode == "ERROR") {
                    $(".msgDeleteFileError").show();
                } else if (response.messageCode == "404") {
                    window.location.replace(domain + path + "/404");
                }
                count_input = count_input - 1;
            },
            error: function () {
                alert('Error while request..');
            }
        });
    }
    $(document).on('click', '.huy', function() {
        $(this).parent().remove();
    });
    function themFile() {
        var n = $(":input[name='multipartFile']").length;
        if (n < 3) {
            $(".themfile").append("<span><input style='float: left ;width: 350px' class='prevent-file_upload myFile ' type='file' name='multipartFile'><a style='float: right' class='huy' >Hủy</a></span>");
        } else {
            $("#iModal").modal("show");
        }
    }
</script>
<jsp:include page="/WEB-INF/pages/layout/footer.jsp"/>
<script src="<%=request.getContextPath()%>/static/chosen2/chosen.jquery.js"></script>



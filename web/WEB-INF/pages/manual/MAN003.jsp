<%@ page import="com.vn.osp.common.util.ValidationPool" %>
<%@ page import="com.vn.osp.common.global.Constants" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/pages/layout/header.jsp"/>
<jsp:include page="/WEB-INF/pages/layout/body_top.jsp"/>

<script src="<%=request.getContextPath()%>/static/ckeditor/ckeditor.js"></script>
<spring:url value="/manual/update" var="updateUrl"/>
<spring:url value="/manual/removeFile" var="removeFileUrl"/>
<spring:url value="/manual/download" var="downloadUrl"/>
<spring:url value="/manual/remove" var="removeUrl"/>
<spring:url value="/manual/list" var="backUrl"/>


<%--
            Chỉnh sửa thông báo
--%>
<div id="menu-map">
    <a href="#menu-toggle" id="menu-toggle"><img src="<%=request.getContextPath()%>/static/image/menu-icon.png"></a>
    <span id="web-map">Chỉnh sửa tài liệu</span>
</div>
<div class="col-md-12" style="width: 95%;margin-left: 36px">
    <div id="uchi-status">
        <c:if test="${successCode != null}">
            <div class="status-success"><img class="status-img"
                                             src="<%=request.getContextPath()%>/static/image/success.png">${successCode}
            </div>
        </c:if>
        <c:if test="${errorCode != null}">
            <div class="status-error"><img class="status-img"
                                           src="<%=request.getContextPath()%>/static/image/error.png">${errorCode}</div>
        </c:if>
    </div>
    <div class="panel-group" id="accordion">
        <form class="form-horizontal" action="${updateUrl}" method="post" enctype="multipart/form-data">
            <input type="hidden" name="mid" value="${manual.mid}">
            <div class="panel panel-default" id="panel1">
                <div class="panel-heading">
                    <h4 class="panel-title">

                        THÔNG TIN TÀI LIỆU

                    </h4>

                </div>
                <div class="panel-body">
                    <div class="form-group">
                        <label class="col-md-2 control-label label-bam-trai required">Tiêu đề</label>
                        <div class="col-md-10">
                            <input type="text" class="form-control" name="title" value="${manual.title}">
                            <div class="error_tooltip">${manual.title_}</div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-2 control-label label-bam-trai required">Nội dung</label>
                        <div class="col-md-10">
                            <textarea class="form-control" name="content" id="content">${manual.content}</textarea>
                            <div class="error_tooltip">${manual.content_}</div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-2 control-label label-bam-trai">File đính kèm</label>
                        <div class="col-md-8" id="removeFile">
                            <c:if test="${manual.file_name.equals('') == false}">
                                <c:forEach items="${listFileName}" var="filaName" varStatus="status">
                                    <div class="removeeee">
                                        File ${status.index + 1} : <a
                                            href="${downloadUrl}/${manual.mid}/${status.index}">${filaName}</a><a
                                            style="margin-left: 20px" onclick="deleteFile(${status.index})">Gỡ bỏ</a><br>
                                    </div>
                                    <input type="hidden" name="multipartFile">
                                </c:forEach>
                            </c:if>
                            <div class="themfile">
                                <c:if test="${countlistFileName < 3}">
                                    <input class="prevent-file_upload myFile" type="file" id="myFile"
                                           name="multipartFile">
                                </c:if>
                            </div>
                            <div class="${maxSizeUpload != null ? "error_tooltip":""}">${maxSizeUpload}</div>
                        </div>
                        <div class="col-md-2">
                            <a class="btn btn-primary btn-xs" style="float: right" onclick="themFile();">Thêm file</a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="truong-prevent-btb col-md-12">
                <div class="truong-button-mid">
                    <%
                        if (ValidationPool.checkRoleDetail(request, "08", Constants.AUTHORITY_SUA)) {
                    %>
                    <div class="truong-button-fixwidthleft">
                        <input type="submit" class="form-control luu" name="" value="Lưu">
                    </div>
                    <%
                        }
                        if (ValidationPool.checkRoleDetail(request, "08", Constants.AUTHORITY_XOA)) {
                    %>
                    <div class="truong-button-fixwidthleft">
                        <input type="button" class="form-control luu" data-toggle="modal" data-target="#myModal" name=""
                               value="Xóa">
                    </div>
                    <%
                        }
                    %>
                    <div class="truong-button-fixwidthleft">
                        <a class="truong-small-linkbt" href="${backUrl}"><input type="button" class="form-control huybo"
                                                                                name="" value="Hủy bỏ"></a>
                    </div>
                </div>
            </div>


            <div class="modal fade" id="myModal" role="dialog">
                <div class="modal-dialog">

                    <!-- Modal content-->
                    <div class="modal-content">
                        <div class="panel-heading" style="background-color: #2e9ad0 ">
                            <h5 class="panel-title truong-text-colorwhite">
                                Xóa tài liệu

                                <button type="button" class="close truong-button-xoa" data-dismiss="modal"
                                        style="margin-bottom: 5px"><img
                                        src="<%=request.getContextPath()%>/static/image/close.png"
                                        class="control-label truong-imagexoa-fix"></button>


                            </h5>

                        </div>
                        <div class="panel-body">
                            <div class="truong-modal-padding" style="padding-bottom: 7%;">
                                <label class="col-md-9 control-label align-giua">Bạn có thực sự muốn xóa dữ liệu
                                    ?</label>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <div class="col-md-3 col-md-offset-3">
                                <a href="${removeUrl}/${manual.mid}" class="truong-small-linkbt"><input type="button"
                                                                                                        class="form-control luu"
                                                                                                        name=""
                                                                                                        value="Đồng ý"></a>
                            </div>
                            <div class="col-md-3 ">
                                <input type="button" data-dismiss="modal" class="form-control huybo" name=""
                                       value="Hủy bỏ">
                            </div>

                        </div>
                    </div>

                </div>
            </div>


            <script type="text/javascript">
                CKEDITOR.replace('content', {

                    height: '300px',
                    resize_enabled: true
                });


            </script>
              
        </form>
    </div>
</div>
<div class="col-md-12"></div>
<div class="modal fade" id="iModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">Lỗi kích cỡ file quá lớn</h4>
            </div>
            <div class="modal-body" style="color:red;">
                File không được vượt quá 10 mb!
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="idModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
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
    function deleteFile(index) {
        var deleteFile = '${manual.mid}';
        alert(index);
        $.ajax({
            type: "GET",
            url: '${removeFileUrl}',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: {
                deleteData: deleteFile,
                index: index
            },
            success: function (response) {
                if (response.ok = "ok") {
                    $('#removeFile').load(location.href + " #removeFile>*","");
                }
            },
            error: function (e) {
                console.log("ERROR: ", e);
            },
            done: function (e) {
                console.log("DONE");
            }
        });
    }

</script>


<div class="col-md-1"></div>
<jsp:include page="/WEB-INF/pages/layout/footer.jsp"/>
<script>
    $(document).ready(function () {
        loadMenu();
    });
    function loadMenu() {
        $(".sidebar-nav > li > #huong-dan").addClass("father-menu");
    }

    $('.myFile').bind('change', function () {
        var size = this.files[0].size;
        if (size > 10240000) {
            $(".myFile").val("");
            $("#iModal").modal("show");
        }
    });
    var countId = 0;
    /*var count_input = $('#countlistFileName').val();*/
    var count_input = 1 + parseInt($('#countlistFileName').val());
    $(document).on('click', '.huy', function () {
        count_input = count_input - 1;
        $(this).parent().remove();
    });

    function themFile() {
        var n = $(":input[name='multipartFile']").length;
        if (n  < 3) {
            $(".themfile").append("<span><input style='float: left ;width: 350px' class='prevent-file_upload myFile " + "file" + countId + "' type='file' name='multipartFile'><a style='float: right' class='huy' >Hủy</a></span>");
        } else {
            $("#idModal").modal("show");
        }
    }

</script>

</html>


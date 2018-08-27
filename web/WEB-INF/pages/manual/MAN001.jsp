<%@ page import="com.vn.osp.common.util.ValidationPool" %>
<%@ page import="com.vn.osp.common.global.Constants" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="/WEB-INF/pages/layout/header.jsp" />
<jsp:include page="/WEB-INF/pages/layout/body_top.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="<%=request.getContextPath()%>/static/ckeditor/ckeditor.js"></script>
<spring:url value="/manual/add" var="addUrl" />
<%--
            Thêm mới thông báo
--%>
<spring:url value="/manual/list" var="backUrl"/>
<div id="menu-map">
    <a href="#menu-toggle" id="menu-toggle"><img src="<%=request.getContextPath()%>/static/image/menu-icon.png"></a>
    <span id="web-map">Thêm mới tài liệu</span>
</div>

<div class="col-md-12" style="width: 95%;margin-left: 36px">
    <div id="uchi-status">
        <c:if test="${successCode != null}">
            <div class="status-success"><img class="status-img" src="<%=request.getContextPath()%>/static/image/success.png">${successCode}</div>
        </c:if>
        <c:if test="${errorCode != null}">
            <div class="status-error"><img class="status-img" src="<%=request.getContextPath()%>/static/image/error.png">${errorCode}</div>
        </c:if>
    </div>
    <div class="panel-group" id="accordion">
        <form class="form-horizontal" action="${addUrl}" method="post" enctype="multipart/form-data" >
            <div class="panel panel-default" id="panel1">
                <div class="panel-heading">
                    <h4 class="panel-title">

                            THÔNG TIN TÀI LIỆU

                    </h4>

                </div>
                <div class="panel-body">
                    <div class="form-group">
                        <label class="col-md-2 control-label label-bam-trai required ">Tiêu đề </label>
                        <div class="col-md-10 ">
                            <input type="text" class="form-control ${addManual.title_!= null ? "error-input":""}" name="title" value ="${addManual.title}">
                            <div class="error_tooltip">${addManual.title_}</div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-2 control-label label-bam-trai required">Nội dung</label>
                        <div class="col-md-10">
                            <textarea class="form-control ${addManual.content_!=null ? "error-input":""}" name="content" id="content" value ="${addManual.content}">${addManual.content}</textarea>
                            <div class="error_tooltip">${addManual.content_}</div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-2 control-label label-bam-trai">File đính kèm</label>
                        <div class="col-md-5 themfile">
                            <input class="prevent-file_upload myFile" type="file" id="myFile" name="multipartFile">
                            <div class="${addManual.content_!= null ? "error_tooltip":""}">${addManual.content_}</div>
                        </div>
                        <div class="col-md-4">
                            <a class="btn btn-primary btn-xs" style="float: right" onclick="themFile();">Thêm file</a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="truong-prevent-btb col-md-12">
                <div class="truong-can-button3">
                    <%
                        if(ValidationPool.checkRoleDetail(request,"08", Constants.AUTHORITY_THEM)){
                    %>
                    <div class="col-md-2 col-md-offset-4 truong-rs-bt2os">
                        <input type="submit" class="form-control luu" name="" value="Lưu">
                    </div>
                    <%
                        }
                    %>
                    <div class="col-md-2 truong-rs-bt2">
                        <a href="${backUrl}" class="truong-small-linkbt"><input type="button" class="form-control huybo" name="" value="Hủy bỏ"></a>
                    </div>
                </div>
            </div>


            <script type="text/javascript">
                CKEDITOR.replace( 'content' , {

                    height: '300px',
                    resize_enabled : true
                } );

            </script>  
        </form>
    </div>
</div>
<div class="col-md-1"></div>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
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
<div class="modal fade" id="iModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
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

<jsp:include page="/WEB-INF/pages/layout/footer.jsp" />
<script>
    $(window).on('load resize',function(){
        var win = $(this);
        if (win.width() < 1200){
            $('.truong-rs-bt2os').removeClass('col-md-2 col-md-offset-4');
            $('.truong-rs-bt2os').addClass('col-md-3 col-md-offset-3');
            $('.truong-rs-bt2').removeClass('col-md-2');
            $('.truong-rs-bt2').addClass('col-md-3');
        }else {
            $('.truong-rs-bt2os').removeClass('col-md-3 col-md-offset-3');
            $('.truong-rs-bt2os').addClass('col-md-2 col-md-offset-4');
            $('.truong-rs-bt2').removeClass('col-md-3');
            $('.truong-rs-bt2').addClass('col-md-2');
        }
    });
</script>
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
            $("#myModal").modal("show");
        }
    });

    $(document).on('click', '.huy', function() {
        $(this).parent().remove();
    });
    function themFile() {
        var count_input = $('input[name="multipartFile"]').length;
        if(count_input  < 3) {
            $(".themfile").append("<span><input style='float: left ;width: 350px' class='prevent-file_upload myFile' type='file' name='multipartFile'><a style='float: right' class='huy' >Hủy</a></span>");
            countId++;
        }else{
            $("#iModal").modal("show");
        }
    }
</script>



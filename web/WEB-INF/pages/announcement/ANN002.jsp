<%@ page import="com.vn.osp.common.util.ValidationPool" %>
<%@ page import="com.vn.osp.common.global.Constants" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="/WEB-INF/pages/layout/header.jsp"/>
<jsp:include page="/WEB-INF/pages/layout/body_top.jsp"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="<%=request.getContextPath()%>/static/ckeditor/ckeditor.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/static/chosen2/chosen.css"/>
<%--
            Thêm mới thông báo
--%>
<spring:url value="/announcement/add" var="addUrl"/>
<spring:url value="/announcement/list" var="backToListUrl"/>
<div id="menu-map">
    <a href="#menu-toggle" id="menu-toggle"><img src="<%=request.getContextPath()%>/static/image/menu-icon.png"></a>
    <span id="web-map">Thêm mới thông báo</span>
</div>
<div class="col-md-12" style="width: 95%;margin-left: 36px">
    <div id="uchi-status">

        <c:if test="${addAnnouncement.announcement.success == false}">
            <div class="status-error"><img class="status-img"
                                           src="<%=request.getContextPath()%>/static/image/error.png">Tạo mới thông báo
                không thành công
            </div>
        </c:if>
    </div>
    <div class="panel-group" id="accordion">
        <form class="form-horizontal" action="${addUrl}" method="post" enctype="multipart/form-data">
            <div class="panel panel-default" id="panel1">
                <div class="panel-heading">
                    <h4 class="panel-title">

                        THÔNG TIN THÔNG BÁO

                    </h4>

                </div>
                <div class="panel-body">
                    <div class="form-group">
                        <label class="col-md-2 control-label label-bam-trai required text-nowrap">Tiêu đề thông
                            báo</label>
                        <div class="col-md-10">
                            <input type="text"
                                   class="form-control ${addAnnouncement.announcement.title_!= null ? "error_input":""}"
                                   name="title" value="${addAnnouncement.announcement.title}">
                            <div class="error_tooltip">${addAnnouncement.announcement.title_}</div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-2 control-label label-bam-trai required">Nội dung thông báo</label>
                        <div class="col-md-10">
                            <textarea
                                    class="form-control ${addAnnouncement.announcement.content_!= null?"error_input":""}"
                                    name="content" id="content"
                                    value="${addAnnouncement.announcement.content}">${addAnnouncement.announcement.content}</textarea>
                            <div class="error_tooltip">${addAnnouncement.announcement.content_}</div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-2 control-label label-bam-trai">File đính kèm</label>
                        <div class="col-md-5 themfile">
                            <input class="prevent-file_upload myFile" type="file" id="myFile" name="multipartFile">
                            <div class="${addAnnouncement.announcement.prevent_data_!= null ? "error_tooltip":""}">${addAnnouncement.announcement.prevent_data_}</div>
                        </div>
                        <div class="col-md-4">
                            <a class="btn btn-primary btn-xs" style="float: right" onclick="themFile();">Thêm file</a>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-2 control-label label-bam-trai">Tùy chọn</label>
                        <div class="col-md-10">
                      <span>

                         <input class="truong-check-fixminh2" type="checkbox" value="02"
                                name="importance_type" ${addAnnouncement.announcement.importance_type} />
                           <input class="truong-check-fixminh2" type="hidden" value="01"
                                  name="importance_type" ${addAnnouncement.announcement.importance_type}/>
                      </span>
                            <span class="truong-font-label text-nowrap">Tin quan trọng</span>
                        </div>


                    </div>
                    <div class="form-group">
                        <div class="col-md-10 col-md-offset-2">
                      <span>

                         <input class="truong-check-fixminh2" type="checkbox" value="1"
                                name="popup_display_flg" ${addAnnouncement.announcement.popup_display_flg} />
                           <input class="truong-check-fixminh2" type="hidden" value="0"
                                  name="popup_display_flg" ${addAnnouncement.announcement.popup_display_flg}/>
                      </span>
                        <span class="truong-font-label">Hiển thị trong popup(
                            <input type="text" class="form-control popup-display-day date-time" name="popup_display_day"
                                   value="${addAnnouncement.announcement.popup_display_day}"> ngày)</span>
                        <div class="error_tooltip">${addAnnouncement.announcement.popup_display_day_}</div>
                        </div>
                    </div>
                </div>

            </div>
            <div class="truong-prevent-btb">
                <div class="truong-can-button3">
                    <%
                        if (ValidationPool.checkRoleDetail(request, "18", Constants.AUTHORITY_THEM)) {
                    %>
                    <div class="col-md-2 col-md-offset-4 truong-rs-bt2os">
                        <input type="submit" class="form-control luu" name="" value="Lưu">
                    </div>
                    <%
                        }
                    %>
                    <div class="col-md-2 truong-rs-bt2">
                        <a href="${backToListUrl}" class="truong-small-linkbt"><input type="button"
                                                                                      class="form-control huybo"
                                                                                      name="" value="Hủy bỏ"></a>
                    </div>
                </div>
            </div>


            <script type="text/javascript">
                CKEDITOR.replace('content', {

                    height: '300px',
                    resize_enabled: true
                });

                $(document).ready(function () {
                    function checkradiobox() {
                        var radio = $('input[name="announcement_type"]:checked').attr("announcementType");
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
              
        </form>
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






<div class="col-md-1"></div>
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

<script src="<%=request.getContextPath()%>/static/chosen2/chosen.jquery.js"></script>
<script>
    $(function() {
        $(".test").chosen({
            disable_search_threshold: 10,
            no_results_text: "Không tìm kiếm được kết quả: ",
            width: "95%"
        });
        $('.test').trigger('chosen:updated');

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
</html>
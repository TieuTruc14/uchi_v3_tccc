<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/pages/layout/header.jsp"/>
<jsp:include page="/WEB-INF/pages/layout/body_top.jsp"/>
<%--Xem thông báo--%>
<style type="text/css">
    #abc_chosen{
        display: none!important;
    }
</style>
<script src="<%=request.getContextPath()%>/static/ckeditor/ckeditor.js"></script>
<spring:url value="/announcement/list" var="backToListUrl"/>
<spring:url value="/announcement/download-from-stp" var="downloadUrl"/>
<div id="menu-map">
    <a href="#menu-toggle" id="menu-toggle"><img src="<%=request.getContextPath()%>/static/image/menu-icon.png"></a>
    <span id="web-map">Thông báo từ Sở Tư pháp</span>
</div>
<div class="col-md-12" style="width: 95%;margin-left: 36px">
    <div class="panel-group" id="accordion">
        <form class="form-horizontal" >

            <input type="hidden" name="aid" value="${updateAnnouncementStp.announcement.aid}">

            <div class="panel panel-default" id="panel1">
                <div class="panel-heading">
                    <h4 class="panel-title">

                            THÔNG TIN THÔNG BÁO

                    </h4>

                </div>
                <div class="panel-body">
                    <div class="form-group">
                        <label class="col-md-2 control-label label-bam-trai">Tiêu đề thông báo</label>
                        <div class="col-md-10">
                            <input type="text" class="form-control" name="title"
                                   value="${updateAnnouncementStp.announcement.title}" disabled>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-2 control-label label-bam-trai">Nội dung thông báo</label>
                        <div class="col-md-10">
                            <textarea class="form-control" name="content" id="content" disabled>${updateAnnouncementStp.announcement.content}</textarea>
                        </div>
                    </div>
                    <c:if test="${updateAnnouncementStp.announcement.attach_file_name.equals('') == true}">
                        <div class="form-group">
                            <label class="col-md-2 control-label label-bam-trai">File đính kèm</label>
                            <div class="col-md-2">
                                <label >
                                    Không có file
                                </label>
                            </div>
                        </div>
                    </c:if>

                    <c:if test="${updateAnnouncementStp.announcement.attach_file_name == null}">
                        <div class="form-group">
                            <label class="col-md-2 control-label label-bam-trai">File đính kèm</label>
                            <div class="col-md-2">
                                <label >
                                Không có file
                                </label>
                            </div>
                        </div>
                    </c:if>
                    <c:if test="${updateAnnouncementStp.announcement.attach_file_name.equals('') == false}">
                        <div class="form-group">
                            <label class="col-md-2 control-label label-bam-trai">File đính kèm</label>
                            <div class="col-md-4">
                                <c:forEach items="${listFileName}" var="item" varStatus="count">
                                        <span>
                                            File ${count.index + 1}: <a href="${downloadUrl}/${updateAnnouncementStp.announcement.aid}/${count.index}">${item}</a>
                                            <%--<a style="margin-left: 20px" onclick="removeFile(${updateAnnouncementStp.announcement.aid},${count.index})" class="removeFile">Gỡ bỏ</a>--%>
                                        </span><br>
                                    <input type="hidden" name="multipartFile">
                                </c:forEach>
                            </div>
                        </div>
                    </c:if>


                    <div class="form-group">
                        <label class="col-md-2 control-label label-bam-trai">Tùy chọn</label>
                        <div class="col-md-2">
                            <c:if test="${updateAnnouncementStp.announcement.importance_type == 2}">
                                <input type="checkbox" name="importance_type" value="2" checked disabled/>
                            </c:if>
                            <c:if test="${updateAnnouncementStp.announcement.importance_type != 2}">
                                <input type="checkbox" name="importance_type" value="2" disabled />
                            </c:if>

                            <input type="hidden" value="1" name="importance_type"/>
                            <span>Tin quan trọng</span>
                        </div>

<%--
                        <div class="col-md-4">
                      <span>
                        <c:if test="${updateAnnouncement.announcement.popup_display_flg == 1}">

                            <input class="truong-check-fixminh" type="checkbox" value="1" name="popup_display_flg" checked/>
                        </c:if>
                        <c:if test="${updateAnnouncement.announcement.popup_display_flg != 1}">
                            <input class="truong-check-fixminh" type="checkbox"  value="1" name="popup_display_flg" />
                        </c:if>
                          <input type="hidden" value="0" name="popup_display_flg"/>

                      </span>
                            <span class="truong-font-chu">Hiển thị trong popup(
                            <input type="text" class="form-control popup-display-day" name = "popup_display_day" value=" ${updateAnnouncement.announcement.popup_display_day}" > ngày)</span>
                        </div>
--%>

                    </div>


                </div>
            </div>
            <div class="row prevent-type-box">
                <div class="form-group">
<%--
                    <div class="col-md-2 col-md-offset-4">
                        <input type="submit" class="form-control luu" name="" value="Lưu">
                    </div>
--%>
                    <%-- <div class="col-md-2">
                         <input type="button" class="form-control luu" data-toggle="modal" data-target="#myModal" name="" value="Xóa">
                     </div>
 --%>

                    <div class="col-md-offset-5 col-md-2">
                        <a href="${backToListUrl}"><input type="button" class="form-control huybo" name=""
                                                          value="Hủy bỏ"></a>
                    </div>

                    <!-- Modal -->
<%--
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
--%>

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

</script>
<jsp:include page="/WEB-INF/pages/layout/footer.jsp"/>
<script src="<%=request.getContextPath()%>/static/chosen2/chosen.jquery.js"></script>



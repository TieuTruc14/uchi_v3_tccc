<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/pages/layout/header.jsp" />
<jsp:include page="/WEB-INF/pages/layout/body_top.jsp" />

<%--
    Thông tin cấu hình 2
--%>
<spring:url value="/system/backup-init-view" var="backupInitUrl" />


<div id="menu-map">
    <a href="#menu-toggle" id="menu-toggle"><img src="<%=request.getContextPath()%>/static/image/menu-icon.png"></a>
    <span id="web-map">Cấu hình tham số sao lưu</span>
</div>
<div class="truong-form-chinhbtt">
    <div id="uchi-status" >
        <c:if test="${cfBackup.success == true}">
            <div class="status-success"><img class="status-img" src="<%=request.getContextPath()%>/static/image/success.png">${cfBackup.action_status}</div>
        </c:if>
        <c:if test="${cfBackup.success == false}">
            <div class="status-error"><img class="status-img" src="<%=request.getContextPath()%>/static/image/error.png">${cfBackup.action_status}</div>
        </c:if>
    </div>
    <div class="panel-group" id="accordion">
        <form class="form-horizontal" action="${addUrl}" method="post">
            <div class="panel panel-default" id="panel1">
                <div class="panel-heading">
                    <h4 class="panel-title">

                            THÔNG TIN CẤU HÌNH LƯU TRỮ DỮ LIỆU SAO LƯU

                    </h4>

                </div>
                <div class="panel-body">
                    <div class="row truong-inline-field">
                        <table class="table " >
                            <tr class="border-table">
                                <th class="border-table col-md-3 ann-title" >Thư mục lưu trữ dữ liệu sao lưu</th>
                                <td class="border-table"></td>
                            </tr>
                            <tr class="border-table">
                                <th class="border-table col-md-3 ann-title">Lịch sao lưu</th>
                                <td class="border-table"></td>
                            </tr>
                            <tr class="border-table">
                                <th class="border-table col-md-3 ann-title">Thời gian sao lưu</th>
                                <td class="border-table"></td>
                            </tr>
                            <tr class="border-table">
                                <th class="border-table col-md-3 ann-title">Danh sách gửi mail</th>
                                <td class="border-table"></td>
                            </tr>
                        </table>
                    </div>
                </div>
                <div class="truong-panel-body">
                    <div class="row truong-inline-field">
                        <div class="form-group">
                            <div class="col-md-4">
                                <div class="row">
                                    <label class="col-md-5 control-label label-bam-trai">Trạng thái sao lưu</label>
                                    <label class="control-label  truong-text-radioyl col-md-6"><span class="truong-text-colorred ">Đã gỡ bỏ sao lưu dữ liệu</span></label>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>



            </div>
        </form>
        <div class="truong-prevent-btb">
            <div class="truong-rs truong-rs-cssbt1-44">
                <div class="col-md-12 ">
                    <a href="${backupInitUrl}" class="truong-small-linkbt"><input type="submit" class="form-control luu" name="" value="Cấu hình sao lưu"></a>
                </div>

            </div>
        </div>

    </div>


</div>



<script>
    $(window).on('load resize',function(){
        var win = $(this);
        if (win.width() < 1523 && win.width() > 1204){

            $('.truong-rs').addClass('truong-rs-cssbt1-42');
            $('.truong-rs').removeClass('truong-rs-cssbt1-44 truong-rs-cssbt1-40 truong-rs-cssbt1-38 col-md-12');
        }
        if(win.width()>1523){

            $('.truong-rs').addClass('truong-rs-cssbt1-44');
            $('.truong-rs').removeClass('truong-rs-cssbt1-42 truong-rs-cssbt1-40 truong-rs-cssbt138 col-md-12');
        }
        if(win.width()<1204 && win.width()>1018){

            $('.truong-rs').addClass('truong-rs-cssbt1-40');
            $('.truong-rs').removeClass('truong-rs-cssbt1-44 truong-rs-cssbt1-42 truong-rs-cssbt1-38 col-md-12');
        }
        if(win.width()<1018 && win.width()>863){

            $('.truong-rs').addClass('truong-rs-cssbt1-38');
            $('.truong-rs').removeClass('truong-rs-cssbt1-44 truong-rs-cssbt1-42 truong-rs-cssbt1-40 col-md-12');
        }
        if(win.width()<863){

            $('.truong-rs').addClass('col-md-12');
            $('.truong-rs').removeClass('truong-rs-cssbt1-44 truong-rs-cssbt1-42 truong-rs-cssbt1-40 truong-rs-cssbt1-38');
        }
    });
</script>


<script>
    $(window).on('resize',function(){
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




<jsp:include page="/WEB-INF/pages/layout/footer.jsp" />

<script>
    $(document).ready(function () {
        var parentItem = $("#quan-tri-he-thong");
        $(parentItem).click();
        $("#ds-backup").addClass("child-menu");
    });
</script>

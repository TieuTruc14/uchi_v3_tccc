<%@ page import="com.vn.osp.common.global.Constants" %>
<%@ page import="com.vn.osp.common.util.ValidationPool" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/pages/layout/header.jsp"/>

<jsp:include page="/WEB-INF/pages/layout/body_top.jsp"/>
<%--
    Phân quyền cán bộ STP
--%>
<spring:url value="/system/user-permission" var="permissionUrl"/>
<spring:url value="/system/user-list" var="backUrl"/>

<div id="menu-map">
    <a href="#menu-toggle" id="menu-toggle"><img src="<%=request.getContextPath()%>/static/image/menu-icon.png"></a>
    <span id="web-map">Phân quyền cán bộ</span>
</div>
<div class="truong-form-chinhpq">
    <div class="panel-group canh-le-trai" id="accordion">
        <form class="form-horizontal" action="${permissionUrl}" method="post">
            <input type="hidden" name="userId" value="${userPermissionForm.user.userId}">
            <div class="panel-body">

                <div class="form-group">
                    <div class="col-md-7">
                        <label class="col-md-3 control-label  label-bam-trai">Họ tên</label>
                        <div class="col-md-6 control-label label-bam-trai">
                            <strong>${userPermissionForm.user.family_name} ${userPermissionForm.user.first_name}</strong>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-md-7">
                        <label class="col-md-3 control-label  label-bam-trai">Tài khoản đăng nhập</label>
                        <div class="col-md-6 control-label label-bam-trai">
                            <strong>${userPermissionForm.user.account}</strong>
                        </div>
                    </div>

                </div>

                <div class="form-group">
                    <div class="col-md-7">
                        <label class="col-md-3 control-label  label-bam-trai">Chức vụ</label>
                        <div class="col-md-6 control-label label-bam-trai">
                            <strong>${userPermissionForm.user.role}</strong>
                        </div>
                    </div>

                </div>


                <div class="form-group">
                    <div class="col-md-7">
                        <label class="col-md-3 control-label  label-bam-trai">Trạng thái hoạt động</label>
                        <div class="col-md-6 control-label label-bam-trai">
                            <c:if test="${userPermissionForm.user.active_flg == 1}">
                                <strong>Đang hoạt động</strong>
                            </c:if>
                            <c:if test="${userPermissionForm.user.active_flg == 0}">
                                <strong>Ngừng hoạt động</strong>
                            </c:if>
                        </div>
                    </div>

                </div>

                <div class="form-group">
                    <div class="col-md-7">
                        <label class="col-md-3 control-label  label-bam-trai">Phân quyền</label>
                        <div class="col-md-6 control-label label-bam-trai truong-font-chu">
                            <input type="checkbox" class="selectAll truong-check-fix">
                            Tất cả nhóm quyền

                            <ul style="list-style:none">
                                <c:forEach items="${userPermissionForm.groupRoles}" var="item">
                                    <c:if test="${item.activeByPreventUser == 1}">
                                        <li><input class="truong-check-fix cb01" type="checkbox" name="cb01" value="${item.groupRoleId}" checked>&nbsp&nbsp&nbsp ${item.grouprolename}</li>
                                    </c:if>
                                    <c:if test="${item.activeByPreventUser != 1}">
                                        <li><input class="truong-check-fix cb01" type="checkbox" name="cb01" value="${item.groupRoleId}">&nbsp&nbsp&nbsp ${item.grouprolename}</li>
                                    </c:if>
                                </c:forEach>
                            </ul>
                        </div>
                    </div>

                </div>
            </div>
            <div class="truong-form-chinhpq">

                <div class="col-md-9">
                    <div class="row">
                        <div class=" col-md-3">

                        </div>
                        <%
                            if(ValidationPool.checkRoleDetail(request, "04", Constants.AUTHORITY_SUA)||ValidationPool.checkRoleDetail(request, "04", Constants.AUTHORITY_THEM)){
                        %>
                        <div class="truong-button-fixwidthleft">
                            <input type="submit" class="form-control luu" name="" value="Lưu">
                        </div>
                        <%
                            }
                        %>
                        <div class="truong-button-fixwidthleft">
                            <a href="${backUrl}" class="truong-small-linkbt"><input type="button" class=" form-control huybo" value="Hủy bỏ"></a>
                        </div>
                    </div>

                </div>
            </div>
            <%--
            <div class="prevent-type-box">
                <div class="form-group">
                    <div class="col-md-3 col-md-offset-2">
                    <div class="row">
                    <div class="col-md-4">
                        <input type="submit" class="form-control luu" name="" value="Lưu">
                    </div>
                    <div class="col-md-4">
                        <input type="button" class="form-control huybo" name="" value="Hủy bỏ">
                    </div>
                    </div>
                    </div>
                </div>
            </div>
            --%>
        </form>
    </div>
</div>

<script>
    $(window).on('load resize',function(){
        var win = $(this);
        if (win.width() < 1200){
            $('.truong-rs-bt2os').removeClass('col-md-2');
            $('.truong-rs-bt2os').addClass('col-md-3 ');
            $('.truong-rs-bt2').removeClass('col-md-2');
            $('.truong-rs-bt2').addClass('col-md-3');
        }else {
            $('.truong-rs-bt2os').removeClass('col-md-3 ');
            $('.truong-rs-bt2os').addClass('col-md-2 ');
            $('.truong-rs-bt2').removeClass('col-md-3');
            $('.truong-rs-bt2').addClass('col-md-2');
        }
    });
</script>


<jsp:include page="/WEB-INF/pages/layout/footer.jsp"/>
<script>
    /*$('.selectAll').click(function(){
     $('input:checkbox').not(this).prop('checked', this.checked);
     });*/

    function checkedValue(element, bool) {
        if (bool) {
            return element.each(function() {
                $(this).prop('checked', true);
            });
        } else {
            return element.each(function() {
                $(this).prop('checked', false);
            });
        }
    }
    $('.selectAll').change(function() {
        var $self = $(this);

        if ($self.is(':checked')) {
            checkedValue($('.cb01'), true);
        } else {
            checkedValue($('.cb01'), false);
        }
    });

    // Checked parent checkbox when all child checkbox checked
    $('.cb01').change(function() {
        var flag = true;

        if (!$('.cb01').is(':checked')) {
            console.log(!$('.cb01').is(':checked'));
            $('.selectAll').prop('checked', false);
        }

        $('.cb01').each(function() {
            var $self = $(this);
            if (!$self.is(':checked')) {
                flag = false;
                return;
            }
        });

        $('.selectAll').prop('checked', flag);
    });
    $(window).on('load',function () {
        var flag = true;

        if (!$('.cb01').is(':checked')) {
            console.log(!$('.cb01').is(':checked'));
            $('.selectAll').prop('checked', false);
        }

        $('.cb01').each(function() {
            var $self = $(this);
            if (!$self.is(':checked')) {
                flag = false;
                return;
            }
        });
        $('.selectAll').prop('checked', flag);
    });
</script>
<script>
    $(document).ready(function () {
        var parentItem = $("#quan-tri-he-thong");
        $(parentItem).click();
        $("#ds-can-bo-stp").addClass("child-menu");
    });
</script>



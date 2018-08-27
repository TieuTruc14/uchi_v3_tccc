<%@ page import="com.vn.osp.common.util.SystemProperties" %>
<%@ page import="com.vn.osp.common.util.ValidationPool" %>
<%@ page import="com.vn.osp.common.global.Constants" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/pages/layout/header.jsp" />
<jsp:include page="/WEB-INF/pages/layout/body_top.jsp" />
<script>
    var url='<%=SystemProperties.getProperty("url_config_server_api")%>';
</script>
<%--
    Thêm mói thông tin cán bộ
--%>
<spring:url value="/system/add-user" var="addUrl" />
<spring:url value="/system/user-list" var="backUrl" />

<div id="menu-map">
    <a href="#menu-toggle" id="menu-toggle"><img src="<%=request.getContextPath()%>/static/image/menu-icon.png"></a>
    <span id="web-map">Thêm mới thông tin cán bộ</span>
</div>
<div class="truong-form-chinhbtt">
    <div id="uchi-status">
        <c:if test="${user.success == true}">
            <div class="status-success"><img class="status-img" src="<%=request.getContextPath()%>/static/image/success.png">Tạo mới thông tin cán bộ thành công</div>
        </c:if>
        <c:if test="${user.success == false}">
            <div class="status-error"><img class="status-img" src="<%=request.getContextPath()%>/static/image/error.png">Tạo mới thông tin cán bộ không thành công</div>
        </c:if>
    </div>
    <div class="panel-group" id="accordion">
        <form class="form-horizontal" action="${addUrl}" method="post">
            <div class="panel panel-default" id="panel1">
                <div class="panel-heading">
                    <h4 class="panel-title">

                            THÔNG TIN CÁN BỘ

                    </h4>
                </div>
                <div class="panel-body">
                    <div class="row truong-inline-field">
                        <!--<div class="form-group">
                            <label class="col-md-3 control-label required label-bam-trai">Tên tổ chức</label>
                            <div class="col-md-9">
                                <select name="office_id" class=" form-control" onchange="changeOffice(this)" id="officeAuthor">
                                    <c:forEach items="${user.notaryOffices}" var="item">
                                        <option value="${item.noid}" authorCode="${item.authentication_id}" ${item.noid==user.office_id?"selected":""}>${item.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>-->
                        <div class="form-group">
                            <label class="col-md-3 control-label required label-bam-trai">Họ đệm</label>
                            <div class="col-md-3">
                                <input type="text" class="form-control ${user.family_name_ != null? "error_input":""}" name="family_name" value="${user.family_name}">
                                <div class="error_tooltip">${user.family_name_}</div>
                            </div>
                            <div class="col-md-2"></div>
                            <label class="col-md-1 control-label required label-bam-phai text-nowrap">Tên</label>
                            <div class="col-md-3">
                                <input type="text" class="form-control ${user.first_name_ != null? "error_input":""}" name="first_name" value="${user.first_name}">
                                <div class="error_tooltip">${user.first_name_}</div>
                            </div>
                        </div>
                    </div>
                    <div class="row truong-inline-field">
                        <div class="form-group">
                            <label class="col-md-3 control-label required label-bam-trai">Tài khoản đăng nhập</label>
                            <div class="col-md-3">
                                <input type="text" class="form-control ${user.account_ != null? "error_input":""}" name="account" value="${user.account}">
                                <div class="error_tooltip">${user.account_}</div>
                            </div>
                            <div class="col-md-6 control-label truong-bo-pl" style="text-align: left!important">
                                <i>
                                    (Tài khoản đăng nhập chỉ được dùng chữ cái, số, dấu gạch dưới và dấu gạch ngang)
                                </i>
                            </div>
                        </div>
                    </div>
                    <div class="row truong-inline-field">
                        <div class="form-group">
                            <label class="col-md-3 control-label required label-bam-trai">Mật khẩu</label>
                            <div class="col-md-3">
                                <input type="password" class="form-control ${user.password_ != null? "error_input":""}" name="password" value="${user.password}">
                                <div class="error_tooltip">${user.password_}</div>
                            </div>
                        </div>
                    </div>
                    <div class="row truong-inline-field">
                        <div class="form-group">
                            <label class="col-md-3 control-label required label-bam-trai">Xác nhận mật khẩu</label>
                            <div class="col-md-3">
                                <input type="password" class="form-control ${user.rePassword_ != null? "error_input":""}" name="rePassword">
                                <div class="error_tooltip">${user.rePassword_}</div>
                            </div>
                        </div>
                    </div>
                    <div class="row truong-inline-field">
                        <div class="form-group">
                            <label class="col-md-3 control-label  label-bam-trai">Ngày sinh</label>
                            <div class="col-md-3">
                                <input type="text" class="form-control ${user.birthday_ != null? "error_input":""}" id="birthday" name="birthday" value="${user.birthday}">
                                <div class="error_tooltip">${user.birthday_}</div>
                            </div>
                            <div class="col-md-1"></div>
                            <label class="col-md-2 control-label  label-bam-phai">Giới tính</label>
                            <div class="col-md-3    ">
                                <select class=" form-control" name="sex">
                                    <option value="1" ${user.sex == 1?"selected":""}>Nam</option>
                                    <option value="0" ${user.sex == 0?"selected":""}>Nữ</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="row truong-inline-field">
                        <div class="form-group">
                            <label class="col-md-3 control-label  label-bam-trai">Địa chỉ</label>
                            <div class="col-md-9">
                                <input type="text" class="form-control ${user.address_ != null? "error_input":""}" name="address" value="${user.address}">
                                <div class="error_tooltip">${user.address_}</div>
                            </div>
                        </div>
                    </div>
                    <div class="row truong-inline-field">
                        <div class="form-group">
                            <label class="col-md-3 control-label  label-bam-trai">Điện thoại cố định</label>
                            <div class="col-md-3">
                                <input type="text" class="form-control ${user.telephone_ != null? "error_input":""}" name="telephone" value="${user.telephone}">
                                <div class="error_tooltip">${user.telephone_}</div>
                            </div>
                            <div class="col-md-1"></div>
                            <label class="col-md-2 control-label  label-bam-phai text-nowrap">Điện thoại di động</label>
                            <div class="col-md-3    ">
                                <input type="text" class="form-control ${user.mobile_ != null? "error_input":""}" name="mobile" value="${user.mobile}">
                                <div class="error_tooltip">${user.mobile_}</div>
                            </div>
                        </div>
                    </div>
                    <div class="row truong-inline-field">
                        <div class="form-group">
                            <label class="col-md-3 control-label  label-bam-trai required">Email</label>
                            <div class="col-md-3">
                                <input type="text" class="form-control ${user.email_ != null? "error_input":""}" name="email" value="${user.email}">
                                <div class="error_tooltip">${user.email_}</div>
                            </div>
                            <div class="col-md-1"></div>
                            <label class="col-md-2 control-label  label-bam-phai text-nowrap">Chức vụ</label>
                            <div class="col-md-3">
                                <input type="hidden" id="userRole" value="${user.role}">
                                <select class="form-control" name="role" id="roleId"></select>
                                <div class="error_tooltip">${user.role_}</div>
                            </div>
                        </div>
                    </div>
                    <div class="row truong-inline-field">
                        <div class="form-group">
                            <label class="col-md-3 control-label label-bam-trai">Công chứng viên hợp danh</label>
                            <div class="col-md-9">
                                <input type="checkbox" name="active_ccv" value="1">
                            </div>
                        </div>
                    </div>
                    <div class="row truong-inline-field">
                        <div class="form-group">
                            <label class="col-md-3 control-label label-bam-trai">Trạng thái hoạt động</label>
                            <div class="col-md-9">
                                <label class="radio-inline prevent-type">
                                    <input type="radio" name="active_flg" value="1" ${user.active_flg == 1? "checked":""}>Đang hoạt động
                                </label>
                                <label class="radio-inline ">
                                    <input type="radio" name="active_flg" value="0" ${user.active_flg == 0? "checked":""}>Ngừng hoạt động
                                </label>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="truong-prevent-btb">
                <div class="truong-can-button3">
                    <%
                        if(ValidationPool.checkRoleDetail(request,"02", Constants.AUTHORITY_THEM)){
                    %>
                    <div class="col-md-2 col-md-offset-4 truong-rs-bt2os">
                        <input type="submit" class="form-control luu" name="" value="Lưu">
                    </div>
                    <%
                        }
                    %>
                    <div class="col-md-2 truong-rs-bt2">
                        <a href="${backUrl}"><input type="button" class="form-control huybo" name="" value="Hủy bỏ"></a>
                    </div>
                </div>
            </div>


        </form>
    </div>


</div>

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
<script>

    $(function () {
        $('#birthday').datepicker({
            format: "dd/mm/yyyy",
            forceParse : false,
            language: 'vi'
        }).on('changeDate', function (ev) {
            $(this).datepicker('hide');
        });
    });
    function changeOffice(e){
        var element = $(e).find('option:selected');
        var authorCode = element.attr("authorCode");
        $('#authorValue').text(authorCode);
        $('#authorId').val(authorCode);
    }
    $(document).ready(function () {
        var e = $('#officeAuthor');
        changeOffice(e);
    });
</script>


<jsp:include page="/WEB-INF/pages/layout/footer.jsp" />

<script>
    $(document).ready(function () {
        var userRole= $("#userRole").val();
        var parentItem = $("#quan-tri-he-thong");
        $(parentItem).click();
        $("#ds-can-bo-stp").addClass("child-menu");

        $.getJSON(url+"/users/roles",function (data) {
            if(data.length){
                $('#roleId').text("");
                for (var i = 0; i < data.length; i++) {
                    if(userRole == data[i].code){
                        $('#roleId').append('<option selected id="'+data[i].code+'" value="' + data[i].code + '">' + data[i].name + '</option>');
                    }else{
                        $('#roleId').append('<option id="'+data[i].code+'" value="' + data[i].code + '">' + data[i].name + '</option>');
                    }
                }
            }
        });
    });
</script>

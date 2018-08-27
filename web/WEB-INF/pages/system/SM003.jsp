<%@ page import="com.vn.osp.common.util.SystemProperties" %>
<%@ page import="com.vn.osp.common.util.ValidationPool" %>
<%@ page import="com.vn.osp.common.global.Constants" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/pages/layout/header.jsp" />
<jsp:include page="/WEB-INF/pages/layout/body_top.jsp" />
<%--
    Chỉnh sửa thông tin cán bộ
--%>
<spring:url value="/system/update-user" var="updateUrl" />
<spring:url value="/system/remove-user" var="removeUrl" />
<spring:url value="/system/user-list" var="backUrl" />
<div id="menu-map">
    <a href="#menu-toggle" id="menu-toggle"><img src="<%=request.getContextPath()%>/static/image/menu-icon.png"></a>
    <span id="web-map">Chỉnh sửa thông tin cán bộ</span>
</div>


<div class="truong-form-chinhbtt">
    <div id="uchi-status">

        <c:if test="${user.success == false}">
            <div class="status-error"><img class="status-img" src="<%=request.getContextPath()%>/static/image/error.png">Sửa thông tin cán bộ không thành công</div>
        </c:if>
    </div>
    <div class="panel-group" id="accordion">
        <form class="form-horizontal" action="${updateUrl}" method="post">
            <input type="hidden" name="userId" value="${user.userId}">
            <input type="hidden" name="account" value="${user.account}">
            <div class="panel panel-default" id="panel1">
                <div class="panel-heading">
                    <h4 class="panel-title">

                            THÔNG TIN CÁN BỘ

                    </h4>

                </div>
                <div class="panel-body">
                    <div class="form-group truong-margin-formgroup">
                        <label class="col-md-3 control-label required label-bam-trai">Họ đệm</label>
                        <div class="col-md-3">
                            <input type="text" class="form-control ${user.family_name_ != null? "error_input":""}" name="family_name" value="${user.family_name}">
                            <div class="error_tooltip">${user.family_name_}</div>
                        </div>
                        <div class="col-md-2"></div>
                        <label class="col-md-1 control-label required label-bam-phai">Tên</label>
                        <div class="col-md-3">
                            <input type="text" class="form-control ${user.first_name_ != null? "error_input":""}" name="first_name" value="${user.first_name}">
                            <div class="error_tooltip">${user.first_name_}</div>
                        </div>
                    </div>


                    <div class="form-group truong-margin-formgroup">
                        <label class="col-md-3 control-label required label-bam-trai">Tài khoản đăng nhập</label>
                        <div class="col-md-3">
                            <p class="truong-can-the-p">${user.account}</p>
                        </div>
                        <div class="error_tooltip"></div>
                    </div>
                    <%--<div class="change-password">
                    <div class="form-group">
                        <label class="col-md-3 control-label label-bam-trai ">Mật khẩu</label>
                        <div class="col-md-3">
                            <a onclick="openPassword()"><p class="truong-can-the-p">Đổi mật khẩu</p></a>
                        </div>
                    </div>
                    </div>
                    <div class="hidden-password" style="display: none">--%>
                    <div class="form-group truong-margin-formgroup">
                        <label class="col-md-3 control-label label-bam-trai">Mật khẩu</label>
                        <div class="col-md-3">
                            <input type="password" class="form-control ${user.password_ != null? "error_input":""}" name="newPassword">
                            <div class="error_tooltip">${user.password_}</div>
                        </div>
                    </div>


                    <div class="form-group truong-margin-formgroup">
                        <label class="col-md-3 control-label label-bam-trai">Xác nhận mật khẩu</label>
                        <div class="col-md-3">
                            <input type="password" class="form-control ${user.rePassword_ != null? "error_input":""}" name="reNewPassword">
                            <div class="error_tooltip">${user.rePassword_}</div>
                        </div>
                    </div>
                    <%--</div>--%>

                    <div class="form-group truong-margin-formgroup">
                        <label class="col-md-3 control-label  label-bam-trai">Ngày sinh</label>
                        <div class="col-md-3">
                            <input type="text" class="form-control" id="birthday" name="birthday" value="${user.birthday}">
                            <div class="error_tooltip">${user.birthday_}</div>
                        </div>
                        <div class="col-md-1"></div>
                        <label class="col-md-2 control-label  label-bam-phai">Giới tính</label>
                        <div class="col-md-3    ">
                            <select class=" form-control" name="sex">
                                <option value="1" ${user.sex == 1?"selected":""}>Nam</option>
                                <option value="0" ${user.sex == 0?"selected":""}>Nữ</option>
                            </select>
                            <div class="error_tooltip"></div>
                        </div>
                    </div>


                    <div class="form-group truong-margin-formgroup">
                        <label class="col-md-3 control-label  label-bam-trai">Địa chỉ</label>
                        <div class="col-md-9">
                            <input type="text" class="form-control ${user.address_ != null? "error_input":""}" name="address" value="${user.address}">
                            <div class="error_tooltip">${user.address_}</div>
                        </div>
                    </div>


                    <div class="form-group truong-margin-formgroup">
                        <label class="col-md-3 control-label required label-bam-trai">Email</label>
                        <div class="col-md-3">
                            <input type="text" class="form-control ${user.email_ != null? "error_input":""}" name="email" value="${user.email}">
                            <div class="error_tooltip">${user.email_}</div>
                        </div>
                    </div>


                    <div class="form-group truong-margin-formgroup">
                        <label class="col-md-3 control-label  label-bam-trai">Điện thoại cố định</label>
                        <div class="col-md-3">
                            <input type="text" class="form-control" name="telephone" value="${user.telephone}">
                            <div class="error_tooltip">${user.telephone_}</div>
                        </div>
                        <div class="col-md-1"></div>
                        <label class="col-md-2 control-label  label-bam-phai">Điện thoại di động</label>
                        <div class="col-md-3    ">
                            <input type="text" class="form-control" name="mobile" value="${user.mobile}">
                            <div class="error_tooltip">${user.mobile_}</div>
                        </div>
                    </div>


                    <div class="form-group truong-margin-formgroup">
                        <label class="col-md-3 control-label label-bam-trai">Chức vụ</label>
                        <div class="col-md-3">
                            <input type="hidden" class="form-control" id="roleValue" value="${user.role}" >
                            <select class="form-control" name="role" id="roleId"></select>
                            <div class="error_tooltip"></div>
                        </div>
                    </div>
                    <div class="row truong-inline-field">
                        <div class="form-group">
                            <label class="col-md-3 control-label label-bam-trai">Công chứng viên hợp danh</label>
                            <div class="col-md-9">
                                <input type="checkbox" name="active_ccv" value="1" ${user.active_ccv == 1? "checked":""}>
                            </div>
                        </div>
                    </div>
                    <div class="form-group truong-margin-formgroup">
                        <label class="col-md-3 control-label label-bam-trai">Trạng thái hoạt động</label>
                        <div class="col-md-9">
                            <label class="radio-inline prevent-type">
                                <input type="radio" name="active_flg" value="1" ${user.active_flg == 1?"checked":""}>Đang hoạt động
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="active_flg" value="0" ${user.active_flg == 0?"checked":""}>Ngừng hoạt động
                            </label>
                        </div>
                    </div>

                </div>
            </div>

            <div class="truong-prevent-btb">
                <div class="truong-can-button3">
                    <%
                        if(ValidationPool.checkRoleDetail(request,"02", Constants.AUTHORITY_SUA)){
                    %>
                    <div class="col-md-2 col-md-offset-3 truong-rs-bt3os">
                        <input type="submit" class="form-control luu" name="" value="Lưu">
                    </div>
                    <%
                        }
                        if(ValidationPool.checkRoleDetail(request,"02", Constants.AUTHORITY_XOA)){
                    %>
                    <div class="col-md-2 truong-rs-bt3">
                        <input type="button" class="form-control luu" data-toggle="modal" data-target="#xoa"
                               name="" value="Xóa">
                    </div>
                    <%
                        }
                    %>
                    <div class="col-md-2 truong-rs-bt3">
                        <a href="${backUrl}" class="truong-small-linkbt"><input type="button" class="form-control huybo" name="" value="Hủy bỏ"></a>
                    </div>
                </div>
            </div>


        </form>
    </div>


</div>


<!-- Modal -->
<div class="modal fade" id="xoa" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="panel panel-default">

                <div class="panel-heading" style="background-color: #2e9ad0 ">
                    <h5 class="panel-title">
                        Xóa thông tin cán bộ
                        <button type="button" class="close" data-dismiss="modal" style="margin-bottom: 5px"><img
                                src="<%=request.getContextPath()%>/static/image/close.png" class="control-label"></button>
                    </h5>
                </div>
                <div class="panel-body">
                    <div class="form-group" style="padding-bottom: 7%;">
                        <label class="col-md-6 control-label label-bam-trai">Bạn có thực sự muốn xóa dữ liệu?</label>
                    </div>
                </div>

            </div>
            <div class="row" style="margin-bottom: 2%;">
                <div class="col-md-2 col-md-offset-4">
                    <a href="${removeUrl}/${user.userId}"><input type="button" class="form-control luu" name="" value="Xóa"></a>
                </div>
                <div class="col-md-2 ">
                    <input type="button" class="form-control huybo" data-dismiss="modal" name="" value="Hủy bỏ">
                </div>

            </div>
        </div>
    </div>
</div>

<%--End Modal--%>
<script>
    $(window).on('resize',function(){
        var win = $(this);
        if(win.width() < 1300){
            $('.truong-rs-bt3os').removeClass('col-md-2 col-md-offset-3');
            $('.truong-rs-bt3os').addClass('col-md-4');
            $('.truong-rs-bt3').removeClass('col-md-2');
            $('.truong-rs-bt3').addClass('col-md-4');
        }else {
            $('.truong-rs-bt3os').removeClass('col-md-4');
            $('.truong-rs-bt3os').addClass('col-md-2 col-md-offset-3');
            $('.truong-rs-bt3').removeClass('col-md-4');
            $('.truong-rs-bt3').addClass('col-md-2');

        }
    });
</script>
<%--<script>
    function openPassword() {
        $('.change-password').css("display","none");
        $('.hidden-password').css("display","block");
    }
</script>--%>
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
</script>


<jsp:include page="/WEB-INF/pages/layout/footer.jsp" />
<script>
    $(document).ready(function () {
        var url='<%=SystemProperties.getProperty("url_config_server_api")%>';
        var parentItem = $("#quan-tri-he-thong");
        $(parentItem).click();
        $("#ds-can-bo-stp").addClass("child-menu");

        <%--var role=("0" + ${user.role}).slice(-2);--%>
        var role=$("#roleValue").val();
        $.getJSON(url+"/users/roles",function (data) {
            if(data.length){
                $('#roleId').text("");
                for (var i = 0; i < data.length; i++) {
                    if(role==data[i].code){
                        $('#roleId').append('<option value="' + data[i].code + '"selected>' + data[i].name + '</option>');
                    }else{
                        $('#roleId').append('<option value="' + data[i].code + '">' + data[i].name + '</option>');
                    }

                }

            }
        });
    });

</script>


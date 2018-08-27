<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ page import="com.vn.osp.controller.HomeController" %>
<%@ page import="com.vn.osp.common.util.SystemProperties" %>
<%@ page import="com.vn.osp.context.CommonContext" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/pages/layout/header.jsp"/>
<jsp:include page="/WEB-INF/pages/layout/body_top.jsp"/>
<spring:url value="/update-admin-info" var="updateUrl"/>

<spring:url value="/home" var="backUrl"/>
<spring:url value="/update-user-home" var="updateUrl"/>
<div id="menu-map">
    <a href="#menu-toggle" id="menu-toggle"><img src="<%=request.getContextPath()%>/static/image/menu-icon.png"></a>
    <span id="web-map">Thông tin cán bộ</span>
</div>
<div class="truong-form-chinhbtt">
    <div id="uchi-status">
        <c:if test="${user.success == true}">
            <div class="status-success"><img class="status-img"
                                             src="<%=request.getContextPath()%>/static/image/success.png">Sửa thông tin
                cán bộ Tổ chức công chứng thành công
            </div>
        </c:if>
        <c:if test="${user.success == false}">
            <div class="status-error"><img class="status-img"
                                           src="<%=request.getContextPath()%>/static/image/error.png">Sửa thông tin cán
                bộ Tổ chức công chứng không thành công
            </div>
        </c:if>
    </div>
    <div class="panel-group" id="accordion">
        <form class="form-horizontal" id="updateUser" action="${updateUrl}" method="post">
            <input type="hidden" name="userId" id="userId" value="${user.userId}">
            <input type="hidden" name="account" id="account" value="${user.account}">
            <input type="hidden" name="image" id="image" value="">
            <input type="hidden" name="filename" id="filename" value="">
            <input type="hidden" name="isAdvanceSearch" id="isAdvanceSearch" value="${user.isAdvanceSearch}">
            <div class="panel panel-default" id="panel1">
                <div class="panel-heading">
                    <h4 class="panel-title">
                        <a data-toggle="collapse">
                            THÔNG TIN CÁN BỘ
                        </a>
                    </h4>

                </div>
                <div class="panel-body">
                    <div class="col-md-6">
                        <div class="form-group ">
                            <label class="col-md-6 control-label required label-bam-trai">Họ đệm</label>
                            <div class="col-md-6">
                                <input type="text" class="form-control ${user.family_name_ != null? "error_input":""}"
                                       name="family_name" value="${user.family_name}">
                                <div class="error_tooltip">${user.family_name_}</div>
                            </div>
                            <div class="col-md-2"></div>
                        </div>
                        <div class="form-group ">
                            <label class="col-md-6 control-label label-bam-trai required">Tên</label>
                            <div class="col-md-6">
                                <input type="text"
                                       class="form-control ${user.first_name_ != null? "error_input":""}" ${user.first_name_ != null? "autofocus":""}
                                       name="first_name" value="${user.first_name}">
                                <div class="error_tooltip">${user.first_name_}</div>
                            </div>
                        </div>

                        <div class="form-group ">
                            <label class="col-md-6 control-label required label-bam-trai">Tài khoản đăng nhập</label>
                            <div class="col-md-6">
                                <p class="truong-can-the-p">${user.account}</p>
                            </div>
                            <div class="error_tooltip"></div>
                        </div>
                        <div id="basic-frm" style="display: block" class="truong-padding-delete">
                            <div class="form-group ">
                                <label class="col-md-6 control-label label-bam-trai ">Mật khẩu</label>
                                <div class="col-md-6">
                                    <a onclick="openChangePassword()"><p class="truong-can-the-p">Đổi mật khẩu</p></a>
                                </div>
                            </div>
                        </div>
                        <div id="advance-frm" style="display: none" class="truong-padding-delete">
                            <div class="form-group">
                                <label class="col-md-6 control-label label-bam-trai ">Mật khẩu</label>
                                <div class="col-md-6">
                                    <a onclick="closeChangePassword()"><p class="truong-can-the-p">Hủy bỏ đổi mật
                                        khẩu</p>
                                    </a>
                                </div>
                            </div>
                            <div class="form-group ">
                                <label class="col-md-6 control-label label-bam-trai required">Mật khẩu cũ</label>
                                <div class="col-md-6">
                                    <input type="password"
                                           class="form-control ${user.oldPassword_!=null?"error_input":""}" ${user.oldPassword_ != null? "autofocus":""}
                                           name="oldPassword">
                                    <div class="error_tooltip">${user.oldPassword_}</div>
                                </div>
                            </div>
                            <div class="form-group ">
                                <label class="col-md-6 control-label label-bam-trai required">Mật khẩu mới</label>
                                <div class="col-md-6">
                                    <input type="password"
                                           class="form-control ${user.password_ != null? "error_input":""}"
                                           name="newPassword" autofocus ${user.newPassword_ != null? "autofocus":""} >
                                    <div class="error_tooltip">${user.password_}</div>
                                </div>
                            </div>
                            <div class="form-group ">
                                <label class="col-md-6 control-label label-bam-trai required">Xác nhận mật khẩu
                                    mới</label>
                                <div class="col-md-6">
                                    <input type="password"
                                           class="form-control ${user.rePassword_ != null? "error_input":""}" ${user.rePassword_ != null? "autofocus":""}
                                           name="reNewPassword">
                                    <div class="error_tooltip">${user.reNewPassword_}</div>
                                </div>
                            </div>
                        </div>
                        <div class="form-group ">
                            <label class="col-md-6 control-label  label-bam-trai">Ngày sinh</label>
                            <div class="col-md-6">
                                <input type="text" class="form-control" id="birthday" name="birthday"
                                       value="${user.birthday}">
                                <div class="error_tooltip">${user.birthday_}</div>
                            </div>
                            <div class="col-md-1"></div>
                        </div>
                        <div class="form-group ">
                            <label class="col-md-6 control-label label-bam-trai required">Giới tính</label>
                            <div class="col-md-6">
                                <select class=" form-control" name="sex">
                                    <option value="1" ${user.sex == 1?"selected":""}>Nam</option>
                                    <option value="0" ${user.sex == 0?"selected":""}>Nữ</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group ">
                            <label class="col-md-6 control-label  label-bam-trai">Địa chỉ</label>
                            <div class="col-md-6">
                                <input type="text" class="form-control"
                                       name="address"  ${user.address_ != null? "autofocus":""} value="${user.address}">
                                <div class="error_tooltip">${user.address_}</div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="col-md-3 text-center hidden-xs hidden-sm"></div>
                        <div class="col-md-9 text-center hidden-xs hidden-sm">
                            <div style="margin-bottom:10px;width: 300px;height: 300px;top: -50px;border: solid #d4d4d4 1px">
                                <div class="profile_pic" id="profile_pic">
                                    <img style="margin-left: 25px;margin-bottom:50px;width: 250px;height: 250px" src="<%=request.getContextPath()%><%=HomeController.checkFileShowView(((CommonContext)request.getSession().getAttribute(request.getSession().getId())).getUser().getUserId()).getFile_name()%>" alt="..." class="img-circle profile_img">
                                </div>
                                <button type="button" class="btn btn-primary btn-stp" data-toggle="modal" data-target="#myModal">
                                    Cập nhật ảnh đại diện
                                </button>

                                <!-- Modal -->
                                <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                                    <div class="modal-dialog" role="document">
                                        <div class="modal-content">
                                            <div class="modal-body">
                                                <div class="row">
                                                    <div style="top:-50px;">
                                                        <div class="col-md-12 text-center">
                                                            <div id="upload-demo"></div>
                                                        </div>
                                                        <label for="upload"
                                                               style="cursor:pointer;position: absolute;top: 306px;right:150px;z-index: 100;margin-right: 5px;margin-bottom: 5px">
                                                            <img style="width:40px;height:40px;"
                                                                 src="<%=request.getContextPath()%>/static/image/camera.png"/>
                                                        </label>
                                                        <input type="file" id="upload"
                                                               style="display: none">
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-default " data-dismiss="modal">Đóng</button>
                                                <button type="button" class="btn btn-primary  upload-result">Lưu ảnh đại diện</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </div>
                    </div>
                    <div class="col-md-12" style="clear: both"></div>
                    <div class="col-md-12">
                        <div class="form-group ">
                            <label class="col-md-3 control-label  label-bam-trai required">Email</label>
                            <div class="col-md-3">
                                <input type="text" class="form-control ${user.email_ != null? "error_input":""}"
                                       name="email" ${user.email_ != null? "autofocus":""} value="${user.email}">
                                <div class="error_tooltip">${user.email_}</div>
                            </div>
                        </div>
                        <div class="form-group ">
                            <label class="col-md-3 control-label  label-bam-trai">Điện thoại cố định</label>
                            <div class="col-md-3">
                                <input type="text" class="form-control ${user.telephone_ != null ? "error_input":""}"
                                       name="telephone" ${user.telephone_ != null ? "autofocus":""}
                                       value="${user.telephone}">
                                <div class="error_tooltip">${user.telephone_}</div>
                            </div>
                        </div>
                        <div class="form-group ">
                            <label class="col-md-3 control-label  label-bam-trai">Điện thoại di động</label>
                            <div class="col-md-3">
                                <input type="text" class="form-control ${user.mobile_ != null ? "error_input":""}"
                                       name="mobile" ${user.mobile_ != null ? "autofocus":""}
                                       value="${user.mobile}">
                                <div class="error_tooltip">${user.mobile_}</div>
                            </div>
                        </div>
                        <div class="form-group ">
                            <label class="col-md-3 control-label  label-bam-trai">Chức vụ</label>
                            <div class="col-md-9">
                                <input type="hidden" class="form-control" id="roleValue" value="${user.role}" >
                                <select class="form-control truong-selectbox-padding" name="role" id="roleId" disabled></select>
                                <div class="error_tooltip"></div>
                            </div>
                        </div>
                        <div class="row truong-inline-field">
                            <div class="form-group">
                                <label class="col-md-3 control-label label-bam-trai">Công chứng viên hợp danh</label>
                                <div class="col-md-9">
                                    <input type="checkbox" name="active_ccv" value="1" disabled ${user.active_ccv == 1? "checked":""}>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
            <div class="truong-prevent-btb">
                <div class="truong-can-button3">
                    <div class="col-md-3 col-md-offset-4 truong-rs-bt2os">
                        <input type="submit" class="form-control luu" name="" value="Lưu">
                        <%--<input type="submit" class="form-control luu" name="" value="Lưu">--%>
                    </div>
                    <div class="col-md-3 truong-rs-bt2">
                        <a class="truong-small-linkbt" href="${backUrl}"><input type="button" class="form-control huybo"
                                                                                name="" value="Hủy bỏ"></a>
                    </div>
                </div>
            </div>
            <div class="col-md-12" style="margin-bottom: 30px;"></div>
        </form>
    </div>
</div>
<script>
    $(function () {
        var openPassword = $('#isAdvanceSearch').val();

        if (openPassword == "true") {

            $("#basic-frm").css("display", "none");
            $("#advance-frm").css("display", "block");
        } else {

            $("#basic-frm").css("display", "block");
            $("#advance-frm").css("display", "none");
        }
    });
</script>
<script>
    function openChangePassword() {
        $("#basic-frm").css("display", "none");
        $("#advance-frm").css("display", "block");
        $('#isAdvanceSearch').val("true");
    }

    function closeChangePassword() {
        $("#basic-frm").css("display", "block");
        $("#advance-frm").css("display", "none");
        $('#isAdvanceSearch').val("false");
    }
</script>
<script>
    $(document).ready(function () {
        var url='<%=SystemProperties.getProperty("url_config_server_api")%>';
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
        $(".cr-slider").hide();
    });
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
<link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/croppie.css"/>
<script src="<%=request.getContextPath()%>/static/js/croppie.js" type="text/javascript"></script>
<script type="text/javascript">
    $("#updateUserInfo").submit(function () {
        $uploadCrop.croppie('result', {
            type: 'canvas',
            size: 'viewport'
        });
    });
    $uploadCrop = $('#upload-demo').croppie({
        enableExif: true,
        viewport: {
            width: 200,
            height: 200,
            type: 'circle'
        },
        boundary: {
            width: 300,
            height: 300
        }
    });
    $('#upload').on('change', function () {
        var reader = new FileReader();
        reader.onload = function (e) {
            $uploadCrop.croppie('bind', {
                url: e.target.result
            }).then(function () {
                console.log('jQuery bind complete');
            });
        }
        reader.readAsDataURL(this.files[0]);
    });
    $('.upload-result').on('click', function (ev) {
        $("#gif").show();
        var fullPath = $("#upload").val();
        if (fullPath) {
            var startIndex = (fullPath.indexOf('\\') >= 0 ? fullPath.lastIndexOf('\\') : fullPath.lastIndexOf('/'));
            var filename = fullPath.substring(startIndex);
            if (filename.indexOf('\\') === 0 || filename.indexOf('/') === 0) {
                filename = filename.substring(1);
            }
        }
        $uploadCrop.croppie('result', {
            type: 'canvas',
            size: 'viewport'
        }).then(function (resp) {
            $("#image").val(resp);
            $("#filename").val(filename);
            $("#updateUser").submit();
        });
    });
</script>
<jsp:include page="/WEB-INF/pages/layout/footer.jsp"/>
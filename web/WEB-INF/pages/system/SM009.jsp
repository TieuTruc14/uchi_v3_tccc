<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="/WEB-INF/pages/layout/header.jsp" />
<jsp:include page="/WEB-INF/pages/layout/body_top.jsp" />
<%--
    Danh sách tổ chức công chứng
--%>
<spring:url value="/prevent/add" var="addUrl" />
<div id="menu-map">
    <a href="#menu-toggle" id="menu-toggle"><img src="<%=request.getContextPath()%>/static/image/menu-icon.png"></a>
    <span id="web-map">Danh sách tổ chức công chứng</span>
</div>
<div class="col-md-12">
        <form class="form-horizontal" >
                <div class="panel-body">
                    <div class="row inline-field">
                        <div class="form-group">
                            <label class="col-md-2 control-label label-bam-trai">Họ đệm</label>
                            <div class="col-md-3">
                                <input type="text" class="form-control">
                            </div>

                            <label class="col-md-1 control-label label-bam-phai">Tên</label>
                            <div class="col-md-3">
                                <input type="text" class="form-control">
                            </div>
                        </div>
                    </div>
                    <div class="row inline-field">
                        <div class="form-group">
                            <label class="col-md-2 control-label label-bam-trai">Tài khoản đăng nhập</label>
                            <div class="col-md-3">
                                <input type="text" class="form-control">
                            </div>

                            <label class="col-md-1 control-label label-bam-phai">Giới tính</label>
                            <div class="col-md-3">
                                <select class=" form-control">
                                    <option>Nam</option>
                                    <option>Nữ</option>
                                </select>
                            </div>
                            <div class="col-md-3">
                                <div class="row">
                            <div class="col-md-6">

                                    <input type="submit" class="form-control luu" name="" value="Tìm kiếm">
                            </div>
                            <div class="col-md-6">
                                    <input type="submit" class="form-control huybo" name="" value="Xóa điều kiện">
                            </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
        </form>
    <div class="panel-body">
        <div class="col-md-2 col-md-offset-10">
            <input type="submit" class="form-control luu" name="" value="Thêm mới">
        </div>
    <table class="table" style="margin-bottom:0%" >

        <tr class="border-table">
            <th class=" ann-title border-table table-giua">Họ tên</th>
            <th class=" ann-title border-table table-giua">Tài khoản đăng nhập</th>
            <th class=" ann-title border-table table-giua">Ngày sinh</th>
            <th class=" ann-title border-table table-giua">Chức vụ</th>
            <th class=" ann-title border-table table-giua">Trạng thái hoạt động</th>
            <th class=" ann-title border-table table-giua">Phân quyền</th>

        </tr>
        <tr>
            <td class="border-table   "> Bùi Quang Minh</td>
            <td class="border-table" style="color: black;">Minhbq </td>
            <td class="border-table" style="color: black;"> 19/10/1994 </td>
            <td class="border-table" style="color: black;">Nhân viên</td>
            <td class="border-table" style="color: black;">Đang hoạt động</td>
            <td class="border-table" style="color: black;">Phân quyền</td>
        </tr>
        <tr>
            <td class="border-table "> Bùi Quang Minh</td>
            <td class="border-table" style="color: black;">Minhbq </td>
            <td class="border-table" style="color: black;"> 19/10/1994 </td>
            <td class="border-table" style="color: black;">Nhân viên</td>
            <td class="border-table" style="color: black;">Đang hoạt động</td>
            <td class="border-table" style="color: black;">Phân quyền</td>
        </tr>
        <tr>
            <td class="border-table "> Bùi Quang Minh</td>
            <td class="border-table" style="color: black;">Minhbq </td>
            <td class="border-table" style="color: black;"> 19/10/1994 </td>
            <td class="border-table" style="color: black;">Nhân viên</td>
            <td class="border-table" style="color: black;">Đang hoạt động</td>
            <td class="border-table" style="color: black;">Phân quyền</td>
        </tr>
        <tr>
            <td class="border-table "> Bùi Quang Minh</td>
            <td class="border-table" style="color: black;">Minhbq </td>
            <td class="border-table" style="color: black;"> 19/10/1994 </td>
            <td class="border-table" style="color: black;">Nhân viên</td>
            <td class="border-table" style="color: black;">Đang hoạt động</td>
            <td class="border-table" style="color: black;">Phân quyền</td>
        </tr>
        <tr>
            <td class="border-table "> Bùi Quang Minh</td>
            <td class="border-table" style="color: black;">Minhbq </td>
            <td class="border-table" style="color: black;"> 19/10/1994 </td>
            <td class="border-table" style="color: black;">Nhân viên</td>
            <td class="border-table" style="color: black;">Đang hoạt động</td>
            <td class="border-table" style="color: black;">Phân quyền</td>
        </tr>
        <tr>
            <th>Tổng 4 dữ liệu</th>
            <th></th>
            <th></th>
            <th></th>
            <th></th>
            <th>a;da;sd</th>
        </tr>
    </table>
    </div>
</div>


<jsp:include page="/WEB-INF/pages/layout/footer.jsp" />
<script>
    $(document).ready(function () {
        var parentItem = $("#quan-tri-he-thong");
        $(parentItem).click();
        $("#ds-to-chuc-cong-chung").addClass("child-menu");
    });
</script>




<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="/WEB-INF/pages/layout/header.jsp" />
<jsp:include page="/WEB-INF/pages/layout/body_top.jsp" />

<div class="col-md-12">
    <div class="panel-group" id="accordion">
        <form class="form-horizontal" >

            <div class="panel-body">


                <label class="control-label col-md-1 ann-title" >Tiêu đề &nbsp&nbsp&nbsp&nbsp</label>
                <div class="col-md-3">
                    <select class="form-control ann-select">
                        <option>1</option>
                    </select>
                </div>


                <label class="control-label col-md-2 ann-title">
                    <button class="button-left">Tìm kiếm</button>
                </label>
            </div>
        </form>
        <div class="col-md-12">
            <div class="col-md-2 col-md-offset-10">
                <input type="submit" class="form-control luu" name="" value="Thêm mới">
            </div>
            <div class="panel-body">

                <table class="table" style="margin-bottom:0%" >

                    <tr class="border-table">
                        <th class=" ann-title border-table table-giua">Người truy cập</th>
                        <th class=" ann-title border-table table-giua">Ngày truy cập</th>
                        <th class=" ann-title border-table table-giua">Máy truy cập</th>
                        <th class=" ann-title border-table table-giua">Kiểu truy cập</th>
                    </tr>
                    <tr>
                        <td class="border-table   "> Bùi Quang Minh</td>
                        <td class="border-table" style="color: black;">Minhbq </td>
                        <td class="border-table" style="color: black;"> 19/10/1994 </td>
                        <td class="border-table" style="color: black;">Nhân viên</td>

                    </tr>
                    <tr>
                        <td class="border-table "> Bùi Quang Minh</td>
                        <td class="border-table" style="color: black;">Minhbq </td>
                        <td class="border-table" style="color: black;"> 19/10/1994 </td>
                        <td class="border-table" style="color: black;">Nhân viên</td>

                    </tr>
                    <tr>
                        <td class="border-table "> Bùi Quang Minh</td>
                        <td class="border-table" style="color: black;">Minhbq </td>
                        <td class="border-table" style="color: black;"> 19/10/1994 </td>
                        <td class="border-table" style="color: black;">Nhân viên</td>

                    </tr>
                    <tr>
                        <td class="border-table "> Bùi Quang Minh</td>
                        <td class="border-table" style="color: black;">Minhbq </td>
                        <td class="border-table" style="color: black;"> 19/10/1994 </td>
                        <td class="border-table" style="color: black;">Nhân viên</td>

                    </tr>
                    <tr>
                        <td class="border-table "> Bùi Quang Minh</td>
                        <td class="border-table" style="color: black;">Minhbq </td>
                        <td class="border-table" style="color: black;"> 19/10/1994 </td>
                        <td class="border-table" style="color: black;">Nhân viên</td>

                    </tr>
                    <tr>
                        <th>Tổng 4 dữ liệu</th>
                        <th></th>
                        <th></th>
                        <th>a;da;sd</th>
                    </tr>
                </table>
    </div>
</div>
        </div>
    </div>
<jsp:include page="/WEB-INF/pages/layout/footer.jsp" />
<script>
    $(document).ready(function () {
        var parentItem = $("#quan-tri-he-thong");
        $(parentItem).click();
        $("#ls-truy-cap-he-thong").addClass("child-menu");
    });
</script>

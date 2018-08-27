<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/pages/layout/header.jsp" />
<jsp:include page="/WEB-INF/pages/layout/body_top.jsp" />
<%--
    Danh sách cán bộ  STP
--%>

<div id="menu-map">
    <a href="#menu-toggle" id="menu-toggle"><img src="<%=request.getContextPath()%>/static/image/menu-icon.png"></a>
    <span id="web-map">Liên hệ</span>
</div>

<div class="truong-form-chinhds panel-group">
    <div id="uchi-status" class="padding-default">

    </div>
    <form class="form-horizontal" action="" id="search-frm" method="get">
        <div class="panel-body">

            <table class="truong-tableinv">
                <td class="align-giua">

                    <img src="<%=request.getContextPath()%>/static/image/logo.png" class="truong-contact-logo"> </br></br>

                    <b class="truong-text-colorbl">Phiên bản 3.0</b>
                    <p>Bản quyền phần mềm Công ty cổ phần công nghệ phần mềm và nội dung số OSP. </p></br></br>
                    <p class="truong-text-heightlh">MỌI CHI TIẾT LIÊN HỆ </p>
                    <div> CÔNG TY CỔ PHẦN CÔNG NGHỆ PHẦN MỀM VÀ NỘI DUNG SỐ OSP.</div>
                    <div> Địa chỉ: Tầng 7, Tòa Nhà Đại Phát, số 82, Phố Duy Tân, Cầu Giấy, Hà Nội.</div>
                    <div> Điện thoại: <span class="truong-text-colorred">024-3568 2502</span>- Fax:<span class="truong-text-colorred">024-3568 2504</span></div>
                    <div>Email: <a href="">uchi@osp.com.vn</a>- Website: <a href="">www.osp.com.vn</a></div>
                </td>
            </table>
        </div>

    </form>
</div>






<jsp:include page="/WEB-INF/pages/layout/footer.jsp" />

<script>
    $(document).ready(function () {
        loadMenu();
    });
    function loadMenu() {
        $(".sidebar-nav > li > #lien-he").addClass("father-menu");
    }
</script>


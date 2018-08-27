<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="/WEB-INF/pages/layout/header.jsp" />
<jsp:include page="/WEB-INF/pages/layout/body_top.jsp" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/contract/uchi.css" />
<div id="menu-map">
    <a href="#menu-toggle" id="menu-toggle"><img src="<%=request.getContextPath()%>/static/image/menu-icon.png"></a>
    <span id="web-map">Danh sách hợp đồng</span>
</div>
<div class="truong-form-chinhds panel-group">

    <form class="form-horizontal" action="/template/contract/list" id="search-frm" method="get">

        <div class="col-md-12 truong-margin-footer0px">

            <table class="table" style="margin-bottom:0%" >

                <tr class="border-table">
                    <th class=" ann-title border-table table-giua" style="width:40px;">STT</th>
                    <th class=" ann-title border-table table-giua">Mã mẫu</th>
                    <th class=" ann-title border-table table-giua">Tên mẫu hợp đồng</th>
                    <th class=" ann-title border-table table-giua">Nhóm hợp đồng</th>
                    <th class=" ann-title border-table table-giua">Trạng thái</th>

                </tr>
                <c:if test="${page.rowCount > 0}">
                    <c:forEach items="${page.items}" var="item" varStatus="star">
                        <tr>
                            <td class="border-table" style="color: black;"><a >${star.index+1+(page.pageNumber-1)*20}</a></td>
                            <td class="border-table" style="color: black;"><a href="<%=request.getContextPath()%>/template/contract/edit/${item.id}">${item.code_template}</a></td>
                            <td class="border-table" style="color: black;"><a href="<%=request.getContextPath()%>/template/contract/edit/${item.id}">${item.name}</a></td>
                            <td class="border-table" style="color: black;"><a>${item.code}</a></td>
                            <td class="border-table" style="color: black;">
                                <c:choose>
                                    <c:when test="${item.active_flg==0}">
                                        <a>Ngừng hoạt động</a>
                                    </c:when>
                                    <c:otherwise>
                                        <a>Hoạt động</a>
                                    </c:otherwise>
                                </c:choose>
                            </td>

                        </tr>
                    </c:forEach>
                    <tr class="table-tr-xam">
                        <th colspan="7"><div class="truong-padding-foot-table"> Tổng số <span style="color:red">${page.rowCount}</span> dữ
                            liệu</div>
                            <div class="align-phai">
                                <ul class="pagination pagination-sm m-t-none m-b-none">
                                    <c:if test="${page.pageNumber>1}"><li><a href="<%=request.getContextPath()%>/template/contract/list?pageNumber=1">«</a></li></c:if>

                                    <c:forEach items="${page.pageList}" var="item" varStatus="star">
                                        <li><a href="<%=request.getContextPath()%>/template/contract/list?pageNumber=${item}"  style="${item==page.pageNumber ? 'color:mediumvioletred;':''}"> ${item}</a></li>
                                    </c:forEach>
                                    <c:if test="${page.pageNumber<page.pageCount}"><li><a href="<%=request.getContextPath()%>/template/contract/list?pageNumber=${page.pageCount}">»</a></li></c:if>
                                </ul>
                            </div>
                        </th>
                    </tr>
                </c:if>
                <c:if test="${page.rowCount == 0}">
                    <tr>
                        <td colspan="7"
                            style="height: 100%;background-color: #ececec; line-height: 5.429;text-align: center;font-size: 265%">
                            Chưa có dữ liệu
                        </td>
                    </tr>
                </c:if>

            </table>
        </div>
    </form>
</div>


<jsp:include page="/WEB-INF/pages/layout/footer.jsp" />
<script>
    $(document).ready(function () {
        loadMenu();
        $(".deleteItem").click(function(){
            var id=$(this).data('privy.id');
            $(".delete_id").val(id);
        });
    });
    function loadMenu() {
        var parentItem = $("#mau-giao-dien");
        $(parentItem).click();
        $("#tenhopdong").addClass("child-menu");
    }
</script>



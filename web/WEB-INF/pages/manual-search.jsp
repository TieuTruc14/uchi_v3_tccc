<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/pages/layout/header.jsp" />
<jsp:include page="/WEB-INF/pages/layout/body_top.jsp" />
<%--
    Hướng dẫn tra cứu
--%>

<div id="menu-map">
    <a href="#menu-toggle" id="menu-toggle"><img src="<%=request.getContextPath()%>/static/image/menu-icon.png"></a>
    <span id="web-map">Hướng dẫn tra cứu</span>
</div>
<div class="truong-form-chinhbtt panel-group">

    <form class="form-horizontal" action="" id="search-frm" method="get">
        <div class="panel panel-default" id="panel1">
            <div class="panel-heading">
                <h4 class="panel-title">

                    THỦ THUẬT TÌM KIẾM VỚI UCHI

                </h4>

            </div>
            <div class="panel-body">

                <table class="truong-tableinv">
                    <td style="">
                        <%--<p style="text-align: center">
                            <b style="font-size: 15px">THỦ THUẬT TÌM KIẾM VỚI UCHI</b>
                        </p>--%>
                        <p>Người dùng có thể nhập bất kỳ thông tin gì vào ô tìm kiếm, Kết quả sẽ bao gồm các dữ liệu: Chứa đầy đủ các từ nhập vào, không phân biệt thứ tự giữa các từ, không phân biệt chữ hoa, chữ thường, không phân biệt dấu. Dữ liệu tìm ra có thể nhiều, để tìm kiếm hiệu quả nên sử dụng các thủ thuật sau:
                        </p>
                        <p><b>1. Tìm kiếm chính xác theo một cụm từ</b></p>
                        <p>Muốn tìm kiếm chính xác một cụm từ, nhập cụm từ cần tìm trong dấu nháy kép <b>“”</b>. Ví dụ muốn tìm kiếm dữ liệu chứa chính xác cụm từ
                            <b>Lê Thanh Nghị,</b> nhập vào <b>“Lê Thanh Nghị”</b>.
                            Nếu không để trong nháy kép thì các dữ liệu có chứa cả 3 từ trên cũng sẽ được tìm kiếm ra. Ví dụ dữ liệu có chứa <b style="color:red">Lê Thanh</b><b> Mai</b> và <b>Nguyễn Văn</b><b style="color: red"> Nghị</b> cũng sẽ được tìm ra.
                        </p>
                        <p><b>2. Tìm kiếm kết hợp nhiều điều kiện</b></p>
                        <p>Càng biết nhiều thông tin tìm kiếm thì phạm vi tìm kiếm càng được giới hạn, kết quả tìm kiếm càng sát với mong muốn. </p>
                        <p>- Nếu biết địa chị cụ thể, ví dụ ở <b>115 Lê Thanh Nghị, Hai Bà Trưng</b>, người dùng nhập vào <b>115 “Lê Thanh Nghị” “Hai Bà Trưng”</b>. Tại sao không nhập <b>“115 Lê Thanh Nghị” “Hai Bà Trưng”</b>? Vì như vậy những dữ liệu chứa <b>115 đường (phường,….) Lê Thanh Nghị</b> sẽ không ra.</p>
                        <p>- Nếu biết thêm số tờ bản đồ, số thửa, ví dụ <b>115 Lê Thanh Nghị, tờ 03 thửa 12</b>. Nhập vào <b>115 “Lê Thanh Nghị” tờ 03 thửa 12</b>. Tại sao không nên nhập <b>115 “Lê Thanh Nghị” tờ</b><b style="color: red;"> bản đồ</b> <b>số 03 thửa</b><b style="color: red"> đất số</b> 12? Vì như vậy các dữ liệu chỉ chứa <b>tờ 03 hoặc tờ số 03, thửa số 12,…</b> sẽ không được tìm ra.</p>
                        <p>- Nếu biết thêm thông tin về bên tham gia giao dịch hoặc chủ sở hữu tài sản, muốn giới hạn kết quả hơn nữa, có thể nhập vào: <b>115 “Lê Thanh Nghị” tờ 03 thửa 12 “Lê Thanh Mai”</b> trong đó<b> Lê Thanh Mai</b> là tên bên giao dịch. Nếu người dùng sử dụng tìm kiếm nâng cao thì thông tin “<b>Lê Thanh Mai”</b> sẽ nhập vào ô Bên Liên Quan/ chủ sở hữu, còn thông tin tài sản nhập vào ô Thông tin tài sản.</p>
                            <p style="text-align:center;"><img style="border: blue solid 1px;width:650px !important;" src="<%=request.getContextPath()%>/static/image/search_skill.png"></p>
                        <p>- Nếu biết người dùng có thể nhập thêm thông tin số giấy chứng nhận. Chỉ cần nhập phần giá trị, không cần nhập cụm từ <b>Số giấy chứng nhận</b>.</p>
                        <p><b style="color: red">Lưu ý</b>: Một số đơn vị sử dụng các phần mềm cũ, không nhập vào số giấy chứng nhận, nên việc tìm theo số giấy chứng nhận có thể sót dữ liệu.</p>
                        <p><b>3. Tìm kiếm mở rộng</b></p>
                        <p>Sử dụng dấu <b>* </b>để tìm kiếm mở rộng về phía trái, phải hoặc cả 2 bên</p>
                        <p>- Nếu nhập <b>*3</b>, kết quả tìm kiếm sẽ bao gồm những dữ liệu chứa số <b>3</b> ở cuối từ, ví dụ <b>12</b><b style="color: red;">3</b>.</p>
                        <p>- Nếu nhập <b>*3*,</b> kết quả tìm kiếm sẽ bao gồm những dữ liệu có chứa số <b>3</b>, ví dụ <b>12</b><b style="color: red;">3</b><b>45</b></p>



                        <%--<b class="truong-text-colorbl">Phiên bản 3.0</b>
                        <p>Bản quyền phần mềm Công ty cổ phần công nghệ phần mềm và nội dung số OSP. </p></br></br>
                        <p class="truong-text-heightlh">MỌI CHI TIẾT LIÊN HỆ </p>
                        <div> TỔNG CÔNG TY CỔ PHẦN CÔNG NGHỆ PHẦN MỀM VÀ NỘI DUNG SỐ OSP.</div>
                        <div> Địa chỉ: Tầng 7, Tòa Nhà Đại Phát, số 82, Phố Duy Tân, Cầu Giấy, Hà Nội.</div>
                        <div> Điện thoại: <span class="truong-text-colorred">04-3568 2502</span>- Fax:<span class="truong-text-colorred">04-3568 2504</span></div>
                        <div>Email: <a href="">uchi@osp.com.vn</a>- Website: <a href="">www.osp.com.vn</a></div>--%>
                    </td>
                </table>
            </div>
        </div>
    </form>
</div>






<jsp:include page="/WEB-INF/pages/layout/footer.jsp" />




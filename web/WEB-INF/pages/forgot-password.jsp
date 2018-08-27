<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<jsp:include page="layout/header.jsp" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/login.css" type="text/css" />
<%--<script src="./static/plugin/jquery.min-1.9.1.js" type="text/javascript"></script>--%>
<%--<script src="./static/plugin/jquery.cookie.js" type="text/javascript"></script>
<script src="./static/js/login.js" type="text/javascript"></script>--%>

<%--<jsp:include page="layout/body_top.jsp" />--%>
<spring:url value="/dangnhap" var="dangnhapUrl" />
<spring:url value="/send-email" var="mailUrl" />

<form name="loginform" method="post" action="${mailUrl }" id="loginform">
  <div id="content" class="col-md-12">
      <div class="truong-logo-stp">
          <img id="img-uchi-logo" src="<%=request.getContextPath()%>/static/image/stp_logo.png" alt="Uchi Logo" style="width: 100px !important;float: left;height: 100% !important;"/>
          <span style="vertical-align: middle;font-size: 20px;margin-left: 5px">VĂN PHÒNG CÔNG CHỨNG SỐ 1</span>

      </div>
    <div id="form" class="truong-login-form">
      <div id="focus">
          <div class="row">

              <div class="row">

                  <div id="uchi-logo" class="col-md-8 col-md-offset-2 col-xs-10 col-xs-offset-1 ">
                      <%--<img id="img-uchi-logo" src="<%=request.getContextPath()%>/static/image/login/uchi-icon.png" alt="Uchi Logo"/>--%>
                      <p id="uchi-slogan"> HỆ THỐNG CƠ SỞ DỮ LIỆU CÔNG CHỨNG VÀ THÔNG TIN NGĂN CHẶN </p>
                  </div>
                  <div class="col-md-2"></div>
              </div>
          </div>
          <div id="truong-login-view" class="">
              <ul>
                  <li class="col-md-5" id="uchi-info">
                      <%--<div class="row">
                          <div class="col-md-9"></div>
                          <div class="col-md-3">
                              <img id="img-logo" src="<%=request.getContextPath()%>/static/image/login/uchi-icon2.png" alt="Uchi Logo"/>
                          </div>
                      </div>--%>
                      <div class="row" class="uchi-info">

                          <div class="col-md-12" id="about-us">
                              <img src="<%=request.getContextPath()%>/static/image/login/uchi-icon.png" alt="Uchi Logo" style="max-width: 80px !important;max-height: 80px !important;"/>
                              <p style="font-size: 14px !important;">Ngôi nhà chung của các tổ chức công chứng</p>
                              <p style="margin-bottom: 0px !important;">Hotline: 02435 682 502 - 0986 083 003</p>
                              <p>2016 Bản quyền thuộc về osp.com.vn</p>
                          </div>
                      </div>
                      <div class="row"></div>
                  </li>
                  <li class="truong-user-fixwidth" id="login-form">
                      <div class="row">
                          <div id="login-system">Quên mật khẩu</div>
                      </div>
                      <div class="row">
                          <div class="image-user" style="float: left"></div>
                          <input type="text" name="account" id="username" placeholder="Tài khoản..."/>
                          <div class="error_tooltip">${forgetPasswordForm.account_}</div>
                      </div>
                      <div class="row">
                          <div class="image-password" style="float: left"></div>
                          <input type="text" name="email" id="password" placeholder="Email..."/>
                          <div class="error_tooltip">${forgetPasswordForm.email_}</div>
                          <div class="success_tooltip">${forgetPasswordForm.emailSuccess_}</div>
                      </div>
                      <div class="row" id="login-consol">
                          <button type="submit" class="col-md-5 col-xs-6" id="login" >ĐỒNG Ý</button>
                          <%--<div class="col-md-1"></div>--%>
                          <div class="col-md-6 col-md-offset-1 col-xs-6" id="forget-pass"><a href="${dangnhapUrl}"><img id="forget-img" src="<%=request.getContextPath()%>/static/image/login/dangnhap.png">Đăng nhập</a></div>
                      </div>
                  </li>
              </ul>
          </div>
      </div>
    </div>
      <div class="col-md-1"></div>
      <div class="row" id="footer-info">
          <div class="col-md-3">Uchi Sở Tư Pháp v3.0 <img src="<%=request.getContextPath()%>/static/image/login/uchi-icon-mini.png"></div>
          <div class="col-md-5"></div>
          <div class="col-md-4" id="company">Công ty cổ phần công nghệ phần mềm và nội dung số OSP</div>
      </div>
  </div>

</form>

<script>
    $(document).ready(function(){
        var firstTarget = $("form[name ='loginform']").find('input[type=text],textarea,select').filter(':visible:first');
        firstTarget.focus();
        $(".error_input:first").focus();

    });
</script>
<style>
    input:-webkit-autofill,
    input:-webkit-autofill:hover,
    input:-webkit-autofill:focus,
    input:-webkit-autofill{


        -webkit-box-shadow: 0 0 0px 1000px white inset;
    }
</style>
<%--
<jsp:include page="layout/footer.jsp" />--%>
<script>
    $(window).on('load resize',function(){
        var win = $(this);
        if (win.width() < 970 && win.width()>700){
            $('#truong-login-view').addClass('truong-padding-300px');


        }else {
            $('#truong-login-view').removeClass('truong-padding-300px');
        }
    });
    $(window).on('load resize',function(){
        var win = $(this);
        if (win.width()>700){
            $('#uchi-info').css('display','block');


        }else {
            $('#uchi-info').css('display','none');
        }
    });
</script>

$(document).ready(function () {
    $("#form").css('height', $(document).height() - 68);
    $("#txtUserName").focus();
    $("#txtUserName").select();
    var remember = $.cookie('remember');
    if (remember == 'true') {
        var username = $.cookie('username');
        var password = $.cookie('password');
        // autofill the fields
        $('#txtUserName').val(username);
        $('#txtPassWord').val(password);
        $('#cbxRememberPassword').attr('checked', true);
    }
});
function checkvalidate() {
    if ($("#txtUserName").val() == '') {
        $("#usernameerror").text("Tên đăng nhập");
        $("#lblMsg").text('');
        $("#txtUserName").focus();
        return false;
    }
    else $("#usernameerror").text('');
    if ($("#txtPassWord").val() == '') {
        $("#passworderror").text("Mật khẩu");
        $("#lblMsg").text('');
        $("#txtPassWord").focus();
        return false;
    }
    else $("#passworderror").text('');
    Rememberme();
    $("#dvloading").html("<img src='/Images/loading1.gif' border='0' alt='Loading' />");
    $("#dvLogin").show();
}
function Rememberme() {
    if ($('#cbxRememberPassword').is(':checked')) {
        var username = $('#txtUserName').val();
        var password = $('#txtPassWord').val();

        // set cookies to expire in 14 days
        $.cookie('username', username, { expires: 14 });
        $.cookie('password', password, { expires: 14 });
        $.cookie('remember', true, { expires: 14 });
    }
    else {
        // reset cookies
        $.cookie('username', null);
        $.cookie('password', null);
        $.cookie('remember', null);
    }
}    
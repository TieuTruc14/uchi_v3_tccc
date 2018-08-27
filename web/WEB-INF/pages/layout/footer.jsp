<%@ page contentType="text/html; charset=UTF-8" %>
<script>
    $(".select2").select2({
        /*placeholder: '---Chọn---',*/
        allowClear: true,
        tags: true,
        width: '100%'
    });
</script>
</div>
</div>
</div>
</div>
<!-- /#page-content-wrapper -->

</div>
<div>

</div>
<div class="col-md-1"></div>
<div class="row" id="footer-info-uchi">
    <div class="col-md-3">Uchi Tổ chức công chứng v3.0 <img src="<%=request.getContextPath()%>/static/image/login/uchi-icon-mini.png"></div>
    <div class="col-md-5"></div>
    <div class="col-md-4" id="company">Công ty cổ phần công nghệ phần mềm và nội dung số OSP</div>
</div>
<!-- /#wrapper -->
<script src="<%=request.getContextPath()%>/static/js/footer-script.js" type="text/javascript"></script>

</body>
</html>
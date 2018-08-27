$(document).ajaxStart(function(){ 
    	$('#ajaxProgress').show(); 
    });
  $(document).ajaxStop(function(){ 
    	$('#ajaxProgress').hide(); 
});
function showPackage(id, roleUpdate){
    	 jQuery.ajax({
    			type: "GET",
    			url: 'views/getPackage',
    			dataType:'json',
    			contenttype:'application/json; charset=utf-8',
    			data : {
    				id : id,
    			},
    			success:function(response) {
    				openPackage(response, roleUpdate);
    			}
    		});
     }
function stopPackage(id){
	jQuery.ajax({
		type: "GET",
		url: 'views/stop',
		dataType:'json',
		contenttype:'application/json; charset=utf-8',
		data : {
			id : id,
		},
		success:function(response) {
			if(response.success != undefined){
				alert("Dừng hoạt động Package thành công!");
			}
			location.reload();
		}
	  });
}
function activePackage(id){
	jQuery.ajax({
		type: "GET",
		url: 'views/active',
		dataType:'json',
		contenttype:'application/json; charset=utf-8',
		data : {
			id : id,
		},
		success:function(response) {
			if(response.success != undefined){
				alert("Kích hoạt Package thành công!");
			}
			location.reload();
		}
	  });
}
function updatePackage(){
	var data = $('#fmPackage').serialize();
	jQuery.ajax({
		type: "GET",
		url: 'views/update',
		dataType:'json',
		contenttype:'application/json; charset=utf-8',
		data : data,
		success:function(response) {
			if(response.success != undefined){
				if(response.testDB && response.testMail){
					alert("Thêm/cập nhật thành công");
					openPackage($.parseJSON(response.success), false);
				}else{
					alert("Cấu hình bị lỗi, ấn ok xem chi tiết");
					var errorContent = "";
					if(!response.testDB){
						errorContent += "<span class='error-head'>Lỗi kết nối databases:</span></br> " + response.errorDB;
					}
					if(!response.testMail){
						errorContent += "<span class='error-head'>Lỗi kết nối mail server:</span></br> " + response.errorMail;
					}
					showError(errorContent);
				}
			}else{
				showError(response.error);	
				}
 			}
	  });
}

function openPackage(packageItm, roleUpdate){
	var popupContent = renderPackage(packageItm, roleUpdate);
	var title = "Thêm gói";
	if(packageItm.id != 0){
		title= 'Cập nhật gói '+packageItm.name;
	}
	jQuery.fancybox(popupContent, {
		  title: title,
          fitToView: false,
          width: 1024,
          closeClick: false,
          tpl: {closeBtn : '<a title="Close" class="fancybox-item fancybox-close-inner" href="javascript:;"></a>'},
      	  padding: [60, 10, 10, 10]
        });
	changeType();
}

function renderPackage(packageItm, roleUpdate){
	packageItm = removeNull(packageItm);
	var popupContent = "";
	if(roleUpdate == 2){
		popupContent +=	"<form id='fmPackage' action='javascript:updatePackage();' ><div id='detail'>";
	} else {
		popupContent +=	"<form id='fmPackage'><div id='detail'>";
	}
	popupContent +="<input type='hidden' name='id' value='"+packageItm.id+"'/>";
	popupContent += "<table class='one-packet'>";
	if(packageItm.id != 0){
		popupContent +="<tr><td>ID</td><td><input type='text' disabled name='id_' value='"+packageItm.id+"'/></td></tr>"
	}
	popupContent += "<tr><td>Name</td><td>";
	popupContent +="<input type='text' name='name' value='"+packageItm.name+"'/></td></tr>";
	popupContent += "<tr><td>Description</td><td>";
	popupContent +="<input type='text' name='description' value='"+packageItm.description+"'/></td></tr>";
	popupContent += "<tr><td>Connection</td><td>";
	popupContent +="<input type='text' name='connection' value='"+packageItm.connection+"'/></td></tr>";
	popupContent += "<tr><td>SourceUsername</td><td>";
	popupContent +="<input type='text' name='sourceUserName' value='"+packageItm.sourceUserName+"'/></td></tr>";
	popupContent += "<tr><td>SourcePassword</td><td>";
	popupContent +="<input type='password' name='sourcePassword' value='"+packageItm.sourcePassword+"'/></td></tr>";
	popupContent += "<tr><td>Type</td><td>";
	if(packageItm.type == 0){
		popupContent +="<select id='selectType' name='type' onchange='changeType()'><option selected value='0'>Mặc định</option><option value='1'>Build</option></select></td></tr>";
	}else{
		popupContent +="<select id='selectType' name='type' onchange='changeType()'><option value='0'>Mặc định</option><option selected value='1'>Build</option></select></td></tr>";
	}
	popupContent += "<tr class='type-build hidden'><td>Query Get</td><td>";
	popupContent +='<textarea type="text" name="queryGet">'+packageItm.queryGet+'</textarea></td></tr>';
	popupContent += "<tr class='type-build hidden'><td>Query Update</td><td>";
	popupContent +='<textarea type="text" name="queryUpdate">'+packageItm.queryUpdate+'</textarea></td></tr>';    
	popupContent += "<tr><td>Template</td><td>";
	popupContent +='<textarea type="text" name="template">'+packageItm.template+'</textarea></td></tr>';
	popupContent += "<tr><td>Period</td><td>";
	popupContent +="<input type='text' name='period' value='"+packageItm.period+"'/></td></tr>";
	popupContent += "<tr><td>Token</td><td>";
	popupContent +="<input type='text' name='token' value='"+packageItm.token+"'/></td></tr>";
	popupContent += "<tr><td>MailServer</td><td>";
	popupContent +="<input type='text' name='mailServer' value='"+packageItm.mailServer+"'/></td></tr>";
	popupContent += "<tr><td>MailUsername</td><td>";
	popupContent +="<input type='text' name='mailUsername' value='"+packageItm.mailUserName+"'/></td></tr>";
	popupContent += "<tr><td>MailPassword</td><td>";
	popupContent +="<input type='password' name='mailPassword' value='"+packageItm.mailPassword+"'/></td></tr>";
	popupContent += "<tr><td>Status</td><td>";
	if(packageItm.status == 0){
		popupContent +="<select id='sttPackage' class='sl-type' name='status'><option selected value='0'>Active</option><option value='9'>Disable</option></select>";
	}else{
		popupContent +="<select id='sttPackage' class='sl-type' name='status'><option value='0'>Active</option><option selected value='9'>Disable</option></select>";
	}
	popupContent += "<tr><td></td><td>";
	if(roleUpdate == 2){
		popupContent +="<button type='submit' >Cập nhật</button></td></tr>";
	} else {
		popupContent +="</td></tr>";
	}
	
	popupContent += "</table></div></form>";
	return popupContent;
}

function deletePackage(id){
	jQuery.ajax({
		type: "GET",
		url: 'views/delete',
		dataType:'json',
		contenttype:'application/json; charset=utf-8',
		data : {
			id : id,
		},
		success:function(response) {
			if(response.success != undefined){
				alert("Xóa Package thành công!");
			}
			location.reload();
		}
	  });
}
function showError(errorCotent){
	var message = "<div class='popup-error'><a href='javascript:$.unblockUI();' class='fancybox-item fancybox-close-inner' title='Close'></a>" +
	"<div class='bkav-fancy-title' style='position:inherit;text-align: left;'><div class='bkav-head-title'>" +
	"Đã xảy ra lỗi" +
	"</div></div>" +
	"<div class='error-content'><p>"+
	errorCotent+
	"</p></div></div>";
	$.blockUI({
	message: $(message),
	css: {
			width: '1000px',
			center:true
		 },
	});
}
function removeNull(json){
	$.each(json, function(key, value) {
	    if(value == 'null' || value ==null){
	    	json[key] = "";

	    }
	});
	return json;
}
function changeType(){
	var a = $('#selectType option:selected').val();
	if(a==1){
		$('.type-build').show();
	} else {
		$('.type-build').hide();
	}
}

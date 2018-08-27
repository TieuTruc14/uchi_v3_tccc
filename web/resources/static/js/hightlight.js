$(document).ready(function () {
    var values = [];
    var keyList=$('#listKey').val();
    var text = $('#key-search').val();
    var property = $('#property-information').val();
    var propertyown = $('#property-own').val();
    var keyword = text +" "+property +" "+ propertyown;
    keyword=$.trim(keyword);

    keyList=keyList.replace(/[\^\'\!\~\@\#\$\%\&]/g, '');
    keyword=keyword.replace(/[\^\'\!\~\@\#\$\%\&]/g, '');
    var options=true;
    if(keyword.indexOf('"') >= 0){
        options=false;
        keyword=keyList;


        var newArray = keyList.split(',');
        for (var i = 0; i < newArray.length; i++) {
            values.push(newArray[i]);
        }

        keyword=values;
    }
    var optionsAccuracy='"exactly"';
    $(".highlight-content").unmark({
        done: function() {
            $(".highlight-content").mark(keyword, {
                "ignorePunctuation": [
                    "/",
                    "-",
                    "!"
                ],
                "separateWordSearch":options,
                "wildcards": "enabled",
                "accuracy": {
                    "value": "exactly",
                    "limiters": [":",";",",", ".","?","/"]
                },
                "synonyms": {
                    "ư":"u",
                    "ứ":"u",
                    "ừ":"u",
                    "ử":"u",
                    "ữ":"u",
                    "ự":"u",

                    "ệ":"e",
                    "ê":"e",
                    "ề":"e",
                    "ế":"e",
                    "ể":"e",
                    "ễ":"e",

                    "ô":"o",
                    "ồ":"o",
                    "ố":"o",
                    "ộ":"o",
                    "ổ":"o",
                    "ỗ":"o",

                    "ù":"u",
                    "ú":"u",
                    "ụ":"u",
                    "ủ":"u",
                    "ũ":"u",

                    "è":"e",
                    "é":"e",
                    "ẹ":"e",
                    "ẻ":"e",
                    "ẽ":"e",

                    "ò":"o",
                    "ó":"o",
                    "ọ":"o",
                    "ỏ":"o",
                    "õ":"o",

                    "à":"a",
                    "á":"a",
                    "ạ":"a",
                    "ả":"a",
                    "ã":"a",

                    "â":"a",
                    "ấ":"a",
                    "ầ":"a",
                    "ẩ":"a",
                    "ẫ":"a",
                    "ậ":"a",

                    "ă":"a",
                    "ắ":"a",
                    "ằ":"a",
                    "ẳ":"a",
                    "ẵ":"a",
                    "ặ":"a",

                    "ì":"i",
                    "í":"i",
                    "ị":"i",
                    "ỉ":"i",
                    "ĩ":"i",

                    "ơ":"o",
                    "ờ":"o",
                    "ớ":"o",
                    "ợ":"o",
                    "ở":"o",
                    "ỡ":"o"


                }
            });
        }
    });
    $('.textInputSearch').keypress(function (e) {
        if (e.which == 13) {
            $('#daDuyetPage').val(1);
            $('#hopDongPage').val(1);
            $('form#search-frm').submit();
        }
    });
    $('#listKey').val("");
});
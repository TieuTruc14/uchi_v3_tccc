/**
 * Created by tranv on 11/29/2016.
 */
//Check font
/*if(!doesFontExist("UTM-Neo-Sans-Intel")) {
    addFont("UTM-Neo-Sans-Intel", "./static/font/UTM-Neo-Sans-Intel.ttf");
}
 */
function addFont(fontName,fontURL){
    var newStyle = document.createElement('style');
    newStyle.appendChild(document.createTextNode("\
	@font-face {\
	    font-family: '" + fontName + "';\
	    src: url('" + fontURL + "') format('truetype');\
	}\
	"));
    var head = document.getElementsByTagName("head")[0];
    head.appendChild(newStyle);
}


function doesFontExist(fontName) {
    try{
        // creating our in-memory Canvas element where the magic happens
        var canvas = document.createElement("canvas");
        var context = canvas.getContext("2d");

        // the text whose final pixel size I want to measure
        var text = "abcdefghijklmnopqrstuvwxyz0123456789";

        // specifying the baseline font
        context.font = "72px monospace";

        // checking the size of the baseline text
        var baselineSize = context.measureText(text).width;

        // specifying the font whose existence we want to check
        context.font = "72px '" + fontName + "', monospace";

        // checking the size of the font we want to check
        var newSize = context.measureText(text).width;

        // removing the Canvas element we created
        delete canvas;

        //
        // If the size of the two text instances is the same, the font does not exist because it is being rendered
        // using the default sans-serif font
        //
        if (newSize == baselineSize) {
            return false;
        } else {
            return true;
        }
    }catch (e) {
        return true;
    }
}
$(document).ready(function(){
    var firstTarget = $('.form-horizontal').find('input[type=text],textarea,select').filter(':visible:enabled:first');
    firstTarget.focus();
    $(".error_input:first").focus();

});


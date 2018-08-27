/**
 * Created by tranv on 11/30/2016.
 */
$(function() {
    var menu_ul = $('.sidebar-nav > li > ul'),
        menu_a  = $('.sidebar-nav > li > a.dropdown');

    menu_ul.hide();
    menu_a.click(function(e) {
        e.preventDefault();
        if(!$(this).hasClass('active')) {
            menu_a.removeClass('active');
            menu_ul.filter(':visible').slideUp('normal');
            $(this).children("img.ar2").css("transform","rotate(90deg)");
            $(this).addClass('active').next().stop(true,true).slideDown(200);
        } else {
            $(this).children(".ar2").removeAttr('style');
            $(this).removeClass('active');
            $(this).next().stop(true,true).slideUp(200);
        }
    });
});
<!-- Menu Toggle Script -->

$("#menu-toggle").click(function(e) {
    e.preventDefault();
    $("#wrapper").toggleClass("toggled");
});

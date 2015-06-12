$(function() {

    $('#side-menu').metisMenu();

});

//Loads the correct sidebar on window load,
//collapses the sidebar on window resize.
// Sets the min-height of #page-wrapper to window size
$(function() {
    $(window).bind("load resize", function() {
        topOffset = 50;
        width = (this.window.innerWidth > 0) ? this.window.innerWidth : this.screen.width;
        if (width < 768) {
            $('div.navbar-collapse').addClass('collapse');
            topOffset = 100; // 2-row-menu
        } else {
            $('div.navbar-collapse').removeClass('collapse');
        }

        height = ((this.window.innerHeight > 0) ? this.window.innerHeight : this.screen.height) - 1;
        height = height - topOffset;
        if (height < 1) height = 1;
        if (height > topOffset) {
            $("#page-wrapper").css("min-height", (height) + "px");
        }
    });

    var url = window.location;
    var element = $('ul.nav a').filter(function() {
        return this.href == url || url.href.indexOf(this.href) == 0;
    }).addClass('active').parent().parent().addClass('in').parent();
    if (element.is('li')) {
        element.addClass('active');
    }
});

// Function for collapse hpanel
$('.showhide').click(function (event) {
    event.preventDefault();
    var hpanel = $(this).closest('div.hpanel');
    var icon = $(this).find('i:first');
    var body = hpanel.find('div.panel-body');
    var footer = hpanel.find('div.panel-footer');
    body.slideToggle(300);
    footer.slideToggle(200);

    // Toggle icon from up to down
    icon.toggleClass('fa-chevron-up').toggleClass('fa-chevron-down');
    hpanel.toggleClass('').toggleClass('panel-collapse');
    setTimeout(function () {
        hpanel.resize();
        hpanel.find('[id^=map-]').resize();
    }, 50);
});

// Function for close hpanel
$('.closebox').click(function (event) {
    event.preventDefault();
    var hpanel = $(this).closest('div.hpanel');
    hpanel.remove();
});

//Handle minimalize sidebar menu
$('.hide-menu').click(function(event){
    event.preventDefault();
    if ($(window).width() < 768) {
        $("body").toggleClass("show-sidebar");
    } else {
        $("body").toggleClass("hide-sidebar");
    }
});
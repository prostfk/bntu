let iScrollPos = 0;
$(window).scroll(function () {
    let iCurScrollPos = $(this).scrollTop();
    if (iCurScrollPos > iScrollPos) {
        $("body").addClass("sticky-shrinknav-wrapper");
    } else {
        $("body").removeClass("sticky-shrinknav-wrapper");
    }
    iScrollPos = iCurScrollPos;
});
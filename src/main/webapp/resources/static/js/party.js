window.onload=buttonClicks;

function buttonClicks() {
    $("#back").click(function () {
        window.location.href = "/search_parties";
    });
    $("#main_page").click(function () {
        window.location.href = "/";
    });
}
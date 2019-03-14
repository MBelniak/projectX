window.onload= function () {

    $("#back").click(function () {
        window.location.href = "/search_parties";
    });
    $("#main_page").click(function () {
        window.location.href = "/";
    });
    if ($("#edit_button") != undefined)
        $("#edit_button").click(function () {

        });
    if ($("#username") != undefined) {
        $("#username").click(function () {
            window.location.href = "/user_details";
        });
    }
};

window.onload= function () {

    $("#back").click(function () {
        history.go(-1);
        return false;
    });
    $("#main_page").click(function () {
        window.location.href = "/";
    });
    if ($("#edit_button") != undefined)
        $("#edit_button").click(function () {

        });
};
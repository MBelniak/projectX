window.onload = function () {
    var user_parties;
    var no_edit = $(".no_edit");
    var edit = $(".edit");
    var warning = $("#warning");
    edit.hide();
    var table_body = $('#parties_table');
    $.ajax('/current_user/organized_parties', {}).then(function success(response) {
        user_parties = response;
        showParties(user_parties, table_body);
    }, function fail() {
        table_body.html("Cannot fetch data from the server");
    });

    $("#back1").click(function () {
        history.go(-1);
        return false;
    });
    $('#main1').click(function () {
        window.location.href = "/";
    });
    $("#back2").click(function () {
        history.go(-1);
        return false;
    });
    $('#main2').click(function () {
        window.location.href = "/";
    });
    $("#edit_button").click(function () {
        edit.show();
        no_edit.hide();
    });
    $("#cancel").click(function () {
        edit.hide();
        no_edit.show();
    });
    $("#save_button").click(function () {
        var requestJSON = prepareJSON();
        $.ajax({
            url: '/users',
            type: 'PUT',
            contentType: 'application/JSON; charset=utf-8',
            data: JSON.stringify(requestJSON),
            success: function (response) {
                if (response == "") {
                    updateInfo(requestJSON);
                    edit.hide();
                    no_edit.show();
                    warning.html("Successfully updated profile");
                }
                else {
                    warning.html("Error: ");
                    for (var i = 0; i < response.length; i++)
                        warning.append("<p>" + response[i] + "</p>");
                }
            },
            error: function (error) {
                warning.html("Error " + error.code + " - " + error.status);
            }
        });

    });
};

function prepareJSON() {
    var userPOJO = {};
    userPOJO.first_name = $("#first_name_input").val();
    userPOJO.surname = $("#surname_input").val();
    userPOJO.date_of_birth = $("#birth_input").val();
    userPOJO.email = $("#email").text();
    return userPOJO;
}

function updateInfo(userPOJO) {
    $("#first_name").text(userPOJO.first_name);
    $("#surname").text(userPOJO.surname);
    $("#birth").text(userPOJO.date_of_birth);
}

function showParties(parties, table) {
    parties.forEach(function (party) {
        table.append("<tr><td><a href='/search_parties/" + party['id'] + "'>" + party["name"] + "</a></td></tr>");
    });
}
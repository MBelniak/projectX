window.onload = function () {
    var user_parties;
    var hideable = $(".hideable");
    var info = $(".info");
    var warning = $("#warning");
    hideable.hide();
    var table_body = $('#parties_table');
    $.ajax('/current_user/organized_parties', {}).then(function success(response) {
        user_parties = response;
        showParties(user_parties, table_body);
    }, function fail() {
        table_body.html("Cannot fetch data from the server");
    });

    $("#back").click(function () {
        history.go(-1);
        return false;
    });
    $('#main').click(function () {
        window.location.href = "/";
    });
    $("#edit").click(function () {
        hideable.show();
        info.hide();
    });
    $("#cancel").click(function () {
        hideable.hide();
        info.show();
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
                    hideable.hide();
                    info.show();
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
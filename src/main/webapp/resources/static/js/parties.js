window.onload=function () {
    var response, publicParties, privateParties, current_user_ID;
    var table_private = $('#table_body_priv');
    var table_public = $('#table_body_pub');
    $.ajax('/parties', {}).then(function success(result) {

        response = result;

        publicParties = response.filter(function (party) {
            return !party["priv"];
        });
        privateParties = response.filter(function (party) {
            return party["priv"];
        });

        $.ajax('user_id', {}).then(
            function success(id) {
                current_user_ID = id;
                console.log(id);
                addInvitationCheck(publicParties, current_user_ID);
                showParties(publicParties, table_public);
                showParties(privateParties, table_private);
            },
            function fail() {
                $('.table').html("<td>Cannot fetch data from the server</td>");
            }
        );

    }, function fail() {


    });

    $("#main_page").click(function () {window.location.href="/";});
    $("#back").click(function () {
        history.go(-1);
        return false;
    });
    $('#th_name_pub').click(function () {
        if (publicParties != null) {
            publicParties.sort(compareByName);
            showParties(publicParties, table_public);
        }
    });
    $('#th_name_priv').click(function () {
        if (privateParties != null) {
            privateParties.sort(compareByName);
            showParties(privateParties, table_private);
        }
    });
    $('#th_date_pub').click(function () {
        if (publicParties != null) {
            publicParties.sort(compareByDate);
            showParties(publicParties, table_public);
        }
    });
    $('#th_date_priv').click(function () {
        if (privateParties != null) {
            privateParties.sort(compareByDate);
            showParties(privateParties, table_private)
        }
    });
    $('#th_address_pub').click(function () {
        if (publicParties != null) {
            publicParties.sort(compareByAddress());
            showParties(publicParties, table_public);
        }
    });
    $('#th_address_priv').click(function () {
        if (privateParties != null) {
            privateParties.sort(compareByAddress);
            showParties(privateParties, table_private);
        }
    });
    $('#th_invitation_pub').click(function () {
        if (publicParties != null) {
            publicParties.sort(compareByInvitation);
            showParties(publicParties, table_public);
        }
    })
};

function showParties(parties, table) {
    table.empty();
    var table_data;
    for (var i = 0; i < parties.length; i++) {
        table_data = '<tr><td><a href="/search_party/' + parties[i]["id"] + '">' + parties[i]["name"] + '</a></td><td>' + parties[i]["date"] +
            ', ' + parties[i]["time"] + '</td><td>' + parties[i]["city"] + ', ul. ' + parties[i]["address"] + '</td>';

        if (!parties[i]["priv"])
            table_data += '<td>' + parties[i].userInvited + '</td></tr>';
        else
            table_data += '</tr>';
        table.append(table_data);
    }
}


function compareByName(party1, party2) {
    return party1["name"].localeCompare(party2["name"]);
}

function compareByDate(party1, party2) {
    var result;
    if ((result = party1["date"].localeCompare(party2["date"])) === 0) {
        return party1["time"].localeCompare(party2["time"]);
    }
    else return result;
}

function compareByAddress(party1, party2) {
    var result;
    if ((result = party1["city"].localeCompare(party2["city"])) === 0) {
        return party1["address"].localeCompare(party2["address"]);
    }
    else return result;
}

function compareByInvitation(party1, party2) {
    if (!party2.userInvited || party1.userInvited)
        return -1;
    return 1;
}

function addInvitationCheck(parties, userID) {
    parties.forEach(function (party) {
        return party.userInvited = false;
    });
    for (var i = 0; i < parties.length; i++) {
        if (parties[i]["invitedUsers"].some(function (user) {
            return user["id"] == userID
        }))
            parties[i].userInvited = true;
    }
}
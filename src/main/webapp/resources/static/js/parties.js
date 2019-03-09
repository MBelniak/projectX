window.onload=function () {
    var response, publicParties, privateParties;
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
        showParties(publicParties, table_public);
        showParties(privateParties, table_private);
    }, function fail() {
        $('.table').html("<td>Cannot fetch data from the server</td>");

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
};

function showParties(parties, table) {
    table.empty();
    for (var i = 0; i < parties.length; i++) {
        table.append('<tr><td><a href="/search_party/' + parties[i]["id"] + '">' + parties[i]["name"] + '</a></td><td>' + parties[i]["date"] +
            ', ' + parties[i]["time"] + '</td><td>' + parties[i]["city"] + ', ul. ' + parties[i]["address"] + '</td></tr>');
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
window.onload=function () {
    var response;
    $.ajax('/parties', {}).then(function success(result) {
        response = result;
        showParties(response);
    }, function fail() {
        $('.table').html("<td>Cannot fetch data from the server</td>");

    });

    $("#main_page").click(function () {window.location.href="/";});
    $('#th_name').click(function () {
        if (response != null) {
            response.sort(compareByName);
            showParties(response);
        }
    });
    $('#th_date').click(function () {
        if (response != null) {
            response.sort(compareByDate);
            showParties(response);
        }
    });
    $('#th_address').click(function () {
        if (response != null) {
            response.sort(compareByAddress);
            showParties(response);
        }
    });
};

function showParties(response) {
    var table_body = $('#table_body');
    table_body.empty();
    for (var i = 0; i < response.length; i++) {
        table_body.append('<tr><td><a href="/search_party/' + response[i]["id"] + '">' + response[i]["name"] + '</a></td><td>' + response[i]["date"] +
            ', ' + response[i]["time"] + '</td><td>' + response[i]["city"] + ', ul. ' + response[i]["address"] + '</td></tr>');
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
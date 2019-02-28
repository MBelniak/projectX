window.onload=function () {

    var response;
    $.ajax('/parties', {

    }).then(function success(result) {
        response = result;
        showParties(response);
    }, function fail() {
        $('.table').html("<td>Cannot fetch data from the server</td>");

    });

    $("#main_page").click(function () {window.location.href="/";});
};

function showParties(response) {
    for(i = 0; i<response.length; i++)
    {
        $('#table_body').append('<tr><td><a href="/search_party/' + response[i]["id"] + '">' + response[i]["name"] + '</a></td><td>' + response[i]["date"] +
            '</td><td>' + response[i]["city"] + ', ul. ' + response[i]["address"] + '</td></tr>');
    }
}
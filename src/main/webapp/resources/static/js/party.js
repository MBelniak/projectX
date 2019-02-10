var response;
window.onload=function () {
    var party_id=window.location.pathname.split("/").pop();
    $.ajax({
        url: "/parties/"+party_id,
        type: "GET",
        dataType: "JSON",
        success: function (result) {
            response = result;
            showParty();
        },
        error: function () {
            alert("Ups! Something went wrong.");
        }
    })
};

function showParty() {
    $("#party_name").html(response["name"]);
    $("#description").html(response["description"]);
    $("#date").html(response["date"]);
    $("#address").html(response["city"] + ", ul. " + response["address"]);

    $("#back").click(function () {
        window.location.href = "/search_parties"
    });
    $("#main_page").click(function () {
        window.location.href = "/"
    });
}
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
            console.log("Ups! Something went wrong.");
        }
    })
};

function showParty() {
    document.getElementById("party_name").innerHTML+=response["name"];
    document.getElementById("description").innerHTML+=response["description"];
    document.getElementById("date").innerHTML+=response["date"];
    document.getElementById("address").innerHTML+=response["address"];
    addButtonListeners();
}
function addButtonListeners() {
    document.getElementById("back").onclick = function () { window.location.href = "/search_parties" };
    document.getElementById("main_page").onclick = function () { window.location.href = "/" };
}
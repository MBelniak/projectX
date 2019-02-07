var response;
window.onload=function () {
    $.ajax(
        {
            url: "/parties",
            type: "GET",
            dataType: "JSON",
            contentType: "application/json; charset=utf-8",
            success: function (result) {
                response = result;
                showParties();
            },
            error: function (error) {
                console.log("Error: "+error);
            }
        }
    )
};

function showParties() {
    for(i = 0; i<response.length; i++)
    {
         document.getElementById("table_body").innerHTML+="<tr><td>"+response[i]["name"]+"</td><td>"+response[i]["date"]+
             "</td><td>"+response[i]["city"]+", ul. "+response[i]["address"]+"</td></tr>";
    }
}
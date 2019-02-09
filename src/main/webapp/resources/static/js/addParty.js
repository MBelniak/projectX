var form, name, description, date, city, address, requestJSON;
window.onload=function()
{
    $("#send_button").click(function () {
        requestJSON = prepareJSON();
        if(requestJSON==null)
            return;
        $.ajax(
            {   url: "/parties",
                type: "POST",
                contentType: "application/json; charset=utf-8",
                data: requestJSON,
                success: function (result) {
                    console.log(JSON.stringify(result));
                    window.location.href='/party_added';
                },
                error:function (error) {
                    alert("Error: "+error);
                }
            })
    });
    var inputs = document.getElementsByClassName("input");
    for(i=0; i<inputs.length; i++)
    {
        inputs[i].addEventListener("focusin", function (){removeDanger(inputs[i]);}, false);
    }
};

function removeDanger(input) {
        if($(input).hasClass("is_danger"))
            $(input).removeClass("is-danger");
}
function prepareJSON() {
    var obj = {};
    obj.name = $("#name").val();
    obj.description  = $("#description").val();
    obj.date = $("#date").val();
    obj.city = $("#city").val();
    obj.address = $("#address").val();

    if(obj.name == "")
    {
        $("#name").addClass("is-danger");
        $("#warning").html("Please fill in all required fields.");
        return null;
    }
    if(obj.description == "")
    {
         $("#description").addClass("is-danger");
        $("#warning").html("Please fill in all required fields.");
        return null;
    }
    if(obj.date=="")
    {
        $("#date").addClass("is-danger");
        $("#warning").html("Please fill in all required fields.");
        return null;
    }
    if(obj.city=="")
    {
        $("#city").addClass("is-danger");
        $("#warning").html("Please fill in all required fields.");
        return null;
    }
    if(obj.address=="")
    {
        $("#address").addClass("is-danger");
        $("#warning").html("Please fill in all required fields.");
        return null;
    }
    $("#warning").html("");
    return JSON.stringify(obj);
}



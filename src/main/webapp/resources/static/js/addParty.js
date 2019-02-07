var form, name, description, date, city, address;
window.onload=function()
{
    $("#send_button").click(function () {
        $.ajax(
            {   url: "/parties",
                type: "POST",
                contentType: "application/json; charset=utf-8",
                data: prepareJSON(),
                success: function (result) {
                    console.log(result);
                },
                error:function (error) {
                    console.log("Error: "+error);
                }
            })
    });
};

function prepareJSON() {
    name = $("#name").val();
    description = $("#description").val();
    date = $("#date").val();
    city = $("#city").val();
    address = $("#address").val();

    form = '{'
        +'"name" : "'+name+'",'
        +'"description" : "'+description+'",'
        +'"date" : "'+date+'",'
        +'"city" : "'+city+'",'
        +'"address" : "'+address+'"}';
    return form;
}
var form, name, description, date, city, address, requestJSON;
window.onload=function()
{
    var file_input = document.querySelector("#image");
    $("#send_button").click(function () {
            var formData = new FormData();
            var files =  file_input.files;
            if(files.length!=0) {
                formData.append("file", files[0]);
                $.ajax(
                    {
                        url: "/images",
                        type: "POST",
                        enctype: 'multipart/form-data',
                        data: formData,
                        processData: false,
                        contentType: false,
                        success: function(){
                            requestJSON = prepareJSON();
                            if(requestJSON==null)
                                return;
                            ajaxPostParty();
                        },
                        error: function (error) {
                            $("#error_file").html("Cannot upload a file: "+error.value+"\nYou can try with other file or post a party without an image.");
                            file_input.value = "";
                        }
                    }
                )
            }
            else {
                requestJSON = prepareJSON();
                if(requestJSON==null)
                    return;
                ajaxPostParty();
            }

    });
    $("#back").click(function () {window.location.href="/";});
    $(".listened").focusin(function ()
    {
        if($(this).hasClass("is-danger"))
            $(this).removeClass("is-danger");
    });
};

function ajaxPostParty() {
    $.ajax(
        {   url: "/parties",
            type: "POST",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(requestJSON),
            success: function () {
                window.location.href='/party_added';
            },
            error:function (error) {
                alert("Error: "+error);
            }
        });
}
function prepareJSON() {
    var obj = {};
    obj.name = $("#name").val();
    obj.description  = $("#description").val();
    obj.date = $("#date").val();
    obj.city = $("#city").val();
    obj.address = $("#address").val();
    obj.image = $("#image").val();

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
    return obj;
}



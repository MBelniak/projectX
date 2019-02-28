window.onload=function()
{
    var file_input = document.querySelector("#image");
    var file_error = $("#error_file");
    setWarn = setWarn.bind($("#warning"));
    $("#send_button").click(function ()
    {
        var requestJSON = prepareJSON();
            if(requestJSON==null)
            return;

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
                        success: function(response){
                            if (response === "") {
                                file_error.html("Error occured when tried to upload an image. \nPlease ensure that you're uploading an image.");
                                file_input.value = "";
                                return;
                            }
                            requestJSON.imageName = response;
                            ajaxPostParty(requestJSON);
                        },
                        error: function (error) {
                            file_error.html("Cannot upload a file: " + error.value + "\nYou can try with other file or post a party without an image.");
                            file_input.value = "";
                        }
                    }
                )
            }
            else {
                requestJSON = prepareJSON();
                if(requestJSON==null)
                    return;

                requestJSON.imageName = null;
                ajaxPostParty(requestJSON);
            }

    });

    $("#back").click(function () {window.location.href="/";});
    $(".listened").focusin(function ()
    {
        if($(this).hasClass("is-danger"))
            $(this).removeClass("is-danger");
    });

};

function ajaxPostParty(requestJSON) {
    $.ajax(
        {   url: "/parties",
            type: "POST",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(requestJSON),
            success: function () {
                window.location.href='/party_added';
            },
            error:function (error) {
                alert("Cannot add party: "+error);
            }
        });
}
function prepareJSON() {
    var name = $("#name");
    var desc = $("#description");
    var date = $("#date");
    var time = $("#time");
    var city = $("#city");
    var address = $("#address");

    var obj = {};
    obj.name = name.val();
    obj.description = desc.val();
    obj.date = date.val();
    obj.time = time.val();
    obj.city = city.val();
    obj.address = address.val();
    obj.imageName="";

    if(obj.name == "")
    {
        name.addClass("is-danger");
        setWarn(true);
        return null;
    }
    if(obj.description == "")
    {
        desc.addClass("is-danger");
        setWarn(true);
        return null;
    }
    if(obj.date=="")
    {
        date.addClass("is-danger");
        setWarn(true);
        return null;
    }
    if(obj.time=="")
    {
        time.addClass("is-danger");
        setWarn();
        return null;
    }
    if(obj.city=="")
    {
        city.addClass("is-danger");
        setWarn(true);
        return null;
    }
    if(obj.address=="")
    {
        address.addClass("is-danger");
        setWarn(true);
        return null;
    }
    setWarn(false);
    return obj;
}

function setWarn(state) {
    state === true ? this.html("Please fill in all required fields.") : this.html("");
}


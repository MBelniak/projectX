var sign_up_button, requestJSON, email_input;
window.onload=function () {

    email_input = $("#email");
    sign_up_button = $("#sign_up");
    sign_up_button.attr("disabled", true);
    email_input.bind('input', function () {
        $.ajax({
            url: "/users/"+email_input.val(),
            type: "GET",
            dataType: "text",
            success: function (result) {
                if(result == "exists")
                    $("#warning").html("Sorry, this email is already in the database.");
                else {
                    console.log(result);
                    sign_up_button.attr("disabled", false);
                    $("#warning").html("");
                }
            },
            error: function () {
              console.log("Something's wrong.");
            }
        })
        }
        );

    sign_up_button.click(function () {

        requestJSON = prepareJSON();
        if(requestJSON==null)
            return;
        $.ajax(
        {
            url: "/users",
            type: "POST",
            contentType: "application/json; charset=utf-8",
            dataType: "text/plain",
            data: JSON.stringify(requestJSON),
            success: function (response) {
                if(response==null)
                    window.location.href='/login?register=ok';
                else
                {
                    $("#warning").html("Error");
                }
            },
            error:function (error) {
                alert("Cannot add user: "+JSON.stringify(error));
            }

        })
    });
    $('#back').click(function () {
        window.location.href="/";
    });
};

function prepareJSON() {
    var email = $("#email");
    var first_name = $("#first_name");
    var surname = $("#surname");
    var password = $("#password");

    var obj = {};
    obj.email = email.val();
    obj.first_name = first_name.val();
    obj.surname = surname.val();
    obj.password = password.val();
    return obj;
}



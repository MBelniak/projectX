var sign_up_button, requestJSON, email_input, warning;
window.onload=function () {

    warning = $("#warning");
    email_input = $("#email");
    sign_up_button = $("#sign_up");
    sign_up_button.attr("disabled", true);
    email_input.bind('input', function () {
            if (email_input.val() == "" || email_input.val().match(/[;?=+/\\"']+/g))
        {
            sign_up_button.attr("disabled", true);
            return;
        }
        $.ajax({
            url: "/users/"+email_input.val(),
            type: "GET",
            dataType: "text",
            success: function (result) {
                if(result == "exists") {
                    warning.html("Sorry, this email is already in the database.");
                    sign_up_button.attr("disabled", true);
                }
                else {
                    sign_up_button.attr("disabled", false);
                    warning.html("");
                }
            },
            error: function () {
                sign_up_button.attr("disabled", true);
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
            data: JSON.stringify(requestJSON),
            success: function (response) {
                if (response == "")
                    window.location.href='/login?register=ok';
                else
                {
                    warning.html("Error: ");
                    for (var i = 0; i < response.length; i++)
                        warning.append("<p>"+response[i]+"</p>");
                }
            },
            error: function (error) {
                    warning.html("Error "+ error.status+" - "+error.status);
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



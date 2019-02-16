var sign_up_button, requestJSON, email_input;
window.onload=function () {

    email_input = $("#email");
    sign_up_button = $("#sign_up");
    sign_up_button.disable();
    email_input.addEventListener('input', function () {
        $.ajax({
            url: "/users/"+email_input.val(),
            type: "GET",
            dataType: "JSON",
            success: function (result) {
                if(result!=null)
                    $("#warning").html("Sorry, this email is already in the database.")
                else
                    sign_up_button.enable();
            },
            error: function (error) {
              console.log("something's wrong.");
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
            success: function () {
                window.location.href='/login?register=ok';
            },
            error:function (error) {
                alert("Cannot add user: "+error);
            }

        })
    })
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



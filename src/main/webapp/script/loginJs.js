$(document).ready(function()
{
    $("#login").click(function(e)
    {
        e.preventDefault();
        let password= $("#password").val();
        let email= $("#email").val();
        let userModel= {password: password, email: email}
        $.ajax({
            async: false,
            method: "POST",
            crossDomain: true,
            url:"http://localhost:8090/manage-users/login",
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            /*headers: {'Access-Control-Allow-Origin' : "*"},*/
            data:JSON.stringify(userModel),

            success:function(user)
            {
                if(password.length<=0 || email.length<=0 || user.error=="wrong pwd, try again" || user.error=="User not found")
                {
                    $(".alert-error").addClass("show");
                    if(user.error!=null && user.error!="")
                    {
                        $("#message-error").text(" "+user.error);
                    }
                    $(".form-control").removeClass("form-field");
                    $(".form-control").addClass("form-field-error");
                }
                else
                {
                    $(".form-control").removeClass("form-field-error");
                    $(".form-control").addClass("form-field");
                    sessionStorage.setItem("surname", user.surname);
                    sessionStorage.setItem("roleList", JSON.stringify(user.roles));
                    sessionStorage.setItem("mainRole", user.mainRole)
                    sessionStorage.setItem("name", user.name);
                    sessionStorage.setItem("uuid", user.uuid);
                    sessionStorage.setItem("email", user.email);
                    window.location.href = 'prova.jsp';
                }
            },
            error: function(error)
            {
                alert("generic error"+ error);
            }
        });
    });

    $("#email").focusout(function(e)
    {
        e.preventDefault();
        if($("#email").val().length<=0)
        {
            $("#email").removeClass("form-field");
            $("#email").addClass("form-field-error");
        }
        else
        {
            $("#email").removeClass("form-field-error");
            $("#email").addClass("form-field");
        }
    });

    $(".btn-close").click(function(e)
    {
        e.preventDefault();
        $(".error-message").removeClass("show");
    });

});

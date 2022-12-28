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

            success:function(result)
            {
                let user=[];
                $.each(result, function (k, v)
                {
                    user.push(v);
                });

                if(password=="" || email=="" || user=="wrong pwd, try again" || user=="User not found")
                {
                    $(".alert-error").addClass("show");
                    if(user=="wrong pwd, try again" || user=="User not found")
                    {
                        $("#message-error").text(" "+user);
                    }
                }
                else
                {
                    let roles=[];
                    $.each(user[2], function (k, v)
                    {
                        roles.push(v);
                    });

                    //$("#label").append("<b>"+user[0]+"</b><br>");

                    /*for(let i in roles)
                    {
                        $("#label").append("<b>"+JSON.stringify(roles[i])+"</b>");
                    }*/
                    //$.session.set("user", user);
                    //sessionStorage.setItem("user", user);
                    sessionStorage.setItem("password", user[0]);
                    sessionStorage.setItem("surname", user[1]);
                    sessionStorage.setItem("roles", JSON.stringify(roles));
                    sessionStorage.setItem("name", user[3]);
                    sessionStorage.setItem("uuid", user[4]);
                    sessionStorage.setItem("email", user[5]);
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

    $("#password").focusout(function(e)
    {
        e.preventDefault();
        if($("#password").val().length<=0)
        {
            $("#password").removeClass("form-field");
            $("#password").addClass("form-field-error");
        }
        else
        {
            $("#password").removeClass("form-field-error");
            $("#password").addClass("form-field");
        }
    });

    $(".btn-close").click(function(e)
    {
        e.preventDefault();
        $(".error-message").removeClass("show");
    });

});

function addUser()
{

    let name=$("#nameField").val();
    let surname=$("#surnameField").val();
    let emailUser=$("#emailField").val();
    //let password=$("#passwordField").val();
    let managerIsChecked = document.getElementById("MANAGER").classList.contains("badgeChecked")
    let bartenderIsChecked = document.getElementById("BARTENDER").classList.contains("badgeChecked")
    let waiterIsChecked = document.getElementById("WAITER").classList.contains("badgeChecked")

    roles = []
    if (managerIsChecked){
        roles.push("MANAGER")
    }

    if (bartenderIsChecked){
        roles.push("BARTENDER")
    }

    if (waiterIsChecked){
        roles.push("WAITER")
    }
    if (roles.length===0){
        alert("Seleziona almeno un ruolo")
        return;
    }
    let mainRole=roles[0];

    /*if(managerIsChecked || bartenderIsChecked || waiterIsChecked) {
        if (managerIsChecked){
            roles.push("MANAGER")
            if (bartenderIsChecked){
                roles.push("BARTENDER")
            }
            if (waiterIsChecked) {
                roles.push("WAITER")
            }
        } else {
            if (bartenderIsChecked){
                roles.push("BARTENDER")
                if (waiterIsChecked){
                    roles.push("WAITER")
                }
            } else {
                if (waiterIsChecked){
                    roles.push("WAITER")
                }
            }
        }
    }*/

    if(name!=="" && surname!=="" && mainRole!=="" && emailUser!=="" /*&& password!==""*/ && !!roles && roles.length>0)
    {
        let userModel=
            {
                name: name,
                surname: surname,
                roleList: roles,
                mainRole: mainRole,
                //password: password,
                email: emailUser
            }

        $.ajax({
            async: true,
            method: "POST",
            crossDomain: true,
            url:"http://localhost:8090/manage-users/add",
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            data:JSON.stringify(userModel),

            success:function(result)
            {
                redirectInfoUser(result.uuid);
            },
            error: function(error)
            {
                alert("ERRORE")
                $(".error").html("<div class=\"alert alert-danger alert-dismissible fade show\" role=\"alert\"><strong>ERRORE! </strong>"+error.responseText+".</div>");
            }
        });
    }
}

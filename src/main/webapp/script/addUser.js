function addUser()
{
    let name=$("#nameField").val();
    let surname=$("#surnameField").val();
    let emailUser=$("#emailField").val();
    let password=$("#passwordField").val();
    let mainRole=$("#roleField").val();
    let managerIsChecked = document.getElementById("MANAGER").classList.contains("badgeChecked")
    let bartenderIsChecked = document.getElementById("BARTENDER").classList.contains("badgeChecked")
    let waiterIsChecked = document.getElementById("WAITER").classList.contains("badgeChecked")

    if(managerIsChecked || bartenderIsChecked || waiterIsChecked) {
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
    }
    let roleList= roles;
    alert(roleList)

    if(name!="" && surname!="" && mainRole!="" && emailUser!="" && password!="" && roleList!=null)
    {
        let userModel=
            {
                name: name,
                surname: surname,
                roleList: roleList,
                mainRole: mainRole,
                password: password,
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
                console.log("generic error"+ JSON.stringify(error));
            }
        });
    }
}
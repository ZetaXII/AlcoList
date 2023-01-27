function redirectInfoUser(uuid)
{
    window.location.href= $("#contextPath").val()+"/users/manager/infoUser.jsp?uuid="+uuid;
}
function redirectListaUser(){
    window.location.href = $("#contextPath").val()+"/users/manager/listaUser.jsp";
}

function getUser(uuid) {
    let u;
    $.ajax({
        async: false,
        method: "GET",
        crossDomain: true,
        url:"http://localhost:8090/manage-users/get/"+uuid,
        contentType: "application/json; charset=utf-8",
        dataType: "json",

        success:function(user)
        {
            u=user;
        },
        error: function(error)
        {
            $(".error").html("<div class=\"alert alert-danger alert-dismissible fade show\" role=\"alert\"><strong>ERRORE! </strong>"+error.responseText+".</div>");
        }
    });
    return u;
}

function getAllUsers(){
    let u=[];
    $.ajax({
        async: false,
        method: "GET",
        crossDomain: true,
        url:"http://localhost:8090/manage-users/getAll",
        contentType: "application/json; charset=utf-8",
        dataType: "json",

        success:function(user)
        {
            u=user;
        },
        error: function(error)
        {
            $(".error").html("<div class=\"alert alert-danger alert-dismissible fade show\" role=\"alert\"><strong>ERRORE! </strong>"+error.responseText+".</div>");
        }
    });
    return u;
}
function appendUser(allUsers){
    for(user in allUsers) {
        console.log("melania: " + allUsers[user])
        if(allUsers[user].dateDelete == null && allUsers[user].email!=email){
            let tagRoles = "";
            for (ruolo in allUsers[user].roles) {
                tagRoles = tagRoles + "<span class=\"role my-4 user-tag\">" + allUsers[user].roles[ruolo].name + "</span>\n"
            }
            let userCard = "<div class=\"card mb-4\" onclick='redirectInfoUser(\""+allUsers[user].uuid+"\")' id=\"userRow\" style=\"background-color: var(--secondaryBlue); border-radius: 30px;\">\n" +
                "            <div class=\"row g-0 user-row\" style=\"background-color: var(--secondaryBlue); border-radius: 30px;\">\n" +
                "                <div class=\"col-md-3\">\n" +
                "                    <img class=\"m-2 p-2\" style=\"vertical-align: central; width:100px; border-radius: 50%\" src=\"https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_1280.png\" alt=\"profile-pic\">\n" +
                "                </div>\n" +
                "                <div class=\"col-md-7\">\n" +
                "                    <div class=\"card-body mt-2 mb-3\">\n" +
                "                        <h5 class=\"card-title profile-title\">" + allUsers[user].name + " " +allUsers[user].surname + "</h5>\n" + tagRoles +
                "                    </div>\n" +
                "                </div>\n" +
                "                <div class=\"col-md-2\">\n" +
                "                    <a class=\"forward-button\" onclick='redirectInfoUser(\""+allUsers[user].uuid+"\")'>\n" +
                "                        <svg xmlns=\"http://www.w3.org/2000/svg\" width=\"16\" height=\"16\" fill=\"currentColor\" class=\"bi bi-chevron-right\" viewBox=\"0 0 16 16\">\n" +
                "                            <path fill-rule=\"evenodd\" d=\"M4.646 1.646a.5.5 0 0 1 .708 0l6 6a.5.5 0 0 1 0 .708l-6 6a.5.5 0 0 1-.708-.708L10.293 8 4.646 2.354a.5.5 0 0 1 0-.708z\"/>\n" +
                "                        </svg>\n" +
                "                    </a>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "\n" +
                "        </div>"
            $(".append-user").append(userCard);
        }
    }
}

function infoModifyUser() //inserisce nei rispettivi campi le varie info del cocktail da modificare
{
    /*prende la query di ricerca e vede se ci sono delle variabili di GET*/
    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);
    /*recupera il valore della variabile uuid se esiste*/
    let userUuid = urlParams.get('uuid');
    let user= getUser(userUuid);

    $("#nameField").val(user.name);
    $("#surnameField").val(user.surname);
    $('#flavourield').append("<option value="+cocktail.flavour+" selected>"+cocktail.flavour+"</option>");
    $(".checkIBA").prop("checked", cocktail.iba);
    $("#descriptionField").val(cocktail.description);
    $("#priceField").val(parseFloat(cocktail.price));
    $("#inMenu").val(cocktail.inMenu);
    $("#isAlcoholic").val(cocktail.alcoholic);
    $("#uuid").val(cocktail.uuid);

    document.getElementById("user-img").src=cocktail.pathFileImg;
}
function modifyUser(uuidUser){
    let name=$("#nameField").val();
    let surname=$("#surnameField").val();
    let emailUser=$("#emailField").val();
    let password=$("#passwordField").val();
    let uuid = uuidUser
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
        $(".error").html("<div class=\"alert alert-danger alert-dismissible fade show\" role=\"alert\"><strong>ERRORE! </strong> Seleziona almeno un ruolo.</div>");
        return;
    }
    let mainRole=roles[0];

    //alert("nome: "+name+" cognome: "+surname+" email: "+emailUser+" password: "+password+" roles: "+roles+" mainrole: "+mainRole)

    if(name!=="" && surname!=="" && mainRole!=="" && emailUser!=="" && password!=="" && !!roles && roles.length>0 && uuid!=="")
    {
        let userModel=
            {
                name: name,
                surname: surname,
                roleList: roles,
                mainRole: mainRole,
                password: password,
                email: emailUser,
                uuid: uuid
            }

        $.ajax({
            async: true,
            method: "POST",
            crossDomain: true,
            url:"http://localhost:8090/manage-users/update",
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            data:JSON.stringify(userModel),

            success:function(result)
            {
                redirectInfoUser(result.uuid);
            },
            error: function(error)
            {
                //alert("ERRORE")
                $(".error").html("<div class=\"alert alert-danger alert-dismissible fade show\" role=\"alert\"><strong>ERRORE! </strong>"+error.responseText+".</div>");
                //window.location.href = $("#contextPath").val()+"/users/manager/listaUser.jsp";
            }
        });
    }
}

function modifyUser_2()
{
    let name=$("#nameField").val();
    let surname=$("#surnameField").val();
    let mainRole=$("#roleField").val();
    let emailUser=$("#emailField").val();
    let password=$("#passwordField").val();
    let uuid=$("#uuid").val();

    if(name!="" && surname!="" && mainRole!="" && emailUser!="" && password!="" && uuid!="")
    {
        let userModel=
            {
                name: name,
                surname: surname,
                roleList: mainRole,
                mainRole: mainRole,
                password: password,
                email: emailUser
            }

        $.ajax({
            async: true,
            method: "POST",
            crossDomain: true,
            url:"http://localhost:8090/manage-users/update",
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            data:JSON.stringify(userModel),

            success:function(result)
            {
                redirectInfoUser(result.uuid);
            },
            error: function(error)
            {
                $(".error").html("<div class=\"alert alert-danger alert-dismissible fade show\" role=\"alert\"><strong>ERRORE! </strong>"+error.responseText+".</div>");
            }
        });
    }
}

function deleteUser(uuid)
{
    $.ajax({
        async: false,
        method: "DELETE",
        crossDomain: true,
        url:"http://localhost:8090/manage-users/delete?uuid="+uuid,
        contentType: "application/json; charset=utf-8",
        dataType: "json",

        success:function()
        {
            alert("L'utente è stato eliminato correttamente")
            redirectListaUser()
        },
        error: function(error)
        {
            $(".error").html("<div class=\"alert alert-danger alert-dismissible fade show\" role=\"alert\"><strong>ERRORE! </strong>"+error.responseText+".</div>");
        }
    });
}

function confirmDelete(uuid)
{
    if (confirm("Sei sicuro di voler eliminare l'utente?"))
    {
        deleteUser(uuid);
        window.location.reload();
    }
}

function deleteSelf(uuid)
{
    $.ajax({
        async: false,
        method: "DELETE",
        crossDomain: true,
        url:"http://localhost:8090/manage-users/delete?uuid="+uuid,
        contentType: "application/json; charset=utf-8",
        dataType: "json",

        success:function()
        {
            alert("L'utente è stato eliminato correttamente")
            logout()
        },
        error: function(error)
        {
            $(".error").html("<div class=\"alert alert-danger alert-dismissible fade show\" role=\"alert\"><strong>ERRORE! </strong>"+error.responseText+".</div>");
        }
    });
}
function confirmDeleteSelf(uuid)
{
    if (confirm("Sei sicuro di voler eliminare l'utente?"))
    {
        deleteSelf(uuid);
        window.location.reload();
    }
}

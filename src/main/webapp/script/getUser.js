var userRole;
function redirectInfoUser(uuid)
{
    //window.location.href= $("#contextPath").val()+"/users/infoUser.jsp?uuid="+uuid;
    window.location.href= $("#contextPath").val()+"/users/profile.jsp";
}
function getUser(uuid)
{
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
            alert("generic error"+ error);
        }
    });
    return u;
}

function infoUser()
{
    /*prende la query di ricerca e vede se ci sono delle variabili di GET*/
    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);
    /*recupera il valore della variabile uuid se esiste*/
    let userUuid = urlParams.get('uuid');
    let user= getUser(userUuid);

    $(".user-name").append(user.name);
    //$(".user-email").append(user.email);

    if(user.role==="MANAGER")
    {
        $(".user-role").append("MANAGER");
    }
    else if(user.role==="BARTENDER")
    {
        $(".user.role").append("BARTENDER");
    }
    else if(user.role==="WAITER")
    {
        $(".user.role").append("WAITER");
    }
}

function getUsersPaginated(size, page)
{
    let userPaginationAttributes= {size: size, page: page};
    let u=[];

    $.ajax({
        async: false,
        method: "POST",
        crossDomain: true,
        url:"http://localhost:8090/manage-users/searchByFields",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        data:JSON.stringify(userPaginationAttributes),

        success:function(result)
        {
            let usersArray= result.user;
            u=usersArray;
        },
        error: function(error)
        {
            console.log("generic error"+ JSON.stringify(error));
        }
    });
    return u;
}

/*ritorna un array di cocktail paginati filtrati per i vari campi*/
function getUsersPaginatedByFilters(size, page)
{
    let searchUserName= $("#nameField").val();
    let searchUserRole= $("#roleField").val();

    if(searchUserRole=="MANAGER")
    {
        searchAlcoholic=true;
    }
    else if(searchUserRole=="BARTENDER")
    {
        searchAlcoholic=false;
    }
    else if(searchUserName=="WAITER")
    {

    }

    let paginationAttributes= {size: size, page: page, name: searchName, flavour: searchFlavour, isAlcoholic: searchAlcoholic};
    let c=[];

    $.ajax({
        async: false,
        method: "POST",
        crossDomain: true,
        url:"http://localhost:8090/manage-users/searchByFields",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        data:JSON.stringify(paginationAttributes),

        success:function(result)
        {
            let cocktailsArray= result.cocktail;
            c=cocktailsArray;
        },
        error: function(error)
        {
            console.log("generic error"+ JSON.stringify(error));
        }
    });
    return c;
}

/*stampa a video la lista paginata dei cocktail*/
function paginatedCocktailList(size, page)
{
    let cocktailsArray= getCocktailsPaginated(size, page);
    if(cocktailsArray.length<=0)
    {
        window.location.href = $("#contextPath").val()+'/users/listaCocktail.jsp?page=0';
    }

    if(page==0)
    {
        /*se si ci trova alla pagina 0 allora NON viene mostrato il tasto per andare alla pagina precedente*/
        $(".prevPage").toggle("hidden");
    }
    let isAlcoholic;
    let isIBA;
    let inMenu;

    if(getCocktailsPaginated(size, page+1).length<1)
    {
        /*se nella pagina successiva non ci sono cocktail  allora non mostra il tasto per andare alla pagina successiva*/
        $(".nextPage").toggle("hidden");
    }

    $(".pageNumber").append("Pagina "+(page+1));

    for(i in cocktailsArray)
    {
        if(cocktailsArray[i].iba==false)
        {
            isIBA="no iba";
        }
        else
        {
            isIBA="iba";
        }

        if(cocktailsArray[i].alcoholic==false)
        {
            isAlcoholic="Analcolico";
        }
        else
        {
            isAlcoholic="Alcolico";
        }

        if(cocktailsArray[i].inMenu==false)
        {
            inMenu="no men&ugrave;";
        }
        else
        {
            inMenu="men&ugrave;";
        }

        let cocktailCard="<div class='col-sm-12 col-xl-6'><div class='card info-cocktail-panel mt-4' style='background-color: var(--secondaryBlue); border-radius: 30px;'><div class='row g-0' style='background-color: var(--secondaryBlue); border-radius: 30px;'><div class='col-md-2 text-center'><img src='"+cocktailsArray[i].pathFileImg+"' class='img p-3' id='cocktail-img' width='120px' height='140px'></div><div class='col-md-10 mt-1'><div class='card-body'><h5 class='card-title cocktail-name d-flex flex-row'>"+cocktailsArray[i].name+"</h5><span class='price text-end py-3'>"+getCorrectPrice(cocktailsArray[i].price)+"&euro;</span><div class='cocktail-tags mt-2'><span class='badge cocktail-flavour'>"+cocktailsArray[i].flavour+"</span><span class='badge cocktail-isIBA'>"+isIBA+"</span><span class='badge cocktail-isAlcoholic'>"+isAlcoholic+"</span><span class='badge cocktail-inMenu'>"+inMenu+"</span></div></div></div><div class='d-flex justify-content-center cocktail-options pb-2 px-4'>   <a href="+$("#contextPath").val()+"/users/bartender/modificaCocktail.jsp?uuid="+cocktailsArray[i].uuid+"&page="+page+"' class='btn btn-modify px-2 m-2 mb-3 w-50'><i class='bx bxs-pencil modify-icon'></i></a><a onclick='confirmDelete(\""+cocktailsArray[i].uuid+"\")' class='btn btn-erase px-2 m-2 mb-3 w-50'><i class='bx bxs-trash erase-icon'></i></a><a href="+$("#contextPath").val()+"/users/infoCocktail.jsp?uuid="+cocktailsArray[i].uuid+"&page="+page+"' class='btn btn-view px-2 m-2 mb-3 w-50'><i class='bx bxs-info-circle view-icon'></i></a></div></div></div></div>";
        $(".cocktail-list").append(cocktailCard);
    }
}

/*stampa a video la lista paginata dei cocktail filtrati*/
function paginatedCocktailListByFilter(size, page)
{
    let cocktailsArray= getCocktailsPaginatedByFilters(size, page);
    if(cocktailsArray.length<=0)
    {
        window.location.href = $("#contextPath").val()+'/users/listaCocktail.jsp?page=0';
    }

    if(page==0)
    {
        /*se si ci trova alla pagina 0 allora NON viene mostrato il tasto per andare alla pagina precedente*/
        $(".prevPage").toggle("hidden");
    }
    let isAlcoholic;
    let isIBA;
    let inMenu;

    if(getCocktailsPaginatedByFilters(size, page+1).length<1)
    {
        /*se nella pagina successiva non ci sono cocktail  allora non mostra il tasto per andare alla pagina successiva*/
        $(".nextPage").toggle("hidden");
    }

    $(".pageNumber").append("Pagina "+(page+1));

    for(i in cocktailsArray)
    {
        if(cocktailsArray[i].iba==false)
        {
            isIBA="no iba";
        }
        else
        {
            isIBA="iba";
        }

        if(cocktailsArray[i].alcoholic==false)
        {
            isAlcoholic="Analcolico";
        }
        else
        {
            isAlcoholic="Alcolico";
        }

        if(cocktailsArray[i].inMenu==false)
        {
            inMenu="no men&ugrave;";
        }
        else
        {
            inMenu="men&ugrave;";
        }

        let cocktailCard="<div class='col-sm-12 col-xl-6'><div class='card info-cocktail-panel mt-4' style='background-color: var(--secondaryBlue); border-radius: 30px;'><div class='row g-0' style='background-color: var(--secondaryBlue); border-radius: 30px;'><div class='col-md-2 text-center'><img src='"+cocktailsArray[i].pathFileImg+"' class='img p-3' id='cocktail-img' width='120px' height='140px'></div><div class='col-md-10 mt-1'><div class='card-body'><h5 class='card-title cocktail-name d-flex flex-row'>"+cocktailsArray[i].name+"</h5><span class='price text-end py-3'>"+getCorrectPrice(cocktailsArray[i].price)+"&euro;</span><div class='cocktail-tags mt-2'><span class='badge cocktail-flavour'>"+cocktailsArray[i].flavour+"</span><span class='badge cocktail-isIBA'>"+isIBA+"</span><span class='badge cocktail-isAlcoholic'>"+isAlcoholic+"</span><span class='badge cocktail-inMenu'>"+inMenu+"</span></div></div></div><div class='d-flex justify-content-center cocktail-options pb-2 px-4'>   <a href="+$("#contextPath").val()+"/users/bartender/modificaCocktail.jsp?uuid="+cocktailsArray[i].uuid+"&page="+page+"' class='btn btn-modify px-2 m-2 mb-3 w-50'><i class='bx bxs-pencil modify-icon'></i></a><a onclick='confirmDelete(\""+cocktailsArray[i].uuid+"\")' class='btn btn-erase px-2 m-2 mb-3 w-50'><i class='bx bxs-trash erase-icon'></i></a><a href="+$("#contextPath").val()+"/users/infoCocktail.jsp?uuid="+cocktailsArray[i].uuid+"&page="+page+"' class='btn btn-view px-2 m-2 mb-3 w-50'><i class='bx bxs-info-circle view-icon'></i></a></div></div></div></div>";
        $(".cocktail-list").append(cocktailCard);
    }
}

function nextPageCocktail()
{
    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);
    /*recupera il valore della variabile page se esiste*/
    let page = parseInt(urlParams.get('page'));
    page= page+1;
    window.location.href = $("#contextPath").val()+'/users/listaCocktail.jsp?page='+page;
}

function previousPageCocktail()
{
    /*prende la query di ricerca e vede se ci sono delle variabili di GET*/
    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);
    /*recupera il valore della variabile page se esiste*/
    let page = parseInt(urlParams.get('page'));
    if(page>0 && page!=null)
    {
        page=page-1;
    }
    else
    {
        page=0;
    }
    window.location.href = $("#contextPath").val()+'/users/listaCocktail.jsp?page='+page;
}

function addUser()
{
    let name=$("#nameField").val();
    let surname=$("#surnameField").val();
    let emailUser=$("#emailField").val();
    let password=$("#passwordField").val();
    let mainRole=$("#roleField").val();
    let roleList= [mainRole];

    if(name!="" && surname!="" && mainRole!="" && emailUser!="" && password!="")
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
        alert(JSON.stringify(userModel));

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
                console.log("POST AVVENUTO")
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

function modifyUser()
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
                console.log("generic error"+ JSON.stringify(error));
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

        success:function(cocktail)
        {
            alert("L'utente è stato eliminato correttamente")
        },
        error: function(error)
        {
            console.log("generic error"+ JSON.stringify(error));
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

//rendirizza
function redirectInfoCocktail(uuid)
{
    window.location.href= $("#contextPath").val()+"/users/infoCocktail.jsp?uuid="+uuid+"&page=0";
}

function getCorrectPrice(price) //funzione per visualizzare in modo corretto i prezzi dei cocktail (es al posto di visualizzare 1.5€ verrà visualizzato 1.50€
{
    price=price.toString(); //converte il dato in stringa prima di analizzarlo
    if(price.includes("."))
    {
        let splittedPrice= price.split(".");
        if(splittedPrice[1].length==1)
        {
            price=splittedPrice[0]+"."+splittedPrice[1]+"0";
        }
    }
    else
    {
        price=price+".00";
    }
    return price;
}

function printFlavoursList()
{
    let f=getFlavours();

    for(let i=0; i<f.length; i++)
    {
        $('#flavourField').append("<option value="+f[i]+" id="+i+">"+f[i]+"</option>");
    }
}

function getCocktail(uuid)
{
    let c;
    $.ajax({
        async: false,
        method: "GET",
        crossDomain: true,
        url:"http://localhost:8090/manage-cocktails/get/"+uuid,
        contentType: "application/json; charset=utf-8",
        dataType: "json",

        success:function(cocktail)
        {
            c=cocktail;
        },
        error: function(error)
        {
            console.log("generic error"+ JSON.stringify(error));
        }
    });
    return c;
}

function infoCocktail()
{
    /*prende la query di ricerca e vede se ci sono delle variabili di GET*/
    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);
    /*recupera il valore della variabile uuid se esiste*/
    let cocktailUuid = urlParams.get('uuid');
    let cocktail= getCocktail(cocktailUuid);

    $(".cocktail-name").append(cocktail.name);
    $(".cocktail-description").append(cocktail.description);
    $(".price").append(getCorrectPrice(cocktail.price)+"&euro;");
    $(".cocktail-flavour").append(cocktail.flavour);
    document.getElementById("cocktail-img").src=cocktail.pathFileImg;

    if(cocktail.iba==true)
    {
        $(".cocktail-isIBA").append("IBA");
    }
    else
    {
        $(".cocktail-isIBA").append("NO IBA");
    }

    if(cocktail.alcoholic==true)
    {
        $(".cocktail-isAlcoholic").append("Alcolico");
    }
    else
    {
        $(".cocktail-isAlcoholic").append("Analcolico");
    }

    if(cocktail.inMenu==true)
    {
        $(".cocktail-inMenu").append("Men&ugrave;");
    }
    else
    {
        $(".cocktail-inMenu").append("no Men&ugrave;");
    }

}

/*ritorna un array di cocktail paginati*/
function getCocktailsPaginated(size, page)
{
    let paginationAttributes= {size: size, page: page};
    let c=[];

    $.ajax({
        async: false,
        method: "POST",
        crossDomain: true,
        url:"http://localhost:8090/manage-cocktails/searchByFields",
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

/*ritorna un array di cocktail paginati filtrati per i vari campi*/
function getCocktailsPaginatedByFilters(size, page)
{
    let searchName= $("#nameField").val();
    let searchFlavour= $("#flavourField").val();
    let searchAlcoholic= $("#isAlcoholic").val();

    if(searchAlcoholic=="alcolico")
    {
        searchAlcoholic=true;
    }
    else
    {
        searchAlcoholic=false;
    }

    let paginationAttributes= {size: size, page: page, name: searchName, flavour: searchFlavour, isAlcoholic: searchAlcoholic};
    let c=[];

    $.ajax({
        async: false,
        method: "POST",
        crossDomain: true,
        url:"http://localhost:8090/manage-cocktails/searchByFields",
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
        window.location.href = $("#contextPath").val()+'/users/listaCocktail.jsp?page='+(page-1);
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
        window.location.href = $("#contextPath").val()+'/users/listaCocktail.jsp?page='+(page-1);
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
    /*prende la query di ricerca e vede se ci sono delle variabili di GET*/
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

function addCocktail()
{
    let title=$("#titleField").val();
    let pathFileImg=$("#pathFileImgField").val()
    let flavour=$("#flavourField").val();
    let isIba=$("#ibaField").is(":checked"); //restituisce true se checkato
    let description=$("#descriptionField").val();
    let price=($("#priceField").val());
    let inMenu=$("#inMenuField").is(":checked"); //restituisce true se checkato

    if(title!="" && pathFileImg!="" && flavour!="" && description!="" && (price!="" && price>0))
    {
        let cocktailModel=
            {
                name: title,
                price: price,
                description: description,
                flavour: flavour,
                pathFileImg: pathFileImg,
                inMenu: inMenu,
                isAlcoholic: "false",
                isIBA: isIba
            }

        $.ajax({
            async: true,
            method: "POST",
            crossDomain: true,
            url:"http://localhost:8090/manage-cocktails/add",
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            data:JSON.stringify(cocktailModel),

            success:function(result)
            {
                redirectInfoCocktail(result.uuid);
            },
            error: function(error)
            {
                console.log("generic error"+ JSON.stringify(error));
            }
        });
    }
}

function infoModifyCocktail() //inserisce nei rispettivi campi le varie info del cocktail da modificare
{
    /*prende la query di ricerca e vede se ci sono delle variabili di GET*/
    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);
    /*recupera il valore della variabile uuid se esiste*/
    let cocktailUuid = urlParams.get('uuid');
    let cocktail= getCocktail(cocktailUuid);

    $("#titleField").val(cocktail.name);
    $("#pathFileImgField").val(cocktail.pathFileImg);
    $('#flavourField').append("<option value="+cocktail.flavour+" selected>"+cocktail.flavour+"</option>");
    $(".checkIBA").prop("checked", cocktail.iba);
    $("#descriptionField").val(cocktail.description);
    $("#priceField").val(parseFloat(cocktail.price));
    $("#inMenu").val(cocktail.inMenu);
    $("#isAlcoholic").val(cocktail.alcoholic);
    $("#uuid").val(cocktail.uuid);

    document.getElementById("cocktail-img").src=cocktail.pathFileImg;
}

function modifyCocktail()
{
    let title=$("#titleField").val();
    let pathFileImg=$("#pathFileImgField").val()
    let flavour=$("#flavourField").val();
    let isIba=$("#ibaField").is(":checked"); //restituisce true se checkato
    let description=$("#descriptionField").val();
    let price=parseFloat($("#priceField").val());
    let inMenu=$("#inMenuField").is(":checked"); //restituisce true se checkato
    let isAlcoholic=$("#isAlcoholic").val();
    let uuid=$("#uuid").val();

    if(title!="" && pathFileImg!="" && flavour!="" && description!="" && (price!="" && price>0) && uuid!="")
    {
        let cocktailModel=
            {
                name: title,
                price: price,
                description: description,
                flavour: flavour,
                pathFileImg: pathFileImg,
                uuid: uuid,
                inMenu: inMenu,
                isAlcoholic: isAlcoholic,
                isIBA: isIba
            }

            $.ajax({
                async: true,
                method: "POST",
                crossDomain: true,
                url:"http://localhost:8090/manage-cocktails/update",
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                data:JSON.stringify(cocktailModel),

                success:function(result)
                {
                    redirectInfoCocktail(result.uuid);
                },
                error: function(error)
                {
                    console.log("generic error"+ JSON.stringify(error));
                }
        });
    }
}

function deleteCocktail(uuid)
{
    $.ajax({
        async: false,
        method: "DELETE",
        crossDomain: true,
        url:"http://localhost:8090/manage-cocktails/delete?uuid="+uuid,
        contentType: "application/json; charset=utf-8",
        dataType: "json",

        success:function(cocktail)
        {
            alert(cocktail.name+" è stato eliminato correttamente")
        },
        error: function(error)
        {
            console.log("generic error"+ JSON.stringify(error));
        }
    });
}

function confirmDelete(uuid)
{
    if (confirm("Sei sicuro di voler eliminare il cocktail?"))
    {
        deleteCocktail(uuid);
        window.location.reload();
    }
}



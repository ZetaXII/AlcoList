function redirectInfoCocktail(uuid) //funzione che rendirizza alle info del cocktail una volta apportata una modifica/aggiunta di uno di esso
{
    window.location.href= $("#contextPath").val()+"/users/infoCocktail.jsp?uuid="+uuid+"&page=0&searchName=&searchFlavour=&searchIsAlcoholic=";
}

function getCorrectPrice(price) //funzione per visualizzare in modo corretto i prezzi dei cocktail (es al posto di visualizzare 1.5€ verrà visualizzato 1.50€
{
    price=price.toString(); //converte il dato in stringa prima di analizzarlo
    if(price.includes("."))
    {
        let splittedPrice= price.split(".");
        if(splittedPrice[1].length===1)
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
        $('.selectFlavours').append("<option value="+f[i]+" id="+i+">"+f[i]+"</option>");
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
            $(".error").html("<div class=\"alert alert-danger alert-dismissible fade show\" role=\"alert\"><strong>ERRORE! </strong>"+error.responseText+".</div>");
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
    let ingredients=cocktail.ingredients;
    let optional="";

    $(".item-name").append(cocktail.name);
    $(".cocktail-description").append(cocktail.description);
    $(".price").append(getCorrectPrice(cocktail.price)+"&euro;");
    $(".cocktail-flavour").append(cocktail.flavour);
    document.getElementById("item-img").src=cocktail.pathFileImg;

    if(ingredients<=0)
    {
        $(".ingredient-list").append("<span>nessun ingrediente presente</span>");
    }
    else
    {
        for(i in ingredients)
        {
            let ingredientQuantity=ingredients[i].quantity;
            if(ingredientQuantity=="" || ingredientQuantity==" " || ingredientQuantity==null)
            {
                ingredientQuantity="q.b.";
            }
            else
            {
                ingredientQuantity=ingredientQuantity+" ml.";
            }

            if(ingredients[i].optional==true)
            {
                optional="(facoltativo)";
            }
            else
            {
                optional="";
            }

            let ingredientName=" "+ingredients[i].product.category+" "+ingredients[i].product.name;
            $(".ingredient-list").append("<li><span class='ingredient-qt'>"+ingredientQuantity+"</span>"+ingredientName.toLowerCase()+" "+optional+"</li>");
        }
    }

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

function backToListCocktail()
{
    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);
    let page = parseInt(urlParams.get('page'));
    let searchName= urlParams.get('searchName');
    let searchFlavour= urlParams.get('searchFlavour');
    let searchIsAlcoholic= urlParams.get('searchIsAlcoholic');

    let src=$("#contextPath").val()+"/users/listaCocktail.jsp?page="+page+"&searchName="+searchName+"&searchFlavour="+searchFlavour+"&searchIsAlcoholic="+searchIsAlcoholic;
    src=src.replace('"', '');
    src=src.replace('\'', '');//elimina possibili residui di virgolette varie e sistema il link

    window.location.href= src;
}

function getCocktailsPaginated(size, page, searchName, searchFlavour, searchIsAlcoholic) //ritorna un array di cocktail paginati e filtrati per i vari campi (se nulli restituisce tutti i cocktails)
{
    let paginationAttributes= {size: size, page: page, name: searchName, flavour: searchFlavour, isAlcoholic: searchIsAlcoholic};
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
            $(".error").html("<div class=\"alert alert-danger alert-dismissible fade show\" role=\"alert\"><strong>ERRORE! </strong>"+error.responseText+".</div>");
        }
    });
    return c;
}

function paginatedCocktailList(size, page, searchName, searchFlavour, searchIsAlcoholic) //stampa a video la lista paginata dei cocktail
{
    let alcolicBoolean;
    let isAlcoholic;
    let isIBA;
    let inMenu;

    if(searchIsAlcoholic=="alcolico")
    {
        alcolicBoolean=true;
    }
    else if(searchIsAlcoholic=="analcolico")
    {
        alcolicBoolean=false;
    }
    else
    {
        alcolicBoolean=null;
    }

    let cocktailsArray= getCocktailsPaginated(size, page, searchName, searchFlavour, alcolicBoolean);
    if(cocktailsArray.length<=0)
    {
        $(".pageSwitch").toggle("hidden");
        //alert("La ricerca non ha prodotto risultati");
    }

    if(page==0)
    {
        /*se si ci trova alla pagina 0 allora NON viene mostrato il tasto per andare alla pagina precedente*/
        $(".prevPage").toggle("hidden");
    }

    if(getCocktailsPaginated(size, page+1, searchName, searchFlavour, alcolicBoolean).length<1)
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

        let cocktailCard=
            "<div class='col-sm-12 col-xl-6'>" +
            "   <div class='card info-item-panel mt-4' style='background-color: var(--secondaryBlue); border-radius: 30px;'>" +
            "       <div class='row g-0' style='background-color: var(--secondaryBlue); border-radius: 30px;'><div class='col-md-2 text-center'>" +
        "               <img src='"+cocktailsArray[i].pathFileImg+"' class='img p-3' id='item-img' width='120px' height='140px'>" +
    "               </div>" +
"                   <div class='col-md-10 mt-1'>" +
"                       <div class='card-body'>" +
"                           <h5 class='card-title item-name d-flex flex-row'>"+cocktailsArray[i].name+"</h5>" +
"                           <span class='price text-end py-3'>"+getCorrectPrice(cocktailsArray[i].price)+"&euro;</span>" +
"                           <div class='item-tags mt-2'>" +
"                               <span class='badge cocktail-flavour'>"+cocktailsArray[i].flavour+"</span>" +
"                               <span class='badge cocktail-isIBA'>"+isIBA+"</span>" +
"                               <span class='badge cocktail-isAlcoholic'>"+isAlcoholic+"</span>" +
"                               <span class='badge cocktail-inMenu'>"+inMenu+"</span>" +
"                           </div>" +
"                       </div>" +
"                   </div>" +
            "       <div class='d-flex justify-content-center item-options pb-2 px-4'>   " +
            "           <a href='"+$("#contextPath").val()+"/users/bartender/modificaCocktail.jsp?uuid="+cocktailsArray[i].uuid+"&page="+page+"&searchName="+searchName+"&searchFlavour="+searchFlavour+"&searchIsAlcoholic="+searchIsAlcoholic+"' class='btn btn-modify px-2 m-2 mb-3 w-50'><i class='bx bxs-pencil modify-icon'></i></a>" +
            "           <a onclick='confirmDelete(\""+cocktailsArray[i].uuid+"\")' class='btn btn-erase px-2 m-2 mb-3 w-50'><i class='bx bxs-trash erase-icon'></i></a>" +
            "           <a href='"+$("#contextPath").val()+"/users/infoCocktail.jsp?uuid="+cocktailsArray[i].uuid+"&page="+page+"&searchName="+searchName+"&searchFlavour="+searchFlavour+"&searchIsAlcoholic="+searchIsAlcoholic+"' class='btn btn-view px-2 m-2 mb-3 w-50'><i class='bx bxs-info-circle view-icon'></i></a>" +
            "       </div></div></div></div>";

        $(".item-list").append(cocktailCard);
    }
}

function setCocktailSearchFilters()
{
    let searchName= $("#searchNameField").val();
    let searchFlavour= $("#searchFlavourField").val();
    let searchIsAlcoholic= $("#searchIsAlcoholic").val();
    window.location.href= $("#contextPath").val()+"/users/listaCocktail.jsp?page=0&searchName="+searchName+"&searchFlavour="+searchFlavour+"&searchIsAlcoholic="+searchIsAlcoholic;
}

function nextPageCocktail()
{
    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);
    let page = parseInt(urlParams.get('page'));
    let searchName= urlParams.get('searchName');
    let searchFlavour= urlParams.get('searchFlavour');
    let searchIsAlcoholic= urlParams.get('searchIsAlcoholic');
    page= page+1;

    let src=$("#contextPath").val()+"/users/listaCocktail.jsp?page="+page+"&searchName="+searchName+"&searchFlavour="+searchFlavour+"&searchIsAlcoholic="+searchIsAlcoholic;
    src=src.replace('"', '');
    src=src.replace('\'', '');//elimina possibili residui di virgolette varie e sistema il link

    window.location.href= src;
}

function previousPageCocktail()
{
    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);
    let page = parseInt(urlParams.get('page'));
    let searchName= urlParams.get('searchName');
    let searchFlavour= urlParams.get('searchFlavour');
    let searchIsAlcoholic= urlParams.get('searchIsAlcoholic');
    if(page>0 && page!=null)
    {
        page=page-1;
    }
    else
    {
        page=0;
    }
    let src=$("#contextPath").val()+"/users/listaCocktail.jsp?page="+page+"&searchName="+searchName+"&searchFlavour="+searchFlavour+"&searchIsAlcoholic="+searchIsAlcoholic;
    src=src.replace('"', '');
    src=src.replace('\'', '');//elimina possibili residui di virgolette varie e sistema il link

    window.location.href= src;
}

function redirectToAddIngredients(uuid)
{
    window.location.href= $("#contextPath").val()+"/users/bartender/aggiungiIngredientiCocktail.jsp?uuid="+uuid;
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
    $("#inMenuField").prop("checked", cocktail.inMenu);
    $("#isAlcoholic").val(cocktail.alcoholic);
    $("#uuid").val(cocktail.uuid);

    document.getElementById("item-img").src=cocktail.pathFileImg;
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
                    window.location.href= $("#contextPath").val()+"/users/bartender/aggiungiIngredientiCocktail.jsp?uuid="+result.uuid;
                },
                error: function(error)
                {
                    $(".error").html("<div class=\"alert alert-danger alert-dismissible fade show\" role=\"alert\"><strong>ERRORE! </strong>"+error.responseText+".</div>");
                }
        });
    }
}

function addCocktail()
{
    let title=$("#titleField").val();
    let pathFileImg=$("#pathFileImgField").val();
    let flavour=$("#flavourField").val();
    let isIba=$("#ibaField").is(":checked"); //restituisce true se checkato
    let description=$("#descriptionField").val();
    let price=($("#priceField").val());
    let inMenu=$("#inMenuField").is(":checked"); //restituisce true se checkato

    /*if(title!="" && pathFileImg!="" && flavour!="" && description!="" && (price!="" && price>0))
    {*/
        let cocktailModel=
            {
                name: title,
                price: price,
                description: description,
                flavour: flavour,
                pathFileImg: pathFileImg,
                inMenu: inMenu,
                isAlcoholic: false,
                isIBA: isIba
            }

        //alert(JSON.stringify(cocktailModel));

        $.ajax({
            async: false,
            method: "POST",
            crossDomain: true,
            url:"http://localhost:8090/manage-cocktails/add",
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            data:JSON.stringify(cocktailModel),

            success:function(result)
            {
                redirectToAddIngredients(result.uuid);
            },
            error: function(error)
            {
                //alert(error.responseText);
                $(".error").html("<div class=\"alert alert-danger alert-dismissible fade show\" role=\"alert\"><strong>ERRORE! </strong>"+error.responseText+".</div>");
            }
        });
    /*}*/
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
            $(".error").html("<div class=\"alert alert-danger alert-dismissible fade show\" role=\"alert\"><strong>ERRORE! </strong>"+error.responseText+".</div>");
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

function getMenuPaginated(size, page)
{
    let paginationAttributes= {size: size, page: page};
    let c=[];

    $.ajax({
        async: false,
        method: "POST",
        crossDomain: true,
        url:"http://localhost:8090/manage-cocktails/getMenu",
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
            $(".error").html("<div class=\"alert alert-danger alert-dismissible fade show\" role=\"alert\"><strong>ERRORE! </strong>"+error.responseText+".</div>");
        }
    });
    return c;
}

function paginatedMenu(size, page)
{
    let cocktailsArray= getMenuPaginated(size, page);
    let isAlcoholic;
    let isIBA;
    let inMenu;

    if(cocktailsArray.length<=0)
    {
        $(".pageSwitch").toggle("hidden");
        //alert("La ricerca non ha prodotto risultati");
    }

    if(page==0)
    {
        /*se si ci trova alla pagina 0 allora NON viene mostrato il tasto per andare alla pagina precedente*/
        $(".prevPage").toggle("hidden");
    }

    if(getMenuPaginated(size, page+1).length<1)
    {
        /*se nella pagina successiva non ci sono cocktail  allora non mostra il tasto per andare alla pagina successiva*/
        $(".nextPage").toggle("hidden");
    }

    $(".pageNumber").append("Pagina "+(page+1));

    for(i in cocktailsArray)
    {
        let ingredients=cocktailsArray[i].ingredients;
        let ingredientsList="";

        for(j in ingredients)
        {
            ingredientsList=ingredientsList+" "+ingredients[j].product.category+" "+ingredients[j].product.name+",";
        }

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

        ingredientsList=ingredientsList.substring(0, ingredientsList.length-1)+"."; //mette il punto alla fine della stringa

        if(ingredientsList.length<60)
        {
            ingredientsList=ingredientsList+"<br/><br/>";
        }

        let cocktailCard=
            "<div class='col-sm-12 col-xl-6'>" +
            "   <div class='card info-item-panel mt-4' style='background-color: var(--secondaryBlue); border-radius: 30px;'>" +
            "       <div class='row g-0' style='background-color: var(--secondaryBlue); border-radius: 30px;'>" +
            "           <div class='col-md-2 text-center'>" +
            "               <img src='"+cocktailsArray[i].pathFileImg+"' class='img p-3' id='item-img' width='120px' height='140px'>" +
            "           </div>" +
            "           <div class='col-md-10 mt-1'>" +
        "                   <div class='card-body'>" +
                    "           <h5 class='card-title item-name d-flex flex-row'>"+cocktailsArray[i].name+"</h5>" +
                "               <span class='price text-end py-3'>"+getCorrectPrice(cocktailsArray[i].price)+"&euro;</span>" +
                    "           <div class='item-tags mt-2'>" +
                    "               <span class='badge cocktail-flavour'>"+cocktailsArray[i].flavour+"</span>" +
                    "               <span class='badge cocktail-isIBA'>"+isIBA+"</span>" +
                    "               <span class='badge cocktail-isAlcoholic'>"+isAlcoholic+"</span>" +
                    "           </div>" +
                    "           <div class='ingredient-list-menu p-1'><span style='color:var(--lightBlue)'>Ingredienti: </span>"+ingredientsList.toLowerCase()+"</div>"+
            "               </div>" +
            "           </div>" +
            "       </div>" +
            "   </div>" +
            "</div>" +
        "</div>";

        $(".item-list").append(cocktailCard);
    }
}

function nextPageMenu()
{
    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);
    let page = parseInt(urlParams.get('page'));
    page= page+1;

    let src=$("#contextPath").val()+"/users/menu.jsp?page="+page;
    src=src.replace('"', '');
    src=src.replace('\'', '');//elimina possibili residui di virgolette varie e sistema il link

    window.location.href= src;
}

function previousPageMenu()
{
    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);
    let page = parseInt(urlParams.get('page'));
    if(page>0 && page!=null)
    {
        page=page-1;
    }
    else
    {
        page=0;
    }
    let src=$("#contextPath").val()+"/users/menu.jsp?page="+page;
    src=src.replace('"', '');
    src=src.replace('\'', '');//elimina possibili residui di virgolette varie e sistema il link

    window.location.href= src;
}



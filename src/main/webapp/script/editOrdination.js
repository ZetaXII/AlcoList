
function printFlavoursList()
{
    let f=getFlavours();

    for(let i=0; i<f.length; i++)
    {
        $('.selectFlavours').append("<option value="+f[i]+" id="+i+">"+f[i]+"</option>");
    }
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
            console.log("generic error"+ JSON.stringify(error));
        }
    });
    return c;
}

function paginatedCocktailList(size, page) //stampa a video la lista paginata dei cocktail
{
    let isAlcoholic;
    let isIBA;
    let inMenu;

    let cocktailsArray= getCocktailsPaginated(size, page, "", "", null);
    if(cocktailsArray.length<=0)
    {
        $(".pageSwitch").toggle("hidden");
        alert("La ricerca non ha prodotto risultati");
    }

    if(page==0)
    {
        /*se si ci trova alla pagina 0 allora NON viene mostrato il tasto per andare alla pagina precedente*/
        $(".prevPage").toggle("hidden");
    }

    if(getCocktailsPaginated(size, page+1, "", "", null).length<1)
    {
        /*se nella pagina successiva non ci sono cocktail  allora non mostra il tasto per andare alla pagina successiva*/
        $(".nextPage").toggle("hidden");
    }

    $(".pageNumber").append("Pagina "+(page+1));

    for(i in cocktailsArray) {
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

        const queryString = window.location.search;
        const urlParams = new URLSearchParams(queryString);
        let tableUuid = urlParams.get('uuid');
        let uuids = tableUuid + "|" + cocktailsArray[i].uuid
        let cocktailCard=
            "<div class='col-sm-12 col-xl-12'>" +
            "   <div class='card info-item-panel mt-4' style='background-color: var(--secondaryBlue); border-radius: 30px;'>" +
            "       <div class='row g-0' style='background-color: var(--secondaryBlue); border-radius: 30px;'><div class='col-md-2 text-center'>" +
            "               <img src='"+cocktailsArray[i].pathFileImg+"' class='img p-3' id='item-img' width='120px' height='140px'>" +
            "               </div>" +
            "                   <div class='col-md-8 mt-1'>" +
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
            "       <div class='card-body d-flex col-xl-2 col-lg-2 col-md-5 col-sm-5 justify-content-center item-options pb-2 px-4'>   " +
            "<div class='row' style='background-color: var(--secondaryBlue)'>"+
            "           <button style='border: 0;' id='"+uuids+"' class='btn btn-view px-2 m-2 mb-3 w-50' onclick='addCocktailInOrdination(id)'><svg xmlns=\"http://www.w3.org/2000/svg\" width=\"16\" height=\"16\" fill=\"currentColor\" class=\"bi bi-plus-lg\" viewBox=\"0 0 16 16\">\n" +
            "  <path fill-rule=\"evenodd\" d=\"M8 2a.5.5 0 0 1 .5.5v5h5a.5.5 0 0 1 0 1h-5v5a.5.5 0 0 1-1 0v-5h-5a.5.5 0 0 1 0-1h5v-5A.5.5 0 0 1 8 2Z\"/>\n" +
            "</svg></button>" +
            "<div class='row item-options pb-2 px-4' style='background-color: var(--secondaryBlue)' id='cocktail-"+cocktailsArray[i].uuid+"'>0</div>" +
            "           <button style='border: 0;' id='"+uuids+"' class='btn btn-view px-2 m-2 mb-3 w-50' onclick='removeCocktailinOrdination(id)'><svg xmlns=\"http://www.w3.org/2000/svg\" width=\"16\" height=\"16\" fill=\"currentColor\" class=\"bi bi-dash\" viewBox=\"0 0 16 16\">\n" +
            "  <path d=\"M4 8a.5.5 0 0 1 .5-.5h7a.5.5 0 0 1 0 1h-7A.5.5 0 0 1 4 8z\"/>\n" +
            "</svg></button>" +
            "       </div> "+
            "             </div></div></div></div>";

        $(".item-list").append(cocktailCard);
    }
}

function nextPageCocktail()
{
    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);
    let page = parseInt(urlParams.get('page'));
    let tableUuid = urlParams.get('uuid');
    let searchName= urlParams.get('searchName');
    let searchFlavour= urlParams.get('searchFlavour');
    let searchIsAlcoholic= urlParams.get('searchIsAlcoholic');
    page= page+1;

    let src=$("#contextPath").val()+"/users/waiter/editOrdination.jsp?uuid="+tableUuid+"&page="+page+"&searchName="+searchName+"&searchFlavour="+searchFlavour+"&searchIsAlcoholic="+searchIsAlcoholic;
    src=src.replace('"', '');
    src=src.replace('\'', '');//elimina possibili residui di virgolette varie e sistema il link

    window.location.href= src;
}

function previousPageCocktail()
{
    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);
    let page = parseInt(urlParams.get('page'));
    let tableUuid = urlParams.get('uuid');
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
    let src=$("#contextPath").val()+"/users/waiter/editOrdination.jsp?uuid="+tableUuid+"&page="+page+"&searchName="+searchName+"&searchFlavour="+searchFlavour+"&searchIsAlcoholic="+searchIsAlcoholic;
    src=src.replace('"', '');
    src=src.replace('\'', '');//elimina possibili residui di virgolette varie e sistema il link

    window.location.href= src;
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

function _postAddOrderedCocktail(cocktailUuid,ordinationUuid){
    let o;
    let body = {
        cocktailUuid, ordinationUuid
    };
    $.ajax({
        async: false,
        method: "POST",
        crossDomain: true,
        url:"http://localhost:8090/manage-ordinations/addCocktail",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        data:JSON.stringify(body),

        success:function(result)
        {
            o=result;
        },
        error: function(error)
        {
            console.log("generic error"+ JSON.stringify(error));
        }
    });
    return o;
}
function _postRemoveOrderedCocktail(cocktailUuid,ordinationUuid){
    let o;
    let body = {
        cocktailUuid, ordinationUuid
    };
    $.ajax({
        async: false,
        method: "POST",
        crossDomain: true,
        url:"http://localhost:8090/manage-ordinations/removeCocktail",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        data:JSON.stringify(body),

        success:function(result)
        {
            o=result;
        },
        error: function(error)
        {
            console.log("generic error"+ JSON.stringify(error));
        }
    });
    return o;
}

function addCocktailInOrdination(uuids){
    let tableUuid = uuids.split("|")[0]
    let cocktailUuid = uuids.split("|")[1]

    console.log(tableUuid +" " +cocktailUuid)

    let ordinationsForTable = getOrdinationForTable(tableUuid)
    let ordination = ordinationsForTable.find(ordination => ordination.status === "CREATED")
    if(ordination === null || ordination === undefined){
        console.log("NON ESISTE, CREO")
        ordination = createOrdination(tableUuid,uuid);
    } else {
        console.log("ESISTE UEEE")
    }
    console.log("ORDINATION UUID: " +ordination.uuid)
    if(!!_postAddOrderedCocktail(cocktailUuid,ordination.uuid)){
        let cocktail = ordination.orderedCocktails.find(order => order.cocktail.uuid === cocktailUuid)
        let counterID = "#cocktail-"+cocktailUuid
        if(!!cocktail){
            $(counterID).text(cocktail.quantity + 1)
        } else {
            $(counterID).text(1)
        }
    }
}

function removeCocktailinOrdination(uuids){
    let tableUuid = uuids.split("|")[0]
    let cocktailUuid = uuids.split("|")[1]

    console.log(tableUuid +" " +cocktailUuid)

    let ordinationsForTable = getOrdinationForTable(tableUuid)
    let ordination = ordinationsForTable.find(ordination => ordination.status === "CREATED")
    if(ordination === null || ordination === undefined){
        createOrdination(tableUuid,uuid);
        return;
    }
    let cocktail = ordination.orderedCocktails.find(order => order.cocktail.uuid === cocktailUuid)
    if(!!cocktail && cocktail.quantity > 0 && !!_postRemoveOrderedCocktail(cocktailUuid,ordination.uuid)){
        let counterID = "#cocktail-"+cocktailUuid
        if(!!cocktail){
            $(counterID).text(cocktail.quantity - 1)
        } else {
            $(counterID).text(0)
        }
    }

}
function updateStatusWithOrder(ordinationUuid, userUuid, status) {
    let note = "messaggio"; // FIXME
    let body = {
        Note: note,
        userUuid: userUuid,
        ordinationUuid: ordinationUuid
    };

    let o;
    $.ajax({
        async: false,
        method: "POST",
        crossDomain: true,
        url:"http://localhost:8090/manage-ordinations/updateStatus?status="+status,
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        data:JSON.stringify(body),

        success:function(result)
        {
            if(status==="PENDING"){
                redirectToTableView()
            }
            o=result;
        },
        error: function(error)
        {
            alert(error)
            console.log("generic error"+ JSON.stringify(error));
        }
    });
    return o;
}
function updateStatus(tableUuid, userUuid){
    let ordinationsForTable = getOrdinationForTable(tableUuid)
    let ordination = ordinationsForTable.find(ordination => ordination.status === "PENDING")
    updateStatusWithOrder(ordination.uuid, userUuid, "PENDING")
}

function redirectToTableView(){
    window.location.href = $("#contextPath").val()+"/users/waiter/selectTable.jsp";
}
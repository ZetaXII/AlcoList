
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
            $(".error").html("<div class=\"alert alert-danger alert-dismissible fade show\" role=\"alert\"><strong>ERRORE! </strong>"+error.responseText+".</div>");
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

function takeInCharge(ordinationUuid){
    if(getOrdination(ordinationUuid).status === "PENDING"){
        updateStatusWithOrder(ordinationUuid,uuid,"WORK_IN_PROGRESS")
    }
    window.location.href = $("#contextPath").val()+"/users/bartender/drinkList.jsp?ordinationUuid="+ordinationUuid;
}

function loadOrdinations(status){
    let rowLabel
    if (status==="WORK_IN_PROGRESS") rowLabel = "Continua a preparare"
    if (status==="PENDING") rowLabel = "Prendi in carico"
    let banco = readComandePerStatus(status).ordinations;
    let num = 1
    for (i in banco) {
        let numeroTavolo = getTavoloFromOrdination(banco[i].uuid).number
        console.log(banco[i].uuid)
        let row = "<div class=\"card mb-4\" id=\"" + banco[i].uuid + "\" onclick='takeInCharge(this.id)' style=\"background-color: var(--secondaryBlue);color: #eaeaea; border-radius: 30px;\">\n" +
            "            <div class=\"row g-0 user-row\" style=\"background-color: var(--secondaryBlue); border-radius: 30px;\">\n" +
            "                <div class=\"col-md-2 mt-3\">\n" +
            "                   <svg fill=\"white\" width=\"100px\" height=\"100px\" version=\"1.1\" viewBox=\"0 0 700 700\" xmlns=\"http://www.w3.org/2000/svg\">" +
            "                   <path d=\"m599.83 296.5-46.668 46.668h0.003906c-4.375 4.375-10.309 6.8359-16.5 6.8359-6.1875 0-12.121-2.4609-16.496-6.8359l-46.668-46.668c-4.375-4.375-6.832-10.309-6.832-16.496 0-6.1914 2.457-12.125 6.832-16.5 4.3789-4.375 10.312-6.832 16.5-6.832h21.469c-6.0195-41.512-27.781-79.113-60.781-105.01-33-25.895-74.699-38.09-116.45-34.062s-80.348 23.969-107.79 55.691c-27.441 31.723-41.617 72.793-39.59 114.69 2.0234 41.895 20.102 81.402 50.477 110.33 30.375 28.926 70.719 45.051 112.66 45.027 8.3359 0 16.039 4.4492 20.207 11.668s4.168 16.113 0 23.332-11.871 11.668-20.207 11.668c-54.352 0.019531-106.59-21.035-145.74-58.734-39.148-37.699-62.156-89.109-64.188-143.42s17.078-107.3 53.305-147.82c36.227-40.516 86.75-65.41 140.95-69.445 54.203-4.0391 107.86 13.094 149.68 47.797 41.832 34.703 68.578 84.273 74.617 138.29h24.707c6.1875 0 12.125 2.457 16.5 6.832s6.832 10.312 6.832 16.5-2.457 12.121-6.8359 16.496z\"/>" +
            "                   </svg>" +
            "               </div>\n" +
            "                <div class=\"col-md-5\">\n" +
            "                    <div class=\"card-body mt-2 mb-3\">\n" +
            "                        <h5 class=\"card-title profile-title mt-4\">Ordinazione tavolo " + numeroTavolo + "</h5>\n" +
            "                    </div>\n" +
            "                </div>\n" +
            "                <div class=\"col-md-5\">\n" +
            "                   <div class=\"forward-button\" style=\"font-size: 10px; margin-right: 100px;\">"+rowLabel+"</div>\n" +
            "                    <div class=\"forward-button\">\n" +
            "                        <svg xmlns=\"http://www.w3.org/2000/svg\" width=\"16\" height=\"16\" fill=\"currentColor\" class=\"bi bi-chevron-right\" viewBox=\"0 0 16 16\">\n" +
            "                            <path fill-rule=\"evenodd\" d=\"M4.646 1.646a.5.5 0 0 1 .708 0l6 6a.5.5 0 0 1 0 .708l-6 6a.5.5 0 0 1-.708-.708L10.293 8 4.646 2.354a.5.5 0 0 1 0-.708z\"/>\n" +
            "                        </svg>\n" +
            "                    </div>\n" +
            "                </div>\n" +
            "            </div>\n" +
            "          </div>"
        $(".item-list").append(row);
        num++
    }
}
function loadBanco(){
    loadOrdinations("WORK_IN_PROGRESS")
    loadOrdinations("PENDING")
}

function getTable(uuid){
    let t;
    $.ajax({
        async: false,
        method: "GET",
        crossDomain: true,
        url:"http://localhost:8090/manage-tables/get/"+uuid,
        contentType: "application/json; charset=utf-8",
        dataType: "json",

        success:function(table)
        {
            t=table;
        },
        error: function(error)
        {
            alert("generic error"+ error);
        }
    });
    return t;
}

function loadOrdinationsForTable(tableUuid){
    let banco = readComandePerStatus("PENDING").ordinations;
    let selectedTableNumber = getTable(tableUuid).number
    let num = 1
    for (i in banco) {
        let numeroTavolo = getTavoloFromOrdination(banco[i].uuid).number
        if (numeroTavolo === selectedTableNumber) {
            console.log(banco[i].uuid)
            let row = "<div class=\"card mb-4\" id=\"" + banco[i].uuid + "\" onclick='updateComanda(this.id)' style=\"background-color: var(--secondaryBlue);color: #eaeaea; border-radius: 30px;\">\n" +
                "            <div class=\"row g-0 user-row\" style=\"background-color: var(--secondaryBlue); border-radius: 30px;\">\n" +
                "                <div class=\"col-md-2 mt-3\">\n" +
                "                   <svg fill=\"white\" width=\"100px\" height=\"100px\" version=\"1.1\" viewBox=\"0 0 700 700\" xmlns=\"http://www.w3.org/2000/svg\">" +
                "                   <path d=\"m599.83 296.5-46.668 46.668h0.003906c-4.375 4.375-10.309 6.8359-16.5 6.8359-6.1875 0-12.121-2.4609-16.496-6.8359l-46.668-46.668c-4.375-4.375-6.832-10.309-6.832-16.496 0-6.1914 2.457-12.125 6.832-16.5 4.3789-4.375 10.312-6.832 16.5-6.832h21.469c-6.0195-41.512-27.781-79.113-60.781-105.01-33-25.895-74.699-38.09-116.45-34.062s-80.348 23.969-107.79 55.691c-27.441 31.723-41.617 72.793-39.59 114.69 2.0234 41.895 20.102 81.402 50.477 110.33 30.375 28.926 70.719 45.051 112.66 45.027 8.3359 0 16.039 4.4492 20.207 11.668s4.168 16.113 0 23.332-11.871 11.668-20.207 11.668c-54.352 0.019531-106.59-21.035-145.74-58.734-39.148-37.699-62.156-89.109-64.188-143.42s17.078-107.3 53.305-147.82c36.227-40.516 86.75-65.41 140.95-69.445 54.203-4.0391 107.86 13.094 149.68 47.797 41.832 34.703 68.578 84.273 74.617 138.29h24.707c6.1875 0 12.125 2.457 16.5 6.832s6.832 10.312 6.832 16.5-2.457 12.121-6.8359 16.496z\"/>" +
                "                   </svg>" +
                "               </div>\n" +
                "                <div class=\"col-md-5\">\n" +
                "                    <div class=\"card-body mt-2 mb-3\">\n" +
                "                        <h5 class=\"card-title profile-title mt-4\">Ordinazione tavolo " + numeroTavolo + "</h5>\n" +
                "                    </div>\n" +
                "                </div>\n" +
                "                <div class=\"col-md-5\">\n" +
                "                   <div class=\"forward-button\" style=\"font-size: 10px; margin-right: 100px;\"></div>\n" +
                "                    <div class=\"forward-button\">\n" +
                "                        <svg xmlns=\"http://www.w3.org/2000/svg\" width=\"16\" height=\"16\" fill=\"currentColor\" class=\"bi bi-chevron-right\" viewBox=\"0 0 16 16\">\n" +
                "                            <path fill-rule=\"evenodd\" d=\"M4.646 1.646a.5.5 0 0 1 .708 0l6 6a.5.5 0 0 1 0 .708l-6 6a.5.5 0 0 1-.708-.708L10.293 8 4.646 2.354a.5.5 0 0 1 0-.708z\"/>\n" +
                "                        </svg>\n" +
                "                    </div>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "          </div>"
            $(".item-list").append(row);
            num++
        }
    }
}
function getTavoloFromOrdination(uuid){
    let t;
    $.ajax({
        async: false,
        method: "GET",
        crossDomain: true,
        url:"http://localhost:8090/manage-tables/getTableByOrderUuid/"+uuid,
        contentType: "application/json; charset=utf-8",
        dataType: "json",

        success:function(table)
        {
            t=table;
        },
        error: function(error)
        {
            alert("generic error"+ error);
        }
    });
    return t;
}
function readComandePerStatus(status){
    let o;
    let body = {status};
    $.ajax({
        async: false,
        method: "POST",
        crossDomain: true,
        url:"http://localhost:8090/manage-ordinations/searchByFields",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        data:JSON.stringify(body),

        success:function(result)
        {
            o=result;
        },
        error: function(error)
        {

            $(".error").html("<div class=\"alert alert-danger alert-dismissible fade show\" role=\"alert\"><strong>ERRORE! </strong>"+error.responseText+".</div>");

            console.log("generic error"+ JSON.stringify(error));
 
        }
    });
    return o;
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

    let src=$("#contextPath").val()+"/users/waiter/addOrdination.jsp?uuid="+tableUuid+"&page="+page+"&searchName="+searchName+"&searchFlavour="+searchFlavour+"&searchIsAlcoholic="+searchIsAlcoholic;
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
    let src=$("#contextPath").val()+"/users/waiter/addOrdination.jsp?uuid="+tableUuid+"&page="+page+"&searchName="+searchName+"&searchFlavour="+searchFlavour+"&searchIsAlcoholic="+searchIsAlcoholic;
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
function increaseQuantity(uuid){
    let currQuantity = comandaDict[uuid] ?? 0
    currQuantity++
    comandaDict[uuid] = currQuantity
    localStorage.setItem("ComandaDict", JSON.stringify(comandaDict))
    console.log("INC: " + JSON.stringify(comandaDict))
}
function decreaseQuantity(uuid){
    let currQuantity = comandaDict[uuid] ?? 0
    currQuantity--
    comandaDict[uuid] = Math.max(0,currQuantity)
    if (comandaDict[uuid] === 0){
        delete comandaDict[uuid]
    }
    localStorage.setItem("ComandaDict", JSON.stringify(comandaDict))
    console.log("DEC: " + JSON.stringify(comandaDict))
}
function createOrdination(tableUuid, userUuid){
    let o;
    let body = {
        tableUuid: tableUuid,
        createdByUserUuid: userUuid
    };
    $.ajax({
        async: false,
        method: "POST",
        crossDomain: true,
        url:"http://localhost:8090/manage-ordinations/create",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        data:JSON.stringify(body),

        success:function(result)
        {
            o=result;
        },
        error: function(error)
        {
            $(".error").html("<div class=\"alert alert-danger alert-dismissible fade show\" role=\"alert\"><strong>ERRORE! </strong>"+error.responseText+".</div>");
        }
    });
    return o;
}

function getOrdinationForTable(uuid){
    let o;
    $.ajax({
        async: false,
        method: "GET",
        crossDomain: true,
        url:"http://localhost:8090/manage-ordinations/getOrdinationForTable/"+uuid,
        contentType: "application/json; charset=utf-8",
        dataType: "json",

        success:function(result)
        {
            o=result;
        },
        error: function(error)
        {
            $(".error").html("<div class=\"alert alert-danger alert-dismissible fade show\" role=\"alert\"><strong>ERRORE! </strong>"+error.responseText+".</div>");
        }
    });
    return o;
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
            $(".error").html("<div class=\"alert alert-danger alert-dismissible fade show\" role=\"alert\"><strong>ERRORE! </strong>"+error.responseText+".</div>");
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
            $(".error").html("<div class=\"alert alert-danger alert-dismissible fade show\" role=\"alert\"><strong>ERRORE! </strong>"+error.responseText+".</div>");
        }
    });
    return o;
}

function addCocktailInOrdination(uuids){
    let tableUuid = uuids.split("|")[0]
    let cocktailUuid = uuids.split("|")[1]
    let ordinationsForTable = getOrdinationForTable(tableUuid)
    let ordination = ordinationsForTable.find(ordination => ordination.status === "CREATED")
    if(ordination === null || ordination === undefined){
        console.log("Ordered not found.\nNew order created.")
        ordination = createOrdination(tableUuid,uuid);
    }
    if(!!_postAddOrderedCocktail(cocktailUuid,ordination.uuid)){
        let cocktail = null;
        if(!!ordination.orderedCocktails){
            cocktail = ordination.orderedCocktails.find(order => order.cocktail.uuid === cocktailUuid)
        }
        let counterID = "#cocktail-"+cocktailUuid;
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

function updateStatusWithOrder(ordinationUuid, userUuid, status, message) {
    let note = message; // FIXME
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
            if(status === "DELIVERED" || status === "ENDED"){
                document.location.reload()
            } else {
                alert(error)
                $(".error").html("<div class=\"alert alert-danger alert-dismissible fade show\" role=\"alert\"><strong>ERRORE! </strong>"+error.responseText+".</div>");
            }
        }
    });
    return o;
}


function updateStatus(tableUuid, userUuid, status){
    let ordinationsForTable = getOrdinationForTable(tableUuid)
    let ordination = ordinationsForTable.find(ordination => ordination.status === "CREATED")
    updateStatusWithOrder(ordination.uuid, userUuid, status, "PENDING")
}

function updateStatusToDelivered(tableUuid, userUuid){

    console.log(tableUuid + " " + userUuid)
    if (confirm("Aggiornare lo stato della comanda a 'DELIVERED'?")){
        let ordinationsForTable = getOrdinationForTable(tableUuid)
        let ordination = ordinationsForTable.find(ordination => ordination.status === "COMPLETED")
        console.log(ordination.status)
        updateStatusWithOrder(ordination.uuid, userUuid, "DELIVERED", "DELIVERED")
        document.location.reload()
    }
}

function updateTable(uuid){
    let note = "messaggio"; // FIXME
    let body = {
        number: note,
        seats: userUuid,
        uuid: ordinationUuid
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
            $(".error").html("<div class=\"alert alert-danger alert-dismissible fade show\" role=\"alert\"><strong>ERRORE! </strong>"+error.responseText+".</div>");
        }
    });
    return o;
}
function updateStatusToEnded(tableUuid, userUuid){
    console.log(tableUuid + " " + userUuid)
    if (confirm("Aggiornare lo stato della comanda a 'ENDED'?")){
        let ordinationsForTable = getOrdinationForTable(tableUuid)
        let ordination = ordinationsForTable.find(ordination => ordination.status === "DELIVERED")
        console.log(ordination.status)
        updateStatusWithOrder(ordination.uuid, userUuid, "ENDED", "ENDED")
        document.location.reload()
    }
}

function redirectToTableView(){
    window.location.href = $("#contextPath").val()+"/users/waiter/selectTable.jsp";
}

function getOrdination(uuid){
        let o;
        $.ajax({
            async: false,
            method: "GET",
            crossDomain: true,
            url:"http://localhost:8090/manage-ordinations/get/"+uuid,
            contentType: "application/json; charset=utf-8",
            dataType: "json",

            success:function(ordination)
            {
                o=ordination;
            },
            error: function(error)
            {
                alert("generic error"+ error);
            }
        });
        return o;
}

function drawCocktailCards(orders){
    for (i in orders) {
        let card = "<div class=\"card mb-4\" id=\"userRow\" style=\"background-color: var(--secondaryBlue);color: #eaeaea; border-radius: 30px;\">\n" +
        "            <div class=\"row g-0 user-row\" style=\"background-color: var(--secondaryBlue); border-radius: 30px;\">\n" +
        "                <div class=\"col-md-3\">\n" +
        "                    <div class=\"m-2 p-2\" style=\"vertical-align: central; width:80px; height: 80px; background-color: #283a57 ; border-radius: 50%\">" +
        "                       <h5 style=\"padding-left:25px; padding-top: 23px;\">"+orders[i].quantity+"</h5>" +
        "                    </div>\n" +
        "                </div>\n" +
        "                <div class=\"col-md-7\">\n" +
        "                    <div class=\"card-body mt-3 mb-3\">\n" +
        "                        <h5 class=\"card-title profile-title\">" +orders[i].cocktail.name+ "</h5>\n" +
        "                    </div>\n" +
        "                </div>\n" +
        "            </div>\n" +
        "           </div>"
        //let cocktailCard = "<span>"+orders[i].cocktail.name+"</span><br>"
        $(".cocktailList").append(card)
    }
}

function completeComanda(ordinationUuid){
    if(getOrdination(ordinationUuid).status === "WORK_IN_PROGRESS"){
        updateStatusWithOrder(ordinationUuid,uuid,"COMPLETED", "COMPLETED")
    }
    window.location.href = $("#contextPath").val()+"/users/bartender/dashboard.jsp";
}

function sendBackComanda(ordinationUuid){
    let result = prompt("Perche' vuoi mandare indietro la comanda?")
    if(getOrdination(ordinationUuid).status === "WORK_IN_PROGRESS"){
        alert(result)
        updateStatusWithOrder(ordinationUuid,uuid,"SENTBACK",result)
    }
    window.location.href = $("#contextPath").val()+"/users/bartender/dashboard.jsp";
}
function cancelComanda(ordinationUuid){
    if(getOrdination(ordinationUuid).status === "WORK_IN_PROGRESS"){
        updateStatusWithOrder(ordinationUuid,uuid,"PENDING", "PENDING")
    }
    window.location.href = $("#contextPath").val()+"/users/bartender/dashboard.jsp";

 
}

function updateStatusWithOrder(ordinationUuid, userUuid, status, message) {
    let note = message; // FIXME
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
            if(status === "DELIVERED" || status === "ENDED"){
                document.location.reload()
            } else {
                alert(error)
                console.log("generic error"+ JSON.stringify(error));
            }
        }
    });
    return o;
}


function updateStatus(tableUuid, userUuid, status){
    let ordinationsForTable = getOrdinationForTable(tableUuid)
    let ordination = ordinationsForTable.find(ordination => ordination.status === "CREATED")
    updateStatusWithOrder(ordination.uuid, userUuid, status, "PENDING")
}

function updateStatusToDelivered(tableUuid, userUuid){

    console.log(tableUuid + " " + userUuid)
    if (confirm("Aggiornare lo stato della comanda a 'DELIVERED'?")){
        let ordinationsForTable = getOrdinationForTable(tableUuid)
        let ordination = ordinationsForTable.find(ordination => ordination.status === "COMPLETED")
        console.log(ordination.status)
        updateStatusWithOrder(ordination.uuid, userUuid, "DELIVERED", "DELIVERED")
        document.location.reload()
    }
}

function updateTable(uuid){
    let note = "messaggio"; // FIXME
    let body = {
        number: note,
        seats: userUuid,
        uuid: ordinationUuid
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
function updateStatusToEnded(tableUuid, userUuid){
    console.log(tableUuid + " " + userUuid)
    if (confirm("Aggiornare lo stato della comanda a 'ENDED'?")){
        let ordinationsForTable = getOrdinationForTable(tableUuid)
        let ordination = ordinationsForTable.find(ordination => ordination.status === "DELIVERED")
        console.log(ordination.status)
        updateStatusWithOrder(ordination.uuid, userUuid, "ENDED", "ENDED")
        document.location.reload()
    }
}

function redirectToTableView(){
    window.location.href = $("#contextPath").val()+"/users/waiter/selectTable.jsp";
}

function getOrdination(uuid){
        let o;
        $.ajax({
            async: false,
            method: "GET",
            crossDomain: true,
            url:"http://localhost:8090/manage-ordinations/get/"+uuid,
            contentType: "application/json; charset=utf-8",
            dataType: "json",

            success:function(ordination)
            {
                o=ordination;
            },
            error: function(error)
            {
                alert("generic error"+ error);
            }
        });
        return o;
}

function drawCocktailCards(orders){
    for (i in orders) {
        let card = "<div class=\"card mb-4\" id=\"userRow\" style=\"background-color: var(--secondaryBlue);color: #eaeaea; border-radius: 30px;\">\n" +
        "            <div class=\"row g-0 user-row\" style=\"background-color: var(--secondaryBlue); border-radius: 30px;\">\n" +
        "                <div class=\"col-md-3\">\n" +
        "                    <div class=\"m-2 p-2\" style=\"vertical-align: central; width:80px; height: 80px; background-color: #283a57 ; border-radius: 50%\">" +
        "                       <h5 style=\"padding-left:25px; padding-top: 23px;\">"+orders[i].quantity+"</h5>" +
        "                    </div>\n" +
        "                </div>\n" +
        "                <div class=\"col-md-7\">\n" +
        "                    <div class=\"card-body mt-3 mb-3\">\n" +
        "                        <h5 class=\"card-title profile-title\">" +orders[i].cocktail.name+ "</h5>\n" +
        "                    </div>\n" +
        "                </div>\n" +
        "            </div>\n" +
        "           </div>"
        //let cocktailCard = "<span>"+orders[i].cocktail.name+"</span><br>"
        $(".cocktailList").append(card)
    }
}

function completeComanda(ordinationUuid){
    if(getOrdination(ordinationUuid).status === "WORK_IN_PROGRESS"){
        updateStatusWithOrder(ordinationUuid,uuid,"COMPLETED", "COMPLETED")
    }
    window.location.href = $("#contextPath").val()+"/users/bartender/dashboard.jsp";
}

function sendBackComanda(ordinationUuid){
    let result = prompt("Perche' vuoi mandare indietro la comanda?")
    if(getOrdination(ordinationUuid).status === "WORK_IN_PROGRESS"){
        alert(result)
        updateStatusWithOrder(ordinationUuid,uuid,"SENTBACK",result)
    }
    window.location.href = $("#contextPath").val()+"/users/bartender/dashboard.jsp";
}
function cancelComanda(ordinationUuid){
    if(getOrdination(ordinationUuid).status === "WORK_IN_PROGRESS"){
        updateStatusWithOrder(ordinationUuid,uuid,"PENDING", "PENDING")
    }
    window.location.href = $("#contextPath").val()+"/users/bartender/dashboard.jsp";
}

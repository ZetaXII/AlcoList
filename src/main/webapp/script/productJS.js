function setProductSearchFilters()
{
    let searchName= $("#searchNameField").val();
    let searchCategory= $("#searchCategoryField").val();
    window.location.href= $("#contextPath").val()+"/users/bartender/magazzino.jsp?page=0&name="+searchName+"&category="+searchCategory;
}

function backToListProduct()
{
    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);
    let page = parseInt(urlParams.get('page'));
    let name= urlParams.get('name');
    let category= urlParams.get('category');

    let src=$("#contextPath").val()+"/users/bartender/magazzino.jsp?page="+page+"&name="+name+"&category="+category;
    src=src.replace('"', '');
    src=src.replace('\'', '');//elimina possibili residui di virgolette varie e sistema il link

    window.location.href= src;
}

function getProduct(uuid)
{
    let p;
    $.ajax({
        async: false,
        method: "GET",
        crossDomain: true,
        url:"http://localhost:8090/manage-products/get/"+uuid,
        contentType: "application/json; charset=utf-8",
        dataType: "json",

        success:function(product)
        {
            p=product;
        },
        error: function(error)
        {
            $(".error").html("<div class=\"alert alert-danger alert-dismissible fade show\" role=\"alert\"><strong>ERRORE! </strong>"+error.responseText+".</div>");
        }
    });
    return p;
}

function getProductsPaginated(size, page, name, category) //ritorna un array di prodotti paginati e filtrati per i vari campi (se nulli restituisce tutti i prodotti)
{
    let paginationAttributes= {size: size, page: page, name: name, category: category};
    let p=[];

    $.ajax({
        async: false,
        method: "POST",
        crossDomain: true,
        url:"http://localhost:8090/manage-products/searchByFields",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        data:JSON.stringify(paginationAttributes),

        success:function(result)
        {
            let productsArray= result.product;
            p=productsArray;
        },
        error: function(error)
        {
            $(".error").html("<div class=\"alert alert-danger alert-dismissible fade show\" role=\"alert\"><strong>ERRORE! </strong>"+error.responseText+".</div>");
        }
    });
    return p;
}

function paginatedProductList(size, page, name, category)
{
    let productsArray= getProductsPaginated(size, page, name, category);
    let ml=" ";
    let alcoholicPercentage;
    $(".pageNumber").append("Pagina "+(page+1));

    if(productsArray.length<=0)
    {
        $(".pageSwitch").toggle("hidden");
        alert("La ricerca non ha prodotto risultati");
    }

    if(page==0)
    {
        /*se si ci trova alla pagina 0 allora NON viene mostrato il tasto per andare alla pagina precedente*/
        $(".prevPage").toggle("hidden");
    }

    if(getProductsPaginated(size, page+1, name, category).length<1)
    {
        /*se nella pagina successiva non ci sono prodotti  allora non mostra il tasto per andare alla pagina successiva*/
        $(".nextPage").toggle("hidden");
    }

    for(i in productsArray)
    {

        if(parseInt(productsArray[i].ml)<=0 || (productsArray[i].present==false))
        {
            //ml="<span class='badge product-ml'style='background-color: var(--red)'> Prodotto mancante!</span>";
            ml= "nessuno";
        }
        else if(productsArray[i].ml!=null)
        {
            //ml="<span class='badge product-ml'>"+productsArray[i].ml+" ml</span>";
            ml=productsArray[i].ml+" ml";
        }
        else
        {
            ml="&nbsp;";
        }

        if(parseInt(productsArray[i].alcoholicPercentage)<=0 || productsArray[i].alcoholicPercentage==null)
        {
            alcoholicPercentage="<span class='badge product-alcoholicPercentage'>Analcolico</span>";
        }
        else
        {
            alcoholicPercentage="<span class='badge product-alcoholicPercentage'>ALC. "+productsArray[i].alcoholicPercentage+"%</span>";
        }

        let productCard=
        "<div class='col-sm-12 col-xl-4'>\n" +
        "                    <div class='card info-item-panel mt-4' style='background-color: var(--secondaryBlue); border-radius: 30px;'>\n" +
        "                        <div class='row g-0' style='background-color: var(--secondaryBlue); border-radius: 30px;'>\n" +
        "                            <div class='col-md-2 text-center'>\n" +
        //                             <img src='"+productsArray[i].pathFileImg+"' class='img-fluid rounded-start p-4' id='item-img' width='100px'>\n" +
        "                            </div>\n" +
        "                            <div class='col-md-12 mt-1 ms-4'>\n" +
        "                                <div class='card-body'>\n" +
        "                                    <span class='card-title item-name' style='font-size:18px'>"+productsArray[i].category+" "+productsArray[i].name+"</span><br/><span style='color: var(--yellow); font-size: 16px;'> "+ ml +"</span>\n" +
        "                                    <div class='item-tags'>\n"+alcoholicPercentage+
        "                                    </div>\n" +
        "                                </div>\n" +
        "                            </div>\n" +
        "                            <div class='d-flex justify-content-center item-options pb-2 px-4'>\n" +
        "                                <a href='"+$("#contextPath").val()+"/users/bartender/modificaProdotto.jsp?uuid="+productsArray[i].uuid+"&page="+page+"&name="+name+"&category="+category+"' class='btn btn-modify px-2 m-2 mb-3 w-50 h-50 fs-5'><i class='bx bxs-pencil modify-icon'></i></a>\n" +
        "                                <a onclick='confirmDelete(\""+productsArray[i].uuid+"\")' class='btn btn-modify px-2 m-2 mb-3 w-50 h-50 fs-5'><i class='bx bxs-trash erase-icon'></i></a>\n" +
        "                            </div>\n" +
        "                        </div>\n" +
        "                    </div>\n" +
        "                </div>";
        $(".item-list").append(productCard);
    }
}

function nextPageProduct()
{
    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);
    let page = parseInt(urlParams.get('page'));
    let searchName= urlParams.get('name');
    let searchCategory= urlParams.get('category');
    page= page+1;

    let src=$("#contextPath").val()+"/users/bartender/magazzino.jsp?page="+page+"&name="+searchName+"&category="+searchCategory;
    src=src.replace('"', '');
    src=src.replace('\'', '');//elimina possibili residui di virgolette varie e sistema il link

    window.location.href= src;
}

function previousPageProduct()
{
    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);
    let page = parseInt(urlParams.get('page'));
    let searchName= urlParams.get('name');
    let searchCategory= urlParams.get('category');
    if(page>0 && page!=null)
    {
        page=page-1;
    }
    else
    {
        page=0;
    }
    let src=$("#contextPath").val()+"/users/bartender/magazzino.jsp?page="+page+"&name="+searchName+"&category="+searchCategory;
    src=src.replace('"', '');
    src=src.replace('\'', '');//elimina possibili residui di virgolette varie e sistema il link

    window.location.href= src;
}

function deleteProduct(uuid)
{
    $.ajax({
        async: false,
        method: "DELETE",
        crossDomain: true,
        url:"http://localhost:8090/manage-products/delete?uuid="+uuid,
        contentType: "application/json; charset=utf-8",
        dataType: "json",

        success:function(product)
        {
            alert(product.category+" "+product.name+" è stato eliminato correttamente")
        },
        error: function(error)
        {
            $(".error").html("<div class=\"alert alert-danger alert-dismissible fade show\" role=\"alert\"><strong>ERRORE! </strong>"+error.responseText+".</div>");
        }
    });
}

function confirmDelete(uuid)
{
    if (confirm("Sei sicuro di voler eliminare il prodotto?"))
    {
        deleteProduct(uuid);
        window.location.reload();
    }
}

function infoModifyProduct() //inserisce nei rispettivi campi le varie info del prodotto da modificare
{
    /*prende la query di ricerca e vede se ci sono delle variabili di GET*/
    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);
    /*recupera il valore della variabile uuid se esiste*/
    let productUuid = urlParams.get('uuid');
    let product= getProduct(productUuid);

    if(product.alcoholicPercentage<=0)
    {
        product.alcoholicPercentage=0;
    }

    $("#categoryField").val(product.category);
    $("#titleField").val(product.name);
    $("#pathFileImgField").val(product.pathFileImg);
    $("#alcoholicPercentage").val(product.alcoholicPercentage);
    $("#ml").val(product.ml);
    $("#uuid").val(product.uuid);

    document.getElementById("item-img").src=product.pathFileImg;
}

function modifyProduct()
{
    let category=$("#categoryField").val();
    let title=$("#titleField").val();
    let pathFileImg=$("#pathFileImgField").val();
    let alcoholicPercentage=$("#alcoholicPercentage").val();
    let ml=$("#ml").val();
    let uuid=$("#uuid").val();
    let present="true";

    if(parseInt(ml)<=0 && ml!=null)
    {
        ml="0";
        present="false";
    }
    else if(ml=="" || ml==" " || ml==null)
    {
        ml=null;
        present="true";
    }

    if(parseFloat(alcoholicPercentage)<=0)
    {
        alcoholicPercentage="0";
    }

    if(title!="" && pathFileImg!="" && category!="" && (alcoholicPercentage!="") && uuid!="")
    {
        let productModel=
            {
                category: category,
                name: title,
                pathFileImg: pathFileImg,
                uuid: uuid,
                ml: ml,
                alcoholicPercentage: alcoholicPercentage,
                present: present
            }

        // MODIFICARE ALCHOLIC IN ALCOHOLIC console.log(JSON.stringify(productModel));
        $.ajax({
            async: true,
            method: "POST",
            crossDomain: true,
            url:"http://localhost:8090/manage-products/update",
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            data:JSON.stringify(productModel),

            success:function(result)
            {
                window.location.href=$("#contextPath").val()+"/users/bartender/magazzino.jsp?page=0&name=&category=";
            },
            error: function(error)
            {
                $(".error").html("<div class=\"alert alert-danger alert-dismissible fade show\" role=\"alert\"><strong>ERRORE! </strong>"+error.responseText+".</div>");
            }
        });
    }
}

function addProduct()
{
    let category=$("#categoryField").val();
    let title=$("#titleField").val();
    let pathFileImg=$("#pathFileImgField").val();
    let alcoholicPercentage=$("#alcoholicPercentage").val();
    let ml=$("#ml").val();
    let present="true";

    if(parseInt(ml)<=0 && ml!=null)
    {
        ml="0";
        present="false";
    }
    else if(ml=="" || ml==" " || ml==null)
    {
        ml=null;
        present="true";
    }

    if(parseFloat(alcoholicPercentage)<=0)
    {
        alcoholicPercentage="0";
    }

    if(title!="" && pathFileImg!="" && category!="" && (alcoholicPercentage!="") && (ml!="") && uuid!="")
    {
        let productModel=
            {
                category: category,
                name: title,
                pathFileImg: pathFileImg,
                ml: ml,
                alcoholicPercentage: alcoholicPercentage,
                present: present
            }

        $.ajax({
            async: true,
            method: "POST",
            crossDomain: true,
            url:"http://localhost:8090/manage-products/add",
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            data:JSON.stringify(productModel),

            success:function(result)
            {
                window.location.href=$("#contextPath").val()+"/users/bartender/magazzino.jsp?page=0&name=&category=";
            },
            error: function(error)
            {
                $(".error").html("<div class=\"alert alert-danger alert-dismissible fade show\" role=\"alert\"><strong>ERRORE! </strong>"+error.responseText+".</div>");
            }
        });
    }
}

function productListForIngredients()
{
    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);
    let uuidCocktail = urlParams.get('uuid');
    let searchName = urlParams.get('searchName');
    let searchCategory = urlParams.get('searchCategory');
    let productsArray= getProductsPaginated(null, 0, searchName, searchCategory);
    let ml=" ";
    let mlField="hidden";
    let alcoholicPercentage;
    let cocktail=getCocktail(uuidCocktail);
    let cocktailIngredientsPresent= cocktail.ingredients;
    let ingredientsAlreadyIn=false;
    let optional="";

    if(cocktailIngredientsPresent.length<=0)
    {
        $(".item-list-added").append("<span class='h3 text-center text-light my-5'>Nessun ingrediente presente</span>");
    }

    if(productsArray.length<=0)
    {
        alert("La ricerca non ha prodotto risultati");
    }
    else
    {
        for(x in cocktailIngredientsPresent)
        {
            ml=" ";
            optional="";
            if(parseInt(cocktailIngredientsPresent[x].quantity)<=0 || (cocktailIngredientsPresent[x].present==false) || (cocktailIngredientsPresent[x].quantity==null))
            {
                ml="<span class='product-ml ms-2'style='color: var(--yellow); font-size: 16px;'>Quanto basta</span>";
            }
            else if(cocktailIngredientsPresent[x].quantity!=null)
            {
                ml="<span class='product-ml ms-2'style='color: var(--yellow); font-size: 16px;'>"+cocktailIngredientsPresent[x].quantity+" ml</span>";
            }

            if(cocktailIngredientsPresent[x].optional==true)
            {
                optional="Facoltativo";
            }
            else
            {
                optional="Obbligatorio";
            }


            let productCardAdded=
                "<div class='col-sm-12 col-xl-6'>\n" +
                "                    <div class='card info-item-panel mt-4' style='background-color: var(--lightBlue); border-radius: 30px;'>\n" +
                "                        <div class='row g-0' style='background-color: var(--darkBlue); border-radius: 30px;'>\n" +
                //                            <div class='col-md-2 text-center'>\n" +
                //                                <img src='"+cocktailIngredientsPresent[x].product.pathFileImg+"' class='img-fluid rounded-start p-4' id='item-img' width='100px'>\n" +
                //                            </div>\n" +
                "                            <div class='col-md-12 mt-1'>\n" +
                "                                <div class='card-body'>\n" +
                "                                    <h5 class='card-title item-name ms-2' style='font-size: 18px;'>"+cocktailIngredientsPresent[x].product.category+" "+cocktailIngredientsPresent[x].product.name+"</h5>\n" +
                "                                    <div class='item-tags'>\n"
                                                        + ml +
                "                                       <br/><span class='badge product-alcoholicPercentage m-1'>"+optional+"</span>"+
                            "                            <div class='d-flex justify-content-end ingredient-quantity-modify text-end'>" +
                            "                               <input type='number' id='"+x+"' placeholder='quantità (ml)' class='ingredient-quantity px-2 m-2 mb-3 w-50' value='"+cocktailIngredientsPresent[x].quantity+"'>" +
                            "                               <select id='isOptional"+x+"' class='ingredient-quantity px-2 m-2 mb-3 w-25'>" +
                                                                "<option value='false'>Obbligatorio</option>"+
                                                                "<option value='true'>Facoltativo</option>"+
                "                                           </select>"+
                "                                           <button class='btn btn-addIngredient px-2 m-2 mb-3 w-25' onclick='modifyIngredient("+x+",\""+cocktailIngredientsPresent[x].uuid+"\")'><i class='bx bxs-pencil modify-icon'></i></button>" +
                            "                               <button onclick='confirmDeleteIngredient(\""+cocktailIngredientsPresent[x].uuid+"\")' class='btn btn-erase px-2 m-2 mb-3 w-25'><i class='bx bxs-trash erase-icon'></i></button>" +
                            "                            </div>\n" +
                "                                    </div>\n" +
                "                                </div>\n" +
                "                            </div>\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "                </div>";
            $(".item-list-added").append(productCardAdded);
        }
    }

    for(i in productsArray)
    {
        ml=" ";
        ingredientsAlreadyIn=false;
        for(j in cocktailIngredientsPresent)
        {
            if(cocktailIngredientsPresent[j].product.uuid==productsArray[i].uuid)
            {
                ingredientsAlreadyIn=true;
                break;
            }
        }

        if(ingredientsAlreadyIn==false)
        {
            if(parseInt(productsArray[i].ml)<=0 || (productsArray[i].present==false) || productsArray[i].ml==" " || productsArray[i].ml=="")
            {
                ml="<span class='product-ml ms-2'style='color: var(--red); font-size: 16px;'>Prodotto mancante!</span>";
                mlField="hidden";
            }
            else if(productsArray[i].ml==null)
            {
                ml="<span class='product-ml'> &nbsp;</span>";
                mlField="hidden";
            }
            else if(productsArray[i].ml!=null)
            {
                ml="<span class='product-ml ms-2'style='color: var(--yellow); font-size: 16px;'>"+productsArray[i].ml+" ml</span>";
                mlField="show";
            }

            if(parseInt(productsArray[i].alcoholicPercentage)<=0 || productsArray[i].alcoholicPercentage==null)
            {
                alcoholicPercentage="<span class='badge product-alcoholicPercentage'>Analcolico</span>";
            }
            else
            {
                alcoholicPercentage="<span class='badge product-alcoholicPercentage'>ALC. "+productsArray[i].alcoholicPercentage+"%</span>";
            }

            let productCard=
                "<div class='col-12 col-sm-4'>\n" +
                "                    <div class='card info-item-panel mt-4' style='background-color: var(--secondaryBlue); border-radius: 30px;'>\n" +
                "                        <div class='row g-0' style='background-color: var(--secondaryBlue); border-radius: 30px;'>\n" +
                //                            <div class='col-md-2 text-center'>\n" +
                //                                <img src='"+productsArray[i].pathFileImg+"' class='img-fluid rounded-start p-4' id='item-img' width='100px'>\n" +
                //                            </div>\n" +
                "                            <div class='col-md-12 mt-1'>\n" +
                "                                <div class='card-body'>\n" +
                "                                    <h5 class='card-title item-name' style='font-size: 18px'>"+productsArray[i].category+" "+productsArray[i].name+"</h5>\n" + ml +
                "                                    <div class='item-tags'>\n"+alcoholicPercentage+
                            "                            <div class='d-flex justify-content-end ingredient-quantity-modify text-end'>" +                                       /*'aggiungiIngrediente(\""+uuidCocktail+"\",\""+productsArray[i].uuid+"\",\""+$("#"+i).val()+"\")'*/
                            "                               <input type='number' id='"+i+"' placeholder='quantità (ml)' class='ingredient-quantity px-2 m-2 mb-3 w-50 "+mlField+"'>"+
                            "                               <select id='isOptional"+i+"' class='ingredient-quantity px-2 m-2 mb-3 w-25'>" +
                                                                "<option value='false'>Obbligatorio</option>"+
                                                                "<option value='true'>Facoltativo</option>"+
                "                                           </select>"+
                    "                                       <button class='btn btn-addIngredient px-2 m-2 mb-3 w-25' onclick='addIngredient(\""+uuidCocktail+"\",\""+productsArray[i].uuid+"\","+i+")'><i class='bx bx-plus add-icon'></i></button>" +
                            "                            </div>\n" +
                "                                    </div>\n" +
                "                                </div>\n" +
                "                            </div>\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "                </div>";
            $(".item-list").append(productCard);
        }
    }
}

function addIngredient(uuidCocktail, uuidProduct, idBox)
{
    let qt=$("#"+idBox).val();
    let isOptional=$("#isOptional"+idBox).val();
    if(isOptional=="true")
    {
        isOptional=true;
    }
    else
    {
        isOptional=false;
    }

    let model=
        {
            productUuid: uuidProduct,
            quantity: qt,
            isOptional: isOptional,
            cocktailUuid: uuidCocktail
        }

    //alert(JSON.stringify(model));

    $.ajax({
        async: false,
        method: "POST",
        crossDomain: true,
        url:"http://localhost:8090/manage-ingredients/add",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        data:JSON.stringify(model),

        success:function(result)
        {
            alert("Ingrediente aggiunto correttamente");
            window.location.reload();
            //window.location.href=$("#contextPath").val()+"/users/bartender/aggiungiIngredientiCocktail.jsp?uuid="+uuidCocktail;
        },
        error: function(error)
        {
            $(".error").html("<div class=\"alert alert-danger alert-dismissible fade show\" role=\"alert\"><strong>ERRORE! </strong>"+error.responseText+".</div>");
        }
    });
}

function confirmDeleteIngredient(ingredientUuid)
{
    if (confirm("Sei sicuro di voler eliminare l'ingrediente?"))
    {
        deleteIngredient(ingredientUuid);
        window.location.reload();
    }
}

function deleteIngredient(ingredientUuid)
{
    $.ajax({
        async: false,
        method: "DELETE",
        crossDomain: true,
        url:"http://localhost:8090/manage-ingredients/delete?uuid="+ingredientUuid,
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        data:JSON.stringify(),

        success:function(result)
        {
            alert("L'ingrediente è stato eliminato correttamente");
        },
        error: function(error)
        {
            $(".error").html("<div class=\"alert alert-danger alert-dismissible fade show\" role=\"alert\"><strong>ERRORE! </strong>"+error.responseText+".</div>");
        }
    });
}

function modifyIngredient(idBox, uuidIngredient)
{
    let qt=$("#"+idBox).val();
    let isOptional=$("#isOptional"+idBox).val();
    if(isOptional=="true")
    {
        isOptional=true;
    }
    else
    {
        isOptional=false;
    }
    const model=
        {
            uuid: uuidIngredient, //uiddIngredient
            quantity: qt,
            isOptional: isOptional
        }

    //alert(JSON.stringify(model));

    $.ajax({
        async: false,
        method: "POST",
        crossDomain: true,
        url:"http://localhost:8090/manage-ingredients/update",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        data:JSON.stringify(model),

        success:function(result)
        {
            alert("L'ingrediente è stato modificato correttamente");
            window.location.reload();
        },
        error: function(error)
        {
            $(".error").html("<div class=\"alert alert-danger alert-dismissible fade show\" role=\"alert\"><strong>ERRORE! </strong>"+error.responseText+".</div>");
        }
    });
}


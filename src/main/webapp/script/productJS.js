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
            console.log("generic error"+ JSON.stringify(error));
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
            console.log("generic error"+ JSON.stringify(error));
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
            ml="<span class='badge product-ml'style='background-color: var(--red)'> Prodotto mancante!</span>";
        }
        else if(productsArray[i].ml!=null)
        {
            ml="<span class='badge product-ml'>"+productsArray[i].ml+" ml</span>";
        }

        if(parseInt(productsArray[i].alcoholicPercentage)<=0 || productsArray[i].alcoholicPercentage==null)
        {
            alcoholicPercentage="<span class='badge product-alcoholicPercentage'>Non alcolico</span>";
        }
        else
        {
            alcoholicPercentage="<span class='badge product-alcoholicPercentage'>ALC. "+productsArray[i].alcoholicPercentage+"%</span>";
        }

        let productCard=
        "<div class='col-sm-12 col-xl-6'>\n" +
        "                    <div class='card info-item-panel mt-4' style='background-color: var(--secondaryBlue); border-radius: 30px;'>\n" +
        "                        <div class='row g-0' style='background-color: var(--secondaryBlue); border-radius: 30px;'>\n" +
        "                            <div class='col-md-2 text-center'>\n" +
        "                                <img src='"+productsArray[i].pathFileImg+"' class='img-fluid rounded-start p-4' id='item-img' width='100px'>\n" +
        "                            </div>\n" +
        "                            <div class='col-md-10 mt-1'>\n" +
        "                                <div class='card-body'>\n" +
        "                                    <h5 class='card-title item-name'>"+productsArray[i].category+" "+productsArray[i].name+"</h5>\n" +
        "                                    <div class='item-tags'>\n"+alcoholicPercentage+ ml +
        "                                    </div>\n" +
        "                                </div>\n" +
        "                            </div>\n" +
        "                            <div class='d-flex justify-content-center item-options pb-2 px-4'>\n" +
        "                                <a href='"+$("#contextPath").val()+"/users/bartender/modificaProdotto.jsp?uuid="+productsArray[i].uuid+"&page="+page+"&name="+name+"&category="+category+"' class='btn btn-modify px-2 m-2 mb-3 w-50'><i class='bx bxs-pencil modify-icon'></i></a>\n" +
        "                                <a onclick='confirmDelete(\""+productsArray[i].uuid+"\")' class='btn btn-erase px-2 m-2 mb-3 w-50'><i class='bx bxs-trash erase-icon'></i></a>\n" +
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
            console.log("generic error"+ JSON.stringify(error));
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

    if(parseInt(ml)<=0)
    {
        ml="0";
        present="false";
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
                uuid: uuid,
                ml: ml,
                alcholicPercentage: alcoholicPercentage,
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
                console.log("generic error"+ JSON.stringify(error));
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

    if(parseInt(ml)<=0)
    {
        ml="0";
        present="false";
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
                alcholicPercentage: alcoholicPercentage,
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
                console.log("generic error"+ JSON.stringify(error));
            }
        });
    }
}

<html>
<script>
    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);
    let uuidCocktail = urlParams.get('uuid');
</script>
<head>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/favicon.ico"/>
    <title>Aggiungi ingredienti</title>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.1/jquery.min.js" integrity="sha512-aVKKRRi/Q/YV+4mjoKBsE4x3H+BkegoM/em46NNlCqNTmUYADjBbeNefNxYV7giUp0VxICtqdrbqU7iVaeZNXA==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <!-- JavaScript Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/jquery.session@1.0.0/jquery.session.min.js"></script>
    <!-- CSS -->
    <link href="${pageContext.request.contextPath}/style/listaStyle.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/style/base.css" rel="stylesheet">
</head>
<body>
<%@include file="../../navBar.jsp"%>
<!--Controlla il ruolo dell'utente prima di mostrare la pagina -->
<script>
    if(!roleList.includes("BARTENDER"))
    {
        logout();
    }
</script>

<div class="title">
    <h1 class="h1" id="title"></h1>
</div>

<div class="container-fluid px-4 py-0">
    <div class="content"><div class="error"></div>
        <div class="row gx-4 containerTables px-3">
            <div class="col-12 row row-cols-12 item-list-added">
                <button class="btn btn-success my-2 p-2 mt-4" style="font-size: 16px;" onclick='window.location.href= $("#contextPath").val()+"/users/infoCocktail.jsp?uuid="+uuidCocktail+"&page=0&searchName=&searchFlavour=&searchIsAlcoholic="'>Fatto</button>
                <h3 class="text-center pt-4 mt-3" style="color:var(--yellow)">Prodotti gi&agrave; aggiunti</h3>
            </div>

            <div class="col-12 row row-cols-12 item-list">
                <h3 class="text-center pt-4 mt-3" style="color:var(--yellow)">Prodotti da poter aggiungere</h3>

                <!--<div class="filters input-group my-3 py-5 px-2 justify-content-center w-100">
                    <input type="text" class="search-cocktail my-3 p-3 w-75" id="searchCategoryField" placeholder=" Cerca per categoria..." minlength="3" maxlength="50" style="border-radius: 12px">
                    <input type="text" class="search-cocktail my-3 p-3 w-75" id="searchNameField" placeholder=" Cerca per nome..." minlength="3" maxlength="50" style="border-radius: 12px">

                    <div class="d-flex justify-content-center w-75">
                        <button onclick="resetProductSearchFiltersForIngredients()" class="btn btn-search px-2 m-2 w-100 fs-4" type="button"><i class='bx bx-reset'></i> Reset</button>
                        <button class="btn btn-search px-2 m-2 w-100 fs-4" onclick="setProductSearchFiltersForIngredients()"><i class='bx bx-search-alt search-icon'></i> Cerca</button>
                    </div>
                </div>-->

                <!-- card che mostrano le info del prodotto-->
            </div>

        </div>
    </div>
</div>
<script src="${pageContext.request.contextPath}/script/productJS.js"></script>
<script src="${pageContext.request.contextPath}/script/cocktailJS.js"></script>
<script src="${pageContext.request.contextPath}/script/utils.js"></script>
<script>
    let c=getCocktail(uuidCocktail);
    $(".title").append("<span class='h3' style='color:var(--yellow);'>"+c.name+"</span>");

    productListForIngredients();

    let searchName= urlParams.get('searchName');
    let searchCategory= urlParams.get('searchCategory');

    $("#searchNameField").val(searchName);
    $("#searchCategoryField").val(searchCategory);
</script>
</body>
</html>

<%int i=0;%>
<html>
<head>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/favicon.ico"/>
    <title>Cocktails</title>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.1/jquery.min.js" integrity="sha512-aVKKRRi/Q/YV+4mjoKBsE4x3H+BkegoM/em46NNlCqNTmUYADjBbeNefNxYV7giUp0VxICtqdrbqU7iVaeZNXA==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <!-- JavaScript Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/jquery.session@1.0.0/jquery.session.min.js"></script>
    <!-- CSS -->
    <link href="${pageContext.request.contextPath}/style/listaStyle.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/style/base.css" rel="stylesheet">
</head>
<body>
    <%@include file="../navBar.jsp"%>
    <!--Controlla il ruolo dell'utente prima di mostrare la pagina -->
    <script>
        if(!roleList)
        {
            logout();
        }

    </script>
    <div class="title">
        <h1 class="h1 text-white" id="title"></h1>
    </div>
    <div class="container-fluid p-4">
        <div class="content"><div class="error"></div>
            <div class="row gx-4 gy-1 containerTables p-3">

                <!--<div class="d-flex justify-content-center">
                    <a class="btn btn-add px-2 m-2 w-75 fs-5" onclick="openFilters()"><i class='bx bx-filter-alt'></i> Filtri</a>
                </div>-->

                <div class="filters input-group my-3 py-5 px-2 justify-content-center w-100">
                    <input type="text" class="search-cocktail p-3 w-75" id="searchNameField" placeholder=" Cerca per nome..." minlength="3" maxlength="50" style="border-radius: 12px">

                    <div class="d-flex justify-content-center w-75 mt-3">
                        <select class="w-100 selectFlavours m-2" id="searchFlavourField" required>
                            <option value=" ">Fragranza</option>
                            <!--verranno generati in automatico i flavours-->
                        </select>

                        <select class="w-100 px-2 m-2" id="searchIsAlcoholic">
                            <option value=" ">Alcolico/Analcolico</option>
                            <option>alcolico</option>
                            <option>analcolico</option>
                        </select>
                    </div>

                    <div class="d-flex justify-content-center w-75">
                        <a href="${pageContext.request.contextPath}/users/listaCocktail.jsp?page=0&searchName=&searchFlavour=&searchIsAlcoholic=" class="btn btn-search px-2 m-2 w-100 fs-4" type="button"><i class='bx bx-reset'></i> Reset</a>
                        <button class="btn btn-search px-2 m-2 w-100 fs-4" onclick="setCocktailSearchFilters()"><i class='bx bx-search-alt search-icon'></i> Cerca</button>
                    </div>

                </div>

                <div class="d-flex justify-content-center">
                    <a href="${pageContext.request.contextPath}/users/bartender/aggiungiCocktail.jsp" class="btn btn-add px-2 m-2 w-75 fs-5"><i class='bx bx-plus add-icon'></i> Aggiungi</a>
                </div>

                <!--bottoni switch pagina-->
                <div class="col-12 pageSwitch text-center" style="display: flex;align-items: center;justify-content: center;">
                    <button class="btn prevPage" onclick="previousPageCocktail()"><i class='bx bx-chevron-left prevPage'></i></button>
                    <span class="pageNumber"></span>
                    <button class="btn nextPage" onclick="nextPageCocktail()"><i class='bx bx-chevron-right nextPage' ></i></button>
                </div>

                <div class="col-12 row row-cols-lg-1 row-cols-xl-3 item-list">
                    <!-- card che mostra le info del cocktail
                    <div class="col-sm-12 col-xl-6">
                        <div class="card info-item-panel mt-4" style="background-color: var(--secondaryBlue); border-radius: 30px;">
                            <div class="row g-0" style="background-color: var(--secondaryBlue); border-radius: 30px;">
                                <div class="col-md-2 text-center">
                                    <img src="https://havana-club.com/wp-content/uploads/cache/2021/02/El-Nacional_434x700/1954922261.png" class="img-fluid rounded-start p-4" id="item-img" width="100px">
                                </div>
                                <div class="col-md-10 mt-1">
                                    <div class="card-body">
                                        <h5 class="card-title item-name">Cocktail <span class="price">12&euro;</span></h5>
                                        <div class="item-tags">
                                            <span class="badge cocktail-flavour">SALATO</span>
                                            <span class="badge cocktail-isIBA">IBA</span>
                                            <span class="badge cocktail-isAlcoholic">ALCOLICO</span>
                                        </div>
                                    </div>
                                </div>
                                <div class="d-flex justify-content-center item-options pb-2 px-4">
                                    <a href="#" class="btn btn-modify px-2 m-2 mb-3 w-50"><i class='bx bxs-pencil modify-icon'></i></a>
                                    <a href="#" class="btn btn-erase px-2 m-2 mb-3 w-50"><i class='bx bxs-trash erase-icon'></i></a>
                                </div>
                            </div>
                        </div>
                    </div>
                    -->
                    <!------------------------------>
                </div>

                <!--bottoni switch pagina-->
                <div class="col-12 pageSwitch text-center" style="display: flex;align-items: center;justify-content: center;">
                    <button class="btn prevPage" onclick="previousPageCocktail()"><i class='bx bx-chevron-left prevPage'></i></button>
                    <span class="pageNumber"></span>
                    <button class="btn nextPage" onclick="nextPageCocktail()"><i class='bx bx-chevron-right nextPage' ></i></button>
                </div>

            </div>
        </div>
    </div>
<script src="${pageContext.request.contextPath}/script/utils.js"></script>
<script src="${pageContext.request.contextPath}/script/cocktailJS.js"></script>
<script>
    printFlavoursList();

    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);
    let page = parseInt(urlParams.get('page'));
    let searchName= urlParams.get('searchName');
    let searchFlavour= urlParams.get('searchFlavour');
    let searchIsAlcoholic= urlParams.get('searchIsAlcoholic');

    $("#searchNameField").val(searchName);

    paginatedCocktailList(12,page,searchName,searchFlavour,searchIsAlcoholic);

    if(roleList.includes("WAITER") && JSON.parse(roleList).length==1)
    {
        $(".btn-add").toggle("hidden");
        $(".btn-erase").toggle("hidden");
        $(".btn-modify").toggle("hidden");
    }
</script>
</body>
</html>

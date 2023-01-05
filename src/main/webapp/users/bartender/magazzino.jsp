<html>
<head>
    <title>Magazzino</title>
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
    <div class="content">
        <div class="row gx-4 containerTables px-3">

            <div class="filters input-group my-3 py-5 px-2 justify-content-center w-100">
                <input type="text" class="search-cocktail my-3 p-3 w-75" id="searchCategoryField" placeholder=" Cerca per categoria..." minlength="3" maxlength="50" style="border-radius: 12px">
                <input type="text" class="search-cocktail my-3 p-3 w-75" id="searchNameField" placeholder=" Cerca per nome..." minlength="3" maxlength="50" style="border-radius: 12px">

                <div class="d-flex justify-content-center w-75">
                    <a href="${pageContext.request.contextPath}/users/bartender/magazzino.jsp?page=0&name=&category=" class="btn btn-search px-2 m-2 w-100 fs-4" type="button"><i class='bx bx-reset'></i> Reset</a>
                    <button class="btn btn-search px-2 m-2 w-100 fs-4" onclick="setProductSearchFilters()"><i class='bx bx-search-alt search-icon'></i> Cerca</button>
                </div>
            </div>

            <div class="d-flex justify-content-center">
                <a href="${pageContext.request.contextPath}/users/bartender/aggiungiProdotto.jsp" class="btn btn-add px-2 m-2 w-75 fs-5"><i class='bx bx-plus add-icon'></i> Aggiungi</a>
            </div>

            <!--bottoni switch pagina-->
            <div class="col-12 pageSwitch text-center" style="display: flex;align-items: center;justify-content: center;">
                <button class="btn prevPage" onclick="previousPageProduct()"><i class='bx bx-chevron-left prevPage'></i></button>
                <span class="pageNumber"></span>
                <button class="btn nextPage" onclick="nextPageProduct()"><i class='bx bx-chevron-right nextPage' ></i></button>
            </div>

            <div class="col-12 row row-cols-lg-1 row-cols-xl-3 item-list">
                <!-- card che mostrano le info del prodotto-->
            </div>

            <!--bottoni switch pagina-->
            <div class="col-12 pageSwitch text-center" style="display: flex;align-items: center;justify-content: center;">
                <button class="btn prevPage" onclick="previousPageProduct()"><i class='bx bx-chevron-left prevPage'></i></button>
                <span class="pageNumber"></span>
                <button class="btn nextPage" onclick="nextPageProduct()"><i class='bx bx-chevron-right nextPage' ></i></button>
            </div>

        </div>
    </div>
</div>
<script src="${pageContext.request.contextPath}/script/productJS.js"></script>
<script src="${pageContext.request.contextPath}/script/utils.js"></script>
<script>
    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);
    let page = parseInt(urlParams.get('page'));
    let searchName= urlParams.get('name');
    let searchCategory= urlParams.get('category');

    $("#searchNameField").val(searchName);
    $("#searchCategoryField").val(searchCategory);

    if(roleList.includes("WAITER") && JSON.parse(roleList).length==1)
    {
        $(".btn-add").toggle("hidden");
        $(".btn-erase").toggle("hidden");
        $(".btn-modify").toggle("hidden");
    }

    paginatedProductList(12, page, searchName, searchCategory);
</script>
</body>
</html>

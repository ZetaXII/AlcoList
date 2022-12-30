<html>
<head>
    <title>Lista cocktails</title>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.1/jquery.min.js" integrity="sha512-aVKKRRi/Q/YV+4mjoKBsE4x3H+BkegoM/em46NNlCqNTmUYADjBbeNefNxYV7giUp0VxICtqdrbqU7iVaeZNXA==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <!-- JavaScript Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/jquery.session@1.0.0/jquery.session.min.js"></script>
    <!-- CSS -->
    <link href="${pageContext.request.contextPath}/style/listaCocktailStyle.css" rel="stylesheet">
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
        <div class="content">
            <div class="row gx-4 gy-1 containerTables p-3">

                <div class="input-group h-100 w-100 m-2 d-flex justify-content-center">
                    <input type="text" class="form-control search-cocktail p-3" placeholder=" Cerca un cocktail dal nome...">
                    <button class="btn btn-search btn-outline-secondary" type="button" id="button-addon1"><i class='bx bx-search-alt search-icon'></i></button>
                </div>

                <div class="d-flex justify-content-center container-filter">
                    <button class="btn btn-alcoholic px-2 m-2 w-50"><i class='bx bxs-wine' ></i></button>
                    <button class="btn btn-flavour px-2 m-2 w-50"><i class='bx bxs-bowl-hot' ></i></button>
                    <button class="btn btn-add px-2 m-2 w-50"><i class='bx bx-plus add-icon'></i></button>
                </div>

                <!-- card che mostra le info del cocktail-->
                <div class="col-12">
                    <div class="card info-cocktail-panel mt-4" style="background-color: var(--secondaryBlue); border-radius: 30px;">
                        <div class="row g-0" style="background-color: var(--secondaryBlue); border-radius: 30px;">
                            <div class="col-md-2 text-center">
                                <img src="https://havana-club.com/wp-content/uploads/cache/2021/02/El-Nacional_434x700/1954922261.png" class="img-fluid rounded-start p-4" id="cocktail-img" width="100px">
                            </div>
                            <div class="col-md-10 mt-1">
                                <div class="card-body">
                                    <h5 class="card-title cocktail-name">Cocktail <span class="price">12&euro;</span></h5>
                                    <div class="cocktail-tags">
                                        <span class="badge cocktail-flavour">SALATO</span>
                                        <span class="badge cocktail-isIBA">IBA</span>
                                        <span class="badge cocktail-isAlcoholic">ALCOLICO</span>
                                    </div>
                                </div>
                            </div>
                            <div class="d-flex justify-content-center cocktail-options pb-2 px-4">
                                <a href="#" class="btn btn-modify px-2 m-2 mb-3 w-50"><i class='bx bxs-pencil modify-icon'></i></a>
                                <a href="#" class="btn btn-erase px-2 m-2 mb-3 w-50"><i class='bx bxs-trash erase-icon'></i></a>
                            </div>
                        </div>
                    </div>
                </div>
                <!------------------------------>

            </div>
        </div>
    </div>
<script src="${pageContext.request.contextPath}/script/utils.js"></script>
</body>
</html>

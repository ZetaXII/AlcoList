<html>
<head>
    <title>Info cocktail</title>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.1/jquery.min.js" integrity="sha512-aVKKRRi/Q/YV+4mjoKBsE4x3H+BkegoM/em46NNlCqNTmUYADjBbeNefNxYV7giUp0VxICtqdrbqU7iVaeZNXA==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <!-- JavaScript Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/jquery.session@1.0.0/jquery.session.min.js"></script>
    <!-- CSS -->
    <link href="${pageContext.request.contextPath}/style/infoCocktailStyle.css" rel="stylesheet">
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
        <h1 class="h1" id="title"></h1>
    </div>
    <div class="container-fluid p-4">
        <div class="content">
            <div class="row-cols-12 gx-1 gy-4 containerTables p-3">

                <!-- card che mostra le info del cocktail-->
                <div class="card info-item-panel mb-3 p-1" style="background-color: var(--secondaryBlue); border-radius: 30px;">
                    <div class="row g-0" style="background-color: var(--secondaryBlue); border-radius: 30px;">
                        <div class="col-md-4 text-center">
                            <a id="backToListCocktail" onclick="backToListCocktail()"><i class='bx bx-chevron-left back-button'></i></a>
                            <img src="" class="img-fluid rounded-start p-4" id="item-img" width="300px">
                        </div>
                        <div class="col-md-7">
                            <div class="card-body p-4 mt-4">
                                <h5 class="card-title item-name"></h5>
                                <div class="list-ingredients fs-6">
                                    <ul class="pt-3 px-3 ingredient-list">
                                    </ul>
                                </div>
                                <div class="item-tags pt-2">
                                    <span class="badge cocktail-flavour"></span>
                                    <span class="badge cocktail-isIBA"></span>
                                    <span class="badge cocktail-isAlcoholic"></span>
                                    <span class="badge cocktail-inMenu"></span>
                                </div>
                                <p class="card-text cocktail-description"></p>
                                <p class="price"></p>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>
<script src="${pageContext.request.contextPath}/script/cocktailJS.js"></script>
<script src="${pageContext.request.contextPath}/script/utils.js"></script>
<script>
    infoCocktail();
</script>
</body>
</html>

<html>
<head>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/favicon.ico"/>
    <title>Aggiungi prodotto</title>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.1/jquery.min.js" integrity="sha512-aVKKRRi/Q/YV+4mjoKBsE4x3H+BkegoM/em46NNlCqNTmUYADjBbeNefNxYV7giUp0VxICtqdrbqU7iVaeZNXA==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <!-- JavaScript Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/jquery.session@1.0.0/jquery.session.min.js"></script>
    <!-- CSS -->
    <link href="${pageContext.request.contextPath}/style/infoCocktailStyle.css" rel="stylesheet">
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

<div class="container-fluid p-4">
    <div class="content">
        <div class="row-cols-12 gx-1 gy-4 containerTables p-3">

            <!-- card che mostra le info del cocktail-->
            <div class="card info-item-panel mb-3 p-1" style="background-color: var(--secondaryBlue); border-radius: 30px;">
                <div class="row g-0" style="background-color: var(--secondaryBlue); border-radius: 30px;">
                    <div class="col-md-1 text-center">
                        <a id="backToListCocktail" href="${pageContext.request.contextPath}/users/bartender/magazzino.jsp?page=0&name=&category="><i class='bx bx-chevron-left back-button'></i></a>
                        <!--<img src="" class="img-fluid rounded-start p-4" id="item-img" width="300px">-->
                    </div>
                    <div class="col-md-11">
                        <form class="card-body p-4 mt-4">
                            <h5 class="card-title"><input type="text" id="categoryField" class="modifyItemFields form-control py-2 mb-4" placeholder="Categoria" maxlength="50" required></h5>

                            <h5 class="card-title"><input type="text" id="titleField" class="modifyItemFields form-control py-2 mb-4" placeholder="Nome" maxlength="50" required></h5>

                            <!--<input type="text" id="pathFileImgField" onfocusout="changeImg()" class="modifyItemFields form-control py-2 mb-4" placeholder="Link dell'immagine" maxlength="255" required>-->

                            <p class="card-text cocktail-description"><input type="number" id="alcoholicPercentage" class="modifyItemFields form-control py-2 mb-4" placeholder="Percentuale alcolica" maxlength="50" required></p>

                            <p class="price"><input type="number" id="ml" class="modifyItemFields form-control py-2 mb-4" placeholder="Quantit&agrave; disponibile (ml)" maxlength="50"></p>

                            <div class="text-center">
                                <button class="btn btn-modifyCocktail px-2 m-2 w-50 text-center" onclick="addProduct()">Aggiungi prodotto</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
<script src="${pageContext.request.contextPath}/script/utils.js"></script>
<script src="${pageContext.request.contextPath}/script/productJS.js" type="text/javascript"></script>
<script>
    let noImage= "https://upload.wikimedia.org/wikipedia/commons/thumb/6/65/No-Image-Placeholder.svg/1200px-No-Image-Placeholder.svg.png";

    let pathFileImg=$("#pathFileImgField").val();
    if(pathFileImg=="")
    {
        document.getElementById("item-img").src=noImage;
    }

    function changeImg()
    {
        pathFileImg=$("#pathFileImgField").val();

        if(pathFileImg!="")
        {
            document.getElementById("item-img").src=pathFileImg;
        }
        else
        {
            document.getElementById("item-img").src=noImage;
        }
    }
</script>
</body>
</html>

<html>
<head>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/favicon.ico"/>
    <title>Dashboard</title>
    <link href="${pageContext.request.contextPath}/style/base.css" rel="stylesheet">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.1/jquery.min.js" integrity="sha512-aVKKRRi/Q/YV+4mjoKBsE4x3H+BkegoM/em46NNlCqNTmUYADjBbeNefNxYV7giUp0VxICtqdrbqU7iVaeZNXA==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <!-- JavaScript Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/jquery.session@1.0.0/jquery.session.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.5.0/Chart.min.js"></script><!--FOR CHARTS-->
    <!-- CSS -->
    <link href="${pageContext.request.contextPath}/style/dashboardStyle.css" rel="stylesheet">
</head>
<body>
    <%@include file="../../navBar.jsp"%>

    <!--Controlla il ruolo dell'utente prima di mostrare la pagina -->
    <script>
        if(!roleList.includes("MANAGER"))
        {
            logout();
        }
    </script>
    <div class="title">
        <h1 class="h1" id="title"></h1>
    </div>
    <div class="container-fluid p-4">
        <div class="content">
            <div class="row gx-3 gy-3">

                <div class="col-lg-6 mb-2">
                    <div class="card h-auto" style="background-color: var(--secondaryBlue); color: var(--white); border-radius: 18px;">
                        <div class="card-header" style="background-color: var(--secondaryBlue); color: var(--white); border-radius: 18px;">
                            <h5>Utenti totali</h5>
                        </div>
                        <ul class="list-group list-group-flush" style="background-color: var(--secondaryBlue); color: var(--white); border-radius: 18px;">
                            <li class="list-group-item mt-2" style="background-color: var(--secondaryBlue); color: var(--white);"><p class="fs-4"><img src="${pageContext.request.contextPath}/img/avatar.svg" class="img-fluid" height="52px" width="52px" style="background-color: var(--white); border-radius: 100%;"> <b class="waiterNumber"></b> Camerieri</p></li>
                            <li class="list-group-item mt-2" style="background-color: var(--secondaryBlue); color: var(--white);"><p class="fs-4"><img src="${pageContext.request.contextPath}/img/avatar.svg" class="img-fluid" height="52px" width="52px" style="background-color: var(--white); border-radius: 100%;"> <b class="bartenderNumber"></b> Bartender</p></li>
                            <li class="list-group-item mt-2" style="background-color: var(--secondaryBlue); color: var(--white);"><p class="fs-4"><img src="${pageContext.request.contextPath}/img/avatar.svg" class="img-fluid" height="52px" width="52px" style="background-color: var(--white); border-radius: 100%;"> <b class="managerNumber"></b> Manager</p></li>
                        </ul>
                    </div>
                </div>

                <div class="col-xl-6 gy-2">
                    <div class="card" style="background-color: var(--secondaryBlue); color: var(--white); border-radius: 18px;">
                        <canvas id="pie-chart" class="py-1 my-3" height="295px"></canvas><!--GRAFICO A TORTA-->
                    </div>
                </div>

                <div class="col-xl-12 gy-2 gy-sx-5">
                    <div class="card" style="background-color: var(--secondaryBlue); color: var(--white); border-radius: 18px;">
                        <canvas id="bar-chart" class="py-1 my-3" height="300px"></canvas><!--GRAFICO A BARRE-->
                    </div>
                </div>

                <div class="col-md-6 col-lg-4">
                    <div class="card mb-3" style="background-color: var(--secondaryBlue); color: var(--white); border-radius: 18px;">
                        <div class="card-title ps-3 pt-3">
                            <span class="h4 p-2 my-5">Camerieri per ordini creati</span>
                        </div>
                        <div class="card-body">
                            <div class="d-grid lista-migliori-camerieri-ordini-creati overflow-auto" style="height: 300px;">
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col-md-6 col-lg-4">
                    <div class="card mb-3" style="background-color: var(--secondaryBlue); color: var(--white); border-radius: 18px;">
                        <div class="card-title ps-3 pt-3">
                            <span class="h4 p-2 my-5">Bartender per ordini realizzati</span>
                        </div>
                        <div class="card-body">
                            <div class="d-grid lista-migliori-bartender-ordini-eseguiti overflow-auto" style="height: 300px;">
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col-md-6 col-lg-4">
                    <div class="card mb-3" style="background-color: var(--secondaryBlue); color: var(--white); border-radius: 18px;">
                        <div class="card-title ps-3 pt-3">
                            <span class="h4 p-2 my-5">Camerieri per ordini consegnati</span>
                        </div>
                        <div class="card-body">
                            <div class="d-grid lista-migliori-camerieri-ordini-consegnati overflow-auto" style="height: 300px;">
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>
<script src="${pageContext.request.contextPath}/script/utils.js"></script>
<%--<script src="${pageContext.request.contextPath}/script/dashboardCharts.js"></script>--%>
<script src="${pageContext.request.contextPath}/script/statsJS.js"></script>
<script>
    $(".waiterNumber").append(getNumbersOfUsers("Waiter"));
    $(".bartenderNumber").append(getNumbersOfUsers("Bartender"));
    $(".managerNumber").append(getNumbersOfUsers("Manager"));

    getBestSellingCocktails();
    getBestSellingCocktailsByFlavour();
    getNumbersOfCreatedByUserUuid();
    getNumbersOfExecutedByUserUuid();
    getNumbersOfDeliveredByUserUuid()
</script>
</body>
</html>

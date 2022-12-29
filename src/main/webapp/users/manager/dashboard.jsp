<html>
<head>
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
                <div class="col-lg-4">
                    <div class="card h-auto">
                        <div class="card-header">
                            <h5>Utenti totali</h5>
                        </div>
                        <ul class="list-group list-group-flush">
                            <li class="list-group-item mt-2"><p class="fs-4"><img src="${pageContext.request.contextPath}/img/waiter.svg" class="img-fluid" height="52px" width="52px" > <b>5</b> Camerieri</p></li>
                            <li class="list-group-item mt-2"><p class="fs-4"><img src="${pageContext.request.contextPath}/img/waiter.svg" class="img-fluid" height="52px" width="52px" > <b>3</b> Bartender</p></li>
                            <li class="list-group-item mt-2"><p class="fs-4"><img src="${pageContext.request.contextPath}/img/waiter.svg" class="img-fluid" height="52px" width="52px" > <b>1</b> Manager</p></li>
                        </ul>
                    </div>
                </div>
                <div class="col-lg-8">
                    <div class="card mb-3">
                        <img src="${pageContext.request.contextPath}/img/manage-users.svg" class="card-img-top p-3" width="300px" height="255px" alt="...">
                        <div class="card-body gap-2">
                            <div class="d-grid gap-2">
                                <button class="btn btn-primary" type="button">Gestisci utenti</button>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-xl-6 gy-1">
                    <div class="card">
                        <canvas id="pie-chart" height="300px"></canvas>
                    </div>
                </div>
                <div class="col-xl-6 gy-1 gy-sx-3">
                    <div class="card">
                        <canvas id="bar-chart" height="300px"></canvas>
                    </div>
                </div>
                <div class="col-md-6 col-lg-4">
                    <div class="card mb-3">
                        <img src="${pageContext.request.contextPath}/img/manage-menu.svg" class="card-img-top p-5" width="300px" height="320px" alt="...">
                        <div class="card-body">
                            <div class="d-grid gap-2">
                                <button class="btn btn-primary" type="button">Gestisci men&ugrave;</button>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-6 col-lg-4">
                    <div class="card mb-3">
                        <img src="${pageContext.request.contextPath}/img/manage-market.svg" class="card-img-top p-5" width="300px" height="320px" alt="...">
                        <div class="card-body">
                            <div class="d-grid gap-2">
                                <button class="btn btn-primary" type="button">Gestisci magazzino</button>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-12 col-lg-4">
                    <div class="card mb-3">
                        <img src="${pageContext.request.contextPath}/img/manage-cocktails.svg" class="card-img-top p-3" width="300px" height="320px" alt="...">
                        <div class="card-body">
                            <div class="d-grid gap-2">
                                <button class="btn btn-primary" type="button">Gestisci cocktails</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
<script src="${pageContext.request.contextPath}/script/setPageTitle.js"></script>
<script src="${pageContext.request.contextPath}/script/dashboardCharts.js"></script>
</body>
</html>

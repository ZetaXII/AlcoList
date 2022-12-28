<html>
<head>
    <title>Dashboard</title>
    <link href="style/base.css" rel="stylesheet">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.1/jquery.min.js" integrity="sha512-aVKKRRi/Q/YV+4mjoKBsE4x3H+BkegoM/em46NNlCqNTmUYADjBbeNefNxYV7giUp0VxICtqdrbqU7iVaeZNXA==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <!-- JavaScript Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/jquery.session@1.0.0/jquery.session.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.5.0/Chart.min.js"></script><!--FOR CHARTS-->
    <!-- CSS -->
    <link href="style/dashboardStyle.css" rel="stylesheet">
</head>
<body>
    <%@include file="navBar.jsp"%>
    <div class="title">
        <h1 class="h1">Prova</h1>
    </div>
    <div class="container-fluid p-4">
        <div class="content">
            <div class="row gx-3 gy-0 m-1">
                <div class="col-lg-4">
                    <div class="card mb-3">
                        <canvas id="bar-chart" width="800" height="450"></canvas>
                    </div>
                    <div class="card mb-3">
                        <canvas id="pie-chart" width="800" height="450"></canvas>
                    </div>
                </div>
                <div class="col-lg-3 h-100">
                    <div class="card mb-3">
                        <!-- CHART CONTAINER
                        <canvas id="pie-chart" width="800" height="450"></canvas>-->
                        <img src="img/manage-users.svg" class="card-img-top p-3" width="300px" height="320px" alt="...">
                        <div class="card-body gap-2">
                            <div class="d-grid gap-2">
                                <button class="btn btn-primary" type="button">Gestisci utenti</button>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4">
                    <div class="card mb-3">
                        <img src="img/manage-users.svg" class="card-img-top p-3" width="300px" height="320px" alt="...">
                        <div class="card-body gap-2">
                            <div class="d-grid gap-2">
                                <button class="btn btn-primary" type="button">Gestisci utenti</button>
                            </div>
                        </div>
                    </div>
                    <div class="row gx-3 gy-0 m-1">
                        <div class="col-12">
                            <div class="card mb-3">
                                <img src="img/manage-users.svg" class="card-img-top p-3" width="300px" height="320px" alt="...">
                                <div class="card-body gap-2">
                                    <div class="d-grid gap-2">
                                        <button class="btn btn-primary" type="button">Gestisci utenti</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-12">
                    <div class="card mb-3">
                        <img src="img/manage-users.svg" class="card-img-top p-3" width="300px" height="320px" alt="...">
                    </div>
                </div>
                <div class="col-lg-4">
                    <div class="card mb-3">
                        <img src="img/manage-users.svg" class="card-img-top p-3" width="300px" height="320px" alt="...">
                        <div class="card-body gap-2">
                            <div class="d-grid gap-2">
                                <button class="btn btn-primary" type="button">Gestisci utenti</button>
                            </div>
                        </div>
                    </div>
                </div>








                <div class="col-xl-3 col-lg-6 p-3 mb-3" style="border-radius: 8px; background-color: var(--white);">
                    <div class="row m-3 text-center" style="border-radius: 10px; background-color: var(--white);">
                        <div class="col-6">
                            <img src="img/waiter.svg" width="100" class="img-fluid"><p class="p-0 fs-6 mb-0">Camerieri attivi:</p><h4>5</h4>
                        </div>
                        <div class="col-6">
                            <img src="img/waiter.svg" width="100" class="img-fluid"><p class="p-0 fs-6 mb-0">Bartender attivi:</p><h4>3</h4>
                        </div>
                    </div>
                    <div class="row gy-0 m-1 text-center" style="border-radius: 10px; background-color: var(--white);">
                        <div class="col-6">
                            <img src="img/waiter.svg" width="100" class="img-fluid"><p class="p-0 fs-6 mb-0">Manager attivi:</p><h4>2</h4>
                        </div>
                        <div class="col-6">
                            <img src="img/waiter.svg" width="100" class="img-fluid"><p class="p-0 fs-6 mb-0">Totale attivi:</p><h4>10</h4>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script>
        let cocktailsName = ["Birra Peroni","Aperol Spritz","Vodka Absolute","Sex on the beach","Angelo azzurro"];
        let cocktailsValue = [1200,1400,980,567,1233];

        new Chart(document.getElementById("bar-chart"), {
            type: 'bar',
            data: {
                labels: [cocktailsName[0],cocktailsName[1],cocktailsName[2],cocktailsName[3],cocktailsName[4]],
                datasets: [
                    {
                        label: "Cocktail venduti",
                        backgroundColor: ["#3e95cd", "#8e5ea2","#3cba9f","#e8c3b9","#c45850"],
                        data: [cocktailsValue[0],cocktailsValue[1],cocktailsValue[2],cocktailsValue[3],cocktailsValue[4]],
                        borderColor: 'white',
                        borderWidth: 2,
                        fill: false,
                    }
                ]
            },
            options: {
                legend: { display: false },
                title: {
                    display: true,
                    text: 'Predicted world population (millions) in 2050'
                },
                scales: {
                    xAxes: [{
                        gridLines: {
                            //color: "#eaeaea",
                            lineWidth: 0.5
                        }
                    }],
                    yAxes: [{
                        gridLines: {
                            //color: "#eaeaea",
                            lineWidth: 0.5
                        }
                    }]
                },
            }
        });

        new Chart(document.getElementById("pie-chart"), {
            type: 'pie',
            data: {
                labels: [cocktailsName[0],cocktailsName[1],cocktailsName[2],cocktailsName[3],cocktailsName[4]],
                datasets: [{
                    label: "Cocktail venduti",
                    backgroundColor: ["#3e95cd", "#8e5ea2","#3cba9f","#e8c3b9","#c45850"],
                    data: [cocktailsValue[0],cocktailsValue[1],cocktailsValue[2],cocktailsValue[3],cocktailsValue[4]]
                }]
            },
            options: {
                title: {
                    display: true,
                    text: 'Predicted world population (millions) in 2050'
                }
            }
        });

        Chart.defaults.global.defaultFontColor = "black";
    </script>
</body>
</html>

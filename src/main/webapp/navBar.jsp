<!DOCTYPE html>
<!-- Coding by CodingLab | www.codinglabweb.com -->
<input type="hidden" value="${pageContext.request.contextPath}" id="contextPath">
<html lang="en">
<head>
    <title>NavBar</title>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/favicon.ico"/>
    <link rel="manifest" href="/site.webmanifest">
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- JavaScript AJAX JQUERY & OTHERS SCRIPT -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.1/jquery.min.js" integrity="sha512-aVKKRRi/Q/YV+4mjoKBsE4x3H+BkegoM/em46NNlCqNTmUYADjBbeNefNxYV7giUp0VxICtqdrbqU7iVaeZNXA==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <!-- CSS & Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <link href='https://unpkg.com/boxicons@2.1.1/css/boxicons.min.css' rel='stylesheet'>
    <link href="${pageContext.request.contextPath}/style/navBarStyle.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/style/base.css" rel="stylesheet">
</head>
<body>
    <nav>
        <div class="logo">
            <i class='bx bx-menu menu-icon'></i>
            <span class="logo-name">AlcoList</span>
            <a href="${pageContext.request.contextPath}/users/profile.jsp" class="user-info p-0 mt-1 text-center mx-3" style="right:20px; position: fixed; transition: 0.2s;">
                <span class="user-name"></span> <span class="badge bg-danger"></span><i class='bx bx-user-circle icon' style="font-size: 30px"></i>
            </a>
        </div>
        <div class="sidebar">
            <div class="sidebar-content mt-0 overflow-auto">
                <ul class="lists">
                    <!-- NAVBAR PER MANAGER -->
                    <div class="manager hidden">
                        <li class="list">
                            <a href="${pageContext.request.contextPath}/users/manager/dashboard.jsp" class="nav-link">
                                <i class='bx bxs-dashboard icon'></i>
                                <span class="link">Dashboard</span>
                            </a>
                        </li>
                        <li class="list">
                            <a href="${pageContext.request.contextPath}/users/manager/listaUser.jsp" class="nav-link">
                                <i class='bx bxs-user-detail icon' ></i>
                                <span class="link">Personale</span>
                            </a>
                        </li>
                    </div>
                    <!-- -------------------------------- -->

                    <!-- NAVBAR PER BARTENDER -->
                    <div class="bartender hidden">
                        <li class="list">
                            <a href="${pageContext.request.contextPath}/users/bartender/dashboard.jsp" class="nav-link">
                                <i class='bx bxs-store-alt icon'></i>
                                <span class="link">Banco</span>
                            </a>
                        </li>
                    <!----------------------------------- -->
                        <li class="list">
                            <a href="${pageContext.request.contextPath}/users/bartender/magazzino.jsp?page=0&name=&category=" class="nav-link">
                                <i class='bx bxs-store-alt icon'></i>
                                <span class="link">Magazzino</span>
                            </a>
                        </li>
                    </div>
                    <!-- -------------------------------- -->

                    <!-- NAVBAR PER WAITER -->
                    <div class="waiter hidden">
                        <li class="list">
                            <a href="${pageContext.request.contextPath}/users/waiter/selectTable.jsp" class="nav-link">
                                <i class='bx bxs-dish icon'></i>
                                <span class="link">Comande</span>
                            </a>
                        </li>
                    </div>
                    <!-- -------------------------------- -->

                    <li class="list">
                        <a href="${pageContext.request.contextPath}/users/menu.jsp?page=0" class="nav-link">
                            <i class='bx bxs-food-menu icon'></i>
                            <span class="link">Men&ugrave;</span>
                        </a>
                    </li>
                    <li class="list">
                        <a href="${pageContext.request.contextPath}/users/listaCocktail.jsp?page=0&searchName=&searchFlavour=&searchIsAlcoholic=" class="nav-link">
                            <i class='bx bxs-drink icon' ></i>
                            <span class="link">Cocktails</span>
                        </a>
                    </li>
                </ul>

                <div class="bottom-content text-center">
                    <li class="list">
                        <a href="#" class="nav-link log_out">
                            <i class="bx bx-log-out icon"></i>
                            <span class="link">Logout</span>
                        </a>
                    </li>
                </div>
            </div>
        </div>
    </nav>
    <!-- CUSTOM JS SCRIPTS -->
    <script src="${pageContext.request.contextPath}/script/navBarJs.js"></script>
    <script src="${pageContext.request.contextPath}/script/utils.js"></script>
    <script src="${pageContext.request.contextPath}/script/logoutJS.js"></script>
    <script src="${pageContext.request.contextPath}/script/loadUserSessionCredentialsJS.js"></script>
    <!-- MOSTRA LE OPZIONI IN BASE AI RUOLI DELL'UTENTE -->
    <script>
        if(roleList.includes("MANAGER"))
        {
            $(".manager").toggle("show");
        }
        if(roleList.includes("BARTENDER"))
        {
            $(".bartender").toggle("show");
        }
        if(roleList.includes("WAITER"))
        {
            $(".waiter").toggle("show");
        }
    </script>
</body>
</html>

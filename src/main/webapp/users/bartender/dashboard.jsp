<html>
<head>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/favicon.ico"/>
    <title>Banco</title>
    <link rel="apple-touch-icon" sizes="180x180" href="${pageContext.request.contextPath}/img/favicon/apple-touch-icon.png">
    <link rel="icon" type="image/png" sizes="32x32" href="${pageContext.request.contextPath}/img/favicon/favicon-32x32.png">
    <link rel="icon" type="image/png" sizes="16x16" href="${pageContext.request.contextPath}/img/favicon/favicon-16x16.png">
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/img/favicon.ico">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.1/jquery.min.js" integrity="sha512-aVKKRRi/Q/YV+4mjoKBsE4x3H+BkegoM/em46NNlCqNTmUYADjBbeNefNxYV7giUp0VxICtqdrbqU7iVaeZNXA==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <!-- JavaScript Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/jquery.session@1.0.0/jquery.session.min.js"></script>
    <!-- CSS -->
    <link href="${pageContext.request.contextPath}/style/dashboardStyle.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/style/base.css" rel="stylesheet">
</head>
<body>
    <%@include file="../../navBar.jsp"%>
    <!--Controlla il ruolo dell'utente prima di mostrare la pagina -->
    <script>
        $(document).ready(function() {
            loadBanco()
        });
        if(roleList.includes("WAITER") && !roleList.includes("BARTENDER") || !roleList.includes("MANAGER"))
        {
            logout();
        }
    </script>
    <div class="title">
        <h1 class="h1" id="title"></h1>
    </div>

    <div class="container-fluid p-4">

            <div class="content"><div class="error"></div>

                <div class="row row-cols-sm-12 row-cols-md-12 row-cols-lg-12 row-cols-xl-12 gx-1 gy-4 containerTables mt-3">
                        <div class="col-12 item-list"></div>
                    </div>
            </div>
    </div>
<script src="${pageContext.request.contextPath}/script/utils.js"></script>
<script src="${pageContext.request.contextPath}/script/ordinationJS.js"></script>
</body>
</html>

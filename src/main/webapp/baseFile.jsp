<html>
<head>
    <title>Titolo</title>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.1/jquery.min.js" integrity="sha512-aVKKRRi/Q/YV+4mjoKBsE4x3H+BkegoM/em46NNlCqNTmUYADjBbeNefNxYV7giUp0VxICtqdrbqU7iVaeZNXA==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <!-- JavaScript Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/jquery.session@1.0.0/jquery.session.min.js"></script>
    <!-- CSS -->
    <link href="${pageContext.request.contextPath}/style/stile.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/style/base.css" rel="stylesheet">
</head>
<body>
    <%@include file="navBar.jsp"%>
    <!--Controlla il ruolo dell'utente prima di mostrare la pagina -->
    <script>
        if(roleList.includes("RUOLO"))
        {
            logout();
        }
    </script>

    <div class="title">
        <h1 class="h1" id="title"></h1>
    </div>

    <div class="container-fluid p-4">
        <div class="content">
            <div class="row gx-4 containerTables px-3">
                INSERIRE TUTTO IL BODY
            </div>
        </div>
    </div>
<script src="${pageContext.request.contextPath}/script/utils.js"></script>
</body>
</html>

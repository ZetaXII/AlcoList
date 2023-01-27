<!DOCTYPE html>
<html>
<head>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/favicon.ico"/>
    <!-- JavaScript AJAX JQUERY & OTHERS SCRIPT -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.1/jquery.min.js" integrity="sha512-aVKKRRi/Q/YV+4mjoKBsE4x3H+BkegoM/em46NNlCqNTmUYADjBbeNefNxYV7giUp0VxICtqdrbqU7iVaeZNXA==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <script src="https://cdn.jsdelivr.net/npm/jquery.session@1.0.0/jquery.session.min.js"></script>
    <!-- CSS & Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <link href="../style/loginStyle.css" rel="stylesheet">
    <link href="../style/base.css" rel="stylesheet">
    <!-- Page Title -->
    <title>Error 404</title>
</head>
<body>
    <nav>
        <div class="logo">
            <i class='bx bx-menu menu-icon'></i>
            <span class="logo-name">AlcoList</span>
        </div>
    </nav>
    <div class="container mt-5 pt-5">
        <div class="row justify-content-center">
            <div class="col-xl-5 col-md-8 p-6" style="width:90%; margin-top:10%;">
                <div class="card b-2 form-login">
                    <div class="card-body p-5" style="height: 300px;">
                        <h1 class="h1 text-center card-title mb-4 mt-5"><b>ERROR 404!</b></h1>
                        <h3 class="h3 text-center card-title">La pagina non &egrave; stata trovata <a href="${pageContext.request.contextPath}/index.jsp">torna all'inizio</a></h3>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>

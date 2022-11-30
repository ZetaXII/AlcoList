<html>
<head>
    <title>Title</title>
    <link href="style/base.css" rel="stylesheet">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.1/jquery.min.js" integrity="sha512-aVKKRRi/Q/YV+4mjoKBsE4x3H+BkegoM/em46NNlCqNTmUYADjBbeNefNxYV7giUp0VxICtqdrbqU7iVaeZNXA==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <!-- JavaScript Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/jquery.session@1.0.0/jquery.session.min.js"></script>
</head>
<body>
    <%@include file="navBar.jsp"%>
    <div class="container home-section">
        <h1 class="h1 text-light hello">Benvenuto </h1>
    </div>
</body>
<script>
    $(document).ready(function()
    {
        let password= sessionStorage.getItem("password");
        let surname= sessionStorage.getItem("surname");
        let roles= sessionStorage.getItem("roles");
        let name= sessionStorage.getItem("name");
        let uuid= sessionStorage.getItem("uuid");
        let email= sessionStorage.getItem("email");
        $(".hello").append(surname+" "+name);
    });
</script>
</html>

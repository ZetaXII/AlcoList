<html>
<head>
    <title>Profilo</title>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.1/jquery.min.js" integrity="sha512-aVKKRRi/Q/YV+4mjoKBsE4x3H+BkegoM/em46NNlCqNTmUYADjBbeNefNxYV7giUp0VxICtqdrbqU7iVaeZNXA==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <!-- JavaScript Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/jquery.session@1.0.0/jquery.session.min.js"></script>
    <!-- CSS -->
    <link href="${pageContext.request.contextPath}/style/profile.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/style/base.css" rel="stylesheet">
</head>
<body>
<%@include file="../navBar.jsp"%>
<!--Controlla il ruolo dell'utente prima di mostrare la pagina -->
<script>
    $(document).ready(function()
    {
        let user_name = name.charAt(0).toUpperCase() + name.slice(1);
        const user_surname = surname.replace(/(^\w{1})|(\s+\w{1})/g, letter => letter.toUpperCase());
        let user_tot = user_name+" "+user_surname;
        $(".profile-title").text(user_tot);
        $(".role").text(mainRole);
        $(".email").text(email);
    });

    if(!roleList.includes("WAITER"))
    {
        //logout();
    }
</script>

<div class="title">
    <h1 class="h1" id="title"></h1>
</div>

<div class="container-fluid p-4">
    <div class="content">
        <div class="row row-cols-sm-1 row-cols-md-3 row-cols-lg-4 row-cols-xl-5 gx-1 gy-4 containerTables">
            <div class="col-md-12 col-lg-12 col-xl-12 mt-1">
                <div class="card profile p-4" style="background-color: var(--secondaryBlue); border-radius: 30px;">
                    <div class="row profile" style="background-color: var(--secondaryBlue); border-radius: 30px;">
                        <div class="col-xl-4 col-lg-4 col-md-4 mt-4 mb-4">
                            <img style="width: 100px; border-radius: 50%" src="https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_1280.png">
                        </div>
                        <div class="col-xl-8 col-lg-8 col-md-8">
                            <div class="card-body mt-4 mb-4">
                                <h5 class="card-title profile-title"></h5>
                                <div class="user-profile fs-6">
                                    <ul class="pt-3 px-3">
                                        <li><span style="color:var(--lightBlue)">Email: </span><span class="email"></span></li>
                                        <li><span class="badge user-tag role"></span></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="${pageContext.request.contextPath}/script/utils.js"></script>
<script src="${pageContext.request.contextPath}/script/loadUserSessionCredentialsJS.js"></script>
</body>
</html>

<%int i=0;%>
<html>
<head>
    <title>Personale</title>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.1/jquery.min.js" integrity="sha512-aVKKRRi/Q/YV+4mjoKBsE4x3H+BkegoM/em46NNlCqNTmUYADjBbeNefNxYV7giUp0VxICtqdrbqU7iVaeZNXA==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <!-- JavaScript Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/jquery.session@1.0.0/jquery.session.min.js"></script>
    <!-- CSS -->
    <link href="${pageContext.request.contextPath}/style/profile.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/style/infoCocktailStyle.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/style/base.css" rel="stylesheet">
</head>
<body>
<%@include file="../../navBar.jsp"%>
<!--Controlla il ruolo dell'utente prima di mostrare la pagina -->
<script>
    function redirectNewUser(){
        window.location.href = $("#contextPath").val()+'/users/manager/addUser.jsp';
    }

    if(!roleList.includes("MANAGER") && (roleList.includes("WAITER") || roleList.includes("BARTENDER")) )
    {
        logout();
    }
</script>

<div class="title">
    <h1 class="h1" id="title"></h1>
</div>

<div class="container-fluid p-4">
    <div class="content">
        <div class="row justify-content-end mb-6">
            <div class="col-8"></div>
            <div class="col-4 addUser card-body" style="margin-top: -60px;">
                <button class="btn-modifyCocktail" style="padding-left: 20px;padding-right: 20px" onclick="redirectNewUser()">NUOVO DIPENDENTE</button>

            </div>
        </div>
        <div class="append-user" value=""></div>
        <!--<div class="card mb-4" onclick="goToInfo(this.id)" id="userRow" style="background-color: var(--secondaryBlue); border-radius: 30px;">
            <div class="row g-0 user-row" style="background-color: var(--secondaryBlue); border-radius: 30px;">
                <div class="col-md-3">
                    <img class="m-2 p-2" style="vertical-align: central; width:100px; border-radius: 50%" src="https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_1280.png" alt="profile-pic">
                </div>
                <div class="col-md-7">
                    <div class="card-body mt-2 mb-3">
                        <h5 class="card-title profile-title">NOME</h5>
                        <span class="role user-tag">RUOLO</span>
                    </div>
                </div>
                <div class="col-md-2">
                    <a class="forward-button" href="${pageContext.request.contextPath}/users/manager/infoUser.jsp/uuid=26aec96d-6e7a-4d63-9063-171b35a72526">
                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-chevron-right" viewBox="0 0 16 16">
                            <path fill-rule="evenodd" d="M4.646 1.646a.5.5 0 0 1 .708 0l6 6a.5.5 0 0 1 0 .708l-6 6a.5.5 0 0 1-.708-.708L10.293 8 4.646 2.354a.5.5 0 0 1 0-.708z"/>
                        </svg>
                    </a>
                </div>
            </div>
        </div>-->
        </div>
    </div>
</div>
<script src="${pageContext.request.contextPath}/script/utils.js"></script>
<script src="${pageContext.request.contextPath}/script/loadUserSessionCredentialsJS.js"></script>
<script src="${pageContext.request.contextPath}/script/userJS.js"></script>
<script>
    appendUser(getAllUsers());
</script>
</body>
</html>

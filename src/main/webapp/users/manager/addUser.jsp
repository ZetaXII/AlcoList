<%--
  Created by IntelliJ IDEA.
  User: melaniaconte
  Date: 03/01/23
  Time: 09:51
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <title>Aggiungi User</title>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.1/jquery.min.js" integrity="sha512-aVKKRRi/Q/YV+4mjoKBsE4x3H+BkegoM/em46NNlCqNTmUYADjBbeNefNxYV7giUp0VxICtqdrbqU7iVaeZNXA==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <!-- JavaScript Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/jquery.session@1.0.0/jquery.session.min.js"></script>
    <!-- CSS -->
    <link href="${pageContext.request.contextPath}/style/infoCocktailStyle.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/style/base.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/style/profile.css" rel="stylesheet">
</head>
<body>
<%@include file="../../navBar.jsp"%>
<!--Controlla il ruolo dell'utente prima di mostrare la pagina -->
<script>
    if(!roleList.includes("WAITER"))
    {
        logout();
    }
</script>
<script>
    function selectedRole(id){
        let managerbtn = document.getElementById("MANAGER");
        let bartenderbtn = document.getElementById("BARTENDER");
        let waiterbtn = document.getElementById("WAITER");
        $(document).ready(function () {
            $('input[name="roleField"]').val(id);
        });
        switch (id) {
            case 'MANAGER':
                managerbtn.classList.add("badgeChecked");
                bartenderbtn.checked = false;
                bartenderbtn.classList.remove("badgeChecked");
                waiterbtn.checked = false;
                waiterbtn.classList.remove("badgeChecked");
                break;
            case 'BARTENDER':
                bartenderbtn.classList.add("badgeChecked");
                managerbtn.checked = false;
                managerbtn.classList.remove("badgeChecked");
                waiterbtn.checked = false;
                waiterbtn.classList.remove("badgeChecked");
                break;
            case 'WAITER':
                waiterbtn.classList.add("badgeChecked");
                bartenderbtn.checked = false;
                bartenderbtn.classList.remove("badgeChecked");
                managerbtn.checked = false;
                managerbtn.classList.remove("badgeChecked");
                break;
        }

    }




</script>

<div class="title">
    <h1 class="h1" id="title"></h1>
</div>

<div class="container-fluid p-4">
    <div class="content">
        <div class="row row-cols-sm-1 row-cols-md-3 row-cols-lg-4 row-cols-xl-5 gx-1 gy-4 containerTables">
            <div class="col-md-12 col-lg-12 col-xl-12 mt-1">
                <div class="card info-cocktail-panel mb-3 p-1" style="background-color: var(--secondaryBlue); border-radius: 30px;">
                    <div class="row g-0" style="background-color: var(--secondaryBlue); border-radius: 30px;">
                        <div class="col-md-4 text-center">
                            <a href="${pageContext.request.contextPath}/users/manager/listaUser.jsp"><i class='bx bx-chevron-left back-button'></i></a>
                            <img src="https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_1280.png" class="img-fluid p-4" style="border-radius: 50%" width="300px">
                        </div>
                        <div class="col-md-7">
                            <form class="card-body p-4 mt-4">
                                    <input type="text" id="nameField" class="addCocktailFields form-control py-2 mb-4" placeholder="Nome" maxlength="50" required>

                                    <input type="text" id="surnameField" class="addCocktailFields form-control py-2 mb-4" placeholder="Cognome" maxlength="255" required>
                                    <div class="cocktail-tags py-2 mb-3">
                                        <button type="button" class="badge badgeChecked" id="MANAGER" value="MANAGER" onclick="selectedRole(this.value)">MANAGER</button>
                                        <button type="button" class="badge" id="BARTENDER" value="BARTENDER" onclick="selectedRole(this.value)">BARTENDER</button>
                                        <button type="button" class="badge" id="WAITER" value="WAITER" onclick="selectedRole(this.value)">CAMERIERE</button>
                                        <!--<input type="radio" class="radio" checked="true" id="MANAGER" value="MANAGER" onclick="selectedRole(this.id)"><span>MANAGER</span></input>
                                        <input type="radio" class="radio" id="BARTENDER" onclick="selectedRole(this.id)"><span>BARTENDER</span></input>
                                        <input type="radio" class="radio" id="WAITER" onclick="selectedRole(this.id)"><span>CAMERIERE</span></input>
                                    --></div>
                                    <input type="text" name="roleField" id="roleField" hidden="hidden" value="MANAGER">
                                        <p class="card-text cocktail-description"><input type="email" id="emailField" class="addCocktailFields form-control py-2 mb-4" placeholder="Email" autocomplete="off" required></p>
                                        <p class="price"><input type="password" id="passwordField" class="addCocktailFields form-control py-2 mb-4" placeholder="Password" maxlength="50" autocomplete="off" required></p>
                                        <div class="text-center">
                                            <button class="btn btn-addCocktail px-2 m-2 w-50 text-center" style="border: none" onclick="addUser()">Aggiungi utente</button>
                                        </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="${pageContext.request.contextPath}/script/utils.js"></script>
<script src="${pageContext.request.contextPath}/script/getUser.js"></script>
<script src="${pageContext.request.contextPath}/script/loadUserSessionCredentialsJS.js"></script>
</body>
</html>


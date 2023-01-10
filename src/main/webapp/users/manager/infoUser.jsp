<%--
  Created by IntelliJ IDEA.
  User: melaniaconte
  Date: 03/01/23
  Time: 16:10
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
  <title id="profile-title1"></title>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.1/jquery.min.js" integrity="sha512-aVKKRRi/Q/YV+4mjoKBsE4x3H+BkegoM/em46NNlCqNTmUYADjBbeNefNxYV7giUp0VxICtqdrbqU7iVaeZNXA==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
  <!-- JavaScript Bundle with Popper -->
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
  <script src="https://cdn.jsdelivr.net/npm/jquery.session@1.0.0/jquery.session.min.js"></script>
  <!-- CSS -->
  <link href="${pageContext.request.contextPath}/style/infoCocktailStyle.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/style/profile.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/style/base.css" rel="stylesheet">
</head>
<body>
<%@include file="../../navBar.jsp"%>
<!--Controlla il ruolo dell'utente prima di mostrare la pagina -->
<script>

  function redirectEditUser(uuid)
  {
    window.location.href= $("#contextPath").val()+"/users/manager/editUser.jsp?uuid="+uuid;
  }

  $(document).ready(function()
  {
    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);
    let selectedUser = getUser(urlParams.get('uuid'));
    let user_name = selectedUser.name.charAt(0).toUpperCase() + selectedUser.name.slice(1);
    const user_surname = selectedUser.surname.replace(/(^\w{1})|(\s+\w{1})/g, letter => letter.toUpperCase());
    let user_tot = user_name+" "+user_surname;
    let user_uuid = selectedUser.uuid
    $("#uuidEdit").val(user_uuid)
    $("#uuidDelete").val(user_uuid)
    $("#profile-title1").text(user_tot);
    $("#profile-title2").text(user_tot);
    let user_roleList = JSON.stringify(selectedUser.roles);
    if(user_roleList.includes("MANAGER"))
    {
      $(".ruoli").append("<span class=\"my-4 user-tag role\">MANAGER</span>");
    }
    if(user_roleList.includes("BARTENDER"))
    {
      $(".ruoli").append("<span class=\"my-4 user-tag role\">BARTENDER</span>");
    }
    if(user_roleList.includes("WAITER"))
    {
      $(".ruoli").append("<span class=\"my-4 user-tag role\">WAITER</span>");
    }
    $(".email").text(selectedUser.email);
  });

  if(!roleList.includes("MANAGER"))
  {
    logout();
  }
/*
  function toggleIsInEditMode(isInEditingMode){
    console.log("ISEDIT E': "+isInEditingMode)
    isInEditingMode = !isInEditingMode
    console.log("ORA ISEDIT E': "+isInEditingMode)
    let textFields = document.getElementsByClassName('edit')
    if (isInEditingMode){
      console.log("AGGIUNGO DISABLED A: ")
      console.log(textFields)
      for (i of textFields){
        textFields[i].setAttribute('disabled','')
      }
    } else {
      console.log("RIMUOVO DISABLED")
      console.log(textFields)
      for (i of textFields){
        textFields[i].removeAttribute('disabled')
      }
    }
  }
*/
</script>

<div class="title">
  <h1 class="h1" id="profile-title2"></h1>
</div>

<div class="container-fluid p-4">
  <div class="content">
    <div class="row gx-4 containerTables px-3">
      <div class="col-9"></div>
      <div class="col-3 mb-5"> <button id="uuidEdit" value="" class="badge-user btn btn-view" onclick="redirectEditUser(value)">EDIT</button><button id="uuidDelete" value="" class="badge-user btn btn-view" onclick="deleteUser(value)">DELETE</button></div>
    </div>
    <div class="row row-cols-sm-1 row-cols-md-3 row-cols-lg-4 row-cols-xl-5 gx-1 gy-4 containerTables">
      <div class="col-md-12 col-lg-12 col-xl-12 mt-1">
        <div class="card profile p-4" style="background-color: var(--secondaryBlue); border-radius: 30px;">
          <div class="row profile" style="background-color: var(--secondaryBlue); border-radius: 30px;">
            <div class="col-xl-4 col-lg-4 col-md-4 mt-4 mb-4">
              <img style="width: 200px; border-radius: 50%" src="https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_1280.png">
            </div>
            <div class="col-xl-8 col-lg-8 col-md-8 mt-4 mb-4">
              <div class="card-body mt-4 mb-4">
                <h5 class="card-title profile-title user-name"></h5>
                <div class="user-profile fs-6">
                  <ul class="pt-3 px-3">
                    <li><span style="color:var(--lightBlue)">Email: </span><span class="email user-email"></span></li>
                    <li class="ruoli"><span style="color:var(--lightBlue)">Ruolo: </span></li>
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
<script src="${pageContext.request.contextPath}/script/userJS.js"></script>
</body>
</html>

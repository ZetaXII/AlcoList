<%--
  Created by IntelliJ IDEA.
  User: melaniaconte
  Date: 13/01/23
  Time: 11:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Ordinazioni</title>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.1/jquery.min.js" integrity="sha512-aVKKRRi/Q/YV+4mjoKBsE4x3H+BkegoM/em46NNlCqNTmUYADjBbeNefNxYV7giUp0VxICtqdrbqU7iVaeZNXA==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
  <!-- JavaScript Bundle with Popper -->
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
  <script src="https://cdn.jsdelivr.net/npm/jquery.session@1.0.0/jquery.session.min.js"></script>
  <!-- CSS -->
  <link href="${pageContext.request.contextPath}/style/stile.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/style/base.css" rel="stylesheet">
</head>
<body>
<%@include file="../../navBar.jsp"%>
<!--Controlla il ruolo dell'utente prima di mostrare la pagina -->
<script>
  $(document).ready(function() {
    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);
    let tableUuid = urlParams.get('uuid');
    console.log("UUID TAVOLO: "+tableUuid)
    $("#uuidTavoloAdd").val(tableUuid)
    $("#uuidTavoloEdit").val(tableUuid)
  });
</script>

<div class="title">
  <h1 class="h1" id="title"></h1>
</div>

<div class="container-fluid p-4">
  <div class="content">
    <div class="row mt-5 gy-4 containerTables px-3">
      <div class="col d-flex justify-content-center text-center" >
        <div class="card cardTable" id="uuidTavoloAdd" value="" onclick="addComanda(this.value)">
          <div class="card-body">
             <h5 class="card-title mt-2">&nbsp;</h5>
             <p class="card-text h1 mt-5">NUOVA COMANDA</p>
             </div>
          <div class="d-flex justify-content-center">
            </div>
        </div>
      </div>
      <div class="col d-flex justify-content-center text-center" >
        <div class="card cardTable" id="uuidTavoloEdit" value="" onclick="redirectToListOrdinations(this.value)">
          <div class="card-body">
            <h5 class="card-title mt-2">&nbsp;</h5>
            <p class="card-text h1 mt-5">MODIFICA COMANDA</p>
          </div>
          <div class="d-flex justify-content-center">
          </div>
        </div>
      </div>
    </div>
  </div>
</div>

<style>
  .cardTable
  {
    height: 18rem;
    width: 18rem;
    color: var(--white);
    background-color: var(--secondaryBlue);
    border: 2px solid var(--lightBlue);
  }
  .cardTable:hover
  {
    background-color: var(--primaryBlue);
    cursor: pointer;
    transition: 0.3s;
  }

</style>
<script src="${pageContext.request.contextPath}/script/utils.js"></script>
<script src="${pageContext.request.contextPath}/script/ordinationJS.js"></script>
<script src="${pageContext.request.contextPath}/script/tableJS.js"></script>
</body>
</html>

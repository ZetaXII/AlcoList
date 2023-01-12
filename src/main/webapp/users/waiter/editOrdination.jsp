<%--
  Created by IntelliJ IDEA.
  User: melaniaconte
  Date: 11/01/23
  Time: 09:11
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
  <title>Comanda Tavolo</title>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.1/jquery.min.js" integrity="sha512-aVKKRRi/Q/YV+4mjoKBsE4x3H+BkegoM/em46NNlCqNTmUYADjBbeNefNxYV7giUp0VxICtqdrbqU7iVaeZNXA==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
  <!-- JavaScript Bundle with Popper -->
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
  <script src="https://cdn.jsdelivr.net/npm/jquery.session@1.0.0/jquery.session.min.js"></script>
  <!-- CSS -->
  <link href="${pageContext.request.contextPath}/style/base.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/style/infoCocktailStyle.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/style/listaStyle.css" rel="stylesheet">
</head>
<body>
<%@include file="../../navBar.jsp"%>
<!--Controlla il ruolo dell'utente prima di mostrare la pagina -->
<script>
  var comandaDict = JSON.parse(localStorage.getItem("ComandaDict") ?? "{}")
  console.log("START: "+ JSON.stringify(comandaDict))
  $(document).ready(function() {
    printFlavoursList();
    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);
    let page = parseInt(urlParams.get('page'));
    let tableUuid = urlParams.get('uuid');
    $("#inviaComanda").val(tableUuid)
    //paginatedMenu(6,page)
    paginatedCocktailList(6,page);
    //let selectedTable = urlParams.get('uuid');
  });
</script>

<div class="title">
  <h1 class="h1" id="title"></h1>
</div>

<div class="container-fluid p-4">
  <div class="content">

    <div class="flex-row gx-4 containerTables px-3">
      <div class="d-flex justify-content-end">
        <button id="inviaComanda" value="" class="badge-user btn btn-view" onclick="updateStatus(this.value,uuid)">Aggiorna comanda</button>
      </div>
    </div>

    <div class="row gx-4 containerTables px-3">

      <!--bottoni switch pagina-->
      <div class="col-12 pageSwitch text-center" style="display: flex;align-items: center;justify-content: center; color: #ebc23b">
        <button class="btn prevPage" onclick="previousPageCocktail()"><i class='bx bx-chevron-left prevPage'></i></button>
        <span class="pageNumber"></span>
        <button class="btn nextPage" onclick="nextPageCocktail()"><i class='bx bx-chevron-right nextPage' ></i></button>
      </div>

      <div class="col-12 item-list"></div>
    </div>
  </div>
</div>
<script src="${pageContext.request.contextPath}/script/utils.js"></script>
<script src="${pageContext.request.contextPath}/script/editOrdination.js"></script>
</body>
</html>

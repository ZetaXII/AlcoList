<html>
<head>
    <title>Tavoli</title>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.1/jquery.min.js" integrity="sha512-aVKKRRi/Q/YV+4mjoKBsE4x3H+BkegoM/em46NNlCqNTmUYADjBbeNefNxYV7giUp0VxICtqdrbqU7iVaeZNXA==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <script src="https://cdn.jsdelivr.net/npm/jquery.session@1.0.0/jquery.session.min.js"></script>
    <!-- CSS -->
    <link href="${pageContext.request.contextPath}/style/base.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/style/comandeCSS.css" rel="stylesheet">
</head>
<body>
<%@include file="../../navBar.jsp"%>
<script>
    $(document).ready(function()
    {
        let tables = getAllTables().sort((a,b)=> (a.number>b.number) ? 1 : (b.number>a.number) ? -1 : 0);
        for(let i=0; i<tables.length; i++) {
            let table
            let stato
            let ordinationsForTable = getOrdinationForTable(tables[i].uuid)
            let ordination = ordinationsForTable[0]
            if (ordination!==undefined){
                console.log(ordination.status)
                stato = ordination.status
            }
            if (ordination!==undefined && !tables[i].isFree && stato === "PENDING") {
                table = '<div class="col d-flex justify-content-center text-center" value="'+uuid+'" id="'+tables[i].uuid+'" onclick="updateComanda(id,this.value)" >' +
                    '<div class="card cardTableEditable"> ' +
                    '<div class="card-body">' +
                    '<h5 class="card-title mt-2" style="font-size: 16px; color: #ebc23b">'+stato+'&nbsp;</h5>' +
                    '<p class="card-text h1 mt-5">' + tables[i].number + '</p>' +
                    '</div>' +
                    '<div class="d-flex justify-content-center">' +
                    '</div>'+
                    '</div>'
            } else if (ordination!==undefined && !tables[i].isFree && stato === "WORK_IN_PROGRESS") {
                table = '<div class="col d-flex justify-content-center text-center" value="' + uuid + '" id="' + tables[i].uuid + '">' +
                    '<div class="card cardTableDisabled"> ' +
                    '<div class="card-body">' +
                    '<h5 class="card-title mt-2" style="font-size: 16px; color: #F93C3C">WORK IN PROGRESS&nbsp;</h5>' +
                    '<p class="card-text h1 mt-5">' + tables[i].number + '</p>' +
                    '</div>' +
                    '<div class="d-flex justify-content-center">' +
                    '</div>' +
                    '</div>'
            } else if (ordination!==undefined && !tables[i].isFree && stato === "COMPLETED") {
                table = '<div class="col d-flex justify-content-center text-center" id="'+tables[i].uuid+'" onclick="updateStatusToDelivered(id,uuid)" >' +
                    '<div class="card cardTableCompleted"> ' +
                    '<div class="card-body">' +
                    '<h5 class="card-title mt-2" style="font-size: 16px; color: limegreen">'+stato+'&nbsp;</h5>' +
                    '<p class="card-text h1 mt-5">' + tables[i].number + '</p>' +
                    '</div>' +
                    '<div class="d-flex justify-content-center">' +
                    '</div>'+
                    '</div>'
            } else if (ordination!==undefined && !tables[i].isFree && stato === "DELIVERED") {
                table = '<div class="col d-flex justify-content-center text-center" id="'+tables[i].uuid+'" onclick="updateStatusToEnded(id,uuid)" >' +
                    '<div class="card cardTableCompleted"> ' +
                    '<div class="card-body">' +
                    '<h5 class="card-title mt-2" style="font-size: 16px; color: limegreen">'+stato+'&nbsp;</h5>' +
                    '<p class="card-text h1 mt-5">' + tables[i].number + '</p>' +
                    '</div>' +
                    '<div class="d-flex justify-content-center">' +
                    '</div>'+
                    '</div>'
            } else {
                table = '<div class="col d-flex justify-content-center text-center" value="'+uuid+'" id="'+tables[i].uuid+'" onclick="addComanda(id,this.value)" >' +
                    '<div class="card cardTable"> ' +
                    '<div class="card-body">' +
                    '<h5 class="card-title mt-2">&nbsp;</h5>' +
                    '<p class="card-text h1 mt-5">' + tables[i].number + '</p>' +
                    '</div>' +
                    '<div class="d-flex justify-content-center">' +
                    '</div>'+
                    '</div>'
            }
            $(".containerTables").append(table)
        }
    });



    function getStatus()
    {
        return ["CREATED", "PENDING", "WORK IN PROGRESS", "COMPLETED", "DELIVERED", "ENDED"];
    }
    function printStatusList()
    {
        let s=getStatus();
        for(let i=0; i<s.length; i++)
        {
            console.log(s[i])
            $('.selectStatus').append("<option value="+s[i]+" id="+i+">"+s[i]+"</option>");
        }
    }

    if(!roleList.includes("WAITER"))
    {
        logout();
    }
</script>
    <div class="title">
        <h1 class="h1">Dashboard</h1>
    </div>
    <div class="container-fluid">
        <div class="content">
            <div class="row row-cols-sm-1 row-cols-md-3 row-cols-lg-4 row-cols-xl-5 gx-1 gy-4 containerTables">

            </div>
        </div>
    </div>
    <script>

    </script>
    <style>
        .cardTable
        {
            height: 16rem;
            width: 16rem;
            color: var(--white);
            background-color: var(--secondaryBlue);
            border: 2px solid var(--lightBlue);
        }

        .cardTableEditable
        {
            height: 16rem;
            width: 16rem;
            color: var(--white);
            background-color: var(--secondaryBlue);
            border: 2px solid #ebc23b;
        }

        .cardTable:hover, .cardTableEditable:hover, .cardTableCompleted:hover
        {
            background-color: var(--primaryBlue);
            cursor: pointer;
            transition: 0.3s;
        }

        .cardTableDisabled {
            height: 16rem;
            width: 16rem;
            color: #F93C3C;
            background-color: var(--secondaryBlue);
            border: 2px solid #F93C3C;
        }

        .cardTableCompleted {
            height: 16rem;
            width: 16rem;
            color: limegreen;
            background-color: var(--secondaryBlue);
            border: 2px solid limegreen;
        }
    </style>

<script src="${pageContext.request.contextPath}/script/utils.js"></script>
<script src="${pageContext.request.contextPath}/script/tableJS.js"></script>
<script src="${pageContext.request.contextPath}/script/ordinationJS.js"></script>
<script>
    printStatusList()
</script>
</body>
</html>

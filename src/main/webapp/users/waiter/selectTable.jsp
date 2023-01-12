<html>
<head>
    <title>Le mie info</title>
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
        $(document).ready(function()
        {
            let tables = getAllTables().sort((a,b)=> (a.number>b.number) ? 1 : (b.number>a.number) ? -1 : 0);
            for(let i=0; i<tables.length; i++) {
                let table
                if (tables[i].isFree) {
                    table = '<div class="col d-flex justify-content-center text-center" value="'+uuid+'" id="'+tables[i].uuid+'" onclick="addComanda(id,this.value)" >' +
                        '<div class="card cardTable"> ' +
                        '<div class="card-body">' +
                        '<h5 class="card-title mt-2">&nbsp;</h5>' +
                        '<p class="card-text h1 mt-5">' + tables[i].number + '</p>' +
                        '</div>' +
                        '<div class="d-flex justify-content-center">' +
                        '<select class="selectStatus px-3" id="statusField" style="z-index: 9999"></select>' +
                        '</div>'+
                        '</div>'
                } else {
                    table = '<div class="col d-flex justify-content-center text-center" value="'+uuid+'" id="'+tables[i].uuid+'" onclick="addComanda(id,this.value)" >' +
                        '<div class="card cardTableDisabled"> ' +
                        '<div class="card-body">' +
                        '<h5 class="card-title mt-2">&nbsp;</h5>' +
                        '<p class="card-text h1 mt-5">' + tables[i].number + '</p>' +
                        '</div>' +
                        '<div class="d-flex justify-content-center">' +
                        '<select class="selectStatus px-3" id="flavourField" style="z-index: 9999"></select>'+
                        '</div>'+
                        '</div>'
                }
                $(".containerTables").append(table)

            }
        });
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

        .cardTable:hover
        {
            color: var(--yellow);
            border-color: var(--yellow);
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
    </style>

<script src="${pageContext.request.contextPath}/script/utils.js"></script>
<script src="${pageContext.request.contextPath}/script/tableJS.js"></script>
<script src="${pageContext.request.contextPath}/script/ordinationJS.js"></script>
<script>
    printStatusList()
</script>
</body>
</html>

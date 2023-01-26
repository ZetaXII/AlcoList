
function getAllTables(){
    let t=[];
    $.ajax({
        async: false,
        method: "GET",
        crossDomain: true,
        url:"http://localhost:8090/manage-tables/getAll/",
        contentType: "application/json; charset=utf-8",
        dataType: "json",

        success:function(table)
        {
            t=table;
        },
        error: function(error)
        {
            $(".error").html("<div class=\"alert alert-danger alert-dismissible fade show\" role=\"alert\"><strong>ERRORE! </strong>"+error.responseText+".</div>");
        }
    });
    return t;
}
function updateComanda(uuid){
    let queryParams = new URLSearchParams({
        uuid: uuid,
        page: 0
    })
    window.location.href= $("#contextPath").val()+"/users/waiter/editOrdination.jsp?"+queryParams.toString();
}
function redirectToListOrdinations(uuidTable){
    window.location.href= $("#contextPath").val()+"/users/waiter/listaComandeTable.jsp?uuid="+uuidTable;
}

function updateCompletedComandaToDelivered(tableUuid,userUuid){
        confirm("Aggiornare lo stato della comanda a 'DELIVERED'?")
        updateStatusToDelivered(tableUuid,userUuid,"DELIVERED")
        window.location.href= $("#contextPath").val()+"/users/waiter/selectTable.jsp";
}

function updateDeliveredComanda(tableUuid,userUuid){
    updateStatus(tableUuid,userUuid,"ENDED")
    // TODO CHANGE TABLE ISFREE
    window.location.href= $("#contextPath").val()+"/users/waiter/selectTable.jsp";
}

function addComanda(uuid){
    localStorage.removeItem("ComandaDict")
    let queryParams = new URLSearchParams({
        uuid: uuid,
        page: 0
    })
    window.location.href= $("#contextPath").val()+"/users/waiter/addOrdination.jsp?"+queryParams.toString();
}

function redirectEditOrdination(uuid){
    window.location.href= $("#contextPath").val()+"/users/waiter/editOrdination.jsp?uuid="+uuid;
}

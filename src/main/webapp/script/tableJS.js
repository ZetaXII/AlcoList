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
            alert("generic error"+ error);
        }
    });
    return t;
}

function addComanda(uuid){
    localStorage.removeItem("ComandaDict")
    let queryParams = new URLSearchParams({
        uuid: uuid,
        page: 0
    })
    window.location.href= $("#contextPath").val()+"/users/waiter/addOrdination.jsp?"+queryParams.toString();
}
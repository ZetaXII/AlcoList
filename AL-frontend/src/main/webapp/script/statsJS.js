function getBestSellingCocktails()
{
    let cocktailsName=[];
    let cocktailsValue=[];

    $.ajax({
        async: false,
        method: "POST",
        crossDomain: true,
        url:"http://localhost:8090/manage-statistics/getBestSellingCocktails?limit=5",
        contentType: "application/json; charset=utf-8",
        dataType: "json",

        success:function(result)
        {
            cocktailsName = [result[0].name,result[1].name,result[2].name,result[3].name,result[4].name];
            cocktailsValue = [result[0].sold,result[1].sold,result[2].sold,result[3].sold,result[4].sold];
        },
        error: function(error)
        {
            $(".error").html("<div class=\"alert alert-danger alert-dismissible fade show\" role=\"alert\"><strong>ERRORE! </strong>"+error.responseText+".</div>");
        }
    });

    new Chart(document.getElementById("bar-chart"), {
        type: 'bar',
        data: {
            labels: [cocktailsName[0],cocktailsName[1],cocktailsName[2],cocktailsName[3],cocktailsName[4]],
            datasets: [
                {
                    label: "Cocktail venduti",
                    backgroundColor: ["#3e95cd", "#8e5ea2","#3cba9f","#e8c3b9","#c45850"],
                    data: [cocktailsValue[0],cocktailsValue[1],cocktailsValue[2],cocktailsValue[3],cocktailsValue[4]],
                    borderColor: 'white',
                    color: 'white',
                    borderWidth: 2,
                    fill: false,
                }
            ]
        },
        options: {
            legend: { display: false },
            title: {
                display: true,
                text: 'Cocktails pi\u016B venduti'
            },
            scales: {
                xAxes: [{
                    gridLines: {
                        //color: "#eaeaea",
                        lineWidth: 0.5
                    }
                }],
                yAxes: [{
                    gridLines: {
                        //color: "#eaeaea",
                        lineWidth: 0.5
                    }
                }]
            },
        }
    });
}

function getBestSellingCocktailsByFlavour()
{
    let cocktailsName=[];
    let cocktailsValue=[];

    $.ajax({
        async: false,
        method: "POST",
        crossDomain: true,
        url:"http://localhost:8090/manage-statistics/getBestSellingCocktailsByFlavour",
        contentType: "application/json; charset=utf-8",
        dataType: "json",

        success:function(result)
        {
            cocktailsName = ["Amaro","Aspro","Dolce","Secco"];
            cocktailsValue = [result[0].sold,result[1].sold,result[2].sold,result[3].sold];
        },
        error: function(error)
        {
            $(".error").html("<div class=\"alert alert-danger alert-dismissible fade show\" role=\"alert\"><strong>ERRORE! </strong>"+error.responseText+".</div>");
        }
    });

    new Chart(document.getElementById("pie-chart"), {
        type: 'pie',
        data: {
            labels: [cocktailsName[0],cocktailsName[1],cocktailsName[2],cocktailsName[3]],
            datasets: [{
                label: "Fragranze più richieste",
                backgroundColor: ["#3e95cd", "#8e5ea2","#3cba9f","#c45850"],
                data: [cocktailsValue[0],cocktailsValue[1],cocktailsValue[2],cocktailsValue[3]]
            }]
        },
        options: {
            title: {
                display: true,
                text: "Fragranze più richieste"
            }
        }
    });
}

function getNumbersOfUsers(role)
{
    let returnRole=0;
    $.ajax({
        async: false,
        method: "GET",
        crossDomain: true,
        url:"http://localhost:8090/manage-statistics/getNumbersOfUsers/"+role,
        contentType: "application/json; charset=utf-8",
        dataType: "json",

        success:function(result)
        {
            returnRole=result;
            //window.location.href=$("#contextPath").val()+"/users/bartender/aggiungiIngredientiCocktail.jsp?uuid="+uuidCocktail;
        },
        error: function(error)
        {
            $(".error").html("<div class=\"alert alert-danger alert-dismissible fade show\" role=\"alert\"><strong>ERRORE! </strong>"+error.responseText+".</div>");
        }
    });
    return returnRole;
}

function getNumbersOfCreatedByUserUuid()
{
    let users;
    let countRes=0;
    $.ajax({
        async: false,
        method: "GET",
        crossDomain: true,
        url:"http://localhost:8090/manage-users/getAll",
        contentType: "application/json; charset=utf-8",
        dataType: "json",

        success:function(result)
        {
            users=result;
        },
        error: function(error)
        {
            $(".error").html("<div class=\"alert alert-danger alert-dismissible fade show\" role=\"alert\"><strong>ERRORE! </strong>"+error.responseText+".</div>");
        }
    });

    for(i in users)
    {
        $.ajax({
            async: false,
            method: "GET",
            crossDomain: true,
            url:"http://localhost:8090/manage-statistics/getNumbersOfCreatedByUserUuid/"+users[i].uuid,
            contentType: "application/json; charset=utf-8",
            dataType: "json",

            success:function(result)
            {
                if(result>0)
                {
                    $(".lista-migliori-camerieri-ordini-creati").append("<span class='my-1 p-3' style='background-color: var(--primaryBlue); border-radius: 8px;'><span style='color: var(--lightBlue);'>"+users[i].name+" "+users[i].surname+"</span>: "+result+" ordini creati</span>");
                    countRes++;
                }
            },
            error: function(error)
            {
                $(".error").html("<div class=\"alert alert-danger alert-dismissible fade show\" role=\"alert\"><strong>ERRORE! </strong>"+error.responseText+".</div>");
            }
        });
    }

    if(countRes<=0)
    {
        $(".lista-migliori-camerieri-ordini-creati").append("<span class='my-1 p-3 text-center'>Nessun cameriere ha creato ordini</span>");
    }
}

function getNumbersOfExecutedByUserUuid()
{
    let users;
    let countRes=0;
    $.ajax({
        async: false,
        method: "GET",
        crossDomain: true,
        url:"http://localhost:8090/manage-users/getAll",
        contentType: "application/json; charset=utf-8",
        dataType: "json",

        success:function(result)
        {
            users=result;
        },
        error: function(error)
        {
            $(".error").html("<div class=\"alert alert-danger alert-dismissible fade show\" role=\"alert\"><strong>ERRORE! </strong>"+error.responseText+".</div>");
        }
    });

    for(i in users)
    {
        $.ajax({
            async: false,
            method: "GET",
            crossDomain: true,
            url:"http://localhost:8090/manage-statistics/getNumbersOfExecutedByUserUuid/"+users[i].uuid,
            contentType: "application/json; charset=utf-8",
            dataType: "json",

            success:function(result)
            {
                if(result>0)
                {
                    $(".lista-migliori-bartender-ordini-eseguiti").append("<span class='my-1 p-3' style='background-color: var(--primaryBlue); border-radius: 8px;'><span style='color: var(--lightBlue);'>"+users[i].name+" "+users[i].surname+"</span>: "+result+" ordini realizzati</span>");
                    countRes++;
                }
            },
            error: function(error)
            {
                $(".error").html("<div class=\"alert alert-danger alert-dismissible fade show\" role=\"alert\"><strong>ERRORE! </strong>"+error.responseText+".</div>");
            }
        });
    }

    if(countRes<=0)
    {
        $(".lista-migliori-bartender-ordini-eseguiti").append("<span class='my-1 p-3 text-center'>Nessun bartender ha realizzato ordini</span>");
    }
}

function getNumbersOfDeliveredByUserUuid()
{
    let users;
    let countRes=0;
    $.ajax({
        async: false,
        method: "GET",
        crossDomain: true,
        url:"http://localhost:8090/manage-users/getAll",
        contentType: "application/json; charset=utf-8",
        dataType: "json",

        success:function(result)
        {
            users=result;
        },
        error: function(error)
        {
            $(".error").html("<div class=\"alert alert-danger alert-dismissible fade show\" role=\"alert\"><strong>ERRORE! </strong>"+error.responseText+".</div>");
        }
    });

    for(i in users)
    {
        $.ajax({
            async: false,
            method: "GET",
            crossDomain: true,
            url:"http://localhost:8090/manage-statistics/getNumbersOfDeliveredByUserUuid/"+users[i].uuid,
            contentType: "application/json; charset=utf-8",
            dataType: "json",

            success:function(result)
            {
                if(result>
                    0)
                {
                    $(".lista-migliori-camerieri-ordini-consegnati").append("<span class='my-1 p-3' style='background-color: var(--primaryBlue); border-radius: 8px;'><span style='color: var(--lightBlue);'>"+users[i].name+" "+users[i].surname+"</span>: "+result+" ordini consegnati</span>");
                    countRes++;
                }
            },
            error: function(error)
            {
                $(".error").html("<div class=\"alert alert-danger alert-dismissible fade show\" role=\"alert\"><strong>ERRORE! </strong>"+error.responseText+".</div>");
            }
        });
    }

    if(countRes<=0)
    {
        $(".lista-migliori-camerieri-ordini-consegnati").append("<span class='my-1 p-3 text-center'>Nessun cameriere ha consegnato cocktail al tavolo</span>");
    }
}

Chart.defaults.global.defaultFontColor = "#fff";

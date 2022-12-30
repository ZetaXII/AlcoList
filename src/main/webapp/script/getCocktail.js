function getCocktail(uuid)
{
    let c;
    $.ajax({
        async: false,
        method: "GET",
        crossDomain: true,
        url:"http://localhost:8090/manage-cocktails/get/"+uuid,
        contentType: "application/json; charset=utf-8",
        dataType: "json",

        success:function(cocktail)
        {
            c=cocktail;
        },
        error: function(error)
        {
            alert("generic error"+ error);
        }
    });
    return c;
}

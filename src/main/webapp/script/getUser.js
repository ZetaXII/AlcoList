function getUser(uuid)
{
    let u;
    $.ajax({
        async: false,
        method: "GET",
        crossDomain: true,
        url:"http://localhost:8090/manage-users/get/"+uuid,
        contentType: "application/json; charset=utf-8",
        dataType: "json",

        success:function(user)
        {
            u=user;
        },
        error: function(error)
        {
            alert("generic error"+ error);
        }
    });
    return u;
}
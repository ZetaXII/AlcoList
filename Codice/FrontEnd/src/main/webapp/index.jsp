<!DOCTYPE html>
<html>
<head>
    <script src="script/jquery.js"></script>
    <!-- JavaScript Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
    <!-- CSS Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <link href="style/loginStyle.css" rel="stylesheet">
    <link href="style/base.css" rel="stylesheet">
    <title>Login</title>
</head>
<body>
    <nav class="navbar">
        <div class="container-fluid">
            <span class="mb-0 ml-3 h1 fs-1 titolo">AlcoList</span>
        </div>
    </nav>
    <div class="container">
        <div class="row">
            <div class="col-sm"></div>
            <div class="col-sm-5 form-login mt-5 p-4">
                <h1 class="h1 text-center mb-4">Login</h1>
                <form class="form-group" method="post">
                    <label for="email" class="form-label fs-5">Email</label>
                    <br/>
                    <input type="text" id="email" name="email" class="form-control fs-5">
                    <br/>
                    <label for="password" class="form-label fs-5">Password</label>
                    <br/>
                    <input type="password" id="password" name="password" class="form-control fs-5">
                    <br/>
                    <div class="text-center">
                        <input type="submit" id="login" name="login" value="ACCEDI" class="fs-3 px-4 py-1 btn btn-login mt-4 mb-4 text-center fs-4">
                    </div>
                </form>
            </div>
            <div class="col-sm"></div>
        </div>
    </div>
</body>
<script>
    $("#login").click(function()
    {
        let password= $("#password").val();
        let email= $("#email").val();
        let userModel=
            {
                password: password,
                email: email
            }
        $.ajax({
            async: false,
            method: "POST",
            crossDomain: true,
            url:"http://localhost:8090/manage-users/login",
            dataType: "application/json",
            contentType: "application/json",
            headers: {'Access-Control-Allow-Origin' : "*"},
            data: JSON.stringify(userModel),

            success:function(result)
            {
                alert("success:"+result.toString());
                console.log(result);
            },
            error:function (request, msg)
            {
                console.log(JSON.stringify(userModel));
                alert("error");
            }
        });
    });
</script>
</html>


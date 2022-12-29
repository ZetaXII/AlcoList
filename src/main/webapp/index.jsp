<!DOCTYPE html>
<html>
<head>
    <!-- JavaScript AJAX JQUERY & OTHERS SCRIPT -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.1/jquery.min.js" integrity="sha512-aVKKRRi/Q/YV+4mjoKBsE4x3H+BkegoM/em46NNlCqNTmUYADjBbeNefNxYV7giUp0VxICtqdrbqU7iVaeZNXA==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <script src="https://cdn.jsdelivr.net/npm/jquery.session@1.0.0/jquery.session.min.js"></script>
    <!-- CSS & Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <link href="style/loginStyle.css" rel="stylesheet">
    <link href="style/base.css" rel="stylesheet">
    <!-- Page Title -->
    <title>Benvenuto | Area Login</title>
</head>
<body>
    <nav>
        <div class="logo">
            <i class='bx bx-menu menu-icon'></i>
            <span class="logo-name">AlcoList</span>
        </div>
    </nav>
    <div class="alert alert-danger alert-dismissible error-message alert-error fade" role="alert" style="position:fixed; z-index: 9999; width: 50%; left:25%;">
        <strong id="title-error">ERRORE!</strong><label id="message-error"> Controlla nuovamente i campi e riprova ad accedere.</label>
        <button type="button" class="btn-close"  aria-label="Close"></button>
    </div>

    <div class="container mt-5 pt-5">
        <div class="row justify-content-center">
            <div class="col-xl-5 col-md-8">
                <div class="card b-2 form-login">
                     <div class="card-body">
                         <h1 class="h1 text-center card-title mb-4">Login</h1>
                         <form class="form-group" method="post">
                             <label for="email" class="form-label fs-5">Email</label>
                             <br/>
                             <input type="text" id="email" name="email" class="form-control form-field fs-5">
                             <br/>
                             <label for="password" class="form-label fs-5">Password</label>
                             <br/>
                             <input type="password" id="password" name="password" class="form-control form-field fs-5">
                             <br/>
                             <div class="text-center">
                                 <input type="submit" id="login" name="login" value="ACCEDI" class="fs-3 px-4 py-1 btn btn-login mt-4 mb-4 text-center fs-4">
                             </div>
                         </form>
                     </div>
                </div>
            </div>
        </div>
    </div>
    <!-- CUSTOM JS SCRIPTS -->
    <script src="script/loginJs.js" type="text/javascript"></script>
</body>
</html>


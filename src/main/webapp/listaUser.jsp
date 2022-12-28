<!DOCTYPE html>
<html>
<head>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.1/jquery.min.js" integrity="sha512-aVKKRRi/Q/YV+4mjoKBsE4x3H+BkegoM/em46NNlCqNTmUYADjBbeNefNxYV7giUp0VxICtqdrbqU7iVaeZNXA==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <!-- JavaScript Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/jquery.session@1.0.0/jquery.session.min.js"></script>
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
 			<%@include file="navBar.jsp"%>
 			    <div class="container-fluid" style="padding-left:250px">
		            <div class="col-sm"></div>
		            <div class="col-sm-12 form-login mt-5 p-4">
		                <h1 class="h2 mb-4">Nome Cognome</h1>
		                    <label for="ruolo" class="form-label fs-7">Ruolo</label>
		                    <br/>
		            </div>
		            <div class="col-sm text-light" id="label"></div>
       		 	</div>
        </div>
    </div>
</body>
<script src="script/userList.js"></script>
</html>


<html>
<head>
    <title>Title</title>
    <link href="style/base.css" rel="stylesheet">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.1/jquery.min.js" integrity="sha512-aVKKRRi/Q/YV+4mjoKBsE4x3H+BkegoM/em46NNlCqNTmUYADjBbeNefNxYV7giUp0VxICtqdrbqU7iVaeZNXA==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <!-- JavaScript Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/jquery.session@1.0.0/jquery.session.min.js"></script>
    <!-- CSS -->
    <link href="style/provaStyle.css" rel="stylesheet">
</head>
<body>
    <%@include file="NB.jsp"%>
    <div class="container main-section mt-4">
        <h1 class="h1 text-light hello text-center">AO </h1>
        <div class="row row-cols-1 row-cols-md-3 g-4 m-2 p-1">
            <div class="col">
                <div class="card text-white">
                    <img src="https://media.istockphoto.com/id/1288629668/it/vettoriale/sviluppo-di-applicazioni-mobili.jpg?s=612x612&w=is&k=20&c=DlyYmzNPOnaYqfwn8t3KJPEF7JuSljsHN7DUSdTqKaw=" class="card-img" alt="...">
                    <div class="card-img-overlay">
                        <h5 class="card-title">Gestisci utenti</h5>
                    </div>
                </div>
            </div>
            <div class="col">
                <div class="card text-white">
                    <img src="https://media.istockphoto.com/id/1288629668/it/vettoriale/sviluppo-di-applicazioni-mobili.jpg?s=612x612&w=is&k=20&c=DlyYmzNPOnaYqfwn8t3KJPEF7JuSljsHN7DUSdTqKaw=" class="card-img" alt="...">
                    <div class="card-img-overlay">
                        <h5 class="card-title">Gestisci utenti</h5>
                    </div>
                </div>
            </div>
            <div class="col">
                <div class="card text-white">
                    <img src="https://media.istockphoto.com/id/1288629668/it/vettoriale/sviluppo-di-applicazioni-mobili.jpg?s=612x612&w=is&k=20&c=DlyYmzNPOnaYqfwn8t3KJPEF7JuSljsHN7DUSdTqKaw=" class="card-img" alt="...">
                    <div class="card-img-overlay">
                        <h5 class="card-title">Gestisci utenti</h5>
                    </div>
                </div>
            </div>
            <div class="col">
                <div class="card text-white">
                    <img src="https://media.istockphoto.com/id/1288629668/it/vettoriale/sviluppo-di-applicazioni-mobili.jpg?s=612x612&w=is&k=20&c=DlyYmzNPOnaYqfwn8t3KJPEF7JuSljsHN7DUSdTqKaw=" class="card-img" alt="...">
                    <div class="card-img-overlay">
                        <h5 class="card-title">Gestisci utenti</h5>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
<script>
    $(document).ready(function()
    {
        $(".hello").append(surname.toUpperCase()+" "+name.toUpperCase());
        //console.log(roles);
    });
</script>
</html>

<!DOCTYPE html>
<!-- Coding by CodingLab | www.codinglabweb.com -->
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.1/jquery.min.js" integrity="sha512-aVKKRRi/Q/YV+4mjoKBsE4x3H+BkegoM/em46NNlCqNTmUYADjBbeNefNxYV7giUp0VxICtqdrbqU7iVaeZNXA==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <!-- JavaScript Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
    <!-- CSS Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <link href='https://unpkg.com/boxicons@2.1.1/css/boxicons.min.css' rel='stylesheet'>
    <link href="style/navBarStyle.css" rel="stylesheet">
    <link href="style/base.css" rel="stylesheet">
</head>
<body>
    <div class="sidebar">
        <div class="logo-details">
            <!--<i class='bx bxl-c-plus-plus icon'></i>-->
            <img src="img/logo-white.png" width="42" height="42" class="icon">
            <div class="logo_name fs-1">AlcoList</div>
            <i class='bx bx-menu' id="btn" ></i>
        </div>
        <ul class="nav-list p-0">
            <li>
                <i class='bx bx-search' ></i>
                <input type="text" placeholder="Search...">
                <span class="tooltip">Search</span>
            </li>
            <li>
                <a href="#">
                    <i class='bx bx-grid-alt'></i>
                    <span class="links_name">Dashboard</span>
                </a>
                <span class="tooltip">Dashboard</span>
            </li>
            <li>
                <a href="#">
                    <i class='bx bx-user' ></i>
                    <span class="links_name">Dipendenti</span>
                </a>
                <span class="tooltip">Dipendenti</span>
            </li>
            <li>
                <a href="#">
                    <i class='bx bx-food-menu' ></i>
                    <span class="links_name">Men&ugrave;</span>
                </a>
                <span class="tooltip">Men&ugrave;</span>
            </li>
            <li>
                <a href="#">
                    <i class='bx bx-cart-alt' ></i>
                    <span class="links_name">Ordini</span>
                </a>
                <span class="tooltip">Ordini</span>
            </li>
            <li>
                <a href="#">
                    <i class='bx bx-pie-chart-alt-2' ></i>
                    <span class="links_name">Statistiche</span>
                </a>
                <span class="tooltip">Statistiche</span>
            </li>
            <li>
                <a href="#">
                    <i class='bx bx-cog' ></i>
                    <span class="links_name">Impostazioni</span>
                </a>
                <span class="tooltip">Impostazioni</span>
            </li>
            <li class="profile">
                <div class="profile-details">
                    <!--<img src="profile.jpg" alt="profileImg">-->
                    <div class="name_job">
                        <div class="name"></div>
                        <div class="job">Finge di amministrare</div>
                    </div>
                </div>
                <i class='bx bx-log-out' id="log_out" ></i>
            </li>
        </ul>
    </div>
    <script>
        let sidebar = document.querySelector(".sidebar");
        let closeBtn = document.querySelector("#btn");
        let searchBtn = document.querySelector(".bx-search");

        closeBtn.addEventListener("click", ()=>{
            sidebar.classList.toggle("open");
            menuBtnChange();//calling the function(optional)
        });

        searchBtn.addEventListener("click", ()=>{ // Sidebar open when you click on the search iocn
            sidebar.classList.toggle("open");
            menuBtnChange(); //calling the function(optional)
        });

        // following are the code to change sidebar button(optional)
        function menuBtnChange() {
            if(sidebar.classList.contains("open")){
                closeBtn.classList.replace("bx-menu", "bx-menu-alt-right");//replacing the iocns class
            }else {
                closeBtn.classList.replace("bx-menu-alt-right","bx-menu");//replacing the iocns class
            }
        }
    </script>
    <script>
        $(document).ready(function()
        {
            let password= sessionStorage.getItem("password");
            let surname= sessionStorage.getItem("surname");
            let r= sessionStorage.getItem("roles");
            let name= sessionStorage.getItem("name");
            let uuid= sessionStorage.getItem("uuid");
            let email= sessionStorage.getItem("email");

            $(".name").text(surname+" "+name);

            let roles= jQuery.parseJSON(r);
            alert(roles.name==="WAITER");
            $(".job").text(roles);
        });
    </script>
</body>
</html>

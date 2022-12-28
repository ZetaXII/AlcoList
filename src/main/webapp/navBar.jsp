<!DOCTYPE html>
<!-- Coding by CodingLab | www.codinglabweb.com -->
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- JavaScript AJAX JQUERY & OTHERS SCRIPT -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.1/jquery.min.js" integrity="sha512-aVKKRRi/Q/YV+4mjoKBsE4x3H+BkegoM/em46NNlCqNTmUYADjBbeNefNxYV7giUp0VxICtqdrbqU7iVaeZNXA==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <!-- CSS & Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <link href='https://unpkg.com/boxicons@2.1.1/css/boxicons.min.css' rel='stylesheet'>
    <link href="style/navBarStyle.css" rel="stylesheet">
    <link href="style/base.css" rel="stylesheet">
</head>
<body>
<nav>
    <div class="logo">
        <i class='bx bx-menu menu-icon'></i>
        <span class="logo-name">AlcoList</span>
        <a href="#" class="user-info p-0 mt-1 text-center mx-3" style="right:20px; position: fixed; transition: 0.2s;">
            <span class="user-name"></span> <span class="badge bg-danger"></span><i class='bx bx-user-circle icon' style="font-size: 30px"></i>
        </a>
    </div>
    <div class="sidebar">
        <div class="sidebar-content mt-0 overflow-auto">
            <ul class="lists">
                <li class="list">
                    <a href="#" class="nav-link">
                        <i class="bx bx-home-alt icon"></i>
                        <span class="link">Dashboard</span>
                    </a>
                </li>
                <li class="list">
                    <a href="#" class="nav-link">
                        <i class="bx bx-bar-chart-alt-2 icon"></i>
                        <span class="link">Revenue</span>
                    </a>
                </li>
                <li class="list">
                    <a href="#" class="nav-link">
                        <i class="bx bx-bell icon"></i>
                        <span class="link">Notifications</span>
                    </a>
                </li>
                <li class="list">
                    <a href="#" class="nav-link">
                        <i class="bx bx-message-rounded icon"></i>
                        <span class="link">Messages</span>
                    </a>
                </li>
                <li class="list">
                    <a href="#" class="nav-link">
                        <i class="bx bx-pie-chart-alt-2 icon"></i>
                        <span class="link">Analytics</span>
                    </a>
                </li>
                <li class="list">
                    <a href="#" class="nav-link">
                        <i class="bx bx-heart icon"></i>
                        <span class="link">Likes</span>
                    </a>
                </li>
                <li class="list">
                    <a href="#" class="nav-link">
                        <i class="bx bx-folder-open icon"></i>
                        <span class="link">Files</span>
                    </a>
                </li>
            </ul>

            <div class="bottom-content text-center">
                <li class="list">
                    <a href="#" class="nav-link log_out">
                        <i class="bx bx-log-out icon"></i>
                        <span class="link">Logout</span>
                    </a>
                </li>
            </div>
        </div>
    </div>
</nav>
    <!-- CUSTOM JS SCRIPTS -->
    <script src="script/loadUserSessionCredentialsJS.js"></script>
    <script src="script/navBarJs.js"></script>
    <script src="script/logoutJS.js"></script>
</body>
</html>

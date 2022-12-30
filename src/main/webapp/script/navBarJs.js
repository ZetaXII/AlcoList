const navBar = document.querySelector("nav"),
    menuBtns = document.querySelectorAll(".menu-icon");

menuBtns.forEach((menuBtn) => {
    menuBtn.addEventListener("click", () => {
        navBar.classList.toggle("open");
    });
});

$(document).ready(function()
{

    let user=name.charAt(0).toUpperCase() + name.slice(1);
    $(".user-name").text(user);
    //$(".user-role").text(mainRole);
});

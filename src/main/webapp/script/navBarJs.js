const navBar = document.querySelector("nav"),
    menuBtns = document.querySelectorAll(".menu-icon");

menuBtns.forEach((menuBtn) => {
    menuBtn.addEventListener("click", () => {
        navBar.classList.toggle("open");
    });
});

$(document).ready(function()
{
    let user=surname+" "+name;
    $(".user-name").text(user.toUpperCase());
    $(".user-role").text(mainRole);
});

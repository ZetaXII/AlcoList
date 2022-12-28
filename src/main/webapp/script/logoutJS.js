function logout()
{
    sessionStorage.clear();
    window.location.href = '../index.jsp';
}

$(".log_out").click(function()
{
    logout();
});

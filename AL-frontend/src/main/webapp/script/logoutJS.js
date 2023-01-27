function logout()
{
    sessionStorage.clear();
    window.location.href = $("#contextPath").val()+'/index.jsp';
}

$(".log_out").click(function()
{
    logout();
});

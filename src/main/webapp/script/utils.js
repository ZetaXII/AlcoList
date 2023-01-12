/*Titolo pagina dinamica*/
if(document.title)
{
    document.getElementById("title").innerHTML= document.title;
}

/*restituisce un array di flavours*/
function getFlavours()
{
    return ["Dolce", "Aspro", "Amaro", "Secco"];
}


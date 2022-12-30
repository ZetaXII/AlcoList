/*prende la query di ricerca e vede se ci sono delle variabili di GET*/
const queryString = window.location.search;
const urlParams = new URLSearchParams(queryString);
/*recupera il valore della variabile uuid se esiste*/
let cocktailUuid = urlParams.get('uuid');
let cocktail= getCocktail(cocktailUuid);

$(".cocktail-name").append(cocktail.name);
$(".cocktail-description").append(cocktail.description);
$(".price").append(cocktail.price+"&euro;");
$(".cocktail-flavour").append(cocktail.flavour);
document.getElementById("cocktail-img").src=cocktail.pathFileImg;

if(cocktail.iba==true)
{
    $(".cocktail-isIBA").append("IBA");
}
else
{
    $(".badge .cocktail-isIBA").toggle("hidden");
}

if(cocktail.alcoholic==true)
{
    $(".cocktail-isAlcoholic").append("Alcolico");
}
else
{
    $(".cocktail-isAlcoholic").append("Analcolico");
}

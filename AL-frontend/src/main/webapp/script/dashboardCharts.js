let cocktailsName = ["Birra Peroni","Aperol Spritz","Vodka Absolute","Sex on the beach","Angelo azzurro"];
let cocktailsValue = [1200,1400,980,567,1233];

new Chart(document.getElementById("bar-chart"), {
    type: 'bar',
    data: {
        labels: [cocktailsName[0],cocktailsName[1],cocktailsName[2],cocktailsName[3],cocktailsName[4]],
        datasets: [
            {
                label: "Cocktail venduti",
                backgroundColor: ["#3e95cd", "#8e5ea2","#3cba9f","#e8c3b9","#c45850"],
                data: [cocktailsValue[0],cocktailsValue[1],cocktailsValue[2],cocktailsValue[3],cocktailsValue[4]],
                borderColor: 'white',
                borderWidth: 2,
                fill: false,
            }
        ]
    },
    options: {
        legend: { display: false },
        title: {
            display: true,
            text: 'Cocktails pi\u016B venduti'
        },
        scales: {
            xAxes: [{
                gridLines: {
                    //color: "#eaeaea",
                    lineWidth: 0.5
                }
            }],
            yAxes: [{
                gridLines: {
                    //color: "#eaeaea",
                    lineWidth: 0.5
                }
            }]
        },
    }
});

new Chart(document.getElementById("pie-chart"), {
    type: 'pie',
    data: {
        labels: [cocktailsName[0],cocktailsName[1],cocktailsName[2],cocktailsName[3],cocktailsName[4]],
        datasets: [{
            label: "Cocktail venduti",
            backgroundColor: ["#3e95cd", "#8e5ea2","#3cba9f","#e8c3b9","#c45850"],
            data: [cocktailsValue[0],cocktailsValue[1],cocktailsValue[2],cocktailsValue[3],cocktailsValue[4]]
        }]
    },
    options: {
        title: {
            display: true,
            text: 'Predicted world population (millions) in 2050'
        }
    }
});

Chart.defaults.global.defaultFontColor = "black";

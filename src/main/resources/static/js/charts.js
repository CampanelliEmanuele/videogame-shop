const baseUrl = 'http://localhost:8080/api/v1';

function getPurchases(variableUrl) {
    fetch(`${baseUrl}/${variableUrl}`)
        .then(response => response.json())
        .then(data => {
            // console.log('ALL PURCHASES:', data);

            const keys = Object.keys(data);
            const values = Object.values(data);

            new Chart("purchasesChart", {
                type: "bar",
                data: {
                    labels: keys,
                    datasets: [{
                        data: values,
                        label: "sold copies",
                        borderWidth: 2
                    }]
                },
                options: {
                    legend: { display: false },
                    title: {
                        display: false,
                        text: "Sold videogames"
                    },
                    scales: {
                        y: {
                            beginAtZero: true
                        }
                    }
                }
            });
        })
        .catch(error => {
            console.error('Si è verificato un errore:', error);
        });
}

async function getData() {
    // Chiamate alle API
    var response = await fetch('http://localhost:8080/api/v1/purchases');
    const purchases = await response.json();
    console.log(purchases);
    var purchasesDates = [];
    purchases.forEach(function(item) {
        purchasesDates.push(item.purchaseDate)
    });
    purchasesDates.sort(function(date1, date2) {
        return new Date(date1) - new Date(date2);
    });
//    console.log(purchasesDates);

    response = await fetch('http://localhost:8080/api/v1/stocks');
    const stocks = await response.json();
    console.log(stocks);
    var stocksDates = [];
    stocks.forEach(function(item) {
        stocksDates.push(item.purchaseDate)
    });
    stocksDates.sort(function(date1, date2) {
        return new Date(date1) - new Date(date2);
    });
//    console.log(stocksDates);

    let transactionsDates = Array.from(new Set([...purchasesDates, ...stocksDates]));
//    console.log(transactionsDates);

    var profits = [];

    transactionsDates.forEach((date) => {
        purchases.forEach(purchase => {
            if (purchase.purchaseDate == date) {
                profits.push(purchase.quantity * purchase.videogame.price);
            }
        });
    });
//    console.log(profits);

    for (let i = 0; i < transactionsDates.length; i++) {
        stocks.forEach(stock => {
            if (transactionsDates[i] == stock.purchaseDate) {
                profits[i] -= stock.price * stock.quantity;
            }
        });
    }
//    console.log(profits);

    new Chart("incomeChart", {
        type: "line",
        data: {
            labels: transactionsDates,
            datasets: [{
                label: "economic income",
                data: profits
            }]
        },
        options: {
            legend: { display: false },
            title: {
                display: false,
                text: "Economic income"
            },
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        }
    });

}

getPurchases('purchases/soldVideogames');
getData();

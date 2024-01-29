 // Funzione per cambiare la modalità
function toggleMode() {
    // Se il body ha la classe dark-mode, passa a light-mode e viceversa
    $('body').toggleClass('dark-mode');
    $('body').toggleClass('light-mode');

    // Cambia l'icona del bottone
    var btn = $('#toggle-mode-btn');
    if (btn.text() === '☀️') {
        btn.text('🌙');
        btn.attr('title', 'Modalità scura');
    } else {
        btn.text('☀️');
        btn.attr('title', 'Modalità chiara');
    }
}

function addClassToBody(className) {
    // Ottieni il tag <body> del documento corrente
    var bodyTag = document.body;

    // Aggiungi la classe specificata al tag <body>
    bodyTag.classList.add(className);
}

addClassToBody('dark-mode');

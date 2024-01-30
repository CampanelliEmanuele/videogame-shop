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

document.addEventListener('DOMContentLoaded', function() {
    // Codice da eseguire dopo il caricamento completo del DOM
    var bodyTag = document.body;
    if (bodyTag) {
        addClassToBody('dark-mode');

        var headers = document.querySelectorAll('header');

        // Itera attraverso ogni header e aggiungi la classe specifica
        headers.forEach(function(header) {
            header.classList.add('mb-3');
        });
    } else {
        console.error('Il tag body non è stato trovato.');
    }
});
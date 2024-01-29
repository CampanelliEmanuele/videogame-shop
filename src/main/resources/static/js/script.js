$(document).ready(function() {
    $('.improved-table').each(function() {
        var table = $(this);
        var columns = [];

        // Trova le intestazioni delle colonne
        table.find('thead th').each(function() {
            columns.push({ "searchable": true }); // Imposta tutte le colonne come ricercabili
        });

        // Inizializza DataTables con le colonne dinamiche
        table.DataTable({
            "language": {
                "search": "Search:",
                'paginate': {
                    'previous': '<span class="fa fa-chevron-left"></span>',
                    'next': '<span class="fa fa-chevron-right"></span>'
                },
                "lengthMenu": 'Show <select class="form-control input-sm">'+
                    '<option value="5">5</option>'+
                    '<option value="10">10</option>'+
                    '<option value="15">15</option>'+
                    '<option value="20">20</option>'+
                    '<option value="25">25</option>'+
                    '<option value="-1">All</option>'+
                    '</select> results'
            },
            "columns": columns
        });
    });
});

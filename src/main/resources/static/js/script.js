$(document).ready(function () {
    $("#button-pesquisar").on("click", function () {
        var value = $("#inputPesquisa").val().toLowerCase();
        $(".table tbody tr").filter(function () {
            $(this).toggle($(this).find('td:nth-child(2)').text().toLowerCase().indexOf(value) > -1);
        });
    });
});
$(document).ready(function () {
    $("#button-pesquisar").on("click", function () {
        var value = $("#inputPesquisa").val().toLowerCase();
        $(".table tbody tr").filter(function () {
            $(this).toggle($(this).find('td:nth-child(2)').text().toLowerCase().indexOf(value) > -1);
        });
    });
});

// Adicione esta função para preparar o cadastro de responsável
function prepararCadastroResponsavel(button) {
    var alunoId = button.getAttribute('data-aluno-id');
    document.getElementById('alunoId').value = alunoId;
}

// Adicione esta função para limpar o formulário de cadastro de responsável quando a modal for fechada
$('#cadastrarResponsavelModal').on('hidden.bs.modal', function () {
    document.getElementById('responsavelForm').reset();
});
$(document).ready(function () {
    $("#button-pesquisar").on("click", function () {
        var value = $("#inputPesquisa").val().toLowerCase();
        $(".table tbody tr").filter(function () {
            $(this).toggle($(this).find('td:nth-child(2)').text().toLowerCase().indexOf(value) > -1);
        });
    });
});

function prepararCadastroResponsavel(button) {
    var alunoId = button.getAttribute('data-aluno-id');
    document.getElementById('alunoId').value = alunoId;
}

$('#cadastrarResponsavelModal').on('hidden.bs.modal', function () {
    document.getElementById('responsavelForm').reset();
});


function confirmarExclusao(button) {
    var nome = button.getAttribute('data-nome');
    var confirmacao = confirm('Tem certeza que deseja excluir o colaborador: ' + nome + '?');

    if (confirmacao) {
        exibirMensagemExclusao();
    }

    return confirmacao;
}

function exibirMensagemExclusao() {
    alert("Cadastro excluído com sucesso!");
}

function confirmarExclusaoAluno(button) {
    var nome = button.getAttribute('data-nome');
    var confirmacao = confirm('Tem certeza que deseja exluir o aluno: ' + nome + '? Esta ação também excluirá os responsáveis cadastrados para este aluno!');

    if (confirmacao) {
        exibirMensagemExclusao();
    }

    return confirmacao;
}

function confirmarExclusaoResponsavel(button) {
    var nome = button.getAttribute('data-nome');
    var confirmacao = confirm('Tem certeza que deseja excluir o responsavel: ' + nome + '?');

    if (confirmacao) {
        exibirMensagemExclusao();
    }

    return confirmacao;
}

function validarCamposAoSalvar() {
    var cpf = document.getElementById('cpf').value;
    var telefone = document.getElementById('telefone').value;

    if (!/^\d{11}$/.test(cpf)) {
        alert('CPF preenchido incorretamente. Por favor, informe os 11 dígitos numéricos.');
        return false;
    }

    if (!/^\d{11}$/.test(telefone)) {
        alert('Telefone preenchido incorretamente. Por favor, informe os 11 dígitos numéricos.');
        return false;
    }

    return true;
}

function prepararVerResponsavel(button) {
    const alunoId = button.getAttribute("data-aluno-id");

    $.ajax({
        url: "/alunos/get-responsaveis/" + alunoId,
        method: "GET",
        success: function (data) {
            $("#responsavelList").html("");

            if (data.length === 0) {
                // Se não houver responsáveis, exibe a mensagem
                $("#mensagemSemResponsavel").show();
            } else {
                // Se houver responsáveis, oculta a mensagem
                $("#mensagemSemResponsavel").hide();

                for (let i = 0; i < data.length; i++) {
                    const responsavel = data[i];
                    const responsavelElement = `
                        <div class="card mb-3">
                            <div class="card-body">
                                <h5 class="card-title">${responsavel.nome}</h5>
                                <p class="card-text">CPF: ${responsavel.cpf}</p>
                                <p class="card-text">Telefone: ${responsavel.telefone}</p>
                                <p class="card-text">Endereço: ${responsavel.endereco}</p>
                            </div>
                        </div>
                    `;
                    $("#responsavelList").append(responsavelElement);
                }
            }

            $("#verResponsavelModal").modal("show");
        },
        error: function (error) {
            console.error(error);
            alert("Ocorreu um erro ao obter os responsáveis.");
        }
    });
}

$(document).ready(function(){
    // Adicione este script para ocultar a mensagem após 4 segundos
    setTimeout(function(){
        $('.alert-success').fadeOut('slow');
    }, 4000);
});
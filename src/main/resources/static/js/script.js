$(document).ready(function () {
    $("#inputPesquisa").on("keyup", function () {
        var value = $(this).val().toLowerCase();
        $(".table tbody tr").filter(function () {
            // Busca o nome do aluno (primeira célula)
            $(this).toggle($(this).find('td:nth-child(1)').text().toLowerCase().indexOf(value) > -1);
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
    var cpf = document.getElementById('cpf').value.replace(/[^\d]/g, '');
    var telefone = document.getElementById('telefone').value.replace(/[^\d]/g, '');

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
                        <div class="card mb-3 shadow-sm border-0">
                            <div class="card-body">
                                <h5 class="card-title text-primary fw-bold">${responsavel.nome}</h5>
                                <p class="card-text mb-1"><strong>CPF:</strong> ${responsavel.cpf}</p>
                                <p class="card-text mb-1"><strong>Telefone:</strong> ${responsavel.telefone}</p>
                                <p class="card-text mb-1"><strong>Endereço:</strong> ${responsavel.endereco}</p>
                            </div>
                            <div class="card-footer bg-white border-0 text-end">
                                <button class="btn btn-sm btn-outline-primary">Ver mais</button>
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


$(document).ready(function () {
    $('#cpf').mask('000.000.000-00');
    $('#telefone').mask('(00) 00000-0000');
});

// Inicializa a máscara de moeda no campo de salário e valor salario
document.addEventListener("DOMContentLoaded", function () {
    var salarioInput = document.getElementById('salario');
    var salarioValue = salarioInput.value;

    // Formata o valor manualmente se necessário
    if (salarioValue) {
        salarioInput.value = parseFloat(salarioValue).toFixed(2).replace('.', ',');
    }

    new Cleave('#salario', {
        numeral: true,
        numeralThousandsGroupStyle: 'thousand', // Ponto como separador de milhar
        numeralDecimalMark: ',', // Vírgula como separador decimal
        delimiter: '.', // Ponto como delimitador
        numeralPositiveOnly: true
    });
});

// Inicializa a máscara de moeda no campo de mensalidade e valor mensalidade
document.addEventListener("DOMContentLoaded", function () {
    var mensalidadenput = document.getElementById('mensalidade');
    var mensalidadeValue = mensalidadenput.value;

    // Formata o valor manualmente se necessário
    if (mensalidadeValue) {
        mensalidadenput.value = parseFloat(mensalidadeValue).toFixed(2).replace('.', ',');
    }

    new Cleave('#mensalidade', {
        numeral: true,
        numeralThousandsGroupStyle: 'thousand', // Ponto como separador de milhar
        numeralDecimalMark: ',', // Vírgula como separador decimal
        delimiter: '.', // Ponto como delimitador
        numeralPositiveOnly: true
    });
});

// Inicializa a máscara de moeda no campo de valor
document.addEventListener("DOMContentLoaded", function () {
    var valorInput = document.getElementById('valor');
    var valorValue = valorInput.value;

    // Formata o valor manualmente se necessário
    if (valorValue) {
        valorInput.value = parseFloat(valorValue).toFixed(2).replace('.', ',');
    }

    new Cleave('#valor', {
        numeral: true,
        numeralThousandsGroupStyle: 'thousand', // Ponto como separador de milhar
        numeralDecimalMark: ',', // Vírgula como separador decimal
        delimiter: '.', // Ponto como delimitador
        numeralPositiveOnly: true
    });
});

$(document).ready(function () {
    // Verifica se há uma mensagem de sucesso no sessionStorage
    if (sessionStorage.getItem('cadastroSucesso') === 'true') {
        $('#cadastroSucessoModal').modal('show');
        sessionStorage.removeItem('cadastroSucesso'); // Remove a mensagem de sucesso após exibir a modal
    }
});

// No script onde você redireciona após o cadastro
function redirectToListaAlunos() {
    sessionStorage.setItem('cadastroSucesso', 'true');
    window.location.href = '/lista-alunos'; // Redireciona para a página onde a modal deve aparecer
}

document.addEventListener("DOMContentLoaded", function () {
    const emailInput = document.getElementById("email");
    const form = document.getElementById("responsavelForm");

    form.addEventListener("submit", function (event) {
        if (!validateEmail(emailInput.value)) {
            event.preventDefault();
            emailInput.classList.add("is-invalid");
        } else {
            emailInput.classList.remove("is-invalid");
        }
    });

    function validateEmail(email) {
        const re = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$/;
        return re.test(email);
    }
});
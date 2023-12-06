package com.dev.nossaescola.controller;

import com.dev.nossaescola.data.AlunoEntity;
import com.dev.nossaescola.data.ColaboradorEntity;
import com.dev.nossaescola.model.Aluno;
import com.dev.nossaescola.model.Colaborador;
import com.dev.nossaescola.model.Responsavel;
import com.dev.nossaescola.service.AlunoService;
import com.dev.nossaescola.service.ColaboradorService;
import com.dev.nossaescola.service.ResponsavelService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class EscolaController {

    @Autowired
    AlunoService alunoService;

    @Autowired
    ColaboradorService colaboradorService;

    @Autowired
    ResponsavelService responsavelService;

    @GetMapping("/lista-alunos")
    public String exibePaginaAlunos(Model model) {
        model.addAttribute("aluno", new Aluno()); // Adiciona o objeto Filme ao modelo
        model.addAttribute("alunos", alunoService.listarTodosAlunos());
        model.addAttribute("responsavel", new Responsavel());
        return "lista-alunos";
    }

    @GetMapping("/colaboradores")
    public String exibePaginaColaboradores(Model model) {
        model.addAttribute("colaborador", new Colaborador()); // Adiciona o objeto Filme ao modelo
        model.addAttribute("colaboradores", colaboradorService.listarTodosColaboradores());
        return "colaboradores";
    }

    @PostMapping("/cadastrar-aluno")
    public String cadastrarAluno(@ModelAttribute AlunoEntity aluno, Model model) {
        alunoService.criarAluno(aluno);
        return "redirect:/lista-alunos";
    }

    @GetMapping("/editar-aluno/{id}")
    public String exibirFormularioEditarAluno(@PathVariable Integer id, Model model) {

        AlunoEntity aluno = alunoService.getAlunoId(id);

        if (aluno != null) {
            model.addAttribute("aluno", aluno);
            return "editar-aluno";
        } else {
            return "redirect:/erro";
        }
    }

    @PostMapping("/salvar-edicao-aluno")
    public String salvarEdicaoAluno(@ModelAttribute AlunoEntity aluno) {
        alunoService.atualizarAluno(aluno.getId(), aluno);
        return "redirect:/lista-alunos";
    }

    @PostMapping("/cadastrar-colaborador")
    public String cadastrarColaborador(@ModelAttribute ColaboradorEntity colaborador, Model model) {
        colaboradorService.criarColaborador(colaborador);
        return "redirect:/colaboradores";
    }

    @GetMapping("/colaboradores/deletar/{id}")
    public String deletarColaborador(@PathVariable(value = "id") Integer id) {
        colaboradorService.deletarColaborador(id);
        return "redirect:/colaboradores";
    }

    @PostMapping("/salvar-edicao")
    public String salvarEdicaoColaborador(@ModelAttribute ColaboradorEntity colaborador) {
        colaboradorService.atualizarColaborador(colaborador.getId(), colaborador);
        return "redirect:/colaboradores";
    }

    @GetMapping("/editar-colaborador/{id}")
    public String exibirFormularioEditarColaborador(@PathVariable Integer id, Model model) {

        ColaboradorEntity colaborador = colaboradorService.getColaboradorId(id);

        if (colaborador != null) {
            model.addAttribute("colaborador", colaborador);
            return "editar-colaborador";
        } else {
            return "redirect:/erro";
        }
    }

    /*public Aluno buscarAlunoPorId(int alunoId) {
        for (Aluno aluno : alunos) {
            if (aluno.getId() == alunoId) {
                return aluno;
            }
        }
        return null;
    }

    @PostMapping("/cadastrar-responsavel")
    public String cadastrarResponsavel(@ModelAttribute Responsavel responsavel, @RequestParam("alunoId") String alunoId, Model model) {
        // Convertendo o alunoId para int
        int alunoIdInt = Integer.parseInt(alunoId);
        Aluno aluno = buscarAlunoPorId(alunoIdInt);

        if (aluno != null) {
            responsavel.setAluno(aluno);

            int novoResponsavelId = responsaveis.size() + 1;
            responsavel.setId(novoResponsavelId);

            Responsavel novoResponsavel = new Responsavel();
            novoResponsavel.setNome(responsavel.getNome());
            novoResponsavel.setCpf(responsavel.getCpf());
            novoResponsavel.setTelefone(responsavel.getTelefone());
            novoResponsavel.setEndereco(responsavel.getEndereco());
            novoResponsavel.setAluno(aluno);

            responsaveis.add(novoResponsavel);

            return "redirect:/alunos";
        } else {
            return "redirect:/erro";
        }
    }*/
}

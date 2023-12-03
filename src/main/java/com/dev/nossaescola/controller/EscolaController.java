package com.dev.nossaescola.controller;

import com.dev.nossaescola.model.Aluno;
import com.dev.nossaescola.model.Colaborador;
import com.dev.nossaescola.model.Responsavel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EscolaController {

    private List<Aluno> alunos = new ArrayList<>();  // Lista de alunos
    private List<Colaborador> colaboradores = new ArrayList<>();  // Lista de colabores
    private List<Responsavel> responsaveis = new ArrayList<>(); // Lista para armazenar responsaveis

    @GetMapping("/mensalidades")
    public String mostraPaginaMensalidades() {
        return "mensalidades";
    }

    @GetMapping("/responsaveis")
    public String mostraPaginaResonsaveis() {
        return "responsaveis";
    }

    @GetMapping("/alunos")
    public String exibePaginaAlunos(Model model) {
        model.addAttribute("aluno", new Aluno());  // Adiciona um objeto Aluno ao modelo
        model.addAttribute("alunos", alunos);  // Adiciona a lista de alunos ao modelo
        model.addAttribute("responsavel", new Responsavel());
        return "alunos";
    }

    @GetMapping("/colaboradores")
    public String exibePaginaColaboradores(Model model) {
        model.addAttribute("colaborador", new Colaborador());
        model.addAttribute("colaboradores", colaboradores);
        return "colaboradores";
    }

    @PostMapping("/cadastrar-aluno")
    public String cadastrarAluno(@ModelAttribute Aluno aluno, Model model) {
        model.addAttribute("aluno", new Aluno());
        aluno.setId(alunos.size() + 1);
        alunos.add(aluno);
        return "redirect:/alunos";
    }

    @PostMapping("/cadastrar-colaborador")
    public String cadastrarColaborador(@ModelAttribute Colaborador colaborador, Model model) {
        model.addAttribute("colaborador", new Colaborador());
        colaborador.setId(colaboradores.size() + 1);
        colaboradores.add(colaborador);
        return "redirect:/colaboradores";
    }

    public Aluno buscarAlunoPorId(int alunoId) {
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
    }
}

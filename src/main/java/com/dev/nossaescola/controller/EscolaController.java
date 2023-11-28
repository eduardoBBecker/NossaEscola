package com.dev.nossaescola.controller;

import com.dev.nossaescola.model.Aluno;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class EscolaController {

    private List<Aluno> alunos = new ArrayList<>();  // Lista de alunos

    @GetMapping("/alunos")
    public String exibePaginaAlunos(Model model) {
        model.addAttribute("aluno", new Aluno());  // Adiciona um objeto Aluno ao modelo
        model.addAttribute("alunos", alunos);  // Adiciona a lista de alunos ao modelo
        return "alunos";
    }

    @GetMapping("/colaboradores")
    public String exibePaginaColaboradores() {
        return "colaboradores";
    }

    @GetMapping("/cadastro-aluno")
    public String exibeFormularioCadastroAluno(Model model) {
        model.addAttribute("alunoForm", new Aluno());
        return "cadastro-aluno";
    }

    @PostMapping("/cadastrar-aluno")
    public String cadastrarAluno(@ModelAttribute Aluno aluno, Model model) {
        model.addAttribute("aluno", new Aluno());
        aluno.setId(alunos.size() + 1);
        alunos.add(aluno);
        return "redirect:/alunos";
    }
}

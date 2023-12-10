package com.dev.nossaescola.controller;

import com.dev.nossaescola.data.AlunoEntity;
import com.dev.nossaescola.data.ColaboradorEntity;
import com.dev.nossaescola.data.ResponsavelEntity;
import com.dev.nossaescola.model.Aluno;
import com.dev.nossaescola.model.Colaborador;
import com.dev.nossaescola.model.Responsavel;
import com.dev.nossaescola.service.AlunoService;
import com.dev.nossaescola.service.ColaboradorService;
import com.dev.nossaescola.service.ResponsavelService;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String cadastrarAluno(@ModelAttribute AlunoEntity aluno, Model model, RedirectAttributes redirectAttributes) {
        alunoService.criarAluno(aluno);
        redirectAttributes.addFlashAttribute("mensagem", "Cadastro salvo com sucesso!");
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
    public String salvarEdicaoAluno(@ModelAttribute AlunoEntity aluno, RedirectAttributes redirectAttributes) {
        alunoService.atualizarAluno(aluno.getId(), aluno);
        redirectAttributes.addFlashAttribute("mensagem", "Cadastro salvo com sucesso");
        return "redirect:/lista-alunos";
    }

    @PostMapping("/cadastrar-colaborador")
    public String cadastrarColaborador(@ModelAttribute ColaboradorEntity colaborador, RedirectAttributes redirectAttributes) {
        colaboradorService.criarColaborador(colaborador);
        redirectAttributes.addFlashAttribute("mensagem", "Cadastro salvo com sucesso");
        return "redirect:/colaboradores";
    }

    @GetMapping("/colaboradores/deletar/{id}")
    public String deletarColaborador(@PathVariable(value = "id") Integer id) {
        colaboradorService.deletarColaborador(id);
        return "redirect:/colaboradores";
    }

    @PostMapping("/salvar-edicao")
    public String salvarEdicaoColaborador(@ModelAttribute ColaboradorEntity colaborador, RedirectAttributes redirectAttributes) {
        colaboradorService.atualizarColaborador(colaborador.getId(), colaborador);
        redirectAttributes.addFlashAttribute("mensagem", "Cadastro salvo com sucesso!");
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

    @GetMapping("/responsaveis")
    public String exibePaginaResponsaveis(Model model) {
        model.addAttribute("responsavel", new Responsavel()); // Adiciona o objeto Filme ao modelo
        model.addAttribute("responsaveis", responsavelService.listarTodosResponsaveis());
        return "responsaveis";
    }

    @PostMapping("/cadastrar-responsavel")
    public String cadastrarResponsavel(@ModelAttribute ResponsavelEntity responsavel, @RequestParam("alunoId") int alunoId, RedirectAttributes redirectAttributes) {
        AlunoEntity aluno = alunoService.getAlunoId(alunoId);

        if (aluno != null) {
            responsavel.setAluno(aluno);
            responsavelService.criarResponsavel(responsavel);
            redirectAttributes.addFlashAttribute("mensagem", "Cadastro salvo com sucesso!");
            return "redirect:/lista-alunos";
        } else {
            return "redirect:/erro";
        }
    }

    @GetMapping("/responsaveis/deletar/{id}")
    public String deletarResponsavel(@PathVariable(value = "id") Integer id) {
        responsavelService.deletarResponsavel(id);
        return "redirect:/responsaveis";
    }

    @PostMapping("/salvar-edicao-responsavel")
    public String salvarEdicaoResponsavel(@ModelAttribute ResponsavelEntity responsavel, RedirectAttributes redirectAttributes) {
        responsavelService.atualizarResponsavel(responsavel.getId(), responsavel);
        redirectAttributes.addFlashAttribute("mensagem", "Cadastro salvo com sucesso!");
        return "redirect:/responsaveis";
    }

    @GetMapping("/editar-responsavel/{id}")
    public String exibirFormularioEditarResponsavel(@PathVariable Integer id, Model model) {

        ResponsavelEntity responsavel = responsavelService.getResponsavelId(id);

        if (responsavel != null) {
            model.addAttribute("responsavel", responsavel);
            return "editar-responsavel";
        } else {
            return "redirect:/erro";
        }
    }

    @GetMapping("/alunos/deletar/{id}")
    public String deletarAluno(@PathVariable(value = "id") Integer id) {
        // Busca o aluno pelo ID
        AlunoEntity aluno = alunoService.getAlunoId(id);

        if (aluno != null) {
            // Busca os responsáveis vinculados a esse aluno
            List<ResponsavelEntity> responsaveis = responsavelService.buscarResaponsalvelPorIdAluno(id);

            // Exclui os responsáveis
            for (ResponsavelEntity responsavel : responsaveis) {
                responsavelService.deletarResponsavel(responsavel.getId());
            }

            // Exclui o aluno
            alunoService.deletarAluno(id);

            return "redirect:/lista-alunos";
        } else {
            return "redirect:/erro";
        }
    }

    @GetMapping("/relatorio-mensalidades")
    public String exibirRelatorioMensalidades(Model model) {
        List<AlunoEntity> alunos = alunoService.listarTodosAlunos();
        Double totalMensalidades = alunoService.calcularTotalMensalidades(alunos);

        model.addAttribute("alunos", alunos);
        model.addAttribute("totalMensalidades", totalMensalidades);

        return "relatorio-mensalidades";
    }

    @GetMapping("/alunos/get-responsaveis/{id}")
    @ResponseBody
    public List<ResponsavelEntity> getResponsaveisPorAluno(@PathVariable Integer id) {
        return responsavelService.buscarResaponsalvelPorIdAluno(id);
    }
}

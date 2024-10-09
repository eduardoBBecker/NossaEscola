package com.dev.nossaescola.controller;

import com.dev.nossaescola.data.LancamentoEntity;
import com.dev.nossaescola.model.Lancamento;
import com.dev.nossaescola.service.LancamentoService;
import java.beans.PropertyEditorSupport;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/lancamentos")
public class LancamentoController {

    @Autowired
    LancamentoService lancamentoService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Double.class, "valor", new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                if (text == null || text.trim().isEmpty()) {
                    setValue(null);
                } else {
                    String valorFormatado = text.replace(".", "").replace(",", ".");
                    setValue(Double.valueOf(valorFormatado));
                }
            }
        });
    }

    @GetMapping
    public String exibePaginaLancamentos(Model model) {
        model.addAttribute("lancamento", new Lancamento());
        return "lancamentos";
    }

    @PostMapping("/salvar-lancamento")
    @ResponseBody
    public Map<String, Object> salvarLancamento(@ModelAttribute LancamentoEntity lancamento) {
        Map<String, Object> response = new HashMap<>();

        try {
            // Lógica para salvar o lançamento
            lancamentoService.salvarLancamento(lancamento);
            // Indicando sucesso na operação
            response.put("sucesso", true);
            response.put("mensagem", "Lançamento salvo com sucesso!");
        } catch (Exception e) {
            // Em caso de erro
            response.put("sucesso", false);
            response.put("mensagem", "Erro ao salvar o lançamento: " + e.getMessage());
        }

        return response;
    }

    @GetMapping("/listar")
    public String listarLancamentos(@RequestParam(value = "dataInicio", required = false) String dataInicio,
            @RequestParam(value = "dataFim", required = false) String dataFim,
            @RequestParam(value = "tipo", required = false) String tipo,
            @RequestParam(value = "categoria", required = false) String categoria,
            Model model) {

        // Aplica filtros conforme parâmetros recebidos
        List<LancamentoEntity> lancamentos = lancamentoService.filtrarLancamentos(dataInicio, dataFim, tipo, categoria);

        // Adiciona a lista filtrada ao modelo
        model.addAttribute("lancamentos", lancamentos);
        return "listar-lancamentos";
    }

}

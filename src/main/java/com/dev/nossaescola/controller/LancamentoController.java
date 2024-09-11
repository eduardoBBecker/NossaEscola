package com.dev.nossaescola.controller;

import com.dev.nossaescola.data.LancamentoEntity;
import com.dev.nossaescola.model.Lancamento;
import com.dev.nossaescola.service.LancamentoService;
import java.beans.PropertyEditorSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
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

    @GetMapping("/lancamentos")
    public String exibePaginaLancamentos(Model model) {
        model.addAttribute("lancamento", new Lancamento());
        return "lancamentos";
    }

    @PostMapping("/salvar-lancamento")
    public String salvarLancamento(@ModelAttribute LancamentoEntity lancamento, RedirectAttributes redirectAttributes) {
        lancamentoService.salvarLancamento(lancamento);
        redirectAttributes.addFlashAttribute("mensagem", "Lançamento salvo com sucesso!");
        return "redirect:/lancamentos";
    }

}

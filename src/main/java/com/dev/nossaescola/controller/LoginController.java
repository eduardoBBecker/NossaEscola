package com.dev.nossaescola.controller;

import com.dev.nossaescola.model.Usuario;
import com.dev.nossaescola.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String logar(Model model, @RequestParam("username") String username, @RequestParam("senha") String senha, HttpSession session) {
        try {
            Usuario usuario = usuarioService.buscarPorUsername(username);

            if (usuario != null && usuario.getSenha().equals(senha)) {
                session.setAttribute("usuarioLogado", usuario);
                return "redirect:/lista-alunos";
            } else {
                model.addAttribute("error", "Usuário ou senha inválidos.");
                return "login";
            }
        } catch (Exception e) {
            model.addAttribute("error", "Erro ao tentar logar.");
            return "login";
        }
    }

}

package com.dev.nossaescola.service;

import com.dev.nossaescola.data.UsuarioEntity;
import com.dev.nossaescola.data.UsuarioRepository;
import com.dev.nossaescola.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario buscarPorUsername(String username) {
        UsuarioEntity usuarioEntity = usuarioRepository.findByUsername(username);

        if (usuarioEntity != null) {
            return new Usuario(usuarioEntity.getId(), usuarioEntity.getUsername(), usuarioEntity.getSenha());
        } else {
            return null;
        }
    }
}

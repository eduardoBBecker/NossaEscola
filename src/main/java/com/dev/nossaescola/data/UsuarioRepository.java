package com.dev.nossaescola.data;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer> {

    public UsuarioEntity findByUsername(String username);

}

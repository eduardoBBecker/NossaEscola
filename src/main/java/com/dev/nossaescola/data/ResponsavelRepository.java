package com.dev.nossaescola.data;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResponsavelRepository  extends JpaRepository<ResponsavelEntity, Integer> {
    
        List<ResponsavelEntity> findByAlunoId(Integer idAluno);
    
}

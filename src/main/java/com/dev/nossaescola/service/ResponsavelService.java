package com.dev.nossaescola.service;

import com.dev.nossaescola.data.ResponsavelEntity;
import com.dev.nossaescola.data.ResponsavelRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResponsavelService {

    @Autowired
    ResponsavelRepository responsavelRepository;

    public ResponsavelEntity criarResponsavel(ResponsavelEntity responsavel) {

        responsavel.setId(null);
        responsavelRepository.save(responsavel);
        return responsavel;
    }

    public ResponsavelEntity getResponsavelId(Integer responsavelId) {

        return responsavelRepository.findById(responsavelId).orElse(null);
    }

    public List<ResponsavelEntity> listarTodosResponsaveis() {

        return responsavelRepository.findAll();
    }

    public List<ResponsavelEntity> buscarResaponsalvelPorIdAluno(Integer idAluno) {
        return responsavelRepository.findByAlunoId(idAluno);
    }

}

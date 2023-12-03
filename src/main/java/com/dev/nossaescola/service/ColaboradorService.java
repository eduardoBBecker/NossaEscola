package com.dev.nossaescola.service;

import com.dev.nossaescola.data.ColaboradorEntity;
import com.dev.nossaescola.data.ColaboradorRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ColaboradorService {

    @Autowired
    ColaboradorRepository colaboradorRepository;

    public ColaboradorEntity criarColaborador(ColaboradorEntity colaborador) {

        colaborador.setId(null);
        colaboradorRepository.save(colaborador);
        return colaborador;
    }

    public ColaboradorEntity getColaboradorId(Integer colaboradorId) {

        return colaboradorRepository.findById(colaboradorId).orElse(null);
    }

    public ColaboradorEntity atualizarColaborador(Integer colaboradorId, ColaboradorEntity colaboradorRequest) {

        ColaboradorEntity colaborador = getColaboradorId(colaboradorId);

        colaborador.setNome(colaboradorRequest.getNome());
        colaborador.setTelefone(colaboradorRequest.getTelefone());
        colaborador.setCpf(colaboradorRequest.getCpf());
        colaborador.setCargo(colaboradorRequest.getCargo());
        colaborador.setSalario(colaboradorRequest.getSalario());

        colaboradorRepository.save(colaborador);

        return colaborador;
    }

    public List<ColaboradorEntity> listarTodosColaboradores() {

        return colaboradorRepository.findAll();
    }

    public void deletarColaborador(Integer colaboradorId) {

        ColaboradorEntity colaborador = getColaboradorId(colaboradorId);
        colaboradorRepository.deleteById(colaborador.getId());
    }

}

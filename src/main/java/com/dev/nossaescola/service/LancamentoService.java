package com.dev.nossaescola.service;

import com.dev.nossaescola.data.LancamentoEntity;
import com.dev.nossaescola.data.LancamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LancamentoService {
    
    @Autowired
    LancamentoRepository lancamentoRepository;

    public LancamentoEntity salvarLancamento(LancamentoEntity lancamento) {

        lancamento.setId(null);
        lancamentoRepository.save(lancamento);
        return lancamento;
    }

}

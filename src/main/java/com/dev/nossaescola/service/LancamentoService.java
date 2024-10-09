package com.dev.nossaescola.service;

import com.dev.nossaescola.data.LancamentoEntity;
import com.dev.nossaescola.data.LancamentoRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
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

    public List<LancamentoEntity> listarLancamentos() {
        return lancamentoRepository.findAll(); // Retorna todos os lançamentos
    }
    
        public List<LancamentoEntity> filtrarLancamentos(String dataInicio, String dataFim, String tipo, String categoria) {
        // Lógica para converter datas e aplicar filtros
        List<LancamentoEntity> lancamentos = lancamentoRepository.findAll();

        if (dataInicio != null && !dataInicio.isEmpty()) {
            // Filtra pela data de início
            LocalDate inicio = LocalDate.parse(dataInicio);
            lancamentos = lancamentos.stream()
                    .filter(l -> l.getDataLanc().isAfter(inicio) || l.getDataLanc().isEqual(inicio))
                    .collect(Collectors.toList());
        }

        if (dataFim != null && !dataFim.isEmpty()) {
            // Filtra pela data de fim
            LocalDate fim = LocalDate.parse(dataFim);
            lancamentos = lancamentos.stream()
                    .filter(l -> l.getDataLanc().isBefore(fim) || l.getDataLanc().isEqual(fim))
                    .collect(Collectors.toList());
        }

        if (tipo != null && !tipo.isEmpty()) {
            // Filtra pelo tipo
            lancamentos = lancamentos.stream()
                    .filter(l -> l.getTipo().equals(tipo))
                    .collect(Collectors.toList());
        }

        if (categoria != null && !categoria.isEmpty()) {
            // Filtra pela categoria
            lancamentos = lancamentos.stream()
                    .filter(l -> l.getCategoria().equals(categoria))
                    .collect(Collectors.toList());
        }

        return lancamentos;
    }
}

package com.dev.nossaescola.service;

import com.dev.nossaescola.data.AlunoEntity;
import com.dev.nossaescola.data.AlunoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlunoService {

    @Autowired
    AlunoRepository alunoRepository;

    public AlunoEntity criarAluno(AlunoEntity aluno) {

        aluno.setId(null);
        alunoRepository.save(aluno);
        return aluno;
    }

    public AlunoEntity getAlunoId(Integer alunoId) {

        return alunoRepository.findById(alunoId).orElse(null);
    }

    public AlunoEntity atualizarAluno(Integer alunoId, AlunoEntity alunoRequest) {

        AlunoEntity aluno = getAlunoId(alunoId);

        aluno.setNome(alunoRequest.getNome());
        aluno.setDataNasc(alunoRequest.getDataNasc());
        aluno.setSexo(alunoRequest.getSexo());
        aluno.setNomeMae(alunoRequest.getNomeMae());
        aluno.setTurno(alunoRequest.getTurno());
        aluno.setMensalidade(alunoRequest.getMensalidade());
        
        alunoRepository.save(aluno);

        return aluno;
    }

    public List<AlunoEntity> listarTodosAlunos() {

        return alunoRepository.findAll();
    }

    public void deletarAluno(Integer alunoId) {

        AlunoEntity aluno = getAlunoId(alunoId);
        alunoRepository.deleteById(aluno.getId());
    }

}

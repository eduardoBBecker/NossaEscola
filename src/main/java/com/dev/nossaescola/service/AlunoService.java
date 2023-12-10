package com.dev.nossaescola.service;

import com.dev.nossaescola.data.AlunoEntity;
import com.dev.nossaescola.data.AlunoRepository;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
        List<AlunoEntity> alunos = alunoRepository.findAll();

        SimpleDateFormat formatoOriginal = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat formatoDesejado = new SimpleDateFormat("dd/MM/yyyy");

        for (AlunoEntity aluno : alunos) {
            if (aluno.getDataNasc() != null) {
                try {
                    String dataFormatada = formatoDesejado.format(formatoOriginal.parse(aluno.getDataNasc()));
                    aluno.setDataNasc(dataFormatada);
                } catch (ParseException e) {
                    // Lidar com a exceção, se necessário
                    e.printStackTrace();
                }
            }
        }

        return alunos;
    }

    public void deletarAluno(Integer alunoId) {

        AlunoEntity aluno = getAlunoId(alunoId);
        alunoRepository.deleteById(aluno.getId());
    }

    public Double calcularTotalMensalidades(List<AlunoEntity> alunos) {
        return alunos.stream()
                .mapToDouble(AlunoEntity::getMensalidade)
                .sum();
    }

}

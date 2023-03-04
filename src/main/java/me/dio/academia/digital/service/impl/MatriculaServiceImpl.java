package me.dio.academia.digital.service.impl;

import me.dio.academia.digital.entity.Aluno;
import me.dio.academia.digital.entity.Matricula;
import me.dio.academia.digital.entity.form.MatriculaForm;
import me.dio.academia.digital.repository.AlunoRepository;
import me.dio.academia.digital.repository.MatriculaRepository;
import me.dio.academia.digital.service.IMatriculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatriculaServiceImpl implements IMatriculaService {

    @Autowired
    private MatriculaRepository matriculaRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    @Override
    public Matricula create(MatriculaForm form) {
        Aluno aluno = alunoRepository.getById(form.getAlunoId());
        Matricula matricula = new Matricula();
        matricula.setAluno(aluno);
        return matriculaRepository.save(matricula);
    }

    @Override
    public Matricula get(Long id) {
        return matriculaRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Matricula> getAll(String bairro) {
        if(bairro ==  null)
            return matriculaRepository.findAll();

        return matriculaRepository.findByAlunoBairro(bairro);
    }

    @Override
    public void delete(Long id) {
        matriculaRepository.deleteById(id);
    }
}

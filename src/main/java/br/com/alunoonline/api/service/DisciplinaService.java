package br.com.alunoonline.api.service;

import br.com.alunoonline.api.model.Disciplina;
import br.com.alunoonline.api.model.Professor;
import br.com.alunoonline.api.model.dto.DisciplinaDto;
import br.com.alunoonline.api.repository.DisciplinaRepository;
import br.com.alunoonline.api.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class DisciplinaService {

    @Autowired
    DisciplinaRepository repository;

    @Autowired
    ProfessorRepository professorRepository;

    public Disciplina create(Disciplina disciplina) {
        return repository.save(disciplina);
    }

    public List<Disciplina> findByProfessorId(Long professorId) {
        return repository.findByProfessorId(professorId);
    }

    public void update(Long id, DisciplinaDto disciplinaDto) {
        Optional<Disciplina> disciplinaFromDb = repository.findById(id);
        if(disciplinaFromDb.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Disciplina não encontrada");
        }
        Disciplina disciplinaUpdated = disciplinaFromDb.get();

        Optional<Professor> professorFromDb = professorRepository.findById(disciplinaDto.getProfessor_id());
        if(professorFromDb.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Professor não encontrado");
        }
        Professor professorUpdated = professorFromDb.get();

        disciplinaUpdated.getId();
        disciplinaUpdated.setNome(disciplinaDto.getNome());
        disciplinaUpdated.setProfessor(new Professor(
                professorUpdated.getId(),
                professorUpdated.getNome(),
                professorUpdated.getDataNascimento(),
                professorUpdated.getEmail(),
                professorUpdated.getRg(),
                professorUpdated.getCpf(),
                professorUpdated.getAreaDoConhecimento(),
                professorUpdated.getDataDaContratacao()
        ));

        repository.save(disciplinaUpdated);
    }

    public void deleteById(Long id){
        repository.deleteById(id);
    }

    public List<Disciplina> listarDisciplinaPorProfessor(String email){
        return repository.listarDisciplinaPorProfessor(email);
    }
}

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

    public List<Disciplina> findAll() {
        return repository.findAll();
    }

    public Optional<Disciplina> findById(Long id) {
        if (repository.findById(id).isEmpty()) {
            throw new RuntimeException("Disciplina não encontrada");
        }
        return repository.findById(id);
    }

    public List<Disciplina> findByProfessorId(Long professorId) {
        return repository.findByProfessorId(professorId);
    }

    public void update(Long id, DisciplinaDto disciplinaDto) {
        Optional<Disciplina> disciplinaFromDb = repository.findById(id);
        Disciplina disciplinaUpdated = disciplinaFromDb.get();

        Optional<Professor> professorFromDb = professorRepository.findById(disciplinaDto.getProfessor_id());
        Professor professorUpdated = professorFromDb.get();

        disciplinaUpdated.setNome(disciplinaDto.getNome());
        disciplinaUpdated.setProfessor(professorUpdated);

        repository.save(disciplinaUpdated);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public List<Disciplina> listarDisciplinaPorProfessor(String email) {
        return repository.listarDisciplinaPorProfessor(email);
    }
}

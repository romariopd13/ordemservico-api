package com.ordemservicoapi.rest;

import com.ordemservicoapi.models.entity.Exame;
import com.ordemservicoapi.models.entity.Paciente;
import com.ordemservicoapi.models.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/pacientes")
@CrossOrigin("http://localhost:4200")
public class PacienteControlle {
    private final PacienteRepository repository;

    @Autowired
    public PacienteControlle(PacienteRepository repository){
        this.repository = repository;
    }

    @GetMapping
    public List<Paciente> obterTodos(){
        return repository.findAll();
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Paciente salvar(@RequestBody @Valid Paciente paciente){
        return repository.save(paciente);
    }

    @GetMapping("{id}")
    public Paciente carregar(@PathVariable Integer id){
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Paciente não encontrado!"));
    }
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Integer id){
        repository.findById(id).map(paciente -> {
            repository.delete(paciente);
            return Void.TYPE;
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Paciente não encontrado!"));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void alterar(@PathVariable Integer id, @RequestBody @Valid Paciente pacienteAtualizado){
        repository.findById(id).
            map(paciente -> {
                pacienteAtualizado.setId(paciente.getId());
                return repository.save(pacienteAtualizado);
            }).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Paciente não encontrado!"));
    }
}

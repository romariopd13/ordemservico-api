package com.ordemservicoapi.rest;

import com.ordemservicoapi.models.entity.Exame;
import com.ordemservicoapi.models.entity.Medico;
import com.ordemservicoapi.models.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/medicos")
@CrossOrigin("http://localhost:4200")
public class MedicoController {
    private final MedicoRepository repository;

    @Autowired
    public MedicoController(MedicoRepository repository){
        this.repository = repository;
    }

    @GetMapping
    public List<Medico> obterTodos(){
        return repository.findAll();
    }
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Medico salvar(@RequestBody @Valid Medico medico){
        return repository.save(medico);
    }

    @GetMapping("{id}")
    public Medico carregar(@PathVariable Integer id){
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Médico não encontrado!"));
    }
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Integer id){
        repository.findById(id).map(medico -> {
            repository.delete(medico);
            return Void.TYPE;
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Médico não encontrado!"));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void alterar(@PathVariable Integer id, @RequestBody @Valid Medico medicoAtualizado){
        repository.findById(id).
            map(medico -> {
                medicoAtualizado.setId(medico.getId());
                return repository.save(medicoAtualizado);
            }).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Paciente não encontrado!"));
    }
}

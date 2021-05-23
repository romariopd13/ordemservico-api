package com.ordemservicoapi.rest;

import com.ordemservicoapi.models.entity.Exame;
import com.ordemservicoapi.models.entity.Paciente;
import com.ordemservicoapi.models.repository.ExameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/exames")
@CrossOrigin("http://localhost:4200")
public class ExameController {
    private final ExameRepository repository;

    @Autowired
    public ExameController(ExameRepository repository){
        this.repository = repository;
    }

    @GetMapping
    public List<Exame> obterTodos(){
        return repository.findAll();
    }
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Exame salvar(@RequestBody @Valid Exame exame){
        return repository.save(exame);
    }

    @GetMapping("{id}")
    public Exame carregar(@PathVariable Integer id){
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Exame não encontrado!"));
    }
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Integer id){
        repository.findById(id).map(exame -> {
            repository.delete(exame);
            return Void.TYPE;
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Exame não encontrado!"));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void alterar(@PathVariable Integer id, @RequestBody @Valid Exame exameAtualizado){
        repository.findById(id).
            map(exame -> {
                exameAtualizado.setId(exame.getId());
                return repository.save(exameAtualizado);
            }).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Paciente não encontrado!"));
    }
}

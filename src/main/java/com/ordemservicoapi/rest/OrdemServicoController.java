package com.ordemservicoapi.rest;

import com.ordemservicoapi.models.entity.Medico;
import com.ordemservicoapi.models.entity.OrdemServico;
import com.ordemservicoapi.models.repository.OrdemServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/ordemservicos")
@CrossOrigin("http://localhost:4200")
public class OrdemServicoController {
    private final OrdemServicoRepository repository;

    @Autowired
    public OrdemServicoController(OrdemServicoRepository repository){
        this.repository = repository;
    }

    @GetMapping
    public List<OrdemServico> obterTodos(){
        return repository.findAll();
    }
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public OrdemServico salvar(@RequestBody @Valid OrdemServico ordemServico){
        return repository.save(ordemServico);
    }

    @GetMapping("{id}")
    public OrdemServico carregar(@PathVariable Integer id){
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ordem de Servico não encontrado!"));
    }
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Integer id){
        repository.findById(id).map(ordemServico -> {
            repository.delete(ordemServico);
            return Void.TYPE;
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ordem Servico não encontrado!"));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void alterar(@PathVariable Integer id, @RequestBody @Valid OrdemServico ordemServicoAtualizado){
        repository.findById(id).
            map(ordemServico -> {
                ordemServicoAtualizado.setId(ordemServico.getId());
                return repository.save(ordemServicoAtualizado);
            }).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ordem de Servico não encontrado!"));
    }
}

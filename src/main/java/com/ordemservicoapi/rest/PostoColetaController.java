package com.ordemservicoapi.rest;

import com.ordemservicoapi.models.entity.Medico;
import com.ordemservicoapi.models.entity.PostoColeta;
import com.ordemservicoapi.models.repository.PostoColetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/postocoletas")
@CrossOrigin("http://localhost:4200")
public class PostoColetaController {
    private final PostoColetaRepository repository;

    @Autowired
    public PostoColetaController(PostoColetaRepository repository){
        this.repository = repository;
    }

    @GetMapping
    public List<PostoColeta> obterTodos(){
        return repository.findAll();
    }
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public PostoColeta salvar(@RequestBody @Valid PostoColeta postoColeta){
        return repository.save(postoColeta);
    }

    @GetMapping("{id}")
    public PostoColeta carregar(@PathVariable Integer id){
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Posto de coleta não encontrado!"));
    }
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Integer id){
        repository.findById(id).map(postoColeta -> {
            repository.delete(postoColeta);
            return Void.TYPE;
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Posto de coleta não encontrado!"));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void alterar(@PathVariable Integer id, @RequestBody @Valid PostoColeta postoColetaAtualizado){
        repository.findById(id).
            map(postoColeta -> {
                postoColetaAtualizado.setId(postoColeta.getId());
                return repository.save(postoColetaAtualizado);
            }).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Posto de coleta não encontrado!"));
    }
}

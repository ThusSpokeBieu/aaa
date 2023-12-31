package br.edu.lbt.aaa.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.edu.lbt.aaa.models.Pessoa;
import br.edu.lbt.aaa.repository.PessoaRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequiredArgsConstructor
public class Controller {
    
    private final PessoaRepository repository;
    
    @GetMapping()
    public List<Pessoa> get() {
        return repository.findAll();
    }

    @PostMapping()
    public Pessoa post(@RequestBody Pessoa entity) {
        repository.save(entity);
        return entity;
    }

    @PutMapping("{id}")
    public ResponseEntity<?> put(@PathVariable Integer id, @RequestBody Pessoa entity) {
        var pessoa = repository.findById(id);
        
        if (pessoa.isPresent()) {
            entity.setId(id);
            var pessoaAtualizada = repository.save(entity);
            return ResponseEntity.ok().body(pessoaAtualizada);
        }

        var erro = new NotFound("Pessoa não foi encontrada", HttpStatus.NOT_FOUND);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);

    }

    record NotFound(String message, HttpStatus status) {};

    
}

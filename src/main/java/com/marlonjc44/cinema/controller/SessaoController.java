package com.marlonjc44.cinema.controller;

import java.util.List;

import com.marlonjc44.cinema.entity.Sessao;
import com.marlonjc44.cinema.repository.SessaoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({ "/sessoes" })
public class SessaoController {
    private SessaoRepository repository;

    SessaoController(SessaoRepository sessaoRepository) {
        this.repository = sessaoRepository;
    }

    @GetMapping
    public List<?> findAll() {
        return repository.findAll();
    }

    @GetMapping(path = { "/{id}" })
    public ResponseEntity<?> findById(@PathVariable long id) {
        return repository.findById(id).map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Sessao create(@RequestBody Sessao sessao) {
        return repository.save(sessao);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> update(@PathVariable("id") long id, @RequestBody Sessao sessao) {
        return repository.findById(id).map(record -> {
            record.setFilme(sessao.getFilme());
            record.setPreco(sessao.getPreco());
            record.setSalaLotada(sessao.getSalaLotada());
            Sessao updated = repository.save(record);
            return ResponseEntity.ok().body(updated);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = { "/{id}" })
    public ResponseEntity<?> delete(@PathVariable long id) {
        return repository.findById(id).map(record -> {
            repository.deleteById(id);
            return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.notFound().build());
    }

}
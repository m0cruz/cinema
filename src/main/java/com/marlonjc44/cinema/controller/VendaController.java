package com.marlonjc44.cinema.controller;

import java.util.List;

import com.marlonjc44.cinema.entity.Cliente;
import com.marlonjc44.cinema.entity.Sessao;
import com.marlonjc44.cinema.entity.Venda;
import com.marlonjc44.cinema.repository.ClienteRepository;
import com.marlonjc44.cinema.repository.SessaoRepository;
import com.marlonjc44.cinema.repository.VendaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({ "/vendas" })
public class VendaController {
    private VendaRepository repository;
    private ClienteRepository clienteRepository;
    private SessaoRepository sessaoRepository;

    VendaController(VendaRepository vendaRepository, ClienteRepository clienteRepository, SessaoRepository sessaoRepository) {
        this.repository = vendaRepository;
        this.clienteRepository = clienteRepository;
        this.sessaoRepository = sessaoRepository;
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
    public ResponseEntity<?> create(@RequestBody Venda venda) {

        Cliente cliente = clienteRepository.findById(
                venda.getCliente().getId()
        ).orElse(null);

        if (cliente == null) {
            return ResponseEntity.badRequest()
                    .body("Cliente não encontrado.");
        }

        Sessao sessao = sessaoRepository.findById(
                venda.getSessao().getId()
        ).orElse(null);

        if (sessao == null) {
            return ResponseEntity.badRequest()
                    .body("Sessão não encontrada.");
        }

        if (sessao.getSalaLotada()) {
            return ResponseEntity.badRequest()
                    .body("Sessão lotada. Não é possível realizar a compra.");
        }

        venda.setCliente(cliente);
        venda.setSessao(sessao);

        Venda novaVenda = repository.save(venda);
        return ResponseEntity.ok(novaVenda);
    }

    @DeleteMapping(path = { "/{id}" })
    public ResponseEntity<?> delete(@PathVariable long id) {
        return repository.findById(id).map(record -> {
            repository.deleteById(id);
            return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.notFound().build());
    }

}
package io.github.OrlandoBG.demo.controller;

import io.github.OrlandoBG.demo.model.entities.ItemDeEstoque;
import io.github.OrlandoBG.demo.model.services.ItemDeEstoqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/itens-de-Estoque")
public class ItemDeEstoqueController {

    @Autowired
    private ItemDeEstoqueService service;

    @GetMapping
    public ResponseEntity<List<ItemDeEstoque>> obterTodos(){
        List<ItemDeEstoque> listaDeItens = service.obterTodos();
        return ResponseEntity.ok().body(listaDeItens);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ItemDeEstoque> obterPorId(@PathVariable Long id){
        ItemDeEstoque itemDeEstoque = service.obterPorId(id);
        return ResponseEntity.ok().body(itemDeEstoque);
    }

    @PostMapping
    public ResponseEntity<ItemDeEstoque> salvar(@RequestBody ItemDeEstoque itemDeEstoqueRequest){
        ItemDeEstoque itemDeEstoque = service.salvar(itemDeEstoqueRequest);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(itemDeEstoqueRequest.getId()).toUri();
        return  ResponseEntity.created(uri).body(itemDeEstoque);
    }
}

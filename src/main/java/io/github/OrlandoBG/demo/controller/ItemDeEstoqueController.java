package io.github.OrlandoBG.demo.controller;

import io.github.OrlandoBG.demo.model.entities.ItemDeEstoque;
import io.github.OrlandoBG.demo.model.services.ItemDeEstoqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}

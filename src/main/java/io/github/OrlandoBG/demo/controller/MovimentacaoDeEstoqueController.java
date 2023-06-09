package io.github.OrlandoBG.demo.controller;

import io.github.OrlandoBG.demo.model.entities.MovimentacaoDeEstoque;
import io.github.OrlandoBG.demo.model.services.MovimentacaoDeEstoqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/movimentacao-de-estoque")
public class MovimentacaoDeEstoqueController {

    @Autowired
    private MovimentacaoDeEstoqueService service;

    @GetMapping
    public ResponseEntity<List<MovimentacaoDeEstoque>> obterTodos(){
        List<MovimentacaoDeEstoque> movimentacaoDeEstoqueList = service.ObterTodos();
        return ResponseEntity.ok().body(movimentacaoDeEstoqueList);
    }
}

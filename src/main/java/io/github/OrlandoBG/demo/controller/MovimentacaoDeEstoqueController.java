package io.github.OrlandoBG.demo.controller;

import io.github.OrlandoBG.demo.model.entities.MovimentacaoDeEstoque;
import io.github.OrlandoBG.demo.model.services.MovimentacaoDeEstoqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping(value = "/page")
    public ResponseEntity<Page<MovimentacaoDeEstoque>> obterPorItemDeEstoque(@RequestParam(value="page", defaultValue="0") Integer page,
                                                                             @RequestParam (value="size", defaultValue="1") Integer size,
                                                                             @RequestParam (value ="itemDeEstoqueId", defaultValue="0")Long itemDeEstoqueId){
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<MovimentacaoDeEstoque> movimentacaoDeEstoquepagina = service.ObterPorItemDeEstoque(pageRequest,itemDeEstoqueId);
        return ResponseEntity.ok().body(movimentacaoDeEstoquepagina);
    }
    
    @PostMapping
    public ResponseEntity<MovimentacaoDeEstoque> salvar(@RequestBody  MovimentacaoEstoqueDTO movimentacaoDeEstoqueDTO){
        MovimentacaoDeEstoque movimentacaoDeEstoque = service.salvar(movimentacaoDeEstoqueDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(movimentacaoDeEstoqueDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(movimentacaoDeEstoque);
    }

}

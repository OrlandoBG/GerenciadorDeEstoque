package io.github.OrlandoBG.demo.model.services;

import io.github.OrlandoBG.demo.controller.dto.MovimentacaoEstoqueDTO;
import io.github.OrlandoBG.demo.model.entities.ItemDeEstoque;
import io.github.OrlandoBG.demo.model.entities.MovimentacaoDeEstoque;
import io.github.OrlandoBG.demo.model.repository.ItemDeEstoqueRepository;
import io.github.OrlandoBG.demo.model.repository.MovimentacaoDeEstoqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class MovimentacaoDeEstoqueService {

    @Autowired
    private MovimentacaoDeEstoqueRepository repository;

    @Autowired
    private ItemDeEstoqueService itemDeEstoqueService;

    @Autowired
    private ItemDeEstoqueRepository itemDeEstoqueRepository;

    @Transactional(readOnly = true)
    public List<MovimentacaoDeEstoque> ObterTodos(){
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Page<MovimentacaoDeEstoque> ObterPorItemDeEstoque(PageRequest pageRequest, Long itemDeEstoqueId) {
        ItemDeEstoque itemDeEstoque = (itemDeEstoqueId == 0 ) ? null : itemDeEstoqueRepository.getOne(itemDeEstoqueId);
        Page<MovimentacaoDeEstoque> movimentacaoDeEstoquePagina = repository.obterPorItemDeEstoque(pageRequest, itemDeEstoque);
        return movimentacaoDeEstoquePagina;
    }

    
    @Transactional
    public MovimentacaoDeEstoque salvar(@RequestBody MovimentacaoEstoqueDTO movimentacaoDeEstoqueDTO){
        MovimentacaoDeEstoque movimentacaoDeEstoque = new MovimentacaoDeEstoque();
        movimentacaoDeEstoque.setId(movimentacaoDeEstoqueDTO.getId());
        movimentacaoDeEstoque.setTipoDeMovimentacao(movimentacaoDeEstoqueDTO.getTipoDeMovimentacao());
        movimentacaoDeEstoque.setData(LocalDate.parse(movimentacaoDeEstoqueDTO.getData(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        movimentacaoDeEstoque.setQuantidade(movimentacaoDeEstoqueDTO.getQuantidade());
        movimentacaoDeEstoque.setItemDeEstoque(new ItemDeEstoque(movimentacaoDeEstoqueDTO.getItemDeEstoque().getId(), null,null,null,null,null));

        ItemDeEstoque itemDeEstoque = itemDeEstoqueRepository.findById(movimentacaoDeEstoque.getItemDeEstoque().getId()).get();
        Integer quantidadeItemAntiga = itemDeEstoque.getQuantidadeTotal();

        if(movimentacaoDeEstoque.getTipoDeMovimentacao().name() == "ENTRADA"){
            itemDeEstoqueService.atualizar(movimentacaoDeEstoque.getItemDeEstoque().getId(),quantidadeItemAntiga + movimentacaoDeEstoque.getQuantidade());
        }
        if(movimentacaoDeEstoque.getTipoDeMovimentacao().name() == "SAIDA"){
            itemDeEstoqueService.atualizar(movimentacaoDeEstoque.getItemDeEstoque().getId(),quantidadeItemAntiga - movimentacaoDeEstoque.getQuantidade());
        }

        return repository.save(movimentacaoDeEstoque);
    }
}

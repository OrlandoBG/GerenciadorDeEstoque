package io.github.OrlandoBG.demo.model.services;

import io.github.OrlandoBG.demo.model.entities.ItemDeEstoque;
import io.github.OrlandoBG.demo.model.entities.MovimentacaoDeEstoque;
import io.github.OrlandoBG.demo.model.repository.ItemDeEstoqueRepository;
import io.github.OrlandoBG.demo.model.repository.MovimentacaoDeEstoqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MovimentacaoDeEstoqueService {

    @Autowired
    private MovimentacaoDeEstoqueRepository repository;

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

}

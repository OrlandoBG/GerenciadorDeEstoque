package io.github.OrlandoBG.demo.model.services;

import io.github.OrlandoBG.demo.model.entities.ItemDeEstoque;
import io.github.OrlandoBG.demo.model.repository.ItemDeEstoqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ItemDeEstoqueService {
    @Autowired
    private ItemDeEstoqueRepository repository;

    @Transactional(readOnly = true)
    public List<ItemDeEstoque> obterTodos(){
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public ItemDeEstoque obterPorId(Long id){
        return repository.findById(id).get();
    }

    @Transactional
    public ItemDeEstoque salvar(ItemDeEstoque itemDeEstoque){
        return repository.save(itemDeEstoque);
    }

    @Transactional
    public void atualizar(Long id, Integer quantidade){
        ItemDeEstoque itemDeEstoque = repository.findById(id).get();
        itemDeEstoque.setQuantidadeTotal(quantidade);
        repository.save(itemDeEstoque);
    }

}

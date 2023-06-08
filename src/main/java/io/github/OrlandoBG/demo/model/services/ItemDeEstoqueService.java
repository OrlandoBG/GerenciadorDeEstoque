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
}

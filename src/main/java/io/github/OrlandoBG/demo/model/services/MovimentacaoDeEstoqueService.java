package io.github.OrlandoBG.demo.model.services;

import io.github.OrlandoBG.demo.model.entities.MovimentacaoDeEstoque;
import io.github.OrlandoBG.demo.model.repository.MovimentacaoDeEstoqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MovimentacaoDeEstoqueService {

    @Autowired
    private MovimentacaoDeEstoqueRepository repository;

    @Transactional(readOnly = true)
    public List<MovimentacaoDeEstoque> ObterTodos(){
        return repository.findAll();
    }

}

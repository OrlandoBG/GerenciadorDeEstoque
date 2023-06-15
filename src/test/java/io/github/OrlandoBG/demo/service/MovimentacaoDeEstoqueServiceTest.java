package io.github.OrlandoBG.demo.service;

import io.github.OrlandoBG.demo.model.repository.ItemDeEstoqueRepository;
import io.github.OrlandoBG.demo.model.repository.MovimentacaoDeEstoqueRepository;
import io.github.OrlandoBG.demo.model.services.ItemDeEstoqueService;
import io.github.OrlandoBG.demo.model.services.MovimentacaoDeEstoqueService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class MovimentacaoDeEstoqueServiceTest {

    @InjectMocks
    private MovimentacaoDeEstoqueService service;

    @Mock
    private MovimentacaoDeEstoqueRepository repository;

    @Mock
    private ItemDeEstoqueService itemDeEstoqueService;

    @Mock
    private ItemDeEstoqueRepository itemDeEstoqueRepository;


}

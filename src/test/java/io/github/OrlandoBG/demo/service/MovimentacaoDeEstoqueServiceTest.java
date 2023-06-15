package io.github.OrlandoBG.demo.service;

import io.github.OrlandoBG.GerenciadorDeEstoque.model.entidade.ItemDeEstoque;
import io.github.OrlandoBG.GerenciadorDeEstoque.model.entidade.MovimentacaoDeEstoque;
import io.github.OrlandoBG.GerenciadorDeEstoque.model.entidade.TipoDeMovimentacao;
import io.github.OrlandoBG.GerenciadorDeEstoque.model.entidade.dto.MovimentacaoEstoqueDTO;
import io.github.OrlandoBG.GerenciadorDeEstoque.model.repository.ItemDeEstoqueRepository;
import io.github.OrlandoBG.GerenciadorDeEstoque.model.repository.MovimentacaoDeEstoqueRepository;
import io.github.OrlandoBG.GerenciadorDeEstoque.model.service.ItemDeEstoqueService;
import io.github.OrlandoBG.GerenciadorDeEstoque.model.service.MovimentacaoDeEstoqueService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

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

MovimentacaoEstoqueDTO dto;
    MovimentacaoDeEstoque movimentacaoDeEstoque;
    MovimentacaoDeEstoque movimentacaoDeEstoqueResponse;
    LocalDate data ;
    TipoDeMovimentacao tipoDeMovimentacao;
    ItemDeEstoque itemDeEstoque;

    Long id;

    @BeforeEach
    void setUp() throws Exception{
        tipoDeMovimentacao = tipoDeMovimentacao.valueOf("ENTRADA");
        data = LocalDate.parse("02/06/2023", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        id = 1L;

        itemDeEstoque = new ItemDeEstoque(1L,"coca" , "pack", 10, 12, 5);
        movimentacaoDeEstoque = new MovimentacaoDeEstoque(null, tipoDeMovimentacao, data, 1, new ItemDeEstoque(1L,null,null,null,null,null));
        movimentacaoDeEstoqueResponse = new MovimentacaoDeEstoque(1L, tipoDeMovimentacao, data, 1, new ItemDeEstoque(1L,null,null,null,null,null));

        Mockito.when(repository.save(movimentacaoDeEstoque)).thenReturn(movimentacaoDeEstoqueResponse);
        Mockito.when(itemDeEstoqueRepository.findById(id)).thenReturn(Optional.ofNullable(itemDeEstoque));
    }

    @Test
    public void salvarDeveriaRetornarMovimentacaoDeEstoque(){

        dto = new MovimentacaoEstoqueDTO(null, tipoDeMovimentacao, "02/06/2023", 1, new ItemDeEstoque(1L,null,null,null,null,null));

       MovimentacaoDeEstoque movimentacaoDeEstoque = service.salvar(dto);

        Assertions.assertEquals(movimentacaoDeEstoqueResponse, movimentacaoDeEstoque);

    }
}

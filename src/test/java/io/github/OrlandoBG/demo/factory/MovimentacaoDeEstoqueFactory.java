package io.github.OrlandoBG.demo.factory;

import io.github.OrlandoBG.demo.controller.dto.MovimentacaoEstoqueDTO;
import io.github.OrlandoBG.demo.model.entities.ItemDeEstoque;
import io.github.OrlandoBG.demo.model.entities.TipoDeMovimentacao;

public class MovimentacaoDeEstoqueFactory {

    public static MovimentacaoEstoqueDTO criarMovimentacaoDeEstoqueDTO(){
        Long id = 1L;
        TipoDeMovimentacao tipoDeMovimentacao = TipoDeMovimentacao.valueOf("ENTRADA");
        String data = "29/05/2023";
        Integer quantidade = 6;
        ItemDeEstoque itemDeEstoque = new ItemDeEstoque(1L,null,null,null,null,null);

        MovimentacaoEstoqueDTO movimentacaoEstoqueDTO = new MovimentacaoEstoqueDTO();
        movimentacaoEstoqueDTO.setId(id);
        movimentacaoEstoqueDTO.setTipoDeMovimentacao(tipoDeMovimentacao);
        movimentacaoEstoqueDTO.setData(data);
        movimentacaoEstoqueDTO.setQuantidade(quantidade);
        movimentacaoEstoqueDTO.setItemDeEstoque(itemDeEstoque);
        return movimentacaoEstoqueDTO;
    }
}

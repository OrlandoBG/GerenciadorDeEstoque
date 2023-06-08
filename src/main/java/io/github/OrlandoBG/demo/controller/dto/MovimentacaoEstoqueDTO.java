package io.github.OrlandoBG.demo.controller.dto;


import io.github.OrlandoBG.demo.model.entities.ItemDeEstoque;
import io.github.OrlandoBG.demo.model.entities.TipoDeMovimentacao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor


public class MovimentacaoEstoqueDTO {

    private Long id;

    private TipoDeMovimentacao tipoDeMovimentacao;

    private String data;

    private Integer quantidade;


    private ItemDeEstoque itemDeEstoque;



}

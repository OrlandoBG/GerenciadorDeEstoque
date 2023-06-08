package io.github.OrlandoBG.demo.model.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class MovimentacaoDeEstoque implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private TipoDeMovimentacao tipoDeMovimentacao;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate data;

    private Integer quantidade;

    @ManyToOne
    @JoinColumn(name = "item_estoque_id")
    private ItemDeEstoque itemDeEstoque;
}

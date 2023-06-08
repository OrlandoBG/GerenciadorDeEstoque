package io.github.OrlandoBG.demo.model.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class ItemDeEstoque implements  Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String unidadeDeMedida;
    private Integer quantidadeTotal;
    private Integer quantidadeMaxima;
    private Integer quantidadeMinima;

}

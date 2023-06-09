package io.github.OrlandoBG.demo.model.repository;


import io.github.OrlandoBG.demo.model.entities.ItemDeEstoque;
import io.github.OrlandoBG.demo.model.entities.MovimentacaoDeEstoque;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MovimentacaoDeEstoqueRepository extends JpaRepository<MovimentacaoDeEstoque, Long> {

    @Query("SELECT obj" +
            " FROM MovimentacaoDeEstoque obj" +
            " WHERE (:itemDeEstoque IS NULL OR obj.itemDeEstoque = :itemDeEstoque)" +
            " ORDER BY obj.data")
    Page<MovimentacaoDeEstoque> obterPorItemDeEstoque(PageRequest pageRequest, ItemDeEstoque itemDeEstoque);
}

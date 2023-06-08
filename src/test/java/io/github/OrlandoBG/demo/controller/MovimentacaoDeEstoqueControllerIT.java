package io.github.OrlandoBG.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.github.OrlandoBG.demo.controller.dto.MovimentacaoEstoqueDTO;
import io.github.OrlandoBG.demo.factory.MovimentacaoDeEstoqueFactory;
import io.github.OrlandoBG.demo.model.entities.TipoDeMovimentacao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import javax.transaction.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class MovimentacaoDeEstoqueControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void obterTodosDeveriaRetornarUmaListaComMovimentacaoDeEstoque()throws Exception{
        ResultActions result =
                mockMvc.perform(get("/movimentacao-de-estoque")
                        .contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$[0].id").value(1L));
        result.andExpect(jsonPath("$[0].tipoDeMovimentacao").value("ENTRADA"));
        result.andExpect(jsonPath("$[1].id").value(2L));
        result.andExpect(jsonPath("$[1].tipoDeMovimentacao").value("SAIDA"));
    }

    @Test
    public void obterPorItemDeEstoqueDeveriaRetornarUmaPaginaComMovimentacaoDeEstoque()throws Exception{
        ResultActions result =
                mockMvc.perform(get("/movimentacao-de-estoque/page")
                        .contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$.content[0].id").value(1L));
        result.andExpect(jsonPath("$.content[0].tipoDeMovimentacao").value("ENTRADA"));
        result.andExpect(jsonPath("$.content[0].itemDeEstoque.id").value(1L));
        result.andExpect(jsonPath("$.content[0].itemDeEstoque.nome").value("coca cola"));

    }

    @Test
    public void salvarDeveriaRetornarMovimentacaoDeEstoque()throws Exception{
        Long id = 1L;
        TipoDeMovimentacao tipoDeMovimentacao = TipoDeMovimentacao.valueOf("ENTRADA");
        String data = "29/05/2023";

        MovimentacaoEstoqueDTO movimentacaoEstoqueDTO = MovimentacaoDeEstoqueFactory.criarMovimentacaoDeEstoqueDTO();


        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        String jsonBody = mapper.writeValueAsString(movimentacaoEstoqueDTO);

        ResultActions result =
                mockMvc.perform(post("/movimentacao-de-estoque")
                        .content(jsonBody)
                        .contentType(MediaType.APPLICATION_JSON));
        result.andExpect(status().isCreated());
        result.andExpect(jsonPath("$.id").isNotEmpty());
        result.andExpect(jsonPath("$.tipoDeMovimentacao").value(tipoDeMovimentacao.name()));
        result.andExpect(jsonPath("$.data").value(data));
    }

}
